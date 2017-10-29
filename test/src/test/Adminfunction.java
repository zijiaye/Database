package test;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;

public class Adminfunction {
	public Connection con;
	private JFrame frame;
	public static Adminfunction window;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new Adminfunction();
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
					window = new Adminfunction(con);
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
	public Adminfunction() {
		initialize();
	}
	
	public Adminfunction(Connection con) {
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
		
		JLabel lblNewLabel = new JLabel("Choose Function");
		lblNewLabel.setBounds(162, 19, 153, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("View Application");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReviewApplication vi_app = new ReviewApplication(con);
				vi_app.show();
				window.frame.setVisible(false);
				window =null;
			}
		});
		btnNewButton.setBounds(117, 47, 210, 29);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnViewPopularProject = new JButton("View popular Project report");
		btnViewPopularProject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Popular_Project popu_project = new Popular_Project(con);
				popu_project.show();
				window.frame.setVisible(false);
				window =null;
			}
		});
		btnViewPopularProject.setBounds(117, 88, 210, 29);
		frame.getContentPane().add(btnViewPopularProject);
		
		JButton btnViewApplicationReport = new JButton("View application report");
		btnViewApplicationReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Application_report appl_report = new Application_report(con);
				appl_report.show();
				window.frame.setVisible(false);
				window =null;
			}
		});
		btnViewApplicationReport.setBounds(117, 120, 189, 29);
		frame.getContentPane().add(btnViewApplicationReport);
		
		JButton btnAddAProject = new JButton("Add a Project ");
		btnAddAProject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Add_project addp = new Add_project(con);
				addp.show();
				window.frame.setVisible(false);
				window =null;
			}
		});
		btnAddAProject.setBounds(162, 161, 117, 29);
		frame.getContentPane().add(btnAddAProject);
		
		JButton btnAddACourse = new JButton("Add a Course ");
		btnAddACourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Add_course addc = new Add_course(con);
				addc.show();
				window.frame.setVisible(false);
				window =null;
			}
		});
		btnAddACourse.setBounds(162, 204, 117, 29);
		frame.getContentPane().add(btnAddACourse);
		
		JButton btnLogOut = new JButton(" Log out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login login = new Login(con);
				login.show();
				window.frame.setVisible(false);
				window = null;
			}
		});
		btnLogOut.setBounds(327, 243, 117, 29);
		frame.getContentPane().add(btnLogOut);
	}

}
