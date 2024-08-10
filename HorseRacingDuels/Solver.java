package CodinGame_files.HorseRacingDuels;

import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int[] HorsePower = new int[N];
        for (int i = 0; i < N; i++) {
            HorsePower[i] = in.nextInt();
        }

        HorsePower = intSimpleSort(HorsePower);

        System.out.println(searchClosest(HorsePower));
    }

    static int[] intSimpleSort(int[] input){
        int[] res = input;
        for(int i = 0; i < input.length; i++){
            int nHighest, nH, atBefore;
            nHighest = atBefore = nH = 0;
            for(int o = 0; o < input.length - i; o++){
                if(nHighest < res[o]){
                    nHighest = res[o];
                    nH = o;
                }
            }

            atBefore = res[input.length - (i + 1)];
            res[input.length - (i + 1)] = nHighest;
            res[nH] = atBefore;
        }
        return res;
    }

    static int searchClosest(int[] input){
        int res = 10;
        for(int i = 1; i < input.length; i++){
            int CurrentRes = input[i] - input[i -1];
            if(CurrentRes < res) res = CurrentRes;
        }
        return res;
    }
}