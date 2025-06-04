package gateway;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class GatewayServer {
    private static final int PORT = 8080;

    public static void main(String[] args) throws InterruptedException {
        // 配置线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup(1); // 接收连接
        EventLoopGroup workerGroup = new NioEventLoopGroup(4); // 处理IO

        try {
            ServerBootstrap bootstrap = new ServerBootstrap()
                    .group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new GatewayInitializer()) // 处理器链
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            bootstrap.bind(PORT).sync().channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}