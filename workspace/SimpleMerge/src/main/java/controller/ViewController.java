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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ViewController implements Initializable {
    @FXML private BorderPane root;
    @FXML private BorderPane leftTextPane;
    @FXML private BorderPane rightTextPane;
    @FXML private BorderPane leftBorder;
    @FXML private BorderPane rightBorder;
    @FXML private AnchorPane leftButtonPane;
    @FXML private AnchorPane rightButtonPane;
    @FXML private AnchorPane CenterButtonPane;
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
		CopyToLeft.setLayoutX(0);
		Compare.setLayoutX(0);
		CopyToRight.setLayoutX(0);
		CopyToLeft.setLayoutY(0);
		Compare.setLayoutY(0);
		CopyToRight.setLayoutY(0);
		
		leftLoad.setLayoutX(0);
		leftEdit.setLayoutX(0);
		leftSave.setLayoutX(0);
		rightLoad.setLayoutX(0);
		rightEdit.setLayoutX(0);
		rightSave.setLayoutX(0);
		
		CenterButtonPane.prefWidthProperty().bind(Compare.widthProperty());
		leftBorder.prefWidthProperty().bind(Bindings.divide(Bindings.subtract(root.widthProperty(),CenterButtonPane.widthProperty()), 2));
		rightBorder.prefWidthProperty().bind(Bindings.divide(Bindings.subtract(root.widthProperty(),CenterButtonPane.widthProperty()), 2));		
		
		CopyToLeft.translateYProperty().bind(Bindings.add(Bindings.divide(CenterButtonPane.heightProperty(),2),Compare.heightProperty()));
		Compare.translateYProperty().bind(Bindings.divide(CenterButtonPane.heightProperty(),2));
		CopyToRight.translateYProperty().bind(Bindings.subtract(Bindings.divide(CenterButtonPane.heightProperty(),2),CopyToRight.heightProperty()));

		CopyToLeft.translateXProperty().bind(Bindings.divide(Bindings.subtract(CenterButtonPane.widthProperty(), CopyToLeft.widthProperty()), 2));
		CopyToRight.translateXProperty().bind(Bindings.divide(Bindings.subtract(CenterButtonPane.widthProperty(), CopyToRight.widthProperty()), 2));
			
		leftEdit.translateXProperty().bind(leftLoad.widthProperty());
		leftSave.translateXProperty().bind(Bindings.add(leftLoad.widthProperty(), leftEdit.widthProperty()));
		rightEdit.translateXProperty().bind(rightLoad.widthProperty());
		rightSave.translateXProperty().bind(Bindings.add(rightLoad.widthProperty(), rightEdit.widthProperty()));
	}
	
	private void FileLoad(TextField Title,TextArea Text) {
		Text.clear();
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(new ExtensionFilter("Text Files", "*.txt"));
		fileChooser.getExtensionFilters().add(new ExtensionFilter("All Files", "*.*"));
		File file = fileChooser.showOpenDialog(null);
		Title.appendText(file.getName());
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
	
	private void Edit(TextArea Text) {
		Text.setEditable(true);
	}
	
	public void LeftLoadClick(ActionEvent event) {
		FileLoad(leftTitle, leftText);		
	}
	
	public void LeftEditClick(ActionEvent event) {
		Edit(leftText);
	}
	
	public void LeftSaveClick(ActionEvent event) {
		FileSave(leftTitle, leftText);
	}

	public void RightLoadClick(ActionEvent event) {
		FileLoad(rightTitle, rightText);			
	}
	
	public void RightEditClick(ActionEvent event) {
		Edit(rightText);
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
