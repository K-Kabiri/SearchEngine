package controller;

import java.util.ArrayList;
import java.util.Objects;

public class SearchText {
    private final MapEditor mapEditor;
    private String inputText;
    private ArrayList<Integer> filesWithPositive;
    private boolean checkPositive = false;
    private ArrayList<Integer> filesWithNegative;
    private ArrayList<Integer> unsignedFiles;
    private boolean checkUnsigned=false;

    private ArrayList<Integer> outputDocuments;

    public SearchText(String inputText, MapEditor mapEditor)  {
        this.inputText = inputText;
        this.filesWithPositive = new ArrayList<>();
        this.filesWithNegative = new ArrayList<>();
        this.unsignedFiles = new ArrayList<>();
        this.outputDocuments = new ArrayList<>();
        this.mapEditor = mapEditor;
        createOutPut();
    }

    public ArrayList<Integer> getOutputDocuments() {
        return outputDocuments;
    }

    public boolean isCheckPositive() {
        return checkPositive;
    }
    public boolean isCheckUnsigned() {
        return checkUnsigned;
    }

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>functions<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    private void createOutPut()  {
        findDocuments();
        if (!unsignedFiles.isEmpty()){
            if (!filesWithPositive.isEmpty() && !filesWithNegative.isEmpty()){
                filesWithPositive = removeNegative(filesWithPositive);
                unsignedFiles = removeNegative(unsignedFiles);
                outputDocuments.addAll(subscription());
            } else if (!filesWithPositive.isEmpty() && filesWithNegative.isEmpty()) {
                outputDocuments.addAll(subscription());
            }
            else if (!filesWithNegative.isEmpty() && filesWithPositive.isEmpty()){
                if (isCheckPositive())
                    outputDocuments.addAll(subscription());
                else {
                    unsignedFiles = removeNegative(unsignedFiles);
                    outputDocuments.addAll(unsignedFiles);
                }
            }
            else
                outputDocuments.addAll(unsignedFiles);
        }
        else{
            if (checkUnsigned){
                outputDocuments.addAll(null);//???
            }
            else {
                if (!filesWithPositive.isEmpty() && !filesWithNegative.isEmpty()){
                    filesWithPositive=removeNegative(filesWithPositive);
                    outputDocuments.addAll(filesWithPositive);
                }
                else if (!filesWithPositive.isEmpty()&& filesWithNegative.isEmpty()){
                    outputDocuments.addAll(filesWithPositive);
                }
                else if (!filesWithNegative.isEmpty()&& filesWithPositive.isEmpty()){
                    outputDocuments.addAll(null);//????
                }
            }
        }
    }

    //---------------------------------------------------
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
        checkUnsigned=true;
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
        checkPositive = true;
        ArrayList<Integer> temp = mapEditor.getMap().get(str);
        if (temp != null) {
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
                for (Integer value : output) {
                    boolean found = false;
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

    // remove subscription of a list with filesWithNegative
    private ArrayList<Integer> removeNegative(ArrayList<Integer> array) {
        ArrayList<Integer> temp = new ArrayList<>();
        for (Integer value : array) {
            boolean found = false;
            for (Integer integer : filesWithNegative) {
                if (Objects.equals(value, integer)) {
                    found = true;
                    break;
                }
            }
            if (!found)
                temp.add(value);
        }
        array = temp;
        return array;
    }

    // find subscription of unSingedFiles and filesWithPositive
    private ArrayList<Integer> subscription() {
        ArrayList<Integer> temp = new ArrayList<>(unsignedFiles);
        unsignedFiles.clear();
        for (Integer value : temp) {
            for (Integer integer : filesWithPositive) {
                if (Objects.equals(value, integer)) {
                    unsignedFiles.add(value);
                    break;
                }
            }
        }
        return unsignedFiles;
    }
}
