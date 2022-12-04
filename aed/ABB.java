package aed;

public class ABB {

    static class Nodo {
        int dato;
        Nodo izq, der;
    }
    Nodo raiz;

    public ABB(){
        raiz = null;
    }

    public void insertar(int dato){
        Nodo nuevo = new Nodo ();
        nuevo.dato = dato;
        nuevo.izq = null;
        nuevo.der = null;
        if(raiz == null){
            raiz = nuevo;
        }else{
            Nodo anterior = null, nodo;
            nodo = raiz;
            while(nodo != null){
                anterior = nodo;
                if(dato < nodo.dato) {
                    nodo = nodo.izq;
                }else{
                    nodo = nodo.der;
                }
            }
            if(dato < anterior.dato) {
                anterior.izq = nuevo;
            }else{
                anterior.der = nuevo;
            }
        }
    }

    private void imprimirPre(Nodo nodo){
        if(nodo != null){
            System.out.print(nodo.dato + " ");
            imprimirPre (nodo.izq);
            imprimirPre (nodo.der);
        }
    }

    public void imprimirPre(){
        imprimirPre (raiz);
        System.out.println();
    }

    private void imprimirIn(Nodo nodo){
        if(nodo != null){
            imprimirIn (nodo.izq);
            System.out.print(nodo.dato + " ");
            imprimirIn (nodo.der);
        }
    }

    public void imprimirIn(){
        imprimirIn (raiz);
        System.out.println();
    }

    private void imprimirPost(Nodo nodo){
        if(nodo != null){
            imprimirPost (nodo.izq);
            imprimirPost (nodo.der);
            System.out.print(nodo.dato + " ");
        }
    }

    public void imprimirPost(){
        imprimirPost (raiz);
        System.out.println();
    }

    public boolean buscar(int num) {
        Nodo nodo = raiz;
        while (nodo != null) {
            if (num == nodo.dato) {
                return true;
            }else {
                if (num > nodo.dato) {
                    nodo = nodo.der;
                } else {
                    nodo = nodo.izq;
                }
            }
        }
        return false;
    }

    public int sumaTotal(Nodo nodo){
        int suma = 0;
        if(nodo != null){
            suma += nodo.dato;
            suma += sumaTotal(nodo.izq) + sumaTotal(nodo.der);
        }
        return suma;
    }

    public int altura(Nodo nodo){
        if(nodo != null){
            if(altura(nodo.der) >= altura(nodo.izq)){
                return altura(nodo.der) + 1;
            }
            else{
                return altura(nodo.izq) + 1;
            }
        }
        return -1;
    }

    public int hojas(Nodo nodo){
//        int hojas;
        if(nodo != null) {
            if (nodo.der == null && nodo.izq == null) {
                return 1;
            } else {
                return (hojas(nodo.der) + hojas(nodo.izq));
            }
        }
        return 0;
    }




    public int contarNodos(Nodo nodo){

        int contador = 1;
        if(nodo.der!=null) {
            contador += contarNodos(nodo.der);
        }
        if(nodo.izq!=null) {
            contador += contarNodos(nodo.izq);
        }
        return contador;
    }

    public int alturaTotal() {
        Nodo nodo = raiz;
        return alturaTotal(nodo);
    }

    static int contador = 0;
    public int alturaTotal(Nodo nodo) {
        if(nodo != null) {
            if(tieneHijos(nodo)) {
                contador++;
                if(!tieneHijos(nodo.der) || !tieneHijos(nodo.izq))
                    return contador;
            }
            contador++;
        }
        return contador;
    }

    public boolean tieneHijos(Nodo nodo) {
        if(nodo.izq == null && nodo.der == null)
            return false;
        return true;
    }

}