package com.pet.petspringboot.repository;

import com.pet.petspringboot.model.entity.Pet;
import com.pet.petspringboot.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer>, JpaSpecificationExecutor<User> {
    User save(User user);
    List<User> findAll();

    User findByUserId(int userId);
   /* @Modifying
    @Query("update User as c set c.userId =?1")
    int updateByUserId(int userId);*/


}
