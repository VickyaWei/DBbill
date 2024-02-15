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
	
	// 创建窗体的Map类型集合对象
	private Map<String, JInternalFrame> ifs = new HashMap<String, JInternalFrame>();
	public BankFrame()
	{
		frame = new JFrame("DB-Bill融资平台");
		frame.addComponentListener(new FrameListener());
		frame.getContentPane().setLayout(new BorderLayout());
		frame.setBounds(100, 100, 700, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		backLabel = new JLabel();// 背景标签
		backLabel.setVerticalAlignment(SwingConstants.TOP);
		backLabel.setHorizontalAlignment(SwingConstants.CENTER);
		updateBackImage(); // 更新或初始化背景图片
		desktopPane = new JDesktopPane();
		desktopPane.add(backLabel, new Integer(Integer.MIN_VALUE));
		frame.getContentPane().add(desktopPane);
		JTabbedPane navigationPanel = createNavigationPanel(); // 创建导航标签面板
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
	{ // 创建导航标签面板的方法
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setFocusable(false);
		tabbedPane.setBorder(new BevelBorder(BevelBorder.RAISED));

		JPanel inforManagePanel = new JPanel(); // 审核面板
		inforManagePanel.setLayout(new BoxLayout(inforManagePanel,BoxLayout.X_AXIS));
		inforManagePanel.add(createFrameButton("信息审核", "Infor_ShenHe"));
		inforManagePanel.add(createFrameButton("贴现审核", "DiscountAudit"));
		//inforManagePanel.add(createFrameButton("订单管理", "Order_GuanLi"));
		inforManagePanel.add(createFrameButton("账期审核", "ProlongAudit"));
		//inforManagePanel.add(createFrameButton("发票信息", "Invoice_GuanLi"));
		//inforManagePanel.add(createFrameButton("信息审核", "Infor_ShenHe"));
		//inforManagePanel.add(createFrameButton("信息审核", "ShenHe_1Panel"));
		
		JPanel sellManagePanel = new JPanel();// 信息面板
		sellManagePanel.setLayout(new BoxLayout(sellManagePanel,BoxLayout.X_AXIS));
		sellManagePanel.add(createFrameButton("收益查询", "Invoice_DiscountSearch"));
		sellManagePanel.add(createFrameButton("已完成发票", "History_Trade"));
		sellManagePanel.add(createFrameButton("未完成发票", "Invoice_Chaxun"));
		sellManagePanel.add(createFrameButton("已贴现发票", "History_SupplierTrade1"));
		sellManagePanel.add(createFrameButton("未贴现发票", "Invoice_SupplierChaxun1"));
		
		
		
		JPanel stockManagePanel = new JPanel();// 供应商信息面板
		stockManagePanel.setLayout(new BoxLayout(stockManagePanel,BoxLayout.X_AXIS));
		//stockManagePanel.add(createFrameButton("贴现审核", "discount_audit"));
		stockManagePanel.add(createFrameButton("供应商信息查询", "Suppliers_ChaXun"));

		tabbedPane.addTab("   审核贴现   ", null, inforManagePanel, "审核贴现");
		tabbedPane.addTab("   信息查询   ", null, sellManagePanel , "信息查询");
		tabbedPane.addTab("   供应商信息   ", null, stockManagePanel , "供应商信息");
		

		return tabbedPane;
	}

	// 内部窗体添加Action方法
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
	
	// 获取内部窗体的唯一实例对象
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
	
	// 更新背景图片方法
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
	
	// 窗体监听器
	private final class FrameListener extends ComponentAdapter
	{
		public void componentResized(final ComponentEvent e)
		{
			updateBackImage();
		}
	}
	
	// 主窗体菜单项的单击事件监听器
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
			// 在内部窗体闭关时，从内部窗体容器ifs对象中清除该窗体。
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