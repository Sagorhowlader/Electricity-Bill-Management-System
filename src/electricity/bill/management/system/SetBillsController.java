package electricity.bill.management.system;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class SetBillsController implements Initializable {

    @FXML
    private Label Amount_lb;

    @FXML
    private ComboBox<String> month_cb;

    @FXML
    private Label back_lb;

    @FXML
    private Label wel_lb;

    @FXML
    private Hyperlink log_hl;

    @FXML
    private Label month_lb;

    @FXML
    private Button set_btn;

    @FXML
    private Hyperlink goback_hl;

    @FXML
    private Label mtr_no_lb;

    @FXML
    private ComboBox<Integer> meter_no_cb;

    @FXML
    private Label username_lb;

    @FXML
    private TextField amount_tf;

    @FXML
    private Label copy_lb;
    
    @FXML
    private TextField year_tf;

    
    FXMLLoader loader = new FXMLLoader();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
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
        month_cb.getItems().addAll("January","February","March","April","May","June","July","August","September","October","November","December");
        
        
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
    public void setBills(ActionEvent event) throws Exception
    {
        if(meter_no_cb.selectionModelProperty().getValue().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Please Choose Meter No");
        }
        else if(month_cb.selectionModelProperty().getValue().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Please Choose Month");
        }
        else if(year_tf.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Please Enter Year");
        }
        else if(amount_tf.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Please Enter Amount");
        }
        else{
        SetAmount amount = new SetAmount();
        if(amount.setAmount(meter_no_cb.getValue(),month_cb.getValue(),year_tf.getText(), Double.parseDouble(amount_tf.getText())))
        {
            JOptionPane.showMessageDialog(null, "Data Inserted Successfully");
            meter_no_cb.valueProperty().set(null);
            month_cb.valueProperty().set(null);
            year_tf.setText(null);
            amount_tf.setText(null);
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Data Insertion Failed");
        }
        }
    }
        
    
}
