package svc;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import dao.IntroDAO;
import vo.Intro;

public class IntroListService {

	public int getListCount() {
		int listCount=0;
		Connection con=getConnection();
		IntroDAO introDAO=IntroDAO.getInstance();
		introDAO.setConnection(con);
		listCount=introDAO.selectListCount();
		close(con);
		return listCount;
	}

	public ArrayList<Intro> getArticleList(int page, int limit) {
		ArrayList<Intro> articleList=null;
		Connection con = getConnection();
		IntroDAO boardDAO=IntroDAO.getInstance();
		boardDAO.setConnection(con);
		articleList=boardDAO.selectArticleList(page, limit);
		close(con);
		return articleList;
	
	}
}
