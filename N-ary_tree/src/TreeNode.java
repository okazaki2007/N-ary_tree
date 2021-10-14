import java.util.Objects;
import java.util.Vector;

class TreeNode {
    public int key;
    public Vector<TreeNode> child;

    public TreeNode(int key) {
        // Establecer llave del nodo
        this.key = key;
        // crear memoria del hijo de TreeNode
        this.child = new Vector<TreeNode>();
    }

    public void addChild(int key) {
        // Create a new node
        TreeNode node = new TreeNode(key);
        // Add node into child
        this.child.add(node);
    }


    public static class NAryTree {
        public TreeNode root;


        public NAryTree() {

            this.root = null;

        }


        public void deleteLeafNodes(TreeNode node) {
            if (node == null) {
                return;
            }

            int i = 0;

            TreeNode temp = null;

            while (i < node.child.size()) {
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

            if (this.root == null) {
                // cuando el arbol es vacio
                return;
            }
            if (this.root.child.size() == 0) {
                // cuando elimina la raiz del nodo
                this.root = null;
            } else {
                // encontrar y elimnar nodo hojas
                this.deleteLeafNodes(this.root);
            }


        }


        public void printPreorder(TreeNode node) {
            if (node == null) {
                return;
            }

            int i = 0;

            TreeNode temp = null;
            System.out.print("  " + node.key);

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
        tree.root = new TreeNode(10);
        tree.root.addChild(8);
        tree.root.addChild(5);
        // agregar nodos hijo [-2,1,6] en nodo (8)
        tree.root.child.get(0).addChild(-2);
        tree.root.child.get(0).addChild(1);
        tree.root.child.get(0).addChild(6);
        // agregar nodos hijo [9,11] en nodo (1)
        tree.root.child.get(0).child.get(1).addChild(9);
        tree.root.child.get(0).child.get(1).addChild(11);
        // agregar nodos hijo  [17  12] en nodo (11)
        tree.root.child.get(0).child.get(1).child.get(1).addChild(17);
        tree.root.child.get(0).child.get(1).child.get(1).addChild(12);
        // agregar nodos hijo  [7 18 3  4] en nodo(5)
        tree.root.child.get(1).addChild(7);
        tree.root.child.get(1).addChild(18);
        tree.root.child.get(1).addChild(3);
        tree.root.child.get(1).addChild(4);

        // agregar nodo hijo [-1] en nodo(7)
        tree.root.child.get(1).child.get(0).addChild(-1);

        // agregar nodos hijo [2,1,3] en nodo(4)
        tree.root.child.get(1).child.get(3).addChild(2);
        tree.root.child.get(1).child.get(3).addChild(1);
        tree.root.child.get(1).child.get(3).addChild(3);

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
        tree.printPreorder(tree.root);//imprime componentes de hojas


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


        tree.printPreorder(tree.root);

    }
}
