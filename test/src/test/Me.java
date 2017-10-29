package test;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;

public class Me {

	private JFrame frame;
	public static Me window;
	public Main_page main;
	public String username;
	public Connection con;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new Me();
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
					window = new Me(username,con);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void showup() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
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
	public Me() {
		initialize();
	}
	
	public Me(String username,Connection con) {
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
		
		JLabel lblMe = new JLabel("Me");
		lblMe.setBounds(203, 32, 61, 16);
		frame.getContentPane().add(lblMe);
		
		JButton btnNewButton = new JButton("Edit Profile");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Edit_Profile edit = new Edit_Profile(username,con);
				edit.show();
				window.frame.setVisible(false);
			}
		});
		btnNewButton.setBounds(154, 60, 117, 29);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("My application");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Myapplication Myapp=new Myapplication(username,con);
				Myapp.show();
				window.frame.setVisible(false);
			}
		});
		btnNewButton_1.setBounds(154, 124, 117, 29);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Back");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main_page main=new Main_page(username,con); 
				main.show();
				window.frame.setVisible(false);
				window = null;
			}
		});
		btnNewButton_2.setBounds(154, 186, 117, 29);
		frame.getContentPane().add(btnNewButton_2);
	}

}
