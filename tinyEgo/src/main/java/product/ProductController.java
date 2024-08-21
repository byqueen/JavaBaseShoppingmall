package product;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

//해당 페이지 내에서만 MultipartConfig 를 적용할 경우 (web.xml 에서도 가능함)
@MultipartConfig (
	location = "/", 
	fileSizeThreshold = 1024*1024, 
	maxFileSize = 1024*1024*5, 
	maxRequestSize = 1024*1024*5*5
)

public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ProductController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 한글 처리
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// 값의 multipart 여부에 따라 메소드를 호출
		String contentType = request.getContentType();
		if (contentType != null && contentType.toLowerCase().startsWith("multipart/")) {
			try {
				multipartIn(request, response); // multipartIn 메소드 호출
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else {
			try {
				multipartNot(request, response); // multipartNot 메소드 호출
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void multipartIn(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String sw = request.getParameter("sw");
		String path = request.getContextPath();
		String realFolder = getServletContext().getRealPath("/product/files/"); // 파일이 저장되는 물리적 경로
		System.out.println("===> realFolder: "+realFolder);
		
		ProductService service = new ProductServiceImpl();
		
		String brand = request.getParameter("brand");
		String pname = request.getParameter("pname");
		String supplyPrice = request.getParameter("supplyPrice");
		String salePrice = request.getParameter("salePrice");
		String delcost = request.getParameter("delcost");
		String pnote = request.getParameter("pnote");
		ProductVo vo = new ProductVo();
		
		String pimg = "";
//		img의 바이너리(메타정보) 그대로 받아 part 객체에 저장
		Part part = request.getPart("pimg");
//		.getSubmittedFileName() : 확장자 사용을 위해 파일명을 가져옴
		String originName = part.getSubmittedFileName();
		System.out.println("===> originName : "+originName);
//		System.out.println("originName : "+originName);
		if (originName.equals("")) {
			vo.setPimg("space.png");
		} else {
//			.substring(변수명.lastIndexOf(".") : 원본 파일 이름에서 확장자 추출
			String ext = originName.substring(originName.lastIndexOf("."));
//			저장될 파일 이름(1) 중복방지. UUID 유틸 클래스를 사용함
//			String txt = (UUID.randomUUID().toString()).replace("-", "");
//			String saveName = txt + ext;
//			System.out.println("saveName : "+saveName);
			
//			저장될 파일 이름(2) 중복방지. date(), Math.random() 사용함
			Date now = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd"); // yyMMddHHmmss
			String k = sdf.format(now);
			int ran= (int)(Math.random()*1000) + 101;
			part.write(realFolder + k + ran + ext); // .write() 폴더에 저장되는 이름
			pimg = k + ran + ext; // img 데이터베이스에 넣는 이름
			vo.setPimg(pimg);
		} 
		
		if (sw.equals("I")) {
			int maxPid = service.maxPid(vo);
			vo.setPid(maxPid);
			vo.setBrand(brand);
			vo.setPname(pname);
			vo.setSupplyPrice(supplyPrice);
			vo.setSalePrice(salePrice);
			vo.setDelcost(delcost);
			vo.setPnote(pnote);
			service.insertProd(vo);
			response.sendRedirect(path+"/ProductController?sw=S_adm");
			
		} else if (sw.equals("U")) {
			String pid = request.getParameter("pid");
			vo.setPid(Integer.parseInt(pid));
			vo.setBrand(brand);
			vo.setPname(pname);
			vo.setSupplyPrice(supplyPrice);
			vo.setSalePrice(salePrice);
			vo.setDelcost(delcost);
			vo.setPnote(pnote);
			
			HashMap<String, Object> m = service.getSelectOneProd(vo);
			if (!originName.equals("")) {
				System.out.println("==> originName : "+originName);
				if (!m.get("pimg").equals("space.png")) {
					System.out.println("===> file(O)");
					File delFile = new File(realFolder+m.get("pimg"));
					delFile.delete();
				} 
				service.updateProd(vo);
			} else {
				System.out.println("===> fileX");
				service.updateProd_fileX(vo);
			}
			// System.out.println("update vo : "+vo);
			response.sendRedirect(path+"/ProductController?sw=S_adm");
			
		}
	}

	private void multipartNot(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sw = request.getParameter("sw");
		String path = request.getContextPath();
		String realFolder = getServletContext().getRealPath("/rePsd/files/");
		ProductService service = new ProductServiceImpl();
		
		String ch1 = request.getParameter("ch1");
		String ch2 = request.getParameter("ch2");
		
		String pid = request.getParameter("pid");
		ProductVo vo = new ProductVo();
		
		if (sw.equals("F")) {
			response.sendRedirect(path+"/product/form.jsp");
			
		} else if (sw.equals("S_user")) {
			vo.setCh1(ch1);
			vo.setCh2(ch2);
			request.setAttribute("li", service.getSelectAllProd(vo));
			RequestDispatcher dispatcher = request.getRequestDispatcher("/product/list_user.jsp");
			dispatcher.forward(request, response);
			
		} else if (sw.equals("S_adm")) { // 관리자용 상품목록
			vo.setCh1(ch1);
			vo.setCh2(ch2);
			request.setAttribute("li", service.getSelectAllProd(vo));
			RequestDispatcher dispatcher = request.getRequestDispatcher("/product/list_adm.jsp");
			dispatcher.forward(request, response);
			
		} else if (sw.equals("E_user")) { // 상품 주문
			vo.setPid(Integer.parseInt(pid));
			request.setAttribute("m", service.getSelectOneProd(vo));
			RequestDispatcher dispatcher = request.getRequestDispatcher("/product/edit_user.jsp");
			dispatcher.forward(request, response);
			
		} else if (sw.equals("E_adm")) { // 상품 수정
			vo.setPid(Integer.parseInt(pid));
			request.setAttribute("m", service.getSelectOneProd(vo));
			RequestDispatcher dispatcher = request.getRequestDispatcher("/product/edit_adm.jsp");
			dispatcher.forward(request, response);
			
		} else if (sw.equals("D")) {
			// 1. 삭제할 파일명 가져오기
			vo.setPid(Integer.parseInt(pid));
			// 2. 파일 삭제
			HashMap<String, Object> m = service.getSelectOneProd(vo);
			File delFile = new File(realFolder + m.get("pthumimg"));
			if (delFile.exists()) { // 파일이 존재하고
				if (!m.get("pthumimg").equals("space.png")) { // 파일명이 space.png 가 아니면
					delFile.delete(); // 파일 삭제
				}
			}
			// 3. 레코드 삭제
			service.deleteProd(vo);
			response.sendRedirect(path+"/ProductController?sw=S");

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
