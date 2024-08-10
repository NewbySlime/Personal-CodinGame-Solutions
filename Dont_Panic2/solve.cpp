/*
  NOTES:
    -gCost increases based on what the bot is on
 */


#include <iostream>
#include <string>
#include <math.h>
#include <vector>
#include <algorithm>
#include <list>

using namespace std;

//uses string as the playSpace
//hcost distance between current position and target position
//gcost distance the path currently taking
class PathFinding{
  private:
  struct step{
    public:
      step(int hcost, int gcost){
        this->hCost = hcost;
        this->gCost = gcost;
      }
      void changeCostPath(int h = -1, int g = -1){
        gCost = (g < 0)?gCost:g;
        hCost = (h < 0)?hCost:h;
        fCost = gCost + hCost;
      }
      int getFCost(){return fCost;}

    private:
      int gCost, fCost, hCost;
  };

  void doPathFindRec(step *stepArray, int stepArrayLength){

  }

  int width, height;
  int normalCost = 10, elevatorCost = normalCost, changeLevelCost = 100;
  string playSpace, result;
  //up down left right
  int *dirCost;

  public:
  template<typename numtype> struct vec2{
    numtype x,y;
    vec2(numtype X, numtype Y){
      x = X;
      y = Y;
    }
    vec2 operator - (vec2 obj){
      return vec2(x - obj.x, y - obj.y);
    }
    vec2 operator + (vec2 obj){
      return vec2(x+obj.x, y+obj.y);
    }
    static float getDist(vec2<numtype> from, vec2<numtype> target){
      return pow(pow(abs(target.x-from.x), 2) + pow(abs(target.y-from.y), 2), 0.5);
    }
  };

  PathFinding(int *dirCost, char *playSpace){

  }

  int *calculateFCost(step *currentStep, int currentStepSize){

  }

  void doPathFind(vec2<int> startPos){
    //step arr[playSpace.length()];
    //step start;
    //start.changeCostPath(0,0);
  }

  void setString(string playSpace, int width, int height){
    this->playSpace = playSpace;
    this->width = width;
    this->height = height;
  }

  string getResult(){
    return result;
  }

};

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/

void doMain(){
  int nbFloors; // number of floors
  int width; // width of the area
  int nbRounds; // maximum number of rounds
  int exitFloor; // floor on which the exit is found
  int exitPos; // position of the exit on its floor
  int nbTotalClones; // number of generated clones
  int nbAdditionalElevators; // number of additional elevators that you can build
  int nbElevators; // number of elevators
  cin >> nbFloors >> width >> nbRounds >> exitFloor >> exitPos >> nbTotalClones >> nbAdditionalElevators >> nbElevators; cin.ignore();
  for (int i = 0; i < nbElevators; i++) {
    int elevatorFloor; // floor on which this elevator is found
    int elevatorPos; // position of the elevator on it floor
    cin >> elevatorFloor >> elevatorPos; cin.ignore();
  }

    // game loop
  while (1) {
    int cloneFloor; // floor of the leading clone
    int clonePos; // position of the leading clone on its floor
    string direction; // direction of the leading clone: LEFT or RIGHT
    cin >> cloneFloor >> clonePos >> direction; cin.ignore();
    cout << "WAIT" << endl; // action: WAIT or BLOCK
  }
}

void doTest(){

}

int main(){
  //doMain();
  doTest();
  return 0;
}