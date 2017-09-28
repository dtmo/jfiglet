package com.github.dtmo.jfiglet;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.github.dtmo.jfiglet.FigFont;
import com.github.dtmo.jfiglet.FigFontResources;
import com.github.dtmo.jfiglet.FigFont.PrintDirection;

public class FigFontTest {

	private FigFont standardFont;
	private FigFont slantFont;

	@Before
	public void before() throws Exception {
		standardFont = FigFontResources.loadFigFontResource(FigFontResources.STANDARD_FLF);
		slantFont = FigFontResources.loadFigFontResource(FigFontResources.SLANT_FLF);
	}
	
//	H:      e:
//	  _   _        
//	 | | | |   ___ 
//	 | |_| |  / _ \
//	 |  _  | |  __/
//	 |_| |_|  \___|
//	               
	@Test
	public void testCalculateSmushAmountHeLeftToRight() {
		assertEquals(2, standardFont.calculateOverlapAmount('H', 'e', standardFont.getFullLayout(), PrintDirection.LEFT_TO_RIGHT));
	}
	
//	H:      e:
//	  _   _        
//	 | | | |   ___ 
//	 | |_| |  / _ \
//	 |  _  | |  __/
//	 |_| |_|  \___|
//	               
	@Test
	public void testCalculateSmushAmountHeRightToLeft() {
		assertEquals(2, standardFont.calculateOverlapAmount('H', 'e', standardFont.getFullLayout(), PrintDirection.RIGHT_TO_LEFT));
	}
	
//	 :e:
//	 $       
//	 $   ___ 
//	 $  / _ \
//	 $ |  __/
//	 $  \___|
//	 $       

	@Test
	public void testCalculateSmushAmountSpaceELeftToRight() {
		assertEquals(1, standardFont.calculateOverlapAmount(' ', 'e', standardFont.getFullLayout(), PrintDirection.LEFT_TO_RIGHT));
	}

/*
o:      :
            $$
  ____     $$ 
 / __ \   $$  
/ /_/ /  $$   
\____/  $$    
       $$     
 */
	
	@Test
	public void testCalculateSmushAmountHollywoodOSpaceLeftToRght() {
		assertEquals(2, slantFont.calculateOverlapAmount('o', ' ', slantFont.getFullLayout(), PrintDirection.LEFT_TO_RIGHT));
	}
}
