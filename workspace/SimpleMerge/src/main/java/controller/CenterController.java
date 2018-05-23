package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public class CenterController implements Initializable {
    @FXML private BorderPane root;
    @FXML private Button compare;
    @FXML private Button copyToLeft;
    @FXML private Button copyToRight;

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
	}

}
