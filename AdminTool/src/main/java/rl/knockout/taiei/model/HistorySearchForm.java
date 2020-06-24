package rl.knockout.taiei.model;

import javax.validation.constraints.*;

public class HistorySearchForm{
	@Size(min=0,max=20)String acc_id;
	@Size(min=0,max=20)String login_name;
	@Size(min=0,max=20)String order_status;
	@Size(min=0,max=20)String dateKindString;
	String beginDate;
	String endDate;
	@NotNull @Min(value=0, message="正の整数で指定してください") int nowPage;
	
	public String getAcc_id() {
		return acc_id;
	}
	public void setAcc_id(String acc_id) {
		this.acc_id = acc_id;
	}
	public String getLogin_name() {
		return login_name;
	}
	public void setLogin_name(String login_name) {
		this.login_name = login_name;
	}
	public String getOrder_status() {
		return order_status;
	}
	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}
	public String getDateKindString() {
		return dateKindString;
	}
	public void setDateKindString(String dateKindString) {
		this.dateKindString = dateKindString;
	}
	public String getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public int getNowPage() {
		return nowPage;
	}
	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}
}