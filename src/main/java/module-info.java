module org.example.apptienditatxt {
	requires javafx.controls;
	requires javafx.fxml;

	exports org.example.apptienditatxt.controller;
	opens org.example.apptienditatxt.controller to javafx.fxml;
	exports org.example.apptienditatxt.view;
	opens org.example.apptienditatxt.view to javafx.fxml;
}