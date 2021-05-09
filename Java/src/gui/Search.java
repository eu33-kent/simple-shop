package gui;

import javax.swing.JFrame;
import javax.swing.UIManager;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.Color;
import javax.swing.border.TitledBorder;

import functions.Databaser;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;

public class Search {

	private String uid, search;
	private JFrame frmSearch;
	private JTextField txtSearch;
	private JScrollPane scrollPane;

	/**
	 * Create the application.
	 */
	public Search(String uid, String search) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		this.uid = uid;
		this.search = search;
		initialize();
		frmSearch.setVisible(true);
		showResults(search);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSearch = new JFrame();
		frmSearch.setTitle("Search Products");
		frmSearch.setBounds(100, 100, 650, 650);
		frmSearch.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		frmSearch.getContentPane().setLayout(gridBagLayout);
		
		JLabel lblSearch = new JLabel("Search for products:");
		lblSearch.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		GridBagConstraints gbc_lblSearch = new GridBagConstraints();
		gbc_lblSearch.gridwidth = 3;
		gbc_lblSearch.insets = new Insets(0, 0, 5, 5);
		gbc_lblSearch.gridx = 2;
		gbc_lblSearch.gridy = 1;
		frmSearch.getContentPane().add(lblSearch, gbc_lblSearch);
		
		txtSearch = new JTextField();
		txtSearch.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_txtSearch = new GridBagConstraints();
		gbc_txtSearch.gridwidth = 2;
		gbc_txtSearch.insets = new Insets(0, 0, 5, 5);
		gbc_txtSearch.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtSearch.gridx = 2;
		gbc_txtSearch.gridy = 3;
		frmSearch.getContentPane().add(txtSearch, gbc_txtSearch);
		txtSearch.setColumns(10);
		txtSearch.setText(search);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showResults(txtSearch.getText());
			}
		});
		btnSearch.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_btnSearch = new GridBagConstraints();
		gbc_btnSearch.insets = new Insets(0, 0, 5, 5);
		gbc_btnSearch.gridx = 4;
		gbc_btnSearch.gridy = 3;
		frmSearch.getContentPane().add(btnSearch, gbc_btnSearch);
		
		scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Results (displays 10):", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.getVerticalScrollBar().setUnitIncrement(15);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.gridwidth = 5;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 5;
		frmSearch.getContentPane().add(scrollPane, gbc_scrollPane);
	}
	
	private void showResults(String query) {
		//String productsQuery = "SELECT * FROM products WHERE name LIKE %?%";
		//String[] productsParams = new String[] {query};
		String productsQuery = "SELECT * FROM products WHERE LOWER(name) LIKE LOWER('%" + query + "%') LIMIT 10";
		String[] productsParams = new String[0];
		ArrayList<ArrayList<String>> products = Databaser.query(productsQuery, productsParams);
		ArrayList<String> columns = Databaser.getColumns(productsQuery, productsParams);
		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		try {
			for (ArrayList<String> product : products) {
				URL url = new URL(product.get(columns.indexOf("imgPath")));
				BufferedImage imageBuffered = ImageIO.read(url);
				ImageIcon imageIcon = new ImageIcon(imageBuffered);
				Image image = imageIcon.getImage();
				image = image.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
				ImageIcon imageNew = new ImageIcon(image);
				JPanel panelProduct = new JPanel();
				panelProduct.setLayout(new FlowLayout(FlowLayout.LEFT));
				panelProduct.add(new JLabel(imageNew));
				JPanel panelProductDetails = new JPanel();
				panelProductDetails.setLayout(new BoxLayout(panelProductDetails, BoxLayout.Y_AXIS));
				JLabel lblProduct = new JLabel(product.get(columns.indexOf("name")));
				lblProduct.setFont(new Font("Segoe UI", Font.PLAIN, 14));
				panelProductDetails.add(lblProduct);
				JLabel lblPrice = new JLabel("£"+product.get(columns.indexOf("price")));
				lblPrice.setFont(new Font("Segoe UI", Font.PLAIN, 14));
				panelProductDetails.add(lblPrice);
				panelProduct.add(panelProductDetails);
				JButton btnOrder = new JButton("Order");
				btnOrder.setFont(new Font("Segoe UI", Font.PLAIN, 14));
				addOrderFunction(btnOrder);
				panelProductDetails.add(btnOrder);
				panel.add(panelProduct);
			}
			Databaser.log(uid, "Searched for '" + query + "'.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void addOrderFunction(JButton btn) {
		// TODO
	}
}