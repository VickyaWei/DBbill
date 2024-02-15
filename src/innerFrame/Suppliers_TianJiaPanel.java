package innerFrame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.mail.internet.InternetAddress;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import tools.InputKeyListener;
import tools.MailUtils;
import model.Suppliers;
import model.Users;
import connect.ConnectDb;
import innerFrame.Suppliers_XiuGaiPanel.DelActionListener;
import innerFrame.Suppliers_XiuGaiPanel.ModifyActionListener;
public class Suppliers_TianJiaPanel extends JPanel {
	private JTextField CnameF;
	private JTextField PropertyF;
	private JTextField ScopeF;
	private JTextField CaddressF;
	private JTextField NumberF;
	private JTextField SareaF;
	private JTextField FareaF;
	private JTextField PhoneF;
	private JTextField EmailF;
	private JTextField StuffF;
	private JTextField SphoneF;
	private JTextField FAF;
	private JTextField RCF;
	private JTextField LicenseF;
	private JTextField IDcardF;
	private JTextField AuthorizationF;
	private JTextField StaxF;
	private final JComboBox yzJ = new JComboBox();
	private JTextField LtaxF;
	private JTextField CodeF;
	private JTextField SupcodeF;
	private JTextField passwordF;
	private JButton resetButton;
	
	String CID = null;
	public Suppliers_TianJiaPanel() {
		setLayout(new GridBagLayout());
		setBounds(10, 10, 510, 602);

		setupComponet(new JLabel("��˾���ƣ�"), 0, 0, 1, 1, false);
		CnameF = new JTextField();
		setupComponet(CnameF, 1, 0, 3, 400, true);

		setupComponet(new JLabel("��˾���ʣ�"), 0, 1, 1, 1, false);
		PropertyF = new JTextField();
		setupComponet(PropertyF, 1, 1, 1, 160, true);

		setupComponet(new JLabel("��Ӫ��Χ��"), 2, 1, 1, 1, false);
		ScopeF = new JTextField();
		//ScopeF.addKeyListener(new InputKeyListener());
		setupComponet(ScopeF, 3, 1, 1, 0, true);

		setupComponet(new JLabel("��˾��ַ��"), 0, 2, 1, 1, false);
		CaddressF = new JTextField();
		setupComponet(CaddressF, 1, 2, 3, 0, true);

		setupComponet(new JLabel("Ա���������ˣ���"), 0, 3, 1, 1, false);
		NumberF = new JTextField();
		NumberF.addKeyListener(new InputKeyListener());
		setupComponet(NumberF, 1, 3, 1, 0, true);

		setupComponet(new JLabel("�ֿ���������O����"), 2, 3, 1, 1, false);
		SareaF = new JTextField();
		SareaF.addKeyListener(new InputKeyListener());
		setupComponet(SareaF, 3, 3, 1, 0, true);

		setupComponet(new JLabel("������������O����"), 0, 4, 1, 1, false);
		FareaF = new JTextField();
		setupComponet(FareaF, 1, 4, 1, 0, true);

		setupComponet(new JLabel("�칫�绰��"), 2, 4, 1, 1, false);
		PhoneF = new JTextField();
		PhoneF.addKeyListener(new InputKeyListener());
		setupComponet(PhoneF, 3, 4, 1, 0, true);

		setupComponet(new JLabel("�칫���䣺"), 0, 5, 1, 1, false);
		EmailF = new JTextField();
		setupComponet(EmailF, 1, 5, 1, 0, true);

		 
		setupComponet(new JLabel("��ϵ��������"), 2, 5, 1, 1, false);
		StuffF = new JTextField();
		setupComponet(StuffF, 3, 5, 1, 0, true);

		setupComponet(new JLabel("��ϵ�˵绰��"), 0, 6, 1, 1, false);
		SphoneF = new JTextField();
		setupComponet(SphoneF, 1, 6, 1, 0, true);
		
		setupComponet(new JLabel("�̶��ʲ�����Ԫ����"), 2, 6, 1, 1, false);
		FAF = new JTextField();
		setupComponet(FAF, 3, 6, 1, 0, true);
		
		setupComponet(new JLabel("ע���ʽ���Ԫ����"), 0, 7, 1, 1, false);
		RCF = new JTextField();
		setupComponet(RCF, 1, 7, 1, 0, true);
		
		setupComponet(new JLabel("����Ӫҵִ�գ�"), 2, 7, 1, 1, false);
		LicenseF = new JTextField();
		setupComponet(LicenseF, 3, 7, 1, 0, true);
		
		setupComponet(new JLabel("�������֤��"), 0, 8, 1, 1, false);
		IDcardF = new JTextField();
		setupComponet(IDcardF, 1, 8, 1, 0, true);
		
		setupComponet(new JLabel("������Ȩ�飺"), 2, 8, 1, 1, false);
		AuthorizationF = new JTextField();
		setupComponet(AuthorizationF, 3, 8, 1, 0, true);
		
		setupComponet(new JLabel("��˰�Ǽ�֤��"), 0, 9, 1, 1, false);
		StaxF = new JTextField();
		setupComponet(StaxF, 1, 9, 1, 0, true);
		
		setupComponet(new JLabel("�տλ�����У�"), 2, 9, 1, 1, false);
		LtaxF = new JTextField();
		setupComponet(LtaxF, 3, 9, 1, 0, true);
		
		setupComponet(new JLabel("�Ƿ�ͨ����֤��"), 0, 10, 1, 1, false);
		yzJ.addItem("ͨ��ISO14000");
		yzJ.addItem("ͨ��SA800");
		yzJ.addItem("����ͨ��");
		yzJ.addItem("��δͨ��");
		yzJ.setEditable(false);
		setupComponet(yzJ, 1, 10, 1, 1, true);
		
		setupComponet(new JLabel("�������˺ţ�"), 2, 10, 1, 1, false);
		CodeF = new JTextField();
		setupComponet(CodeF, 3, 10, 1, 0, true);
		
		setupComponet(new JLabel("��˾���ţ�"), 0, 11, 1, 1, false);
		SupcodeF = new JTextField();
		setupComponet(SupcodeF, 1, 11, 1, 0, true);
		
		setupComponet(new JLabel("ע�����룺"), 2, 11, 1, 1, false);
		passwordF = new JTextField();
		setupComponet(passwordF, 3, 11, 1, 0, true);

		
		final JButton tjButton = new JButton();
		tjButton.addActionListener(new TjActionListener());
		tjButton.setText("���");

		resetButton = new JButton();	
		resetButton.addActionListener(new ResetActionListener());
		resetButton.setText("����");
		
		JPanel panel = new JPanel();
		panel.add(tjButton);
		panel.add(resetButton);
		// ��λ��ť
		setupComponet(panel,2, 12, 1, 0, false);
		
		
		//System.out.println(set);
		
				String sid =  ConnectDb.selectSupplier2();
				
				if (sid == null){
					CID ="gys1001";					
				}							
				else {
					String str = sid.substring(3);
					CID = "gys" + (Integer.parseInt(str) + 1);
				}
		
	
		SupcodeF.setText(CID);
		SupcodeF.setEditable(false);
	}
	
	// �������λ�ò���ӵ�������
	private void setupComponet(JComponent component, int gridx, int gridy,
			int gridwidth, int ipadx, boolean fill) {
		final GridBagConstraints gridBagConstrains = new GridBagConstraints();
		gridBagConstrains.gridx = gridx;
		gridBagConstrains.gridy = gridy;
		gridBagConstrains.insets = new Insets(5, 1, 3, 1);
		if (gridwidth > 1)
			gridBagConstrains.gridwidth = gridwidth;
		if (ipadx > 0)
			gridBagConstrains.ipadx = ipadx;
		if (fill)
			gridBagConstrains.fill = GridBagConstraints.HORIZONTAL;
		add(component, gridBagConstrains);
	}
	class ResetActionListener implements ActionListener {// ���ť���¼�������
		public void actionPerformed(final ActionEvent e) {
			FareaF.setText("");
			PhoneF.setText("");
			NumberF.setText("");
			EmailF.setText("");
			CaddressF.setText("");
			ScopeF.setText("");
			CnameF.setText("");
			StuffF.setText("");
			SareaF.setText("");
			PropertyF.setText("");
			SphoneF.setText("");
			FAF.setText("");
			RCF.setText("");
			LicenseF.setText("");
			IDcardF.setText("");
			AuthorizationF.setText("");
			StaxF.setText("");
			LtaxF.setText("");
			CodeF.setText("");
			SupcodeF.setText("");
			passwordF.setText("");
		}
	}
	class TjActionListener implements ActionListener {// ��Ӱ�ť���¼�������
		public void actionPerformed(final ActionEvent e) {
			if (FareaF.getText().equals("") || StuffF.getText().equals("")
					|| NumberF.getText().equals("")
					|| EmailF.getText().equals("")
					|| PropertyF.getText().equals("")
					|| PhoneF.getText().equals("")
					|| CaddressF.getText().equals("")
					|| ScopeF.getText().equals("")
					|| CnameF.getText().equals("")
					|| SareaF.getText().equals("")
					|| SphoneF.getText().equals("")
					|| FAF.getText().equals("")
					|| RCF.getText().equals("")
					|| LicenseF.getText().equals("")
					|| IDcardF.getText().equals("")
					|| AuthorizationF.getText().equals("")
					|| StaxF.getText().equals("")
					|| LtaxF.getText().equals("")
					|| CodeF.getText().equals("")
					|| SupcodeF.getText().equals("")
				    || passwordF.getText().equals("")
					|| yzJ.getSelectedItem().equals(""))
				{
				JOptionPane.showMessageDialog(Suppliers_TianJiaPanel.this, "����дȫ����Ϣ");
				return;
			}
			
			MailUtils cn = new MailUtils();		       
	        cn.setAddress("tz9788@163.com", EmailF.getText().trim(), CnameF.getText().trim()+"ע��ɹ�֪ͨ");
	         cn.setContent("�����û����ǣ�"+SupcodeF.getText().trim()+"\n"+"���ĳ�ʼ�����ǣ�"+passwordF.getText()+"\n"
	        		+"Ϊ���˺Ű�ȫ�����¼ϵͳ�޸�����");	
	          
	        cn.send("smtp.163.com", "tz9788@163.com", "xyy19970706");
	        
	       
				
				
			int i=ConnectDb.addSuppliers(CID,CnameF.getText().trim(),PropertyF.getText().trim(),ScopeF.getText().trim(),
						CaddressF.getText().trim(),NumberF.getText().trim(),SareaF.getText().trim(),
						FareaF.getText().trim(),PhoneF.getText().trim(),EmailF.getText().trim(),StuffF.getText().trim(),
						SphoneF.getText().trim(),FAF.getText().trim(),RCF.getText().trim(),LicenseF.getText().trim(),
						IDcardF.getText().trim(),AuthorizationF.getText().trim(),StaxF.getText().trim(),LtaxF.getText().trim(),CodeF.getText().trim(),yzJ.getSelectedItem().toString().trim());
				
				Users tbUserlist = new Users();
				
				tbUserlist.setPassword(passwordF.getText().trim());
				tbUserlist.setLoginName(SupcodeF.getText().trim());					
				ConnectDb.addUser1(tbUserlist);
				
				if(i==1){JOptionPane.showMessageDialog(Suppliers_TianJiaPanel.this, "�ѳɹ���ӹ�Ӧ��",
						"��Ӧ�������Ϣ", JOptionPane.INFORMATION_MESSAGE);
				}
				resetButton.doClick();
			
		}
	}
	//public static void main(String args[])
    {
    	//new Suppliers_TianJiaPanel();
    }
	
}