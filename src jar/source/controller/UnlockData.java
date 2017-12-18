package source.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class UnlockData {
	private int unlock;
	private Scanner unlockScanner;
	
	public UnlockData() throws FileNotFoundException{		
		if(new File(System.getProperty("user.home"), "Desktop/unlock.txt").isFile()){
			unlockScanner = new Scanner(new File(System.getProperty("user.home"), "Desktop/unlock.txt"));
		}
		else if(new File(System.getProperty("user.home") + File.separator + "Desktop" + File.separator + "unlock.txt").isFile()){
			unlockScanner = new Scanner(new File(System.getProperty("user.home") + File.separator + "Desktop" + File.separator + "unlock.txt"));
		}
		else{
			unlockScanner = new Scanner(getClass().getResourceAsStream("/data/unlock.txt"));
		}
		
		unlock = unlockScanner.nextInt();
	}
	
	public int getUnlock(){
		return unlock;
	}
	
	public void setUnlock(int unlock){
		if(unlock > this.unlock){
			this.unlock = unlock;
			
			//Write the text file
			BufferedWriter writer = null;
			try {
				File unlockFile = null;
				
				if(isWindows()){
					unlockFile = new File(System.getProperty("user.home"), "Desktop/unlock.txt");
				}
				else if(isMac()){
					unlockFile = new File(System.getProperty("user.home") + File.separator + "Desktop" + File.separator + "unlock.txt");
				}
				
				if(unlockFile != null){
					writer = new BufferedWriter(new FileWriter(unlockFile));
					writer.write(Integer.toString(unlock));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private boolean isWindows(){
		if(System.getProperty("os.name").toLowerCase().contains("windows")){
			return true;
		}
		return false;
	}
	
	private boolean isMac(){
		if(System.getProperty("os.name").toLowerCase().contains("mac")){
			return true;
		}
		return false;
	}
}
