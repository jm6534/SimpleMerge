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
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.Callback;
import model.Line;
import model.TextPage;

public class TextController implements Initializable {
    @FXML private BorderPane root;
    @FXML private AnchorPane buttonPane;
    @FXML private Button load;
    @FXML private Button edit;
    @FXML private Button save;
    @FXML private BorderPane textPane;
    @FXML private TextField title;
    @FXML private ListView<Line> text;
    
    private TextPage textPage = new TextPage();
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
		load.setLayoutX(0);
		edit.setLayoutX(0);
		save.setLayoutX(0);

		edit.translateXProperty().bind(load.widthProperty());
		save.translateXProperty().bind(Bindings.add(load.widthProperty(), edit.widthProperty()));
		
		text.itemsProperty().bindBidirectional(textPage.getListProperty());
		title.textProperty().bindBidirectional(textPage.getFilePathProperty());
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
		File file = fileChooser.showOpenDialog(null);
		textPage.setFilePath(file);
		System.out.println("aaa");
	}
	
	private void fileSave() {
		try {
			FileChooser fileChooser = new FileChooser();
			fileChooser.getExtensionFilters().add(new ExtensionFilter("Text Files", "*.txt"));
			fileChooser.getExtensionFilters().add(new ExtensionFilter("All Files", "*.*"));
			File file = fileChooser.showSaveDialog(null);
			System.out.println(file);
			if (file != null) {
				try{
					FileWriter writer = null;
					writer = new FileWriter(file);
					for(int i = 0; i < textPage.getTextLines().size(); i++) {
						writer.write(textPage.getTextLines().get(i).toString());
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
		text.setCellFactory(new Callback<ListView<Line>, ListCell<Line>>(){
			@Override
			public ListCell<Line> call(ListView<Line> lineView) {
				ListCell<Line> line = new ListCell<Line>() {
					@Override
					public void updateItem(Line item, boolean empty) {
						super.updateItem(item,empty);
						if(empty || item == null || item.toString() == null) {
							setText(null);
						} else {
							setText(item.toString());
						}
					}
				};
				return line;
			}
		});
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
