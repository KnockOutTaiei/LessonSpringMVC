package rl.knockout.taiei.ditest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MyBean2 implements Printable{
	String message;
	LocalDateTime createdTime; 
	
	public String getMessage() {return message;}
	public void setMessage(String message) {this.message = message;}

	@Override
	public void print(){DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");System.out.println("We are " + message + createdTime.format(formatter));}
	
	public MyBean2(String message) {this.message=message;createdTime=LocalDateTime.now();}
}