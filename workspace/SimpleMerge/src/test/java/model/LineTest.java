package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import javafx.embed.swing.JFXPanel;
import javafx.scene.paint.Color;

public class LineTest{
	
	private Line lineTest;
	private String LINE_TEXT_TEST = "Line test.";
	private Color LINE_COLOR_TEST = Color.WHITE; 
	private JFXPanel a = new JFXPanel();
	
	@Before
	public void init() {
		lineTest = new Line(LINE_TEXT_TEST, LINE_COLOR_TEST);
	}
	
	@Test
	public void testLineConstructor() {
		Line lineCnstrTest = new Line();
		assertEquals("", lineCnstrTest.getLineText());
		assertEquals(Color.WHITE, lineCnstrTest.getLineColor());
		assertTrue(lineCnstrTest.isRealLine());
	}
	
	@Test
	public void testlineModificationWithFakeLine() {
		assertEquals(lineTest.getLineText(), LINE_TEXT_TEST);
		assertEquals(lineTest.getLineColor(), LINE_COLOR_TEST);
		assertTrue(lineTest.isRealLine());		
		
		lineTest.setFakeLine();
		assertEquals(lineTest.getLineColor(), Color.LIGHTGRAY);
		assertEquals(lineTest.isRealLine(), false);
		
		lineTest.setFakeLine(LINE_TEXT_TEST);
		assertEquals(lineTest.getLineText(), LINE_TEXT_TEST);
		assertEquals(lineTest.getLineColor(), Color.LIGHTGRAY);
		assertEquals(lineTest.isRealLine(), false);
	}
	
	@Test
	public void testlineModificationWithRealLine() {
		assertEquals(lineTest.getLineText(), LINE_TEXT_TEST);
		assertEquals(lineTest.getLineColor(), LINE_COLOR_TEST);
		assertTrue(lineTest.isRealLine());		
		
		lineTest.setLineText(LINE_TEXT_TEST);
		assertEquals(lineTest.getLineText(), LINE_TEXT_TEST);
	}
	
	@Test
	public void testlineColor() {
		Line lineClrTest = new Line();
		
		assertEquals(lineClrTest.getLineColorProperty().getValue(), Color.WHITE);
		assertEquals(lineClrTest.isRealLine(), true);
		
		lineClrTest.setLineWHITE();
		assertEquals(lineClrTest.getLineColor(), LINE_COLOR_TEST);
	}
	
	@Test
	public void testrealLine() {
		assertEquals(lineTest.getLineText(), LINE_TEXT_TEST);
		assertEquals(lineTest.getLineColor(), LINE_COLOR_TEST);
		assertTrue(lineTest.isRealLine());		
		
		lineTest.setIsRealLine(false);
		assertEquals(lineTest.isRealLine(), false);
		
		lineTest.toggleIsRealLine();
		assertTrue(lineTest.isRealLine());
		
	}
	
	
	
}
