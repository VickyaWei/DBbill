package tools;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;


import innerFrame.Prolong;




public class MobileAuthentication {
	
	public static int random;
	//private Confirmation conf = Prolong.getConfirmation();
	
    public static void main(String[] args) throws Exception{
    	//���ó�ʱʱ��-�����е���
    	System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
    	System.setProperty("sun.net.client.defaultReadTimeout", "10000");
    	//��ʼ��ascClient��Ҫ�ļ�������
    	final String product = "Dysmsapi";//����API��Ʒ���ƣ����Ų�Ʒ���̶��������޸ģ�
    	final String domain = "dysmsapi.aliyuncs.com";//����API��Ʒ�������ӿڵ�ַ�̶��������޸ģ�
    	//�滻�����AK
    	final String accessKeyId = "LTAIvRSZAdvop6ht";//���accessKeyId,�ο����ĵ�����2
    	final String accessKeySecret = "dhaYydMZVohTlti45i4c8oWSHdtvKG";//���accessKeySecret���ο����ĵ�����2
    	//��ʼ��ascClient,��ʱ��֧�ֶ�region�������޸ģ�
    	IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId,
    	accessKeySecret);
    	DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
    	IAcsClient acsClient = new DefaultAcsClient(profile);
    	 //��װ�������
    	 SendSmsRequest request = new SendSmsRequest();
    	 //ʹ��post�ύ
    	 request.setMethod(MethodType.POST);
    	 //����:�������ֻ��š�֧���Զ��ŷָ�����ʽ�����������ã���������Ϊ1000���ֻ�����,������������ڵ������ü�ʱ�������ӳ�,��֤�����͵Ķ����Ƽ�ʹ�õ������õķ�ʽ
    	 @SuppressWarnings("unused")
    	 String Tel = Prolong.phonenumber;
    	 //System.out.println(Tel);
    	 request.setPhoneNumbers(Tel);
    	 //����:����ǩ��-���ڶ��ſ���̨���ҵ�
    	 request.setSignName("DBbill����ƽ̨");
    	 //����:����ģ��-���ڶ��ſ���̨���ҵ�
    	 request.setTemplateCode("SMS_127157288");
    	 //��ѡ:ģ���еı����滻JSON��,��ģ������Ϊ"�װ���${name},������֤��Ϊ${code}"ʱ,�˴���ֵΪ
    	 //������ʾ:���JSON����Ҫ�����з�,����ձ�׼��JSONЭ��Ի��з���Ҫ��,������������а���\r\n�������JSON����Ҫ��ʾ��\\r\\n,����ᵼ��JSON�ڷ���˽���ʧ��
    	 random = (int) ((Math.random()*9+1)*100000);     
         request.setTemplateParam("{\"code\":\"" + random + "\"}");
         
         //˼·�������ɵ���֤������д�����ݿ⣬�ٷ����û����ϣ����ͳһ
    	 //��ѡ-���ж�����չ��(��չ���ֶο�����7λ�����£������������û�����Դ��ֶ�)
    	 //request.setSmsUpExtendCode("90997");
    	 //��ѡ:outIdΪ�ṩ��ҵ����չ�ֶ�,�����ڶ��Ż�ִ��Ϣ�н���ֵ���ظ�������
    	 request.setOutId("yourOutId");
    	//����ʧ���������ClientException�쳣
    	SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
    	if(sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
    	//����ɹ�
    	}
    }
    
}

