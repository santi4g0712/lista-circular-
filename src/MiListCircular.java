public class MiListCircular implements ListInterface {
     private Node head;
     private Node tail;
     private int size;

     // Constructor
     public MiListCircular() {
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
         return (head != null) ? head.getDato() : null;
     }

     @Override
     public Object getTail() {
         return (tail != null) ? tail.getDato() : null;
     }

     @Override
     public Object get(Node node) {
         if (node == null || isEmpty()) return null;
         Node current = head;
         do {
             if (current == node) {
                 return current.getDato();
             }
             current = current.getSiguiente();
         } while (current != head);
         return null;
     }

     @Override
     public Node search(Object object) {
         if (isEmpty()) return null;
         Node current = head;
         do {
             if (current.getDato().equals(object)) {
                 return current;
             }
             current = current.getSiguiente();
         } while (current != head);
         return null;
     }

     @Override
     public boolean add(Object object) {
         return insertTail(object);
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
             current = current.getSiguiente();
         } while (current != head);

         if (!found) return false;

         Node newNode = new Node(object);
         newNode.setSiguiente(current.getSiguiente());
         current.setSiguiente(newNode);

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
             tail.setSiguiente(head);
         } else {
             newNode.setSiguiente(head);
             head = newNode;
             tail.setSiguiente(head);
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
         tail.setSiguiente(newNode);
         tail = newNode;
         tail.setSiguiente(head);
         size++;
         return true;
     }

     @Override
     public boolean set(Node node, Object object) {
         if (node == null || isEmpty()) return false;
         Node current = head;
         do {
             if (current == node) {
                 current.setDato(object);
                 return true;
             }
             current = current.getSiguiente();
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
             head = head.getSiguiente();
             tail.setSiguiente(head);
             size--;
             return true;
         }

         Node current = head;
         while (current.getSiguiente() != head && current.getSiguiente() != node) {
             current = current.getSiguiente();
         }

         if (current.getSiguiente() == node) {
             current.setSiguiente(node.getSiguiente());
             if (node == tail) {
                 tail = current;
             }
             size--;
             return true;
         }
         return false;
     }

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
             array[i++] = current.getDato();
             current = current.getSiguiente();
         } while (current != head);
         return array;
     }

     @Override
     public Object[] toArray(Object[] object) {
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
     public MiListCircular subList(Node from, Node to) {
         MiListCircular sub = new MiListCircular();
         if (isEmpty() || from == null || to == null) return sub;

         Node current = from;
         boolean recording = false;
         do {
             if (current == from) recording = true;
             if (recording) {
                 sub.add(current.getDato());
             }
             if (current == to) break;
             current = current.getSiguiente();
         } while (current != from);

         return sub;
     }

     @Override
     public MiListCircular sortList() {
         if (size <= 1) return this;

         boolean swapped;
         do {
             swapped = false;
             Node current = head;
             for (int i = 0; i < size - 1; i++) {
                 Node nextNode = current.getSiguiente();
                 Comparable val1 = (Comparable) current.getDato();
                 Comparable val2 = (Comparable) nextNode.getDato();

                 if (val1.compareTo(val2) > 0) {
                     Object temp = current.getDato();
                     current.setDato(nextNode.getDato());
                     nextNode.setDato(temp);
                     swapped = true;
                 }
                 current = nextNode;
             }
         } while (swapped);

         return this;
     }
 }


