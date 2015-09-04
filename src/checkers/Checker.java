package checkers;

public class Checker {

	private CheckerGui game1;

	public Checker() {
		game1 = new CheckerGui();
	}

	public static void main(String[] args) {
		new Checker().run();
	}
/** */ 
	public void run() {
		game1.checkerBoard();
		while (!game1.game.gameIsWon(game1.getBoard())) {
			game1.move();
			game1.draw();
		}
		game1.displayInstructions();
	}

}
