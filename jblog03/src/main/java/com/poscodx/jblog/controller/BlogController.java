package com.poscodx.jblog.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.poscodx.jblog.service.BlogService;
import com.poscodx.jblog.service.FileUploadService;
import com.poscodx.jblog.vo.BlogVo;
import com.poscodx.jblog.vo.CategoryVo;
import com.poscodx.jblog.vo.PostVo;

@Controller
@RequestMapping("/{id:(?!assets).*}")
public class BlogController {
	
	@Autowired
	private FileUploadService fileUploadService;
	
	@Autowired
	private BlogService blogService;
	
	@RequestMapping({"", "/{categoryNo}", "/{categoryNo}/{postNo}" })
	public String index(
			@PathVariable("id") String blogId,
	         @PathVariable(value = "categoryNo", required = false) Long categoryNo,
	         @PathVariable(value = "postNo", required = false) Long postNo,
		Model model) {
		Map<String,Object> map = blogService.getBlog(blogId,categoryNo,postNo);
		model.addAttribute("map",map);
		
		return "blog/main";
	}
	
	@RequestMapping("/admin/basic")
	public String adminBasic(@PathVariable("id") String blogId,Model model) {
		Map<String, Object> map = blogService.getBlog(blogId, null, null);

		model.addAttribute("map", map);
		return "blog/admin-basic";
	}
	@RequestMapping(value = "/admin/basic/update", method=RequestMethod.POST)
	public String update(@PathVariable("id") String blogId,
						@RequestParam("f") MultipartFile file, BlogVo vo) {
		String url = fileUploadService.restore(file);
		vo.setImage(url);
		vo.setBlog_id(blogId);
		System.out.println(vo);
		
		blogService.updateBlog(vo);
		
		
		
		return "redirect:/" + blogId +"/admin/basic"; 
	}
	
	@RequestMapping("/admin/category")
	public String adminCategory(@PathVariable("id") String blogId, Model model) {
		Map<String, Object> map = blogService.getBlog(blogId, null, null);

		model.addAttribute("map", map);

		List<CategoryVo> list = blogService.getDetailCategory(blogId);

		model.addAttribute("list", list);
		return "blog/admin-category";
	}

	
	@RequestMapping("/admin/write")
	public String adminWrite(@PathVariable("id") String blogId, Model model) {
		Map<String, Object> map = blogService.getBlog(blogId, null, null);

		model.addAttribute("map", map);
		List<CategoryVo> list = blogService.getCategory(blogId);
		
		model.addAttribute("list", list);
		return "blog/admin-write";
	}
	@RequestMapping("/admin/delete/{no}")
	public String delete(@PathVariable("id") String blogId,@PathVariable("no") Long no) {
		blogService.delete(blogId,no);
		return "redirect:/" + blogId + "/admin/category";
	}
	
	@RequestMapping("/writecategory")
	public String writeCategory(@PathVariable("id") String blogId, CategoryVo categoryVo) {
		categoryVo.setBlog_id(blogId);
		blogService.writeCategory(categoryVo);
		return "redirect:/" + blogId + "/admin/category";
	}
	
	@RequestMapping("/writepost")
	public String writePost(@PathVariable("id") String blogId, PostVo postVo) {
		blogService.writePost(postVo);
		return "redirect:/" + blogId + "/admin/category";
	}
	
	
	
	
	
}