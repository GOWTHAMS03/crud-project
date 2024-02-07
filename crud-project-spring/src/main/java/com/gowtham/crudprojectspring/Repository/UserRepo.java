package com.gowtham.crudprojectspring.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gowtham.crudprojectspring.Model.User;

public interface UserRepo extends JpaRepository<User, Long> {

}
