import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Duration;

public class TimeTracker extends JDialog {
	private JPanel contentPane;
	private JButton stopButton;
	private JButton startButton;
	private JRadioButton opacityButton;
	private JLabel elapsedTime;
	private long lastTimeStored;
	private Timer timer;

	public TimeTracker () {

		setTitle("Developed by ldvzanotti");
		setContentPane(contentPane);
		setModal(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		timer = new Timer(0, new ActionListener() {
			@Override
			public void actionPerformed (ActionEvent e) {
				long runningTime = System.currentTimeMillis() - lastTimeStored;
				Duration duration = Duration.ofMillis(runningTime);
				long minutes = duration.toMinutes();
				duration = duration.minusMinutes(minutes);
				long seconds = duration.toSeconds();
				elapsedTime.setText(String.format("%02d:%02d", minutes, seconds));
			}
		});

		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed (ActionEvent e) {
				if (! timer.isRunning()) {
					lastTimeStored = System.currentTimeMillis();
					timer.start();
				} else if (! timer.isRunning() && ! elapsedTime.getText().equals("00:00")) {
					lastTimeStored = System.currentTimeMillis();
					timer.start();
				}
			}
		});

		stopButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed (ActionEvent e) {
				if (timer.isRunning() && ! elapsedTime.getText().equals("00:00")) {
					timer.stop();
				} else elapsedTime.setText("00:00");
			}
		});
	}

//TO BE IMPLEMENTED
//		opacityButton.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed (ActionEvent e) {
//				;
//			}
//		});
//	}

	public static void main (String[] args) {
		TimeTracker window = new TimeTracker();
		window.pack();
		window.setVisible(true);
	}
}

