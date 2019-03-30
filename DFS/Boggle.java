import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Arrays;

/**
 * Defines the methods needed to play a word search game.
 *
 * @author Jonathan Osborne (jwo0010@auburn.edu)
 * @version 2018-03-22
 * 
 */
public class Boggle implements WordSearchGame {


   private TreeSet<String> lexicon;
   private String[][] board;
   private static final int MAX_NEIGHBORS = 8;
   private int width;
   private int height;
   private boolean[][] visited; 
   private ArrayList<Integer> path;
   private ArrayList<Position> ppath;
   private String wordSoFar;
   private SortedSet<String> allWords;
   
   
   public Boggle() {
      lexicon = null;
      board = new String[4][4];
      board[0][0] = "J"; 
      board[0][1] = "A"; 
      board[0][2] = "V"; 
      board[0][3] = "A"; 
      board[1][0] = "I"; 
      board[1][1] = "S"; 
      board[1][2] = "A"; 
      board[1][3] = "W"; 
      board[2][0] = "E"; 
      board[2][1] = "S"; 
      board[2][2] = "O"; 
      board[2][3] = "M"; 
      board[3][0] = "E"; 
      board[3][1] = "A"; 
      board[3][2] = "A"; 
      board[3][3] = "A";    
      width = board.length;
      height = board[0].length;
      markAllAsUnvisited();
   }
   /**
    * Loads the lexicon into a data structure for later use. 
    * 
    * @param fileName A string containing the name of the file to be opened.
    * @throws IllegalArgumentException if fileName is null
    * @throws IllegalArgumentException if fileName cannot be opened.
    */
   public void loadLexicon(String fileName) {
      if (fileName == null) {
         throw new IllegalArgumentException();
      }
      try {
         lexicon = new TreeSet<String>();
         Scanner scanFile = new Scanner(new File(fileName));
         while (scanFile.hasNext()) {
            lexicon.add(scanFile.next().toUpperCase());
            scanFile.nextLine();
         }
         scanFile.close();
      }
      
      catch (FileNotFoundException e) {
         throw new IllegalArgumentException();
      }
   }
   
   /**
    * Stores the incoming array of Strings in a data structure that will make
    * it convenient to find words.
    * 
    * @param letterArray This array of length N^2 stores the contents of the
    *     game board in row-major order. Thus, index 0 stores the contents of board
    *     position (0,0) and index length-1 stores the contents of board position
    *     (N-1,N-1). Note that the board must be square and that the strings inside
    *     may be longer than one character.
    * @throws IllegalArgumentException if letterArray is null, or is  not
    *     square.
    */
   public void setBoard(String[] letterArray) {
      if (letterArray == null) {
         throw new IllegalArgumentException();
      }
      
      int n = (int)Math.sqrt(letterArray.length);
      
      if ((n * n) != letterArray.length) {
         throw new IllegalArgumentException();
      }
      
      board = new String[n][n];
      width = n;
      height = n;
      int index = 0;
      for (int i = 0; i < height; i++) {
         for (int j = 0; j < width; j++) {
            board[i][j] = letterArray[index];
            index++;
         }
      }
      markAllAsUnvisited(); 
   }
   
   /**
    * Creates a String representation of the board, suitable for printing to
    *   standard out. Note that this method can always be called since
    *   implementing classes should have a default board.
    */
   public String getBoard() {
      String output = "";
      for (int i = 0; i < height; i++) {
         if (i > 0) {
            output += "\n";
         }
         for (int j = 0; j < width; j++) {
            output += board[i][j] + " ";
         }
      }
      return output;
   }
   
   /**
    * Retrieves all valid words on the game board, according to the stated game
    * rules.
    * 
    * @param minimumWordLength The minimum allowed length (i.e., number of
    *     characters) for any word found on the board.
    * @return java.util.SortedSet which contains all the words of minimum length
    *     found on the game board and in the lexicon.
    * @throws IllegalArgumentException if minimumWordLength < 1
    * @throws IllegalStateException if loadLexicon has not been called.
    */
   public SortedSet<String> getAllValidWords(int minimumWordLength) {
      if (lexicon == null) {
         throw new IllegalStateException();
      }
      
      if (minimumWordLength < 1) {
         throw new IllegalArgumentException();
      }
      
      ppath = new ArrayList<Position>();
      allWords = new TreeSet<String>();
      wordSoFar = "";
      for (int i = 0; i < height; i++) {
         for (int j = 0; j < width; j ++) {
            wordSoFar = board[i][j];
            if (isValidWord(wordSoFar) && wordSoFar.length() >= minimumWordLength) {
               allWords.add(wordSoFar);
            }
            if (isValidPrefix(wordSoFar)) {
               Position temp = new Position(i, j);
               ppath.add(temp);
               dfs2(i, j, minimumWordLength); 
               ppath.remove(temp);
            }
         }
      }
      return allWords;
   }
   
   /**
   * Computes the cummulative score for the scorable words in the given set.
   * To be scorable, a word must (1) have at least the minimum number of characters,
   * (2) be in the lexicon, and (3) be on the board. Each scorable word is
   * awarded one point for the minimum number of characters, and one point for 
   * each character beyond the minimum number.
   *
   * @param words The set of words that are to be scored.
   * @param minimumWordLength The minimum number of characters required per word
   * @return the cummulative score of all scorable words in the set
   * @throws IllegalArgumentException if minimumWordLength < 1
   * @throws IllegalStateException if loadLexicon has not been called.
   */
   public int getScoreForWords(SortedSet<String> words, int minimumWordLength) {
      if (minimumWordLength < 1) {
         throw new IllegalArgumentException();
      }
      if (lexicon == null) {
         throw new IllegalStateException();
      }
      
      
      int score = 0;
      Iterator<String> myIt = words.iterator();
      while (myIt.hasNext()) {
         String word = myIt.next();
         if (word.length() >= minimumWordLength && isValidWord(word)
             && !isOnBoard(word).isEmpty()) {
            
            score += (word.length() - minimumWordLength) + 1;
         }
      }
      return score;
   }
   
   /**
    * Determines if the given word is in the lexicon.
    * 
    * @param wordToCheck The word to validate
    * @return true if wordToCheck appears in lexicon, false otherwise.
    * @throws IllegalArgumentException if wordToCheck is null.
    * @throws IllegalStateException if loadLexicon has not been called.
    */
   public boolean isValidWord(String wordToCheck) {
      return lexicon.contains(wordToCheck.toUpperCase());
   }
   
   /**
    * Determines if there is at least one word in the lexicon with the 
    * given prefix.
    * 
    * @param prefixToCheck The prefix to validate
    * @return true if prefixToCheck appears in lexicon, false otherwise.
    * @throws IllegalArgumentException if prefixToCheck is null.
    * @throws IllegalStateException if loadLexicon has not been called.
    */
   public boolean isValidPrefix(String prefixToCheck) {
      /*Iterator lexIt = new TreeSet<String>.iterator<TreeSet<String>>();
     
      while (lexIt.hasNext()) {
         String elem = lexIt.next();
         if (elem.startsWith(prefixToCheck)) {
            return true;
         }
      }
      return false;*/
      
      String ceiling = lexicon.ceiling(prefixToCheck);
      if (ceiling == null) {
         return false;
      }
      return ceiling.startsWith(prefixToCheck);
   }
   /**
    * Determines if the given word is in on the game board. If so, it returns
    * the path that makes up the word.
    * @param wordToCheck The word to validate
    * @return java.util.List containing java.lang.Integer objects with  the path
    *     that makes up the word on the game board. If word is not on the game
    *     board, return an empty list. Positions on the board are numbered from zero
    *     top to bottom, left to right (i.e., in row-major order). Thus, on an NxN
    *     board, the upper left position is numbered 0 and the lower right position
    *     is numbered N^2 - 1.
    * @throws IllegalArgumentException if wordToCheck is null.
    * @throws IllegalStateException if loadLexicon has not been called.
    */
   public List<Integer> isOnBoard(String wordToCheck) {
      if (wordToCheck == null) {
         throw new IllegalArgumentException();
      }
      if (lexicon == null) {
         throw new IllegalStateException();
      }
      
      
      
      ppath = new ArrayList<Position>();
      wordToCheck = wordToCheck.toUpperCase();
      wordSoFar = "";
      path = new ArrayList<Integer>();
   
      for (int i = 0; i < height; i++) {
         for (int j = 0; j < width; j ++) {
         
            if (wordToCheck.equals(board[i][j])) {
               path.add(i * width + j); 
               return path;
            }
            if (wordToCheck.startsWith(board[i][j])) {
               Position pos = new Position(i, j);
               ppath.add(pos); 
               wordSoFar = board[i][j]; 
               dfs(i, j, wordToCheck);
               if (!wordToCheck.equals(wordSoFar)) {
                  ppath.remove(pos);
               }
               else {
                  for (Position p: ppath) {
                     path.add((p.x * width) + p.y);
                  } 
                  return path;
               }
            }
         }
      }
      return path;
   }
   
   private void dfs(int x, int y, String wordToCheck) {
      Position start = new Position(x, y);
      markAllAsUnvisited();
      markPathVisited(); 
      for (Position p: start.neighbors()) {
         if (!isVisited(p)) {
            visit(p);
            if (wordToCheck.startsWith(wordSoFar + board[p.x][p.y])) {
               wordSoFar += board[p.x][p.y];
               ppath.add(p);
               dfs(p.x, p.y, wordToCheck);
               if (wordToCheck.equals(wordSoFar)) {
                  return;
               }
               else {
                  ppath.remove(p);
               
                  int endIndex = wordSoFar.length() - board[p.x][p.y].length();
                  wordSoFar = wordSoFar.substring(0, endIndex);
               }
            }
         }
      }
      markAllAsUnvisited(); 
      markPathVisited();
   }
   
   private void markAllAsUnvisited() {
      visited = new boolean[width][height];
      for (boolean[] row : visited) {
         Arrays.fill(row, false);
      }
   }
   
   private void markPathVisited() {
      for (int i = 0; i < ppath.size(); i ++) {
         visit(ppath.get(i));
      }
   }
   
   private class Position {
      int x;
      int y;
   
      public Position(int x, int y) {
         this.x = x;
         this.y = y;
      }
      
      
      public String toString() {
         return "(" + x + ", " + y + ")";
      }
      
      public Position[] neighbors() {
         Position[] nbrs = new Position[MAX_NEIGHBORS];
         int count = 0;
         Position p;
         for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
               if (!((i == 0) && (j == 0))) {
                  p = new Position(x + i, y + j);
                  if (isValid(p)) {
                     nbrs[count++] = p;
                  }
               }
            }
         }
         return Arrays.copyOf(nbrs, count);
      }
   }
   
   private boolean isValid(Position p) {
      return (p.x >= 0) 
         && (p.x < width) 
         && (p.y >= 0) 
         && (p.y < height);
   }
   
   private boolean isVisited(Position p) {
      return visited[p.x][p.y];
   }
   
   private void visit(Position p) {
      visited[p.x][p.y] = true;
   }
   
   private void dfs2(int x, int y, int min) {
      Position start = new Position(x, y);
      markAllAsUnvisited(); 
      markPathVisited(); 
      for (Position p : start.neighbors()) {
         if (!isVisited(p)) {
            visit(p);
            if (isValidPrefix(wordSoFar + board[p.x][p.y])) {
               wordSoFar += board[p.x][p.y];
               ppath.add(p);
               if (isValidWord(wordSoFar) && wordSoFar.length() >= min) {
                  allWords.add(wordSoFar);
               }
               dfs2(p.x, p.y, min);
               
               ppath.remove(p);
               int endIndex = wordSoFar.length() - board[p.x][p.y].length();
               wordSoFar = wordSoFar.substring(0, endIndex);
            }
         }
      }
      markAllAsUnvisited();
      markPathVisited(); 
   }
}