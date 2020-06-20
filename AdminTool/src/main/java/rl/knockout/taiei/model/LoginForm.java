package rl.knockout.taiei.model;

import javax.persistence.Id;
import javax.validation.constraints.*;

public class LoginForm{
	@Id Integer staff_id;
	@NotEmpty(message="Required") String staff_pw;
	
	public Integer getStaff_id() {
		return staff_id;
	}
	public void setStaff_id(Integer staff_id) {
		this.staff_id = staff_id;
	}
	public String getStaff_pw() {
		return staff_pw;
	}
	public void setStaff_pw(String staff_pw) {
		this.staff_pw = staff_pw;
	}
}