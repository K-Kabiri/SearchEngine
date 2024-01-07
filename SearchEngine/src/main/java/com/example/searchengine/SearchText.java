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


}
