#include <iostream>
#include <string>
#include <vector>
#include <algorithm>
#include <cmath>

using namespace std;

/**
 * Save humans, destroy zombies!
 **/
/*TODO: 
 *-target the zombie closest to a human (done)
 *-if a human can't be saved because too far, prioritize the one can be saved first

 --unnecesseries--
 *-use math?
 *-make a prediction on where he should be (ex. defending a certain human because more zombies stacked up on him)

 --ideas--
 *-use centroid as target position for nearby zombies
 *-zombies seems to get lured when close, maybe make use of it
 *-use Monte Carlo algorithm to randomize the algorithm(?)
 *-or use decision tree wether save or not
 */
class ipoint2{
    public:
    ipoint2(){x=0;y=0;}
    ipoint2(int n,int m){x=n;y=m;}
    double getLength(ipoint2 from){
        return sqrt(pow(x - from.x, 2) + pow(y - from.y,2));
    }
    string toString(){
        return to_string(x) + " " + to_string(y);
    }
    int x, y;
};
class Entity{
    public:
    Entity(){}
    Entity(int Id, ipoint2 Pos){id = Id; pos = Pos;}
    int id;
    ipoint2 pos;
};
class Zombie:public Entity{
    public:
    Zombie():Entity(){}
    Zombie(int Id, ipoint2 Pos, ipoint2 NextPos):Entity(Id, Pos){nextPos = NextPos;}
    ipoint2 nextPos;
};
class Human:public Entity{
    public:
    Human():Entity(){}
    Human(int Id, ipoint2 Pos):Entity(Id, Pos){}
    double LenToZombie = 0;
    Zombie* zom;
};

int main()
{
    // game loop
    while (1) {
        int x;
        int y;
        cin >> x >> y; cin.ignore();
        int humanCount;
        cin >> humanCount; cin.ignore();
        Human HumanProperties[humanCount];
        for (int i = 0; i < humanCount; i++){
            int Id;
            ipoint2 pos;
            cin >> Id >> pos.x >> pos.y; cin.ignore();
            HumanProperties[i] = Human(Id, pos);
        }
        int zombieCount;
        cin >> zombieCount; cin.ignore();
        Zombie Zomb[zombieCount];
        ipoint2 ClosestToHuman;
        
        double LenCTH = 0;
        //cerr << ClosestToHuman.toString() << endl;
        for (int i = 0; i < zombieCount; i++) {
            int Id;
            ipoint2 pos, nextPos;
            cin >> Id >> pos.x >> pos.y >> nextPos.x >> nextPos.y; cin.ignore();
            Zomb[i] = Zombie(Id, pos, nextPos);
            for(Human h: HumanProperties){
                double len = h.pos.getLength(pos);
                if(h.LenToZombie == 0 || h.LenToZombie > len){
                    if(len < LenCTH || LenCTH == 0){
                        LenCTH = len;
                        ClosestToHuman = pos;
                        cerr << h.pos.toString() << " " << pos.toString() << endl;
                        cerr << LenCTH << endl;
                    }
                    h.LenToZombie = len;
                    h.zom = &Zomb[i];
                }
            }
        }

        /*cout << to_string(zombiePosNext[0].x) + " " + to_string(zombiePosNext[0].y) << endl;*/
        cout << ClosestToHuman.toString() << endl;
    }
}