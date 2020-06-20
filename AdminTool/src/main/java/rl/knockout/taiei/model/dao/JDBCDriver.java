package rl.knockout.taiei.model.dao;

import java.sql.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import rl.knockout.taiei.model.*;
/**
 * @author Education
 *
 */
public class JDBCDriver extends JDBCBase{
	/**@override*/
	/**@deprecated*/
	protected boolean startSQL(){return false;}//delete definition
	
	/**
	 * @param staff_id
	 * @param staff_pw
	 * @return
	 */
	public boolean canLogin(LoginForm loginForm) {
		boolean resultFlag = false;
		if(super.isConnect) {
			try {
				//precompile
				String query = "SELECT staff_id,staff_pw FROM StaffTbl WHERE staff_id=?;";
				PreparedStatement pstmt = super.connection.prepareStatement(query);

				//Set
				pstmt.setInt(1, loginForm.getStaff_id());
				ResultSet resultSet = pstmt.executeQuery();
				//commit
				super.connection.commit();

				//digest result
				while(resultSet.next()) {
					resultFlag = resultSet.getString("staff_pw").equals(loginForm.getStaff_pw());
				}
				pstmt.close();
				return resultFlag;
			}catch(SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		return resultFlag;
	}
	
	/**
	 * @Overload
	 * @param staff_id
	 * @param staff_pw
	 */
	public boolean canLogin(int staff_id, String staff_pw) {
		boolean resultFlag = false;
		if(super.isConnect) {
			try {
				//precompile
				String query = "SELECT staff_id,staff_pw FROM StaffTbl WHERE staff_id=?;";
				PreparedStatement pstmt = super.connection.prepareStatement(query);

				//Set
				pstmt.setInt(1, staff_id);
				ResultSet resultSet = pstmt.executeQuery();
				//commit
				super.connection.commit();

				//digest result
				while(resultSet.next()) {
					resultFlag = resultSet.getString("staff_pw").equals(staff_pw);
				}
				pstmt.close();
				return resultFlag;
			}catch(SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		return resultFlag;
	}
	
	/**
	 * @param staff_id
	 * @param user
	 * @return
	 */
	public boolean createUser(int staff_id,User user) {
		boolean resultFlag = false;
		if(super.isConnect) {
			try {
				//precompile
				String query = "SELECT staff_id,staff_name,staff_roll_id FROM StaffTbl WHERE staff_id=?;";
				PreparedStatement pstmt = super.connection.prepareStatement(query);

				//Set
				pstmt.setInt(1, staff_id);
				ResultSet resultSet = pstmt.executeQuery();
				//commit
				super.connection.commit();

				//digest result
				while(resultSet.next()) {
					user.setStaff_id(resultSet.getInt("staff_id"));
					user.setStaff_name(resultSet.getString("staff_name"));
					user.setStaff_roll_id(resultSet.getInt("staff_roll_id"));
					break;
				}
				pstmt.close();
				return resultFlag;
			}catch(SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		return resultFlag;
	}

	/**
	 * @param staff_id
	 * @param staff_pw
	 * @return
	 */
	public boolean canLoginAdmin(LoginForm loginForm) {
		boolean resultFlag = false;
		if(super.isConnect) {
			try {
				//precompile
				String query = "SELECT staff_id,staff_pw FROM StaffTbl WHERE staff_id=? AND staff_roll_id=9;";
				PreparedStatement pstmt = super.connection.prepareStatement(query);

				//Set
				pstmt.setInt(1, loginForm.getStaff_id());
				ResultSet resultSet = pstmt.executeQuery();
				//commit
				super.connection.commit();

				//digest result
				while(resultSet.next()) {
					resultFlag = resultSet.getString("staff_pw").equals(loginForm.getStaff_pw());
				}
				pstmt.close();
				return resultFlag;
			}catch(SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		return resultFlag;
	}
	
	/**
	 * @param acc_id
	 * @param login_name
	 * @param order_status
	 * @param dateKind
	 * @param begin
	 * @param end
	 * @param page
	 * @param historySummaries
	 * @return
	 */
	public boolean getHistorySummaries(String acc_id, String login_name, String order_status, DateKind dateKind, LocalDateTime begin, LocalDateTime end, int page, ArrayList<HistorySummary> historySummaries) {
		if(super.isConnect) {
			try {
				//precompile
				String query = "SELECT OrderTbl.order_id,OrderTbl.acc_id,OrderTbl.order_date,OrderTbl.limit_date,OrderTbl.confirm_date,OrderTbl.order_status,Account.login_name FROM OrderTbl INNER JOIN OrderDetailTbl ON OrderTbl.order_id=OrderDetailTbl.order_id INNER JOIN ProductTbl ON OrderDetailTbl.product_id= ProductTbl.product_id INNER JOIN Account ON OrderTbl.acc_id=Account.acc_id";
				
				//If Search-condition exists, add "WHERE"
				if(isExist(acc_id) || isExist(login_name) || isExist(order_status) || isExist(dateKind)) {
					query += " WHERE ";
					////WHERE CONDITION
					if(isExist(acc_id)) {
						query += "Account.acc_id=?";
					}
					if(isExist(login_name)) {
						if(isExist(acc_id))query += " AND ";
						query += "Account.login_name=?";
					}
					if(isExist(order_status)) {
						if(isExist(acc_id) || isExist(login_name))query += " AND ";
						query += "OrderTbl.order_status=?";
					}
					switch(dateKind) {
					case ORDER:
						if(isExist(acc_id) || isExist(login_name) || isExist(order_status))query += " AND ";
						query += "OrderTbl.order_date BETWEEN ? AND ?";
						break;
					case LIMIT:
						if(isExist(acc_id) || isExist(login_name) || isExist(order_status))query += " AND ";
						query += "OrderTbl.limit_date BETWEEN ? AND ?";
						break;
					case CONFIRM:
						if(isExist(acc_id) || isExist(login_name) || isExist(order_status))query += " AND ";
						query += "OrderTbl.confirm_date BETWEEN ? AND ?";
						break;
					case NONE:
					default:
						break;
					}
				}
				
				query += " LIMIT 10";
				query += " OFFSET "+ ((Integer)((page-1)*10)).toString();//0,10,20,...

				query+=";";
				PreparedStatement pstmt = super.connection.prepareStatement(query);

				//Set
				////from LocalDateTime to like"2020/05/11 15:36:45"
				DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				if(isExist(acc_id) || isExist(login_name) || isExist(order_status) || isExist(dateKind)) {
					if(isExist(acc_id)) {
						pstmt.setString(1, acc_id);
					}
					if(isExist(login_name)) {
						//which place of ? is
						if(isExist(acc_id)) {
							pstmt.setString(2, login_name);
						}else {
							pstmt.setString(1, login_name);
						}
					}
					if(isExist(order_status)) {
						//which place of ? is
						if(isExist(acc_id) && isExist(login_name)) {
							pstmt.setString(3, order_status);
						}else if (isExist(acc_id) || isExist(login_name)) {//XOR
							pstmt.setString(2, order_status);
						}else {
							pstmt.setString(1, order_status);
						}
					}
					switch(dateKind) {
					case ORDER:
					case LIMIT:
					case CONFIRM:
						//which place of ? is
						if(isExist(acc_id) && isExist(login_name) && isExist(order_status)) {
							pstmt.setString(4, begin.format(format));
							pstmt.setString(5, end.format(format));
						}else if((!isExist(acc_id) && isExist(login_name) && isExist(order_status)) || (isExist(acc_id) && !isExist(login_name) && isExist(order_status)) || (isExist(acc_id) && isExist(login_name) && !isExist(order_status))) {//2 of 3 exist
							pstmt.setString(3, begin.format(format));
							pstmt.setString(4, end.format(format));
						}else if((!isExist(acc_id) && !isExist(login_name) && isExist(order_status)) || (isExist(acc_id) && !isExist(login_name) && !isExist(order_status)) || (!isExist(acc_id) && isExist(login_name) && !isExist(order_status))){//1 of 3 exists
							pstmt.setString(2, begin.format(format));
							pstmt.setString(3, end.format(format));
						}else {
							pstmt.setString(1, begin.format(format));
							pstmt.setString(2, end.format(format));
						}
						break;
					case NONE:
					default:
						break;
					}
				}
				
				ResultSet resultSet = pstmt.executeQuery();
				//commit
				super.connection.commit();

				//digest result
				while(resultSet.next()) {
					HistorySummary tempHistorySummary = new HistorySummary();
					tempHistorySummary.setOrder_id(resultSet.getInt("order_id"));
					tempHistorySummary.setAcc_id(resultSet.getString("acc_id"));
					tempHistorySummary.setOrder_date(resultSet.getString("order_date"));
					tempHistorySummary.setLimit_date(resultSet.getString("limit_date"));
					tempHistorySummary.setConfirm_date(resultSet.getString("confirm_date"));
					tempHistorySummary.setOrder_status(resultSet.getString("order_status"));
					tempHistorySummary.setLogin_name(resultSet.getString("login_name"));
					historySummaries.add(tempHistorySummary);
				}//TODO: Is type corresponding to Document? 
				
				pstmt.close();
				return true;
			}catch(SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}
	
	/**
	 * @param order_id
	 * @param history
	 * @return
	 */
	public boolean getHistory(String order_id, History history) {
		if(super.isConnect) {
			try {
				//precompile
				String query = "SELECT OrderTbl.order_id,OrderTbl.acc_id,OrderTbl.whole_amount,OrderTbl.tax,OrderTbl.order_date,OrderTbl.limit_date,OrderTbl.confirm_date,OrderTbl.order_status,OrderDetailTbl.voucher_id,OrderDetailTbl.product_id,OrderDetailTbl.stock,OrderDetailTbl.amount,ProductTbl.product_name,ProductTbl.product_price,Account.login_name FROM OrderTbl INNER JOIN OrderDetailTbl ON OrderTbl.order_id=OrderDetailTbl.order_id INNER JOIN ProductTbl ON OrderDetailTbl.product_id= ProductTbl.product_id INNER JOIN Account ON OrderTbl.acc_id=Account.acc_id WHERE OrderTbl.order_id=?;";
				PreparedStatement pstmt = super.connection.prepareStatement(query);

				//Set
				pstmt.setString(1, order_id);
				ResultSet resultSet = pstmt.executeQuery();
				//commit
				super.connection.commit();

				//digest result
				////from LocalDateTime to like"2020/05/11 15:36:45"
				DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				OrderDetail tempOrderDetail;
				while(resultSet.next()) {
					history.setOrder_id(resultSet.getString("order_id"));
					history.setAcc_id(resultSet.getString("acc_id"));
					history.setWhole_amount(resultSet.getInt("whole_amount"));
					history.setTax(resultSet.getInt("tax"));
					history.setOrder_date(resultSet.getString("order_date"));//DATETIME型で入れてんのにgetDatetimeがねえ　java.time.format.DateTimeParseException: Text '2020-05-19 12:13:07.0' could not be parsed, unparsed text found at index 19
					history.setLimit_date(resultSet.getString("limit_date"));
					history.setConfirm_date(resultSet.getString("confirm_date"));
					history.setOrder_status(resultSet.getString("order_status"));
					tempOrderDetail = new OrderDetail();
					tempOrderDetail.setVoucher_id(resultSet.getInt("voucher_id"));
					tempOrderDetail.setProduct_id(resultSet.getInt("product_id"));
					tempOrderDetail.setStock(resultSet.getInt("stock"));
					tempOrderDetail.setAmount(resultSet.getInt("amount"));
					tempOrderDetail.setProduct_name(resultSet.getString("product_name"));
					tempOrderDetail.setProduct_price(resultSet.getInt("product_price"));
					history.addOrderDetail(tempOrderDetail);
					history.setLogin_name(resultSet.getString("login_name"));
					//break;
				}//TODO: Is type corresponding to Document? 

				pstmt.close();
				return true;
			}catch(SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}
	
	/**
	 * @param history
	 * @return
	 */
	public boolean deleteHistory(History history) {
		if(history==null) {System.out.println("deleteHistory() failed; history is null");return false;}
		if(super.isConnect) {
			try {
				//precompile
				String query = "UPDATE OrderTbl SET del_flg=TRUE WHERE OrderTbl.order_id=?;";
				PreparedStatement pstmt = super.connection.prepareStatement(query);

				//Set
				pstmt.setString(1, history.getOrder_id());
				int succeededRowNum  = pstmt.executeUpdate();
				//commit
				super.connection.commit();

				//digest result
				////from LocalDateTime to like"2020/05/11 15:36:45"
				if(succeededRowNum!=1) {pstmt.close();return false;}
				
				pstmt.close();
				return true;
			}catch(SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}
	
	/**
	 * @param staff_name
	 * @param staff_roll_id
	 * @param experience
	 * @param page
	 * @param staffSummaries
	 * @return
	 */
	public boolean getStaffSummaries(String staff_name, String staff_roll_id, int experience, int page, ArrayList<StaffSummary> staffSummaries) {
		if(super.isConnect) {
			try {
				////precompile
				String query = "SELECT staff_id,staff_name,staff_roll_id,experience FROM StaffTbl";
				
				//If Search-condition exists, add "WHERE"
				if(isExist(staff_name) || isExist(staff_roll_id) || isExist(experience)) {
					query += " WHERE ";
					////WHERE CONDITION
					if(isExist(staff_name)) {
						query += "staff_name=?";
					}
					if(isExist(staff_roll_id)) {
						if(isExist(staff_name))query += " AND ";
						query += "staff_roll_id=?";
					}
					if(isExist(experience)) {
						if(isExist(staff_name) || isExist(staff_roll_id))query += " AND ";
						query += "experience=?";
					}
				}

				query += " LIMIT 10";
				query += " OFFSET "+ ((Integer)((page-1)*10)).toString();//0,10,20,...
				query+=";";
				
				PreparedStatement pstmt = super.connection.prepareStatement(query);
				
				//Set
				////from LocalDateTime to like"2020/05/11 15:36:45"
				DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				if(isExist(staff_name) || isExist(staff_roll_id) || isExist(experience)) {
					if(isExist(staff_name)) {
						pstmt.setString(1, staff_name);
					}
					if(isExist(staff_roll_id)) {
						if(isExist(staff_name)){
							pstmt.setString(2, staff_roll_id);
						}else {
							pstmt.setString(1, staff_roll_id);
						}
					}
					if(isExist(experience)) {
						if(isExist(staff_name) && isExist(staff_roll_id)) {
							pstmt.setInt(3, experience);
						}
						else if(isExist(staff_name) || isExist(staff_roll_id)) {//XOR
							pstmt.setInt(2, experience);
						}
						else {
							pstmt.setInt(1, experience);
						}
					}
				}
				
				ResultSet resultSet = pstmt.executeQuery();
				////commit
				super.connection.commit();

				//digest result
				while(resultSet.next()) {
					StaffSummary key = new StaffSummary();
					key.setStaff_id(resultSet.getString("staff_id"));
					key.setStaff_name(resultSet.getString("staff_name"));
					key.setStaff_roll_id(resultSet.getString("staff_roll_id"));
					key.setExperience(resultSet.getInt("experience"));
					staffSummaries.add(key);
				}

				pstmt.close();
				return true;
			}catch(SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}

	/**
	 * @param staff_id
	 * @param staff
	 * @return
	 */
	public boolean getStaff(int staff_id, Staff staff) {
		if(super.isConnect) {
			try {
				////precompile
				String query = "SELECT staff_id,staff_name,staff_pw,staff_roll_id,experience,gender,age,mail,tel_no,join_date,leave_date FROM StaffTbl WHERE staff_id=?;";
				PreparedStatement pstmt = super.connection.prepareStatement(query);

				//Set
				pstmt.setInt(1, staff_id);
				ResultSet resultSet = pstmt.executeQuery();
				////commit
				super.connection.commit();

				//digest result
				while(resultSet.next()) {
					staff.setStaff_id(resultSet.getInt("staff_id"));
					staff.setStaff_name(resultSet.getString("staff_name"));
					staff.setStaff_pw(resultSet.getString("staff_pw"));
					staff.setStaff_roll_id(resultSet.getInt("staff_roll_id"));
					staff.setExperience(resultSet.getInt("experience"));
					staff.setGender(resultSet.getString("gender"));
					staff.setAge(resultSet.getInt("age"));
					staff.setMail(resultSet.getString("mail"));
					staff.setTel_no(resultSet.getString("tel_no"));
					staff.setJoin_date(resultSet.getString("join_date"));
					staff.setLeave_date(resultSet.getString("leave_date"));
					break;
				}

				pstmt.close();
				return true;
			}catch(SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}
	
	/**
	 * @param staff
	 * @return
	 */
	public boolean editStaff(Staff staff){
		if(staff==null) {System.out.println("editStaff() failed; staff is null");return false;}
		if(super.isConnect) {
			try {
				//precompile
				String query = "UPDATE StaffTbl SET staff_name=?,staff_pw=?,staff_roll_id=?,experience=?,gender=?,age=?,mail=?,tel_no=?,join_date=?,leave_date=?,upd_date=? WHERE staff_id=?;";//TODO:個別更新、９個もisExistとifelse文を書くのは面倒すぎる
				PreparedStatement pstmt = super.connection.prepareStatement(query);

				//Set
				pstmt.setString(1, staff.getStaff_name());
				pstmt.setString(2, staff.getStaff_pw());
				pstmt.setInt(3, staff.getStaff_roll_id());
				pstmt.setInt(4, staff.getExperience());
				pstmt.setString(5, staff.getGender());
				pstmt.setInt(6, staff.getAge());
				pstmt.setString(7, staff.getMail());
				pstmt.setString(8, staff.getTel_no());
				//from LocalDateTime to like"2020/05/11 15:36:45"
				LocalDateTime localdatetime = LocalDateTime.now();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
				String timeStr = localdatetime.format(formatter);
				pstmt.setString(9, staff.getJoin_date());
				pstmt.setString(10, staff.getLeave_date());
				pstmt.setString(11, timeStr);
				pstmt.setInt(12, staff.getStaff_id());
				int succeededRowNum  = pstmt.executeUpdate();
				//commit
				super.connection.commit();

				//digest result
				////from LocalDateTime to like"2020/05/11 15:36:45"
				if(succeededRowNum!=1) {pstmt.close();throw new SQLException();}
				
				pstmt.close();
				return true;
			}catch(SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}
	
	/**
	 * @param staff
	 * @return
	 */
	public boolean deleteStaff(Staff staff) {
		if(staff==null) {System.out.println("deleteStaff() failed; staff is null");return false;}
		if(super.isConnect) {
			try {
				//precompile
				String query = "UPDATE StaffTbl SET del_flg=TRUE WHERE staff_id=?;";
				PreparedStatement pstmt = super.connection.prepareStatement(query);

				//Set
				pstmt.setInt(1, staff.getStaff_id());
				int succeededRowNum  = pstmt.executeUpdate();
				//commit
				super.connection.commit();

				//digest result
				if(succeededRowNum!=1) {pstmt.close();return false;}
				
				pstmt.close();
				return true;
			}catch(SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}
	

	/**
	 * @param staff
	 * @return
	 */
	public boolean registerStaff(Staff staff){
		if(staff==null) {System.out.println("registarStaff() failed; staff is null");return false;}
		if(super.isConnect) {
			try {
				//precompile
				String query = "INSERT INTO StaffTbl(staff_id,staff_name,staff_pw,staff_roll_id,experience,gender,age,mail,tel_no,join_date,leave_date,del_flg,ins_date,upd_date) VALUES(?,?,?,?,?,?,?,?,?,?,?,FALSE,?,?);";
				PreparedStatement pstmt = super.connection.prepareStatement(query);

				//Set
				pstmt.setInt(1, staff.getStaff_id());
				pstmt.setString(2, staff.getStaff_name());
				pstmt.setString(3, staff.getStaff_pw());
				pstmt.setInt(4, staff.getStaff_roll_id());
				pstmt.setInt(5, staff.getExperience());
				pstmt.setString(6, staff.getGender());
				pstmt.setInt(7, staff.getAge());
				pstmt.setString(8, staff.getMail());
				pstmt.setString(9, staff.getTel_no());
				//from LocalDateTime to like"2020/05/11 15:36:45"
				LocalDateTime localdatetime = LocalDateTime.now();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
				String timeStr = localdatetime.format(formatter);
				pstmt.setString(10, staff.getJoin_date());
				pstmt.setString(11, staff.getLeave_date());
				pstmt.setString(12, timeStr);
				pstmt.setString(13, timeStr);
				int succeededRowNum  = pstmt.executeUpdate();
				//commit
				super.connection.commit();

				//digest result
				////from LocalDateTime to like"2020/05/11 15:36:45"
				if(succeededRowNum!=1) {pstmt.close();return false;}
				
				pstmt.close();
				return true;
			}catch(SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}
	
	private boolean isExist(String str) {return (!"".equals(str) && str!=null);}
	private boolean isExist(DateKind dateKind) {return (dateKind==DateKind.ORDER || dateKind==DateKind.LIMIT || dateKind==DateKind.CONFIRM);}
	private boolean isExist(int num) {return true;}

}
/**/