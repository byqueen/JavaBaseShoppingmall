package login;

import user.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		String sw = request.getParameter("sw");
		
		HttpSession session = request.getSession();
		UserVo vo = new UserVo();
		UserService service = new UserServiceImpl(); 
				
		if(sw.equals("F")) {
			response.sendRedirect(path+"/login/form.jsp");
			
		} else if (sw.equals("login")) {
			String uid = request.getParameter("uid");
			String upwd = request.getParameter("upwd");
			vo.setUid(uid);
			vo.setUpwd(upwd);
			
			HashMap<String, Object> m = service.userLogin(vo);
			uid = (String) m.get("uid");			
			if (uid!=null && !uid.equals("")) {
				System.out.println("===> 로그인 성공");
				session.setAttribute("uid", uid);
				
				vo.setUid(uid);
				String ugrade = (String) service.getSelectOneUser(vo).get("ugrade");
				if (ugrade == null || !ugrade.equals("admin")) {
					response.sendRedirect(path+"/index.jsp");
				} else if (ugrade.equals("admin")) {
					session.setAttribute("ugrade", ugrade);
					response.sendRedirect(path+"/index.jsp");
				}
			} else {
				System.out.println("===> 로그인 실패");
				response.sendRedirect(path+"/LoginController?sw=F");
			}
//			PrintWriter out = response.getWriter(); // getWriter() 응답을 작성하기 위해 PrintWriter 객체를 반환함
//			out.print(uid); // 지정된 uid 값을 HTTP 응답으로 출력함
			
		} else if (sw.equals("out")) {
			session.invalidate();
			response.sendRedirect(path+"/index.jsp");
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
