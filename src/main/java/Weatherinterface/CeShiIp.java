package Weatherinterface;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;


public class CeShiIp {

    public static void main(String[] args) throws IOException {
        //获取IP地址
        String hostAddress = InetAddress.getLocalHost().getHostAddress();
        //工具类
        String detail = IPUtil.getCityInfo(hostAddress);
        System.out.println("IP地址:"+hostAddress+"地区:"+detail);
    }
}
