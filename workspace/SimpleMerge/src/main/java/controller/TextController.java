package controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ListView.EditEvent;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.Callback;
import javafx.util.StringConverter;
import model.Line;
import model.MainModel;
import model.SubModel;
import model.TextPage;

public class TextController implements Initializable {
	@FXML private BorderPane root;
	@FXML private AnchorPane buttonPane;
	@FXML private Button load;
	@FXML private ToggleButton edit;
	@FXML private Button save;
	@FXML private BorderPane textPane;
	@FXML private TextField title;
	@FXML private ListView<Line> text;

	private TextPage textPage;
	private SubModel subModel;
	private MainModel mainModel;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		load.setLayoutX(0);
		edit.setLayoutX(0);
		save.setLayoutX(0);

		edit.translateXProperty().bind(load.widthProperty());
		save.translateXProperty().bind(Bindings.add(load.widthProperty(), edit.widthProperty()));

		text.setCellFactory(new Callback<ListView<Line>, ListCell<Line>>(){
			@Override
			public ListCell<Line> call(ListView<Line> arg0) {
				TextFieldListCell<Line> cell = new TextFieldListCell<Line>() {
					@Override
					public void updateItem(Line item, boolean empty) {
						if(item == null) {
							Background bg = new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY));
							backgroundProperty().unbind();
							backgroundProperty().setValue(bg);
						}
						super.updateItem(item, empty);
						if(item != null) {
							setText(item.toString());
							backgroundProperty().bind(Bindings.createObjectBinding(()->{
								BackgroundFill fill = new BackgroundFill(item.getLineColorProperty().getValue(), CornerRadii.EMPTY, Insets.EMPTY);
								return new Background(fill);
							}, item.getLineColorProperty().valueProperty()));
						}
					}
				};
				cell.setConverter(new StringConverter<Line>() {
					@Override
					public Line fromString(String string) {
						return new Line(string);
					}
					@Override
					public String toString(Line line) {
						return line.toString();
					}
				});
				return cell;
			}

		});
	}

	public DoubleProperty preWidthProperty() {
		return root.prefWidthProperty();
	}

	private void fileLoad() {
		FileChooser fileChooser = new FileChooser();
		setDefaultExtentionsOfFileChooser(fileChooser);
		File file = fileChooser.showOpenDialog(null);
		if(file == null || file.getAbsolutePath() == null || file.getAbsolutePath().equals("")) return;
		mainModel.resetTextPages();
		textPage.clearContents();
		textPage.setFilePath(file);
		if(text.getItems().isEmpty()) {
			addEmptyLine();
		}
		subModel.setIsEditable(false);
		subModel.setIsModified(false);
	}

	private void setDefaultExtentionsOfFileChooser(FileChooser fileChooser) {
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt")
				,new ExtensionFilter("C Source Files", "*.c")
				,new ExtensionFilter("Java Source Files", "*.java")
				,new ExtensionFilter("C++ Source Files", "*.cpp")
				,new ExtensionFilter("Python Source Files", "*.py"));		
	}

	@SuppressWarnings("unchecked")
	private void addEmptyLine() {
		((List<Line>) textPage.getListProperty()).add(new Line(""));
	}

	private void fileSave() {
		try {
			FileChooser fileChooser = new FileChooser();
			setDefaultExtentionsOfFileChooser(fileChooser);
			File file = fileChooser.showSaveDialog(null);
			if (file != null) {
				try{
					FileWriter writer = null;
					writer = new FileWriter(file);
					writer.write(textPage.getTextFieldForSave());
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		subModel.setIsModified(false);
		subModel.setIsEditable(false);
	}

	private void edit() {
		subModel.setIsModified(true);
	}

	public void keyPressed(KeyEvent event) {
		if(event.getCode() != KeyCode.BACK_SPACE) return;
		String selectedString = text.getSelectionModel().getSelectedItem().toString();
		int selectedIndex = text.getSelectionModel().getSelectedIndex();
		if(text.isEditable()) {
			if(selectedString.length() >= 1) {
				textPage.setLineText(selectedIndex, selectedString.substring(0, selectedString.length() - 1));
				text.getSelectionModel().select(selectedIndex);
			}
			else if(text.getItems().size() >= 2) {
				textPage.deleteLine(selectedIndex);
			}
		}
	}

	public void editCommit(EditEvent<Line> event) {
		mainModel.setIsCompared(false);
		textPage.setLineText(event.getIndex(), event.getNewValue().toString());
		if(event.getIndex() == textPage.getMaxNListProperty() - 1
				||textPage.getMaxNListProperty() == 1) {
			textPage.addLineText("");
		}
		text.getSelectionModel().select(event.getIndex() + 1);
	}

	public void loadClick(ActionEvent event) {
		if(subModel.isModified()) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("SimpleMerge");
			alert.setHeaderText("변경 내용이 있습니다.");
			alert.setContentText("계속하시겠습니까?");

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK){
				fileLoad();		
			} else {
			}
		}else {
			fileLoad();					
		}
	}

	public void editClick(ActionEvent event) {
		edit();
	}

	public void saveClick(ActionEvent event) {
		fileSave();
	}

	public void setModels(MainModel mainModel, SubModel subModel) {
		this.mainModel = mainModel;
		this.subModel = subModel;
		this.textPage = subModel.getTextPage();

		text.editableProperty().bindBidirectional(subModel.getEditableProperty());
		edit.selectedProperty().bindBidirectional(subModel.getEditableProperty());
		text.itemsProperty().bindBidirectional(textPage.getListProperty());
		title.textProperty().bindBidirectional(textPage.getFilePathProperty());
		textPage.getSelectedIndexProperty().bind(text.getSelectionModel().selectedIndexProperty());
		text.getSelectionModel().selectFirst();
		textPage.getSelectedIndexProperty().addListener((obs, oldValue, newValue) -> {
			text.getSelectionModel().clearAndSelect(newValue.intValue());
			textPage.setSelectedLineColor(text.getSelectionModel().getSelectedIndex());
		}); 

		addEmptyLine();
	}
	public void setModelsForTest(MainModel mainModel, SubModel subModel) {
		text = new ListView<Line>();
		edit = new ToggleButton();
		title = new TextField();

		setModels(mainModel, subModel);
	}
}
