package GitCheck1;

import java.io.*;
import java.net.*;

public class Client2 {
    public static void main(String[] args) throws IOException {
        System.out.println("Socket started");


        String path = "D:\\";
        String fileName = "tttt.txt";
        try (Socket socket = new Socket("localhost", 4400)) {
            File file = new File(path, fileName);
            byte [] b = new byte[200200];
            OutputStream os = socket.getOutputStream();
            BufferedOutputStream bos = new BufferedOutputStream(os);
            try (DataOutputStream dos = new DataOutputStream(bos)) {
                FileInputStream fis = new FileInputStream(file);
                fis.read(b , 0 , b.length);
                try (BufferedInputStream bis = new BufferedInputStream(fis)) {
                    dos.write(b , 0 , b.length);
                }
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
