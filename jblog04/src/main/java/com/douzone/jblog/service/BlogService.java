package com.douzone.jblog.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.BlogRepository;
import com.douzone.jblog.repository.CategoryRepository;
import com.douzone.jblog.repository.PostRepository;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.PostVo;

@Service
public class BlogService {

	@Autowired 
	private BlogRepository blogRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	
//	public List<BlogVo> getBlogList() {
//		return blogRepository.findBlog();
//	}
	public Map<String, Object> getBlogPage(String id, long categoryNo, long postNo) {
		
		BlogVo blogvo = blogRepository.findBlog(id);
		
		List<CategoryVo> categorylist = categoryRepository.findCategoryList(id);
		
		List<PostVo> postlist = null;
		
		PostVo postvo =null;
		// postRepository.findPost(id, categoryNo, postNo);
		if(postNo == 0l && categoryNo != 0l ) {
			System.out.println("1");
			postvo=postRepository.findByPostNoNull(id, categoryNo);
			postlist=postRepository.findByIdCategoryList(id, categoryNo);
		} 
		else if(postNo == 0l && categoryNo == 0l) {
			System.out.println("2");

			postvo=postRepository.showPost(id);
			postlist=postRepository.findByIdDefaultList(id);
			
			//postvo = postlist.get(0);
		} 
		else if(postNo != 0l && categoryNo != 0l) {
			System.out.println("3");

			postvo=postRepository.findByPostNoAndCategoryNoNull(id, categoryNo, postNo);
			postlist=postRepository.findByAllList(id,categoryNo,postNo);
		}
		System.out.println(postvo+ ":" +postlist);
		
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("blogvo", blogvo);
		
		map.put("categorylist", categorylist);
		map.put("postlist", postlist);


		map.put("postvo", postvo);
		
		
			
		return map;
	}


	public void insertDefault(String id ) {
		blogRepository.insertDefault(id);
	}
	
	public void updateBlog(BlogVo vo) {
		blogRepository.updateBlog(vo);
		
	}

	public BlogVo getBlogPage(String id) {
		return blogRepository.findBlog(id);
	}

	public List<CategoryVo> getCategory(String id) {
		return categoryRepository.findCategoryList(id);
	}

	public void addCategory(CategoryVo vo) {
		categoryRepository.addCategory(vo);
		
	}


	public void deleteCategory(Long no) {
		categoryRepository.deleteCategory(no);
		
	}


	public void writePost(PostVo vo) {
		postRepository.writePost(vo);
	}


	

}
