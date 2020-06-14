package Socket.UDPLANSearch.multicast;

// 多播口令
public class MessageCreator {
    // 受组播设备的标识
    private static final String SN_HEADER = "I received the code, I am (SN): ";
    // 发起组播设备的口令
    private static final String PORT_HEADER = "This is a code, please reply to the port (Port): ";

    // 创建端口
    public static String buildWithPort(int port){
        return PORT_HEADER + port;
    }

    // 解析端口
    public static int parsePort(String data){
        if (data.startsWith(PORT_HEADER)){
            return Integer.parseInt(data.substring(PORT_HEADER.length()));
        }
        return -1;
    }

    // 创建标识
    public static String buildWithSn(String sn){
        return SN_HEADER + sn;
    }

    // 解析标识
    public static String parseSn(String data){
        if (data.startsWith(SN_HEADER)){
            return data.substring(SN_HEADER.length());
        }
        return null;
    }
}
