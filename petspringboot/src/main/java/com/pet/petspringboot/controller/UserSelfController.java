package com.pet.petspringboot.controller;

import com.pet.petspringboot.model.entity.Admin;
import com.pet.petspringboot.model.entity.Pet;
import com.pet.petspringboot.model.entity.Staff;
import com.pet.petspringboot.model.entity.User;
import com.pet.petspringboot.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/self")
@CrossOrigin
public class UserSelfController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private FosterRepository fosterRepository;

    @Autowired
    private AdoptPetRepository adoptPetRepository;

    @Autowired
    private PublishAdoptRepository publishAdoptRepository;

    @Autowired
    private PickupRepository pickupRepository;

    @Autowired
    private LoseRepository loseRepository;

    @RequestMapping(value = "/getAdminSelfInfo", method = RequestMethod.GET)
    public Map<String, Object> getAdminSelfInfo(@RequestParam("info")int info) {
        Map<String, Object> map = new HashMap<>();
        List<Admin> listadmin=adminRepository.findAll();
        List<Admin> information=new ArrayList<>();
        int flag=0;
        for(int i=0;i<listadmin.size();i++){
            /*int m=listuser.get(i).getUserId();
            String str=String.valueOf(m);*/
            if(listadmin.get(i).getAdminId()==info)
                information.add(listadmin.get(i));
            flag=1;
        }

        if(flag==1){
            map.put("getinfo",true);
            map.put("info",information);
        }
        if(flag==0){
            map.put("msg","无匹配数据");
        }
        return map;
    }

    @RequestMapping(value = "/getStaffSelfInfo", method = RequestMethod.GET)
    public Map<String, Object> getStaffSelfInfo(@RequestParam("info")int info) {
        Map<String, Object> map = new HashMap<>();
        List<Staff> liststaff=staffRepository.findAll();
        List<Staff> information=new ArrayList<>();
        int flag=0;
        for(int i=0;i<liststaff.size();i++){
            /*int m=listuser.get(i).getUserId();
            String str=String.valueOf(m);*/
            if(liststaff.get(i).getStaffId()==info)
                information.add(liststaff.get(i));
            flag=1;
        }

        if(flag==1){
            map.put("getinfo",true);
            map.put("info",information);
        }
        if(flag==0){
            map.put("msg","无匹配数据");
        }
        return map;
    }



    /*根据用户输入的关键字查找满足条件的值*/
    @RequestMapping(value = "/getUserSelfInfo", method = RequestMethod.GET)
    public Map<String, Object> getUserSelfInfo(@RequestParam("info")int info) {
        Map<String, Object> map = new HashMap<>();
        List<User> listuser=userRepository.findAll();
        List<User> information=new ArrayList<>();
        int flag=0;
        for(int i=0;i<listuser.size();i++){
            /*int m=listuser.get(i).getUserId();
            String str=String.valueOf(m);*/
            if(listuser.get(i).getUserId()==info)
                information.add(listuser.get(i));
                flag=1;
        }

        if(flag==1){
            map.put("getinfo",true);
            map.put("info",information);
        }
        if(flag==0){
            map.put("msg","无匹配数据");
        }
        return map;
    }

}
