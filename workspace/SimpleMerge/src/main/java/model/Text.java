package model;

import java.util.ArrayList;

public class Text {
	private ArrayList<Line> textArrList;
	
	// Split whole string to line by line when the text object first made
	public Text(String strTextArea) {
		textArrList = new ArrayList<>();
		String strArr[] = strTextArea.split("\n");
		
		for(int i = 0;i < strArr.length;i++) {
			this.textArrList.add(new Line(strArr[i]));
		}
	}
	
	
	
	////////////////// 함수 어디까지 구현해야 되는지 모르겟음 (arraylist통째로 반환? / arraylist내부 함수 오버라이딩?)
}
