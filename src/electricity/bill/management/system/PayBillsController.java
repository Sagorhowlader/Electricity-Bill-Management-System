package electricity.bill.management.system;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javax.accessibility.AccessibleAction;
import javax.swing.JOptionPane;
public class PayBillsController implements Initializable {
     @FXML
    private Label mtr_no_lb;

    @FXML
    private Label bill_lb;

    @FXML
    private ComboBox<String> month_cb;

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
    private Label month_lb;

    @FXML
    private Hyperlink goback_hl;

    @FXML
    private Button pay_bill_btn;
    
    @FXML
    private PasswordField pass_pf;

    @FXML
    private Label pass_lb;
    
    @FXML
    private ProgressBar prog;

    
    FXMLLoader loader = new FXMLLoader();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }
    public void setCombo()
    {
        DBConnect connection = new DBConnect();
            Connection conn = connection.connectDb(); 
       try{
        String get = "Select `Meter_No` From tb_customer WHERE C_Name='"+username_lb.getText()+"';";
        PreparedStatement getmeter = conn.prepareStatement(get);
        ResultSet rsget = getmeter.executeQuery();
        rsget.next();
        int meter_no=rsget.getInt("Meter_No");
        PreparedStatement column=conn.prepareStatement("Select `Bill_Month`,`Amount`,`Status` FROM tb_"+meter_no+" WHERE Status='Unpaid';");
        ResultSet rs = column.executeQuery();
        
        while (rs.next()) {
            month_cb.getItems().addAll(rs.getString("Bill_Month")); 
       } 
       ObservableList<String> a=month_cb.itemsProperty().getValue();
        if( a.isEmpty())
        {
            month_cb.getItems().addAll("No Unpaid Bills"); 
        } 
       }
       catch(Exception e)
       {
           System.out.println(e);
       }
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
    
    
    
    
    
    
    public void payBills()
    {
        String month = month_cb.getValue();
        if(month=="No Unpaid Bills")
        {
            JOptionPane.showMessageDialog(null, "No Unpaid Bills. Wait For Next Bill");
        }
        else{
        
        try{
            DBConnect connection = new DBConnect();
            Connection conn = connection.connectDb(); 
                  String query = "Select `C_Password` From tb_customer WHERE C_Name='"+username_lb.getText()+"';";
                  
                  PreparedStatement ps = conn.prepareStatement(query);
                  ResultSet rs = ps.executeQuery(query);
                  ResultSet a=rs;
                
                  rs.next();
                      
                  String password = a.getString("C_Password");
        
            if(month_cb.selectionModelProperty().getValue().isEmpty())
            {
                JOptionPane.showMessageDialog(null, "Please Choose Month");
            }
            else if(pass_pf.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(null, "Please Enter Password");
            }
            else if(password.equals(pass_pf.getText()))
            {
            String balance = "Select `Meter_No`,`C_Balance` FROM tb_customer WHERE C_Name='"+username_lb.getText()+"';";
            PreparedStatement blnc = conn.prepareStatement(balance);
            ResultSet rsblnc = blnc.executeQuery();
            rsblnc.next();
            double Balance = rsblnc.getDouble("C_Balance");
            int Meter = rsblnc.getInt("Meter_No");
            String Due = "Select `Amount` FROM tb_"+Meter+" WHERE `Bill_Month`='"+month+"';";
            PreparedStatement due = conn.prepareStatement(Due);
            ResultSet rsdue = due.executeQuery();
            rsdue.next();
            double Amount = rsdue.getDouble("Amount");
            if(Amount >Balance)
            {
               JOptionPane.showMessageDialog(null, "Insufficient Balance. Please Recharge Account");
            }
            else
            {
               
                String pay = "UPDATE tb_"+Meter+" SET `Status`='Paid' WHERE `Bill_Month`='"+month+"';";
                String payment = "UPDATE tb_customer SET `C_Balance`=C_Balance-"+Amount+" WHERE `C_Name`='"+username_lb.getText()+"';";
                PreparedStatement Pay = conn.prepareStatement(pay);
                PreparedStatement Payment = conn.prepareStatement(payment);
                Payment.execute();
                Pay.execute();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Transaction Processing");
                alert.setHeaderText(null);
                alert.getDialogPane().lookupButton(ButtonType.OK).setDisable(true);
                alert.setContentText("Transaction is processing.");
                alert.show();
                synchronized(alert){
                alert.wait(2000);
                }
                alert.setTitle("Bill Payment Complete");
                alert.setContentText("Transaction Complete. Bill payment successfull.");
                alert.getDialogPane().lookupButton(ButtonType.OK).setDisable(false);
                goback(null);
                
            }
            }
            else{
                JOptionPane.showMessageDialog(null, "Invalid Password");
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
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
    
    
   public void setName(String a)
    {
        username_lb.setText(a);
    }
    
}
