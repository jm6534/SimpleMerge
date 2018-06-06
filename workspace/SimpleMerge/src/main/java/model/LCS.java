package model;

import java.util.*;
import javafx.scene.paint.Color;

public class LCS { 
	public static void modifyMainModel(MainModel main, ArrayList<Line> leftList, ArrayList<Line> rightList) {
		main.setLeftTextLines(leftList);
		main.setRightTextLines(rightList);
	}
	public static void makeListFromStack(ArrayList<Line> resultLeft, ArrayList<Line> resultRight,
			Stack<Line> resultStackLeft, Stack<Line> resultStackRight) {
	
		while(!resultStackLeft.isEmpty()) {
			resultLeft.add(resultStackLeft.pop());
		}
		while(!resultStackRight.isEmpty()) {
			resultRight.add(resultStackRight.pop());
		}
	}
	public static boolean makeResultStack(int[][] intArray, boolean[][] boolArray,
			ArrayList<Line> leftList, ArrayList<Line> rightList, int row, int column,
			Stack<Line> leftStack, Stack<Line> rightStack) {
		int i = row-1;
		int j = column-1;
		boolean isModified = false;
		
		while (i>0||j>0) {
			if (i==0&&j==0) // if making stack is over
				break;
			else if (i==0&&j>0) { // no more on only left
				leftStack.push(new Line(false));
				rightList.get(j-1).setLineYELLOW();
				rightStack.push(rightList.get(j-1));
				j--;
				isModified = true;
			}
			else if (i>0&&j==0) { // no more on only right
				leftList.get(i-1).setLineYELLOW();
				leftStack.push(leftList.get(i-1));
				rightStack.push(new Line(false));
				i--;
				isModified = true;
			}
			else {
				if (boolArray[i][j]) { // if reached same side
					leftStack.push(leftList.get(i-1));
					rightStack.push(rightList.get(j-1));
					i--;
					j--;
				}
				else {
					int k = returnLargestIndex(intArray[i-1][j], intArray[i][j-1], intArray[i-1][j-1]);
					switch (k) {
						case 1:
							leftList.get(i-1).setLineYELLOW();
							leftStack.push(leftList.get(i-1));
							rightStack.push(new Line(false));
							i--;
							isModified = true;
							break;
						case 2:
							leftStack.push(new Line(false));
							rightList.get(j-1).setLineYELLOW();
							rightStack.push(rightList.get(j-1));
							j--;
							isModified = true;
							break;
						case 3:
							Line leftinput = leftList.get(i-1);
							leftinput.setLineYELLOW();
							Line rightinput = rightList.get(j-1);
							rightinput.setLineYELLOW();
							leftStack.push(leftinput);
							rightStack.push(rightinput);
							i--;
							j--;
							isModified = true;
							break;
					}
				}
			}
		}
		
		return isModified;
	}
	public static void makeLCSMatrix(int[][] intArray, boolean[][] boolArray,
			ArrayList<Line> leftList, ArrayList<Line> rightList, int row, int column) {
		for (int i = 1; i < row ; i++ ) { // 
			for (int j = 1; j < column ; j++ ) { // fill the matrix left to right
				if(compareOneLine(leftList.get(i-1),rightList.get(j-1))){
					intArray[i][j] = intArray[i-1][j-1]+1;
					boolArray[i][j] = true;
				}
				else { // if left and above is same, it came from left
					int k = returnLargestIndex(intArray[i-1][j], intArray[i][j-1], intArray[i-1][j-1]); 
					switch (k){
						case 1:
							intArray[i][j] = intArray[i-1][j]-1;
							break;
						case 2:
							intArray[i][j] = intArray[i][j-1]-1;
							break;
						case 3:
							intArray[i][j] = intArray[i-1][j-1]-1;
							break;
					}
				}
			}
		}
	}
	public static void initializeArray(int[][] intArray, boolean[][] boolArray, int row, int column) {
		for (int i = 0; i < row ; i++ ) { // initialize array
			for (int j = 0 ; j < column ; j++ ) {
				if (i==0) 
					intArray[i][j] = -j;
				else if (j==0)
					intArray[i][j] = -i;
				else
					intArray[i][j] = 0;
				boolArray[i][j]=false;
			}
		}
	}
	public static int returnLargestIndex(int x, int y, int z) { // x == up, y == left, z == diagonal
		if (z>=x&&z>=y) // 
			return 3;
		else {
			if (x>y)
				return 1;
			else
				return 2;
		}
	}
	
	public static boolean compareOneLine(Line left, Line right) {
		if (left.getLineText().equals(right.getLineText())) // return true if two Strings are 'ACTUALLY' same
			return true;
		else
			return false;
	}
	
	public static void resetArrayListBeforeLCS(ArrayList<Line> left, ArrayList<Line> right) {
		int i;
		int k = left.size();
		for (i = k-1; i >= 0; i--) {
			if (left.get(i).getLineColor() != Color.WHITE)
				left.get(i).setLineWHITE();
			if ( !left.get(i).isRealLine() )
				left.remove(i);
		}
		k = right.size();
		for (i = k-1; i >= 0; i--) {
			if (right.get(i).getLineColor()!= Color.WHITE)
				right.get(i).setLineWHITE();
			if ( !right.get(i).isRealLine() )
				right.remove(i);
		}
	}
	
	public static boolean doLCS(MainModel lcsMainModel) { // this method will return MainModel
		int[][] lcsCount; // this array makes lcs matrix 
		boolean[][] isSame; // save the result of compare 
		ArrayList<Line> leftList = lcsMainModel.getLeftSubModel().getTextPage().getTextLines();
		ArrayList<Line> rightList=lcsMainModel.getRightSubModel().getTextPage().getTextLines();
		Stack<Line> resultStackLeft = new Stack<Line>();
		Stack<Line> resultStackRight = new Stack<Line>();
		ArrayList<Line> resultLeft = new ArrayList<Line>();
		ArrayList<Line> resultRight = new ArrayList<Line>();
		boolean isModified = false;
		
		resetArrayListBeforeLCS(leftList,rightList);
		
		int row = leftList.size()+1; // row and column for lcs count matrix
		int column = rightList.size()+1; 
		
		lcsCount=new int[row][column];
		isSame=new boolean[row][column];
	
		initializeArray(lcsCount, isSame, row, column);
		makeLCSMatrix(lcsCount, isSame, leftList, rightList, row, column);
		isModified=makeResultStack(lcsCount, isSame, leftList, rightList, row, column, resultStackLeft, resultStackRight);
		makeListFromStack(resultLeft, resultRight, resultStackLeft, resultStackRight);
		modifyMainModel(lcsMainModel, resultLeft, resultRight);
		
		return isModified;
	}
}
