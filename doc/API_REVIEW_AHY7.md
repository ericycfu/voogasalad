API_REVIEW_AHY7.md
===
1. Part 1
    * The API is intended to be as flexible as possible in terms of defining advanced interations and defining unit fucntionalities accoridng to the author's specifications.
    * The GameObject class is very generic and 
    * The Game Authoring Environment uses the framework laid out by the Game Engine to allow the game author to create the game. The GamePlayer is responsible for displaying the objects in the front end 
    * For invalid commands or commands with necessary conditions not met, the Game Engine ignores the command. For invalid file read in, the GameEngine just throws an error.
    * I think the API/design is good because it allows for the most customization according to the game author. Basic interactions can be combined and modified to make more complex ones such as abilities, the author can define new stats for specific units, and the author can mix and match a variety of properties at will.
2. Part 2
    * The use cases that the GameEngine is responsible for are related to the play of the game (the Game Authoring Environment will handle on most of the authoring use cases). This includes things such as movement, attacking, and unit production.
    * Simple use cases like movement and manual attacking can be done over the weekend. Automatic unit behavior such as getting units to automatically attack anything in range will take longer as they are inherent game behaviors not specified by the user.
    * I am most excited to work on getting game mechanics to work and translating units specified by the author into actual functional units in the game.
    * I am also most worried about getting game mechanics to work because it seems like a very daunting task and I have little idea how to implement it.
    * This weekend, I'm going to help write the framework for causing Interactions to occur and the basic specifications for units and buildings.
