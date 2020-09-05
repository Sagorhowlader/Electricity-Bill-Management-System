
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

public class SetHashController implements Initializable {

    
    @FXML
    private TextField hash_tf;

    @FXML
    private Label Amount_lb;

    @FXML
    private Label back_lb;

    @FXML
    private Label username_lb;

    @FXML
    private TextField amount_tf;

    @FXML
    private Label hash_lb;

    @FXML
    private Label copy_lb;

    @FXML
    private Label wel_lb;

    @FXML
    private Hyperlink log_hl;

    @FXML
    private Button set_btn;

    @FXML
    private Hyperlink goback_hl;

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
    public void set(ActionEvent event) throws Exception
    {
        
        if(hash_tf.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Please Enter Hash Code");
        }
        else if(amount_tf.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Please Enter Amount");
        }
        else if(hash_tf.getText().length()!=15)
                {
                     JOptionPane.showMessageDialog(null, "Invalid Hash Code");
                }
        else{
        SetHashes hash = new SetHashes();
        if(hash.setHash(Long.parseLong(hash_tf.getText()), Double.parseDouble(amount_tf.getText())))
        {
            JOptionPane.showMessageDialog(null, "Data Inserted Successfully");
            hash_tf.setText("");
            amount_tf.setText("");
            
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Data Insertion Failed");
        }
        }
        
    }
    
}
