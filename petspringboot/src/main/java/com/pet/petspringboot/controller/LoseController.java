package com.pet.petspringboot.controller;

import com.pet.petspringboot.model.entity.*;
import com.pet.petspringboot.repository.*;
import jdk.nashorn.internal.ir.IdentNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/lose")
@CrossOrigin
public class LoseController {
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
    private LoseInfoRepository loseInfoRepository;

    @Autowired
    private AdoptInfoRepository adoptInfoRepository;
    @Autowired
    private AbandonpetRepository abandonpetRepository;
    @Autowired
    private FosterpetinfoRepository fosterpetinfoRepository;
    @Autowired
    private VaccinRepository vaccinRepository;
    @Autowired
    private AcceptRepository acceptRepository;
    @Autowired
    private PickupInfoRepository pickupInfoRepository;

    @Autowired
    private TipinfoRepository tipinfoRepository;


    /*发布宠物丢失信息*/
    @RequestMapping(value = "/losepublish", method = RequestMethod.POST)
    public Map<String, Object> losepublish(@RequestParam("petid")String petid,
                                           @RequestParam("petuserid")String petuserid,
                                           @RequestParam("losetime") Date losetime,
                                           @RequestParam("loseplace") String loseplace,
                                           @RequestParam("losedetail")String losedetail,
                                           @RequestParam("publosetime")Date publosetime,
                                           Loseinfo lose) {
        Map<String, Object> map = new HashMap<>();
        List<User> listuser=userRepository.findAll();
        List<Pet> listpet=petRepository.findAll();
        List<Loseinfo> listinfo=loseInfoRepository.findAll();
        int flag=0;
        int flag1=0;
        int flag2=0;
        int flag3=0;
        for(int i=0;i<listuser.size();i++){
            int m=listuser.get(i).getUserId();
            String str=String.valueOf(m);
            if(str.equals(petuserid)){
                lose.setLosepetuserName(listuser.get(i).getUserName());
                lose.setLosepetEmail(listuser.get(i).getUserEmail());
                lose.setLosepetTel(listuser.get(i).getUserTel());
                flag1=1;
            }
        }
        for(int i=0;i<listpet.size();i++){
            int m=listpet.get(i).getPetId();
            String str=String.valueOf(m);
            int n=listpet.get(i).getPetuserId();
            String str1=String.valueOf(n);
            if(listpet.get(i).getPetisuserApply().equals("否")&&str.equals(petid)&&str1.equals(petuserid)){
                lose.setLosepetName(listpet.get(i).getPetName());
                lose.setLosepetImg(listpet.get(i).getPetImg());
                lose.setLosepetSex(listpet.get(i).getPetSex());
                lose.setLosepetType(listpet.get(i).getPetType());
                flag2=1;
            }
        }
        if(flag1==1&&flag2==1){
            for(int i=0;i<listinfo.size();i++){
                int m=listinfo.get(i).getPetId();
                String str=String.valueOf(m);
                if(str.equals(petid)){
                    /*info.get(i).setPetId(Integer.parseInt(petid));*/
                    listinfo.get(i).setUserId(Integer.parseInt(petuserid));
                    listinfo.get(i).setLosepetTime(losetime);
                    listinfo.get(i).setLosepetPlace(loseplace);
                    listinfo.get(i).setLosepetDetail(losedetail);
                    listinfo.get(i).setLosepubTime(publosetime);
                    loseInfoRepository.saveAndFlush(listinfo.get(i));
                    flag3=1;
                }
            }
            if(flag1==1&&flag2==1&&flag3==0){
                try{
                    lose.setPetId(Integer.parseInt(petid));
                    lose.setUserId(Integer.parseInt(petuserid));
                    lose.setLosepetTime(losetime);
                    lose.setLosepetPlace(loseplace);
                    lose.setLosepetDetail(losedetail);
                    lose.setLosepubTime(publosetime);
                    loseInfoRepository.save(lose);
                    flag=1;
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        if(flag==1){
            map.put("success",true);
            map.put("data","发布成功!");
        }
        if(flag1==0){
            map.put("notreg",true);
            map.put("data", "用户未注册");
        }
        if(flag2==0){
            map.put("notreg",true);
            map.put("data", "该宠物未登记或用户与宠物不对应");
        }
        if(flag3==1){
            map.put("update",true);
            map.put("data","发布丢失信息更新成功!");
        }
        return map;
    }


    /*初始化寄养批复*/
    @RequestMapping(value = "/getloseinfo", method = RequestMethod.GET)
    public Map<String, Object> getloseinfo() {
        Map<String, Object> map = new HashMap<>();
        List<Loseinfo> listlose=loseInfoRepository.findAll();
        map.put("getinfo",true);
        map.put("info", listlose);
        return map;
    }


    @RequestMapping(value = "/getloseById", method = RequestMethod.GET)
    public Map<String, Object> getloseById(@RequestParam("index")int info) {
        Map<String, Object> map = new HashMap<>();
        List<Loseinfo> list=loseInfoRepository.findAll();
        List<Loseinfo> information=new ArrayList<>();
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


    /*修改丢失宠物信息*/
    @RequestMapping(value = "/updateloseinfo", method = RequestMethod.POST)
    public Map<String, Object> updateloseinfo(@RequestParam("id")int id,
                                              @RequestParam("petid")int petid,
                                              @RequestParam("userid")int userid,
                                              @RequestParam("petname")String petname,
                                              @RequestParam("petsex")String petsex,
                                              @RequestParam("petype")String petype,
                                              @RequestParam("petimg")String petimg,
                                              @RequestParam("username")String username,
                                              @RequestParam("usertel")String usertel,
                                              @RequestParam("useremail")String useremail,
                                              @RequestParam("losetime")Date losetime,
                                              @RequestParam("loseplace")String loseplace,
                                              @RequestParam("losedetail")String losedetail,
                                              @RequestParam("pubtime")Date pubtime,
                                              Loseinfo loseinfo) {
        Map<String, Object> map = new HashMap<>();
        List<Loseinfo> listlose=loseInfoRepository.findAll();
        List<Adoptinfo> listadopt=adoptInfoRepository.findAll();
        List<Pet> listpet=petRepository.findAll();
        List<User> listuser=userRepository.findAll();
        List<Abandonpet> listabandon =abandonpetRepository.findAll();
        List<Fosterpetinfo> listfoster =fosterpetinfoRepository.findAll();
        List<Vaccin> listvaccin =vaccinRepository.findAll();
        List<Acceptpet> listaccept =acceptRepository.findAll();
        List<Fosterpet> listfpet =fosterRepository.findAll();
        List<Pickupinfo> listpick =pickupInfoRepository.findAll();
        List<Tipinfo> listip =tipinfoRepository.findAll();
        int flag=0;
        for (int i = 0; i < listlose.size(); i++) {
            if (listlose.get(i).getId()==id) {
                listlose.get(i).setPetId(petid);
                listlose.get(i).setUserId(userid);
                listlose.get(i).setLosepetName(petname);
                listlose.get(i).setLosepetSex(petsex);
                listlose.get(i).setLosepetType(petype);
                listlose.get(i).setLosepetImg(petimg);
                listlose.get(i).setLosepetuserName(username);
                listlose.get(i).setLosepetTel(usertel);
                listlose.get(i).setLosepetEmail(useremail);
                listlose.get(i).setLosepetTime(losetime);
                listlose.get(i).setLosepetPlace(loseplace);
                listlose.get(i).setLosepetDetail(losedetail);
                listlose.get(i).setLosepubTime(pubtime);
                loseInfoRepository.saveAndFlush(listlose.get(i));
                flag=1;
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
            for (int i = 0; i < listpick.size(); i++) {
                if (listpick.get(i).getUserId() == userid&&listpick.get(i).getPickuserProp().equals("业主")) {
                    listpick.get(i).setPickpetuserName(username);
                    listpick.get(i).setPickpetuserTel(usertel);
                    listpick.get(i).setPickpetuserEmail(useremail);
                    pickupInfoRepository.saveAndFlush(listpick.get(i));
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
            for (int i = 0; i < listuser.size(); i++){
                if(listuser.get(i).getUserId()==userid){
                    listuser.get(i).setUserName(username);
                    listuser.get(i).setUserTel(usertel);
                    listuser.get(i).setUserEmail(useremail);
                    userRepository.saveAndFlush(listuser.get(i));
                }
            }
        }
        if(flag==1){
            for (int i = 0; i < listpet.size(); i++){
                if(listpet.get(i).getPetId()==petid){
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
            map.put("data","发布信息更新成功");
        }
        return map;
    }


    /*删除发布的宠物丢失信息*/
    @RequestMapping(value = "/deleteloseinfo", method = RequestMethod.DELETE)
    public Map<String, Object> deleteadopt(@RequestParam("id") int id) {
        Map<String, Object> map = new HashMap<>();
        List<Loseinfo> listlose=loseInfoRepository.findAll();
        List<Loseinfo> information=new ArrayList<>();
        int flag=0;
        for(int i=0;i<listlose.size();i++){
            if(listlose.get(i).getId()==id){
                loseInfoRepository.delete(id);
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
    @RequestMapping(value = "/getloseBykeywords", method = RequestMethod.GET)
    public Map<String, Object> getloseBykeywords(@RequestParam("info")String info) {
        Map<String, Object> map = new HashMap<>();
        List<Loseinfo> lose=loseInfoRepository.findAll();
        List<Loseinfo> information=new ArrayList<>();
        int Flag=0;
        for (int i = 0; i < lose.size(); i++) {
            int m=lose.get(i).getId();
            String str=String.valueOf(m);

            int m1=lose.get(i).getPetId();
            String str1=String.valueOf(m1);

            int m2=lose.get(i).getUserId();
            String str2=String.valueOf(m2);

            if(str.equals(info)){
                information.add(lose.get(i));
                Flag=1;
            }
            else if (str1.equals(info)) {
                information.add(lose.get(i));
                Flag=1;
            }
            else if (str2.equals(info)) {
                information.add(lose.get(i));
                Flag=1;
            }
            else if (lose.get(i).getLosepetName().equals(info)) {
                information.add(lose.get(i));
                Flag=1;
            }
            else if (lose.get(i).getLosepetSex().equals(info)) {
                information.add(lose.get(i));
                Flag=1;
            }
            else if(lose.get(i).getLosepetType().equals(info)){
                information.add(lose.get(i));
                Flag=1;
            }
            else if(lose.get(i).getLosepetuserName().equals(info)){
                information.add(lose.get(i));
                Flag=1;
            }
            else if(lose.get(i).getLosepetEmail().equals(info)){
                information.add(lose.get(i));
                Flag=1;
            }
            else if(lose.get(i).getLosepetTel().equals(info)){
                information.add(lose.get(i));
                Flag=1;
            }
            else if(lose.get(i).getLosepetPlace().equals(info)){
                information.add(lose.get(i));
                Flag=1;
            }
            else if(lose.get(i).getLosepubTime().equals(info)){
                information.add(lose.get(i));
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

}
