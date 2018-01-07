package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Label;

import javafx.scene.control.ComboBox;

import javafx.scene.control.Tab;

import javafx.scene.control.TableView;

import javafx.scene.control.TableColumn;

public class MainController implements Initializable{
	@FXML
	public Tab track;
	@FXML
	public TextField track_Id;
	@FXML
	public TextField dateText;
	@FXML
	public TextField act_delText;
	@FXML
	public TableView table;
	@FXML
	private TableColumn date;
	@FXML
	private TableColumn time;
	@FXML
	private TableColumn activity;
	@FXML
	private TableColumn location;
	@FXML
	public TextField track_Num;
	@FXML
	public TextField wt;
	@FXML
	public TextField pieceText;
	@FXML
	private Tab shipping;
	@FXML
	private ComboBox fromCombo;
	@FXML
	private ComboBox toCombo;
	@FXML
	private TextField weight;
	@FXML
	private TextField pieces;
	@FXML
	private Label track_no;

	DatabaseQuery dq;
	ArrayList<String> list;
	static int t_id=3000000;
	Delivery_Pojo pojo;
	Constants c;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		setInitial();
		setColumn();
	}
	public void setInitial()
	{	
		fromCombo.setItems(FXCollections.observableArrayList(Constants.getList()));
		toCombo.setItems(FXCollections.observableArrayList(Constants.getList()));
	}
	
	@FXML
	public void shipping(Event event)
	{
		weight.clear();
		pieces.clear();
		track_no.setText("");
		fromCombo.getSelectionModel().clearSelection();
		toCombo.getSelectionModel().clearSelection();
		
	}

	@FXML
	public void tracking(Event event)
	{
		table.getItems().clear();
		dateText.setText("");
		act_delText.setText("");
		wt.setText("");
		track_Num.setText("");
		pieceText.setText("");
		track_Id.clear();
	}
	
	@FXML
	public void shipButton(Event event)
	{
		pojo=new Delivery_Pojo();
		c=new Constants();
		pojo.setId(t_id);
		track_no.setText(t_id+"");
		pojo.setFrom(Constants.getList().get(fromCombo.getSelectionModel().getSelectedIndex()));
		pojo.setTo(Constants.getList().get(toCombo.getSelectionModel().getSelectedIndex()));
		pojo.setWeight(weight.getText());
		pojo.setPieces(pieces.getText());
		pojo.setLocation(Constants.getList().get(fromCombo.getSelectionModel().getSelectedIndex()));
		pojo.setDate(c.getDate_Time(true));
		pojo.setTime(c.getDate_Time(false));
		pojo.setActivity("Ready to Pickup");
		t_id++;
		dq=new DatabaseQuery(fromCombo.getSelectionModel().getSelectedIndex(),toCombo.getSelectionModel().getSelectedIndex(), pojo);
	}
	
	@FXML
	public void trackButton(Event event)
	{
		dq=new DatabaseQuery(Integer.parseInt(track_Id.getText()), MainController.this);
	}
	
	public void setColumn()
	{
		date.setCellValueFactory(new PropertyValueFactory<>("date"));
		time.setCellValueFactory(new PropertyValueFactory<>("time"));
		activity.setCellValueFactory(new PropertyValueFactory<>("activity"));
		location.setCellValueFactory(new PropertyValueFactory<>("location"));
	}
}
