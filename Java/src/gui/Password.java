package gui;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPasswordField;
import javax.swing.UIManager;

import functions.Databaser;
import functions.Globals;
import functions.Hash;

import javax.swing.JButton;

public class Password {

	private JFrame frmPassword;
	private JPasswordField txtPassword;
	private JPasswordField txtPasswordConfirm;
	
	private String uid, login;

	/**
	 * Create the application.
	 */
	public Password(String uid, String login) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		this.uid = uid;
		this.login = login;
		initialize();
		frmPassword.setVisible(true);
	}

	/**
	 * Initialize the contents of the frmPassword.
	 */
	private void initialize() {
		frmPassword = new JFrame("Change Password");
		frmPassword.setBounds(100, 100, 450, 300);
		frmPassword.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{25, 0, 0, 0, 0, 0, 25, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frmPassword.getContentPane().setLayout(gridBagLayout);
		
		JLabel lblPassword = new JLabel("Enter your new password:");
		lblPassword.setFont(Globals.font);
		GridBagConstraints gbc_lblPassword = Globals.gbc(1,1);
		frmPassword.getContentPane().add(lblPassword, gbc_lblPassword);
		
		txtPassword = new JPasswordField();
		txtPassword.setFont(Globals.font);
		GridBagConstraints gbc_txtPassword = Globals.gbc(1,2);
		gbc_txtPassword.fill = GridBagConstraints.HORIZONTAL;
		frmPassword.getContentPane().add(txtPassword, gbc_txtPassword);
		
		JLabel lblPasswordConfirm = new JLabel("Confirm your new password:");
		lblPasswordConfirm.setFont(Globals.font);
		GridBagConstraints gbc_lblPasswordConfirm = Globals.gbc(1,3);
		frmPassword.getContentPane().add(lblPasswordConfirm, gbc_lblPasswordConfirm);
		
		txtPasswordConfirm = new JPasswordField();
		txtPasswordConfirm.setFont(Globals.font);
		GridBagConstraints gbc_txtPasswordConfirm = Globals.gbc(1,4);
		gbc_txtPasswordConfirm.fill = GridBagConstraints.HORIZONTAL;
		frmPassword.getContentPane().add(txtPasswordConfirm, gbc_txtPasswordConfirm);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pass = new String(txtPassword.getPassword()), passConfirm = new String(txtPasswordConfirm.getPassword());
				if (pass.length() < 8) JOptionPane.showMessageDialog(null, "Password should be at least 8 characters long.", "Error", JOptionPane.ERROR_MESSAGE);
				else if (!pass.equals(passConfirm)) JOptionPane.showMessageDialog(null, "Passwords don't match.", "Error", JOptionPane.ERROR_MESSAGE);
				else {
					Databaser.modify("UPDATE users SET passHash = ? WHERE uid = ?", new String[] {Hash.getHash(pass), uid});
					Databaser.log(uid, "Changed their password.");
					JOptionPane.showMessageDialog(null, "Successfully changed your password.", "Success", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnSave.setFont(Globals.font);
		GridBagConstraints gbc_btnSave = Globals.gbc(1,5);
		frmPassword.getContentPane().add(btnSave, gbc_btnSave);
		
		JButton btnGoBack = new JButton("Go Back");
		btnGoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Account(uid);
				frmPassword.dispose();
			}
		});
		btnGoBack.setFont(Globals.font);
		GridBagConstraints gbc_btnGoBack = Globals.gbc(1,7);
		frmPassword.getContentPane().add(btnGoBack, gbc_btnGoBack);
	}
}