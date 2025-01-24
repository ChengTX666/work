package nefu.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
//        事件循环组，负责处理 I/O 操作和事件。
//NioEventLoopGroup 是 EventLoopGroup 的一个具体实现，它使用了 NIO（非阻塞 I/O）的机制，适用

//        Group 是线程池  默认线程数为 CPU 核心数乘以 2

        NioEventLoopGroup bossGroup = new NioEventLoopGroup();//处理客户端的连接请求
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();//用于处理连接上的IO操作

        try {
            //服务端启动辅助类
            ServerBootstrap bootstrap = new ServerBootstrap();

            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new HttpServerInitializer());

            ChannelFuture future = bootstrap.bind(8080).sync();
            //等待服务端口关闭
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            // 优雅退出，释放线程池资源
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
