package CodinGame_files.MarsLander_2;

import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class ipoint2{
    int x, y;
    public ipoint2(){
        x = 0; y = 0;
    }
    public ipoint2(int n, int m){
        x = n; y = m;
    }
    public void subtract(int x, int y){
      subtract(new ipoint2(x,y));
    }
    public void subtract(ipoint2 ip){
      x += ip.x;
      y += ip.y;
    }
    public void Assign(int X, int Y){
      x = X;
      y = Y;
    }
}

class line{
  ipoint2 p1, p2;
  public line(ipoint2 pt1, ipoint2 pt2){
    p1 = pt1;
    p2 = pt2;
  }
  public line(){}
}
  
class Rocket{
    ipoint2 Position;
    ipoint2 Speed;
    int fuel, rotate, power;
    final ipoint2 LandMaxSpeed = new ipoint2(20, 40);
    int LandAngle = 0;
    final double MarsGravity = 3.711;
    Scanner Input;
    ipoint2[] land;
    int Far = 100;
    line LandArea = new line();
    ipoint2 LandAreaArray = new ipoint2();
    double LandDistInTime = 0;
    String Todo = "";
    Mode LandMode = Mode.TO_DESTINATION;
    enum Mode{
        HOVER, //zeroing the speed
        TO_DESTINATION, //goto place without worrying abot landing(reckless)
        LAND //Land mode, safely land, but it won't care about how far it will drift when stopping ySpeed
    }
    public Rocket(Scanner s, ipoint2[] Surface){
        Input = s; land = Surface;
    }

    void Update(){
        int X = Input.nextInt();
        int Y = Input.nextInt();
        Position.Assign(X, Y);
        int hSpeed = Input.nextInt(); // the horizontal speed (in m/s), can be negative.
        int vSpeed = Input.nextInt(); // the vertical speed (in m/s), can be negative.
        Speed.Assign(hSpeed, vSpeed);
        fuel = Input.nextInt(); // the quantity of remaining fuel in liters.
        rotate = Input.nextInt(); // the rotation angle in degrees (-90 to 90).
        power = Input.nextInt(); // the thrust power (0 to 4).
        LandDistInTime = TimeToLanded(vSpeed, LandArea.p1.y);
        DoUpdate();
    }

    private void DoUpdate(){
        switch(LandMode){
            case LAND:
            //if speed.y or speed.x the top priority or do calculation of what rotation and power does the lander use
            ipoint2 AccTarget = new ipoint2(FindAcc(Position.x - LandArea.x, Speed.x))
            break;
            case TO_DESTINATION:
            //check if it's below the landing speed requirements
            //predict where it is going to land, if it's going to the not flat landing then readjust hspeed
            //if it's good to go, then just readjust vspeed over time
            int offset = checkIfNotOnLanding();
            if(offset != 0){
                //need calculate how long can we rotate until it need the target velocity to land
            }
            break;
        }
    }

    double DistToStop(double vel, double acc, int offsetVel){
        vel  += offsetVel;
        double res = (vel/acc+1)*vel/2;
        return res;
    }

    double TimeToLanded(double vSpeed, double TargetY){
        double res = TargetY / vSpeed;
        return res;
    }

    double FindAcc(double Distance, double vel){
        double res = vel*vel/(2*Distance-vel);
        return res;
    }

    void SearchFlatArea(){
        ipoint2 ip1, ip2;
        for(int i = 0; i < land.length - 1; i++){
            ip1 = land[i];
            ip2 = land[i + 1];
            if(ip1.y - ip2.y == 0){
                LandArea = new line(ip1, ip2);
                LandAreaArray = new ipoint2(i, i+1);
                return;
            }
        }
    }

    int checkIfNotOnLanding(){
      //a in the equation
      double mLandA = (LandArea.p2.y - LandArea.p1.y) / (LandArea.p2.x - LandArea.p1.x);
      //c in the equation
      double LandB = LandArea.p1.y - (LandArea.p1.x * mLandA);
      //b in the equation
      double mPosA = (Speed.y - Position.y) / (Speed.x - Position.x);
      //d in the equation
      double PosB = Position.y - (Position.x * mPosA);
      
      double x = (PosB - LandB) / (mLandA - mPosA);
      
      double param = x;
      if(x < LandArea.p1.x) param = LandArea.p1.x;
      else if(x > LandArea.p2.x) param = LandArea.p2.x;
      return (int)Math.ceil(x - param);
    }
    
    String GetActs(){
        return Todo;
    }
}

class Player{
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int surfaceN = in.nextInt(); // the number of points used to draw the surface of Mars.
        ipoint2[] ip = new ipoint2[surfaceN];
        for (int i = 0; i < surfaceN; i++) {
            int landX = in.nextInt(); // X coordinate of a surface point. (0 to 6999)
            int landY = in.nextInt(); // Y coordinate of a surface point. By linking all the points together in a sequential fashion, you form the surface of Mars.
            ip[i] = new ipoint2(landX, landY);
        }
        Rocket Lander = new Rocket(in, ip);
        //TODO: after one function from the class, it should re update itself and not use the while function in main
        while (true) {
            Lander.Update();
            System.out.println(Lander.GetActs());
        }
    }
}
