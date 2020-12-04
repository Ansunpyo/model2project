package dao;

import static db.JdbcUtil.*;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.sql.DataSource;

import vo.Intro;

public class IntroDAO {
	DataSource ds;
	Connection conn;
	private static IntroDAO introDAO;
	
	private IntroDAO() {
	}
	
	public static IntroDAO getInstance() {
		if(introDAO == null) introDAO = new IntroDAO();
		return introDAO;
	}
	
	public void setConnection(Connection conn) {
		this.conn = conn;
	}
	
	public int selectListCount() {
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement("select count(*) from teacher_introduce");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				listCount = rs.getInt(1);
				
			}
		} catch (Exception e) {
			System.out.println("getListCount 에러: " + e);
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return listCount;
	}
	
	public ArrayList<Intro> selectArticleList(int page, int limit) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String Intro_sql = "select * from board order by board_re_ref desc, board_re_seq asc limit ?, ?";
		ArrayList<Intro> articleList = new ArrayList<Intro>();
		Intro intro = null;
		int startRow = (page - 1) * 10;
		
		try {
			pstmt = conn.prepareStatement(Intro_sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, limit);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				intro = new Intro();
				intro.setNumber(rs.getInt("Introduce_Number"));
				intro.setContents(rs.getString("Introduce_Contents"));
				intro.setImage(rs.getString("Introduce_image"));
				articleList.add(intro);
			}
		} catch (Exception e) {
			System.out.println("getBoardList 에러: " + e);
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return articleList;
	}

	public int insertArticle(Intro article) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int num = 0;
		String sql = "";
		int insertCount = 0;
		
		try {
			pstmt = conn.prepareStatement("select max(board_num) from board");
			rs = pstmt.executeQuery();
			
			if(rs.next()) num = rs.getInt(1) + 1;
			else num = 1;
			
			sql = "insert into board values (?,?,?)";
			if(rs != null) close(rs);
			if(pstmt != null) close(pstmt);

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, article.getNumber());
			pstmt.setString(2, article.getContents());
			pstmt.setString(3, article.getImage());
			
			insertCount = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("boardInsert 에러: " + e);
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return insertCount;
	}
}
