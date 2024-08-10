package CodinGame_files_multi.CodersStrikeBack;

import java.util.*;
import java.io.*;
import java.math.*;

class vec2{
    public vec2(int x, int y){
        X = x; Y = y;
    }
    int X, Y;
}

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Player {
    static Scanner in = new Scanner(System.in);
    static int surfaceN;
    static vec2[] SurfaceLand;

    static vec2 Pos, RocketSpd;
    static int rotate, power,fuel;

    static double MarsAccel = 3.711;
    static int MaxlandingSpd = 40;


    public static void update(){
        int X = in.nextInt();
        int Y = in.nextInt();
        int hSpeed = in.nextInt(); // the horizontal speed (in m/s), can be negative.
        int vSpeed = in.nextInt(); // the vertical speed (in m/s), can be negative.
        fuel = in.nextInt(); // the quantity of remaining fuel in liters.
        rotate = in.nextInt(); // the rotation angle in degrees (-90 to 90).
        power = in.nextInt(); // the thrust power (0 to 4).

        Pos = new vec2(X, Y);
        RocketSpd = new vec2(hSpeed, vSpeed);
    }

    public static void main(String args[]) {
        int surfaceN = in.nextInt(); // the number of points used to draw the surface of Mars.
        SurfaceLand = new vec2[surfaceN];
        for (int i = 0; i < surfaceN; i++) {
            int landX = in.nextInt(); // X coordinate of a surface point. (0 to 6999)
            int landY = in.nextInt(); // Y coordinate of a surface point. By linking all the points together in a sequential fashion, you form the surface of Mars.
            SurfaceLand[i] = new vec2(landX, landY);
            String i = new String();
            i.e
        }

        int[] FlatAtWhere = atIndexFlat(SurfaceLand);
        int FlatCoorY = SurfaceLand[FlatAtWhere[0]].Y;
        for(int n : FlatAtWhere){
            System.err.println("d: " + SurfaceLand[n].Y);
        }

        boolean truex, truey;
        truex = true; //for going to the flat area
        truey = true; //for going to land
        // game loop
        while (truey) {
            update();

            int Thrust = 0;
            System.err.println(RocketSpd.Y);
            if(RocketSpd.Y < -(MaxlandingSpd)){
                double dist = calculateAtHeight(RocketSpd.Y, 4, FlatCoorY) * RocketSpd.Y * -0.5;
                System.err.println(dist);
                if(Pos.Y < dist) while(true) {
                    System.out.println("0 4");
                    update();
                }
            }
            System.out.println("0 " + Thrust);
        }
    }

    static double calculateAtHeight(int VerSpd, int MaxAccel, int LandCoor){
        double Spd = (VerSpd + MaxlandingSpd) * 1.1;
        double MaxAcc = MaxAccel - MarsAccel;
        return (Math.pow(Spd, 2) / MaxAcc) + LandCoor;
    }

    static int[] atIndexFlat(vec2[] Surface){
        int num = Surface[0].Y;
        for(int i = 1; i < Surface.length; i++){
            if(Surface[i].Y == num){
                int[] intArr = {i, i-1};
                return intArr;
            }else{
                num = Surface[i].Y;
            }
        }

        return null;
    }
}