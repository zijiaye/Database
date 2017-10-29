package test;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Viewcourse {
	public Connection con;
	private JFrame frame;
	public String coursename;
	public String username;
	public static Viewcourse window;
	public Statement myStmt = null;
	public ResultSet myRs = null;
	public Statement myStmt2 = null;
	public ResultSet myRs2 = null;
	public Statement myStmt3 = null;
	public ResultSet myRs3 = null;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new Viewcourse();
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
					window = new Viewcourse(con,coursename,username);
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
	public Viewcourse() {
		initialize();
	}

	public Viewcourse(Connection con,String coursename,String username) {
		this.username = username;
		this.coursename=coursename;
		this.con=con;
		initialize();
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		String course_number="";
		String instructor="";
		
		String Designation_name = "";
		
		String cate="";
		System.out.println(course_number+"this is number");
		int estnumber=0;
		try {
			myStmt = con.createStatement();
			
			myRs = myStmt.executeQuery("select * from Course where Name = " + "'"+coursename+"'");
			
			if(myRs.next()) {
				//System.out.println(myRs.getString("User_name") + ", " + myRs.getString("Password"));
				course_number = myRs.getString("Course_number");
				instructor = myRs.getString("Instructor");
				
				estnumber = myRs.getInt("EstNumOfStudent");
				Designation_name = myRs.getString("Designation_name");
			}
			
			myRs3 = myStmt.executeQuery("select * from Course_is_category where Course_name = " + "'"+coursename+"'");
			while(myRs3.next()) {
				//System.out.println(myRs.getString("User_name") + ", " + myRs.getString("Password"));
				cate = cate+myRs3.getString("Category_name")+".";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		
		System.out.println(course_number+"this is number");
		
		
		
		frame = new JFrame();
		frame.setBounds(100, 100, 650, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Course ID:");
		lblNewLabel.setBounds(60, 85, 128, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblCourseName = new JLabel("Course name:");
		lblCourseName.setBounds(60, 31, 128, 16);
		frame.getContentPane().add(lblCourseName);
		
		JLabel lblIns = new JLabel("Instructor:");
		lblIns.setBounds(60, 113, 128, 16);
		frame.getContentPane().add(lblIns);
		
		JLabel label = new JLabel("Designation:");
		label.setBounds(60, 184, 86, 16);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("Category:");
		label_1.setBounds(60, 249, 104, 16);
		frame.getContentPane().add(label_1);
		
		JLabel label_3 = new JLabel("Est number of student:");
		label_3.setBounds(60, 295, 160, 16);
		frame.getContentPane().add(label_3);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main_page main = new Main_page(username,con); 
				main.show();
				window.frame.setVisible(false);
				window = null;
			}
		});
		btnNewButton.setBounds(163, 368, 117, 29);
		frame.getContentPane().add(btnNewButton);
		
		textField = new JTextField();
		textField.setText(coursename);
		textField.setBounds(184, 26, 460, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setText(course_number);
		textField_1.setColumns(10);
		textField_1.setBounds(184, 80, 460, 26);
		frame.getContentPane().add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setText(instructor);
		textField_2.setColumns(10);
		textField_2.setBounds(184, 113, 460, 26);
		frame.getContentPane().add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setText(Designation_name);
		textField_3.setColumns(10);
		textField_3.setBounds(184, 179, 466, 26);
		frame.getContentPane().add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setText(cate);
		textField_4.setColumns(10);
		textField_4.setBounds(184, 244, 460, 26);
		frame.getContentPane().add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setText(Integer.toString(estnumber));
		textField_5.setColumns(10);
		textField_5.setBounds(230, 290, 130, 26);
		frame.getContentPane().add(textField_5);
	}
}
