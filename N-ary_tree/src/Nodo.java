
import java.util.ArrayList;
import java.util.List;
public class Nodo<T> {
    private T dato;
    private List<Nodo<T>> hijos;
    private Nodo<T> padre;

    public Nodo(T dato) {
        this.dato = dato;
        this.hijos = new ArrayList<>();
    }

    public Nodo(Nodo<T> nodo) {
        this.dato = (T) nodo.getDato();
        hijos = new ArrayList<>();
    }

    public void agregarHijo(Nodo<T> hijo) {
        hijo.setPadre(this);
        hijos.add(hijo);

    }

    public void setHijos(List<Nodo<T>> hijos) {
        for (Nodo<T> hijo : hijos)
            hijo.setPadre(this);
        this.hijos = hijos;
    }

    public void eliminarHijos() {
        this.hijos.clear();
    }

    public Nodo<T> eliminarHijoEn(int posicion) {
        return hijos.remove(posicion);
    }

    public T getDato() {
        return this.dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }


    public Nodo<T> getPadre() {
        return this.padre;
    }


    public void setPadre(Nodo<T> padre) {
        this.padre = padre;
    }


    public List<Nodo<T>> getHijos() {
        return this.hijos;
    }


    public Nodo<T> getHijoEn(int posicion) {
        return hijos.get(posicion);
    }


    @Override
    public boolean equals(Object obj) {
        if (null == obj)
            return false;
        if (obj instanceof Nodo) {

            if (((Nodo<?>) obj).getDato().equals(this.dato))
                return true;
        }
        return false;
    }


    @Override
    public String toString() {
        return this.dato.toString();
    }

    private Nodo<T> raiz;
    static int tamaño=1;
    public void ArbolNArio(Nodo<T> raiz) {
        this.raiz = raiz;

    }

    public boolean vacio() {
        return raiz == null;
    }

    public Nodo<T> getRaiz() {
        System.out.println("La raiz es: "+raiz);
        return raiz;
    }


    public void setRaiz(Nodo<T> raiz) {
        this.raiz = raiz;
    }

    public boolean existe(T clave) {
        return encontrar(raiz, clave);
    }

    public ArrayList<Nodo<T>> geTamaño() {
        ArrayList<Nodo<T>> size = new ArrayList<Nodo<T>>();
        Size(raiz, size);
        System.out.println("El tamaño es: " +tamaño);
        return size;
    }


    public void Size(Nodo<T> nodo, ArrayList<Nodo<T>> size) {
        for (Nodo<T> hijo : nodo.getHijos()) {
            Size(hijo, size);
            tamaño++;
        }
    }



    public int getNumeroNodos() {
        return getNumeroDescendientes(raiz) + 1;
    }

    public int getNumeroDescendientes(Nodo<T> nodo) {
        int n = nodo.getHijos().size();
        for (Nodo<T> hijo : nodo.getHijos())
            n += getNumeroDescendientes(hijo);
        System.out.println(n);
        return n;
    }

    boolean encontrar(Nodo<T> nodo, T nodoClave) {
        if (nodo.getDato().equals(nodoClave))
            return true;
        else {
            for (Nodo<T> hijo : nodo.getHijos()) {
                if (encontrar(hijo, nodoClave)){
                    System.out.println("Si esta el valor "+nodoClave);
                    return true;}



            }


        }
        return false;


    }

    public Nodo<T> encontrarNodo(Nodo<T> nodo, T nodoClave) {
        if (nodo == null)
            return null;
        if (nodo.getDato().equals(nodoClave))
            return nodo;
        else {
            Nodo<T> cnodo = null;
            for (Nodo<T> hijo : nodo.getHijos())
                if ((cnodo = encontrarNodo(hijo, nodoClave)) != null)
                    return cnodo;
        }
        return null;
    }

    public ArrayList<Nodo<T>> getPreOrder() {
        ArrayList<Nodo<T>> preOrder = new ArrayList<Nodo<T>>();
        construirPreOrder(raiz, preOrder);
        System.out.println("El pre orden es " +preOrder);
        return preOrder;
    }


    public ArrayList<Nodo<T>> getPostOrder() {
        ArrayList<Nodo<T>> postOrder = new ArrayList<Nodo<T>>();
        construirPostOrder(raiz, postOrder);
        System.out.println("El pos orden es " +postOrder);
        return postOrder;
    }

    public void construirPreOrder(Nodo<T> nodo, ArrayList<Nodo<T>> preOrder) {

        preOrder.add(nodo);

        for (Nodo<T> hijo : nodo.getHijos()) {
            construirPreOrder(hijo, preOrder);




        }


    }

    public void construirPostOrder(Nodo<T> nodo, ArrayList<Nodo<T>> postOrder) {
        for (Nodo<T> hijo : nodo.getHijos()) {

            construirPostOrder(hijo, postOrder);
        }
        postOrder.add(nodo);
    }

    public ArrayList<Nodo<T>> caminoMasLargo() {
        ArrayList<Nodo<T>> camino = null;
        int max = 0;
        for (ArrayList<Nodo<T>> ruta : getRamas()) {
            if (ruta.size() > max) {
                max = ruta.size();
                camino = ruta;
            }
        }
        System.out.println(camino);
        return camino;
    }

    public int getCaminoMasLargo()   {
        return caminoMasLargo().size();   }

    public ArrayList<ArrayList<Nodo<T>>> getRamas() {
        ArrayList<ArrayList<Nodo<T>>> rutas = new ArrayList<ArrayList<Nodo<T>>>();
        ArrayList<Nodo<T>> camino = new ArrayList<Nodo<T>>();
        getPath(raiz, camino, rutas);
        return rutas;   }

    public void getPath(Nodo<T> nodo, ArrayList<Nodo<T>> camino,  ArrayList<ArrayList<Nodo<T>>> rutas) {
        if (camino == null)
            return;
        camino.add(nodo);
        if (nodo.getHijos().size() == 0) {
            rutas.add(clone(camino));     }

        for (Nodo<T> hijo : nodo.getHijos())
            getPath(hijo, camino, rutas);
        int index = camino.indexOf(nodo);

        for (int i = index; i < camino.size(); i++)
            camino.remove(index);   }

    private ArrayList<Nodo<T>> clone(ArrayList<Nodo<T>> list) {
        ArrayList<Nodo<T>> lista = new ArrayList<Nodo<T>>();
        for (Nodo<T> nodo : list)
            lista.add(new Nodo<T>(nodo));

        return lista;   }
}

class ArbolesNArios {
    public static void main(String[] args) {
        ArbolesNArios arbol=new ArbolesNArios();

        Nodo nodo=new Nodo(2);
        nodo.setRaiz(nodo);
        Nodo node=new Nodo(5);
        Nodo nodi=new Nodo(10);
        Nodo nodt=new Nodo(100);

        Nodo nt=new Nodo(0);
        Nodo nl=new Nodo(40);
        Nodo a=new Nodo(50);
        Nodo b=new Nodo(60);
        Nodo c=new Nodo(70);
        Nodo d=new Nodo(80);
        Nodo e=new Nodo(90);
        Nodo f=new Nodo(120);
        Nodo g=new Nodo(130);

        
        nodo.agregarHijo(node);
        node.agregarHijo(nodi);
        nodi.agregarHijo(nodt);


        nodi.agregarHijo(nt);
        nodt.agregarHijo(nl);
        nt.agregarHijo(a);
        nt.agregarHijo(b);
        nt.agregarHijo(c);

        nodt.agregarHijo(d);
        nodt.agregarHijo(e);

        node.agregarHijo(f);
        node.agregarHijo(g);


        nodo.encontrar(nodo,5);



        System.out.print("El camino mas largo es: ");
        nodo.getCaminoMasLargo();

         nodo.getPreOrder();
         nodo.getPostOrder();
         nodo.geTamaño();
         nodo.getRaiz();



          /*
                    2
                    |
                    5
                  /| \
                 / \  \
                /   \  \
               10   120  130
            /    \
         100      0
       / / \       \
     40 80  90   / | \
               50 60 70






           */






           }
}



