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
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import model.TextPage;

public class TextController implements Initializable {
    @FXML private BorderPane root;
    @FXML private AnchorPane buttonPane;
    @FXML private Button load;
    @FXML private Button edit;
    @FXML private Button save;
    @FXML private BorderPane textPane;
    @FXML private TextField title;
    @FXML private ListView<String> text;
    
    private TextPage textPage = new TextPage();
    private File file;
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
		load.setLayoutX(0);
		edit.setLayoutX(0);
		save.setLayoutX(0);

		edit.translateXProperty().bind(load.widthProperty());
		save.translateXProperty().bind(Bindings.add(load.widthProperty(), edit.widthProperty()));
		
		text.itemsProperty().bindBidirectional(textPage.ListProperty());
		title.textProperty().bindBidirectional(textPage.FilePathProperty());
		for(int i = 0; i < 100; i++) {
  	  	textPage.ListProperty().add("\r\n");
		}
	}
    
    public DoubleProperty preWidthProperty() {
    	return root.prefWidthProperty();
    }
    
    
	private void fileLoad() {
		text.getItems().clear();
		title.clear();
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(new ExtensionFilter("Text Files", "*.txt"));
		fileChooser.getExtensionFilters().add(new ExtensionFilter("All Files", "*.*"));
		file = fileChooser.showOpenDialog(null);
		title.appendText(file.getAbsolutePath());
	    BufferedReader br = null;
	    try{
	      br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
	      String line;
	      while((line = br.readLine()) != null){
	    	  textPage.ListProperty().add(line + "\r\n");
	      }
	    } catch (FileNotFoundException e) {
	      e.printStackTrace();
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	}
	
	private void fileSave() {
		try {
			FileChooser fileChooser = new FileChooser();
			fileChooser.getExtensionFilters().add(new ExtensionFilter("Text Files", "*.txt"));
			fileChooser.getExtensionFilters().add(new ExtensionFilter("All Files", "*.*"));
			fileChooser.setInitialDirectory(file.getParentFile());
			fileChooser.setInitialFileName(file.getName());
			file = fileChooser.showSaveDialog(null);
			System.out.println(file);
			if (file != null) {
				try{
					FileWriter writer = null;
					writer = new FileWriter(file);
					for(int i = 0; i < textPage.ListProperty().getSize(); i++) {
						writer.write(textPage.ListProperty().get(i));
					}
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (NullPointerException e) {

		}
	}

	private void edit() {
		text.setEditable(true);
		text.setCellFactory(TextFieldListCell.forListView());
	}
	
	public void loadClick(ActionEvent event) {
		fileLoad();		
	}
	
	public void editClick(ActionEvent event) {
		edit();
	}
	
	public void saveClick(ActionEvent event) {
		fileSave();
	}
}
