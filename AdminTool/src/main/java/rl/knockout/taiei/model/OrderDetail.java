/*
 Only for History class
 Just putting together fields in History class
 
 But in getter/setter, return value type / argument type is to be ClassType, so to be public (rather dangerous, though)
 */
package rl.knockout.taiei.model;

import javax.validation.constraints.*;

public class OrderDetail{
	@NotNull(message="入力してください") @Min(value = 0, message = "The value must be positive") private int voucher_id;
	@NotNull(message="入力してください") @Min(value = 0, message = "The value must be positive") private int product_id;
	@NotNull(message="入力してください") @Min(value = 0, message = "The value must be positive") private int stock;
	@NotNull(message="入力してください") @Min(value = 0, message = "The value must be positive") private int amount;
	//below are not included in OrderDetailTbl
	@NotEmpty(message="入力してください") private String product_name;
	@NotNull(message="入力してください") @Min(value = 0, message = "The value must be positive") private int product_price;
	
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