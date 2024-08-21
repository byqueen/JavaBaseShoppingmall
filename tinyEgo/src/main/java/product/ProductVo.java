package product;

import lombok.Data;

@Data
public class ProductVo {
	private int pid;
	private String brand;
	private String pname;
	private String supplyPrice;	// 공급가
	private String salePrice;	// 판매가
	private String delcost;		// 배송비
	private String pimg;
	private String pnote;
	private String regdate;		// 등록일시
	private int maxPid;
	private String ch1;
	private String ch2;
}