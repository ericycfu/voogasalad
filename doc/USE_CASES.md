USE_CASES.md
===

-   Use case 1: An AOE attack occurs.
    

-   Multiple interactions are issued, between the source and each targets
    

-   Use case 2: Dealing with movement of game objects at the borders of the player screen
    

-   The user attempts to move the unit off the screen; this translates to moving the unit as close to the edge of the map as possible.
    

-   Use case 3: Dealing with incorrect dragging placement of game objects (e.g. not on the player screen)
    

-   Generate an error message on the screen to let the user know that the placement cannot be made
    

-   Use case 4: Dealing with zero health for each gameObject type (where applicable)
    

-   When the health of an object reaches zero, the object dies (component is removed)
    

-   Use case 5: Creating an interaction
    

-   User pressed the create interaction button and a pop up opens up.
    
-   User inputs the origin and target of that interaction.
    
-   User sets which variables will be changed by that interaction
    

-   Use case 6: Creating a building
    

-   User clicks “new unit” with specify type “building”
    
-   he/she chooses a building and position it on the screen
    

-   Use case 7: Creating a worker with attack interaction
    

-   User clicks “new unit” with specify type “worker”. The user specify interaction by selecting from drop-down menu. Here he/she selects pre-set “attack interaction” and specifies amount (-50 health) and targets (all Biological units).
    

-   Use case 8: Creating an attacker
    

-   User selects a barrack
    
-   “new attacker”
    

-   Use case 9: Editing the map’s
    

-   User clicks “terrain” and choose a terrain
    

-   Use case 10: Placing interaction onto new unit
    

-   User drags specific interaction (e.g. heal) from drop down menu and specifies amount (+50 health) and targets (all Friendly Biological units).
    

-   Use case 11: Selecting and moving unit in game, no obstructions
    

-   User left clicks a fighting unit and right clicks a spot on terrain some distance away
    
-   The Game Player creates a Move Interaction between the start point and the end point and executes Interaction to move the fighting unit.
    

-   Use case 12: Moving a unit in game with obstructions
    

-   User left clicks a fighting unit and right clicks a spot on terrain some distance away. The path is blocked by a building.
    
-   The Game Player calculates the shortest path that takes the unit around all obstacles, then creates Move Interactions to move the fighting unit around the obstacles to the destination.
    

-   Use case 13: Attack-moving a fighting unit.
    

-   User left-clicks a fighting unit, presses “A”, and right-clicks a spot on terrain. There is an enemy along the way.
    
-   The Game Player creates an Attack-Move Interaction and executes it as in (11). Along the way, the unit senses that an enemy is in range of attacking; the Interaction stops movement and attacks the enemy.
    

-   Use case 14: Save the current game to a file so that it can be loaded later
    

-   User clicks the save button on the authoring environment and the writer class calls its write function on the units, terrain, and rules, and the appropriate xml files are created.
    
-   User can edit it or play it later by clicking the saved game under Make Game button or Play Game button
    

-   Use case 15: Load the file to continue a previously saved game
    

-   The game loop of engine -> state data->player->commands continues from the current set (both state and command) of data saved.
    

-   Use case 16: Pause/resume the game
    

-   User clicks on pause/resume button, and the game loop can be paused and resumed accordingly by the GamePlayer
    

-   Use case 17: Start the game
    

-   The User chooses their selected game and presses a “Play Game” button.
    
-   The files for the game, including what kind of units are included, the map layout, and the image resources, are loaded through each parts’ reader file, calling the read function which returns lists of the different objects and are stored in each respective part.
    

-   Use case 18: Turn on/off music
    

-   The player enters the menu and deselects music.
    
-   The GamePlayer stops the music.
    

-   Use case 19: How to wake up Eric
    

-   How to locate Eric’s position on bed (e.g. feet on pillow)
    
-   Close out of program
    
-   Walk to Edens-1C
    
-   Knock on Eric’s door. If no one answers just enter through window
    

-   Use case 20: Surrender
    

-   The user clicks to reach the menu
    
-   The user clicks the button “surrender”
    

-   Use case 21: Display game status (time, resources, population, etc.)
    
-   Use case 22: Develop animation behavior
    

-   The animation is represented as a series of state updates, moving the unit incrementally. This is so that all players know the location of all game objects at all times, even when the animation is being completed. It also allows movement to be cancelled.
    

-   Use case 23: Switch from one game to another
    

-   The user pauses the current game, opens a new window, and selects the new game they want to play.
    

-   Use case 24: Display available types of games
    

-   The user goes to the main menu and selects (“Play Game”)
    

-   Use case 25: Set personal preferences
    

-   The user clicks the options button and tweaks
    

-   Use case 26: Scroll the map
    

-   The user moves the mouse to the edge of the screen
    

-   Use case 27: Zoom in/out the view
    

-   The user scrolls the mouse up/down
    

-   Use case 28: Access inventory and drag loaded components on-screen
    

-   The user drags from available inventory to screen
    

-   Use case 29: Grouping multiple units
    

-   The user clicks and drags over the units
    

-   Use case 30: The user attempts to save a new unit in the authoring environment without specifying enough parameters.
    

-   An error pops up telling the user that they need to fill in more parameters before they can save.
    

-   Use case 31: Unit dies from an attack
    

-   The Game Engine will create a trigger for the unit which calls the KillUnit functionality when the health reaches 0. When this condition is fulfilled in the game player, the update function for the unit will have its condition fulfilled and call upon the KillUnit function from the backend.
    

-   Use case 32: Moving multiple units
    

-   The player holds down the mouse button and drags over the rectangular area containing the unit he/she wishes to move. This selects all the units.
    
-   The player then right clicks a position on the screen.
    

-   Use case 33: Unit gathers resources
    

-   The user selects a worker then right clicks the resource.
    
-   An interaction is created for the unit with the target being the resource tag. The interaction will fire when the conditions for gathering resources are met. The user will have given the updateResources function to the interaction and that function will automatically change.
    

-   Use case 34: A Player loses
    

-   The GameInstance determines that loss conditions have been met for a particular player. The user is shown a defeat screen with their score.
    
-   Other players will continue playing until there is only one player remaining and the player is human, or the remaining players are all AIs. The remaining player receives a win screen.
    

-   Use case 35: Start of main game loop
    

-   The GamePlayer and GameEngine will use their respective Reader classes to read the necessary starting data.
    

-   Use case 36: User wants to balance the game by nerfing a unit
    

-   The user can edit game from the Game Authoring Environment, allowing them to edit the attack and health of the previously created unit.
    

-   Use case 37: Specify winning condition: destroy enemy HQ building
    

-   User creates new game, and for loss condition select “HQ destroyed” The user will have to specify at some point what building is the HQ for each individual, and that building must start on the map.
    

-   Use case 38: Specify winning condition: gold production
    

-   User creates new game, and for lose condition select “other player” and “gold production” and gold level
    
-   When a player reaches that particular gold level, the game will stop and a victory screen will pop up for that user; defeat screens will pop up for other users
    

-   Use case 39: The game engine interacting with the game player
    

-   The game engine reads in the commands from the Player using the Reader.read() method on the commands file.
    
-   The Game engine uses the previous state it has and uses the interactions stored along with the commands to come up with the next states. Then it writes these states to the xml file using Writer.write().
    

-   Use case 40:  The game player interacting with the game engine
    

-   The player reads in the states using its Reader.read() method on the states file and it displays the current states on the display and the minimap.
    
-   The player will store the commands that are inputted by the user at the end of each cycle, write them to the command file, using the writer.
    

-   Use case 41: Getting the Game Authoring Environment data stored.
    

-   The person will save the data, prompting the writer in the Game Authoring Environment to write to the correct file directories.
    