package model;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import javafx.embed.swing.JFXPanel;

public class MainModelTest {
	private JFXPanel a = new JFXPanel();
	private MainModel mainModel;
	private File mockFileForLeft, mockFileForRight;
	private ArrayList<String> leftFileStr;
	private ArrayList<String> rightFileStr;
	

	@Rule
	public final TemporaryFolder tempFolder = new TemporaryFolder();
	
	@Before
	public void init() throws IOException {
		mockFileForLeft = tempFolder.newFile("sample_file_left.txt");
		mockFileForRight = tempFolder.newFile("sample_file_right.txt");
		leftFileStr = new ArrayList<String>(Arrays.asList("A", "B", "C", "D", "E"));
		rightFileStr = new ArrayList<String>(Arrays.asList("A", "B", "", "D", "E", "F"));
		
		for(int i = 0;i < leftFileStr.size();i++) {
			FileUtils.writeStringToFile(mockFileForLeft, leftFileStr.get(i), "UTF8", true);
		}
		for(int i = 0;i < rightFileStr.size();i++) {
			FileUtils.writeStringToFile(mockFileForRight, rightFileStr.get(i), "UTF8", true);
		}
	}
	
	@Test
	public void TestMainModelWithGivenFiles() {
		mainModel = new MainModel();
		mainModel.setLeftSubModel(new SubModel(mockFileForLeft));
		mainModel.setRightSubModel(new SubModel(mockFileForRight));

		////////////////////// Needs to be modified //////////////////////////
		//////////////////////////////////////////////////////////////////////
		assertEquals(mainModel.isCompared(), false);	// both sub models are not been compared
		mainModel.toggleIsCompared();					// as the compare button clicked, isCompare toggled.
		assertEquals(mainModel.LCS(), true);			// conduct LCS (Comparing)
		assertEquals(mainModel.isCompared(), true);
		
		ArrayList<Line> tmpLineList = new ArrayList<Line>();
		for(int i = 0;i < leftFileStr.size();i++) {
			tmpLineList.add(new Line(leftFileStr.get(i)));
		}
		/////////////////////////////////////////////////////////////////////
	}
}
