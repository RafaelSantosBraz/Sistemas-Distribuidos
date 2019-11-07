/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.List;
import model.Node;

/**
 *
 * @author Rafael Braz
 */
public class NodeControl {

    private final List<Node> nodes;

    public NodeControl() {
        nodes = new ArrayList<>();
    }

    public NodeControl(List<Node> nodes) {
        this.nodes = nodes;
    }

    public void addNode(Node node) {
        nodes.add(node);
    }

    public void removeNode(Node node) {
        nodes.remove(node);
    }

    public List<Node> getNodes() {
        return nodes;
    }

}
