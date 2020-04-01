package com.pet.petspringboot.controller;

import com.pet.petspringboot.model.entity.*;
import com.pet.petspringboot.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/foster")
@CrossOrigin
public class FosterController {
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
    @Autowired
    private  FosterpetinfoRepository fosterpetinfoRepository;

    @Autowired
    private VaccinRepository vaccinRepository;

    /*根据用户输入的关键字查找满足条件的值*/
    @RequestMapping(value = "/getfosterApplyInfoBykeywords", method = RequestMethod.GET)
    public Map<String, Object> getfosterApplyInfoBykeywords(@RequestParam("info")String info) {
        Map<String, Object> map = new HashMap<>();
        List<Fosterpet> listfoster=fosterRepository.findAll();
        List<Fosterpet> information=new ArrayList<>();
        int flag=0;
        int flag1=0;
        for(int i=0;i<listfoster.size();i++){
            if(listfoster.get(i).getFosterpetisuserApply().equals("是")){
                int m1 = listfoster.get(i).getFosterId();
                String str1 = String.valueOf(m1);

                int m2 = listfoster.get(i).getFosterpetId();
                String str2 = String.valueOf(m2);

                int m3 = listfoster.get(i).getFosteruserId();
                String str3 = String.valueOf(m3);
                if (str1.equals(info)) {
                    information.add(listfoster.get(i));
                    flag = 1;
                }

                else if (str2.equals(info)) {
                    information.add(listfoster.get(i));
                    flag=1;
                }

                else if (str3.equals(info)) {
                    information.add(listfoster.get(i));
                    flag=1;
                }

                else  if (listfoster.get(i).getFosterpetType().equals(info)) {
                    information.add(listfoster.get(i));
                    flag=1;
                }
                else if (listfoster.get(i).getFosterPrice().equals(info)) {
                    information.add(listfoster.get(i));
                    flag=1;
                }

            }

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
    @RequestMapping(value = "/queryfosterInfoBykeywords", method = RequestMethod.GET)
    public Map<String, Object> queryfosterInfoBykeywords(@RequestParam("info")String info) {
        Map<String, Object> map = new HashMap<>();
        List<Fosterpet> listfoster=fosterRepository.findAll();
        List<Fosterpet> information=new ArrayList<>();
        int flag=0;
        int flag1=0;
        for(int i=0;i<listfoster.size();i++){
            if(listfoster.get(i).getFosterpetisuserApply().equals("否")){
                int m1 = listfoster.get(i).getFosterId();
                String str1 = String.valueOf(m1);

                int m2 = listfoster.get(i).getFosterpetId();
                String str2 = String.valueOf(m2);

                int m3 = listfoster.get(i).getFosteruserId();
                String str3 = String.valueOf(m3);
                if (str1.equals(info)) {
                    information.add(listfoster.get(i));
                    flag = 1;
                }

                else if (str2.equals(info)) {
                    information.add(listfoster.get(i));
                    flag=1;
                }

                else if (str3.equals(info)) {
                    information.add(listfoster.get(i));
                    flag=1;
                }

                else  if (listfoster.get(i).getFosterpetType().equals(info)) {
                    information.add(listfoster.get(i));
                    flag=1;
                }
                else if (listfoster.get(i).getFosterPrice().equals(info)) {
                    information.add(listfoster.get(i));
                    flag=1;
                }
            }
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


    @RequestMapping(value = "/queryrecordinfo", method = RequestMethod.GET)
    public Map<String, Object> queryrecordinfo(@RequestParam("info")String info) {
        Map<String, Object> map = new HashMap<>();
        List<Fosterpetinfo> listfoster=fosterpetinfoRepository.findAll();
        List<Fosterpetinfo> information=new ArrayList<>();
        int flag=0;
        int flag1=0;
        for(int i=0;i<listfoster.size();i++){
                int m1 = listfoster.get(i).getFosterId();
                String str1 = String.valueOf(m1);

                int m2 = listfoster.get(i).getId();
                String str2 = String.valueOf(m2);

                if (str1.equals(info)) {
                    information.add(listfoster.get(i));
                    flag = 1;
                }
                else if (str2.equals(info)) {
                    information.add(listfoster.get(i));
                    flag=1;
                }
                else  if (listfoster.get(i).getFosterpetType().equals(info)) {
                    information.add(listfoster.get(i));
                    flag=1;
                }
                else  if (listfoster.get(i).getFosterpetName().equals(info)) {
                    information.add(listfoster.get(i));
                    flag=1;
                }
                else  if (listfoster.get(i).getFosterpetpayornot().equals(info)) {
                    information.add(listfoster.get(i));
                    flag=1;
                }
                else  if (listfoster.get(i).getFosterPricescale().equals(info)) {
                    information.add(listfoster.get(i));
                    flag=1;
                }
                else  if (listfoster.get(i).getFosterpetSex().equals(info)) {
                    information.add(listfoster.get(i));
                    flag=1;
                }
                else if (listfoster.get(i).getFosterpetallPrice().equals(info)) {
                    information.add(listfoster.get(i));
                    flag=1;
                }
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
    @RequestMapping(value = "/getRegApplyInfoBykeywords", method = RequestMethod.GET)
    public Map<String, Object> getRegApplyInfoBykeywords(@RequestParam("info")String info) {
        Map<String, Object> map = new HashMap<>();
        List<Pet> listpet=petRepository.findAll();
        List<Pet> information=new ArrayList<>();
        int flag=0;
        int flag1=0;
        for(int i=0;i<listpet.size();i++){
            if(listpet.get(i).getPetisuserApply().equals("是")){
                int m1 = listpet.get(i).getPetId();
                String str1 = String.valueOf(m1);
                int m2 = listpet.get(i).getPetuserId();
                String str2 = String.valueOf(m2);
                if (str1.equals(info)) {
                    information.add(listpet.get(i));
                    flag = 1;
                }
                else if (str2.equals(info)) {
                    information.add(listpet.get(i));
                    flag=1;
                }

                else  if (listpet.get(i).getPetType().equals(info)) {
                    information.add(listpet.get(i));
                    flag=1;
                }
                else if (listpet.get(i).getPetSex().equals(info)) {
                    information.add(listpet.get(i));
                    flag=1;
                }

            }
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

    /*查询已寄养宠物*/
    @RequestMapping(value = "/getfosterpetInfo", method = RequestMethod.GET)
    public Map<String, Object> getfosterpetInfo() {
        Map<String, Object> map = new HashMap<>();
        List<Fosterpet> listfoster=fosterRepository.findAll();
        List<Fosterpet> info=new ArrayList<>();
        int flag=0;
        for(int i=0;i<listfoster.size();i++){
            if(listfoster.get(i).getFosterpetisuserApply().equals("否")){
                info.add(listfoster.get(i));
                flag=1;
            }
        }
        if(flag==1){
            map.put("getinfo",true);
            map.put("info", info);
        }
        return map;
    }


    /*初始寄养记录信息*/
    @RequestMapping(value = "/getfosterinfo", method = RequestMethod.GET)
    public Map<String, Object> queryfosterinfo() {
        Map<String, Object> map = new HashMap<>();
        List<Fosterpetinfo> listfoster=fosterpetinfoRepository.findAll();
        map.put("getinfo",true);
        map.put("info", listfoster);
        return map;
    }




    /*初始化寄养批复*/
    @RequestMapping(value = "/getApplyfosterInfo", method = RequestMethod.GET)
    public Map<String, Object> getApplyfosterInfo() {
        Map<String, Object> map = new HashMap<>();
        List<Fosterpet> listfoster=fosterRepository.findAll();
        List<Fosterpet> info=new ArrayList<>();
        int flag=0;
        for(int i=0;i<listfoster.size();i++){
            if(listfoster.get(i).getFosterpetisuserApply().equals("是")){
                info.add(listfoster.get(i));
                flag=1;
            }
        }
        if(flag==1){
            map.put("getinfo",true);
            map.put("info", info);
        }
        return map;
    }

    /*初始化登记批复*/
    @RequestMapping(value = "/getUpetReplyInfo", method = RequestMethod.GET)
    public Map<String, Object> getUpetReplyInfo() {
        Map<String, Object> map = new HashMap<>();
        List<Pet> listpet=petRepository.findAll();
        List<Pet> info=new ArrayList<>();
        int flag=0;
        for(int i=0;i<listpet.size();i++){
            if(listpet.get(i).getPetisuserApply().equals("是")){
                info.add(listpet.get(i));
                flag=1;
            }
        }
        if(flag==1){
            map.put("getinfo",true);
            map.put("info", info);
        }
        return map;
    }




    /*根据jiyang id获取寄养申请信息，注意类型是string还是int*/
    @RequestMapping(value = "/getfosterInfoById", method = RequestMethod.GET)
    public Map<String, Object> getfosterInfoById(@RequestParam("index")int id) {
        Map<String, Object> map = new HashMap<>();
        List<Fosterpet> listfoster=fosterRepository.findAll();
        List<Fosterpet> information=new ArrayList<>();
        int Flag=0;
        for (int i = 0; i < listfoster.size(); i++) {
           /* int m=liststaff.get(i).getStaffId();
            String str=String.valueOf(m);*/
            if(listfoster.get(i).getFosterId()==id){
                information.add(listfoster.get(i));
                Flag=1;
            }
        }
        if(Flag==1){
            map.put("indexinfo",true);
            map.put("info",information);
        }
        return map;
    }

    /*根据jiyang id获取寄养申请信息，注意类型是string还是int*/
    @RequestMapping(value = "/getfosterecordInfoById", method = RequestMethod.GET)
    public Map<String, Object> getfosterecordInfoById(@RequestParam("index")int info) {
        Map<String, Object> map = new HashMap<>();
        List<Fosterpetinfo> listfoster=fosterpetinfoRepository.findAll();
        List<Fosterpetinfo> information=new ArrayList<>();
        int Flag=0;
        for (int i = 0; i < listfoster.size(); i++) {
            if(listfoster.get(i).getId()==info){
                information.add(listfoster.get(i));
                Flag=1;
            }
        }
        if(Flag==1){
            map.put("indexinfo",true);
            map.put("info",information);
        }
        return map;
    }


    /*根据jiyang id获取寄养申请信息，注意类型是string还是int*/
    @RequestMapping(value = "/getvaccinInfoById", method = RequestMethod.GET)
    public Map<String, Object> getvaccinInfoById(@RequestParam("index")int info) {
        Map<String, Object> map = new HashMap<>();
        List<Vaccin> listvaccin=vaccinRepository.findAll();
        List<Vaccin> information=new ArrayList<>();
        int Flag=0;
        for (int i = 0; i < listvaccin.size(); i++) {
            if(listvaccin.get(i).getVaccinId()==info){
                information.add(listvaccin.get(i));
                Flag=1;
            }
        }
        if(Flag==1){
            map.put("indexinfo",true);
            map.put("info",information);
        }

        return map;
    }



    /* *//*根据jiyang id获取寄养申请信息，注意类型是string还是int*//*
    @RequestMapping(value = "/getfosterpetInfoById", method = RequestMethod.GET)
    public Map<String, Object> getfosterpetInfoById(@RequestParam("index")int info) {
        Map<String, Object> map = new HashMap<>();
        List<Fosterpet> listfoster=fosterRepository.findAll();
        List<Pet> listpet=petRepository.findAll();
        List<User> listuser=userRepository.findAll();
        List<Fosterpetinfo> listfosterinfo=fosterpetinfoRepository.findAll();
        Fosterpetinfo fosterpetinfo=new Fosterpetinfo();
        List<Fosterpet> infofoster=new ArrayList<>();
        List<Pet> infopet=petRepository.findAll();
        List<User> infouser=userRepository.findAll();
        List<Fosterpetinfo> infomation=new ArrayList<>();
        int Flag=0;
        int m=0;
        for (int i = 0; i < listfoster.size(); i++) {
           *//* int m=liststaff.get(i).getStaffId();
            String str=String.valueOf(m);*//*
            if(listfoster.get(i).getFosterId()==info){
                m=i;
                try {
                    fosterpetinfo.setFosterpetType(listfoster.get(i).getFosterpetType());
                    fosterpetinfo.setFostertime(listfoster.get(i).getFostertime());
                    fosterpetinfo.setFosterpetBegintime(listfoster.get(i).getFosterpetbeginTime());
                    fosterpetinfo.setFosterpetEndtime(listfoster.get(i).getFosterpetendTime());
                    fosterpetinfo.setFosterPricescale(listfoster.get(i).getFosterPrice());
                    for (int j = 0; j < listpet.size(); j++) {
                        if (listfoster.get(i).getFosterpetId() == listpet.get(j).getPetId()) {
                            fosterpetinfo.setFosterpetName(listpet.get(j).getPetName());
                            fosterpetinfo.setFosterpetSex(listpet.get(j).getPetSex());
                            fosterpetinfo.setFosterpetImg(listpet.get(j).getPetImg());
                        }
                    }
                    for (int k = 0; k < listuser.size(); k++) {
                        if (listfoster.get(i).getFosteruserId() == listuser.get(k).getUserId()) {
                            fosterpetinfo.setFosterpetuserName(listuser.get(k).getUserName());
                            fosterpetinfo.setFosterpetuserTel(listuser.get(k).getUserTel());
                            fosterpetinfo.setFosterpetuserEmail(listuser.get(k).getUserEmail());
                        }
                    }
                    fosterpetinfoRepository.save(fosterpetinfo);
                    Flag = 1;
                }
                catch (Exception e){
                    e.printStackTrace();

                }
            }
        }
        if(Flag==1){
            for(int n=0;n<listfosterinfo.size();n++){
                if(listfoster.get(m).getFosterId()==listfosterinfo.get(n).getFosterId()){
                    infomation.add(listfosterinfo.get(n));
                    map.put("indexinfo",true);
                    map.put("info",listfosterinfo);
                }
            }
        }
        return map;
    }
*/
    /*修改疫苗信息*/
    @RequestMapping(value = "/updatevaccin", method = RequestMethod.PUT)
    public Map<String, Object> updatevaccin(@RequestParam("vaccinid")int vaccinid,
                                            @RequestParam("petid")int petid,
                                            @RequestParam("userid")int userid,
                                            @RequestParam("petname")String petname,
                                            @RequestParam("petsex")String petsex,
                                            @RequestParam("petype")String petype,
                                            @RequestParam("username")String username,
                                            @RequestParam("usersex")String usersex,
                                            @RequestParam("usertel")String usertel,
                                            @RequestParam("useremail")String useremail,
                                            @RequestParam("prevaccin")Date prevaccin,
                                            @RequestParam("nextvaccin")Date nextvaccin,
                                            @RequestParam("petregtime") Date petregtime,
                                            Pet pet,
                                            Vaccin vaccin) {
        Map<String, Object> map = new HashMap<>();
        List<Vaccin> listvaccin = vaccinRepository.findAll();
        List<Pet> listpet = petRepository.findAll();
        int flag1=0;
        int flag2=0;
        int m=0;
        for(int i = 0; i < listpet.size();i++){
            if(listpet.get(i).getPetId()==petid){
                listpet.get(i).setPetrabidPrevaccin(prevaccin);
                listpet.get(i).setPetrabidNextvaccin(nextvaccin);
                petRepository.saveAndFlush(listpet.get(i));
                flag1=1;
            }
        }
        for(int i = 0; i < listvaccin.size();i++){
            if(listvaccin.get(i).getVaccinId()==vaccinid){
                listvaccin.get(i).setUserId(userid);
                listvaccin.get(i).setPetpreVaccin(prevaccin);
                listvaccin.get(i).setPetnextVaccin(nextvaccin);
                vaccinRepository.saveAndFlush(listvaccin.get(i));
                flag2=1;
            }
        }
        if(flag1==1&&flag2==1){
            map.put("update", true);
            map.put("data", "更新成功!");
        }
        else{
            map.put("msg", true);
            map.put("data", "更新失败!");
        }
        return map;
    }





    /*修改yonghu寄养申请信息信息，即同意用户寄养*/
    @RequestMapping(value = "/fosterecord", method = RequestMethod.POST)
    public Map<String, Object> fosterecord(@RequestParam("fosterid")int fosterid,
                                            @RequestParam("fosterpetid")int fosterpetid,
                                            @RequestParam("fosteruserid")int fosteruserid,
                                            @RequestParam("fosterpetype")String fosterpetype,
                                            @RequestParam("fosterbegintime")Date fosterbegintime,
                                            @RequestParam("fosterendtime")Date fosterendtime,
                                            @RequestParam("fosterpricescale")String fosterpricescale,
                                            @RequestParam("fosterpayprice")String fosterpayprice,
                                            @RequestParam("fosterpayornot")String fosterpayornot,
                                            @RequestParam("fosterdetail")String fosterdetail,
                                            @RequestParam("fostertime")Date fostertime,
                                            Fosterpetinfo fosterpetinfo) {
        Map<String, Object> map = new HashMap<>();
        List<Pet> listpet=petRepository.findAll();
        List<User> listuser=userRepository.findAll();
        List<Fosterpet> listfoster=fosterRepository.findAll();
        List<Fosterpetinfo> info=fosterpetinfoRepository.findAll();
        int flag=0;
        int flag1=0;
        int flag2=0;
        int flag3=0;
        int flag4=0;
        for (int i = 0; i < listpet.size(); i++) {
           /* int m=listfoster.get(i).getFosterId();
            String str=String.valueOf(m);*/
            if (listpet.get(i).getPetId()==fosterpetid) {
                fosterpetinfo.setFosterpetName(listpet.get(i).getPetName());
                fosterpetinfo.setFosterpetSex(listpet.get(i).getPetSex());
                fosterpetinfo.setFosterpetImg(listpet.get(i).getPetImg());
                flag1=1;
            }
        }
        for(int i=0;i<listuser.size();i++){
            if(listuser.get(i).getUserId()==fosteruserid){
                fosterpetinfo.setFosterpetuserName(listuser.get(i).getUserName());;
                fosterpetinfo.setFosterpetuserSex(listuser.get(i).getUserSex());
                fosterpetinfo.setFosterpetuserTel(listuser.get(i).getUserTel());
                fosterpetinfo.setFosterpetuserEmail(listuser.get(i).getUserEmail());
                flag2=1;
            }
        }
        for(int i=0;i<listfoster.size();i++){
            if(listfoster.get(i).getFosterId()==fosterid){
                listfoster.get(i).setFosterpetId(fosterpetid);
                listfoster.get(i).setFosteruserId(fosteruserid);
                listfoster.get(i).setFosterpetType(fosterpetype);
                listfoster.get(i).setFosterpetbeginTime(fosterbegintime);
                listfoster.get(i).setFosterpetendTime(fosterendtime);
                listfoster.get(i).setFosterPrice(fosterpricescale);
                listfoster.get(i).setFostertime(fostertime);
                fosterRepository.saveAndFlush(listfoster.get(i));
                flag=1;
            }
        }


        if(flag1==1&&flag2==1&&flag==1){
            for(int i=0;i<info.size();i++){
                if(info.get(i).getFosterpetId()==fosterpetid) {
                    info.get(i).setFosterId(fosterid);
                    info.get(i).setFosteruserId(fosteruserid);
                    info.get(i).setFosterpetType(fosterpetype);
                    info.get(i).setFosterpetBegintime(fosterbegintime);
                    info.get(i).setFosterpetEndtime(fosterendtime);
                    info.get(i).setFosterPricescale(fosterpricescale);
                    info.get(i).setFosterpetpayornot(fosterpayornot);
                    info.get(i).setFosterpetDetail(fosterdetail);
                    if(fosterpricescale.equals("40")){
                        int a=Integer.parseInt(fosterpayprice);
                        int n=a*40;
                        info.get(i).setFosterpetallPrice(String.valueOf(n));
                    }
                    else  if(fosterpricescale.equals("70")){
                        int a=Integer.parseInt(fosterpayprice);
                        int n=a*70;
                        info.get(i).setFosterpetallPrice(String.valueOf(n));
                    }
                    else if(fosterpricescale.equals("90")){
                        int a=Integer.parseInt(fosterpayprice);
                        int n=a*90;
                        info.get(i).setFosterpetallPrice(String.valueOf(n));
                    }
                    fosterpetinfoRepository.saveAndFlush(info.get(i));
                    flag3=1;
                }
            }
        }
        if(flag1==1&&flag2==1&&flag3==0){
            try{
                fosterpetinfo.setFosterId(fosterid);
                fosterpetinfo.setFosterpetId(fosterpetid);
                fosterpetinfo.setFosteruserId(fosteruserid);
                fosterpetinfo.setFosterpetType(fosterpetype);
                fosterpetinfo.setFosterpetBegintime(fosterbegintime);
                fosterpetinfo.setFosterpetEndtime(fosterendtime);
                fosterpetinfo.setFosterPricescale(fosterpricescale);
                fosterpetinfo.setFosterpetpayornot(fosterpayornot);
                fosterpetinfo.setFosterpetDetail(fosterdetail);
                fosterpetinfo.setFostertime(fostertime);
                if(fosterpricescale.equals("40")){
                    int a=Integer.parseInt(fosterpayprice);
                    int n=a*40;
                    fosterpetinfo.setFosterpetallPrice(String.valueOf(n));
                }
                else  if(fosterpricescale.equals("70")){
                    int a=Integer.parseInt(fosterpayprice);
                    int n=a*70;
                    fosterpetinfo.setFosterpetallPrice(String.valueOf(n));
                }
                else if(fosterpricescale.equals("90")){
                    int a=Integer.parseInt(fosterpayprice);
                    int n=a*90;
                    fosterpetinfo.setFosterpetallPrice(String.valueOf(n));
                }
                fosterpetinfoRepository.save(fosterpetinfo);
                flag4=1;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
      if(flag4==1){
          map.put("save", true);
          map.put("data","生成记录成功!");
      }
      if(flag3==1){
          map.put("update", true);
          map.put("data","记录更新成功!");
      }
        if(flag1==0||flag2==0){
            map.put("notReg",true);
            map.put("data","该宠物未注册!或该用户未注册!");
        }
        return map;
    }


  /*  *//*修改yonghu寄养申请信息信息，即同意用户寄养*//*
    @RequestMapping(value = "/queryfosterpet", method = RequestMethod.POST)
    public Map<String, Object> queryfosterpet(@RequestParam("fosterid")int fosterid,
                                           @RequestParam("fosterpetid")int fosterpetid,
                                           @RequestParam("fosteruserid")int fosteruserid,
                                           @RequestParam("fosterpetype")String fosterpetype,
                                           @RequestParam("fosterbegintime")Date fosterbegintime,
                                           @RequestParam("fosterendtime")Date fosterendtime,
                                           @RequestParam("fosterpricescale")String fosterpricescale,
                                           @RequestParam("fostertime")Date fostertime,
                                           Fosterpetinfo fosterpetinfo) {
        Map<String, Object> map = new HashMap<>();
        List<Pet> listpet=petRepository.findAll();
        List<User> listuser=userRepository.findAll();
        List<Fosterpetinfo> info=fosterpetinfoRepository.findAll();
        int flag=0;
        int flag1=0;
        int flag2=0;
        int flag3=0;
        int m=0;
        for (int i = 0; i < listpet.size(); i++) {
           *//* int m=listfoster.get(i).getFosterId();
            String str=String.valueOf(m);*//*
            if (listpet.get(i).getPetId()==fosterpetid) {
                String petname=listpet.get(i).getPetName();
                String petsex=listpet.get(i).getPetSex();
                String petimg=listpet.get(i).getPetImg();
                fosterpetinfo.setFosterpetName(petname);
                fosterpetinfo.setFosterpetSex(petsex);
                fosterpetinfo.setFosterpetImg(petimg);
                flag1=1;
            }
        }
        for(int i=0;i<listuser.size();i++){
            if(listuser.get(i).getUserId()==fosteruserid){
                String username=listuser.get(i).getUserName();
                String usersex=listuser.get(i).getUserSex();
                String usertel=listuser.get(i).getUserTel();
                String useremail=listuser.get(i).getUserEmail();
                fosterpetinfo.setFosterpetuserName(username);;
                fosterpetinfo.setFosterpetuserSex(usersex);
                fosterpetinfo.setFosterpetuserTel(usertel);
                fosterpetinfo.setFosterpetuserEmail(useremail);
                flag2=1;
            }
        }

        for(int i=0;i<info.size();i++){
            if(info.get(i).getFosterId()==fosterid) {
                flag3 = 1;
                info.get(i).setFosterpetType(fosterpetype);
                info.get(i).setFosterpetEndtime(fosterendtime);
                info.get(i).setFosterPricescale(fosterpricescale);
                info.get(i).setFosterpetpayornot(fosterpayornot);
                info.get(i).setFosterpetDetail(fosterdetail);
                if(fosterpricescale.equals("40")){
                    int a=Integer.parseInt(fosterpayprice);
                    int n=a*40;
                    System.out.println(n);
                    fosterpetinfo.setFosterpetallPrice(String.valueOf(n));
                }
                else  if(fosterpricescale.equals("70")){
                    int a=Integer.parseInt(fosterpayprice);
                    int n=a*70;
                    System.out.println(n);
                    fosterpetinfo.setFosterpetallPrice(String.valueOf(n));
                }
                else if(fosterpricescale.equals("90")){
                    int a=Integer.parseInt(fosterpayprice);
                    int n=a*90;
                    System.out.println(n);
                    fosterpetinfo.setFosterpetallPrice(String.valueOf(n));
                }
                fosterpetinfoRepository.saveAndFlush(info.get(i));
                map.put("save", true);
                map.put("success","记录更新成功!");
            }
        }

        if(flag1==1&&flag2==1&&flag3==0){
            try{
                fosterpetinfo.setFosterId(fosterid);
                fosterpetinfo.setFosterpetType(fosterpetype);
                fosterpetinfo.setFosterpetBegintime(fosterbegintime);
                fosterpetinfo.setFosterpetEndtime(fosterendtime);
                fosterpetinfo.setFosterPricescale(fosterpricescale);
                fosterpetinfo.setFosterpetpayornot(fosterpayornot);
                fosterpetinfo.setFosterpetDetail(fosterdetail);
                fosterpetinfo.setFostertime(fostertime);
                if(fosterpricescale.equals("40")){
                    int a=Integer.parseInt(fosterpayprice);
                    int n=a*40;
                    System.out.println(n);
                    fosterpetinfo.setFosterpetallPrice(String.valueOf(n));
                }
                else  if(fosterpricescale.equals("70")){
                    int a=Integer.parseInt(fosterpayprice);
                    int n=a*70;
                    System.out.println(n);
                    fosterpetinfo.setFosterpetallPrice(String.valueOf(n));
                }
                else if(fosterpricescale.equals("90")){
                    int a=Integer.parseInt(fosterpayprice);
                    int n=a*90;
                    System.out.println(n);
                    fosterpetinfo.setFosterpetallPrice(String.valueOf(n));
                }
                fosterpetinfoRepository.save(fosterpetinfo);
                map.put("save", true);
                map.put("success","记录成功生成!");
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        if(flag1==0||flag2==0){
            map.put("notReg",true);
            map.put("data","该宠物未注册!或该用户未注册!");
        }
        return map;
    }

*/



    /*根据申请登记的petid获取登记申请信息，注意类型是string还是int*/
    @RequestMapping(value = "/getApplypetInfoById", method = RequestMethod.GET)
    public Map<String, Object> getApplypetInfoById(@RequestParam("index")int info) {
        Map<String, Object> map = new HashMap<>();
        List<Pet> listpet=petRepository.findAll();
        List<Pet> information=new ArrayList<>();
        int Flag=0;
        for (int i = 0; i < listpet.size(); i++) {
           /* int m=liststaff.get(i).getStaffId();
            String str=String.valueOf(m);*/
            if(listpet.get(i).getPetId()==info){
                information.add(listpet.get(i));
                Flag=1;
            }
        }
        if(Flag==1){
            map.put("indexinfo",true);
            map.put("info",information);
        }

        return map;
    }


    /*拒绝用户寄养*/
    @RequestMapping(value = "/refusefosterapplyById", method = RequestMethod.DELETE)
    public Map<String, Object> refusefosterapply(@RequestParam("id") int id) {
        Map<String, Object> map = new HashMap<>();
        List<Fosterpet> listfoster=fosterRepository.findAll();
        int flag=0;
        for(int i=0;i<listfoster.size();i++){
            if(listfoster.get(i).getFosterId()==id){
                fosterRepository.delete(id);
                flag=1;
            }
        }
        /*if(flag==1){
            for(int i=0;i<listinfo.size();i++){
                if(listinfo.get(i).getFosterId()==id){
                    fosterpetinfoRepository.delete(id);
                }
            }
        }*/
        if(flag==1){
            map.put("refuse",true);
            map.put("back","操作成功.");
        }
        if(flag==0){
            map.put("fail",true);
            map.put("back","操作失败.");
        }
        return map;
    }

    /*删除寄养信息*/
    @RequestMapping(value = "/delfosterpetById", method = RequestMethod.DELETE)
    public Map<String, Object> delfosterpetById(@RequestParam("id") int id) {
        Map<String, Object> map = new HashMap<>();
        List<Fosterpet> listfoster=fosterRepository.findAll();
        List<Fosterpetinfo> listinfo=fosterpetinfoRepository.findAll();
        int flag=0;
        int flag1=0;
        for(int i=0;i<listfoster.size();i++){
            if(listfoster.get(i).getFosterpetId()==id){
                fosterRepository.delete(listfoster.get(i).getFosterId());
                for(int j=0;j<listinfo.size();j++){
                    if(listinfo.get(j).getFosterpetId()==id){
                        fosterpetinfoRepository.delete(listinfo.get(j).getId());
                    }
                }
                flag=1;
            }
        }
        if(flag==1){
            map.put("del",true);
            map.put("data","操作成功.");
        }
        if(flag==0){
            map.put("fail",true);
            map.put("data","操作失败");
        }

        return map;
    }


    /*删除寄养信息,只删除寄养的记录信息，*/
    @RequestMapping(value = "/delfosterecordById", method = RequestMethod.DELETE)
    public Map<String, Object> delfosterecordById(@RequestParam("id") int id) {
        Map<String, Object> map = new HashMap<>();
        List<Fosterpetinfo> listinfo=fosterpetinfoRepository.findAll();
        int flag=0;
        int flag1=0;
        for(int i=0;i<listinfo.size();i++){
            if(listinfo.get(i).getId()==id){
                fosterpetinfoRepository.delete(id);
                flag1=1;
            }
        }
        if(flag1==1){
            map.put("del",true);
            map.put("data","删除成功.");
        }
        return map;
    }

    /*删除宠物登记申请信息*/
    @RequestMapping(value = "/refuseUpetapplyById", method = RequestMethod.DELETE)
    public Map<String, Object> refuseUpetapplyById(@RequestParam("id") int id) {
        Map<String, Object> map = new HashMap<>();
        List<Pet> listpet=petRepository.findAll();
        int flag=0;
        for(int i=0;i<listpet.size();i++){
            if(listpet.get(i).getPetId()==id){
                petRepository.delete(id);
                flag=1;
            }

        }
        if(flag==1){
            map.put("refuse",true);
            map.put("back","操作成功.");
            map.put("data",listpet);
        }
        return map;
    }

    /*删除寄养申请信息*/
    @RequestMapping(value = "/delvaccinById", method = RequestMethod.DELETE)
    public Map<String, Object> delvaccinById(@RequestParam("id") int id) {
        Map<String, Object> map = new HashMap<>();
        List<Vaccin> listvaccin=vaccinRepository.findAll();
        List<Pet> listpet=petRepository.findAll();
        int flag=0;
        int flag1=0;
        for(int i=0;i<listvaccin.size();i++) {
            for (int j = 0; j < listpet.size(); j++) {
                if (listpet.get(j).getPetId() == listvaccin.get(i).getPetId()&&listpet.get(j).getPetisuserApply().equals("否")) {
                    flag= 1;
                }
            }
        }
         if(flag==1){
             map.put("exist",true);
             map.put("data","该宠物有业务尚未完成,不能删除相关疫苗信息。");
         }
         if(flag==0){
             for(int i=0;i<listvaccin.size();i++){
                 if(listvaccin.get(i).getVaccinId()==id){
                     vaccinRepository.delete(id);
                     flag1=1;
                 }
             }
         }
        if(flag1==1){
            map.put("del",true);
            map.put("back","删除成功.");
            map.put("data",listvaccin);
        }
        return map;
    }



    /*修改yonghu寄养申请信息信息，即同意用户寄养*/
    @RequestMapping(value = "/admitapplyfoster", method = RequestMethod.PUT)
    public Map<String, Object> admitapplyfoster(  @RequestParam("fosterid")int fosterid,
                                                  @RequestParam("fosterpetid")int fosterpetid,
                                                  @RequestParam("fosterpetype")String fosterpetype,
                                                  @RequestParam("fosteruserid")int fosteruserid,
                                                  @RequestParam("fosterbegintime")Date fosterbegintime,
                                                  @RequestParam("fosterendtime")Date fosterendtime,
                                                  @RequestParam("fosterpricescale")String fosterpricescale,
                                                  @RequestParam("fosterisuserapply")String fosterisuserapply,
                                                  @RequestParam("fostertime")Date fostertime) {
        Map<String, Object> map = new HashMap<>();
        List<Fosterpet> listfoster = fosterRepository.findAll();
        int flag=0;
        for (int i = 0; i < listfoster.size(); i++) {
            int m=listfoster.get(i).getFosterId();
            String str=String.valueOf(m);
            if (listfoster.get(i).getFosterId()==fosterid) {
                listfoster.get(i).setFosterpetId(fosterpetid);
                listfoster.get(i).setFosteruserId(fosteruserid);
                listfoster.get(i).setFosterpetType(fosterpetype);
                listfoster.get(i).setFosterpetbeginTime(fosterbegintime);
                listfoster.get(i).setFosterpetendTime(fosterendtime);
                listfoster.get(i).setFosterPrice(fosterpricescale);
                listfoster.get(i).setFosterpetisuserApply(fosterisuserapply);
                listfoster.get(i).setFostertime(fostertime);
                fosterRepository.saveAndFlush(listfoster.get(i));
                flag=1;
            }
        }
        if(flag==1){
            map.put("foster", true);
            map.put("data","寄养成功");
        }
        return map;
    }


    /*修改yonghu登记申请信息，即同意用户登记宠物*/
    @RequestMapping(value = "/admitapplyReg", method = RequestMethod.PUT)
    public Map<String, Object> admitapplyReg(@RequestParam("petid")int petid,
                                             @RequestParam("petname")String petname,
                                             @RequestParam("petsex")String petsex,
                                             @RequestParam("petuserid")int petuserid,
                                             @RequestParam("petimg")String petimg,
                                             @RequestParam("petype")String petype,
                                             @RequestParam("petprevaccin")Date petprevaccin,
                                             @RequestParam("petnextvaccin")Date petnextvaccin,
                                             @RequestParam("petisuserapply")String petisuserapply,
                                             @RequestParam("petregtime")Date petregtime,
                                             Vaccin vaccin) {
        Map<String, Object> map = new HashMap<>();
        List<Pet> listpet = petRepository.findAll();
        List<User> listuser = userRepository.findAll();
        List<Vaccin> listvaccin =vaccinRepository.findAll();
        int flag = 0;
        int flag1 = 0;
        int flag2 = 0;
        int flag3 = 0;
        for (int i = 0; i < listpet.size(); i++) {
            if (listpet.get(i).getPetId()==petid) {
                listpet.get(i).setPetName(petname);
                listpet.get(i).setPetSex(petsex);
                listpet.get(i).setPetuserId(petuserid);
                listpet.get(i).setPetImg(petimg);
                listpet.get(i).setPetType(petype);
                listpet.get(i).setPetrabidPrevaccin(petprevaccin);
                listpet.get(i).setPetrabidNextvaccin(petnextvaccin);
                listpet.get(i).setPetisuserApply(petisuserapply);
                listpet.get(i).setPetrecodTime(petregtime);
                petRepository.saveAndFlush(listpet.get(i));
                flag=1;
            }
        }
        if(flag==1){
            for (int i = 0; i < listuser.size(); i++){
                if(listuser.get(i).getUserId()==petuserid){
                    vaccin.setUserName(listuser.get(i).getUserName());
                    vaccin.setUserSex(listuser.get(i).getUserSex());
                    vaccin.setUserTel(listuser.get(i).getUserTel());
                    vaccin.setUserEmail(listuser.get(i).getUserEmail());
                    flag1=1;
                }
            }

        }
        if(flag1==1&&flag==1){
            for (int i = 0; i < listvaccin.size(); i++) {
                if ( listvaccin.get(i).getPetId()==petid) {
                    listvaccin.get(i).setUserId(petuserid);
                    listvaccin.get(i).setPetName(petname);
                    listvaccin.get(i).setPetSex(petsex);
                    listvaccin.get(i).setPetType(petype);
                    listvaccin.get(i).setPetpreVaccin(petprevaccin);
                    listvaccin.get(i).setPetnextVaccin(petnextvaccin);
                    listvaccin.get(i).setPetRegtime(petregtime);
                    vaccinRepository.saveAndFlush(listvaccin.get(i));
                    flag2 = 1;
                }
            }
        }
        if(flag1==1&&flag==1&&flag2==0){
            try {
                vaccin.setPetId(petid);
                vaccin.setUserId(petuserid);
                vaccin.setPetName(petname);
                vaccin.setPetSex(petsex);
                vaccin.setPetType(petype);
                vaccin.setPetpreVaccin(petprevaccin);
                vaccin.setPetnextVaccin(petnextvaccin);
                vaccin.setPetRegtime(petregtime);
                vaccinRepository.save(vaccin);
                flag3=1;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(flag2==1){
            map.put("updatevaccin",true);
            map.put("data","疫苗信息更新成功");
        }
        if(flag3==1) {
            map.put("success",true);
            map.put("data","疫苗信息成功生成");
        }

        if(flag==1){
            map.put("reg", true);
            map.put("data","登记成功");
        }

        return map;
    }

    /* */
    @RequestMapping(value = "/vaccinInfo", method = RequestMethod.GET)
    public Map<String, Object> vaccin() {
        FosterController fosterController=new FosterController();
        String s=fosterController.GetNowDate();
        Map<String, Object> map = new HashMap<>();
        List<Vaccin> vaccin=vaccinRepository.findAll();
        List<Vaccin> info=new ArrayList<>();
        int flag=0;
        for(int i=0;i<vaccin.size();i++){
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            String str=sdf.format(vaccin.get(i).getPetnextVaccin());
            int res=s.compareTo(str);
            if(res>0){
                info.add(vaccin.get(i));
                flag=1;
            }
        }
        if(flag==1){
            map.put("getinfo",true);
            map.put("info", info);
        }
        return map;
    }


    public String GetNowDate(){
        String str="";
        Date dt = new Date();
        //最后的aa表示“上午”或“下午”    HH表示24小时制    如果换成hh表示12小时制
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        str=sdf.format(dt);
        return str;
    }



}
