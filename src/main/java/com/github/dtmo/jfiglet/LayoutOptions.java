package com.github.dtmo.jfiglet;

public class LayoutOptions {
	/**
	 * Rule 1: EQUAL CHARACTER SMUSHING (code value 1)
	 * <p>
	 * Two sub-characters are smushed into a single sub-character if they are the
	 * same. This rule does not smush hardblanks.
	 * </p>
	 */
	public static final int HORIZONTAL_EQUAL_CHARACTER_SMUSHING = 1 << 0;

	/**
	 * Rule 2: UNDERSCORE SMUSHING (code value 2)
	 * <p>
	 * An underscore ("_") will be replaced by any of: "|", "/", "\", "[", "]", "{",
	 * "}", "(", ")", "&lt;" or "&gt;".
	 * </p>
	 */
	public static final int HORIZONTAL_UNDERSCORE_SMUSHING = 1 << 1;

	/**
	 * Rule 3: HIERARCHY SMUSHING (code value 4)
	 * <p>
	 * A hierarchy of six classes is used: "|", "/\", "[]", "{}", "()", and
	 * "&lt;&gt;". When two smushing sub-characters are from different classes, the
	 * one from the latter class will be used.
	 * </p>
	 */
	public static final int HORIZONTAL_HIERARCHY_SMUSHING = 1 << 2;

	/**
	 * Rule 4: OPPOSITE PAIR SMUSHING (code value 8)
	 * <p>
	 * Smushes opposing brackets ("[]" or "]["), braces ("{}" or "}{") and
	 * parentheses ("()" or ")(") together, replacing any such pair with a vertical
	 * bar ("|").
	 * </p>
	 */
	public static final int HORIZONTAL_OPPOSITE_PAIR_SMUSHING = 1 << 3;

	/**
	 * Rule 5: BIG X SMUSHING (code value 16)
	 * <p>
	 * Smushes "/\" into "|", "\/" into "Y", and "&gt;&lt;" into "X". Note that
	 * "&lt;&gt;" is not smushed in any way by this rule. The name "BIG X" is
	 * historical; originally all three pairs were smushed into "X".
	 * </p>
	 */
	public static final int HORIZONTAL_BIG_X_SMUSHING = 1 << 4;

	/**
	 * Rule 6: HARDBLANK SMUSHING (code value 32)
	 * <p>
	 * Smushes two hardblanks together, replacing them with a single hardblank.
	 * </p>
	 */
	public static final int HORIZONTAL_HARDBLANK_SMUSHING = 1 << 5;

	/**
	 * Moves FIGcharacters closer together until they touch. Typographers use the
	 * term "kerning" for this phenomenon when applied to the horizontal axis, but
	 * fitting also includes this as a vertical behavior, for which there is
	 * apparently no established typographical term.
	 */
	public static final int HORIZONTAL_FITTING_BY_DEFAULT = 1 << 6;

	/**
	 * Moves FIGcharacters one step closer after they touch, so that they partially
	 * occupy the same space. A FIGdriver must decide what sub-character to display
	 * at each junction. There are two ways of making these decisions: by controlled
	 * smushing or by universal smushing.
	 */
	public static final int HORIZONTAL_SMUSHING_BY_DEFAULT = 1 << 7;

	/**
	 * Rule 1: EQUAL CHARACTER SMUSHING (code value 256)
	 * <p>
	 * Same as horizontal smushing rule 1.
	 * </p>
	 */
	public static final int VERTICAL_EQUAL_CHARACTER_SMUSHING = 1 << 8;

	/**
	 * Rule 2: UNDERSCORE SMUSHING (code value 512)
	 * <p>
	 * Same as horizontal smushing rule 2.
	 * </p>
	 */
	public static final int VERTICAL_UNDERSCORE_SMUSHING = 1 << 9;

	/**
	 * Rule 3: HIERARCHY SMUSHING (code value 1024)
	 * <p>
	 * Same as horizontal smushing rule 3.
	 * </p>
	 */
	public static final int VERTICAL_HIERARCHY_SMUSHING = 1 << 10;

	/**
	 * Rule 4: HORIZONTAL LINE SMUSHING (code value 2048)
	 * <p>
	 * Smushes stacked pairs of "-" and "_", replacing them with a single "="
	 * sub-character. It does not matter which is found above the other. Note that
	 * vertical smushing rule 1 will smush IDENTICAL pairs of horizontal lines,
	 * while this rule smushes horizontal lines consisting of DIFFERENT
	 * sub-characters.
	 * </p>
	 */
	public static final int VERTICAL_HORIZONTAL_LINE_SMUSHING = 1 << 11;

	/**
	 * Rule 5: VERTICAL LINE SUPERSMUSHING (code value 4096)
	 * <p>
	 * This one rule is different from all others, in that it "supersmushes"
	 * vertical lines consisting of several vertical bars ("|"). This creates the
	 * illusion that FIGcharacters have slid vertically against each other.
	 * Supersmushing continues until any sub-characters other than "|" would have to
	 * be smushed. Supersmushing can produce impressive results, but it is seldom
	 * possible, since other sub-characters would usually have to be considered for
	 * smushing as soon as any such stacked vertical lines are encountered.
	 * </p>
	 */
	public static final int VERTICAL_VERTICAL_LINE_SMUSHING = 1 << 12;

	/**
	 * Moves FIGcharacters closer together until they touch. Typographers use the
	 * term "kerning" for this phenomenon when applied to the horizontal axis, but
	 * fitting also includes this as a vertical behavior, for which there is
	 * apparently no established typographical term.
	 */
	public static final int VERTICAL_FITTING_BY_DEFAULT = 1 << 13;

	/**
	 * Moves FIGcharacters one step closer after they touch, so that they partially
	 * occupy the same space. A FIGdriver must decide what sub-character to display
	 * at each junction. There are two ways of making these decisions: by controlled
	 * smushing or by universal smushing.
	 */
	public static final int VERTICAL_SMUSHING_BY_DEFAULT = 1 << 14;

	private LayoutOptions() {
	}

	public static boolean islayoutOptionSelected(final int layoutOption, final int layoutValue) {
		return (layoutValue & layoutOption) != 0;
	}

	/**
	 * Converts an old layout value (Legal values -1 to 63) into the equivalent full
	 * layout value.
	 * 
	 * <dl>
	 * <dt>-1</dt>
	 * <dd>Full-width layout by default</dd>
	 * <dt>0</dt>
	 * <dd>Horizontal fitting (kerning) layout by default</dd>
	 * <dt>1</dt>
	 * <dd>Apply horizontal smushing rule 1 by default</dd>
	 * <dt>2</dt>
	 * <dd>Apply horizontal smushing rule 2 by default</dd>
	 * <dt>4</dt>
	 * <dd>Apply horizontal smushing rule 3 by default</dd>
	 * <dt>8</dt>
	 * <dd>Apply horizontal smushing rule 4 by default</dd>
	 * <dt>16</dt>
	 * <dd>Apply horizontal smushing rule 5 by default</dd>
	 * <dt>32</dt>
	 * <dd>Apply horizontal smushing rule 6 by default</dd>
	 * </dl>
	 * 
	 * @param oldLayout
	 *            The old layout value to convert into a full layout value.
	 * @return The full layout value.
	 */
	public static int fullLayoutFromOldLayout(final int oldLayout) {
		final int layout;

		if (oldLayout == -1) {
			layout = 0;
		} else if (oldLayout == 0) {
			layout = LayoutOptions.HORIZONTAL_FITTING_BY_DEFAULT;
		} else {
			layout = oldLayout;
		}

		return layout;
	}
}
