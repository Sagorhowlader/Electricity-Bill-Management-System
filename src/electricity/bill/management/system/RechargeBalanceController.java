
package electricity.bill.management.system;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;
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

public class RechargeBalanceController implements Initializable {

    @FXML
    private Button Rchrg_btn;

    @FXML
    private Label back_lb;

    @FXML
    private Label username_lb;

    @FXML
    private Label hash_lb;

    @FXML
    private Label copy_lb;

    @FXML
    private Label wel_lb;

    @FXML
    private Hyperlink log_hl;

    @FXML
    private Hyperlink goback_hl;

    @FXML
    private Label pass_lb;

    @FXML
    private PasswordField pass_pf;

    @FXML
    private TextField hash_tf;
    
    FXMLLoader loader = new FXMLLoader();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
   } 
    
    public void recharge()
    {
        try{
            if(hash_tf.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(null, "Please Enter Secret Code");
            }
            else if(hash_tf.getText().length()!=15)
            {
                JOptionPane.showMessageDialog(null, "Invalid Secret Code");
            }
            else if (pass_pf.getText().isEmpty())
            {
                
            JOptionPane.showMessageDialog(null, "Please Enter Password");
            }
        else{
            DBConnect connection = new DBConnect();
            Connection conn = connection.connectDb(); 
                  String query = "Select `C_Password` From tb_customer WHERE C_Name='"+username_lb.getText()+"';";
                  
                  PreparedStatement ps = conn.prepareStatement(query);
                  ResultSet rs = ps.executeQuery(query);
                  ResultSet a=rs;
                
                  rs.next();
                      
                  String password = a.getString("C_Password");
                      if(password.equals(pass_pf.getText()))
                      {
                        double amount=0.0;
                        String status;
                        String shash = "Select * From `tb_hash` WHERE `Hash_Code`="+hash_tf.getText()+";";
                        
                        PreparedStatement phash = conn.prepareStatement(shash);
                        ResultSet rhash = ps.executeQuery(shash);
                       if(rhash.next()){
                        
                        amount = rhash.getDouble("Amount");
                        status=rhash.getString("Status");
                        
                      
                  
                 
                  if(status.equals("Used")){
                       JOptionPane.showMessageDialog(null, "Recharge Failed. Please Check your HashCode. \nUsing the same code twice will result in error.");
                  }
                  else{
                          String update = ("UPDATE tb_customer SET C_Balance= C_Balance+"+amount+" WHERE C_Name='"+username_lb.getText()+"';");
                          String change = ("UPDATE tb_hash SET Status='Used' WHERE Hash_Code="+hash_tf.getText()+";");
                          PreparedStatement up = conn.prepareStatement(update);
                          PreparedStatement chng = conn.prepareStatement(change);
                          up.execute();
                          chng.execute();
                          hash_tf.setText("");
                          pass_pf.setText("");
                          Random rand = new Random();
                          int txid = 10000+rand.nextInt(90000);
                       JOptionPane.showMessageDialog(null, "Recharge Successful\nTransaction ID :"+txid+"\nRecharge Amount : "+amount+"\nThank you for using our service");
                  }
                  } 
                   else{
                           JOptionPane.showMessageDialog(null, "Secret Code not found please check your Code");
                       }
                  }
                      
                      else
                      {
                          JOptionPane.showMessageDialog(null, "Recharge Failed. Please Check your Password");
                      }
                }
            
        }
        catch(Exception e)
        {
            System.out.println(e);
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
}
    
