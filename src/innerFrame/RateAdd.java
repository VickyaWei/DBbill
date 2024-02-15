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
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import connect.ConnectDb;
import innerFrame.OrdercheckIFrame.CheckActionListener;
import innerFrame.OrdercheckIFrame.CloseActionListener;
import innerFrame.OrdercheckIFrame.DateListener;
import innerFrame.OrdercheckIFrame.TableListener;
import model.Order;
import model.Rate;

public class RateAdd extends JInternalFrame{

	private JTable table;
	
	private JTextField operator;
	private JTextField orderNumber;
	private JTextField ISBN;
	private JFormattedTextField orderDate;
	
	
	
	private String[] columnNames={ "更新时间", "一年以内利率","一至五年利率", "五年以上利率"};
	//private Map map=MapPz.getMap();
	
	private Object[][] getFileStates(List list){
		Object[][]results=new Object[list.size()][columnNames.length];
		for(int i=0;i<list.size();i++){
			Rate order=(Rate)list.get(i);
			results[i][1]=order.getRate1();	
			results[i][2]=order.getRate2();
			results[i][3]=order.getRate3();			
			results[i][0]=order.getUpdatedate();		
					
		}
		return results;
	         		
	}

	/**
	 * Create the frame
	 */
	public RateAdd() {
		super();
		setClosable(true);
		setIconifiable(true);
		setAutoscrolls(true);
		setTitle("基准利率更新");
		setBounds(100, 100, 500, 340);
		

		final JPanel panel = new JPanel();
		getContentPane().add(panel);

		final JLabel label = new JLabel();
		label.setText("当前利率信息");
		panel.add(label);
		
		final JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(480, 180));
		panel.add(scrollPane);

		final DefaultTableModel model=new DefaultTableModel();
		Object[][] results=getFileStates(ConnectDb.getRate());
		model.setDataVector(results,columnNames);
		table = new JTable();
		table.setModel(model);
		//table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);//关闭列自动变小
		scrollPane.setViewportView(table);
		//table.addMouseListener(new TableListener());



		final JPanel panel_1_1 = new JPanel();
		final GridLayout gridLayout = new GridLayout(0, 4);
		gridLayout.setVgap(5);
		panel_1_1.setLayout(gridLayout);
		panel_1_1.setPreferredSize(new Dimension(450, 50));
		panel.add(panel_1_1);

		final JLabel label_1 = new JLabel();
		label_1.setText("更新日期：");
		panel_1_1.add(label_1);

		SimpleDateFormat myfmt=new SimpleDateFormat("yyyy-MM-dd");

		orderDate = new JFormattedTextField(myfmt.getDateInstance());
		orderDate.setValue(new java.util.Date());
		orderDate.addKeyListener(new DateListener());
		orderDate.setEditable(false);
		panel_1_1.add(orderDate);

		final JLabel label_3 = new JLabel();
		label_3.setText("一年内利率：");
		panel_1_1.add(label_3);

		ISBN = new JTextField();
		panel_1_1.add(ISBN);

		final JLabel label_4 = new JLabel();
		label_4.setText("一至五年利率：");
		panel_1_1.add(label_4);

		orderNumber = new JTextField();
		panel_1_1.add(orderNumber);

		final JLabel label_5 = new JLabel();
		label_5.setText("五年以上利率：");
		panel_1_1.add(label_5);
		//operator = new JTextField(user.getName());
		operator = new JTextField();
		panel_1_1.add(operator);



		final JPanel panel_2 = new JPanel();
		getContentPane().add(panel_2, BorderLayout.SOUTH);

		final JButton buttonCheck = new JButton();
		panel_2.add(buttonCheck);
		buttonCheck.setText("更新");
		buttonCheck.addActionListener(new CheckActionListener(model));

		final JButton buttonExit = new JButton();
		panel_2.add(buttonExit);
		buttonExit.addActionListener(new CloseActionListener());
		buttonExit.setText("重置");
		
		
		//
	}
	class DateListener extends KeyAdapter {
		public void keyTyped(KeyEvent e) {
			if(orderDate.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "时间格式请使用\"2007-05-10\"格式");
			}
		}
	}
	
	class CloseActionListener implements ActionListener {			// 添加关闭按钮的事件监听器
		public void actionPerformed(final ActionEvent e) {
			ISBN.setText("");
			orderNumber.setText("");
			operator.setText("");
		}
	}
	class CheckActionListener implements ActionListener{
		private final DefaultTableModel model;

		CheckActionListener(DefaultTableModel model) {
			this.model = model;
		}
		public void actionPerformed(final ActionEvent e) {
			
				int b=ConnectDb.selectRateid();
				
				b=b+1;
				int i=ConnectDb.addRate(b,Double.parseDouble(ISBN.getText().trim()),Double.parseDouble(orderNumber.getText().trim()),Double.parseDouble(operator.getText().trim()),java.sql.Date.valueOf(orderDate.getText().trim()));
				if(i==1){
					JOptionPane.showMessageDialog(null, "更新成功！");
					Object[][] results=getFileStates(ConnectDb.getRate());
					model.setDataVector(results,columnNames);
					table.setModel(model);
					
				}
			
	
		}
	}
}
