package gui;

import javax.swing.JFrame;
import javax.swing.UIManager;

public class Account {

	private JFrame frmAccount;
	
	private String login;

	/**
	 * Create the application.
	 */
	public Account(String login) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		this.login = login;
		initialize();
		frmAccount.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAccount = new JFrame();
		frmAccount.setTitle("Account");
		frmAccount.setBounds(100, 100, 450, 300);
		frmAccount.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}

}
