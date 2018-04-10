package game_player;

/**
 * All UI components will extend this super-class; allows us to place and resize all UI components easily
 *
 */
public interface Element {
	
	/**
	 * sets the x coordinate of the Element
	 * @param x
	 */
	public void setX(double x);
	
	/**
	 * sets the y coordinate of the Element
	 * @param y
	 */
	public void setY(double y);
	
	/**
	 * sets the width of the element
	 * @param w
	 */
	public void setWidth(double w);
	
	/**
	 * sets the height of the element
	 * @param h
	 */
	public void setHeight(double h);
	
	/**
	 * returns the pane
	 * @return
	 */
	public Pane getPane();
	
	/**
	 * adds a node to the pane
	 * @param n
	 */
	public void addNode(Node n);
	
}