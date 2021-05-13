package gui;

import javax.swing.JFrame;
import javax.swing.UIManager;

import functions.Databaser;
import functions.Globals;

import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

public class Orders {

	private JFrame frmOrders;
	private JScrollPane scrollPane;
	
	private String uid;

	/**
	 * Create the application.
	 */
	public Orders(String uid) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		this.uid = uid;
		initialize();
		getOrders();
		frmOrders.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmOrders = new JFrame("Orders");
		frmOrders.setBounds(100, 100, 500, 450);
		frmOrders.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 25, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		frmOrders.getContentPane().setLayout(gridBagLayout);
		
		JLabel lblOrders = new JLabel("Your orders:");
		lblOrders.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lblOrders = Globals.gbc(1, 1);
		frmOrders.getContentPane().add(lblOrders, gbc_lblOrders);
		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = Globals.gbc(1, 2);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		frmOrders.getContentPane().add(scrollPane, gbc_scrollPane);
	}
	
	private void getOrders() {
		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		String ordersQuery = "SELECT * FROM orders o JOIN users u ON o.uid = u.uid JOIN products p ON o.pid = p.pid WHERE o.uid = ?";
		String[] ordersParams = new String[] {uid};
		ArrayList<ArrayList<String>> orders = Databaser.query(ordersQuery, ordersParams);
		ArrayList<String> columns = Databaser.getColumns(ordersQuery, ordersParams);
		for (ArrayList<String> order : orders) {
			try {
				URL url = new URL(order.get(columns.indexOf("imgPath")));
				BufferedImage imageBuffered = ImageIO.read(url);
				ImageIcon imageIcon = new ImageIcon(imageBuffered);
				Image image = imageIcon.getImage();
				image = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
				ImageIcon imageNew = new ImageIcon(image);
				JPanel panelOrder = new JPanel();
				panelOrder.setLayout(new FlowLayout(FlowLayout.LEFT));
				JLabel lblImage = new JLabel(imageNew);
				lblImage.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				panelOrder.add(lblImage);
				JPanel panelOrderDetails = new JPanel();
				panelOrderDetails.setLayout(new BoxLayout(panelOrderDetails, BoxLayout.Y_AXIS));
				JLabel lblName = new JLabel(order.get(columns.indexOf("name")));
				lblName.setFont(Globals.font);
				panelOrderDetails.add(lblName);
				JLabel lblPrice = new JLabel("£"+order.get(columns.indexOf("price")));
				lblPrice.setFont(Globals.font);
				panelOrderDetails.add(lblPrice);
				JLabel lblTime = new JLabel("Time of order: " + order.get(columns.indexOf("timestamp")));
				lblTime.setFont(Globals.font);
				panelOrderDetails.add(lblTime);
				panelOrder.add(panelOrderDetails);
				panel.add(panelOrder);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}