package test;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class ReviewApplication {

	private JFrame frame;
	public static ReviewApplication window;
	public Connection con;
	ArrayList<String> populor_pj = new ArrayList<String>();
	ArrayList<String> usernameary = new ArrayList<String>();
	ArrayList<String> studentinfomajor = new ArrayList<String>();
	ArrayList<String> studentinfoyear = new ArrayList<String>();
	ArrayList<String> pdname = new ArrayList<String>();
	ArrayList<String> pdusername = new ArrayList<String>();
	ArrayList<String> states = new ArrayList<String>();
	String pendingproject[] = new String[200];
	Statement myStmt = null;
	ResultSet myRs = null;
	String resultString="";
	Statement stmt = null;

	int update;
	int choice = -1;
	private JTextField textField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new ReviewApplication();
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
					window = new ReviewApplication(con);
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
	public ReviewApplication() {
		initialize();
	}
	public ReviewApplication(Connection con) {
		this.con=con;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 650, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblApplication = new JLabel("Application");
		lblApplication.setBounds(223, 24, 130, 16);
		frame.getContentPane().add(lblApplication);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Adminfunction adminview = new Adminfunction(con);
				adminview.show();
				window.frame.setVisible(false);
				window=null;
			}
		});
		btnNewButton.setBounds(6, 525, 117, 29);
		frame.getContentPane().add(btnNewButton);
		textField = new JTextField();
		
		textField.setBounds(22, 473, 130, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 79, 605, 311);
		frame.getContentPane().add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		
		scrollPane.setRowHeaderView(textArea);
		
		JLabel lblNewLabel = new JLabel("project name // applicant’s major// applicant’s year // application status // username.");
		lblNewLabel.setBounds(22, 47, 605, 16);
		frame.getContentPane().add(lblNewLabel);

		try {
			myStmt = con.createStatement();
			myRs = myStmt.executeQuery("select * from Apply");
			while(myRs.next()){
				populor_pj.add(myRs.getString("Project_name"));
				usernameary.add(myRs.getString("Student_name"));
				states.add(myRs.getString("Status"));
			}
			stmt = con.createStatement();
			myRs = myStmt.executeQuery("select * from Apply where Status = 'pending'");
			int counter = 1;
			pendingproject[0]="";
			while(myRs.next()){
				pendingproject[counter] = myRs.getString("Project_name")+"      username: "+myRs.getString("Student_name");
				pdname.add(myRs.getString("Project_name"));
				pdusername.add(myRs.getString("Student_name"));
				counter++;
			}
			
			
			for(int i=0;i<populor_pj.size();i++){
				myRs = myStmt.executeQuery("select * from User where Username = '"+usernameary.get(i)+"'");
				if(myRs.next()){
					studentinfomajor.add(myRs.getString("Major"));
					
					if(myRs.getString("Year").equals("1")){
						studentinfoyear.add("freshman");
					}else if(myRs.getString("Year").equals("2")){
						studentinfoyear.add("sophomore");
					}else if(myRs.getString("Year").equals("3")){
						studentinfoyear.add("junior");
					}else if(myRs.getString("Year").equals("4")){
						studentinfoyear.add("senior");
					}	
				}
				
				
				resultString = resultString+populor_pj.get(i)+" // "+studentinfomajor.get(i) + "  // "+ studentinfoyear.get(i) + "  //  "+ states.get(i)+ "   //  "+ usernameary.get(i)+"\n";
				
			}
			
			
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		textArea.setText(resultString);
		
		JComboBox comboBox = new JComboBox(pendingproject);
		comboBox.setBounds(22, 422, 487, 27);
		frame.getContentPane().add(comboBox);
		
		
		JButton btnAccept = new JButton("Accept");
		btnAccept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				choice = comboBox.getSelectedIndex();
				if(choice!=0 && choice!=-1){
					System.out.println(choice);
					try {
						stmt.executeUpdate("UPDATE Apply set Status = 'accepted' WHERE Project_name = '"+ pdname.get(choice-1)+"'" + "and Student_name = '"+ pdusername.get(choice-1)+"'");
						textField.setText("Done!");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
					
					
					
					
				}else{
					return;
				}
			}
		});
		btnAccept.setBounds(298, 525, 117, 29);
		frame.getContentPane().add(btnAccept);
		
		JButton btnReject = new JButton("Reject");
		btnReject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				choice = comboBox.getSelectedIndex();
				if(choice!=0 && choice!=-1){
					System.out.println(choice);
					try {
						stmt.executeUpdate("UPDATE Apply set Status = 'rejected' WHERE Project_name = '"+ pdname.get(choice-1)+"'" + "and Student_name = '"+ pdusername.get(choice-1)+"'");
						textField.setText("Done!");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
					
					
					
					
				}else{
					return;
				}
			}
		});
		btnReject.setBounds(433, 525, 117, 29);
		frame.getContentPane().add(btnReject);
		
		
		
		
	}
}
