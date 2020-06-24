package rl.knockout.taiei.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.*;
import javax.validation.constraints.*;


//DAO
public class History{
	//From/To OrderTbl
	@NotEmpty(message="入力してください") @Size(min=0,max=20) private String order_id;
	@NotEmpty(message="入力してください") @Size(min=0,max=20) private String acc_id;
	@NotNull(message="入力してください") private int whole_amount;
	@NotNull(message="入力してください") private double tax;
	@NotEmpty(message="入力してください") private String order_date;
	@NotEmpty(message="入力してください") private String limit_date;
	@NotEmpty(message="入力してください") private String confirm_date;
	@NotEmpty(message="入力してください") private String order_status;
	
	//From/To OrderDetailTbl
	//From/To ProductTbl
	@Valid private ArrayList<OrderDetail> orderDetail;{orderDetail = new ArrayList<OrderDetail>();};
	
	//From/To Account
	@NotEmpty(message="入力してください") @Size(min=0,max=20) private String login_name;
	
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public String getAcc_id() {
		return acc_id;
	}
	public void setAcc_id(String acc_id) {
		this.acc_id = acc_id;
	}
	public int getWhole_amount() {
		return whole_amount;
	}
	public void setWhole_amount(int whole_amount) {
		this.whole_amount = whole_amount;
	}
	public double getTax() {
		return tax;
	}
	public void setTax(double tax) {
		this.tax = tax;
	}
	public String getOrder_date() {
		return order_date;
	}
	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}
	public String getLimit_date() {
		return limit_date;
	}
	public void setLimit_date(String limit_date) {
		this.limit_date = limit_date;
	}
	public String getConfirm_date() {
		return confirm_date;
	}
	public void setConfirm_date(String confirm_date) {
		this.confirm_date = confirm_date;
	}
	public String getOrder_status() {
		return order_status;
	}
	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}
	public ArrayList<OrderDetail> getOrderDetail() {
		return orderDetail;
	}
	public void setOrderDetail(ArrayList<OrderDetail> orderDetail) {
		this.orderDetail = orderDetail;
	}
	public String getLogin_name() {
		return login_name;
	}
	public void setLogin_name(String login_name) {
		this.login_name = login_name;
	}
	
	//Only for OrderDetail
	public void addOrderDetail(OrderDetail orderDetail) {
		this.orderDetail.add(orderDetail);
	}
	public void clearOrderDetail() {
		this.orderDetail.clear();
	}
}