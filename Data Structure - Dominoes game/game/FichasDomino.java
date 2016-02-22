package game;

/**
 * Created by AS
 */
public class FichasDomino {
    int numeroSuperior;
    int numeroInferior;

    public FichasDomino(int numeroSuperior, int numeroInferior) {
        this.numeroSuperior = numeroSuperior;
        this.numeroInferior = numeroInferior;
    }

    @Override
    public String toString() 
    {
        if(numeroSuperior == -1 && numeroInferior == -1)

            return "||||";

        String str = "";
        str = "[" + numeroSuperior + ", " + numeroInferior + "]";
        return str;
    }

    public int getNumeroSuperior() 
    {
        return numeroSuperior;
    }

    public void setNumeroSuperior(int numeroSuperior) 
    {
        this.numeroSuperior = numeroSuperior;
    }

    public int getNumeroInferior() {
        return numeroInferior;
    }

    public void setNumeroInferior(int numeroInferior) 
    {
        this.numeroInferior = numeroInferior;
    }

    public boolean isDouble(){
        return numeroInferior == numeroSuperior;
    }

    @Override
    public boolean equals(Object obj) 
    {
        FichasDomino other = (FichasDomino) obj;

        if(this.numeroSuperior == other.numeroSuperior && this.numeroInferior == other.numeroInferior)
            return true;

        if(this.numeroSuperior == other.numeroInferior && this.numeroInferior == other.numeroSuperior)
            return true;

        return false;

    }

    public FichasDomino reverse()
    {
        return new FichasDomino(numeroInferior, numeroSuperior);
    }

    public boolean canBeNextTo(FichasDomino ultimaFicha) 
    {
        return ultimaFicha.numeroInferior == this.numeroSuperior ||
                ultimaFicha.numeroSuperior == this.numeroInferior ||
                ultimaFicha.numeroInferior == this.numeroInferior ||
                ultimaFicha.numeroSuperior == this.numeroSuperior;
    }

    public boolean compareValue(int num1, int num2) 
    {
        return num1 == numeroInferior && num2 == numeroSuperior ||
                num1 == numeroSuperior && num2 == numeroInferior;
    }

    public int getScore() 
    {
        return numeroSuperior + numeroInferior;
    }
}
