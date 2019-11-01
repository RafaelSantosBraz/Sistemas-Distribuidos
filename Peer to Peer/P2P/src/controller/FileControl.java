/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.HashMap;

/**
 *
 * @author Rafael Braz
 */
public class FileControl {

    private final String dirPath;
    private final HashMap<String, String> fileList;

    public FileControl(String dirPath) {
        this.dirPath = dirPath;
        fileList = new HashMap<>();
        updateFileList();
    }

    public void updateFileList() {
        fileList.clear();

    }

    public byte[] getFileContent(String fileName) {
        return null;
    }

    public boolean fileExists(String fileName) {
        return fileList.containsKey(fileName);
    }
}
