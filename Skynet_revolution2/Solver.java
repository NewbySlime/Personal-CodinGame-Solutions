import java.util.*;
import java.io.*;
import java.math.*;

class nodes{
    private String[] NodesStr;
    private int[][] NodesInt;
    private int[] NodesExit;
    private boolean[] IsConnected;
    private boolean[] IsChecked;
    private List<String> ExitStr = new ArrayList<String>();

    public nodes(int[] origin, int[] target, int ManyNode, int[] ExitNode){
        NodesStr = new String[ManyNode];
        NodesInt = new int[ManyNode][0];
        NodesExit = ExitNode;
        IsConnected = new boolean[ManyNode];
        IsChecked = new boolean[ManyNode];

        for(int i = 0; i < origin.length; i++){
            int Or = origin[i];
            int Trgt = target[i];

            NodesStr[Or] += " " + Trgt;
            NodesStr[Trgt] += " " + Or;
            
            for(int n: NodesExit){
                if(n == Trgt){
                    ExitStr.add(new String(Or + " " + Trgt));
                    IsConnected[Or] = true;
                }else if(n == Or){
                    ExitStr.add(new String(Trgt + " " + Or));
                    IsConnected[Trgt] = true;
                }
            }
        }

        toInt();
        NodesStr = null;
    }

    private void toInt(){
        for(int i = 0; i < NodesStr.length; i++){
            //System.err.println(NodesStr[i]);
            String[] IntStr = NodesStr[i].split(" ");
            NodesInt[i] = new int[IntStr.length];

            for(int o = 1; o < IntStr.length; o++){
                //System.err.println(IntStr.length + " " + IntStr[o]);
                NodesInt[i][o] = Integer.parseInt(IntStr[o]);
            }
        }
    }

    private String QueueStr;
    List<String> PathFind(int AgentNode){
        //System.err.println("PathFinding");
        IsChecked = new boolean[IsChecked.length];
        QueueStr = new String();
        if(IsConnected[AgentNode]){
            QueueStr = "" + AgentNode;
        }else{
            for(int n: NodesInt[AgentNode]){
                QueueStr += n + " ";
            }
        }

        ListStr = new ArrayList<String>();
        int i = 0;
        int x = 0;
        while(i == 0){
            i = StartQueue();
            //System.err.println(i);
            x++;
        }

        if(x < i){
            int ListSize = ListStr.size();
            while(ListSize > 1){
                ListStr.remove(ListSize-1);
                ListSize = ListStr.size();
            }
        }


        for(String s: ListStr){
            System.err.println(s);
            String[] Ss = s.split(" ");
            IsConnected[Integer.parseInt(Ss[0])] = false;
        }
        return ListStr;
    }

    private List<String> ListStr;
    private int StartQueue(){
        int res = 0;
        String[] IntStrs = QueueStr.split(" ");
        QueueStr = new String();
        int[] Ints = new int[IntStrs.length];

        for(int i = 0; i < IntStrs.length; i++){
            Ints[i] = Integer.parseInt(IntStrs[i]);
        }
        IntStrs = null;

        for(int n: Ints){
            if(IsChecked[n]){
                continue;
            }else if(IsConnected[n]){
                for(int m : NodesInt[n]){
                    for(int c: NodesExit){
                        if(c == m){
                            ListStr.add(new String(n + " " + c));
                            res++;
                        }
                    }
                }
            }

            IsChecked[n] = true;
        }

        return res;
    }
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
        int[] N1, N2, EI;
        N1 = new int[L];
        N2 = new int[L];
        for (int i = 0; i < L; i++) {
            N1[i] = in.nextInt(); // N1 and N2 defines a link between these nodes
            N2[i] = in.nextInt();
        }

        EI = new int[E];
        for (int i = 0; i < E; i++) {
            EI[i] = in.nextInt(); // the index of a gateway node
        }

        nodes Node = new nodes(N1, N2, N, EI);

        int SI = in.nextInt();
        // game loop
        while (true) {
            List<String> ListStr = Node.PathFind(SI);
            for(String s: ListStr){
                System.out.println(s);
                SI = in.nextInt();
            }
        }
    }
}