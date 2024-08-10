package CodinGame_files.Game_Of_A_Go;
import java.util.*;
import java.io.*;
import java.lang.ProcessBuilder.Redirect.Type;
import java.math.*;
/*ErrorList:
    Task4:
    "W 2 0" is flipped between x and y
 */

class vect2{
    public vect2(int x, int y){
        X = x; Y = y;
    }

    public vect2 Subtitute(vect2 one){
        vect2 res = new vect2(one.X+X, one.Y+Y);
        return res;
    }
    public int X,Y;
}

class RockType{
    public RockType(vect2 Pos, char Type){
        PosSet = Pos;
        TypeSet = Type;
    }

    public boolean isChecked = false;

    private vect2 PosSet;
    public vect2 CurrentPos(){return PosSet;}

    private char TypeSet;
    public char CurrentType(){return TypeSet;}
}


public class GameOfAGo_Solver {
    static int[] Manipulator = {1,0,-1,0};
    static int[] Ss = {5,5,5,5,5,5,9}; static int S = 4;
    static int[] Ms = {5,7,1,5,7,3,10}; static int M = 4;
    static String[] Moves = {
        "B 3 4",
"W 0 2",
"B 0 3",
"W 0 0",
"B 1 2",
"W 0 1",
"B 1 0"
    };

    static List<vect2> RockLists = new ArrayList<vect2>();
    static List<vect2> CheckLists = new ArrayList<vect2>();

    static String[] BoardSet = {
        ".B...",
"BW...",
"WB..W",
"...W.",
"...BW"
    };

    static RockType[][] Rocks; 
    static boolean[][] isUsed;

    public static void main(String[] args) {
        S = Ss[S];
        M = Ms[M];

        Rocks = new RockType[S][S];
        isUsed = new boolean[S][S];

        for(int i = 0; i < S; i++){ //preparing the playspace
            char[] rows = BoardSet[i].toCharArray();
            for (int o = 0; o < S; o++){
                if(rows[o] == '.'){
                    Rocks[o][i] = null;
                    isUsed[o][i] = false;
                }else{
                    Rocks[o][i] = new RockType(new vect2(o,i), rows[o]);
                    isUsed[o][i] = true;
                }
            }
        }

        for(int i = 0; i < M; i++){
            char[] move = Moves[i].toCharArray();
            vect2 newPos = new vect2(Character.getNumericValue(move[2]), Character.getNumericValue(move[4])); 

            
            System.out.println("\n" + move[0] + " " + newPos.X + " " + newPos.Y);
            if(isUsed[newPos.X][newPos.Y]){
                System.out.println("NOT_VALID");
                continue;
            }
            
            Rocks[newPos.X][newPos.Y] = new RockType(newPos, move[0]);
            isUsed[newPos.X][newPos.Y] = true;
            
            checkSelf(newPos);
            for (vect2 CheckedPos : CheckLists) {
                RockType Rock = Rocks[CheckedPos.X][CheckedPos.Y];
                if(Rock != null){
                    Rocks[CheckedPos.X][CheckedPos.Y].isChecked = false;
                }
            }
            
            for(int y = 0; y < S; y++){
                String s = "";
                for(int x = 0; x < S; x++){
                    if(Rocks[x][y] == null) s += ".";
                    else s += Rocks[x][y].CurrentType(); 
                }
                System.out.println(s);
            }
        }
    }

    static void checkSelf(vect2 Pos){
        //check surrounding first, then check another rock
        
        System.out.println("d: checking from " + Pos.X + " " + Pos.Y);
        char Type = Rocks[Pos.X][Pos.Y].CurrentType();
        RockLists.add(Pos);
        switch(Type){ //regular
            case 'B':
                Type = 'W';
                break;
            case 'W':
                Type = 'B';
                break;
        }

        System.out.println("d: Pass check");
        for(int i = 0; i < Manipulator.length; i++){
            RockLists = new ArrayList<vect2>();
            vect2 newMnpltPos = Pos.Subtitute(new vect2(Manipulator[i], Manipulator[(Manipulator.length - 1) - i]));
            RockType Rock = null;

            try {
                Rock = Rocks[newMnpltPos.X][newMnpltPos.Y];
            } catch (Exception e) {
                continue;
            }

            if(Rock != null && Rock.CurrentType() == Type){
                System.out.println("d: Checking from " + newMnpltPos.X + " " + newMnpltPos.Y);
                if(check(newMnpltPos, Type)){
                    System.out.println("d: Destroying from " + newMnpltPos.X + " " + newMnpltPos.Y);
                    destroyInList();
                    return;
                }else{
                    System.out.println("d: Pass check");
                }
            }
        }
        if(check(Pos, Type)){ //suicidal move
            System.out.println("d: Destroying from " + Pos.X + " " + Pos.Y);
            destroyInList();
        }
    }

    static void destroyInList(){
        for (vect2 RockPos : RockLists) {
            Rocks[RockPos.X][RockPos.Y] = null;
        }

        RockLists = new ArrayList<vect2>();
    }

    static boolean check(vect2 Pos, char Rocktype){
        RockLists.add(Pos);
        boolean res = true;
        for(int i = 0; i < Manipulator.length; i++){
            vect2 newMnpltPos = Pos.Subtitute(new vect2(Manipulator[i], Manipulator[(Manipulator.length - 1) - i]));
            RockType Rock = null;
            
            try {//checking if this out of bounds
                Rock = Rocks[newMnpltPos.X][newMnpltPos.Y];
            } catch (Exception e) {
                continue;
            }
            
            CheckLists.add(newMnpltPos);
            if(Rock == null){
                return false;
            }else if(Rock.CurrentType() != Rocktype || Rock.isChecked){
                continue;
            }else{
                Rocks[newMnpltPos.X][newMnpltPos.Y].isChecked = true;
                res = (res && check(newMnpltPos, Rocktype) && true);
                if(!res) return false;
                RockLists.add(newMnpltPos);
            }
        }
        return res;
    }
}
