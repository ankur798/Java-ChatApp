package com.AnkurSharma.chatapp.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ClientThread extends Thread{
	private InputStream in;
	public ClientThread(InputStream in) {
		this.in = in;
		
	}
	@Override
	public void run() {
		try {
			readMessage();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void readMessage() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String inputLine = "";
		while(true) { // Infinite Loop (Main Thread Busy)
			inputLine = br.readLine();
			System.out.println("Message Rec "+inputLine);
		}
		 
	}
}
