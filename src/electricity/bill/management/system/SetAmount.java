package electricity.bill.management.system;

import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

public class SetAmount {
    
    public boolean setAmount(int meter,String month,String Year,double amount)
    {
        try{
            DBConnect connection = new DBConnect();
            Connection conn = connection.connectDb(); 
            String insert ="INSERT INTO `tb_"+meter+"` (`Meter_No`,`Customer_Name`, `Bill_Month`, `Amount`, `Status`) VALUES (DEFAULT,DEFAULT,?,?,?);";
            PreparedStatement st =conn.prepareStatement(insert);
            st.setString(1, month+"-"+Year);
            st.setDouble(2, amount);
            st.setString(3, "Unpaid");
            st.execute();
            return true;
        }
        catch(Exception e)
        {
            return false;
        }
    }
}
