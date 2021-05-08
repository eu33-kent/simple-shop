package gui;


import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JFrame;

import functions.Databaser;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.UIManager;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class Main {

	private JFrame frmMain;
	
	private String login;
	private JTextField txtSearch;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login = args[0];
					Main window = new Main();
					window.frmMain.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 */
	public Main(String login) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		this.login = login;
		initialize();
		frmMain.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMain = new JFrame();
		frmMain.setTitle("Main Menu");
		frmMain.setBounds(100, 100, 600, 500);
		frmMain.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		frmMain.getContentPane().setLayout(gridBagLayout);
		
		// set up the variables
		String detailsQuery = "SELECT uid, firstName, lastName, balance FROM users WHERE login = ?;";
		String[] detailsParams = new String[] {login};
		// vulnerable to SQL Injection
		//ArrayList<String> details = Databaser.query("SELECT uid, firstName, lastName, balance FROM users WHERE login = '" + login + "';", new String[0]).get(0);
		// not vulnerable
		ArrayList<String> details = Databaser.query(detailsQuery, detailsParams).get(0);
		ArrayList<String> columns = Databaser.getColumns(detailsQuery, detailsParams);
		String firstName = details.get(columns.indexOf("firstName"));
		String uid = details.get(columns.indexOf("uid"));
		String balance = details.get(columns.indexOf("balance"));

		// log that the user logged out before closing
		frmMain.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				Databaser.log(uid, "Logged out.");
				//frmMainMenu.dispose();
				System.exit(0);
			}
		});
		
		JLabel lblWelcome = new JLabel("Welcome, " + firstName + ".");
		lblWelcome.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		GridBagConstraints gbc_lblWelcome = new GridBagConstraints();
		gbc_lblWelcome.gridwidth = 2;
		gbc_lblWelcome.insets = new Insets(0, 0, 5, 5);
		gbc_lblWelcome.gridx = 1;
		gbc_lblWelcome.gridy = 0;
		frmMain.getContentPane().add(lblWelcome, gbc_lblWelcome);
		
		JLabel lblBalance = new JLabel("Balance: £" + balance);
		lblBalance.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		GridBagConstraints gbc_lblBalance = new GridBagConstraints();
		gbc_lblBalance.anchor = GridBagConstraints.EAST;
		gbc_lblBalance.insets = new Insets(0, 0, 5, 5);
		gbc_lblBalance.gridx = 1;
		gbc_lblBalance.gridy = 1;
		frmMain.getContentPane().add(lblBalance, gbc_lblBalance);
		
		JButton btnAccount = new JButton("View Account Details");
		btnAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Account(login);
				//Databaser.log(uid, "Viewed account details.");
			}
		});
		
		JButton btnFunds = new JButton("Add Funds");
		btnFunds.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Funds(login);
			}
		});
		btnFunds.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		GridBagConstraints gbc_btnFunds = new GridBagConstraints();
		gbc_btnFunds.anchor = GridBagConstraints.WEST;
		gbc_btnFunds.insets = new Insets(0, 0, 5, 5);
		gbc_btnFunds.gridx = 2;
		gbc_btnFunds.gridy = 1;
		frmMain.getContentPane().add(btnFunds, gbc_btnFunds);
		btnAccount.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		GridBagConstraints gbc_btnAccount = new GridBagConstraints();
		gbc_btnAccount.gridwidth = 2;
		gbc_btnAccount.insets = new Insets(0, 0, 5, 5);
		gbc_btnAccount.gridx = 1;
		gbc_btnAccount.gridy = 2;
		frmMain.getContentPane().add(btnAccount, gbc_btnAccount);
		
		JButton btnOrders = new JButton("View Orders");
		btnOrders.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Orders(login);
				//Databaser.log(uid, "Viewed orders.");
			}
		});
		btnOrders.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		GridBagConstraints gbc_btnOrders = new GridBagConstraints();
		gbc_btnOrders.gridwidth = 2;
		gbc_btnOrders.insets = new Insets(0, 0, 5, 5);
		gbc_btnOrders.gridx = 1;
		gbc_btnOrders.gridy = 3;
		frmMain.getContentPane().add(btnOrders, gbc_btnOrders);
		
		JLabel lblSearch = new JLabel("Search for products:");
		lblSearch.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lblSearch = new GridBagConstraints();
		gbc_lblSearch.gridwidth = 2;
		gbc_lblSearch.insets = new Insets(0, 0, 5, 5);
		gbc_lblSearch.gridx = 1;
		gbc_lblSearch.gridy = 5;
		frmMain.getContentPane().add(lblSearch, gbc_lblSearch);
		
		txtSearch = new JTextField();
		txtSearch.setColumns(1);
		txtSearch.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		GridBagConstraints gbc_txtSearch = new GridBagConstraints();
		gbc_txtSearch.gridwidth = 2;
		gbc_txtSearch.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtSearch.insets = new Insets(0, 0, 5, 5);
		gbc_txtSearch.gridx = 1;
		gbc_txtSearch.gridy = 6;
		frmMain.getContentPane().add(txtSearch, gbc_txtSearch);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String search = txtSearch.getText();
				new Search(search);
				//Databaser.log(uid, "Searched for '" + search + "'");
			}
		});
		btnSearch.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_btnSearch = new GridBagConstraints();
		gbc_btnSearch.anchor = GridBagConstraints.NORTH;
		gbc_btnSearch.gridwidth = 2;
		gbc_btnSearch.insets = new Insets(0, 0, 5, 5);
		gbc_btnSearch.gridx = 1;
		gbc_btnSearch.gridy = 7;
		frmMain.getContentPane().add(btnSearch, gbc_btnSearch);
	}
}