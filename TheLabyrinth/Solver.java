package CodinGame_files.TheLabyrinth;

import java.util.*;
import java.io.*;
import java.math.*;
import java.nio.file.Paths;

class vec2{
    public vec2(int x, int y){
        X = x; Y = y;
    }

    int X, Y;
}

class PlaySpace{
    public PlaySpace(int ManyRow, int ManyColumn){
        AreaPlay = new char[ManyRow][ManyColumn];
        nRow = ManyRow;
        nColumn = ManyColumn;
    }

    public void UpdatePlaySpace(String[] Ss){
        for(int i = 0; i < AreaPlay.length; i++){
            AreaPlay[i] = Ss[i].toCharArray();
        }
    }

    public void CharIdentifier(char Wall, char Path, char StartPoint, char Deactivor){
        W = Wall; P = Path; SP = StartPoint; D = Deactivor;
    }

    private char W, P, SP, D;
    private int nRow, nColumn;
    char[][] AreaPlay;

    public String[] PathFindTo(vec2 From, char WhatType){
        isChecked = new boolean[nRow][nColumn];
        queueParameters = new ArrayList<QueueParameter>();

        List<vec2> PathList = BFSSearch(From, WhatType, null);
        System.err.println("dPFT: " + PathList);

        while(PathList == null){
            List<QueueParameter> Queues = queueParameters;
            queueParameters = new ArrayList<QueueParameter>();
            
            for (QueueParameter q : Queues) {
                PathList = BFSSearch(q.newPos, q.CurrentType, q.List);
                if(PathList != null) break;
            }
        }

        String[] resArr = new String[PathList.size()];
        int i = 0;
        for(vec2 v : PathList){
            if(v.X > 0)resArr[i] = "RIGHT";
            else if(v.X < 0)resArr[i] = "LEFT";
            else if(v.Y > 0)resArr[i] = "UP";
            else if(v.Y < 0)resArr[i] = "DOWN";

            i++;
        }

        return resArr;
    }

    private List<vec2> BFSSearch(vec2 Pos, char WhatType, List<vec2> Lists){//need to do sth, it needs the bfs algorithm
        List<vec2> lVec2s = Lists;
        if(Lists == null){
            lVec2s = new ArrayList<vec2>();
            isChecked[Pos.Y][Pos.X] = true;
        }

        lVec2s.add(Pos);

        int[] Manipulator = {1,0,-1,0};
        for(int i = 0; i < Manipulator.length; i++){
            vec2 newVec = new vec2(Pos.X + Manipulator[i], Pos.Y + Manipulator[(Manipulator.length - 1) - i]);

            if(!isChecked[newVec.Y][newVec.X]){
                char CurrenChar = AreaPlay[newVec.Y][newVec.X];
                if(CurrenChar == WhatType){
                    if(CurrenChar != '?') lVec2s.add(newVec);
                    return lVec2s;
                }else if(CurrenChar == W){
                    continue;
                }else{
                    queueParameters.add(new QueueParameter(lVec2s, WhatType, newVec));
                    isChecked[newVec.Y][newVec.X] = true;
                }
            }
        }

        return null;
    }

    private class QueueParameter{
        public QueueParameter(List<vec2> Vec2Lists, char Type, vec2 Pos){
            List = Vec2Lists;
            CurrentType = Type;
            newPos = Pos;
        }

        public List<vec2> List;
        public char CurrentType;
        public vec2 newPos;
    }

    List<QueueParameter> queueParameters;

    boolean[][] isChecked;
}


/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Player {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int R = in.nextInt(); // number of rows.
        int C = in.nextInt(); // number of columns.
        int A = in.nextInt(); // number of rounds between the time the alarm countdown is activated and the time the alarm goes off.

        PlaySpace newPlaySpace = new PlaySpace(R, C);
        newPlaySpace.CharIdentifier('#', '.', 'T', 'C');

        System.out.println("RIGHT");

        int n = 0;
        // game loop
        while (true) {
            int KR = in.nextInt(); // row where Kirk is located.
            int KC = in.nextInt(); // column where Kirk is located.

            String[] ROW = new String[R];
            for (int i = 0; i < R; i++) {
                ROW[i] = in.next(); // C of the characters in '#.TC?' (i.e. one line of the ASCII maze).         
            }
            newPlaySpace.UpdatePlaySpace(ROW);
            

            while(true){//check until all roads revealed
                String[] Moves = newPlaySpace.PathFindTo(new vec2(KC, KR), '?');
                System.err.println("d: " + Moves);

                if(Moves == null) break;

                for(String s : Moves){
                    System.err.println("d: " + s);
                }

                int o = 0;
                for(String s : Moves){
                    System.err.println("length: " + Moves.length + " iter: " + o);
                    System.out.println(s);

                    KR = in.nextInt();
                    KC = in.nextInt();
                    for(int i = 0; i < R; i++){
                        ROW[i] = in.next();
                        System.err.println(ROW[i]);
                    }
                    o++;
                }

                newPlaySpace.UpdatePlaySpace(ROW);
            }

            /*char[] Chars = {'C', 'T'};
            for(int i = 0; i < Chars.length; i++){
                String[] Moves = newPlaySpace.PathFindTo(new vec2(KC, KR), Chars[i]);
                for(String s : Moves){
                    System.out.println(s);
    
                    KR = in.nextInt();
                    KC = in.nextInt();
                    for(int o = 0; o < R; o++) ROW[o] = in.next();
                }       
            }*/
        }
    }
}
