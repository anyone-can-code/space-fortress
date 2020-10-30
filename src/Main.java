// Main.java

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Main extends JFrame {

	public Main() {

		add(new Panel());

		setTitle("S P A C E   F O R T R E S S");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize(1400, 700);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(true);

	}

	public static void main(String[] args) {
		new Main();
	}
}
