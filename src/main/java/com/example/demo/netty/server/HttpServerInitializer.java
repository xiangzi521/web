package com.example.demo.netty.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;


/**
 * Created by Administrator on 2020/6/1.
 */
public class HttpServerInitializer extends ChannelInitializer<io.netty.channel.socket.SocketChannel> {


    @Override
    protected void initChannel(SocketChannel sc) throws Exception {
        ChannelPipeline pipeline = sc.pipeline();
        //消息编码
        pipeline.addLast("httpServerCodec",new HttpServerCodec());
        // 自定义ChannelHandler
        pipeline.addLast("httpServerHandler",new HttpserverHandler());
        // 将请求转换为单一的FullHttpReques
        pipeline.addLast("aggregator",new HttpObjectAggregator(65536));
    }
}
