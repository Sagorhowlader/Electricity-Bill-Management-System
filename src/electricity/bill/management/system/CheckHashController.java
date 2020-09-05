
package electricity.bill.management.system;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class CheckHashController implements Initializable {

    
    @FXML
    private Label back_lb;

    @FXML
    private Label wel_lb;

    @FXML
    private TableColumn<HashesModel, String> status_tc;

    @FXML
    private Hyperlink log_hl;

    @FXML
    private Hyperlink goback_hl;

    @FXML
    private TableView<HashesModel> chkhashes_tv;
    
    @FXML
    private TableColumn<HashesModel, Button> action_tc;

    @FXML
    private Label username_lb;

    @FXML
    private Label copy_lb;

    @FXML
    private TableColumn<HashesModel, Long> hashcd_tc;

    @FXML
    private TableColumn<HashesModel, Double> amount_tc;
    
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
    
    public void CheckData()
    {
        
       
        HashesModel hash = new HashesModel();
        hash.setUsername(username_lb.getText());
        try{
        ObservableList<HashesModel> oblist = FXCollections.observableArrayList();
        DBConnect connect = new DBConnect();
        Connection conn = connect.connectDb();
        PreparedStatement column=conn.prepareStatement("Select `Hash_Code`,`Amount`,`Status` FROM `tb_hash`;");
        ResultSet rs = column.executeQuery();
        while(rs.next())
                {
                    
                oblist.add(new HashesModel(rs.getLong("Hash_Code"),rs.getDouble("Amount"),rs.getString("Status"),null));
                    
                }
            hashcd_tc.setCellValueFactory(new PropertyValueFactory<>("hash"));
            amount_tc.setCellValueFactory(new PropertyValueFactory<>("amount"));
            status_tc.setCellValueFactory(new PropertyValueFactory<>("status"));
            action_tc.setCellValueFactory(new PropertyValueFactory<>("delete"));
            chkhashes_tv.setItems(oblist);
            }
            catch(Exception e)
                    {
                    
                    }
    }
}
