package source.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ScoreData {
   private Scanner textReader;
   private ArrayList<Integer> scores;
   private ArrayList<String> names;
   
   public ScoreData() throws FileNotFoundException{
	  textReader = new Scanner(new File("src/data/score.txt"));
	  scores = new ArrayList<Integer>();
	  names = new ArrayList<String>();
	  
	  readFile(scores, names);
   }
   
   
   private void readFile(ArrayList<Integer> scores, ArrayList<String> names){
	   while(textReader.hasNext()){
		   String namesLine, scoresLine;
           String line = textReader.nextLine();
           
           scoresLine = line.replaceAll("[-A-Z]+[0-9]*", " ");
           namesLine = line.substring(0, 5);
           scoresLine = scoresLine.trim();
           namesLine = namesLine.trim();
           
           scores.add(Integer.parseInt(scoresLine));
           names.add(namesLine);
	   }
       
       textReader.close();
   }
   
   public void setHighscore(String name, int score) throws IOException{
      
      int index = 0;
      
      if(score >= scores.get(0)){
         for(int i = 0; i < scores.size(); i++){
            if(score >= scores.get(i))
               index = i;
         }
         
         //swap scores
         for(int j = 0; j < index; j++){
            scores.set(j, scores.get(j + 1));
            names.set(j, names.get(j + 1));
         }
         
         scores.set(index, score);
         names.set(index, name);
         
         writeFile(scores, names);
      }
   }
   
   private void writeFile(ArrayList<Integer> scores, ArrayList<String> names) throws IOException{
      BufferedWriter textWriter = new BufferedWriter(new FileWriter("src/data/score.txt"));
      
      for(int i = 0; i < scores.size(); i++){
         textWriter.write(names.get(i) + " " + scores.get(i));
         textWriter.newLine();
      }
      
      textWriter.close();
   }
    
   public int getScore(int i){
      return scores.get(i);
   }
   
   public String getName(int i){
      return names.get(i);
   }  
}