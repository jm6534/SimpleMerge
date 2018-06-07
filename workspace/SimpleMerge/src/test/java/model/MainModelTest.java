package model;

import static org.easymock.EasyMock.createMock;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.awt.Color;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import javafx.embed.swing.JFXPanel;

public class MainModelTest {
	private JFXPanel a = new JFXPanel();
	private MainModel mainModel;
	private SubModel mockLeftSubModel;
	private SubModel mockRightSubModel;
	private File mockLeftFile;
	private File mockRightFile;

	@Rule
	public final TemporaryFolder tempFolder = new TemporaryFolder();
	
	@Before
	public void init() throws IOException {
		mainModel = new MainModel();
		mockLeftFile = tempFolder.newFile("lefttFile.txt");
		mockRightFile = tempFolder.newFile("rightFIle.txt");
		FileUtils.writeStringToFile(mockLeftFile, "HelloWorld\n", "UTF8", true);
		FileUtils.writeStringToFile(mockLeftFile, "HelloWorld\n", "UTF8", true);
		FileUtils.writeStringToFile(mockLeftFile, "HelloWorld\n", "UTF8", true);
		FileUtils.writeStringToFile(mockRightFile, "SETEAMPROJECT\n", "UTF8", true);
		FileUtils.writeStringToFile(mockRightFile, "SETEAMPROJECT\n", "UTF8", true);
		FileUtils.writeStringToFile(mockRightFile, "SETEAMPROJECT\n", "UTF8", true);
		mockLeftSubModel = new SubModel(mockLeftFile);
		mockRightSubModel = new SubModel(mockRightFile);
	}
	@Test
	public void testMainModelConstructor() {
		try {
			new MainModel();
		}
		catch(Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testSetLeftSubModel() {
		mainModel.setLeftSubModel(mockLeftSubModel);
		assertEquals(mainModel.getLeftSubModel(), mockLeftSubModel);
	}

	@Test
	public void testSetRightSubModel() {
		mainModel.setRightSubModel(mockRightSubModel);
		assertEquals(mainModel.getRightSubModel(), mockRightSubModel);
	}

	@Test
	public void testSetIsCompared() {
		mainModel.setIsCompared(true);
		assertTrue(mainModel.isCompared());
		assertTrue(mainModel.getIsComparedProperty().getValue());
	}

	@Test
	public void testToggleIsCompared() {
		mainModel.setIsCompared(false);
		mainModel.toggleIsCompared();
		assertTrue(mainModel.isCompared());
		assertTrue(mainModel.getIsComparedProperty().getValue());		
	} 
	
	@Test
	public void testCopyToLeft() {
	
	}
	
	@Test
	public void testCopyToRight() {
		mainModel.getIsComparedProperty().setValue(true);
		
		mockLeftSubModel.setIsEditable(false);
		System.out.println(mockLeftSubModel.isEditable());
		mockRightSubModel.setIsEditable(false);
		System.out.println(mockRightSubModel.isEditable());
		System.out.println(mockLeftSubModel.getTextPage().getMaxNListProperty());
		mockLeftSubModel.getTextPage().setLineYELLOW(0);
		mockLeftSubModel.getTextPage().setLineYELLOW(1);
		mockLeftSubModel.getTextPage().setLineYELLOW(2);
		mockRightSubModel.getTextPage().setLineYELLOW(0);
		mockRightSubModel.getTextPage().setLineYELLOW(1);
		mockRightSubModel.getTextPage().setLineYELLOW(2);
		mockLeftSubModel.getTextPage().getSelectedIndexProperty().setValue(1);
		mainModel.setLeftSubModel(mockLeftSubModel);
		mainModel.setRightSubModel(mockRightSubModel);
		assertEquals(mainModel.copyToRight(), true);
	}
}
