package gui;

import javax.swing.JFrame;
import javax.swing.UIManager;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
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
import java.awt.Color;
import javax.swing.border.TitledBorder;

import functions.Databaser;
import functions.Globals;

import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;

public class Search {

	private String uid, login, search;
	private JFrame frmSearch;
	private JTextField txtSearch;
	private JScrollPane scrollPane;

	/**
	 * Create the application.
	 */
	public Search(String uid, String login, String search) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		this.uid = uid;
		this.login = login;
		this.search = search;
		initialize();
		frmSearch.setVisible(true);
		showResults(search);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSearch = new JFrame("Search Products");
		frmSearch.setBounds(100, 100, 650, 650);
		frmSearch.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{25, 0, 0, 0, 0, 0, 25, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		frmSearch.getContentPane().setLayout(gridBagLayout);
		
		JLabel lblSearch = new JLabel("Search for products:");
		lblSearch.setFont(Globals.font(18));
		GridBagConstraints gbc_lblSearch = Globals.gbc(2,1);
		gbc_lblSearch.gridwidth = 3;
		frmSearch.getContentPane().add(lblSearch, gbc_lblSearch);
		
		txtSearch = new JTextField();
		txtSearch.setFont(Globals.font);
		GridBagConstraints gbc_txtSearch = Globals.gbc(2,3);
		gbc_txtSearch.gridwidth = 2;
		gbc_txtSearch.fill = GridBagConstraints.HORIZONTAL;
		frmSearch.getContentPane().add(txtSearch, gbc_txtSearch);
		txtSearch.setColumns(10);
		txtSearch.setText(search);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showResults(txtSearch.getText());
			}
		});
		btnSearch.setFont(Globals.font);
		GridBagConstraints gbc_btnSearch = Globals.gbc(4,3);
		frmSearch.getContentPane().add(btnSearch, gbc_btnSearch);
		
		scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Results (displays 10):", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		scrollPane.getVerticalScrollBar().setUnitIncrement(15);
		GridBagConstraints gbc_scrollPane = Globals.gbc(1,5);
		gbc_scrollPane.gridwidth = 5;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		frmSearch.getContentPane().add(scrollPane, gbc_scrollPane);
		
		JButton btnGoBack = new JButton("Go Back");
		btnGoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Main(login);
				frmSearch.dispose();
			}
		});
		btnGoBack.setFont(Globals.font);
		GridBagConstraints gbc_btnGoBack = Globals.gbc(2,6);
		gbc_btnGoBack.gridwidth = 3;
		frmSearch.getContentPane().add(btnGoBack, gbc_btnGoBack);
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
				JLabel lblImage = new JLabel(imageNew);
				lblImage.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				panelProduct.add(lblImage);
				JPanel panelProductDetails = new JPanel();
				panelProductDetails.setLayout(new BoxLayout(panelProductDetails, BoxLayout.Y_AXIS));
				JLabel lblProduct = new JLabel(product.get(columns.indexOf("name")));
				lblProduct.setFont(Globals.font);
				panelProductDetails.add(lblProduct);
				JLabel lblPrice = new JLabel("£"+product.get(columns.indexOf("price")));
				lblPrice.setFont(Globals.font);
				panelProductDetails.add(lblPrice);
				panelProduct.add(panelProductDetails);
				JButton btnOrder = new JButton("Order");
				btnOrder.setFont(Globals.font);
				btnOrder.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						double price = Double.parseDouble(product.get(columns.indexOf("price")));
						double balance = Double.parseDouble(Databaser.query("SELECT balance FROM users WHERE uid = ?", new String[] {uid}).get(0).get(0));
						if (balance-price >= 0) {
							String pid = product.get(columns.indexOf("pid"));
							Databaser.modify("INSERT INTO orders (uid, pid, timestamp) VALUES (?, ?, NOW())", new String[] {uid, pid});
							Databaser.modify("UPDATE users SET balance = ? WHERE uid = ?", new String[] {String.valueOf(balance-price), uid});
							Databaser.log(uid, "Purchased pid: " + pid);
							JOptionPane.showMessageDialog(null, "Your order was successfully placed.", "Order Placed", JOptionPane.INFORMATION_MESSAGE);
						} else JOptionPane.showMessageDialog(null, "You have insufficient balance to order this product.", "Error", JOptionPane.ERROR_MESSAGE);
					}
				});
				panelProductDetails.add(btnOrder);
				panel.add(panelProduct);
			}
			if (products.size() == 0) {
				JLabel lblNoResults = new JLabel("No Results.");
				lblNoResults.setFont(Globals.font);
				panel.add(lblNoResults);
			}
			Databaser.log(uid, "Searched for '" + query + "'.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}