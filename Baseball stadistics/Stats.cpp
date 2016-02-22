#include "Stats.h"

Stats::Stats(void)
{
}


Stats::~Stats(void)
{
}

//Metodo para leer los datos de los jugadores
void Stats::getData()
{
	int i;

	//Leyendo la cantidad de jugadores
	cout << "Cuantos jugadores quiere introducir (1-20): ";
	cin >> players;
	cout << endl;

	checkPlayers(); //Funcion que valida el # de jugadores.

	//Un poco de estética
	system("cls");
	system("color 2");

	//Ciclo for para mi entrada de datos dependiendo el # de jugadores.
	for(i=0; i<players; i++)
	{
		char tipo = ' ';
		//Informacion del jugador
		cout << "\t\t****Programa Estadisticas de Baseball!*****\n\n\n\n";
		cout <<	"Tipo de Jugador #" << i+1 << ": 'P' para Pitcher y 'B' para Bateador ";
		cin >> tipo;
		system("cls");

		//A partir del tipo de jugador, se instancia este tipo
		switch(tipo){

		case 'P': 
			{
			Pitcher pitcher;

			cout <<	"Nombre del jugador #" << i+1 << "'s [Primer nombre]: ";
			cin >> pitcher.FirstName;

			cout << "Apellido del jugador #" << i+1 << "'s [Primer apellido]: ";
			cin >> pitcher.LastName;

			//Datos del Pitcher
			cout << "Juegos jugados del jugador #" << i+1 << ": "; //JJ
			cin >> pitcher.JJ; //JJ, juegos jugados

			cout << "Juegos iniciados #" << i+1 << ": "; //GA
			cin >> pitcher.GA; //Juegos iniciados

			cout << "Cuantos Innings en Total #" << i+1 << ": "; //P
			cin >> pitcher.TI; //Cuantos Innings en total

			cout << "Entradas lanzadas #" << i+1 << ": ";
			cin >> pitcher.EL; //Entradas lanzadas *FLOAT*

			cout << "Innings Pichados #" << i+1 << ": ";
			cin >> pitcher.IP; //Innings Pichados *ERA = carrerasXtotalinnings/innings pichados*

			cout << "Cuantos hits #" << i+1 << ": ";
			cin >> pitcher.HTS; //hits

			cout << "Cuantas carreras #" << i+1 << ": "; //D
			cin >> pitcher.CS; //Cuantas carreras

			cout << "Carreras limpias obtuvo el jugador #" << i+1 << ": "; //CL
			cin >> pitcher.CL; //Carreras limpias

			cout << "Cuantas bases por bola #" << i+1 << ": "; //BB
			cin >> pitcher.BB2; //Bases por bola

			cout << "Cuantos ponches #" << i+1 << ": "; //P
			cin >> pitcher.PC; //Ponches

			cout << "Cuantas victorias #" << i+1 << ": "; //G
			cin >> pitcher.VS; //Victorias

			cout << "Cuantas derrotas #" << i+1 << ": "; //P
			cin >> pitcher.DS; //Cuantas derrotas


			InsertPlayer(pitcher);
				  }
			break;


		case 'B':
			{Bateador bateador;

			cout <<	"Nombre del jugador #" << i+1 << "'s [Primer nombre]: ";
			cin >> bateador.FirstName;

			cout << "Apellido del jugador #" << i+1 << "'s [Primer apellido]: ";
			cin >> bateador.LastName;

			//Datos del Bateador
			cout << "Cual fue el AB [Al bate] del jugador #" << i+1 << ": ";
			cin >> bateador.AB; //AB = Al Bate

			cout << "Cuantos sencillos dio el jugador #" << i+1 << ": ";
			cin >> bateador.singles; //Sencillos

			cout << "Cuantos dobles bateo el jugador #" << i+1 << ": ";
			cin >> bateador.doubles; //dobles

			cout << "Cuantos triples bateo el jugador #" << i+1 << ": ";
			cin >> bateador.triples; //Triples

			cout << "Cuantos homeruns dio el jugador #" << i+1 << ": ";
			cin >> bateador.HR; //Homeruns

			cout << "Cuantas bases por bola tuvo el jugador #" << i+1 << ": ";
			cin >> bateador.walks; //Base por bola

			cout << "Total de bases del jugador #" << i+1 << ": ";
			cin >> bateador.TB; //Total de Bases

			cout << "Total de faltas que le han hecho jugador #" << i+1 << ": ";
			cin >> bateador.HBP; //Total de faltas que le han hecho

			cout << "Total de Sacrifice Flies del jugador #" << i+1 << ": ";
			cin >> bateador.SF;                    //Service Flies

			InsertPlayer(bateador);
			}
			break;
		}
		cout << endl;
	}
}

//Metodo para desplegar los datos estadisticos de los jugadores
void Stats::showData()
{
	int i;//variable contadora/posición

	cout << "RESULTADOS!";
	cout << endl << endl;
	cin.clear();
	cin.sync();

	///Imprimiendo Bateadores
	if(listaBateadores.cantidad>0)//Validando si hay Bateadores en la lista
	{

		cout << "Bateadores:" << endl;
		//Imprimiendo la cabeza de la tabla
		//Utilizé setw para rellenar los campos [setw(14) me dice que el tamaño del campo es de 14 caracteres]
		cout << "Nombre" << setw(12) << "Apellido" << setw(6) << "AB" << setw(6);
		cout << "1B" << setw(6) << "2B" << setw(6) << "3B" << setw(6);
		cout << setw(6) << "HR" << setw(6) << "BB" << setw(6);
		cout << setw(6) << "HBP" << setw(6) << "SF" << setw(6) << "TB";
		cout << setw(7) << "batAvg" << setw(6) << "SLG" << setw(6) << "OBP" << setw(6) << "OPS" << "\n";

		//Imprimiendo los datos de cada Bateador
		for(i=0; i<listaBateadores.cantidad; i++)
		{
			cout << listaBateadores.lista[i].FirstName
				<< setw(14) << listaBateadores.lista[i].LastName
				<< setw(6) << (int) listaBateadores.lista[i].AB
				<< setw(6) << (int) listaBateadores.lista[i].singles
				<< setw(6) << (int) listaBateadores.lista[i].doubles
				<< setw(6) << (int) listaBateadores.lista[i].triples
				<< setw(6) << (int) listaBateadores.lista[i].HR
				<< setw(6) << (int) listaBateadores.lista[i].walks
				<< setw(6) << (int) listaBateadores.lista[i].HBP
				<< setw(6) << (int) listaBateadores.lista[i].SF
				<< setw(6) << (int) listaBateadores.lista[i].TB
				<< setw(6) << listaBateadores.lista[i].batAvg()
				<< setw(6) << listaBateadores.lista[i].SLG()
				<< setw(6) << listaBateadores.lista[i].OBP()
				<< setw(6) << listaBateadores.lista[i].OPS()
				<< endl;
					system("pause");
		}
	}

	///Imprimiendo Pitchers
	if(listaPitchers.cantidad>0)//Validando si hay Pitchers en la lista
	{
		cout << "Pitchers:" << endl;
		//Imprimiendo la cabeza de la tabla
		cout << "Nombre" << setw(13) << "Apellido" << setw(6) << "JJ" << setw(6) << "GA" << setw(6) << "EL";
		cout << setw(6) << "IP" << setw(6) << "HTS" << setw(6) << "CS" << setw(6) << "CL" << setw(6) << "BB2";
		cout << setw(6) << "PC" << setw(6) << "VS" << setw(6) << "DS"  << setw(6) << "TI";
		cout << setw(6) << "WHIP" << setw(6) << "PCL" << endl;

		//Imprimiendo los datos de cada Pitcher
		for(i=0; i<listaPitchers.cantidad; i++)
		{
			cout << listaPitchers.lista[i].FirstName
				<< setw(14)<<       listaPitchers.lista[i].LastName
				<< setw(6) << (int) listaPitchers.lista[i].JJ
				<< setw(6) << (int) listaPitchers.lista[i].GA
				<< setw(6) << (int) listaPitchers.lista[i].EL
				<< setw(6) << (int) listaPitchers.lista[i].IP
				<< setw(6) << (int) listaPitchers.lista[i].HTS
				<< setw(6) << (int) listaPitchers.lista[i].CS
				<< setw(6) << (int) listaPitchers.lista[i].CL
				<< setw(6) << (int) listaPitchers.lista[i].BB2
				<< setw(6) << (int) listaPitchers.lista[i].PC
				<< setw(6) << (int) listaPitchers.lista[i].VS
				<< setw(6) << (int) listaPitchers.lista[i].DS
				<< setw(6) << (int) listaPitchers.lista[i].TI
				<< setw(4) << listaPitchers.lista[i].WHIP()
				<< setw(7) << listaPitchers.lista[i].PCL()
				<< endl;
					system("pause");
		}
	}
}

//Metodo para validar que no se puedan insertar más del límite de jugadores
void Stats::checkPlayers()
{
	while((players < 1) || (players > 20)) //Si el valor no está entre 1 y 20...
	{
		//se pide que vuelva a ingresar el valor
		cout << "Debe ser un valor entre 1-20!!!\n";
		cout << "Reingrese un valor: ";
		cin >> players;
	}
}

void Stats::InsertPlayer(Pitcher jugador)//Metodo de Insercion de un jugador tipo Pitcher
{
	listaPitchers.lista[listaPitchers.cantidad]=jugador;//Se inserta el nuevo jugador en la lista de jugadores.
	listaPitchers.cantidad++;//Se incrementa en 1 la cantidad de jugadores.

}

void Stats::InsertPlayer(Bateador jugador)//Metodo de Insercion de un jugador tipo Pitcher
{
	listaBateadores.lista[listaBateadores.cantidad]=jugador;//Se inserta el nuevo jugador en la lista de jugadores.
	listaBateadores.cantidad++;//Se incrementa en 1 la cantidad de jugadores.
}