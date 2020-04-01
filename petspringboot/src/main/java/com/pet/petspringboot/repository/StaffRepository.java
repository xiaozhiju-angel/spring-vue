package com.pet.petspringboot.repository;

import com.pet.petspringboot.model.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;


public interface StaffRepository extends JpaRepository<Staff,Integer> , JpaSpecificationExecutor<Staff> {
    /**
     * 根据userId删除用户及配置
     */
   /* @Modifying
    @Transactional*/
    @Query(value = "delete  from  staff    where   staffId = ?1", nativeQuery = true)
    List<Staff> deleteByStaffId(String staffId);
   /* @Query(value = "select * from `tb_announcement_mgmt`"
            + " where `title`=:title and `is_del`=0 "
            + "and (case when :areaCode=0 then 1=1 else `area_code`=:areaCode end) limit 10", nativeQuery = true)
    List<Staff> deleteByStaffId(String staffId);*/


}

