package clock;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Frame extends JFrame implements ActionListener{

	DateFormat timeFormat;
	SimpleDateFormat dayFormat;
	SimpleDateFormat dateFormat;
	JLabel timeLabel;
	JLabel dayLabel;
	JLabel dateLabel;
	JLabel dayIcon;
	String time;
	String day;
	String date;
	String zoneIds[] = {"America/Buenos_Aires", "Europe/Budapest", "Japan", "Africa/Luanda"};
	int index;
	JMenuItem argItem;
	JMenuItem japItem;
	JMenuItem hunItem;
	JMenuItem afrItem;
	boolean horarioCambiado = false;
	
	
	
			
			
			
			
	Frame(){
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Reloj Calendario");
		this.setLayout(new FlowLayout());
		this.setSize(300, 220);
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
		
		//Menu para elegir que zona mostrar
		JMenuBar mb = new JMenuBar();
		JMenu menu = new JMenu("Zona");
		argItem = new JMenuItem("Buenos Aires (Argentina)");
		hunItem = new JMenuItem("Budapest (Hungría)");
		japItem = new JMenuItem("Japon");
		afrItem = new JMenuItem("Luanda (Africa)");
		
		this.add(mb);
		mb.add(menu);
		menu.add(argItem);
		menu.add(hunItem);
		menu.add(japItem);
		menu.add(afrItem);
		
		argItem.addActionListener(this);
		hunItem.addActionListener(this);
		japItem.addActionListener(this);
		afrItem.addActionListener(this);
		
		
		this.add(timeLabel);
		this.add(dayLabel);
		this.add(dateLabel);
		this.setVisible(true);
		
		
		setTime();

	
	}
	
	public void setTime() { //Para que se actualice cada segundo
		
	while(horarioCambiado == false) {
		Date now = new Date();
		timeFormat.setTimeZone(TimeZone.getTimeZone(zoneIds[index]));
		String time = timeFormat.format(now);
		timeLabel.setText(time);
		dayFormat.setTimeZone(TimeZone.getTimeZone(zoneIds[index]));
		String today = dayFormat.format(now);
		dayLabel.setText(today);
		dateFormat.setTimeZone(TimeZone.getTimeZone(zoneIds[index]));
		String date = dateFormat.format(now);
		dateLabel.setText(date);
		
		//Pasar el horario actual a int asi podemos saber si es de dia o de noche
		String horaActual[] = time.split(":");
		int hora = Integer.parseInt(horaActual[0]);
		
		if(hora > 18 || hora < 6) {
			URL url = Main.class.getResource("/resources/moon.png");
			ImageIcon moon = new ImageIcon(url);
			Image moonImg = moon.getImage();
			Image moonScaled = moonImg.getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH);
			moon = new ImageIcon(moonScaled);
			dayLabel.setIcon(moon);
		}else {
			URL url = Main.class.getResource("/resources/sun.png");
			ImageIcon sun = new ImageIcon(url);
			Image sunImg = sun.getImage();
			Image sunScaled = sunImg.getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH);
			sun = new ImageIcon(sunScaled);
			dayLabel.setIcon(sun);
		}
	

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		  } 
		}
		horarioCambiado = false;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == argItem) {
			index = 0;
			
			System.out.println(index);
			horarioCambiado = true;
			setTime();
		}else if(e.getSource() == hunItem) {
			horarioCambiado = true;
			index = 1;
			setTime();
		}else if(e.getSource() == japItem) {
			horarioCambiado = true;
			index = 2;
			setTime();
		}else {
			horarioCambiado = true;
			index = 3;
			setTime();
		}
		
	}
	
}
