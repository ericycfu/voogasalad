Mid-Point Demo Game Player Notes
====

## High Level Design
The GamePlayer module allows users to load, play and save a game using the GameEngine in the back end. <br/>
At the center of our design is the GamePlayer class, which
    1. sets the scene
    2. intializes all the gui components
    3. adds them to the scene
    4. updates all the gui components during each frame
    5. keeps track of all the gameobjects in the game through its GameObjectManager instance variable
    6. keeps track of what units(gameobjects) have been selected through its SelectedUnitsManager instance variable<br/>

Users will run our program starting from the GameAuthoringEnvironment module; when they decide to play an actual game, the scene instance variable of the GamePlayer classwill be displayed in front of them, and they can play the game.
The SelectedUnitsManager is where the GamePlayer module communicates with the back-end GameEngine, telling gameobjects what interactions to take.

### Gui components:
* TopPanel:
    1. allows the user to pause and resume the current game and save it and load a different game;
    2. displays the ingame time and resouces.
* Minimap:
    1. displays all the units in the current game and their movement
* MainDisplay:
    1. displays units in the current window, which users will be able to move around after future implementation;
    2. allows users to select units and interact with them.
* UnitDisplay:
    1. shows what units are currently being selected;
    2. displays an avatar of the first unit in the selected group; its attritbutes, such as health, mana, damage, and armor; and its skills, such as attack, build and heal.

### Others
* In-class communication: between GamePlayer and its subclasses, SelectedUnitManager and GameObjectManager, etc.

## Example Program 
* Eddie talks about how the TopPanel would interact with the player and its future features.
* Frank introduces Minimap, UnifInfoDisp (how they're implemented, scaling, etc.) and their future features.
* Siyuan talks about how UnitActionDisp works (how it changes the current interaction) and its features not yet implemented

Open: add UI components
We'll justify that we can't make them open