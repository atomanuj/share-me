package GitCheck1;

import java.io.*;
import java.net.*;

public class Server2 {
    public static void main (String[] args)  throws IOException {
        ServerSocket ss = new ServerSocket(4400);
        while(true){
            System.out.println("Waiting for Connection!!");
            Socket s = null;
            try{
                s = ss.accept();
                System.out.println("A new Client is Connected");
            }catch(IOException e){
                e.printStackTrace();
                return;
            }
            byte [] b = new byte[200200];
            File tempFile = new File("F:\\", "client.txt");
            InputStream is = s.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            try (DataInputStream dis = new DataInputStream(bis)) {
                is.read(b , 0 , b.length);
                FileOutputStream fos = new FileOutputStream(tempFile);
                try (BufferedOutputStream bos = new BufferedOutputStream(fos)) {
                    bos.write(b , 0 , b.length);
                }
            }

catch(Exception e) {
            e.printStackTrace();
        }
        }
    }
}
