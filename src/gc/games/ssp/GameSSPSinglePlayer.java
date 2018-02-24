package gc.games.ssp;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import gc.main.GameCollection;
import gc.main.RandomInt;

public class GameSSPSinglePlayer extends JFrame {
	private static final long serialVersionUID = 1L;
	public static int img;
	public static int imgC;

	int pWidth = 400;
	int pHeight = 700;
	int bWidth = 100;
	int bHeight = 25;

	public static int maxRounds;
	int round = 1;
	int points = 0;
	int pointsC = 0;

	String prRound = "Runde: ";
	String prPoints = "Spieler: ";
	String prPointsC = "Computer: ";
	String space = "     ";
	String winner;

	JPanel panel;
	JButton next;
	JButton end;

	JLabel imglbl1;
	JLabel imglbl2;
	JLabel imglbl3;

	JLabel imglblc1;
	JLabel imglblc2;
	JLabel imglblc3;

	JButton scissors;
	JButton stone;
	JButton paper;

	JLabel pointsDisplay;
	JLabel roundDisplay;

	Font font = new Font("Arial", Font.BOLD, 16);
	JLabel winnerlbl;

	public GameSSPSinglePlayer() {
		GameCollection.gameSSPrc.dispose();

		setVisible(true);
		setSize(pWidth, pHeight);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle("Schere, Stein, Papier");
		setResizable(false);
		setLayout(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon.png")));
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent WE) {
				final int reply = JOptionPane.showConfirmDialog(null, "Willst du wirklich das Spiel beenden und zum GameHUB zurückkehren??", "Beenden", JOptionPane.YES_NO_OPTION);
				if(reply == JOptionPane.YES_OPTION) {
					GameCollection.gameHUB.setVisible(true);
					GameCollection.gameSSPsp.dispose();
				}else {
				}
			}
		});
		addKeyListener(new HotkeyListener());
		setName("Spiel");
		setFocusable(true);
		setAutoRequestFocus(true);

		panel = new JPanel();
		panel.setBounds(0, 0, pWidth, pHeight);
		panel.setLayout(null);
		panel.setBackground(Color.LIGHT_GRAY);
		add(panel);

		roundDisplay = new JLabel();
		roundDisplay.setText(prRound + round);
		roundDisplay.setBounds(pWidth / 2 - 60 / 2, 10, 60, bHeight);
		roundDisplay.setLayout(null);
		panel.add(roundDisplay);

		pointsDisplay = new JLabel();
		pointsDisplay.setText(prPoints + points + space + prPointsC + pointsC);
		pointsDisplay.setBounds(pWidth / 2 - 145 / 2, bHeight + 10, 145, bHeight);
		pointsDisplay.setLayout(null);
		panel.add(pointsDisplay);

		scissors = new JButton();
		scissors.setText("Schere");
		scissors.setBounds(pWidth / 5 - bWidth / 2, pHeight - (2 * bHeight + 10), bWidth, bHeight);
		scissors.setLayout(null);
		scissors.addActionListener(new Buttonlistener());
		panel.add(scissors);

		stone = new JButton();
		stone.setText("Stein");
		stone.setBounds(pWidth / 2 - bWidth / 2, pHeight - (2 * bHeight + 10), bWidth, bHeight);
		stone.setLayout(null);
		stone.addActionListener(new Buttonlistener());
		panel.add(stone);

		paper = new JButton();
		paper.setText("Papier");
		paper.setBounds(pWidth / 2 + bWidth / 2 + 20, pHeight - (2 * bHeight + 10), bWidth, bHeight);
		paper.setLayout(null);
		paper.addActionListener(new Buttonlistener());
		panel.add(paper);

		imglbl1 = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("scissors.png"))));
		imglbl1.setBounds(pWidth / 2 - 221 / 2, pHeight - (50 + bHeight * 2 + 192), 221, 192);

		imglbl2 = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("stone.png"))));
		imglbl2.setBounds(pWidth / 2 - 136 / 2, pHeight - (50 + bHeight * 2 + 108), 136, 108);

		imglbl3 = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("paper.png"))));
		imglbl3.setBounds(pWidth / 2 - 178 / 2, pHeight - (50 + bHeight * 2 + 240), 178, 240);

		next = new JButton();
		next.setText("Weiter");
		next.setBounds(pWidth - (bWidth + 10), 10, bWidth, bHeight);
		next.setLayout(null);
		next.addActionListener(new Buttonlistener());

		imglblc1 = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("scissors.png"))));
		imglblc1.setBounds(pWidth / 2 - 221 / 2, 75, 221, 192);

		imglblc2 = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("stone.png"))));
		imglblc2.setBounds(pWidth / 2 - 136 / 2, 75, 136, 108);

		imglblc3 = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("paper.png"))));
		imglblc3.setBounds(pWidth / 2 - 178 / 2, 75, 178, 240);

		winnerlbl = new JLabel();
		winnerlbl.setFont(font);

		end = new JButton();
		end.setText("Ende");
		end.setLayout(null);
		end.addActionListener(new Buttonlistener());

		redraw();
	}

	public void redraw() {
		setVisible(false);
		setVisible(true);
	}

	public void image(int id) {
		switch(id) {
		case 0:
			try {
				panel.remove(imglbl2);
				panel.remove(imglbl3);
			}catch(final NullPointerException NPE) {
			}
			panel.add(imglbl1);
			scissors.setEnabled(false);
			stone.setEnabled(false);
			paper.setEnabled(false);
			panel.add(next);

			break;
		case 1:
			try {
				panel.remove(imglbl1);
				panel.remove(imglbl3);
			}catch(final NullPointerException NPE) {
			}
			panel.add(imglbl2);
			scissors.setEnabled(false);
			stone.setEnabled(false);
			paper.setEnabled(false);
			panel.add(next);

			break;
		case 2:
			try {
				panel.remove(imglbl1);
				panel.remove(imglbl2);
			}catch(final NullPointerException NPE) {
			}
			panel.add(imglbl3);
			scissors.setEnabled(false);
			stone.setEnabled(false);
			paper.setEnabled(false);
			panel.add(next);

			break;
		case 3:
			try {
				panel.remove(imglbl1);
				panel.remove(imglbl2);
				panel.remove(imglbl3);
				panel.remove(next);
			}catch(final NullPointerException NPE) {
			}
			scissors.setEnabled(true);
			stone.setEnabled(true);
			paper.setEnabled(true);

			break;
		default:

			break;
		}
		redraw();
	}

	public void imageC(int id) {
		switch(id) {
		case 0:
			try {
				panel.remove(imglblc2);
				panel.remove(imglblc3);
			}catch(final NullPointerException NPE) {
			}
			panel.add(imglblc1);

			break;
		case 1:
			try {
				panel.remove(imglblc1);
				panel.remove(imglblc3);
			}catch(final NullPointerException NPE) {
			}
			panel.add(imglblc2);

			break;
		case 2:
			try {
				panel.remove(imglblc1);
				panel.remove(imglblc2);
			}catch(final NullPointerException NPE) {
			}
			panel.add(imglblc3);

			break;
		case 3:
			try {
				panel.remove(imglblc1);
				panel.remove(imglblc2);
				panel.remove(imglblc3);
			}catch(final NullPointerException NPE) {
			}

			break;
		default:

			break;
		}
		redraw();
	}

	public class Buttonlistener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent ae) {
			final int random = RandomInt.random(0, 2);
			final Object source = ae.getSource();

			if(source == scissors) {
				img = 0;
				imgC = random;
				image(0);
				imageC(random);
			}else if(source == stone) {
				img = 1;
				imgC = random;
				image(1);
				imageC(random);
			}else if(source == paper) {
				img = 2;
				imgC = random;
				image(2);
				imageC(random);
			}else if(source == next) {
				image(3);
				imageC(3);

				if(img == imgC) {
				}else if(img == 0 && imgC == 1) pointsC++;
				else if(img == 0 && imgC == 2) points++;
				else if(img == 1 && imgC == 0) points++;
				else if(img == 1 && imgC == 2) pointsC++;
				else if(img == 2 && imgC == 0) pointsC++;
				else if(img == 2 && imgC == 1) points++;

				round++;
				roundDisplay.setText(prRound + round);
				pointsDisplay.setText(prPoints + points + space + prPointsC + pointsC);

				if(round > maxRounds) {
					panel.remove(scissors);
					panel.remove(stone);
					panel.remove(paper);
					panel.remove(roundDisplay);

					if(points > pointsC) winner = "Spieler";
					else if(points < pointsC) winner = "Computer";
					else if(points == pointsC) winner = "";

					if(winner == "") winnerlbl.setText("Unentschieden !!");
					else winnerlbl.setText(winner + " hat gewonnen.");

					pWidth = 600;
					pHeight = 200;

					setSize(pWidth, pHeight);
					setLocationRelativeTo(null);
					setName("Winner");
					panel.setBounds(0, 0, pWidth, pHeight);
					winnerlbl.setBounds(0, pHeight / 2 - bHeight / 2, pWidth, bHeight);
					winnerlbl.setHorizontalAlignment(JLabel.CENTER);
					pointsDisplay.setBounds(pWidth / 2 - 145 / 2, bHeight + 10, 145, bHeight);
					end.setBounds(pWidth / 2 - bWidth / 2, pHeight - (bHeight / 2 + 50), bWidth, bHeight);

					panel.add(winnerlbl);
					panel.add(end);
				}
			}else if(source == end && getName() == "Winner") {
				GameCollection.main(null);
				GameCollection.gameSSPsp.dispose();
				return;
			}
			redraw();
		}

	}

	public class HotkeyListener implements KeyListener {
		private boolean isSelected = false;

		@Override
		public void keyPressed(KeyEvent e) {
			final int random = RandomInt.random(0, 2);
			final int source = e.getKeyCode();

			if(source == KeyEvent.VK_1 || source == KeyEvent.VK_NUMPAD1 && getName() == "Spiel" && !isSelected) {
				isSelected = true;

				img = 0;
				imgC = random;
				image(0);
				imageC(random);
			}else if(source == KeyEvent.VK_2 || source == KeyEvent.VK_NUMPAD2 && getName() == "Spiel" && !isSelected) {
				isSelected = true;

				img = 1;
				imgC = random;
				image(1);
				imageC(random);
			}else if(source == KeyEvent.VK_3 || source == KeyEvent.VK_NUMPAD3 && getName() == "Spiel" && !isSelected) {
				isSelected = true;

				img = 2;
				imgC = random;
				image(2);
				imageC(random);
			}else if(source == KeyEvent.VK_ENTER && getName() == "Spiel" && isSelected) {
				isSelected = false;

				image(3);
				imageC(3);

				if(img == imgC) {
				}else if(img == 0 && imgC == 1) pointsC++;
				else if(img == 0 && imgC == 2) points++;
				else if(img == 1 && imgC == 0) points++;
				else if(img == 1 && imgC == 2) pointsC++;
				else if(img == 2 && imgC == 0) pointsC++;
				else if(img == 2 && imgC == 1) points++;

				round++;
				roundDisplay.setText(prRound + round);
				pointsDisplay.setText(prPoints + points + space + prPointsC + pointsC);

				if(round > maxRounds) {
					panel.remove(scissors);
					panel.remove(stone);
					panel.remove(paper);
					panel.remove(roundDisplay);

					if(points > pointsC) winner = "Spieler";
					else if(points < pointsC) winner = "Computer";
					else if(points == pointsC) winner = "";

					if(winner == "") winnerlbl.setText("Unentschieden !!");
					else winnerlbl.setText(winner + " hat gewonnen.");

					pWidth = 600;
					pHeight = 200;

					setSize(pWidth, pHeight);
					setLocationRelativeTo(null);
					setName("Winner");
					panel.setBounds(0, 0, pWidth, pHeight);
					winnerlbl.setBounds(0, pHeight / 2 - bHeight / 2, pWidth, bHeight);
					winnerlbl.setHorizontalAlignment(JLabel.CENTER);
					pointsDisplay.setBounds(pWidth / 2 - 145 / 2, bHeight + 10, 145, bHeight);
					end.setBounds(pWidth / 2 - bWidth / 2, pHeight - (bHeight / 2 + 50), bWidth, bHeight);

					panel.add(winnerlbl);
					panel.add(end);
				}
			}else if(source == KeyEvent.VK_ENTER && getName() == "Winner") {
				GameCollection.gameHUB.setVisible(true);
				GameCollection.gameSSPsp.dispose();
				return;
			}
			redraw();
		}

		@Override
		public void keyReleased(KeyEvent e) {
		}

		@Override
		public void keyTyped(KeyEvent e) {
		}
	}
}
