/*
ID: paulcru1
LANG: JAVA
TASK: namenum
*/
import java.io.*;
import java.util.*;

class namenum {
  public static Map<Character,Integer> letters;
  public static TreeSet<String> valid;
  public static void main (String [] args) throws IOException {
    BufferedReader f = new BufferedReader(new FileReader("namenum.in"));
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("namenum.out")));
    BufferedReader names = new BufferedReader(new FileReader("dict.txt"));
    valid = new TreeSet<String>();
    while(names.ready()){
      valid.add(names.readLine());
    }
    letters = getMapping();
    String num = f.readLine();
    removeUseless(num);
    search(num, 0);
    for(String s : valid){
      out.println(s);
    }
    if(valid.size() == 0)
      out.println("NONE");
    out.close();
    System.exit(0);
  }
  public static void removeUseless(String s){
    HashSet<String> nonMatch = new HashSet<>();
    for(String name : valid){
      if(name.length() != s.length()){
        nonMatch.add(name);
      }
    }
    valid.removeAll(nonMatch);
  }
  public static void search(String s, int index){
    if(index >= s.length()){
      return;
    }
    int num = charToInt(s.charAt(index));
    HashSet<String> removedNames = new HashSet<>();
    for(String name : valid){
      int x = letters.get(name.charAt(index));
      if(x != num)
        removedNames.add(name);
    }
    valid.removeAll(removedNames);
    search(s, index+1);
  }
  public static int charToInt(char c){
    return (int)(c-'0');
  }
  public static Map<Character, Integer> getMapping(){
    char[][] sets = {{'A', 'B', 'C'}, {'D', 'E', 'F'}, {'G', 'H', 'I'}, {'J', 'K', 'L'}, {'M', 'N', 'O'}, {'P', 'R', 'S'}, {'T', 'U', 'V'}, {'W', 'X', 'Y'}, {'Q', 'Z'}};
    Map<Character, Integer> map = new HashMap<>();
    for(int i = 2;i <= 10;i++){
      HashSet<Character> chars = new HashSet<>();
      for(char c : sets[i-2])
        map.put(c,i);
    }
    return map;
  }
}