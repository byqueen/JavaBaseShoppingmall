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
import java.util.Scanner;

public class ApproveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    private static final String ADMIN_KEY = "af99e988ba0822beb55ac73e77e5667d"; // Admin 키
    private static final String CID = "TC0ONETIME"; // 테스트 CID
       
    public ApproveServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		String path = request.getContextPath();
		System.out.println("===> ApproveServlet" );
		
        String pgToken = request.getParameter("pg_token");
        String tid = (String) request.getSession().getAttribute("tid");
                        
        System.out.println("===> ApproveServlet pgToken 확인: " +  pgToken);
        System.out.println("===> ApproveServlet tid 확인: " + tid );
        
        String apiUrl = "https://kapi.kakao.com/v1/payment/approve";
        URL url = new URL(apiUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Authorization", "KakaoAK " + ADMIN_KEY);
        conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        conn.setDoOutput(true);

        System.out.println("===> ApproveServlet conn 확인: " + conn );
        
        HttpSession session = request.getSession();
        String orderid = (String) session.getAttribute("orderid");
        String uid = (String) session.getAttribute("uid");
        String totalprice = (String) session.getAttribute("totalprice");
        
        System.out.println("===> (2.Approve) orderid 확인: " + orderid );
        System.out.println("===> (2.Approve) uid 확인: " + uid );
        System.out.println("===> (2.Approve) totalprice 확인: " + totalprice );
        
        String params = "cid=" + CID +
                        "&tid=" + tid +
                        "&partner_order_id=" + orderid + 
                        "&partner_user_id=" + uid +
                        "&pg_token=" + pgToken;

        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = params.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        int code = conn.getResponseCode();
        Scanner sc;
        if (code == 200) {
            sc = new Scanner(conn.getInputStream());
            System.out.println("===> ApproveServlet sc 성공 확인: " + sc );
        } else {
            sc = new Scanner(conn.getErrorStream());
            System.out.println("===> ApproveServlet sc 실패 확인: " + sc );
        }
        
        StringBuilder result = new StringBuilder();
        while (sc.hasNext()) {
            result.append(sc.nextLine());
        }
        
        System.out.println("===> ApproveServlet result 확인: " + result );
        
        sc.close();
        
//        response.setContentType("application/json;charset=UTF-8");
//        response.getWriter().print(result.toString());

		response.sendRedirect(path+"/CartController?sw=orderIn");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
