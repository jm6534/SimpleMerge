package model;

import java.util.*;

public class LCS { 
	private static boolean compareInt(int x, int y) {
		if (x>y)
			return true;
		else
			return false;
	}
	private static int returnBiggerInt(int x, int y) {
		if (compareInt(x,y))
			return x;
		else
			return y;
	}
	public static boolean compareOneLine(Line left, Line right) {
		if (left.getLineText().equals(right.getLineText())) // return true if two Strings are 'ACTUALLY' same
			return true;
		else
			return false;
	}
	public static void doLCS(MainModel lcsMainModel) { // this method will return MainModel
		int i, j;
		int[][] lcsCount; // this array makes lcs matrix 
		boolean[][] isSame; // save the result of compare 
		ArrayList<Line> leftList;
		ArrayList<Line> rightList;
		Stack<Line> reversedResultLeft = new Stack<Line>();
		Stack<Line> reversedResultRight = new Stack<Line>();
		
		
		leftList=lcsMainModel.getLeftSubModel().getTextPage().getTextLines();
		rightList=lcsMainModel.getRightSubModel().getTextPage().getTextLines();
		
		
		
		int row = leftList.size()+1; // row and column for lcs count matrix
		int column = rightList.size()+1; 
		
		
		lcsCount=new int[row][column];
		isSame=new boolean[row][column];
		
		
		for (i = 0; i < row ; i++ ) { // initialize array
			for (j = 0 ; j < column ; j++ ) {
				lcsCount[i][j]=0;
				isSame[i][j]=false;
			}
		}

		for (i = 1; i < row ; i++ ) {
			for (j = 1; j < column ; j++ ) { // fill the matrix left to right
				if(compareOneLine(leftList.get(i-1),rightList.get(j-1))){
					lcsCount[i][j]=lcsCount[i-1][j-1]+1;
					isSame[i][j]=true;
				}
				else { // if left and above is same, it came from left
					lcsCount[i][j]=returnBiggerInt(lcsCount[i-1][j],lcsCount[i][j-1]); 
				}
			}
		}
		
		i = row-1;
		j = column-1;
		
		while (i>0||j>0) {
			if (i==0&&j==0) // if making stack is over
				break;
			else if (i==0&&j>0) { // no more on only left
				//System.out.println("i="+i+", j="+j+"no more on left");
				reversedResultLeft.push(new Line(false));
				reversedResultRight.push(rightList.get(j-1));
				j--;
			}
			else if (i>0&&j==0) { // no more on only right
				//System.out.println("i="+i+", j="+j+"no more on right");
				reversedResultLeft.push(leftList.get(i-1));
				reversedResultRight.push(new Line(false));
				i--;
			}
			else {
				if (isSame[i][j]) { // if reached same side
					//System.out.println("i="+i+", j="+j+" equal");
					reversedResultLeft.push(leftList.get(i-1));
					reversedResultRight.push(rightList.get(j-1));
					i--;
					j--;
				}
				else {
					if (lcsCount[i-1][j]==lcsCount[i][j-1]) { 
						//System.out.println("i="+i+", j="+j+" not equal but left up same");
						reversedResultLeft.push(new Line(false));
						reversedResultRight.push(rightList.get(j-1));
						j--;
					}
					else {
						if (lcsCount[i-1][j]>lcsCount[i][j-1]) {
							//System.out.println("i="+i+", j="+j+" up is bigger");
							reversedResultLeft.push(leftList.get(i-1));
							reversedResultRight.push(new Line(false));
							i--;
						}
						else {
							//System.out.println("i="+i+", j="+j+" left is bigger");
							reversedResultLeft.push(new Line(false));
							reversedResultRight.push(rightList.get(j-1));
							j--;
						}
					}
				}
			}
		}
		lcsMainModel.setLeftTextLines(reversedResultLeft);
		lcsMainModel.setRightTextLines(reversedResultRight);
		
	}
}
