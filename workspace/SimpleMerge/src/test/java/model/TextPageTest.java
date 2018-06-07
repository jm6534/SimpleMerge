package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import javafx.embed.swing.JFXPanel;
import javafx.scene.paint.Color;

public class TextPageTest{
	private JFXPanel a = new JFXPanel();
	private TextPage textPage;
	private File mockFile;
	private int lineCnt;
	private static final String FILE_NAME = "test_file.txt";
    private static final String LINE_CONTENT = "line test";

	@Rule
	public final TemporaryFolder tempFolder = new TemporaryFolder();
	 
    @Before
    public void init() throws IOException {
    	lineCnt = 0;
    	mockFile = tempFolder.newFile(FILE_NAME);
    	FileUtils.writeStringToFile(mockFile, LINE_CONTENT + lineCnt++ + "\n","UTF8", true);
    }
    @Test
    public void testTextPageConstructor() {
    	try {
    		new TextPage();
    		new TextPage(mockFile);
    	}
    	catch(Exception e) {
    		fail(e.getMessage());
    	}
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
	@Test
	public void testSetLineColor() {
		Color testColor;
		testColor = Color.WHITE;
		textPage = new TextPage(mockFile);
		
	}
}