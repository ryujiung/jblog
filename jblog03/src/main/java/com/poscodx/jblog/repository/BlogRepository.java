package com.poscodx.jblog.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscodx.jblog.vo.BlogVo;
import com.poscodx.jblog.vo.CategoryVo;
import com.poscodx.jblog.vo.PostVo;
import com.poscodx.jblog.vo.UserVo;

@Repository
public class BlogRepository {
	@Autowired
	private SqlSession sqlSession;

	public Boolean insert(UserVo vo) {
		int count = sqlSession.insert("blog.insert", vo);
		return count == 1;
	}

	public void update(BlogVo vo) {
		sqlSession.update("blog.update", vo);
		
	}
	
	public BlogVo findBlog(String blogId) {
		
		return sqlSession.selectOne("blog.findBlog",blogId);
	}
	
	public List<CategoryVo> findCategory(String blogId){
		return sqlSession.selectList("blog.findCategory",blogId);
	}
	
	public List<CategoryVo> findDetailCategory(String blogId) {
		return sqlSession.selectList("blog.findDetailCategory", blogId);
	}
	
	public List<PostVo> findPost(String blogId,Long categoryNo){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("blogId", blogId);
		map.put("categoryNo",categoryNo);
		return sqlSession.selectList("blog.findPost",categoryNo);
		
	}

	public void delete(String blogId, Long no) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("blogId", blogId);
		map.put("no",no);
		
		sqlSession.delete("blog.deletePost",map);
		sqlSession.delete("blog.deleteCategory",map);
		
	}

	public boolean writeCategory(CategoryVo categoryVo) {
		int count = sqlSession.insert("blog.writeCategory",categoryVo);
		return count ==1;
		
	}

	public boolean writePost(PostVo postVo) {
		int count = sqlSession.insert("blog.writePost",postVo);
		return count == 1;
	}

}
