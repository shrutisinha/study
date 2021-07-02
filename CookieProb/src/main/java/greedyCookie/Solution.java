package greedyCookie;

import java.util.Scanner;

public class Solution {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            long a = sc.nextLong();
            long b = sc.nextLong();
            long n = sc.nextLong();
            long m = sc.nextLong();
            if ((a + b) >= (m + n) && m < Math.min(a, b)) {
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }
        }
    }
}
