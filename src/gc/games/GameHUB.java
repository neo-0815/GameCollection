package gc.games;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import gc.games.dr_nim.GameDrNim;
import gc.games.ssp.GameSSPModeChoice;
import gc.main.GameCollection;

public class GameHUB extends JFrame {
	private static final long serialVersionUID = 1L;

	int pWidth = 400;
	int pHeight = 400;
	int bWidth = 100;
	int bHeight = 25;

	JPanel panel;
	JButton exit;

	JButton ssp;
	JButton drNim;
	JButton game3;
	JButton game4;
	JButton game5;
	JButton game6;

	public GameHUB() {
		setVisible(true);
		setSize(pWidth, pHeight);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle("GameHUB");
		setResizable(false);
		setLayout(null);

		panel = new JPanel();
		panel.setBounds(0, 0, pWidth, pHeight);
		panel.setLayout(null);
		panel.setBackground(Color.LIGHT_GRAY);
		add(panel);

		exit = new JButton();
		exit.setText("Ende");
		exit.setBounds(pWidth / 2 - bWidth / 2, pHeight - (2 * bHeight + 10), bWidth, bHeight);
		exit.setLayout(null);
		exit.addActionListener(new ButtonListener());
		panel.add(exit);

		ssp = new JButton();
		ssp.setText("Schere, Stein, Papier");
		ssp.setBounds(pWidth / 4 - bWidth / 2 - 35, 20, bWidth + 70, bHeight);
		ssp.setLayout(null);
		ssp.addActionListener(new ButtonListener());
		panel.add(ssp);

		drNim = new JButton();
		drNim.setText("Dr. Nim");
		drNim.setBounds(pWidth / 2 - bWidth / 2 + 60, 20, bWidth + 70, bHeight);
		drNim.setLayout(null);
		drNim.addActionListener(new ButtonListener());
		panel.add(drNim);

		game3 = new JButton();
		game3.setText("Coming Soon");
		game3.setBounds(pWidth / 4 - bWidth / 2 - 35, 55, bWidth + 70, bHeight);
		game3.setLayout(null);
		game3.addActionListener(new ButtonListener());
		panel.add(game3);

		game4 = new JButton();
		game4.setText("Coming Soon");
		game4.setBounds(pWidth / 2 - bWidth / 2 + 60, 55, bWidth + 70, bHeight);
		game4.setLayout(null);
		game4.addActionListener(new ButtonListener());
		panel.add(game4);

		game5 = new JButton();
		game5.setText("Coming Soon");
		game5.setBounds(pWidth / 4 - bWidth / 2 - 35, 90, bWidth + 70, bHeight);
		game5.setLayout(null);
		game5.addActionListener(new ButtonListener());
		panel.add(game5);

		game6 = new JButton();
		game6.setText("Coming Soon");
		game6.setBounds(pWidth / 2 - bWidth / 2 + 60, 90, bWidth + 70, bHeight);
		game6.setLayout(null);
		game6.addActionListener(new ButtonListener());
		panel.add(game6);

		redraw();
	}

	public void redraw() {
		setVisible(false);
		setVisible(true);
	}

	public class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent ae) {
			final Object source = ae.getSource();

			GameCollection.gameHUB.setVisible(false);

			if(source == exit) System.exit(0);
			else if(source == ssp) GameCollection.gameSSPmc = new GameSSPModeChoice();
			else if(source == drNim) GameCollection.gameDrNim = new GameDrNim();
		}
	}
}
