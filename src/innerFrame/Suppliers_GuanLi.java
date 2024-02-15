package innerFrame;
import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
public class Suppliers_GuanLi extends JInternalFrame {
	public Suppliers_GuanLi()
	{
		setIconifiable(true);
		setClosable(true);
		setTitle("供应商管理");
		JTabbedPane tabPane = new JTabbedPane();
		final Suppliers_XiuGaiPanel spxgPanel = new Suppliers_XiuGaiPanel();
		final Suppliers_TianJiaPanel sptjPanel = new Suppliers_TianJiaPanel();
		
		tabPane.addTab("供应商信息添加", null, sptjPanel, "供应商添加");
		tabPane.addTab("供应商信息修改与删除", null, spxgPanel, "修改与删除");
	
		getContentPane().add(tabPane);
		tabPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e)
			{
				spxgPanel.initComboBox();
			}
		});
		pack();
		setVisible(true);
	}
}
