package nefu.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

public class HttpServerInitializer extends ChannelInitializer<SocketChannel> {
    protected void initChannel(SocketChannel sc) throws Exception {
        ChannelPipeline pipeline = sc.pipeline();
        //处理http消息的编解码
        pipeline.addLast("httpServerCodec", new HttpServerCodec());

//        Netty 的设计中把 Http 请求分为了 HttpRequest 和 HttpContent 两个部分
//        HttpRequest 主要包含请求头、请求方法等信息，HttpContent 主要包含请求体的信息。

        pipeline.addLast("aggregator", new HttpObjectAggregator(65536));

        //添加自定义的ChannelHandler
        pipeline.addLast("httpServerHandler", new HttpServerChannelHandler());
    }
}