package com.github.dtmo.figlet;

import static org.junit.Assert.*;

import org.junit.Test;

import com.github.dtmo.figlet.LayoutOptions;

public class LayoutOptionsTest {

	@Test
	public void testIsIncludedIn() {
		// 24463 = binary (msb) 101111110001111 (lsb)
		
		assertTrue(LayoutOptions.islayoutOptionSelected(LayoutOptions.HORIZONTAL_EQUAL_CHARACTER_SMUSHING, 24463)); // (1),
		assertTrue(LayoutOptions.islayoutOptionSelected(LayoutOptions.HORIZONTAL_UNDERSCORE_SMUSHING, 24463)); // (2),
		assertTrue(LayoutOptions.islayoutOptionSelected(LayoutOptions.HORIZONTAL_HIERARCHY_SMUSHING, 24463)); // (4),
		assertTrue(LayoutOptions.islayoutOptionSelected(LayoutOptions.HORIZONTAL_OPPOSITE_PAIR_SMUSHING, 24463)); // (8),
		assertFalse(LayoutOptions.islayoutOptionSelected(LayoutOptions.HORIZONTAL_BIG_X_SMUSHING, 24463)); // (16),
		assertFalse(LayoutOptions.islayoutOptionSelected(LayoutOptions.HORIZONTAL_HARDBLANK_SMUSHING, 24463)); // (32),
		assertFalse(LayoutOptions.islayoutOptionSelected(LayoutOptions.HORIZONTAL_FITTING_BY_DEFAULT, 24463)); // (64),
		assertTrue(LayoutOptions.islayoutOptionSelected(LayoutOptions.HORIZONTAL_SMUSHING_BY_DEFAULT, 24463)); // (128),
		assertTrue(LayoutOptions.islayoutOptionSelected(LayoutOptions.VERTICAL_EQUAL_CHARACTER_SMUSHING, 24463)); // (256),
		assertTrue(LayoutOptions.islayoutOptionSelected(LayoutOptions.VERTICAL_UNDERSCORE_SMUSHING, 24463)); // (512),
		assertTrue(LayoutOptions.islayoutOptionSelected(LayoutOptions.VERTICAL_HIERARCHY_SMUSHING, 24463)); // (1024),
		assertTrue(LayoutOptions.islayoutOptionSelected(LayoutOptions.VERTICAL_HORIZONTAL_LINE_SMUSHING, 24463)); // (2048),
		assertTrue(LayoutOptions.islayoutOptionSelected(LayoutOptions.VERTICAL_VERTICAL_LINE_SMUSHING, 24463)); // (4096),
		assertFalse(LayoutOptions.islayoutOptionSelected(LayoutOptions.VERTICAL_FITTING_BY_DEFAULT, 24463)); // (8192),
		assertTrue(LayoutOptions.islayoutOptionSelected(LayoutOptions.VERTICAL_SMUSHING_BY_DEFAULT, 24463)); // (16384);
	}

}
