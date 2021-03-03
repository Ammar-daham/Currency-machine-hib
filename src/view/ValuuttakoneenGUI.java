package view;

import com.sun.prism.Image;

import controller.ValuuttaKoneenOhjain;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Valuuttakone;

public class ValuuttakoneenGUI extends Application implements IValuuttakoneenUI {
	
	

	
	private Stage primaryStage;
	Valuuttakone valuuttakone = new Valuuttakone();
	ValuuttaKoneenOhjain ohjain = new ValuuttaKoneenOhjain(valuuttakone, this);
	ListView<String> valuuttalistaVasen;
	ListView<String> valuuttalistaOikea;
	TextField maara;
	TextField tulos;
	
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Valuuttakone");

	

		GridPane root = new GridPane();
		root.setPadding(new Insets(10, 10, 10, 10));
		root.setVgap(10);
		root.setHgap(10);

		Scene scene = new Scene(root, 500, 200);

		valuuttalistaVasen = new ListView<String>();
		valuuttalistaVasen.setPrefSize(150, 150);

		
		ObservableList<String> valuuttaObsVas = FXCollections.observableArrayList();
		valuuttaObsVas.addAll(ohjain.getValuutat());
		valuuttalistaVasen.setItems(valuuttaObsVas);
		valuuttalistaVasen.getSelectionModel().select(0);

		VBox valuuttaDivVasen = new VBox();
		valuuttaDivVasen.getChildren().addAll(new Label("Mist‰:"), valuuttalistaVasen);
		root.add(valuuttaDivVasen, 0, 0);
		

		valuuttalistaOikea = new ListView<String>();
		valuuttalistaOikea.setPrefSize(150, 150);

		ObservableList<String> valuuttaObsOik = FXCollections.observableArrayList();
		valuuttaObsOik.addAll(ohjain.getValuutat());
		valuuttalistaOikea.setItems(valuuttaObsOik);
		valuuttalistaOikea.getSelectionModel().select(0);

		VBox valuuttaDivOikea = new VBox();
		valuuttaDivOikea.getChildren().addAll(new Label("Mihin:"), valuuttalistaOikea);
		root.add(valuuttaDivOikea, 1, 0);
	

		maara = new TextField();
		Button muunna = new Button("Muunna");
		muunna.setPrefWidth(150);
		tulos = new TextField();

		VBox tekstit = new VBox();
		tekstit.getChildren().addAll(new Label("M‰‰r‰:"), maara, muunna, new Label("Tulos:"), tulos);
		root.add(tekstit, 2, 0);

		muunna.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				ohjain.muunnos();
			}
		});

		primaryStage.setScene(scene);
		primaryStage.show();

	}

	@Override
	public int getLahtoIndeksi() {
		return valuuttalistaVasen.getSelectionModel().getSelectedIndex();
	}

	@Override
	public int getKohdeIndeksi() {
		return valuuttalistaOikea.getSelectionModel().getSelectedIndex();
	}
	
	@Override
	public double getM√§√§r√§() {
		double paluuarvo = 0;
		try {
			paluuarvo = Double.parseDouble(maara.getText());
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Ei numero");
			alert.setHeaderText(null);
			alert.setContentText("Syˆt‰ kentt‰‰n vain numeroita.");
			alert.initOwner(primaryStage);
			alert.showAndWait();
		}
		return paluuarvo;
	}


	@Override
	public void setTulos(double m‰‰r‰) {
		tulos.setText(String.valueOf(m‰‰r‰));
	}

	public static void main(String[] args) {
		launch(args);
	}

	

}