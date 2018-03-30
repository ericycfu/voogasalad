package game_player;

public interface Element {
	
	public void setX(double x);
	
	public void setY(double y);
	
	public void setWidth(double w);
	
	public void setHeight(double h);
	
	
	public Pane getPane();
	
	public void addNode(Node n);
	
}