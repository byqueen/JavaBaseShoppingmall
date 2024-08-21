package cart;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CartController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sw = request.getParameter("sw");
		String path = request.getContextPath();
		
		HttpSession session = request.getSession();
		String orderid = (String) session.getAttribute("orderid");
		String uid = (String) session.getAttribute("uid");
		String cartid = request.getParameter("cartid");
		String pid = request.getParameter("pid");
		String cartAmount = request.getParameter("cartAmount");
		
		CartVo vo = new CartVo();
		CartService service = new CartServiceImpl();
		
		if (sw.equals("F")) {
		
		} else if (sw.equals("S")) {
			vo.setUid(uid);
			request.setAttribute("li", service.getSelectAllCart(vo));
			RequestDispatcher dispatcher = request.getRequestDispatcher("/cart/list.jsp");
			dispatcher.forward(request, response);
			
		} else if (sw.equals("I")) {
			vo.setUid(uid);
			List<HashMap<String, Object>> li = service.getSelectAllCart(vo);
			boolean itemExistsInCart = false;

			for (HashMap<String, Object> m : li) {
//				System.out.println("===> in cart item: "+m);
			    if (m.get("pid").equals(pid)) {
			        itemExistsInCart = true;
			        cartid = (String) m.get("cartid");
			        vo.setUid(uid);
			        vo.setCartid(cartid);
			        vo.setCartAmount(cartAmount);
			        service.insertCartIn(vo);
//			        System.out.println("===> Updated existing cart item: " + vo);
			        break; // Exit
			    }
			}

			if (!itemExistsInCart) {
			    vo.setUid(uid);
			    vo.setPid(pid);
			    vo.setCartAmount(cartAmount);
			    service.insertCart(vo);
//			    System.out.println("===> Added new cart item: " + vo);
			}
			response.sendRedirect(path+"/CartController?sw=S");
		
		} else if (sw.equals("U")) { // 장바구니 수량
			String [] cartidList = request.getParameterValues("cartid");
			String [] cartAmountList = request.getParameterValues("cartAmount");
			
			for (int i = 0; i < cartidList.length; i++) {
				vo.setCartid(cartidList[i]);
				vo.setCartAmount(cartAmountList[i]);
				service.updateCart(vo);		
			}
			response.sendRedirect(path+"/CartController?sw=S");
			
		} else if (sw.equals("D")) {
			vo.setPid(pid);
			service.deleteCart(vo);
			response.sendRedirect(path+"/CartController?sw=S");

		} else if (sw.equals("D_all")) {
			service.deleteCartAll(vo);
			response.sendRedirect(path+"/CartController?sw=S");
			
		} else if (sw.equals("orderIn")) {
			vo.setUid(uid);
			List<HashMap<String, Object>> li = service.getSelectAllCart(vo);
			for (HashMap<String, Object> m : li) {
			        pid = (String) m.get("pid");
			        String orderAmount = (String) m.get("cartAmount");
			        String orderPrice = (String) m.get("salePrice");
			        String delcost = (String) m.get("delcost");
			        
			        vo.setOrderid(orderid);
					vo.setUid(uid);
					vo.setPid(pid);
					vo.setOrderAmount(orderAmount);
					vo.setOrderPrice(orderPrice);
					vo.setDelcost(delcost);
					vo.setDname((String)session.getAttribute("dname"));
					vo.setDphone((String)session.getAttribute("dphone"));
					vo.setDaddr((String)session.getAttribute("daddr"));
					vo.setDmemo((String)session.getAttribute("dmemo"));
					service.insertOrder(vo);
			}
			service.deleteCartAll(vo);
			session.removeAttribute("dname"); // 세션에 담은 값 삭제
			session.removeAttribute("dphone");
			session.removeAttribute("daddr");
			session.removeAttribute("dmemo");
			response.sendRedirect(path+"/CartController?sw=orderS_user");
			
		} else if (sw.equals("orderS_adm")) {
			request.setAttribute("li", service.getSelectAllOrderAdm(vo));
			RequestDispatcher dispatcher = request.getRequestDispatcher("/order/list_adm.jsp");
			dispatcher.forward(request, response);
			
		} else if (sw.equals("orderS_user")) {
			vo.setUid(uid);
			request.setAttribute("li", service.getSelectAllOrderCust(vo));
			RequestDispatcher dispatcher = request.getRequestDispatcher("/order/list_user.jsp");
			dispatcher.forward(request, response);
						
		} else if (sw.equals("order_detail")) {
			orderid = request.getParameter("orderid");
			vo.setUid(uid);
			vo.setOrderid(orderid);
			request.setAttribute("li", service.getSelectOneOrder(vo));
			RequestDispatcher dispatcher = request.getRequestDispatcher("/order/detail.jsp");
			dispatcher.forward(request, response);
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
