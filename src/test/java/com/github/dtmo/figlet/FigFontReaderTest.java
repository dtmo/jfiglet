package com.github.dtmo.figlet;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FigFontReaderTest {

	@Test
	public void testParseHeader() {
		FigFont.Builder fontBuilder = new FigFont.Builder();

		FigFontReader.parseHeader("flf2a$ 6 5 16 15 11 0 24463 229", fontBuilder);

		assertEquals('$', fontBuilder.getHardBlankChar());
		assertEquals(6, fontBuilder.getHeight());
		assertEquals(5, fontBuilder.getBaseline());
		assertEquals(16, fontBuilder.getMaxLength());
		assertEquals(15, fontBuilder.getOldLayout());
		assertEquals(11, fontBuilder.getCommentLines());
		assertEquals(FigFont.PrintDirection.LEFT_TO_RIGHT, fontBuilder.getPrintDirection());
		assertEquals(24463, fontBuilder.getFullLayout());
		assertEquals(229, fontBuilder.getCodetagCount());
	}
}
