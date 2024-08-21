package cart;

import lombok.Data;

@Data
public class CartVo {
	private String cartid;
	private String cartAmount;
	
	private String uid;
	private String pid;
	
	private String orderid;
	private String orderAmount; 
	private String orderPrice;
	private String delcost;
	private String dname;
	private String dphone;
	private String daddr; // dzip + daddr1 + daddr2
	private String dmemo;

}
