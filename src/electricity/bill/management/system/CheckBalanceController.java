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

public class CheckBalanceController implements Initializable {

    @FXML
    private Label back_lb;

    @FXML
    private Label username_lb;

    @FXML
    private Label copy_lb;

    @FXML
    private Label wel_lb;

    @FXML
    private Label crnt_lb;

    @FXML
    private Hyperlink log_hl;

    @FXML
    private Label taka_lb;

    @FXML
    private Label blnc_lb;

    @FXML
    private Hyperlink goback_hl;
    
    FXMLLoader loader = new FXMLLoader();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
        loader.setLocation(getClass().getResource("CustomerPanel.fxml"));
        Parent goback = loader.load();
        CustomerPanelController controller = loader.getController();
        controller.setName(username_lb.getText());
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene back = new Scene(goback);
        window.setScene(back);
        window.setTitle("Customer Panel");
        window.show();
    }
    
   public void setName(String a)
    {
        username_lb.setText(a);
    }
     public void setData()
     {
         try{
        DBConnect connect = new DBConnect();
        Connection conn = connect.connectDb();
        PreparedStatement column=conn.prepareStatement("Select `C_Balance` FROM tb_customer WHERE C_Name='"+username_lb.getText()+"';");
        ResultSet result = column.executeQuery();
        result.next();
        double blnc = result.getInt("C_Balance");
        String balance = String.valueOf(blnc);
        blnc_lb.setText(balance);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
     }
    
}
