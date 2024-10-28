package TRIE;

import java.util.HashMap;
import java.util.Map;

import DTO.Customer;

public class Node<T> {
    Map<Character, Node<T>> children = new HashMap<>();
    boolean isEnd;
    T object;

    Node() {
        isEnd = false;
        object = null;
    }
}
