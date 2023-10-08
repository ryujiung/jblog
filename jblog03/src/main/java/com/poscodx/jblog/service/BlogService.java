package com.poscodx.jblog.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poscodx.jblog.repository.BlogRepository;
import com.poscodx.jblog.vo.BlogVo;
import com.poscodx.jblog.vo.CategoryVo;
import com.poscodx.jblog.vo.PostVo;
import com.poscodx.jblog.vo.UserVo;

@Service
public class BlogService {
	
	@Autowired
	private BlogRepository blogRepository;

	public void join(UserVo vo) {
		System.out.println(vo);
		
		blogRepository.insert(vo);
		
		System.out.println(vo);
		
	}
	public void updateBlog(BlogVo vo) {
		blogRepository.update(vo);
	}
	public Map<String, Object> getBlog(String blogId,Long categoryNo,Long postNo) {
		Map<String,Object> map = new HashMap<String,Object>();
		
		BlogVo blogVo = blogRepository.findBlog(blogId);
		List<CategoryVo> categorylist = blogRepository.findCategory(blogId);
		List<PostVo> postlist =blogRepository.findPost(blogId, categoryNo);
		
		map.put("blogVo", blogVo);
		map.put("categorylist", categorylist);
		map.put("postlist",postlist);
		map.put("postNo",postNo);
		map.put("blogId", blogId);
		
		return map;
		
		
	}
	public List<CategoryVo> getCategory(String blogId) {
		
		List<CategoryVo> list = blogRepository.findCategory(blogId);
		return list;
	}
	
	public List<CategoryVo> getDetailCategory(String blogId) {
		
		List<CategoryVo> list = blogRepository.findDetailCategory(blogId);
		return list;
	}
	public void delete(String blogId, Long no) {
		blogRepository.delete(blogId,no);
		
	}
	public boolean writeCategory(CategoryVo categoryVo) {
		return blogRepository.writeCategory(categoryVo);
		
	}
	public boolean writePost(PostVo postVo) {
		return blogRepository.writePost(postVo);
	}

}
