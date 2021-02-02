package UI;

import javafx.application.Application;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;


import java.awt.event.MouseListener;
import java.util.function.Predicate;


public class Main extends Application  {
    ComboBox<String> comboBoxCity;
    ComboBox<String> comboBoxBrand;
   // ComboBox<Integer> comboBoxYear;
   // ComboBox<String> comboBoxCondition;
    CarShowroom salon;
    TableView<Vehicle> vehicleTable;
    TableColumn<Vehicle, String> brandColumn;
    TableColumn<Vehicle, String> modelColumn;
    TableColumn<Vehicle, Double> priceColumn;
    TableColumn<Vehicle, String> cityColumn;
    TableColumn<Vehicle, String> reserveColumn;
    TextField brandInput;
   // TextField modelInput;
   // TextField cityInput;
    Label title;
   // Button searchButton;
    Button reserveButton;
    Stage currentStage;
    ObservableList<Vehicle> vehicles;

    @Override
    public void init() throws  Exception {
        salon = new CarShowroom("salon", 100);
        salon.insertIntoList();
    }


    @Override
    public void start(Stage stage) throws Exception {
        currentStage = stage;
        setColumns();
        vehicleTable = new TableView<>();
        getVehicle();


        vehicleTable.setItems(vehicles);
        vehicleTable.getColumns().addAll(brandColumn, modelColumn, priceColumn,cityColumn, reserveColumn);

        vehicleTable.setEditable(true);

        vehicleTable.setRowFactory(tv -> new TableRow<Vehicle>() {
            private Tooltip tooltip = new Tooltip();
            @Override
            public void updateItem(Vehicle v, boolean empty) {
                super.updateItem(v, empty);
                if (v == null) {
                    setTooltip(null);
                } else {
                    tooltip.setText(getCarInfo(v));
                    setTooltip(tooltip);
                }
            }
        });


        currentStage.setTitle("CarShop");
        currentStage.setWidth(800);
        currentStage.setHeight(1000);

        BorderPane BPane = new BorderPane();

        VBox topContainerVBox = new VBox();
        HBox comboBoxHBox = new HBox();
        HBox inputsHBox = new HBox();
        HBox buttonHBox = new HBox();
        VBox tableVBox = new VBox();


        comboBoxCity = new ComboBox<>();
        comboBoxCity.getItems().addAll("any","Krakow", "Warszawa", "Lodz", "Bielsko-Biala", "Wroclaw");
        comboBoxCity.setPromptText("Choose city");
        comboBoxCity.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if(comboBoxCity.getValue() == "any")  comboBoxCity.getSelectionModel().clearSelection();
            }
        });

        comboBoxBrand = new ComboBox();
        comboBoxBrand.getItems().addAll("any","Fiat","Opel","Seat","Mercedes-Benz","Volkswagen", "Audi");
        comboBoxBrand.setPromptText("Choose brand");
        comboBoxBrand.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if(comboBoxBrand.getValue() == "any")  comboBoxBrand.getSelectionModel().clearSelection();
            }
        });
//        comboBoxYear = new ComboBox();
//        comboBoxYear.getItems().addAll(0,2000,2001,2002,2003,2004,2005,2006,2007,2009,2010,2011,2012,2013,2014,2015,2016,2017,2018,2019,2000);
//        comboBoxYear.setPromptText("Choose production year");
//
//        comboBoxCondition = new ComboBox();
//        comboBoxCondition.getItems().addAll("NEW", "USED","DAMAGED");
//        comboBoxCondition.setPromptText("Choose car condition");
        //comboBoxPane.


        brandInput = new TextField();
        brandInput.setPromptText("Brand");
        brandInput.setMinWidth(100);

       // modelInput = new TextField();
       // modelInput.setPromptText("Model");
       // brandInput.setMinWidth(100);

       // cityInput = new TextField();
       // cityInput.setPromptText("City");
       // cityInput.setMinWidth(100);



        title = new Label("THE BEST CAR SHOP");

       // searchButton = new Button("Search!");
       // searchButton.setOnAction(e->Search());

        reserveButton = new Button("RESERVE CAR NOW!");
        reserveButton.setMinWidth(40);
        reserveButton.setMinHeight(20);
        reserveButton.setOnAction(e->Reserve());


        comboBoxHBox.getChildren().addAll(comboBoxBrand, comboBoxCity);
                //comboBoxYear, comboBoxCondition);
        comboBoxHBox.setSpacing(10);
       // inputsHBox.getChildren().addAll(brandInput, searchButton);
        inputsHBox.getChildren().addAll(brandInput, reserveButton);
        inputsHBox.setSpacing(10);

        topContainerVBox.getChildren().addAll(title,comboBoxHBox, inputsHBox, buttonHBox);
        topContainerVBox.setSpacing(20);
        topContainerVBox.setPadding(new Insets(20,20,20,20));
        BPane.setTop(topContainerVBox);
        BorderPane.setAlignment(topContainerVBox, Pos.TOP_CENTER);

        tableVBox.getChildren().addAll(vehicleTable);
        tableVBox.setSpacing(20);
        BPane.setCenter(tableVBox);


        //BPane.setBottom(reserveButton);
        //BorderPane.setAlignment(reserveButton, Pos.TOP_CENTER);


        FilteredList<Vehicle> filteredData = new FilteredList<>(vehicles, e -> true);
        brandInput.setOnKeyReleased(e -> {
            brandInput.textProperty().addListener((observableValue, oldValue, newValue) -> {
                filteredData.setPredicate((Predicate<? super Vehicle>) vehicle -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (vehicle.getBrand().toLowerCase().contains(newValue)) {
                        return true;
                    } else if (vehicle.getCity().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (vehicle.getModel().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    return false;
                });
            });
            SortedList<Vehicle> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(vehicleTable.comparatorProperty());
            vehicleTable.setItems(sortedData);
        });

        //comboBoxFun();

        ObjectProperty<Predicate<Vehicle>> brandFilter = new SimpleObjectProperty<>();
        ObjectProperty<Predicate<Vehicle>> cityFilter = new SimpleObjectProperty<>();

        brandFilter.bind(Bindings.createObjectBinding(() ->
                        Vehicle -> (comboBoxBrand.getValue() == null) || (comboBoxBrand.getValue() == Vehicle.getBrand()),
                comboBoxBrand.valueProperty()));


        cityFilter.bind(Bindings.createObjectBinding(() ->
                        Vehicle -> comboBoxCity.getValue() == null || comboBoxCity.getValue() == Vehicle.getCity(),
                comboBoxCity.valueProperty()));


        FilteredList<Vehicle> filteredItems2 = new FilteredList<>(FXCollections.observableList(vehicles));
        vehicleTable.setItems(filteredItems2);

        filteredItems2.predicateProperty().bind(Bindings.createObjectBinding(
                () -> brandFilter.get().and(cityFilter.get()),
                brandFilter, cityFilter ));



       // Scene sc = new Scene(updateStage());
         Scene sc = new Scene(BPane);



        currentStage.setScene(sc);
        currentStage.show();

//        Stage n = new Stage();
//        n.initModality((Modality.APPLICATION_MODAL));
//        n.show();
    }

    private void Reserve() {

       ObservableList<Vehicle> vehicleSelected, newVehicleList;
        Vehicle VS;
      newVehicleList = vehicleTable.getItems();
     // newVehicleList.remove(2);
        vehicleSelected = vehicleTable.getSelectionModel().getSelectedItems();

        if(vehicleSelected != null) {
            vehicleSelected.forEach(newVehicleList::remove);
        }
//        Vehicle v = vehicleTable.getSelectionModel().getSelectedItem();
//        v.Reserve();
//        int i = 0;
//        for(Vehicle ve : vehicles) {
//            if(ve.getBrand() == v.getBrand() && ve.getModel() == v.getModel() && v.getCity() == ve.getCity()) break;
//            i++;
//        }
//
//        vehicles.remove(i);
//        vehicles.add(i, v);
//        vehicleTable.setItems(vehicles);

    }

    public void comboBoxFun() {

        ObjectProperty<Predicate<Vehicle>> brandFilter = new SimpleObjectProperty<>();
        ObjectProperty<Predicate<Vehicle>> cityFilter = new SimpleObjectProperty<>();

        brandFilter.bind(Bindings.createObjectBinding(() ->
                        Vehicle -> (comboBoxBrand.getValue() == null) || (comboBoxBrand.getValue() == Vehicle.getBrand()),
                comboBoxBrand.valueProperty()));


        cityFilter.bind(Bindings.createObjectBinding(() ->
                        Vehicle -> comboBoxCity.getValue() == null || comboBoxCity.getValue() == Vehicle.getCity(),
                comboBoxCity.valueProperty()));


        FilteredList<Vehicle> filteredItems2 = new FilteredList<>(FXCollections.observableList(vehicles));
        vehicleTable.setItems(filteredItems2);

        filteredItems2.predicateProperty().bind(Bindings.createObjectBinding(
                () -> brandFilter.get().and(cityFilter.get()),
                brandFilter, cityFilter ));


    }

    private void Search() {



        String brandCom = comboBoxBrand.getPromptText();
        String cityCom = comboBoxCity.getPromptText();
       // String yearCom = comboBoxYear.getPromptText();
       // String conditionCom = comboBoxCondition.getPromptText();
        String brandIn = brandInput.getText();
       // String modelIn = modelInput.getText();
       // String cityIn = cityInput.getText();

        brandInput.clear();
       // modelInput.clear();
      //  cityInput.clear();


    }

    private void setColumns() {
        brandColumn = new TableColumn<>("Brand");
        brandColumn.setMinWidth(150);
        brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));

        modelColumn = new TableColumn<>("Model");
        modelColumn.setMinWidth(150);
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));

        priceColumn = new TableColumn<>("Price");
        priceColumn.setMinWidth(150);
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        cityColumn = new TableColumn<>("City");
        cityColumn.setMinWidth(150);
        cityColumn.setCellValueFactory(new PropertyValueFactory<>("city"));

        reserveColumn = new TableColumn<>("Reserved");
        reserveColumn.setMinWidth(150);
        reserveColumn.setCellValueFactory(new PropertyValueFactory<>("isReservedS"));
    }

    // get products;
    public void getVehicle() {

        vehicles = FXCollections.observableArrayList();
        for (Vehicle v : salon.carList) {
            if (!v.isReserved()) {
                vehicles.add(v);
            }

        }
    }

    @Override
    public void stop() throws Exception {
        // po aplikacji
    }

    String getCarInfo(Vehicle v) {

        String ret = "Brand: " + v.getBrand() + "\n" +
                     "Model: " + v.getModel() + "\n" +
                     "Item Condition: " + v.getCondition() + "\n" +
                     "Production year: " + v.getProduction_year() + "\n" +
                     "Mileage: " + v.getMileage() + "\n" +
                     "Engine size: " + v.getEngineSize() + "\n" +
                     "City: " + v.getCity() + "\n" +
                     "Price: " + v.getPrice();

        return ret;
    }


    public static void main(String[] args) {
        launch();
    }


}
