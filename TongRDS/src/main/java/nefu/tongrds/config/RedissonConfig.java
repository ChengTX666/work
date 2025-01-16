package nefu.tongrds.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.config.Config;
import org.redisson.config.ReadMode;
import org.redisson.config.SentinelServersConfig;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class RedissonConfig {
    @Bean
    public RedissonClient redissonClient(){
        Config config=new Config();
        SentinelServersConfig sentinelConfig = config.useSentinelServers()
                .setMasterName("master") // 主节点名称，根据实际情况修改
                .addSentinelAddress("redis://123.57.2.70:6379", "redis://123.57.2.70:16379") // 哨兵节点地址列表
                .setReadMode(ReadMode.SLAVE) // 设置读取模式为从节点，可根据需求调整
                .setDatabase(0); // 设置使用的数据库编号，默认为 0

        config.setCodec(new JsonJacksonCodec());//设置为JSON形式
        return Redisson.create(config);
    }
}