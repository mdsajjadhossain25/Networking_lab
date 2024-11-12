import java.net.*;
import java.io.*;
import java.io.InputStreamReader;

public class Client{
    public static void main(String[] args){
        try{
            Socket socket = new Socket("localhost", 6666);
            System.out.println("Connected");
            while(true){
                DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

                String messageToServer = reader.readLine();
                dataOutputStream.writeUTF(messageToServer);
                
                if(messageToServer.equals("exit")){
                    dataOutputStream.close();
                    socket.close();
                    break;
                }

                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                String messageFromServer = (String)dataInputStream.readUTF();
                System.out.println("Server: "+messageFromServer);
                if(messageFromServer.equals("exit")){
                    dataInputStream.close();
                    socket.close();
                    break;
                }
            }
            
        }catch(Exception e){
            System.out.println(e);
        }
    }
}