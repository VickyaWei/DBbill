package innerFrame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import connect.ConnectDb;
import frame.LoginFrame;
import innerFrame.ProlongAudit.CheckActionListener;
import innerFrame.ProlongAudit.CloseActionListener;
import innerFrame.ProlongAudit.TableListener;
import model.Discount;
import model.Users;

public class DiscountAudit extends JInternalFrame{

	private JTable table;
	String ISBN=null;

	private JTextField operator;
	private String[] columnNames={ "��Ʊ��", "������Ӧ��", "��Ʊ���",  "����", "ԭ�տ�ʱ��", "���տ�ʱ��", "�Ƿ�ͨ�����"};
	//private Map map=MapPz.getMap();
	
	
	private Object[][] getselect1(List list) {
		Object[][] s = new Object[list.size()][7];
		
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
			  
			  SimpleDateFormat myfmt=new SimpleDateFormat("yyyy-MM-dd");

				
				//System.out.println(dt1);
			s[i][4] = myfmt.format(dt1);
			Calendar rightNow1 = Calendar.getInstance();
			  rightNow1.setTime(d);		  
			  rightNow1.add(Calendar.DAY_OF_YEAR,60);
			  Date dt2=rightNow1.getTime();	
			//System.out.println(dt2);  			 
			s[i][5] = myfmt.format(dt2);
			
			String CheckAndAccepts;
			String v;
			v=ConnectDb.getVerification1(book.getIncode());
			if(v.trim().equals("1"))//1����û������
				CheckAndAccepts="��";
			else
				CheckAndAccepts="��";
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

	/**
	 * Create the frame
	 */
	public DiscountAudit() {
		super();
		setClosable(true);
		setIconifiable(true);
		setAutoscrolls(true);
		setTitle("��Ʊ�������");
		setBounds(100, 100, 700, 420);
		

		final JPanel panel = new JPanel();
		getContentPane().add(panel);
		
		

		final JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(680, 370));
		panel.add(scrollPane);

		final DefaultTableModel model=new DefaultTableModel();
		Object[][] results=getselect1(ConnectDb.getDis1());
		model.setDataVector(results,columnNames);
		table = new JTable();
		table.setModel(model);
		//table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);//�ر����Զ���С
		scrollPane.setViewportView(table);
		table.addMouseListener(new TableListener());
		
		setVisible(true);

		final JPanel panel_2 = new JPanel();
		getContentPane().add(panel_2, BorderLayout.SOUTH);

		final JButton buttonCheck = new JButton();
		panel_2.add(buttonCheck);
		buttonCheck.setText("���");
		buttonCheck.addActionListener(new CheckActionListener(model));

		final JButton buttonExit = new JButton();
		panel_2.add(buttonExit);
		buttonExit.addActionListener(new CloseActionListener());
		buttonExit.setText("�˳�");
		
		//
	}
	
	class TableListener extends MouseAdapter {
		public void mouseClicked(final MouseEvent e) {
			
			int selRow = table.getSelectedRow();
			
			ISBN=table.getValueAt(selRow, 0).toString().trim();
			
		}
	}
	class CloseActionListener implements ActionListener {			// ��ӹرհ�ť���¼�������
		public void actionPerformed(final ActionEvent e) {
			doDefaultCloseAction();
		}
	}
	
	class CheckActionListener implements ActionListener{
		private final DefaultTableModel model;

		CheckActionListener(DefaultTableModel model) {
			this.model = model;
		}
		public void actionPerformed(final ActionEvent e) {
			
				
				int i=ConnectDb.UpdateDiscount(ISBN);
				
				if(i==1){
					JOptionPane.showMessageDialog(null, "��˳ɹ���");
			Object[][] results=getselect1(ConnectDb.getDis1());
					
					model.setDataVector(results,columnNames);
					table.setModel(model);					
				}
			
			else {
				JOptionPane.showMessageDialog(null, "��ѡ��ķ�Ʊ�Ѿ����й���ˣ���ѡ��������Ʊ�������");
			}
			
		}
	}

}
