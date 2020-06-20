/*Loginning staff info to save into session scope
 * 'cause avoid holding staff password*/
package rl.knockout.taiei.model;
import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.*;

public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id int staff_id;
	@NotEmpty String staff_name;
	int staff_roll_id;
	
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
	public int getStaff_roll_id() {
		return staff_roll_id;
	}
	public void setStaff_roll_id(int staff_roll_id) {
		this.staff_roll_id = staff_roll_id;
	}
}