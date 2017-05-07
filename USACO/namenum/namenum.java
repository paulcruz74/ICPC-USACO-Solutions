/*
ID: paulcru1
LANG: JAVA
TASK: namenum
*/
import java.io.*;
import java.util.*;

class namenum {
  public static Map<Integer, HashSet<Character>> letters;
  public static TreeSet<String> possibleNames;
  public static HashSet<String> valid;
  public static void main (String [] args) throws IOException {
    BufferedReader f = new BufferedReader(new FileReader("namenum.in"));
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("namenum.out")));
    BufferedReader names = new BufferedReader(new FileReader("dict.txt"));
    valid = new HashSet<String>();
    while(names.ready()){
      valid.add(names.readLine());
    }
    letters = getMapping();
    possibleNames = new TreeSet<>();
    String num = f.readLine();
    search(num, "");
    for(String s : possibleNames){
      out.println(s);
    }
    out.close();
    System.exit(0);
  }
  public static void search(String s, String accum){
    if(s.equals("")){
      if(valid.contains(accum)){
        possibleNames.add(accum);
      }
      return;
    }
    int num = charToInt(s.charAt(0));
    String remain = s.substring(1);
    for(char x : letters.get(num)){
      search(remain, accum + x);
    }
  }
  public static int charToInt(char c){
    return (int)(c-'0');
  }
  public static Map<Integer, HashSet<Character>> getMapping(){
    char[][] sets = {{'A', 'B', 'C'}, {'D', 'E', 'F'}, {'G', 'H', 'I'}, {'J', 'K', 'L'}, {'M', 'N', 'O'}, {'P', 'R', 'S'}, {'T', 'U', 'V'}, {'W', 'X', 'Y'}};
    HashMap<Integer, HashSet<Character>> map = new HashMap<>();
    for(int i = 2;i <= 9;i++){
      HashSet<Character> chars = new HashSet<>();
      for(char c : sets[i-2])
        chars.add(c);
      map.put(i, chars);
    }
    return map;
  }
}