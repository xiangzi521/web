package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by Administrator on 2019/12/10.
 */
@org.springframework.stereotype.Controller
public class MailController {

    @Autowired
    JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String username;

    @Value("${spring.mail.password}")
    private String password;

    @Value("${spring.mail.host}")
    private String host;

    @ResponseBody
    @RequestMapping("index")
    public String index(){
        return "success";
    }

    /**
     *  还可以进一步提取，把接收者，邮件主题，邮件内容当作参数传递进来
     * @throws Exception
     */
    @GetMapping("/send")
    public String send() throws Exception{

        //1.设置邮件相关属性
        Properties properties = new Properties();
        properties.setProperty("mail.smtp.host",host);
        properties.setProperty("mail.smtp.auth","true");
        //2.获取邮件会话(实例)对象
        Session session = Session.getInstance(properties);

        //3.创建一个消息对象
        MimeMessage message = new MimeMessage(session);

        //4.封装消息
        message.setFrom(new InternetAddress(username));
            //设置邮件容器发送的模式
        message.setRecipient(Message.RecipientType.TO,new InternetAddress("534157386@QQ.com"));
        message.setSubject("这里是邮件主题...");
        message.setText("这个是邮件内容");

        //5.发送
        Transport smtp = session.getTransport();//发送邮件的协议
        smtp.connect(host,username,password); //设置发送邮箱的协议,发送邮箱名称,发送邮箱的秘钥
        smtp.sendMessage(message,message.getAllRecipients());
        smtp.close();//关闭资源
        System.out.println("邮件发送成功");
        return "success";
    }
}
