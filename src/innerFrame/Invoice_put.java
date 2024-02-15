package innerFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JInternalFrame;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import innerFrame.Infor_ShenHe.BorrowActionListener;
import innerFrame.Infor_ShenHe.TableListener;
import connect.ConnectDb;
import frame.BankFrame;
import frame.LoginFrame;
import model.Check;
import model.Users;

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
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Spring;
import javax.swing.table.DefaultTableModel;
public class Invoice_put extends JInternalFrame implements ActionListener{
	private String[] columnNames = { "选择", "订单号", "商品详情", "数量", "总价", "收货日期", "账期" };
	DefaultTableModel model = new DefaultTableModel();
	private JTable table,table_1;
	private String id;
	public static double price;
	public static int num;
	public static String score[]=new String[100000];
	//public static ArrayList list = new ArrayList();
	public static int t;
	public static String Array;
	private Users user = LoginFrame.getUser();
	//public  static int t2;
	private Object[][] getselect2(List list) {
		Object[][] s1 = new Object[list.size()][columnNames.length];
		for (int i = 0; i < list.size(); i++) {
			Check check = (Check) list.get(i);
			s1[i][0] = check.getFinalprice();	
			s1[i][1] = check.getComnum();
		}
		return s1;
	}

	
   
	private Object[][] getselect(List list) {
		//Object[][] s = new Object[list.size()][columnNames.length];
		Object[][] s = new Object[list.size()][7];
		for (int i = 0; i < list.size(); i++) {
			Check check = (Check) list.get(i);
			s[i][1] = check.getComcode();	
			s[i][2] = check.getCombewrite();
			s[i][3] = check.getComnum();
			s[i][4] = check.getFinalprice();
			s[i][5]=check.getGooddate();
			s[i][6]=check.getPaytime();
		}
		return s;
	}
	
	public Invoice_put(){
		super();
		setIconifiable(true);
		setClosable(true);//窗体开关
		//setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setTitle("订单");	
		setBounds(100, 100, 500, 380);
		setVisible(true);
		final JPanel p =new JPanel();
		p.setLayout(new BorderLayout());
		getContentPane().add(p);
	 
		
		final JPanel panel = new JPanel();
		p.add(panel);
	    
		//final JScrollPane scrollPane = new JScrollPane();//滚动条
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(400, 280));
		panel.add(scrollPane);

		table = new JTable();
		//table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane.setViewportView(table);//在table中增加滚动条
		model.setColumnIdentifiers(columnNames);//用setColumnIdentifiers替换列（或者说设置列名）
		table.setModel(model);
		p.add(scrollPane, BorderLayout.NORTH);
		Object[][] results=getselect(ConnectDb.select1(user.getLoginName()));
		model.setDataVector(results,columnNames);
		//table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		//table.addMouseListener(new TableListener());
		TableColumn   aColumn   =   table.getColumnModel().getColumn(0);   
		aColumn.setCellEditor(table.getDefaultEditor(Boolean.class));   
		aColumn.setCellRenderer(table.getDefaultRenderer(Boolean.class));
		//aColumn.setHeaderValue(false);
		int selRow1 = table.getRowCount();
		for(int l=0;l<selRow1;l++)
		{
		   table.setValueAt(false, l, 0);
		}
		
		
		final JPanel panel1 = new JPanel();
		p.add(panel1, BorderLayout.SOUTH);
		final JButton button1 = new JButton();
		button1.setText("开一张发票");
		panel1.add(button1);
		button1.addActionListener(new BorrowActionListener());
		
		final JButton button2 = new JButton();
		button2.setText("开多张发票");
		panel1.add(button2);
		button2.addActionListener(new BorrowActionListener2());
		
		
		
		table.addMouseListener(new MouseAdapter() {
			 public void mouseClicked(MouseEvent e) {
				 int selRow = table.getRowCount();
				 int ii = 0;
				 for (int c = 0; c < selRow; c++ ) { 
					    try{
							id=table.getValueAt(c, 0).toString().trim();
					    } catch (Exception e1) {  
			      
			            }
					  // System.out.println(id);
							if(id=="true")
							{
								ii=ii+1;
							}
						
						}
				// System.out.println(ii);
				 if(ii>1){
					 
					 button2.setEnabled(false);
					 
				 }
				 else
				 {
					 button2.setEnabled(true);
				 }
				 
			 }
			
			
			
			
		});		
		
		
		
		
		
	}
	class BorrowActionListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			int selRow = table.getRowCount();
			t=-1;
			for (int c = 0; c < selRow; c++ ) {
		    try{
				id=table.getValueAt(c, 0).toString().trim();
		    } catch (Exception e) {  
      
            }
		  // System.out.println(id);
				if(id=="true")
				{
					t=t+1;
					score[t]=table.getValueAt(c, 1).toString().trim();
		
				}
			}
			//System.out.println(t);
			price=0;
			num=0;
			for(int r=0;r<=t;r++)
			{
				Object[][] results1=getselect2(ConnectDb.select2(score[r]));
				price=price+(double) results1[0][0];
				num=num+(int) results1[0][1];
			}
			// System.out.println(num);
			 
		   new Invoice_Details();
		
		}	
		
		}

class BorrowActionListener2 implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent arg0) { 
			int selRow = table.getRowCount();
			t=-1;
			for (int c = 0; c < selRow; c++ ) {
		    try{
				id=table.getValueAt(c, 0).toString().trim();
		    } catch (Exception e) {  
      
            }
		  // System.out.println(id);
				if(id=="true")
				{
					t=t+1;
					score[t]=table.getValueAt(c, 1).toString().trim();
		
				}
			}
			//System.out.println(t);
			price=0;
			num=0;
			for(int r=0;r<=t;r++)
			{
				Object[][] results1=getselect2(ConnectDb.select2(score[r]));
				price=price+(double) results1[0][0];
				num=num+(int) results1[0][1];
			}
			new Invoice_Details2();
			
		}

		}
	
	
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
