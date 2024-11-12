import java.net.*;
import java.io.*;
import java.time.LocalTime;  
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;  

public class Server{
    public static void main(String[] args){
        try{
            ServerSocket serverSocket = new ServerSocket(6666);
            Socket socket = serverSocket.accept();
            System.out.println("Server Started");
            while(true){
                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                String messageFromClient = (String)dataInputStream.readUTF();
                System.out.println("Client: "+messageFromClient);
                
                if(messageFromClient.equals("hi")){
                    System.out.println("Server: Hello");
                }
                else if(messageFromClient.equals("date")){
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");  
                    LocalDateTime now = LocalDateTime.now();  
                    System.out.println(dtf.format(now));
                }
                else if(messageFromClient.equals("time")){
                    System.out.println("Server: " + LocalTime.now());
                }
                else if(messageFromClient.equals("exit")){
                    dataInputStream.close();
                    socket.close();
                    serverSocket.close();
                    break;
                }
                else{
                    System.out.println("Invalid Message from Client");
                }
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
}