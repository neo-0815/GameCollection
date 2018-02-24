package gc.games.dr_nim;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import gc.main.GameCollection;

public class GameDrNim extends JFrame {
	private static final long serialVersionUID = 1L;

	int pWidth = 400;
	int pHeight = 400;
	int bWidth = 100;
	int bHeight = 25;

	JPanel panel;

	JLabel img;

	public GameDrNim() {
		setVisible(true);
		setSize(pWidth, pHeight);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle("Dr. Nim");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent WE) {
				final int reply = JOptionPane.showConfirmDialog(null, "Willst du wirklich das Spiel beenden und zum GameHUB zurückkehren??", "Beenden", JOptionPane.YES_NO_OPTION);
				if(reply == JOptionPane.YES_OPTION) {
					GameCollection.gameHUB.setVisible(true);
					GameCollection.gameDrNim.dispose();
				}else {
				}
			}
		});
		setResizable(false);
		setLayout(null);
		setFocusable(true);
		setAutoRequestFocus(true);

		panel = new JPanel();
		panel.setBounds(0, 0, pWidth, pHeight);
		panel.setLayout(null);
		panel.setBackground(Color.LIGHT_GRAY);
		add(panel);

		img = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("ball.png"))));
		img.setBounds(0, 0, 221, 192);
		img.setLayout(null);
		panel.add(img);

		redraw();
	}

	public void redraw() {
		setVisible(false);
		setVisible(true);
	}
}
