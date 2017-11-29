package soundEffect;
import sun.audio.*;
import java.io.*;

public class SoundEffect {
	String walkSound = "/Users/Fuad/Desktop/soundEffectDK/walking.wav";//these addreses should be modified
	String jumpSound = "/Users/Fuad/Desktop/soundEffectDK/jump.wav";
	String introSound = "/Users/Fuad/Desktop/soundEffectDK/intro1.wav";
	String gettingItemSound = "/Users/Fuad/Desktop/soundEffectDK/itemGet.wav";
	String hammerSound = "/Users/Fuad/Desktop/soundEffectDK/hammer.wav";
	String loseSound = "/Users/Fuad/Desktop/soundEffectDK/death.wav";
	String winSound = "/Users/Fuad/Desktop/soundEffectDK/win1.wav";
	String backgroundSound = "/Users/Fuad/Desktop/soundEffectDK/bacmusic.wav";
	
	boolean enable = true;
	
	public void disableSound(){
		enable=false;
	}
	
	public void enableSound(){
		enable=true;
	}
	
	public void walk(){
		if(enable){
			try{
				InputStream ws = new FileInputStream(walkSound);
				AudioStream audioStream = new AudioStream(ws);
				AudioPlayer.player.start(audioStream);
			}catch(IOException a){
				System.out.println("Not Found");
			}
		}
	}
	
	public void jump(){
		if(enable){
			try{
				InputStream ws = new FileInputStream(jumpSound);
				AudioStream audioStream = new AudioStream(ws);
				AudioPlayer.player.start(audioStream);
			}catch(IOException a){
				System.out.println("Not Found");
			}
		}
	}
	
	public void intro(){
		if(enable){
			try{
				InputStream ws = new FileInputStream(introSound);
				AudioStream audioStream = new AudioStream(ws);
				AudioPlayer.player.start(audioStream);
			}catch(IOException a){
				System.out.println("Not Found");
			}
		}
	}
	
	public void getNewItem(){
		if(enable){
			try{
				InputStream ws = new FileInputStream(gettingItemSound);
				AudioStream audioStream = new AudioStream(ws);
				AudioPlayer.player.start(audioStream);
			}catch(IOException a){
				System.out.println("Not Found");
			}
		}
	}
	
	public void hammer(){
		if(enable){
			try{
				InputStream ws = new FileInputStream(hammerSound);
				AudioStream audioStream = new AudioStream(ws);
				AudioPlayer.player.start(audioStream);
			}catch(IOException a){
				System.out.println("Not Found");
			}
		}
	}
	
	public void lose(){
		if(enable){
			try{
				InputStream ws = new FileInputStream(loseSound);
				AudioStream audioStream = new AudioStream(ws);
				AudioPlayer.player.start(audioStream);
			}catch(IOException a){
				System.out.println("Not Found");
			}
		}
	}
	
	public void win(){
		if(enable){
			try{
				InputStream ws = new FileInputStream(winSound);
				AudioStream audioStream = new AudioStream(ws);
				AudioPlayer.player.start(audioStream);
			}catch(IOException a){
				System.out.println("Not Found");
			}
		}
	}
	
	public void background(){
		if(enable){
			try{
				InputStream ws = new FileInputStream(backgroundSound);
				AudioStream audioStream = new AudioStream(ws);
				AudioData a = audioStream.getData();
				ContinuousAudioDataStream loop = new ContinuousAudioDataStream(a);
				AudioPlayer.player.start(loop);
			}catch(IOException a){
				System.out.println("Not Found");
			}
		}
	}
}
