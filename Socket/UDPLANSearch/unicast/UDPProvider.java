package Socket.UDPLANSearch.unicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

// 提供UDP服务（单播）
// 先处于监听状态
public class UDPProvider {
    public static void main(String[] args) throws IOException {
        System.out.println("UDPProvider Started.");
        // 作为接收者，指定一个端口用于数据接收
        DatagramSocket datagramSocket = new DatagramSocket(20000);
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
        System.out.println("UDPProvider receive from ip: " + ip + "\tport: " + port + "\tdata: " + data );
        // 构建回送数据
        String responseData = "Receive data with length: " + data.length();
        // 构建回送消息的实体
        byte[] responseDataBytes = responseData.getBytes();
        // 根据发送者的信息进行回送消息
        DatagramPacket responsePacket = new DatagramPacket(responseDataBytes, responseDataBytes.length, receivePack.getAddress(), receivePack.getPort());
        // 发送～
        datagramSocket.send(responsePacket);
        // 发送完成
        System.out.println("UDPProvider Finished.");
        // 关闭连接
        datagramSocket.close();
    }
}
