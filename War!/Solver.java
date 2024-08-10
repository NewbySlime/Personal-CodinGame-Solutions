import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {
    static char[] HighSort = {'2', '3', '4', '5', '6', '7', '8', '9', '1', 'J', 'Q', 'K', 'A'};
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // the number of cards for player 1
        String cardp1 = new String(); // the n cards of player 1
        for (int i = 0; i < n; i++) {
            cardp1 += " " + in.next();
        }
        int m = in.nextInt(); // the number of cards for player 2
        System.err.println(cardp1);
        String cardp2 = new String(); // the m cards of player 2
        for (int i = 0; i < m; i++) {
            cardp2 += " " + in.next();
        }
        System.err.println(cardp2);

        int x = 0;
        List<String> ListStr = new ArrayList<String>();
        while(cardp1 != null || cardp2 != null){
            int p1 = 0;
            int p2 = 1;
			int w = 10;
            
            int BattleState = Battle(cardp1, cardp2);
            switch(BattleState){
                case 1:
                ListStr = Swap(cardp2, cardp1, 1);
                p1 = 1; p2 = 0;
                break;
                case 0:
                w = War(cardp1, cardp2);
                break;
                case -1:
                ListStr = Swap(cardp1, cardp2, 1);
                break;
            }
            
            cardp1 = ListStr.get(p1);
            cardp2 = ListStr.get(p2);
            x++;
            if(w != 10){
				switch(w){
					case 1:
					cardp2 = null;
					break;
					case 0:
					System.out.println("PAT");
					return;
					case -1:
					cardp1 = null;
					break;
				}
			}
        }

        if(cardp1 == null){
            System.out.println("2 " + x);
        } 
        if(cardp2 == null){
            System.out.println("1 " + x);
        }
    }

    static int Battle(String P1Card, String P2Card){ //1: p1 > p2
        char[] P1P2 = {P1Card.charAt(0), P2Card.charAt(0)};
        int[] P1P2Int = new int[2];
        
        for(int i = 0; i < P1P2.length; i++){
        for(int o = 0; o < HighSort.length; o++){
            if(P1P2[i] == HighSort[o]){
                P1P2Int[i] = o + 1;
            }
        }
        }

        if(P1P2Int[0] > P1P2Int[1]) return 1;
        else if(P1P2Int[0] == P1P2Int[1]) return 0;
        else return -1;
    }

    static int War(String P1, String P2){
        String[] P1Arr = P1.split(" ");
		String[] P2Arr = P2.split(" ");
		
		int n = 4;
		int Res = Battle(P1Arr[n], P2Arr[n]);
		while(Res == 0){
			if(n > P1Arr.length || P2Arr.length < n) return 0;
			n += 3;
			String P1C, P2C;
			P1C = new String();
			P2C = new String();
			if(P1Arr.length < n){
				P1C = P1Arr[P1Arr.length - 1];
			}else{
				P1C = P1Arr[n];
			}if(P2Arr.length < n){
				P2C = P2Arr[P2Arr.length - 1];
			}else{
				P2C = P2Arr[n];
			}
			
			Res = Battle(P1C, P2C);
		}
		
		return Res;
    }

    static List<String> Swap(String Target, String WhereTo, int HowMany){
        String[] TargetArr = Target.split(" ");
        String newTarget = new String();
        String newWhereTo = WhereTo;

        for(int i = 0; i < HowMany && i < TargetArr.length; i++){
            newWhereTo += " " + TargetArr[i];
        }
        
        for(int i = HowMany; i < TargetArr.length; i++){
            newTarget += " " + TargetArr[i];
        }

        if(TargetArr.length < HowMany) newTarget = null;

        List<String> ListStr = new ArrayList<String>();
        ListStr.add(newTarget);
        ListStr.add(newWhereTo);

        return ListStr;
    }
}