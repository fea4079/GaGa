package spring.service.user.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import spring.domain.User;
import spring.service.user.KakaoDao;




//==> 회원관리 DAO CRUD 구현
@Repository("KakaoDao")
public class KakaoDaoImpl implements KakaoDao{
	
	///Field
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	///Constructor
	public KakaoDaoImpl() {
		System.out.println(this.getClass());
	}

	///Method
	public void addUser(User user) throws Exception {
		sqlSession.insert("KakaoUserMapper.addUser", user);
	}

	@Override
	public User getUser(String userId) throws Exception {
		return sqlSession.selectOne("UserMapper.getUser", userId);
	}
	
	public boolean checkDuplication(String userId) throws Exception {
		boolean result = true;
		User user = sqlSession.selectOne("KakaoUserMapper.getUser", userId);
		if(user==null) {
			result = false;
		}
		
		return result;
	}
	
	
}