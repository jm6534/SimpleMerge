package model;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.Stack;

import org.junit.Before;
import org.junit.Test;
import javafx.embed.swing.JFXPanel;
import javafx.scene.paint.Color;

public class LCStest {
	private MainModel testModel;
	@Before
	public void setUp(){
		testModel = new MainModel();
		JFXPanel a = new JFXPanel();
	}
	@Test
	public void testDoLCSTrue() {
		ArrayList<Line> testList = new ArrayList<Line>();
		testList.add(new Line("abcde"));
		testList.add(new Line("defgh"));
		testList.add(new Line("bcdef"));
		testModel.setLeftTextLines(testList);

		testList.clear();
		testList.add(new Line("abcde"));
		testList.add(new Line("cdefg"));
		testList.add(new Line("bcdef"));
		testModel.setRightTextLines(testList);
		
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
	public void testReturnLargestIndexTrue() {
		assertEquals(3,LCS.returnLargestIndex(1,2,3));
	}
	@Test
	public void testResetArrayListBeforeLCSTrue() {
		ArrayList<Line> testList = new ArrayList<Line>();
		testList.add(new Line("abcde"));
		testList.add(new Line(false));
		testList.add(new Line("bcdef"));
		testModel.setLeftTextLines(testList);

		testList.clear();
		testList.add(new Line("abcde"));
		testList.add(new Line(false));
		testList.add(new Line("bcdef"));
		testModel.setRightTextLines(testList);
		LCS.resetArrayListBeforeLCS(testModel.getLeftSubModel().getTextPage().getTextLines(), testModel.getRightSubModel().getTextPage().getTextLines());
		
		assertEquals(testModel.getLeftSubModel().getTextPage().getLineText(1),testModel.getRightSubModel().getTextPage().getLineText(1));
	}
	@Test
	public void testInitializeArrayTrue() {
		int[][] testIntArray = { {0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0}};
		boolean[][] testBooleanArray = new boolean[4][4];
		int[][] expectedIntArray = {{0,-1,-2,-3},{-1,0,0,0},{-2,0,0,0},{-3,0,0,0}};
		
		
		LCS.initializeArray(testIntArray, testBooleanArray, 4, 4);
		
		for (int i = 0; i < 4 ; i++ )
			for (int j = 0; j < 4 ; j++) {
				assertEquals(testIntArray[i][j],expectedIntArray[i][j]);
				assertEquals(false,testBooleanArray[i][j]);
			}
		
	}
	@Test
	public void testMakeLCSMatrix() {
		//makeLCSMatrix(int[][], bool[][], Array<Line>, Array<Line>, row, column)
		int[][] testIntArray = {{0,-1,-2,-3},{-1,0,0,0},{-2,0,0,0},{-3,0,0,0}};
		boolean[][] testBooleanArray = new boolean[4][4];
		int[][] expectedArray = {{0,-1,-2,-3},{-1,1,0,-1},{-2,0,0,-1},{-3,-1,-1,-1}};
		ArrayList<Line> testLeftList = new ArrayList<Line>();
		ArrayList<Line> testRightList = new ArrayList<Line>();
		
		
		for (int i = 0; i < 4; i++ )
			for (int j = 0; j < 4; j++)
				testBooleanArray[i][j]=false;
		
		testLeftList.add(new Line("A"));
		testLeftList.add(new Line("B"));
		testLeftList.add(new Line("D"));
		
		testRightList.add(new Line("A"));
		testRightList.add(new Line("E"));
		testRightList.add(new Line("F"));
		
		LCS.makeLCSMatrix(testIntArray, testBooleanArray, testLeftList, testRightList, 4, 4);
		
		for(int i = 0; i < 4; i++ )
			for (int j = 0; j < 4; j++)
				assertEquals(testIntArray[i][j],expectedArray[i][j]);
		assertEquals(true,testBooleanArray[1][1]);
		
		
	}
	@Test
	public void testMakeResultStack() {
		//makeResultStack(int[][], bool[][], Array<Line>, Array<Line>, row, column, Stack<Line>, Stack<Line>)
		int[][] testIntArray = {{0,-1,-2,-3},{-1,1,0,-1},{-2,0,0,-1},{-3,-1,-1,-1}};
		boolean[][] testBooleanArray = new boolean[4][4];
		ArrayList<Line> testLeftList = new ArrayList<Line>();
		ArrayList<Line> testRightList = new ArrayList<Line>();
		Stack<Line> testLeftStack = new Stack<Line>();
		Stack<Line> testRightStack = new Stack<Line>();
		Line temp;
		
		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 4; j++) {
					testBooleanArray[i][j]=false;
			}
		testBooleanArray[1][1] = true;
		
		testLeftList.add(new Line("A"));
		testLeftList.add(new Line("B"));
		testLeftList.add(new Line("D"));
		
		testRightList.add(new Line("A"));
		testRightList.add(new Line("E"));
		testRightList.add(new Line("F"));
		
		LCS.makeResultStack(testIntArray, testBooleanArray, testLeftList, testRightList, 4, 4, testLeftStack, testRightStack);
		
		temp = testLeftStack.pop();
		assertEquals(Color.WHITE,temp.getLineColor());
		assertEquals("A",temp.getLineText());
		temp = testLeftStack.pop();
		assertEquals(Color.LIGHTGOLDENRODYELLOW,temp.getLineColor());
		assertEquals("B",temp.getLineText());
		temp = testLeftStack.pop();
		assertEquals(Color.LIGHTGOLDENRODYELLOW,temp.getLineColor());
		assertEquals("D",temp.getLineText());
		
		assertEquals(true,testLeftStack.isEmpty());
		
		temp = testRightStack.pop();
		assertEquals(Color.WHITE,temp.getLineColor());
		assertEquals("A",temp.getLineText());
		temp = testRightStack.pop();
		assertEquals(Color.LIGHTGOLDENRODYELLOW,temp.getLineColor());
		assertEquals("E",temp.getLineText());
		temp = testRightStack.pop();
		assertEquals(Color.LIGHTGOLDENRODYELLOW,temp.getLineColor());
		assertEquals("F",temp.getLineText());
		
		assertEquals(true,testRightStack.isEmpty());
		
	}
	@Test
	public void testMakeListFromStack() {
		//makeListFromStack(Array<Line>, Array<Line>, Stack<Line>, Stack<Line>)
		ArrayList<Line> testResultLeftList = new ArrayList<Line>();
		ArrayList<Line> testResultRightList = new ArrayList<Line>();
		Stack<Line> testLeftStack = new Stack<Line>();
		Stack<Line> testRightStack = new Stack<Line>();
		
		testLeftStack.push(new Line("D",Color.LIGHTGOLDENRODYELLOW));
		testLeftStack.push(new Line("B",Color.LIGHTGOLDENRODYELLOW));
		testLeftStack.push(new Line("A",Color.WHITE));
		
		testRightStack.push(new Line("F",Color.LIGHTGOLDENRODYELLOW));
		testRightStack.push(new Line("E",Color.LIGHTGOLDENRODYELLOW));
		testRightStack.push(new Line("A",Color.WHITE));
		
		LCS.makeListFromStack(testResultLeftList, testResultRightList, testLeftStack, testRightStack);
		
		assertEquals("A",testResultLeftList.get(0).getLineText());
		assertEquals(Color.WHITE,testResultLeftList.get(0).getLineColor());
		assertEquals("B",testResultLeftList.get(1).getLineText());
		assertEquals(Color.LIGHTGOLDENRODYELLOW,testResultLeftList.get(1).getLineColor());
		assertEquals("D",testResultLeftList.get(2).getLineText());
		assertEquals(Color.LIGHTGOLDENRODYELLOW,testResultLeftList.get(2).getLineColor());
		
		assertEquals("A",testResultRightList.get(0).getLineText());
		assertEquals(Color.WHITE,testResultRightList.get(0).getLineColor());
		assertEquals("E",testResultRightList.get(1).getLineText());
		assertEquals(Color.LIGHTGOLDENRODYELLOW,testResultRightList.get(1).getLineColor());
		assertEquals("F",testResultRightList.get(2).getLineText());
		assertEquals(Color.LIGHTGOLDENRODYELLOW,testResultRightList.get(2).getLineColor());
	}
	@Test
	public void testModifyMainModel() {
		//modifyMainModel(MainModel, Array<Line>, Array<Line>)
		ArrayList<Line> testLeftList = new ArrayList<Line>();
		ArrayList<Line> testRightList = new ArrayList<Line>();
		
		testLeftList.add(new Line("A",Color.WHITE));
		testLeftList.add(new Line("B",Color.LIGHTGOLDENRODYELLOW));
		testLeftList.add(new Line("D",Color.LIGHTGOLDENRODYELLOW));
		testModel.getLeftSubModel().setTextLines(testLeftList);
		
		testRightList.add(new Line("A",Color.WHITE));
		testRightList.add(new Line("E",Color.LIGHTGOLDENRODYELLOW));
		testRightList.add(new Line("F",Color.LIGHTGOLDENRODYELLOW));
		testModel.getRightSubModel().setTextLines(testRightList);
		
		assertEquals("A", testModel.getLeftSubModel().getTextPage().getLineText(0));
		assertEquals(Color.WHITE, testModel.getLeftSubModel().getTextPage().getLineColor(0));
		assertEquals("B", testModel.getLeftSubModel().getTextPage().getLineText(1));
		assertEquals(Color.LIGHTGOLDENRODYELLOW, testModel.getLeftSubModel().getTextPage().getLineColor(1));
		assertEquals("D", testModel.getLeftSubModel().getTextPage().getLineText(2));
		assertEquals(Color.LIGHTGOLDENRODYELLOW, testModel.getLeftSubModel().getTextPage().getLineColor(2));
		
		assertEquals("A", testModel.getRightSubModel().getTextPage().getLineText(0));
		assertEquals(Color.WHITE, testModel.getRightSubModel().getTextPage().getLineColor(0));
		assertEquals("E", testModel.getRightSubModel().getTextPage().getLineText(1));
		assertEquals(Color.LIGHTGOLDENRODYELLOW, testModel.getRightSubModel().getTextPage().getLineColor(1));
		assertEquals("F", testModel.getRightSubModel().getTextPage().getLineText(2));
		assertEquals(Color.LIGHTGOLDENRODYELLOW, testModel.getRightSubModel().getTextPage().getLineColor(2));
		
	}
	
	
}
