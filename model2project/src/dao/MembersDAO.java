package dao;

import static db.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import vo.Member;

public class MembersDAO {
	private static MembersDAO membersDAO;
	private Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	int pageCount = 5;
	int range = 5;
	
	private MembersDAO() {
		
	}
	public static MembersDAO getInstance() {
		if(membersDAO == null) {
			membersDAO = new MembersDAO();
		}
		return membersDAO;
	}
	
	public void setConnection(Connection conn) {
		this.conn = conn;
	}
	public ArrayList<Member> selectMembersList(int nowPage) {
		String sql = "SELECT * FROM member ORDER BY number DESC LIMIT ?, " + pageCount;
		ArrayList<Member> memList = new ArrayList<Member>();
		Member mem = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (nowPage - 1) * pageCount);

			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				do {
					mem = new Member();
					mem.setClassify(rs.getString("classify"));
					mem.setId(rs.getString("id"));
					mem.setName(rs.getString("name"));
					mem.setEmail(rs.getString("email"));
					mem.setGender(rs.getString("gender"));
					if(rs.getString("classify").equals("교사")) {
						mem.setMajor(rs.getString("major"));
						mem.setEducation(rs.getString("education"));
					}
					memList.add(mem);
				} while(rs.next());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return memList;
	}
	public int getMembersNumber() {
		String sql = "SELECT count(*) AS c FROM member";
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
	public ArrayList<Member> selectQuittersList(int nowPage) {
		String sql = "SELECT * FROM quitter LIMIT ?, " + pageCount;
		ArrayList<Member> memList = new ArrayList<Member>();
		Member mem = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (nowPage - 1) * pageCount);

			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				do {
					mem = new Member();
					mem.setId(rs.getString("id"));
					mem.setEmail(rs.getString("email"));
					memList.add(mem);
				} while(rs.next());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return memList;
	}
	public int getQuittersNumber() {
		String sql = "SELECT count(*) AS c FROM quitter";
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
	
}
