package test;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import javax.swing.JEditorPane;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Add_project {
	public Connection con;
	private JFrame frame;
	public static Add_project window;
	public Statement myStmt = null;
	public ResultSet myRs = null;
	public Statement myStmt2 = null;
	public ResultSet myRs2 = null;
	public Statement myStmt3 = null;
	public ResultSet myRs3 = null;
	int update;
	ArrayList<String> designation = new ArrayList();
	ArrayList<String> category = new ArrayList();
	ArrayList<String> coursecate = new ArrayList();
	String designations[] = null;
	String cateselected = "";
	String categories[] = null;
	String Description="";
	String Advisor="";
	String Advisor_email="";
	String Designation_name = "";
	String requirement="";
	String pjname;
	String major_requirement[]=new String[45];
	String year_requirement[] = new String[5];
	String Dept_requirement[]=new String[9];
	int estnumber;
	private JTextField textField;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new Add_project();
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
					window = new Add_project(con);
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
	public Add_project() {
		initialize();
	}
	
	public Add_project(Connection con) {
		this.con=con;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 750);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblAddAProject = new JLabel("Add a Project");
		lblAddAProject.setBounds(181, 20, 104, 16);
		frame.getContentPane().add(lblAddAProject);
		
		JLabel label = new JLabel("Project name: ");
		label.setBounds(31, 48, 104, 16);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("Description:");
		label_1.setBounds(31, 130, 104, 16);
		frame.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("Designation:");
		label_2.setBounds(53, 500, 86, 16);
		frame.getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("Category:");
		label_3.setBounds(31, 234, 104, 16);
		frame.getContentPane().add(label_3);
		
		JLabel lblMajorRequirement = new JLabel("Year  Requirement:");
		lblMajorRequirement.setBounds(53, 581, 160, 16);
		frame.getContentPane().add(lblMajorRequirement);
		
		JLabel label_5 = new JLabel("Est number of student:");
		label_5.setBounds(53, 541, 160, 16);
		frame.getContentPane().add(label_5);
		
		JLabel lblAdvisor = new JLabel("Advisor");
		lblAdvisor.setBounds(31, 79, 61, 16);
		frame.getContentPane().add(lblAdvisor);
		
		JLabel lblAdvisorEmail = new JLabel("Advisor Email");
		lblAdvisorEmail.setBounds(31, 102, 86, 16);
		frame.getContentPane().add(lblAdvisorEmail);
		
		JLabel lblYearRequirement = new JLabel("Major Requirement:");
		lblYearRequirement.setBounds(53, 611, 144, 16);
		frame.getContentPane().add(lblYearRequirement);
		
		JLabel lblDepartmentRequirement = new JLabel("Department Requirement:");
		lblDepartmentRequirement.setBounds(53, 641, 189, 16);
		frame.getContentPane().add(lblDepartmentRequirement);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Adminfunction adminview = new Adminfunction(con);
				adminview.show();
				window.frame.setVisible(false);
				window=null;
			}
		});
		btnBack.setBounds(80, 693, 117, 29);
		frame.getContentPane().add(btnBack);
		
				
		JEditorPane editorPane = new JEditorPane();
		
		editorPane.setBounds(166, 48, 100, 16);
		frame.getContentPane().add(editorPane);
		

		JEditorPane editorPane_1 = new JEditorPane();
		editorPane_1.setBounds(166, 79, 100, 16);
		frame.getContentPane().add(editorPane_1);
		
		
		JEditorPane editorPane_2 = new JEditorPane();
		editorPane_2.setBounds(166, 102, 100, 16);
		frame.getContentPane().add(editorPane_2);
		JEditorPane editorPane_4 = new JEditorPane();
		editorPane_4.setBounds(207, 541, 100, 16);
		frame.getContentPane().add(editorPane_4);
		
		
		try {
			myStmt = con.createStatement();
			myRs = myStmt.executeQuery("select Name from Designation WHERE true ");
			while(myRs.next()) {
				designation.add(myRs.getString("Name")) ;
			}
			designations = new String[designation.size() +1 ];
			Iterator<String> iterator = designation.iterator();
			int i = 1;
			while(iterator.hasNext()){
			    designations[i++] = (String) iterator.next();	
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		

		
		try {
			//con = DriverManager.getConnection("jdbc:mysql://academic-mysql.cc.gatech.edu/cs4400_Team_86", "cs4400_Team_86","KpKnhohb");
			myStmt = con.createStatement();
			myRs = myStmt.executeQuery("select Name from Category WHERE true ");
			while(myRs.next()) {
				category.add(myRs.getString("Name")) ;
			}
			categories = new String[category.size() +1 ];
			Iterator<String> iterator = category.iterator();
			int i = 1;
			while(iterator.hasNext()){
			    categories[i++] = (String) iterator.next();	
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		JComboBox comboBox = new JComboBox(categories);
		comboBox.setBounds(147, 230, 214, 27);
		frame.getContentPane().add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox(designations);
		comboBox_1.setBounds(169, 489, 208, 27);
		frame.getContentPane().add(comboBox_1);
		year_requirement[0]="none";
		year_requirement[1]="Freshman students only";
		year_requirement[2]="Sophomore students only";
		year_requirement[3]="Junior students only";
		year_requirement[4]="Senior students only";
		
		JComboBox comboBox_2 = new JComboBox(year_requirement);
		comboBox_2.setBounds(217, 577, 207, 27);
		frame.getContentPane().add(comboBox_2);
		try {
			myStmt = con.createStatement();
			myRs = myStmt.executeQuery("select Name from Category WHERE true ");
			while(myRs.next()) {
				category.add(myRs.getString("Name")) ;
			}
			categories = new String[category.size() +1 ];
			Iterator<String> iterator = category.iterator();
			int i = 1;
			while(iterator.hasNext()){
			    categories[i++] = (String) iterator.next();	
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			myStmt = con.createStatement();
			myRs = myStmt.executeQuery("select Name from Major");
			major_requirement[0]="none";
			int xxx=1;
			while(myRs.next()) {
				major_requirement[xxx]=myRs.getString("Name")+" students only";
				xxx=xxx+1;
			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		JComboBox comboBox_3 = new JComboBox(major_requirement);
		comboBox_3.setBounds(227, 607, 197, 27);
		frame.getContentPane().add(comboBox_3);
		
		try {
			myStmt = con.createStatement();
			myRs = myStmt.executeQuery("select Name from Department");
			major_requirement[0]="none";
			int xxx=1;
			while(myRs.next()) {
				major_requirement[xxx]=myRs.getString("Name")+" students only";
				xxx=xxx+1;
			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		JComboBox comboBox_4 = new JComboBox(major_requirement);
		comboBox_4.setBounds(236, 637, 188, 27);
		frame.getContentPane().add(comboBox_4);
		
		
		
		
		JTextArea textArea = new JTextArea();
		
		textArea.setBounds(147, 130, 255, 88);
		frame.getContentPane().add(textArea);
		
		JTextArea textArea_1 = new JTextArea();
		
		textArea_1.setBounds(135, 316, 197, 161);
		frame.getContentPane().add(textArea_1);
		
		JButton btnAddNewCategory = new JButton("Add new Category");
		btnAddNewCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				coursecate.add(comboBox.getSelectedItem().toString());
				cateselected="";
				for(String xx:coursecate){
					cateselected= cateselected+xx+"\n";
				}
				textArea_1.setText(cateselected);
			}
		});
		btnAddNewCategory.setBounds(135, 269, 197, 29);
		frame.getContentPane().add(btnAddNewCategory);
		
		
		
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!editorPane_4.getText().equals("")){
					estnumber = Integer.parseInt(editorPane_4.getText());
				}else{
					textField.setText("fill in all the blink pls!");
					return;
				}
				
				
				Advisor_email = editorPane_2.getText();
				Advisor = editorPane_1.getText();
				pjname = editorPane.getText();
				Description = textArea.getText();
				//这里还要调整
				Designation_name = comboBox_1.getSelectedItem().toString();
				
				int update;
				
				if(Advisor_email.equals("") || Advisor.equals("") || pjname.equals("") ||Description.equals("")||Designation_name.equals("")){
					textField.setText("fill in all the blink pls!");
					return;
				}
				
				
				try {
					myRs = myStmt.executeQuery("select * from Project where Name = " + "'"+pjname+"'");
					if(myRs.next()){
						textField.setText("project already in database!");
						return;
					}
					
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				
				try {
					myStmt = con.createStatement();
					update = myStmt.executeUpdate("INSERT INTO Project (Name,Description,Advisor_email,Advisor_name,EstNumOfStudent,Designation_name) VALUES ("+"'"+pjname+"', "+"'"+Description+"', "+"'"+Advisor_email+"', "+ "'"+Advisor+"', "+"'"+estnumber+"', "+ "'"+Designation_name+"');"  );
					textField.setText("project added!");
					for(String cate:coursecate){
						update = myStmt.executeUpdate("INSERT INTO Project_is_category (Project_name,Category_name) VALUES ("+"'"+pjname+"', "+"'"+cate+"');"  );
						
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				try {
					
					myStmt = con.createStatement();
					update = myStmt.executeUpdate("INSERT INTO Project_requirement (Name,Requirement) VALUES ("+"'"+pjname+"', "+"'"+comboBox_4.getSelectedItem().toString()+"');");
					update = myStmt.executeUpdate("INSERT INTO Project_requirement (Name,Requirement) VALUES ("+"'"+pjname+"', "+"'"+comboBox_2.getSelectedItem().toString()+"');");
					update = myStmt.executeUpdate("INSERT INTO Project_requirement (Name,Requirement) VALUES ("+"'"+pjname+"', "+"'"+comboBox_3.getSelectedItem().toString()+"');");
					
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
				
				
				
				
				
			}
		});
		btnSubmit.setBounds(307, 693, 117, 29);
		frame.getContentPane().add(btnSubmit);
		
		textField = new JTextField();
		textField.setBounds(46, 669, 356, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		
		
	}
	
	
	
}
