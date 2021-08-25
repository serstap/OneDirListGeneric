package ru.avalov.javapp.j110.onedirlist;

import java.util.Iterator;

public class OneDirList<T> implements Iterable<T> {
    private ListItem<T> head;
    private ListItem<T> tail;
    
    public void addToHead(T val) {
        if(head != null) {
            ListItem<T> nh = new ListItem<>(val);   // (1)
            nh.next = head;                         // (2)
            head = nh;                              // (3)
        } else
            head = tail = new ListItem<>(val);
    }
    
    public T peekFromHead() {
        if(head != null)
            return head.value;
        else
            return null;
    }
    
    public T removeFromHead() {
        if(head == null)
            return null;
        
        T res = head.value;
        head = head.next;  // (1)
        if(head == null)
            tail = null;
        return res;
    }
    
    public void addToTail(T val) {
        if(tail != null) {
            tail.next = new ListItem<>(val);    // (1)
            tail = tail.next;                   // (2)
        } else
            head = tail = new ListItem<>(val);
    }
    
    public T peekFromTail() {
        if(tail != null)
            return tail.value;
        else
            return null;
    }
    
    public T removeFromTail() {
        if(head == null)
            return null;
        
        if(head == tail) {
            T res = head.value;
            head = tail = null;
            return res;
        }
        
        ListItem<T> prv = head,
                nxt = head.next;
        while(nxt != tail) {
            prv = nxt;
            nxt = nxt.next;
        }
        
        tail = prv;
        tail.next = null;
        return nxt.value;
    }
    
    public boolean contains(Object val) {
        return findNodeWithVal(val) != null;
    }
    
    private ListItem<T> findNodeWithVal(Object val) {
        ListItem<T> li = head;
        while(li != null) {
            if(li.value == null && val == null
                    || li.value != null && li.value.equals(val))
                return li;
            li = li.next;
        }
        return null;
    }
    
    public boolean isEmpty() {
        return head == null;
    }
    
    public void printContent() {
        ListItem<T> li = head;
        while(li != null) {
            System.out.println(li.value);
            li = li.next;
        }
        System.out.println();
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIterator<>(head);
    }
    
    public Iterable<T> iterateSince(Object val) {
        return () -> new ListIterator<>(findNodeWithVal(val));
    }
    
    private static class ListIterator<V> implements Iterator<V> {
        ListItem<V> currentNode;

        public ListIterator(ListItem<V> currentNode) {
            this.currentNode = currentNode;
        }

        @Override
        public boolean hasNext() {
            return currentNode != null;
        }

        @Override
        public V next() {
            V res = currentNode.value;
            currentNode = currentNode.next;
            return res;
        }
    }
    
    private static class ListItem<V> {
        V value;
        ListItem<V> next;

        ListItem(V value) {
            this.value = value;
        }
    }
}
