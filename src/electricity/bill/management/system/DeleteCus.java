
package electricity.bill.management.system;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class DeleteCus {
    public boolean Delete(int meter_no)
    {
        try{
            DBConnect connection = new DBConnect();
            Connection conn = connection.connectDb(); 
            String delete="DELETE FROM tb_customer WHERE Meter_No="+meter_no+";";
            String drop = "DROP TABLE `tb_"+meter_no+";";
            PreparedStatement del = conn.prepareStatement(delete);
            PreparedStatement Drop = conn.prepareStatement(drop);
            del.execute();
            Drop.execute();
            return true;
        }
        catch(Exception e)
        {
            System.out.println(e);
        return false;
        }
    }
}
