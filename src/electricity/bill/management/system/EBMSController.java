
package electricity.bill.management.system;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class EBMSController implements Initializable {
    
    @FXML
    private Button user_type_admin;

    @FXML
    private Button user_login_customer;

    @FXML
    private Label back_lb;

    @FXML
    private Label copy_lb;

    @FXML
    private Label user_type_label;
    
    FXMLLoader loader = new FXMLLoader();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    public void Admin(ActionEvent event)throws Exception
    {
        loader.setLocation(getClass().getResource("AdminLoginPanel.fxml"));
        Parent adminlog = loader.load();
        AdminLoginPanelController controller = loader.getController(); 
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene log = new Scene(adminlog);
        window.setScene(log);
        window.setTitle("Admin Login Panel");
        window.show();
        
    }
    public void Customer(ActionEvent event)throws Exception
    {
        loader.setLocation(getClass().getResource("CustomerLoginPanel.fxml"));
        Parent cuslog = loader.load();
        CustomerLoginPanelController controller = loader.getController(); 
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene log = new Scene(cuslog);
        window.setScene(log);
        window.setTitle("Customer Login Panel");
        window.show();
       
    }

    
}
