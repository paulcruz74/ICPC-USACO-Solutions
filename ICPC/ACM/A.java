import java.io.*;
import java.util.*;

public class A {
  public static void main(String[] args) throws IOException{
    BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(f.readLine());
    while (n != 0) {
      Set<String> insig = new HashSet<>();
      for (int i = 0; i < n; i++) {
        insig.add(f.readLine().toLowerCase());
      }
      String s = f.readLine();
      while (!s.equals("LAST CASE")) {
        solve(insig, s.toLowerCase(), s);
        s = f.readLine();
      }
      n = Integer.parseInt(f.readLine());
    }
  }
  public static void solve(Set<String> insig, String s, String orig) {
    // determine map from index to word 
    int[] wordMap = new int[s.length()];
    int wordCount = 0;
    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) == ' ') {
        wordMap[i] = -1;
        wordCount++;
      }
      else {
        wordMap[i] = wordCount;
      }
    }

    // get rid of insig words
    List<String> words = new ArrayList<>();
    String[] in = s.split("\\s+");
    String abbrev = in[0];
    for (int i = 1; i < in.length; i++) {
      if (!insig.contains(in[i])) {
        words.add(in[i]);
      }
    }


    int[][][] c = new int[300][300][300];
    for (int i = 0; i < words.get(0).length(); i++) {
      if (words.get(0).charAt(i) == abbrev.charAt(0)) {
        c[0][0][i] = 1;
      }
    }
    for (int i = 1; i < abbrev.length(); i++) {
      for (int j = 0; j <= Math.min(i, words.size()-1); j++) {
        for (int k = 0; k < words.get(j).length(); k++) {
          if (words.get(j).charAt(k) == abbrev.charAt(i)) {
            if (j > 0) {
              for (int l = 0; l < words.get(j-1).length(); l++) {
                c[i][j][k] += c[i-1][j-1][l];
              }
            }
            for (int l = 0; l < k; l++) {
              c[i][j][k] += c[i-1][j][l];
            }
          }
        }
      }
    }

    int total = 0;
    for (int idx = 0; idx < words.get(words.size() - 1).length(); idx++) {
      total += c[abbrev.length() - 1][words.size() - 1][idx];
    }
    String abbrevOut = orig.split("\\s+")[0];
    if (total == 0) {
      System.out.printf("%s is not a valid abbreviation\n", abbrevOut);
    }
    else {
      System.out.printf("%s can be formed in %d ways\n", abbrevOut, total);
    }

  }
}