//package com.AnkurSharma.chatapp.network;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.io.OutputStream;
//import java.net.Socket;
//
//public class ServerThread extends Thread {
//	
//	private Socket socket; // Client Connection
//	private InputStream in;
//	private OutputStream out;
//	private Server server;
//	public ServerThread(Server server, Socket socket) throws IOException {
//		this.socket = socket;
//		this.in = this.socket.getInputStream();
//		this.out = this.socket.getOutputStream();
//		this.server = server;
//	}
//	
//	@Override
//	public void run() {
//			try {
//				readMessage();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//	}
//	
//	
//	public void readMessage() throws IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(in));
//		String inputLine = "";
//		while(true) { // Infinite Loop (Main Thread Busy)
//			inputLine = br.readLine();
//			System.out.println("Message Rec "+inputLine);
//			for(ServerThread thread : server.sockets) {
//					thread.socket.getOutputStream().write(inputLine.getBytes());
//			}
//			// write to all
//			
//		}
//		 
//	}
//
//}
//
