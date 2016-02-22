#include "Baseball.h"


Baseball::Baseball(void)
{
}


Baseball::~Baseball(void)
{
}

    void Baseball::begin()
    {
    //Instaciación de la clase de estadística
	Stats stats;

	stats.getData();//Lectura de Datos
	system("cls");//Limpiar la pantalla
	stats.showData();//Desplegar los Datos (dados y calculados)
    }
