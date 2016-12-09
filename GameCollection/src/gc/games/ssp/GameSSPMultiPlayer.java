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

public class GameSSPMultiPlayer extends JFrame {
	private static final long serialVersionUID = 1L;
	public static int img1;
	public static int img2;

	int pWidth = 400;
	int pHeight = 700;
	int bWidth = 100;
	int bHeight = 25;

	public static int maxRounds;
	int round = 1;
	int points1 = 0;
	int points2 = 0;

	String prRound = "Runde: ";
	String prPoints1 = "Spieler 1: ";
	String prPoints2 = "Spieler 2: ";
	String space = "     ";
	String winner;

	JPanel panel;
	JButton end;

	JLabel imglbl1;
	JLabel imglbl2;
	JLabel imglbl3;

	JLabel imglblc1;
	JLabel imglblc2;
	JLabel imglblc3;

	JLabel pointsDisplay;
	JLabel roundDisplay;

	Font font = new Font("Arial", Font.BOLD, 16);
	JLabel winnerlbl;

	public GameSSPMultiPlayer() {
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
				int reply = JOptionPane.showConfirmDialog(null, "Willst du wirklich das Spiel beenden und zum GameHUB zurückkehren??", "Beenden", JOptionPane.YES_NO_OPTION);
				if(reply == JOptionPane.YES_OPTION) {
					GameCollection.main(null);
					GameCollection.gameSSPmp.dispose();
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
		pointsDisplay.setText(prPoints1 + points1 + space + prPoints2 + points2);
		pointsDisplay.setBounds(pWidth / 2 - 145 / 2, bHeight + 10, 145, bHeight);
		pointsDisplay.setLayout(null);
		panel.add(pointsDisplay);

		imglbl1 = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("scissors.png"))));
		imglbl1.setBounds(pWidth / 2 - 221 / 2, pHeight - (50 + bHeight * 2 + 192), 221, 192);

		imglbl2 = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("stone.png"))));
		imglbl2.setBounds(pWidth / 2 - 136 / 2, pHeight - (50 + bHeight * 2 + 108), 136, 108);

		imglbl3 = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("paper.png"))));
		imglbl3.setBounds(pWidth / 2 - 178 / 2, pHeight - (50 + bHeight * 2 + 240), 178, 240);

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
		end.addActionListener(new ButtonListener());

		redraw();
	}

	public void redraw() {
		setVisible(false);
		setVisible(true);
	}

	public void imageP1(int id) {
		switch(id) {
		case 0:
			try {
				panel.remove(imglbl2);
				panel.remove(imglbl3);
			}catch(NullPointerException NPE) {
			}

			panel.add(imglbl1);

			break;
		case 1:
			try {
				panel.remove(imglbl1);
				panel.remove(imglbl3);
			}catch(NullPointerException NPE) {
			}

			panel.add(imglbl2);

			break;
		case 2:
			try {
				panel.remove(imglbl1);
				panel.remove(imglbl2);
			}catch(NullPointerException NPE) {
			}

			panel.add(imglbl3);

			break;
		case 3:
			try {
				panel.remove(imglbl1);
				panel.remove(imglbl2);
				panel.remove(imglbl3);
			}catch(NullPointerException NPE) {
			}

			break;
		default:

			break;
		}

		redraw();
	}

	public void imageP2(int id) {
		switch(id) {
		case 0:
			try {
				panel.remove(imglblc2);
				panel.remove(imglblc3);
			}catch(NullPointerException NPE) {
			}

			panel.add(imglblc1);

			break;
		case 1:
			try {
				panel.remove(imglblc1);
				panel.remove(imglblc3);
			}catch(NullPointerException NPE) {
			}

			panel.add(imglblc2);

			break;
		case 2:
			try {
				panel.remove(imglblc1);
				panel.remove(imglblc2);
			}catch(NullPointerException NPE) {
			}

			panel.add(imglblc3);

			break;
		case 3:
			try {
				panel.remove(imglblc1);
				panel.remove(imglblc2);
				panel.remove(imglblc3);
			}catch(NullPointerException NPE) {
			}

			break;
		default:

			break;
		}

		redraw();
	}

	public class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent ae) {
			Object source = ae.getSource();
			if(source == end && getName() == "Winner") {
				GameCollection.main(null);
				GameCollection.gameSSPmp.dispose();
				return;
			}
		}
	}

	public class HotkeyListener implements KeyListener {
		private boolean p1 = false;
		private boolean p2 = false;

		@Override
		public void keyPressed(KeyEvent e) {
			int source = e.getKeyCode();

			if(source == KeyEvent.VK_1 && getName() == "Spiel" && !p1) {
				p1 = true;

				img1 = 0;
				imageP1(0);
			}else if(source == KeyEvent.VK_2 && getName() == "Spiel" && !p1) {
				p1 = true;

				img1 = 1;
				imageP1(1);
			}else if(source == KeyEvent.VK_3 && getName() == "Spiel" && !p1) {
				p1 = true;

				img1 = 2;
				imageP1(2);
			}else if(source == KeyEvent.VK_NUMPAD1 && getName() == "Spiel" && !p2) {
				p2 = true;

				img2 = 0;
				imageP2(0);
			}else if(source == KeyEvent.VK_NUMPAD2 && getName() == "Spiel" && !p2) {
				p2 = true;

				img2 = 1;
				imageP2(1);
			}else if(source == KeyEvent.VK_NUMPAD3 && getName() == "Spiel" && !p2) {
				p2 = true;

				img2 = 2;
				imageP2(2);
			}else if(source == KeyEvent.VK_ENTER && getName() == "Spiel" && p1 && p2) {
				p1 = false;
				p2 = false;

				imageP1(3);
				imageP2(3);

				if(img1 == 0 && img2 == 1) points2++;
				else if(img1 == 0 && img2 == 2) points1++;
				else if(img1 == 1 && img2 == 0) points1++;
				else if(img1 == 1 && img2 == 2) points2++;
				else if(img1 == 2 && img2 == 0) points2++;
				else if(img1 == 2 && img2 == 1) points1++;

				round++;
				roundDisplay.setText(prRound + round);
				pointsDisplay.setText(prPoints1 + points1 + space + prPoints2 + points2);

				if(round > maxRounds) {
					panel.remove(roundDisplay);

					if(points1 > points2) winner = "Spieler 1";
					else if(points1 < points2) winner = "Spieler 2";
					else if(points1 == points2) winner = "";

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
				GameCollection.main(null);
				GameCollection.gameSSPmp.dispose();
				return;
			}

			redraw();
		}

		@Override
		public void keyTyped(KeyEvent e) {
		}

		@Override
		public void keyReleased(KeyEvent e) {
		}
	}
}
