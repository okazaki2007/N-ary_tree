import org.w3c.dom.Node;

import java.util.Vector;

class TreeNode {
    public String valor;
    private Node siguiente;
    public Vector<TreeNode> child;

    public TreeNode(String valor) {
        // Establecer llave del nodo
        this.valor = valor;
        // crear memoria del hijo de TreeNode
        this.child = new Vector<TreeNode>();
    }

    public String getValor() {
        return valor;
    }


    public void setValor(String valor) {
        this.valor = valor;
    }

    public Node getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Node siguiente) {
        this.siguiente = siguiente;
    }


    public void addChild(String valor) {
        // Create a new node
        TreeNode node = new TreeNode(valor);
        // Add node into child
        this.child.add(node);


    }
    public void addFirst( String valor){
        TreeNode node = new TreeNode(valor);
        if(NAryTree.inicio.child==null){
            NAryTree.inicio=node;

        }else{
           this.valor=valor+" "+getValor();





        }
    }









    // Add node into child







    public static class NAryTree {
        static TreeNode inicio;
        int tamaño;






        public void deleteLeafNodes(TreeNode node) {
            if (node == null) {
                return;
            }

            int i = 0;

            TreeNode temp = null;

            while (i < node.child.size()) {
                tamaño++;
                temp = node.child.get(i);

                if (temp.child.size() == 0) {
                    // cuando nodo es una hoja de nodo
                    node.child.remove(i);
                } else {
                    deleteLeafNodes(temp);

                    i++;
                }
            }


        }

        public void removeLeafNodes() {

            if (this.inicio == null) {
                // cuando el arbol es vacio
                return;
            }
            if (this.inicio.child.size() == 0) {
                // cuando elimina la raiz del nodo
                this.inicio = null;
            } else {
                // encontrar y elimnar nodo hojas
                this.deleteLeafNodes(this.inicio);
            }


        }



        public void printPreorder(TreeNode node) {
            if (node == null) {
                return;
            }

            int i = 0;

            TreeNode temp = null;
            System.out.print("  " + node.valor);

            // Iterando el hijo del nodo dado
            while (i < node.child.size()) {


                temp = node.child.get(i);

                printPreorder(temp);

                i++;
            }


        }
    }



    public static void main(String[] args) {
        NAryTree tree = new NAryTree();

        // Primer elemento del arbol

        tree.inicio = new TreeNode("10");

        tree.inicio.addChild("8");

        tree.inicio.addChild("5");
        tree.inicio.addFirst( "12000");
        tree.inicio.addFirst( "444444444");



        // agregar nodos hijo [-2,1,6] en nodo (8)

        tree.inicio.child.get(0).addChild("-2");
        tree.inicio.child.get(0).addChild("1");
        tree.inicio.child.get(0).addChild("6");
        // agregar nodos hijo [9,11] en nodo (1)
        tree.inicio.child.get(0).child.get(1).addChild("9");
        tree.inicio.child.get(0).child.get(1).addChild("11");


        // agregar nodos hijo  [17  12] en nodo (11)
        tree.inicio.child.get(0).child.get(1).child.get(1).addChild("17");
        tree.inicio.child.get(0).child.get(1).child.get(1).addChild("12");
        // agregar nodos hijo  [7 18 3  4] en nodo(5)
        tree.inicio.child.get(1).addChild("7");
        tree.inicio.child.get(1).addChild("18");
        tree.inicio.child.get(1).addChild("3");
        tree.inicio.child.get(1).addChild("4");

        // agregar nodo hijo [-1] en nodo(7)
        tree.inicio.child.get(1).child.get(0).addChild("-1");

        // agregar nodos hijo [2,1,3] en nodo(4)
        tree.inicio.child.get(1).child.get(3).addChild("2");
        tree.inicio.child.get(1).child.get(3).addChild("1");
        tree.inicio.child.get(1).child.get(3).addChild("3");




        System.out.print("\n Antes de remover nodos hojas \n");




        /*
                   10
                  /   \
                 /     \
                /       \
               8         5
              /|\      /|\ \
             / | \    / | \ \
            -2 1  6  7 18 3  4
              / \     \     /| \
             9  11    -1   2 1  3
               /  \
              17   12
            -----------------------
            Constructing N-Ary tree
        */
        tree.printPreorder(tree.inicio);//imprime componentes de hojas



        // Remover nodos hijos
        tree.removeLeafNodes();
        System.out.print("\n Despues de remover nodos hojas \n");
        /*
               10
              /  \
             /    \
            /      \
           8        5
           |       / \
           |      /   \
           1     7     4
            \
            11

        */


        tree.printPreorder(tree.inicio);

    }
}
