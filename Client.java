package GitCheck1;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.*;

public class Client {

    InputStream dataIs;
    OutputStream dataOs;
    static DataOutputStream out;
    static  BufferedReader in;
    static Socket clientSocket;
    static FileInputStream fStream;
    static  PrintWriter ctrlWriter;



    public static void main(String[] args) throws Exception {
            BufferedReader bufferedReader;
            try {
                clientSocket = new Socket("localhost", 4400);
                ctrlWriter = new PrintWriter(clientSocket.getOutputStream(),true);
                bufferedReader = new BufferedReader(new InputStreamReader(System.in));

                System.out.println("Socket established to " + clientSocket.getInetAddress() + " port " + clientSocket.getPort());
                String pC = null;
                String pS;
                String dirPath = null;
                String userCommand = "";
                System.out.println("write the command ");
                userCommand = bufferedReader.readLine();



                    switch (userCommand) {
                        case "upload":
                            System.out.println("Upload Selected - Client");
                            System.out.println("Enter path on CLIENT :");
                            pC = bufferedReader.readLine();
                            System.out.println("Enter path on SERVER :");
                            pS = bufferedReader.readLine();
                            new Thread(new upload(pC , pS , ctrlWriter , fStream , clientSocket )).start();
                            break;

                        case "download":
                            System.out.println("Download Selected - Client");
                            System.out.println("Enter path on SERVER :");
                            pS = bufferedReader.readLine();
                            System.out.println("Enter path on CLIENT :");
                            pC = bufferedReader.readLine();
                            new Thread(new download(pS , pC , ctrlWriter , clientSocket)).start();
                            break;

                        case "dir":
                            System.out.println("List Directory Content Selected - Client");
                            System.out.println("Enter Directory path :");
                            dirPath = bufferedReader.readLine();
                            new Thread(new listDirectory(dirPath , ctrlWriter , clientSocket)).start();
                            break;

                        case "mkdir":
                            System.out.println("Create New Directory Selected - Client");
                            System.out.println("Enter Directory path :");
                            dirPath = bufferedReader.readLine();
                            new Thread(new listDirectory(dirPath , ctrlWriter , clientSocket)).start();
                            break;

                        case "rmdir":
                            System.out.println("Remove  Directory Selected - Client");
                            System.out.println("Enter Directory path :");
                            dirPath = bufferedReader.readLine();
                            new Thread(new removeDirectory(dirPath , ctrlWriter , clientSocket)).start();
                            break;

                        case "rm":
                            System.out.println("Remove  File Selected - Client");
                            System.out.println("Enter file path :");
                            dirPath = bufferedReader.readLine();
                            new Thread(new removeFile(dirPath , ctrlWriter , clientSocket)).start();
                            break;

                        case "shutdown":
                            new Thread(new shutdown(ctrlWriter , clientSocket)).start();
                            break;

                        default:
                            System.out.println("Invalid command.");
                    }
                } catch (IOException e) {
                e.printStackTrace();
            }

    }// End main()


}
