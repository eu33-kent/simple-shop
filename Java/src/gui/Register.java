package gui;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

import functions.Databaser;


public class Register {

	private JFrame frmRegister;
	private JTextField txtUsername;
	private JLabel lblPassword;
	private JPasswordField txtPassword;
	private JButton btnRegister;
	private JTextField txtFirstName;
	private JLabel lblFirstName;
	private JPasswordField txtPasswordConfirm;
	private JLabel lblPasswordConfirm;
	private JLabel lblLogin;
	private JButton btnLogin;
	private JLabel lblLastName;
	private JTextField txtLastName;

	/**
	 * Create the application.
	 */
	public Register() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		initialize();
		frmRegister.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		// set up the window
		frmRegister = new JFrame();
		frmRegister.setResizable(false);
		frmRegister.setTitle("Register");
		frmRegister.setBounds(100, 100, 500, 700);
		frmRegister.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// create grid bag layout for simpler form element positioning
		GridBagLayout gridBagLayout = new GridBagLayout();
		// set up the layout
		gridBagLayout.columnWidths = new int[] { 100, 300, 100, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		frmRegister.getContentPane().setLayout(gridBagLayout); // apply the layout

		// login title label
		JLabel lblRegister = new JLabel("Register with simple-shop");
		lblRegister.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		GridBagConstraints gbc_lblRegister = new GridBagConstraints();
		gbc_lblRegister.anchor = GridBagConstraints.SOUTH;
		gbc_lblRegister.gridwidth = 3;
		gbc_lblRegister.insets = new Insets(0, 0, 5, 0);
		gbc_lblRegister.gridx = 0;
		gbc_lblRegister.gridy = 0;
		frmRegister.getContentPane().add(lblRegister, gbc_lblRegister);

		// email label
		lblFirstName = new JLabel("First Name");
		lblFirstName.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lblFirstName = new GridBagConstraints();
		gbc_lblFirstName.anchor = GridBagConstraints.WEST;
		gbc_lblFirstName.insets = new Insets(0, 0, 5, 5);
		gbc_lblFirstName.gridx = 1;
		gbc_lblFirstName.gridy = 2;
		frmRegister.getContentPane().add(lblFirstName, gbc_lblFirstName);

		// email input
		txtFirstName = new JTextField();
		txtFirstName.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_txtFirstName = new GridBagConstraints();
		gbc_txtFirstName.insets = new Insets(0, 0, 5, 5);
		gbc_txtFirstName.fill = GridBagConstraints.BOTH;
		gbc_txtFirstName.gridx = 1;
		gbc_txtFirstName.gridy = 3;
		frmRegister.getContentPane().add(txtFirstName, gbc_txtFirstName);
		txtFirstName.setColumns(10);
		
		lblLastName = new JLabel("Last Name");
		lblLastName.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lblLastName = new GridBagConstraints();
		gbc_lblLastName.anchor = GridBagConstraints.WEST;
		gbc_lblLastName.insets = new Insets(0, 0, 5, 5);
		gbc_lblLastName.gridx = 1;
		gbc_lblLastName.gridy = 5;
		frmRegister.getContentPane().add(lblLastName, gbc_lblLastName);
		
		txtLastName = new JTextField();
		txtLastName.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_txtLastName = new GridBagConstraints();
		gbc_txtLastName.insets = new Insets(0, 0, 5, 5);
		gbc_txtLastName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtLastName.gridx = 1;
		gbc_txtLastName.gridy = 6;
		frmRegister.getContentPane().add(txtLastName, gbc_txtLastName);
		txtLastName.setColumns(10);

		// user id label
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lblUsername = new GridBagConstraints();
		gbc_lblUsername.fill = GridBagConstraints.VERTICAL;
		gbc_lblUsername.anchor = GridBagConstraints.WEST;
		gbc_lblUsername.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsername.gridx = 1;
		gbc_lblUsername.gridy = 8;
		frmRegister.getContentPane().add(lblUsername, gbc_lblUsername);

		// user id input element
		txtUsername = new JTextField();
		txtUsername.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_txtUsername = new GridBagConstraints();
		gbc_txtUsername.insets = new Insets(0, 0, 5, 5);
		gbc_txtUsername.fill = GridBagConstraints.BOTH;
		gbc_txtUsername.gridx = 1;
		gbc_txtUsername.gridy = 9;
		frmRegister.getContentPane().add(txtUsername, gbc_txtUsername);
		txtUsername.setColumns(10);

		// password label
		lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.anchor = GridBagConstraints.WEST;
		gbc_lblPassword.fill = GridBagConstraints.VERTICAL;
		gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblPassword.gridx = 1;
		gbc_lblPassword.gridy = 11;
		frmRegister.getContentPane().add(lblPassword, gbc_lblPassword);

		// password input element
		txtPassword = new JPasswordField();
		txtPassword.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_txtPassword = new GridBagConstraints();
		gbc_txtPassword.insets = new Insets(0, 0, 5, 5);
		gbc_txtPassword.fill = GridBagConstraints.BOTH;
		gbc_txtPassword.gridx = 1;
		gbc_txtPassword.gridy = 12;
		frmRegister.getContentPane().add(txtPassword, gbc_txtPassword);
		txtPassword.setColumns(10);

		// password confirm label
		lblPasswordConfirm = new JLabel("Confirm Password");
		lblPasswordConfirm.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lblPasswordConfirm = new GridBagConstraints();
		gbc_lblPasswordConfirm.anchor = GridBagConstraints.WEST;
		gbc_lblPasswordConfirm.insets = new Insets(0, 0, 5, 5);
		gbc_lblPasswordConfirm.gridx = 1;
		gbc_lblPasswordConfirm.gridy = 14;
		frmRegister.getContentPane().add(lblPasswordConfirm, gbc_lblPasswordConfirm);

		// password confirm input
		txtPasswordConfirm = new JPasswordField();
		txtPasswordConfirm.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_txtPasswordConfirm = new GridBagConstraints();
		gbc_txtPasswordConfirm.insets = new Insets(0, 0, 5, 5);
		gbc_txtPasswordConfirm.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPasswordConfirm.gridx = 1;
		gbc_txtPasswordConfirm.gridy = 15;
		frmRegister.getContentPane().add(txtPasswordConfirm, gbc_txtPasswordConfirm);
		txtPasswordConfirm.setColumns(10);

		// register button
		btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { // when clicked
				String login = txtUsername.getText(),
						pass = new String(txtPassword.getPassword()),
						first = txtFirstName.getText(),
						last = txtLastName.getText();
				if (!pass.equals(new String(txtPasswordConfirm.getPassword())))
					JOptionPane.showMessageDialog(null, "Your passwords do not match.", "Error", JOptionPane.ERROR_MESSAGE);
				else if (first.length() < 2) JOptionPane.showMessageDialog(null, "Please enter your first name.", "Error", JOptionPane.ERROR_MESSAGE);
				else if (last.length() < 2) JOptionPane.showMessageDialog(null, "Please enter your last name.", "Error", JOptionPane.ERROR_MESSAGE);
				else {
					String result = Databaser.register(login, pass, first, last);
					if (result.equals("success")) {
						JOptionPane.showMessageDialog(null, "Account created.", "Success", JOptionPane.INFORMATION_MESSAGE);
						// log that a new user was registered
						String uid = Databaser.query("SELECT uid FROM users WHERE login = ?;", new String[] {login}).get(0).get(0);
						Databaser.log(uid, "Registered account.");
						new Login(login);
						frmRegister.dispose();
					} else if (result.equals("fail")) {
						JOptionPane.showMessageDialog(null, "Database Error.", "Error", JOptionPane.ERROR_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, result, "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnRegister.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_btnRegister = new GridBagConstraints();
		gbc_btnRegister.fill = GridBagConstraints.VERTICAL;
		gbc_btnRegister.insets = new Insets(0, 0, 5, 5);
		gbc_btnRegister.gridx = 1;
		gbc_btnRegister.gridy = 17;
		frmRegister.getContentPane().add(btnRegister, gbc_btnRegister);

		// login label
		lblLogin = new JLabel("Already have an account?");
		lblLogin.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lblLogin = new GridBagConstraints();
		gbc_lblLogin.insets = new Insets(0, 0, 5, 5);
		gbc_lblLogin.gridx = 1;
		gbc_lblLogin.gridy = 19;
		frmRegister.getContentPane().add(lblLogin, gbc_lblLogin);

		// login button
		btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Login.main(new String[] {});
				frmRegister.dispose();
			}
		});
		btnLogin.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_btnLogin = new GridBagConstraints();
		gbc_btnLogin.insets = new Insets(0, 0, 5, 5);
		gbc_btnLogin.gridx = 1;
		gbc_btnLogin.gridy = 20;
		frmRegister.getContentPane().add(btnLogin, gbc_btnLogin);
	}
}