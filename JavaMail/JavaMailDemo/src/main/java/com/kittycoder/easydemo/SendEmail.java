package com.kittycoder.easydemo;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by shucheng on 2018/5/1.
 * JavaMail API 发送简单的电子邮件
 * 参考：https://www.yiibai.com/javamail_api/javamail_api_sending_simple_email.html
 * https://www.cnblogs.com/ysocean/p/7666061.html
 */
public class SendEmail {

    public static void main(String[] args) {
        // 收件人地址
        String to = "to@qq.com";

        // 发件人地址
        String from = "from@outlook.com";
        // 邮箱登录的用户名（通常和邮件地址相同）、密码
        final String username = "from@outlook.com";
        final String password = "";

        // 邮件服务器地址
        String host = "smtp-mail.outlook.com";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        // 获取session对象
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // 创建一个mime对象
            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(from));

            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

            message.setSubject("测试邮件");

            // 设置邮件正文
            message.setText("你好，这是一封测试邮件");

            // 发送邮件信息
            Transport.send(message);

            System.out.println("邮件发送成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
