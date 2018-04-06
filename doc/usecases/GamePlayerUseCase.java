// Game Player Use Case: The gamer clicks on a unit and right click on a terrain

// From game player to data
Command cmd = new Command(selectedList, target, "attack");

GameData.addCommands(cmd);
{
	GameEngine.execute(cmd);
	{
		selectedList.move(target);
	}
}
List<GameObjects> currentObjects = GameData.reader.read();
Display.display(currentObjects);
