package com.example.searchengine;

import controller.MapEditor;
import controller.SearchText;

import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc=new Scanner(System.in).useDelimiter("\n");

        System.out.println("> Enter folder Address :");
        String path=sc.next();//"EnglishData"
        MapEditor mapEditor=new MapEditor(path);

        System.out.println("> Write your sentence :");
        System.out.println("If you want to left,just write Exit:)");
        String input=sc.next();

        while (!Objects.equals(input, "Exit")) {

            SearchText searchText = new SearchText(input, mapEditor);

            System.out.println("Number of Documents:"+searchText.getOutputDocuments().size());
            for (int i = 0; i < searchText.getOutputDocuments().size(); i++) {
                System.out.println(">>> Document "+searchText.getOutputDocuments().get(i));
            }

            System.out.println();
            System.out.println("> Write your sentence :");
            input=sc.next();
        }

    }
}
