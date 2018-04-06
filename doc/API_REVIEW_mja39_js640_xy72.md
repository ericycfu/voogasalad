API Review
Game Authoring Environments
mja39, js640, xy72

#### Part 1

* What about your API/design is intended to be flexible?
    * (js640, mja39) Our API is designed to support creatable custom elements with any combination of attributes. It also features a modular UI split into different design elements that can be changed independantly, making it easier to add new panels, buttons, windows, etc. Furthermore, the designs of our graphical elements are controlled by CSS files, which makes it easier to change the look of the different parts of the UI in one place.
    * (xy72) Our team's API allows users to specify a variety of settings (attributes/interactions) to synthesize into custom made GameObjects. Created instances of GUI elements individually, and allows placement anywhere. 

* How is your API/design encapsulating your implementation decisions?
    * (js640, mja39) The game authoring environment only writes data about objects, including their attributes, locations, behaviors, appearances, etc., to data files, which are then handled by game data. Thus, while it uses the game engine's API as a guide, it doesn't affect the function of the rest of the project at all. 
    * (xy72) Modifies object one at a time. In addition, game authoring environment will be completely separate from game player as separated by game data. 
* How is your part linked to other parts of the project?
    * (js640, mja39) Our part is linked to game data in that our objects in each level will be saved via serialization. Our part is linked to game engine because we must work within the framework that they have provided. 
    * (xy72) Allows design to specify within supported framework of Game Engine. Later sends customized game to Game Data so they may interpret for Game Player.
* What exceptions (error cases) might occur in your part and how will you handle them (or not, by throwing)?
    * (js640, mja39) We have considered errors wherein the user does not upload a valid image file for their custom element. We will probably throw an error. We have also recently been introduced to the possibility of the user leaving some attributes blank, and we will consider how to handle this at the next small team meeting. 
    *  (xy72) Use error catching from parts of Game Engine when using back-end logic. Have own system to error catching and display for user interface.
* Why do you think your API/design is _good_ (also define what your measure of good is)?
    * (js640, mja39) Good design, in my opinion, should be easy to update, modify, and expand from the developer's perspective, as well as easy to understand and use from the user's perspective while still being powerful. I think that our design is good because, as was mentioned above, authoring custom elements should be very easy for the user to do, allowing one to create a very broad scope of games and make many decisions about how they work, yet that complexity is handled well on the development with a series of modular element types and behavior options, which can be expanded with relative ease.
    * (xy72) It's good b/c it works foo.

#### Part 2

1.  Discuss the use cases/issues that you are responsible for: are they descriptive, appropriate, reasonably sized?
    * (js640)
        * Creating custom elements
        * Creating grids for each level
        * Updating element attributes
    * (mja39)
        * Also creating custom elements
        * Selecting and placing elements on the grid/map
        * Possibly implementing motion controls via leap motion
    * (xy72)
        * Ability for newly created objects to appear in tab
        * Ability to place created objects in map
        * Ability for objects/maps to dynamically update
        * Use cases are descriptive, specific, and reasonable. 
3.  Estimate how long you think each will take and why. What, if anything, makes estimating these tasks uncertain?
    * (js640)
        * I have allocated 2 hours each day for the rest of the semester to work on my use cases and the project in general. 
    * (mja39)
        * One thing that makes estimating the time these tasks will take very uncertain is the number of people and moving parts associated with the project; we don't know if/when each module on which another module is dependent will be complete. That being said, I think these will complete by Sunday, at which point we'll regroup and reallocate more advanced use cases.
    * (xy72)
        * Each will take approximately 5 hours each.  
5.  What feature/design problem are you most excited to work on?
    * (js640)
        * Creating an attribute window that is functional in updating element data. 
    * (mja39) 
        * I'm most excited to work on the functionality of the element adding interface, and apprehensive yet excited about trying to implement motion controls.
    * (xy72)
        * Placement of objects: click & drag, dynamic updating of location
7.  What feature/design problem are you most worried about working on?
    * (js640)
        * Allowing our game elements to be saveable by game data. 
    * (mja39)
        * I am worried about the complexity associated with managing the parameters associated with cusom authored elements
    * (xy72)
        * Sending information through multiple classes
9.  What major feature do you plan to implement this weekend?
    * (js640)
        * Functional grid; functional attribute editor 
    * (mja39)
        * This weekend I plan on finishing the element adding interface.
    * (xy72)
        * CreatedObject display

