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
		setTitle("��Ӧ�̹���");
		JTabbedPane tabPane = new JTabbedPane();
		final Suppliers_XiuGaiPanel spxgPanel = new Suppliers_XiuGaiPanel();
		final Suppliers_TianJiaPanel sptjPanel = new Suppliers_TianJiaPanel();
		
		tabPane.addTab("��Ӧ����Ϣ���", null, sptjPanel, "��Ӧ�����");
		tabPane.addTab("��Ӧ����Ϣ�޸���ɾ��", null, spxgPanel, "�޸���ɾ��");
	
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
