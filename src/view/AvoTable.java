package view;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class AvoTable extends Application {

	private TableView<Row> table = new TableView<Row>();
	private static ObservableList<Row> data = null;

	public static void initData(ArrayList<String[]> data) {
		AvoTable.data = FXCollections.observableArrayList();
		data.forEach(dat -> {
			AvoTable.data.add(new Row(dat[0], dat[1], dat[2], dat[3], dat[4], dat[5]));
		});
	}

	public static void start(String[] args) {
		launch(args);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void start(Stage stage) {
		Scene scene = new Scene(new Group());
		stage.setTitle("Table View Sample");

		final Label label = new Label("Vertretungsplan");
		label.setFont(new Font("Arial", 20));

		table.setEditable(true);

		TableColumn klasse = new TableColumn("Klasse(n)");
		klasse.setCellValueFactory(new PropertyValueFactory<Row, String>("klasse"));

		TableColumn stunde = new TableColumn("Stunde");
		stunde.setCellValueFactory(new PropertyValueFactory<Row, String>("stunde"));

		TableColumn fach = new TableColumn("Fach");
		fach.setCellValueFactory(new PropertyValueFactory<Row, String>("fach"));

		TableColumn raum = new TableColumn("Raum");
		raum.setCellValueFactory(new PropertyValueFactory<Row, String>("raum"));

		TableColumn art = new TableColumn("Art");
		art.setCellValueFactory(new PropertyValueFactory<Row, String>("art"));

		TableColumn text = new TableColumn("Vertretungs-Text");
		text.setCellValueFactory(new PropertyValueFactory<Row, String>("text"));

		table.setItems(data);
		text.setMinWidth(300);

		double width = klasse.getPrefWidth() + stunde.getPrefWidth() + fach.getPrefWidth() + raum.getPrefWidth()
				+ art.getPrefWidth() + text.getPrefWidth();

		table.getColumns().addAll(klasse, stunde, fach, raum, art, text);

		final VBox vbox = new VBox();
		vbox.setSpacing(5);
		vbox.setPadding(new Insets(10, 0, 0, 0));
		vbox.getChildren().addAll(label, table);
		vbox.setVgrow(table, Priority.ALWAYS);

		((Group) scene.getRoot()).getChildren().addAll(vbox);

		stage.setScene(scene);
		stage.show();
	}
}
