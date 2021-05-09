package functions;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class Globals {

	public static final Font font = new Font("Segoe UI", Font.PLAIN, 14);
	
	public static Font font(int size) {
		return new Font("Segoe UI", Font.PLAIN, size);
	}
	
	public static GridBagConstraints gbc(int gridx, int gridy) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(0, 0, 5, 5);
		gbc.gridx = gridx;
		gbc.gridy = gridy;
		return gbc;
	}

}