package com.website.travel.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.website.travel.Model.Post;

public interface PostRepository extends CrudRepository<Post, Integer> {
	
	
	@Query(value = "select * from post where UserID=?1 ORDER BY ID DESC ", nativeQuery = true)
	public List<Post> findByUserID(int UserID);
	
	
	@Query(value = "SELECT post.userid,post.cost,post.title,post.body,post.ID FROM users,subscriber,post where subscriber.UserID=?1 AND subscriber.Status=1 AND subscriber.SubscriberID=post.UserID AND users.ID=subscriber.SubscriberID ORDER BY post.ID DESC ", nativeQuery = true)
	public List<Post> findAll(int UserID);

}
