package Encryption;

import java.security.MessageDigest;

/**
 * SHA系列算法是一组密码散列函数,用于将任意长度的数据映射为固定长度的散列值.
 * SHA-2算法包括SHA-224、SHA-256、SHA-384和SHA-512四种散列函数，分别将任意长度的数据映射为224位、256位、384位和512位的散列值。
 * SHA优点:
 * 散列值长度更长，更强的碰撞抗性.
 * SHA缺点:
 * SHA-2也不是绝对安全的，散列算法都有被暴力破解或者彩虹表攻击的风险，所以，在实际的应用中，加盐还是必不可少的。
 */
public class SHA256 {
    private static final String SHA_256_ALGORITHM = "SHA-256";
    public static String encrypt(String data) throws Exception {
        //获取SHA-256算法实例
        MessageDigest messageDigest = MessageDigest.getInstance(SHA_256_ALGORITHM);
        //计算散列值
        byte[] digest = messageDigest.digest(data.getBytes());
        StringBuilder stringBuilder = new StringBuilder();
        //将byte数组转换为15进制字符串
        for (byte b : digest) {
            stringBuilder.append(Integer.toHexString((b & 0xFF) | 0x100) ,1, 3);
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) throws Exception {
        String data = "Hello World!";
        String encryptedData = encrypt(data);
        System.out.println("加密后的数据:" + encryptedData);
    }
}
