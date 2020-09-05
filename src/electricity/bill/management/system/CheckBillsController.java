package electricity.bill.management.system;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class CheckBillsController implements Initializable {

    FXMLLoader loader = new FXMLLoader();
    
    @FXML
    private Label mtr_no_lb;

    @FXML
    private ComboBox<Integer> meter_no_cb;

    @FXML
    private Button chk_btn;

    @FXML
    private Label back_lb;

    @FXML
    private Label username_lb;

    @FXML
    private Label copy_lb;

    @FXML
    private Label wel_lb;

    @FXML
    private Hyperlink log_hl;

    @FXML
    private Hyperlink goback_hl;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    public void setName(String a)
    {
        username_lb.setText(a);
    }
    public void setCombo()
    {
        DBConnect connection = new DBConnect();
            Connection conn = connection.connectDb(); 
       try{
        PreparedStatement statement1 = conn.prepareStatement("Select Meter_No from tb_customer");
        ResultSet combo1 = statement1.executeQuery();
        
        while (combo1.next()) {
            meter_no_cb.getItems().addAll(combo1.getInt("Meter_no")); 
       }
       }
        catch(Exception e)
                {
                
                }
        
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
    
    public void checkBills(ActionEvent event) throws Exception
    {
        
        if( meter_no_cb.selectionModelProperty().getValue().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Please choose a Meter Number");
        } 
        else{
        loader.setLocation(getClass().getResource("Bills.fxml"));
        Parent Bills = loader.load();
        BillsController controller = loader.getController();
        controller.setName(username_lb.getText());
        controller.setData(meter_no_cb.getValue());
        controller.CheckData(meter_no_cb.getValue());
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene bill = new Scene(Bills);
        window.setScene(bill);
        window.setTitle("Check Bills");
        window.show();
        }
    }
    
    
}
