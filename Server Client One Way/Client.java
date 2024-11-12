import java.net.*;
import java.io.*;
import java.io.InputStreamReader;

public class Client{
    public static void main(String[] args){
        try{
            Socket socket = new Socket("localhost", 6666);
            System.out.println("Client Started. Type a message: ");
            while(true){
                DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

                String messageToServer = bufferedReader.readLine();
                dataOutputStream.writeUTF(messageToServer);
                
                if(messageToServer.equals("exit")){
                    dataOutputStream.close();
                    socket.close();
                    break;
                }
            }
            
        }catch(Exception e){
            System.out.println(e);
        }
    }
}