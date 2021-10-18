
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

    public void insertNext(Nodo<T> hijo) {
        hijo.setPadre(this);
        hijos.add(hijo);

    }

    public void setHijos(List<Nodo<T>> hijos) {
        for (Nodo<T> hijo : hijos)
            hijo.setPadre(this);
        this.hijos = hijos;
    }

    public void eliminarHijos() {
        System.out.println("\nNodos hijos de "+this.dato+" eliminados");
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
        System.out.println("El padre es: "+this.padre+"\n");
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

    public boolean vacio() {
        return raiz == null;
    }

    public Nodo<T> getRaiz() {
        System.out.println("\nLa raiz es: "+raiz);
        return raiz;
    }


    public void setRaiz(Nodo<T> raiz) {
        this.raiz = raiz;
    }



    public boolean existe(T clave) {
        return encontrarNodo(raiz, clave);
    }

    public ArrayList<Nodo<T>> geTamaño() {
        ArrayList<Nodo<T>> size = new ArrayList<Nodo<T>>();
        Size(raiz, size);
        System.out.println("\nEl tamaño del arbol es: " +tamaño);
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

    boolean encontrarNodo(Nodo<T> nodo, T nodoClave) {
        if (nodo.getDato().equals(nodoClave))
            return true;
        else {
            for (Nodo<T> hijo : nodo.getHijos()) {
                if (encontrarNodo(hijo, nodoClave)){
                    System.out.println("Existe el nodo con valor "+nodoClave+"?");
                    System.out.println("Si existe el nodo con valor "+nodoClave+"\n");
                    return true;}



            }


        }
        System.out.println("Existe el nodo con valor "+nodoClave+"?");
        System.out.println("No existe\n");
        return false;


    }

  /*  public Nodo<T> encontrarNodo(Nodo<T> nodo, T nodoClave) {
        if (nodo == null)
            return null;
        if (nodo.getDato().equals(nodoClave)){
            return nodo;}
        else {
            Nodo<T> cnodo = null;
            for (Nodo<T> hijo : nodo.getHijos())
                if ((cnodo = encontrarNodo(hijo, nodoClave)) != null)
                    return cnodo;
        }
        return null;

   */





    public ArrayList<Nodo<T>> getPreOrder() {
        ArrayList<Nodo<T>> preOrder = new ArrayList<Nodo<T>>();
        construirPreOrder(raiz, preOrder);
        System.out.println("\nEl pre orden es " +preOrder);
        return preOrder;
    }


    public ArrayList<Nodo<T>> getPostOrder() {
        ArrayList<Nodo<T>> postOrder = new ArrayList<Nodo<T>>();
        construirPostOrder(raiz, postOrder);
        System.out.println("\nEl pos orden es " +postOrder);
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
        System.out.print("El camino mas largo es: ");
        return caminoMasLargo().size();   }

    public ArrayList<ArrayList<Nodo<T>>> getRamas() {
        ArrayList<ArrayList<Nodo<T>>> rutas  = new ArrayList<ArrayList<Nodo<T>>>();
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

    public ArrayList<ArrayList<Nodo<T>>> GRamas() {
        ArrayList<ArrayList<Nodo<T>>> roos= new ArrayList<ArrayList<Nodo<T>>>();
        ArrayList<Nodo<T>> Track= new ArrayList<Nodo<T>>();
        getPath(raiz, Track, roos);
        System.out.println("\nLas ramas son: " +roos);

        return roos;   }


    public boolean isLeaf() {
        System.out.println("\n"+this.getDato()+ " es un nodo hoja?");
        if (this.getHijos().isEmpty()){
            System.out.println("Si lo es");
            return true;
        }
        else {
            System.out.println("No lo es");
            return false;
        }

    }

    public Nodo<T> leftMostChild() {

            List<Nodo<T>> temp = new ArrayList<>();
            temp = this.getHijos();
        System.out.print("\nElemento mas a la izquierda de "+this.getDato()+"? ");

            if(temp.size()==0){
                System.out.print("no existe elemento mas a la izquierda\n");


            }else{
                System.out.print("\nEs el "+temp.get(0)+"\n");
            return temp.get(0);}

        return null;


    }




    public List<Nodo<T>> rightSibling() {
        List<Nodo<T>> temp = new ArrayList<>();
        List<Nodo<T>> fin = new ArrayList<>();
        temp = this.getHijos();
        for (Nodo<T> nod: temp ){
            if (nod !=temp.get(0)){
                fin.add(nod);
            }
        }
        System.out.println("\nLos hermanos derechos de "+this.getDato()+" estan dados como: "+fin);
        return  fin;
    }

}


class ArbolesNArios {
    public static void main(String[] args) {

        Nodo<Integer> nodo=new Nodo<>(2);
        Nodo<Integer> node=new Nodo<>(5);
        Nodo<Integer> nodi=new Nodo<>(10);
        Nodo<Integer> nodt=new Nodo<>(100);

        Nodo<Integer> nt=new Nodo<>(0);
        Nodo<Integer> nl=new Nodo<>(40);
        Nodo<Integer> a=new Nodo<>(50);
        Nodo<Integer> b=new Nodo<>(60);
        Nodo<Integer> c=new Nodo<>(70);
        Nodo<Integer> d=new Nodo<>(80);
        Nodo<Integer> e=new Nodo<>(90);
        Nodo<Integer> f=new Nodo<>(120);
        Nodo<Integer> g=new Nodo<>(130);

        nodo.setRaiz(nodo);
        nodo.setPadre(node);
        nodo.insertNext(node);
        node.insertNext(nodi);
        nodi.insertNext(nodt);


        nodi.insertNext(nt);
        nodt.insertNext(nl);
        nt.insertNext(a);
        nt.insertNext(b);
        nt.insertNext(c);

        nodt.insertNext(d);
        nodt.insertNext(e);

        node.insertNext(f);
        node.insertNext(g);


        nodo.encontrarNodo(nodi,100);

        nodo.getPadre();


        nodo.getCaminoMasLargo();


         nodo.getPreOrder();
         nodo.getPostOrder();
         nodo.geTamaño();
         nodo.getRaiz();

         nodo.GRamas();


         nodt.leftMostChild();
         nodt.rightSibling();

         nodo.isLeaf();
         f.isLeaf();



         nodt.eliminarHijos();
         nt.eliminarHijos();
         nodo.setDato(1);
         nodo.getPreOrder();
         nodo.getPostOrder();




          /*
          Arbol inicial                               Arbol final
                    2                                      1
                    |                                      |
                    5                                      5
                  /| \                                   / \  \
                 / \  \                                10  120 130
                /   \  \                              /  \
              10   120  130                        100    0
            /    \
         100      0
       / / \       \
     40 80  90   / | \
               50 60 70






           */






           }
}



