package com.example.demo.netty.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;

/**
 * Created by Administrator on 2020/6/1.
 */
public class HttpClientHandler extends SimpleChannelInboundHandler<FullHttpResponse> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpResponse msg) throws Exception {

        FullHttpResponse response = msg;
        response.headers().get(HttpHeaderNames.CONTENT_LENGTH);
        ByteBuf buf = response.content();
        System.out.println(buf.toString(CharsetUtil.UTF_8));
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        URI uri = new URI("http://127.0.0.1:8080");
        String msg = " Are you OK";
        DefaultFullHttpRequest request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1,
                HttpMethod.GET, uri.toASCIIString(), Unpooled.wrappedBuffer(msg.getBytes("UTF-8")));

        //构建http请求
        request.headers().set(HttpHeaderNames.CONTENT_LENGTH,request.content().readableBytes());
        //发送http请求
        ctx.channel().writeAndFlush(request);
    }
}
