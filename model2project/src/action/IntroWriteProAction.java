package action;

import java.io.PrintWriter;


import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import svc.IntroWriteProService;
import vo.ActionForward;
import vo.Intro;

public class IntroWriteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		Intro intro = null;
//		String realFolder = "";
//		String saveFolder = "/boardUpload";
		int fileSize = 5 * 1024 * 1024;
//		ServletContext context = request.getServletContext();
//		realFolder = context.getRealPath(saveFolder);
		String realFolder = "C:/boardUpload";
		MultipartRequest multi = new MultipartRequest(request, realFolder, fileSize, "UTF-8", new DefaultFileRenamePolicy());
		intro = new Intro();
//		intro.setNumber(Integer(Integer.parseInt(request.getParameter("Number"))); 인트값으로
		intro.setContents(multi.getParameter("Contents"));
		intro.setImage(multi.getOriginalFileName((String)multi.getFileNames().nextElement()));
		IntroWriteProService introWriteProService = new IntroWriteProService();
		boolean isWriteSuccess = IntroWriteProService.registArticle(intro);
		
		if(!isWriteSuccess) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('등록실패');");
			out.println("history.back();");
			out.println("</script>");
		} else {
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("introList.do");
		}
		
		return forward;
	}

}
