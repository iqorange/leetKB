package Socket.UDPLANSearch.unicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

// 用于搜索UDP服务（单播）
public class UDPSearcher {
    public static void main(String[] args) throws IOException {
        System.out.println("UDPSearcher Started.");
        // 作为搜索者，系统自动分配端口
        DatagramSocket datagramSocket = new DatagramSocket();

        // 构建请求数据
        String requestData = "Hello UDP Socket!";
        // 构建请求消息的实体
        byte[] requestDataBytes = requestData.getBytes();
        // 请求消息封装
        DatagramPacket requestPacket = new DatagramPacket(requestDataBytes, requestDataBytes.length);
        // 设置本机IP与端口
        requestPacket.setAddress(InetAddress.getLocalHost());
        requestPacket.setPort(20000);
        // 发送～
        datagramSocket.send(requestPacket);

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
        System.out.println("UDPSearcher receive from ip: " + ip + "\tport: " + port + "\tdata: " + data );

        // 发送完成
        System.out.println("UDPSearcher Finished.");
        // 关闭连接
        datagramSocket.close();
    }
}
