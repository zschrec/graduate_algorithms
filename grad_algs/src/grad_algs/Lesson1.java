package grad_algs;

import java.util.Map;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Lesson1 {

  public static void main(String[] args) {
//    boolean testFibSpeed = true;
    boolean testFibSpeed = false;
    boolean testLIS = true;
    if(testFibSpeed) {
      long n = 50;
      long time = System.currentTimeMillis();
      System.out.println("Fib1 value: " + fib1(n));
      System.out.println("Fib1 time: " + (System.currentTimeMillis() - time));

      time = System.currentTimeMillis();
      System.out.println("Fib2 value: " + fib2(n));
      System.out.println("Fib2 time: " + (System.currentTimeMillis() - time));
    }
    
    if(testLIS) {
      Integer[] seq = {5, 7, 4, -3, 9, 1, 10, 4, 5, 8, 9, 3};
      List<Integer> seq_list = Arrays.asList(seq);
      Collections.shuffle(seq_list);
      for(Integer c_int : seq_list) {
        System.out.print(c_int + " ");
      }
      System.out.println();

      System.out.println("Longest increasing subsequence length: " + LIS_length(seq));
      System.out.println("Longest increasing subsequence: " + LIS(seq));
    }

  }
  
  /**
   * Recursive version of Fibonacci nth sequence 
   * @param n
   * @return
   */
  public static long fib1(long n) {
    if(n == 0 || n == 1) {
      return n;
    }
    return fib1(n-1) + fib1(n-2);
  }
  
  /**
   * Dynamic programming version of Fibonacci nth sequence 
   * @param n
   * @return
   */
  public static long fib2(long n) {
    long[] fibNums = new long[(int) (n+1)];
    fibNums[0] = 0;
    fibNums[1] = 1;
    if(n == 0 || n == 1) {
      return n;
    }
    for(int i = 2; i < n+1; ++i) {
      fibNums[i] = fibNums[i-1] + fibNums[i-2];
    }
    return fibNums[(int) n];
  }
  
  /**
   * Get length of longest increasing subsequence
   * @param seq
   * @return
   */
  public static int LIS_length(Integer[] seq) {
    int[] lens = new int[seq.length];
    int largestEntryIndex = 0;
    Map<Integer, int[]> seqs = new HashMap<Integer, int[]>();

    for(int i = 0; i < seq.length; ++i) {
      lens[i] = 1;
      for(int j = 0; j < i; ++j) {
        if(seq[j] < seq[i] && lens[i] < 1 + lens[j]) {
          lens[i] = 1 + lens[j];
          if(lens[i] > lens[largestEntryIndex]) {
            largestEntryIndex = i;
          }
        }

      }
    }
    return lens[largestEntryIndex];
  }
  
  /**
   * Get longest increasing subsequence
   * @param seq
   * @return
   */
  public static List<Integer> LIS(Integer[] seq) {
    int largestEntryKey= 0;
    Map<Integer, List<Integer>> seqs = new HashMap<Integer,List<Integer>>(); 
    int[] lens = new int[seq.length]; 

    for(int i = 0; i < seq.length; ++i) {
      List<Integer> cur_seq = new ArrayList<Integer>(seq.length);
      seqs.put(i, cur_seq);
      lens[i] = 1;
      for(int j = 0; j < i; ++j) {
        if(seq[j] < seq[i] && lens[i] < 1 + lens[j]) {
          cur_seq.clear();
          cur_seq.addAll(seqs.get(j));
          lens[i] = 1 + lens[j];
          if(lens[i] > lens[largestEntryKey]) {
            largestEntryKey = i;
          }
        }
      }
      cur_seq.add(seq[i]);
    }
    return seqs.get(largestEntryKey);
  }

}
