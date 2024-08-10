package CodinGame_files.Skynet_revolution;

import java.util.*;
import java.io.*;
import java.math.*;

class Nodes{
    public Nodes(int HowManyNodes, int[] OriginalNodes, int[] TargetNodes, int[] GatewayNodes, int HowManyLinks){
        ConnectedNodesSet = new String[HowManyNodes];
        isLinkedWCenter = new boolean[HowManyNodes];
        Centers = new int[HowManyNodes];
        LinksString = new String[HowManyLinks];

        this.GatewayNodes = GatewayNodes;

        int i = 0;
        for(int Target : TargetNodes){
            ConnectedNodesSet[i] = Target + " ";
            ConnectedNodesSet[Target] = OriginalNodes[i] + " ";

            LinksString[i] = OriginalNodes[i] + " " + Target;
            
            for(int Gateway : GatewayNodes){
                System.err.println("d: " + Target + " " + Gateway);
                if(Target == Gateway){
                    Centers[i] = Target;
                }
            } 
            i++;
        }

        ToIntAll();
        SortAll();
    }

    private String[] LinksString;
    
    public void ToIntAll(){
        intConnectedNodesSet = new int[ConnectedNodesSet.length][];
        
        for(int i = 0; i < ConnectedNodesSet.length; i++){
            String[] ArrS = ConnectedNodesSet[i].split(" ");
            intConnectedNodesSet[i] = new int[ArrS.length];
            for(int o = 0; o < ArrS.length; o++){
                intConnectedNodesSet[i][o] = Integer.parseInt(ArrS[o]);
            }
        }
    }
    
    public void SortAll(){
        if(intConnectedNodesSet == null){
            ToIntAll();
        }
        
        int n = 0;
        for(int[] ArrI : intConnectedNodesSet){
            for(int i = 0; i < ArrI.length; i++){
                int Highest = 0;
                int at = 0;
                int High = ArrI.length - (i + 1);
                for(int o = 0; o < ArrI.length - i; o++){
                    if(Highest < ArrI[o]){
                        Highest = ArrI[o];
                        at = o;
                    }
                }
                
                intConnectedNodesSet[n][at] = ArrI[High];
                intConnectedNodesSet[n][High] = Highest;
                ArrI = intConnectedNodesSet[n];
            }
            n++;
        }
    }
    
    public int AtIndexSearch(int index, int Target){
        int res = -1;
        int L, R, M;
        L = 0; R = intConnectedNodesSet[index].length - 1;
    
        while(L <= R){
            M = (int)Math.floor((L+R)/2);
            int Current = intConnectedNodesSet[index][M];
            if(Current < Target){
                L = M + 1;
            }else if(Current > Target){
                R = M - 1;
            }else{
                res = M;
            }
        }
    
        return res;
    }

    public int RandomNodes(){
        int res =  (int)(Math.random() * isLinkedWCenter.length);
        return res;
    }

    public String randomLink(){
        int index = (int)(Math.random() * (LinksString.length - 1));
        return LinksString[index];
    }

    public int GetCenter(int index){return Centers[index];}
    private int[] Centers;

    public boolean isLinkedCenter(int index){return isLinkedWCenter[index];}
    private boolean[] isLinkedWCenter;

    public int GatewayNodesGet(int index){return GatewayNodes[index];}
    private int[] GatewayNodes;

    private int[][] intConnectedNodesSet;
    private String[] ConnectedNodesSet;
}

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Player {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt(); // the total number of nodes in the level, including the gateways
        int L = in.nextInt(); // the number of links
        int E = in.nextInt(); // the number of exit gateways
        System.err.println(N + " " + L + " " + E);

        int[] N1, N2;

        N1 = N2 = new int[L];
        for (int i = 0; i < L; i++) {
            N1[i] = in.nextInt(); // N1 and N2 defines a link between these nodes
            N2[i] = in.nextInt();
        }

        int[] Centers = new int[E];
        for (int i = 0; i < E; i++) {
            int EI = in.nextInt(); // the index of a gateway node

            Centers[i] = EI;
        }

        Nodes Node = new Nodes(N, N1, N2, Centers, L);

        // game loop
        while (true) {
            int SI = in.nextInt(); // The index of the node on which the Skynet agent is positioned this turn

            System.err.println(SI + " " + Node.isLinkedCenter(SI));

            if(Node.isLinkedCenter(SI)){
                System.out.println(SI + " " + Node.GetCenter(SI));
            }else{
                System.out.println(Node.randomLink());
            }
        }
    }
}