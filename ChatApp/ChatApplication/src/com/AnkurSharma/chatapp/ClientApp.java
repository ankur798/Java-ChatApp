package com.AnkurSharma.chatapp;

import java.awt.EventQueue;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JTextArea;

public class ClientApp {

	JFrame frame;
	private JTextField inputField;
	static JTextArea chatField;
	
	
	static Socket s;
	static DataInputStream din;
	static DataOutputStream dout;
	private JTextField txtServer;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientApp window = new ClientApp();
					//window.frame.setUndecorated(true);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
		
		
		try
		{
			String message="";
			
			s= new Socket("localhost",1201);
			
			din=new DataInputStream(s.getInputStream());
			dout=new DataOutputStream(s.getOutputStream());
			
			while(!message.equals("exit"))
			{
				message=din.readUTF();
				
				chatField.setText(chatField.getText().trim()+"\n\t\t\t"+message);
				
			}
			
		}
		catch(Exception e)
		{
			
		}
	}

	/**
	 * Create the application.
	 */
	public ClientApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(46, 139, 87));
		frame.setBackground(new Color(46, 139, 87));
		frame.setBounds(100, 100, 496, 554);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		inputField = new JTextField();
		inputField.setBounds(0, 470, 390, 45);
		frame.getContentPane().add(inputField);
		inputField.setColumns(10);
		
		JButton sendButton = new JButton("Send");
		sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String msgOut="";
					msgOut=inputField.getText();
					dout.writeUTF(msgOut);
					inputField.setText("");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		sendButton.setBackground(new Color(46, 139, 87));
		sendButton.setBounds(385, 470, 95, 45);
		frame.getContentPane().add(sendButton);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setBackground(new Color(46, 139, 87));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\ankur\\eclipse-workspace\\ChatApp\\ChatApplication\\src\\download.png"));
		btnNewButton.setBounds(436, 0, 44, 60);
		frame.getContentPane().add(btnNewButton);
		
		txtServer = new JTextField();
		txtServer.setFont(new Font("Vrinda", Font.BOLD, 18));
		txtServer.setBackground(new Color(46, 139, 87));
		txtServer.setHorizontalAlignment(SwingConstants.LEFT);
		txtServer.setText("CLIENT");
		txtServer.setEditable(false);
		txtServer.setBounds(171, 0, 86, 32);
		frame.getContentPane().add(txtServer);
		txtServer.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Active Now");
		lblNewLabel.setBounds(181, 37, 76, 14);
		frame.getContentPane().add(lblNewLabel);
		
		chatField = new JTextArea();
		chatField.setBackground(new Color(192, 192, 192));
		chatField.setBounds(0, 58, 480, 413);
		chatField.setLineWrap(true);
		chatField.setWrapStyleWord(true);
		frame.getContentPane().add(chatField);
	}
}
