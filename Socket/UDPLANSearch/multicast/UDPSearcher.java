package Socket.UDPLANSearch.multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

// 用于搜索UDP服务（多播）
public class UDPSearcher {
    // 定义回送端口号
    private static final int LISTEN_PORT = 30000;

    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("UDPSearcher Started.");

        // 获取一个监听器线程
        Listener listener = listen();
        // 发送带密闻的广播（组播）
        sendBroadcast();

        // 发送完成
        System.out.println("UDPSearcher Finished.");
        // 读取任意信息
        System.in.read();
        // 拿到设备信息
        List<Device> devices = listener.getDevicesAndClose();
        // 输出设备信息
        for (Device device: devices){
            System.out.println("Device: " + device.toString());
        }
        // 完成
        System.out.println("UDPSearcher Finished.");
    }

    // 监听方法
    private static Listener listen() throws InterruptedException{
        System.out.println("UDPSearcher start listen.");
        // 初始化线程计数器
        CountDownLatch countDownLatch = new CountDownLatch(1);
        // 构建监听线程类
        Listener listener = new Listener(LISTEN_PORT, countDownLatch);
        // 启动线程
        listener.start();
        // 等待线程启动完成
        countDownLatch.await();
        // 启动完成后放回listener
        return listener;
    }

    // 设备信息内部类
    private static class Device{
        // 端口
        final int port;
        // IP地址
        final String ip;
        // 设备唯一标识符
        final String sn;

        public Device(int port, String ip, String sn) {
            this.port = port;
            this.ip = ip;
            this.sn = sn;
        }

        @Override
        public String toString() {
            return "Device{" +
                    "port=" + port +
                    ", ip='" + ip + '\'' +
                    ", sn='" + sn + '\'' +
                    '}';
        }
    }

    // 监听线程类实现
    private static class Listener extends Thread{
        // 端口号
        private final int listenPort;
        // 外部可感知的线程运行状态（线程计数器）
        private final CountDownLatch countDownLatch;
        // 存储设备信息的列表
        private final List<Device> devices = new ArrayList<>();
        // 结束符
        private boolean done = false;
        // 搜索者
        private DatagramSocket datagramSocket = null;

        public Listener(int listenPort, CountDownLatch countDownLatch){
            super();
            this.listenPort = listenPort;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run(){
            super.run();
            // 回调启动
            countDownLatch.countDown();
            try {
                // 监听回送端口
                datagramSocket = new DatagramSocket(listenPort);
                while (!done){
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
                    // 解析SN
                    String sn = MessageCreator.parseSn(data);
                    if (sn != null){
                        // 注册设备
                        Device device = new Device(port, ip, sn);
                        devices.add(device);
                    }
                }

            }catch (Exception ignored){
            }finally {
                close();
            }
            System.out.println("UDPSearcher listener finished.");
        }

        private void close(){
            if (datagramSocket != null){
                datagramSocket.close();
                datagramSocket = null;
            }
        }

        List<Device> getDevicesAndClose(){
            done = true;
            close();
            return devices;
        }
    }

    // 发送广播的方法
    private static void sendBroadcast() throws IOException {
        System.out.println("UDPSearcher sendBroadcast Started.");
        // 作为搜索者，系统自动分配端口
        DatagramSocket datagramSocket = new DatagramSocket();

        // 构建请求数据，构建一个发送包
        String requestData = MessageCreator.buildWithPort(LISTEN_PORT);
        // 构建请求消息的实体
        byte[] requestDataBytes = requestData.getBytes();
        // 请求消息封装
        DatagramPacket requestPacket = new DatagramPacket(requestDataBytes, requestDataBytes.length);
        // 2000端口与广播地址
        requestPacket.setAddress(InetAddress.getByName("255.255.255.255"));
        requestPacket.setPort(20000);
        // 发送～
        datagramSocket.send(requestPacket);
        datagramSocket.close();
        // 发送完成
        System.out.println("UDPSearcher Finished");
    }
}
