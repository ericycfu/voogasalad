package gui_elements.panes;

import java.io.InputStream;
import java.util.Properties;

import javafx.scene.layout.Pane;

public abstract class MainPane {

	protected static final String X_LOC_STRING = "x_loc";
	protected static final String Y_LOC_STRING = "y_loc";
	protected static final String WIDTH = "width";
	protected static final String HEIGHT = "height";
	protected static final String DIRECTORY_STRING = "data/pane_properties/";
	protected String full_directory_name;
	protected Properties properties;
	protected InputStream input;	
		
	protected abstract void getProperties();
	protected abstract void setX(int x);
	protected abstract void setY(int y);
	protected abstract int getX();
	protected abstract int getY();
	protected abstract void createPane();
	public abstract Pane getPane();	
}