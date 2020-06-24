package rl.knockout.taiei.model;

import javax.validation.constraints.*;

public class StaffSearchForm{
	@Size(min=0,max=20) String staff_name;
	@Size(min=0,max=20) String staff_roll_id;
	@Min(value = 0, message = "The value must be positive") int experience;
	@Min(value = 0, message = "The value must be positive") int nowPage;
	
	public String getStaff_name() {
		return staff_name;
	}
	public void setStaff_name(String staff_name) {
		this.staff_name = staff_name;
	}
	public String getStaff_roll_id() {
		return staff_roll_id;
	}
	public void setStaff_roll_id(String staff_roll_id) {
		this.staff_roll_id = staff_roll_id;
	}
	public int getExperience() {
		return experience;
	}
	public void setExperience(int experience) {
		this.experience = experience;
	}
	public int getNowPage() {
		return nowPage;
	}
	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}
}