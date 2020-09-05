package electricity.bill.management.system;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class Model_Customer {
 
    private static String username;

    public void setUsername(String username) {
        this.username = username;
    }
    
    private String customer_name;
    private int Meter_no;
    private double Balance;
    private Button Delete;

    public Button getDelete() {
        return Delete;
    }

    public void setDelete(Button Delete) {
       
        this.Delete = Delete;
    }

   

    public Model_Customer(String customer_name, int Meter_no, double Balance, Button Delete) {
         CheckCustomersController del = new CheckCustomersController();
        this.customer_name = customer_name;
        this.Meter_no = Meter_no;
        this.Balance = Balance;
        this.Delete = new Button("Delete");
        this.Delete.setOnAction(new EventHandler<ActionEvent>(){

             @Override
             public void handle(ActionEvent event) {
                DeleteCus del = new DeleteCus();
                
                if(del.Delete(Meter_no))
                {
                    try{
                    
                    JOptionPane.showMessageDialog(null, "Data Deleted Successfully");
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("CheckCustomers.fxml"));
                    Parent logout = loader.load();
                    CheckCustomersController controller = loader.getController();
                    controller.setName(username);
                    controller.CheckData();
                    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                    Scene log = new Scene(logout);
                    window.setScene(log);
                    window.setTitle("Check Customeres");
                    window.show();
                    }
                    catch(Exception e)
                    {
                        System.out.println(e);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "Failed to delete Customer");
                }
             }
         });
    }
    

    public Model_Customer() {
    }

    public double getBalance() {
        return Balance;
    }

    public void setBalance(double Balance) {
        this.Balance = Balance;
    }
    
    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public void setMeter_no(int Meter_no) {
        this.Meter_no = Meter_no;
    }

    public int getMeter_no() {
        return Meter_no;
    }

    public String getCustomer_name() {
        return customer_name;
    }
}
