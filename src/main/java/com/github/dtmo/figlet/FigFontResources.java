package com.github.dtmo.figlet;

import java.io.IOException;
import java.io.InputStream;

/**
 * FigFontResources contains constants used to identify bundles FIGfont
 * resources.
 */
public class FigFontResources {
	/**
	 * The {@value #BANNER_FLF} FIGfont.
	 * <p>
	 * Example output:
	 * </p>
	 * 
	 * <pre>
######                                     
#     #   ##   #    # #    # ###### #####  
#     #  #  #  ##   # ##   # #      #    # 
######  #    # # #  # # #  # #####  #    # 
#     # ###### #  # # #  # # #      #####  
#     # #    # #   ## #   ## #      #   #  
######  #    # #    # #    # ###### #    # 
                                           
	 * </pre>
	 */
	public static final String BANNER_FLF = "banner.flf";

	/**
	 * The {@value #BIG_FLF} FIGfont.
	 * <p>
	 * Example output:
	 * </p>
	 * 
	 * <pre>
  ____  _       
 |  _ \(_)      
 | |_) |_  __ _ 
 |  _ &lt;| |/ _` |
 | |_) | | (_| |
 |____/|_|\__, |
           __/ |
          |___/ 
	 * </pre>
	 */
	public static final String BIG_FLF = "big.flf";

	/**
	 * The {@value #BLOCK_FLF} FIGfont.
	 * <p>
	 * Example output:
	 * </p>
	 * 
	 * <pre>
                                             
 _|_|_|    _|                      _|        
 _|    _|  _|    _|_|      _|_|_|  _|  _|    
 _|_|_|    _|  _|    _|  _|        _|_|      
 _|    _|  _|  _|    _|  _|        _|  _|    
 _|_|_|    _|    _|_|      _|_|_|  _|    _|  
                                             
	 * </pre>
	 */
	public static final String BLOCK_FLF = "block.flf";

	/**
	 * The {@value #BUBBLE_FLF} FIGfont.
	 * <p>
	 * Example output:
	 * </p>
	 * 
	 * <pre>
   _   _   _   _   _   _  
  / \ / \ / \ / \ / \ / \ 
 ( B | u | b | b | l | e )
  \_/ \_/ \_/ \_/ \_/ \_/ 
	 * </pre>
	 */
	public static final String BUBBLE_FLF = "bubble.flf";

	/**
	 * The {@value #DIGITAL_FLF} FIGfont.
	 * <p>
	 * Example output:
	 * </p>
	 * 
	 * <pre>
 +-+-+-+-+-+-+-+
 |D|i|g|i|t|a|l|
 +-+-+-+-+-+-+-+
	 * </pre>
	 */
	public static final String DIGITAL_FLF = "digital.flf";

	/**
	 * The {@value #IVRIT_FLF} FIGfont.
	 * <p>
	 * Example output:
	 * </p>
	 * 
	 * <pre>
  _   _            ___ 
 | |_(_)_ ____   _|_ _|
 | __| | '__\ \ / /| | 
 | |_| | |   \ V / | | 
  \__|_|_|    \_/ |___|
                       
	 * </pre>
	 */
	public static final String IVRIT_FLF = "ivrit.flf";

	/**
	 * The {@value #LEAN_FLF} FIGfont.
	 * <p>
	 * Example output:
	 * </p>
	 * 
	 * <pre>
                                           
    _/                                     
   _/          _/_/      _/_/_/  _/_/_/    
  _/        _/_/_/_/  _/    _/  _/    _/   
 _/        _/        _/    _/  _/    _/    
_/_/_/_/    _/_/_/    _/_/_/  _/    _/     
                                           
	 * </pre>
	 */
	public static final String LEAN_FLF = "lean.flf";

	/**
	 * The {@value #MINI_FLF} FIGfont.
	 * <p>
	 * Example output:
	 * </p>
	 * 
	 * <pre>
           
 |\/|o._ o 
 |  ||| || 
           
	 * </pre>
	 */
	public static final String MINI_FLF = "mini.flf";

	/**
	 * The {@value #MNEMONIC_FLF} FIGfont.
	 * <p>
	 * Example output:
	 * </p>
	 * 
	 * <pre>
mnemonic
	 * </pre>
	 */
	public static final String MNEMONIC_FLF = "mnemonic.flf";

	/**
	 * The {@value #SCRIPT_FLF} FIGfont.
	 * <p>
	 * Example output:
	 * </p>
	 * 
	 * <pre>
                            
   ()            o          
   /\  __   ,_        _ _|_ 
  /  \/    /  |  |  |/ \_|  
 /(__/\___/   |_/|_/|__/ |_/
                   /|       
                   \|       
	 * </pre>
	 */
	public static final String SCRIPT_FLF = "script.flf";

	/**
	 * The {@value #SHADOW_FLF} FIGfont.
	 * <p>
	 * Example output:
	 * </p>
	 * 
	 * <pre>

   ___|  |               |                
 \___ \  __ \   _` |  _` |  _ \\ \  \   / 
       | | | | (   | (   | (   |\ \  \ /  
 _____/ _| |_|\__,_|\__,_|\___/  \_/\_/   
                                          
	 * </pre>
	 */
	public static final String SHADOW_FLF = "shadow.flf";

	/**
	 * The {@value #SLANT_FLF} FIGfont.
	 * <p>
	 * Example output:
	 * </p>
	 * 
	 * <pre>
   _____ __            __ 
  / ___// /___ _____  / /_
  \__ \/ / __ `/ __ \/ __/
 ___/ / / /_/ / / / / /_  
/____/_/\__,_/_/ /_/\__/  
                          
	 * </pre>
	 */
	public static final String SLANT_FLF = "slant.flf";

	/**
	 * The {@value #SMALL_FLF} FIGfont.
	 * <p>
	 * Example output:
	 * </p>
	 * 
	 * <pre>
  ___            _ _ 
 / __|_ __  __ _| | |
 \__ \ '  \/ _` | | |
 |___/_|_|_\__,_|_|_|
                     
	 * </pre>
	 */
	public static final String SMALL_FLF = "small.flf";

	/**
	 * The {@value #SMSCRIPT_FLF} FIGfont.
	 * <p>
	 * Example output:
	 * </p>
	 * 
	 * <pre>
                                   
  ()          ()  _   ,_  o    _|_ 
  /\ /|/|/|   /\ /   /  | | |/\_|  
 /(_) | | |_//(_)\__/   |/|/|_/ |_/
                           (|      
	 * </pre>
	 */
	public static final String SMSCRIPT_FLF = "smscript.flf";

	/**
	 * The {@value #SMSHADOW_FLF} FIGfont.
	 * <p>
	 * Example output:
	 * </p>
	 * 
	 * <pre>
   __|        __| |              |              
 \__ \  ` \ \__ \   \   _` |  _` |  _ \\ \  \ / 
 ____/_|_|_|____/_| _|\__,_|\__,_|\___/ \_/\_/  
                                                
	 * </pre>
	 */
	public static final String SMSHADOW_FLF = "smshadow.flf";

	/**
	 * The {@value #SMSLANT_FLF} FIGfont.
	 * <p>
	 * Example output:
	 * </p>
	 * 
	 * <pre>
   ____      ______          __ 
  / __/_ _  / __/ /__ ____  / /_
 _\ \/  ' \_\ \/ / _ `/ _ \/ __/
/___/_/_/_/___/_/\_,_/_//_/\__/ 
                                
	 * </pre>
	 */
	public static final String SMSLANT_FLF = "smslant.flf";

	/**
	 * The {@value #STANDARD_FLF} FIGfont.
	 * <p>
	 * Example output:
	 * </p>
	 * 
	 * <pre>
  ____  _                  _               _ 
 / ___|| |_ __ _ _ __   __| | __ _ _ __ __| |
 \___ \| __/ _` | '_ \ / _` |/ _` | '__/ _` |
  ___) | || (_| | | | | (_| | (_| | | | (_| |
 |____/ \__\__,_|_| |_|\__,_|\__,_|_|  \__,_|
                                             
	 * </pre>
	 */
	public static final String STANDARD_FLF = "standard.flf";

	/**
	 * The {@value #TERM_FLF} FIGfont.
	 * <p>
	 * Example output:
	 * </p>
	 * 
	 * <pre>
Terminal
	 * </pre>
	 */
	public static final String TERM_FLF = "term.flf";

	private FigFontResources() {
		// Do nothing.
	}

	/**
	 * Loads a {@link FigFont} from a resource name.
	 * 
	 * @param resourceName
	 *            The name of the resource from which to load a {@link FigFont}.
	 * @return The {@link FigFont} loaded from the requested resource.
	 * @throws IOException
	 *             if there is problem loading a {@link FigFont} from the specified
	 *             resource.
	 */
	public static FigFont loadFigFontResource(final String resourceName) throws IOException {
		try (final InputStream inputStream = FigFontResources.class.getClassLoader()
				.getResourceAsStream(resourceName)) {
			return FigFont.loadFigFont(inputStream);
		}
	}
}
