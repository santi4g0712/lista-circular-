
public class Node {
    private Object dato;
    private Node siguiente;

    public Node(Object dato) {
        this.dato = dato;
        this.siguiente = null;
    }

    // --- Métodos en Español ---
    public Object getDato() {
        return dato;
    }

    public void setDato(Object dato) {
        this.dato = dato;
    }

    public Node getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Node siguiente) {
        this.siguiente = siguiente;
    }

    // --- Métodos en Inglés (Aliases de compatibilidad) ---
    public Object getItem() {
        return dato;
    }

    public void setItem(Object item) {
        this.dato = item;
    }

    public Node getNext() {
        return siguiente;
    }

    public void setNext(Node next) {
        this.siguiente = next;
    }
}



