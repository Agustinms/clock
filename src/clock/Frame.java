package clock;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Frame extends JFrame{

	SimpleDateFormat timeFormat;
	SimpleDateFormat dayFormat;
	SimpleDateFormat dateFormat;
	JLabel timeLabel;
	JLabel dayLabel;
	JLabel dateLabel;
	JLabel dayIcon;
	String time;
	String day;
	String date;
	
	
	Frame(){
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Reloj");
		this.setLayout(new FlowLayout());
		this.setSize(300, 200);
		this.setResizable(false);
		
		timeFormat = new SimpleDateFormat("HH:mm:ss");
		dayFormat = new SimpleDateFormat("EEEE");
		dateFormat = new SimpleDateFormat("dd 'de' MMMMM 'del' yyyy");
		
		
		timeLabel = new JLabel();
		timeLabel.setFont(new Font("Verdana", Font.PLAIN, 50));
		timeLabel.setForeground(new Color(0x00FF00));
		timeLabel.setBackground(Color.black);
		timeLabel.setOpaque(true);
		
		dayLabel = new JLabel();
		dayLabel.setFont(new Font("Verdana", Font.PLAIN, 30));
		
		
		dateLabel = new JLabel();
		dateLabel.setFont(new Font("Verdana", Font.PLAIN, 25));
		
	
		
		this.add(timeLabel);
		this.add(dayLabel);
		this.add(dateLabel);
		this.setVisible(true);
		
		
		setTime();
		
	
	}
	
	public void setTime() { //Para que se actualice cada segundo
		
		while(true) {
		time = timeFormat.format(Calendar.getInstance().getTime());
		timeLabel.setText(time);
		day = dayFormat.format(Calendar.getInstance().getTime());
		dayLabel.setText(day);
		date = dateFormat.format(Calendar.getInstance().getTime());
		dateLabel.setText(date);
		
		
		//Pasar el horario actual a int asi podemos saber si es de dia o de noche
		String horaActual[] = time.split(":");
		int hora = Integer.parseInt(horaActual[0]);
		
		if(hora > 18) {
			ImageIcon moon = new ImageIcon(new ImageIcon("moon.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
			dayLabel.setIcon(moon);
		}else {
			ImageIcon sun = new ImageIcon(new ImageIcon("sun.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
			dayLabel.setIcon(sun);
		}
	

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		}
	}
	
}
