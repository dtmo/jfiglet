package com.github.dtmo.figlet;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.github.dtmo.figlet.FigFont.FigCharacter;
import com.github.dtmo.figlet.FigFont.PrintDirection;

/**
 * FigletRenderer renders text as FIGlet text.
 */
public class FigletRenderer {
	private FigFont figFont;
	private int smushMode;
	private FigFont.PrintDirection printDirection;

	/**
	 * Constructs a new instance of FigletRenderer.
	 * 
	 * @param figFont
	 *            The FIGlet font with which to render text.
	 */
	public FigletRenderer(final FigFont figFont) {
		this.figFont = figFont;
		this.smushMode = figFont.getFullLayout();
		this.printDirection = figFont.getPrintDirection();
	}

	/**
	 * Returns the text smushing mode that will be used when rendering FIGlet text.
	 * 
	 * @return The text smushing mode that will be used when rendering FIGlet text.
	 */
	public int getSmushMode() {
		return smushMode;
	}

	/**
	 * Sets the text smushing mode that will be used when rendering FIGlet text. If
	 * not set then the default smushing mode specified by the FIGfont will be used.
	 * 
	 * @param smushMode
	 *            The text smushing mode to set.
	 */
	public void setSmushMode(int smushMode) {
		this.smushMode = smushMode;
	}

	/**
	 * Returns the print direction for rendered FIGlet text.
	 * 
	 * @return The print direction for rendered FIGlet text.
	 */
	public FigFont.PrintDirection getPrintDirection() {
		return printDirection;
	}

	/**
	 * Sets the print direction for rendered FIGlet text. If not set then the
	 * default print direction of the FIGfont will be used.
	 * 
	 * @param printDirection The print direction to set.
	 */
	public void setPrintDirection(final FigFont.PrintDirection printDirection) {
		this.printDirection = printDirection;
	}

	/**
	 * Renders text as FIGlet text.
	 * 
	 * @param text
	 *            The text to render.
	 * @return The rendered FIGlet text as a multi-line string.
	 */
	public String renderText(final String text) {
		final StringBuilder result = new StringBuilder();

		final List<StringBuilder> rowBuilders = new ArrayList<>(figFont.getHeight());
		for (int row = 0; row < figFont.getHeight(); row++) {
			rowBuilders.add(new StringBuilder());
		}

		char prevChar = '\0';
		for (char character : text.toCharArray()) {

			// Treat tabs and spaces as spaces, and all other whitespace characters as
			// newlines.
			if (Character.isWhitespace(character)) {
				character = (character == '\t' || character == ' ') ? ' ' : '\n';
			}

			// Skip over unprintable characters.
			if ((character > '\0' && character < ' ' && character != '\n') || character == 127)
				continue;

			if (character != '\n') {
				final int smushAmount = figFont.calculateOverlapAmount(prevChar, character, smushMode, printDirection);
				final FigCharacter figChar = figFont.getFigCharacter(character);

				for (int row = 0; row < figFont.getHeight(); row++) {
					final StringBuilder rowBuilder = rowBuilders.get(row);

					if (rowBuilder.length() > 0) {
						if (printDirection == PrintDirection.LEFT_TO_RIGHT) {
							// Smush the new FIGcharacter onto the right of the previous FIGcharacter.
							for (int smushColumn = 0; smushColumn < smushAmount; smushColumn++) {
								int smushIndex = rowBuilder.length() - (smushColumn + 1);
								rowBuilder.setCharAt(smushIndex,
										figFont.smushem(rowBuilder.charAt(smushIndex),
												figChar.getCharacterAt(smushAmount - (smushColumn + 1), row), smushMode,
												printDirection));
							}
							rowBuilder.append(figChar.getRow(row).substring(smushAmount));
						} else {
							// Smush the new FIGcharacter into the left of the previous FIGcharacter.
							for (int smushColumn = 0; smushColumn < smushAmount; smushColumn++) {
								rowBuilder.setCharAt(smushColumn,
										figFont.smushem(rowBuilder.charAt(smushColumn), figChar
												.getCharacterAt((figChar.getWidth() - smushAmount) + smushColumn, row),
												smushMode, printDirection));
							}
							rowBuilder.insert(0, figChar.getRow(row).substring(0, figChar.getWidth() - smushAmount));
						}
					} else {
						rowBuilder.append(figChar.getRow(row));
					}
				}

				prevChar = character;
			} else {
				// We've encountered a newline. We need to render the current buffer and then
				// start a new one.
				result.append(rowBuilders.stream().map((rowBuilder) -> rowBuilder.toString())
						.map(s -> s.replace(figFont.getHardBlankChar(), ' '))
						.collect(Collectors.joining("\n", "", "\n")));

				for (int row = 0; row < figFont.getHeight(); row++) {
					rowBuilders.get(row).setLength(0);
				}

				prevChar = '\0';
			}
		}

		result.append(rowBuilders.stream().map((rowBuilder) -> rowBuilder.toString())
				.map(s -> s.replace(figFont.getHardBlankChar(), ' ')).collect(Collectors.joining("\n")));

		return result.toString();
	}
}
