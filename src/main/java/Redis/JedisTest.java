package Redis;

import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.Set;

public class JedisTest {
    @Test
    public void testRedis() {
        // 1.获取连接
        Jedis jedis = new Jedis("localhost", 6379);
        // 2.执行具体操作
        jedis.set("username", "Simon");

        // 输出打印
        String value = jedis.get("username");
        System.out.println(value);

        // 删除操作
        // jedis.del("username");

        // 哈希存储
        jedis.hset("myhash", "addr", "bj");

        // 输出打印
        String hValue = jedis.hget("myhash", "addr");
        System.out.println(hValue);

        // keys * 输出打印
        Set<String> keys = jedis.keys("*");
        for (String key : keys) {
            System.out.println(key);
        }

        // 关闭连接
        jedis.close();
    }
}
