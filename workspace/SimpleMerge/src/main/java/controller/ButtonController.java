package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.Property;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import model.MainModel;

public class ButtonController implements Initializable {
    @FXML private AnchorPane root;
    @FXML private Button compare;
    @FXML private Button copyToLeft;
    @FXML private Button copyToRight;
    
    MainModel mainModel;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		copyToLeft.setLayoutX(0);
		compare.setLayoutX(0);
		copyToRight.setLayoutX(0);
		copyToLeft.setLayoutY(0);
		compare.setLayoutY(0);
		copyToRight.setLayoutY(0);
		
		copyToLeft.translateYProperty().bind(Bindings.add(Bindings.divide(root.heightProperty(),2),compare.heightProperty()));
		compare.translateYProperty().bind(Bindings.divide(root.heightProperty(),2));
		copyToRight.translateYProperty().bind(Bindings.subtract(Bindings.divide(root.heightProperty(),2),copyToRight.heightProperty()));

		copyToLeft.translateXProperty().bind(Bindings.divide(Bindings.subtract(root.widthProperty(), copyToLeft.widthProperty()), 2));
		copyToRight.translateXProperty().bind(Bindings.divide(Bindings.subtract(root.widthProperty(), copyToRight.widthProperty()), 2));
		
		root.prefWidthProperty().bind(compare.widthProperty());		
	}
	
	public DoubleProperty preWidthProperty() {
		return root.prefWidthProperty();
	}
	
	public void compareClick(ActionEvent event) {
		if(mainModel.LCS()) mainModel.setIsCompared(true);
		else mainModel.setIsCompared(false);
	}
	
	public void copyToLeftClick(ActionEvent event) {
		mainModel.copyToLeft();
		mainModel.getLeftSubModel().setIsModified(true);
	}
	
	public void copyToRightClick(ActionEvent event) {
		mainModel.copyToRight();
		mainModel.getRightSubModel().setIsModified(true);		
	}

	public void setMainModel(MainModel mainModel) {
		this.mainModel = mainModel;
		copyToLeft.disableProperty().bind(mainModel.getIsComparedProperty().not());
		copyToRight.disableProperty().bind(mainModel.getIsComparedProperty().not());
	}
	
	public void setMainModelForTest(MainModel mainModel) {
		this.mainModel = mainModel;
		copyToLeft = new Button();
		copyToRight = new Button();
		copyToLeft.disableProperty().bind(mainModel.getIsComparedProperty().not());
		copyToRight.disableProperty().bind(mainModel.getIsComparedProperty().not());
	}
}
