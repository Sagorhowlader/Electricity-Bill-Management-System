package electricity.bill.management.system;


import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class DBConnect {
    
        public  Connection conn=null;
    public  Connection connectDb(){
        if(conn==null){
            try{
                Class.forName("com.mysql.jdbc.Driver");
                final String url="jdbc:mysql://127.0.0.1:3306/db_ebms";
                final String user="root";
                final String pass="";
                conn=DriverManager.getConnection(url,user,pass);
                return conn;
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
            }
            
        }
        return conn;
        
    }

}

