package Socket.BaseSocket;

import java.io.*;
import java.net.Inet4Address;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket();
        // 设定读取流超时时间
        socket.setSoTimeout(3000);
        // 设置本地连接的地址、端口和超时时间
        socket.connect(new InetSocketAddress(Inet4Address.getLocalHost(), 2000), 3000);
        System.out.println("Server connect Success~");
        System.out.println("客户端信息："  + socket.getLocalAddress() + "\tPort: "+ socket.getLocalPort());
        System.out.println("服务器信息："  + socket.getInetAddress() + "\tPort: " + socket.getPort());
        try {
            toSend(socket);
        }catch (Exception e){
            System.out.println("异常关闭");
        }

        socket.close();
        System.out.println("Client Closed~");
    }

    // 发送数据方法
    private static void toSend(Socket client) throws IOException{
        // 构建键盘输入流
        InputStream in = System.in;
        BufferedReader input = new BufferedReader(new InputStreamReader(in));
        // 拿到客户端输出流
        OutputStream outputStream = client.getOutputStream();
        PrintStream socketPrintStream = new PrintStream(outputStream);
        // 获取Socket输入流
        InputStream inputStream = client.getInputStream();
        BufferedReader socketBufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        boolean flag = true;
        do {
            // 从键盘读取一行
            String str = input.readLine();
            // 发送数据到服务器
            socketPrintStream.println(str);
            // 从服务器读取一行
            String echo = socketBufferedReader.readLine();
            if ("bye".equalsIgnoreCase(echo)) {
                flag = false;
            }else{
                System.out.println(echo);
            }
        }while (flag);
        // 退出后关闭socket留
        socketPrintStream.close();
        socketBufferedReader.close();
    }
}
