package com.douzone.jblog.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.PostVo;

@Repository
public class PostRepository {

	@Autowired
	private SqlSession sqlSession;
	

//	public PostVo findPost(String id) {
//		return sqlSession.selectOne("post.findPost",id);
//	}

	public void insert(String no) {
		sqlSession.insert("post.insert", no);			
	}

//	public PostVo findPost(String id, long categoryNo, long postNo) {
//		Map<String, Object> map = new HashMap<>();
//		map.put("id", id);
//		map.put("categoryNo", categoryNo);
//		map.put("postNo", postNo);
//		return sqlSession.selectOne("post.findPost", map);
//	}

	public PostVo findByPostNoNull(String id, long categoryNo) {
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("categoryNo", categoryNo);
		return sqlSession.selectOne("post.findByPostNoNull", map);
		
	}

	public PostVo findByPostNoAndCategoryNoNull(String id, long categoryNo, long postNo) {
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("categoryNo", categoryNo);
		map.put("postNo", postNo);
		return sqlSession.selectOne("post.findByPostNoAndCategoryNoNull", map);
		
	}

	public PostVo findByNotNull(String id) {
		return sqlSession.selectOne("post.findByNotNull", id);		
	}

	public List<PostVo> findByIdDefaultList(String id) {
		return sqlSession.selectList("post.findByIdDefaultList", id);
	}
	public List<PostVo> findByIdCategoryList(String id, long categoryNo) {
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("categoryNo", categoryNo);
		return sqlSession.selectList("post.findByIdCategoryList", map);
	}
	public List<PostVo> findByAllList(String id, long categoryNo, long postNo) {
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("categoryNo", categoryNo);
		map.put("postNo", postNo);
		return sqlSession.selectList("post.findByAllList", map);
	}

	public void writePost(PostVo vo) {
		sqlSession.insert("post.writePost", vo);
	}

	public PostVo showPost(String id) {
		return sqlSession.selectOne("post.showPost", id);
	}
	
	
	

}
