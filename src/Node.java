public class Node {
    Object dato;
    Node siguiente;

    public Node(Object dato) {
        this.dato = dato;
        this.siguiente = null;
    }

    public Object getItem() {
        return dato;
    }

    public void setItem(Object dato) {
        this.dato = dato;
    }

    public Node getNext() {
        return siguiente;
    }

    public void setNext(Node siguiente) {
        this.siguiente = siguiente;
    }
}



