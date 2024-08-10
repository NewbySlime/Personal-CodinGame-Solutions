package CodinGame_files.Dont_Panic;

import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Player {
    static int[] DistBetween;
    static String[] DirectionBetween;
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int nbFloors = in.nextInt(); // number of floors
        int width = in.nextInt(); // width of the area
        int nbRounds = in.nextInt(); // maximum number of rounds
        int exitFloor = in.nextInt(); // floor on which the exit is found
        int exitPos = in.nextInt(); // position of the exit on its floor
        int nbTotalClones = in.nextInt(); // number of generated clones
        int nbAdditionalElevators = in.nextInt(); // ignore (always zero)
        int nbElevators = in.nextInt(); // number of elevators

        System.err.println(nbFloors);

        int[] ElevatorsPos = new int[nbFloors];
        DistBetween = new int[nbFloors];
        for (int i = 0; i < nbElevators; i++) {
            int elevatorFloor = in.nextInt(); // floor on which this elevator is found
            int elevatorPos = in.nextInt(); // position of the elevator on its floor
            ElevatorsPos[elevatorFloor] = elevatorPos;
        }

        DirectionBetween = new String[nbFloors];
        for(int i = 0; i < DistBetween.length; i++){
            if(i - 1 >= 0){
                if(i == exitFloor){
                    DistBetween[i] = exitPos - ElevatorsPos[i - 1];
                }else{
                    int CurrentDist = ElevatorsPos[i] - ElevatorsPos[i-1];
                    System.err.println(ElevatorsPos[i] + " " + ElevatorsPos[i-1]);
                    DistBetween[i] = CurrentDist;
                }
            }
            System.err.println(DistBetween[i] + " " + i);
            CheckDirection(i);
        }


        int CurrentFloor = -1;
        // game loop
        while (true) {
            int cloneFloor = in.nextInt(); // floor of the leading clone
            int clonePos = in.nextInt(); // position of the leading clone on its floor
            String direction = in.next(); // direction of the leading clone: LEFT or RIGHT

            System.err.println(clonePos + " " + cloneFloor);
            if(CurrentFloor != cloneFloor){
                if(cloneFloor == -1){
                    System.out.println("WAIT");
                    continue;
                }
                if(CurrentFloor == -1){
                    DistBetween[cloneFloor] = ElevatorsPos[cloneFloor] - clonePos;
                    CheckDirection(cloneFloor);
                }

                CurrentFloor = cloneFloor;

                System.err.println(direction + " " + DirectionBetween[cloneFloor]);
                
                if(!direction.equals(DirectionBetween[cloneFloor])){
                    System.out.println("BLOCK");
                    continue;
                }
            }
            System.out.println("WAIT"); // action: WAIT or BLOCK
        }
    }

    static void CheckDirection(int index){
        if(DistBetween[index] < 0){
            DirectionBetween[index] = "LEFT";
        }else if(DistBetween[index] > 0){
            DirectionBetween[index] = "RIGHT";
        }
    }
}