package checkers;

import static stdlib.StdDraw.*;

import java.awt.Color;

public class CheckerGui {

	private Color[][] board = new Color[8][8];

	public Logic game;

	int desX, desY;

	public CheckerGui() {
		game = new Logic();
	}

	public Color[][] getBoard() {
		return board;
	}

	public void draw() {

		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board.length; col++) {
				if ((board[row][col] == RED) || (board[row][col] == BLACK)) {
					setPenColor(board[row][col]);
					filledRectangle(col + .5, row + .5, .5, .5);
				} else {
					setPenColor(BLACK);
					filledRectangle(col + .5, row + .5, .5, .5);
					setPenColor(board[row][col]);
					filledCircle(col + 0.5, row + 0.5, 0.5);

					if ((board[row][col] == BLUE) && (row == 7)) {
						board[row][col] = PINK;

						setPenColor(BLACK);
						filledRectangle(col + .5, row + .5, .5, .5);
						setPenColor(board[row][col]);
						filledCircle(col + 0.5, row + 0.5, 0.5);
					}
					if ((board[row][col] == YELLOW) && (row == 0)) {
						board[row][col] = GREEN;

						setPenColor(BLACK);
						filledRectangle(col + .5, row + .5, .5, .5);
						setPenColor(board[row][col]);
						filledCircle(col + 0.5, row + 0.5, 0.5);
					}
				}
			}
		}
	}

	public void displayInstructions() {
		setPenColor(BLACK);
		if (game.winner(board) == BLUE) {
			text(5, 9.5, " blue won the game");
		}
		else {
			text(5, 9.5, "yellow won the game");
		}

	}

	public void checkerBoard() {
		setXscale(-2, 10);
		setYscale(-2, 10);
		for (int col = 0; col < 8; col++) {
			for (int row = 0; row < 8; row++) {
				if (row % 2 == col % 2) {
					board[row][col] = RED;
					setPenColor(RED);
					filledRectangle(col + .5, row + .5, .5, .5);

				} else {
					board[row][col] = BLACK;
					setPenColor(BLACK);
					filledRectangle(col + .5, row + .5, .5, .5);
					if (row < 3) {
						board[row][col] = BLUE;
						setPenColor(BLUE);
						filledCircle(col + 0.5, row + 0.5, 0.5);

					}
					if (row > 4) {
						board[row][col] = YELLOW;
						setPenColor(YELLOW);
						filledCircle(col + 0.5, row + 0.5, 0.5);

					}

					if ((row > 3) && (row < 4)) {
						board[row][col] = BLACK;
					}
				}
			}
		}
	}

	public boolean rightPlayer(int pcX, int pcY) {
		if (((game.switchPlayer % 2 == 0) && ((board[pcY][pcX] == BLUE) || (board[pcY][pcX] == PINK)))
				|| ((game.switchPlayer % 2 == 1) && ((board[pcY][pcX] == YELLOW) || (board[pcY][pcX] == GREEN)))) {
			return true;
		}
		return false;
	}

	public void move() {
		int pcX = 0;
		int pcY = 0;

		while (!mousePressed()) {
			pcX = (int) mouseX();
			pcY = (int) mouseY();
		}

		while (mousePressed()) {

		}

		if (rightPlayer(pcX, pcY)) {

			while (!mousePressed()) {
				desX = (int) mouseX();
				desY = (int) mouseY();
			}

			while (mousePressed()) {

			}
		} else {
			int dX = desX;
			int dY = desY;

			desX = pcX;
			desY = pcY;

			pcX = dX;
			pcY = dY;

			if (!game.isLegalJump(pcY, pcX, desY, desX, board)) {
				return;
			}
			game.switchPlayer++;
		}
		clear();
		playAt(pcX, pcY, desX, desY);
	}

	public void clear() {
		setPenColor(WHITE);
		filledRectangle(5, 9.5, 5, .3);
	}

	public void playAt(int pcX, int pcY, int desX, int desY) {

		if (game.isLegalMove(pcX, pcY, desX, desY, board)) {
			if (game.isLegalJump(pcY, pcX, desY, desX, board)) {

				// game.wasLegalJump = true;

				board[(desY + pcY) / 2][(desX + pcX) / 2] = BLACK;
			}
			Color t = board[pcY][pcX];
			board[pcY][pcX] = board[desY][desX];
			board[desY][desX] = t;

		} else {
			setPenColor(RED);
			text(5, 9.5, "Illegal move");
		}
	}
}
