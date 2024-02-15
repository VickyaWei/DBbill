package innerFrame;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import connect.ConnectDb;
import frame.BrandFrame;
import tools.InputKeyListener;
import tools.Item;
import model.Suppliers;
import model.Users;
import innerFrame.Suppliers_TianJiaPanel.TjActionListener;

public class Suppliers_XiuGaiPanel extends JPanel {
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
	private JTextField passwordF;
	private final JComboBox yzJ = new JComboBox();
	private JTextField LtaxF;
	private JTextField CodeF;
	private JComboBox gys;
	DefaultComboBoxModel sModel;
	
	String name=null;
	
	public Suppliers_XiuGaiPanel() {
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
		ScopeF.addKeyListener(new InputKeyListener());
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

		setupComponet(new JLabel("ѡ��Ӧ��"), 0, 11, 1, 0, false);
		gys = new JComboBox();
		
		
		gys.setPreferredSize(new Dimension(160, 21));
		initComboBox();// ��ʼ������ѡ���
		
		gys.addActionListener(new doGysSelectAction());
		// ����Ӧ����Ϣ������ѡ����ѡ���¼�
		
		// ��λ��Ӧ����Ϣ������ѡ���
		setupComponet(gys, 1, 11, 1, 0, true);
		
		setupComponet(new JLabel("��¼���룺"), 2, 11, 1, 1, false);
		passwordF = new JTextField();
		setupComponet(passwordF, 3, 11, 1, 0, true);
		
		JButton modifyButton = new JButton("�޸�");
		JButton delButton = new JButton("ɾ��");
		JPanel panel = new JPanel();
		panel.add(modifyButton);
		panel.add(delButton);
		// ��λ��ť
		setupComponet(panel,2, 12, 1, 0, false);
		// ����ɾ����ť�ĵ����¼�
		delButton.addActionListener(new DelActionListener());
		// �����޸İ�ť�ĵ����¼�
		modifyButton.addActionListener(new ModifyActionListener());
	}
	// ��ʼ����Ӧ������ѡ���
	
	
		public void initComboBox() {
			List gysInfo = ConnectDb.getSuppliersInfos();
			//List<Item> items = new ArrayList<Item>();
			gys.removeAllItems();
			for (Iterator iter = gysInfo.iterator(); iter.hasNext();) {
				List element = (List) iter.next();
				Item item = new Item();
				item.setId(element.get(0).toString().trim());
				//System.out.println(element);
				//item.setName(element.get(1).toString().trim());
				
				gys.addItem(item.getId());
			}
			//new doGysSelectAction();
		}
	
		
	
	// �������λ�ò���ӵ�������
	private void setupComponet(JComponent component, int gridx, int gridy,
			int gridwidth, int ipadx, boolean fill) {
		final GridBagConstraints gridBagConstrains = new GridBagConstraints();
		gridBagConstrains.gridx = gridx;
		gridBagConstrains.gridy = gridy;
		if (gridwidth > 1)
			gridBagConstrains.gridwidth = gridwidth;
		if (ipadx > 0)
			gridBagConstrains.ipadx = ipadx;
		gridBagConstrains.insets = new Insets(5, 1, 3, 1);
		if (fill)
			gridBagConstrains.fill = GridBagConstraints.HORIZONTAL;
		add(component, gridBagConstrains);
	}
	// ����Ӧ��ѡ���¼�
	class doGysSelectAction implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			//Item selectedItem;
			String selectedItem;
		//if (!(gys.getSelectedItem() instanceof Item)) {
			//return;
		//}
		 selectedItem =(String)gys.getSelectedItem();
	
		name=String.valueOf(selectedItem).trim();
		Suppliers gysInfo = ConnectDb.getSuppliersInfo(name);
		Users user=ConnectDb.getUser(name);		
		CnameF.setText(gysInfo.getName());
		ScopeF.setText(gysInfo.getScope());
		PropertyF.setText(gysInfo.getProperty());
		CaddressF.setText(gysInfo.getCaddress());
		NumberF.setText(String.valueOf(gysInfo.getNumber()));
		SareaF.setText(String.valueOf(gysInfo.getSarea()));
		FareaF.setText(String.valueOf(gysInfo.getFarea()));
		PhoneF.setText(gysInfo.getPhone());
		EmailF.setText(gysInfo.getEmail());
		StuffF.setText(gysInfo.getStuff());
		SphoneF.setText(gysInfo.getSphone());
		FAF.setText(String.valueOf(gysInfo.getFA()));
		RCF.setText(String.valueOf(gysInfo.getRC()));
		LicenseF.setText(gysInfo.getLicense());
		IDcardF.setText(gysInfo.getIDcard());
		AuthorizationF.setText(gysInfo.getAuthorization());
		StaxF.setText(gysInfo.getStax());
		LtaxF.setText(gysInfo.getLtax());
		CodeF.setText(gysInfo.getCode());
		yzJ.setSelectedItem(gysInfo.getYz());
		passwordF.setText(user.getPassword());
	}
	}
	//�޸İ�ť���¼�������
	class ModifyActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String item;
			item= (String)gys.getSelectedItem();
			Suppliers gysInfo = new Suppliers();
			gysInfo.setId(item);
			gysInfo.setScope(ScopeF.getText().trim());
			gysInfo.setCaddress(CaddressF.getText().trim());
			gysInfo.setSarea(SareaF.getText().trim());
			gysInfo.setSphone(SphoneF.getText().trim());
			gysInfo.setNumber(NumberF.getText().trim());
			gysInfo.setProperty(PropertyF.getText().trim());
			gysInfo.setName(CnameF.getText().trim());
			gysInfo.setFarea(FareaF.getText().trim());
			gysInfo.setPhone(PhoneF.getText().trim());
			gysInfo.setEmail(EmailF.getText().trim());
			gysInfo.setStuff(StuffF.getText().trim());
			gysInfo.setFA(FAF.getText().trim());
			gysInfo.setRC(RCF.getText().trim());
			gysInfo.setLicense(LicenseF.getText().trim());
			gysInfo.setIDcard(IDcardF.getText().trim());
			gysInfo.setAuthorization(AuthorizationF.getText().trim());
			
			gysInfo.setStax(StaxF.getText().trim());
			gysInfo.setLtax(LtaxF.getText().trim());
			gysInfo.setCode(CodeF.getText().trim());
			gysInfo.setYz(yzJ.getSelectedItem().toString().trim());
			
			ConnectDb.updateUser(name,passwordF.getText().trim());
			
			if (ConnectDb.updateSuppliers(gysInfo) == 1)
				JOptionPane.showMessageDialog(Suppliers_XiuGaiPanel.this, "�޸����");
			else
				JOptionPane.showMessageDialog(Suppliers_XiuGaiPanel.this, "�޸�ʧ��");
		}
	}
	//ɾ����ť���¼�������
	class DelActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String item = (String) gys.getSelectedItem();
			//if (item == null || !(item instanceof Item))
				//return;
			int confirm = JOptionPane.showConfirmDialog(
					Suppliers_XiuGaiPanel.this, "ȷ��ɾ����Ӧ����Ϣ��");
			if (confirm == JOptionPane.YES_OPTION) {
				int rs = ConnectDb.delete("delete T_Supplier where Supcode='"
						+ item + "'");
				ConnectDb.delete("delete T_Authority where Code='" + item + "'");
				if (rs > 0) {
					JOptionPane.showMessageDialog(Suppliers_XiuGaiPanel.this,
							"��Ӧ�̣�" + item + "��ɾ���ɹ�");
					gys.removeItem(item);
				} else {
					JOptionPane.showMessageDialog(Suppliers_XiuGaiPanel.this,
							"�޷�ɾ����Ӧ�̣�" + item + "��");
				}
			}
		}
	}
	
	//public static void main(String args[])
    {
    	//new Suppliers_XiuGaiPanel();
    }
}
