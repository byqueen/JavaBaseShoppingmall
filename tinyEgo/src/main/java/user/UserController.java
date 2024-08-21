package user;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 한글 인코딩
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String path = request.getContextPath();
		String sw = request.getParameter("sw");
		
		String ch1 = request.getParameter("ch1");
		String ch2 = request.getParameter("ch2");
		
		String uid = request.getParameter("uid");
		String upwd = request.getParameter("upwd");
		String uname = request.getParameter("uname");
		String ubirth = request.getParameter("ubirth");
		String ugen = request.getParameter("ugen");
		String uphone = request.getParameter("uphone");
		String uzip = request.getParameter("uzip");
		String uaddr1 = request.getParameter("uaddr1");
		String uaddr2 = request.getParameter("uaddr2");
		String ugrade = request.getParameter("ugrade");
		String unote = request.getParameter("unote");
		UserVo vo = new UserVo();
		UserService service = new UserServiceImpl();
		
		if (sw.equals("F")) {
			response.sendRedirect(path+"/user/join.jsp");
			
		} else if (sw.equals("S")) {
			vo.setCh1(ch1);
			vo.setCh2(ch2);
			request.setAttribute("ch1", ch1);
			request.setAttribute("ch2", ch2);
			
			String idxStr = request.getParameter("pidx");
			int pidx = 0;
			if (idxStr==null) {
				pidx = 0;
			} else {
				pidx = Integer.parseInt(request.getParameter("pidx"));
			}
			vo.setPidx(pidx);
			request.setAttribute("pidx", pidx);
			
			int pageSize = 10;
			vo.setPaseSize(pageSize);
			request.setAttribute("pageSize", pageSize);
			
			int listSize = 10;
			request.setAttribute("listSize", listSize);
			
			int totalRecord = service.totalRecord(vo);
			request.setAttribute("totalRecord", totalRecord);
			
			int totalPage = (int)Math.ceil((double)totalRecord/pageSize);
			request.setAttribute("totalPage", totalPage);
			
			int currentPage = (pidx/pageSize)+1;
			request.setAttribute("currentPage", currentPage);
			
			int listStartPage = (currentPage-1)/listSize*listSize+1 ;
			request.setAttribute("listStartPage", listStartPage);
			
			int listEnd = listStartPage+listSize-1;
			request.setAttribute("listEndPage", listEnd);

			request.setAttribute("li", service.getSelectAllUser(vo));
			RequestDispatcher dispatcher = request.getRequestDispatcher("user/list.jsp");
			dispatcher.forward(request, response);
			
		} else if (sw.equals("E")) {
			vo.setUid(uid);
			request.setAttribute("m", service.getSelectOneUser(vo));
			RequestDispatcher dispatcher = request.getRequestDispatcher("user/edit.jsp");
			dispatcher.forward(request, response);
			
		} else if (sw.equals("I")) {
			vo.setUid(uid);
			upwd = BCrypt.hashpw(upwd, BCrypt.gensalt()); // BCrypt암호화
			vo.setUpwd(upwd);
			vo.setUname(uname);
			vo.setUbirth(ubirth);
			vo.setUgen(ugen);
			vo.setUphone(uphone);
			vo.setUzip(uzip);
			vo.setUaddr1(uaddr1);
			vo.setUaddr2(uaddr2);
			service.insertUser(vo);
			response.sendRedirect(path+"/index.jsp");
			
		} else if (sw.equals("U")) {
			System.out.println("===> upwd: "+upwd);
			if (upwd.equals("")) {
				vo.setUid(uid);
				vo.setUname(uname);
				vo.setUbirth(ubirth);
				vo.setUgen(ugen);
				vo.setUphone(uphone);
				vo.setUzip(uzip);
				vo.setUaddr1(uaddr1);
				vo.setUaddr2(uaddr2);
				vo.setUgrade(ugrade);
				vo.setUnote(unote);
				service.updateUser_pwdX(vo);
			} else {
				vo.setUid(uid);
				upwd = BCrypt.hashpw(upwd, BCrypt.gensalt()); // BCrypt암호화
				vo.setUpwd(upwd);
				vo.setUname(uname);
				vo.setUbirth(ubirth);
				vo.setUgen(ugen);
				vo.setUphone(uphone);
				vo.setUzip(uzip);
				vo.setUaddr1(uaddr1);
				vo.setUaddr2(uaddr2);
				vo.setUgrade(ugrade);
				vo.setUnote(unote);
				service.updateUser(vo);
			}
			response.sendRedirect(path+"/UserController?sw=S");
			
		} else if (sw.equals("D")) {
			vo.setUid(uid);
			service.deleteUser(vo);
			response.sendRedirect(path+"/UserController?sw=S");
			
		} else if (sw.equals("ckId")) {
			vo.setUid(uid);
			String idcheck = service.userIdCk(vo);
			
			PrintWriter out = response.getWriter(); // 서블릿에서 HTTP 응답을 클라이언트로 보낼 때 사용됨
			if (idcheck.equals("T")) {
				out.print("T"); // out.print() ajax 로 값을 넘길 수 있음
			} else {
				out.print("F");
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
}
