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

import connect.ConnectDb;
import model.Check;


public class Infor_passed extends JInternalFrame {
	private JComboBox choice,choice1,choice2;
	private JTextField textField_1,textField_2,textField_3;
	private JScrollPane scrollPane_2, scrollPane_1,scrollPane_3;
	private JTable table1,table2;
	private String[] columnNames1 = { "供应商编号", "供应商名称", "发票号", "订单号", "对账参考号", "货物数量", "总金额", "详细信息", "收货日期", "账期" };
	DefaultTableModel model1 = new DefaultTableModel();
	private String[] columnNames2 = { "供应商编号", "供应商名称", "发票号", "订单号", "对账参考号", "货物数量", "总金额", "详细信息", "收货日期", "账期" };
	DefaultTableModel model2 = new DefaultTableModel();
	private Object[][] getselect1(List list) {
		Object[][] s = new Object[list.size()][columnNames1.length];
		for (int i = 0; i < list.size(); i++) {
			Check check = (Check) list.get(i);
			s[i][0] = check.getSupcode();
			s[i][1] = check.getCname();	
			s[i][2] = check.getIncode();
			s[i][3] = check.getComcode();
			s[i][4] = check.getAuditcode();
			s[i][5] = check.getComnum();
			s[i][6] = check.getFinalprice();
			s[i][7] = check.getCombewrite();
			s[i][8] = check.getGooddate();
			s[i][9] = check.getPaytime();
		}
		return s;
	}

	public Infor_passed() {
		super();
		setIconifiable(true);
		setClosable(true);//窗体开关
		setTitle("已通过审核信息");	
		setBounds(100, 100, 500, 400);
		setVisible(true);
		final JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setPreferredSize(new Dimension(0, 60));
		getContentPane().add(tabbedPane);
		//第一页
		final JPanel panel1 =new JPanel();
		panel1.setLayout(new BorderLayout());
		tabbedPane.addTab("未申请贴现", null, panel1, null);
		//final JPanel panel1_1 = new JPanel();
		//panel1.add(panel1_1);
		final JPanel panel_11 = new JPanel();
		panel_11.setBorder(new TitledBorder(null, "请选择查询项目", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
		panel_11.setPreferredSize(new Dimension(0, 60));
		panel1.add(panel_11, BorderLayout.NORTH);
		choice=new JComboBox();
		String[] array={"供应商名称","发票号"};
		for(int i=0;i<array.length;i++){
			choice.addItem(array[i]);
		}
		
		
		panel_11.add(choice);
		textField_1 = new JTextField();
		textField_1.setColumns(20);
		panel_11.add(textField_1);
		
		final JPanel panel_22 = new JPanel();
		panel_22.setBorder(new TitledBorder(null, "查询信息", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
		panel1.add(panel_22);
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setPreferredSize(new Dimension(400, 150));
		panel_22.add(scrollPane_1);
		table1 = new JTable();
		table1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane_1.setViewportView(table1);
		table1.setModel(model1);
		Object[][] re1=getselect1(ConnectDb.getInfo3(0));
		model1.setDataVector(re1,columnNames1);
		
		
		
		
		final JPanel panel_2_1 = new JPanel();
		panel_2_1.setPreferredSize(new Dimension(0, 50));
		panel1.add(panel_2_1, BorderLayout.SOUTH);

		final JButton button = new JButton();
		button.setText("查询");
		panel_2_1.add(button);
		
		 button.addActionListener(new ActionListener(){

				public void actionPerformed(ActionEvent arg0) {
					String name=(String)choice.getSelectedItem();
					if(name.equals("供应商名称")){
						
						//Object[][] re1=getselect(ConnectDb.selectBook2(textField_1.getText()),String.valueOf(ConnectDb.selectBook3(textField_1.getText())));
						//table_2 = new JTable(re1,Book);
						Object[][] re2=getselect1(ConnectDb.getInfo4(0,textField_1.getText()));
						model1.setDataVector(re2,columnNames1);
						//scrollPane_1.setViewportView(table_2);
					}
					else if(name.equals("发票号")){
						Object[][] re3=getselect1(ConnectDb.getInfo5(0,textField_1.getText()));
						model1.setDataVector(re3,columnNames1);
						
					}
				}
	        	
	        	
	        });
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		//第二页
		final JPanel panel2 =new JPanel();
		panel2.setLayout(new BorderLayout());
		tabbedPane.addTab("已申请贴现", null, panel2, null);
		//final JPanel panel2_1 = new JPanel();
		//panel2.add(panel2_1);
		//从这里开始改
	
		final JPanel panel2_1 = new JPanel();
		panel2_1.setBorder(new TitledBorder(null, "请选择查询项目", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
		panel2_1.setPreferredSize(new Dimension(0, 60));
		panel2.add(panel2_1, BorderLayout.NORTH);
		choice1=new JComboBox();
		String[] array1={"供应商名称","发票号"};
		for(int i=0;i<array1.length;i++){
			choice1.addItem(array[i]);
		}
		
		
		panel2_1.add(choice1);
		textField_2 = new JTextField();
		textField_2.setColumns(20);
		panel2_1.add(textField_2);
		
		final JPanel panel2_2 = new JPanel();
		panel2_2.setBorder(new TitledBorder(null, "查询信息", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
		panel2.add(panel2_2);
		scrollPane_2 = new JScrollPane();
		scrollPane_2.setPreferredSize(new Dimension(400, 150));
		panel2_2.add(scrollPane_2);
		
	    table2 = new JTable();
		table2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane_2.setViewportView(table2);
		table2.setModel(model2);
		Object[][] re4=getselect1(ConnectDb.getInfo3(1));
		model2.setDataVector(re4,columnNames2);
		
		
		
		
		
		
		final JPanel panel2_3 = new JPanel();
		panel2_3.setPreferredSize(new Dimension(0, 50));
		panel2.add(panel2_3, BorderLayout.SOUTH);

		final JButton button1 = new JButton();
		button1.setText("查询");
		panel2_3.add(button1);
		
		 button1.addActionListener(new ActionListener(){

				public void actionPerformed(ActionEvent arg0) {
					String name1=(String)choice1.getSelectedItem();
					if(name1.equals("供应商名称")){
						
						//Object[][] re1=getselect(ConnectDb.selectBook2(textField_1.getText()),String.valueOf(ConnectDb.selectBook3(textField_1.getText())));
						//table_2 = new JTable(re1,Book);
						Object[][] re5=getselect1(ConnectDb.getInfo4(0,textField_1.getText()));
						model2.setDataVector(re5,columnNames2);
						//scrollPane_1.setViewportView(table_2);
					}
					else if(name1.equals("发票号")){
						Object[][] re6=getselect1(ConnectDb.getInfo5(0,textField_1.getText()));
						model2.setDataVector(re6,columnNames2);
						
					}
				}
	        	
	        	
	        });
		

		
		//第三页
		final JPanel panel3 =new JPanel();
		panel3.setLayout(new BorderLayout());
		tabbedPane.addTab("延长账期信息", null, panel3, null);
		//final JPanel panel3_1 = new JPanel();
		//panel3.add(panel3_1);


		
		
		
		final JPanel panel3_1 = new JPanel();
		panel3_1.setBorder(new TitledBorder(null, "请选择查询项目", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
		panel3_1.setPreferredSize(new Dimension(0, 60));
		panel3.add(panel3_1, BorderLayout.NORTH);
		choice2=new JComboBox();
		String[] array2={"供应商名称","发票号"};
		for(int i=0;i<array2.length;i++){
			choice2.addItem(array[i]);
		}
		
		
		panel3_1.add(choice2);
		textField_3 = new JTextField();
		textField_3.setColumns(20);
		panel3_1.add(textField_3);
		
		final JPanel panel3_2 = new JPanel();
		panel3_2.setBorder(new TitledBorder(null, "查询信息", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
		panel3.add(panel3_2);
		scrollPane_3 = new JScrollPane();
		scrollPane_3.setPreferredSize(new Dimension(400, 150));
		panel3_2.add(scrollPane_3);
		
		
		final JPanel panel3_3 = new JPanel();
		panel3_3.setPreferredSize(new Dimension(0, 50));
		panel3.add(panel3_3, BorderLayout.SOUTH);

		final JButton button2 = new JButton();
		button2.setText("查询");
		panel3_3.add(button2);
	 
		
		
		
		
		
		
		
	}
	
	
	
	
}
