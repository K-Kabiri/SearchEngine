package com.example.searchengine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileEditor {
    private String path;
    private int name;
    private StringBuilder text;

    public FileEditor(File file) {
        this.name=Integer.parseInt(file.getName());
        this.path = file.getPath();
        this.text = new StringBuilder();
        fileReader();
    }

    public StringBuilder getText() {
        return text;
    }
    public int getName() {
        return name;
    }

    public void fileReader(){
        String[] in;
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            while ((line = br.readLine()) != null) {
                in=line.split("[,?.@+*/:;\"()=&^%$#!><{}_|'\\[\\]\\\\`~-]+");
                for (String s : in) {
                    text.append(s+" ");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
