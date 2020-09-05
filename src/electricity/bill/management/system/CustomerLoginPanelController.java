
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
import javax.swing.JOptionPane;


public class CustomerLoginPanelController implements Initializable {

    
    @FXML
    private Label back_lb;

    @FXML
    private PasswordField up_tf;

    @FXML
    private Label copy_lb;

    @FXML
    private TextField un_tf;

    @FXML
    private Button login_btn;

    @FXML
    private Label un_label;

    @FXML
    private Label up_lb;
    FXMLLoader loader = new FXMLLoader();
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
    }
    public void login(ActionEvent event) throws Exception
    {
        if(un_tf.getText().isEmpty())
                    {
                        JOptionPane.showMessageDialog(null, "Please Enter Username ");
                    }
                    else if(up_tf.getText().isEmpty())
                    {
                        JOptionPane.showMessageDialog(null, "Please Enter Password");
                    }
                    else{
                    String user_name=un_tf.getText();
                    String user_password= up_tf.getText();
                    
                    Login in = new Login();
    
                    if(in.Log("Customer",user_name,user_password))
                    {
                    loader.setLocation(getClass().getResource("CustomerPanel.fxml"));
                    Parent CustomerPanel = loader.load();
                    CustomerPanelController controller= loader.getController();
                    controller.setName(user_name); 
                    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                    Scene Cuspanel = new Scene(CustomerPanel);
                    window.setScene(Cuspanel);
                    window.setTitle("Customer Panel");
                    window.show();
                    }
                    else
                    { 
                    JOptionPane.showMessageDialog(null, "Login Failed. Please Check your Name and Password or Create a new ID");
                    }
                    }
                    
        
    }
    
    public void goback(ActionEvent event) throws Exception
    {
        loader.setLocation(getClass().getResource("EBMS.fxml"));
        Parent goback = loader.load();
        EBMSController controller = loader.getController();
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene back = new Scene(goback);
        window.setScene(back);
        window.setTitle("EBMS Home");
        window.show();
    }
    public void Signup(ActionEvent event) throws Exception
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
    
    
}
