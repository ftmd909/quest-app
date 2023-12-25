package com.project.fd.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.fd.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    // Özel sorgular ekleyebilirim, ancak temel CRUD işlemleri bu arayüzde zaten bulunmaktadır.
}
