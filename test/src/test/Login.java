package test;

import java.awt.EventQueue;

import javax.swing.JFrame;
//import java.awt.GridLayout;
import javax.swing.JButton;
//import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JEditorPane;
import javax.swing.JTextField;

public class Login {
	public String username;
	public String password;
	private JFrame frame;
	public static Login window;
	public Connection con;
	private JTextField textField;
	public String loginresult="";
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void show() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new Login(con);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}
	
	public Login(Connection con) {
		this.con = con;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JEditorPane editorPane_1 = new JEditorPane();
		editorPane_1.setBounds(167, 134, 100, 29);
		frame.getContentPane().add(editorPane_1);
		
		JEditorPane editorPane = new JEditorPane();
		editorPane.setBounds(167, 80, 100, 29);
		frame.getContentPane().add(editorPane);
		
		
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				password = editorPane_1.getText();
				username=editorPane.getText();
				Statement myStmt = null;
				ResultSet myRs = null;
				
				try {
					myStmt = con.createStatement();
					myRs = myStmt.executeQuery("select * from User where Username = " + "'"+username+"'" +"and "+ "Password = "  + "'"+password+"'");
					if(!myRs.next()){
						loginresult = "Wrong login infomation!";
						textField.setText(loginresult);
						myRs.beforeFirst();
						return;
					}else{
						myRs = myStmt.executeQuery("select UserType from User where Username = " + "'"+username+"'" +"and "+ "Password = "  + "'"+password+"'");
						
						if(myRs.next()){

							if(myRs.getString("UserType").equals("student")){
								Main_page main = new Main_page(username,con); 
								main.show();
								window.frame.setVisible(false);
								window = null;
							}else if(myRs.getString("UserType").equals("admin")){
								Adminfunction main = new Adminfunction(con); 
								main.show();
								window.frame.setVisible(false);
								window = null;
							}
							
						}
		
									
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
					
				
			}
		});
		btnNewButton.setBounds(85, 187, 117, 29);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Register");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				New_regis regis = new New_regis(con); 
				regis.show();
				
				System.out.println(editorPane.getText());
				window.frame.setVisible(false);
				window = null;
			}
		});
		btnNewButton_1.setBounds(279, 187, 117, 29);
		frame.getContentPane().add(btnNewButton_1);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setBounds(200, 30, 35, 16);
		frame.getContentPane().add(lblLogin);
		
		JLabel lblNewLabel = new JLabel("username");
		lblNewLabel.setBounds(55, 80, 61, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("password");
		lblNewLabel_1.setBounds(55, 134, 61, 16);
		frame.getContentPane().add(lblNewLabel_1);
		
		textField = new JTextField();
		
		textField.setBounds(55, 231, 276, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		
		
		
	}
}
