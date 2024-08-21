package pay;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Random;
import java.util.Scanner;

public class PaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    private static final String KAKAO_API_KEY = "af99e988ba0822beb55ac73e77e5667d"; // 발급받은 Admin 키
    private static final String CID = "TC0ONETIME"; // 테스트 CID (고정키) -> 실제로는 상점 ID가 들어감 
       
    public PaymentServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		
		// String path = request.getContextPath();
        String path = "http://localhost:8090/tinyEgo"; // 필히 문자열로 넘겨주어야 한다. (중요)
		
        String apiUrl = "https://kapi.kakao.com/v1/payment/ready";
        URL url = new URL(apiUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Authorization", "KakaoAK " + KAKAO_API_KEY);
        conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        conn.setDoOutput(true);
        
        System.out.println("conn: " + conn);
        
        HttpSession session = request.getSession();
        
        // 현재 날짜를 yyMMdd 포맷으로 얻기
        LocalDate currentDate = LocalDate.now();
        String datePart = String.format("%02d%02d%02d", currentDate.getYear(), currentDate.getMonthValue(), currentDate.getDayOfMonth());
        // 5자리 랜덤 숫자 생성
        Random random = new Random();
        int randomNumber = random.nextInt(100000);
        String randomPart = String.format("%05d", randomNumber);
        String orderid = datePart + randomPart;
        session.setAttribute("orderid", orderid);
        
        String uid = (String) session.getAttribute("uid");
        session.setAttribute("uid", uid);
        
        System.out.println("(1.Payment) orderid: " + orderid);
        System.out.println("(1.Payment) uid: "+uid);
        
        String totalprice = request.getParameter("totalprice");
        session.setAttribute("totalprice", totalprice);
        
        String dname = request.getParameter("dname");
        String dphone = request.getParameter("dphone");
        String dzip = request.getParameter("dzip");
        String daddr1 = request.getParameter("daddr1");
        String daddr2 = request.getParameter("daddr2");
        String dmemo = request.getParameter("dmemo");
        
        System.out.println("request.getParameter dname : "+dname);
        session.setAttribute("dname", dname);
        session.setAttribute("dphone", dphone);
        session.setAttribute("daddr", dzip+") "+daddr1+", "+daddr2);
        session.setAttribute("dmemo", dmemo);
                
        String params = "cid=" + CID +
                        "&partner_order_id=" + orderid + //  주문번호
                        "&partner_user_id=" + uid +		 //  고객번호
                        "&item_name=tinyEgo" +			 //  상품명 
                        "&quantity=1" +					 //  주문수량
                        "&total_amount=" + totalprice +	 //  총결제금액
                        "&vat_amount=1" +				 //  부가가치세(세금)
                        "&tax_free_amount=0" +			 //  면세
                        "&approval_url=" + path + "/ApproveServlet" +	//  성공
                        "&cancel_url=" + path + "/CartController?sw=S" +//  실패
                        "&fail_url="+ path +"/CartController?sw=S";		//  취소

        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = params.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        int code = conn.getResponseCode();
        Scanner sc;
        if (code == 200) {
            sc = new Scanner(conn.getInputStream());
            System.out.println("conn1: " + sc);
        } else {
            sc = new Scanner(conn.getErrorStream());
            System.out.println("conn2: " + sc);
        }
        
        StringBuilder result = new StringBuilder();
        while (sc.hasNext()) {
            result.append(sc.nextLine());
        }
        System.out.println("result: " + result);
        sc.close();
        
        // JSON 파싱 후, next_redirect_pc_url 값으로 리다이렉트
        
        // tid 는 결제 준비단계에서 카카오페이로부터 받은 거래고유번호이다. 
        // 이 tid 는 결제 승인단계에서 필수로 사용되며 세션 또는 다늘 방법으로 관리해야 한다. 
        // 준비단계에서 받은 tid를 결제 승인 요청시 함께 전송하여 결제를 완료해야 한다. 
        String tid = result.toString().split("\"tid\":\"")[1].split("\"")[0];
        System.out.println("tid :"+tid);
        
        // 방법1.
        request.getSession().setAttribute("tid", tid);
        
        // 방법2.
//        HttpSession session = request.getSession();
//        session.setAttribute("tid", tid);
        
        String redirectUrl = result.toString().split("\"next_redirect_pc_url\":\"")[1].split("\"")[0];
//        System.out.println("===>redirectUrl 확인: " + redirectUrl);
        response.sendRedirect(redirectUrl);
        
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
