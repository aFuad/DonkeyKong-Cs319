package source;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ScoreData {
	private int score;
	
	
	public ScoreData() throws FileNotFoundException{
		Scanner scoreScanner = new Scanner(new File("src/data/score.txt"));
		score = scoreScanner.nextInt();
	}
	
	public int getScore(){
		return score;
	}
	
	public void setScore(int score){
		this.score = score; //During runtime in order not to create another score object, we also update local score
		
		//Write the text file
		BufferedWriter writer = null;
		try {
			File scoreFile = new File("src/data/score.txt");
			
			writer = new BufferedWriter(new FileWriter(scoreFile));
			writer.write(Integer.toString(score));
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
