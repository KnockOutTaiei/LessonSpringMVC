package rl.knockout.taiei.model;

import javax.persistence.*;
import javax.validation.constraints.*;

public class LoginForm{
	@Id @NotNull(message="入力してください") @Min(value=0, message="正の整数で指定してください") Integer staff_id;
	@NotEmpty(message="入力してください") String staff_pw;
	
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