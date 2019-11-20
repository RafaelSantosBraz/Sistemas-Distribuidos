/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Node;

/**
 *
 * @author cc47192962899
 */
public class LogItem {

    private final String fileName;
    private final Node node;

    public LogItem(String fileName, Node node) {
        this.fileName = fileName;
        this.node = node;
    }

    public String getFileName() {
        return fileName;
    }

    public Node getNode() {
        return node;
    }

}
