package model;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Stack;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import javafx.embed.swing.JFXPanel;
import javafx.scene.paint.Color;
import junit.framework.*;

public class LCStest {
	private MainModel testModel;
	
	@Before
	public void setUp(){
		ArrayList<Line> testList = new ArrayList<Line>();
		testModel = new MainModel();
		JFXPanel a = new JFXPanel();
		
		testList.add(new Line("abcde"));
		testList.add(new Line("defgh"));
		testList.add(new Line("bcdef"));
		testModel.setLeftTextLines(testList);

		testList.clear();
		testList.add(new Line("abcde"));
		testList.add(new Line("cdefg"));
		testList.add(new Line("bcdef"));
		testModel.setRightTextLines(testList);
	}
	@Test
	public void testLCS1True() {
		testModel.LCS();
		ArrayList<Line> left = testModel.getLeftSubModel().getTextPage().getTextLines();
		ArrayList<Line> right = testModel.getRightSubModel().getTextPage().getTextLines();
		
		assertEquals("abcde",left.get(0).getLineText());
		assertEquals("bcdef",left.get(2).getLineText());
		
		assertEquals("abcde",right.get(0).getLineText());
		assertEquals("cdefg",right.get(1).getLineText());
		assertEquals("bcdef",right.get(2).getLineText());
	}
	@Test
	public void tesetLCS2True() {
		testModel.LCS();
		ArrayList<Line> left = testModel.getLeftSubModel().getTextPage().getTextLines();
		ArrayList<Line> right = testModel.getLeftSubModel().getTextPage().getTextLines();
		
		assertEquals(Color.LIGHTGOLDENRODYELLOW,left.get(1).getLineColor());
		assertEquals(Color.LIGHTGOLDENRODYELLOW,right.get(1).getLineColor());
		
	}
	@Test
	public void testCompareOneLineTrue() {
		Line testA = new Line("abcde");
		Line testB = new Line("abcde");
		assertEquals(true,LCS.compareOneLine(testA, testB));
	}
	@Test
	public void testReturnLargestIndexFalse() {
		assertEquals(2,LCS.returnLargestIndex(1,2,3));
	}
}
