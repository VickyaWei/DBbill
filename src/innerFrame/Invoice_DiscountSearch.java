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
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import connect.ConnectDb;
import frame.LoginFrame;
import innerFrame.Invoice_SupplierChaxun.doGysSelectAction;
import model.Discount;
import model.Users;

public class Invoice_DiscountSearch extends JInternalFrame{



	
	
	private JComboBox comboBox_1;

	private JTable table_1, table_2;

	private JComboBox choice;
	
	private JTextField textField_2;

	private JScrollPane scrollPane, scrollPane_1;
	 SimpleDateFormat myfmt=new SimpleDateFormat("yyyy-MM-dd");
	
	String BA[] = { "发票号", "所属供应商", "手续费","品牌商收益", "银行收益" };
	private Object[][] getselect(List list) {
		Object[][] s = new Object[list.size()][5];
		
		for (int i = 0,j=0; i < list.size()&& j < list.size(); i++,j++) {
			 for (; j < list.size(); j++){
					Discount book2 = (Discount) list.get(j);
				 int a=book2.getpaytime();
				 Date d=ConnectDb.selectMaxtime(book2.getIncode());
				 int day=ConnectDb.getDay(book2.getIncode());
				 Calendar rightNow = Calendar.getInstance();
				  rightNow.setTime(d);		  
				  rightNow.add(Calendar.DAY_OF_YEAR,a-day);
				Date dt1=rightNow.getTime();
				  
					Date d1=new java.util.Date();
					System.out.println(dt1);
					if(dt1.after(d1)){
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
			
			String v1;
			v1=ConnectDb.getCost1(book.getIncode());
			float n=Float.valueOf(v1);
			s[i][2]=v1;	
			float n1=(float) (n*0.06);
			float n2=n-n1;
			s[i][3]=n1;
			s[i][4]=n2;
			
			
			
			
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

	public Invoice_DiscountSearch() {
		//setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);		
		setIconifiable(true);
		setClosable(true);
		setTitle("贴现收益查询");
		setBounds(100, 100, 500, 400);
		//setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setVisible(true);
	


		final JPanel panel_1 = new JPanel();
		panel_1.setLayout(new BorderLayout());
		panel_1.setPreferredSize(new Dimension(0, 390));
		getContentPane().add(panel_1);
		//tabbedPane.addTab("未延长发票", null, panel_1, null);
		

		final JPanel panel_1_1 = new JPanel();
		panel_1_1.setBorder(new TitledBorder(null, "请选择查询方式", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
		panel_1_1.setPreferredSize(new Dimension(0, 60));
		panel_1.add(panel_1_1, BorderLayout.NORTH);
		
		
         choice=new JComboBox();
		String[] array={"供应商编号","查询全部"};
		for(int i=0;i<array.length;i++){
			choice.addItem(array[i]);
			
			
		}
		panel_1_1.add(choice);
		choice.addActionListener(new doGysSelectAction());
		
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
					if(name.trim().equals("供应商编号")){
						results=getselect(ConnectDb.getUndis3(textField_2.getText().trim()));
						
					
					}
					else
					{
						results=getselect(ConnectDb.getUndis4());
					}
					
					table_2 = new JTable(results,BA);
					
					scrollPane_1.setViewportView(table_2);
				}
	        	
	        	
	        });
		
		
		
		setVisible(true);
		
		
		}
	class doGysSelectAction implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String name=(String)choice.getSelectedItem();
			if(name.equals("查询全部")){
				textField_2.setEditable(false);
			}
			else{
				textField_2.setEditable(true);
			}
		}
		}


}
