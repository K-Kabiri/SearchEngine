package com.example.searchengine;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MapEditor {
    private final Map<String, ArrayList<Integer>> map;

    public MapEditor() {
        this.map=new HashMap<>();
    }

    public Map<String, ArrayList<Integer>> getMap() {
        return map;
    }

    public void folderReader() {
        java.io.File directoryPath = new java.io.File("EnglishData");
        java.io.File filesList[] = directoryPath.listFiles();
        for (java.io.File file : filesList) {
            creatMap(file);
        }
    }

    private void creatMap(File inputFile){
        FileEditor newFile=new FileEditor(inputFile);
        String text=newFile.getText().toString();
        String[] newText=text.split(" ");
        for (int i=0;i<newText.length;i++){
            newText[i]=newText[i].toUpperCase();
            if (!map.containsKey(newText[i])){
                ArrayList<Integer> array=new ArrayList<>();
                array.add(newFile.getName());
                map.put(newText[i],array);
            }
            else {
                ArrayList<Integer> temp=map.get(newText[i]);
                boolean found=false;
                for (int j=0;j<temp.size();j++){
                    if (temp.get(j)==newFile.getName()){
                        found=true;
                        break;
                    }
                }
                if (!found)
                    temp.add(newFile.getName());
            }
        }
    }
}
