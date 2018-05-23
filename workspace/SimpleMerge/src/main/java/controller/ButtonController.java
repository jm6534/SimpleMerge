package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public class ButtonController implements Initializable {	
    @FXML private BorderPane root;
    @FXML private Button load;
    @FXML private Button edit;
    @FXML private Button save;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		load.setLayoutX(0);
		edit.setLayoutX(0);
		save.setLayoutX(0);
		edit.translateXProperty().bind(load.widthProperty());
		save.translateXProperty().bind(Bindings.add(load.widthProperty(), edit.widthProperty()));
	}

}
