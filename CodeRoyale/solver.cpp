#include <iostream>
#include <string>
#include <vector>
#include <map>
#include <algorithm>

#define NO_STRUCTURE -1


using namespace std;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/

enum owner_type_enum: int{
  ot_friendly = 0,
  ot_enemy = 1
};

enum structure_type_enum: int{
  st_barracks = 2
};

enum barracks_type_enum: int{
  bt_knights = 0,
  bt_archers = 1
};

enum unit_type_enum: int{
  ut_queen = -1,
  ut_knight = 0,
  ut_archer = 1
};



struct barracks_infotype{
  int turns;
  barracks_type_enum btype;
};


struct site_data{
  int x;
  int y;
  int radius;
  int ignore_1;
  int ignore_2;

  structure_type_enum structuretype;
  owner_type_enum owner;

  int param_1;
  int param_2;

  barracks_infotype *infotype_asbarracks(){
    return (barracks_infotype*)&param_1;
  }
};


struct units_data{
  int x;
  int y;
  int health;

  owner_type_enum owner;
  unit_type_enum unittype;
};


int main(){
  int num_sites;
  cin >> num_sites; cin.ignore();

  map<int, site_data*> sites;
  for (int i = 0; i < num_sites; i++) {
    int site_id;
    int x;
    int y;
    int radius;
    cin >> site_id >> x >> y >> radius; cin.ignore();

    site_data &_sites = *new site_data();
    _sites.x = x;
    _sites.y = y;
    _sites.radius = radius;

    sites[site_id] = &_sites;
  }

  // game loop
  while (1) {
    int gold;
    int touched_site; // -1 if none
    cin >> gold >> touched_site; cin.ignore();
    for (int i = 0; i < num_sites; i++) {
      int site_id;
      int ignore_1; // used in future leagues
      int ignore_2; // used in future leagues
      int structure_type; // -1 = No structure, 2 = Barracks
      int owner; // -1 = No structure, 0 = Friendly, 1 = Enemy
      int param_1;
      int param_2;
      cin >> site_id >> ignore_1 >> ignore_2 >> structure_type >> owner >> param_1 >> param_2; cin.ignore();

      site_data &_site = *sites[site_id];
      _site.ignore_1 = ignore_1;
      _site.ignore_2 = ignore_2;
      _site.structuretype = (structure_type_enum)structure_type;
      _site.owner = (owner_type_enum)owner;
      _site.param_1 = param_1;
      _site.param_2 = param_2;
    }

    int num_units;
    cin >> num_units; cin.ignore();
    vector<units_data> units; units.reserve(num_units);
    for (int i = 0; i < num_units; i++) {
      int x;
      int y;
      int owner;
      int unit_type; // -1 = QUEEN, 0 = KNIGHT, 1 = ARCHER
      int health;
      cin >> x >> y >> owner >> unit_type >> health; cin.ignore();

      units_data _unit;
      _unit.x = x;
      _unit.y = y;
      _unit.owner = (owner_type_enum)owner;
      _unit.unittype = (unit_type_enum)unit_type;
      _unit.health = health;

      units.insert(units.end(), _unit);
    }

    // Write an action using cout. DON'T FORGET THE "<< endl"
    // To debug: cerr << "Debug messages..." << endl


    // First line: A valid queen action
    // Second line: A set of training instructions
    cout << "WAIT" << endl;
    cout << "TRAIN" << endl;
  }
}