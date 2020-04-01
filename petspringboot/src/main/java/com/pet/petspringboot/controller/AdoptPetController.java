package com.pet.petspringboot.controller;

import com.pet.petspringboot.model.entity.*;
import com.pet.petspringboot.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/adopt")
@CrossOrigin
public class AdoptPetController {
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
    private PubAdoptInfoRepository pubAdoptInfoRepository;

    @Autowired
    private AdoptInfoRepository adoptInfoRepository;

    @Autowired
    private LoseInfoRepository loseInfoRepository;

    @Autowired
    private AbandonpetRepository abandonpetRepository;


    @Autowired
    private  AcceptRepository acceptRepository;

    @Autowired
    private FosterpetinfoRepository fosterpetinfoRepository;

    @Autowired
    private  VaccinRepository vaccinRepository;

    @Autowired
    private PickupInfoRepository pickupInfoRepository;
    @Autowired
    private TipinfoRepository tipinfoRepository;

    /*写入宠物信息*/
    @RequestMapping(value = "/adoptpublish", method = RequestMethod.POST)
    public Map<String, Object> adoptpublish(@RequestParam("adoptype")String adoptype,
                                            @RequestParam("petid")String petid,
                                            @RequestParam("petype")String petype,
                                            @RequestParam("petuserid")String petuserid,
                                            @RequestParam("publishtime")Date publishtime,
                                            @RequestParam("abandonreason") String abandonreason,
                                            Abandonpet abandonpet,
                                            Acceptpet acceptpet) {
        Map<String, Object> map = new HashMap<>();
        List<User> listuser=userRepository.findAll();
        List<Pet> listpet=petRepository.findAll();
        List<Abandonpet> abandoninfo=abandonpetRepository.findAll();
        List<Acceptpet> acceptinfo=acceptRepository.findAll();
        int flag=0;
        int flag1=0;
        int flag2=0;
        int flag3=0;
        int flag4=0;
        for(int i=0;i<listuser.size();i++){
            int m=listuser.get(i).getUserId();
            String str=String.valueOf(m);
            if(str.equals(petuserid)){
                abandonpet.setUserName(listuser.get(i).getUserName());
                abandonpet.setUserEmail(listuser.get(i).getUserEmail());
                abandonpet.setUserTel(listuser.get(i).getUserTel());
                acceptpet.setUserName(listuser.get(i).getUserName());
                acceptpet.setUserEmail(listuser.get(i).getUserEmail());
                acceptpet.setUserTel(listuser.get(i).getUserTel());
                flag1=1;
            }
        }
        if(flag1==1&&adoptype.equals("弃养"))
        for(int i=0;i<listpet.size();i++){
            int m=listpet.get(i).getPetId();
            String str=String.valueOf(m);
            int n=listpet.get(i).getPetuserId();
            String str1=String.valueOf(n);
            if(listpet.get(i).getPetisuserApply().equals("否")&&listpet.get(i).getPetType().equals(petype)
                    &&str.equals(petid)&&str1.equals(petuserid)){
                abandonpet.setPetName(listpet.get(i).getPetName());
                abandonpet.setPetImg(listpet.get(i).getPetImg());
                abandonpet.setPetSex(listpet.get(i).getPetSex());
                flag2=1;
            }
        }
        /*一个人可以发布多条领养信息*/
        if(adoptype.equals("领养")&&flag1==1){
            try{
                acceptpet.setUserId(Integer.parseInt(petuserid));
                acceptpet.setAdoptType(adoptype);
                acceptpet.setPetType(petype);
                acceptpet.setPubtime(publishtime);
                acceptRepository.save(acceptpet);
                flag=1;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
       if(flag2==1){
            for(int i=0;i<abandoninfo.size();i++){
                int m=abandoninfo.get(i).getPetId();
                String str=String.valueOf(m);
                if(str.equals(petid)){
                    /*info.get(i).setPetId(Integer.parseInt(petid));*/
                    abandoninfo.get(i).setUserId(Integer.parseInt(petuserid));
                    abandoninfo.get(i).setAdoptType(adoptype);
                    abandoninfo.get(i).setPetType(petype);
                    abandoninfo.get(i).setAbandonReason(abandonreason);
                    abandonpetRepository.saveAndFlush(abandoninfo.get(i));
                    flag3=1;
                }
            }
        }
        if(flag2==1&&flag3==0){
            try{
                abandonpet.setPetId(Integer.parseInt(petid));
                abandonpet.setUserId(Integer.parseInt(petuserid));
                abandonpet.setAdoptType(adoptype);
                abandonpet.setPetType(petype);
                abandonpet.setPubtime(publishtime);
                abandonpet.setAbandonReason(abandonreason);
                abandonpetRepository.save(abandonpet);
                flag4=1;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        if(flag==1||flag4==1){
            map.put("success",true);
            map.put("data","发布成功!");
        }
        if(flag3==1){
            map.put("update",true);
            map.put("data","发布领养信息更新成功!");
        }
        if(flag1==0){
            map.put("notreg",true);
            map.put("data", "该用户未注册");
        }
        if(adoptype.equals("弃养")&&flag1==1&&flag2==0){
            map.put("notreg",true);
            map.put("data", "该宠物未登记或用户与宠物不对应");
        }
        return map;
    }






    /*写入宠物信息*/
    @RequestMapping(value = "/adoptrecord", method = RequestMethod.POST)
    public Map<String, Object> adoptrecord(@RequestParam("petid")String petid,
                                           @RequestParam("preuserid")String preuserid,
                                           @RequestParam("nowuserid")String nowuserid,
                                           @RequestParam("adoptime")Date adoptime,
                                           @RequestParam("abandonreason") String abandonreason,
                                           Adoptinfo adopt,
                                           Pet pet) {
        Map<String, Object> map = new HashMap<>();
       /* List<Adoptinfo> listadopt=adoptInfoRepository.findAll();*/
        List<Adoptinfo> info=new ArrayList<>();
        List<User> listuser=userRepository.findAll();
        List<Pet> listpet=petRepository.findAll();
        List<Adoptinfo> listadopt=adoptInfoRepository.findAll();
        int flag=0;
        int flag1=0;
        int flag2=0;
        int flag3=0;
        int flag4=0;
        for(int i=0;i<listuser.size();i++){
            int m=listuser.get(i).getUserId();
            String str=String.valueOf(m);
            if(str.equals(preuserid)){
                adopt.setPetpreuserName(listuser.get(i).getUserName());
                adopt.setPetpreuserEmail(listuser.get(i).getUserEmail());
                adopt.setPetpreuserTel(listuser.get(i).getUserTel());
                flag1=1;
            }
        }
        for(int i=0;i<listuser.size();i++){
            int m=listuser.get(i).getUserId();
            String str=String.valueOf(m);
            if(str.equals(nowuserid)){
                adopt.setPetnowuserName(listuser.get(i).getUserName());
                adopt.setPetnowuserEmail(listuser.get(i).getUserEmail());
                adopt.setPetnowuserTel(listuser.get(i).getUserTel());
                flag2=1;
            }
        }
        if(flag1==1&&flag2==1){
            for(int i=0;i<listpet.size();i++){
                int m=listpet.get(i).getPetId();
                String str=String.valueOf(m);
                int n=listpet.get(i).getPetuserId();
                String str1=String.valueOf(n);
                if(listpet.get(i).getPetisuserApply().equals("否")&&str.equals(petid)&&str1.equals(preuserid)){
                    adopt.setPetName(listpet.get(i).getPetName());
                    adopt.setPetImg(listpet.get(i).getPetImg());
                    adopt.setPetSex(listpet.get(i).getPetSex());
                    adopt.setPetType(listpet.get(i).getPetType());
                    flag3=1;
                }
            }
        }
        if(flag1==1&&flag2==1&&flag3==1){
            for(int i=0;i<listadopt.size();i++){
                int m= listadopt.get(i).getPetId();
                String str=String.valueOf(m);
                if(str.equals(petid)){
                    listadopt.get(i).setPreuserId(Integer.parseInt(preuserid));
                    listadopt.get(i).setNowuserId(Integer.parseInt(nowuserid));
                    listadopt.get(i).setAdoptTime(adoptime);
                    listadopt.get(i).setAbandonReason(abandonreason);
                    adoptInfoRepository.saveAndFlush(listadopt.get(i));
                    flag4=1;
                }
            }
        }
        if(flag1==1&&flag2==1&&flag3==1&&flag4==0){
            try{
                adopt.setPetId(Integer.parseInt(petid));
                adopt.setPreuserId(Integer.parseInt(preuserid));
                adopt.setNowuserId(Integer.parseInt(nowuserid));
                adopt.setAdoptTime(adoptime);
                adopt.setAbandonReason(abandonreason);
               /* listadopt.add(adopt);*/
                adoptInfoRepository.save(adopt);
                flag=1;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        if(flag==1||flag4==1){
            for(int i=0;i<listpet.size();i++){
                int m=listpet.get(i).getPetId();
                String str=String.valueOf(m);
                int n=listpet.get(i).getPetuserId();
                String str1=String.valueOf(n);
                if(str.equals(petid)&&str1.equals(preuserid)){
                    listpet.get(i).setPetuserId(Integer.parseInt(nowuserid));
                    listpet.get(i).setPetrecodTime(adoptime);
                    petRepository.saveAndFlush(listpet.get(i));
                }
            }
        }
        if(flag==1){
            map.put("success",true);
            map.put("data","领养成功!");
        }
        if(flag4==1){
            map.put("update",true);
            map.put("data", "领养信息更新成功");
        }
        if(flag1==0||flag2==0){
            map.put("notreg",true);
            map.put("data", "用户未注册");
        }
        if(flag3==0){
            map.put("notreg",true);
            map.put("data", "该宠物未登记或用户与宠物不对应");
        }
        return map;
    }




    @RequestMapping(value = "/getabandoninfo", method = RequestMethod.GET)
    public Map<String, Object> getabandoninfo() {
        Map<String, Object> map = new HashMap<>();
        List<Abandonpet> listabandon=abandonpetRepository.findAll();
        map.put("getinfo",true);
        map.put("info", listabandon);
        return map;
    }

    @RequestMapping(value = "/getacceptinfo", method = RequestMethod.GET)
    public Map<String, Object> getacceptinfo() {
        Map<String, Object> map = new HashMap<>();
        List<Acceptpet> listaccept=acceptRepository.findAll();
        map.put("getinfo",true);
        map.put("info", listaccept);
        return map;
    }


    /*初始化寄养批复*/
    @RequestMapping(value = "/getadoptinfo", method = RequestMethod.GET)
    public Map<String, Object> getadoptinfo() {
        Map<String, Object> map = new HashMap<>();
        List<Adoptinfo> listadopt=adoptInfoRepository.findAll();
        map.put("getinfo",true);
        map.put("info", listadopt);
        return map;
    }


    @RequestMapping(value = "/getabandonbyId", method = RequestMethod.GET)
    public Map<String, Object> getabandonbyId(@RequestParam("index")int info) {
        Map<String, Object> map = new HashMap<>();
        List<Abandonpet> list=abandonpetRepository.findAll();
        List<Abandonpet> information=new ArrayList<>();
        int Flag=0;
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getAbandonId()==info){
                information.add(list.get(i));
                Flag=1;
            }
        }
        if(Flag==1){
            map.put("indexinfo",true);
            map.put("info",information);
        }
        return map;
    }

    @RequestMapping(value = "/getacceptbyId", method = RequestMethod.GET)
    public Map<String, Object> getacceptbyId(@RequestParam("index")int info) {
        Map<String, Object> map = new HashMap<>();
        List<Acceptpet> list=acceptRepository.findAll();
        List<Acceptpet> information=new ArrayList<>();
        int Flag=0;
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getAcceptId()==info){
                information.add(list.get(i));
                Flag=1;
            }
        }
        if(Flag==1){
            map.put("indexinfo",true);
            map.put("info",information);
        }
        return map;
    }

    @RequestMapping(value = "/getadoptById", method = RequestMethod.GET)
    public Map<String, Object> getadoptById(@RequestParam("index")int info) {
        Map<String, Object> map = new HashMap<>();
        List<Adoptinfo> list=adoptInfoRepository.findAll();
        List<Adoptinfo> information=new ArrayList<>();
        int Flag=0;
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getId()==info){
                information.add(list.get(i));
                Flag=1;
            }
        }
        if(Flag==1){
            map.put("indexinfo",true);
            map.put("info",information);
        }
        return map;
    }

    /*删除管理员信息*/
    @RequestMapping(value = "/deleteadopt", method = RequestMethod.DELETE)
    public Map<String, Object> deleteadopt(@RequestParam("id") int id) {
        Map<String, Object> map = new HashMap<>();
        List<Adoptinfo> listadopt=adoptInfoRepository.findAll();
        List<Adoptinfo> information=new ArrayList<>();
        int flag=0;
        for(int i=0;i<listadopt.size();i++){
            if(listadopt.get(i).getId()==id){
                adoptInfoRepository.delete(id);
                flag=1;
            }
        }
        if(flag==1){
            map.put("delete",true);
            map.put("data","删除成功.");
        }
        return map;
    }



    @RequestMapping(value = "/deleteabandon", method = RequestMethod.DELETE)
    public Map<String, Object> deleteabandon(@RequestParam("id") int id) {
        Map<String, Object> map = new HashMap<>();
        List<Abandonpet> list=abandonpetRepository.findAll();
        List<Abandonpet> information=new ArrayList<>();
        int flag=0;
        for(int i=0;i<list.size();i++){
          /*  int m=listpub.get(i).getId();
            String str=String.valueOf(m);*/
            if(list.get(i).getAbandonId()==id){
                abandonpetRepository.delete(id);
                flag=1;
            }
        }
        if(flag==1){
            map.put("delete",true);
            map.put("data","删除成功.");
        }
        return map;
    }

    @RequestMapping(value = "/deleteaccept", method = RequestMethod.DELETE)
    public Map<String, Object> deleteaccept(@RequestParam("id") int id) {
        Map<String, Object> map = new HashMap<>();
        List<Acceptpet> list=acceptRepository.findAll();
        List<Acceptpet> information=new ArrayList<>();
        int flag=0;
        for(int i=0;i<list.size();i++){
          /*  int m=listpub.get(i).getId();
            String str=String.valueOf(m);*/
            if(list.get(i).getAcceptId()==id){
                acceptRepository.delete(id);
                flag=1;
            }
        }
        if(flag==1){
            map.put("delete",true);
            map.put("data","删除成功.");
        }
        return map;
    }

    /*根据用户输入的关键字查找满足条件的值*/
    @RequestMapping(value = "/getabandonkey", method = RequestMethod.GET)
    public Map<String, Object> getabandonkey(@RequestParam("info")String info) {
        Map<String, Object> map = new HashMap<>();
        List<Abandonpet> list=abandonpetRepository.findAll();
        List<Abandonpet> information=new ArrayList<>();
        int Flag=0;
        for (int i = 0; i < list.size(); i++) {
            int m=list.get(i).getPetId();
            String str=String.valueOf(m);

            int m1=list.get(i).getAbandonId();
            String str1=String.valueOf(m1);

            int m2=list.get(i).getUserId();
            String str2=String.valueOf(m2);
            if(str.equals(info)){
                information.add(list.get(i));
                Flag=1;
            }
            else if(str1.equals(info)){
                information.add(list.get(i));
                Flag=1;
            }
            else if(str2.equals(info)){
                information.add(list.get(i));
                Flag=1;
            }
            else if (list.get(i).getUserName().equals(info)) {
                information.add(list.get(i));
                Flag=1;
            }
            else if (list.get(i).getUserTel().equals(info)) {
                information.add(list.get(i));
                Flag=1;
            }
            else if (list.get(i).getUserEmail().equals(info)) {
                information.add(list.get(i));
                Flag=1;
            }
            else if (list.get(i).getPetName().equals(info)) {
                information.add(list.get(i));
                Flag=1;
            }
            else if(list.get(i).getPetSex().equals(info)){
                information.add(list.get(i));
                Flag=1;
            }
            else if(list.get(i).getPetType().equals(info)){
                information.add(list.get(i));
                Flag=1;
            }
            else if(list.get(i).getPubtime().equals(info)){
                information.add(list.get(i));
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
    @RequestMapping(value = "/getacceptkey", method = RequestMethod.GET)
    public Map<String, Object> getacceptkey(@RequestParam("info")String info) {
        Map<String, Object> map = new HashMap<>();
        List<Acceptpet> list=acceptRepository.findAll();
        List<Acceptpet> information=new ArrayList<>();
        int Flag=0;
        for (int i = 0; i < list.size(); i++) {
            int m=list.get(i).getAcceptId();
            String str=String.valueOf(m);
            int m1=list.get(i).getUserId();
            String str1=String.valueOf(m1);
            if(str.equals(info)){
                information.add(list.get(i));
                Flag=1;
            }
            else if(str1.equals(info)){
                information.add(list.get(i));
                Flag=1;
            }
            else if (list.get(i).getUserName().equals(info)) {
                information.add(list.get(i));
                Flag=1;
            }
            else if (list.get(i).getUserTel().equals(info)) {
                information.add(list.get(i));
                Flag=1;
            }
            else if (list.get(i).getUserEmail().equals(info)) {
                information.add(list.get(i));
                Flag=1;
            }
            else if(list.get(i).getPetType().equals(info)){
                information.add(list.get(i));
                Flag=1;
            }
            else if(list.get(i).getPubtime().equals(info)){
                information.add(list.get(i));
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
    @RequestMapping(value = "/getadoptBykeywords", method = RequestMethod.GET)
    public Map<String, Object> getadoptBykeywords(@RequestParam("info")String info) {
        Map<String, Object> map = new HashMap<>();
        List<Adoptinfo> adopt=adoptInfoRepository.findAll();
        List<Adoptinfo> information=new ArrayList<>();
        int Flag=0;
        for (int i = 0; i < adopt.size(); i++) {
            int m=adopt.get(i).getId();
            String str=String.valueOf(m);

            int m1=adopt.get(i).getPreuserId();
            String str1=String.valueOf(m1);

            int m2=adopt.get(i).getNowuserId();
            String str2=String.valueOf(m2);

            int m3=adopt.get(i).getPetId();
            String str3=String.valueOf(m1);
            if(str.equals(info)){
                information.add(adopt.get(i));
                Flag=1;
            }
            else if(str1.equals(info)){
                information.add(adopt.get(i));
                Flag=1;
            }
            else if(str2.equals(info)){
                information.add(adopt.get(i));
                Flag=1;
            }
            else if(str3.equals(info)){
                information.add(adopt.get(i));
                Flag=1;
            }
            else if (adopt.get(i).getPetpreuserName().equals(info)) {
                information.add(adopt.get(i));
                Flag=1;
            }
            else if (adopt.get(i).getPetnowuserName().equals(info)) {
                information.add(adopt.get(i));
                Flag=1;
            }
            else if(adopt.get(i).getAdoptTime().equals(info)){
                information.add(adopt.get(i));
                Flag=1;
            }
            else if(adopt.get(i).getPetnowuserTel().equals(info)){
                information.add(adopt.get(i));
                Flag=1;
            }
            else if(adopt.get(i).getPetnowuserEmail().equals(info)){
                information.add(adopt.get(i));
                Flag=1;
            }
            else if(adopt.get(i).getPetpreuserEmail().equals(info)){
                information.add(adopt.get(i));
                Flag=1;
            }
            else if(adopt.get(i).getPetSex().equals(info)){
                information.add(adopt.get(i));
                Flag=1;
            }
            else if(adopt.get(i).getPetName().equals(info)){
                information.add(adopt.get(i));
                Flag=1;
            }
            else if(adopt.get(i).getPetType().equals(info)){
                information.add(adopt.get(i));
                Flag=1;
            }
            else if(adopt.get(i).getPetpreuserTel().equals(info)){
                information.add(adopt.get(i));
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

    /*修改領養宠物信息*/
    @RequestMapping(value = "/updateadoptinfo", method = RequestMethod.POST)
    public Map<String, Object> fosterecord(@RequestParam("id")int id,
                                           @RequestParam("petid")int petid,
                                           @RequestParam("preuserid")int preuserid,
                                           @RequestParam("nowuserid")int nowuserid,
                                           @RequestParam("petname")String petname,
                                           @RequestParam("petsex")String petsex,
                                           @RequestParam("petype")String petype,
                                           @RequestParam("petimg")String petimg,
                                           @RequestParam("preusername")String preusername,
                                           @RequestParam("preusertel")String preusertel,
                                           @RequestParam("preuseremail")String preuseremail,
                                           @RequestParam("nowusername")String nowusername,
                                           @RequestParam("nowusertel")String nowusertel,
                                           @RequestParam("nowuseremail")String nowuseremail,
                                           @RequestParam("abandonreason")String abandonreason,
                                           @RequestParam("adoptime")Date adoptime,
                                           Adoptinfo adoptinfo) {
        Map<String, Object> map = new HashMap<>();
        List<Pet> listpet=petRepository.findAll();
        List<Adoptinfo> listadopt=adoptInfoRepository.findAll();
        List<Loseinfo> listlose=loseInfoRepository.findAll();
        List<User> listuser=userRepository.findAll();
        List<Abandonpet> listabandon =abandonpetRepository.findAll();
        List<Acceptpet> listaccept =acceptRepository.findAll();
        List<Fosterpetinfo> listfoster =fosterpetinfoRepository.findAll();
        List<Vaccin> listvaccin =vaccinRepository.findAll();
        List<Fosterpet> listfpet =fosterRepository.findAll();
        List<Tipinfo> listip =tipinfoRepository.findAll();
        List<Pickupinfo> lispick =pickupInfoRepository.findAll();
        int flag=0;
        int m=0;
        for(int i=0;i<listadopt.size();i++){
            if(listadopt.get(i).getId()==id){
                listadopt.get(i).setPetId(petid);
                listadopt.get(i).setPreuserId(preuserid);
                listadopt.get(i).setNowuserId(nowuserid);
                listadopt.get(i).setPetName(petname);
                listadopt.get(i).setPetSex(petsex);
                listadopt.get(i).setPetImg(petimg);
                listadopt.get(i).setPetType(petype);
                listadopt.get(i).setPetpreuserName(preusername);
                listadopt.get(i).setPetpreuserTel(preusertel);
                listadopt.get(i).setPetpreuserEmail(preuseremail);
                listadopt.get(i).setPetnowuserName(nowusername);
                listadopt.get(i).setPetnowuserTel(nowusertel);
                listadopt.get(i).setPetnowuserEmail(nowuseremail);
                listadopt.get(i).setAbandonReason(abandonreason);
                listadopt.get(i).setAdoptTime(adoptime);
                adoptInfoRepository.saveAndFlush(listadopt.get(i));
                flag=1;
            }
        }
        if(flag==1){
            for (int i = 0; i < lispick.size(); i++){
                if(lispick.get(i).getUserId()==preuserid&&lispick.get(i).getPickuserProp().equals("业主")){
                    lispick.get(i).setPickpetuserName(preusername);
                    lispick.get(i).setPickpetuserTel(preusertel);
                    lispick.get(i).setPickpetuserEmail(preuseremail);
                    pickupInfoRepository.saveAndFlush(lispick.get(i));
                }
            }
        }
        if(flag==1){
            for (int i = 0; i < lispick.size(); i++){
                if(lispick.get(i).getUserId()==nowuserid&&lispick.get(i).getPickuserProp().equals("业主")){
                    lispick.get(i).setPickpetuserName(nowusername);
                    lispick.get(i).setPickpetuserTel(nowusertel);
                    lispick.get(i).setPickpetuserEmail(nowuseremail);
                    tipinfoRepository.saveAndFlush(listip.get(i));
                }
            }
        }
        if(flag==1){
            for (int i = 0; i < listip.size(); i++){
                if(listip.get(i).getUserId()==preuserid){
                    listip.get(i).setUserName(preusername);
                    listip.get(i).setUserTel(preusertel);
                    listip.get(i).setUserEmail(preuseremail);
                    tipinfoRepository.saveAndFlush(listip.get(i));
                }
            }
        }
        if(flag==1){
            for (int i = 0; i < listip.size(); i++){
                if(listip.get(i).getUserId()==nowuserid){
                    listip.get(i).setUserName(nowusername);
                    listip.get(i).setUserTel(nowusertel);
                    listip.get(i).setUserEmail(nowuseremail);
                    tipinfoRepository.saveAndFlush(listip.get(i));
                }
            }
        }
        if(flag==1){
            for (int i = 0; i < listadopt.size(); i++) {
                if (listadopt.get(i).getPreuserId() == preuserid) {
                    listadopt.get(i).setPetpreuserName(preusername);
                    listadopt.get(i).setPetpreuserTel(preusertel);
                    listadopt.get(i).setPetpreuserEmail(preuseremail);
                    adoptInfoRepository.saveAndFlush(listadopt.get(i));
                }
            }
        }
        if(flag==1){
            for (int i = 0; i < listadopt.size(); i++) {
                if (listadopt.get(i).getNowuserId() == nowuserid) {
                    listadopt.get(i).setPetnowuserName(nowusername);
                    listadopt.get(i).setPetnowuserTel(nowusertel);
                    listadopt.get(i).setPetnowuserEmail(nowuseremail);
                    adoptInfoRepository.saveAndFlush(listadopt.get(i));
                }
            }
        }
        if(flag==1){
            for (int i = 0; i < listadopt.size(); i++) {
                if (listadopt.get(i).getNowuserId() == preuserid) {
                    listadopt.get(i).setPetnowuserName(preusername);
                    listadopt.get(i).setPetnowuserTel(preusertel);
                    listadopt.get(i).setPetnowuserEmail(preuseremail);
                    adoptInfoRepository.saveAndFlush(listadopt.get(i));
                }
            }
        }
        if(flag==1){
            for (int i = 0; i < listadopt.size(); i++) {
                if (listadopt.get(i).getPreuserId() == nowuserid) {
                    listadopt.get(i).setPetpreuserName(nowusername);
                    listadopt.get(i).setPetpreuserTel(nowusertel);
                    listadopt.get(i).setPetpreuserEmail(nowuseremail);
                    adoptInfoRepository.saveAndFlush(listadopt.get(i));
                }
            }
        }
        if(flag==1){
            for (int i = 0; i < listfpet.size(); i++) {
                if (listfpet.get(i).getFosterpetId()==petid) {
                    listfpet.get(i).setFosterpetType(petype);
                    fosterRepository.saveAndFlush(listfpet.get(i));
                }
            }
        }
        if(flag==1){
            for (int i = 0; i < listvaccin.size(); i++) {
                if (listvaccin.get(i).getUserId()==preuserid) {
                    listvaccin.get(i).setUserName(preusername);
                    listvaccin.get(i).setUserTel(preusertel);
                    listvaccin.get(i).setUserEmail(preuseremail);
                    vaccinRepository.saveAndFlush(listvaccin.get(i));
                }
            }
        }
        if(flag==1){
            for (int i = 0; i < listvaccin.size(); i++) {
                if (listvaccin.get(i).getUserId()==nowuserid) {
                    listvaccin.get(i).setUserName(nowusername);
                    listvaccin.get(i).setUserTel(nowusertel);
                    listvaccin.get(i).setUserEmail(nowuseremail);
                    vaccinRepository.saveAndFlush(listvaccin.get(i));
                }
            }
        }
        if(flag==1){
            for (int i = 0; i < listvaccin.size(); i++) {
                if (listvaccin.get(i).getPetId()==petid) {
                    listvaccin.get(i).setPetName(petname);
                    listvaccin.get(i).setPetSex(petsex);
                    listvaccin.get(i).setPetType(petype);
                    vaccinRepository.saveAndFlush(listvaccin.get(i));
                }
            }
        }
        if(flag==1){
            for (int i = 0; i < listfoster.size(); i++) {
                if (listfoster.get(i).getFosteruserId()==preuserid){
                    listfoster.get(i).setFosterpetuserName(preusername);
                    listfoster.get(i).setFosterpetuserTel(preusertel);
                    listfoster.get(i).setFosterpetuserEmail(preuseremail);
                    fosterpetinfoRepository.saveAndFlush(listfoster.get(i));
                }
            }
        }
        if(flag==1){
            for (int i = 0; i < listfoster.size(); i++) {
                if (listfoster.get(i).getFosteruserId()==nowuserid){
                    listfoster.get(i).setFosterpetuserName(nowusername);
                    listfoster.get(i).setFosterpetuserTel(nowusertel);
                    listfoster.get(i).setFosterpetuserEmail(nowuseremail);
                    fosterpetinfoRepository.saveAndFlush(listfoster.get(i));
                }
            }
        }
        if(flag==1){
            for (int i = 0; i < listfoster.size(); i++) {
                if (listfoster.get(i).getFosterpetId()==petid){
                    listfoster.get(i).setFosterpetName(petname);
                    listfoster.get(i).setFosterpetSex(petsex);
                    listfoster.get(i).setFosterpetType(petype);
                    fosterpetinfoRepository.saveAndFlush(listfoster.get(i));
                }
            }
        }
        if(flag==1){
            for (int i = 0; i < listuser.size(); i++) {
                if (listuser.get(i).getUserId()==preuserid){
                    listuser.get(i).setUserName(preusername);
                    listuser.get(i).setUserTel(preusertel);
                    listuser.get(i).setUserEmail(preuseremail);
                    userRepository.saveAndFlush(listuser.get(i));
                }
            }
        }
        if(flag==1){
            for (int i = 0; i < listuser.size(); i++) {
                if (listuser.get(i).getUserId()==nowuserid){
                    listuser.get(i).setUserName(nowusername);
                    listuser.get(i).setUserTel(nowusertel);
                    listuser.get(i).setUserEmail(nowuseremail);
                    userRepository.saveAndFlush(listuser.get(i));
                }
            }
        }
        if(flag==1){
            for (int i = 0; i < listpet.size(); i++) {
                if (listpet.get(i).getPetId()==petid) {
                    listpet.get(i).setPetName(petname);
                    listpet.get(i).setPetSex(petsex);
                    listpet.get(i).setPetType(petype);
                    petRepository.saveAndFlush(listpet.get(i));
                }
            }
        }
        if(flag==1){
            for (int i = 0; i < listlose.size(); i++) {
                if (listlose.get(i).getPetId()==petid) {
                    listlose.get(i).setLosepetName(petname);
                    listlose.get(i).setLosepetSex(petsex);
                    listlose.get(i).setLosepetType(petype);
                    loseInfoRepository.saveAndFlush(listlose.get(i));
                }
            }
        }
        if(flag==1){
            for (int i = 0; i < listlose.size(); i++) {
                if (listlose.get(i).getUserId()==preuserid) {
                    listlose.get(i).setLosepetuserName(preusername);
                    listlose.get(i).setLosepetTel(preusertel);
                    listlose.get(i).setLosepetEmail(preuseremail);
                    loseInfoRepository.saveAndFlush(listlose.get(i));
                }
            }
        }
        if(flag==1){
            for (int i = 0; i < listlose.size(); i++) {
                if (listlose.get(i).getUserId()==nowuserid) {
                    listlose.get(i).setLosepetuserName(nowusername);
                    listlose.get(i).setLosepetTel(nowusertel);
                    listlose.get(i).setLosepetEmail(nowuseremail);
                    loseInfoRepository.saveAndFlush(listlose.get(i));
                }
            }
        }
        if(flag==1){
            for (int i = 0; i < listaccept.size(); i++) {
                if (listaccept.get(i).getUserId() == preuserid) {
                    listaccept.get(i).setUserName(preusername);
                    listaccept.get(i).setUserTel(preusertel);
                    listaccept.get(i).setUserEmail(preuseremail);
                    acceptRepository.saveAndFlush( listaccept.get(i));
                }
            }
        }
        if(flag==1){
            for (int i = 0; i < listaccept.size(); i++) {
                if (listaccept.get(i).getUserId() == nowuserid) {
                    listaccept.get(i).setUserName(nowusername);
                    listaccept.get(i).setUserTel(nowusertel);
                    listaccept.get(i).setUserEmail(nowuseremail);
                    acceptRepository.saveAndFlush( listaccept.get(i));
                }
            }
        }
        if(flag==1){
            for (int i = 0; i < listabandon.size(); i++) {
                if (listabandon.get(i).getPetId() == petid) {
                    listabandon.get(i).setPetName(petname);
                    listabandon.get(i).setPetSex(petsex);
                    listabandon.get(i).setPetType(petype);
                    abandonpetRepository.saveAndFlush( listabandon.get(i));
                }
            }
        }
        if(flag==1){
            for (int i = 0; i < listabandon.size(); i++) {
                if (listabandon.get(i).getUserId() == preuserid) {
                    listabandon.get(i).setUserName(preusername);
                    listabandon.get(i).setUserTel(preusertel);
                    listabandon.get(i).setUserEmail(preuseremail);
                    abandonpetRepository.saveAndFlush( listabandon.get(i));
                }
            }
        }
        if(flag==1){
            for (int i = 0; i < listabandon.size(); i++) {
                if (listabandon.get(i).getUserId() == nowuserid) {
                    listabandon.get(i).setUserName(nowusername);
                    listabandon.get(i).setUserTel(nowusertel);
                    listabandon.get(i).setUserEmail(nowuseremail);
                    abandonpetRepository.saveAndFlush( listabandon.get(i));
                }
            }
        }
        if(flag==1){
            map.put("update",true);
            map.put("data","领养记录更新成功");
        }
        return map;
    }


    /*修改領養宠物信息*/
    @RequestMapping(value = "/updateabandoninfo", method = RequestMethod.POST)
    public Map<String, Object> updateabandoninfo(@RequestParam("abandonid")int abandonid,
                                                 @RequestParam("petid")int petid,
                                                 @RequestParam("userid")int userid,
                                                 @RequestParam("adoptype")String adoptype,
                                                 @RequestParam("petname")String petname,
                                                 @RequestParam("petsex")String petsex,
                                                 @RequestParam("petype")String petype,
                                                 @RequestParam("petimg")String petimg,
                                                 @RequestParam("username")String username,
                                                 @RequestParam("usertel")String usertel,
                                                 @RequestParam("useremail")String useremail,
                                                 @RequestParam("abandonreason")String abandonreason,
                                                 @RequestParam("pubtime")Date pubtime,
                                                 Abandonpet abandonpet) {

        Map<String, Object> map = new HashMap<>();
        List<Pet> listpet=petRepository.findAll();
        List<User> listuser=userRepository.findAll();
        List<Adoptinfo> listadopt=adoptInfoRepository.findAll();
        List<Loseinfo> listlose=loseInfoRepository.findAll();
        List<Abandonpet> listabandon =abandonpetRepository.findAll();
        List<Fosterpetinfo> listfoster =fosterpetinfoRepository.findAll();
        List<Vaccin> listvaccin =vaccinRepository.findAll();
        List<Acceptpet> listaccept=acceptRepository.findAll();
        List<Fosterpet> listfpet =fosterRepository.findAll();
        List<Tipinfo> listip =tipinfoRepository.findAll();
        List<Pickupinfo> lispick =pickupInfoRepository.findAll();
        int flag=0;
        for(int i=0;i<listabandon.size();i++){
            if(listabandon.get(i).getAbandonId()==abandonid){
                listabandon.get(i).setPetId(petid);
                listabandon.get(i).setUserId(userid);
                listabandon.get(i).setAdoptType(adoptype);
                listabandon.get(i).setPetName(petname);
                listabandon.get(i).setPetSex(petsex);
                listabandon.get(i).setPetImg(petimg);
                listabandon.get(i).setPetType(petype);
                listabandon.get(i).setUserName(username);
                listabandon.get(i).setUserTel(usertel);
                listabandon.get(i).setUserEmail(useremail);
                listabandon.get(i).setAbandonReason(abandonreason);
                listabandon.get(i).setPubtime(pubtime);
                abandonpetRepository.saveAndFlush(listabandon.get(i));
                flag=1;
            }
        }
        if(flag==1){
            for (int i = 0; i < lispick.size(); i++){
                if(lispick.get(i).getUserId()==userid&&lispick.get(i).getPickuserProp().equals("业主")){
                    lispick.get(i).setPickpetuserName(username);
                    lispick.get(i).setPickpetuserTel(usertel);
                    lispick.get(i).setPickpetuserEmail(useremail);
                    pickupInfoRepository.saveAndFlush(lispick.get(i));
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
        if(flag==1){
            for (int i = 0; i < listfpet.size(); i++) {
                if (listfpet.get(i).getFosterpetId()==petid) {
                    listfpet.get(i).setFosterpetType(petype);
                    fosterRepository.saveAndFlush(listfpet.get(i));
                }
            }
        }
        if(flag==1){
            for (int i = 0; i < listaccept.size(); i++) {
                if (listaccept.get(i).getUserId()==userid) {
                    listaccept.get(i).setUserName(username);
                    listaccept.get(i).setUserTel(usertel);
                    listaccept.get(i).setUserEmail(useremail);
                    acceptRepository.saveAndFlush(listaccept.get(i));
                }
            }
        }
        if(flag==1){
            for (int i = 0; i < listvaccin.size(); i++) {
                if (listvaccin.get(i).getUserId()==userid) {
                    listvaccin.get(i).setUserName(username);
                    listvaccin.get(i).setUserTel(usertel);
                    listvaccin.get(i).setUserEmail(useremail);
                    vaccinRepository.saveAndFlush(listvaccin.get(i));
                }
            }
        }
        if(flag==1){
            for (int i = 0; i < listvaccin.size(); i++) {
                if (listvaccin.get(i).getPetId()==petid) {
                    listvaccin.get(i).setPetName(petname);
                    listvaccin.get(i).setPetSex(petsex);
                    listvaccin.get(i).setPetType(petype);
                    vaccinRepository.saveAndFlush(listvaccin.get(i));
                }
            }
        }
        if(flag==1){
            for (int i = 0; i < listfoster.size(); i++) {
                if (listfoster.get(i).getFosteruserId()==userid) {
                    listfoster.get(i).setFosterpetuserName(username);
                    listfoster.get(i).setFosterpetuserTel(usertel);
                    listfoster.get(i).setFosterpetuserEmail(useremail);
                    fosterpetinfoRepository.saveAndFlush(listfoster.get(i));
                }
            }
        }
        if(flag==1){
            for (int i = 0; i < listfoster.size(); i++) {
                if (listfoster.get(i).getFosterpetId()==petid) {
                    listfoster.get(i).setFosterpetName(petname);
                    listfoster.get(i).setFosterpetSex(petsex);
                    listfoster.get(i).setFosterpetType(petype);
                    fosterpetinfoRepository.saveAndFlush(listfoster.get(i));
                }
            }
        }
        if(flag==1){
            for (int i = 0; i < listuser.size(); i++) {
                if (listuser.get(i).getUserId()==userid) {
                    listuser.get(i).setUserName(username);
                    listuser.get(i).setUserTel(usertel);
                    listuser.get(i).setUserEmail(useremail);
                    userRepository.saveAndFlush(listuser.get(i));
                }
            }
        }
        if(flag==1){
            for (int i = 0; i < listpet.size(); i++) {
                if (listpet.get(i).getPetId()==petid) {
                    listpet.get(i).setPetName(petname);
                    listpet.get(i).setPetSex(petsex);
                    listpet.get(i).setPetType(petype);
                    petRepository.saveAndFlush(listpet.get(i));
                }
            }
        }
        if(flag==1){
            for (int i = 0; i < listlose.size(); i++) {
                if (listlose.get(i).getPetId()==petid) {
                    listlose.get(i).setLosepetName(petname);
                    listlose.get(i).setLosepetSex(petsex);
                    listlose.get(i).setLosepetType(petype);
                    loseInfoRepository.saveAndFlush(listlose.get(i));
                }
            }
        }
        if(flag==1){
            for (int i = 0; i < listlose.size(); i++) {
                if (listlose.get(i).getUserId()==userid) {
                    listlose.get(i).setLosepetuserName(username);
                    listlose.get(i).setLosepetTel(usertel);
                    listlose.get(i).setLosepetEmail(useremail);
                    loseInfoRepository.saveAndFlush(listlose.get(i));
                }
            }
        }
        if(flag==1){
            for (int i = 0; i < listadopt.size(); i++) {
                if (listadopt.get(i).getPetId() == petid) {
                    listadopt.get(i).setPetName(petname);
                    listadopt.get(i).setPetSex(petsex);
                    listadopt.get(i).setPetType(petype);
                    adoptInfoRepository.saveAndFlush( listadopt.get(i));
                }
            }
        }
        if(flag==1){
            for (int i = 0; i < listadopt.size(); i++) {
                if (listadopt.get(i).getPreuserId() == userid) {
                    listadopt.get(i).setPetpreuserName(username);
                    listadopt.get(i).setPetpreuserTel(usertel);
                    listadopt.get(i).setPetpreuserEmail(useremail);
                    adoptInfoRepository.saveAndFlush( listadopt.get(i));
                }
            }
        }
        if(flag==1){
            for (int i = 0; i < listadopt.size(); i++) {
                if (listadopt.get(i).getNowuserId() == userid) {
                    listadopt.get(i).setPetnowuserName(username);
                    listadopt.get(i).setPetnowuserTel(usertel);
                    listadopt.get(i).setPetnowuserEmail(useremail);
                    adoptInfoRepository.saveAndFlush( listadopt.get(i));
                }
            }
        }
        if(flag==1){
            for (int i = 0; i < listabandon.size(); i++) {
                if (listabandon.get(i).getUserId() == userid) {
                    listabandon.get(i).setUserName(username);
                    listabandon.get(i).setUserTel(usertel);
                    listabandon.get(i).setUserEmail(useremail);
                    abandonpetRepository.saveAndFlush(listabandon.get(i));
                }
            }
        }
        if(flag==1){
            for (int i = 0; i < listabandon.size(); i++) {
                if (listabandon.get(i).getPetId() == petid) {
                    listabandon.get(i).setPetName(petname);
                    listabandon.get(i).setPetSex(petsex);
                    listabandon.get(i).setPetType(petype);
                    abandonpetRepository.saveAndFlush(listabandon.get(i));
                }
            }
        }

        if(flag==1){
            map.put("update",true);
            map.put("data","发布弃养信息更新成功");
        }
        return map;
    }


    /*修改領養宠物信息*/
    @RequestMapping(value = "/updateacceptinfo", method = RequestMethod.POST)
    public Map<String, Object> updateacceptinfo(@RequestParam("acceptid")int acceptid,
                                                 @RequestParam("userid")int userid,
                                                 @RequestParam("adoptype")String adoptype,
                                                 @RequestParam("petype")String petype,
                                                 @RequestParam("username")String username,
                                                 @RequestParam("usertel")String usertel,
                                                 @RequestParam("useremail")String useremail,
                                                 @RequestParam("pubtime")Date pubtime,
                                                 Acceptpet acceptpet) {
        Map<String, Object> map = new HashMap<>();
        List<User> listuser=userRepository.findAll();
        List<Acceptpet> listaccept=acceptRepository.findAll();
        List<Adoptinfo> listadopt=adoptInfoRepository.findAll();
        List<Loseinfo> listlose=loseInfoRepository.findAll();
        List<Abandonpet> listabandon =abandonpetRepository.findAll();
        List<Fosterpetinfo> listfoster =fosterpetinfoRepository.findAll();
        List<Vaccin> listvaccin=vaccinRepository.findAll();
        List<Tipinfo> listip =tipinfoRepository.findAll();
        List<Pickupinfo> lispick =pickupInfoRepository.findAll();
        int flag=0;
        int m=0;
        for(int i=0;i<listaccept.size();i++){
            if(listaccept.get(i).getAcceptId()==acceptid){
                listaccept.get(i).setUserId(userid);
                listaccept.get(i).setAdoptType(adoptype);
                listaccept.get(i).setPetType(petype);
                listaccept.get(i).setUserName(username);
                listaccept.get(i).setUserTel(usertel);
                listaccept.get(i).setUserEmail(useremail);
                listaccept.get(i).setPubtime(pubtime);
                acceptRepository.saveAndFlush(listaccept.get(i));
                flag=1;
            }
        }
        if(flag==1){
            for (int i = 0; i < lispick.size(); i++){
                if(lispick.get(i).getUserId()==userid&&lispick.get(i).getPickuserProp().equals("业主")){
                    lispick.get(i).setPickpetuserName(username);
                    lispick.get(i).setPickpetuserTel(usertel);
                    lispick.get(i).setPickpetuserEmail(useremail);
                    pickupInfoRepository.saveAndFlush(lispick.get(i));
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
        if(flag==1){
            for (int i = 0; i < listvaccin.size(); i++) {
                if (listvaccin.get(i).getUserId()==userid) {
                    listvaccin.get(i).setUserName(username);
                    listvaccin.get(i).setUserTel(usertel);
                    listvaccin.get(i).setUserEmail(useremail);
                    vaccinRepository.saveAndFlush(listvaccin.get(i));
                }
            }
        }
        if(flag==1){
            for (int i = 0; i < listaccept.size(); i++) {
                if (listaccept.get(i).getUserId()==userid) {
                    listaccept.get(i).setUserName(username);
                    listaccept.get(i).setUserTel(usertel);
                    listaccept.get(i).setUserEmail(useremail);
                    acceptRepository.saveAndFlush(listaccept.get(i));
                }
            }
        }
        if(flag==1){
            for (int i = 0; i < listfoster.size(); i++) {
                if (listfoster.get(i).getFosteruserId()==userid) {
                    listfoster.get(i).setFosterpetuserName(username);
                    listfoster.get(i).setFosterpetuserTel(usertel);
                    listfoster.get(i).setFosterpetuserEmail(useremail);
                    fosterpetinfoRepository.saveAndFlush(listfoster.get(i));
                }
            }
        }
        if(flag==1){
            for (int i = 0; i < listuser.size(); i++) {
                if (listuser.get(i).getUserId()==userid) {
                    listuser.get(i).setUserName(username);
                    listuser.get(i).setUserTel(usertel);
                    listuser.get(i).setUserEmail(useremail);
                    userRepository.saveAndFlush(listuser.get(i));
                }
            }
        }
        if(flag==1){
            for (int i = 0; i < listlose.size(); i++) {
                if (listlose.get(i).getUserId()==userid) {
                    listlose.get(i).setLosepetuserName(username);
                    listlose.get(i).setLosepetTel(usertel);
                    listlose.get(i).setLosepetEmail(useremail);
                    loseInfoRepository.saveAndFlush(listlose.get(i));
                }
            }
        }
        if(flag==1){
            for (int i = 0; i < listadopt.size(); i++) {
                if (listadopt.get(i).getPreuserId() == userid) {
                    listadopt.get(i).setPetpreuserName(username);
                    listadopt.get(i).setPetpreuserTel(usertel);
                    listadopt.get(i).setPetpreuserEmail(useremail);
                    adoptInfoRepository.saveAndFlush( listadopt.get(i));
                }
            }
        }
        if(flag==1){
            for (int i = 0; i < listadopt.size(); i++) {
                if (listadopt.get(i).getNowuserId() == userid) {
                    listadopt.get(i).setPetnowuserName(username);
                    listadopt.get(i).setPetnowuserTel(usertel);
                    listadopt.get(i).setPetnowuserEmail(useremail);
                    adoptInfoRepository.saveAndFlush( listadopt.get(i));
                }
            }
        }
        if(flag==1){
            for (int i = 0; i < listabandon.size(); i++) {
                if (listabandon.get(i).getUserId() == userid) {
                    listabandon.get(i).setUserName(username);
                    listabandon.get(i).setUserTel(usertel);
                    listabandon.get(i).setUserEmail(useremail);
                    abandonpetRepository.saveAndFlush(listabandon.get(i));
                }
            }
        }
        if(flag==1){
            map.put("update",true);
            map.put("data","发布领养信息更新成功");
        }
        return map;
    }

}
