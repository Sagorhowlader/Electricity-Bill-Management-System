
package electricity.bill.management.system;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class DeleteHash {
    public boolean Delete(Long hash)
    {
        try{
            DBConnect connection = new DBConnect();
            Connection conn = connection.connectDb(); 
            String delete="DELETE FROM `tb_hash` WHERE Hash_Code="+hash+";";
            PreparedStatement del = conn.prepareStatement(delete);
            del.execute();
            return true;
        }
        catch(Exception e)
        {
            System.out.println(e);
        return false;
        }
    }
    
}
