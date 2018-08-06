package com.jarena;

import javafx.util.Pair;
import java.util.ArrayList;
import java.util.LinkedList;

public class DigraphAL extends Graph {
    private ArrayList<LinkedList<Pair<Integer, Integer>>> nodo;

    public DigraphAL(int size) {
        super(size);
        nodo = new ArrayList<>();
        for (int i = 0; i < size + 1; i++) {
            nodo.add(new LinkedList<>());
        }
    }

    public void addArc(int source, int destination,int weight) {
        nodo.get(source).add(new Pair<>(destination, weight));
    }
}