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

	private String[] columnNames={ "编号", "商品明细","是否验收", "收货数量","单价","总价","折扣金额","折扣后金额","收货日期","账期","上传日期","所属供应商","操作员"};
	//private Map map=MapPz.getMap();
	
	private Object[][] getFileStates(List list){
		Object[][]results=new Object[list.size()][columnNames.length];
		for(int i=0;i<list.size();i++){
			Order order=(Order)list.get(i);
			results[i][0]=order.getComcode();
			results[i][1]=order.getCombewrite();
			String CheckAndAccepts;
			if(order.getVerification().equals("1"))//1代表没有验收
				CheckAndAccepts="否";
			else
				CheckAndAccepts="是";
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
		setTitle("收货单修改与删除");
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
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);//关闭列自动变小
		scrollPane.setViewportView(table);
		table.addMouseListener(new TableListener());



		
		setVisible(true);

		final JPanel panel_2 = new JPanel();
		getContentPane().add(panel_2, BorderLayout.SOUTH);

		final JButton buttonCheck = new JButton();
		panel_2.add(buttonCheck);
		buttonCheck.setText("修改");
		buttonCheck.addActionListener(new CheckActionListener(model));

		final JButton buttonExit = new JButton();
		panel_2.add(buttonExit);
		buttonExit.addActionListener(new CloseActionListener(model));
		buttonExit.setText("删除");
		
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
		// 添加关闭按钮的事件监听器
		public void actionPerformed(final ActionEvent e) {
			if(check.equals("否")){
				ConnectDb.delete("delete from T_Order where Comcode='"+code+"'");
				JOptionPane.showMessageDialog(null, "删除成功");
				Object[][] results=getFileStates(ConnectDb.getOrder());
				model.setDataVector(results,columnNames);
				table.setModel(model);
			}
			else{
				JOptionPane.showMessageDialog(null, "您选择的收货单已经进行过验收，请联系系统管理员删除");
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
			if(check.equals("否")){

				i=ConnectDb.updateOrder(code,detail,quantity,price,tprice,discount,dprice,goodday,period);
			}
			else{
				JOptionPane.showMessageDialog(null, "您选择的收货单已经进行过验收，请联系系统管理员修改");
			}
				if(i==1){
					JOptionPane.showMessageDialog(null, "修改成功！");
					Object[][] results=getFileStates(ConnectDb.getOrder());
					model.setDataVector(results,columnNames);
					table.setModel(model);
					
				}
			
			else {
				JOptionPane.showMessageDialog(null, "您选择的收货单已经进行过验收，请选择其他收货单进行审核");
			}
			}
						
		}
	
	}


