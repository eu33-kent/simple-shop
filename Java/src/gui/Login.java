package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;

import functions.Databaser;
import functions.Globals;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;

public class Login {

	private JFrame frmLogin;
	private JTextField txtUsername;
	private JPasswordField txtPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}
	
	/**
	 * Constructor for logging in after registering
	 */
	public Login(String login) {
		initialize();
		txtUsername.setText(login);
		frmLogin.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLogin = new JFrame();
		frmLogin.getContentPane().setFont(Globals.font);
		frmLogin.setTitle("Login");
		frmLogin.setBounds(100, 100, 450, 400);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		frmLogin.getContentPane().setLayout(gridBagLayout);
		
		JLabel lblLogin = new JLabel("Login to simple-shop");
		lblLogin.setFont(Globals.font(24));
		GridBagConstraints gbc_lblLogin = new GridBagConstraints();
		gbc_lblLogin.insets = new Insets(0, 0, 5, 5);
		gbc_lblLogin.gridx = 1;
		gbc_lblLogin.gridy = 0;
		frmLogin.getContentPane().add(lblLogin, gbc_lblLogin);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(Globals.font);
		GridBagConstraints gbc_lblUsername = new GridBagConstraints();
		gbc_lblUsername.anchor = GridBagConstraints.WEST;
		gbc_lblUsername.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsername.gridx = 1;
		gbc_lblUsername.gridy = 2;
		frmLogin.getContentPane().add(lblUsername, gbc_lblUsername);
		
		txtUsername = new JTextField();
		txtUsername.setFont(Globals.font);
		GridBagConstraints gbc_txtUsername = new GridBagConstraints();
		gbc_txtUsername.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtUsername.insets = new Insets(0, 0, 5, 5);
		gbc_txtUsername.gridx = 1;
		gbc_txtUsername.gridy = 3;
		frmLogin.getContentPane().add(txtUsername, gbc_txtUsername);
		txtUsername.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(Globals.font);
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.anchor = GridBagConstraints.WEST;
		gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblPassword.gridx = 1;
		gbc_lblPassword.gridy = 5;
		frmLogin.getContentPane().add(lblPassword, gbc_lblPassword);
		
		txtPassword = new JPasswordField();
		txtPassword.setFont(Globals.font);
		GridBagConstraints gbc_txtPassword = new GridBagConstraints();
		gbc_txtPassword.insets = new Insets(0, 0, 5, 5);
		gbc_txtPassword.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPassword.gridx = 1;
		gbc_txtPassword.gridy = 6;
		frmLogin.getContentPane().add(txtPassword, gbc_txtPassword);
		txtPassword.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String login = txtUsername.getText(), pass = new String(txtPassword.getPassword());
				if (Databaser.login(login, pass)) { // validate login details
					//Main.main(new String[] { login }); // open main menu
					new Main(login); // open main menu
					// vulnerable to SQL Injection
					//String uid = Databaser.query("SELECT uid FROM users WHERE login = '" + login + "';", new String[0]).get(0).get(0);
					// not vulnerable
					String uid = Databaser.query("SELECT uid FROM users WHERE login = ?;", new String[] {login}).get(0).get(0);
					Databaser.log(uid, "Logged in.");
					frmLogin.dispose(); // close login menu
				} else JOptionPane.showMessageDialog(null, "Incorrect login details!", "Error", JOptionPane.ERROR_MESSAGE);
			}
		});
		btnLogin.setFont(Globals.font);
		GridBagConstraints gbc_btnLogin = new GridBagConstraints();
		gbc_btnLogin.insets = new Insets(0, 0, 5, 5);
		gbc_btnLogin.gridx = 1;
		gbc_btnLogin.gridy = 8;
		frmLogin.getContentPane().add(btnLogin, gbc_btnLogin);
		
		JLabel lblRegister = new JLabel("Don't have an account?");
		lblRegister.setFont(Globals.font);
		GridBagConstraints gbc_lblRegister = new GridBagConstraints();
		gbc_lblRegister.insets = new Insets(0, 0, 5, 5);
		gbc_lblRegister.gridx = 1;
		gbc_lblRegister.gridy = 10;
		frmLogin.getContentPane().add(lblRegister, gbc_lblRegister);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Register();
				frmLogin.dispose();
			}
		});
		btnRegister.setFont(Globals.font);
		GridBagConstraints gbc_btnRegister = new GridBagConstraints();
		gbc_btnRegister.insets = new Insets(0, 0, 5, 5);
		gbc_btnRegister.gridx = 1;
		gbc_btnRegister.gridy = 11;
		frmLogin.getContentPane().add(btnRegister, gbc_btnRegister);
	}
}