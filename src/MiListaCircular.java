 public class MiListaCircular implements ListInterface {
    private Node head;
    private Node tail;
    private int size;
private int getItem;
    // Constructor
    public MiListaCircular() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public Object getHead() {
        return (head != null) ? head.getItem() : null; // Ajusta .getItem() si tu Node usa otro nombre
    }

    @Override
    public Object getTail() {
        return (tail != null) ? tail.getItem() : null;
    }

    @Override
    public Object get(Node node) {
        if (node == null || isEmpty()) return null;
        Node current = head;
        do {
            if (current == node) {
                return current.getItem();
            }
            current = current.getNext();
        } while (current != head);
        return null;
    }

    @Override
    public Node search(Object object) {
        if (isEmpty()) return null;
        Node current = head;
        do {
            if (current.getItem().equals(object)) {
                return current;
            }
            current = current.getNext();
        } while (current != head);
        return null;
    }

    @Override
    public boolean add(Object object) {
        return insertTail(object); // Añadir al final por defecto
    }

    @Override
    public boolean insert(Node node, Object object) {
        if (node == null) return false;
        Node current = head;
        boolean found = false;
        if (isEmpty()) return false;

        do {
            if (current == node) {
                found = true;
                break;
            }
            current = current.getNext();
        } while (current != head);

        if (!found) return false;

        Node newNode = new Node(object);
        newNode.setNext(current.getNext());
        current.setNext(newNode);

        if (current == tail) {
            tail = newNode;
        }
        size++;
        return true;
    }

    @Override
    public boolean insert(Object objectRef, Object object) {
        Node targetNode = search(objectRef);
        if (targetNode == null) return false;
        return insert(targetNode, object);
    }

    @Override
    public boolean insertHead(Object object) {
        Node newNode = new Node(object);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
            tail.setNext(head); // Cierra el círculo
        } else {
            newNode.setNext(head);
            head = newNode;
            tail.setNext(head); // Mantiene la circularidad
        }
        size++;
        return true;
    }

    @Override
    public boolean insertTail(Object object) {
        if (isEmpty()) {
            return insertHead(object);
        }
        Node newNode = new Node(object);
        tail.setNext(newNode);
        tail = newNode;
        tail.setNext(head); // Cierra el círculo apuntando al inicio
        size++;
        return true;
    }

    @Override
    public boolean set(Node node, Object object) {
        if (node == null || isEmpty()) return false;
        Node current = head;
        do {
            if (current == node) {
                current.setItem(object); // Ajusta a .setItem() o equivalente en tu Node
                return true;
            }
            current = current.getNext();
        } while (current != head);
        return false;
    }

    @Override
    public boolean remove(Node node) {
        if (node == null || isEmpty()) return false;

        if (head == tail && head == node) {
            clear();
            return true;
        }

        if (head == node) {
            head = head.getNext();
            tail.setNext(head);
            size--;
            return true;
        }

        Node current = head;
        while (current.getNext() != head && current.getNext() != node) {
            current = current.getNext();
        }

        if (current.getNext() == node) {
            current.setNext(node.getNext());
            if (node == tail) {
                tail = current;
            }
            size--;
            return true;
        }
        return false;
    }

    // --- OPERACIONES DE EXPANSIÓN ---

    @Override
    public boolean contains(Object object) {
        return search(object) != null;
    }

    @Override
    public Object[] toArray() {
        if (isEmpty()) return new Object[0];
        Object[] array = new Object[size];
        Node current = head;
        int i = 0;
        do {
            array[i++] = current.getItem();
            current = current.getNext();
        } while (current != head);
        return array;
    }

    @Override
    public Object[] toArray(Object[] object) {
        // Implementación estándar de conversión a array tipado
        Object[] sourceArray = toArray();
        if (object.length < size) {
            return sourceArray;
        }
        System.arraycopy(sourceArray, 0, object, 0, size);
        if (object.length > size) {
            object[size] = null;
        }
        return object;
    }

    @Override
    public MiListaCircular subList(Node from, Node to) {
        MiListaCircular sub = new MiListaCircular();
        if (isEmpty() || from == null || to == null) return sub;

        Node current = from;
        boolean recording = false;
        do {
            if (current == from) recording = true;
            if (recording) {
                sub.add(current.getItem());
            }
            if (current == to) break;
            current = current.getNext();
        } while (current != from);

        return sub;
    }

    @Override
    public MiListaCircular sortList() {
        // Ordenamiento por burbuja adaptado para lista circular
        if (size <= 1) return this;

        boolean swapped;
        do {
            swapped = false;
            Node current = head;
            for (int i = 0; i < size - 1; i++) {
                Node nextNode = current.getNext();
                // Asumiendo que los elementos implementan Comparable
                Comparable val1 = (Comparable) current.getItem();
                Comparable val2 = (Comparable) nextNode.getItem();

                if (val1.compareTo(val2) > 0) {
                    Object temp = current.getItem();
                    current.setItem(nextNode.getItem());
                    nextNode.setItem(temp);
                    swapped = true;
                }
                current = nextNode;
            }
        } while (swapped);

        return this;
    }
}


