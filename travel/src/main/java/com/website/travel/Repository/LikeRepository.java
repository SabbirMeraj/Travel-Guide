package com.website.travel.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.website.travel.Model.Like;


@Repository
public interface LikeRepository extends CrudRepository<Like, Integer> {

	
	public Like findByUserIDAndPostId(int UserID,int PostID);
	
	
	@Query(value = "select SUM(Status) from likes where PostID=?1", nativeQuery = true)
	public Integer findByPostId(int PostID);
	
	@Query(value = "select likes.id, likes.Status from subscriber,post,likes where subscriber.UserID=?1 AND subscriber.SubscriberID=post.UserID AND post.ID=likes.PostId AND likes.UserID=?1 ORDER BY post.ID DESC", nativeQuery = true)
	public List<Like> findByUserID(int UserID);
	
}
