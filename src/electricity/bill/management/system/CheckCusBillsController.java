
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

public class CheckCusBillsController implements Initializable {

    @FXML
    private Label back_lb;

    @FXML
    private Label username_lb;

    @FXML
    private Label copy_lb;

    @FXML
    private Label wel_lb;

    @FXML
    private TableColumn<CusBills, String> mnth_tc;

    @FXML
    private TableColumn<CusBills,String> status_tc;

    @FXML
    private Hyperlink log_hl;

    @FXML
    private Label meter_no_lb;

    @FXML
    private TableColumn<CusBills, Double> amount_tc;

    @FXML
    private Label cst_name_lb;

    @FXML
    private Hyperlink goback_hl;

    @FXML
    private TableView<CusBills> chkbills_tv;
    
    FXMLLoader loader = new FXMLLoader();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
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
    public void checkBills()
    {
        try{
        ObservableList<CusBills> oblist = FXCollections.observableArrayList();
        DBConnect connect = new DBConnect();
        Connection conn = connect.connectDb();
        String get = "Select `Meter_No` From tb_customer WHERE C_Name='"+username_lb.getText()+"';";
        
        PreparedStatement getmeter = conn.prepareStatement(get);
        ResultSet rsget = getmeter.executeQuery();
        rsget.next();
        int meter_no=rsget.getInt("Meter_No");
        PreparedStatement column=conn.prepareStatement("Select `Bill_Month`,`Amount`,`Status` FROM tb_"+meter_no+" WHERE Amount IS NOT NULL;");
        ResultSet rs = column.executeQuery();
        while(rs.next())
                {
                    
                oblist.add(new CusBills(rs.getString("Bill_Month"),rs.getDouble("Amount"),rs.getString("Status")));
                    
                }
            mnth_tc.setCellValueFactory(new PropertyValueFactory<>("Month"));
            amount_tc.setCellValueFactory(new PropertyValueFactory<>("Amount"));
            status_tc.setCellValueFactory(new PropertyValueFactory<>("Status"));
            chkbills_tv.setItems(oblist);
            }
            catch(Exception e)
                    {
                    System.out.println(e);
                    }
    }
}
