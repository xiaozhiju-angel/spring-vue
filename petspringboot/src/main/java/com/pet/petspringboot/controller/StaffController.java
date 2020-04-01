package com.pet.petspringboot.controller;

import com.pet.petspringboot.model.entity.Admin;
import com.pet.petspringboot.model.entity.Staff;
import com.pet.petspringboot.model.entity.User;
import com.pet.petspringboot.repository.AdminRepository;
import com.pet.petspringboot.repository.StaffRepository;
import com.pet.petspringboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/staff")
@CrossOrigin
public class StaffController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private AdminRepository adminRepository;

    MD5 md=new MD5();
    /*注册员工信息*/
    @RequestMapping(value = "/savestaff", method = RequestMethod.POST)
    public Map<String, Object> savestaff(@RequestParam("staffname")String staffname,
                                         @RequestParam("staffsex")String staffsex,
                                         @RequestParam("staffidcard")String staffidcard,
                                         @RequestParam("stafftel")String stafftel,
                                         @RequestParam("staffemail")String staffemail,
                                         @RequestParam("staffloginame")String staffloginame,
                                         @RequestParam("staffpass")String staffpass,
                                         @RequestParam("staffprop")String staffprop,
                                         @RequestParam("staffimg")String staffimg,
                                         @RequestParam("staffregtime") Date staffregtime,
                                         Staff staff) {
        Map<String, Object> map = new HashMap<>();
        List<Admin> listadmin = adminRepository.findAll();
        List<Staff> liststaff = staffRepository.findAll();
        List<User> listuser = userRepository.findAll();
        int flag=0;
        for (int i = 0; i < liststaff.size(); i++) {
            if (liststaff.get(i).getStaffLoginame().equals(staffloginame)) {
                map.put("loginamerepeat",true);
                map.put("repeat", "用户名重复");
                flag=1;
            }
            else if (liststaff.get(i).getStaffIdcard().equals(staffidcard)) {
                map.put("staffidcard",true);
                map.put("idcard", "该身份证已注册");
                flag=1;
            }
        }
        for (int i = 0; i < listuser.size(); i++) {
            if (listuser.get(i).getUserIdcard().equals(staffidcard)) {
                map.put("useridcard",true);
                map.put("idcard", "该身份证已注册");
                flag=1;
            }
        }
        for (int i = 0; i < listadmin.size(); i++) {
            if (listadmin.get(i).getAdminIdcard().equals(staffidcard)) {
                map.put("adminidcard",true);
                map.put("idcard", "该身份证已注册");
                flag=1;
            }
        }
        if(flag==0){
            try{
                staff.setStaffName(staffname);
                staff.setStaffSex(staffsex);
                staff.setStaffIdcard(staffidcard);
                staff.setStaffTel(stafftel);
                staff.setStaffEmail(staffemail);
                staff.setStaffLoginame(staffloginame);
                staff.setStaffPass(staffpass);
                staff.setStaffProp(staffprop);
                staff.setStaffImg(staffimg);
                staff.setStaffRegtime(staffregtime);
                staffRepository.save(staff);
                map.put("save", "添加成功");
                map.put("success",true);

            }catch (Exception e){
                e.printStackTrace();

            }
        }
        return map;
    }

}
