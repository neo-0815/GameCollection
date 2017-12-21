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

import gc.main.GameCollection;

public class GameSSPModeChoice extends JFrame {
	private static final long serialVersionUID = 1L;

	int pWidth = 300;
	int pHeight = 200;
	int bWidth = 100;
	int bHeight = 25;

	JPanel panel;
	JButton sp;
	JButton mp1;
	JButton mp2;

	public GameSSPModeChoice() {
		GameCollection.gameHUB.dispose();

		setVisible(true);
		setSize(pWidth, pHeight);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle("Modusauswahl");
		setResizable(false);
		setLayout(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage((getClass().getResource("icon.png"))));
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent WE) {
				GameCollection.main(null);
				GameCollection.gameSSPmc.dispose();
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

		sp = new JButton();
		sp.setText("SinglePlayer");
		sp.setToolTipText("Player vs. Computer");
		sp.setBounds(pWidth / 2 - (bWidth / 2 + 50), 20, bWidth + 100, bHeight);
		sp.setLayout(null);
		sp.addActionListener(new ButtonListener());
		panel.add(sp);

		mp1 = new JButton();
		mp1.setText("MultiPlayer -- 1x Computer");
		mp1.setToolTipText("Player vs. Player");
		mp1.setBounds(pWidth / 2 - (bWidth / 2 + 50), 70, bWidth + 100, bHeight);
		mp1.setLayout(null);
		mp1.addActionListener(new ButtonListener());
		panel.add(mp1);

		mp2 = new JButton();
		mp2.setText("MultiPlayer -- 2x Computer");
		mp2.setToolTipText("Player vs. Player -- linked pc´s");
		mp2.setBounds(pWidth / 2 - (bWidth / 2 + 50), 120, bWidth + 100, bHeight);
		mp2.setLayout(null);
		mp2.addActionListener(new ButtonListener());
		panel.add(mp2);

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

			if(source == sp) {
				GameSSPRoundChoice.gameMode = 1;
				GameCollection.gameSSPrc = new GameSSPRoundChoice();
			}else if(source == mp1) {
				GameSSPRoundChoice.gameMode = 2;
				GameCollection.gameSSPrc = new GameSSPRoundChoice();
			}else if(source == mp2) {
				GameSSPRoundChoice.gameMode = 3;
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

			if(source == KeyEvent.VK_1 || source == KeyEvent.VK_NUMPAD1) {
				GameSSPRoundChoice.gameMode = 1;
				GameCollection.gameSSPrc = new GameSSPRoundChoice();
			}else if(source == KeyEvent.VK_2 || source == KeyEvent.VK_NUMPAD2) {
				GameSSPRoundChoice.gameMode = 2;
			}else if(source == KeyEvent.VK_3 || source == KeyEvent.VK_NUMPAD3) {
				GameSSPRoundChoice.gameMode = 3;
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
		}
	}
}
