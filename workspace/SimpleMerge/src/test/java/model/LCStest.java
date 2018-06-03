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
		ArrayList<Line> testList = new ArrayList<Line>();
		testModel = new MainModel();

		testList.add(new Line("abcde"));
		testList.add(new Line("bcdef"));
		testModel.setLeftTextLines(testList);

		testList.clear();
		testList.add(new Line("abcde"));
		testList.add(new Line("cdefg"));
		testList.add(new Line("bcdef"));
		testModel.setRightTextLines(testList);
	}
	@Test
	public void testLCSTrue() {
		testModel.LCS();
		ArrayList<Line> left = testModel.getLeftSubModel().getTextPage().getTextLines();
		assertEquals("abcde",left.get(0).getLineText());
		assertEquals("bcdef",left.get(2).getLineText());
		ArrayList<Line> right = testModel.getRightSubModel().getTextPage().getTextLines();
		assertEquals("abcde",right.get(0).getLineText());
		assertEquals("cdefg",right.get(1).getLineText());
		assertEquals("bcdef",right.get(2).getLineText());
		
	}
	@Test
	public void testCompareOneLineTrue() {
		Line testA = new Line("abcde");
		Line testB = new Line("abcde");
		assertEquals(true,LCS.compareOneLine(testA, testB));
	}
	@Test
	public void testReturnBiggerIntFalse() {
		assertEquals(2,LCS.returnBiggerInt(2, 3));
	}
}
