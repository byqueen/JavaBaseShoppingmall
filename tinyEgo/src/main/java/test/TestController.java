package test;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class TestController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public TestController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getContextPath();
		String sw = request.getParameter("sw");

		if (sw.equals("F")) {
			response.sendRedirect(path + "/test1004/form.jsp");

		} else if (sw.equals("kakao")) {
			response.sendRedirect(path + "/test1004/pay.jsp");

		} else if (sw.equals("success")) {
			response.sendRedirect(path + "/test1004/success.jsp");
			
		} else if (sw.equals("I")) {
			System.out.println("===> 배열");

//			String[] fruits = { "사과", "수박", "딸기", "체리", "키위", "블루베리", "귤", "바나나", "토마토" };

			// 선택된 체크박스 값만 가져오기
			String[] selectedNames = request.getParameter("selectedNames").split(",");
			String[] selectedAmounts = request.getParameter("selectedAmounts").split(",");
			System.out.println("선택 갯수 : "+selectedNames.length);
			
			List<HashMap<String, Object>> li = new ArrayList<HashMap<String, Object>>();
			for (int i=0; i<selectedNames.length; i++) {
			    System.out.println(selectedNames[i] + " : " + selectedAmounts[i]);

			    HashMap<String, Object> m = new HashMap<>();
			    m.put("name", selectedNames[i]);
			    m.put("amount", selectedAmounts[i]);
			    li.add(m);
			}
			
			System.out.println("li :"+li);
			request.setAttribute("li", li); 
			RequestDispatcher dispatcher = request.getRequestDispatcher("/test1004/list.jsp");
			dispatcher.forward(request, response);

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
