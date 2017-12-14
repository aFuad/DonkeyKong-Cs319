package source;

import java.io.File;
import java.util.Scanner;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class ScoreData {
   private Scanner input = new Scanner(System.in);
   private String fileName = "src/data/score.txt";
   private Integer[] scores = new Integer[5];
   private String[] names = new String[5];
   
   public ScoreData() throws IOException{
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
   
   public void addNewScore(String name, int newScore) throws IOException{
      
      int index = 0;
      
      if(newScore >= scores[0]){
         for(int i = 0; i < scores.length; i++){
            if(newScore >= scores[i])
               index = i;
         }
         
         //swap scores
         for(int j = 0; j < index; j++){
            scores[j] = scores[j + 1];
            names[j] = names[j + 1];
         }
         
         scores[index] = newScore;
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