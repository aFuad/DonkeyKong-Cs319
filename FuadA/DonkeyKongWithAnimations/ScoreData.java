package source;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ScoreData {
   private String fileName = "src/data/score.txt";
   private Integer[] scores = new Integer[5];
   private String[] names = new String[5];
   
   public ScoreData(){
      readFile(fileName, scores, names);
   }
   
   
   private void readFile(String fileName, Integer[] scores, String[] names){
      int index = 0;
      try (Scanner textReader = new Scanner(new File(fileName));)
      {
         while(textReader.hasNext())
         {
            String namesLine, scoresLine;
            String line = textReader.nextLine();
            
            scoresLine = line.replaceAll("[^0-9]+", " ");
            namesLine = line.replaceAll("[^-a-zA-Z]+", " ");
            scoresLine = scoresLine.trim();
            namesLine = namesLine.trim();
            
            scores[index] = Integer.parseInt(scoresLine);
            names[index] = namesLine;
            index++;
         }
        
         textReader.close();
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
   }
   
   public void setHighscore(String name, int score) throws IOException{
      
      int index = 0;
      
      if(score >= scores[0]){
         for(int i = 0; i < scores.length; i++){
            if(score >= scores[i])
               index = i;
         }
         
         //swap scores
         for(int j = 0; j < index; j++){
            scores[j] = scores[j + 1];
            names[j] = names[j + 1];
         }
         
         scores[index] = score;
         names[index] = name;
         
         writeFile(fileName, scores, names);
      }
   }
   
   private void writeFile(String fileName,Integer[] scores, String[] names) throws IOException{
      BufferedWriter textWriter = new BufferedWriter(new FileWriter(fileName));
      
      for(int i = 0; i < scores.length; i++){
         textWriter.write(names[i] + " " + scores[i]);
         textWriter.newLine();
      }
      
      textWriter.close();
   }
    
   public int getScore(int i){
      return scores[i];
   }
   
   public String getName(int i){
      return names[i];
   }  
}