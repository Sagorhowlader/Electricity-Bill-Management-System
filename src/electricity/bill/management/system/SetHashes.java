package electricity.bill.management.system;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class SetHashes {
    
    public boolean setHash(long Hash,double amount)
    {
        try{
            DBConnect connection = new DBConnect();
            Connection conn = connection.connectDb(); 
            String insert ="INSERT INTO `tb_hash` (`Hash_Code`, `Amount`,`Status`) VALUES (?,?,?);";
            PreparedStatement st =conn.prepareStatement(insert);
            st.setLong(1, Hash);
            st.setDouble(2, amount);
            st.setString(3, "Unused");
            st.execute();
            return true;
        }
        catch(Exception e)
        {
            return false;
        }
    }
    
}
