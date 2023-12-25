package com.project.fd.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.fd.entities.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
	 List<Post> findByUserId(Long userId);  //userId ye ait tüm postları döner
}
