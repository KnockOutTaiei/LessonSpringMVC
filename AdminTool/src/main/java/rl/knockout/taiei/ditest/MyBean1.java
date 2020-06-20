package rl.knockout.taiei.ditest;

public class MyBean1 implements Printable{
	String message;
	
	public String getMessage() {return message;}
	public void setMessage(String message) {this.message = message;}
	
	@Override
	public void print() {System.out.println("I am " + message);}
	
	public MyBean1(String message) {this.message=message;}
}