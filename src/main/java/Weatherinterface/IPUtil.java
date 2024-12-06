package Weatherinterface;

import org.lionsoul.ip2region.DataBlock;
import org.lionsoul.ip2region.DbConfig;
import org.lionsoul.ip2region.DbSearcher;
import org.lionsoul.ip2region.Util;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;

public class IPUtil {

    /**
     * 根据IP地址获取城市
     * @param ip
     * 如果放在服务器读取不了,两种方式
     * 1.可以在服务器上创建文件,ip2region.db这个放在文件里面，然后开始读取
     * 2.可以整个配置文件,在配置配置目录，然后读取配置文件
     * @return
     */
    public static String getCityInfo(String ip) throws IOException {
        URL url = IPUtil.class.getClassLoader().getResource("ip2region.db");
        File file;
        if (url != null) {
            file = new File(url.getFile());
        } else {
            return null;
        }
        if (!file.exists()) {
            System.out.println("Error: Invalid ip2region.db file, filePath：" + file.getPath());
            return null;
        }
        //查询算法
        int algorithm = DbSearcher.BTREE_ALGORITHM; //B-tree
        //DbSearcher.BINARY_ALGORITHM //Binary
        //DbSearcher.MEMORY_ALGORITYM //Memory
        try {
            DbConfig config = new DbConfig();
            DbSearcher searcher = new DbSearcher(config, file.getPath());
            Method method;
            switch ( algorithm )
            {
                case DbSearcher.BTREE_ALGORITHM:
                    method = searcher.getClass().getMethod("btreeSearch", String.class);
                    break;
                case DbSearcher.BINARY_ALGORITHM:
                    method = searcher.getClass().getMethod("binarySearch", String.class);
                    break;
                case DbSearcher.MEMORY_ALGORITYM:
                    method = searcher.getClass().getMethod("memorySearch", String.class);
                    break;
                default:
                    return null;
            }
            DataBlock dataBlock;
            if (!Util.isIpAddress(ip)) {
                System.out.println("Error: Invalid ip address");
                return null;
            }
            dataBlock  = (DataBlock) method.invoke(searcher, ip);
            return dataBlock.getRegion();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}