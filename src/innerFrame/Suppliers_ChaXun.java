package innerFrame;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import connect.ConnectDb;

import innerFrame.Suppliers_XiuGaiPanel.DelActionListener;
import innerFrame.Suppliers_XiuGaiPanel.ModifyActionListener;
import innerFrame.Suppliers_XiuGaiPanel.doGysSelectAction;
import model.Suppliers;
import model.Users;
import tools.InputKeyListener;
import tools.Item;

public class Suppliers_ChaXun extends JInternalFrame {
	private JTable table;
	private JTextField conditionContent;
	private JComboBox conditionOperation;
	private JComboBox conditionName;
	public Suppliers_ChaXun()
	{
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("供应商信息查询");
		getContentPane().setLayout(new GridBagLayout());
		setBounds(100, 100, 609, 375);

		table = new JTable();
		table.setEnabled(false);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		final DefaultTableModel dftm = (DefaultTableModel) table.getModel();
		String[] tableHeads = new String[]{"公司代号", "公司名称", "公司性质", "经营范围","公司地址", "员工人数", "仓库总面积", "厂房总面积", "办公电话", "办公邮箱", "联系人姓名", "联系人电话", "固定资产", "注册资金", "法人营业执照", "法人身份证","法人授权书", "国税登记证", "收款单位开户行", "开户行账号","是否通过验证"};
		
																									
		dftm.setColumnIdentifiers(tableHeads);

		final JScrollPane scrollPane = new JScrollPane(table);
		final GridBagConstraints gridBagConstraints_6 = new GridBagConstraints();
		gridBagConstraints_6.weighty = 1.0;
		gridBagConstraints_6.anchor = GridBagConstraints.NORTH;
		gridBagConstraints_6.insets = new Insets(0, 10, 0, 10);
		gridBagConstraints_6.fill = GridBagConstraints.BOTH;
		gridBagConstraints_6.gridwidth = 6;
		gridBagConstraints_6.gridy = 1;
		gridBagConstraints_6.gridx = 0;
		getContentPane().add(scrollPane, gridBagConstraints_6);

		setupComponet(new JLabel(" 选择查询条件："), 0, 0, 1, 1, false);
		conditionName = new JComboBox();
		conditionName.setModel(new DefaultComboBoxModel(new String[]{"供应商编号","供应商名称"}));
		setupComponet(conditionName, 1, 0, 1, 30, true);

		conditionOperation = new JComboBox();
		conditionOperation.setModel(new DefaultComboBoxModel(new String[]{"等于"}));
		setupComponet(conditionOperation, 2, 0, 1, 30, true);

		conditionContent = new JTextField();
		setupComponet(conditionContent, 3, 0, 1, 140, true);

		final JButton queryButton = new JButton();
		queryButton.addActionListener(new queryAction(dftm));
		setupComponet(queryButton, 4, 0, 1, 1, false);
		queryButton.setText("查询");

		final JButton showAllButton = new JButton();
		showAllButton.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				conditionContent.setText("");
				List list = ConnectDb.getSuppliersInfos();
				updateTable(list, dftm);
			}
		});
		setupComponet(showAllButton, 5, 0, 1, 1, false);
		showAllButton.setText("显示全部数据");
	}

	private void updateTable(List list, final DefaultTableModel dftm) {
		int num = dftm.getRowCount();
		for (int i = 0; i < num; i++)
			dftm.removeRow(0);
		Iterator iterator = list.iterator();
		Suppliers gysInfo;
			
		
		while (iterator.hasNext()) {
			List info = (List) iterator.next();
			String item = (String) info.get(0);
			gysInfo = ConnectDb.getSuppliersInfo(item);		
			Vector rowData = new Vector();
			rowData.add(gysInfo.getId());
			rowData.add(gysInfo.getName());
			rowData.add(gysInfo.getScope());
			rowData.add(gysInfo.getProperty());
			rowData.add(gysInfo.getCaddress());
			rowData.add(String.valueOf(gysInfo.getNumber()));
			rowData.add(String.valueOf(gysInfo.getSarea()));
			rowData.add(String.valueOf(gysInfo.getFarea()));
			rowData.add(gysInfo.getPhone());
			rowData.add(gysInfo.getEmail());
			rowData.add(gysInfo.getStuff());
			rowData.add(gysInfo.getSphone());
			rowData.add(String.valueOf(gysInfo.getFA()));
			rowData.add(String.valueOf(gysInfo.getRC()));
			rowData.add(gysInfo.getLicense());
			rowData.add(gysInfo.getIDcard());
			rowData.add(gysInfo.getAuthorization());
			rowData.add(gysInfo.getStax());
			rowData.add(gysInfo.getLtax());
			rowData.add(gysInfo.getCode());
			rowData.add(gysInfo.getYz());
			dftm.addRow(rowData);
			 		
		}
		
	}
	// 设置组件位置并添加到容器中
	private void setupComponet(JComponent component, int gridx, int gridy,
			int gridwidth, int ipadx, boolean fill) {
		final GridBagConstraints gridBagConstrains = new GridBagConstraints();
		gridBagConstrains.gridx = gridx;
		gridBagConstrains.gridy = gridy;
		if (gridwidth > 1)
			gridBagConstrains.gridwidth = gridwidth;
		if (ipadx > 0)
			gridBagConstrains.ipadx = ipadx;
		gridBagConstrains.insets = new Insets(5, 1, 3, 1);
		if (fill)
			gridBagConstrains.fill = GridBagConstraints.HORIZONTAL;
		getContentPane().add(component, gridBagConstrains);
	}
	private final class queryAction implements ActionListener {
		private final DefaultTableModel dftm;
		private queryAction(DefaultTableModel dftm)
		{
			this.dftm = dftm;
		}
		public void actionPerformed(final ActionEvent e)
		{
			String conName, conOperation, content;
			List list = null;
			conName = conditionName.getSelectedItem().toString().trim();
			conOperation = conditionOperation.getSelectedItem().toString().trim();
			content = conditionContent.getText().trim();
			//String sql = "select * from Suppliers where ";
			if (conName.equals("供应商编号"))
			{
				if (conOperation.equals("等于")){					
				    list =ConnectDb.findForList("select Supcode from T_Supplier where Supcode='"+content+"'");
				}
			} else
			{
				if (conOperation.equals("等于"))
					list =ConnectDb.findForList("select Supcode from T_Supplier where Cname='"+content+"'");
				
			}
			updateTable(list, dftm);
		}
	}
}

