package com.github.dtmo.figlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * FigFont represents a FIGlet font that may be used to render text.
 */
public class FigFont {
	/**
	 * The direction of printing.
	 */
	public enum PrintDirection {
		/**
		 * Left-to-right.
		 */
		LEFT_TO_RIGHT,

		/**
		 * Right-to-left.
		 */
		RIGHT_TO_LEFT;

		/**
		 * Returns the print direction represented by a FIGfont header value. A value of
		 * 0 means left-to-right, and 1 means right-to-left.
		 * 
		 * @param headerValue
		 *            The FIGfont print direction value. 0 means left-to-right, and 1
		 *            means right-to-left.
		 * @return The print direction represented by the value.
		 */
		public static PrintDirection ofHeaderValue(final int headerValue) throws IllegalArgumentException {
			final PrintDirection printDirection;

			switch (headerValue) {
			case 0:
				printDirection = LEFT_TO_RIGHT;
				break;
			case 1:
				printDirection = PrintDirection.RIGHT_TO_LEFT;
				break;

			default:
				throw new IllegalArgumentException("Unrecognised header value: " + headerValue);
			}

			return printDirection;
		}
	}

	/**
	 * The sub-character used to represent hardblanks in the FIGcharacter data.
	 * <p>
	 * By convention, the usual hardblank is a "$", but it can be any character
	 * except a blank (space), a carriage-return, a newline (linefeed) or a null
	 * character. If you want the entire printable ASCII set available to use, make
	 * the hardblank a "delete" character (character code 127). With the exception
	 * of delete, it is inadvisable to use non-printable characters as a hardblank.
	 * </p>
	 */
	private char hardBlankChar;

	/**
	 * The consistent height of every FIGcharacter, measured in sub-characters. Note
	 * that ALL FIGcharacters in a given FIGfont have the same height, since this
	 * includes any empty space above or below. This is a measurement from the top
	 * of the tallest FIGcharacter to the bottom of the lowest hanging FIGcharacter,
	 * such as a lowercase g.
	 */
	private int height;

	/**
	 * The number of lines of sub-characters from the baseline of a FIGcharacter to
	 * the top of the tallest FIGcharacter. The baseline of a FIGfont is an
	 * imaginary line on top of which capital letters would rest, while the tails of
	 * lowercase g, j, p, q, and y may hang below. In other words, Baseline is the
	 * height of a FIGcharacter, ignoring any descenders.
	 */
	private int baseline;

	/**
	 * The maximum length of any line describing a FIGcharacter. This is usually the
	 * width of the widest FIGcharacter, plus 2 (to accommodate end marks).
	 */
	private int maxLength;

	private int oldLayout;

	/**
	 * The number of lines there are between the first line and the actual
	 * FIGcharacters of the FIGfont. Comments are optional, but recommended to
	 * properly document the origin of a FIGfont.
	 */
	private int commentLines;

	/**
	 * The direction the font is to be printed by default.
	 */
	private PrintDirection printDirection;

	private int fullLayout;

	/**
	 * The number of code-tagged (non-required) FIGcharacters in this FIGfont. This
	 * is always equal to the total number of FIGcharacters in the font minus 102.
	 */
	private int codetagCount;

	private Map<Character, FigCharacter> figCharacters = new HashMap<>();

	/**
	 * Returns the sub-character used to represent hardblanks in the FIGcharacter
	 * data.
	 * <p>
	 * By convention, the usual hardblank is a "$", but it can be any character
	 * except a blank (space), a carriage-return, a newline (linefeed) or a null
	 * character. If you want the entire printable ASCII set available to use, make
	 * the hardblank a "delete" character (character code 127). With the exception
	 * of delete, it is inadvisable to use non-printable characters as a hardblank.
	 * </p>
	 * 
	 * @return The hardblank character in the header line defines which
	 *         sub-character will be used to represent hardblanks in the
	 *         FIGcharacter data.
	 */
	public char getHardBlankChar() {
		return hardBlankChar;
	}

	/**
	 * Returns the consistent height of every FIGcharacter, measured in
	 * sub-characters. Note that ALL FIGcharacters in a given FIGfont have the same
	 * height, since this includes any empty space above or below. This is a
	 * measurement from the top of the tallest FIGcharacter to the bottom of the
	 * lowest hanging FIGcharacter, such as a lowercase g.
	 * 
	 * @return The consistent height of every FIGcharacter, measured in
	 *         sub-characters.
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Returns the number of lines of sub-characters from the baseline of a
	 * FIGcharacter to the top of the tallest FIGcharacter. The baseline of a
	 * FIGfont is an imaginary line on top of which capital letters would rest,
	 * while the tails of lowercase g, j, p, q, and y may hang below. In other
	 * words, Baseline is the height of a FIGcharacter, ignoring any descenders.
	 * 
	 * @return The number of lines of sub-characters from the baseline of a
	 *         FIGcharacter to the top of the tallest FIGcharacter.
	 */
	public int getBaseline() {
		return baseline;
	}

	/**
	 * Returns the maximum length of any line describing a FIGcharacter. This is
	 * usually the width of the widest FIGcharacter, plus 2 (to accommodate end
	 * marks).
	 * 
	 * @return The maximum length of any line describing a FIGcharacter.
	 */
	public int getMaxLength() {
		return maxLength;
	}

	public int getOldLayout() {
		return oldLayout;
	}

	/**
	 * Returns the number of lines there are between the first line and the actual
	 * FIGcharacters of the FIGfont. Comments are optional, but recommended to
	 * properly document the origin of a FIGfont.
	 * 
	 * @return The number of lines there are between the first line and the actual
	 *         FIGcharacters of the FIGfont.
	 */
	public int getCommentLines() {
		return commentLines;
	}

	/**
	 * Returns the direction the font is to be printed by default.
	 * 
	 * @return The direction the font is to be printed by default.
	 */
	public PrintDirection getPrintDirection() {
		return printDirection;
	}

	public int getFullLayout() {
		return fullLayout;
	}

	/**
	 * Returns the number of code-tagged (non-required) FIGcharacters in this
	 * FIGfont. This is always equal to the total number of FIGcharacters in the
	 * font minus 102.
	 * 
	 * @return The number of code-tagged (non-required) FIGcharacters in this
	 *         FIGfont.
	 */
	public int getCodetagCount() {
		return codetagCount;
	}

	/**
	 * Returns the {@link FigCharacter} that represents a character. If the font
	 * does not have a {@link FigCharacter} for the requested character then
	 * <code>null</code> is returned.
	 * 
	 * @param character
	 *            The character for which to return a {@link FigCharacter}.
	 * @return The {@link FigCharacter} for the requested character or
	 *         <code>null</code> if the font does not contain a suitable
	 *         {@link FigCharacter}.
	 */
	public FigCharacter getFigCharacter(final char character) {
		return figCharacters.get(character);
	}

	/**
	 * Calculates the amount that two FigCharacters will overlap based on a smushing
	 * mode and print direction.
	 * 
	 * @param char1
	 *            The first character to use in the overlap calculation.
	 * @param char2
	 *            The second character to use in the overlap calculation.
	 * @param smushMode
	 *            The smush mode that determines the nature of the overlap. This
	 *            value is calculated by combining values specified in
	 *            {@link LayoutOptions}.
	 * @param printDirection
	 *            The print direction that determines whether the second character
	 *            will be to the right or the left of the first. Left to right will
	 *            interpret the second character as being to the right of the first,
	 *            and right-to-left will interpret the second character as being to
	 *            the left.
	 * @return The amount of overlap measured in characters.
	 * 
	 * @see LayoutOptions
	 */
	public int calculateOverlapAmount(final char char1, final char char2, final int smushMode,
			final PrintDirection printDirection) {
		if (LayoutOptions.islayoutOptionSelected(
				LayoutOptions.HORIZONTAL_SMUSHING_BY_DEFAULT | LayoutOptions.HORIZONTAL_FITTING_BY_DEFAULT,
				smushMode) == false) {
			return 0;
		}
		if (char1 == '\0' || char2 == '\0') {
			return 0;
		}

		final FigCharacter leftFigChar;
		final FigCharacter rightFigChar;
		if (printDirection == PrintDirection.LEFT_TO_RIGHT) {
			leftFigChar = getFigCharacter(char1);
			rightFigChar = getFigCharacter(char2);
		} else {
			leftFigChar = getFigCharacter(char2);
			rightFigChar = getFigCharacter(char1);
		}
		if (rightFigChar.getWidth() < 2 || leftFigChar.getWidth() < 2) {
			return 0;
		}
		int smushAmount = rightFigChar.getWidth();

		// Calculate the minimum amount that a row of rightFigChar may be smushed into
		// the corresponding row of leftFigChar
		for (int row = 0; row < getHeight(); row++) {
			int rowSmushAmount;

			int leftFigCharRightBoundary = leftFigChar.getWidth() - 1;
			while (leftFigChar.getCharacterAt(leftFigCharRightBoundary, row) == ' ' && leftFigCharRightBoundary > 0) {
				leftFigCharRightBoundary--;
			}

			int rightFigCharLeftBoundary = 0;
			while (rightFigChar.getCharacterAt(rightFigCharLeftBoundary, row) == ' '
					&& rightFigCharLeftBoundary < rightFigChar.getWidth() - 1) {
				rightFigCharLeftBoundary++;
			}

			rowSmushAmount = Math.min(rightFigChar.getWidth(),
					(leftFigChar.getWidth() - (leftFigCharRightBoundary + 1)) + rightFigCharLeftBoundary);

			if (leftFigChar.getCharacterAt(leftFigCharRightBoundary, row) == ' ') {
				rowSmushAmount++;
			} else if (smushem(leftFigChar.getCharacterAt(leftFigCharRightBoundary, row),
					rightFigChar.getCharacterAt(rightFigCharLeftBoundary, row), smushMode, printDirection) != '\0') {
				rowSmushAmount++;
			}

			smushAmount = Math.min(smushAmount, rowSmushAmount);
		}

		return smushAmount;
	}

	/**
	 * Calculates the character that is the result of merging two characters.
	 * Possible outcomes are a single character to use as the replacement for the
	 * input characters when smushing FIGcharacters, or the <code>null</code>
	 * character '/0' which represents unsmushable input characters.
	 * 
	 * @param char1
	 *            The first character to smush.
	 * @param char2
	 *            The second character to smush.
	 * @param smushmode
	 *            The smushmode that determines how smushing occurs. This value may
	 *            be generated by combining values from {@link LayoutOptions}.
	 * @param printDirection
	 *            The print direction that determines whether the second character
	 *            is considered to be to the right or the left of the first.
	 * @return The character representing the result of smushing the input
	 *         characters, or the <code>null</code> character '/0' if the input
	 *         characters cannot be smushed.
	 * @see LayoutOptions
	 */
	public char smushem(final char char1, final char char2, final int smushmode,
			final FigFont.PrintDirection printDirection) {
		if (char1 == ' ')
			return char2;
		if (char2 == ' ')
			return char1;

		if (LayoutOptions.islayoutOptionSelected(LayoutOptions.HORIZONTAL_SMUSHING_BY_DEFAULT, smushmode) == false)
			return '\0'; /* kerning */

		if ((smushmode & 63) == 0) {
			/* This is smushing by universal overlapping. */
			if (char1 == ' ')
				return char2;
			if (char2 == ' ')
				return char1;
			if (char1 == hardBlankChar)
				return char2;
			if (char2 == hardBlankChar)
				return char1;
			/* Above four lines ensure overlapping preference to */
			/* visible characters. */
			if (printDirection == PrintDirection.LEFT_TO_RIGHT)
				return char2;
			/* Above line ensures that the dominant (foreground) */
			/* fig-character for overlapping is the latter in the */
			/* user's text, not necessarily the rightmost character. */
			return char1;
			/* Occurs in the absence of above exceptions. */
		}

		if (LayoutOptions.islayoutOptionSelected(LayoutOptions.HORIZONTAL_HARDBLANK_SMUSHING, smushmode)) {
			if (char1 == hardBlankChar && char2 == hardBlankChar)
				return char1;
		}

		if (char1 == hardBlankChar || char2 == hardBlankChar)
			return '\0';

		if (LayoutOptions.islayoutOptionSelected(LayoutOptions.HORIZONTAL_EQUAL_CHARACTER_SMUSHING, smushmode)) {
			if (char1 == char2)
				return char1;
		}

		if (LayoutOptions.islayoutOptionSelected(LayoutOptions.HORIZONTAL_UNDERSCORE_SMUSHING, smushmode)) {
			if (char1 == '_' && "|/\\[]{}()<>".indexOf(char2) != -1)
				return char2;
			if (char2 == '_' && "|/\\[]{}()<>".indexOf(char1) != -1)
				return char1;
		}

		if (LayoutOptions.islayoutOptionSelected(LayoutOptions.HORIZONTAL_HIERARCHY_SMUSHING, smushmode)) {
			if (char1 == '|' && "/\\[]{}()<>".indexOf(char2) != -1)
				return char2;
			if (char2 == '|' && "/\\[]{}()<>".indexOf(char1) != -1)
				return char1;
			if ("/\\".indexOf(char1) != -1 && "[]{}()<>".indexOf(char2) != -1)
				return char2;
			if ("/\\".indexOf(char2) != -1 && "[]{}()<>".indexOf(char1) != -1)
				return char1;
			if ("[]".indexOf(char1) != -1 && "{}()<>".indexOf(char2) != -1)
				return char2;
			if ("[]".indexOf(char2) != -1 && "{}()<>".indexOf(char1) != -1)
				return char1;
			if ("{}".indexOf(char1) != -1 && "()<>".indexOf(char2) != -1)
				return char2;
			if ("{}".indexOf(char2) != -1 && "()<>".indexOf(char1) != -1)
				return char1;
			if ("()".indexOf(char1) != -1 && "<>".indexOf(char2) != -1)
				return char2;
			if ("()".indexOf(char2) != -1 && "<>".indexOf(char1) != -1)
				return char1;
		}

		if (LayoutOptions.islayoutOptionSelected(LayoutOptions.HORIZONTAL_OPPOSITE_PAIR_SMUSHING, smushmode)) {
			if (char1 == '[' && char2 == ']')
				return '|';
			if (char2 == '[' && char1 == ']')
				return '|';
			if (char1 == '{' && char2 == '}')
				return '|';
			if (char2 == '{' && char1 == '}')
				return '|';
			if (char1 == '(' && char2 == ')')
				return '|';
			if (char2 == '(' && char1 == ')')
				return '|';
		}

		if (LayoutOptions.islayoutOptionSelected(LayoutOptions.HORIZONTAL_BIG_X_SMUSHING, smushmode)) {
			if (char1 == '/' && char2 == '\\')
				return '|';
			if (char2 == '/' && char1 == '\\')
				return 'Y';
			if (char1 == '>' && char2 == '<')
				return 'X';
			/* Don't want the reverse of above to give 'X'. */
		}

		return '\0';
	}

	@Override
	public String toString() {
		final StringBuilder stringBuilder = new StringBuilder();

		for (final Map.Entry<Character, FigCharacter> figCharactersEntry : figCharacters.entrySet()) {
			stringBuilder.append(figCharactersEntry.getKey());
			stringBuilder.append(":\n");
			stringBuilder.append(figCharactersEntry.getValue());
			stringBuilder.append("\n");
		}

		return stringBuilder.toString();
	}

	/**
	 * Loads a FigFont from an {@link InputStream}.
	 * 
	 * @param inputStream
	 *            The input stream containing the FIGfont data to load.
	 * @return The loaded FigFont instance.
	 * @throws IOException
	 *             if there is a problem loading the stream data.
	 */
	public static FigFont loadFigFont(final InputStream inputStream) throws IOException {
		try (final InputStreamReader inputStreamReader = new InputStreamReader(inputStream)) {
			final FigFontReader fontReader = new FigFontReader(inputStreamReader);
			return fontReader.readFont();
		}
	}

	public static class Builder extends FigFont {

		/**
		 * The sub-character used to represent hardblanks in the FIGcharacter data.
		 * <p>
		 * By convention, the usual hardblank is a "$", but it can be any character
		 * except a blank (space), a carriage-return, a newline (linefeed) or a null
		 * character. If you want the entire printable ASCII set available to use, make
		 * the hardblank a "delete" character (character code 127). With the exception
		 * of delete, it is inadvisable to use non-printable characters as a hardblank.
		 * </p>
		 */
		private char hardBlankChar;

		/**
		 * The consistent height of every FIGcharacter, measured in sub-characters. Note
		 * that ALL FIGcharacters in a given FIGfont have the same height, since this
		 * includes any empty space above or below. This is a measurement from the top
		 * of the tallest FIGcharacter to the bottom of the lowest hanging FIGcharacter,
		 * such as a lowercase g.
		 */
		private int height;

		/**
		 * The number of lines of sub-characters from the baseline of a FIGcharacter to
		 * the top of the tallest FIGcharacter. The baseline of a FIGfont is an
		 * imaginary line on top of which capital letters would rest, while the tails of
		 * lowercase g, j, p, q, and y may hang below. In other words, Baseline is the
		 * height of a FIGcharacter, ignoring any descenders.
		 */
		private int baseline;

		/**
		 * The maximum length of any line describing a FIGcharacter. This is usually the
		 * width of the widest FIGcharacter, plus 2 (to accommodate end marks).
		 */
		private int maxLength;

		private int oldLayout;

		/**
		 * The number of lines there are between the first line and the actual
		 * FIGcharacters of the FIGfont. Comments are optional, but recommended to
		 * properly document the origin of a FIGfont.
		 */
		private int commentLines;

		/**
		 * The direction the font is to be printed by default.
		 */
		private PrintDirection printDirection;

		private int fullLayout;

		/**
		 * The number of code-tagged (non-required) FIGcharacters in this FIGfont. This
		 * is always equal to the total number of FIGcharacters in the font minus 102.
		 */
		private int codetagCount;

		private Map<Character, String> characterDataMap = new HashMap<>();

		public char getHardBlankChar() {
			return hardBlankChar;
		}

		public Builder setHardBlankChar(char hardBlankChar) {
			this.hardBlankChar = hardBlankChar;
			return this;
		}

		public int getHeight() {
			return height;
		}

		public Builder setHeight(int height) {
			this.height = height;
			return this;
		}

		public int getBaseline() {
			return baseline;
		}

		public Builder setBaseline(int baseline) {
			this.baseline = baseline;
			return this;
		}

		public int getMaxLength() {
			return maxLength;
		}

		public Builder setMaxLength(int maxLength) {
			this.maxLength = maxLength;
			return this;
		}

		public int getOldLayout() {
			return oldLayout;
		}

		public Builder setOldLayout(int oldLayout) {
			this.oldLayout = oldLayout;
			return this;
		}

		public int getCommentLines() {
			return commentLines;
		}

		public Builder setCommentLines(int commentLines) {
			this.commentLines = commentLines;
			return this;
		}

		public PrintDirection getPrintDirection() {
			return printDirection;
		}

		public Builder setPrintDirection(PrintDirection printDirection) {
			this.printDirection = printDirection;
			return this;
		}

		public int getFullLayout() {
			return fullLayout;
		}

		public Builder setFullLayout(int fullLayout) {
			this.fullLayout = fullLayout;
			return this;
		}

		public int getCodetagCount() {
			return codetagCount;
		}

		public Builder setCodetagCount(int codetagCount) {
			this.codetagCount = codetagCount;
			return this;
		}

		public Builder setFigCharacter(final char character, final String characterData) {
			characterDataMap.put(character, characterData);

			return this;
		}

		public FigFont build() {
			final FigFont font = new FigFont();

			font.hardBlankChar = hardBlankChar;
			font.height = height;
			font.baseline = baseline;
			font.maxLength = maxLength;
			font.oldLayout = oldLayout;
			font.commentLines = commentLines;
			font.printDirection = printDirection;
			font.fullLayout = fullLayout;
			font.codetagCount = codetagCount;

			font.figCharacters = new HashMap<>(characterDataMap.size());
			for (Map.Entry<Character, String> entry : characterDataMap.entrySet()) {
				font.figCharacters.put(entry.getKey(), new FigCharacter(font, entry.getValue()));
			}

			return font;
		}
	}

	/**
	 * FigCharacter represents a single FIGlet character from a FIGfont.
	 */
	public static class FigCharacter {
		private final FigFont font;
		private final String characterData;

		/**
		 * Constructs a new instance of {@link FigCharacter}.
		 * 
		 * @param font
		 *            The font of which this FigCharacter is a part.
		 * @param characterData
		 *            The character data that defines this characters appearance.
		 */
		private FigCharacter(final FigFont font, final String characterData) {
			this.font = font;
			this.characterData = characterData;
		}

		/**
		 * Returns the FIGcharacter sub-character at the requested column and row.
		 * 
		 * @param column
		 *            The column for which to return a sub-character.
		 * @param row
		 *            The row for which to return a sub-character.
		 * @return The sub-character at the requested column and row.
		 * @throws IndexOutOfBoundsException
		 *             if the requested column and row does not exist within the
		 *             FIGcharacter data.
		 */
		public char getCharacterAt(final int column, final int row) throws IndexOutOfBoundsException {
			if (column >= 0 && column < getWidth() && row >= 0 && row < getHeight()) {
				return characterData.charAt((row * getWidth()) + column);
			} else {
				throw new IndexOutOfBoundsException("Character index out of bounds: " + column + ", " + row);
			}
		}

		/**
		 * Returns string of sub-characters that define a row of the FIGcharacter.
		 * 
		 * @param row
		 *            The row for which to return the sub-character string.
		 * @return The sub-characters that define the requested FIGcharacter row.
		 * @throws IndexOutOfBoundsException
		 *             if the requested row does not exist within the FIGcharacter data.
		 */
		public String getRow(final int row) throws IndexOutOfBoundsException {
			if (row >= 0 && row < getHeight()) {
				int rowStart = row * getWidth();
				return characterData.substring(rowStart, rowStart + getWidth());
			} else {
				throw new IndexOutOfBoundsException(
						"Character row must be between 0 and " + (getHeight() - 1) + ": " + row);
			}
		}

		/**
		 * Returns the width of the FIGcharacter.
		 * 
		 * @return The width of the FIGcharacter.
		 */
		public int getWidth() {
			return characterData.length() / font.getHeight();
		}

		/**
		 * Returns the height of the FIGcharacter.
		 * 
		 * @return The height of the FIGcharacter.
		 */
		public int getHeight() {
			return font.getHeight();
		}

		@Override
		public String toString() {
			final StringBuilder stringBuilder = new StringBuilder();

			int width = getWidth();
			for (int y = 0; y < font.getHeight(); y++) {
				stringBuilder.append(characterData.substring(width * y, (width * y) + width));
				stringBuilder.append("\n");
			}

			return stringBuilder.toString();
		}
	}
}
