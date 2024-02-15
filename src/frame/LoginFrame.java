package frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import connect.ConnectDb;
import model.Users;



@SuppressWarnings("serial")
public class LoginFrame extends JFrame implements ActionListener
{
	private JLabel userLabel;
	private JLabel passLabel;
	private JButton exit;
	private JButton login;
	private static Users user;
	public static String operater;
	

	public LoginFrame()
	{
		setTitle("登录界面");
		
		final JPanel panel = new LoginPanel();
		panel.setLayout(null);
		getContentPane().add(panel);
		setBounds(300, 200, panel.getWidth(), panel.getHeight());
		userLabel = new JLabel();
		userLabel.setText("用户名：");
		userLabel.setBounds(100, 135, 200, 18);
		panel.add(userLabel);
		
		final JTextField userName = new JTextField();
		userName.setBounds(150, 135, 200, 18);
		panel.add(userName);
		operater=userName.getText().trim();
		
		passLabel = new JLabel();
		passLabel.setText("密  码：");
		passLabel.setBounds(100, 165, 200, 18);
		panel.add(passLabel);
		
		final JPasswordField userPassword = new JPasswordField();
		userPassword.addKeyListener(new KeyAdapter()
		{
			public void keyPressed(final KeyEvent e) {
				if (e.getKeyCode() == 10)
					login.doClick();
			}
		});
		userPassword.setBounds(150, 165, 200, 18);
		panel.add(userPassword);
		login = new JButton();
		
		login.addActionListener(new ActionListener()
		{
			@SuppressWarnings("deprecation")
			public void actionPerformed(final ActionEvent e)
			{
				user = ConnectDb.getUser(userName.getText());
				//System.out.println(user.getPassword().trim()); 
				if (user.getPassword().trim().equals(userPassword.getText()))
				{
					if(user.getAthority().trim().equals("品牌商")){
						setVisible(false);
				        new BrandFrame();
					}
					if(user.getAthority().trim().equals("银行")){
						setVisible(false);
				        new BankFrame();
					}
					
					if(user.getAthority().trim().equals("供应商")){
						setVisible(false);
				        new SuppliersFrame();
					}
				}
				else{
					userName.setText(null);
					userPassword.setText(null);
					JOptionPane.showMessageDialog(null, "用户名或密码输入错误！请重新输入。", "登录失败", JOptionPane.ERROR_MESSAGE);
					return;
				}
					
				
			}
		});
		
		login.setText("登录");
		login.setBounds(180, 195, 60, 18);
		panel.add(login);
		exit = new JButton();
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				System.exit(0);
			}
		});
		exit.setText("退出");
		exit.setBounds(260, 195, 60, 18);
		panel.add(exit);
		setVisible(true);
		setResizable(false);
	}
	public static void main(String args[])
    {
    	new LoginFrame();
    }
	
	public static Users getUser() {
		return user;
	}
	public static void setUser(Users user) {
		LoginFrame.user = user;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}