package nefu.tongrds;

import nefu.tongrds.Service.RedisService;
import org.junit.jupiter.api.Test;
import org.redisson.api.RBucket;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisService redisService;

    @Autowired
    private RedissonClient redissonClient;

    @Test
    void set(){
        RBucket<Object> bucket = redissonClient.getBucket("key", StringCodec.INSTANCE);
        bucket.set("vvvv");
    }

    @Test
    void setRedisService(){
        redisService.set(List.of("t","BO"));
    }
    @Test
    void list(){

    }
}
