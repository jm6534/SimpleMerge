package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import javafx.embed.swing.JFXPanel;

public class TextPageTest{
	private TextPage textPage;
	private File mockFile;
	private int lineCnt;
	private SubModel mockSubModel;
	
	private static final String FILE_NAME = "test-file.txt";
    private static final String LINE_CONTENT = "line test";
	private JFXPanel a = new JFXPanel();

	@Rule
	public final TemporaryFolder tempFolder = new TemporaryFolder();
	 
    
    @Before
    public void init() throws IOException {
    	mockFile = tempFolder.newFile(FILE_NAME);
    	FileUtils.writeStringToFile(mockFile, LINE_CONTENT + lineCnt++ + "\n","UTF8", true);
    }
    
    @Test
    public void testTextPageDefaultConstructor() {
    	textPage = new TextPage();
    	assertEquals(textPage.getFilePath(), textPage.getFilePathProperty().getValue());
    }
    
    @Test
    public void testTextPageConstructorWithFile() {
    	textPage = new TextPage(mockFile);
    	assertTrue(mockFile.exists());    	
     	assertEquals(textPage.getFilePath(), mockFile.getAbsolutePath());
     	assertEquals(textPage.getLineText(0), LINE_CONTENT + (lineCnt - 1));
    }

	@Test
	public void testTextFieldForSave() throws IOException {
		String strTmp = new String();
		mockSubModel = EasyMock.createMock(SubModel.class);
		FileUtils.writeStringToFile(mockFile, LINE_CONTENT + lineCnt++ +"\n","UTF8", true);
    	FileUtils.writeStringToFile(mockFile, LINE_CONTENT + lineCnt++ +"\n","UTF8", true);
    	FileUtils.writeStringToFile(mockFile, LINE_CONTENT + lineCnt++ +"\n","UTF8", true);
		textPage = new TextPage(mockFile);
		
		textPage.addLineText(LINE_CONTENT + lineCnt++ + "\n");
		System.out.println(textPage.getLineText(0));
		System.out.println(textPage.getLineText(1));
		System.out.println(textPage.getLineText(2));
		System.out.println(textPage.getLineText(3));
		System.out.println(textPage.getLineText(4));
		assertEquals(textPage.getMaxNListProperty(), lineCnt);

		textPage.deleteLine(--lineCnt);
		assertEquals(textPage.getMaxNListProperty(), lineCnt);
		///////////////////////////////////////////////////////////
		assertEquals(textPage.getTextFieldForSave().split("\n")[1], textPage.getLineText(1));
		////////////////////////////////////////////////////////////
		System.out.println();
		
	}

}
