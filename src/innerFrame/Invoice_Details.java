package innerFrame;

import java.awt.BorderLayout;
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
import java.util.List;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import innerFrame.Infor_ShenHe.BorrowActionListener;
import model.Check;
import model.Supplier;

import model.Users;
import tools.InputKeyListener;
import connect.ConnectDb;
import frame.LoginFrame;



public class Invoice_Details extends JFrame{

	private JTextField textField_1,textField_2,textField_3,textField_4,textField_5,textField_6,textField_7,textField_8,textField_9,textField_10,textField_11,textField_12,textField_13,textField_14,textField_15,textField_16,textField_17,textField_18;
	private String sc[]=new String[100000];
    private String Stc;
    StringBuffer buffer =new StringBuffer();
    private int t1;
    private double p1;
    private int n1;
    private Users user = LoginFrame.getUser();
    private String a,a1,a2,a3,a4,a5,a6;
    
	private Object[][] getselect(List list) {
		//Object[][] s = new Object[list.size()][columnNames.length];
		Object[][] s = new Object[list.size()][6];
		for (int i = 0; i < list.size(); i++) {
			Supplier check = (Supplier) list.get(i);
			s[i][0] = check.getCname();	
			s[i][1] = check.getStax();
			s[i][2] = check.getPhone();
			s[i][3] = check.getLtax();
			s[i][4]=check.getCcode();
			s[i][5]=check.getCaddress();
			
			
			
			
		}		
		return s;
	}
	
    
    
    
    
    
    
    
    
    
	public Invoice_Details(){
		super();
		Invoice_put invoice=new Invoice_put();
	    sc=invoice.score;
        t1=invoice.t;
        p1=invoice.price;
        n1=invoice.num;
    	setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setTitle("发票");	
		setBounds(100, 100, 700, 500);
		setVisible(true);
		
		Object[][] re=getselect(ConnectDb.select6(user.getLoginName()));
		a=(String) re[0][0];
		a1=(String) re[0][1];
		a2=(String) re[0][2];
		a3=(String) re[0][3];
		a4=(String) re[0][4];
		a5=(String) re[0][5];
		
		
		
		final JPanel panel1=new JPanel();
		getContentPane().add(panel1, BorderLayout.NORTH);
		 buffer.delete(0, buffer.length());
		//上部分
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
		

		   for(int c=0;c<=t1;c++)
		  {		
	
		    buffer.append(sc[c]).append(",");
	      
	      }
		String bu=buffer.toString();
	    textField_1.setText(bu);
		   
		
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
		
		
		
		//下部分
	    final JPanel p=new JPanel();
	    //getContentPane().add(p, BorderLayout.CENTER);
	    getContentPane().add(p);
	    p.setBorder(new TitledBorder(null, "发票信息", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
		
		
		final JPanel panel = new JPanel();
		p.add(panel, BorderLayout.WEST);
		final GridLayout gridLayout = new GridLayout(0, 2);//创建网格布局管理器对象
		gridLayout.setVgap(10);//设置组件的垂直间距
		panel.setLayout(gridLayout);//设置容器采用网格布局管理器

		final JLabel label3 = new JLabel();
		label3.setText("发票号：  ");
		panel.add(label3);
		textField_4 = new JTextField();
		//textField_3.setEditable(false);
		panel.add(textField_4);
		
		final JLabel label4 = new JLabel();
		label4.setText("开票日期：  ");
		panel.add(label4);
		textField_5 = new JTextField();
		//textField_3.setEditable(false);
		panel.add(textField_5);		
		
	//	final JLabel label5 = new JLabel();
	//	label5.setText("开票日期：");
	//	panel.add(label5);
	//	textField_6 = new JTextField();
	//	//textField_3.setEditable(false);
	//	panel.add(textField_6);		
		final JLabel label17 = new JLabel();
		label17.setText("货物名称：       ");
		panel.add(label17);
		textField_18 = new JTextField();
		//textField_3.setEditable(false);
		panel.add(textField_18);
		
		final JLabel label6 = new JLabel();
		label6.setText("收货数量：    ");
		panel.add(label6);
		textField_7 = new JTextField();
		//textField_3.setEditable(false);
		panel.add(textField_7);
		
		final JLabel label7 = new JLabel();
		label7.setText("单价：       ");
		panel.add(label7);
		textField_8 = new JTextField();
		//textField_3.setEditable(false);
		panel.add(textField_8);		
		
		final JLabel label8 = new JLabel();
		label8.setText("总金额：      ");
		panel.add(label8);
		textField_9 = new JTextField();
		//textField_3.setEditable(false);
		panel.add(textField_9);		
		
		final JLabel label9 = new JLabel();
		label9.setText("开票人：       ");
		panel.add(label9);
		textField_10 = new JTextField();
		//textField_3.setEditable(false);
		panel.add(textField_10);
		
		final JPanel p1=new JPanel();
		final GridLayout gridLayout2 = new GridLayout(0, 2);//创建网格布局管理器对象
		gridLayout2.setVgap(10);//设置组件的垂直间距
		p1.setLayout(gridLayout2);//设置容器采用网格布局管理器
		p.add(p1, BorderLayout.EAST);
		
		
		final JLabel label10 = new JLabel();
		label10.setText("供应商名称：");
		p1.add(label10);
		textField_11 = new JTextField();
		textField_11.setEditable(false);
		p1.add(textField_11);
		textField_11.setText(a);
		
		
		
		final JLabel label11 = new JLabel();
		label11.setText("收款单位名称：");
		p1.add(label11);
		textField_12 = new JTextField();
		textField_12.setEditable(false);
		p1.add(textField_12);
		textField_12.setText(a);
		
		
		
		final JLabel label12 = new JLabel();
		label12.setText("收款单位税号：");
		p1.add(label12);
		textField_13 = new JTextField();
		textField_13.setEditable(false);
		p1.add(textField_13);
		textField_13.setText(a1);
		
		
		final JLabel label13 = new JLabel();
		label13.setText("收款单位电话：");
		p1.add(label13);
		textField_14 = new JTextField();
		textField_14.setEditable(false);
		p1.add(textField_14);
		textField_14.setText(a2);
		
		
		
		final JLabel label14 = new JLabel();
		label14.setText("收款单位开户行：");
		p1.add(label14);
		textField_15 = new JTextField();
		textField_15.setEditable(false);
		p1.add(textField_15);
		textField_15.setText(a3);
		
		
		
		final JLabel label15 = new JLabel();
		label15.setText("开户行账号：");
		p1.add(label15);
		textField_16 = new JTextField();
		textField_16.setEditable(false);
		p1.add(textField_16); 
		textField_16.setText(a4);
		
		
		
		
		final JLabel label16 = new JLabel();
		label16.setText("收款方地址：");
		p1.add(label16);
		textField_17 = new JTextField();
		textField_17.setEditable(false);
		p1.add(textField_17); 
		textField_17.setText(a5);
		//JPanel pa1=new JPanel();
		
			
		
		
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
	class BorrowActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			int i1 = 0,i2=0,i=0;
		
			 i=ConnectDb.InsertInvoice(textField_4.getText().trim(),textField_18.getText().trim(),java.sql.Date.valueOf(textField_5.getText()),Double.valueOf(textField_9.getText()),textField_10.getText().trim(),Double.valueOf(textField_8.getText()),Integer.parseInt(textField_7.getText()),user.getLoginName());
		
			 Random rm = new Random(); 
			 int pross=(int)(Math.random()*900)+100; 
			 String fixLenthString = String.valueOf(pross);  
			 textField_6.setText(textField_4.getText()+fixLenthString);
			 String set=textField_4.getText()+fixLenthString;
			  for(int c1=0;c1<=t1;c1++)
			  {		
				 i1=ConnectDb.InsertRecon(sc[c1],textField_4.getText().trim(),set.trim(),1);
				 i2=ConnectDb.UpdateCom(0, sc[c1]);
			  		      
		      }
			 
			  if(i==1 && i1==1 && i2==1)
			  {
				  JOptionPane.showMessageDialog(getContentPane(), "发票添加成功！");  
				  textField_1.setText("");
				  textField_2.setText("");
				  textField_3.setText("");
				  textField_4.setText("");
				  textField_5.setText("");
				  textField_18.setText("");
				  textField_7.setText("");
				  textField_8.setText("");
				  textField_9.setText("");
				  textField_10.setText("");
		      }
			  else{
				  JOptionPane.showMessageDialog(getContentPane(), "发票添加失败！");
			  }
			  
			
			   
			  
			  
			  
			  
			  
		}

	
	}
}	

