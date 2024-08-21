package review;

import lombok.Data;

@Data
public class ReviewVo {
	private String ridx;
	private String orderid;
	private String uid;
	private String uname;
	private String pid;
	private String pname;
	private String rnote;
	
	private String ch1;
	private String ch2;
		
	private int pidx; // page idx
	private int paseSize; // 페이지사이즈 10
	private int listSize; // 리스트사이즈 10 (1~10),(11~20),...
	private int totalRecord; // 총레코드수
	private int totalPage; // 총페이지수 : 올림(총레코드수/페이지사이즈)
	private int currentPage; // 현재페이지 ?
	private int listStartPage; // 리스트시작 1,11,21,...
	private int listEndPage; // 리스트끝 10,20,30,...
}
