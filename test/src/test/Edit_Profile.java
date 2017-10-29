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
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.AbstractAction;
import javax.swing.Action;


public class Edit_Profile {
	public String username;
	private JFrame frame;
	public static Edit_Profile window;
	public Connection con;
	private JTextField textField;
	Statement myStmt = null;
	ResultSet myRs = null;
	ArrayList<String> major = new ArrayList();
	String majors[] = null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new Edit_Profile();
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
					window = new Edit_Profile(username,con);
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
	public Edit_Profile() {
		initialize();
	}
	public Edit_Profile(String username, Connection con) {
		this.con=con;
		this.username = username;
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
		
		JLabel lblNewLabel = new JLabel("Edit Profile");
		lblNewLabel.setBounds(192, 43, 148, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Major");
		lblNewLabel_1.setBounds(58, 80, 61, 16);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Year ");
		lblNewLabel_2.setBounds(58, 122, 61, 16);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Department ");
		lblNewLabel_3.setBounds(58, 160, 78, 16);
		frame.getContentPane().add(lblNewLabel_3);
		
		textField = new JTextField();
		textField.setBounds(206, 154, 238, 28);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://academic-mysql.cc.gatech.edu/cs4400_Team_86", "cs4400_Team_86","KpKnhohb");
			myStmt = con.createStatement();
			myRs = myStmt.executeQuery("select Name from Major WHERE true ");
			while(myRs.next()) {
				major.add(myRs.getString("Name")) ;
			}
			majors = new String[major.size() +1];
			Iterator<String> iterator = major.iterator();
			int i = 1;
			while(iterator.hasNext()){
			    majors[i++] = (String) iterator.next();	
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		JComboBox comboBox_1 = new JComboBox(majors);
		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                String myMajor = comboBox_1.getSelectedItem().toString();
                try {
    				con = DriverManager.getConnection("jdbc:mysql://academic-mysql.cc.gatech.edu/cs4400_Team_86", "cs4400_Team_86","KpKnhohb");
    				myStmt = con.createStatement();
    				myRs = myStmt.executeQuery("Select DISTINCT Dept_name from Major WHERE Name = " +
    				"'" + myMajor+ "'");
    				if(myRs.next()){
    				textField.setText(myRs.getString("Dept_name"));
    				}
    				
    			} catch (SQLException e1) {
    				// TODO Auto-generated catch block
    				e1.printStackTrace();
    			}
			}
		});
		comboBox_1.setBounds(206, 79, 238, 17);
		frame.getContentPane().add(comboBox_1);
		
		String year[] = {null, "1", "2", "3", "4"};
		JComboBox comboBox = new JComboBox(year);
		comboBox.setBounds(206, 118, 238, 28);
		frame.getContentPane().add(comboBox);
		
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			String myMajor = comboBox_1.getSelectedItem().toString();
			String myYear = comboBox.getSelectedItem().toString();
			String sqlCommand = "UPDATE User SET Major = " + "'" + myMajor + "'"+
			", Year = " +"'" + myYear + "'" + " WHERE Username = " + "'" +username+"'";
			try {
				con = DriverManager.getConnection("jdbc:mysql://academic-mysql.cc.gatech.edu/cs4400_Team_86", "cs4400_Team_86","KpKnhohb");
				myStmt = con.createStatement();
				int result = myStmt.executeUpdate(sqlCommand);
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Me me = new Me(username,con);
			me.show();
			window.frame.setVisible(false);
			window = null;
			}
		});
		btnNewButton.setBounds(172, 209, 117, 29);
		frame.getContentPane().add(btnNewButton);
	}


}


