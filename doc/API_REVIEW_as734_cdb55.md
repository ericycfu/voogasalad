#### Part 1

1.  What about your API/design is intended to be flexible?
    * Broke up the design into different phases. Broke it up into designing objects, ... Trying to decrease the number of dependencies while simultaneously creating a solid heirachy of classes. Tries to only handle the graphical portions and pass on the changes to functionality to all of the other groups
    * Our design is designed to be flexible by relying heavily on reflection and inheritence. Almost every list or array is populated using inheritance and there is an entire seperate thread that we created to handle the interactions between threads so that its possible for one action to impact all of the different classes. Moreover, we created wide inheritence stuctures to deal with this.

2.  How is your API/design encapsulating your implementation decisions?
    * They use data as an intermediary. They use this to limit the amount of data they need. 
    * We tried to limit the amount of access that our element has with other elements and the different parts of our classes had. Thus we created broadcast and broadcast listeners which essentially create another thread that runs through a controller to decide what methods need to be exectured. 

3.  How is your part linked to other parts of the project?
    * Their Authoring enviroment operates through data. They have a mini memory and are only ever keeping the graphics of objects. They can load through data or save through data. They use reflection to handle the interactions with other classes. 
    * We rely on the gameEngine to determine what objects that we have the ability to create and we rely on gameData to load and save the game but we've really tried to limit the interactions.
     
4.  What exceptions (error cases) might occur in your part and how will you handle them (or not, by throwing)?
    * Trying to process null values --> Creating a new component with empty fields
    * Duplication --> e.x. Same ids
    * Front End Errors --> files missing, null pointer exceptions, threadding errors etc
    * We're trying to throw as few errors as possible. The user doesn't need to know of errors unless they need to offer an alternative value. Therefore, creating a popup to prompt this would be good.

5.  Why do you think your API/design is _good_ (also define what your measure of good is)?
    * Their flexibility, their structure, and their integration, limited role
    * Our design is great because it is extremely flexible. Honestly, that's our definition of good at this point because flexibility means less of a headache in the long run. When we refactor our code we can worry about encapsulation.


#### Part 2

1. Discuss the use cases/issues that you are responsible for: are they descriptive, appropriate, reasonably sized?
	* Multiple interactions are issued, between the source and each target
	* Dealing with movement of game objects at the borders of the player screen
	* The user attempts to move the unit off the screen; this translates to moving the unit as close to the edge of the map as possible.
	* Dealing with incorrect dragging placement of game objects (e.g. not on the player screen)
	* Generate an error message on the screen to let the user know that the placement cannot be made
	
2. Estimate how long you think each will take and why. What, if anything, makes estimating these tasks uncertain?
    * Use case 1: 3-4 hours, since we need to transfer data into a structured object to which functionality can be added by the game engine
    * Use case 2: 3 hours, as there are several border cases to take care of (e.g. edge cases)
    * Use case 3: 3-4 hours, as we need to experiment with dragging listeners
    * Use case 4: 3-4 hours, as we need to experiment with dragging listeners
    * Use case 5: 2-3 hours, as we need to incorporate the front-end and back-end components to the error that is caught by improper interactions
            
3. What feature/design problem are you most excited to work on?
    * Creating the tab for designing a new component

4. What feature/design problem are you most worried about work
    * Integration of component design tab with the map

5. What major feature do you plan to implement this weekend?
	* Working on tab design of components