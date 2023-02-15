package com.douzone.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.UserVo;

@Repository
public class CategoryRepository {
	
	@Autowired
	private SqlSession sqlSession;

	public List<CategoryVo> findCategoryList(String id) {
		return sqlSession.selectList("category.findCategoryList", id);
	}

//	public void insert(UserVo vo) {
//		sqlSession.insert("category.insert", vo);		
//	}
	public void insert(String id) {
		sqlSession.insert("category.insert", id);		
	}

	public void addCategory(CategoryVo vo) {
		sqlSession.insert("category.addCategory", vo);
	}

	public void deleteCategory(Long no) {
		sqlSession.delete("category.deleteCategory", no);
		
	}

//	public void addCategory(CategoryVo vo) {
//		sqlSession.insert("category.addCategory", vo);
//		
//	}

	

}
