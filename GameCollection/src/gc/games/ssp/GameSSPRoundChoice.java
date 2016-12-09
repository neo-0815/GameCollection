package gc.games.ssp;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import gc.main.GameCollection;

public class GameSSPRoundChoice extends JFrame {
	private static final long serialVersionUID = 1L;

	public static int gameMode = -1;

	int pWidth = 300;
	int pHeight = 125;
	int bWidth = 100;
	int bHeight = 25;

	JPanel panel;
	JButton confirm;
	JTextField input;

	public GameSSPRoundChoice() {
		GameCollection.gameSSPmc.dispose();

		setVisible(true);
		setSize(pWidth, pHeight);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle("Rundenauswahl");
		setResizable(false);
		setLayout(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage((getClass().getResource("icon.png"))));
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent WE) {
				GameCollection.main(null);
				GameCollection.gameSSPrc.dispose();
			}
		});
		addKeyListener(new HotkeyListener());
		setFocusable(true);
		setAutoRequestFocus(true);

		panel = new JPanel();
		panel.setBounds(0, 0, pWidth, pHeight);
		panel.setLayout(null);
		panel.setBackground(Color.LIGHT_GRAY);
		add(panel);

		confirm = new JButton();
		confirm.setText("Bestätigen");
		confirm.setBounds(pWidth / 2 - (bWidth / 2), pHeight - (bHeight + 45), bWidth, bHeight);
		confirm.setLayout(null);
		confirm.addActionListener(new ButtonListener());
		panel.add(confirm);

		input = new JTextField();
		input.setBounds(pWidth / 2 - (200 / 2), 25, 200, bHeight);
		input.setLayout(null);
		input.setSelectedTextColor(Color.MAGENTA);
		input.setToolTipText("Anzahl der zu spielenden Runden hier eintragen. Leer lassen um unendlich lange zu spielen.");
		input.addKeyListener(new HotkeyListener());
		input.setFocusable(true);
		panel.add(input);

		redraw();
	}

	public void redraw() {
		setVisible(false);
		setVisible(true);
	}

	public class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent ae) {
			Object source = ae.getSource();
			if(source == confirm) {
				if(gameMode == 1) {
					if(input.getText().isEmpty()) {
						GameSSPSinglePlayer.maxRounds = Integer.MAX_VALUE;
					}else {
						GameSSPSinglePlayer.maxRounds = Integer.valueOf(input.getText().replaceAll("[^\\d.]", ""));
					}
					GameCollection.gameSSPsp = new GameSSPSinglePlayer();
				}else if(gameMode == 2) {
					if(input.getText().isEmpty()) {
						GameSSPMultiPlayer.maxRounds = Integer.MAX_VALUE;
					}else {
						GameSSPMultiPlayer.maxRounds = Integer.valueOf(input.getText().replaceAll("[^\\d.]", ""));
					}
					GameCollection.gameSSPmp = new GameSSPMultiPlayer();
				}else if(gameMode == 3) {

				}
			}
		}
	}

	public class HotkeyListener implements KeyListener {

		@Override
		public void keyTyped(KeyEvent e) {
		}

		@Override
		public void keyPressed(KeyEvent e) {
			int source = e.getKeyCode();

			if(source == KeyEvent.VK_ENTER) {
				if(gameMode == 1) {
					if(input.getText().isEmpty()) {
						GameSSPSinglePlayer.maxRounds = Integer.MAX_VALUE;
					}else {
						GameSSPSinglePlayer.maxRounds = Integer.valueOf(input.getText().replaceAll("[^\\d.]", ""));
					}
					GameCollection.gameSSPsp = new GameSSPSinglePlayer();
				}else if(gameMode == 2) {

				}else if(gameMode == 3) {

				}
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
		}
	}
}