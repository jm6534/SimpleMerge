package model;

import java.util.*;

import javafx.scene.paint.Color;

public class LCS { 
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
	public static void removeUselessFakeLinesBefore(ArrayList<Line> left, ArrayList<Line> right) {
		int i;
		int k = left.size();
		for (i = k-1; i >= 0; i--) {
			if ( !left.get(i).isRealLine() )
				left.remove(i);
		}
		k = right.size();
		for (i = k-1; i >= 0; i--) {
			if ( !right.get(i).isRealLine() )
				right.remove(i);
		}
	}
	public static void removeUselessFakeLinesAfter(ArrayList<Line> left, ArrayList<Line> right) {
		int i;
		int k=left.size();
		for (i=0;i<k-1;i++) {
			if (left.get(i).isRealLine()&&!right.get(i).isRealLine()) { // when left is real and right is fake
				if (!left.get(i+1).isRealLine()&&right.get(i+1).isRealLine()) { // and at the next line, if left is fake and right is real
					left.remove(i+1); // remove those fake lines
					right.remove(i);
					k--;	// size of ArrayList has been decreased
					if (!compareOneLine(left.get(i),right.get(i))) {
						left.get(i).setLineColor(Color.LIGHTGOLDENRODYELLOW);
						right.get(i).setLineColor(Color.LIGHTGOLDENRODYELLOW);
					}
					else {
						left.get(i).setLineColor(Color.WHITE);
						right.get(i).setLineColor(Color.WHITE);
					}
				}	
			}
			else if (!left.get(i).isRealLine()&&right.get(i).isRealLine()) { // when left is fake and right is real
				if (left.get(i+1).isRealLine()&&!right.get(i+1).isRealLine()) { // and at the next line, if left is real and right is line
					left.remove(i); // remove those fake lines
					right.remove(i+1);
					k--;	// size of ArrayList has been decreased
					if (!compareOneLine(left.get(i),right.get(i))) {
						left.get(i).setLineColor(Color.LIGHTGOLDENRODYELLOW);
						right.get(i).setLineColor(Color.LIGHTGOLDENRODYELLOW);
					}
					else {
						left.get(i).setLineColor(Color.WHITE);
						right.get(i).setLineColor(Color.WHITE);
					}
				}	
			}
		}
	}
	public static void doLCS(MainModel lcsMainModel) { // this method will return MainModel
		int i, j;
		int[][] lcsCount; // this array makes lcs matrix 
		boolean[][] isSame; // save the result of compare 
		ArrayList<Line> leftList;
		ArrayList<Line> rightList;
		Stack<Line> reversedResultLeft = new Stack<Line>();
		Stack<Line> reversedResultRight = new Stack<Line>();
		ArrayList<Line> resultLeft;
		ArrayList<Line> resultRight;
		
		
		leftList=lcsMainModel.getLeftSubModel().getTextPage().getTextLines();
		rightList=lcsMainModel.getRightSubModel().getTextPage().getTextLines();
		
		removeUselessFakeLinesBefore(leftList,rightList);
		
		int row = leftList.size()+1; // row and column for lcs count matrix
		int column = rightList.size()+1; 
		
		
		lcsCount=new int[row][column];
		isSame=new boolean[row][column];
		
		
		for (i = 0; i < row ; i++ ) { // initialize array
			for (j = 0 ; j < column ; j++ ) {
				if (i==0) 
					lcsCount[i][j] = -j;
				else if (j==0)
					lcsCount[i][j] = -i;
				else
					lcsCount[i][j] = 0;
				isSame[i][j]=false;
			}
		}
		
		for (i = 1; i < row ; i++ ) { // 
			for (j = 1; j < column ; j++ ) { // fill the matrix left to right
				if(compareOneLine(leftList.get(i-1),rightList.get(j-1))){
					lcsCount[i][j] = lcsCount[i-1][j-1]+1;
					isSame[i][j] = true;
				}
				else { // if left and above is same, it came from left
					int k = returnLargestIndex(lcsCount[i-1][j],lcsCount[i][j-1],lcsCount[i-1][j-1]); 
					switch (k){
						case 1:
							lcsCount[i][j] = lcsCount[i-1][j]-1;
							break;
						case 2:
							lcsCount[i][j] = lcsCount[i][j-1]-1;
							break;
						case 3:
							lcsCount[i][j] = lcsCount[i-1][j-1]-1;
							break;
					}
				}
			}
		}
		
		i = row-1;
		j = column-1;
		
		while (i>0||j>0) {
			if (i==0&&j==0) // if making stack is over
				break;
			else if (i==0&&j>0) { // no more on only left
				reversedResultLeft.push(new Line(false));
				reversedResultRight.push(rightList.get(j-1));
				j--;
			}
			else if (i>0&&j==0) { // no more on only right
				reversedResultLeft.push(leftList.get(i-1));
				reversedResultRight.push(new Line(false));
				i--;
			}
			else {
				if (isSame[i][j]) { // if reached same side
					reversedResultLeft.push(leftList.get(i-1));
					reversedResultRight.push(rightList.get(j-1));
					i--;
					j--;
				}
				else {
					int k = returnLargestIndex(lcsCount[i-1][j],lcsCount[i][j-1],lcsCount[i-1][j-1]);
					switch (k) {
						case 1:
							leftList.get(i-1).setLineColor(Color.LIGHTGOLDENRODYELLOW);
							reversedResultLeft.push(leftList.get(i-1));
							reversedResultRight.push(new Line(false));
							i--;
							break;
						case 2:
							reversedResultLeft.push(new Line(false));
							rightList.get(j-1).setLineColor(Color.LIGHTGOLDENRODYELLOW);
							reversedResultRight.push(rightList.get(j-1));
							j--;
							break;
						case 3:
							Line leftinput = leftList.get(i-1);
							leftinput.setLineColor(Color.LIGHTGOLDENRODYELLOW);
							Line rightinput = rightList.get(j-1);
							rightinput.setLineColor(Color.LIGHTGOLDENRODYELLOW);
							reversedResultLeft.push(leftinput);
							reversedResultRight.push(rightinput);
							i--;
							j--;
							break;
					}
					/*
					if (lcsCount[i-1][j]==lcsCount[i][j-1]) { 
						reversedResultLeft.push(new Line(false));
						reversedResultRight.push(rightList.get(j-1));
						j--;
					}
					else {
						if (lcsCount[i-1][j]>lcsCount[i][j-1]) {
							reversedResultLeft.push(leftList.get(i-1));
							reversedResultRight.push(new Line(false));
							i--;
						}
						else {
							reversedResultLeft.push(new Line(false));
							reversedResultRight.push(rightList.get(j-1));
							j--;
						}
					}
					*/
				}
			}
		}
		//lcsMainModel.setLeftTextLines(reversedResultLeft);
		//lcsMainModel.setRightTextLines(reversedResultRight);
		resultLeft=new ArrayList<Line>();
		resultRight=new ArrayList<Line>();
		
		while(!reversedResultLeft.isEmpty()) {
			resultLeft.add(reversedResultLeft.pop());
		}
		while(!reversedResultRight.isEmpty()) {
			resultRight.add(reversedResultRight.pop());
		}
		
		// at this point, both ArrayList has same size
		
		//removeUselessFakeLinesAfter(resultLeft,resultRight);
		lcsMainModel.setLeftTextLines(resultLeft);
		lcsMainModel.setRightTextLines(resultRight);
		
	}
}
