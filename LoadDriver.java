package GitCheck1;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.*;

// Notice, do not import com.mysql.cj.jdbc.*
// or you will have problems!

public class LoadDriver {
    static Connection Con;
    public static void main(String[] args) {
        try {
            // The newInstance() call is a work around for some
            // broken Java implementations

            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/anuj";
            String uname = "root";
            String pass = "root";
            Con = DriverManager.getConnection(url , uname , pass);
//            InputStream paths = new FileInputStream("F:\\ANURAG_SINGH_MNNIT.pdf");


//            select the query
            String sel = "select * from seniors where id = 6";
            Statement st = Con.createStatement();
            ResultSet rs = st.executeQuery(sel);

            //rs.next();
            String userdata = "";
            while(rs.next())
            {
                userdata = rs.getInt(1) + " : " + rs.getString(2);
                System.out.println(userdata);
            }
            st.close();

//            String ins = "insert into seniors values (? , ? , ?)";
//            PreparedStatement pst = Con.prepareStatement(ins);
//            String str = "F:\\ANURAG_SINGH_MNNIT.pdf" ;
//            //String ins = "insert into seniors values (? , ?)";
//            pst.setInt(1 , 9);
//            pst.setString(2 , str);
//            pst.setString(3 , "");
//            //insert into database
//
//
//            int count = pst.executeUpdate();
//            System.out.println(count + " row affected");
//            pst.close();



            Con.close();

        } catch (Exception ex) {
            ex.printStackTrace();
            // handle the error
        }
    }

    public static void putstr(String str , String password) throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");

        String url = "jdbc:mysql://localhost:3306/anuj";
        String uname = "root";
        String pass = "root";
        Con = DriverManager.getConnection(url , uname , pass);
        InputStream paths = new FileInputStream(str);
        //insert into database
        String ins = "insert into seniors values ( ? , ? , ? , ?)";
        PreparedStatement pst = Con.prepareStatement(ins);
        pst.setInt(1 , 11);
        pst.setString(2 , str);
        pst.setString(3 , password);
        pst.setBlob(4 , paths);
        int count = pst.executeUpdate();
        System.out.println(count + " row affected");
        pst.close();

    }
    public static String getstr(String filename , String password) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");

        String url = "jdbc:mysql://localhost:3306/anuj";
        String uname = "root";
        String pass = "root";
        Con = DriverManager.getConnection(url , uname , pass);
        //select the query
        String sel = "select file from seniors where id = 11";
        Statement st = Con.createStatement();
        ResultSet rs = st.executeQuery(sel);
        //rs.next();
        String filepath = "F:\\" + filename;
        File myfile = new File(filepath);
        FileOutputStream output = new FileOutputStream(myfile);
        System.out.println("Writing to file " + myfile.getAbsolutePath());
        while (rs.next()) {
            InputStream input = rs.getBinaryStream("file");
            byte[] buffer = new byte[10240];
            while (input.read(buffer) > 0) {
                output.write(buffer);
            }
        }
//        InputStream myfile = rs.getBinaryStream("file");
//        String userdata = "";
//        userdata = rs.getString("path") ;
//        while(rs.next())
//        {
//            userdata = rs.getInt(1) + " : " + rs.getString(2);
//            System.out.println(userdata);
//        }
        st.close();
        Con.close();
        return filepath;
    }
}