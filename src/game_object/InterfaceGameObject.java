package game_object;

import transform_library.Transform;

public interface InterfaceGameObject {

	public Transform getTransform();
	public void setTransform(Transform transform);
	public String getTag();
	public void setTag(String tag);

}
