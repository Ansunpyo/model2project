package controller;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.EditProfileAction;
import action.EditProfilePageAction;
import action.FavoritesAction;
import action.FavoritesDeleteAction;
import action.JoinAction;
import action.LoginAction;
import action.MessageDeleteAction;
import action.MessageSendAction;
import action.MessengerAction;
import action.MyMessageAction;
import action.MyReviewAction;
import action.OneOnOneListAction;
import action.OneOnOneSendAction;
import action.QuitAction;
import action.ReviewAction;
import action.ReviewDeleteAction;
import action.ReviewUpdateAction;
import action.ReviewUpdatePageAction;
import action.ReviewViewAction;
import action.ReviewWriteAction;
import action.ReviewWritePageAction;
import action.IntroDeleteProAction;
import action.IntroDetailAction;
import action.IntroListAction;
import action.IntroModifyFormAction;
import action.IntroModifyProAction;
import action.IntroWriteProAction;
import vo.ActionForward;

@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProc(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProc(request, response);
	}

	protected void doProc(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length());
		ActionForward forward = null;
		Action action = null;

		if (command.contentEquals("/index.do")) {
			forward = new ActionForward();
			forward.setPath("/index.jsp");
		} else if (command.contentEquals("/login.do")) {
			action = new LoginAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.contentEquals("/join.do")) {
			action = new JoinAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.contentEquals("/editProfilePage.do")) {
			action = new EditProfilePageAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.contentEquals("/editProfile.do")) {
			action = new EditProfileAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.contentEquals("/favorites.do")) {
			action = new FavoritesAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.contentEquals("/favoritesDelete.do")) {
			action = new FavoritesDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.contentEquals("/quit.do")) {
			action = new QuitAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.contentEquals("/review.do")) {
			action = new ReviewAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.contentEquals("/reviewView.do")) {
			action = new ReviewViewAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.contentEquals("/reviewDelete.do")) {
			action = new ReviewDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.contentEquals("/reviewUpdatePage.do")) {
			action = new ReviewUpdatePageAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.contentEquals("/reviewUpdate.do")) {
			action = new ReviewUpdateAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.contentEquals("/reviewWritePage.do")) {
			action = new ReviewWritePageAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.contentEquals("/reviewWrite.do")) {
			action = new ReviewWriteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.contentEquals("/myReview.do")) {
			action = new MyReviewAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.contentEquals("/messenger.do")) {
			action = new MessengerAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.contentEquals("/messageDelete.do")) {
			action = new MessageDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.contentEquals("/sendMessage.do")) {
			action = new MessageSendAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.contentEquals("/myMessage.do")) {
			action = new MyMessageAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.contentEquals("/one_on_one.do")) {
			action = new OneOnOneListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.contentEquals("/one_on_one_submit.do")) {
			action = new OneOnOneSendAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
// 여기서 부터 내가 할 영역			
		} else if (command.equals("/introWriteForm.do")) {
			forward = new ActionForward();
			forward.setPath("/intro/intro_write.jsp");
		} else if (command.equals("/introWritePro.do")) {
			action = new IntroWriteProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/introList.do")) {
			action = new IntroListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/introDetail.do")) {
			action = new IntroDetailAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/introModifyForm.do")) {
			action = new IntroModifyFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/introModifyPro.do")) {
			action = new IntroModifyProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/introDeleteForm.do")) {
			String nowPage = request.getParameter("page");
			request.setAttribute("page", nowPage);
			int number = Integer.parseInt(request.getParameter("number"));
			request.setAttribute("number", number);
			forward = new ActionForward();
			forward.setPath("/intro/intro_delete.jsp");
		} else if (command.equals("/introDeletePro.do")) {
			action = new IntroDeleteProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (forward != null) {

			if (forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
	}
}
