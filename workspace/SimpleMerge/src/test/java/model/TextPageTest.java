package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import controller.TextController;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionModel;
import javafx.scene.input.KeyEvent;

public class TextPageTest{
	private JFXPanel a = new JFXPanel();
	private TextPage textPage;
	private File mockFile;
	private int lineCnt;
	private static final String FILE_NAME = "test-file.txt";
    private static final String LINE_CONTENT = "line test";

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
	public void testLoadAndSaveFuncInTextPage() throws IOException {
		String strTmp = new String();
		textPage = new TextPage();

		FileUtils.writeStringToFile(mockFile, LINE_CONTENT + lineCnt++ +"\n","UTF8", true);
    	FileUtils.writeStringToFile(mockFile, LINE_CONTENT + lineCnt++ +"\n","UTF8", true);
    	FileUtils.writeStringToFile(mockFile, LINE_CONTENT + lineCnt++ +"\n","UTF8", true);

		textPage.setFilePath(mockFile);							// method setFilePath() test
		assertEquals(textPage.getFilePathProperty().getValue(), mockFile.getAbsolutePath());
		
		textPage.addLineText(LINE_CONTENT + lineCnt++ + "\n");	// method addLineText() test
		assertEquals(textPage.getMaxNListProperty(), lineCnt);

		textPage.deleteLine(--lineCnt);							// method deleteLine() test
		assertEquals(textPage.getMaxNListProperty(), lineCnt);	// method getMaxNListProperty() test

		/* method getTextFieldForSave() test */
		assertEquals(textPage.getTextFieldForSave().split(System.lineSeparator())[1], textPage.getLineText(1));

		System.out.println();	
	}
	
	@Test
	public void testIsRealLineModification() {
		textPage = new TextPage(mockFile);
		
		assertEquals(textPage.isRealLine(lineCnt - 1), true);	// method isRealLine() test

		textPage.addFakeLine();									// method addFakeLine() test
		lineCnt++;
		assertEquals(textPage.isRealLine(lineCnt - 1), false);
		
		textPage.toogleIsRealLine(lineCnt - 1);					// method toggleIsRealLine() test
		assertEquals(textPage.isRealLine(lineCnt - 1), true);
	}

}