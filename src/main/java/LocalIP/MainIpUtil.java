package LocalIP;

import java.net.SocketException;

public class MainIpUtil {
    public static void main(String[] args) throws SocketException {
        System.out.println(IpUtil.getLocalIp4Address().get().toString().replaceAll("/", ""));
    }
}
