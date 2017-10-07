import java.io.*;
import java.util.*;

public class J {
  public static Map<Character, Integer> v;
  public static Map<Integer, Character> vInv;
  public static void main(String [] args) throws IOException {
    BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
    initV();
    int n = Integer.parseInt(f.readLine());
    for (int i = 0; i < n; i++) {
      String s = f.readLine();
      StringTokenizer st = new StringTokenizer(s);
      if (st.nextToken().equals("e")) {
        System.out.println(encrypt(s.substring(2)));
      }
      else {
        System.out.println(decrypt(s.substring(2)));
      }
    }
  }
  public static String encrypt(String s) {
    StringBuilder t = new StringBuilder();
    int[] vals = new int[s.length()];
    for (int i = 0; i < s.length(); i++) {
      vals[i] = v.get(s.charAt(i));
      if (i > 0) 
        vals[i] += vals[i-1];
      vals[i] %= 27;
      t.append(vInv.get(vals[i]));
    }
    return t.toString();
  }
  public static String decrypt(String s) {
    StringBuilder t = new StringBuilder();
    int[] vals = new int[s.length()];
    for (int i = 0; i < s.length(); i++) {
      vals[i] = v.get(s.charAt(i));
      if (i > 0) {
        int x = (vals[i] - vals[i-1] + 27) % 27;
        t.append(vInv.get(x));
      }
      else {
        t.append(vInv.get(vals[i]));
      }
    }
    return t.toString();
  }
  public static void initV() {
    v = new HashMap<>();
    vInv = new HashMap<>();
    v.put(' ', 0);
    vInv.put(0, ' ');
    for (int i = (int)'a'; i <= (int)'z'; i++) {
      v.put((char)i, i - 'a' + 1);
      vInv.put(i - 'a' + 1, (char)i);
    }
    // System.out.println(v.keySet());
  }
}