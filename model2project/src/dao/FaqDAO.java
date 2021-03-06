package dao;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import vo.Faq;
import vo.Lecture;
import vo.Member;

public class FaqDAO {
	private static FaqDAO faqDAO;
	private Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	private FaqDAO() {
		
	}
	
	public static FaqDAO getInstance() {
		if(faqDAO == null) {
			faqDAO = new FaqDAO();
		}
		return faqDAO;
	}
	
	public void setConnection(Connection conn) {
		this.conn = conn;
	}

	public ArrayList<Faq> selectFaqList() {
		String sql = "SELECT question, answer, faq_num FROM faq ORDER BY faq_num";
		ArrayList<Faq> faqList = new ArrayList<Faq>();
		Faq faq = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				do {
					faq = new Faq();
					faq.setQuestion(rs.getString("question"));
					faq.setAnswer(rs.getString("answer"));
					faq.setFaq_num(rs.getInt("faq_num"));
					faqList.add(faq);
				} while(rs.next());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return faqList;
	}

	public int deleteFaq(int faqId) {
		String sql = "DELETE FROM faq WHERE faq_num = ?";
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, faqId);
			result = pstmt.executeUpdate();
			commit(conn);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}

	public int writeFaq(Faq faq) {
		String sql = "INSERT INTO faq VALUES (NULL, ?, ?)";
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, faq.getQuestion());
			pstmt.setString(2, faq.getAnswer());
			result = pstmt.executeUpdate();
			commit(conn);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return result;
	}
}
