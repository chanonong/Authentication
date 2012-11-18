package ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import core.jpa.JPAAuthentication;
import core.jpa.User;

@SuppressWarnings("serial")
public class JPAUI extends UI {
	JLabel user;
	JLabel password;
	JTextField userField;
	JPasswordField passwordField;
	JButton submit;
	JButton cancel;
	JButton reset;
	JLabel status;
	JLabel info;
	public JPAUI() {
		super("Authentication with JPA");
	}
	
	public void init() {
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(320,240));
		this.setResizable(false);
		
		Container topContainer = new Container();
		topContainer.setLayout(new BorderLayout());
		
		Container userContainer = new Container();
		userField = new JTextField();
		userField.setPreferredSize(new Dimension(150,30));
		userField.addActionListener(new MyActionListener());
		user = new JLabel("Username : ");
		
		userContainer.setLayout(new FlowLayout());
		userContainer.add(user);
		userContainer.add(userField);
		
		Container passContainer = new Container();
		passwordField = new JPasswordField();
		passwordField.setPreferredSize(new Dimension(150,30));
		passwordField.addActionListener(new MyActionListener());
		password = new JLabel("Password : ");
		
		passContainer.setLayout(new FlowLayout());
		passContainer.add(password);
		passContainer.add(passwordField);
		
		topContainer.add(userContainer,BorderLayout.NORTH);
		topContainer.add(passContainer,BorderLayout.SOUTH);
		
		Container midContainer = new Container();
		midContainer.setLayout(new FlowLayout());
		
		submit = new JButton("submit");
		submit.addActionListener(new MyActionListener());
		
		cancel = new JButton("cancel");
		cancel.addActionListener(new MyActionListener());
		
		reset= new JButton("reset");
		reset.addActionListener(new MyActionListener());
		
		midContainer.add(submit);
		midContainer.add(cancel);
		midContainer.add(reset);
		
		Container botContainer = new Container();
		botContainer.setLayout(new BorderLayout());
		status = new JLabel("Please Login");
		status.setHorizontalAlignment(WIDTH/2);
		
		info = new JLabel("User: null , Name: null , LastName: null ");
		info.setHorizontalAlignment(WIDTH/2);
		
		botContainer.add(status,BorderLayout.NORTH);
		botContainer.add(info,BorderLayout.SOUTH);
		
		this.add(topContainer,BorderLayout.NORTH);
		this.add(midContainer,BorderLayout.CENTER);
		this.add(botContainer,BorderLayout.SOUTH);
		
	}
	
	class MyActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			//System.out.println(arg0.getActionCommand());
			
			if (arg0.getActionCommand().equals("cancel")) {
				UI.ActivateWelcome();
			}
			else if(arg0.getActionCommand().equals("reset")) {
				userField.setText("");
				passwordField.setText("");
				status.setText("Please Login");
				info.setText("User: null , Name: null , LastName: null ");
			}
			else {
				/*U
				@SuppressWarnings("deprecation")
				String result = core.jdbc.JDBCUserAuthentication.login(userField.getText(), passwordField.getText());
				*/
				User user = JPAAuthentication.Authenticate(userField.getText() , passwordField.getText());
				if(user == null) {
					userField.setText("");
					passwordField.setText("");
					JOptionPane.showMessageDialog(null, "fail");
					status.setText("Wrong username or password , Please Login");
					status.setForeground(Color.RED);
					info.setText("User: null , Name: null , LastName: null ");
					System.err.println("Wrong username or password , Please Login");
				}
				else {
					//String[] tmp = result.split(",");
					info.setText("User: " + user.getUsername() +", Name: " + user.getName() + " , LastName: " + user.getLastname());
					JOptionPane.showMessageDialog(null, "Logged in");
					status.setText("Logged in");
					status.setForeground(Color.BLACK);
				}
			}
			
		}
		
	}
}
