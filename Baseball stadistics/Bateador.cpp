#include "Bateador.h"


Bateador::Bateador(void)
{
}


Bateador::~Bateador(void)
{
}

float Bateador::batAvg()//C�lculo del Bat Average
{
    return singles/AB;
}

float Bateador::SLG()//C�lculo del Sluggin Percentage
{
    return TB/AB;
}

float Bateador::OBP()//C�lculo del On-base percentage
{
    return (singles + walks + HBP) / (AB + walks + SF + HBP);
}

float Bateador::OPS()//C�lculo del On-base Plus Slugging Percentage
{
    return OBP() + SLG();
}
