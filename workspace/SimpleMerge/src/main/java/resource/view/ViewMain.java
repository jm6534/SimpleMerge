package resource.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ViewMain implements Initializable {
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
    @FXML private TextArea leftTitle;
    @FXML private TextArea rightTitle;
    @FXML private TextField leftText;
    @FXML private TextField rightText;


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
	
	public void LeftLoadClick(ActionEvent event) {
		
	}
	
	public void LeftEditClick(ActionEvent event) {
		
	}
	
	public void LeftSaveClick(ActionEvent event) {
		
	}

	public void RightLoadClick(ActionEvent event) {
		
	}
	
	public void RightEditClick(ActionEvent event) {
		
	}
	
	public void RightSaveClick(ActionEvent event) {
		
	}
	
	public void CompareClick(ActionEvent event) {
		
	}
	
	public void CopyToLeftClick(ActionEvent event) {
		
	}
	
	public void CopyToRightClick(ActionEvent event) {
		
	}
	
}
