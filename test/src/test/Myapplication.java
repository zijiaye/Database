package test;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class Myapplication {
	public String username;
	private JFrame frame;
	public static Myapplication window;
	public Connection con;
	public String myresult="";
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new Myapplication();
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
					window = new Myapplication(username,con);
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
	public Myapplication() {
		initialize();
	}
	
	public Myapplication(String username,Connection con) {
		this.con=con;
		this.username = username;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Statement myStmt = null;
		ResultSet myRs = null;
		frame = new JFrame();
		frame.setBounds(100, 100, 550, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("My application");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblNewLabel.setBounds(157, 32, 147, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnBack = new JButton(" Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Me me = new Me(username,con);	
				me.show();
			window.frame.setVisible(false);
			window = null;
			}
		});
		btnBack.setBounds(157, 443, 117, 29);
		frame.getContentPane().add(btnBack);
		
		JTextArea textArea = new JTextArea();
		
		textArea.setBounds(35, 122, 460, 298);
		frame.getContentPane().add(textArea);
		
		JLabel lblNewLabel_1 = new JLabel("Date  / /                              Project Name  //                        States");
		lblNewLabel_1.setBounds(35, 94, 460, 16);
		frame.getContentPane().add(lblNewLabel_1);
		try {
			myStmt = con.createStatement();
			myRs = myStmt.executeQuery("select * from Apply where Student_name = " + "'"+username+"'");
			while(myRs.next()){
				myresult = myresult+ myRs.getString("Date")+ "    //    " + myRs.getString("Project_name") + "     //      "+ myRs.getString("Status")+"\n";
			}
			textArea.setText(myresult);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
}
