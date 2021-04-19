import akka.stream.impl.fusing.Scan;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class SocketServer {

    public static void main(String[] a) throws IOException {
        ServerSocket ss=new ServerSocket(8000);
        Socket socket=ss.accept();
//        InputStream inputStream=socket.getInputStream();
//        byte[] bytes=new byte[1024];
//        int len;
//        StringBuilder stringBuilder=new StringBuilder();
//        while((len=inputStream.read(bytes))!=-1){
//            stringBuilder.append(new String(bytes,0,len,"UTF-8"));
//        }
//        System.out.println("get message from client: " + stringBuilder);
//        inputStream.close();
        OutputStream outputStream=socket.getOutputStream();
        Scanner sc=new Scanner(System.in);
        while (sc.hasNext()){
            String str=sc.next()+"\n";
            System.out.print(str);
            outputStream.write(str.getBytes("UTF-8"));
        }
        outputStream.close();
        socket.close();
        ss.close();
    }

    public static void getConsole(){



    }

}
