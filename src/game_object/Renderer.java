package game_object;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

import game_engine.Timer;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

/**
 * 
 * @author Rayan
 * Contains data about the game object that will be vital for rendering.
 * Will deal with java fx logic
 */

public class Renderer implements Serializable{
	private String myImageLocation;
	public final static double TEMP_OPACITY = 0.5;
	public final static double NORMAL_OPACITY = 1;
	public final static double INVISIBLE_OPACITY = 0.1;
	public final static double FLASH_DURATION = 0.05;
	public final static Color INTERACTION_COLOR = Color.RED;

	@XStreamOmitField
	private transient ImageView myDisp;
	
	
	private Timer invisTimer;
	private boolean isHit;
	private double elapsedTime;

	private ImageView litImg;
	private ImageView unLitImg;
	
	public Renderer(String imageLocation)
	{
		myImageLocation = imageLocation;
		myDisp = new ImageView(new Image(imageLocation));
		isHit = false;
		this.elapsedTime = 0;
	}
	
	public Renderer(Renderer other)
	{
		this.myImageLocation = other.myImageLocation;
		setupImage();
	}
	
	public ImageView getDisp() {
		return myDisp;
	}
	public void setupImage() {
		litImg = new ImageView(new Image(myImageLocation));
		unLitImg = new ImageView(new Image(myImageLocation));
		myDisp = unLitImg;
		setUpLitImg();
	}
	
	public String getImagePath() {
		return myImageLocation;
	}
	
	public void reduceOpacity(double opacity)
	{
		getDisp().setOpacity(opacity);
	}
	
	public void setDefaultOpacity()
	{
		getDisp().setOpacity(Renderer.NORMAL_OPACITY);
	}
	
	public void updateRenderer()
	{
		if(isHit)
		{
			if(invisTimer.timeLimit(elapsedTime, FLASH_DURATION))
			{
				deFlashUnit();
			}		
		}
	}
	
	public void makeUnitInvis()
	{
		myDisp.setVisible(false);
	}
	
	public void setElapsedTime(double time)
	{
		this.elapsedTime += time;
	}
	
	public void setDisp(ImageView disp) {
		myDisp = disp;
	}
	
	
	
	public void flashUnit()
	{
		//getDisp().setOpacity(Renderer.INVISIBLE_OPACITY);
		this.myDisp = litImg;
		this.isHit = true;
		invisTimer = new Timer();
		invisTimer.setInitialTime(elapsedTime);
	}
	
	private void setUpLitImg()
	{
		Lighting lighting = new Lighting();
		lighting.setDiffuseConstant(1);
		lighting.setSpecularConstant(0);
		lighting.setSpecularExponent(0);
		lighting.setSurfaceScale(0);
		lighting.setLight(new Light.Distant(45, 45, INTERACTION_COLOR));
		litImg.setEffect(lighting);
	}
	
	public void deFlashUnit()
	{
		this.isHit = false;
		//getDisp().setOpacity(Renderer.NORMAL_OPACITY);
		this.myDisp = unLitImg;
	}
	
	public Renderer()
	{
		
	}
}
