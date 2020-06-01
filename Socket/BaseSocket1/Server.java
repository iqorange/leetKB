package Socket.BaseSocket1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        // 服务端监听，默认不指定会在所有可用IP地址上监听
        ServerSocket serverSocket = new ServerSocket(2000);
        System.out.println("服务器准备就绪～");
        System.out.println("服务其信息" + serverSocket.getInetAddress() + "\tPoint: " + serverSocket.getLocalPort());
        // 等待客户端连接
        for (;;){
            // 捕获到客户端
            Socket client = serverSocket.accept();
            // 客户端构建异步线程
            ClientHandler clientHandler = new ClientHandler(client);
            // 启动线程
            clientHandler.start();
        }
    }

    // 客户端消息处理
    private static class ClientHandler extends Thread{
        private Socket socket;
        private boolean flag = true;
        ClientHandler(Socket socket){
            this.socket = socket;
        }

        @Override
        public void run() {
            super.run();
            System.out.println("新客户端连接：" + socket.getInetAddress() + "\tPort: " + socket.getPort());
            try {
                // 获取到打印流，用于数据输出。服务器回送数据使用；
                PrintStream socketOutput = new PrintStream(socket.getOutputStream());
                // 构建输入流，用于接收客户端的数据
                BufferedReader socketInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                do {
                    // 从客户端拿到一条数据
                    String str = socketInput.readLine();
                    if ("bye".equalsIgnoreCase(str)){
                        flag = false;
                        // 回送
                        socketOutput.println("bye");
                    }else{
                        // 打印，并回送数据长度
                        System.out.println(str);
                        socketOutput.println("server: " + str.length());
                    }
                }while (flag);
                // 连接结束
                socketInput.close();
                socketOutput.close();
            }catch (Exception e){
                System.out.println("连接异常断开");
            }finally{
                // socket整体连接关闭
                try {
                    socket.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
            System.out.println("客户端已关闭：" + socket.getInetAddress() + "\tPort: " + socket.getPort());
        }
    }
}
