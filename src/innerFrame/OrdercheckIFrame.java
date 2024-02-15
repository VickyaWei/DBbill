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
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
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
import model.Order;


public class OrdercheckIFrame extends JInternalFrame{
	private JTextField bookType;
	private JTextField orderPrice;
	private JTextField zk;
	private JTable table;
	private ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField price;
	private JTextField operator;
	private JTextField orderNumber;
	private JTextField ISBN;
	private JFormattedTextField orderDate;
	
	
	JRadioButton radioButton2;
	JRadioButton radioButton1;
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
	public OrdercheckIFrame() {
		super();
		setClosable(true);
		setIconifiable(true);
		setAutoscrolls(true);
		setTitle("收货单验收");
		setBounds(100, 100, 700, 420);
		

		final JPanel panel = new JPanel();
		getContentPane().add(panel);

		final JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(680, 180));
		panel.add(scrollPane);

		final DefaultTableModel model=new DefaultTableModel();
		Object[][] results=getFileStates(ConnectDb.getOrder());
		model.setDataVector(results,columnNames);
		table = new JTable();
		table.setModel(model);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);//关闭列自动变小
		scrollPane.setViewportView(table);
		table.addMouseListener(new TableListener());



		final JPanel panel_1_1 = new JPanel();
		final GridLayout gridLayout = new GridLayout(0, 4);
		gridLayout.setVgap(5);
		panel_1_1.setLayout(gridLayout);
		panel_1_1.setPreferredSize(new Dimension(450, 150));
		panel.add(panel_1_1);

		final JLabel label_1 = new JLabel();
		label_1.setText("收货日期：");
		panel_1_1.add(label_1);

		SimpleDateFormat myfmt=new SimpleDateFormat("yyyy-MM-dd");

		orderDate = new JFormattedTextField(myfmt.getDateInstance());
		//orderDate.setValue(new java.util.Date());
		orderDate.addKeyListener(new DateListener());

		panel_1_1.add(orderDate);

		final JLabel label_3 = new JLabel();
		label_3.setText("收货单编号：");
		panel_1_1.add(label_3);

		ISBN = new JTextField();
		panel_1_1.add(ISBN);

		final JLabel label_4 = new JLabel();
		label_4.setText("收货数量：");
		panel_1_1.add(label_4);

		orderNumber = new JTextField();
		panel_1_1.add(orderNumber);

		final JLabel label_5 = new JLabel();
		label_5.setText("操作员：");
		panel_1_1.add(label_5);
		//operator = new JTextField(user.getName());
		operator = new JTextField();
		panel_1_1.add(operator);

		final JLabel label_6 = new JLabel();
		label_6.setText("商品明细：");
		panel_1_1.add(label_6);

		bookType = new JTextField();
		panel_1_1.add(bookType);


		final JLabel label_7 = new JLabel();
		label_7.setText("单价：");
		panel_1_1.add(label_7);

		price = new JTextField();
		panel_1_1.add(price);

		final JLabel label_9 = new JLabel();
		label_9.setText("是否验收：");
		panel_1_1.add(label_9);

		final JPanel panel_1 = new JPanel();
		panel_1_1.add(panel_1);

		radioButton1 = new JRadioButton();
		radioButton1.setSelected(true);
		panel_1.add(radioButton1);
		buttonGroup.add(radioButton1);
		radioButton1.setText("是");

		radioButton2= new JRadioButton();
		panel_1.add(radioButton2);
		buttonGroup.add(radioButton2);
		radioButton2.setText("否");

		final JLabel label = new JLabel();
		label.setText("折扣：");
		panel_1_1.add(label);

		zk = new JTextField();
		panel_1_1.add(zk);

		final JLabel label_2 = new JLabel();
		label_2.setText("折扣后金额：");
		panel_1_1.add(label_2);

		orderPrice = new JTextField();
		panel_1_1.add(orderPrice);
		setVisible(true);

		final JPanel panel_2 = new JPanel();
		getContentPane().add(panel_2, BorderLayout.SOUTH);

		final JButton buttonCheck = new JButton();
		panel_2.add(buttonCheck);
		buttonCheck.setText("验收");
		buttonCheck.addActionListener(new CheckActionListener(model));

		final JButton buttonExit = new JButton();
		panel_2.add(buttonExit);
		buttonExit.addActionListener(new CloseActionListener());
		buttonExit.setText("退出");
		
		//
	}
	class DateListener extends KeyAdapter {
		public void keyTyped(KeyEvent e) {
			if(orderDate.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "时间格式请使用\"2007-05-10\"格式");
			}
		}
	}
	class TableListener extends MouseAdapter {
		public void mouseClicked(final MouseEvent e) {
			
			int selRow = table.getSelectedRow();
			
			ISBN.setText(table.getValueAt(selRow, 0).toString().trim());
			orderDate.setText(table.getValueAt(selRow, 8).toString().trim());
			
			orderNumber.setText(table.getValueAt(selRow, 3).toString().trim());
			operator.setText(table.getValueAt(selRow, 12).toString().trim());
			
			bookType.setText(table.getValueAt(selRow, 1).toString().trim());
			
			price.setText(table.getValueAt(selRow, 4).toString().trim());
			if(table.getValueAt(selRow, 2).toString().trim().equals("否"))//1代表没有验收
				radioButton2.setSelected(true);
			else
				radioButton1.setSelected(true);
			zk.setText(table.getValueAt(selRow, 6).toString().trim());
			orderPrice.setText(table.getValueAt(selRow, 7).toString().trim());
			
		}
	}
	class CloseActionListener implements ActionListener {			// 添加关闭按钮的事件监听器
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
			if(radioButton2.isSelected()){
				String ISBNs=ISBN.getText();
				int i=ConnectDb.UpdateCheckOrder(ISBNs);
				if(i==1){
					JOptionPane.showMessageDialog(null, "验收成功！");
					Object[][] results=getFileStates(ConnectDb.getOrder());
					model.setDataVector(results,columnNames);
					table.setModel(model);
					radioButton1.setSelected(true);
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "您选择的收货单已经进行过验收，请选择其他收货单进行审核");
			}
			
			
			
			
		}
	}
}
