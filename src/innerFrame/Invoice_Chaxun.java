package innerFrame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import connect.ConnectDb;
import model.Discount;


public class Invoice_Chaxun extends JInternalFrame{


	
	private JComboBox comboBox_1;

	private JTable table_1, table_2,table_3;

	private JComboBox choice;

	private JTextField textField_2;

	private JScrollPane scrollPane, scrollPane_1;
	 SimpleDateFormat myfmt=new SimpleDateFormat("yyyy-MM-dd");
	
	String BA[] = { "发票号", "所属供应商", "发票金额",  "账期", "应付款时间" };
	private Object[][] getselect(List list) {
		
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
				System.out.println(dt1.before(d1));
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
	private Object[][] getselect1(List list) {
		Object[][] s = new Object[list.size()][7];
		
		for (int i = 0,j=0; i < list.size()&& j < list.size(); i++,j++) {
			 for (; j < list.size(); j++){
					Discount book2 = (Discount) list.get(j);
				 int a=book2.getpaytime();
				 Date d=ConnectDb.selectMaxtime(book2.getIncode());
				 Calendar rightNow = Calendar.getInstance();
				  rightNow.setTime(d);		  
				  rightNow.add(Calendar.DAY_OF_YEAR,60);
				Date dt1=rightNow.getTime();
				  
					Date d1=new java.util.Date();
					System.out.println(dt1.before(d1));
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
			  
			  SimpleDateFormat myfmt=new SimpleDateFormat("yyyy-MM-dd");

				
				//System.out.println(dt1);
			s[i][4] = myfmt.format(dt1);
			Calendar rightNow1 = Calendar.getInstance();
			  rightNow1.setTime(d);		  
			  rightNow1.add(Calendar.DAY_OF_YEAR,60);
			  Date dt2=rightNow1.getTime();	
			  			 
			s[i][5] = myfmt.format(dt2);
			
			String CheckAndAccepts;
			String v;
			v=ConnectDb.getVerification(book.getIncode());
			if(v.trim().equals("1"))//1代表没有验收
				CheckAndAccepts="否";
			else
				CheckAndAccepts="是";
			s[i][6]=CheckAndAccepts;	
			
			
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
	public Invoice_Chaxun() {
		//setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);		
		setIconifiable(true);
		setClosable(true);
		setTitle("未付发票查询");
		setBounds(100, 100, 500, 400);
		//setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setVisible(true);

		final JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setPreferredSize(new Dimension(0, 60));
		getContentPane().add(tabbedPane);

		final JPanel panel_1 = new JPanel();
		panel_1.setLayout(new BorderLayout());
		tabbedPane.addTab("未延长发票", null, panel_1, null);

		final JPanel panel_1_1 = new JPanel();
		panel_1_1.setBorder(new TitledBorder(null, "请选择查询方式", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
		panel_1_1.setPreferredSize(new Dimension(0, 60));
		panel_1.add(panel_1_1, BorderLayout.NORTH);
         choice=new JComboBox();
		String[] array={"发票号","供应商编号"};
		for(int i=0;i<array.length;i++){
			choice.addItem(array[i]);
			
			
		}
		panel_1_1.add(choice);
		
		textField_2=new JTextField();
		textField_2.setColumns(20);
		panel_1_1.add(textField_2);
		
		final JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "发票信息", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
		panel_1.add(panel);

		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setPreferredSize(new Dimension(400, 180));
		panel.add(scrollPane_1);

		final JPanel panel_2_1 = new JPanel();
		panel_2_1.setPreferredSize(new Dimension(0, 50));
		panel_1.add(panel_2_1, BorderLayout.SOUTH);

		final JButton button = new JButton();
		button.setText("查询");
		panel_2_1.add(button);

		 button.addActionListener(new ActionListener(){

				public void actionPerformed(ActionEvent arg0) {
					String name=(String)choice.getSelectedItem();
					Object[][] results;
					if(name.trim().equals("发票号")){
						results=getselect(ConnectDb.getUnpro1(textField_2.getText().trim()));
						
					
					}
					else
					{
						results=getselect(ConnectDb.getUnpro2(textField_2.getText().trim()));
					}
					
					table_2 = new JTable(results,BA);
					
					scrollPane_1.setViewportView(table_2);
				}
	        	
	        	
	        });
		
		
		
		setVisible(true);
		
		final JPanel panel_2 = new JPanel();
		tabbedPane.addTab("已延长发票", null, panel_2, null);
         
		 scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(450, 250));
		
		panel_2.add(scrollPane);
		Object[][] results;
		results=getselect1(ConnectDb.getPro());
		
		String BA[] = { "发票号", "所属供应商", "发票金额",  "账期", "原付款时间", "现付款时间", "是否通过审核" };
		table_1 = new JTable(results,BA);
		table_1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);//关闭列自动变小
		scrollPane.setViewportView(table_1);
		
		final JPanel panel_3 = new JPanel();
		tabbedPane.addTab("等待延长审核", null, panel_3, null);
         
		 scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(450, 250));
		
		panel_3.add(scrollPane);
		Object[][] results1;
		results1=getselect1(ConnectDb.getPro2());
		
		String BA1[] = { "发票号", "所属供应商", "发票金额",  "账期", "原付款时间", "现付款时间", "是否通过审核" };
		table_3 = new JTable(results1,BA1);
		table_3.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);//关闭列自动变小
		scrollPane.setViewportView(table_3);
		}

	//public static void main(String args[])
    {
		//new BookAnalyse();
    }
    

}
