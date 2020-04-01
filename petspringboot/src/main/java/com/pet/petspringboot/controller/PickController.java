package com.pet.petspringboot.controller;

import com.pet.petspringboot.model.entity.*;
import com.pet.petspringboot.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/pick")
@CrossOrigin
public class PickController {
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
    @RequestMapping(value = "/pickpublish", method = RequestMethod.POST)
    public Map<String, Object> pickpublish(@RequestParam("userid")String userid,
                                           @RequestParam("petsex")String petsex,
                                           @RequestParam("petype")String petype,
                                           @RequestParam("petstatus")String petstatus,
                                           @RequestParam("petimageUrl") String petimageUrl,
                                           @RequestParam("picktime")Date picktime,
                                           @RequestParam("pickplace") String pickplace,
                                           @RequestParam("pickdetail") String pickdetail,
                                           @RequestParam("pubpicktime") Date pubpicktime,
                                           Pickupinfo pickinfo) {
        Map<String, Object> map = new HashMap<>();
        List<User> listuser=userRepository.findAll();
        int flag=0;
        for(int i=0;i<listuser.size();i++){
            int m=listuser.get(i).getUserId();
            String str=String.valueOf(m);
            if(str.equals(userid)){
                pickinfo.setPickpetuserName(listuser.get(i).getUserName());
                pickinfo.setPickpetuserEmail(listuser.get(i).getUserEmail());
                pickinfo.setPickpetuserTel(listuser.get(i).getUserTel());
                flag=1;
            }
        }
        if(flag==1){
            try{
                pickinfo.setPickpetSex(petsex);
                pickinfo.setPickpetType(petype);
                pickinfo.setPickpetState(petstatus);
                pickinfo.setPickpetImg(petimageUrl);

                pickinfo.setPickpetTime(picktime);
                pickinfo.setPickpetPlace(pickplace);
                pickinfo.setPickpetDetail(pickdetail);
                pickinfo.setPickpubTime(pubpicktime);
                pickupInfoRepository.save(pickinfo);
                map.put("success",true);
                map.put("data","发布成功!");
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        if(flag==0){
            map.put("notreg",true);
            map.put("data", "该用户未注册");
        }
        return map;
    }


    @RequestMapping(value = "/userpubpick", method = RequestMethod.POST)
    public Map<String, Object> userpubpick(@RequestParam("userid")int userid,
                                           @RequestParam("petsex")String petsex,
                                           @RequestParam("petype")String petype,
                                           @RequestParam("petstatus")String petstatus,
                                           @RequestParam("petimg") String petimageUrl,
                                           @RequestParam("picktime")Date picktime,
                                           @RequestParam("pickplace") String pickplace,
                                           @RequestParam("pickdetail") String pickdetail,
                                           @RequestParam("pubpicktime") Date pubpicktime,
                                           Pickupinfo pickinfo) {
        Map<String, Object> map = new HashMap<>();
        List<User> listuser=userRepository.findAll();
        int flag=0;
        int flag1=0;
        for(int i=0;i<listuser.size();i++){
            if(listuser.get(i).getUserId()==userid){
                pickinfo.setPickpetuserName(listuser.get(i).getUserName());
                pickinfo.setPickpetuserEmail(listuser.get(i).getUserEmail());
                pickinfo.setPickpetuserTel(listuser.get(i).getUserTel());
                pickinfo.setPickuserProp(listuser.get(i).getUserProp());
                flag=1;
            }
        }
        if(flag==1){
            try{
                pickinfo.setUserId(userid);
                pickinfo.setPickpetSex(petsex);
                pickinfo.setPickpetType(petype);
                pickinfo.setPickpetState(petstatus);
                pickinfo.setPickpetImg(petimageUrl);
                pickinfo.setPickpetTime(picktime);
                pickinfo.setPickpetPlace(pickplace);
                pickinfo.setPickpetDetail(pickdetail);
                pickinfo.setPickpubTime(pubpicktime);
                pickupInfoRepository.save(pickinfo);
                flag1=1;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        if(flag1==1){
            map.put("success",true);
            map.put("data","发布成功!");
        }
        if(flag==0){
            map.put("notreg",true);
            map.put("data", "该用户未注册");
        }
        return map;
    }




    @RequestMapping(value = "/pickpublishuser", method = RequestMethod.POST)
    public Map<String, Object> pickpublishuser(@RequestParam("userid")String userid,
                                               @RequestParam("petsex")String petsex,
                                               @RequestParam("petype")String petype,
                                               @RequestParam("petstatus")String petstatus,
                                               @RequestParam("petimg") String petimageUrl,
                                               @RequestParam("picktime")Date picktime,
                                               @RequestParam("pickplace") String pickplace,
                                               @RequestParam("pickdetail") String pickdetail,
                                               @RequestParam("pubpicktime") Date pubpicktime,
                                               Pickupinfo pickinfo) {
        Map<String, Object> map = new HashMap<>();
        List<User> listuser=userRepository.findAll();
        int flag=0;
        int flag1=0;
        for(int i=0;i<listuser.size();i++){
            int m=listuser.get(i).getUserId();
            String str=String.valueOf(m);
            if(str.equals(userid)){
                pickinfo.setPickpetuserName(listuser.get(i).getUserName());
                pickinfo.setPickpetuserEmail(listuser.get(i).getUserEmail());
                pickinfo.setPickpetuserTel(listuser.get(i).getUserTel());
                pickinfo.setPickuserProp(listuser.get(i).getUserProp());
                flag=1;
            }
        }
        if(flag==1){
            try{
                pickinfo.setUserId(Integer.parseInt(userid));
                pickinfo.setPickpetSex(petsex);
                pickinfo.setPickpetType(petype);
                pickinfo.setPickpetState(petstatus);
                pickinfo.setPickpetImg(petimageUrl);
                pickinfo.setPickpetTime(picktime);
                pickinfo.setPickpetPlace(pickplace);
                pickinfo.setPickpetDetail(pickdetail);
                pickinfo.setPickpubTime(pubpicktime);
                pickupInfoRepository.save(pickinfo);
                flag1=1;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        if(flag1==1){
            map.put("success",true);
            map.put("data","发布成功!");
        }
        if(flag==0){
            map.put("notreg",true);
            map.put("data", "该用户未注册");
        }
        return map;
    }

    @RequestMapping(value = "/pickpublishstaff", method = RequestMethod.POST)
    public Map<String, Object> pickpublishstaff(@RequestParam("userid")String userid,
                                               @RequestParam("petsex")String petsex,
                                                @RequestParam("petype")String petype,
                                               @RequestParam("petstatus")String petstatus,
                                               @RequestParam("petimg") String petimageUrl,
                                               @RequestParam("picktime")Date picktime,
                                               @RequestParam("pickplace") String pickplace,
                                               @RequestParam("pickdetail") String pickdetail,
                                               @RequestParam("pubpicktime") Date pubpicktime,
                                                Pickupinfo pickinfo) {
        Map<String, Object> map = new HashMap<>();
        List<Staff> liststaff=staffRepository.findAll();
        int flag=0;
        for(int i=0;i<liststaff.size();i++){
            int m=liststaff.get(i).getStaffId();
            String str=String.valueOf(m);
            if(str.equals(userid)){
                pickinfo.setPickpetuserName(liststaff.get(i).getStaffName());
                pickinfo.setPickpetuserEmail(liststaff.get(i).getStaffEmail());
                pickinfo.setPickpetuserTel(liststaff.get(i).getStaffTel());
                pickinfo.setPickuserProp(liststaff.get(i).getStaffProp());
                flag=1;
            }
        }
        if(flag==1){
            try{
                pickinfo.setUserId(Integer.parseInt(userid));
                pickinfo.setPickpetSex(petsex);
                pickinfo.setPickpetType(petype);
                pickinfo.setPickpetState(petstatus);
                pickinfo.setPickpetImg(petimageUrl);
                pickinfo.setPickpetTime(picktime);
                pickinfo.setPickpetPlace(pickplace);
                pickinfo.setPickpetDetail(pickdetail);
                pickinfo.setPickpubTime(pubpicktime);
                pickupInfoRepository.save(pickinfo);
                map.put("success",true);
                map.put("data","发布成功!");
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        if(flag==0){
            map.put("notreg",true);
            map.put("data", "该用户未注册");
        }
        return map;
    }

    @RequestMapping(value = "/pickpublishadmin", method = RequestMethod.POST)
    public Map<String, Object> pickpublishadmin(@RequestParam("userid")String userid,
                                                @RequestParam("petsex")String petsex,
                                                @RequestParam("petype")String petype,
                                                @RequestParam("petstatus")String petstatus,
                                                @RequestParam("petimg") String petimageUrl,
                                                @RequestParam("picktime")Date picktime,
                                                @RequestParam("pickplace") String pickplace,
                                                @RequestParam("pickdetail") String pickdetail,
                                                @RequestParam("pubpicktime") Date pubpicktime,
                                                Pickupinfo pickinfo) {
        Map<String, Object> map = new HashMap<>();
        List<Admin> listadmin=adminRepository.findAll();
        int flag=0;
        for(int i=0;i<listadmin.size();i++){
            int m=listadmin.get(i).getAdminId();
            String str=String.valueOf(m);
            if(str.equals(userid)){
                pickinfo.setPickpetuserName(listadmin.get(i).getAdminName());
                pickinfo.setPickpetuserEmail(listadmin.get(i).getAdminEmail());
                pickinfo.setPickpetuserTel(listadmin.get(i).getAdminTel());
                pickinfo.setPickuserProp(listadmin.get(i).getAdminProp());
                flag=1;
            }
        }
        if(flag==1){
            try{
                pickinfo.setUserId(Integer.parseInt(userid));
                pickinfo.setPickpetSex(petsex);
                pickinfo.setPickpetType(petype);
                pickinfo.setPickpetState(petstatus);
                pickinfo.setPickpetImg(petimageUrl);
                pickinfo.setPickpetTime(picktime);
                pickinfo.setPickpetPlace(pickplace);
                pickinfo.setPickpetDetail(pickdetail);
                pickinfo.setPickpubTime(pubpicktime);
                pickupInfoRepository.save(pickinfo);
                map.put("success",true);
                map.put("data","发布成功!");
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        if(flag==0){
            map.put("notreg",true);
            map.put("data", "该用户未注册");
        }
        return map;
    }

    /*初始化寄养批复*/
    @RequestMapping(value = "/getpickinfo", method = RequestMethod.GET)
    public Map<String, Object> getloseinfo() {
        Map<String, Object> map = new HashMap<>();
        List<Pickupinfo> listpick=pickupInfoRepository.findAll();
        map.put("getinfo",true);
        map.put("info", listpick);
        return map;
    }

    /*删除发布的宠物拾到信息*/
    @RequestMapping(value = "/deletepickinfo", method = RequestMethod.DELETE)
    public Map<String, Object> deleteadopt(@RequestParam("id") int id) {
        Map<String, Object> map = new HashMap<>();
        List<Pickupinfo> listpick=pickupInfoRepository.findAll();
        List<Pickupinfo> information=new ArrayList<>();
        int flag=0;
        for(int i=0;i<listpick.size();i++){
            if(listpick.get(i).getId()==id){
                pickupInfoRepository.delete(id);
                flag=1;
            }
        }
        if(flag==1){
            map.put("delete",true);
            map.put("data","删除成功.");
        }
        return map;
    }

    @RequestMapping(value = "/getpickById", method = RequestMethod.GET)
    public Map<String, Object> getpickById(@RequestParam("index")int info) {
        Map<String, Object> map = new HashMap<>();
        List<Pickupinfo> list=pickupInfoRepository.findAll();
        List<Pickupinfo> information=new ArrayList<>();
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
    @RequestMapping(value = "/updatepickinfo", method = RequestMethod.POST)
    public Map<String, Object> updatepickinfo(@RequestParam("id")int id,
                                              @RequestParam("userid")int userid,
                                              @RequestParam("petsex")String petsex,
                                              @RequestParam("petype")String petype,
                                              @RequestParam("petimg")String petimg,
                                              @RequestParam("username")String username,
                                              @RequestParam("usertel")String usertel,
                                              @RequestParam("useremail")String useremail,
                                              @RequestParam("picktime")Date picktime,
                                              @RequestParam("pickplace")String pickplace,
                                              @RequestParam("petstate")String petstate,
                                              @RequestParam("pickdetail")String pickdetail,
                                              @RequestParam("userprop")String userprop,
                                              @RequestParam("pubtime")Date pubtime,
                                              Pickupinfo pickinfo) {
        Map<String, Object> map = new HashMap<>();
        List<Pickupinfo> listinfo=pickupInfoRepository.findAll();
        List<User> listuser=userRepository.findAll();
        List<Adoptinfo> listadopt=adoptInfoRepository.findAll();
        List<Loseinfo> listlose=loseInfoRepository.findAll();
        List<Abandonpet> listabandon =abandonpetRepository.findAll();
        List<Fosterpetinfo> listfoster =fosterpetinfoRepository.findAll();
        List<Vaccin> listvaccin =vaccinRepository.findAll();
        List<Acceptpet> listaccept=acceptRepository.findAll();
        List<Tipinfo> listip =tipinfoRepository.findAll();
        int flag=0;
        int flag1=0;
        int flag2=0;
        int flag3=0;
        int m=0;

        for (int i = 0; i < listinfo.size(); i++) {
            if (listinfo.get(i).getId()==id) {
                listinfo.get(i).setUserId(userid);
                listinfo.get(i).setPickpetSex(petsex);
                listinfo.get(i).setPickpetType(petype);
                listinfo.get(i).setPickpetImg(petimg);
                listinfo.get(i).setPickpetuserName(username);
                listinfo.get(i).setPickpetuserTel(usertel);
                listinfo.get(i).setPickpetuserEmail(useremail);
                listinfo.get(i).setPickpetTime(picktime);
                listinfo.get(i).setPickpetPlace(pickplace);
                listinfo.get(i).setPickpetState(petstate);
                listinfo.get(i).setPickpetDetail(pickdetail);
                listinfo.get(i).setPickuserProp(userprop);
                listinfo.get(i).setPickpubTime(pubtime);
                pickupInfoRepository.saveAndFlush(listinfo.get(i));
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
            map.put("data","发布信息更新成功");
        }
        return map;
    }


    /*根据用户输入的关键字查找满足条件的值*/
    @RequestMapping(value = "/getpickBykeywords", method = RequestMethod.GET)
    public Map<String, Object> getpickBykeywords(@RequestParam("info")String info) {
        Map<String, Object> map = new HashMap<>();
        List<Pickupinfo> pick=pickupInfoRepository.findAll();
        List<Pickupinfo> information=new ArrayList<>();
        int Flag=0;
        for (int i = 0; i < pick.size(); i++) {
            int m=pick.get(i).getId();
            String str=String.valueOf(m);

            int m1=pick.get(i).getUserId();
            String str1=String.valueOf(m1);
            if(str.equals(info)){
                information.add(pick.get(i));
                Flag=1;
            }
            else if (str1.equals(info)) {
                information.add(pick.get(i));
                Flag=1;
            }
            else if (pick.get(i).getPickpetSex().equals(info)) {
                information.add(pick.get(i));
                Flag=1;
            }
            else if (pick.get(i).getPickpetType().equals(info)) {
                information.add(pick.get(i));
                Flag=1;
            }
            else if (pick.get(i).getPickpetuserName().equals(info)) {
                information.add(pick.get(i));
                Flag=1;
            }
            else if(pick.get(i).getPickpetuserTel().equals(info)){
                information.add(pick.get(i));
                Flag=1;
            }
            else if(pick.get(i).getPickpetuserEmail().equals(info)){
                information.add(pick.get(i));
                Flag=1;
            }
            else if(pick.get(i).getPickpetPlace().equals(info)){
                information.add(pick.get(i));
                Flag=1;
            }
            else if(pick.get(i).getPickpubTime().equals(info)){
                information.add(pick.get(i));
                Flag=1;
            }
            else if(pick.get(i).getPickpetState().equals(info)){
                information.add(pick.get(i));
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
