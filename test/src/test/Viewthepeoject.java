package test;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Viewthepeoject {
	public  Connection con;
	public  String pjname;
	private JFrame frame;
	public  static Viewthepeoject window;
	public  String Student_name;
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
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	ArrayList<String> reqlist = new ArrayList<String>();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//con = DriverManager.getConnection("jdbc:mysql://academic-mysql.cc.gatech.edu/cs4400_Team_86", "cs4400_Team_86","KpKnhohb");
					//pjname = "project1";
					//Student_name = "user2";
					Viewthepeoject window = new Viewthepeoject();
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
					//con = DriverManager.getConnection("jdbc:mysql://academic-mysql.cc.gatech.edu/cs4400_Team_86", "cs4400_Team_86","KpKnhohb");
					//pjname = "project1";
					
					window = new Viewthepeoject(con,pjname,Student_name);
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
	public Viewthepeoject() {
		initialize();
	}
	
	public Viewthepeoject(Connection con,String project_name,String username) {
		this.Student_name = username;
		this.pjname = project_name;
		this.con=con;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		Date dateobj = new Date();
		String reportDate = df.format(dateobj);
		
		String Description="";
		String Advisor="";
		String Advisor_email="";
		String Designation_name = "";
		String requirement="";
		String cate="";
		int estnumber=0;
		try {
			myStmt = con.createStatement();
			
			myRs = myStmt.executeQuery("select * from Project where Name = " + "'"+pjname+"'");
			
			if(myRs.next()) {
				//System.out.println(myRs.getString("User_name") + ", " + myRs.getString("Password"));
				Description = myRs.getString("Description");
				Advisor = myRs.getString("Advisor_name");
				Advisor_email=myRs.getString("Advisor_email");
				estnumber = myRs.getInt("EstNumOfStudent");
				Designation_name = myRs.getString("Designation_name");
			}
			myRs2 = myStmt.executeQuery("select * from Project_requirement where Name = " + "'"+pjname+"'");
			while(myRs2.next()) {
				reqlist.add(myRs2.getString("Requirement"));
				requirement = requirement+myRs2.getString("Requirement")+".";
			}
			myRs3 = myStmt.executeQuery("select * from Project_is_category where Project_name = " + "'"+pjname+"'");
			while(myRs3.next()) {
				
				cate = cate+myRs3.getString("Category_name")+".";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		frame = new JFrame();
		frame.setBounds(100, 100, 650, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Project name: ");
		lblNewLabel.setBounds(54, 41, 104, 16);
		frame.getContentPane().add(lblNewLabel);
	
		JLabel lblNewLabel_1 = new JLabel("Description:");
		lblNewLabel_1.setBounds(54, 100, 104, 16);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblDe = new JLabel("Designation:");
		lblDe.setBounds(54, 215, 86, 16);
		frame.getContentPane().add(lblDe);
		
		textField_8 = new JTextField();
		
		
		textField_8.setColumns(10);
		textField_8.setBounds(54, 483, 302, 26);
		frame.getContentPane().add(textField_8);
		
		JLabel lblCa = new JLabel("Category:");
		lblCa.setBounds(54, 293, 104, 16);
		frame.getContentPane().add(lblCa);
		
		JLabel lblRequirement = new JLabel("Requirement:");
		lblRequirement.setBounds(54, 342, 86, 16);
		frame.getContentPane().add(lblRequirement);
		
		JLabel lblEstNumberOf = new JLabel("Est number of student:");
		lblEstNumberOf.setBounds(54, 415, 160, 16);
		frame.getContentPane().add(lblEstNumberOf);
		
		JLabel lblViewProject = new JLabel("View Project");
		lblViewProject.setBounds(181, 17, 146, 16);
		frame.getContentPane().add(lblViewProject);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main_page main = new Main_page(Student_name,con); 
				main.show();
				window.frame.setVisible(false);
				window = null;
			}
		});
		btnBack.setBounds(40, 543, 117, 29);
		frame.getContentPane().add(btnBack);
		
		JButton btnApply = new JButton("Apply");
		btnApply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					myRs = myStmt.executeQuery("select * from User where Username = " + "'"+Student_name+"'");
					if(myRs.next()){
						
						if(myRs.getString("Major")==null){
							
							textField_8.setText("Edit profile before apply project!  GG! ");
							return;
						}
						
					}
					
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
					//check requirement
				
				System.out.println(reqlist.get(0));
				if(!reqlist.get(0).equals("none")){
					try {
						String yearr="";
						myRs = myStmt.executeQuery("select * from User where Username = " + "'"+Student_name+"'");
						
						if(myRs.next()){
							if(myRs.getString("Year").equals("1")){
								yearr=("freshman");
							}else if(myRs.getString("Year").equals("2")){
								yearr=("sophomore");
							}else if(myRs.getString("Year").equals("3")){
								yearr=("junior");
							}else if(myRs.getString("Year").equals("4")){
								yearr=("senior");
							}	
							
							for(int i =0;i<reqlist.size();i++){
								if(reqlist.get(i).contains(yearr)){
									reqlist.remove(i);
								}
							}
							String majorr = myRs.getString("Major");
							for(int i =0;i<reqlist.size();i++){
								if(reqlist.get(i).contains(myRs.getString("Major"))){
									reqlist.remove(i);
								}
							}
							
							myRs = myStmt.executeQuery("select Dept_name from Major where Name = " + "'"+majorr+"'");
							
							for(int i =0;i<reqlist.size();i++){
								
								if(myRs.next()){
								if(reqlist.get(i).contains(myRs.getString("Dept_name"))){
									reqlist.remove(i);
								}
							}
							}
							if(reqlist.size()>0) {
								textField_8.setText("you do not meet project requirement LUL");
								return;
							}
							
							
						}
						
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				}
				
				
				
				
				//check application in database or not
				try {
					myRs = myStmt.executeQuery("select * from Apply where Project_name = " + "'"+pjname+"'" +"and "+ "Student_name = "  + "'"+Student_name+"'");
					if(myRs.next()){
						textField_8.setText("application already in database!");
						return;
					}
					
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				
				int update;
				try {
					myStmt = con.createStatement();
					update = myStmt.executeUpdate("INSERT INTO Apply (Project_name,Student_name,Date,Status) VALUES ("+"'"+pjname+"', "+"'"+Student_name+"', "+"'"+reportDate+"', "+"'Pending');");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			}
		});
		btnApply.setBounds(259, 543, 117, 29);
		frame.getContentPane().add(btnApply);
		
		textField = new JTextField();
		textField.setText(pjname);
		textField.setBounds(170, 36, 497, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setText(Description);
		textField_1.setBounds(170, 95, 474, 26);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setText(Designation_name);
		textField_2.setColumns(10);
		textField_2.setBounds(152, 210, 454, 71);
		frame.getContentPane().add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setText(cate);
		textField_3.setColumns(10);
		textField_3.setBounds(152, 288, 440, 26);
		frame.getContentPane().add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setText(requirement);
		textField_4.setColumns(10);
		textField_4.setBounds(152, 316, 398, 69);
		frame.getContentPane().add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setText(Integer.toString(estnumber));
		textField_5.setColumns(10);
		textField_5.setBounds(226, 410, 130, 26);
		frame.getContentPane().add(textField_5);
		
		JLabel lblAdvisor = new JLabel("Advisor");
		lblAdvisor.setBounds(54, 159, 104, 16);
		frame.getContentPane().add(lblAdvisor);
		
		JLabel lblAdvisorEmail = new JLabel("Advisor Email");
		lblAdvisorEmail.setBounds(54, 187, 104, 16);
		frame.getContentPane().add(lblAdvisorEmail);
		
		textField_6 = new JTextField();
		textField_6.setText(Advisor);
		textField_6.setBounds(170, 154, 474, 26);
		frame.getContentPane().add(textField_6);
		textField_6.setColumns(10);
		
		textField_7 = new JTextField();
		textField_7.setText(Advisor_email);
		textField_7.setBounds(170, 182, 474, 26);
		frame.getContentPane().add(textField_7);
		textField_7.setColumns(10);
		
		
	}
}
