package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import model.MainModel;

public class MainController implements Initializable {
    @FXML private BorderPane root;

    @FXML private ButtonController centerButtonController;
    @FXML private TextController leftTextController;
    @FXML private TextController rightTextController;
    
    private MainModel mainModel;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		mainModel = new MainModel();
		this.leftTextController.setModels(mainModel, mainModel.getLeftSubModel());
		this.rightTextController.setModels(mainModel, mainModel.getRightSubModel());
		this.centerButtonController.setMainModel(mainModel);
		
		leftTextController.preWidthProperty().bind(Bindings.divide(Bindings.subtract(root.widthProperty(), centerButtonController.preWidthProperty()), 2));
		rightTextController.preWidthProperty().bind(Bindings.divide(Bindings.subtract(root.widthProperty(), centerButtonController.preWidthProperty()), 2));
	}
	
}
