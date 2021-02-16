import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {

	static final int SCREEN_WIDTH = 600;
	static final int SCREEN_HEIGHT = 600;
	static final int UNITS_SIZE = 25;
	static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/ UNITS_SIZE;
	final int[] x = new int[GAME_UNITS];
	final int[] y = new int[GAME_UNITS];
	int gameSpeed = 190;
	int bodySize = 6;
	int applesEaten;
	int appleCordX;
	int appleCordY;
	char moveDirection = 'R';
	boolean running = false;
	Timer timer;
	Random random;

	GamePanel() {
		random = new Random();
		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.setBackground(Color.BLACK);
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter());
		startGame();
	}

	public void startGame() {
		newToken();
		running = true;
		timer = new Timer(200, this);
		timer.start();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}

	public void draw(Graphics g) {
		if (running) {
			//Draw grid
			for (int i = 0; i < SCREEN_HEIGHT / UNITS_SIZE; i++) {
				g.drawLine(i * UNITS_SIZE, 0, i * UNITS_SIZE, SCREEN_HEIGHT);
				g.drawLine(0, i * UNITS_SIZE, SCREEN_WIDTH, i * UNITS_SIZE);
			}
			//Apple color
			g.setColor(Color.RED);
			g.fillOval(appleCordX, appleCordY, UNITS_SIZE, UNITS_SIZE);

			//Snake color
			for (int i = 0; i < bodySize; i++) {
				//Snake's head
				if (i == 0) {
					g.setColor(new Color(0, 230, 0));
				}
				//Snake's body
				else {
					g.setColor(new Color(0, 102, 0));
				}
				g.fillOval(x[i], y[i], UNITS_SIZE+2, UNITS_SIZE+2);
			}
			g.setColor(Color.RED);
			g.setFont(new Font("Ink Free", Font.BOLD, 30));
			FontMetrics metricsScore = getFontMetrics(g.getFont());
			g.drawString("Score: " + applesEaten,
					(SCREEN_WIDTH - metricsScore.stringWidth("Score: " + applesEaten))/2,
					g.getFont().getSize());
		}
		else gameOver(g);
	}

	public void newToken() {
		appleCordX = random.nextInt(SCREEN_WIDTH/ UNITS_SIZE)* UNITS_SIZE;
		appleCordY = random.nextInt(SCREEN_HEIGHT/ UNITS_SIZE)* UNITS_SIZE;
	}

	public void move() {
		//Move
		for(int i = bodySize; i > 0; i--) {
			x[i] = x[i-1];
			y[i] = y[i-1];
		}
		//Change direction
		switch (moveDirection) {
			case 'U' -> y[0] = y[0] - UNITS_SIZE;
			case 'D' -> y[0] = y[0] + UNITS_SIZE;
			case 'L' -> x[0] = x[0] - UNITS_SIZE;
			case 'R' -> x[0] = x[0] + UNITS_SIZE;
		}
	}

	public void eatApples () {
		if((x[0]) == appleCordX && (y[0] == appleCordY)) {
			bodySize++;
			applesEaten++;
			if (timer.getDelay() > 40) {
				timer.setDelay(gameSpeed-10);
				gameSpeed -= 10;
			}
			newToken();
		}

	}

	public void checkCollisions() {
		//Checks snake's head collision with body
		for(int i = bodySize; i > 0; i--) {
			if ((x[0] == x[i]) && (y[0] == y[i])) {
				running = false;
				break;
			}
		}
		//Checks snake's head collision with screen borders
		if(x[0] < 0 || x[0] > SCREEN_HEIGHT || y[0] < 0 || y[0] > SCREEN_WIDTH) {
			running = false;
		}
		//Stops timer
		if(!running) {
			timer.stop();
		}
	}

	public void gameOver(Graphics g) {
		//Score text
		g.setColor(Color.RED);
		g.setFont(new Font("Ink Free", Font.BOLD, 35));
		FontMetrics metricsScore = getFontMetrics(g.getFont());
		g.drawString("Score: " + applesEaten,
				(SCREEN_WIDTH - metricsScore.stringWidth("Score: " + applesEaten))/2, SCREEN_HEIGHT-g.getFont().getSize());

		//Game over text
		g.setColor(Color.RED);
		g.setFont(new Font("Ink Free", Font.BOLD, 75));
		FontMetrics metrics = getFontMetrics(g.getFont());
		g.drawString("Game Over!", (SCREEN_WIDTH - metrics.stringWidth("Game Over!"))/2, SCREEN_HEIGHT/2);

	}

	@Override
	public void actionPerformed (ActionEvent e) {

		if(running) {
			move();
			eatApples();
			checkCollisions();
		}
		repaint();
	}

	public class MyKeyAdapter extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					if(moveDirection != 'R') {
						moveDirection = 'L';
					}
					break;
				case KeyEvent.VK_RIGHT:
					if(moveDirection != 'L') {
						moveDirection = 'R';
					}
					break;
				case KeyEvent.VK_UP:
					if(moveDirection != 'D') {
						moveDirection = 'U';
					}
					break;
				case KeyEvent.VK_DOWN:
					if(moveDirection != 'U') {
						moveDirection = 'D';
					}
					break;
			}

		}
	}
}
