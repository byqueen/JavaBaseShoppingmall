package review;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class ReviewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public ReviewController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String sw = request.getParameter("sw");
		String path = request.getContextPath();
		
		ReviewVo vo = new ReviewVo();
		ReviewService service = new ReviewServiceImpl();
		
		String orderid = request.getParameter("orderid");
		String uid = request.getParameter("uid");
		String uname = request.getParameter("uname");
		String pid = request.getParameter("pid");
		String pname = request.getParameter("pname");
		String rnote = request.getParameter("rnote");
		
		if (sw.equals("E")) {
			vo.setOrderid(orderid);
			vo.setPid(pid);
			request.setAttribute("m", service.getSelectOneReview(vo));
			RequestDispatcher dispatcher = request.getRequestDispatcher("/review/form.jsp");
			dispatcher.forward(request, response);
		
		} else if (sw.equals("S")) {
			String ch1 = request.getParameter("ch1");
			String ch2 = request.getParameter("ch2");
			vo.setCh1(ch1);
			vo.setCh2(ch2);
			
			String pidxstr = request.getParameter("pidx");
//			System.out.println("===> pidxstr: "+pidxstr);
			int pidx = 0;
			if (pidxstr == null) {
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
			
			int totalPage = (int) Math.ceil((double)totalRecord/pageSize);
			request.setAttribute("totalPage", totalPage);
			
			int currentPage = (pidx/pageSize)+1; // 현재페이지
			request.setAttribute("currentPage", currentPage);
			
			int listStartPage = (currentPage-1)/listSize*listSize+1 ; // 리스트시작페이지
			request.setAttribute("listStartPage", listStartPage);
			
			int listEnd = listStartPage+listSize-1; // 리스트끝페이지
			request.setAttribute("listEndPage", listEnd);
			
			request.setAttribute("li", service.getSelectAllReview(vo));
			RequestDispatcher dispatcher = request.getRequestDispatcher("/review/list.jsp");
			dispatcher.forward(request, response);
			
		} else if (sw.equals("I")) {
			vo.setOrderid(orderid);
			vo.setUid(uid);
			vo.setUname(uname);
			vo.setPid(pid);
			vo.setPname(pname);
			vo.setRnote(rnote);
			service.insertReview(vo);
			response.sendRedirect(path+"/ReviewController?sw=S");
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}