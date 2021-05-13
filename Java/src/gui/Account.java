package gui;

import javax.swing.JFrame;
import javax.swing.UIManager;

public class Account {

	private JFrame frmAccount;
	
	private String uid;

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
		frmAccount = new JFrame("Account");
		frmAccount.setBounds(100, 100, 450, 300);
		frmAccount.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}
}