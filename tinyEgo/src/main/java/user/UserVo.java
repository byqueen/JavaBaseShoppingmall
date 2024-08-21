package user;

import lombok.Data;

@Data
public class UserVo {
	private String uid;			// 회원아이디
	private String upwd;		// '암호화'암호
	private String uname;		// 회원명
	private String ubirth;		// 생년월일
	private String ugen;		// 성별
	private String uphone;		// 휴대폰번호
	private String uzip;		// 우편번호
	private String uaddr1;		// 도로명주소
	private String uaddr2;		// 상세주소
	private String ugrade;		// 고객등급
	private String unote;		// 고객메모
	private String joindate;	// 가입일시
	
	private String ch1;
	private String ch2;
	
	private int paseSize;		// 페이지사이즈 10
	private int listSize;		// 리스트사이즈 10 (1~10),(11~20),...
	private int totalRecord;	// 총레코드수
	private int totalPage;		// 총페이지수 : 올림(총레코드수/페이지사이즈)
	private int pidx;			// page idx
	private int currentPage;	// 현재페이지 ?
	private int listStartPage;	// 리스트시작 1,11,21,...
	private int listEndPage;	// 리스트끝 10,20,30,...
}
