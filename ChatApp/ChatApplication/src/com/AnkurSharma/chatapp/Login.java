package com.AnkurSharma.chatapp;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.SQLException;

import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

import com.AnkurSharma.chatapp.dao.UserDao;
import com.AnkurSharma.chatapp.dto.UserDTO;

public class Login extends JFrame{

	JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;
	Client client;
	Socket socket;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
					Login window = new Login();
					window.frame.setVisible(true);
				
			
		
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	private void register() {
		UserDTO userDTO = new UserDTO();
		userDTO.setUserid(textField.getText());
		userDTO.setPassword(passwordField.getPassword());
		try {
			String result = userDAO.register(userDTO);
			JOptionPane.showMessageDialog(this, result);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private UserDao userDAO = new UserDao();
	private void doLogin() throws ClassNotFoundException, SQLException {
		String userid = textField.getText();
		char[] password =passwordField.getPassword();
		System.out.println(userid + " "+password);
		UserDTO userDTO =new UserDTO(userid, password);
		UserDao userDAO = new UserDao();
		userDAO.doLogin(userDTO);
		try {
			String result = userDAO.doLogin(userDTO);
			if(result.contains("Welcome")) {
				JOptionPane.showMessageDialog(this, result.toUpperCase());
                   
				
				try {
					socket = new Socket("localhost", 1234);
					 client = new Client(socket, userDTO.getUserid());
					 client.frame.setVisible(true);
								      
			        client.listenForMessage();
				   client.userName();
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
							        
				

				this.setVisible(false);
				
				
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(135, 206, 235));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setBounds(135, 11, 167, 47);
		lblNewLabel.setFont(new Font("Tunga", Font.BOLD, 40));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblNewLabel);
		
		JButton login = new JButton("Login");
		login.addActionListener((ActionListener) new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					doLogin();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			
		});
		login.setBounds(108, 194, 83, 23);
		login.setBackground(new Color(0, 0, 0));
		frame.getContentPane().add(login);
		
		JButton btnNewButton_1 = new JButton("Register");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				register();
			}
		});
		btnNewButton_1.setBackground(new Color(0, 0, 0));
		btnNewButton_1.setBounds(230, 194, 89, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JLabel lblNewLabel_1 = new JLabel("USER-ID");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblNewLabel_1.setBounds(108, 82, 89, 23);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_2.setBounds(108, 125, 96, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(243, 82, 111, 23);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(243, 125, 111, 20);
		frame.getContentPane().add(passwordField);
	}
}
