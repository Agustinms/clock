package clock;

import java.awt.FlowLayout;

import javax.swing.JFrame;

public class Frame extends JFrame{

	Frame(){
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Reloj");
		this.setLayout(new FlowLayout());
		this.setSize(350, 200);
		this.setResizable(false);
		this.setVisible(true);
	}
	
}
