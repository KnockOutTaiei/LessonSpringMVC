/*
 Only for History class
 Just putting together fields in History class
 
 But in getter/setter, return value type / argument type is to be ClassType, so to be public (rather dangerous, though)
 */
package rl.knockout.taiei.model;

public class OrderDetail{
	private int voucher_id;
	private int product_id;
	private int stock;
	private int amount;
	//below are not included in OrderDetailTbl
	private String product_name;
	private int product_price;
	
	public int getVoucher_id() {
		return voucher_id;
	}
	public void setVoucher_id(int voucher_id) {
		this.voucher_id = voucher_id;
	}
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public int getProduct_price() {
		return product_price;
	}
	public void setProduct_price(int product_price) {
		this.product_price = product_price;
	}
}