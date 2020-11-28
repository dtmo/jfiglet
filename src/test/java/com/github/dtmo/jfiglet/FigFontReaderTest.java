package com.github.dtmo.jfiglet;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.github.dtmo.jfiglet.FigFont;
import com.github.dtmo.jfiglet.FigFontReader;

public class FigFontReaderTest {

	@Test
	public void testParseHeader() {
		FigFont.Builder fontBuilder = new FigFont.Builder();

		FigFontReader.parseHeader("flf2a$ 6 5 16 15 11 1 24463 229", fontBuilder);

		assertEquals('$', fontBuilder.getHardBlankChar());
		assertEquals(6, fontBuilder.getHeight());
		assertEquals(5, fontBuilder.getBaseline());
		assertEquals(16, fontBuilder.getMaxLength());
		assertEquals(15, fontBuilder.getOldLayout());
		assertEquals(11, fontBuilder.getCommentLines());
		assertEquals(FigFont.PrintDirection.RIGHT_TO_LEFT, fontBuilder.getPrintDirection());
		assertEquals(24463, fontBuilder.getFullLayout());
		assertEquals(229, fontBuilder.getCodetagCount());
	}

	@Test
	public void testDefaultHeaderValues() {
		FigFont.Builder fontBuilder = new FigFont.Builder();

		FigFontReader.parseHeader("flf2a$ 5 4 20 11 1", fontBuilder);

		assertEquals('$', fontBuilder.getHardBlankChar());
		assertEquals(5, fontBuilder.getHeight());
		assertEquals(4, fontBuilder.getBaseline());
		assertEquals(20, fontBuilder.getMaxLength());
		assertEquals(11, fontBuilder.getOldLayout());
		assertEquals(1, fontBuilder.getCommentLines());
		assertEquals(FigFont.PrintDirection.LEFT_TO_RIGHT, fontBuilder.getPrintDirection());
		assertEquals(11, fontBuilder.getFullLayout());
		assertEquals(0, fontBuilder.getCodetagCount());
	}

}
