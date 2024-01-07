package com.example.searchengine;

import java.util.ArrayList;
import java.util.Objects;

public class SearchText {
    private final MapEditor mapEditor;
    private String inputText;
    private ArrayList<Integer> filesWithPositive;
    private boolean checkPositive = false;
    private ArrayList<Integer> filesWithNegative;
    private ArrayList<Integer> unsignedFiles;

    private ArrayList<Integer> outputDocuments;

    public SearchText(String inputText, MapEditor mapEditor) {
        this.inputText = inputText;
        this.filesWithPositive = new ArrayList<>();
        this.filesWithNegative = new ArrayList<>();
        this.unsignedFiles = new ArrayList<>();
        this.outputDocuments = new ArrayList<>();
        this.mapEditor = mapEditor;
    }

    public ArrayList<Integer> getOutputDocuments() {
        return outputDocuments;
    }

    public boolean isCheckPositive() {
        return checkPositive;
    }

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>functions<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    private void findDocuments() {
        String[] temp = inputText.split(" ");
        for (int i = 0; i < temp.length; i++) {
            String word = temp[i].toUpperCase();
            if (word.charAt(0) == '+') {
                word = creatWord(word);
                findPositiveWords(word);
            } else if (word.charAt(0) == '-') {
                word = creatWord(word);
                findNegativeWords(word);
            } else {
                findUnsignedWords(word);
            }
        }
    }

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>.
    private void findUnsignedWords(String str) {
        if (unsignedFiles.isEmpty()) {
            unsignedFiles.addAll(mapEditor.getMap().get(str));
        } else {
            ArrayList<Integer> temp = new ArrayList<>();
            ArrayList<Integer> output = mapEditor.getMap().get(str);
            for (Integer unsignedFile : unsignedFiles) {
                for (Integer integer : output) {
                    if (Objects.equals(unsignedFile, integer)) {
                        temp.add(unsignedFile);
                        break;
                    }
                }
            }
            unsignedFiles = temp;
        }
    }

    //-------------------------------------------------------------------
    private void findPositiveWords(String str) {
        ArrayList<Integer> temp = mapEditor.getMap().get(str);
        if (temp != null) {
            checkPositive = true;
            filesWithPositive.addAll(temp);
        }
    }

    //-------------------------------------------------------------------
    private void findNegativeWords(String str) {
        if (filesWithNegative.isEmpty()) {
            ArrayList<Integer> temp = mapEditor.getMap().get(str);
            if (temp != null) {
                filesWithNegative.addAll(mapEditor.getMap().get(str));
            }
        } else {
            ArrayList<Integer> output = mapEditor.getMap().get(str);
            if (output != null) {
                boolean found = false;
                for (Integer value : output) {
                    for (Integer integer : filesWithNegative) {
                        if (Objects.equals(value, integer)) {
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        filesWithNegative.add(value);
                    }
                }
            }
        }
    }

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>.
    private String creatWord(String str) {
        StringBuilder newStr = new StringBuilder();
        for (int i = 1; i < str.length(); i++) {
            newStr.append(str.charAt(i));
        }
        return newStr.toString();
    }
}
