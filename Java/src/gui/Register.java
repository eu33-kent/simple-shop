package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
import functions.Globals;


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
		lblRegister.setFont(Globals.font(24));
		GridBagConstraints gbc_lblRegister = Globals.gbc(0,0);
		gbc_lblRegister.anchor = GridBagConstraints.SOUTH;
		gbc_lblRegister.gridwidth = 3;
		frmRegister.getContentPane().add(lblRegister, gbc_lblRegister);

		// email label
		lblFirstName = new JLabel("First Name");
		lblFirstName.setFont(Globals.font);
		GridBagConstraints gbc_lblFirstName = Globals.gbc(1,2);
		gbc_lblFirstName.anchor = GridBagConstraints.WEST;
		frmRegister.getContentPane().add(lblFirstName, gbc_lblFirstName);

		// email input
		txtFirstName = new JTextField();
		txtFirstName.setFont(Globals.font);
		GridBagConstraints gbc_txtFirstName = Globals.gbc(1,3);
		gbc_txtFirstName.fill = GridBagConstraints.BOTH;
		frmRegister.getContentPane().add(txtFirstName, gbc_txtFirstName);
		txtFirstName.setColumns(10);
		
		lblLastName = new JLabel("Last Name");
		lblLastName.setFont(Globals.font);
		GridBagConstraints gbc_lblLastName = Globals.gbc(1,5);
		gbc_lblLastName.anchor = GridBagConstraints.WEST;
		frmRegister.getContentPane().add(lblLastName, gbc_lblLastName);
		
		txtLastName = new JTextField();
		txtLastName.setFont(Globals.font);
		GridBagConstraints gbc_txtLastName = Globals.gbc(1,6);
		gbc_txtLastName.fill = GridBagConstraints.HORIZONTAL;
		frmRegister.getContentPane().add(txtLastName, gbc_txtLastName);
		txtLastName.setColumns(10);

		// user id label
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(Globals.font);
		GridBagConstraints gbc_lblUsername = Globals.gbc(1,8);
		gbc_lblUsername.fill = GridBagConstraints.VERTICAL;
		gbc_lblUsername.anchor = GridBagConstraints.WEST;
		frmRegister.getContentPane().add(lblUsername, gbc_lblUsername);

		// user id input element
		txtUsername = new JTextField();
		txtUsername.setFont(Globals.font);
		GridBagConstraints gbc_txtUsername = Globals.gbc(1,9);
		gbc_txtUsername.fill = GridBagConstraints.BOTH;
		frmRegister.getContentPane().add(txtUsername, gbc_txtUsername);
		txtUsername.setColumns(10);

		// password label
		lblPassword = new JLabel("Password");
		lblPassword.setFont(Globals.font);
		GridBagConstraints gbc_lblPassword = Globals.gbc(1,11);
		gbc_lblPassword.anchor = GridBagConstraints.WEST;
		gbc_lblPassword.fill = GridBagConstraints.VERTICAL;
		frmRegister.getContentPane().add(lblPassword, gbc_lblPassword);

		// password input element
		txtPassword = new JPasswordField();
		txtPassword.setFont(Globals.font);
		GridBagConstraints gbc_txtPassword = Globals.gbc(1,12);
		gbc_txtPassword.fill = GridBagConstraints.BOTH;
		frmRegister.getContentPane().add(txtPassword, gbc_txtPassword);
		txtPassword.setColumns(10);

		// password confirm label
		lblPasswordConfirm = new JLabel("Confirm Password");
		lblPasswordConfirm.setFont(Globals.font);
		GridBagConstraints gbc_lblPasswordConfirm = Globals.gbc(1,14);
		gbc_lblPasswordConfirm.anchor = GridBagConstraints.WEST;
		frmRegister.getContentPane().add(lblPasswordConfirm, gbc_lblPasswordConfirm);

		// password confirm input
		txtPasswordConfirm = new JPasswordField();
		txtPasswordConfirm.setFont(Globals.font);
		GridBagConstraints gbc_txtPasswordConfirm = Globals.gbc(1,15);
		gbc_txtPasswordConfirm.fill = GridBagConstraints.HORIZONTAL;
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
		btnRegister.setFont(Globals.font);
		GridBagConstraints gbc_btnRegister = Globals.gbc(1,17);
		gbc_btnRegister.fill = GridBagConstraints.VERTICAL;
		frmRegister.getContentPane().add(btnRegister, gbc_btnRegister);

		// login label
		lblLogin = new JLabel("Already have an account?");
		lblLogin.setFont(Globals.font);
		GridBagConstraints gbc_lblLogin = Globals.gbc(1,19);
		frmRegister.getContentPane().add(lblLogin, gbc_lblLogin);

		// login button
		btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Login.main(new String[] {});
				frmRegister.dispose();
			}
		});
		btnLogin.setFont(Globals.font);
		GridBagConstraints gbc_btnLogin = Globals.gbc(1,20);
		frmRegister.getContentPane().add(btnLogin, gbc_btnLogin);
	}
}