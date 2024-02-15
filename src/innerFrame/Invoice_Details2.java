package innerFrame;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import innerFrame.Infor_ShenHe.BorrowActionListener;

import model.Users;
import tools.InputKeyListener;
import connect.ConnectDb;
import frame.LoginFrame;

public class Invoice_Details2 extends JFrame {
	private JTextField textField_1,textField_2,textField_3,textField_4,textField_5,textField_6,textField_7,textField_8,textField_9,textField_10,textField_11,textField_12,textField_13,textField_14,textField_15,textField_16,textField_17,textField_18;
	private String[] columnNames = { "发票号", "开票时间", "货物名称", "收货数量", "单价","总金额", "开票人"};
	DefaultTableModel model = new DefaultTableModel();
	private JTable table;
	private String sc[]=new String[100000];
	private int t1;
	private double p1;
	private int n1;
	private String in,name,draw,price,sum; 
	private String time;
	private String num;
	private Users user = LoginFrame.getUser();
	
	
	public Invoice_Details2(){
		super();
		Invoice_put invoice=new Invoice_put();
		sc=invoice.score;
		t1=invoice.t;
		p1=invoice.price;
	    n1=invoice.num;
	    
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setTitle("添加发票");	
		setBounds(100, 100, 700, 620);
		setVisible(true);
		
		final JPanel panel1=new JPanel();
		getContentPane().add(panel1, BorderLayout.NORTH);
		
		//上半部分
	
		panel1.setBorder(new TitledBorder(null, "收货单信息", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
		final JPanel panel1_1=new JPanel();
		panel1.add(panel1_1, BorderLayout.NORTH);
		
		final JLabel label = new JLabel();
		label.setText("订单号：");
		panel1_1.add(label);
		textField_1 = new JTextField();
		textField_1.setColumns(35);
		textField_1.setEditable(false);
		panel1_1.add(textField_1);
		textField_1.setText(sc[0]);
		
		final JPanel panel1_2=new JPanel();
		final GridLayout gridLayout1 = new GridLayout(0, 2);//创建网格布局管理器对象
		gridLayout1.setVgap(10);//设置组件的垂直间距
		panel1_2.setLayout(gridLayout1);//设置容器采用网格布局管理器
		panel1.add(panel1_2, BorderLayout.SOUTH);

		final JLabel label1 = new JLabel();
		label1.setText("订单总金额：");
		panel1_2.add(label1);
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		panel1_2.add(textField_2);
		textField_2.setText(""+p1);
		
		
		
		final JLabel label2 = new JLabel();
		label2.setText("货物数量：");
		panel1_2.add(label2);
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		panel1_2.add(textField_3);
		textField_3.setText(""+n1);
		
		
		//中部分
		 final JPanel p=new JPanel();
	     getContentPane().add(p);
	     p.setBorder(new TitledBorder(null, "发票信息", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
		  
	
	    JScrollPane scrollPane = new JScrollPane();
	 	scrollPane.setPreferredSize(new Dimension(600, 350));
	 	p.add(scrollPane);
	 	
	 	

	 	table = new JTable();
	 	//table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	 	scrollPane.setViewportView(table);//在table中增加滚动条
	 	model.setColumnIdentifiers(columnNames);//用setColumnIdentifiers替换列（或者说设置列名）
	 	table.setModel(model);
	 	//((DefaultTableModel) this.getModel()).addRow(new Object[3]);
	 	
	 	((DefaultTableModel) table.getModel()).addRow(new Object[6]);
	

		final JButton buttonBorrow1 = new JButton();
		buttonBorrow1.setText("添加一张");
	   buttonBorrow1.addActionListener(new BorrowActionListener1());
		p.add(buttonBorrow1);
		
	 	
	 	
	 	
		//下部分
	JPanel pa=new JPanel();
		

		final JLabel label5 = new JLabel();
			label5.setText("对账参考号：");
			pa.add(label5);
			textField_6 = new JTextField();
			//textField_3.setEditable(false);
			textField_6.setColumns(40);
			pa.add(textField_6);	
			textField_6.setEditable(false);
		
		
		final JButton buttonBorrow = new JButton();
		buttonBorrow.setText("开发票");
	    buttonBorrow.addActionListener(new BorrowActionListener());
		pa.add(buttonBorrow);
		getContentPane().add(pa, BorderLayout.SOUTH);
		
}
	class BorrowActionListener1 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			((DefaultTableModel) table.getModel()).addRow(new Object[6]);
		}}
	
	
	class BorrowActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			 Random rm = new Random();
			 int i=0,i1=0,i2=0;
			int pross=(int)(Math.random()*900)+100; 
			String fixLenthString = String.valueOf(pross);  
			textField_6.setText(textField_1.getText()+fixLenthString);
			//System.out.println(textField_1.getText()+fixLenthString);
			String set=textField_1.getText()+fixLenthString;
			int f=table.getRowCount();
			//System.out.println(f);
			for(int t=0;t<f;t++)
			{
				in=(String) table.getValueAt(t, 0);
				time=(String) table.getValueAt(t, 1);
				name=(String) table.getValueAt(t, 2);
			    num=(String) table.getValueAt(t, 3);
				price=(String) table.getValueAt(t, 4);
				sum=(String) table.getValueAt(t, 5);
				draw=(String) table.getValueAt(t, 6);
				
				i=ConnectDb.InsertInvoice(in.trim(),name.trim(),java.sql.Date.valueOf(time),Double.valueOf(sum),draw.trim(),Double.valueOf(price),Integer.parseInt(num),user.getLoginName());
				i1=ConnectDb.InsertRecon(textField_1.getText().trim(),in.trim(),set.trim(),1);
				  
			}
		i2=ConnectDb.UpdateCom(0, sc[0]);
			
		if(i==1 && i1==1 && i2==1){
			 JOptionPane.showMessageDialog(getContentPane(), "发票添加成功！");  
			 textField_1.setText("");
			 textField_2.setText("");
			 textField_3.setText("");
			 dispose();
		}
		else{
			JOptionPane.showMessageDialog(getContentPane(), "发票添加失败！");  
		}
		
		
		}}
		
	
	
}
