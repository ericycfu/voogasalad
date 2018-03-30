DESIGN_PLAN
===

# Introduction
The goal for our program is to create an RTS game authoring and playing environment, which allows users to save, edit, and play a game they created.

The program consists of four main components: graphical authoring environment, game engine, game data, and game player. The user can use the graphical authoring environment to create actual games, which are stored as game data, through the game engine. The user can then use the game player to play the games by loading game data. 

Our program is most flexible in that adding new kinds of game objects and interactions will be easy, and this is the open part of our project. Closed part of the project will be how the game authoring environment saves and loads a created game and adding a new element through the authoring environment.

How the four primary parts interact among themselves should be closed for modification. 

Real-Time Strategy defines a genre of games in which a player manipulates their units and/or resources to achieve a goal (e.g. destroy all enemy units, destroy all enemy buildings). Unlike other genres, most games in this genre do not have levels or waves. Usually, the player is responsible for both macromanagement (the ability to use resources efficiently to produce units and the ability to generate a large supply of resources) and micromanagement (the ability to control unit behavior in fights).


# Overview
*   Game Authoring Environment

    -   customizable by goal, map, and objects. It will have the following modules.

    -   Design tab:


    -   CustomizeGame: allows the author to edit game settings, such as win condition

    -   CustomizeObject: allows author to create game objects with Attributes (own characteristics such as health) and Interactions (behavior of object in conjunction with other objects, such as Attack). Objects are stored in CreatedObjects.


    -   CustomizeAttribute

    -   CustomizeInteractions


    -   Placement tab:


    -   CustomizeMap: allows the author to visually place objects from CreatedObjects for the map’s initial configuration.


    -   Created items:


    -   CreatedObjects: displays the objects that user has created, filtered by type (unit building)

    -   CreatedMaps: maps customized by the authors


    -   AuthoringEnvironmentView: lays out front end GUI placement

    -   Transfer of Authoring Environment to Game Data


    -   Activates on click of “save” or “play” button, as well as when the user exits the program (upon which the user will be prompted if it wants to save game state if it hasn’t already)

    -   Settings are held by Game Data’s AuthorVisitor object for transfer to other classes.

    -   Data to be sent: list of gameObjects containing data structures of attributes (e.g. position), interactions (e.g. type of interaction and list of components to interact with), map, etc
    

    
[Image 1] (image1.jpg)

*   Game Engine
    -   The game engine will provide a framework for the game and interactions between units. Units/buildings/obstacles in the game will be all instances of GameObject; the GameObject is responsible for handling all interactions between GameObjects (e.g. attacking, resource gathering). The game engine will also provide a framework for maps with a certain number players, initial state of the map (starting buildings, starting units) and certain win conditions per player.

    -   One of the core ways that GameObjects will interact with each other is through “interactions”. Interactions are user defined actions that happen when one GameObject interacts with another GameObject through collision, or user input. The interaction will modify variables or properties in both GameObjects through functions that we provide ourselves e.g. ModifyProperty(String property, double modifier) or SlowUnit(double modifier), addToPlayerResources(String resourceName). The interaction will also contain conditions on when to execute e.g. on different keys or mouse clicks.

    -   The GameEngine will also take care of Triggers. Triggers are events that happen when values reach a certain level for GameObjects. For exampe, if a unit runs out of a ‘ammo’, the Trigger will disable it’s attack interaction.

*   Game Player
    * The overarching class will be named Gameplayer, in which we will instantiate all the objects of ui components, read from game data and write to it. 
    * Each ui component, such as main display, minimap, and attribute display, will have its own class.
    * Each game player holds on its own UI data (e.g., the viewer's current position and the selected units). The actions taken by the user (right/left click, key pressed, etc.) on selected unit(s) are passed to the Game Data via commands, and the updates of all of the Game Object instances (units, terrains, etc.) are passed from the Game Data to the Game Player. 
    * The filtering of the units are done by the Game Player class - the Game Data passes all of the Game Object information to the Game Player, and the Game Player determines what gets instantialized. For now, it is planned that only selected units and the viewable units are instantialized. 
*   Game Data
    -   The game data is in charge of bridging the gap between the other three. We have a reader class and a writer class. Each main class of the other three parts will store an instance of a reader and a writer. The writer will create the different xml files, and the reader will read the xml files and return a list of the different objects made from the files. There will be  five files: one for the different units created from the game authoring environment, one for the states/units on the map during the game from the game engine, one for the commands entered by the player during gameplay, one for the map/terrain, and one for the game rules (win conditions etc.). The Game Engine will read from the initial states, and the rules, and the Game Player will read from the initial states and the visuals from the units, and the terrain. Then during the game loop, the game engine will read from the commands and write to the states, while the player will read from the states and write to the commands. During the game authoring loop, the game authoring environment will initially read all of the units, the terrain, and the rules. Then it will write to these if any are updated. Then the reader will read in the interactions and rules to the engine, and the player’s reader will read in the visuals of the units and the terrain. 

# User Interface

### Game Player UI:
* The GamePlayer user interface contains the following components: the TopPanel, the Display, the MiniMap and the InfoDisplay. The TopPanel contains a list of buttons (the Menu, the Help and the Score) and a list of texts (the Time, the Gold and the Wood). The Menu button pops up the menu when it gets clicked, the Help button pops up the text field in which the game information is written, and the Score button pops the list of all players enrolled in this game and their scores. The Display contains all of the visualization for the Game Objects that are visible to the user at his/her current location. The MiniMap contains all of the explored terrains, and all of the Game Object units on the terrains in a simplified format (e.g., colored dots). Upon mouse click actions, the MiniMap should be able to reflect the click actions on the big map (i.e., the corresponding coordinates). The InfoDisplay contains the information visualization of the currently selected unit. The UnitInfo under the InfoDisplay shows the unit's image and basic properties (health, mana, armor, etc.), and the UnitSkill under the InfoDisplay shows the unit's abilities and skills, which can be clicked to initialize commands.

[Image 2](image2.jpg)
### GameAuthoring UI

-   The starting window will have two buttons, Make Game and Play Game. Make Game button will open up a game authoring environment that lets you make a new game and edit created games. Play Game button goes to choose game and then to game player with the chosen game. The constructed game will be visualized.  The authoring environment has two tabs: Design and Place.Under the Design tab, there are different panels such as image, attribute setting, interaction setting, and a panel that shows created objects. The purpose of this tab is the creation, edit, and display of units. The user should be able to set settings of the units and most of the game’s functionality should be created here. The user interface of the tabs are shown in the image. Under the Place tab, there will be the map of the game as well as the  created objects. Possible errors to show the users are incorrect parameters for game settings, failure to save the game, and confirmation messages when the user tries to destroy data. The errors will be shown by JavaFX dialogs.
    
[Image 3](image3.jpg)
# Design Details


*   Game Authoring Environment

    -   Maintains a design pattern similar to a model-view-controller (MVC), but specific to the category; consists of a general authoring environment controller, a collection of classes that collaborate to constitute multiple views for the environment (e.g. design view for adding components), and a model consisting of the individual components


    -   AuthoringEnvironmentView


    -   Purpose: highest level of Game Authoring Environment presents front-end layout.

    -   Classes it interacts with: encompasses tabs between “design” and “place” with fixed CreatedObjects display on the side.


    -   Creating game:


    -   CustomizeGame


    -   Purpose: To allow the game designer to choose the conditions of the game


    -   Will consist of a series of combo boxes targeting specific settings


    -   Classes it interacts with: Specific interfaces/classes managed and maintained by Game Data in the form of data structures


    -   Creating objects


    -   CustomizeObject


    -   Purpose: customize game object by attributes

    -   Classes it interacts with: CustomizeAttributes and CustomizeInteractions to specify settings in 2 maps, CreatedObjects to edit available objects or store new objects


    -   CustomizeAttribute


    -   Purpose: author selects attributes according to available list provided by Game Engine, fills in necessary information, again provided by Game Engine. Stored as map.

    -   Classes it interacts with: CustomizeObject to link back to specific object. Game Engine property files for default settings to fill out.


    -   CustomizeInteractions


    -   Purpose: author selects object interactions according to available list provided by Game Engine. Stored as map.

    -   Classes it interacts with: CustomizeObject to link back to specific object. Game Engine property files for required settings to fill out.


    -   Creating maps


    -   CustomizeMap


    -   Purpose: customize maps by attributes. Allows authors to drag and drop from CreatedObjects

    -   Classes it interacts with: CreatedMaps to store/edit map, CreatedObjects for available objects


    -   Made objects


    -   CreatedObjects


    -   Purpose: Maintains a window containing the objects created by the game designer; it is contained within a Group object that acts as a “temporary infinite memory cache” of gameObject objects

    -   Classes it interacts with: CustomizeMap to allow addition of objects in its inventory right onto the map, CustomizeObject (to allow objects in its inventory to be edited), AuthoringEnvironmentView (which contains this window)


    -   CreatedMaps


    -   Purpose: Maintains a list of map objects containing game components specific to each map

    -   Classes it interacts with: CustomizeMap (which will create and store the map here)


    -   Sending data


    -   AuthorDataWriter


    -   Purpose: Collect all the required information to write into data, including gameObject objects and maps

    -   Classes it interacts with: DataWriter (a Game Data class)


    -   AuthorDataReader


    -   Purpose: Get data from Game Data

    -   Classes it interacts with: DataReader (a Game Data class)  
*   Game Engine
    -   The Game represents the game that the user creates. It stores what Maps exist for the game, and the GameInfo.

    -   The GameInstance represents an instance of a game that is currently being run. Each GameInstance stores the Map, lose conditions for each player, and any other information central to the game itself (time elapsed, etc); the Map stores all GameObjects on the field at a particular point in time. The GameObject represents any object in the game, such as an obstacle, a building, or a unit; it stores information such as its position, its owner, attack, and health. The GameInstance will store an instance of GameInfo which dictates what GameObjects exist, their specifications (e.g. initial health), and what Interactions exist between objects; the GameInfo will be the same for the duration of the game.

    -   Interactions are defined as any action; the framework default Interactions such as attacking, healing, and resource gathering will be implemented as default specifications for Interaction instead of being their own classes.

    -   The GameObject represents any object in the game. Each GameObject will store its graphical representation and any relevant attributes. The DisplayableObject interface will be used to give the GamePlayer only the graphical representation.

    -   The GameInfo stores all information about the game, such as what Interactions exist between GameObjects and what GameObjects exist. It is a fairly passive class (used mostly for data storage) because its information is intended not to change while a game is running.
    
*   Game Player
    *   The Gameplayer class instantiates all components: TopPanel, BottomPanel, Minimap, and Display. This class is where we are related to other modules the most. It keeps track of all the game objects in the game.
    *   The Display is where the game objects and the terrain are displayed. It has a filter methods that decides which objects are in the screen and should be displayed. When the user moves their view window, objects that are no longer in the window will be removed from the display; objects that enters the window will now be displayed.
    *   The TopPanel contains a few sub-classes, such as status display and menubar. The status display will use change listeners to update the values being displayed, such as resource amount.
    *   The BottomPanel also contains a few sub-classes, such as skill display and attribute display. When a game object is clicked in the main window, the internal api of skill display and attribute display is called so that the skills and attribute of that specific object is displayed.
    *   The MiniMap contains the method to update display, which filters the list of Game Object instances that is stored in the Game Player class by certain conditions - specifically, all of the explored terrains and the units on them gets selected and represented on the MiniMap. Instead of the exact ImageViews, simple shapes (e.g., circles, triangles and squares) are added on top of the MiniMap. The locations of these shapes should be correctly scaled, corresponding to the locations of the Game Object instances in the real map. 

*   Game Data

    -   The reason why we only have one reader and writer class is because the details of the reading and writing are encapsulated by the Xstream library-in that the code for going from an object to xml will be the same for the different types of files. In creating the objects from reading the files, we will again use Xstream to get the information and cast it to the right type of object from the root tags in the xml file.

    -   It will need specific resource files, the locations of which will be stored in a constants files. The writer and reader modules will never interact with each other.
    


# Example games
### 1. Red Alert
* Most units only have very few simple interactions, such as attack and repair. They don't have "skills" or "spells".
* All units have health, damage, armor type, armor value attritubes.
* Buildings don't have active abilities for users to use.
* Only one type of resource exists.
* User can decide the winning condition: destroying all units or destroying all buildings.
### 2. Warcraft
* Most units have multiple skills, that interacts with other units in more complicated ways.
* Buildings have active abilities.
* Multiple types of resources exist.
* Units have more attributes, such as mana.
### 3. Dota2
* Much fewer units, however, each individual unit is very complicated, with multiple attributes, abilities , and modifiers. 
* Solely multiplayer based, with multiple players on a team. 

### How the functional differences in these games is supported by your design and enabled by your authoring environment:
* We allow users to customize what interactions/abilities each kind of unit has.
* We allow users to customize what attributes each kind of unit has
* We allow users to decide what units can be controlled and what units cannot.
* We allow users to customize winning conditions
* We allow users to play against other people through a network.
# Design Considerations
* Game Authoring Environment
    -   We discussed implementing Builder vs Abstract Factory design patterns. We settled on Builder design pattern due to its compatibility with Game Engine and Game Data structuring, which allows us to define object behavior as a series of tags

* Game Player
    * We discussed whether we should have a super class for all the ui components. We decided that we should because that makes placing and resizing the components much easier.
    * We discussed how we should update the minimap. We decided to have each node on the actual map to have location properties that the minimap can listen to.
    * We discussed how we should communicate with other components of this program for visualization. One alternative is to have the Game Player communicates with the Game Engine, which passes all the Game Object instances to the Game Player for display. The other alternative is to have the Game Data store the current game states and act as the controller for the program. The first alternative is more efficient than the second one because reading/writing data is not repeated as the program runs. The second alternative is more organized and structured, and can potentially make the networking feature easier by having one central server that stores the game states for all the players. We decided to take the second alternative because it could be a very flexible design pattern that is helpful for multiplayer networking. 



-   Game Player
    

    -   We discussed whether we should have a super class for all the ui components. We decided that we should because that makes placing and resizing the components much easier.

    -   We discussed how we should update the minimap. We decided to have each node on the actual map to have location properties that the minimap can listen to.

    -   We discussed how we should communicate with other components of this program for visualization. One alternative is to have the Game Player communicates with the Game Engine, which passes all the Game Object instances to the Game Player for display. The other alternative is to have the Game Data store the current game states and act as the controller for the program. The first alternative is more efficient than the second one because reading/writing data is not repeated as the program runs. The second alternative is more organized and structured, and can potentially make the networking feature easier by having one central server that stores the game states for all the players. We decided to take the second alternative because it could be a very flexible design pattern that is helpful for multiplayer networking.
       

-   Game Data
    

    -   A lot of consideration was put into the exact classes we would need. At the start we were planning to use the visitor pattern for writing files. This would have had the advantage that we would only need one method (accept for the classes, visit for the visitor). However, it turns out that our model can do the same thing, since through predetermined locations, we can read and write to those locations the same way, since xstream uses toXML() for all types as long as they are serializable.

    -   Another issue we decided on is whether or not to use inheritance or composition for reader types or neither. Since we could have Reader, we could have VisualReader, InteractionReader, etc…  and through either polymorphism or internal calls, each different necessary loading of data could be handled the same way. In the end though, neither really seemed optimal, since the core issue behind both is that it requires hard-coding of the types of data that need to read, which might need to change, so letting the class that contains the reader decide, so if they want to read a specific type, they can specify it.
    
