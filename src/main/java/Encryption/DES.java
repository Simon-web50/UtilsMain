package Encryption;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.Scanner;

/**
 * DES是一种对称机密算法,DES使用56位秘钥对数据进行加密,加密过程中使用了置换、替换、异或等运算,就有较高的安全性.
 * DES优点:
 * DES的算法速度较快.
 * DES确定:
 * 安全性上面并不是最优选择，因为DES算法的密钥长度比较短，被暴力破解和差分攻击的风险比较高，一般推荐用一些更安全的对称加密算法.
 */
public class DES {
    private static final String DES_ALGORITHM = "DES";
    /**
     * DES加密
     * @param data 待加密的数据
     * @param key  秘钥,长度必须为8位
     * @return 加密后的数据,使用Base64编码.
     */
    public static String encrypt(String data, String key) throws Exception{
        // 根据秘钥生产秘钥规范
        KeySpec keySpec = new DESKeySpec(key.getBytes());
        // 根据秘钥规范生产秘钥工厂
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(DES_ALGORITHM);
        // 根据秘钥工厂和秘钥规范生产秘钥
        SecretKey secretKey = secretKeyFactory.generateSecret(keySpec);
        // 根据加密算法获取加密器
        Cipher cipher = Cipher.getInstance(DES_ALGORITHM);
        // 初始化加密器,设置加密模式和秘钥
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        // 加密数据
        byte[] recryptedData = cipher.doFinal(data.getBytes());
        // 对加密后的数据进行Base64编码
        return Base64.getEncoder().encodeToString(recryptedData);
    }

    /**
     * DES解密
     * @param encryptedData 加密后的数据,使用Base64编码
     * @param key          密钥，长度必须为8位
     * @return 解密后的数据
     */

    public static String decrypt(String encryptedData, String key) throws Exception {
        // 根据秘钥生产秘钥规范
        KeySpec keySpec = new DESKeySpec(key.getBytes());
        // 根据秘钥规范生产秘钥工厂
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(DES_ALGORITHM);
        // 根据秘钥工厂和秘钥规范生产秘钥
        SecretKey secretKey = secretKeyFactory.generateSecret(keySpec);

        // 对加密后的数据进行Base64解密
        byte[] decodeData = Base64.getDecoder().decode(encryptedData);
        // 根据加密算法获取解密器
        Cipher cipher = Cipher.getInstance(DES_ALGORITHM);
        // 初始化解密器,设置解密模式和秘钥
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        // 解密数据
        byte[] decryptedData = cipher.doFinal(decodeData);
        // 将解密后的数据转换为字符串
        return new String(decryptedData);
    }

    public static void main(String[] args) throws Exception {
//        String data = "Hello World";
        Scanner input = new Scanner(System.in);
        System.out.print("请输入要加密的数据:");
        String data = input.nextLine();
        String key = "06091234";

        String encryptedData = encrypt(data, key);
        System.out.println("加密后的数据：" + encryptedData);

        String decryptedData = decrypt(encryptedData, key);
        System.out.println("解密后的数据：" + decryptedData);
    }
}
