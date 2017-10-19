package Example;

import JCat.Display.Bitmap;
import JCat.Display.Texture;
import JCat.Event.Event;
import JCat.Event.EventListener;
import JCat.Interaction.KeyCode;
import JCat.Utils.ImageLoader;
import JCat.Utils.ImageLoader.onAchieveListener;
import JCatGame.JGame;

public class Input {
	public static void main(String[] args) {

		ImageLoader loader = new ImageLoader();
		loader.add("resources\\bunny.png").start(new onAchieveListener() {

			@Override
			public void onAchieve(ImageLoader loader) {

				loadAchieve();
			}
		});
	}

	protected static void loadAchieve() {

		JGame game = new JGame(800, 600);
		Texture texture = game.getImageManager().getTextureByName("bunny");
		Bitmap bitmap = new Bitmap(texture);
		bitmap.x = game.getStage().getStageWidth() / 2;
		bitmap.y = game.getStage().getStageHeight() / 2;
		bitmap.setAnchorX(0.5);
		bitmap.setAnchorY(0.5);
		game.getGamePlane().addChildAll(bitmap);
		
		game.getStage().addEventListener(Event.UPDATE, new EventListener() {
			
			@Override
			public void onResponce(Event event) {
				if(game.getInput().isKeyDowning(KeyCode.W))
				{
					bitmap.y-=5;
				}
				if(game.getInput().isKeyDowning(KeyCode.A))
				{
					bitmap.x-=5;
				}
				if(game.getInput().isKeyDowning(KeyCode.S))
				{
					bitmap.y+=5;
				}
				if(game.getInput().isKeyDowning(KeyCode.D))
				{
					bitmap.x+=5;
				}
			}
		});

	}
}
