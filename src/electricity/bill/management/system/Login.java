package electricity.bill.management.system;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class Login {
    
    
    public boolean Log(String type,String name, String pass){
        ElectricityBillManagementSystem system = new ElectricityBillManagementSystem();
        try{
            DBConnect connection = new DBConnect();
            Connection conn = connection.connectDb();

                  String query = "Select * From tb_"+type;
                  PreparedStatement ps = conn.prepareStatement(query);
                  ResultSet rs = ps.executeQuery(query);
                  ResultSet a=rs;
                  ResultSet b=rs;
                 
                  while(rs.next()){
                      if(type=="Admin")
                      {
                           String username = a.getString("A_Name");
                  String password = b.getString("A_Password");
                      if(username.equals(name) && password.equals(pass)){
                
                
                
                JOptionPane.showMessageDialog(null, "Login Successful");
                return true;
                
                  }
                  }
                      else if(type=="Customer"){
                          {
                              String username = a.getString("C_Name");
                  String password = b.getString("C_Password");
                      if(username.equals(name) && password.equals(pass)){
               
                
                JOptionPane.showMessageDialog(null, "Login Successful");
                return true;
                
                  }
                  }
                  }
            
                }
    
}
        catch(Exception e)
        {
            return false;

        }
        return false;
    }
}
