package com.nuriweb.mybom.model.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


import com.nuriweb.mybom.model.dao.inf.IMemberDAO;
import com.nuriweb.mybom.model.vo.CenterVO;
import com.nuriweb.mybom.model.vo.MemberVO;

@Repository
public class MemberMySqlDAOImpl implements IMemberDAO{

	private class MemberRowMapper 
	implements RowMapper<MemberVO> {
			// 콜백..
	@Override
	public MemberVO mapRow(ResultSet rs, int rowNum) 
			throws SQLException {			
		return new MemberVO(rs.getInt("id"), 
				rs.getString("email"), 
				rs.getString("nickname"), 
				rs.getString("password"), 
				rs.getTimestamp("joined_at"),
				rs.getTimestamp("updated_at"),
				rs.getInt("pw_question"),
				rs.getString("pw_answer")
				);
	}
	
}
	// SQL 정의부
		private static String SQL_INSERT_MEMBER = 
				"insert into members values(null, ?, ?, ?, now(), now(), ?, ?)";
		private static String SQL_INSERT_MEMBER_CRYPTO =
				"insert into members values(null, ?, ?, hex(aes_encrypt(?, ?)), now(), now(), ?, ?)"; // 마지막 인자가 login 
		private static String SQL_MEMBER_EMAIL_DUPCHECK =
				"select count(id) from members where email = ?";
		private static String SQL_MEMBER_NICK_DUPCHECK =
				"select count(id) from members where nickname = ?";
		private static String SQL_SELECT_ALL_MEMBERS =
				"select * from members order by joined_at desc;";
		private static String SQL_SELECT_ONE_MEMBER = 
				"select * from members where id = ?";
		private static String SQL_SELECT_ONE_MEMBER_NICK = 
				"select * from members where nickname = ?";
		private static String SQL_SELECT_ONE_MEMBER_EMAIL = 
				"select * from members where email = ?";
		private static String SQL_UPDATE_MEMBER = 
				"update members set nickname = ?, password = ?, updated_at = now(), pw_question = ?, pw_answer= ? where id = ?";
		private static String SQL_UPDATE_MEMBER2 = 
				"update members set nickname = ?,  updated_at = now(), pw_question = ?, pw_answer= ? where id = ?";
		
		private static String SQL_DECRYPT_PW = 
				"select id, nickname, aes_decrypt(unhex(password), ?) as pw, nickname from members where email = ?"; 
		private static String SQL_DECRYPT_PW2 = 
				"select id, nickname, cast(aes_decrypt(unhex(password),?) as char(32) character set utf8) as password, email from members where email = ?";
		private static String SQL_MEMBER_DELETE =
				"delete from members where id = ?";
		private static String SQL_UPDATE_PW =
				"update members set password = hex(aes_encrypt(?, ?)) where email = ?";
		private static String SQL_CHECK_PW_QNA =
				"select * from members where id = ? and pw_question = ? and pw_answer = ?";
		private static String SQL_CHECK_ALL_COUNT_MB =
				"SELECT count(id) FROM members";
		private static String SQL_ARTICLE_SELECT_ALL_PG =
				"select * from members order by joined_at desc limit ?, ?";
		public static final String SQL_MEMBER_CHEKC_SEARCH_ALL_PG
		= "SELECT count(*) as searchCnt FROM members where nickname like concat('%',?,'%') or email like concat('%', ? ,'%')";
		public static final String SQL_MEMBER_SEARCH_ALL_PG
		= "SELECT * from members where nickname like concat('%',?,'%') or email like concat('%', ? ,'%') order by id desc limit ?, ?";
		public static String SQL_SelectNewMember ="SELECT COUNT(*) as newmembers from members " + 
	            "WHERE FORMAT(joined_at, 'YYYY-MM-DD') = FORMAT(now(), 'YYYY-MM-DD')";
	   
	      public static String SQL_SelectTotalMember ="SELECT COUNT(*) as totalmembers from members"; 
	      
		@Autowired
		private JdbcTemplate jtem;
		
		private MemberRowMapper mbRowMapper; // 필드화 공유
		
		public MemberMySqlDAOImpl() {
			this.mbRowMapper = new MemberRowMapper();
		}
		
		
	@Override
	public boolean isDuplicatedEmail(String email) {
		int r = this.jtem.queryForObject(SQL_MEMBER_EMAIL_DUPCHECK,
				Integer.class, email); // 쿼리 결과 RS테이블의 값을 정수 매핑
		// r >= 1 이미 사용중...
		// r == 0 사용가능한 nick
		//System.out.println("dao: dup check r = " + r);
		return r >= 1;
	}

	@Override
	public boolean insertNewMember(MemberVO mb) {
		int r = this.jtem.update(SQL_INSERT_MEMBER, mb.getEmail(), mb.getNickname(), 
				mb.getPassword(), mb.getPwQuestion(), mb.getPwAnswer());
		return r == 1;
	}

	@Override
	public boolean insertNewMember(String email, String nickname, String password, int pwQuestion, String pwAnswer) {
		int r = this.jtem.update(SQL_INSERT_MEMBER, email, nickname, password, pwQuestion, pwAnswer);
		return r == 1;
	}

	@Override
	public boolean insertNewMemberWithCrypto(MemberVO mb) {
		System.out.println("insertNewMemberWithCrypto()...");
		int r = this.jtem.update(SQL_INSERT_MEMBER_CRYPTO, 
				mb.getEmail(), mb.getNickname(), 
				mb.getPassword(), mb.getEmail(), mb.getPwQuestion(), mb.getPwAnswer()
				); // email을 기준키로 해서 pw를 AES 암호화
		return r == 1;
	}

	@Override
	public boolean isDuplicatedNick(String nickname) {
		int r = this.jtem.queryForObject(SQL_MEMBER_NICK_DUPCHECK,
				Integer.class, nickname); // 쿼리 결과 RS테이블의 값을 정수 매핑
		// r >= 1 이미 사용중...
		// r == 0 사용가능한 nick
		//System.out.println("dao: dup check r = " + r);
		return r >= 1;
	}

	@Override
	public String selectPwByEmail(String email) {
		
		Map<String, Object> rMap 
		//= jtem.queryForMap(SQL_DECRYPT_PW2, login, login);
		 = jtem.queryForMap(SQL_DECRYPT_PW2, 
				 new Object[] {email,email},
				 new int[] {Types.VARCHAR, Types.VARCHAR});// 타입 명시..
		// 쿼리 결과 컬럼명이 String 키, 컬럼 값이 Object로 가져옴.
		int id = (int) rMap.get("id");
		String nickname = (String) rMap.get("nickname");
		String password = (String) rMap.get("password"); // blob 에러 - cast char
		String emailDB = (String) rMap.get("email");
		System.out.println(id);
		System.out.println(password); //
		System.out.println(emailDB);
		return password;
	}

	

	@Override
	public String decryptPassword(String email) {
		Map<String, Object> rMap 
		//= jtem.queryForMap(SQL_DECRYPT_PW2, login, login);
		 = jtem.queryForMap(SQL_DECRYPT_PW2, 
				 new Object[] {email,email},
				 new int[] {Types.VARCHAR, Types.VARCHAR});// 타입 명시..
		// 쿼리 결과 컬럼명이 String 키, 컬럼 값이 Object로 가져옴.
		int id = (int) rMap.get("id");
		String nickname = (String) rMap.get("nickname");
		String password = (String) rMap.get("password"); // blob 에러 - cast char
		String emailDB = (String) rMap.get("email");
		System.out.println(id);
		System.out.println(nickname);
		System.out.println(password); //
		System.out.println(emailDB);
		return password;
	
	}

	@Override
	public MemberVO selectOneMember(int id) { // 
		try {
			MemberVO mb = jtem.queryForObject(SQL_SELECT_ONE_MEMBER,
				//this.mbRowMapper, id); //커스터 field RW
				BeanPropertyRowMapper
				.newInstance(MemberVO.class), id);
			return mb;
		} catch(EmptyResultDataAccessException e) {
			//e.printStackTrace();
			System.out.println("dao: 회원 조회 실패  " + id );
			return null;
		}
	}
	
	// nick 
	@Override
	public MemberVO selectOneMemberbyNick(String nickname) {
		try {
			return this.jtem.queryForObject(SQL_SELECT_ONE_MEMBER_NICK,
					new RowMapper<MemberVO>() {
						@Override
						public MemberVO mapRow(ResultSet rs, int rowNum) throws SQLException {
							// 익명객체 정의 방식으로 rowmapper
							// 공용이나 beanproperty 안쓰고.. 자신만의 맵핑을 사용...						
							return new MemberVO(rs.getInt("id"), 
									rs.getString("email"), 
									rs.getString("nickname"), 
									rs.getString("password"), 
									rs.getTimestamp("joined_at"),
									rs.getTimestamp("updated_at"),
									rs.getInt("pw_question"),
									rs.getString("pw_answer"));
						}
						
					}  
			, nickname);
			} catch(EmptyResultDataAccessException dae) {
				System.out.println("dao: 회원 조회 실패 empty nickname - " + nickname);
				return null;
			} catch(DataAccessException dae) {
				// DataAccessException spring dao에서 최상위 예외 객체
				System.out.println("dao: 회원 조회 실패 dae nickname - " + nickname);
				return null;
			}
			
	}
	
	@Override
	public MemberVO selectOneMemberbyEmail(String nickname) {
		try {
			return this.jtem.queryForObject(SQL_SELECT_ONE_MEMBER_EMAIL,
					new RowMapper<MemberVO>() {
						@Override
						public MemberVO mapRow(ResultSet rs, int rowNum) throws SQLException {
							// 익명객체 정의 방식으로 rowmapper
							// 공용이나 beanproperty 안쓰고.. 자신만의 맵핑을 사용...						
							return new MemberVO(rs.getInt("id"), 
									rs.getString("email"), 
									rs.getString("nickname"), 
									rs.getString("password"), 
									rs.getTimestamp("joined_at"),
									rs.getTimestamp("updated_at"),
									rs.getInt("pw_question"),
									rs.getString("pw_answer"));
						}
						
					}  
			, nickname);
			} catch(EmptyResultDataAccessException dae) {
				System.out.println("dao: 회원 조회 실패 empty nickname - " + nickname);
				return null;
			} catch(DataAccessException dae) {
				// DataAccessException spring dao에서 최상위 예외 객체
				System.out.println("dao: 회원 조회 실패 dae nickname - " + nickname);
				return null;
			}
			
	}

	@Override
	public boolean updateOneMember(MemberVO mb) {
		try {
			int r = jtem.update(SQL_UPDATE_MEMBER2, mb.getNickname(), 
					mb.getPwQuestion(), mb.getPwAnswer(), mb.getId());
	  								   // aes_encrypt()	
			System.out.println(r);
			System.out.println("nn: " + mb.getNickname());
			System.out.println("pwQ: " + mb.getPwQuestion());
			System.out.println("pwA: " +  mb.getPwAnswer());
			System.out.println("id: " + mb.getId());
					return r == 1;
			} catch(DataAccessException dae) {
				// DataAccessException spring dao에서 최상위 예외 객체
				System.out.println("dao: 회원 갱신 실패 dae - " + mb.toString());
				return false;
			}
	}

	@Override
	public boolean updatePw(String email, String pw) {
		int r = this.jtem.update(SQL_UPDATE_PW, pw, email, email);
		return r == 1;
	}

	@Override
	public boolean deleteOneMember(int id) {
		int r = this.jtem.update(SQL_MEMBER_DELETE, id);
		return r == 1;
	}

	@Override
	public boolean deleteOneMember(String login) {
		int r = this.jtem.update(SQL_MEMBER_DELETE, login);
		return r == 1;
	}


	@Override
	public List<MemberVO> selectAllMember() {
		return jtem.query(SQL_SELECT_ALL_MEMBERS,
				BeanPropertyRowMapper.
					newInstance(MemberVO.class) );
	}


	@Override
	public int checkAllMemberCount() {
		return jtem.queryForObject(SQL_CHECK_ALL_COUNT_MB, Integer.class);
	}


	@Override
	public List<MemberVO> selectAllArticles(int offset, int limit) {
		return jtem.query(SQL_ARTICLE_SELECT_ALL_PG, 
				BeanPropertyRowMapper.newInstance(MemberVO.class),
				offset, limit);
	}


	@Override
	public List<MemberVO> searchMember(
			String keyword, int offset, 
			int limit, String orderBy) {
		return jtem.query(SQL_MEMBER_SEARCH_ALL_PG, 
				BeanPropertyRowMapper
				.newInstance(MemberVO.class),
				keyword, keyword, 
				offset, limit);
	}


	@Override
	public int checkAllArticleCount(String target, String keyword) {
		return jtem.queryForObject(SQL_MEMBER_CHEKC_SEARCH_ALL_PG,
				Integer.class, keyword, keyword);
	}

	  @Override
	   public int countNewMember() {
	   
	      return jtem.queryForObject(SQL_SelectNewMember, Integer.class);
	   }


	   @Override
	   public int countTotalMember() {

	      return jtem.queryForObject(SQL_SelectTotalMember, Integer.class);
	   }
	

	
	

	

}
