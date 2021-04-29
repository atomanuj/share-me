package GitCheck1;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;

// Notice, do not import com.mysql.cj.jdbc.*
// or you will have problems!

public class DemoDatabase {
    public static void main(String[] args) {
        try {
            // The newInstance() call is a work around for some
            // broken Java implementations
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/anuj";
            String uname = "root";
            String pass = "root";
            //String str = "D:";
            Connection Con = DriverManager.getConnection(url , uname , pass);
            InputStream paths = new FileInputStream("F:\\ANURAG_SINGH_MNNIT.pdf");
            //insert into database
            String ins = "insert into seniors values ( ? , ? , ? , ?)";
            PreparedStatement pst = Con.prepareStatement(ins);
            pst.setInt(1 , 11);
            pst.setString(2 , "D:");
            pst.setString(3 , "root");
            pst.setBlob(4 , paths);
            int count = pst.executeUpdate();
            System.out.println(count + " row affected");
            pst.close();


        } catch (Exception ex) {
            ex.printStackTrace();
            // handle the error
        }
    }
}



