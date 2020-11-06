// Frame.java

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import core.View;

public class Frame extends JFrame implements Runnable {

	private Thread t;

	private View gameView;

	public Frame() {


		setTitle("S P A C E   F O R T R E S S");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize(1400, 700);
		setLocationRelativeTo(null);
		setResizable(false);
	
		gameView = new View();
		gameView.setLayout(null);
		gameView.setBounds(0, 0, getWidth(), getHeight());
		add(gameView);

		setVisible(true);

		t = new Thread(this);
		t.start();
		
	}

	public void run() {
		
		long beforeTime = System.currentTimeMillis();
		while (true) {

			long timeDiff = System.currentTimeMillis() - beforeTime;
			beforeTime = System.currentTimeMillis();
			long sleep = 30 - timeDiff;

			gameView.repaint();

			if (sleep < 2) {
				sleep = 2;
			}
			try {
				Thread.sleep(sleep);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}
}
