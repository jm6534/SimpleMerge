package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class TextController implements Initializable {
    @FXML private BorderPane root;
    @FXML private AnchorPane buttonPane;
    @FXML private Button load;
    @FXML private Button edit;
    @FXML private Button save;
    @FXML private BorderPane textPane;
    @FXML private TextField title;
    @FXML private TextArea text;
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
		load.setLayoutX(0);
		edit.setLayoutX(0);
		save.setLayoutX(0);

		edit.translateXProperty().bind(load.widthProperty());
		save.translateXProperty().bind(Bindings.add(load.widthProperty(), edit.widthProperty()));
	}
    
    public DoubleProperty preWidthProperty() {
    	return root.prefWidthProperty();
    }
    
    
	private void fileLoad(TextField title,TextArea text) {
		text.clear();
		title.clear();
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(new ExtensionFilter("Text Files", "*.txt"));
		fileChooser.getExtensionFilters().add(new ExtensionFilter("All Files", "*.*"));
		File file = fileChooser.showOpenDialog(null);
		title.appendText(file.getName());
	    BufferedReader br = null;
	    try{
	      br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
	      String line;
	      while((line = br.readLine()) != null){
	    	  text.appendText(line + "\n");
	      }
	    } catch (FileNotFoundException e) {
	      e.printStackTrace();
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	}
	
	private void fileSave(TextField title,TextArea text) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(new ExtensionFilter("Text Files", "*.txt"));
		fileChooser.getExtensionFilters().add(new ExtensionFilter("All Files", "*.*"));
		fileChooser.setInitialFileName(title.getText());
		File file = fileChooser.showSaveDialog(null);
		System.out.println(file);
		if (file != null) {
		    try{
		        FileWriter writer = null;
		        writer = new FileWriter(file);
		        writer.write(text.getText().replaceAll("\n", "\r\n"));
		        writer.close();
		      } catch (IOException e) {
		        e.printStackTrace();
		      }
		}
	}
	
	private void edit(TextArea text) {
		text.setEditable(true);
	}
	
	public void loadClick(ActionEvent event) {
		fileLoad(title, text);		
	}
	
	public void editClick(ActionEvent event) {
		edit(text);
	}
	
	public void saveClick(ActionEvent event) {
		fileSave(title, text);
	}
}
