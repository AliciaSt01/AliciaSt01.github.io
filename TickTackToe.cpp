/*ENG Tic-Tac_Toe Project 1
Alicia D. St Clair
November 13, 2019*/

#include <iostream> //cout and cin
#include <iomanip> //output formating
#include <ctime> //for randomized number generator
using namespace std; //standard namespace


//declare variables
const int GAME_BOARD = 9; //number of elements in a game
char nums, pos, AI;


//function prototypes
void display(char[]); //void function
void turn(char, char);//asks the player to go first or second
void game(char, char[]); // plays a move
int check(char, char[]); //checks the spaces for use
void gameAI(char, char[]); //computer plays a move
bool endgame(char[]); //game over function
bool play = false;

int main()
{
	cout << "Lets play Tic-Tac-Toe!" << endl << endl;

	char nums[GAME_BOARD] = { '0', '1', '2', '3', '4', '5', '6', '7', '8' }; //initial array
	// '0' - '8' = empty postitions
	// 'X' = 'X'
	// 'O' = 'O'

	do
	{
		//player functions
		display(nums); //displays a new board after every turn
		game(pos, nums); //player turn function
		if (endgame(nums)) //to end and close the game
		{
			display(nums); //displays a new board after every turn
			break;
		}

		//computer
		display(nums);  //displays a new board after every turn
		gameAI(AI, nums); //computer move function
		if (endgame(nums)) //to end and close the game
		{
			display(nums);  //displays a new board after every turn
			break;
		}

		cout << endl;

	} while (play == false);
	cout << endl;

	system("pause"); //pause screen
	return 15; //end program
}

//function definition
void display(char nums[]) //display Tic-Tac-Toe game
{
	int pos;
	char i;

	for (i = 0; i < 8; i++)
	{
			//displays square 1
			if (nums[0] == 'X')
			{
				cout << 'X';
			}
			else if (nums[0] == 'O')
			{
				cout << 'O';
			}
			else
			{
				cout << nums[0];
			}
			cout << "  | ";

			//displays square 2
			if (nums[1] == 'X')
			{
				cout << 'X';
			}
			else if (nums[1] == 'O')
			{
				cout << 'O';
			}
			else
			{
				cout << nums[1];
			}
			cout << "  | ";

			//displays square 3
			if (nums[2] == 'X')
			{
				cout << 'X';
			}
			else if (nums[2] == 'O')
			{
				cout << 'O';
			}
			else
			{
				cout << nums[2];
			}

			//----------------Display vertical lines--------------------//
			cout << endl;

			cout << "---+----+---";

			cout << endl;

			//---------------------------------------------------------//

			//displays square 4
			if (nums[3] == 'X')
			{
				cout << 'X';
			}
			else if (nums[3] == 'O')
			{
				cout << 'O';
			}
			else
			{
				cout << nums[3];
			}
			cout << "  | ";

			//displays square 5
			if (nums[4] == 'X')
			{
				cout << 'X';
			}
			else if (nums[4] == 'O')
			{
				cout << 'O';
			}
			else
			{
				cout << nums[4];
			}
			cout << "  | ";

			//displays square 6
			if (nums[5] == 'X')
			{
				cout << 'X';
			}
			else if (nums[5] == 'O')
			{
				cout << 'O';
			}
			else
			{
				cout << nums[5];
			}

			//----------------Display verticlal lines--------------------//
			cout << endl;

			cout << "---+----+---";

			cout << endl;

			//----------------------------------------------------------//

			//displays square 7
			if (nums[6] == 'X')
			{
				cout << 'X';
			}
			else if (nums[6] == 'O')
			{
				cout << 'O';
			}
			else
			{
				cout << nums[6];
			}
			cout << "  | ";

			//displays square 8
			if (nums[7] == 'X')
			{
				cout << 'X';
			}
			else if (nums[7] == 'O')
			{
				cout << 'O';
			}
			else
			{
				cout << nums[7];
			}
			cout << "  | ";

			//displays square 9
			if (nums[8] == 'X')
			{
				cout << 'X';
			}
			else if (nums[8] == 'O')
			{
				cout << 'O';
			}
			else
			{
				cout << nums[8];
			}
			break;
	}
}

void game(char per, char nums[]) //player move
{
	bool takein = false;
	int pos;

	do
	{
		cout << "\n\nEnter a position number (0 - 8): "; //user input
		cin >> pos;

		takein = true;
		if (nums[pos] != 'O' && nums[pos] != 'X')
		{
			nums[pos] = 'X'; //replaces the character with 'X' in the display
		}
		else
			cout << "That postition is already taken.\n Please enter a valid position.\n" << endl;
	} while (!takein);
}

void gameAI(char comp, char nums[]) //computer move
{
	int i;
	bool compturn = false;
	int pos;

		/*----------------------For the computer trying to win------------------------------*/

		//Checks every combination for row 0, 1, 2
		if (nums[0] == 'O' && nums[1] == 'O' && nums[2] != 'O' && nums[2] != 'X') //for 0, 1, 2
			nums[2] = 'O';

		else if (nums[1] == 'O' && nums[2] == 'O' && nums[0] != 'O' && nums[0] != 'X') //for 1, 2, 0
			nums[0] = 'O';

		else if (nums[2] == 'O' && nums[0] == 'O' && nums[1] != 'O' && nums[1] != 'X') //for 2, 0, 1
			nums[1] = 'O';

		//Checks every combination for row 3, 4, 5
		else if (nums[3] == 'O' && nums[4] == 'O' && nums[5] != 'O' && nums[5] != 'X') //for 3, 4, 5
			nums[5] = 'O';

		else if (nums[4] == 'O' && nums[5] == 'O' && nums[3] != 'O' && nums[3] != 'X') //for 4, 5, 3
			nums[3] = 'O';

		else if (nums[5] == 'O' && nums[3] == 'O' && nums[4] != 'O' && nums[4] != 'X') //for 5, 3, 4
			nums[3] = 'O';

		//Checks every combination for row 6, 7, 8
		else if (nums[6] == 'O' && nums[7] == 'O' && nums[8] != 'O' && nums[8] != 'X') //for 6, 7, 8
			nums[8] = 'O';

		else if (nums[7] == 'O' && nums[8] == 'O' && nums[6] != 'O' && nums[6] != 'X') //for 7, 8, 6
			nums[6] = 'O';

		else if (nums[8] == 'O' && nums[6] == 'O' && nums[7] != 'O' && nums[7] != 'X') //for 8, 6, 7
			nums[7] = 'O';

		//Checks every combination for column 0, 3, 6
		else if (nums[0] == 'O' && nums[3] == 'O' && nums[6] != 'O' && nums[6] != 'X') //for 0, 3, 6
			nums[6] = 'O';

		else if (nums[3] == 'O' && nums[6] == 'O' && nums[0] != '0' && nums[0] != 'X') //for 3, 6, 0
			nums[0] = 'O';

		else if (nums[6] == 'O' && nums[0] == 'O' && nums[3] != 'O' && nums[7] != 'X') //for 6, 0, 3
			nums[7] = 'O';

		//Checks every combination for column 1, 4, 7
		else if (nums[4] == 'O' && nums[7] == 'O' && nums[1] != 'O' && nums[1] != 'X') //for 4, 7, 1
			nums[1] = 'O';

		else if (nums[7] == 'O' && nums[1] == 'O' && nums[4] != 'O' && nums[4] != 'X') //for 7, 1, 4
			nums[4] = 'O';

		else if (nums[1] == 'O' && nums[4] == 'O' && nums[7] != 'O' && nums[7] != 'X') //for 1, 4, 7
			nums[7] = 'O';

		//Checks every combination for column 2, 5, 8
		else if (nums[2] == 'O' && nums[5] == 'O' && nums[8] != 'O' && nums[8] != 'X') //for 2, 5, 8
			nums[8] = 'O';

		else if (nums[5] == 'O' && nums[8] == 'O' && nums[2] != 'O' && nums[2] != 'X') //for 5, 8, 2
			nums[2] = 'O';

		else if (nums[8] == 'O' && nums[2] == 'O' && nums[5] != 'O' && nums[5] != 'X') //for 8, 2, 5
			nums[5] = 'O';

		//Checks every combination for diagonal 0, 4, 8
		else if (nums[0] == 'O' && nums[4] == 'O' && nums[8] != 'O' && nums[8] != 'X') //for 0, 4, 8
			nums[8] = 'O';

		else if (nums[4] == 'O' && nums[8] == 'O' && nums[0] != 'O' && nums[0] != 'X') //for 4, 8, 0
			nums[0] = 'O';

		else if (nums[8] == 'O' && nums[0] == 'O' && nums[4] != 'O' && nums[4] != 'X') //for 8, 0, 4
			nums[4] = 'O';

		//Checks every combination for diagonal 3, 4, 6
		else if (nums[3] == 'O' && nums[4] == 'O' && nums[6] != 'O' && nums[6] != 'X') //for 3, 4, 6
			nums[6] = 'O';

		else if (nums[4] == 'O' && nums[6] == 'O' && nums[3] != 'O' && nums[3] != 'X') //for 4, 6, 3
			nums[3] = 'O';

		else if (nums[6] == 'O' && nums[3] == 'O' && nums[4] != 'O' && nums[4] != 'X') //for 6, 3, 4
			nums[4] = 'O';


		/*----------------For the computer to block the player---------------------------------*/

		//Checks every combination for row 0, 1, 2
		else if (nums[0] == 'X' && nums[1] == 'X' && nums[2] != 'X' && nums[2] != 'O') //for 0, 1, 2
			nums[2] = 'O';

		else if (nums[1] == 'X' && nums[2] == 'X' && nums[0] != 'X' && nums[0] != 'O') //for 1, 2, 0
			nums[0] = 'O';

		else if (nums[2] == 'X' && nums[0] == 'X' && nums[1] != 'X' && nums[1] != 'O') //for 2, 0, 1
			nums[1] = 'O';

		//Checks every combination for row 3, 4, 5
		else if (nums[3] == 'X' && nums[4] == 'X' && nums[5] != 'X' && nums[5] != 'O') //for 3, 4, 5
			nums[5] = 'O';

		else if (nums[4] == 'X' && nums[5] == 'X' && nums[3] != 'X' && nums[3] != 'O') //for 4, 5, 3
			nums[3] = 'O';

		else if (nums[5] == 'X' && nums[3] == 'X' && nums[4] != 'X' && nums[4] != 'O') //for 5, 3, 4
			nums[4] = 'O';

		//Checks every combination for row 6, 7, 8
		else if (nums[6] == 'X' && nums[7] == 'X' && nums[8] != 'X' && nums[8] != 'O') //for 6, 7, 8
			nums[8] = 'O';

		else if (nums[7] == 'X' && nums[8] == 'X' && nums[6] != 'X' && nums[6] != 'O') //for 7, 8, 6
			nums[6] = 'O';

		else if (nums[8] == 'X' && nums[6] == 'X' && nums[7] != 'X' && nums[7] != 'O') //for 8, 6, 7
			nums[7] = 'O';

		//Checks every combination for column 0, 3, 6
		else if (nums[0] == 'X' && nums[3] == 'X' && nums[6] != 'X' && nums[6] != 'O') //for 0, 3, 6
				nums[6] = 'O';

		else if (nums[3] == 'X' && nums[6] == 'X' && nums[0] != 'X' && nums[0] != 'O') //for 3, 6, 0
				nums[0] = 'O';

		else if (nums[6] == 'X' && nums[0] == 'X' && nums[3] != 'X' && nums[3] != 'O') //for 6, 0, 3
				nums[7] = 'O';

		//hecks every combination for column 1, 4, 7
		else if (nums[4] == 'X' && nums[7] == 'X' && nums[1] != 'X' && nums[1] != 'O') //for 4, 7, 1   
				nums[1] = 'O';

		else if (nums[7] == 'X' && nums[1] == 'X' && nums[4] != 'X' && nums[4] != 'O') //for 7, 1, 4
				nums[4] = 'O';

		else if (nums[1] == 'X' && nums[4] == 'X' && nums[7] != 'X' && nums[7] != 'O') //for 1, 4, 7
				nums[7] = 'O';

		//Checks every combination for column 2, 5, 8
		else if (nums[2] == 'X' && nums[5] == 'X' && nums[8] != 'X' && nums[8] != 'O') //for 2, 5, 8
				nums[8] = 'O';

		else if (nums[5] == 'X' && nums[8] == 'X' && nums[2] != 'X' && nums[2] != 'O') //for 5, 8, 2
				nums[2] = 'O';

		else if (nums[8] == 'X' && nums[2] == 'X' && nums[5] != 'X' && nums[5] != 'O') //for 8, 2, 5
				nums[5] = 'O';

		//Checks every combination for diagonal 0, 4, 8
		else if (nums[0] == 'X' && nums[4] == 'X' && nums[8] != 'X' && nums[8] != 'O') //for 0, 4, 8
				nums[8] = 'O';

		else if (nums[4] == 'X' && nums[8] == 'X' && nums[0] != 'X' && nums[0] != 'O') //for 4, 8, 0
				nums[0] = 'O';

		else if (nums[8] == 'X' && nums[0] == 'X' && nums[4] != 'X' && nums[4] != 'O') //for 8, 0, 4
				nums[4] = 'O';

		//Checks every combination for diagonal 3, 4, 6
		else if (nums[3] == 'X' && nums[4] == 'X' && nums[6] != 'X' && nums[6] != 'O') //for 3, 4, 6
				nums[6] = 'O';

		else if (nums[4] == 'X' && nums[6] == 'X' && nums[3] != 'X' && nums[3] != 'O') //for 4, 6, 3
				nums[3] = 'O';

		else if (nums[6] == 'X' && nums[3] == 'X' && nums[4] != 'X' && nums[4] != 'O') //for 6, 3, 4
				nums[4] = 'O';

		/*-------------------------For when neither above applies----------------------------------------------------*/
			else
			{
				do
				{
					pos = rand() % 9; //random number generator

					if (nums[pos] != 'O' && nums[pos] != 'X') // checks position for computer or player inputs
					{
						nums[pos] = 'O'; //replaces the character with a 'O' in the board display
						break;
					}
				} while (1); //returns false
				cout << endl;
			}
		cout << endl;
}

bool endgame(char nums[]) //ends game if winner or draw
{
	play = false;
	int i;
	int count = 0; //counter

	/*------------If the computer wins---------------------------------------------------------------------------------*/
	if (nums[0] == 'O' && nums[1] == 'O' && nums[2] == 'O') //checks row 0, 1, 2 for 3 in a row
	{
		play = true;
		cout << "Ha! Looks like you need more training!" << endl; 
		return play;
	}
	else if (nums[3] == 'O' && nums[4] == 'O' && nums[5] == 'O') //checks row 3, 4, 5 for 3 in a row
	{
		play = true;
		cout << "Ha! Looks like you need more training!" << endl;
		return play;
	}
	else if (nums[6] == 'O' && nums[7] == 'O' && nums[8] == 'O') //checks row 6, 7, 8 for 3 in a row
	{
		play = true;
		cout << "Ha! Looks like you need more training!" << endl;
		return play;
	}
	else if (nums[0] == 'O' && nums[3] == 'O' && nums[6] == 'O') //checks column 0, 3, 6 for 3 in a row
	{
		play = true;
		cout << "Ha! Looks like you need more training!" << endl;
		return play;
	}
	else if (nums[1] == 'O' && nums[4] == 'O' && nums[7] == 'O') //checks column 1, 4, 7 for 3 in a row
	{
		play = true;
		cout << "Ha! Looks like you need more training!" << endl;
		return play;
	}
	else if (nums[2] == 'O' && nums[5] == 'O' && nums[8] == 'O') //checks column 2, 5, 8 for 3 in a row
	{
		play = true;
		cout << "Ha! Looks like you need more training!" << endl;
		return play;
	}
	else if (nums[0] == 'O' && nums[4] == 'O' && nums[8] == 'O') //checks diagonal 0, 4, 8 for 3 in a row
	{
		play = true;
		cout << "Ha! Looks like you need more training!" << endl;
		return play;
	}
	else if (nums[2] == 'O' && nums[4] == 'O' && nums[6] == 'O') //checks diagonal 2, 4, 6 for 3 in a row
	{
		play = true;
		cout << "Ha! Looks like you need more training!" << endl;
		return play;
	}

	/*-------------------If the player wins-----------------------------------------------------------------*/
	else if (nums[0] == 'X' && nums[1] == 'X' && nums[2] == 'X') //checks row 0, 1, 2 for 3 in a row
	{
		play = true;
		cout << "I have under-estimated your power... Congradulations! You have defeated me!" << endl;
		return play;
	}
	else if (nums[3] == 'X' && nums[4] == 'X' && nums[5] == 'X') //checks row 3, 4, 5 for 3 in a row
	{
		play = true;
		cout << "I have under-estimated your power... Congradulations! You have defeated me!" << endl;
		return play;
	}
	else if (nums[6] == 'X' && nums[7] == 'X' && nums[8] == 'X') //checks row 6, 7, 8 for 3 in a row
	{
		play = true;
		cout << "I have under-estimated your power... Congradulations! You have defeated me!" << endl;
		return play;
	}
	else if (nums[0] == 'X' && nums[3] == 'X' && nums[6] == 'X') //checks column 0, 3, 6 for 3 in a row
	{
		play = true;
		cout << "I have under-estimated your power... Congradulations! You have defeated me!" << endl;
		return play;
	}
	else if (nums[1] == 'X' && nums[4] == 'X' && nums[7] == 'X') //checks column 1, 4, 7 for 3 in a row
	{
		play = true;
		cout << "I have under-estimated your power... Congradulations! You have defeated me!" << endl;
		return play;
	}
	else if (nums[2] == 'X' && nums[5] == 'X' && nums[8] == 'X') //checks column 2, 5, 8 for 3 in a row
	{
		play = true;
		cout << "I have under-estimated your power... Congradulations! You have defeated me!" << endl;
		return play;
	}
	else if (nums[0] == 'X' && nums[4] == 'X' && nums[8] == 'X') //checks diagonal 0, 4, 8 for 3 in a row
	{
		play = true;
		cout << "I have under-estimated your power... Congradulations! You have defeated me!" << endl;
		return play;
	}
	else if (nums[2] == 'X' && nums[4] == 'X' && nums[6] == 'X') //checks diagonal 2, 4, 6 for 3 in a row
	{
		play = true;
		cout << "I have under-estimated your power... Congradulations! You have defeated me!" << endl;
		return play;
	}

	/*--------------------------If the game ends in a draw--------------------------------------------------*/
	else
	{
	/*--------Checks every space to see if it is empty or taken----------------------------*/
			if (nums[0] == 'O' || nums[0] == 'X') //space 0
			{
				count++;
			}
			if (nums[1] == 'O' || nums[1] == 'X') //space 1
			{
				count++;
			}
			if (nums[2] == 'O' || nums[2] == 'X') //space 2
			{
				count++;
			}
			if (nums[3] == 'O' || nums[3] == 'X') //space 3
			{
				count++;
			}
			if (nums[4] == 'O' || nums[4] == 'X') //space 4
			{
				count++;
			}
			if (nums[5] == 'O' || nums[5] == 'X') //space 5
			{
				count++;
			}
			if (nums[6] == 'O' || nums[6] == 'X') //space 6
			{
				count++;
			}
			if (nums[7] == 'O' || nums[7] == 'X') //space 7
			{
				count++;
			}
			if (nums[8] == 'O' || nums[8] == 'X') //space 8
			{
				count++;
			}
			/*-----------For if every space is taken-------------------------------------*/
			if (count == 9)
			{
				play = true;
				cout << "We both need to train harder!" << endl;
				return play;
			}
	}
	return false;
}