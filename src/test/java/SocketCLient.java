import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class SocketCLient  {
    public static void main(String[] a) throws IOException {
        Socket socket=new Socket("127.0.0.1",8000);
        OutputStream outputStream=socket.getOutputStream();
        String msg="dddf,pppp";
        socket.getOutputStream().write(msg.getBytes("UTF-8"));
        outputStream.close();
        socket.close();
    }
}
