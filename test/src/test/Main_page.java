package test;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import java.awt.Scrollbar;
//import java.awt.List;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JRadioButton;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JTable;
import java.awt.Button;








public class Main_page {








	private JFrame frame;
	public static Main_page window;
	public String username;
	private JTextField txtJ;
	public Connection con;
	public ButtonGroup bg = new ButtonGroup();
	private final Action action = new SwingAction();
	private final ButtonGroup buttonGroup = new ButtonGroup();
	Statement myStmt = null;
	ResultSet myRs = null;
	ArrayList<String> category = new ArrayList();
	ArrayList<String> designation = new ArrayList();
	ArrayList<String> major = new ArrayList();
	String categories[] = null;
	String designations[] = null;
	String majors[] = null;
	private JTable table;
	String seleCourse = null;
	String seleProject = null;
	String[][] arr;
	
	public Main_page(String username,Connection con) {
		this.con=con;
		this.username = username;
		initialize();
		
	}
	public Main_page() {
		
		initialize();
		
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new Main_page();
					window.frame.setVisible(true);
					//System.out.println(username);
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
					window = new Main_page(username,con);
					window.frame.setVisible(true);
					System.out.println(username);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */

	/**
	 * Initialize the contents of the frame.
	 * @return 
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 550, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		try {
//			con = DriverManager.getConnection("jdbc:mysql://academic-mysql.cc.gatech.edu/cs4400_Team_86", "cs4400_Team_86","KpKnhohb");
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
		JList list = new JList(categories);
		list.setBounds(284, 54, 244, 159);
		frame.getContentPane().add(list);
		list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		
	    String[] title = {"Name", "Type"};
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(33, 270, 468, 100);
		DefaultTableModel model = new DefaultTableModel(title,0);
		table = new JTable(model);
		table.setModel(model);
		table.setRowSelectionAllowed(true);
		scrollPane.setViewportView(table);
		frame.getContentPane().add(scrollPane);
		table.setFillsViewportHeight(true);
		
		JLabel lblMainPage = new JLabel("Main Page");
		lblMainPage.setBounds(231, 31, 115, 16);
		frame.getContentPane().add(lblMainPage);
		
		JButton btnMe = new JButton("Me");
		btnMe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Me me = new Me(username,con);	
					me.show();
				window.frame.setVisible(false);
				window = null;
			}
		});
		btnMe.setBounds(33, 48, 117, 29);
		frame.getContentPane().add(btnMe);
		
		txtJ = new JTextField(null);
		txtJ.setBounds(75, 101, 130, 26);
		frame.getContentPane().add(txtJ);
		txtJ.setColumns(10);
		
		JLabel lblTitle = new JLabel("Title");
		lblTitle.setBounds(33, 106, 45, 16);
		frame.getContentPane().add(lblTitle);
		
		JLabel lblCategory = new JLabel("Category");
		lblCategory.setBounds(217, 106, 61, 16);
		frame.getContentPane().add(lblCategory);


		
		
		JLabel lblDesignation = new JLabel("Designation");
		lblDesignation.setBounds(18, 163, 87, 16);
		frame.getContentPane().add(lblDesignation);
		
		JLabel lblMajor = new JLabel("Major");
		lblMajor.setBounds(18, 215, 61, 16);
		frame.getContentPane().add(lblMajor);
		
		JLabel lblYear = new JLabel("Year");
		lblYear.setBounds(18, 190, 61, 16);
		frame.getContentPane().add(lblYear);
		
		
		
		try {
//			con = DriverManager.getConnection("jdbc:mysql://academic-mysql.cc.gatech.edu/cs4400_Team_86", "cs4400_Team_86","KpKnhohb");
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
		comboBox.setBounds(117, 160, 87, 20);
		frame.getContentPane().add(comboBox);
		
		//major combo
		try {
//			con = DriverManager.getConnection("jdbc:mysql://academic-mysql.cc.gatech.edu/cs4400_Team_86", "cs4400_Team_86","KpKnhohb");
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
		comboBox_1.setBounds(117, 215, 88, 16);
		frame.getContentPane().add(comboBox_1);
		
		String year[] = {null, "Freshman", "Sophomore", "Junior", "Senior"};
		JComboBox comboBox_2 = new JComboBox(year);
		comboBox_2.setBounds(117, 188, 87, 18);
		frame.getContentPane().add(comboBox_2);
		
		
		JRadioButton rdbtnProject = new JRadioButton("Project");
		bg.add(rdbtnProject);
		rdbtnProject.setBounds(240, 219, 82, 16);
		frame.getContentPane().add(rdbtnProject);
		
		JRadioButton rdbtnCourse = new JRadioButton("Course");
		bg.add(rdbtnCourse);
		rdbtnCourse.setBounds(317, 215, 82, 26);
		frame.getContentPane().add(rdbtnCourse);
		
		JRadioButton rdbtnBoth = new JRadioButton("Both");
		bg.add(rdbtnBoth);
		rdbtnBoth.setBounds(396, 215, 76, 23);
		frame.getContentPane().add(rdbtnBoth);
		
		JButton btnResetFilter = new JButton("Reset Filter");
		btnResetFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    txtJ.setText(null);
			    model.setRowCount(0);
			    list.clearSelection();
			    comboBox.setSelectedItem(null);
			    comboBox_1.setSelectedItem(null);
			    comboBox_2.setSelectedItem(null);
			    bg.clearSelection();
			}
		});
		btnResetFilter.setAction(action);
		buttonGroup.add(btnResetFilter);
		btnResetFilter.setBounds(247, 240, 117, 29);
		frame.getContentPane().add(btnResetFilter);
		
		
		
		JButton btnApplyFilter = new JButton("Apply Filter");
		btnApplyFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    String sqlcommand;
			    if (rdbtnCourse.isSelected()) {
			    	try {
//						con = DriverManager.getConnection("jdbc:mysql://academic-mysql.cc.gatech.edu/cs4400_Team_86", "cs4400_Team_86","KpKnhohb");
						myStmt = con.createStatement();
						sqlcommand = selectCourse(txtJ, list, comboBox);
						myRs = myStmt.executeQuery(sqlcommand);
						ArrayList<String> course = new ArrayList<>();
				    	while(myRs.next()) {
//							course.add(myRs.getString("Name")) ;
							model.addRow(new Object[] {myRs.getString("Name"), "course"});
						}
				    	table.setModel(model);
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			    	
			    } else if(rdbtnProject.isSelected()) {
			    	try {
//						con = DriverManager.getConnection("jdbc:mysql://academic-mysql.cc.gatech.edu/cs4400_Team_86", "cs4400_Team_86","KpKnhohb");
						myStmt = con.createStatement();
						sqlcommand = selectProject(comboBox, comboBox_1, comboBox_2, 
								  txtJ, list);
						myRs = myStmt.executeQuery(sqlcommand);
						ArrayList<String> project = new ArrayList<>();
				    	while(myRs.next()) {
//							project.add(myRs.getString("Name")) ;
							model.addRow(new Object[] {myRs.getString("Name"), "project"});
						}
				    	table.setModel(model);


					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}  
				
			    } else {
			    	try {


//						con = DriverManager.getConnection("jdbc:mysql://academic-mysql.cc.gatech.edu/cs4400_Team_86", "cs4400_Team_86","KpKnhohb");
						myStmt = con.createStatement();
						sqlcommand = selectCourse(txtJ, list, comboBox);
						myRs = myStmt.executeQuery(sqlcommand);
				    	while(myRs.next()) {
				    		model.addRow(new Object[] {myRs.getString("Name"), "course"});
						}


						myStmt = con.createStatement();
						sqlcommand = selectProject(comboBox, comboBox_1, comboBox_2, 
								  txtJ, list);
						myRs = myStmt.executeQuery(sqlcommand);
				    	while(myRs.next()) {
				    		model.addRow(new Object[] {myRs.getString("Name"), "project"});
						}
				    	table.setModel(model);


						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}  
			    }
			}
		});
		btnApplyFilter.setBounds(368, 240, 117, 29);
		frame.getContentPane().add(btnApplyFilter);
		bg.add(rdbtnBoth);
		
		JButton btnView = new JButton("View");
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = table.getSelectedRow();
				String type = (String)table.getValueAt(index, 1);


				if(type == "course") {
					seleCourse = (String)table.getValueAt(index, 0);
					Viewcourse viewcourse = new Viewcourse(con,seleCourse,username);	
					viewcourse.show();
					model.setRowCount(0);
				} else {
					seleProject = (String)table.getValueAt(index, 0);
					Viewthepeoject viewthepeoject = new Viewthepeoject(con,seleProject,username);	
					viewthepeoject.show();
				}
			    window.frame.setVisible(false);
			    window = null;
			}
		});
		btnView.setBounds(15, 240, 87, 26);
		frame.getContentPane().add(btnView);
		
		JLabel lblPleaseResetFilter = new JLabel("Please Reset Filter after view");
		lblPleaseResetFilter.setBounds(6, 6, 221, 20);
		frame.getContentPane().add(lblPleaseResetFilter);
		
		


		
		
	}
	 public String selectCourse(JTextField txtJ,JList list, JComboBox comboBox) {
		 String sql = "Select distinct Name from Course_is_category,Course Where Name = Course_name";
//			System.out.println((txtJ.getText().isEmpty()));
		    if (!(txtJ.getText().isEmpty())) {
		    	sql += " And Name = " + "'" + txtJ.getText() +"'";
			}
			if (!(comboBox.getSelectedItem() == null)) {
				sql += " AND Designation_name = "+"'" +
							comboBox.getSelectedItem().toString() + "'";	
			}
			if (!(list.getSelectedValuesList().isEmpty())){
				List objs = list.getSelectedValuesList();
				sql += " AND (";
				int k;
				for (k = 0; k < objs.size()-1;k++) {
					sql += "Category_name = " + "'" + objs.get(k) +"' OR ";
				}
				sql += " Category_name = " + "'" + objs.get(k) +"' )";
				
		}
//			System.out.println(sql);
			return sql;
		}
	 
	 public String selectProject(JComboBox comboBox,JComboBox comboBox_1, JComboBox comboBox_2, 
			 JTextField txtJ, JList list) {
			String sql = "Select DISTINCT Project.Name from Project, Project_requirement,Project_is_category"
					+ " WHERE Project.Name = Project_requirement.Name = Project_is_category.Project_name";
		    if (!(comboBox.getSelectedItem() == null)) {
		    	sql += " And Designation_name = "+"'" +
						comboBox.getSelectedItem().toString() + "'";
		        
		    }
			if (!(comboBox_1.getSelectedItem() == null)){
				sql += " And Requirement = " + "'" +
						comboBox_1.getSelectedItem().toString() + " students only ' ";
			}
			if (!(comboBox_2.getSelectedItem() == null)) {
				 sql += " AND Requirement = "+"'" +
						comboBox_2.getSelectedItem().toString() + " students only ' ";
				
			}
			if (!(txtJ.getText().isEmpty())) {
				 sql += " AND Project_name = " + "'" + txtJ.getText() + "'";
			}
			if (!(list.getSelectedValuesList().isEmpty())){
				List objs = list.getSelectedValuesList();
				sql += " AND (";
				int i;
				for (i = 0; i < objs.size()-1;i++) {
					sql += "Category_name = " + "'" + objs.get(i) +"' OR ";
				}
				sql += " Category_name = " + "'" + objs.get(i) +"' )";
				
		}
			
//			System.out.println(sql);
			return sql;
		}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "Reset Filter");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}














