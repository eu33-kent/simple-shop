package gui;

import javax.swing.JFrame;
import javax.swing.UIManager;

import functions.Databaser;
import functions.Globals;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class Account {

	private JFrame frmAccount;
	
	private String uid;
	private JTextField txtFirstName;
	private JTextField txtLastName;
	private JTextField txtUsername;

	/**
	 * Create the application.
	 */
	public Account(String uid) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		this.uid = uid;
		initialize();
		frmAccount.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAccount = new JFrame("Account Details");
		frmAccount.setBounds(100, 100, 450, 550);
		frmAccount.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{10, 0, 25, 0, 0, 0, 0, 0, 0, 0, 25, 0, 25, 0, 0, 25, 0, 25, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frmAccount.getContentPane().setLayout(gridBagLayout);
		
		Globals.logoutLog(frmAccount, uid);
		
		// get account details
		String detailsQuery = "SELECT * FROM users WHERE uid = ?";
		String[] detailsParams = new String[] {uid};
		ArrayList<String> details = Databaser.query(detailsQuery, detailsParams).get(0);
		ArrayList<String> columns = Databaser.getColumns(detailsQuery, detailsParams);
		String firstName = details.get(columns.indexOf("firstName"));
		String lastName = details.get(columns.indexOf("lastName"));
		String login = details.get(columns.indexOf("login"));
		String balance = details.get(columns.indexOf("balance"));
		
		JLabel lblAccount = new JLabel("Your account details:");
		lblAccount.setFont(Globals.font);
		GridBagConstraints gbc_lblAccount = Globals.gbc(1,1);
		gbc_lblAccount.gridwidth = 2;
		frmAccount.getContentPane().add(lblAccount, gbc_lblAccount);
		
		JLabel lblFirstName = new JLabel("First name:");
		lblFirstName.setFont(Globals.font);
		GridBagConstraints gbc_lblFirstName = Globals.gbc(1,3);
		gbc_lblFirstName.anchor = GridBagConstraints.WEST;
		gbc_lblFirstName.gridwidth = 2;
		frmAccount.getContentPane().add(lblFirstName, gbc_lblFirstName);
		
		txtFirstName = new JTextField(firstName);
		txtFirstName.setFont(Globals.font);
		GridBagConstraints gbc_txtFirstName = Globals.gbc(1,4);
		gbc_txtFirstName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFirstName.gridwidth = 2;
		frmAccount.getContentPane().add(txtFirstName, gbc_txtFirstName);
		txtFirstName.setColumns(10);
		
		JLabel lblLastName = new JLabel("Last name:");
		lblLastName.setFont(Globals.font);
		GridBagConstraints gbc_lblLastName = Globals.gbc(1,5);
		gbc_lblLastName.anchor = GridBagConstraints.WEST;
		gbc_lblLastName.gridwidth = 2;
		frmAccount.getContentPane().add(lblLastName, gbc_lblLastName);
		
		txtLastName = new JTextField(lastName);
		txtLastName.setFont(Globals.font);
		GridBagConstraints gbc_txtLastName = Globals.gbc(1,6);
		gbc_txtLastName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtLastName.gridwidth = 2;
		frmAccount.getContentPane().add(txtLastName, gbc_txtLastName);
		txtLastName.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(Globals.font);
		GridBagConstraints gbc_lblUsername = Globals.gbc(1,7);
		gbc_lblUsername.anchor = GridBagConstraints.WEST;
		gbc_lblUsername.gridwidth = 2;
		frmAccount.getContentPane().add(lblUsername, gbc_lblUsername);
		
		txtUsername = new JTextField(login);
		txtUsername.setFont(Globals.font);
		GridBagConstraints gbc_txtUsername = Globals.gbc(1,8);
		gbc_txtUsername.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtUsername.gridwidth = 2;
		frmAccount.getContentPane().add(txtUsername, gbc_txtUsername);
		txtUsername.setColumns(10);
		
		JButton btnSave = new JButton("Save Changes");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String first = txtFirstName.getText(), last = txtLastName.getText(), username = txtUsername.getText();
				if (first.length() < 2) JOptionPane.showMessageDialog(null, "Please enter your first name.", "Error", JOptionPane.ERROR_MESSAGE);
				else if (last.length() < 2) JOptionPane.showMessageDialog(null, "Please enter your last name.", "Error", JOptionPane.ERROR_MESSAGE);
				else if (username.length() < 2) JOptionPane.showMessageDialog(null, "This username is too short.", "Error", JOptionPane.ERROR_MESSAGE);
				else if (!username.equals(login)) { // wants to change username
					if (Databaser.query("SELECT uid FROM users WHERE login = ?", new String[] {username}).size() > 0) JOptionPane.showMessageDialog(null, "Username already taken.", "Error", JOptionPane.ERROR_MESSAGE);
					else { // username not taken
						Databaser.modify("UPDATE users SET firstName = ?, lastName = ?, login = ? WHERE uid = ?", new String[] {first, last, username, uid});
						Databaser.log(uid, "Updated their account details.");
						JOptionPane.showMessageDialog(null, "Successfully updated your details.", "Success", JOptionPane.INFORMATION_MESSAGE);
					}
				} else { // same username
					Databaser.modify("UPDATE users SET firstName = ?, lastName = ? WHERE uid = ?", new String[] {first, last, uid});
					Databaser.log(uid, "Updated their account details.");
					JOptionPane.showMessageDialog(null, "Successfully updated your details.", "Success", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnSave.setFont(Globals.font);
		GridBagConstraints gbc_btnSave = Globals.gbc(1,9);
		gbc_btnSave.gridwidth = 2;
		frmAccount.getContentPane().add(btnSave, gbc_btnSave);
		
		JButton btnPassword = new JButton("Change Password");
		btnPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Password(uid, login);
			}
		});
		btnPassword.setFont(Globals.font);
		GridBagConstraints gbc_btnPassword = Globals.gbc(1,11);
		gbc_btnPassword.gridwidth = 2;
		frmAccount.getContentPane().add(btnPassword, gbc_btnPassword);
		
		JLabel lblBalance = new JLabel("Balance: " + balance);
		lblBalance.setFont(Globals.font);
		GridBagConstraints gbc_lblBalance = Globals.gbc(1,13);
		gbc_lblBalance.anchor = GridBagConstraints.EAST;
		frmAccount.getContentPane().add(lblBalance, gbc_lblBalance);
		
		JButton btnFunds = new JButton("Add Funds");
		btnFunds.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Funds(uid, login);
				frmAccount.dispose();
			}
		});
		btnFunds.setFont(Globals.font);
		GridBagConstraints gbc_btnFunds = Globals.gbc(2,13);
		gbc_btnFunds.anchor = GridBagConstraints.WEST;
		frmAccount.getContentPane().add(btnFunds, gbc_btnFunds);
		
		JButton btnGoBack = new JButton("Go Back");
		btnGoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Main(login);
				frmAccount.dispose();
			}
		});
		btnGoBack.setFont(Globals.font);
		GridBagConstraints gbc_btnGoBack = Globals.gbc(1,16);
		gbc_btnGoBack.gridwidth = 2;
		frmAccount.getContentPane().add(btnGoBack, gbc_btnGoBack);
	}
}