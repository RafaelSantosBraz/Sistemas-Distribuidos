/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.swing.JOptionPane;
import model.Node;
import view.MainView;

/**
 *
 * @author henriquericordi
 */
public class ViewController {

    //<editor-fold defaultstate="collapsed" desc="SINGLETON">
    private static ViewController instance;       

    private ViewController() {

    }

    public static ViewController getInstance() {
        if (instance == null) {
            instance = new ViewController();
        }
        return instance;
    }
    //</editor-fold>

    private MainView view;

    public void updateFiles(Set<String> fileNames) {
        view.updateFiles(fileNames);
    }
    
    public List<Node> createNodeList(String[] inf) {
        List<Node> res = new ArrayList<>();
        for (String s : inf) {
            String[] vec = s.split(";");
            if (vec.length != 3) {
                return null;
            }
            int port;
            try {
                port = Integer.parseInt(vec[1]);
            } catch (NumberFormatException e) {
                return null;
            }
            res.add(new Node(vec[0], port, vec[2]));
        }
        return res;
    }

    public boolean startService(String IP, String regName, int port, String dirPath, List<Node> connectedNodes) {
        return ServiceController.getInstance().createService(dirPath, new Node(IP, port, regName), connectedNodes);
    }

    public void startView() {
        (view = new MainView()).setVisible(true);
    }

    public boolean startDownload(String fileName) {
        return ServiceController.getInstance().startRequest(fileName);
    }

    public void notifyNewFile() {
        //JOptionPane.showMessageDialog(view, "Arquivo recebido!", "Serviço", JOptionPane.INFORMATION_MESSAGE);
    }

    public void refreshFileList() {
        ServiceController.getInstance().refreshFileList();
    }
}
