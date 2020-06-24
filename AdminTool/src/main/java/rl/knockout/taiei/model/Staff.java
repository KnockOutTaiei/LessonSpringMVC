package rl.knockout.taiei.model;

import javax.validation.constraints.*;

public class Staff{
	@NotNull(message="入力してください") @Min(value=0, message="正の整数で指定してください") int staff_id;
	@NotEmpty(message="入力してください") String staff_name;
	@NotEmpty(message="入力してください") String staff_pw;
	@NotNull(message="入力してください") @Min(value=0, message="正の整数で指定してください") int staff_roll_id;
	@NotNull(message="入力してください") @Min(value=0, message="正の整数で指定してください") int experience;
	@NotEmpty(message="入力してください") String gender;
	@NotNull(message="入力してください") @Min(value=0, message="正の整数で指定してください") int age;
	@NotEmpty(message="入力してください") String mail;
	@NotEmpty(message="入力してください") String tel_no;
	@NotEmpty(message="入力してください") String join_date;
	@NotEmpty(message="入力してください") String leave_date;
	
	public int getStaff_id() {
		return staff_id;
	}
	public void setStaff_id(int staff_id) {
		this.staff_id = staff_id;
	}
	public String getStaff_name() {
		return staff_name;
	}
	public void setStaff_name(String staff_name) {
		this.staff_name = staff_name;
	}
	public String getStaff_pw() {
		return staff_pw;
	}
	public void setStaff_pw(String staff_pw) {
		this.staff_pw = staff_pw;
	}
	public int getStaff_roll_id() {
		return staff_roll_id;
	}
	public void setStaff_roll_id(int staff_roll_id) {
		this.staff_roll_id = staff_roll_id;
	}
	public int getExperience() {
		return experience;
	}
	public void setExperience(int experience) {
		this.experience = experience;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getTel_no() {
		return tel_no;
	}
	public void setTel_no(String tel_no) {
		this.tel_no = tel_no;
	}
	public String getJoin_date() {
		return join_date;
	}
	public void setJoin_date(String join_date) {
		this.join_date = join_date;
	}
	public String getLeave_date() {
		return leave_date;
	}
	public void setLeave_date(String leave_date) {
		this.leave_date = leave_date;
	}
}