package innerFrame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import connect.ConnectDb;
import innerFrame.OrdercheckIFrame.CheckActionListener;
import innerFrame.OrdercheckIFrame.CloseActionListener;
import innerFrame.OrdercheckIFrame.DateListener;
import innerFrame.OrdercheckIFrame.TableListener;
import model.Order;

public class Order_upanddel extends JInternalFrame{

	private JTable table;
	String code;
	String detail;			
	String check;
	String quantity;
	String price;
	String tprice;
	String discount;
	String dprice;
	String goodday;
	String period;
	String upday;
	String supplier;
	String operator;

	private String[] columnNames={ "���", "��Ʒ��ϸ","�Ƿ�����", "�ջ�����","����","�ܼ�","�ۿ۽��","�ۿۺ���","�ջ�����","����","�ϴ�����","������Ӧ��","����Ա"};
	//private Map map=MapPz.getMap();
	
	private Object[][] getFileStates(List list){
		Object[][]results=new Object[list.size()][columnNames.length];
		for(int i=0;i<list.size();i++){
			Order order=(Order)list.get(i);
			results[i][0]=order.getComcode();
			results[i][1]=order.getCombewrite();
			String CheckAndAccepts;
			if(order.getVerification().equals("1"))//1����û������
				CheckAndAccepts="��";
			else
				CheckAndAccepts="��";
			results[i][2]=CheckAndAccepts;			
			results[i][3]=order.getComnum();
			
			results[i][4]=order.getComprice();
			results[i][5]=order.getSprice();
			
			//String bookTypes=String.valueOf(MapPz.getMap().get(order.getTypeId()));
			//results[i][6]=bookTypes;
			
			results[i][6]=order.getDisprice();
			results[i][7]=order.getFinalprice();
			results[i][8]=order.getGooddate();
			results[i][9]=order.getPaytime();
			results[i][10]=order.getuplatedate();
			
			results[i][11]=order.getSupcode();		
			results[i][12]=order.getstaff();
			
			
		}
		return results;
	         		
	}

	/**
	 * Create the frame
	 */
	public Order_upanddel () {
		super();
		setClosable(true);
		setIconifiable(true);
		setAutoscrolls(true);
		setTitle("�ջ����޸���ɾ��");
		setBounds(100, 100, 700, 420);
		

		final JPanel panel = new JPanel();
		getContentPane().add(panel);

		final JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(680, 300));
		panel.add(scrollPane);

		final DefaultTableModel model=new DefaultTableModel(){
			public boolean isCellEditable(int rowIndex, int columnIndex) {  
		        if (columnIndex == 0||columnIndex == 2||columnIndex == 10||columnIndex == 11||columnIndex == 12) {  
		            return false;  
		        }  
		        return true;  
		    }  
		};
		Object[][] results=getFileStates(ConnectDb.getOrder());
		model.setDataVector(results,columnNames);
		table = new JTable();
		table.setModel(model);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);//�ر����Զ���С
		scrollPane.setViewportView(table);
		table.addMouseListener(new TableListener());



		
		setVisible(true);

		final JPanel panel_2 = new JPanel();
		getContentPane().add(panel_2, BorderLayout.SOUTH);

		final JButton buttonCheck = new JButton();
		panel_2.add(buttonCheck);
		buttonCheck.setText("�޸�");
		buttonCheck.addActionListener(new CheckActionListener(model));

		final JButton buttonExit = new JButton();
		panel_2.add(buttonExit);
		buttonExit.addActionListener(new CloseActionListener(model));
		buttonExit.setText("ɾ��");
		
		//
	}

	class TableListener extends MouseAdapter {
		public void mouseClicked(final MouseEvent e) {
			
			int selRow = table.getSelectedRow();
			
			code=table.getValueAt(selRow, 0).toString().trim();
			detail=table.getValueAt(selRow, 1).toString().trim();			
			check=table.getValueAt(selRow, 2).toString().trim();
			quantity=table.getValueAt(selRow, 3).toString().trim();
			price=table.getValueAt(selRow, 4).toString().trim();
			tprice=table.getValueAt(selRow, 5).toString().trim();
			discount=table.getValueAt(selRow, 6).toString().trim();
			dprice=table.getValueAt(selRow, 7).toString().trim();
			goodday=table.getValueAt(selRow, 8).toString().trim();
			period=table.getValueAt(selRow, 9).toString().trim();
			upday=table.getValueAt(selRow, 10).toString().trim();
			supplier=table.getValueAt(selRow, 11).toString().trim();
			operator=table.getValueAt(selRow, 12).toString().trim();
			
			
		}
	}
	class CloseActionListener implements ActionListener {	
		private final DefaultTableModel model;

		CloseActionListener(DefaultTableModel model) {
			this.model = model;
		}
		// ��ӹرհ�ť���¼�������
		public void actionPerformed(final ActionEvent e) {
			if(check.equals("��")){
				ConnectDb.delete("delete from T_Order where Comcode='"+code+"'");
				JOptionPane.showMessageDialog(null, "ɾ���ɹ�");
				Object[][] results=getFileStates(ConnectDb.getOrder());
				model.setDataVector(results,columnNames);
				table.setModel(model);
			}
			else{
				JOptionPane.showMessageDialog(null, "��ѡ����ջ����Ѿ����й����գ�����ϵϵͳ����Աɾ��");
			}
		}
	}
	
	
	
	class CheckActionListener implements ActionListener{
		private final DefaultTableModel model;

		CheckActionListener(DefaultTableModel model) {
			this.model = model;
		}
		public void actionPerformed(final ActionEvent e) {
			int i = 0;
			if(check.equals("��")){

				i=ConnectDb.updateOrder(code,detail,quantity,price,tprice,discount,dprice,goodday,period);
			}
			else{
				JOptionPane.showMessageDialog(null, "��ѡ����ջ����Ѿ����й����գ�����ϵϵͳ����Ա�޸�");
			}
				if(i==1){
					JOptionPane.showMessageDialog(null, "�޸ĳɹ���");
					Object[][] results=getFileStates(ConnectDb.getOrder());
					model.setDataVector(results,columnNames);
					table.setModel(model);
					
				}
			
			else {
				JOptionPane.showMessageDialog(null, "��ѡ����ջ����Ѿ����й����գ���ѡ�������ջ����������");
			}
			}
						
		}
	
	}


