package dao;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import vo.Member;
import vo.One_On_One;

public class OneOnOneDAO {
	private static OneOnOneDAO oneOnOneDAO;
	private Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	int pageCount = 5;
	int range = 5;
	
	private OneOnOneDAO() {
		
	}
	
	public static OneOnOneDAO getInstance() {
		if(oneOnOneDAO == null) {
			oneOnOneDAO = new OneOnOneDAO();
		}
		return oneOnOneDAO;
	}
	
	public void setConnection(Connection conn) {
		this.conn = conn;
	}

	public ArrayList<One_On_One> selectOneOnOneList(String id) {
		String sql = "SELECT title, contents, answer FROM one_on_one WHERE number = (SELECT number FROM member WHERE id = ?)";
		ArrayList<One_On_One> oneList = new ArrayList<One_On_One>();
		One_On_One one = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				do {
					one = new One_On_One();
					one.setTitle(rs.getString("title"));
					one.setContents(rs.getString("contents"));
					if(rs.getString("answer") != null) {
						one.setAnswer(rs.getString("answer"));
					}
					oneList.add(one);
				} while(rs.next());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return oneList;
	}

	public int sendOneOnOne(String id, One_On_One one) {
		String sql = "INSERT INTO one_on_one VALUES ((SELECT number FROM member WHERE id = ?), ?, ?, NULL, NULL)";
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, one.getTitle());
			pstmt.setString(3, one.getContents());
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

	public ArrayList[] selectOneOnOneAdList(int nowPage) {
		String sql = "SELECT m.name, o.title, o.contents, o.answer, o.one_on_one_num FROM member AS m JOIN one_on_one AS o ON m.number = o.number ORDER BY o.one_on_one_num DESC LIMIT ?," + pageCount;
		ArrayList[] oneOnOneAdList = null;
		ArrayList<One_On_One> oneList = new ArrayList<One_On_One>();
		ArrayList<Member> memList = new ArrayList<Member>();
		One_On_One one = null;
		Member mem = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (nowPage - 1) * pageCount);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				do {
					mem = new Member();
					one = new One_On_One();
					mem.setName(rs.getString("name"));
					one.setTitle(rs.getString("title"));
					one.setContents(rs.getString("contents"));
					one.setOne_on_one_num(rs.getInt("one_on_one_num"));
					if(rs.getString("answer") != null) {
						one.setAnswer(rs.getString("answer"));
					}
					memList.add(mem);
					oneList.add(one);
				} while(rs.next());
				oneOnOneAdList = new ArrayList[] {memList, oneList};
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return oneOnOneAdList;
	}

	public int getOneOnOneNumber() {
		String sql = "SELECT count(*) AS c FROM one_on_one";
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt("c");
				if (result % 5 != 0) {
					result = (result / 5) + 1;
				} else {
					result = result / 5;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int oneOnOneAnswer(One_On_One one) {
		String sql = "UPDATE one_on_one SET answer = ? WHERE one_on_one_num = ?";
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, one.getAnswer());
			pstmt.setInt(2, one.getOne_on_one_num());
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
