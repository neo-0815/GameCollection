package gc.main;

import javax.swing.JFrame;

import gc.games.GameHUB;

public class GameCollection {

	public static JFrame gameHUB;

	public static JFrame gameSSPmc;
	public static JFrame gameSSPrc;
	public static JFrame gameSSPsp;
	public static JFrame gameSSPmp;

	public static JFrame gameDrNim;

	public static void main(String[] args) {
		gameHUB = new GameHUB();
	}
}
