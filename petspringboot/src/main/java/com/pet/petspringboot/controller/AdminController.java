package com.pet.petspringboot.controller;

import com.pet.petspringboot.config.DateConverterConfig;
import com.pet.petspringboot.model.entity.*;
import com.pet.petspringboot.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.util.DigestUtils;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/admin")
@CrossOrigin
public class AdminController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private  PetRepository petRepository;

    @Autowired
    private  FosterRepository fosterRepository;

    @Autowired
    private AdoptPetRepository adoptPetRepository;

    @Autowired
    private PublishAdoptRepository publishAdoptRepository;

    @Autowired
    private PickupRepository pickupRepository;

    @Autowired
    private LoseRepository loseRepository;

    @Autowired
    private  VaccinRepository vaccinRepository;

    @Autowired
    private AdoptInfoRepository adoptInfoRepository;

    @Autowired
    private  LoseInfoRepository loseInfoRepository;

    @Autowired
    private PickupInfoRepository pickupInfoRepository;

    @Autowired
    private FosterpetinfoRepository fosterpetinfoRepository;

    @Autowired
    private AbandonpetRepository abandonpetRepository;

    @Autowired
    private AcceptRepository acceptRepository;

    @Autowired
    private TipinfoRepository tipinfoRepository;




    MD5 md=new MD5();
    /*用户登录*/
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public Map<String, Object> login(@RequestParam("userloginame") String userloginame,
                                     @RequestParam("userpass") String userpass,
                                     @RequestParam("userprop") String userprop) {
        Map<String, Object> map = new HashMap<>();
        /*String md5Password = DigestUtils.md5DigestAsHex(userpass.getBytes());*/
        List<User> listuser=userRepository.findAll();
        List<Staff> liststaff=staffRepository.findAll();
        List<Admin> listadmin=adminRepository.findAll();
        if (userprop.equals("用户")) {
            for (int i = 0; i < listuser.size(); i++) {
                if (listuser.get(i).getUserLoginame().equals(userloginame)&& md.getMD5Str(listuser.get(i).getUserPass(),null).equals(userpass)) {
                    map.put("keyuser", true);
                    map.put("userid",listuser.get(i).getUserId());
                    map.put("username",listuser.get(i).getUserName());
                    map.put("userauthority",listuser.get(i).getUserProp());
                    map.put("userimg",listuser.get(i).getUserImg());
                }
            }
        }
        else if (userprop.equals("物业管理员")) {
            for (int i = 0; i < liststaff.size(); i++) {
                if (liststaff.get(i).getStaffLoginame().equals(userloginame)
                        &&md.getMD5Str(liststaff.get(i).getStaffPass(), null).equals(userpass)) {
                    map.put("keystaff", true);
                    map.put("staffid",liststaff.get(i).getStaffId());
                    map.put("staffname",liststaff.get(i).getStaffName());
                    map.put("staffauthority",liststaff.get(i).getStaffProp());
                    map.put("staffimg",liststaff.get(i).getStaffImg());
                }
            }
        }
       else if (userprop.equals("后台管理员")) {
            for (int i = 0; i < listadmin.size(); i++) {
                if (listadmin.get(i).getAdminLoginame().equals(userloginame)
                        && md.getMD5Str(listadmin.get(i).getAdminPass(), null).equals(userpass)) {
                    map.put("keyadmin", true);
                    map.put("adminid",listadmin.get(i).getAdminId());
                    map.put("adminame",listadmin.get(i).getAdminName());
                    map.put("adminauthority",listadmin.get(i).getAdminProp());
                    map.put("adminimg",listadmin.get(i).getAdminImg());
                }
            }
        }
        return map;
    }

    /*注册管理员信息*/
    @RequestMapping(value = "/saveadmin", method = RequestMethod.POST)
    public Map<String, Object> saveadmin(@RequestParam("adminame")String adminname,
                                         @RequestParam("adminsex")String adminsex,
                                         @RequestParam("adminidcard")String adminidcard,
                                         @RequestParam("admintel")String admintel,
                                         @RequestParam("adminemail")String adminemail,
                                         @RequestParam("adminloginame")String adminloginame,
                                         @RequestParam("adminpass")String adminpass,
                                         @RequestParam("adminprop")String adminprop,
                                         @RequestParam("adminimg")String adminimg,
                                         @RequestParam("adminregtime") Date adminregtime,
                                         Admin admin) {
        Map<String, Object> map = new HashMap<>();
        List<Admin> listadmin = adminRepository.findAll();
        List<Staff> liststaff = staffRepository.findAll();
        List<User> listuser = userRepository.findAll();
        int flag=0;
        for (int i = 0; i < listadmin.size(); i++) {
            if (listadmin.get(i).getAdminLoginame().equals(adminloginame)) {
                map.put("loginamerepeat",true);
                map.put("repeat", "用户名重复");
                flag=1;
            }
            else if(listadmin.get(i).getAdminIdcard().equals(adminidcard)){
                map.put("adminidcard",true);
                map.put("idcard", "该身份证已注册");
                flag=1;
            }
        }
        for (int i = 0; i < liststaff.size(); i++) {
            if (liststaff.get(i).getStaffIdcard().equals(adminidcard)) {
                map.put("staffidcard",true);
                map.put("idcard", "该身份证已注册");
                flag=1;
            }
        }
        for (int i = 0; i < listuser.size(); i++) {
            if (listuser.get(i).getUserIdcard().equals(adminidcard)) {
                map.put("useridcard",true);
                map.put("idcard", "该身份证已注册");
                flag=1;
            }
        }
        if (flag==0) {
            try {
                admin.setAdminName(adminname);
                admin.setAdminSex(adminsex);
                admin.setAdminIdcard(adminidcard);
                admin.setAdminTel(admintel);
                admin.setAdminEmail(adminemail);
                admin.setAdminLoginame(adminloginame);
                admin.setAdminPass(adminpass);
                admin.setAdminProp(adminprop);
                admin.setAdminImg(adminimg);
                admin.setAdminRegtime(adminregtime);
                adminRepository.save(admin);
                map.put("save", "添加成功");
                map.put("success",true);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return map;
    }


    /*查询全部业主信息，用于查询初始化*/
    @RequestMapping(value = "/getUserInfo", method = RequestMethod.GET)
     public Map<String, Object> getAllUserInfo() {
        Map<String, Object> map = new HashMap<>();
        List<User> listuser=userRepository.findAll();
        /*System.out.println(listuser.get(1).getUserRegtime());*/
        map.put("getinfo",true);
        map.put("userinfo", listuser);
        return map;
    }

    /*查询全部员工信息，用于查询初始化*/
    @RequestMapping(value = "/getStaffInfo", method = RequestMethod.GET)
    public Map<String, Object> getAllStaffInfo() {
        Map<String, Object> map = new HashMap<>();
        List<Staff> liststaff=staffRepository.findAll();
        map.put("getinfo",true);
        map.put("staffinfo", liststaff);
        return map;
    }
    /*查询管理员个人信息，用于查询初始化*/
    @RequestMapping(value = "/getAdminInfo", method = RequestMethod.GET)
    public Map<String, Object> getAllAdminInfo() {
        Map<String, Object> map = new HashMap<>();
        List<Admin> listadmin=adminRepository.findAll();
        map.put("getinfo",true);
        map.put("admininfo", listadmin);
        return map;
    }

    /*根据用户输入的关键字查找满足条件的值*/
    @RequestMapping(value = "/getStaffInfoBykeywords", method = RequestMethod.GET)
    public Map<String, Object> getBykeywords(@RequestParam("info")String info) {
        Map<String, Object> map = new HashMap<>();
        List<Staff> liststaff=staffRepository.findAll();
        List<Staff> information=new ArrayList<>();
        int Flag=0;
        for (int i = 0; i < liststaff.size(); i++) {
            int m=liststaff.get(i).getStaffId();
            String str=String.valueOf(m);
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            String str1=sdf.format(liststaff.get(i).getStaffRegtime());
            if(str.equals(info)){
                information.add(liststaff.get(i));
                Flag=1;
            }
            else if (str1.equals(info)) {
                information.add(liststaff.get(i));
                Flag=1;
            }
            else if (liststaff.get(i).getStaffName().equals(info)) {
                information.add(liststaff.get(i));
                Flag=1;
            }
            else  if (liststaff.get(i).getStaffSex().equals(info)) {
                information.add(liststaff.get(i));
                Flag=1;
            }
            else if (liststaff.get(i).getStaffTel().equals(info)) {
                information.add(liststaff.get(i));
                Flag=1;
            }
            else if (liststaff.get(i).getStaffIdcard().equals(info)) {
                information.add(liststaff.get(i));
                Flag=1;
            }
            else if (liststaff.get(i).getStaffLoginame().equals(info)) {
                information.add(liststaff.get(i));
                Flag=1;
            }
            else if(liststaff.get(i).getStaffPass().equals(info)){
                information.add(liststaff.get(i));
                Flag=1;
            }
        }
        if(Flag==1){
            map.put("getinfo",true);
            map.put("info",information);
        }
        if(Flag==0){
            map.put("msg","无匹配数据");
        }
        return map;
    }


    /*根据用户输入的关键字查找满足条件的值*/
    @RequestMapping(value = "/getAdminInfoBykeywords", method = RequestMethod.GET)
    public Map<String, Object> getAdminInfoBykeywords(@RequestParam("info")String info) {
        Map<String, Object> map = new HashMap<>();
        List<Admin> listadmin=adminRepository.findAll();
        List<Admin> information=new ArrayList<>();
        int Flag=0;
        for (int i = 0; i < listadmin.size(); i++) {
            int m=listadmin.get(i).getAdminId();
            String str=String.valueOf(m);
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            String str1=sdf.format(listadmin.get(i).getAdminRegtime());
            if(str.equals(info)){
                information.add(listadmin.get(i));
                Flag=1;
            }
            else if (listadmin.get(i).getAdminName().equals(info)) {
                information.add(listadmin.get(i));
                Flag=1;
            }
            else if (str1.equals(info)) {
                information.add(listadmin.get(i));
                Flag=1;
            }
            else  if (listadmin.get(i).getAdminSex().equals(info)) {
                information.add(listadmin.get(i));
                Flag=1;
            }
            else if (listadmin.get(i).getAdminTel().equals(info)) {
                information.add(listadmin.get(i));
                Flag=1;
            }
            else if (listadmin.get(i).getAdminIdcard().equals(info)) {
                information.add(listadmin.get(i));
                Flag=1;
            }
            else if (listadmin.get(i).getAdminLoginame().equals(info)) {
                information.add(listadmin.get(i));
                Flag=1;
            }
            else if(listadmin.get(i).getAdminPass().equals(info)){
                information.add(listadmin.get(i));
                Flag=1;
            }
            else if(listadmin.get(i).getAdminEmail().equals(info)){
                information.add(listadmin.get(i));
                Flag=1;
            }

        }
        if(Flag==1){
            map.put("getinfo",true);
            map.put("info",information);
        }
        if(Flag==0){
            map.put("msg","无匹配数据");
        }
        return map;
    }



    /*根据用户输入的关键字查找满足条件的值*/
    @RequestMapping(value = "/getUserInfoBykeywords", method = RequestMethod.GET)
    public Map<String, Object> getUserInfoBykeywords(@RequestParam("info")String info) {
        Map<String, Object> map = new HashMap<>();
        List<User> listuser=userRepository.findAll();
        List<User> information=new ArrayList<>();

        int Flag=0;
        for (int i = 0; i < listuser.size(); i++) {
            int m = listuser.get(i).getUserId();
            String str = String.valueOf(m);
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            String str1=sdf.format(listuser.get(i).getUserRegtime());
            if (str.equals(info) || listuser.get(i).getUserName().equals(info)|| listuser.get(i).getUserSex().equals(info)
                    || listuser.get(i).getUserTel().equals(info)|| listuser.get(i).getUserEmail().equals(info)||
                    listuser.get(i).getUserDoor().equals(info)|| listuser.get(i).getUserIdcard().equals(info)
                    || listuser.get(i).getUserLoginame().equals(info)|| listuser.get(i).getUserPass().equals(info)
                    ||str1.equals(info)) {
                information.add(listuser.get(i));
                map.put("getinfo",true);
                map.put("info",information);
                System.out.println(listuser.get(i).getUserName());
                Flag = 1;
            }
         /*  else if (listuser.get(i).getUserName().equals(info)) {
                information.add(listuser.get(i));
                Flag=1;
            }

            else if (listuser.get(i).getUserSex().equals(info)) {
                information.add(listuser.get(i));
                Flag=1;
            }

            else  if (listuser.get(i).getUserTel().equals(info)) {
                information.add(listuser.get(i));
                Flag=1;
            }
            else if (listuser.get(i).getUserEmail().equals(info)) {
                information.add(listuser.get(i));
                Flag=1;
            }
            else if (listuser.get(i).getUserDoor().equals(info)) {
                information.add(listuser.get(i));
                Flag=1;
            }
            else  if (listuser.get(i).getUserIdcard().equals(info)) {
                information.add(listuser.get(i));
                Flag=1;
            }

            else  if (listuser.get(i).getUserLoginame().equals(info)) {
                information.add(listuser.get(i));
                Flag=1;
            }

            else if(listuser.get(i).getUserPass().equals(info)){
                information.add(listuser.get(i));
                Flag=1;
            }*/
        }
        if(Flag==1){
            map.put("getinfo",true);
            map.put("info",information);
        }
        if(Flag==0){
            map.put("msg","无匹配数据");
        }
        return map;
    }


    /*根据管理员id获取管理员信息，注意类型是string还是int*/
    @RequestMapping(value = "/getAdminInfoById", method = RequestMethod.GET)
    public Map<String, Object> getAdminInfoById(@RequestParam("index")int info) {
        Map<String, Object> map = new HashMap<>();
        List<Admin> listadmin=adminRepository.findAll();
        List<Admin> information=new ArrayList<>();
        int Flag=0;
        for (int i = 0; i < listadmin.size(); i++) {
           /* int m=liststaff.get(i).getStaffId();
            String str=String.valueOf(m);*/
            if(listadmin.get(i).getAdminId()==info){
                information.add(listadmin.get(i));
                Flag=1;
            }
        }
        if(Flag==1){
            map.put("indexinfo",true);
            map.put("info",information);
        }
        if(Flag==0){

        }
        return map;
    }



    /*根据员工id获取员工信息，注意类型是string还是int*/
    @RequestMapping(value = "/getStaffInfoById", method = RequestMethod.GET)
    public Map<String, Object> getStaffInfoByindex(@RequestParam("index")int info) {
        Map<String, Object> map = new HashMap<>();
        List<Staff> liststaff=staffRepository.findAll();
        List<Staff> information=new ArrayList<>();
        int Flag=0;
        for (int i = 0; i < liststaff.size(); i++) {
           /* int m=liststaff.get(i).getStaffId();
            String str=String.valueOf(m);*/
            if(liststaff.get(i).getStaffId()==info){
                information.add(liststaff.get(i));
                Flag=1;
            }
        }
        if(Flag==1){
            map.put("indexinfo",true);
            map.put("info",information);
        }
        return map;
    }

    /*根据业主id获取员工信息，注意类型是string还是int*/
    @RequestMapping(value = "/getUserInfoById", method = RequestMethod.GET)
    public Map<String, Object> getUserInfoById(@RequestParam("index")int info) {
        Map<String, Object> map = new HashMap<>();
        List<User> listuser=userRepository.findAll();
        List<User> information=new ArrayList<>();
        int Flag=0;
        for (int i = 0; i < listuser.size(); i++) {
           /* int m=liststaff.get(i).getStaffId();
            String str=String.valueOf(m);*/
            if(listuser.get(i).getUserId()==info){
                information.add(listuser.get(i));
                Flag=1;
            }
        }
        if(Flag==1){
            map.put("indexinfo",true);
            map.put("info",information);
        }
        if(Flag==0){

        }
        return map;
    }



    /*根据id删除相关值，就是delete在起作用，删除员工*/
    /*删除员工和与员工相关的全部信息*/
    @RequestMapping(value = "/deleteStaff", method = RequestMethod.DELETE)
    public Map<String, Object> deleteStaffByStaffId(@RequestParam("ids") String ids) {
          int flag=0;
          Map<String, Object> map = new HashMap<>();
          List<Staff> liststaff=staffRepository.findAll();
          for(int i=0;i<liststaff.size();i++){
              int m=liststaff.get(i).getStaffId();
              String str=String.valueOf(m);
              if(str.equals(ids)){
                  staffRepository.delete(Integer.parseInt(ids));
                  flag=1;
              }
          }
          if(flag==1){
              map.put("delete",true);
              map.put("back","删除成功.");
              map.put("data",liststaff);
          }
          return map;
    }


    /*删除业主信息*/
    @RequestMapping(value = "/deleteUser", method = RequestMethod.DELETE)
    public Map<String, Object> deleteUserByUserId(@RequestParam("id") String id) {
        Map<String, Object> map = new HashMap<>();
        List<User> listuser=userRepository.findAll();
        List<Pet> listpet=petRepository.findAll();
        int flag1=0;
        int flag2=0;
        for(int i=0;i<listpet.size();i++){
            int m=listpet.get(i).getPetuserId();
            String str=String.valueOf(m);
            if(str.equals(id))
                flag2=2;
        }
        if(flag2==2){
            map.put("msg","该用户尚有业务在办理，不可删除该用户!");
        }
        else{
                for(int i=0;i<listuser.size();i++){
                    int m=listuser.get(i).getUserId();
                    String str=String.valueOf(m);
                    if(str.equals(id)){
                        userRepository.delete(Integer.parseInt(id));
                        flag1=1;
                    }
                }
            }
        if(flag1==1){
            map.put("delete",true);
            map.put("back","删除成功.");
            map.put("data",listuser);
        }
        return map;
    }

    /*删除管理员信息*/
    @RequestMapping(value = "/deleteAdmin", method = RequestMethod.DELETE)
    public Map<String, Object> deleteAdminByUserId(@RequestParam("id") int id) {
        Map<String, Object> map = new HashMap<>();
        List<Admin> listadmin=adminRepository.findAll();
        int flag=0;
        for(int i=0;i<listadmin.size();i++){
            if(listadmin.get(i).getAdminId()==id){
                adminRepository.delete(id);
                flag=1;
            }
        }

        if(flag==1){
            map.put("delete",true);
            map.put("back","删除成功.");
            map.put("data",listadmin);
        }
        return map;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public Map<String, Object> delete(@RequestParam(value = "ids") String ids) {
        Map<String, Object> map = new HashMap<String, Object>();
        String[] idsStr = ids.split(",");
        List<Staff> liststaff=staffRepository.findAll();
        int flag=0;
        for(int i=0;i<liststaff.size();i++){
            int m=liststaff.get(i).getStaffId();
            String str=String.valueOf(m);
            for (int j = 0; j < idsStr.length; j++){
                if(str.equals(idsStr[j])){
                    staffRepository.delete(Integer.parseInt(idsStr[j]));
                    flag=1;
                }
            }
        }
        if(flag==1){
            map.put("delete",true);
        }
        return map;
    }


    /*@RequestMapping(value = "/update", method = RequestMethod.PUT)
    public Map<String,Object> updateStaff(Staff staff) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<Staff> liststaff=staffRepository.findAll();
        for(int i=0;i<liststaff.size();i++){
            if(liststaff.get(i).getStaffLoginame().equals(staff.getStaffLoginame())){
                staffRepository.saveAndFlush(staff);
                map.put("success",true);
            }
         }
        return map;
    }*/

    /*@RequestMapping(value = "/save", method = RequestMethod.PUT)
    public Map<String,Object> saveStaff(Staff staff) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<Staff> liststaff=staffRepository.findAll();
        int flag=0;
        for(int i=0;i<liststaff.size();i++){
            if(liststaff.get(i).getStaffLoginame().equals(staff.getStaffLoginame())){
                map.put("fault",true);
                map.put("detail","用户名重复");
                flag=1;

               *//* map.put("success",false);*//*
            }
        }
        if(flag==0){
            staffRepository.save(staff);
            map.put("success",true);
            map.put("data","添加成功.");
        }
        return map;
    }*/


   /*修改员工信息*/
    @RequestMapping(value = "/updatestaff", method = RequestMethod.PUT)
    public Map<String, Object> updatestaff(@RequestParam("staffid")int staffid,
                                           @RequestParam("staffname")String staffname,
                                           @RequestParam("staffsex")String staffsex,
                                           @RequestParam("staffidcard")String staffidcard,
                                           @RequestParam("stafftel")String stafftel,
                                           @RequestParam("staffemail")String staffemail,
                                           @RequestParam("staffloginame")String staffloginame,
                                           @RequestParam("staffpass")String staffpass,
                                           @RequestParam("staffimg")String staffimg,
                                           @RequestParam("staffregtime") Date staffregtime,
                                           Staff staff) {
        Map<String, Object> map = new HashMap<>();
        List<Staff> liststaff = staffRepository.findAll();
        List<Pickupinfo> listpick =pickupInfoRepository.findAll();
        int flag=0;
        int m=0;
        for(int i = 0; i < liststaff.size();){
            if(liststaff.get(i).getStaffId()==staffid){
                i++;
                continue;
            }
            else if(liststaff.get(i).getStaffLoginame().equals(staffloginame)){
                m++;
                i++;
            }
            else
                i++;
        }
        if(m==1){
            map.put("repeat",true);
            map.put("data","用户名重复");
        }
        else if(m==0){
            for (int i = 0; i < liststaff.size(); i++) {
                if (liststaff.get(i).getStaffIdcard().equals(staffidcard)) {
                    liststaff.get(i).setStaffName(staffname);
                    liststaff.get(i).setStaffSex(staffsex);
                    liststaff.get(i).setStaffTel(stafftel);
                    liststaff.get(i).setStaffEmail(staffemail);
                    liststaff.get(i).setStaffLoginame(staffloginame);
                    liststaff.get(i).setStaffPass(staffpass);
                    liststaff.get(i).setStaffImg(staffimg);
                    liststaff.get(i).setStaffRegtime(staffregtime);
                    staffRepository.saveAndFlush(liststaff.get(i));
                    flag=1;
                }
            }
        }
        if(flag==1){
            for (int i = 0; i < listpick.size(); i++){
                if(listpick.get(i).getUserId()==null){
                    i++;
                }
                else if(listpick.get(i).getUserId()==staffid&&listpick.get(i).getPickuserProp().equals("物业管理员")){
                    listpick.get(i).setPickpetuserName(staffname);
                    listpick.get(i).setPickpetuserTel(stafftel);
                    listpick.get(i).setPickpetuserEmail(staffemail);
                    pickupInfoRepository.saveAndFlush(listpick.get(i));
                }
            }
        }
        if(flag==1){
            map.put("updatestaff", true);
            map.put("data", "更新成功!");
        }
        return map;
    }

    /*修改业主信息*/
    @RequestMapping(value = "/updateuser", method = RequestMethod.PUT)
    public Map<String, Object> updateuser( @RequestParam("userid")int userid,
                                           @RequestParam("username")String username,
                                           @RequestParam("usersex")String usersex,
                                           @RequestParam("useridcard")String useridcard,
                                           @RequestParam("usertel")String usertel,
                                           @RequestParam("useremail")String useremail,
                                           @RequestParam("userloginame")String userloginame,
                                           @RequestParam("userpass")String userpass,
                                           @RequestParam("userdoor")String userdoor,
                                           @RequestParam("useristenant")String useristenant,
                                           @RequestParam("userimg")String userimg,
                                           @RequestParam("useregtime") Date useregtime ) {
        Map<String, Object> map = new HashMap<>();
        List<User> listuser = userRepository.findAll();
        List<Adoptinfo> listadopt = adoptInfoRepository.findAll();
        List<Abandonpet> listabandon = abandonpetRepository.findAll();
        List<Loseinfo> listlose = loseInfoRepository.findAll();
        List<Vaccin> listvaccin = vaccinRepository.findAll();
        List<Pickupinfo> listpick = pickupInfoRepository.findAll();
        List<Fosterpetinfo> listfoster = fosterpetinfoRepository.findAll();
        List<Acceptpet> listaccept=acceptRepository.findAll();
        List<Tipinfo> listip =tipinfoRepository.findAll();
        int flag=0;
        int m=0;
        for(int i = 0; i < listuser.size();){
            if(listuser.get(i).getUserId()==userid){
                i++;
                continue;
            }
            else if(listuser.get(i).getUserLoginame().equals(userloginame)){
                m++;
                i++;
            }
            else
                i++;
        }
        if(m==1){
            map.put("repeat",true);
            map.put("data","用户名重复");
        }
        else if(m==0){
            for (int i = 0; i < listuser.size(); i++) {
                if (listuser.get(i).getUserIdcard().equals(useridcard)&&listuser.get(i).getUserId()==userid) {
                    listuser.get(i).setUserName(username);
                    listuser.get(i).setUserSex(usersex);
                    listuser.get(i).setUserTel(usertel);
                    listuser.get(i).setUserEmail(useremail);
                    listuser.get(i).setUserLoginame(userloginame);
                    listuser.get(i).setUserPass(userpass);
                    listuser.get(i).setUserDoor(userdoor);
                    listuser.get(i).setUserisTenant(useristenant);
                    listuser.get(i).setUserImg(userimg);
                    listuser.get(i).setUserRegtime(useregtime);
                    userRepository.saveAndFlush(listuser.get(i));
                    flag=1;
                }
            }
        }
        if(flag==1){
            for (int i = 0; i < listip.size(); i++){
                if(listip.get(i).getUserId()==userid){
                    listip.get(i).setUserName(username);
                    listip.get(i).setUserTel(usertel);
                    listip.get(i).setUserEmail(useremail);
                    tipinfoRepository.saveAndFlush(listip.get(i));
                }
            }
        }

        /*yuan业主*/
        if(flag==1){
            for (int i = 0; i < listadopt.size(); i++){
                if(listadopt.get(i).getPreuserId()==userid){
                    listadopt.get(i).setPetpreuserName(username);
                    listadopt.get(i).setPetpreuserTel(usertel);
                    listadopt.get(i).setPetpreuserEmail(useremail);
                    adoptInfoRepository.saveAndFlush(listadopt.get(i));
                }
            }
        }
        /*现主人*/
        if(flag==1){
            for (int i = 0; i < listadopt.size(); i++){
                if(listadopt.get(i).getNowuserId()==userid){
                    listadopt.get(i).setPetnowuserName(username);
                    listadopt.get(i).setPetnowuserTel(usertel);
                    listadopt.get(i).setPetnowuserEmail(useremail);
                    adoptInfoRepository.saveAndFlush(listadopt.get(i));
                }
            }
        }
        if(flag==1){
            for (int i = 0; i < listaccept.size(); i++){
                if(listaccept.get(i).getUserId()==userid){
                    listaccept.get(i).setUserName(username);
                    listaccept.get(i).setUserTel(usertel);
                    listaccept.get(i).setUserEmail(useremail);
                    acceptRepository.saveAndFlush(listaccept.get(i));
                }
            }
        }
        if(flag==1){
            for (int i = 0; i < listabandon.size(); i++){
                if(listabandon.get(i).getUserId()==userid){
                    listabandon.get(i).setUserName(username);
                    listabandon.get(i).setUserTel(usertel);
                    listabandon.get(i).setUserEmail(useremail);
                    abandonpetRepository.saveAndFlush(listabandon.get(i));
                }
            }
        }
        if(flag==1){
            for (int i = 0; i < listlose.size(); i++){
                if(listlose.get(i).getUserId()==userid){
                    listlose.get(i).setLosepetuserName(username);
                    listlose.get(i).setLosepetTel(usertel);
                    listlose.get(i).setLosepetEmail(useremail);
                    loseInfoRepository.saveAndFlush( listlose.get(i));
                }
            }

        }
        if(flag==1){
            for (int i = 0; i < listvaccin.size(); i++){
                if(listvaccin.get(i).getUserId()==userid){
                    listvaccin.get(i).setUserName(username);
                    listvaccin.get(i).setUserTel(usertel);
                    listvaccin.get(i).setUserEmail(useremail);
                    vaccinRepository.saveAndFlush(listvaccin.get(i));
                }
            }
        }
        if(flag==1){
            for (int i = 0; i < listpick.size(); i++){
                if(listpick.get(i).getUserId()==null){
                    i++;
                }
                else if(listpick.get(i).getUserId()==userid&&listpick.get(i).getPickuserProp().equals("业主")){
                    listpick.get(i).setPickpetuserName(username);
                    listpick.get(i).setPickpetuserTel(usertel);
                    listpick.get(i).setPickpetuserEmail(useremail);
                    pickupInfoRepository.saveAndFlush(listpick.get(i));
                }
            }
        }

        if(flag==1){
            for (int i = 0; i < listfoster.size(); i++){
                if(listfoster.get(i).getFosteruserId()==userid){
                    listfoster.get(i).setFosterpetuserName(username);
                    listfoster.get(i).setFosterpetuserTel(usertel);
                    listfoster.get(i).setFosterpetuserEmail(useremail);
                    fosterpetinfoRepository.saveAndFlush(listfoster.get(i));
                }
            }
        }
        if(flag==1){
            map.put("updateuser", true);
            map.put("data", "更新成功!");
        }
        return map;
    }


/*修改管理员信息*/
    @RequestMapping(value = "/updateadmin", method = RequestMethod.PUT)
    public Map<String, Object> updateadmin(@RequestParam("adminid")int adminid,
                                           @RequestParam("adminame")String adminame,
                                           @RequestParam("adminsex")String adminsex,
                                           @RequestParam("adminidcard")String adminidcard,
                                           @RequestParam("admintel")String admintel,
                                           @RequestParam("adminemail")String adminemail,
                                           @RequestParam("adminloginame")String adminloginame,
                                           @RequestParam("adminpass")String adminpass,
                                           @RequestParam("adminimg")String adminimg,
                                           @RequestParam("adminregtime") Date adminregtime ) {
        Map<String, Object> map = new HashMap<>();
        List<Admin> listadmin = adminRepository.findAll();
        List<Pickupinfo> listpick =pickupInfoRepository.findAll();
        int flag=0;
        int m=0;
        for(int i = 0; i < listadmin.size();){
            if(listadmin.get(i).getAdminId()==adminid){
               i++;
               continue;
            }
            else if(listadmin.get(i).getAdminLoginame().equals(adminloginame)){
                m++;
                i++;
            }
            else
                i++;
        }
        if(m==1){
            map.put("repeat",true);
            map.put("data","用户名重复");
        }
        else if(m==0){
            for (int i = 0; i < listadmin.size(); i++) {
                if (listadmin.get(i).getAdminIdcard().equals(adminidcard)) {
                    listadmin.get(i).setAdminName(adminame);
                    listadmin.get(i).setAdminSex(adminsex);
                    listadmin.get(i).setAdminTel(admintel);
                    listadmin.get(i).setAdminEmail(adminemail);
                    listadmin.get(i).setAdminLoginame(adminloginame);
                    listadmin.get(i).setAdminPass(adminpass);
                    listadmin.get(i).setAdminImg(adminimg);
                    listadmin.get(i).setAdminRegtime(adminregtime);
                    adminRepository.saveAndFlush(listadmin.get(i));
                    flag=1;
                }
            }
        }
        if(flag==1){
            for (int i = 0; i < listpick.size(); i++){
                if(listpick.get(i).getUserId()==null){
                    i++;
                }
                else if(listpick.get(i).getUserId()==adminid&&listpick.get(i).getPickuserProp().equals("后台管理员")){
                    listpick.get(i).setPickpetuserName(adminame);
                    listpick.get(i).setPickpetuserTel(admintel);
                    listpick.get(i).setPickpetuserEmail(adminemail);
                    pickupInfoRepository.saveAndFlush(listpick.get(i));
                }
            }
        }


        if(flag==1){
            map.put("updateadmin", true);
            map.put("data", "更新成功!");
        }
        return map;
    }



    /*添加宠物信息*/
    @RequestMapping(value = "/addpet", method = RequestMethod.POST)
    public Map<String, Object> addpet(@RequestParam("petname") String petname,
                                      @RequestParam("petsex") String petsex,
                                      @RequestParam("petuserid") String petuserid,
                                      @RequestParam("petimg") String petimg,
                                      @RequestParam("petype") String petype,
                                      @RequestParam("petprevaccin") Date petprevaccin,
                                      @RequestParam("petnextvaccin") Date petnextvaccin,
                                      @RequestParam("petisuserapply") String petisuserapply,
                                      @RequestParam("petregtime") Date petregtime,
                                       Pet pet) {
        Map<String, Object> map = new HashMap<>();
        List<User> listuser = userRepository.findAll();
        List<Pet> listpet = petRepository.findAll();
        List<Vaccin> listvaccin =vaccinRepository.findAll();
        int flag = 0;
        int flag1=0;
        for (int i = 0; i < listuser.size(); i++) {
            int m = listuser.get(i).getUserId();
            String str = String.valueOf(m);
            if (str.equals(petuserid)) {
                flag = 1;
            }
        }
        if (flag == 1) {
            try {
                pet.setPetName(petname);
                pet.setPetSex(petsex);
                pet.setPetImg(petimg);
                pet.setPetType(petype);
                pet.setPetuserId(Integer.parseInt(petuserid));
                pet.setPetrabidPrevaccin(petprevaccin);
                pet.setPetrabidNextvaccin(petnextvaccin);
                pet.setPetisuserApply(petisuserapply);
                pet.setPetrecodTime(petregtime);
                petRepository.save(pet);
                flag1=1;
                map.put("success", true);
                map.put("data", "添加成功");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (flag == 0) {
            map.put("data", "该用户未注册，请先注册.");
        }
        return map;
    }

}
