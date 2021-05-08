package gui;

import javax.swing.JFrame;
import javax.swing.UIManager;

import functions.Databaser;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Funds {

	private JFrame frmFunds;
	
	private String uid, login;

	/**
	 * Create the application.
	 */
	public Funds(String uid, String login) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		this.uid = uid;
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
		frmFunds.setBounds(100, 100, 450, 450);
		frmFunds.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		frmFunds.getContentPane().setLayout(gridBagLayout);
		
		JLabel lblAddFunds = new JLabel("Add Funds");
		lblAddFunds.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		GridBagConstraints gbc_lblAddFunds = new GridBagConstraints();
		gbc_lblAddFunds.insets = new Insets(0, 0, 5, 5);
		gbc_lblAddFunds.gridx = 1;
		gbc_lblAddFunds.gridy = 1;
		frmFunds.getContentPane().add(lblAddFunds, gbc_lblAddFunds);
		
		JButton btn5 = new JButton("\u00A35.00");
		addFunctionality(btn5, 5.00);
		btn5.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_btn5 = new GridBagConstraints();
		gbc_btn5.insets = new Insets(0, 0, 5, 5);
		gbc_btn5.gridx = 1;
		gbc_btn5.gridy = 3;
		frmFunds.getContentPane().add(btn5, gbc_btn5);
		
		JButton btn10 = new JButton("\u00A310.00");
		addFunctionality(btn10, 10.00);
		btn10.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_btn10 = new GridBagConstraints();
		gbc_btn10.insets = new Insets(0, 0, 5, 5);
		gbc_btn10.gridx = 1;
		gbc_btn10.gridy = 4;
		frmFunds.getContentPane().add(btn10, gbc_btn10);
		
		JButton btn20 = new JButton("\u00A320.00");
		addFunctionality(btn20, 20.00);
		btn20.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_btn20 = new GridBagConstraints();
		gbc_btn20.insets = new Insets(0, 0, 5, 5);
		gbc_btn20.gridx = 1;
		gbc_btn20.gridy = 5;
		frmFunds.getContentPane().add(btn20, gbc_btn20);
		
		JButton btn50 = new JButton("\u00A350.00");
		addFunctionality(btn50, 50.00);
		btn50.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_btn50 = new GridBagConstraints();
		gbc_btn50.insets = new Insets(0, 0, 5, 5);
		gbc_btn50.gridx = 1;
		gbc_btn50.gridy = 6;
		frmFunds.getContentPane().add(btn50, gbc_btn50);
		
		JButton btn100 = new JButton("\u00A3100.00");
		addFunctionality(btn100, 100.00);
		btn100.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_btn100 = new GridBagConstraints();
		gbc_btn100.insets = new Insets(0, 0, 5, 5);
		gbc_btn100.gridx = 1;
		gbc_btn100.gridy = 7;
		frmFunds.getContentPane().add(btn100, gbc_btn100);
		
		JButton btn250 = new JButton("\u00A3250.00");
		addFunctionality(btn250, 250.00);
		btn250.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_btn250 = new GridBagConstraints();
		gbc_btn250.insets = new Insets(0, 0, 5, 5);
		gbc_btn250.gridx = 1;
		gbc_btn250.gridy = 8;
		frmFunds.getContentPane().add(btn250, gbc_btn250);
		
		JButton btnGoBack = new JButton("Go Back");
		btnGoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Main(login);
				frmFunds.dispose();
			}
		});
		btnGoBack.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_btnGoBack = new GridBagConstraints();
		gbc_btnGoBack.insets = new Insets(0, 0, 5, 5);
		gbc_btnGoBack.gridx = 1;
		gbc_btnGoBack.gridy = 10;
		frmFunds.getContentPane().add(btnGoBack, gbc_btnGoBack);
	}
	
	private void addFunctionality(JButton btn, double amt) {
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Databaser.updateBalance(uid, amt);
				Databaser.log(uid, "Deposited £" + amt + ".");
				JOptionPane.showMessageDialog(null, "Successfully deposited £" + amt + ".", "Success", JOptionPane.INFORMATION_MESSAGE);
			}
		});
	}
}