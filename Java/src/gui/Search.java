package gui;

import javax.swing.JFrame;
import javax.swing.UIManager;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Search {

	private JFrame frmSearch;
	
	private String search;
	private JTextField txtSearch;

	/**
	 * Create the application.
	 */
	public Search(String search) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		this.search = search;
		initialize();
		frmSearch.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSearch = new JFrame();
		frmSearch.setTitle("Search Products");
		frmSearch.setBounds(100, 100, 600, 600);
		frmSearch.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		frmSearch.getContentPane().setLayout(gridBagLayout);
		
		JLabel lblSearch = new JLabel("Search for products:");
		lblSearch.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		GridBagConstraints gbc_lblSearch = new GridBagConstraints();
		gbc_lblSearch.gridwidth = 3;
		gbc_lblSearch.insets = new Insets(0, 0, 5, 5);
		gbc_lblSearch.gridx = 1;
		gbc_lblSearch.gridy = 1;
		frmSearch.getContentPane().add(lblSearch, gbc_lblSearch);
		
		txtSearch = new JTextField();
		txtSearch.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_txtSearch = new GridBagConstraints();
		gbc_txtSearch.gridwidth = 2;
		gbc_txtSearch.insets = new Insets(0, 0, 5, 5);
		gbc_txtSearch.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtSearch.gridx = 1;
		gbc_txtSearch.gridy = 3;
		frmSearch.getContentPane().add(txtSearch, gbc_txtSearch);
		txtSearch.setColumns(1);
		txtSearch.setText(search);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnSearch.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_btnSearch = new GridBagConstraints();
		gbc_btnSearch.insets = new Insets(0, 0, 5, 5);
		gbc_btnSearch.gridx = 3;
		gbc_btnSearch.gridy = 3;
		frmSearch.getContentPane().add(btnSearch, gbc_btnSearch);
	}

}
