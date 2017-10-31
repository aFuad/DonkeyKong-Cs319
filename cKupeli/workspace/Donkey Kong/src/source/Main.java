package source;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		MapData myMapData = new MapData(1);
		
		for(int y = 0; y < 10; y++){
			for(int x = 0; x < 10; x++){
				System.out.print(myMapData.getMapData(x, y) + " ");
			}
			System.out.println();
		}
		
		ScoreData myScoreData = new ScoreData();
		
		System.out.println("Please enter the new highscore!");
		Scanner scan = new Scanner(System.in);
		int score = scan.nextInt();
		
		System.out.println(myScoreData.getScore());
		myScoreData.setScore(score);
		System.out.println(myScoreData.getScore());
	}
}