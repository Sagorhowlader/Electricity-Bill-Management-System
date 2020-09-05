package electricity.bill.management.system;

import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

public class CreateCustomer {
    
    public boolean create(int Meter_no,String User,String Pass)
    {
        try{
            DBConnect connection = new DBConnect();
            Connection conn = connection.connectDb(); 
            String query="INSERT INTO `tb_customer` (`Meter_No`, `C_Name`, `C_Password`,`C_Balance`) VALUES (?, ?, ?,0);";
            PreparedStatement st =conn.prepareStatement(query);
            st.setInt(1, Meter_no);
            st.setString(2, User);
            st.setString(3, Pass);
            st.execute();
            String create="CREATE TABLE tb_"+Meter_no+" (Meter_No int(20) DEFAULT "+Meter_no+",Customer_Name VARCHAR(20) DEFAULT '"+User+"', Bill_Month VARCHAR(20),Amount VARCHAR(20),Status Varchar(20), UNIQUE (Meter_No,Customer_Name,Bill_Month));";
            
            PreparedStatement cr =conn.prepareStatement(create);
            cr.execute();
            String insert ="INSERT INTO `tb_"+Meter_no+"` (`Meter_No`,`Customer_Name`, `Bill_Month`, `Amount`, `Status`) VALUES (DEFAULT,DEFAULT,NULL,NULL,NULL);";
            PreparedStatement ins = conn.prepareStatement(insert);
            ins.execute();
            return true;
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
        
    }
    }
