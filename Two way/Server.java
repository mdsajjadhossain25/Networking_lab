import java.net.*;
import java.io.*;
import java.io.InputStreamReader;

public class Server{
    public static void main(String[] args){
        try{
            ServerSocket serverSocket = new ServerSocket(6666);
            Socket socket = serverSocket.accept();
            System.out.println("Connected");
            while(true){
                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                String messageFromClient = (String)dataInputStream.readUTF();
                System.out.println("Client: "+messageFromClient);
                if(messageFromClient.equals("exit")){
                    dataInputStream.close();
                    socket.close();
                    serverSocket.close();
                    break;
                }
                DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                String messageToClient = bufferedReader.readLine();
                dataOutputStream.writeUTF(messageToClient);
                if(messageToClient.equals("exit")){
                    dataOutputStream.close();
                    socket.close();
                    serverSocket.close();
                    break;
                }
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
}