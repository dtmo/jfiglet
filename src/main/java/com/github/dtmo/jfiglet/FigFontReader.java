package com.github.dtmo.jfiglet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * FigFontReader reads {@link FigFont} instances from a {@link Reader}.
 */
public class FigFontReader {
	/**
	 * The magic number used to determine if a stream of data contains a FIGfont
	 * definition.
	 */
	public static final String FONT_MAGIC_NUMBER = "flf2";

	// Based on http://www.jave.de/docs/figfont.txt

	private static final Pattern CODE_TAG_PATTERN = Pattern.compile("([^\\s]+)\\s*.*");

	private static final int[] deutschCodePoints = new int[] { 196, 214, 220, 228, 246, 252, 223 };

	private final Reader reader;

	/**
	 * Constructs a new instance of FontReader.
	 * 
	 * @param reader
	 *            The {@link Reader} from which to read font data.
	 */
	public FigFontReader(final Reader reader) {
		this.reader = reader;
	}

	/**
	 * Reads a {@link FigFont}.
	 * 
	 * @return The {@link FigFont} read from the {@link Reader} data.
	 * @throws IOException
	 *             if there is a problem reading the font data.
	 */
	public FigFont readFont() throws IOException {
		final FigFont.Builder fontBuilder = new FigFont.Builder();

		try (final BufferedReader bufferedReader = new BufferedReader(reader)) {
			String header = bufferedReader.readLine();

			try {
				parseHeader(header, fontBuilder);
			} catch (final IllegalArgumentException e) {
				throw new IOException("Could not read font header", e);
			}

			// Skip over the comment lines
			for (int commentCounter = 0; commentCounter < fontBuilder.getCommentLines(); commentCounter++) {
				bufferedReader.readLine();
			}

			// A FIGfont is required to have characters for ASCII 32 to 126 inclusive
			for (int codePoint = 32; codePoint < 127; codePoint++) {
				final String characterData = readCharacterData(fontBuilder.getHeight(), bufferedReader);
				fontBuilder.setFigCharacter((char) codePoint, characterData);
			}

			// Additional required Deutsch FIGcharacters, in order:
			//
			// 196 (umlauted "A" -- two dots over letter "A")
			// 214 (umlauted "O" -- two dots over letter "O")
			// 220 (umlauted "U" -- two dots over letter "U")
			// 228 (umlauted "a" -- two dots over letter "a")
			// 246 (umlauted "o" -- two dots over letter "o")
			// 252 (umlauted "u" -- two dots over letter "u")
			// 223 ("ess-zed" -- see FIGcharacter illustration below)

			for (int codePoint : deutschCodePoints) {
				final String characterData = readCharacterData(fontBuilder.getHeight(), bufferedReader);
				fontBuilder.setFigCharacter((char) codePoint, characterData);
			}

			// Now there just remains to parse any code tags that have been defined.
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				final String characterData = readCharacterData(fontBuilder.getHeight(), bufferedReader);
				try {
					fontBuilder.setFigCharacter((char) parseCodeTag(line), characterData);
				} catch (final IllegalArgumentException e) {
					throw new IOException("Could not parse code tag", e);
				}
			}
		}

		return fontBuilder.build();
	}

	/**
	 * Returns the unicode character represented by a code tag.
	 * 
	 * @param codeTagText
	 *            The code tag text to parse.
	 * @return The character represented.
	 * @throws IllegalArgumentException
	 *             if the text cannot be parsed as a code tag.
	 */
	public static char parseCodeTag(final String codeTagText) throws IllegalArgumentException {
		final Matcher codeTagMatcher = CODE_TAG_PATTERN.matcher(codeTagText);
		if (codeTagMatcher.matches()) {
			final String codePointText = codeTagMatcher.group(1);
			final int codePoint = Integer.decode(codePointText);
			return (char) codePoint;
		} else {
			throw new IllegalArgumentException("Could not parse text as a code tag: " + codeTagText);
		}
	}

	/**
	 * Reads the data that defines a single character from a {@link BufferedReader}.
	 * 
	 * @param height
	 *            The height of the character in lines of data.
	 * @param bufferedReader
	 *            The buffered reader from which to read the character data.
	 * @return The string that represents the character data.
	 * @throws IOException
	 *             if there is a problem reading the data.
	 */
	public static String readCharacterData(final int height, final BufferedReader bufferedReader) throws IOException {
		final StringBuilder stringBuilder = new StringBuilder();

		for (int charLine = 0; charLine < height; charLine++) {
			final String line = bufferedReader.readLine();
			int charIndex = line.length() - 1;

			// Skip over any whitespace characters at the end of the line
			while (charIndex >= 0 && Character.isWhitespace(line.charAt(charIndex))) {
				charIndex--;
			}

			// We've found a non-whitespace character that we will interpret as an
			// end-character.
			char endChar = line.charAt(charIndex);

			// Skip over any end-characters.
			while (charIndex >= 0 && line.charAt(charIndex) == endChar) {
				charIndex--;
			}

			// We've found the right-hand edge of the actual character data for this line.
			stringBuilder.append(line.substring(0, charIndex + 1));
		}

		return stringBuilder.toString();
	}

	/**
	 * Parses a FIGfont header into a {@link FigFont.Builder} instance.
	 * 
	 * @param header
	 *            The header to parse.
	 * @param fontBuilder
	 *            The font builder to set with values read from the header.
	 * @throws IllegalArgumentException
	 *             if the header text cannot be parsed as a FIGfont header.
	 */
	public static void parseHeader(final String header, final FigFont.Builder fontBuilder)
			throws IllegalArgumentException {
		String[] arguments = header.split("\\s+");
		if (arguments[0].startsWith(FONT_MAGIC_NUMBER)) {
			fontBuilder.setHardBlankChar(arguments[0].charAt(arguments[0].length() - 1));

			if (arguments.length > 1) {
				fontBuilder.setHeight(Integer.decode(arguments[1]).intValue());
			}

			if (arguments.length > 2) {
				fontBuilder.setBaseline(Integer.decode(arguments[2]).intValue());
			}

			if (arguments.length > 3) {
				fontBuilder.setMaxLength(Integer.decode(arguments[3]).intValue());
			}

			if (arguments.length > 4) {
				final int oldLayout = Integer.decode(arguments[4]).intValue();
				fontBuilder.setOldLayout(oldLayout);
				fontBuilder.setFullLayout(LayoutOptions.fullLayoutFromOldLayout(oldLayout));
			}

			if (arguments.length > 5) {
				fontBuilder.setCommentLines(Integer.decode(arguments[5]).intValue());
			}

			if (arguments.length > 6) {
				fontBuilder.setPrintDirection(
						FigFont.PrintDirection.ofHeaderValue(Integer.decode(arguments[6]).intValue()));
			}

			if (arguments.length > 7) {
				fontBuilder.setFullLayout(Integer.decode(arguments[7]).intValue());
			}

			if (arguments.length > 8) {
				fontBuilder.setCodetagCount(Integer.decode(arguments[8]).intValue());
			}
		} else {
			throw new IllegalArgumentException(
					"Header does not start with FIGfont magic number " + FONT_MAGIC_NUMBER + ": " + header);
		}
	}
}
