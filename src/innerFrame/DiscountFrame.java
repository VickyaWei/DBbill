package innerFrame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import connect.ConnectDb;
import frame.LoginFrame;
import model.Discount;
import model.Users;
import tools.MobileAuthentication;
import tools.MobileAuthentication1;

public class DiscountFrame extends JInternalFrame{

	private Users user = LoginFrame.getUser();
	public static String phonenumber;
	
	private JTextField operator1;
	private JTextField bookType;
	private JTextField orderPrice;
	private JTextField zk;
	private JTable table;
	
	private JTextField price;
	private JTextField operator;
	private JTextField orderNumber;
	private JTextField ISBN;
	private JTextField orderDate;
	private JTextField updatetime;
	double money;

	private String[] columnNames={"��Ʊ��", "������Ӧ��", "��Ʊ���",  "����", "Ӧ�տ�ʱ��"};
	//private Map map=MapPz.getMap();
	 SimpleDateFormat myfmt=new SimpleDateFormat("yyyy-MM-dd");
	private Object[][] getFileStates(List list){
		
		Object[][] s = new Object[list.size()][5];
		
		for (int i = 0,j=0; i < list.size()&& j < list.size(); i++,j++) {	
			  
			  for (; j < list.size(); j++){
				Discount book2 = (Discount) list.get(j);
			 int a=book2.getpaytime();
			 Date d=ConnectDb.selectMaxtime(book2.getIncode());
			 Calendar rightNow = Calendar.getInstance();
			  rightNow.setTime(d);		  
			  rightNow.add(Calendar.DAY_OF_YEAR,a);
			Date dt1=rightNow.getTime();
			  
				Date d1=new java.util.Date();
				//System.out.println(dt1.before(d1));
				if(dt1.before(d1)){
					continue;
				}
				else{
					break;
				}
			}
			if(j<list.size()){			
			
			Discount book = (Discount) list.get(j);
			s[i][0] = book.getIncode();		
			s[i][1] = book.getSupcode();
			s[i][2] = book.getFinalprice();
			s[i][3] = book.getpaytime();
			
			int a=book.getpaytime();
			 Date d=ConnectDb.selectMaxtime(book.getIncode());
			 Calendar rightNow = Calendar.getInstance();
			  rightNow.setTime(d);		  
			  rightNow.add(Calendar.DAY_OF_YEAR,a);
			Date dt1=rightNow.getTime();
				//System.out.println(dt1.before(d1));
			s[i][4] = myfmt.format(dt1);
			
			for (; j < list.size()-1; j++){
				int t=j+1;
				//System.out.println(t);
			Discount book1 = (Discount) list.get(t);
			if(book1.getIncode().trim().equals(book.getIncode().trim())){
				continue;
			}
			    break;
			}
		}
			
			
		}
		return s;

	}

	/**
	 * Create the frame
	 */
	public DiscountFrame() {
		super();
		setClosable(true);
		setIconifiable(true);
		setAutoscrolls(true);
		setTitle("��Ʊ����");
		setBounds(100, 100, 700, 440);
		

		final JPanel panel = new JPanel();
		getContentPane().add(panel);
		
		final JLabel label_5 = new JLabel();
		label_5.setText("��ǰ��Ӧ�̣�");
		panel.add(label_5);
		operator = new JTextField(user.getLoginName());
		operator.setColumns(20);
		operator.setEditable(false);
		panel.add(operator);

		final JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(680, 180));
		panel.add(scrollPane);

		final DefaultTableModel model=new DefaultTableModel();
		Object[][] results=getFileStates(ConnectDb.getUndis2(operator.getText().trim()));
		model.setDataVector(results,columnNames);
		table = new JTable();
		table.setModel(model);
		//table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);//�ر����Զ���С
		scrollPane.setViewportView(table);
		table.addMouseListener(new TableListener());



		final JPanel panel_1_1 = new JPanel();
		final GridLayout gridLayout = new GridLayout(0, 4);
		gridLayout.setVgap(5);
		panel_1_1.setLayout(gridLayout);
		panel_1_1.setPreferredSize(new Dimension(450, 150));
		panel.add(panel_1_1);

		final JLabel label_1 = new JLabel();
		label_1.setText("��Ʊ�ţ�");
		panel_1_1.add(label_1);

		

		orderDate = new JTextField();
		//orderDate.setValue(new java.util.Date());
		orderDate.setEditable(false);

		panel_1_1.add(orderDate);

		final JLabel label_3 = new JLabel();
		label_3.setText("��׼�����ʣ�");
		panel_1_1.add(label_3);

		ISBN = new JTextField();
		ISBN.setEditable(false);
		panel_1_1.add(ISBN);

		final JLabel label_4 = new JLabel();
		label_4.setText("���������ʣ�");
		panel_1_1.add(label_4);

		orderNumber = new JTextField();
		orderNumber.setEditable(false);
		panel_1_1.add(orderNumber);
		
		final JLabel label_2 = new JLabel();
		label_2.setText("��ǰ������");
		panel_1_1.add(label_2);

		orderPrice = new JTextField();
		orderPrice.setEditable(false);
		panel_1_1.add(orderPrice);
		
		final JLabel label_8 = new JLabel();
		label_8.setText("��ǰ���ڣ�");
		panel_1_1.add(label_8);

		updatetime = new JTextField();
		updatetime.setEditable(false);
		panel_1_1.add(updatetime);

		final JLabel label_10 = new JLabel();
		label_10.setText("ʵ�����ʣ�");
		panel_1_1.add(label_10);
		operator1 = new JTextField();
		
		operator1.setEditable(false);
		panel_1_1.add(operator1);

		final JLabel label_6 = new JLabel();
		label_6.setText("�����ѣ�");
		panel_1_1.add(label_6);

		bookType = new JTextField();
		bookType.setEditable(false);
		panel_1_1.add(bookType);
		
		

		final JLabel label_7 = new JLabel();
		label_7.setText("�ֻ��ţ�");
		panel_1_1.add(label_7);

		price = new JTextField();
		panel_1_1.add(price);
		
		final JLabel label = new JLabel();
		label.setText("��֤�룺");
		panel_1_1.add(label);

		zk = new JTextField();
		panel_1_1.add(zk);
		
		final JLabel label_9 = new JLabel();
		label_9.setText("");
		panel_1_1.add(label_9);
		
		final JButton buttonExit = new JButton();
		panel_1_1.add(buttonExit);
		buttonExit.addActionListener(new CloseActionListener());
		buttonExit.setText("��ȡ��֤��");
		
		setVisible(true);

		final JPanel panel_2 = new JPanel();
		getContentPane().add(panel_2, BorderLayout.SOUTH);


		
		final JButton buttonCheck = new JButton();
		panel_2.add(buttonCheck);
		buttonCheck.setText("���ַ�Ʊ");
		buttonCheck.addActionListener(new CheckActionListener(model));//
		
	}
	
	class TableListener extends MouseAdapter {
		public void mouseClicked(final MouseEvent e) {
			
			int selRow = table.getSelectedRow();
			
			orderDate.setText(table.getValueAt(selRow, 0).toString().trim());
			float a=0;
					float a1=0;
					a=ConnectDb.getRate1();
			
			a1=(float) (a*1.15);
			
			
			ISBN.setText(String.valueOf(a));
			orderNumber.setText(String.valueOf(a1));
			
			String b=table.getValueAt(selRow, 4).toString().trim();
			
			Date d1=(new java.util.Date());
			Date d2=java.sql.Date.valueOf(b);
			int day=(int) ((d2.getTime()-d1.getTime())/(1000*3600*24));
			System.out.println(d2.getTime());
			
			
			orderPrice.setText(String.valueOf(day+1));
			
			
			updatetime.setText(myfmt.format(d1));
			
			float rate=a1/360*day;
			operator1.setText(String.valueOf(rate));
			money=Double.parseDouble(table.getValueAt(selRow, 2).toString().trim());
			float cost=(float) (money*rate/100);
			bookType.setText(String.valueOf(cost));
		}
	}
	class CloseActionListener implements ActionListener {			// ��ӹرհ�ť���¼�������
		public void actionPerformed(final ActionEvent e) {
			
			phonenumber=price.getText().trim();
			//conf.setphonenumber(phonenumber);
			MobileAuthentication1 mobile = new MobileAuthentication1();
			try {
				mobile.main(null);
			} catch (Exception e1) {
				// TODO �Զ����ɵ� catch ��
				e1.printStackTrace();
			}
		}
	}
	class CheckActionListener implements ActionListener{
		private final DefaultTableModel model;

		CheckActionListener(DefaultTableModel model) {
			this.model = model;
		}
		
		public void actionPerformed(final ActionEvent e) {
			
			if(MobileAuthentication1.random!=Integer.parseInt(zk.getText().trim())){
				JOptionPane.showMessageDialog(null, "��֤�����");
				return;
			}
			
			int confirm = JOptionPane.showConfirmDialog(
					DiscountFrame.this, "ȷ�����ַ�Ʊ��");
			if (confirm == JOptionPane.YES_OPTION) {
				if(money>50000000){
				int i=ConnectDb.InsertDiscount1(orderDate.getText().trim(),ISBN.getText().trim(),orderNumber.getText().trim(),Integer.parseInt(orderPrice.getText().trim()),operator1.getText().trim(),bookType.getText().trim());
				if(i==1){
					JOptionPane.showMessageDialog(null, "�����������ύ����ȴ����");
					bookType.setText("");
					orderPrice.setText("");
					zk.setText("");		
					price.setText("");
					operator1.setText("");
					orderNumber.setText("");
					ISBN.setText("");
					orderDate.setText("");
					updatetime.setText("");
	                Object[][] results=getFileStates(ConnectDb.getUndis2(operator.getText().trim()));
					model.setDataVector(results,columnNames);
					table.setModel(model);
					
				}
				}
				else{
					int i=ConnectDb.InsertDiscount(orderDate.getText().trim(),ISBN.getText().trim(),orderNumber.getText().trim(),Integer.parseInt(orderPrice.getText().trim()),operator1.getText().trim(),bookType.getText().trim());
					if(i==1){
						JOptionPane.showMessageDialog(null, "���ֳɹ���");
						bookType.setText("");
						orderPrice.setText("");
						zk.setText("");		
						price.setText("");
						operator1.setText("");
						orderNumber.setText("");
						ISBN.setText("");
						orderDate.setText("");
						updatetime.setText("");
						Object[][] results=getFileStates(ConnectDb.getUndis2(operator.getText().trim()));
						model.setDataVector(results,columnNames);
						table.setModel(model);
				}
				//JOptionPane.showMessageDialog(null, "��ѡ���ͼ���Ѿ����й����գ���ѡ������ͼ���������");
			
			}
		}
	}
	}
	
	

}
