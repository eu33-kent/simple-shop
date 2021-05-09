package gui;

import javax.swing.JFrame;
import javax.swing.UIManager;

public class Orders {

	private JFrame frmOrders;
	
	private String login;

	/**
	 * Create the application.
	 */
	public Orders(String login) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		this.login = login;
		initialize();
		frmOrders.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmOrders = new JFrame("Orders");
		frmOrders.setBounds(100, 100, 450, 300);
		frmOrders.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}

}
