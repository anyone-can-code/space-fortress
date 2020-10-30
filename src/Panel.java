// Main.java

import javax.swing.JPanel;

class Panel extends JPanel implements Runnable {

	private Thread t;

	public Panel() {
		
		System.out.println("S P A C E   F O R T R E S S");

		t = new Thread(this);
		t.start();

	}

	public void run() {

		long beforeTime = System.currentTimeMillis();
		while (true) {

			long timeDiff = System.currentTimeMillis() - beforeTime;
			beforeTime = System.currentTimeMillis();
			long sleep = 30 - timeDiff;
			repaint();

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
