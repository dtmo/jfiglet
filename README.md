# JFiglet

## Java implementation of FIGlet
Inspired by https://github.com/lalyos/jfiglet

### Features

 * Implementation based on FIGlet 2.2.5 C code: http://www.figlet.org
 * Includes font smushing
 * Includes left-to-right and right-to-left text rendering
 
### Example usage

Rendering FIGlet text with a build-in FIGfont resource

```Java
final FigletRenderer figletRenderer = new FigletRenderer(FigFontResources.loadFontResource(FigFontResources.STANDARD_FLF));
System.out.println(figletRenderer.renderText("Hello world"));
```
Rendering FIGlet text with an external FIGfont file

```Java
final FigFont figFont;
try(final InputStream inputStream = Files.newInputStream(Paths.get("/usr/local/share/figlet/standard.flf")))
{
	figFont = FigFont.loadFont(inputStream);
}
final FigletRenderer figletRenderer = new FigletRenderer(figFont);

System.out.println(figletRenderer.renderText("Hello world!"));
```

### Built-in FIGfonts

 * Banner
 
```
######                                     
#     #   ##   #    # #    # ###### #####  
#     #  #  #  ##   # ##   # #      #    # 
######  #    # # #  # # #  # #####  #    # 
#     # ###### #  # # #  # # #      #####  
#     # #    # #   ## #   ## #      #   #  
######  #    # #    # #    # ###### #    # 
                                           
```
 * Big
 
 ```
  ____  _       
 |  _ \(_)      
 | |_) |_  __ _ 
 |  _ <| |/ _` |
 | |_) | | (_| |
 |____/|_|\__, |
           __/ |
          |___/ 
```
 * Block
 
 ```
                                             
 _|_|_|    _|                      _|        
 _|    _|  _|    _|_|      _|_|_|  _|  _|    
 _|_|_|    _|  _|    _|  _|        _|_|      
 _|    _|  _|  _|    _|  _|        _|  _|    
 _|_|_|    _|    _|_|      _|_|_|  _|    _|  
                                             
```
 * Bubble
 
```
   _   _   _   _   _   _  
  / \ / \ / \ / \ / \ / \ 
 ( B | u | b | b | l | e )
  \_/ \_/ \_/ \_/ \_/ \_/ 
```
 * Digital
 
 ```
 +-+-+-+-+-+-+-+
 |D|i|g|i|t|a|l|
 +-+-+-+-+-+-+-+
```
 * Ivrit

```
  _   _            ___ 
 | |_(_)_ ____   _|_ _|
 | __| | '__\ \ / /| | 
 | |_| | |   \ V / | | 
  \__|_|_|    \_/ |___|
                       
```
 * Lean

```
                                           
    _/                                     
   _/          _/_/      _/_/_/  _/_/_/    
  _/        _/_/_/_/  _/    _/  _/    _/   
 _/        _/        _/    _/  _/    _/    
_/_/_/_/    _/_/_/    _/_/_/  _/    _/     
                                           
```
 * Mini
 
```
           
 |\/|o._ o 
 |  ||| || 
           
```
 * Mnemonic
 
```
mnemonic
```
 * Script
 
```
                            
   ()            o          
   /\  __   ,_        _ _|_ 
  /  \/    /  |  |  |/ \_|  
 /(__/\___/   |_/|_/|__/ |_/
                   /|       
                   \|       
```
 * Shadow

```

   ___|  |               |                
 \___ \  __ \   _` |  _` |  _ \\ \  \   / 
       | | | | (   | (   | (   |\ \  \ /  
 _____/ _| |_|\__,_|\__,_|\___/  \_/\_/   
                                          
```
 * Slant

```
   _____ __            __ 
  / ___// /___ _____  / /_
  \__ \/ / __ `/ __ \/ __/
 ___/ / / /_/ / / / / /_  
/____/_/\__,_/_/ /_/\__/  
                          
```
 * Small
 
```
  ___            _ _ 
 / __|_ __  __ _| | |
 \__ \ '  \/ _` | | |
 |___/_|_|_\__,_|_|_|
                     
```
 * SmScript

```
                                   
  ()          ()  _   ,_  o    _|_ 
  /\ /|/|/|   /\ /   /  | | |/\_|  
 /(_) | | |_//(_)\__/   |/|/|_/ |_/
                           (|      
```
 * SmShadow

``` 
   __|        __| |              |              
 \__ \  ` \ \__ \   \   _` |  _` |  _ \\ \  \ / 
 ____/_|_|_|____/_| _|\__,_|\__,_|\___/ \_/\_/  
                                                
```
 * SmSlant

```
   ____      ______          __ 
  / __/_ _  / __/ /__ ____  / /_
 _\ \/  ' \_\ \/ / _ `/ _ \/ __/
/___/_/_/_/___/_/\_,_/_//_/\__/ 
                                
```
 * Standard

```
  ____  _                  _               _ 
 / ___|| |_ __ _ _ __   __| | __ _ _ __ __| |
 \___ \| __/ _` | '_ \ / _` |/ _` | '__/ _` |
  ___) | || (_| | | | | (_| | (_| | | | (_| |
 |____/ \__\__,_|_| |_|\__,_|\__,_|_|  \__,_|
                                             
```
 * Terminal

```
Terminal
```
