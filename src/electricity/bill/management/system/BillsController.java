package electricity.bill.management.system;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class BillsController implements Initializable {

    
    @FXML
    private Label back_lb;

    @FXML
    private Label wel_lb;

    @FXML
    private Label cust_info_lb;

    @FXML
    private TableColumn<Bills, String> status_tc;

    @FXML
    private Hyperlink log_hl;

    @FXML
    private Label meter_no_lb;

    @FXML
    private Label cst_name_lb;

    @FXML
    private Hyperlink goback_hl;

    @FXML
    private TableView<Bills> chkbills_tv;

    @FXML
    private Label username_lb;

    @FXML
    private Label copy_lb;

    @FXML
    private TableColumn<Bills, String> mnth_tc;

    @FXML
    private TableColumn<Bills,Double> amount_tc;

    FXMLLoader loader = new FXMLLoader();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    
    
    
    public void CheckData(int meter_no)
    {
        
       
        try{
        ObservableList<Bills> oblist = FXCollections.observableArrayList();
        DBConnect connect = new DBConnect();
        Connection conn = connect.connectDb();
        PreparedStatement column=conn.prepareStatement("Select `Bill_Month`,`Amount`,`Status` FROM tb_"+meter_no+" WHERE Amount IS NOT NULL;");
        ResultSet rs = column.executeQuery();
        while(rs.next())
                {
                    
                oblist.add(new Bills(rs.getString("Bill_Month"),rs.getDouble("Amount"),rs.getString("Status")));
                    
                }
            mnth_tc.setCellValueFactory(new PropertyValueFactory<>("month"));
            amount_tc.setCellValueFactory(new PropertyValueFactory<>("amount"));
            status_tc.setCellValueFactory(new PropertyValueFactory<>("status"));
            chkbills_tv.setItems(oblist);
            }
            catch(Exception e)
                    {
                    
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
    
    public void setName(String a)
    {
        username_lb.setText(a);
    }
    public void setData(int meter)
    {
        try{
        DBConnect connect = new DBConnect();
        Connection conn = connect.connectDb();
        PreparedStatement column=conn.prepareStatement("Select `Meter_No`,`Customer_Name` FROM tb_"+meter+";");
        ResultSet result = column.executeQuery();
        result.next();
        cst_name_lb.setText(result.getString("Customer_Name"));
        int a = result.getInt("Meter_No");
        String meter_no=String.valueOf(a);
        meter_no_lb.setText(meter_no);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    
}
