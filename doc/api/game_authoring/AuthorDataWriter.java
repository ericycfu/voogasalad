package game_authoring;
/**
 * Collect all the required information to write into data, including gameObject 
 * objects and maps. Interacts with Game Data’s class Datawriter
 * @author xlany
 *
 */
public interface AuthorDataWriter {
	/**
	 * Sends CreatedMaps and CreatedObjects to DataWriter
	 */
	public void write(CreatedMaps maps, CreatedObjects objects);
}
