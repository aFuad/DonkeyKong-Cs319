package source;

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
		unlockScanner = new Scanner(new File("src/data/unlock.txt"));
		unlock = unlockScanner.nextInt();
	}
	
	public int getUnlock(){
		return unlock;
	}
	
	public void setUnlock(int unlock){
		this.unlock = unlock;
		
		//Write the text file
		BufferedWriter writer = null;
		try {
			File unlockFile = new File("src/data/unlock.txt");
			
			writer = new BufferedWriter(new FileWriter(unlockFile));
			writer.write(Integer.toString(unlock));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
