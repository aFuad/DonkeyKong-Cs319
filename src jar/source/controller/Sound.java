package source.controller;

import java.io.IOException;
import java.io.InputStream;

import sun.audio.*;

public class Sound {
	private AudioStream audioStream;
	private ContinuousAudioDataStream loop1;
	private ContinuousAudioDataStream loop2;
	private ContinuousAudioDataStream loop3;
	private InputStream ws;
	private AudioData a;

	boolean enable;
	
	public Sound(){
		enable = true;
		
		
	}

	public void disableSound(){
		enable=false;
		if(enable==false) {
			AudioPlayer.player.stop(loop1);
			AudioPlayer.player.stop(loop2);
			AudioPlayer.player.stop(audioStream);
		}
			
	}

	public void enableSound(){
		enable=true;
		if(enable==true) {
			AudioPlayer.player.start(loop1);
			AudioPlayer.player.start(audioStream);
		}
	}

	public boolean sound(){
		return enable;
	}

	public void walk(){
		if(enable){
			try{	
				ws = getClass().getResourceAsStream("/data/walking.wav");
				audioStream = new AudioStream(ws);
				a = audioStream.getData();

				loop3 = new ContinuousAudioDataStream(a);
				AudioPlayer.player.start(loop3);

			}catch(IOException a){
				System.out.println("Not Found");
			}
		}
	}

		public void jump(){
			if(enable){
				try{
					ws = getClass().getResourceAsStream("/data/jump.wav");
					audioStream = new AudioStream(ws);
					AudioPlayer.player.start(audioStream);
				}catch(IOException a){
					System.out.println("Not Found");
				}
			}
		}

		public void intro(){
			if(enable){
				try{
					ws = getClass().getResourceAsStream("/data/intro.wav");
					audioStream = new AudioStream(ws);
					AudioPlayer.player.start(audioStream);
				}catch(IOException a){
					System.out.println("Not Found");
				}
			}
		}

		public void getNewItem(){
			if(enable){
				try{
					ws = getClass().getResourceAsStream("/data/get item.wav");
					audioStream = new AudioStream(ws);
					AudioPlayer.player.start(audioStream);
				}catch(IOException a){
					System.out.println("Not Found");
				}
			}
		}

		public void hammer(){
			if(enable){
				try{
					ws = getClass().getResourceAsStream("/data/hammer.wav");
					audioStream = new AudioStream(ws);
					AudioPlayer.player.start(audioStream);
				}catch(IOException a){
					System.out.println("Not Found");
				}
			}
		}

		public void lose(){
			if(enable){
				try{
					ws = getClass().getResourceAsStream("/data/death.wav");
					audioStream = new AudioStream(ws);
					AudioPlayer.player.start(audioStream);
				}catch(IOException a){
					System.out.println("Not Found");
				}
			}
		}

		public void win(){
			if(enable){
				try{
					ws = getClass().getResourceAsStream("/data/win.wav");
					audioStream = new AudioStream(ws);
					AudioPlayer.player.start(audioStream);
				}catch(IOException a){
					System.out.println("Not Found");
				}
			}
		}

	public void background(){
		if(enable){
			try{	
				ws = getClass().getResourceAsStream("/data/background.wav");
				audioStream = new AudioStream(ws);
				a = audioStream.getData();

				loop1 = new ContinuousAudioDataStream(a);
				AudioPlayer.player.start(loop1);

			}catch(IOException a){
				System.out.println("Not Found");
			}
		}
	}
	
	public void levelSelection(){
		if(enable){
			try{	
				ws = getClass().getResourceAsStream("/data/level selection.wav");
				audioStream = new AudioStream(ws);
				a = audioStream.getData();

				loop2 = new ContinuousAudioDataStream(a);
				AudioPlayer.player.start(loop2);

			}catch(IOException a){
				System.out.println("Not Found");
			}
		}
	}
	
	public void stopBackground(){
		AudioPlayer.player.stop(loop1);
	}
	
	public void stopLevelSelection(){
		AudioPlayer.player.stop(loop2);
	}
	
	public void stopWalk(){
		AudioPlayer.player.stop(loop3);
	}
	
	public boolean soundWalkOn(){
		if(loop3 != null){
			return true;
		}
		return false;
	}
}