package test;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JEditorPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class New_regis {

	private JFrame frame;
	public static New_regis window;
	public Connection con;
	public String username;
	public String password;
	public String gtemail;
	public String secondpassword;
	private JTextField textField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new New_regis();
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
					window = new New_regis(con);
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
	public New_regis() {
		initialize();
	}
	
	public New_regis(Connection con) {
		this.con=con;
		initialize();
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 350);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New student registration");
		lblNewLabel.setBounds(139, 28, 203, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setBounds(83, 61, 71, 16);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblGtEmail = new JLabel("GT email");
		lblGtEmail.setBounds(83, 107, 61, 16);
		frame.getContentPane().add(lblGtEmail);
		
		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setBounds(83, 153, 90, 16);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblCom = new JLabel("Comfirm Password");
		lblCom.setBounds(30, 193, 138, 16);
		frame.getContentPane().add(lblCom);
		
		JEditorPane editorPane = new JEditorPane();
		editorPane.setBounds(185, 61, 240, 16);
		frame.getContentPane().add(editorPane);
		
		JEditorPane editorPane_1 = new JEditorPane();
		editorPane_1.setBounds(185, 107, 240, 16);
		frame.getContentPane().add(editorPane_1);
		
		JEditorPane editorPane_2 = new JEditorPane();
		editorPane_2.setBounds(185, 153, 240, 16);
		frame.getContentPane().add(editorPane_2);
		
		JEditorPane editorPane_3 = new JEditorPane();
		editorPane_3.setBounds(185, 193, 240, 16);
		frame.getContentPane().add(editorPane_3);
		
		JButton btnNewButton = new JButton("Create");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Statement myStmt = null;
				ResultSet myRs = null;
				int update;
				username = editorPane.getText();
				gtemail = editorPane_1.getText();
				password = editorPane_2.getText();
				secondpassword = editorPane_3.getText();
				
				
				
				if(!gtemail.contains("@gatech.edu")){
					textField.setText("please use gt email!");
					return;
				}
				
				if(!password.equals(secondpassword)){
					textField.setText("two password not match!");
					return;
				}
				
				try {
					myStmt = con.createStatement();
					myRs = myStmt.executeQuery("select Username from User where Username = " + "'"+username+"'");
					if(myRs.next()){
						textField.setText("this username already in the database, cant register again!");
						return;
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
				
				
				try {
					myStmt = con.createStatement();
					myRs = myStmt.executeQuery("select Email from User where Email = " + "'"+gtemail+"'");
					if(myRs.next()){
						textField.setText("this email already in the database, cant register again!");
						return;
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					myStmt = con.createStatement();
					update = myStmt.executeUpdate("INSERT INTO User (Username,Password,Email,UserType) VALUES ("+"'"+username+"', "+"'"+password+"', "+"'"+gtemail+"', "
+" 'student');");
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
		
				
				Login login = new Login(con);
				login.show();
				window.frame.setVisible(false);
				window=null;
				
			}
		});
		btnNewButton.setBounds(152, 228, 162, 29);
		frame.getContentPane().add(btnNewButton);
		
		textField = new JTextField();
		
		textField.setBounds(6, 272, 438, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
	}
}
