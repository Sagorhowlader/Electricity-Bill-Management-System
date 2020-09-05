
package electricity.bill.management.system;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class HashesModel {
    private long hash;
    private double amount;
    private String status;
    private Button delete;
    private static String username;

    public void setDelete(Button delete) {
        this.delete = delete;
    }

    public Button getDelete() {
        return delete;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setHash(long hash) {
        this.hash = hash;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public long getHash() {
        return hash;
    }

    public double getAmount() {
        return amount;
    }

    public HashesModel() {
    }
    public HashesModel(long hash, double amount, String status, Button delete) {
        this.hash = hash;
        this.amount = amount;
        this.status = status;
        this.delete = new Button("Delete");
        this.delete.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                DeleteHash del = new DeleteHash();
                
                if(del.Delete(hash))
                {
                    try{
                    
                    JOptionPane.showMessageDialog(null, "Data Deleted Successfully");
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("CheckHash.fxml"));
                    Parent logout = loader.load();
                    CheckHashController controller = loader.getController();
                    controller.setName(username);
                    controller.CheckData();
                    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                    Scene log = new Scene(logout);
                    window.setScene(log);
                    window.setTitle("Check Hash");
                    window.show();
                    }
                    catch(Exception e)
                    {
                        System.out.println(e);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "Failed to delete Hash Code");
                }
            }
        });
    }
}
