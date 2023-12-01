package com.fooding.admin.action;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fooding.admin.db.AdminDAO;
import com.fooding.util.Action;
import com.fooding.util.ActionForward;
import com.fooding.util.JSMoveFunction;

public class AdminFoodtruckDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 출력문
		System.out.println("\t\t M : AdminFoodtruckDeleteAction_execute() 호출 ");
		
		// 세션 제어
		HttpSession session = request.getSession();
//		session.setAttribute("id", "admin"); // 이 부분만 삭제하면 됨
		String id = (String) session.getAttribute("id");
		
		ActionForward forward = new ActionForward();
		if(id == null) {
			forward.setPath("./FtMainAction.man"); // main 주소
			forward.setRedirect(true);
			return forward;
		}else if(id != null && !id.equals("admin")) {
			forward.setPath("./FtMainAction.man"); // main 주소
			forward.setRedirect(true);
			return forward;
		}
		
		// 한글 처리 (배포 서술자 자동)
		
		// 전달 정보 저장 (member_id)
		int foodtruck_id = Integer.parseInt(request.getParameter("foodtruck_id"));
		
		// 삭제 메서드 호출
		AdminDAO dao = new AdminDAO();
		// 기존 이미지 있는지 확인
		String image = dao.adminFoodtruckImage(foodtruck_id);
		int result = dao.adminFoodtruckDelete(foodtruck_id);
		System.out.println("result : " + result);
		
		
		if(result == 1) {
			// 기존 이미지 삭제
			String realPath = request.getRealPath("/upload/foodtruck");
			File file = new File(realPath + "/" + image);
			file.delete();
			JSMoveFunction.alertLocation(response, "푸드트럭 삭제가 처리되었습니다.", "./AdminFoodtruck.adm");
			return null;
		}else {
			JSMoveFunction.alertHistory(response, "삭제가 실패했습니다.");
		}
		return null;
	}

}
