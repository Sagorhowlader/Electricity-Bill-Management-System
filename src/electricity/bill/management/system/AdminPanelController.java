
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

public class AdminPanelController implements Initializable {

   
    @FXML
    private Label back_lb;

    @FXML
    private Label copy_lb;

    @FXML
    private Button set_blls_btn;

    @FXML
    private Button chck_cus_btn;

    @FXML
    private Button chck_bills_btn;

    @FXML
    private Label wel_lb;

    @FXML
    private Label username_lb;

    @FXML
    private Hyperlink log_hl;

    @FXML
    private Button chkhashbtn;

    @FXML
    private Button sethashbtn;

    @FXML
    private Button cre_cus;
    
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
    public void crecus(ActionEvent event) throws Exception
    {
        loader.setLocation(getClass().getResource("CustomerCreate.fxml"));
        Parent CCPanel = loader.load();
        CustomerCreateController controller= loader.getController();
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene CCreatePanel = new Scene(CCPanel);
        window.setScene(CCreatePanel);
        window.setTitle("Customer Creation Panel");
        window.show();
    }
    public void CheckCus(ActionEvent event) throws Exception
    {
        loader.setLocation(getClass().getResource("CheckCustomers.fxml"));
        Parent chckcus = loader.load();
        CheckCustomersController controller = loader.getController();
        controller.setName(username_lb.getText());
        controller.CheckData();
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene chckcst = new Scene(chckcus);
        window.setScene(chckcst);
        window.setTitle("Check Customers");
        window.show();
    }
    public void SetBills(ActionEvent event) throws Exception
    {
        loader.setLocation(getClass().getResource("SetBills.fxml"));
        Parent setBills = loader.load();
        SetBillsController controller = loader.getController();
        controller.setCombo();
        controller.setName(username_lb.getText());
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene setbls = new Scene(setBills);
        window.setScene(setbls);
        window.setTitle("Set Bills");
        window.show();
    }
    public void checkBills(ActionEvent event) throws Exception
    {
        loader.setLocation(getClass().getResource("CheckBills.fxml"));
        Parent chckbills = loader.load();
        CheckBillsController controller = loader.getController();
        controller.setCombo();
        controller.setName(username_lb.getText());
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene chckbls = new Scene(chckbills);
        window.setScene(chckbls);
        window.setTitle("Check Bills");
        window.show();
    }
    public void checkHash(ActionEvent event) throws Exception
    {
        loader.setLocation(getClass().getResource("CheckHash.fxml"));
        Parent chckhash = loader.load();
        CheckHashController controller = loader.getController();
        controller.setName(username_lb.getText());
        controller.CheckData();
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene chckhsh = new Scene(chckhash);
        window.setScene(chckhsh);
        window.setTitle("Check Hash");
        window.show();
    }
    
    public void setHash(ActionEvent event) throws Exception
    {
        loader.setLocation(getClass().getResource("SetHash.fxml"));
        Parent sethash = loader.load();
        SetHashController controller = loader.getController();
        controller.setName(username_lb.getText());
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene sthash = new Scene(sethash);
        window.setScene(sthash);
        window.setTitle("Set Hash");
        window.show();
    }
   
    
}
