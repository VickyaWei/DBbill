package tools;

import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MailUtils {

    private String host = ""; // smtp服务器
    private String from = ""; // 发件人地址
    private String to = ""; // 收件人地址
   
    private String user = ""; // 用户名
    private String pwd = ""; // 密码
    private String subject = ""; // 邮件标题
    
    private String content = ""; // 邮件标题

    public void setAddress(String from, String to, String subject) {
        this.from = from;
        this.to = to;
        this.subject = subject;
    }
    
    public void setContent(String content) {      
        this.content = content;
    }

    

    public void send(String host, String user, String pwd) {
        this.host = host;
        this.user = user;
        this.pwd = pwd;

        Properties props = new Properties();      
        props.put("mail.smtp.host", host);// 设置发送邮件的邮件服务器的属性       
        props.put("mail.smtp.auth", "true"); // 需要经过授权

        // 用刚刚设置好的props对象构建一个session
        Session session = Session.getDefaultInstance(props);


        // 用session为参数定义消息对象
        MimeMessage message = new MimeMessage(session);
        try {   
        	 InternetAddress from = new InternetAddress("tz9788@163.com",  
                     "DB-bill融资平台", "utf-8");
            message.setFrom(from); // 加载发件人地址            
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));// 加载收件人地址          
            message.setSubject(subject);
           
            Multipart multipart = new MimeMultipart(); // 向multipart对象中添加邮件的各个部分内容
            
            BodyPart contentPart = new MimeBodyPart();// 设置邮件的文本内容
            contentPart.setText(content);
            multipart.addBodyPart(contentPart);
            // 将multipart对象放到message中
            message.setContent(multipart);
            // 保存邮件
            message.saveChanges();
            // 发送邮件
            Transport transport = session.getTransport("smtp");
            // 连接服务器的邮箱
            transport.connect(host, user, pwd);
            // 把邮件发送出去
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //public static void main(String[] args) {
        //MailUtils cn = new MailUtils();
        // 设置发件人地址、收件人地址和邮件标题
        //cn.setAddress("tz9788@163.com", "624259681@qq.com", "注册成功通知");
       
        //cn.send("smtp.163.com", "tz9788@163.com", "xyy19970706");

   // }
}
