package com.AnkurSharma.chatapp;

import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.ImageIcon;



public class Client {

	public JFrame frame;
	
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String username;
    private JTextField inputField;
    JTextArea chatArea;
    private static JTextField nameLabel;
    private JLabel lblNewLabel;
    private JButton btnNewButton_1;

    public Client(Socket socket, String username) {
    	initialize();
        try {
            this.socket = socket;
            this.username = username.toUpperCase();
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter= new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            // Gracefully close everything.
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    // Sending a message isn't blocking and can be done without spawning a thread, unlike waiting for a message.
    public void sendMessage(String message) {
        try {
            
            
            // Create a scanner for user input.
            
            // While there is still a connection with the server, continue to scan the terminal and then send the message.
            
               
                bufferedWriter.write(username + ": " + message);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            
            
        } catch (IOException e) {
            // Gracefully close everything.
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }
    
    public void userName()
    {
    	 try {
    		 bufferedWriter.write(username);
			bufferedWriter.newLine();
			bufferedWriter.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         
    }

    // Listening for a message is blocking so need a separate thread for that.
    public void listenForMessage() {
    	nameLabel.setText(username);
        new Thread(new Runnable() {
            @Override
            public void run() {
                String msgFromGroupChat;
                // While there is still a connection with the server, continue to listen for messages on a separate thread.
                while (socket.isConnected()) {
                    try {
                        // Get the messages sent from other users and print it to the console.
                        msgFromGroupChat = bufferedReader.readLine();
                        chatArea.setText(chatArea.getText()+"\n"+msgFromGroupChat);
                    } catch (IOException e) {
                        // Close everything gracefully.
                        closeEverything(socket, bufferedReader, bufferedWriter);
                    }
                }
            }
        }).start();
    }

    // Helper method to close everything so you don't have to repeat yourself.
    public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
       
        try {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	/**
	 * Launch the application.
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public static void main(String[] args) throws UnknownHostException, IOException {
		
					
					
					
			        Scanner scanner = new Scanner(System.in);
			        System.out.print("Enter your username for the group chat: ");
			        String username = scanner.nextLine();
			       
			        Socket socket = new Socket("localhost", 1234);

			        
			        Client client = new Client(socket, username);
			        client.frame.setVisible(true);
			      
			        client.listenForMessage();
			        client.userName();
			        
			        
			
			
	}

	
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(60, 179, 113));
		
		frame.setBounds(100, 100, 496, 554);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		inputField = new JTextField();
		inputField.setBackground(new Color(175, 238, 238));
		inputField.setBounds(0, 483, 410, 32);
		frame.getContentPane().add(inputField);
		inputField.setColumns(10);
		
		JButton btnNewButton = new JButton("Send");
		btnNewButton.setBackground(new Color(46, 139, 87));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendMessage(inputField.getText());
				inputField.setText("");
			}
		});
		btnNewButton.setBounds(409, 485, 71, 30);
		frame.getContentPane().add(btnNewButton);
		
		 chatArea = new JTextArea();
		 chatArea.setBackground(new Color(192, 192, 192));
		chatArea.setEditable(false);
		chatArea.setBounds(0, 33, 480, 451);
		frame.getContentPane().add(chatArea);
		
		nameLabel = new JTextField();
		nameLabel.setBackground(new Color(60, 179, 113));
		nameLabel.setFont(new Font("Stencil", Font.BOLD, 20));
		nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		nameLabel.setEditable(false);
		nameLabel.setBounds(0, 0, 184, 32);
		frame.getContentPane().add(nameLabel);
		nameLabel.setColumns(10);
		
		lblNewLabel = new JLabel("Active Now");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(173, 9, 116, 14);
		frame.getContentPane().add(lblNewLabel);
		
		btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton_1.setIcon(new ImageIcon("C:\\Users\\ankur\\eclipse-workspace\\ChatApp\\ChatApplication\\src\\download.png"));
		btnNewButton_1.setBounds(441, 0, 39, 32);
		frame.getContentPane().add(btnNewButton_1);
		
	}
}
