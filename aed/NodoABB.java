package aed;

public class NodoABB {

    private int dato;
    private NodoABB izq;
    private NodoABB der;

    public NodoABB (int dato, NodoABB izq, NodoABB der){
        this.dato = dato;
        this.izq = izq;
        this.der = der;
    }

    public NodoABB getIzq(){
        return this.izq;
    }

    public NodoABB getDer(){
        return this.der;
    }

    public int getDato(){
        return this.dato;
    }

    public void setDato(int dato){
        this.dato = dato;
    }

    public void setDer(NodoABB der){
        this.der = der;
    }

    public void setIzq(NodoABB izq){
        this.izq = izq;
    }


}
