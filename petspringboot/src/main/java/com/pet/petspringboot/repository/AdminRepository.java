package com.pet.petspringboot.repository;

import com.pet.petspringboot.model.entity.Admin;
import com.pet.petspringboot.model.entity.Pet;
import com.pet.petspringboot.model.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AdminRepository extends JpaRepository<Admin,Integer> , JpaSpecificationExecutor<Admin> {
}
