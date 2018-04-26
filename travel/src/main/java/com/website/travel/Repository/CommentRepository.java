package com.website.travel.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.website.travel.Model.Comment;

public interface CommentRepository extends CrudRepository<Comment, Integer> {

	
	/*
	@Query(value = "select comment,username from users,comments where PostID=?1 AND users.id=comments.UserID", nativeQuery = true)
	public List<Comment> findByPostId(int PostID);
	
	*/

	
	public List<Comment> findByPostId(int PostID);
}
