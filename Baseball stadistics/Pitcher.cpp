#include "Pitcher.h"


Pitcher::Pitcher(void)
{
}


Pitcher::~Pitcher(void)
{
}

float Pitcher::WHIP()//C�lculo del walks and hits per innings pitched
{
    return (HTS + CS)/TI;
}

float Pitcher::PCL()//C�lculo del Promedio Carreras Limpias
{
    return (CS*TI)/IP;
}
