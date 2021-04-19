import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServerTest {
    private static final int PORT = 9000;

    public static void test() {
        ServerSocket server = null;
        Socket socket = null;
        DataOutputStream out = null;

        try {
            server = new ServerSocket(PORT);
            socket = server.accept();
            out = new DataOutputStream(socket.getOutputStream());
            int time = 0;
            int num = 0;
            while (true) {
                Thread.sleep(1000);
                time = time + 1000;
                String MYstr = getRandomStr();
                out.writeUTF(MYstr);
                num = num + 1;
                System.out.println("我发" + MYstr +"序号是" + num + "时间是" + System.currentTimeMillis());
                //System.out.println(System.currentTimeMillis());
                out.flush();
                if (time >= 60000) {
                    System.out.println("我沉睡了" + System.currentTimeMillis());
                    Thread.sleep(12000000);
                    time = 0;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 生产ItemName随机函数
     * @param
     * @return
     */
    private static String getRandomStr() {
        String str = "";

        int q = (int) (Math.random() * 30);
        int x = (int) (Math.random() * 200);
        int y = (int) (Math.random() * 300);
        int z = (int) (Math.random() * 10);

        str = q + " " + x + " " + y + " " + z;
        //System.out.println(str);
        return str;
    }

    public static void main(String[] args) {
        test();
    }

}
