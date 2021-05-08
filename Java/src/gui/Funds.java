package gui;

import javax.swing.JFrame;
import javax.swing.UIManager;

public class Funds {

	private JFrame frmFunds;
	
	private String login;

	/**
	 * Create the application.
	 */
	public Funds(String login) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		this.login = login;
		initialize();
		frmFunds.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmFunds = new JFrame();
		frmFunds.setTitle("Add Funds");
		frmFunds.setBounds(100, 100, 450, 300);
		frmFunds.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}

}
