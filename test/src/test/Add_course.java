package test;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JEditorPane;
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
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTextArea;

public class Add_course {
	public Statement myStmt = null;
	public ResultSet myRs = null;
	public Statement myStmt2 = null;
	public ResultSet myRs2 = null;
	public Statement myStmt3 = null;
	public ResultSet myRs3 = null;
	
	ArrayList<String> designation = new ArrayList();
	ArrayList<String> category = new ArrayList();
	ArrayList<String> coursecate = new ArrayList();
	String designations[] = null;
	String cateselected = "";
	String categories[] = null;
	String course_number="";
	String coursename ="";
	int estnumber=0;
	String instructor = "";
	String Designation_name="";
	
	public Connection con;
	private JFrame frame;
	public static Add_course window;
	private JTextField textField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new Add_course();
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
					window = new Add_course(con);
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
	public Add_course() {
		initialize();
	}
	
	public Add_course(Connection con) {
		this.con=con;
		initialize();
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblAddACourse = new JLabel("Add a course");
		lblAddACourse.setBounds(167, 25, 89, 16);
		frame.getContentPane().add(lblAddACourse);
		
		JLabel lblNewLabel = new JLabel("Course number");
		lblNewLabel.setBounds(34, 74, 142, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblCourseName = new JLabel("Course name");
		lblCourseName.setBounds(34, 102, 89, 16);
		frame.getContentPane().add(lblCourseName);
		
		JLabel lblInstructor = new JLabel("Instructor");
		lblInstructor.setBounds(34, 130, 88, 16);
		frame.getContentPane().add(lblInstructor);
		
		JLabel lblDesignation = new JLabel("category");
		lblDesignation.setBounds(34, 201, 126, 16);
		frame.getContentPane().add(lblDesignation);
		
		JLabel lblEstNumberOf = new JLabel("Est number of students");
		lblEstNumberOf.setBounds(34, 361, 160, 16);
		frame.getContentPane().add(lblEstNumberOf);
		
		JEditorPane editorPane = new JEditorPane();
		JTextArea textArea = new JTextArea();
		
		textArea.setBounds(86, 270, 308, 61);
		frame.getContentPane().add(textArea);
		
		editorPane.setBounds(177, 74, 100, 16);
		frame.getContentPane().add(editorPane);
		
		JEditorPane editorPane_1 = new JEditorPane();
		
		
		editorPane_1.setBounds(177, 102, 100, 16);
		frame.getContentPane().add(editorPane_1);
		
		JEditorPane editorPane_2 = new JEditorPane();
		
		
		editorPane_2.setBounds(177, 130, 100, 16);
		frame.getContentPane().add(editorPane_2);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					Adminfunction adminview = new Adminfunction(con);
					adminview.show();
					window.frame.setVisible(false);
					window=null;
				
			}
		});
		btnBack.setBounds(57, 427, 117, 29);
		frame.getContentPane().add(btnBack);
		
		
		
		JEditorPane editorPane_3 = new JEditorPane();
		
		editorPane_3.setBounds(268, 361, 100, 16);
		frame.getContentPane().add(editorPane_3);
		
		JLabel label = new JLabel("Designation");
		label.setBounds(34, 158, 126, 16);
		frame.getContentPane().add(label);
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
		
		JComboBox comboBox = new JComboBox(designations);
		comboBox.setBounds(173, 158, 154, 27);
		frame.getContentPane().add(comboBox);
		try {
			con = DriverManager.getConnection("jdbc:mysql://academic-mysql.cc.gatech.edu/cs4400_Team_86", "cs4400_Team_86","KpKnhohb");
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
		
		
		
		JComboBox comboBox_1 = new JComboBox(categories);
		comboBox_1.setBounds(167, 197, 160, 27);
		frame.getContentPane().add(comboBox_1);
		
		textField = new JTextField();
		textField.setBounds(34, 389, 360, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnAddNewCategory = new JButton("Add new category");
		btnAddNewCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				coursecate.add(comboBox_1.getSelectedItem().toString());
				cateselected="";
				for(String xx:coursecate){
					cateselected= cateselected+xx+"\n";
				}
				textArea.setText(cateselected);
				
			}
		});
		btnAddNewCategory.setBounds(84, 229, 284, 29);
		frame.getContentPane().add(btnAddNewCategory);
		
		JLabel lblCategory = new JLabel("category:");
		lblCategory.setBounds(20, 270, 126, 16);
		frame.getContentPane().add(lblCategory);
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!editorPane_3.getText().equals("")){
					estnumber = Integer.parseInt(editorPane_3.getText());
				}else{
					textField.setText("fill in all the blink pls!");
					return;
				}
				
				
				
				coursename=editorPane_1.getText(); 
				course_number =editorPane.getText();
				instructor =editorPane_2.getText();
				Designation_name = comboBox.getSelectedItem().toString();
				int update;
				
				if(coursename.equals("") || course_number.equals("") || instructor.equals("") ||Designation_name.equals("")){
					textField.setText("fill in all the blink pls!");
					return;
				}
				
				
				
				
				
				
				
				try {
					myRs = myStmt.executeQuery("select * from Course where Course_number = " + "'"+course_number+"'");
					if(myRs.next()){
						textField.setText("course already in database!");
						return;
					}
					
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				
				try {
					myStmt = con.createStatement();
					update = myStmt.executeUpdate("INSERT INTO Course (Name,Course_number,Instructor,EstNumOfStudent,Designation_name) VALUES ("+"'"+coursename+"', "+"'"+course_number+"', "+"'"+instructor+"', "+ "'"+estnumber+"', "+"'"+Designation_name+"');"  );
					textField.setText("course added!");
					for(String cate:coursecate){
						update = myStmt.executeUpdate("INSERT INTO Course_is_category (Course_name,Category_name) VALUES ("+"'"+coursename+"', "+"'"+cate+"');"  );
						
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnSubmit.setBounds(277, 427, 117, 29);
		frame.getContentPane().add(btnSubmit);
		
		
		
		
		
	}
}
