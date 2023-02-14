package com.douzone.jblog.repository;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.UserVo;

@Repository
public class UserRepository {

	@Autowired
	private SqlSession sqlSession;
	
	public void insert(UserVo vo) {
		sqlSession.insert("user.insert", vo);
	}

	public UserVo findByIdAndPassword(String id, String password) {
		Map<String, Object> map = new HashMap<>();
		map.put("i", id);
		map.put("p", password);
		
		return sqlSession.selectOne("user.findByIdAndPassword", map);
	}

	public String compareEqualId(String id) {
		return sqlSession.selectOne("user.compareEqualId", id);
	}

}
