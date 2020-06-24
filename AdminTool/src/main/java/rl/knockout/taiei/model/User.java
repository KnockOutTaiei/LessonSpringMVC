/*Loginning staff info to save into session scope
 * 'cause avoid holding staff password*/
package rl.knockout.taiei.model;
import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.*;

public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id @NotNull int staff_id;
	@NotEmpty(message="入力してください") String staff_name;
	@NotNull  @Min(value = 0, message = "The value must be positive") int staff_roll_id;
	@NotNull(message="入力してください") boolean privilegeLogin;{privilegeLogin = false;}
	@NotNull(message="入力してください")boolean privilegeLoginAdmin;{privilegeLoginAdmin = false;}
	
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
	public boolean isPrivilegeLogin() {
		return privilegeLogin;
	}
	public void setPrivilegeLogin(boolean privilegeLogin) {
		this.privilegeLogin = privilegeLogin;
	}
	public boolean isPrivilegeLoginAdmin() {
		return privilegeLoginAdmin;
	}
	public void setPrivilegeLoginAdmin(boolean privilegeLoginAdmin) {
		this.privilegeLoginAdmin = privilegeLoginAdmin;
	}
}