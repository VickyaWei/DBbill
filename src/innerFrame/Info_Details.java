package innerFrame;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import innerFrame.Infor_ShenHe.BorrowActionListener;
import connect.ConnectDb;
import model.Check;
//import model.a;

//import model.ReaderType;
//import model.BookType;
public class Info_Details extends JFrame implements ActionListener{
	private JTable table2;
	private JTextField todaydate;
	private JTextField price;
	private JTextField number2;
	private JTextField Invoice;
	private JTextField Detail2;
	private JTextField keepMoney;
	private JTextField number;
	private JTextField Detail;
	private JTextField comISBN;
	private  String b;
	public int c1;
	public int c2;
	public int d1=0;
	public int d2=0;
	public double e1=0;
	public double e2=0;
	private String[] columnNames = { "订单号", "收货数量", "折扣后总金额", "详细描述", "收货日期","账期" };
	DefaultTableModel model1 = new DefaultTableModel();
	private String[] columnNames1 = { "发票号", "收货数量", "折扣后总金额", "详细描述", "上传日期" };
	DefaultTableModel model2 = new DefaultTableModel();
	private String str;
	public Date date1;
	public Date max1;
	public Date date2;
	public Date max2;
    private int t;
	//public Date max1 =new Date(2007, 7, 18);
	private Object[][] getselect1(List list) {
	     
		
		
		Object[][] s1 = new Object[list.size()][columnNames.length];
		c1=list.size();
		Check checks=(Check) list.get(0);
		max1=checks.getGooddate();
		for (int i = 0; i < list.size(); i++) {
		Check check = (Check) list.get(i);	
			s1[i][0] = check.getComcode();
			s1[i][1] = check.getComnum();
			s1[i][2] = check.getFinalprice();
			s1[i][3] = check.getCombewrite();
			s1[i][4] = check.getGooddate();
		    s1[i][5] = check.getPaytime();
		    d1=d1+check.getComnum();
		    e1=e1+check.getFinalprice();
		    date1=check.getGooddate();
		    
		    //max1=date1;
		    if(max1.before(date1))
		    {
		    	max1=date1;
		    }
		    
		}
		return s1;
	}
	private Object[][] getselect2(List list) {
		Object[][] s2 = new Object[list.size()][columnNames.length];
		c2=list.size();
		Check checks2=(Check) list.get(0);
		max2=checks2.getIntime();
		for (int i = 0; i < list.size(); i++) {
		Check check1 = (Check) list.get(i);	
			s2[i][0] = check1.getIncode();
			s2[i][1] = check1.getComnum1();
			s2[i][2] = check1.getFinalprice1();
			s2[i][3] = check1.getCombewrite1();
		    s2[i][4] = check1.getIntime();
		    d2=d2+check1.getComnum1();
		    e2=e2+check1.getFinalprice1();
	        date2=check1.getIntime();
		    if(max2.before(date2))
		    {
		    	max2=date2;
		    }
		}
		return s2;
	}
	public Info_Details() {
	//super();

		Date now = new Date(); 
		Calendar cal = Calendar.getInstance(); 
		DateFormat da1 = DateFormat.getDateInstance();
		str = da1.format(now);
		
		
		
	Infor_ShenHe a=new Infor_ShenHe();
	b=a.audit;
	//测试使用可以去掉
	//System.out.println(b);
	
	
	setVisible(true);
	setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	setTitle("审核界面");
	setBounds(100, 100, 600, 500);

	final JPanel panel1 = new JPanel();
	getContentPane().add(panel1, BorderLayout.NORTH);
	panel1.setLayout(new BorderLayout());
	
	final JPanel panel11 = new JPanel();
	panel1.add(panel11, BorderLayout.NORTH);
	panel11.setBorder(new TitledBorder(null, "收货单信息", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
	
	final JScrollPane scrollPane1 = new JScrollPane();//滚动条
	scrollPane1.setPreferredSize(new Dimension(550, 100));
	panel11.add(scrollPane1);
	
	JTable table1 = new JTable();
	//table1.setEnabled(false);
	scrollPane1.setViewportView(table1);
	model1.setColumnIdentifiers(columnNames);
	table1.setModel(model1);
	Object[][] result1=getselect1(ConnectDb.getInfo(b));
	
	
	model1.setDataVector(result1,columnNames);
	//System.out.println(result1);
	
	
	final JPanel panel12 = new JPanel();
	panel1.add(panel12, BorderLayout.SOUTH);
	
	panel12.setBorder(new TitledBorder(null, "发票信息", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
	final JScrollPane scrollPane2 = new JScrollPane();//滚动条
	scrollPane2.setPreferredSize(new Dimension(550, 100));
	panel12.add(scrollPane2);
	
	table2 = new JTable();
	scrollPane2.setViewportView(table2);
	model2.setColumnIdentifiers(columnNames1);
	table2.setModel(model2);
	Object[][] result2=getselect2(ConnectDb.getInfo2(b));
	model2.setDataVector(result2,columnNames1);
	
	
	final JPanel panel1_2 = new JPanel();
	panel1_2.setPreferredSize(new Dimension(0, 120));
	getContentPane().add(panel1_2, BorderLayout.CENTER);
	
	
	final JSplitPane splitPane = new JSplitPane();//一次将两个组件同时显示在两个显示区中
	panel1_2.add(splitPane);
	
	final JPanel panel_5 = new JPanel();
	final GridLayout gridLayout = new GridLayout(0, 2);//创建网格布局管理器对象
	gridLayout.setVgap(10);//设置组件的垂直间距
	panel_5.setLayout(gridLayout);//设置容器采用网格布局管理器
	panel_5.setPreferredSize(new Dimension(230, 110));//大小
	splitPane.setLeftComponent(panel_5);

//分隔左侧
	//final JLabel lab = new JLabel();
	//lab.setText("订单信息");
	//panel_5.add(lab);
	

	final JLabel label = new JLabel();
	label.setText("收货单数量：");
	panel_5.add(label);

	comISBN = new JTextField();
	comISBN.setText(c1+"");
	//readerISBN.addKeyListener(new ISBNListenerlostFocus());
	comISBN.setEditable(false);
	
	panel_5.add(comISBN);

	final JLabel label_1 = new JLabel();
	label_1.setText("收货数量：");
	panel_5.add(label_1);

	Detail = new JTextField();
	Detail.setEditable(false);
	Detail.setText(d1+"");
	panel_5.add(Detail);

	final JLabel label_2 = new JLabel();
	label_2.setText("总金额：");
	panel_5.add(label_2);

	number = new JTextField();
	number.setEditable(false);
	number.setText(e1+"");
	panel_5.add(number);

	final JLabel label_4 = new JLabel();
	label_4.setText("收货日期：");
	panel_5.add(label_4);

	keepMoney = new JTextField();
	keepMoney.setEditable(false);
	keepMoney.setText(max1+"");
	panel_5.add(keepMoney);
	//Icon icon = new ImageIcon("1.gif");

	
	
	//分隔右侧
	final JPanel panel_4 = new JPanel();
	final GridLayout gridLayout_1 = new GridLayout(0, 2);
	gridLayout_1.setVgap(10);//设置组件的垂直间距
	panel_4.setLayout(gridLayout_1);
	panel_4.setPreferredSize(new Dimension(240, 110));//大小
	splitPane.setRightComponent(panel_4);

	//final JLabel lab1 = new JLabel();
	//lab1.setText("发票信息");
	//panel_4.add(lab1);
	
	final JLabel label_6 = new JLabel();
	label_6.setText("发票数量：");
	panel_4.add(label_6);

	Invoice = new JTextField();
	Invoice.setEditable(false);
	Invoice.setText(c2+"");
	//bookName.addKeyListener(new bookISBNListenerlostFocus());
	panel_4.add(Invoice);

	final JLabel label_5 = new JLabel();
	label_5.setText("收货数量：");
	panel_4.add(label_5);

	Detail2 = new JTextField();
	Detail2.setEditable(false);
	Detail2.setText(d2+"");
	panel_4.add(Detail2);

	
	final JLabel label_7 = new JLabel();
	label_7.setText("总金额：");
	panel_4.add(label_7);

	number2 = new JTextField();
	number2.setEditable(false);
	number2.setText(e2+"");
	panel_4.add(number2);

	final JLabel label_8 = new JLabel();
	label_8.setText("上传日期：");
	panel_4.add(label_8);

	price = new JTextField();
	price.setEditable(false);
	price.setText(max2+"");
	panel_4.add(price);

	final JPanel panel2 = new JPanel();
	getContentPane().add(panel2, BorderLayout.SOUTH);
	
	final JButton buttonClear = new JButton();
	buttonClear.setText("审核");
	buttonClear.addActionListener(new ClearActionListener());
	//buttonClear.addActionListener(new ClearActionListener(model));
	panel2.add(buttonClear);
	}
	
	
	class ClearActionListener implements ActionListener {
	
		@Override
		public void actionPerformed(ActionEvent e) {
			int i1=0,i2=0,i3=0;
			int n= Integer.parseInt(Detail.getText());
			int n1=Integer.parseInt(Detail2.getText());
			double m=Double.valueOf(number.getText());
			double m2=Double.valueOf(number2.getText());
		    if(n==n1 && Math.abs(m-m2)<=1)
		    {
		    	i1=ConnectDb.UpdateRec(0,java.sql.Date.valueOf(str),b.trim());
		    	JOptionPane.showMessageDialog(getContentPane(), "审核通过！");  
		    	dispose();
		    	
		    }
		    else
		    {
		    	i2=ConnectDb.UpdateRec(1,java.sql.Date.valueOf(str),b.trim());
		    	t=table2.getRowCount();
		    	for(int c=0;c<t;c++)
		    	{
		    	i3=ConnectDb.UpdateInc(1,table2.getValueAt(c, 0).toString().trim());
		    	}
		    	
		    	JOptionPane.showMessageDialog(getContentPane(), "审核不通过！");  
		    	
		  
		    }
			
			
			
			// TODO Auto-generated method stub
			
		}
		} 
		
	
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	}

