package test;




import java.awt.EventQueue;




import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.util.Arrays;
import java.util.Comparator;


public class Application_report {
	public String username;
	private JFrame frame;
	private JTable table;
	public static Application_report window;
	public Connection con;
	Statement myStmt = null;
	ResultSet myRs = null;
	String projectName;
	String numApp;
	String accRate;
	String major;
	int accept;
	int Applicant;
	String [][]report = {{"kin","A","A","A"},
			{"A","A","A","A"}
			};




	public String[] title = {"Project","Applicants #", "accept rate","top 3 major"};
	private JTextField textField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new Application_report();
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
					window = new Application_report(con);
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
	public Application_report() {
		initialize();
	}
	
	public Application_report(Connection con) {
		this.con=con;
		initialize();
	}
	
	public Application_report(String username) {
		this.username= username;
		initialize();
	}




	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 850, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblApplicationReport = new JLabel("Application report");
		lblApplicationReport.setBounds(168, 6, 127, 16);
		frame.getContentPane().add(lblApplicationReport);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Adminfunction adminview = new Adminfunction(con);
				adminview.show();
				window.frame.setVisible(false);
				window=null;
			}
		});
		btnNewButton.setBounds(34, 223, 117, 29);
		frame.getContentPane().add(btnNewButton);
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://academic-mysql.cc.gatech.edu/cs4400_Team_86", "cs4400_Team_86","KpKnhohb");
			myStmt = con.createStatement();
			myRs = myStmt.executeQuery("Select DISTINCT Student_name, COUNT(*) from Apply WHERE true");
			while(myRs.next()){
				numApp = myRs.getString("COUNT(*)");
				Applicant = Integer.parseInt(numApp);
				myStmt = con.createStatement();	
			}
			myStmt = con.createStatement();
			myRs = myStmt.executeQuery("Select DISTINCT Student_name, COUNT(*) from Apply WHERE Status = " + "'"
			+ "accepted" + "'");
			while(myRs.next()) {
			accRate = myRs.getString("COUNT(*)");
			accept = Integer.parseInt(accRate);
			}
			
			
			ArrayList<String> project = new ArrayList<>();
			
			//the table content,number of Applicants
			myStmt = con.createStatement();
			myRs = myStmt.executeQuery("Select DISTINCT Project_name from Apply WHERE true");
			while (myRs.next()) {
                project.add(myRs.getString("Project_name"));    				
			}
			report = new String[project.size()][4];
			for (int n = 0; n < report.length; n++) {
				report[n][3] = " ";
			}


			for (int i = 0; i < project.size(); i++) {
				report[i][0] = project.get(i);
			}
			
			String cerProject;
			String noApp = "";
			for (int k = 0; k < project.size(); k++) {
				cerProject = report[k][0];
				myStmt = con.createStatement();
				
				myRs = myStmt.executeQuery("Select DISTINCT Student_name, COUNT(*) from Apply where Project_name = "
						+ "'" + cerProject + "'");
				while(myRs.next()) {
					noApp = myRs.getString("COUNT(*)");
					report[k][1] = noApp;
				}
				
				//student accepted
				myStmt = con.createStatement();
				myRs = myStmt.executeQuery("Select DISTINCT Student_name, COUNT(*) from Apply where Project_name = "
						+ "'" + cerProject + "'" + "And Status = 'accepted' ");
				while(myRs.next()) {
					String accepted = myRs.getString("COUNT(*)");
					double app = Double.parseDouble(noApp);
					double acc = Double.parseDouble(accepted);
					double res= acc/app;
					report[k][2] = "" + (res);
				}
				
				//top 3 major
				myStmt = con.createStatement();
				myRs = myStmt.executeQuery("Select Major, COUNT(*) FROM User, Apply WHERE Username = Student_name And Project_name =" +
				"'" + cerProject + "'" + "GROUP BY Major "+" ORDER BY COUNT(*) DESC");
				int topcount = 0;
				
				while(myRs.next() && topcount < 3) {
					String topMajor = myRs.getString("Major");
					report[k][3] += " "+topMajor;
                    topcount++;
				}
				
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		


	
	Double[] dou = new Double[report.length];
	for(int m = 0; m < dou.length; m++) {
		dou[m] = Double.parseDouble(report[m][2]);
	}


	String[] temp =null;
	double tempDou = 0.0;
	for (int t = 0; t < dou.length-1; t++) {
	for(int n = 0; n < dou.length-1; n++) {
		if(dou[n] - (dou[n+1]) < 0) {
			tempDou = dou[n];
			dou[n] = dou[n+1];
			dou[n+1] = tempDou;
		    temp = report[n];
		    report[n] = report[n+1];
			report[n+1] = temp;
		}
	}
}


		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 79, 838, 126);
		frame.getContentPane().add(scrollPane);
		table = new JTable(report, title);
		table.getColumnModel().getColumn(0).setPreferredWidth(120);
		table.getColumnModel().getColumn(1).setPreferredWidth(1);
		table.getColumnModel().getColumn(2).setPreferredWidth(1);
		table.getColumnModel().getColumn(3).setPreferredWidth(380);
		scrollPane.setViewportView(table);
		table.setFillsViewportHeight(true);
		




		JLabel lblNewLabel = new JLabel("" + numApp + " applicants in total, " + accept + " accepted applicants");
		lblNewLabel.setBounds(34, 38, 387, 29);
		frame.getContentPane().add(lblNewLabel);
		
		
		
	}
}






