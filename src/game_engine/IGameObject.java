package game_engine;

public interface IGameObject {

	public Transform getTransform();
	public void setTransform(Transform transform);
	public String getTag();
	public void setTag(String tag);

}
