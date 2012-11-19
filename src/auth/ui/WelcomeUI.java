package auth.ui;

import javax.swing.*;

import com.mysql.jdbc.BalanceStrategy;

import java.awt.*;
import java.awt.event.ActionEvent;

public class WelcomeUI extends UI {
	private static Font font = new Font("Tahoma",Font.PLAIN,15);
	JLabel welcomeMessage;
	JButton jpa;
	JButton jdbc;
	JButton close;
	public WelcomeUI() {
		super("Welcome :D");
	}

	@Override
	public void init() {
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(320,240));
		this.setResizable(false);
		JLabel blank = new JLabel();
		blank.setPreferredSize(new Dimension(30,75));
		Container messageContainer = new Container();
		messageContainer.setLayout(new FlowLayout());
		//messageContainer.setPreferredSize(new Dimension(30,100));
		welcomeMessage = new JLabel("Which method do you like to login with ?");
		//welcomeMessage.setHorizontalAlignment(WIDTH/2);
		//welcomeMessage.setVerticalAlignment(HEIGHT/2);
		welcomeMessage.setFont(font);
		messageContainer.add(welcomeMessage);
		
		
		Container buttonContainer = new Container();
		buttonContainer.setLayout(new FlowLayout());
		jpa = new JButton("JPA");
		jpa.addActionListener(new MyButtonListener());
		jdbc = new JButton("JDBC");
		jdbc.addActionListener(new MyButtonListener());
		close = new JButton("close");
		close.addActionListener(new MyButtonListener());
		buttonContainer.add(jpa);
		buttonContainer.add(jdbc);
		buttonContainer.add(close);
		
		this.add(blank,BorderLayout.NORTH);
		this.add(messageContainer,BorderLayout.CENTER);
		this.add(buttonContainer,BorderLayout.SOUTH);
	}
	
	class MyButtonListener extends AbstractAction {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(arg0.getActionCommand().equals("JPA")) {
				UI.ActivateJPA();
			}
			else if(arg0.getActionCommand().equals("JDBC")) {
				UI.ActivateJDBC();
			}
			else if(arg0.getActionCommand().equals("close")) {
				System.exit(0);
			}
		}
		
	}
}
