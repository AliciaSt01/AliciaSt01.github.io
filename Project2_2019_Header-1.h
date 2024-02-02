#pragma once

#include <iostream>
#include <vector>
#include <string>
#include <fstream> //for ifstream ofstream
#include <cstdlib> //exit()
#include <iomanip> //include iomanp library - setw, fixed, setprecision
using namespace std;

//class function
class ACME
{
	friend vector<ACME> finput(); //empty vector for data
	friend void update(vector<ACME>&);
	friend void stats(vector<ACME>&);
	friend void floorplan(vector<ACME>&);
	friend void output(vector<ACME>&);
	friend ostream& operator<<(ostream&, ACME&); //overload output operator

private:
	char floor; //floor variables
	int id; //floor id integer
	double width, length; //length and width variables
	string type;
	static int Ccount; //count the number of ACME objects
	static int Cfloor;

public:
	ACME(char = 'A', int = 1, double = 0.0, double = 0.0, string = "invalid"); //setting default variables
	void setACME(double, double); //mutator
	double area(); //calculates the area of the room
	bool operator<<(ACME&); //overload the << operator
	static void numC(); //add total number of ACME objects
	static void numF();
};

//define starting values of static data members
int ACME::Ccount = 0; //initialize my static number of ACME objects
int ACME::Cfloor = 0;

//function prototypes
bool isvalidInt(string); //function prototype
bool isvalidReal(string);