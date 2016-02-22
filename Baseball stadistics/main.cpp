#include <iostream>
#include <stdlib.h>
#include <iomanip> //Para los manipuladores de entrada con argumentos
#include "Baseball.h"

using namespace std;

int main()
{
        int opt;
    cout << "\n\tAlberto Senior" << "\t" << "Proyecto P1";
	cout << "\t\n\n\t" << "***********MENU************\n\n\n\n";
    system("color 2");
	cout << "1." << "\t" << "Programa Estadisticas de Baseball\n";
	cout << "2." << "\t" << "Salir\n";
	cout << "Elija su opcion: ";
		cin >> opt;

		Baseball baseball;

	cout << endl;

	switch (opt)
	{
	    case 1: 
			baseball.begin();
	    system("cls");
		break;

	case 2: return 0;
		break;


	default: cout << "Invalid number, please enter a number"
		      << " from the entrees above.\n\n";
		break;
	}
	return 0;


}


