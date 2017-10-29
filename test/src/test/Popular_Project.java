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
import java.awt.Scrollbar;
import java.awt.ScrollPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Popular_Project {

	private JFrame frame;
	public static Popular_Project window;
	public Connection con;
	ArrayList<String> populor_pj = new ArrayList<String>();
	ArrayList<Integer> amount = new ArrayList<Integer>();
	Statement myStmt = null;
	ResultSet myRs = null;
	String resultString="";
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new Popular_Project();
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
					window = new Popular_Project(con);
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
	public Popular_Project() {
		initialize();
	}
	
	public Popular_Project(Connection con) {
		this.con=con;
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
		
		JLabel lblNewLabel = new JLabel("Popular Project");
		lblNewLabel.setBounds(177, 29, 109, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnBack = new JButton(" Back");
		btnBack.setBounds(39, 214, 117, 29);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Adminfunction adminview = new Adminfunction(con);
				adminview.show();
				window.frame.setVisible(false);
				window=null;
			}
		});
		frame.getContentPane().add(btnBack);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(51, 78, 353, 111);
		frame.getContentPane().add(scrollPane);
		
		JTextArea txtrn = new JTextArea();
		
		scrollPane.setViewportView(txtrn);
		
		try {
			myStmt = con.createStatement();
			myRs = myStmt.executeQuery("select * from Project");
			while(myRs.next()){
				populor_pj.add(myRs.getString("Name"));
				
			}
			int counter = 0;
			for(int i=0;i<populor_pj.size();i++){
				
				myRs = myStmt.executeQuery("Select Project_name, COUNT(*) from Apply where Project_name = "
						+ "'" + populor_pj.get(i) + "'");
				if(myRs.next()) {
					amount.add(myRs.getInt("COUNT(*)"));
				}
			}
			int position=-1;
			int min_number =-1;
			for(int i=0;i<10;i++){
				for(int j=0;j<amount.size();j++){
					if(min_number<amount.get(j)){
						min_number = amount.get(j);
					position = j;
					}
				}
			resultString = resultString+populor_pj.get(position)+"    Number: "+ Integer.toString(amount.get(position))+"\n";
			populor_pj.remove(position);
			amount.remove(position);
			min_number = -1;
			position = -1;
			
			}
			
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		txtrn.setText(resultString);
		
		JLabel lblNewLabel_1 = new JLabel("Project name                   number of applicant");
		lblNewLabel_1.setBounds(51, 57, 339, 16);
		frame.getContentPane().add(lblNewLabel_1);
		
	}
}
