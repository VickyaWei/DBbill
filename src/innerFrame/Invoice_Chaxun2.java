package innerFrame;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;

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
import java.util.List;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import innerFrame.Infor_ShenHe.BorrowActionListener;
import model.Check;
import model.Suppliers;
import model.Users;
import tools.InputKeyListener;
import connect.ConnectDb;
import frame.LoginFrame;
public class Invoice_Chaxun2 extends JInternalFrame{
	private JTextField textField_1,textField_2;
	private JScrollPane scrollPane_2, scrollPane_1;
	private JTable table1,table2;
	private String[] columnNames1 = { "发票号", "收货单号", "对账参考号", "货物名称", "收货单货物数量", "收货单总金额", "收货日期", "发票货物数量", "发票总金额", "开票日期", "账期" };
	DefaultTableModel model1 = new DefaultTableModel();
	private String[] columnNames2 = { "发票号", "收货单号", "对账参考号", "货物名称", "收货单货物数量", "收货单总金额", "收货日期", "发票货物数量", "发票总金额", "开票日期", "账期" };
	DefaultTableModel model2 = new DefaultTableModel();
	private JComboBox choice,choice1;
	private Users user = LoginFrame.getUser();
	private Object[][] getselect1(List list) {
		Object[][] s = new Object[list.size()][columnNames1.length];
		for (int i = 0; i < list.size(); i++) {
			Check check = (Check) list.get(i);
			s[i][0] = check.getIncode();
			s[i][1] = check.getComcode();	
			s[i][2] = check.getAuditcode();
			s[i][3] = check.getCombewrite();
			s[i][4] = check.getComnum();
			s[i][5] = check.getFinalprice();
			s[i][6] = check.getGooddate();
			s[i][7] = check.getComnum1();
			s[i][8] = check.getFinalprice1();
			s[i][9] = check.getIntime();
			s[i][10] = check.getPaytime();
		}
		return s;
	}
	
	
	
	
	public Invoice_Chaxun2(){
		super();
		setIconifiable(true);
		setClosable(true);//窗体开关
		setTitle("信息查询");	
		setBounds(100, 100, 600, 500);
		setVisible(true);
		final JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setPreferredSize(new Dimension(0, 60));
		getContentPane().add(tabbedPane);
		
		//第一页
		final JPanel panel1 =new JPanel();
		panel1.setLayout(new BorderLayout());
		tabbedPane.addTab("已通过审核", null, panel1, null);
		final JPanel panel1_1 = new JPanel();
		panel1_1.setBorder(new TitledBorder(null, "请选择查询项目", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
		panel1_1.setPreferredSize(new Dimension(0, 60));
		panel1.add(panel1_1, BorderLayout.NORTH);
		
		
		choice=new JComboBox();
		String[] array={"订单号","发票号"};
		for(int i=0;i<array.length;i++){
			choice.addItem(array[i]);
		}
		panel1_1.add(choice);
		textField_1 = new JTextField();
		textField_1.setColumns(20);
		panel1_1.add(textField_1);
		
		final JPanel panel1_2 = new JPanel();
		panel1_2.setBorder(new TitledBorder(null, "查询信息", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
		panel1.add(panel1_2);
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setPreferredSize(new Dimension(500, 280));
		panel1_2.add(scrollPane_1);
		table1 = new JTable();
		table1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane_1.setViewportView(table1);
		table1.setModel(model1);
		Object[][] re1=getselect1(ConnectDb.getInfo7(0));
		model1.setDataVector(re1,columnNames1);		
		
		final JPanel panel1_3 = new JPanel();
		panel1_3.setPreferredSize(new Dimension(0, 50));
		panel1.add(panel1_3, BorderLayout.SOUTH);

		final JButton button = new JButton();
		button.setText("查询");
		panel1_3.add(button);
		
		
		 button.addActionListener(new ActionListener(){

				public void actionPerformed(ActionEvent arg0) {
					String name=(String)choice.getSelectedItem();
					if(name.equals("订单号")){
						Object[][] re2=getselect1(ConnectDb.select8(0,textField_1.getText()));
						model1.setDataVector(re2,columnNames1);
						
					}
					else if(name.equals("发票号")){
						Object[][] re3=getselect1(ConnectDb.select7(0,textField_1.getText()));
						model1.setDataVector(re3,columnNames1);
					}
		
				}
		 });
		
		//第二页
		 
			final JPanel panel2 =new JPanel();
			panel2.setLayout(new BorderLayout());
			tabbedPane.addTab("未通过审核", null, panel2, null);
			final JPanel panel2_1 = new JPanel();
			panel2_1.setBorder(new TitledBorder(null, "请选择查询项目", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
			panel2_1.setPreferredSize(new Dimension(0, 60));
			panel2.add(panel2_1, BorderLayout.NORTH);
		 
			choice1=new JComboBox();
			String[] array1={"订单号","发票号"};
			for(int i=0;i<array1.length;i++){
				choice1.addItem(array1[i]);
			}
			panel2_1.add(choice1);
			textField_2 = new JTextField();
			textField_2.setColumns(20);
			panel2_1.add(textField_2);
			
			final JPanel panel2_2 = new JPanel();
			panel2_2.setBorder(new TitledBorder(null, "查询信息", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
			panel2.add(panel2_2);
			
			scrollPane_2 = new JScrollPane();
			scrollPane_2.setPreferredSize(new Dimension(500, 280));
			panel2_2.add(scrollPane_2);
			table2 = new JTable();
			table2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			scrollPane_2.setViewportView(table2);
			table2.setModel(model2);
			Object[][] re14=getselect1(ConnectDb.getInfo7(1));
			model2.setDataVector(re14,columnNames2);
			
			final JPanel panel2_3 = new JPanel();
			panel2_3.setPreferredSize(new Dimension(0, 50));
			panel2.add(panel2_3, BorderLayout.SOUTH);

			final JButton button2 = new JButton();
			button2.setText("查询");
			panel2_3.add(button2);
			
			
			 button2.addActionListener(new ActionListener(){

					public void actionPerformed(ActionEvent arg0) {
						String name1=(String)choice1.getSelectedItem();
						if(name1.equals("订单号")){
							Object[][] re5=getselect1(ConnectDb.select8(1,textField_2.getText()));
							model2.setDataVector(re5,columnNames2);
							
						}
						else if(name1.equals("发票号")){
				            
							Object[][] re6=getselect1(ConnectDb.select7(1,textField_2.getText()));
							model2.setDataVector(re6,columnNames2);
							
						}
			
					}
			 });
			
			
			
		 
		

	}
}
	