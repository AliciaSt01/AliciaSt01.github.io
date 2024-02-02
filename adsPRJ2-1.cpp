/*Project 2 "ACME Flooring Company”
© Alicia D. St Clair Dec 12, 2019*/

#include "Project2_2019_Header.h";

ACME::ACME(char A, int room, double wdth, double lngth, string invld) //constructor
{
	floor = A;
	id = room;
	width = wdth;
	length = lngth;
	type = invld;
	Ccount++; //increment counter of complex objects
}

double ACME::area() //calculates the area of the selected room
{
	double area = length * width;
	return area; //return nothing
}

void ACME::numC() //counts the total number of rooms
{
	cout << "There are " << Ccount << " rooms in this building.";
	return; //return nothing
}


/*-----------------Friend Case Functions----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
vector<ACME> finput() //Part 1
{
	vector<ACME>p(0);
	string fstring; //floor string
	string rstring; //room string
	string wstring; //width string
	string lstring; //length string
	string type; //floor type string
	string filename; //filename if file is in project
	int room; 
	double width, length;
	char floor;

	ifstream inFile; //open a file stream for input
	ofstream outFile; //open a file stream for output

	cout << "Please enter a filename without its sufix '.txt': "; //user instructions
	getline(cin, filename); //user input
	filename += ".txt"; //open filename
	inFile.open(filename.c_str()); //attempt to open the file file1_F19.txt

	if (inFile.fail()) //check if the file failed to open
	{
		cout << "\nThe file could not be opened successfully." //user insructions if the file does not open
			<< "\nPlease check that the file currently exists.\n";
		system("pause"); //waits for user input
		exit(1); //if file could not be opened, quit the program
	}
	cout << "The file has been successfully opened for reading.\n"; //user instructions if the program opens

	while (!inFile.eof()) 
	{
		inFile >> fstring >> rstring >> wstring >> lstring >> type; //inputs strings

		if (isvalidInt(rstring) && isvalidReal(wstring) && isvalidReal(lstring)) //checks for real or valid integrs
		{
			//convert these to int to double
			floor = fstring.at(0);
			room = atoi(rstring.c_str()); //converts an int to a string
			width = atof(wstring.c_str()); //converts a double into a string
			length = atof(lstring.c_str()); //converts a double into a string

			p.push_back({ floor, room, width, length, type }); //adds the entered variables to the end of the file
			rstring = "x";
		}
	}
	ACME::numC(); //display total number of ACME objects

	inFile.close(); //closes file
	cin.ignore(); //ignores 'enter'
	cout << endl << endl;

	return p;
}

void update(vector<ACME>& up) //Part 2
{
	int i; //counter variable
	int r;
	char flr;
	int person, user; //user input
	double width, length;
	string type;
	string option;
	string select;
	string rm;
	string w;
	string l;

	do
	{
		do
		{
			cout << "Room Update Menu\n"
				<< "Item 1: Display room information\n"
				<< "Item 2: Change room specifications\n"
				<< "Item 3: Quit modifications" << endl;

			//enter the number but read it in as a string
			cout << "Please enter an option: ";
			getline(cin, option);
			cout << endl;

			if (isvalidInt(option))
			{
				//convert string to char array to int
				person = atoi(option.c_str());
				break; //escape loop if we have a valid awnser
			}
			else
			{
				cout << "The number you have entered is an invalid variable." //error message
					<< "Please enter a number between 1-3." << endl;
			}
		} while (1);

		//switch case for each option
		switch (person)
		{
		case 1: //Display room info
			cout << "What floor would you like to display?\n";
			cin >> flr; //user input
			cin.ignore();

			//counts for every floor and room
			for (i = 0; i < up.size(); i++)
			{
				if (up.at(i).floor == flr)
					cout << up.at(i) << endl;
			}
			break;

		case 2: //Change room specifications
				/*-----------------Ask the user for a floor level-------------------------------------*/
			cout << "Please enter a floor level: ";
			cin >> flr;
			cin.ignore();

			//counts for every floor and room
			for (i = 0; i < up.size(); i++)
			{
				if (up.at(i).floor == flr); //forces the program to close
			}
			cout << endl;

			/*-----------------------Ask the user for a room number-----------------------------------*/
			cout << "Please enter a room number 1-10: ";
			getline(cin, rm);
			cin.ignore();

			if (!isvalidReal(rm))
			{
				cout << "The room number you entered in not a valid integer." << endl;
			}
			else
			{
				r = atoi(rm.c_str());

				cout << "What would you like to change about the room:\n"
					<< "Item 1: Width\n"
					<< "Item 2: Length\n"
					<< "Item 3: Flooring\n"
					<< "Item 4: Quit Modifications" << endl;

				//enter the number but read it in as a string
				cout << "Please enter an option: ";
				getline(cin, select);
				cin.ignore();
				cout << endl;

				if (isvalidInt(select))
				{
					//convert string to char array to int
					user = atoi(select.c_str());

					//switch-case
					switch (user)
					{
					case 1: //Width

						cout << "What would you like the new width to be? ";
						getline(cin, w);
						width = atof(w.c_str());
						cin.ignore();

						for (i = 0; i < up.size(); i++)
						{
							if (up.at(i).floor == flr && up.at(i).id == r)
							{
								up.at(i).width = width;
							}
						}
						break;

					case 2: //Length								
						cout << "What would you like the new length to be? ";
						getline(cin, l);
						length = atof(l.c_str());
						cin.ignore();

						for (i = 0; i < up.size(); i++)
						{
							if (up.at(i).floor == flr && up.at(i).id == r)
							{
								up.at(i).length = length;
							}
						}
						break;

					case 3: //Flooring
						cout << "What would you like the new flooring to be? ";
						getline(cin, type);
						cin.ignore();

						for (i = 0; i < up.size(); i++)
						{
							if (up.at(i).floor == flr && up.at(i).id == r)
							{
								up.at(i).type = type;
							}
						}
						break;

					default: //Quit
						break;
					}
				}
				else
				{
					cout << "The number you have entered is an invalid variable." //error message
						<< "Please enter a number between 1-4." << endl;
				}

			}
			cout << endl;
			break;

		default: //Quit modifications
			break;
		}

	} while (person != 3);
	cout << endl << endl;
	return; //return nothing
}

void stats(vector<ACME>& s) //Part 3
{
	double max = 0; //maximum variable
	double min = 10000; //minimun variable
	int i; //counter
	int CarpetTotal = 0; //total carpet variable
	int TileTotal = 0; //total tile variable
	int WoodTotal = 0; //total wood variable
	double CarpetArea = 0; //total carpet area variable
	double TileArea = 0; //total tile area variable
	double WoodArea = 0; //total wood area variable
	char pple; //user input
	char umin; //user minimum input
	char umax; //user maximum input
	char in; //user input
	string people; //user input string
	string userMinimum; //user minimum input string
	string userMaximum; //user maximum input string
	string inuser; //user input string

	do
	{
			cout << "What would you like to check?\n"
				<< "1. Total Flooring Type\n"
				<< "2. Minimum Room Size\n"
				<< "3. Maximum Room Size\n"
				<< "4. Average Room size\n"
				<< "5. Quit" << endl;

			getline(cin, people);
			pple = atoi(people.c_str()); //converts an int to a string
			cin.ignore();

			switch (pple)
			{
			case 1:
				//menu options
					cout << "Which flooring type would you like to search for?\n"
						<< "1. Carpet\n"
						<< "2. Tile\n"
						<< "3. Wood\n"
						<< "4. Quit" << endl;

					getline(cin, inuser);
					in = atoi(inuser.c_str()); //converts an int to a string
					cin.ignore();

				switch (in)
				{
				case 1:
					for (i = 0; i < s.size(); i++)
					{
						if (s.at(i).type == "Carpet")
						{
							CarpetTotal++; //total wood rooms with carpet flooring
							cout << "There are " << CarpetTotal << " rooms with carpet flooring";
							cout << endl;
						}
					}
					break;

				case 2:
					for (i = 0; i < s.size(); i++)
					{
						if (s.at(i).type == "Tile")
						{
							TileTotal++; //total wood rooms with tile flooring
							cout << "There are " << TileTotal << " rooms with tile flooring";
							cout << endl;
						}
					}
					break;

				case 3:
					for (i = 0; i < s.size(); i++)
					{
						if (s.at(i).type == "Wood")
						{
							WoodTotal++; //total wood rooms with wood flooring
							cout << "There are " << WoodTotal << " rooms with wood flooring";
							cout << endl;
						}
					}
					break;

				default:
					break;
				}
			case 2:
				//menu options
					cout << "Which flooring type would you like to find the minimum for?\n"
						<< "1. Carpet\n"
						<< "2. Tile\n"
						<< "3. Wood\n"
						<< "4. Quit" << endl;

					getline(cin, userMinimum);
					umin = atoi(userMinimum.c_str()); //sconverts an int into a string
					cin.ignore();

				switch (umin)
				{
				case 1:
					for (i = 0; i < s.size(); i++)
					{
						if (s.at(i).type == "Carpet" && s.at(i).area() < min)
							min = s.at(i).area(); //finds min carpet room(s)
					}

					for (i = 0; i < s.size(); i++)
					{
						if (s.at(i).type == "Carpet" && s.at(i).area() == min)
							cout << s.at(i).area() << endl; //finds matching min carpet room(s)
					}

					//counts for every floor and room
					for (i = 0; i < s.size(); i++)
					{
						if (s.at(i).type == "Carpet" && s.at(i).area() == min)
							cout << s.at(i) << endl; //diplays room(s) and floor(s) with min carpet area
					}
					break;

					//counts for every floor and room
					for (i = 0; i < s.size(); i++)
					{
						if (s.at(i).type == "Carpet" && s.at(i).area() < min)
							cout << s.at(i) << endl; //diplays room(s) and floor(s) with min carpet area
					}
					break;
	
				case 2:
					for (i = 0; i < s.size(); i++)
					{
						if (s.at(i).type == "Tile" && s.at(i).area() < min)
							min = s.at(i).area(); //finds matching min tile room(s)
					}

					for (i = 0; i < s.size(); i++)
					{
						if (s.at(i).type == "Tile" && s.at(i).area() == min)
							cout << s.at(i).area() << endl; //finds matching min tile room(s)
					}

					//counts for every floor and room
					for (i = 0; i < s.size(); i++)
					{
						if (s.at(i).type == "Tile" && s.at(i).area() == min)
							cout << s.at(i) << endl; //diplays room(s) and floor(s) with min tile area
					}
					break;

					//counts for every floor and room
					for (i = 0; i < s.size(); i++)
					{
						if (s.at(i).type == "Tile" && s.at(i).area() < min)
							cout << s.at(i) << endl; //diplays room(s) and floor(s) with min tile area
					}
					break;
				case 3:
					for (i = 0; i < s.size(); i++)
					{
						if (s.at(i).type == "Wood" && s.at(i).area() < min)
							min = s.at(i).area(); //finds min carpet room(s)
					}

					for (i = 0; i < s.size(); i++)
					{
						if (s.at(i).type == "Wood" && s.at(i).area() == min)
							cout << s.at(i).area() << endl; //finds matching min wood room(s)
					}

					//counts for every floor and room
					for (i = 0; i < s.size(); i++)
					{
						if (s.at(i).type == "Wood" && s.at(i).area() == min)
							cout << s.at(i) << endl; //diplays room(s) and floor(s) with min wood area
					}
					break;

					//counts for every floor and room
					for (i = 0; i < s.size(); i++)
					{
						if (s.at(i).type == "Wood" && s.at(i).area() < min) 
							cout << s.at(i) << endl; //diplays room(s) and floor(s) with min wood area
					}
					break;
				default:
					break;
				}
			case 3:
				//menu options
					cout << "Which flooring type would you like to find the maximum for?\n"
						<< "1. Carpet\n"
						<< "2. Tile\n"
						<< "3. Wood\n"
						<< "4. Quit" << endl;

					getline(cin, userMaximum);
					umax = atoi(userMaximum.c_str()); //converts an int into a string
					cin.ignore();

				switch (umax)
				{
				case 1:
					for (i = 0; i < s.size(); i++)
					{
						if (s.at(i).type == "Carpet" && s.at(i).area() > max)
							max = s.at(i).area(); //finds max carpet room(s)
					}

					for (i = 0; i < s.size(); i++)
					{
						if (s.at(i).type == "Carpet" && s.at(i).area() == max)
							cout << s.at(i).area() << endl; //finds matching max carpet room(s)
					}

					//counts for every floor and room
					for (i = 0; i < s.size(); i++)
					{
						if (s.at(i).type == "Carpet" && s.at(i).area() == max)
							cout << s.at(i) << endl; //diplays room(s) and floor(s) with max carpet area
					}
					break;

					//counts for every floor and room
					for (i = 0; i < s.size(); i++)
					{
						if (s.at(i).type == "Carpet" && s.at(i).area() > max)
							cout << s.at(i) << endl; //diplays room(s) and floor(s) with max carpet area
					}
					break;
				case 2:
					for (i = 0; i < s.size(); i++)
					{
						if (s.at(i).type == "Tile" && s.at(i).area() > max) //finds max tile room(s)
							max = s.at(i).area();
					}

					for (i = 0; i < s.size(); i++)
					{
						if (s.at(i).type == "Tile" && s.at(i).area() == max)
							cout << s.at(i).area() << endl; //finds matching max tile room(s)
					}

					//counts for every floor and room
					for (i = 0; i < s.size(); i++)
					{
						if (s.at(i).type == "Tile" && s.at(i).area() == max)
							cout << s.at(i) << endl; //diplays room(s) and floor(s) with max tile area
					}
					break;

					//counts for every floor and room
					for (i = 0; i < s.size(); i++)
					{
						if (s.at(i).type == "Tile" && s.at(i).area() > max)  //diplays room(s) and floor(s) with max tile area
							cout << s.at(i) << endl;
					}
					break;
				case 3:
					for (i = 0; i < s.size(); i++)
					{
						if (s.at(i).type == "Wood" && s.at(i).area() > max)  //finds max wood room
							max = s.at(i).area();
					}

					for (i = 0; i < s.size(); i++)
					{
						if (s.at(i).type == "Wood" && s.at(i).area() == max)
							cout << s.at(i).area() << endl; //finds matching max wood room(s)
					}

					//counts for every floor and room
					for (i = 0; i < s.size(); i++)
					{
						if (s.at(i).type == "Wood" && s.at(i).area() == max)
							cout << s.at(i) << endl; //diplays room(s) and floor(s) with max wood area
					}
					break;

					//counts for every floor and room
					for (i = 0; i < s.size(); i++)
					{
						if (s.at(i).type == "Wood" && s.at(i).area() > max)
							cout << s.at(i) << endl; //diplays room(s) and floor(s) with max wood area
					}
					break;

				default:
					break;
				}
			case 4:
				//menu options
					cout << "Which flooring type would you like to solve for?\n"
						<< "1. Carpet\n"
						<< "2. Tile\n"
						<< "3. Wood\n"
						<< "4. Quit" << endl;

					getline(cin, inuser); //user input
					in = atoi(inuser.c_str()); //converts an int to a string
					cin.ignore(); //ignore 'enter';

				switch (in)
				{
				case 1:
					for (i = 0; i < s.size(); i++)
					{
						if (s.at(i).type == "Carpet")
						{
							CarpetTotal++; //total rooms with carpet flooring
							CarpetArea += s.at(i).area(); //total carpet flooring area
							cout << "The average area for the rooms with Carpet is: " << CarpetArea / CarpetTotal;  //calculates the total average area
							cout << endl;
						}
					}
					break;

				case 2:
					for (i = 0; i < s.size(); i++)
					{
						if (s.at(i).type == "Tile")
						{
							TileTotal++; //total rooms with tile flooring
							TileArea += s.at(i).area(); //total tile flooring area
							cout << "The average area for the rooms with Tile is: " << TileArea / TileTotal; //calculates the total average area
							cout << endl;
						}
					}
					break;

				case 3:
					for (i = 0; i < s.size(); i++)
					{
						if (s.at(i).type == "Wood")
						{
							WoodTotal++; //total rooms with wood flooring
							WoodArea += s.at(i).area(); //total wood flooring area
							cout << "The average area for the rooms with Wood is: " << WoodArea / WoodTotal; //calculates the total average area
							cout << endl;
						}
					}
					break;

				default:
					break;
				}
			default:
				break;
			}

	} while (pple != 5);
	return;
}

void floorplan(vector<ACME>& f) //Part 4
{
	int i; //counter
//Carpet variables
	double totalCarpetA = 0, totalCarpetB = 0, totalCarpetC = 0, totalCarpetD = 0, totalCarpetE = 0;
	double totalCarpet = totalCarpetA + totalCarpetB + totalCarpetC + totalCarpetD + totalCarpetE;
	double percentACarpet, percentBCarpet, percentCCarpet, percentDCarpet, percentECarpet; //total area of Carpet flooring in the building *10%
	//Wood variables
	double totalWoodA = 0, totalWoodB = 0, totalWoodC = 0, totalWoodD = 0, totalWoodE = 0;
	double totalWood = totalWoodA + totalWoodB + totalWoodC + totalWoodD + totalWoodE;
	double percentAWood, percentBWood, percentCWood, percentDWood, percentEWood; //total area of Wood flooring in the building *10%
	//Tile variables
	double totalTileA = 0, totalTileB = 0, totalTileC = 0, totalTileD = 0, totalTileE = 0;
	double percentATile, percentBTile, percentCTile, percentDTile, percentETile; //total area of Tile flooring in the building *10%
	double totalTile = totalTileA + totalTileB + totalTileC + totalTileD + totalTileE;
	//Total area percent variables
	double totalPercentCarpet, totalPercentWood, totalPercentTile; //total percent

	/*-----------------------Total Carpet--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
	for (i = 0; i < f.size(); i++) //Carpet 'A'
	{
		if (f.at(i).type == "Carpet" && f.at(i).floor == 'A')
		{
			totalCarpetA += f.at(i).area(); //calculates the total area of rooms with carpet on floor 'A'
		}
		percentACarpet = (totalCarpetA * 0.1) + totalCarpetA; //calcultes 10% of the total amount of tile
	}

	for (i = 0; i < f.size(); i++) //Carpet 'B'
	{
		if (f.at(i).type == "Carpet" && f.at(i).floor == 'B')
		{
			totalCarpetB += f.at(i).area(); //calculates the total area of rooms with carpet on floor 'B'
		}
		percentBCarpet = (totalCarpetB * 0.1) + totalCarpetB; //calcultes 10% of the total amount of tile
	}

	for (i = 0; i < f.size(); i++) //Carpet'C'
	{
		if (f.at(i).type == "Carpet" && f.at(i).floor == 'C')
		{
			totalCarpetC += f.at(i).area(); //calculates the total area of rooms with carpet on floor 'C'
		}
		percentCCarpet = (totalCarpetC * 0.1) + totalCarpetC; //calcultes 10% of the total amount of tile
	}

	for (i = 0; i < f.size(); i++) //Carpet 'D'
	{
		if (f.at(i).type == "Carpet" && f.at(i).floor == 'D')
		{
			totalCarpetD += f.at(i).area(); //calculates the total area of rooms with carpet on floor 'D'
		}
		percentDCarpet = (totalCarpetD * 0.1) + totalCarpetD; //calcultes 10% of the total amount of tile
	}

	for (i = 0; i < f.size(); i++) // Carpet 'E'
	{
		if (f.at(i).type == "Carpet" && f.at(i).floor == 'E')
		{
			totalCarpetE += f.at(i).area(); //calculates the total area of rooms with carpet on floor 'E'
		}
		percentECarpet = (totalCarpetE * 0.1) + totalCarpetE; //calcultes 10% of the total amount of tile
	}

	for (i = 0; i < f.size(); i++) //Total Carpet
	{
		if (f.at(i).type == "Carpet")
		{
			totalCarpet += f.at(i).area(); //calculates the total area of rooms with carpet
		}
		totalPercentCarpet = (totalCarpet * 0.1) + totalCarpet; //calcultes 10% of the total amount of tile
	}
	/*-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/

	/*---------------------Total Wood------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
	for (i = 0; i < f.size(); i++) //Wood 'A'
	{
		if (f.at(i).type == "Wood" && f.at(i).floor == 'A')
		{
			totalWoodA += f.at(i).area(); //calculates the total area of rooms with wood on floor 'A'
		}
		percentAWood = (totalWoodA * 0.1) + totalWoodA; //calcultes 10% of the total amount of tile
	}

	for (i = 0; i < f.size(); i++) //Wood 'B'
	{
		if (f.at(i).type == "Wood" && f.at(i).floor == 'B')
		{
			totalWoodB += f.at(i).area(); //calculates the total area of rooms with wood on floor 'B'
		}
		percentBWood = (totalWoodB * 0.1) + totalWoodB; //calcultes 10% of the total amount of tile
	}

	for (i = 0; i < f.size(); i++) //Wood 'C'
	{
		if (f.at(i).type == "Wood" && f.at(i).floor == 'C')
		{
			totalWoodC += f.at(i).area(); //calculates the total area of rooms with wood on floor 'C'
		}
		percentCWood = (totalWoodC * 0.1) + totalWoodC; //calcultes 10% of the total amount of tile
	}

	for (i = 0; i < f.size(); i++) //Wood 'D'
	{
		if (f.at(i).type == "Wood" && f.at(i).floor == 'D')
		{
			totalWoodD += f.at(i).area(); //calculates the total area of rooms with wood on floor 'D'
		}
		percentDWood = (totalWoodD * 0.1) + totalWoodD; //calcultes 10% of the total amount of tile
	}

	for (i = 0; i < f.size(); i++) //Wood 'E'
	{
		if (f.at(i).type == "Wood" && f.at(i).floor == 'E')
		{
			totalWoodE += f.at(i).area(); //calculates the total area of rooms with wood on floor 'E'
		}
		percentEWood = (totalWoodE * 0.1) + totalWoodE; //calcultes 10% of the total amount of tile
	}

	for (i = 0; i < f.size(); i++) //Total Wood
	{
		if (f.at(i).type == "Wood")
		{
			totalWood += f.at(i).area(); //calculates the total area of rooms with wood 
		}
		totalPercentWood = (totalWood * 0.1) + totalWood; //calcultes 10% of the total amount of tile
	}
	/*-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/

	/*---------------------Total Tile------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
	for (i = 0; i < f.size(); i++) //Tile 'A'
	{
		if (f.at(i).type == "Tile" && f.at(i).floor == 'A')
		{
			totalTileA += f.at(i).area(); //calculates the total area of rooms with tile on floor 'A'
		}
		percentATile = (totalTileA * 0.1) + totalTileA; //calcultes 10% of the total amount of tile
	}

	for (i = 0; i < f.size(); i++) //'Tile 'B'
	{
		if (f.at(i).type == "Tile" && f.at(i).floor == 'B')
		{
			totalTileB += f.at(i).area(); //calculates the total area of rooms with tile on floor 'B'
		}
		percentBTile = (totalTileB * 0.1) + totalTileB; //calcultes 10% of the total amount of tile
	}

	for (i = 0; i < f.size(); i++) //Tile 'C'
	{
		if (f.at(i).type == "Tile" && f.at(i).floor == 'C')
		{
			totalTileC += f.at(i).area(); //calculates the total area of rooms with tile on floor 'C'
		}
		percentCTile = (totalTileC * 0.1) + totalTileC; //calcultes 10% of the total amount of tile
	}

	for (i = 0; i < f.size(); i++) //Tile 'D'
	{
		if (f.at(i).type == "Tile" && f.at(i).floor == 'D')
		{
			totalTileD += f.at(i).area(); //calculates the total area of rooms with tile on floor 'D'
		}
		percentDTile = (totalTileD * 0.1) + totalTileD; //calcultes 10% of the total amount of tile
	}

	for (i = 0; i < f.size(); i++) //Tile 'E'
	{
		if (f.at(i).type == "Tile" && f.at(i).floor == 'E')
		{
			totalTileE += f.at(i).area(); //calculates the total area of rooms with tile on floor 'E'
		}
		percentETile = (totalTileE * 0.1) + totalTileE; //calcultes 10% of the total amount of tile
	}

	for (i = 0; i < f.size(); i++) //Total Tile
	{
		if (f.at(i).type == "Tile")
		{
			totalTile += f.at(i).area(); //calculates the total area of rooms with tile
		}
		totalPercentTile = (totalTile * 0.1) + totalTile; //calcultes 10% of the total amount of tile
	}
	/*-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/

	/*------------------------Table--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
	cout << "Square Area in ft^2 by floor and floor type.\n\n"
		<< "Floor    Carpet    (+10%)     Wood     (+10%)    Tile    (+10%)\n"
		<< "----------------------------------------------------------------\n"
		<< "  A" << setw(10) << fixed << setprecision(0) << totalCarpetA << setw(10) << fixed << setprecision(0) << percentACarpet << setw(10) << fixed << setprecision(0) << totalWoodA << setw(10) << fixed << setprecision(0) << percentAWood << setw(10) << fixed << setprecision(0) << totalTileA << setw(10) << fixed << setprecision(0) << percentATile << "\n"
		<< "  B" << setw(10) << fixed << setprecision(0) << totalCarpetB << setw(10) << fixed << setprecision(0) << percentBCarpet << setw(10) << fixed << setprecision(0) << totalWoodB << setw(10) << fixed << setprecision(0) << percentBWood << setw(10) << fixed << setprecision(0) << totalTileB << setw(10) << fixed << setprecision(0) << percentBTile << "\n"
		<< "  C" << setw(10) << fixed << setprecision(0) << totalCarpetC << setw(10) << fixed << setprecision(0) << percentCCarpet << setw(10) << fixed << setprecision(0) << totalWoodC << setw(10) << fixed << setprecision(0) << percentCWood << setw(10) << fixed << setprecision(0) << totalTileC << setw(10) << fixed << setprecision(0) << percentCTile << "\n"
		<< "  D" << setw(10) << fixed << setprecision(0) << totalCarpetD << setw(10) << fixed << setprecision(0) << percentDCarpet << setw(10) << fixed << setprecision(0) << totalWoodD << setw(10) << fixed << setprecision(0) << percentDWood << setw(10) << fixed << setprecision(0) << totalTileD << setw(10) << fixed << setprecision(0) << percentDTile << "\n"
		<< "  E" << setw(10) << fixed << setprecision(0) << totalCarpetE << setw(10) << fixed << setprecision(0) << percentECarpet << setw(10) << fixed << setprecision(0) << totalWoodE << setw(10) << fixed << setprecision(0) << fixed << setprecision(0) << percentEWood << setw(10) << fixed << setprecision(0) << totalTileE << setw(10) << fixed << setprecision(0) << percentETile << "\n"
		<< "----------------------------------------------------------------\n"
		<< "Totals" << setw(7) << fixed << setprecision(0) << totalCarpet << setw(10) << fixed << setprecision(0) << totalPercentCarpet << setw(10) << fixed << setprecision(0) << totalWood << setw(10) << fixed << setprecision(0) << totalPercentWood << setw(10) << totalTile << setw(10) << fixed << setprecision(0) << totalPercentTile << endl << endl;
	/*-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/

	return;
}

void output(vector <ACME>& f) //Part 5
{
	/*----------Part 4 variables---------------------------------------------------------------------------*/
	int i; //counter
	double totalCarpetA = 0, totalCarpetB = 0, totalCarpetC = 0, totalCarpetD = 0, totalCarpetE = 0;
	double totalCarpet = totalCarpetA + totalCarpetB + totalCarpetC + totalCarpetD + totalCarpetE;
	double percentACarpet, percentBCarpet, percentCCarpet, percentDCarpet, percentECarpet;
	//Wood variables
	double totalWoodA = 0, totalWoodB = 0, totalWoodC = 0, totalWoodD = 0, totalWoodE = 0;
	double totalWood = totalWoodA + totalWoodB + totalWoodC + totalWoodD + totalWoodE;
	double percentAWood, percentBWood, percentCWood, percentDWood, percentEWood;
	//Tile variables
	double totalTileA = 0, totalTileB = 0, totalTileC = 0, totalTileD = 0, totalTileE = 0;
	double percentATile, percentBTile, percentCTile, percentDTile, percentETile;
	double totalTile = totalTileA + totalTileB + totalTileC + totalTileD + totalTileE;
	//Total area percent variables
	double totalPercentCarpet, totalPercentWood, totalPercentTile;
	/*-------------------------------------------------------------------------------------------------------*/

	ifstream inFile; //input file stream 
	ofstream outFile; //output file stream 
	char response; //user input variable 
	string filename; //file name 

	cout << "Please enter a filename without including the .txt extension.\n";
	getline (cin, filename);
	filename += ".txt"; //open filename
	cin.ignore(); //in case we need to use getline() later
	inFile.open(filename.c_str()); //open file as input file to test
	if (!inFile.fail()) //check if the file opened 
	{
		cout << "A file by the name " << filename << " exists.\n\n"
			<< "Do you want to continue and overwrite it "
			<< "with the new data (y or n): " << endl;
		cin >> response; //user input 
		cin.ignore(); //ignore rest of input buffer 
		if (tolower(response) != 'y') //if the user did not say yes
		{
			cout << "The existing file will not be overwritten.\n";
			system("pause");
			exit(1);  //if file exists and user wants to keep 
		}
	}
	inFile.close(); //close the input stream 
	//end of input file section  

	//beginning of output file section 
	outFile.open(filename.c_str()); //open file for writing 
	if (outFile.fail()) //if the files could not be opened for writing
	{
		cout << "The file was not successfully opened for writing.\n";
		system("pause");
		exit(1); //if file was not opened, quit program 
	}
	cout << "The file is ready for writing.\n"; //successfully opened
		//Code Copied from Part 4

	/*-----------------------Total Carpet--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
	for (i = 0; i < f.size(); i++) //Carpet 'A'
	{
		if (f.at(i).type == "Carpet" && f.at(i).floor == 'A')
		{
			totalCarpetA += f.at(i).area(); //calculates the total area of rooms with carpet on floor 'A'
		}
		percentACarpet = (totalCarpetA * 0.1) + totalCarpetA; //calcultes 10% of the total amount of tile
	}

	for (i = 0; i < f.size(); i++) //Carpet 'B'
	{
		if (f.at(i).type == "Carpet" && f.at(i).floor == 'B')
		{
			totalCarpetB += f.at(i).area(); //calculates the total area of rooms with carpet on floor 'B'
		}
		percentBCarpet = (totalCarpetB * 0.1) + totalCarpetB; //calcultes 10% of the total amount of tile
	}

	for (i = 0; i < f.size(); i++) //Carpet'C'
	{
		if (f.at(i).type == "Carpet" && f.at(i).floor == 'C')
		{
			totalCarpetC += f.at(i).area(); //calculates the total area of rooms with carpet on floor 'C'
		}
		percentCCarpet = (totalCarpetC * 0.1) + totalCarpetC; //calcultes 10% of the total amount of tile
	}

	for (i = 0; i < f.size(); i++) //Carpet 'D'
	{
		if (f.at(i).type == "Carpet" && f.at(i).floor == 'D')
		{
			totalCarpetD += f.at(i).area(); //calculates the total area of rooms with carpet on floor 'D'
		}
		percentDCarpet = (totalCarpetD * 0.1) + totalCarpetD; //calcultes 10% of the total amount of tile
	}

	for (i = 0; i < f.size(); i++) // Carpet 'E'
	{
		if (f.at(i).type == "Carpet" && f.at(i).floor == 'E')
		{
			totalCarpetE += f.at(i).area(); //calculates the total area of rooms with carpet on floor 'E'
		}
		percentECarpet = (totalCarpetE * 0.1) + totalCarpetE; //calcultes 10% of the total amount of tile
	}

	for (i = 0; i < f.size(); i++) //Total Carpet
	{
		if (f.at(i).type == "Carpet")
		{
			totalCarpet += f.at(i).area(); //calculates the total area of rooms with carpet
		}
		totalPercentCarpet = (totalCarpet * 0.1) + totalCarpet; //calcultes 10% of the total amount of tile
	}
	/*-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/

	/*---------------------Total Wood------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
	for (i = 0; i < f.size(); i++) //Wood 'A'
	{
		if (f.at(i).type == "Wood" && f.at(i).floor == 'A')
		{
			totalWoodA += f.at(i).area(); //calculates the total area of rooms with wood on floor 'A'
		}
		percentAWood = (totalWoodA * 0.1) + totalWoodA; //calcultes 10% of the total amount of tile
	}

	for (i = 0; i < f.size(); i++) //Wood 'B'
	{
		if (f.at(i).type == "Wood" && f.at(i).floor == 'B')
		{
			totalWoodB += f.at(i).area(); //calculates the total area of rooms with wood on floor 'B'
		}
		percentBWood = (totalWoodB * 0.1) + totalWoodB; //calcultes 10% of the total amount of tile
	}

	for (i = 0; i < f.size(); i++) //Wood 'C'
	{
		if (f.at(i).type == "Wood" && f.at(i).floor == 'C')
		{
			totalWoodC += f.at(i).area(); //calculates the total area of rooms with wood on floor 'C'
		}
		percentCWood = (totalWoodC * 0.1) + totalWoodC; //calcultes 10% of the total amount of tile
	}

	for (i = 0; i < f.size(); i++) //Wood 'D'
	{
		if (f.at(i).type == "Wood" && f.at(i).floor == 'D')
		{
			totalWoodD += f.at(i).area(); //calculates the total area of rooms with wood on floor 'D'
		}
		percentDWood = (totalWoodD * 0.1) + totalWoodD; //calcultes 10% of the total amount of tile
	}

	for (i = 0; i < f.size(); i++) //Wood 'E'
	{
		if (f.at(i).type == "Wood" && f.at(i).floor == 'E')
		{
			totalWoodE += f.at(i).area(); //calculates the total area of rooms with wood on floor 'E'
		}
		percentEWood = (totalWoodE * 0.1) + totalWoodE; //calcultes 10% of the total amount of tile
	}

	for (i = 0; i < f.size(); i++) //Total Wood
	{
		if (f.at(i).type == "Wood")
		{
			totalWood += f.at(i).area(); //calculates the total area of rooms with wood 
		}
		totalPercentWood = (totalWood * 0.1) + totalWood; //calcultes 10% of the total amount of tile
	}
	/*-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/

	/*---------------------Total Tile------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
	for (i = 0; i < f.size(); i++) //Tile 'A'
	{
		if (f.at(i).type == "Tile" && f.at(i).floor == 'A')
		{
			totalTileA += f.at(i).area(); //calculates the total area of rooms with tile on floor 'A'
		}
		percentATile = (totalTileA * 0.1) + totalTileA; //calcultes 10% of the total amount of tile
	}

	for (i = 0; i < f.size(); i++) //'Tile 'B'
	{
		if (f.at(i).type == "Tile" && f.at(i).floor == 'B')
		{
			totalTileB += f.at(i).area(); //calculates the total area of rooms with tile on floor 'B'
		}
		percentBTile = (totalTileB * 0.1) + totalTileB; //calcultes 10% of the total amount of tile
	}

	for (i = 0; i < f.size(); i++) //Tile 'C'
	{
		if (f.at(i).type == "Tile" && f.at(i).floor == 'C')
		{
			totalTileC += f.at(i).area(); //calculates the total area of rooms with tile on floor 'C'
		}
		percentCTile = (totalTileC * 0.1) + totalTileC; //calcultes 10% of the total amount of tile
	}

	for (i = 0; i < f.size(); i++) //Tile 'D'
	{
		if (f.at(i).type == "Tile" && f.at(i).floor == 'D')
		{
			totalTileD += f.at(i).area(); //calculates the total area of rooms with tile on floor 'D'
		}
		percentDTile = (totalTileD * 0.1) + totalTileD; //calcultes 10% of the total amount of tile
	}

	for (i = 0; i < f.size(); i++) //Tile 'E'
	{
		if (f.at(i).type == "Tile" && f.at(i).floor == 'E')
		{
			totalTileE += f.at(i).area(); //calculates the total area of rooms with tile on floor 'E'
		}
		percentETile = (totalTileE * 0.1) + totalTileE; //calcultes 10% of the total amount of tile
	}

	for (i = 0; i < f.size(); i++) //Total Tile
	{
		if (f.at(i).type == "Tile")
		{
			totalTile += f.at(i).area(); //calculates the total area of rooms with tile
		}
		totalPercentTile = (totalTile * 0.1) + totalTile; //calcultes 10% of the total amount of tile
	}
	/*-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/

	/*------------------------Table--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/

	outFile << "Square Area in ft^2 by floor and floor type.\n"
		<< "Floor    Carpet    (+10%)     Wood     (+10%)    Tile    (+10%)\n"
		<< "----------------------------------------------------------------\n"
		<< "  A" << setw(10) << fixed << setprecision(0) << totalCarpetA << setw(10) << fixed << setprecision(0) << percentACarpet << setw(10) << fixed << setprecision(0) << totalWoodA << setw(10) << fixed << setprecision(0) << percentAWood << setw(10) << fixed << setprecision(0) << totalTileA << setw(10) << fixed << setprecision(0) << percentATile << "\n"
		<< "  B" << setw(10) << fixed << setprecision(0) << totalCarpetB << setw(10) << fixed << setprecision(0) << percentBCarpet << setw(10) << fixed << setprecision(0) << totalWoodB << setw(10) << fixed << setprecision(0) << percentBWood << setw(10) << fixed << setprecision(0) << totalTileB << setw(10) << fixed << setprecision(0) << percentBTile << "\n"
		<< "  C" << setw(10) << fixed << setprecision(0) << totalCarpetC << setw(10) << fixed << setprecision(0) << percentCCarpet << setw(10) << fixed << setprecision(0) << totalWoodC << setw(10) << fixed << setprecision(0) << percentCWood << setw(10) << fixed << setprecision(0) << totalTileC << setw(10) << fixed << setprecision(0) << percentCTile << "\n"
		<< "  D" << setw(10) << fixed << setprecision(0) << totalCarpetD << setw(10) << fixed << setprecision(0) << percentDCarpet << setw(10) << fixed << setprecision(0) << totalWoodD << setw(10) << fixed << setprecision(0) << percentDWood << setw(10) << fixed << setprecision(0) << totalTileD << setw(10) << fixed << setprecision(0) << percentDTile << "\n"
		<< "  E" << setw(10) << fixed << setprecision(0) << totalCarpetE << setw(10) << fixed << setprecision(0) << percentECarpet << setw(10) << fixed << setprecision(0) << totalWoodE << setw(10) << fixed << setprecision(0) << percentEWood << setw(10) << fixed << setprecision(0) << totalTileE << setw(10) << fixed << setprecision(0) << percentETile << "\n"
		<< "----------------------------------------------------------------\n"
		<< "Totals" << setw(7) << fixed << setprecision(0) << totalCarpet << setw(10) << fixed << setprecision(0) << totalPercentCarpet << setw(10) << fixed << setprecision(0) << totalWood << setw(10) << fixed << setprecision(0) << totalPercentWood << setw(10) << totalTile << setw(10) << fixed << setprecision(0) << totalPercentTile << endl << endl;
	/*-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/

	outFile.close(); //close output file stream 

	return;
}

ostream& operator<<(ostream& out, ACME& O) //overload output operator
{
	cout << O.floor << O.id << " " << O.length << " ft x " << O.width << " ft " << O.type << " " << O.area() << " ft2 ";
	return out;
}

/*-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/

int main()
{
	vector<ACME> data(0); //main vector
	int opselect; //user input
	int number; //number from input string if it is valid
	string value;

	do
	{
		do
		{
			//menu options
			cout << "Welcome to the ACME floor company database\n"
				<< "Options:\n"
				<< "1. File input\n"
				<< "2. Room Update\n"
				<< "3. Statistics\n"
				<< "4. Flooring Totals\n"
				<< "5. File output\n"
				<< "6. Exit" << endl;

			//enter the number but read it in as a string
			cout << "Please enter an option: ";
			getline(cin, value);
			cout << endl;

			if (isvalidInt(value))
			{
				//convert string to char array to int
				opselect = atoi(value.c_str());
				break; //escape loop if we have a valid awnser
			}
			else
			{
				cout << "The number you have entered is an invalid variable." //error message
					<< "Please enter a number between 1-6." << endl;
			}
		} while (1);

		//switch case for each option
		switch (opselect)
		{
		case 1: //File input
			data = finput();
			break;
		case 2: //Room update
			update(data);
			break;
		case 3: //Statistics
			stats(data);
			break;
		case 4: //Flooring totals
			floorplan(data);
			break;
		case 5: //File output
			output(data);
			break;
		default: //Exit
			break;
		}

	} while (opselect != 6);

	system("pause");
	return 1823;
}

bool isvalidInt(string str) //function definition 
{
	bool valid = true; //Is this a valid integer? 
	bool sign = false; //Is there a sign in front of the number
	char x;
	int i, start = 0; //be default, check first character first
	if (str.size() == 0) //check for empty string 
		return false;

	if (str.at(0) == '+' || str.at(0) == '-') //check for sign
	{
		sign = true; //there is a sign 
		start = 1; //start checking characters from second character
	}

	if (sign && str.size() == 1) //if +/- and length 1 
		return false;

	for (i = start; i < str.size(); i++) //check each charafter sign
	{
		x = str.at(i); // assign current char to x 
		if (isdigit(x))
			valid = true; //redundant 
		else
			return false;
	}

	return valid;
}

bool isvalidReal(string str)
{
	int start = 0;
	int i;
	int period = 0; //# of periods
	bool valid = true; //assume a valid integer
	bool sign = false; //assume no sign

	//check for an empty string
	if (str.length() == 0)
		return false;


	//check for a leading sign
	if (str.at(0) == '-' || str.at(0) == '+')
	{
		start = 1; //start checking for digits after the sign
		sign = true;
		if (str.length() == 1)
		{
			return false;
		}
	}

	//check for a leading sign
	if (str.at(0) == '.')
	{
		period++;
		if (str.length() == 1)
		{
			return false;
		}
	}

	//Check that there's at least one character after the sign
	if (sign && str.at(1) == '.')
	{
		if (str.length() == 2)
		{
			return false;
		}
	}

	//Now check the string, which you know has at least one non-sign character
	i = start;
	while (valid && i < str.length())
	{
		if (str.at(i) == '.') //found a non-digit character
			period++; //move to next character
		else if (isdigit(str.at(i)))
			valid = true;
		else
			return false;
		i++; //move to the next character
	}

	if (period > 1)
		return false;

	return valid;
}