package JCatGame.Core.Input;

import java.util.HashMap;
import java.util.Map;

import JCatGame.Core.Manager.BaseManager;



/**
 * input manager
 * @author Administrator
 *
 */
public class Input extends BaseManager{

	private enum KeyState
	{
	//分别是未按下,正在按下,第一次按下,第一次弹起
	UpIng,DownIng,Down,Up
	}
	
	private class Key 
	{
		public int keycode;
		public KeyState state=KeyState.UpIng;
		public boolean isLock=false;
		@Override
		public String toString() {
			return "Key [keycode=" + keycode + ", " + (state != null ? "state=" + state + ", " : "") + "isLock="
					+ isLock + "]";
		}

	}
	
	
	/**
	 * 保存按键状态
	 */
	private Map<Integer, Key> keys=new HashMap<>();
	
	
	
	public boolean isKeyUp(int keyCode)
	{
		//默认为弹起
		return keys.get(keyCode)==null||keys.get(keyCode).state==KeyState.Up;
	}

	public boolean isKeyUpIng(int keyCode)
	{
		//这里把down和downing都返回true了 不然貌似单单检测downing会有不爽的延时 虽然只有两帧还是感觉的出来
		return keys.get(keyCode)!=null&&(keys.get(keyCode).state==KeyState.UpIng||keys.get(keyCode).state==KeyState.Up);
	}
	
	public boolean isKeyDown(int keyCode)
	{
	
		return keys.get(keyCode)!=null&&keys.get(keyCode).state==KeyState.Down;
	}
	
	public boolean isKeyDowning(int keyCode)
	{
		//这里把down和downing都返回true了 不然貌似单单检测downing会有不爽的延时 虽然只有两帧还是感觉的出来
		return keys.get(keyCode)!=null&&(keys.get(keyCode).state==KeyState.Down||keys.get(keyCode).state==KeyState.DownIng);
	}

	
	/**
	 * 输入原始输入(某按键是否按下)
	 * 将捕获到的原始输入转换为按键状态
	 */
	public void rawInput(int keyCode,boolean isDown)
	{
		
		//递进更新(如果很难明白逻辑可以画一个状态机就明白了)
		Key key=keys.get(keyCode);
		//按键按下
		if(isDown)
		{
			if(key==null)
			{
				//如果按键还不存在 则为弹起状态 设置为按下状态 
				Key newKey=new Key();
				newKey.isLock=true;
				newKey.keycode=keyCode;
				newKey.state=KeyState.Down;
				keys.put(keyCode, newKey);
			}
			//如果是down状态 则转为downing状态
			else if(key.state==KeyState.Down&&key.isLock==false)
			{
				key.state=KeyState.DownIng;
				key.isLock=true;
			}
			//如果是up或者uping状态则转为down状态
			else if((key.state==KeyState.Up||key.state==KeyState.UpIng)&&key.isLock==false)
			{
				key.state=KeyState.Down;
				key.isLock=true;
			}
		}
		//按键弹起
		else
		{
			//此时按键不可能不存在 因为按键先按下后弹起
			//如果是down或者downing状态 则转为up状态
			if((key.state==KeyState.Down||key.state==KeyState.DownIng)&&key.isLock==false)
			{
				key.state=KeyState.Up;
				key.isLock=true;
			}
			
			
			
		}
		
		
		
		
	}

	public void lockInput() {
		
		
				for(Key key:keys.values())
				{
					//如果是down则转化成downing
					//这里比较难理解 就是之前收到一条down信息但是没有收到up消息的状态
					//原因可能是底层的信息发送频率相对帧率还说很低
					//所以如果之前收到一条down信息但是还没有收到up消息的状态 则其实还是按下
					 if(key.state==KeyState.Down&&key.isLock==false)
						{
							key.state=KeyState.DownIng;
							key.isLock=true;
						}
					//如果是up状态则转为uping状态
					 else if(key.state==KeyState.Up&&key.isLock==false)
					{
						key.state=KeyState.UpIng;
						key.isLock=true;
					}
				}
		
		
				//System.out.println(keys);	
		
	}

	public void upLockInput() {
		
			//解锁输入
				for(Key key:keys.values())
				{
					key.isLock=false;
				}
				
	}
}
