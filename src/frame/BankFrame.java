package frame;
import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.beans.PropertyVetoException;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

public class BankFrame {
	private JPanel sysManagePanel;
	private JDesktopPane desktopPane;
	private JFrame frame;
	private JLabel backLabel;
	
	// ���������Map���ͼ��϶���
	private Map<String, JInternalFrame> ifs = new HashMap<String, JInternalFrame>();
	public BankFrame()
	{
		frame = new JFrame("DB-Bill����ƽ̨");
		frame.addComponentListener(new FrameListener());
		frame.getContentPane().setLayout(new BorderLayout());
		frame.setBounds(100, 100, 700, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		backLabel = new JLabel();// ������ǩ
		backLabel.setVerticalAlignment(SwingConstants.TOP);
		backLabel.setHorizontalAlignment(SwingConstants.CENTER);
		updateBackImage(); // ���»��ʼ������ͼƬ
		desktopPane = new JDesktopPane();
		desktopPane.add(backLabel, new Integer(Integer.MIN_VALUE));
		frame.getContentPane().add(desktopPane);
		JTabbedPane navigationPanel = createNavigationPanel(); // ����������ǩ���
		frame.getContentPane().add(navigationPanel, BorderLayout.NORTH);
		frame.setVisible(true);
	}
	
	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				new LoginFrame();
			}
		});
	}
	
	private JTabbedPane createNavigationPanel()
	{ // ����������ǩ���ķ���
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setFocusable(false);
		tabbedPane.setBorder(new BevelBorder(BevelBorder.RAISED));

		JPanel inforManagePanel = new JPanel(); // ������
		inforManagePanel.setLayout(new BoxLayout(inforManagePanel,BoxLayout.X_AXIS));
		inforManagePanel.add(createFrameButton("��Ϣ���", "Infor_ShenHe"));
		inforManagePanel.add(createFrameButton("�������", "DiscountAudit"));
		//inforManagePanel.add(createFrameButton("��������", "Order_GuanLi"));
		inforManagePanel.add(createFrameButton("�������", "ProlongAudit"));
		//inforManagePanel.add(createFrameButton("��Ʊ��Ϣ", "Invoice_GuanLi"));
		//inforManagePanel.add(createFrameButton("��Ϣ���", "Infor_ShenHe"));
		//inforManagePanel.add(createFrameButton("��Ϣ���", "ShenHe_1Panel"));
		
		JPanel sellManagePanel = new JPanel();// ��Ϣ���
		sellManagePanel.setLayout(new BoxLayout(sellManagePanel,BoxLayout.X_AXIS));
		sellManagePanel.add(createFrameButton("�����ѯ", "Invoice_DiscountSearch"));
		sellManagePanel.add(createFrameButton("����ɷ�Ʊ", "History_Trade"));
		sellManagePanel.add(createFrameButton("δ��ɷ�Ʊ", "Invoice_Chaxun"));
		sellManagePanel.add(createFrameButton("�����ַ�Ʊ", "History_SupplierTrade1"));
		sellManagePanel.add(createFrameButton("δ���ַ�Ʊ", "Invoice_SupplierChaxun1"));
		
		
		
		JPanel stockManagePanel = new JPanel();// ��Ӧ����Ϣ���
		stockManagePanel.setLayout(new BoxLayout(stockManagePanel,BoxLayout.X_AXIS));
		//stockManagePanel.add(createFrameButton("�������", "discount_audit"));
		stockManagePanel.add(createFrameButton("��Ӧ����Ϣ��ѯ", "Suppliers_ChaXun"));

		tabbedPane.addTab("   �������   ", null, inforManagePanel, "�������");
		tabbedPane.addTab("   ��Ϣ��ѯ   ", null, sellManagePanel , "��Ϣ��ѯ");
		tabbedPane.addTab("   ��Ӧ����Ϣ   ", null, stockManagePanel , "��Ӧ����Ϣ");
		

		return tabbedPane;
	}

	// �ڲ��������Action����
	private JButton createFrameButton(String fName, String cname)
	{
		String imgUrl = "res/ActionIcon/" + fName + ".png";
		String imgUrl_roll = "res/ActionIcon/" + fName	+ "_roll.png";
		String imgUrl_down = "res/ActionIcon/" + fName	+ "_down.png";
		Icon icon = new ImageIcon(imgUrl);
		Icon icon_roll = null;
		if (imgUrl_roll != null)
			icon_roll = new ImageIcon(imgUrl_roll);
		Icon icon_down = null;
		if (imgUrl_down != null)
			icon_down = new ImageIcon(imgUrl_down);
		Action action = new openFrameAction(fName, cname, icon);
		JButton button = new JButton(action);
		button.setMargin(new Insets(0, 0, 0, 0));
		button.setHideActionText(true);
		button.setFocusPainted(false);
		button.setBorderPainted(false);
		button.setContentAreaFilled(false);
		if (icon_roll != null)
			button.setRolloverIcon(icon_roll);
		if (icon_down != null)
			button.setPressedIcon(icon_down);
		return button;
	}
	
	// ��ȡ�ڲ������Ψһʵ������
	private JInternalFrame getIFrame(String frameName)
	{
		JInternalFrame jf = null;
		if (!ifs.containsKey(frameName))
		{
			try {
				@SuppressWarnings("rawtypes")
				Class fClass = Class.forName("innerFrame." + frameName);
				@SuppressWarnings({ "unchecked", "rawtypes" })
				Constructor constructor = fClass.getConstructor();
				jf = (JInternalFrame) constructor.newInstance();
				ifs.put(frameName, jf);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else
			jf = ifs.get(frameName);
		return jf;
	}
	
	// ���±���ͼƬ����
	private void updateBackImage()
	{
		if (backLabel != null)
		{
			int backw = BankFrame.this.frame.getWidth();
			int backh = frame.getHeight();
			backLabel.setSize(backw, backh);
			backLabel.setText("<html><body><image width='" + backw + "' height='" + (backh - 110) + "' src="+ BankFrame.this.getClass().getResource("welcome.jpg")+ "'></img></body></html>");
		}
	}
	
	// ���������
	private final class FrameListener extends ComponentAdapter
	{
		public void componentResized(final ComponentEvent e)
		{
			updateBackImage();
		}
	}
	
	// ������˵���ĵ����¼�������
	@SuppressWarnings("serial")
	protected final class openFrameAction extends AbstractAction
	{
		private String frameName = null;
		@SuppressWarnings("unused")
		private openFrameAction() {
		}
		public openFrameAction(String cname, String frameName, Icon icon) {
			this.frameName = frameName;
			putValue(Action.NAME, cname);
			putValue(Action.SHORT_DESCRIPTION, cname);
			putValue(Action.SMALL_ICON, icon);
		}
		public void actionPerformed(final ActionEvent e) {
			JInternalFrame jf = getIFrame(frameName);
			// ���ڲ�����չ�ʱ�����ڲ���������ifs����������ô��塣
			jf.addInternalFrameListener(new InternalFrameAdapter() {
				public void internalFrameClosed(InternalFrameEvent e) {
					ifs.remove(frameName);
				}
			});
			if (jf.getDesktopPane() == null) {
				desktopPane.add(jf);
				jf.setVisible(true);
			}
			try {
				jf.setSelected(true);
			} catch (PropertyVetoException e1) {
				e1.printStackTrace();
			}
		}
	}
	static {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}