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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ViewController implements Initializable {
    @FXML private BorderPane root;
    @FXML private BorderPane leftBorder;
    @FXML private BorderPane rightBorder;
    @FXML private AnchorPane CenterAnchor;
    @FXML private Button leftLoad;
    @FXML private Button leftEdit;
    @FXML private Button leftSave;
    @FXML private Button rightLoad;
    @FXML private Button rightEdit;
    @FXML private Button rightSave;
    @FXML private Button Compare;
    @FXML private Button CopyToLeft;
    @FXML private Button CopyToRight;
    @FXML private TextArea leftText;
    @FXML private TextArea rightText;
    @FXML private TextField leftTitle;
    @FXML private TextField rightTitle;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
        //rightText.layoutXProperty().bind(Bindings.subtract(root.widthProperty(),0));
		CopyToLeft.setLayoutY(0);
		Compare.setLayoutY(0);
		CopyToRight.setLayoutY(0);

		CopyToLeft.translateYProperty().bind(Bindings.add(Bindings.divide(CenterAnchor.heightProperty(),2),23));
		Compare.translateYProperty().bind(Bindings.divide(CenterAnchor.heightProperty(),2));
		CopyToRight.translateYProperty().bind(Bindings.subtract(Bindings.divide(CenterAnchor.heightProperty(),2),23));
		
		leftBorder.prefWidthProperty().bind(Bindings.divide(Bindings.subtract(root.widthProperty(),69), 2));
		rightBorder.prefWidthProperty().bind(Bindings.divide(Bindings.subtract(root.widthProperty(),69), 2));
		

	}
	
	private void FileLoad(TextField Title,TextArea Text) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(new ExtensionFilter("Text Files", "*.txt"));
		fileChooser.getExtensionFilters().add(new ExtensionFilter("All Files", "*.*"));
		File file = fileChooser.showOpenDialog(null);
	    BufferedReader br = null;
	    try{
	      br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
	      String line;
	      while((line = br.readLine()) != null){
	    	  Text.appendText(line + "\n");
	      }
	    } catch (FileNotFoundException e) {
	      e.printStackTrace();
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	}
	
	private void FileSave(TextField Title,TextArea Text) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(new ExtensionFilter("Text Files", "*.txt"));
		fileChooser.getExtensionFilters().add(new ExtensionFilter("All Files", "*.*"));
		fileChooser.setInitialFileName(Title.getText());
		File file = fileChooser.showSaveDialog(null);
		System.out.println(file);
		if (file != null) {
		    try{
		        FileWriter writer = null;
		        writer = new FileWriter(file);
		        writer.write(Text.getText().replaceAll("\n", "\r\n"));
		        writer.close();
		      } catch (IOException e) {
		        e.printStackTrace();
		      }
		}
	}
	
	public void LeftLoadClick(ActionEvent event) {
		FileLoad(leftTitle, leftText);		
	}
	
	public void LeftEditClick(ActionEvent event) {
		
	}
	
	public void LeftSaveClick(ActionEvent event) {
		FileSave(leftTitle, leftText);
	}

	public void RightLoadClick(ActionEvent event) {
		FileLoad(rightTitle, rightText);			
	}
	
	public void RightEditClick(ActionEvent event) {
		
	}
	
	public void RightSaveClick(ActionEvent event) {
		FileSave(rightTitle, rightText);
	}
	
	public void CompareClick(ActionEvent event) {
		
	}
	
	public void CopyToLeftClick(ActionEvent event) {
		
	}
	
	public void CopyToRightClick(ActionEvent event) {
		
	}
	
}
