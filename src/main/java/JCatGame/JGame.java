package JCatGame;


import JCat.RenderSystem;
import JCat.Canvas.CanvasType;
import JCat.Display.Stage;
import JCat.Event.Event;
import JCat.Event.EventListener;
import JCat.Event.KeyEvent;
import JCat.Manager.TextureManager;
import JCat.Utils.Ticker.OnResponceListener;
import JCatGame.Core.GameObject.BaseDisplayObject;
import JCatGame.Core.Input.Input;

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

	private Input input;
	

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
		
		renderSystem.getTicker().addListener(new OnResponceListener() {
			
			@Override
			public void onResponce() {
				loop();
				
			}
		});
		
		//init show pane
		initShowPlane();
		
		//init manager
		InitManager();
		
		
		
	}
	
	private void InitManager() {
		//because input is a frequently used class,so i don't want it to be a static class
		this.input=new Input();
		getStage().addEventListener(KeyEvent.KEY_DOWN, new EventListener() {
			
			@Override
			public void onResponce(Event event) {
				KeyEvent keyEvent=(KeyEvent) event;
				input.rawInput(keyEvent.keyCode, true);
			}
		});
		getStage().addEventListener(KeyEvent.KEY_UP, new EventListener() {
			
			@Override
			public void onResponce(Event event) {
				KeyEvent keyEvent=(KeyEvent) event;
				input.rawInput(keyEvent.keyCode, false);
			}
		});
	}

	private void initShowPlane() {
		
		gamePlane=new BaseDisplayObject();
		getStage().addChild(gamePlane);
		
		cameraPlane=new BaseDisplayObject();
		getStage().addChild(cameraPlane);
		
		UIPlane=new BaseDisplayObject();
		getStage().addChild(UIPlane);
		
		UIPlane2=new BaseDisplayObject();
		getStage().addChild(UIPlane2);
		
		debugPlane=new BaseDisplayObject();
		getStage().addChild(debugPlane);
		
	}

	protected void loop() {
		
		//锁定输入
		input.lockInput();
//		//广播更新事件
//		eventManager.sendBroadCast(new UpdateEvent());
//		//处理消息
//		eventManager.dealMessage();
//		//进行渲染
//		renderManager.render(gameStage,root);
//		//解锁输入
		input.upLockInput();
		
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

	public RenderSystem getRenderSystem() {
		return renderSystem;
	}

	public BaseDisplayObject getGamePlane() {
		return gamePlane;
	}

	public BaseDisplayObject getCameraPlane() {
		return cameraPlane;
	}

	public BaseDisplayObject getUIPlane() {
		return UIPlane;
	}

	public BaseDisplayObject getUIPlane2() {
		return UIPlane2;
	}

	public BaseDisplayObject getDebugPlane() {
		return debugPlane;
	}

	public Input getInput() {
		return input;
	}
	
	
	
}
