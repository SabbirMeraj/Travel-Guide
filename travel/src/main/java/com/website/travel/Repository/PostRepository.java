package com.website.travel.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.website.travel.Model.Post;

public interface PostRepository extends CrudRepository<Post, Integer> {
	
	
	@Query(value = "select * from post where UserID=?1 ORDER BY id DESC ", nativeQuery = true)
	public List<Post> findByUserID(int UserID);
	
	
	@Query(value = "SELECT post.userid,post.cost,post.place,post.title,post.body,post.id FROM users,subscriber,post where subscriber.UserID=?1 AND subscriber.Status=1 AND subscriber.SubscriberID=post.UserID AND users.ID=subscriber.SubscriberID ORDER BY post.id DESC ", nativeQuery = true)
	public List<Post> findAll(int UserID);
	
	
	/*
	
	@Query(value = "SELECT post.userid,post.cost,post.title,post.body,post.id,likes.status FROM users,subscriber,post,likes where subscriber.UserID=?1 AND subscriber.Status=1 AND subscriber.SubscriberID=post.UserID AND users.ID=subscriber.SubscriberID AND likes.UserID=?1 AND likes.PostID=post.ID ORDER BY post.id DESC ", nativeQuery = true)
	public List<Post> findAll(int UserID);
	
	*/
	
	public List<Post> findByPlace(String Place);
	
	
	@Query(value = "select * from post where cost>?1 and cost <?2  ", nativeQuery = true)
	public List<Post> findByCost(int start, int end);
	
	
	@Query(value = "select * from post,users where post.UserID=users.ID and users.username=?1", nativeQuery = true)
	public List<Post> findByUserID(String UserName);
	
	public Post findById(int PostID);

}
