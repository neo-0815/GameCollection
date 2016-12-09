package gc.main;

public class RandomInt {

	// returns a number between min and max
	public static int random(int min, int max) {
		max = (max + 1) - min;
		int i = (int) (Math.random() * max) + min;
		return i;
	}

	// returns a number between 0 and max
	public static int random(int max) {
		int i = (int) (Math.random() * max);
		return i;
	}

	// returns 0 or 1
	public static int random() {
		int i = (int) Math.random();
		return i;
	}
}
