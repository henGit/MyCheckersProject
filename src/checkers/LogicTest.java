package checkers;

import static org.junit.Assert.*;
import static stdlib.StdDraw.*;

import java.awt.Color;

import org.junit.Test;


public class LogicTest {

	Logic c = new Logic();
	CheckerGui b = new CheckerGui();
	Color[][] board = b.getBoard();

	@Test
	public void testIsInside(){
		assertEquals(true, c.isInside(0, 4));
		assertEquals(false, c.isInside(1, 9));
		
	}
	
	@Test
	public void testIsEmpty() {
		c.setBoard(3, 0, BLACK, board);
		assertEquals(true, c.isEmpty(3, 0, board));
		c.setBoard(4, 0, RED, board);
		assertEquals(false, c.isEmpty(4, 0, board));
	}
	@Test
	public void testIsBackWardMove(){
		c.setBoard(5, 0, YELLOW, board);
		assertEquals(true,c.isBackWardMove(5,0, 4, 1, board));
		c.setBoard(3, 0, BLUE, board);
		assertEquals(false, c.isBackWardMove(3, 0, 2, 1, board));	
	}
	@Test
	public void testOccupied() {
		c.setBoard(0, 1, BLACK, board);
		assertEquals(false, c.occupied(0, 1, board));
		c.setBoard(5, 2, YELLOW, board);
		assertEquals(true, c.occupied(5, 2, board));
	}

	@Test
	public void testIsKing(){
		c.setBoard(0, 7, PINK, board);
		assertEquals(true, c.isKing(7, 0, board));
		c.setBoard(1, 6, PINK, board);
		assertEquals(false, c.isKing(1, 6, board));
		c.setBoard(1, 0, GREEN, board);
		assertEquals(true, c.isKing(0, 1, board));
		c.setBoard(1, 2, GREEN, board);
		assertEquals(false, c.isKing(1, 1, board));
		
	}
	@Test
	public void testIsLegalJump(){
		c.setBoard(1, 2, BLUE, board);
		c.setBoard(2, 3, YELLOW, board);
		c.setBoard(3, 4, BLACK, board);
		assertEquals(true, c.isLegalJump( 2,1,4,3, board));
		c.setBoard(5, 4, YELLOW, board);
		c.setBoard(4, 3, BLUE, board);
		c.setBoard(3, 2, BLACK, board);
		assertEquals(true, c.isLegalJump(4,5,2,3, board));
		c.setBoard(1, 2, BLUE, board);
		c.setBoard(2, 4, YELLOW, board);
		c.setBoard(3, 4, BLACK, board);
		assertEquals(false, c.isLegalJump(2,2,4,3, board));
		}
	@Test
	public void testIsLegalMove(){
		c.setBoard(3, 0, BLACK, board);
		assertEquals(true, c.isEmpty(3, 0, board));
		c.setBoard(4, 0, RED, board);
		assertEquals(false, c.isEmpty(4, 0, board));
		c.setBoard(1, 2, BLUE ,board);
		c.setBoard(0, 1, BLACK, board);
		assertEquals(false, c.occupied(0, 1, board));
		c.setBoard(1,2,BLUE, board);
		//assertEquals(true, c.getSwitchPlayer());
	}
}