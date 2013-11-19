package net.spx.spago.utils;

public class ScreenConfiguration {
	
	private int frameWidth;
	private int frameHeight;
	
	protected static ScreenConfiguration instance;

	protected ScreenConfiguration(int frameWidth, int frameHeight){
		this.frameWidth=frameWidth;
		this.frameHeight=frameHeight;
	}
	
	public static void initInstance(int frameWidth, int frameHeight){
		instance=new ScreenConfiguration(frameWidth, frameHeight);
	}
	
	public static ScreenConfiguration getInstance(){
		return instance;
	}

	public int getFrameWidth() {
		return frameWidth;
	}


	public int getFrameHeight() {
		return frameHeight;
	}


}
