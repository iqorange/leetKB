package Socket.UDPLANSearch.multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.UUID;

// 提供UDP服务（多播）
// 先处于监听状态
public class UDPProvider {
    public static void main(String[] args) throws IOException {
        // 通过UUID生成唯一标识
        String sn = UUID.randomUUID().toString();
        // 构建线程
        Provider provider = new Provider(sn);
        provider.start();
        // 读取任意键盘信息退出
        System.in.read();
        provider.exit();
    }

    private static class Provider extends Thread{
        // 受组播者的唯一标识
        private final String sn;
        // 是否已完成的状态
        private boolean done = false;
        // 循环数据流支持
        private DatagramSocket datagramSocket = null;
        public Provider(String sn){
            super();
            // 唯一标识由外部传入
            this.sn = sn;
        }

        @Override
        public void run(){
            super.run();
            // 作为接收者，指定一个端口用于数据接收
            try {
                // 监听20000端口
                datagramSocket = new DatagramSocket(20000);
                while (!done) {
                    System.out.println("UDPProvider Started.");
                    // 构建接收实体
                    final byte[] buffer = new byte[512];
                    DatagramPacket receivePack = new DatagramPacket(buffer, buffer.length);
                    // 接收
                    datagramSocket.receive(receivePack);
                    // 打印接收到的信息与发送者的信息
                    // 发送者的IP地址
                    String ip = receivePack.getAddress().getHostAddress();
                    // 发送者的端口
                    int port = receivePack.getPort();
                    // 拿到一个数据大小
                    int dataLength = receivePack.getLength();
                    // 提取数据
                    String data = new String(receivePack.getData(), 0, dataLength);
                    // 输出信息
                    System.out.println("UDPProvider receive from ip: " + ip + "\tport: " + port + "\tdata: " + data);

                    // 发送到一个约定好的指定端口上
                    int responsePort = MessageCreator.parsePort(data);
                    if (responsePort != -1){
                        // 构建回送数据，回送唯一标识
                        String responseData = MessageCreator.buildWithSn(sn);
                        // 构建回送消息的实体
                        byte[] responseDataBytes = responseData.getBytes();
                        // 根据发送者的信息进行回送消息
                        DatagramPacket responsePacket = new DatagramPacket(responseDataBytes, responseDataBytes.length, receivePack.getAddress(), responsePort);
                        // 发送～
                        datagramSocket.send(responsePacket);
                    }


                }
            } catch (Exception ignored) {
            } finally {
                close();
            }
            // 发送完成
            System.out.println("UDPProvider Finished.");
        }

        // 关闭循环数据流
        private void close(){
            if (datagramSocket != null){
                datagramSocket.close();
                datagramSocket = null;
            }
        }

        // 退出方法
        void exit(){
            done = true;
            close();
        }
    }
}
