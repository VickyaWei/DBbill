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
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import connect.ConnectDb;
import innerFrame.Suppliers_XiuGaiPanel.doGysSelectAction;
import model.Discount;

public class History_Trade extends JInternalFrame{


	
	private JComboBox comboBox_1;

	private JTable table_1, table_2;

	private JComboBox choice;

	private JTextField textField_2;

	private JScrollPane scrollPane, scrollPane_1;
	 SimpleDateFormat myfmt=new SimpleDateFormat("yyyy-MM-dd");
	
	String BA[] = { "��Ʊ��", "������Ӧ��", "��Ʊ���",  "����", "����ʱ��" };
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
				//System.out.println(dt1.before(d1));
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
					//System.out.println(dt1.before(d1));
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
			
			
			String v;
			v=ConnectDb.getCost(book.getIncode());
			
			s[i][6]=v;	
			
			
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
	public History_Trade() {
		//setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);		
		setIconifiable(true);
		setClosable(true);
		setTitle("�Ѹ���Ʊ��ѯ");
		setBounds(100, 100, 500, 400);
		//setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setVisible(true);

		final JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setPreferredSize(new Dimension(0, 60));
		getContentPane().add(tabbedPane);

		final JPanel panel_1 = new JPanel();
		panel_1.setLayout(new BorderLayout());
		tabbedPane.addTab("δ�ӳ���Ʊ", null, panel_1, null);

		final JPanel panel_1_1 = new JPanel();
		panel_1_1.setBorder(new TitledBorder(null, "��ѡ���ѯ��ʽ", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
		panel_1_1.setPreferredSize(new Dimension(0, 60));
		panel_1.add(panel_1_1, BorderLayout.NORTH);
         choice=new JComboBox();
		String[] array={"��Ӧ�̱��","��ѯȫ��"};
		for(int i=0;i<array.length;i++){
			choice.addItem(array[i]);
			
			
		}
		panel_1_1.add(choice);
		choice.addActionListener(new doGysSelectAction());
		
		textField_2=new JTextField();
		textField_2.setColumns(20);
		panel_1_1.add(textField_2);
		
		final JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "��Ʊ��Ϣ", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
		panel_1.add(panel);

		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setPreferredSize(new Dimension(400, 180));
		panel.add(scrollPane_1);

		final JPanel panel_2_1 = new JPanel();
		panel_2_1.setPreferredSize(new Dimension(0, 50));
		panel_1.add(panel_2_1, BorderLayout.SOUTH);

		final JButton button = new JButton();
		button.setText("��ѯ");
		panel_2_1.add(button);

		 button.addActionListener(new ActionListener(){

				public void actionPerformed(ActionEvent arg0) {
					String name=(String)choice.getSelectedItem();
					Object[][] results;
					if(name.trim().equals("��Ӧ�̱��")){
						results=getselect(ConnectDb.getUnpro2(textField_2.getText().trim()));
											
					}
					else
					{
						results=getselect(ConnectDb.getUnpro3());
						
					}
					
					table_2 = new JTable(results,BA);
					
					scrollPane_1.setViewportView(table_2);
				}
	        	
	        	
	        });
		
		
		
		setVisible(true);
		final JPanel panel_2 = new JPanel();
		tabbedPane.addTab("���ӳ���Ʊ", null, panel_2, null);
         
		 scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(450, 250));
		
		panel_2.add(scrollPane);
		Object[][] results;
		results=getselect1(ConnectDb.getPro());
		
		String BA[] = { "��Ʊ��", "������Ӧ��", "��Ʊ���",  "����", "ԭ����ʱ��", "�ָ���ʱ��", "������" };
		table_1 = new JTable(results,BA);
		table_1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);//�ر����Զ���С
		scrollPane.setViewportView(table_1);
		
		
		}

	//public static void main(String args[])
    {
		//new BookAnalyse();
    }
    
    class doGysSelectAction implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String name=(String)choice.getSelectedItem();
			if(name.equals("��ѯȫ��")){
				textField_2.setEditable(false);
			}
			else{
				textField_2.setEditable(true);
			}
		}
		}
}
