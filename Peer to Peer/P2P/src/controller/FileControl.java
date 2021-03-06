/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Rafael Braz
 */
public final class FileControl {

    private final String dirPath;
    private final HashMap<String, String> fileList;

    public FileControl(String dirPath) {
        this.dirPath = dirPath;
        fileList = new HashMap<>();
        updateFileList();
    }

    public void updateFileList() {
        fileList.clear();
        File directory = new File(dirPath);        
        File[] files = directory.listFiles((File f) -> {
            return f.isFile();
        });
        for (File f : files) {
            fileList.put(f.getName(), f.getAbsolutePath());
        }
        printFiles();                
    }

    private void printFiles() {
        System.out.println("Local Files List:");
        fileList.keySet().forEach((t) -> {
            System.out.printf("\t'%s'\n", t);
        });
        ViewController.getInstance().updateFiles(fileList.keySet());
    }

    public byte[] getFileContent(String fileName) {
        try {
            return Files.readAllBytes(new File(fileList.get(fileName)).toPath());
        } catch (IOException e) {
            System.err.printf("Error reading file '%s' content! Message: %s\n", fileName, e.getMessage());
            return null;
        }
    }

    public boolean fileExists(String fileName) {
        return fileList.containsKey(fileName);
    }

    public void addFile(String fileName, byte[] content) {
        String filePath = dirPath + File.separator + fileName;
        fileList.put(fileName, filePath);
        try {
            FileOutputStream stream = new FileOutputStream(new File(filePath));
            stream.write(content);
        } catch (Exception e) {
            System.err.printf("Error writing file '%s' content! Message: %s\n", fileName, e.getMessage());
            fileList.remove(fileName);
        }
        printFiles();
    }

}
