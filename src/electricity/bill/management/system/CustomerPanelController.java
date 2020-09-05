
package electricity.bill.management.system;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class CustomerPanelController implements Initializable {

    @FXML
    private Button chck_bills_btn;

    @FXML
    private Label back_lb;

    @FXML
    private Label username_lb;

    @FXML
    private Label copy_lb;

    @FXML
    private Label wel_lb;

    @FXML
    private Button chck_blnc_btn;

    @FXML
    private Button pay_bls_btn;

    @FXML
    private Button rechrg_btn;
    
    FXMLLoader loader = new FXMLLoader();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
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
    public void CheckBalance(ActionEvent event) throws Exception
    {
        loader.setLocation(getClass().getResource("CheckBalance.fxml"));
        Parent balance = loader.load();
        CheckBalanceController controller = loader.getController(); 
        controller.setName(username_lb.getText());
        controller.setData();
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene blnc = new Scene(balance);
        window.setScene(blnc);
        window.setTitle("Check Balance");
        window.show();
    }
     public void rechargeBalance(ActionEvent event) throws Exception
    {
        loader.setLocation(getClass().getResource("RechargeBalance.fxml"));
        Parent balance = loader.load();
        RechargeBalanceController controller = loader.getController(); 
        controller.setName(username_lb.getText());
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene blnc = new Scene(balance);
        window.setScene(blnc);
        window.setTitle("Recharge Balance");
        window.show();
    }
     public void checkCusBills(ActionEvent event) throws Exception
    {
        loader.setLocation(getClass().getResource("CheckCusBills.fxml"));
        Parent checkbills = loader.load();
        CheckCusBillsController controller = loader.getController(); 
        controller.setName(username_lb.getText());
        controller.checkBills();
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene bills = new Scene(checkbills);
        window.setScene(bills);
        window.setTitle("Check Bills");
        window.show();
    }
     public void PayBills(ActionEvent event) throws Exception
    {
        loader.setLocation(getClass().getResource("PayBills.fxml"));
        Parent paybills = loader.load();
        PayBillsController controller = loader.getController(); 
        controller.setName(username_lb.getText());
        controller.setCombo();
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene bills = new Scene(paybills);
        window.setScene(bills);
        window.setTitle("Pay Bills");
        window.show();
    }
    
    
}
