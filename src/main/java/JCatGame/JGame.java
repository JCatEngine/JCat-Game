package JCatGame;

import JCat.RenderSystem;
import JCat.Canvas.CanvasType;
import JCat.Display.Stage;
import JCat.Manager.TextureManager;
import JCatGame.Core.GameObject.BaseDisplayObject;

public class JGame {	
	
	/**
	 * sub render System
	 */
	private RenderSystem renderSystem;

	/**
	 *gamePlane
	 */		
	private BaseDisplayObject gamePlane;	
	/**
	 *cameraPlane
	 */		
	private BaseDisplayObject cameraPlane;
	
	/**
	 *UIPlane
	 */		
	private BaseDisplayObject UIPlane;
	/**
	 *UIPlane2
	 */		
	private BaseDisplayObject UIPlane2;
	/**
	 *debugPlane
	 */		
	private BaseDisplayObject debugPlane;
	

	/**
	 * create a render system au not auto-update
	 * @param width
	 * @param height
	 * @param type
	 */
	public JGame(int width, int height,int frameRate,CanvasType type) 
	{		
		this.renderSystem=new RenderSystem(width, height, frameRate, type);
		init();
	}

	/**
	 * create a render System
	 * @param width
	 * @param height
	 * @param frameRate
	 */
	public JGame(int width, int height,int frameRate) 
	{
		this.renderSystem=new RenderSystem(width, height, frameRate);
		init();
	}



	public JGame(int width, int height) {
		this.renderSystem=new RenderSystem(width, height);
		init();
	}
	

	private void init() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * getImageManager
	 * @return
	 */
	public TextureManager getImageManager() {
		// TODO Auto-generated method stub
		return TextureManager.getInstance();
	}

	/**
	 * get the stage of the render system,stage is the root container
	 * @return
	 */
	public Stage getStage() {
		return renderSystem.getStage();
	}

	
	/**
	 * set frameRate
	 * @return
	 */
	public int getFrameRate()
	{
		return renderSystem.getFrameRate();
		
	}
	
	/**
	 * get frameRate
	 * @param frameRate
	 */
	public void setFrameRate(int frameRate) {
		
		renderSystem.setFrameRate(frameRate);
		
	}
}
