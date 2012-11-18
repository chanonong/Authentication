package ui;

import java.awt.Font;

import javax.swing.JFrame;

public abstract class UI extends JFrame {
	private static JPAUI jpa = new JPAUI();
	private static JDBCUI jdbc = new JDBCUI();
	private static WelcomeUI w_ui = new WelcomeUI();
	
	public UI(String string) {
		super(string);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		init();
		this.pack();
	}
	
	public void run() { 
		this.setVisible(true);
	}
	public void stop() {
		this.setVisible(false);
	}
	public abstract void init();
	
	public static void ActivateJPA() {
		jpa.run();
		jdbc.stop();
		w_ui.stop();
	}

	public static void ActivateWelcome() {
		w_ui.run();
		jpa.stop();
		jdbc.stop();
	
	}
	
	public static void ActivateJDBC() {
		jdbc.run();
		jpa.stop();
		w_ui.stop();
	}
}
