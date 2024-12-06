package Encryption;

import java.security.MessageDigest;
import java.util.Formatter;

/**
 * MD5是"信息摘要算法",它对任意长度的数据,算出固定长度的散列值(hash value).
 * MD5算法的输出长度为128位,通常用32个16进制数来表示.
 * MD5优点:
 * 计算速度快,输出长度固定,应用广泛等.
 * MD5缺点:
 * 不安全,MD5算法已经被攻破,而且MD5算法的输出长度有限,攻击者可以通过暴力破解或彩虹表攻击等方式,
 * 找到与原始数据相同的散列值,从而破解数据.
 */
public class MD5 {
    private static final String MD5_ALGORITHM = "MD5" ;
    public static String eccrypt(String data) throws Exception{
        //获取MD5算法实例
        MessageDigest messageDigest = MessageDigest.getInstance(MD5_ALGORITHM);
        //计算散列值
        byte[] digest = messageDigest.digest(data.getBytes());
        Formatter formatter = new Formatter();
        //补齐前导0,并格式化
        for (byte b : digest) {
            formatter.format("%02x", b);
        }
        return formatter.toString();
    }

    public static void main(String[] args) throws Exception {
        String data = "Hello World";
        String encryptedData = eccrypt(data);
        System.out.println("加密后的数据:" + encryptedData);
    }
}
