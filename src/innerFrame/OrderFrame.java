package innerFrame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;


import connect.ConnectDb;
import frame.LoginFrame;

import model.Suppliers;
import model.Users;



public class OrderFrame extends JInternalFrame{
	private Users user = LoginFrame.getUser();
	
	private JTextField Code;
	private JTextField Manager;
	private JTextField period;
	
	private JTextField price;
	private JTextField tprice;
	private JTextField lprice;
	private JTextField discount;
	private JTextField detail;
	private JTextField quantity;
	
	DefaultComboBoxModel sModel;
	private JComboBox supplier;
	private JFormattedTextField addDate;
	private JFormattedTextField period1;
	DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
	
	
	private Object[] getselect(List list) {
		Object[] s = new Object[list.size()];
		for (int i = 0; i < list.size(); i++) {
			Suppliers ss = (Suppliers) list.get(i);
			s[i] = ss.getName();		
		}
		return s;

	}
	
	public OrderFrame() {
		
		super();
		setClosable(true);
		setIconifiable(true);
		setAutoscrolls(true);
		setTitle("�ϴ��ջ�����Ϣ");
		setBounds(100, 100, 500, 360);
		//setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		
		
		final JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		panel.setPreferredSize(new Dimension(0, 240));
		getContentPane().add(panel);

		final JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(null, "������ϸ", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
		panel_4.setPreferredSize(new Dimension(490, 100));
		final GridLayout gridLayout_1 = new GridLayout(0, 4);
		gridLayout_1.setVgap(8);
		panel_4.setLayout(gridLayout_1);
		panel.add(panel_4);

		
		final JLabel label_5 = new JLabel();
		panel_4.add(label_5);
		label_5.setText("�ջ�����");
		
		SimpleDateFormat myFmt=new SimpleDateFormat("yyyy-mm-dd");  
		period1 = new JFormattedTextField(myFmt.getDateInstance());       
		//period1 = new JTextField();
		panel_4.add(period1);

		
		
		final JLabel label_3 = new JLabel();
		panel_4.add(label_3);
		label_3.setText("���ڣ�");
		period = new JTextField();
		panel_4.add(period);
		
        final JLabel label_2 = new JLabel();
		panel_4.add(label_2);
		label_2.setText("����Ա��");
		Manager = new JTextField(user.getLoginName());
		Manager.setEditable(false);
		panel_4.add(Manager);
		
		final JLabel label_1 = new JLabel();
		panel_4.add(label_1);
		label_1.setText("�ϴ����ڣ�");

		SimpleDateFormat myfmt1=new SimpleDateFormat("yyyy-MM-dd");
		addDate = new JFormattedTextField(myfmt1.getDateInstance());
		panel_4.add(addDate);
		addDate.setEditable(false);
		addDate.setValue(new java.util.Date());
		//orderDate.addKeyListener(new DateListener());
		
		final JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "��Ʒ��Ϣ", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
		final GridLayout gridLayout = new GridLayout(0, 4);
		gridLayout.setVgap(5);
		panel_1.setLayout(gridLayout);
		panel_1.setPreferredSize(new Dimension(0, 160));
		getContentPane().add(panel_1, BorderLayout.NORTH);

		final JLabel label_7 = new JLabel();
		label_7.setText("�ջ�����ţ�");
		panel_1.add(label_7);

		Code = new JTextField();
	
		//ISBN.addKeyListener(new ISBNListener());
		///ISBN.addFocusListener(new ISBNListenerlostFocus());
		panel_1.add(Code);

		final JLabel label_6 = new JLabel();
		label_6.setText("������Ӧ�̣�");
		panel_1.add(label_6);

		supplier = new JComboBox();
		sModel=(DefaultComboBoxModel)supplier.getModel();
		panel_1.add(supplier);
		
		Object[] results=getselect(ConnectDb.selectSupplier());
		supplier.setModel(new DefaultComboBoxModel(results));
		


		final JLabel label_9 = new JLabel();
		label_9.setText("��Ʒ��ϸ��");
		panel_1.add(label_9);
		
		detail = new JTextField();
		panel_1.add(detail);
		
		
		final JLabel label_11 = new JLabel();
		label_11.setText("���ۣ�");
		panel_1.add(label_11);

		price = new JTextField();
		panel_1.add(price);

		final JLabel label_12 = new JLabel();
		label_12.setText("�ջ�������");
		panel_1.add(label_12);

		quantity = new JTextField();
		panel_1.add(quantity);
			
		
		final JLabel label_16 = new JLabel();
		label_16.setText("�ܼۣ�");
		panel_1.add(label_16);

		tprice = new JTextField();
		panel_1.add(tprice);
		
		final JLabel label_13 = new JLabel();
		label_13.setText("�ۿ۽�");
		panel_1.add(label_13);

		discount = new JTextField();
		panel_1.add(discount);
		
		final JLabel label_14 = new JLabel();
		label_14.setText("�ۿۺ��ܼۣ�");
		panel_1.add(label_14);

		lprice = new JTextField();
		panel_1.add(lprice);
		
		
		final JPanel panel_2 = new JPanel();
		panel_2.setPreferredSize(new Dimension(0, 50));
		getContentPane().add(panel_2, BorderLayout.SOUTH);

		final JButton buttonAdd = new JButton();
		buttonAdd.setText("���");
		buttonAdd.addActionListener(new ButtonAddLisenter());
		panel_2.add(buttonAdd);

		final JButton ButtonExit = new JButton();
		ButtonExit.setText("�˳�");
		ButtonExit.addActionListener(new CloseActionListener());
		panel_2.add(ButtonExit);
		setVisible(true);
		
		this.setVisible(true);
	}
	
	
	class CloseActionListener implements ActionListener {			// ��ӹرհ�ť���¼�������
		public void actionPerformed(final ActionEvent e) {
			dispose();
		}
	}
	class ButtonAddLisenter implements ActionListener{
		public void actionPerformed(final ActionEvent e) {
			
			if(Code.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "�ջ�������ı��򲻿�Ϊ��");
				return;
			}
			if(Manager.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "����Ա�ı��򲻿�Ϊ��");
				return;
			}
			if(period.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "�����ı��򲻿�Ϊ��");
				return;
			}
			if(period1.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "�ջ������ı��򲻿�Ϊ��");
				return;
			}
			if(price.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "�����ı��򲻿�Ϊ��");
				return;
			}
			
			if(tprice.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "�ܼ��ı��򲻿�Ϊ��");
				return;
			}
			if(lprice.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "�ۿۺ����ı��򲻿�Ϊ��");
				return;
			}
			
			if(discount.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "�ۿ۽���ı��򲻿�Ϊ��");
				return;
			}
			
			if(detail.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "��Ʒ��ϸ�ı��򲻿�Ϊ��");
				return;
			}
			if(quantity.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "�����ı��򲻿�Ϊ��");
				return;
			}
			
			          
			String Comcode=Code.getText().trim();	
			String Staff=Manager.getText().trim();		
			String Combewrite=detail.getText().trim();
			String Comnum=quantity.getText().trim();
			Double Comprice=Double.parseDouble(price.getText().trim());
			Double Sprice=Double.parseDouble(tprice.getText().trim());	
			Double Disprice=Double.parseDouble(discount.getText().trim());
			Double Finalprice=Double.parseDouble(lprice.getText().trim());
			Date Gooddate = java.sql.Date.valueOf(period1.getText().trim());
			
			int Paytime=Integer.parseInt(period.getText().trim());
			Date uplatedate=java.sql.Date.valueOf(addDate.getText().trim());
			
			String selectedItem = ConnectDb.selectSupplier1(supplier.getSelectedItem().toString().trim());
			//System.out.println(selectedItem);
		
			
			int i=ConnectDb.Insertorder(Comcode,Combewrite, Comnum, Comprice, Sprice, Disprice, Finalprice,Gooddate,Paytime,uplatedate,selectedItem,Staff);
			//System.out.println(Gooddate);	
			if(i==1){
			
				JOptionPane.showMessageDialog(null, "��ӳɹ�");
				
				Code.setText("");						
				detail.setText("");
				quantity.setText("");
				price.setText("");
				tprice.setText("");	
				discount.setText("");
				lprice.setText("");
				period1.setText("");
				period.setText("");
			
				
			}
	
		
		
	//public static void main(String args[])
    {
    	//new OrderFrame();
    }
	
		}
	}
}
