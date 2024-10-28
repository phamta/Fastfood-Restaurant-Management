package TRIE;

import java.util.ArrayList;
import java.util.List;

public class Trie<T> {
    private Node<T> root;

    public Trie() {
        root = new Node<>();
    }

    // Method to insert a phone number and associated customer into the trie
    public void insert(String key, T object) {
        Node<T> node = root;
        for (char c : key.toCharArray()) {
            node.children.putIfAbsent(c, new Node<>());
            node = node.children.get(c);
        }
        node.isEnd = true;
        node.object = object;
    }

    // Method to search for customers by phone number starting with prefix
    public List<T> searchByStartsWith(String prefix) {
        List<T> list = new ArrayList<>();
        if (prefix.isEmpty()) {
            return list; // Return empty list if prefix is empty
        }

        Node<T> node = root;
        for (char c : prefix.toCharArray()) {
            node = node.children.get(c);
            if (node == null) {
                return list; // Return empty list if prefix is not found
            }
        }
        // Collect all customers under this prefix
        collectAllCustomers(node, list);
        return list;
    }


    // Helper method to collect all customers from a given node
    private void collectAllCustomers(Node<T> node, List<T> list) {
        if (node.isEnd && node.object != null) {
            list.add(node.object);
        }
        for (Node<T> child : node.children.values()) {
            collectAllCustomers(child, list);
        }
    }
    
    public void printAllCustomers() {
        List<T> list = new ArrayList<>();
        collectAllCustomers(root, list);
        for (T t : list) {
            System.out.println(t);
        }
    }
}
