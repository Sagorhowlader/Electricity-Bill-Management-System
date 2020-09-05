
package electricity.bill.management.system;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.*;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class CheckCustomersController implements Initializable {

    @FXML
    private Label back_lb;

    @FXML
    public Label username_lb;

    @FXML
    private Label copy_lb;

    @FXML
    private Label wel_lb;

    @FXML
    private Hyperlink log_hl;

    @FXML
    private TableView<Model_Customer> chkcustomer_tv;

    @FXML
    private Hyperlink goback_hl;
    
    @FXML
    private TableColumn<Model_Customer, Double> blnc_tc;
    
    @FXML
    private TableColumn<Model_Customer, String> mt_tc;

    @FXML
    private TableColumn<Model_Customer, String> name_tc;
    
    
    @FXML
    private Label lst_lb;
    
    @FXML
    private TableColumn<Model_Customer, Button> Delete_tc;
    
    
    FXMLLoader loader = new FXMLLoader();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }   
    public void CheckData()
    {
        try{
            Model_Customer cust = new Model_Customer();
            cust.setUsername(username_lb.getText()); 
            
                ObservableList<Model_Customer> oblist = FXCollections.observableArrayList();
            DBConnect connect = new DBConnect();
        Connection conn = connect.connectDb();
        PreparedStatement column=conn.prepareStatement("Select `C_Name`,`Meter_No`,`C_Balance` FROM tb_customer;");
        ResultSet rs = column.executeQuery();
        while(rs.next())
                {
                    
                    oblist.add(new Model_Customer(rs.getString("C_Name"),rs.getInt("Meter_No"),rs.getDouble("C_Balance"),null));
                    
                }
            mt_tc.setCellValueFactory(new PropertyValueFactory<>("Meter_no"));
            name_tc.setCellValueFactory(new PropertyValueFactory<>("customer_name"));
            blnc_tc.setCellValueFactory(new PropertyValueFactory<>("Balance"));
            Delete_tc.setCellValueFactory(new PropertyValueFactory<>("Delete"));
            chkcustomer_tv.setItems(oblist);
            }
            catch(Exception e)
                    {
                    
                    }
    }
    public void setName(String a)
    {
        username_lb.setText(a);
    }
    public void logout(ActionEvent event)throws Exception
    {
        
        
        loader.setLocation(getClass().getResource("EBMS.fxml"));
        Parent logout = loader.load();
        EBMSController controller = loader.getController(); 
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene log = new Scene(logout);
        window.setScene(log);
        window.setTitle("EBMS Home");
        window.show();
    }
    public void goback(ActionEvent event) throws Exception
    {
        loader.setLocation(getClass().getResource("AdminPanel.fxml"));
        Parent goback = loader.load();
        AdminPanelController controller = loader.getController();
        controller.setName(username_lb.getText());
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene back = new Scene(goback);
        window.setScene(back);
        window.setTitle("Admin Panel");
        window.show();
    }
     
}
