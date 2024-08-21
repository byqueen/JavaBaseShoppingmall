package login;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.HashMap;

public class KakaoLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public KakaoLoginController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		
		String code = request.getParameter("code");
		System.out.println("====>code :" +  code);
		
		KaKaoLoginService kakao = new KaKaoLoginService();
		
		HttpSession session = request.getSession();
		
		String access_Token = kakao.getKakaoAccessToken(code);
		HashMap<String, Object> userInfo = kakao.getUserInfo(access_Token);
			System.out.println("====> controller access_token: " +  access_Token);
			System.out.println("====> controller userInfo: " +  userInfo);
			if (userInfo.get("email") != null) {
		        session.setAttribute("email", userInfo.get("email"));
		        session.setAttribute("nickname", userInfo.get("nickname"));
		        session.setAttribute("profile_image", userInfo.get("profile_image"));
		        session.setAttribute("access_Token", access_Token);
		        
		    }				
//		response.sendRedirect(path+"/index.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
