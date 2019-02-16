package com.yl.util.demo;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SentinelServersConfig;

/**
 * @author Alex
 * @since 2019/2/12 16:41
 */
public class RedissonUtil {

    private static final RedissonClient CLIENT;

    static {
        Config config = new Config();
        SentinelServersConfig sentinelServersConfig = config.useSentinelServers().setMasterName("mymaster");
        sentinelServersConfig.addSentinelAddress("redis://127.0.0.1:26379");
        sentinelServersConfig.addSentinelAddress("redis://127.0.0.1:26380");
        sentinelServersConfig.addSentinelAddress("redis://127.0.0.1:26381");
        sentinelServersConfig.setMasterConnectionPoolSize(32);
        sentinelServersConfig.setMasterConnectionMinimumIdleSize(18);
        CLIENT = Redisson.create(config);
    }


    public static RedissonClient getClient(){
        return CLIENT;
    }


}
