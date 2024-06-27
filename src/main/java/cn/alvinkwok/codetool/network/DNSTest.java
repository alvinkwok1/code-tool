package cn.alvinkwok.codetool.network;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Description
 *
 * @author alvinkwok
 * @since 2024/5/11
 */
public class DNSTest {
    public static void main(String[] args) throws UnknownHostException, InterruptedException {
        if (args.length < 2)
            return;
        // DNS缓存0秒
//        java.security.Security.setProperty("networkaddress.cache.ttl", "0");
//        java.security.Security.setProperty("networkaddress.cache.negative.ttl", "0");
        long time = System.currentTimeMillis();
        InetAddress addresses1[] = InetAddress.getAllByName(args[0]);
        System.out.println("addresses1:" + (System.currentTimeMillis() - time) + "毫秒");
        for (InetAddress address : addresses1) {
            System.out.println(address);
        }

        time = System.currentTimeMillis();
        InetAddress addresses2[] = InetAddress.getAllByName(args[0]);
        System.out.println("addresses2:" + (System.currentTimeMillis() - time) + "毫秒");
        for (InetAddress address : addresses2) {
            System.out.println(address);
        }


        Thread.sleep(2000);

        time = System.currentTimeMillis();
        InetAddress addresses3[] = InetAddress.getAllByName(args[0]);
        System.out.println("addresses3:" + (System.currentTimeMillis() - time) + "毫秒");
        for (InetAddress address : addresses3) {
            System.out.println(address);
        }
    }
}
