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

    private String host = ""; // smtp������
    private String from = ""; // �����˵�ַ
    private String to = ""; // �ռ��˵�ַ
   
    private String user = ""; // �û���
    private String pwd = ""; // ����
    private String subject = ""; // �ʼ�����
    
    private String content = ""; // �ʼ�����

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
        props.put("mail.smtp.host", host);// ���÷����ʼ����ʼ�������������       
        props.put("mail.smtp.auth", "true"); // ��Ҫ������Ȩ

        // �øո����úõ�props���󹹽�һ��session
        Session session = Session.getDefaultInstance(props);


        // ��sessionΪ����������Ϣ����
        MimeMessage message = new MimeMessage(session);
        try {   
        	 InternetAddress from = new InternetAddress("tz9788@163.com",  
                     "DB-bill����ƽ̨", "utf-8");
            message.setFrom(from); // ���ط����˵�ַ            
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));// �����ռ��˵�ַ          
            message.setSubject(subject);
           
            Multipart multipart = new MimeMultipart(); // ��multipart����������ʼ��ĸ�����������
            
            BodyPart contentPart = new MimeBodyPart();// �����ʼ����ı�����
            contentPart.setText(content);
            multipart.addBodyPart(contentPart);
            // ��multipart����ŵ�message��
            message.setContent(multipart);
            // �����ʼ�
            message.saveChanges();
            // �����ʼ�
            Transport transport = session.getTransport("smtp");
            // ���ӷ�����������
            transport.connect(host, user, pwd);
            // ���ʼ����ͳ�ȥ
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //public static void main(String[] args) {
        //MailUtils cn = new MailUtils();
        // ���÷����˵�ַ���ռ��˵�ַ���ʼ�����
        //cn.setAddress("tz9788@163.com", "624259681@qq.com", "ע��ɹ�֪ͨ");
       
        //cn.send("smtp.163.com", "tz9788@163.com", "xyy19970706");

   // }
}
