# Brick Destroy
This is a simple arcade video game.
Player's goal is to destroy all the bricks with the ball.
The game has  very simple commands:
Press SPACE to start/pause the game.
Press on the A key to move the player to the left.
Press on the D key to move the player to the right.
Press on the ESC key to enter/exit pause menu.
Press ALT+SHIFT+F1 to open the debug console.
The game automatically pause when the window loses focus.

Enjoy ;-)

## Work Done:
1. Refactoring
   - Change the package name
     - From test to com.game.BrickDestroy (the main package)
     - Created more packages inside the main package (eg. View, Controller, Model, CSS)
     - Easier to know what the package contains
   - Change the file directory
     - Follows Maven’s conventions
     - Easily locate the src and test files
   - Change almost all the class name
     - To match the MVC design pattern
     - After converting to JavaFX, rather than frames, stages are used instead.
   - Change variable name
     - Easier to understand what the variable represents
     - In PlayerModel class, change the variable ballpoint to startX and startY as it represents the coordinates of the player paddle at the start
   - Encapsulate fields
     - Un-encapsulated data is a violation of OOP principles
     - Encapsulated the instances of BrickModel, BallModel and PlayerModel classes created in the WallModel class.
   - Remove used imports
     - From ClayBrickModel, GameFrame (later on renamed to Stages) classes
   - Remove unused MIN_CRACK variable from Brick class
   - Extract Crack (later on renamed as BrickCracks) class from BrickModel class
     - Not all the subclasses use the Crack class.
     - Made CementBrickModel & BlueBrickModel inherit BrickCracks class, BrickCracks class inherits BrickModel class.
   - Extract new class to adhere single responsibility
     - Extract Levels class from WallModel class
     - Extract GameTimer class from GameBoardModel class
   - Pull up methods
     - Pull up makeBrickFace and getBrick method from all the Brick subclasses to the BrickModel class.
     - After applying the MVC pattern, all the classes have the same implementation of that method.
     - Pull up makeBall method from RubberBallModel to the BallModel class.
     - The same reason as the BrickModel subclasses. As all other balls in the future will same the same implementation of that method. However, a variable NAME is created which is used in the view. So when the ball name is different, the looks of the ball will be different.
   - Applied MVC design pattern
     - A design pattern that many people knows and understand.
     - Do not have to worry about changing anything in the view when changing something in the controller or model class.
   - Create JUnit Tests
     - Easier to do regression tests in the future
   - Create Maven build file

2. Additions/Modifications
   - Add info menu in home menu
   - Add background image in home menu
   - Increase speed of player and ball
     - Increase the game difficulty, however the player must be fast enough as well 
   - Calculate score for every game
   - Create a permanent high score list
   - Create a game over menu
   - Create a new level
     - Has 3 brick types (cement, clay and blue)
     - Blue brick is a new type of brick created
   - Add a hidden additional ball that will be added to the total ball count in levels with 3 types of bricks (currently only level 5)
   - In the debug console, rather than allowing users to change the specific x and y coordinates that the ball moves, users can set the speed of the ball. The default ball speed is 1.
   - Next level button will not appear in game over menu and debug console if it is the last level.

3. Convert to JavaFX