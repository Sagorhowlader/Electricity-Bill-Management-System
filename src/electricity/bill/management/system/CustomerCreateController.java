
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
import javax.swing.JOptionPane;

public class CustomerCreateController implements Initializable {

    @FXML
    private Button crt_btn;

    @FXML
    private Label back_lb;

    @FXML
    private PasswordField up_tf;

    @FXML
    private Label copy_lb;

    @FXML
    private Label meter_no_lb;

    @FXML
    private TextField un_tf;

    @FXML
    private Label un_label;

    @FXML
    private TextField mtr_tf;

    @FXML
    private Label up_lb;
    
    FXMLLoader loader = new FXMLLoader();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }
    public void CreateCus(ActionEvent event) throws Exception
    {
        if(mtr_tf.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Please Enter Meter No.");
        }
        else if(un_tf.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Please Enter User Name");
        }
        else if(up_tf.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Please Enter Password");
        }
        else{
        String meter=mtr_tf.getText();
        int Meter = Integer.parseInt(meter);
        String user = un_tf.getText();
        String pass = up_tf.getText();
        CreateCustomer cus = new CreateCustomer();
        if(cus.create(Meter,user,pass)){
        JOptionPane.showMessageDialog(null, "Customer Created Successfully. Please Login");
        loader.setLocation(getClass().getResource("CustomerLoginPanel.fxml"));
        Parent cuslog = loader.load();
        CustomerLoginPanelController controller = loader.getController(); 
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene log = new Scene(cuslog);
        window.setScene(log);
        window.setTitle("Customer Login Panel");
        window.show();
        }
        
        else
        {
            JOptionPane.showMessageDialog(null, "Customer Creation Failed. Please check the credentials");
        }
        }
        
    }
    public void goback(ActionEvent event) throws Exception
    {
        loader.setLocation(getClass().getResource("CustomerLoginPanel.fxml"));
        Parent goback = loader.load();
        CustomerLoginPanelController controller = loader.getController();
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene back = new Scene(goback);
        window.setScene(back);
        window.setTitle("Customer Login Panel");
        window.show();
    }
    
}
