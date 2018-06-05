package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class TextPageTest{
	private TextPage textPage;
	private File mockFile;

	private static final String FILE_NAME = "test-file.txt";
    private static final String LINE_CONTENT = "line";

	@Rule
	public final TemporaryFolder tempFolder = new TemporaryFolder();
	 
    
    @Before
    public void init() throws IOException {
    	mockFile = tempFolder.newFile(FILE_NAME);
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
    }

	@Test
	public void testSetTextField() throws IOException {
		textPage = new TextPage();
		textPage.setTextField(LINE_CONTENT, 1);
//		assertEquals(LINE_CONTENT, textPage.getLineText(0));
	}
/*
	@Test
	public void testAddFakeLines() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetRealLine() {
		fail("Not yet implemented");
	}

	@Test
	public void testToogleIsRealLine() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetLineColor() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetLineText() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddLineText() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddFakeLine() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteLine() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetTextLines() {
		fail("Not yet implemented");
	}

	@Test
	public void testClearBackground() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetSelectedLineColor() {
		fail("Not yet implemented");
	}
*/
}
