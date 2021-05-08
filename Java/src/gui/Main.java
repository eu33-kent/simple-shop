package gui;

import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JFrame;

import functions.Databaser;

public class Main {

	private JFrame frmMain;
	
	private static String login;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMain = new JFrame();
		frmMain.setTitle("Main Menu");
		frmMain.setBounds(100, 100, 450, 300);
		frmMain.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		// set up the variables
		String detailsQuery = "SELECT uid, firstName, lastName " +
				"FROM users " +
				"WHERE login = '" + login + "';";
		ArrayList<String> details = Databaser.query(detailsQuery).get(0);
		ArrayList<String> columns = Databaser.getColumns(detailsQuery);
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
	}
}