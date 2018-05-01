# voogasalad

README.md
===
### Project Members:
Siyuan Chen, Eric Fu, Jamie Kim, Shicheng Rao, Aditya Sridhar, Rayan Tofique, Eddie Yang, Jing Yang, Andrew Yeung, Yifan Yin, Xiaolan You
### Dates Worked:
3/23-5/4/18
### Hours Worked:
~350 hours total
### Roles:
* Siyuan: GamePlayer
* Eric: Data (Integrating Data with rest of project)
* Jamie: Authoring Environment(Map Settings)
* Shicheng: Data (Reading/Writing)
* Aditya: Authoring Environment (Creation of objects/interactions)
* Rayan: Engine (GameObjects and their properties/interactions, Game conditions)
* Eddie: GamePlayer
* Jing: Moral support
* Andrew: Server, Engine (high-level organization)
* Yifan: GamePlayer
* Xiaolan: Authoring Environment (Map placement Integration with other parts)

### Resources used:
StackOverflow, TA's, Oracle's Java documentation, XStream documentation
### Files used to start:
Launch starts the main project. ServerMain starts the server.
Note that the server does not have a graphical component.
### Files used to test project:
DataTester, DemoTester, ReaderTest, WriterTest, TestMain. Most testing was done by running interacting with parts of the application
### Data Files required:
All .css and .properties files are required.
### Errors that can be handled without crashing:
* Incompatible files being selected at any point in the program
* Invalid commands attempted in GamePlayer
* Moving while the game is paused
* Client disconnecting from server
* Server or client errors when reading/writing information

### Other Info about the program
* The extension for this project is a dedicated server that players can connect to to play the game.

### Known bugs
* A couple buttons are currently still broken.
* Server client screen sometimes flickers when a screen transition occurs

### Design Decisions
* Some elements don't really apply to RTS's, such as different levels or high scores. These game elements have been ignored.
* It is assumed that all necessary image resources are loaded. The game itself can be created on 1 computer.
### Impressions
* This project was a large undertaking and introduced the team to something resemblant of a real-world project environment. With so many group members and several subgroups, communication between members and clarity about what needed to be done was key. This project was stressful at times but was very educational and a formative experience.

