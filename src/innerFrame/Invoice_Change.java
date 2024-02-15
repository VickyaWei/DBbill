package innerFrame;

import java.awt.Dimension;

import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import innerFrame.Invoice_put.BorrowActionListener;
import connect.ConnectDb;
import frame.LoginFrame;
import model.Check;
import model.Users;
public class Invoice_Change extends JInternalFrame {
	private JTextField j1;
	private JTextField j2;
	private JTextField j3;
	private JTextField j4,j5,j6,j7,j8;
	private String a2,a3,a4,a5,a6,a7;
	public String a1;
	private String b1,b2,b3,b4,b5,b6,b7;
	private String c2,c3,c4,c5,c6,c7;
	private String d1,d2,d3,d4,d5,d6,d7;
	public String c1;
	private JTextField t1,t2,t3,t4,t5,t6,t7,t8;
	//private Date a5;
	//private int a6;
	//private Double a7,a8;
	
	private JScrollPane scrollPane_2, scrollPane_1;
	private JTable table1,table2;
	private String[] columnNames1 = { "发票编号", "开票日期", "货物名称", "单价", "货物数量", "总金额", "开票人" };
	DefaultTableModel model1 = new DefaultTableModel();
	private String[] columnNames2 = { "发票编号", "开票日期", "货物名称", "单价", "货物数量", "总金额", "开票人" };
	DefaultTableModel model2 = new DefaultTableModel();
	private Users user = LoginFrame.getUser();
	
	
	private Object[][] getselect1(List list) {
		Object[][] s = new Object[list.size()][columnNames1.length];
		for (int i = 0; i < list.size(); i++) {
			Check check = (Check) list.get(i);
			s[i][0] = check.getIncode();
			s[i][1] = check.getIntime();	
			s[i][2] = check.getCombewrite1();
			s[i][3] = check.getComnum1();
			s[i][4] = check.getPrice();
			s[i][5] = check.getFinalprice1();
			s[i][6] = check.getdrawer();
	
		}
		return s;
	}
	
	public Invoice_Change() {
		super();
		setIconifiable(true);
		setClosable(true);//窗体开关
		setTitle("已通过审核信息");	
		setBounds(100, 100, 650, 450);
		setVisible(true);
		
		final JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setPreferredSize(new Dimension(0, 60));
		getContentPane().add(tabbedPane);
		
		//第一页
		final JPanel panel1 =new JPanel();
		panel1.setLayout(new BorderLayout());
		tabbedPane.addTab("审核前修改", null, panel1, null);
		
		final JPanel panel1_1 = new JPanel();
		panel1_1.setBorder(new TitledBorder(null, "发票信息", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
		panel1_1.setPreferredSize(new Dimension(0, 200));
		panel1.add(panel1_1, BorderLayout.NORTH);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setPreferredSize(new Dimension(500, 150));
		table1 = new JTable();
		table1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane_1.setViewportView(table1);
		table1.setModel(model1);
		Object[][] re1=getselect1(ConnectDb.select3(1,0,user.getLoginName()));
		model1.setDataVector(re1,columnNames1);
		panel1_1.add(scrollPane_1);
		
		
		final JPanel panel1_2 = new JPanel();
		panel1_2.setBorder(new TitledBorder(null, "修改信息", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
		
		final GridLayout gridLayout = new GridLayout(0, 4);
		gridLayout.setVgap(5);
		panel1_2.setLayout(gridLayout);
		panel1_2.setPreferredSize(new Dimension(450, 150));
		panel1.add(panel1_2);

		final JLabel label_1 = new JLabel();
		label_1.setText("发票号：");
		panel1_2.add(label_1);
		
		
		j1 = new JTextField();
		panel1_2.add(j1);
		//j1.enable(false);
		
		
		final JLabel label_2 = new JLabel();
		label_2.setText("开票日期：");
		panel1_2.add(label_2);

		j2 = new JTextField();
		panel1_2.add(j2);
		j2.setEnabled(false);
		
		
		final JLabel label_3 = new JLabel();
		label_3.setText("货物名称：");
		panel1_2.add(label_3);

		j3 = new JTextField();
		panel1_2.add(j3);
		
		final JLabel label_4 = new JLabel();
		label_4.setText("货物数量：");
		panel1_2.add(label_4);

		j4 = new JTextField();
		panel1_2.add(j4);
		
		final JLabel label_5 = new JLabel();
		label_5.setText("货物单价：");
		panel1_2.add(label_5);

		j5 = new JTextField();
		panel1_2.add(j5);
		
		final JLabel label_6 = new JLabel();
		label_6.setText("总金额：");
		panel1_2.add(label_6);

		j6 = new JTextField();
		panel1_2.add(j6);
		
		final JLabel label_7 = new JLabel();
		label_7.setText("开票人：");
		panel1_2.add(label_7);

		j7 = new JTextField();
		panel1_2.add(j7);
		j7.setEnabled(false);

		final JLabel label_8 = new JLabel();
		label_8.setText("       ");
		panel1_2.add(label_8);

		final JButton button1 = new JButton();
		button1.setText("修改");
		panel1_2.add(button1);
		button1.addActionListener(new BorrowActionListener());
		
		table1.addMouseListener(new MouseAdapter() {
			 public void mousePressed(MouseEvent e) {
				 int selRow = table1.getSelectedRow();
				 a1=table1.getValueAt(selRow, 0).toString().trim();
				 a2=table1.getValueAt(selRow, 1).toString().trim();
				 a3=table1.getValueAt(selRow, 2).toString().trim();
				 a4=table1.getValueAt(selRow, 3).toString().trim();
				 a5=table1.getValueAt(selRow, 4).toString().trim();
				 a6=table1.getValueAt(selRow, 5).toString().trim();
				 a7=table1.getValueAt(selRow, 6).toString().trim();
				 
				 j1.setText(a1);
				 j2.setText(a2);
				 j3.setText(a3);
				 j4.setText(a4);
				 j5.setText(a5);
				 j6.setText(a6);
				 j7.setText(a7);
				 
				 
			 }
		
		});
		
		
		
		
		
		//第二页
		final JPanel panel2 =new JPanel();
		panel2.setLayout(new BorderLayout());
		tabbedPane.addTab("审核错误修改", null, panel2, null);
		
		final JPanel panel2_1 = new JPanel();
		panel2_1.setBorder(new TitledBorder(null, "发票信息", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
		panel2_1.setPreferredSize(new Dimension(0, 200));
		panel2.add(panel2_1, BorderLayout.NORTH);
		
		scrollPane_2 = new JScrollPane();
		scrollPane_2.setPreferredSize(new Dimension(500, 150));
		table2 = new JTable();
		table2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane_2.setViewportView(table2);
		table2.setModel(model2);
		Object[][] re12=getselect1(ConnectDb.select3(1,1,user.getLoginName()));
		model2.setDataVector(re12,columnNames2);
		panel2_1.add(scrollPane_2);
		
		final JPanel panel2_2 = new JPanel();
		panel2_2.setBorder(new TitledBorder(null, "修改信息", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
		
		final GridLayout gridLayout2 = new GridLayout(0, 4);
		gridLayout2.setVgap(5);
		panel2_2.setLayout(gridLayout2);
		panel2_2.setPreferredSize(new Dimension(450, 150));
		panel2.add(panel2_2);
		
		
		final JLabel label_9 = new JLabel();
		label_9.setText("发票号：");
		panel2_2.add(label_9);
		t1 = new JTextField();
		panel2_2.add(t1);
		//j1.enable(false);
		
		
		final JLabel label_10 = new JLabel();
		label_10.setText("开票日期：");
		panel2_2.add(label_10);

		t2 = new JTextField();
		panel2_2.add(t2);
		t2.setEnabled(false);
		
		
		final JLabel label_11 = new JLabel();
		label_11.setText("货物名称：");
		panel2_2.add(label_11);

		t3 = new JTextField();
		panel2_2.add(t3);
		
		final JLabel label_12 = new JLabel();
		label_12.setText("货物数量：");
		panel2_2.add(label_12);

		t4 = new JTextField();
		panel2_2.add(t4);
		
		final JLabel label_13 = new JLabel();
		label_13.setText("货物单价：");
		panel2_2.add(label_13);

		t5 = new JTextField();
		panel2_2.add(t5);
		
		final JLabel label_14 = new JLabel();
		label_14.setText("总金额：");
		panel2_2.add(label_14);

		t6 = new JTextField();
		panel2_2.add(t6);
		
		final JLabel label_15 = new JLabel();
		label_15.setText("开票人：");
		panel2_2.add(label_15);

		t7 = new JTextField();
		panel2_2.add(t7);
		t7.setEnabled(false);

		final JLabel label_16 = new JLabel();
		label_16.setText("       ");
		panel2_2.add(label_16);

		final JButton button2 = new JButton();
		button2.setText("修改");
		panel2_2.add(button2);
		button2.addActionListener(new BorrowActionListener2());
		
		table2.addMouseListener(new MouseAdapter() {
			 public void mousePressed(MouseEvent e) {
				 int selRow = table2.getSelectedRow();
				 c1=table2.getValueAt(selRow, 0).toString().trim();
				 c2=table2.getValueAt(selRow, 1).toString().trim();
				 c3=table2.getValueAt(selRow, 2).toString().trim();
				 c4=table2.getValueAt(selRow, 3).toString().trim();
				 c5=table2.getValueAt(selRow, 4).toString().trim();
				 c6=table2.getValueAt(selRow, 5).toString().trim();
				 c7=table2.getValueAt(selRow, 6).toString().trim();
				 //System.out.println(c1);
				 t1.setText(c1);
				 t2.setText(c2);
				 t3.setText(c3);
				 t4.setText(c4);
				 t5.setText(c5);
				 t6.setText(c6);
				 t7.setText(c7);
				 
				 
			 }
		
		});
		
	
		
	}
	
class BorrowActionListener implements ActionListener {
		int i=0;
		@Override
		public void actionPerformed(ActionEvent arg0) {
		  b1=j1.getText().toString();
		 // b2=j2.getText();
		  b3=j3.getText().toString();
		  b4=j4.getText().toString();
		  b5=j5.getText().toString();
		  b6=j6.getText().toString();
		  //System.out.println(b1);
		 //b7=j7.getText();
		    if(j1.getText().length()==0){
				JOptionPane.showMessageDialog(null, "发票号文本框不可为空");
				return;
			}
		    if(j3.getText().length()==0){
				JOptionPane.showMessageDialog(null, "货物名称文本框不可为空");
				return;
			}
		    if(j4.getText().length()==0){
				JOptionPane.showMessageDialog(null, "货物数量文本框不可为空");
				return;
			}
		    if(j5.getText().length()==0){
				JOptionPane.showMessageDialog(null, "货物单价文本框不可为空");
				return;
			}
		    if(j6.getText().length()==0){
				JOptionPane.showMessageDialog(null, "总金额文本框不可为空");
				return;
			}
		    
		    
		    
		  i=ConnectDb.UpdateInco(a1.trim(),b1.trim(),b3.trim(),Integer.parseInt(b4),Double.valueOf(b5),Double.valueOf(b6),0);		  
	      Object[][] re1=getselect1(ConnectDb.select3(1,0,user.getLoginName()));
		  model1.setDataVector(re1,columnNames1);
		  
		
		  if(i==1)
		  {
			  
			   JOptionPane.showMessageDialog(getContentPane(), "修改成功！"); 
			   j1.setText("");
			   j2.setText("");
			   j3.setText("");
			   j4.setText("");
			   j5.setText("");
			   j6.setText("");
			   j7.setText("");
		  }
		  else
		  {
			  JOptionPane.showMessageDialog(getContentPane(), "修改不成功！发生错误"); 
		  }
		
			
			
		}	
		
		}
	
class BorrowActionListener2 implements ActionListener {
	int i=0;
	@Override
	public void actionPerformed(ActionEvent arg0) {
		  d1=t1.getText().toString();
		  d3=t3.getText().toString();
		  d4=t4.getText().toString();
		  d5=t5.getText().toString();
		  d6=t6.getText().toString();
	      System.out.println(d1);
	 //b7=j7.getText();
	    if(t1.getText().length()==0){
			JOptionPane.showMessageDialog(null, "发票号文本框不可为空");
			return;
		}
	    if(t3.getText().length()==0){
			JOptionPane.showMessageDialog(null, "货物名称文本框不可为空");
			return;
		}
	    if(t4.getText().length()==0){
			JOptionPane.showMessageDialog(null, "货物数量文本框不可为空");
			return;
		}
	    if(t5.getText().length()==0){
			JOptionPane.showMessageDialog(null, "货物单价文本框不可为空");
			return;
		}
	    if(t6.getText().length()==0){
			JOptionPane.showMessageDialog(null, "总金额文本框不可为空");
			return;
		}
	    
	    
	    
	  i=ConnectDb.UpdateInco(c1.trim(),d1.trim(),d3.trim(),Integer.parseInt(d4),Double.valueOf(d5),Double.valueOf(d6),0);		  
	  Object[][] re12=getselect1(ConnectDb.select3(1,1,user.getLoginName()));
	  model2.setDataVector(re12,columnNames2);
	  if(i==1)
	  {
		  JOptionPane.showMessageDialog(getContentPane(), "修改成功！"); 
		   t1.setText("");
		   t2.setText("");
		   t3.setText("");
		   t4.setText("");
		   t5.setText("");
		   t6.setText("");
		   t7.setText("");
	  }
	  else
	  {
		  JOptionPane.showMessageDialog(getContentPane(), "修改不成功！发生错误"); 
	  }
	  
	  
	}	
	
	}
	
	
	
}
