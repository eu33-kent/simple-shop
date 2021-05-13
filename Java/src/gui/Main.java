package gui;


import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JFrame;

import functions.Databaser;
import functions.Globals;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.UIManager;

import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class Main {

	private JFrame frmMain;
	
	private String login;
	private JTextField txtSearch;

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
		frmMain = new JFrame("Main Menu");
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
		lblWelcome.setFont(Globals.font(24));
		GridBagConstraints gbc_lblWelcome = Globals.gbc(1,0);
		gbc_lblWelcome.gridwidth = 2;
		frmMain.getContentPane().add(lblWelcome, gbc_lblWelcome);
		
		JLabel lblBalance = new JLabel("Balance: £" + balance);
		lblBalance.setFont(Globals.font(12));
		GridBagConstraints gbc_lblBalance = Globals.gbc(1,1);
		gbc_lblBalance.anchor = GridBagConstraints.EAST;
		frmMain.getContentPane().add(lblBalance, gbc_lblBalance);
		
		JButton btnAccount = new JButton("View Account Details");
		btnAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Account(uid);
				Databaser.log(uid, "Viewed account details.");
			}
		});
		
		JButton btnFunds = new JButton("Add Funds");
		btnFunds.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Funds(uid, login);
				frmMain.dispose();
			}
		});
		btnFunds.setFont(Globals.font(12));
		GridBagConstraints gbc_btnFunds = Globals.gbc(2,1);
		gbc_btnFunds.anchor = GridBagConstraints.WEST;
		frmMain.getContentPane().add(btnFunds, gbc_btnFunds);
		btnAccount.setFont(Globals.font(12));
		GridBagConstraints gbc_btnAccount = Globals.gbc(1,2);
		gbc_btnAccount.gridwidth = 2;
		frmMain.getContentPane().add(btnAccount, gbc_btnAccount);
		
		JButton btnOrders = new JButton("View Orders");
		btnOrders.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Orders(uid);
				Databaser.log(uid, "Viewed orders.");
			}
		});
		btnOrders.setFont(Globals.font(12));
		GridBagConstraints gbc_btnOrders = Globals.gbc(1,3);
		gbc_btnOrders.gridwidth = 2;
		frmMain.getContentPane().add(btnOrders, gbc_btnOrders);
		
		JLabel lblSearch = new JLabel("Search for products:");
		lblSearch.setFont(Globals.font);
		GridBagConstraints gbc_lblSearch = Globals.gbc(1,5);
		gbc_lblSearch.gridwidth = 2;
		frmMain.getContentPane().add(lblSearch, gbc_lblSearch);
		
		txtSearch = new JTextField();
		txtSearch.setColumns(1);
		txtSearch.setFont(Globals.font(18));
		GridBagConstraints gbc_txtSearch = Globals.gbc(1,6);
		gbc_txtSearch.gridwidth = 2;
		gbc_txtSearch.fill = GridBagConstraints.HORIZONTAL;
		frmMain.getContentPane().add(txtSearch, gbc_txtSearch);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String search = txtSearch.getText();
				new Search(uid, login, search);
				frmMain.dispose();
			}
		});
		btnSearch.setFont(Globals.font);
		GridBagConstraints gbc_btnSearch = Globals.gbc(1,7);
		gbc_btnSearch.anchor = GridBagConstraints.NORTH;
		gbc_btnSearch.gridwidth = 2;
		frmMain.getContentPane().add(btnSearch, gbc_btnSearch);
	}
}