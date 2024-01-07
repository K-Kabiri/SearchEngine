package com.example.searchengine;

import controller.MapEditor;
import controller.SearchText;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class SearchPageController implements Initializable {
    public static MapEditor mapEditor;
    public static SearchText searchText;
    @FXML
    private AnchorPane pane;

    @FXML
    private Button searchButton;

    @FXML
    private TextField searchField;

    @FXML
    void searchButton(MouseEvent event) throws IOException {
        String inputSearch=searchField.getText();
        searchText=new SearchText(inputSearch,mapEditor);
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("result-page.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mapEditor=new MapEditor("EnglishData");
    }
}
