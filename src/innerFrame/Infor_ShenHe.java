package innerFrame;

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
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import connect.ConnectDb;
import model.Check;
import model.Users;
//import model.a;

public class Infor_ShenHe extends JInternalFrame implements ActionListener{
private JTable table,table_1;
//private static ResultSet sel;
public static String audit;
public  static String aa;
private JTextField todaydate;
private JTextField price;
private JTextField number2;
private JTextField Invoice;
private JTextField Detail2;
private JTextField keepMoney;
private JTextField number;
private JTextField Detail;
private JTextField comISBN;

private String[] columnNames = { "供应商名称", "对账参考号" };
DefaultTableModel model = new DefaultTableModel();
private String[] columnNames1 = { "发票号", "收货数量", "折扣后总金额", "详细描述", "上传日期" };
DefaultTableModel model2 = new DefaultTableModel();

private Object[][] getselect(List list) {
	Object[][] s = new Object[list.size()][columnNames.length];
	for (int i = 0; i < list.size(); i++) {
		Check check = (Check) list.get(i);
		s[i][0] = check.getCname();	
		s[i][1] = check.getAuditcode();

	}
	return s;
}



public Infor_ShenHe() {
	super();
	this.audit=audit;
	setIconifiable(true);
	setClosable(true);//窗体开关
	//setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	setTitle("发票审核");	
	setBounds(100, 100, 500, 380);
	setVisible(true);
	

	final JPanel p =new JPanel();
	p.setLayout(new BorderLayout());
	getContentPane().add(p);
 

	
	//主要控件
	final JPanel panel = new JPanel();
	p.add(panel);
    
	//final JScrollPane scrollPane = new JScrollPane();//滚动条
	JScrollPane scrollPane = new JScrollPane();
	scrollPane.setPreferredSize(new Dimension(400, 280));
	panel.add(scrollPane);

	table = new JTable();
	scrollPane.setViewportView(table);//在table中增加滚动条
	model.setColumnIdentifiers(columnNames);//用setColumnIdentifiers替换列（或者说设置列名）
	table.setModel(model);
	p.add(scrollPane, BorderLayout.NORTH);
	Object[][] results=getselect(ConnectDb.selectInfo());
	model.setDataVector(results,columnNames);
	//table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	table.addMouseListener(new TableListener());
	
	
	//分隔
	
//中

			
			
			
//下
	final JPanel panel_2 = new JPanel();
	panel_2.setPreferredSize(new Dimension(0, 100));
	p.add(panel_2, BorderLayout.SOUTH);

	final JPanel panel_7 = new JPanel();
	final GridLayout gridLayout_2 = new GridLayout(0, 2);
	gridLayout_2.setVgap(10);
	panel_7.setLayout(gridLayout_2);
	panel_7.setPreferredSize(new Dimension(280, 20));
	panel_2.add(panel_7);



	final JPanel panel_8 = new JPanel();
	panel_8.setLayout(new FlowLayout());
	panel_8.setPreferredSize(new Dimension(200, 120));
	panel_2.add(panel_8);

	final JButton buttonBorrow = new JButton();
	buttonBorrow.setText("显示详情");
	buttonBorrow.addActionListener(new BorrowActionListener());
	panel_8.add(buttonBorrow);



	setVisible(true);
	
	
	final JPanel panel2 = new JPanel();

	 
	}
 
class TableListener extends MouseAdapter {
	//public void mouseClicked(final MouseEvent e) {
		public void mousePressed(final MouseEvent e) {
		int selRow = table.getSelectedRow();
		audit=table.getValueAt(selRow, 1).toString().trim();
		aa=audit;
	
	
	}
	
}

class BorrowActionListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(aa==null)
		{
			JOptionPane.showMessageDialog(null, "对不起，请选择表格中的某一行", "加载失败", JOptionPane.ERROR_MESSAGE);
			
		}
		else
		{
		new Info_Details();
		}
	} 
	
	
	
	
	
	
}
@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	
}
}

