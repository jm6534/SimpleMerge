package model;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Stack;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import junit.framework.*;

public class LCStest {
	private MainModel testModel;
	
	@Before
	public void setUp(){
		Stack<Line> stack = new Stack<Line>();
		testModel = new MainModel();

		stack.add(new Line("E"));
		stack.add(new Line("C"));
		stack.add(new Line("B"));
		stack.add(new Line("D"));
		stack.add(new Line("A"));
		testModel.setLeftTextLines(stack);

		stack.add(new Line("E"));
		stack.add(new Line("A"));
		stack.add(new Line("C"));
		stack.add(new Line("B"));
		stack.add(new Line("A"));
		testModel.setRightTextLines(stack);
	}
	@Test
	public void testLCS() {
		testModel.LCS();
		ArrayList<Line> left = testModel.getLeftSubModel().getTextPage().getTextLines();
		assertEquals("A", left.get(0).getLineText());
		assertEquals("D", left.get(1).getLineText());
		assertEquals("B", left.get(2).getLineText());
		assertEquals("C", left.get(3).getLineText());
		assertEquals("", left.get(4).getLineText());
		assertEquals("E", left.get(5).getLineText());
		
		ArrayList<Line> right = testModel.getRightSubModel().getTextPage().getTextLines();
		assertEquals("A", right.get(0).getLineText());
		assertEquals("", right.get(1).getLineText());
		assertEquals("B", right.get(2).getLineText());
		assertEquals("C", right.get(3).getLineText());
		assertEquals("A", right.get(4).getLineText());
		assertEquals("E", right.get(5).getLineText());
	}
}
