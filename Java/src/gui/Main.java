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

public class Main {

	private JFrame frmMain;
	
	private String login;

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
		frmMain.setBounds(100, 100, 450, 300);
		frmMain.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		frmMain.getContentPane().setLayout(gridBagLayout);
		
		// set up the variables
		String detailsQuery = "SELECT uid, firstName, lastName FROM users WHERE login = ?;";
		String[] detailsParams = new String[] {login};
		// vulnerable to SQL Injection
		//ArrayList<String> details = Databaser.query("SELECT uid, firstName, lastName FROM users WHERE login = '" + login + "';", detailsParams).get(0);
		// not vulnerable
		ArrayList<String> details = Databaser.query(detailsQuery, detailsParams).get(0);
		ArrayList<String> columns = Databaser.getColumns(detailsQuery, detailsParams);
		String firstName = details.get(columns.indexOf("firstName"));
		String uid = details.get(columns.indexOf("uid"));

		// log that the user logged out before closing
		frmMain.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				Databaser.log(uid, "Logged out.");
				//frmMainMenu.dispose();
				System.exit(0);
			}
		});
		
		JLabel lblWelcome = new JLabel("Welcome, " + firstName);
		lblWelcome.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		GridBagConstraints gbc_lblWelcome = new GridBagConstraints();
		gbc_lblWelcome.insets = new Insets(0, 0, 0, 5);
		gbc_lblWelcome.gridx = 1;
		gbc_lblWelcome.gridy = 0;
		frmMain.getContentPane().add(lblWelcome, gbc_lblWelcome);
	}
}