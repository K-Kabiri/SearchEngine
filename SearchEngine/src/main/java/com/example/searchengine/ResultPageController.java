package com.example.searchengine;

import controller.FileEditor;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ResultPageController implements Initializable {

    @FXML
    private ImageView backIcon;

    @FXML
    private AnchorPane documentListPane;

    @FXML
    private Pane documentPagePane;

    @FXML
    private ListView<String> documentViewList;

    @FXML
    private Label textField;
    @FXML
    private ImageView searchPageIcon;
    @FXML
    void backIcon(MouseEvent event) {
        documentPagePane.setVisible(false);
        documentListPane.setVisible(true);
    }

    @FXML
    void searchPageIcon(MouseEvent event) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("search-page.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }


    private final String[] documents=new String[SearchPageController.searchText.getOutputDocuments().size()];
    private String currentChoice;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        for (int i = 0; i < SearchPageController.searchText.getOutputDocuments().size(); i++) {
            documents[i] = String.valueOf(SearchPageController.searchText.getOutputDocuments().get(i));
            documentViewList.getItems().add(i,"Document"+(i+1));
        }

        //documentViewList.getItems().addAll("Document"+i+documents);
        documentViewList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                currentChoice = documentViewList.getSelectionModel().getSelectedItem();
                documentPagePane.setVisible(true);
                textField.setText(printText(documents[currentChoice.charAt(8)-1]).toString());
            }
        });

    }
    private StringBuilder printText(String key){
        FileEditor fileEditor=new FileEditor(new File("EnglishData/" + key));
        return fileEditor.getText();
    }
}
