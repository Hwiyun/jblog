package com.douzone.jblog.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.jblog.security.Auth;
import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.service.FileuploadService;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.PostVo;

@Controller
@RequestMapping("/{id:(?!assets).*}")
public class BlogController {
	@Autowired
	private ServletContext servletContext;
	
	@Autowired
	private ApplicationContext applicationContext;
	
	@Autowired
	private FileuploadService fileuploadService;
	
	@Autowired
	private BlogService blogService;
	
//	@RequestMapping({"","/{categoryNo}", "/{categoryNo}/{postNo}"})
//	public String main(
//			Model model, 
//			@PathVariable("id") String id,
//			@PathVariable("categoryNo") Optional<Long> categoryNo,
//			@PathVariable("postNo") Optional<Long> postNo) {
//		
//		Map<String, Object> map = blogService.getBlogPage(id);
//		model.addAllAttributes(map);
//	
//		return "blog/main";
//	}
	
	@RequestMapping({"", "/{pathNo1}", "/{pathNo1}/{pathNo2}" })
	public String index(Model model,
		@PathVariable("id") String id,
		@PathVariable("pathNo1") Optional<Long> pathNo1,
		@PathVariable("pathNo2") Optional<Long> pathNo2) {
		
		Long categoryNo = 0L;
		Long postNo = 0L;
		
//		if(pathNo2.isPresent()) {
//			categoryNo = pathNo1.get();
//			postNo = pathNo2.get();
//		} else if(pathNo1.isPresent()) {
//			categoryNo = pathNo1.get();
//		}

		if(pathNo2.isPresent()) {
			categoryNo = pathNo1.get();
			postNo = pathNo2.get();
		} else if(pathNo1.isPresent()) {
			categoryNo = pathNo1.get();
		}
		

		
		Map<String, Object> map = blogService.getBlogPage(id, categoryNo, postNo);
		model.addAllAttributes(map);
		
		return "blog/main";
	
	}

	@Auth
	@RequestMapping(value = "/admin-basic", method = RequestMethod.GET)
	public String adminEntry(
			Model model, 
			@PathVariable("id") String id) {
		BlogVo vo = blogService.getBlogPage(id);
		model.addAttribute("blogVo", vo);
		System.out.println("blogVo : "+vo);
		return "blog/admin-basic";
	}
	
	@Auth
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updateBlog(
//			Model model,
			BlogVo vo,
			@PathVariable("id") String id,
			MultipartFile file) {
		
		String url = fileuploadService.restore(file);
		vo.setProfile(url);
		
//		BlogVo blog = applicationContext.getBean(BlogVo.class);
		
		blogService.updateBlog(vo);
//		servletContext.setAttribute("blogvo", vo);
//		
//		BeanUtils.copyProperties(vo, blog);
//		model.addAttribute("vo", vo);
		return "redirect:/"+id+"/admin-basic";
	}
	
	@Auth
	@RequestMapping(value = "/admin-category", method = RequestMethod.GET)
	public String categoryEntry(Model model, @PathVariable("id") String id) {
		BlogVo blogVo = blogService.getBlogPage(id);
		List<CategoryVo> list = blogService.getCategory(id);
		
		model.addAttribute("blogVo", blogVo);
		model.addAttribute("list", list);
		return "blog/admin-category";
	}
	
	@Auth
	@RequestMapping(value = "/updateCategory", method = RequestMethod.POST)
	public String updateCategory(
			Model model,
			@PathVariable("id") String id,
			CategoryVo vo) {
		
		blogService.addCategory(vo);
		
		
		return "redirect:/"+id+"/admin-category";
	}
	
	@Auth
	@RequestMapping(value = "/{no}/delete")
	public String deleteCategory(
			Model model,
			@PathVariable("no") Long no, @PathVariable("id") String id, int count) {
		if(count < 1) {
			blogService.deleteCategory(no);
		}
		
		return "redirect:/"+id+"/admin-category";
	}
	
	@Auth
	@RequestMapping(value = "/admin-write", method = RequestMethod.GET)
	public String postEntry(Model model, @PathVariable("id") String id) {
		BlogVo blogVo = blogService.getBlogPage(id);
		List<CategoryVo> list = blogService.getCategory(id);
		
		model.addAttribute("list", list);
		model.addAttribute("blogVo", blogVo);
		return "blog/admin-write";
	}
	
	@Auth
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String writePost(
			Model model,
			@PathVariable("id") String id,
			PostVo vo) {
		
		blogService.writePost(vo);
		
		
		return "redirect:/"+id+"/admin-write";
	}
}
