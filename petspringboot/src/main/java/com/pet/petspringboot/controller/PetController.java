package com.pet.petspringboot.controller;

import com.pet.petspringboot.model.entity.*;
import com.pet.petspringboot.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/pet")
@CrossOrigin
public class PetController {
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
    private LoseInfoRepository loseInfoRepository;

    @Autowired
    private AdoptPetRepository adoptPetRepository;

    @Autowired
    private PublishAdoptRepository publishAdoptRepository;

    @Autowired
    private PubAdoptInfoRepository pubAdoptInfoRepository;
    @Autowired
    private PickupRepository pickupRepository;

    @Autowired
    private LoseRepository loseRepository;
    @Autowired
    private  FosterpetinfoRepository fosterpetinfoRepository;

    @Autowired
    private VaccinRepository vaccinRepository;

    @Autowired
    private AdoptInfoRepository adoptInfoRepository;
    @Autowired
    private  AbandonpetRepository abandonpetRepository;
    @Autowired
    private AcceptRepository acceptRepository;

    @Autowired
    private TipinfoRepository tipinfoRepository;



    /*写入宠物信息*/
    @RequestMapping(value = "/applyregpet", method = RequestMethod.POST)
    public Map<String, Object> savepet(@RequestParam("petname") String petname,
                                       @RequestParam("petsex") String petsex,
                                       @RequestParam("petuserid") int petuserid,
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
        int flag = 0;
        for (int i = 0; i < listuser.size(); i++) {
           /* int m = listuser.get(i).getUserId();
            String str = String.valueOf(m);*/
            if (listuser.get(i).getUserId()==petuserid) {
                flag = 1;
            }
        }
        if (flag == 1) {
            try {
                pet.setPetName(petname);
                pet.setPetSex(petsex);
                pet.setPetImg(petimg);
                pet.setPetType(petype);
                pet.setPetuserId(petuserid);
                pet.setPetrabidPrevaccin(petprevaccin);
                pet.setPetrabidNextvaccin(petnextvaccin);
                pet.setPetisuserApply(petisuserapply);
                pet.setPetrecodTime(petregtime);
                petRepository.save(pet);
                map.put("applyregpet", "申请成功");
                map.put("success", true);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (flag == 0) {
            map.put("data", "该用户未注册，请先注册.");
        }

        return map;
    }


    /*查询已寄养宠物*/
    @RequestMapping(value = "/getpetInfo", method = RequestMethod.GET)
    public Map<String, Object> getpetInfo() {
        Map<String, Object> map = new HashMap<>();
        List<Pet> listpet = petRepository.findAll();
        List<Pet> info = new ArrayList<>();
        int flag = 0;
        for (int i = 0; i < listpet.size(); i++) {
            if (listpet.get(i).getPetisuserApply().equals("否")) {
                info.add(listpet.get(i));
                flag = 1;
            }
        }
        if (flag == 1) {
            map.put("getinfo", true);
            map.put("info", info);
        }
        return map;
    }

    /*写入宠物寄养申请信息*/
    @RequestMapping(value = "/fosterpet", method = RequestMethod.POST)
    public Map<String, Object> fosterpet(@RequestParam("petid") String petid,
                                         @RequestParam("petype") String petype,
                                         @RequestParam("petuserid") int petuserid,
                                         @RequestParam("begintime") Date begintime,
                                         @RequestParam("endtime") Date endtime,
                                         @RequestParam("pricescale") String pricescale,
                                         @RequestParam("fosterisuserapply") String fosterisuserapply,
                                         @RequestParam("fostertime") Date fostertime,
                                         Fosterpet fosterpet) {
        Map<String, Object> map = new HashMap<>();
        List<Fosterpet> listfoster = fosterRepository.findAll();
        List<Pet> listpet = petRepository.findAll();
        int flag = 0;
        int flag1=0;
        int flag2=0;
        for (int i = 0; i < listpet.size(); i++) {
            int m1 = listpet.get(i).getPetId();
            String strm1 = String.valueOf(m1);
            if (strm1.equals(petid) && listpet.get(i).getPetuserId()==petuserid&&listpet.get(i).getPetType().equals(petype)) {
                flag = 1;
            }
        }
        if(flag==1){
            for(int i=0;i<listfoster.size();i++){
                int n=listfoster.get(i).getFosterpetId();
                String str = String.valueOf(n);
                if(str.equals(petid)&&listfoster.get(i).getFosterpetisuserApply().equals("是")){
                    flag1=1;
                }
            }
        }

        if(flag==1){
            for(int i=0;i<listfoster.size();i++){
                int n=listfoster.get(i).getFosterpetId();
                String str = String.valueOf(n);
                if(str.equals(petid)&&listfoster.get(i).getFosterpetisuserApply().equals("否")){
                    flag2=1;
                }
            }
        }

        if (flag == 1&&flag1==0&&flag2==0) {
            try {
                fosterpet.setFosterpetId(Integer.parseInt(petid));
                fosterpet.setFosterpetType(petype);
                fosterpet.setFosteruserId(petuserid);
                fosterpet.setFosterpetbeginTime(begintime);
                fosterpet.setFosterpetendTime(endtime);
                fosterpet.setFosterPrice(pricescale);
                fosterpet.setFosterpetisuserApply(fosterisuserapply);
                fosterpet.setFostertime(fostertime);
                fosterRepository.save(fosterpet);
                map.put("fosterpet", "申请成功");
                map.put("success", true);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (flag == 0) {
            map.put("notreg", true);
            map.put("data", "用户未注册或宠物未注册或宠物类型不符.");
        }
        if(flag1==1){
            map.put("repeat", true);
            map.put("data", "您已提交申请,请勿重复提交");
        }
        if(flag2==1){
            map.put("foster", true);
            map.put("data", "您的宠物已在寄养中,请到查看寄养宠物里面查看");
        }

        return map;
    }

    /*根据用户输入的关键字查找满足条件的值*/
    @RequestMapping(value = "/querypetInfoBykeywords", method = RequestMethod.GET)
    public Map<String, Object> querypetInfoBykeywords(@RequestParam("info") String info) {
        Map<String, Object> map = new HashMap<>();
        List<Pet> listpet = petRepository.findAll();
        List<Pet> information = new ArrayList<>();
        int flag = 0;
        int flag1 = 0;
        for (int i = 0; i < listpet.size(); i++) {
            if (listpet.get(i).getPetisuserApply().equals("否")) {
                int m1 = listpet.get(i).getPetId();
                String str1 = String.valueOf(m1);
                int m2 = listpet.get(i).getPetuserId();
                String str2 = String.valueOf(m2);
                if (str1.equals(info)) {
                    information.add(listpet.get(i));
                    flag = 1;
                } else if (str2.equals(info)) {
                    information.add(listpet.get(i));
                    flag = 1;
                } else if (listpet.get(i).getPetType().equals(info)) {
                    information.add(listpet.get(i));
                    flag = 1;
                } else if (listpet.get(i).getPetSex().equals(info)) {
                    information.add(listpet.get(i));
                    flag = 1;
                } else if (listpet.get(i).getPetName().equals(info)) {
                    information.add(listpet.get(i));
                    flag = 1;
                }

            }

        }
        if (flag == 1) {
            map.put("getinfo", true);
            map.put("info", information);
        }
        if (flag == 0) {
            map.put("msg", "无匹配数据");
        }
        return map;
    }

    @RequestMapping(value = "/getpetInfoById", method = RequestMethod.GET)
    public Map<String, Object> getpetInfoById(@RequestParam("index") int info) {
        Map<String, Object> map = new HashMap<>();
        List<Pet> listpet = petRepository.findAll();
        List<Pet> information = new ArrayList<>();
        int Flag = 0;
        for (int i = 0; i < listpet.size(); i++) {
            if (listpet.get(i).getPetId() == info) {
                information.add(listpet.get(i));
                Flag = 1;
            }
        }
        if (Flag == 1) {
            map.put("indexinfo", true);
            map.put("info", information);
        }
        return map;
    }


    /*修改宠物登记信息*/
    @RequestMapping(value = "/updatepetinfo", method = RequestMethod.POST)
    public Map<String, Object> fosterecord(@RequestParam("petid") int petid,
                                           @RequestParam("petname") String petname,
                                           @RequestParam("petsex") String petsex,
                                           @RequestParam("petype") String petype,
                                           @RequestParam("petuserid") int petuserid,
                                           @RequestParam("petimg") String petimg,
                                           @RequestParam("petprevaccin") Date petprevaccin,
                                           @RequestParam("petnextvaccin") Date petnextvaccin,
                                           @RequestParam("petregtime") Date petregtime,
                                           Vaccin vaccin,
                                           Pet pet) {
        Map<String, Object> map = new HashMap<>();
        List<Pet> listpet = petRepository.findAll();
        List<User> listuser = userRepository.findAll();
        List<Vaccin> listvaccin =vaccinRepository.findAll();
        List<Loseinfo> listlose = loseInfoRepository.findAll();
        List<Adoptinfo> listadopt =adoptInfoRepository.findAll();
        List<Abandonpet> listabandon =abandonpetRepository.findAll();
        List<Fosterpetinfo> listfoster =fosterpetinfoRepository.findAll();
        List<Fosterpet> listfpet =fosterRepository.findAll();
        int flag = 0;
        int flag1 = 0;
        int flag2 = 0;
        int flag3 = 0;
        int m = 0;
        for (int i = 0; i < listuser.size(); i++){
            if(listuser.get(i).getUserId()==petuserid){
                vaccin.setUserName(listuser.get(i).getUserName());
                vaccin.setUserSex(listuser.get(i).getUserSex());
                vaccin.setUserTel(listuser.get(i).getUserTel());
                vaccin.setUserEmail(listuser.get(i).getUserEmail());
                flag1=1;
            }
        }
        for (int i = 0; i < listpet.size(); i++) {
            if (listpet.get(i).getPetId() == petid) {
                listpet.get(i).setPetName(petname);
                listpet.get(i).setPetSex(petsex);
                listpet.get(i).setPetType(petype);
                listpet.get(i).setPetuserId(petuserid);
                listpet.get(i).setPetImg(petimg);
                listpet.get(i).setPetrabidPrevaccin(petprevaccin);
                listpet.get(i).setPetrabidNextvaccin(petnextvaccin);
                listpet.get(i).setPetrecodTime(petregtime);
                petRepository.saveAndFlush(listpet.get(i));
                flag = 1;
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
            for (int i = 0; i < listlose.size(); i++) {
                if (listlose.get(i).getPetId() == petid) {
                    listlose.get(i).setLosepetName(petname);
                    listlose.get(i).setLosepetSex(petsex);
                    listlose.get(i).setLosepetType(petype);
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
            for (int i = 0; i < listabandon.size(); i++) {
                if (listabandon.get(i).getPetId() == petid) {
                    listabandon.get(i).setPetName(petname);
                    listabandon.get(i).setPetSex(petsex);
                    listabandon.get(i).setPetType(petype);
                    abandonpetRepository.saveAndFlush( listabandon.get(i));
                }
            }
        }


        if(flag1==1&&flag==1){
            for (int i = 0; i < listvaccin.size(); i++) {
                if (listvaccin.get(i).getPetId() == petid) {
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
        if(flag==1&&flag1==1){
            map.put("update",true);
            map.put("data","更新成功");
        }
        else {
            map.put("msg",true);
            map.put("data","更新失败");
        }
        return  map;
    }

    /*修改宠物登记信息*/
    @RequestMapping(value = "/vaccinrecord", method = RequestMethod.POST)
    public Map<String, Object> vaccinrecord(@RequestParam("petid") int petid,
                                            @RequestParam("petname") String petname,
                                            @RequestParam("petsex") String petsex,
                                            @RequestParam("petype") String petype,
                                            @RequestParam("petuserid") int petuserid,
                                            @RequestParam("petimg") String petimg,
                                            @RequestParam("petprevaccin") Date petprevaccin,
                                            @RequestParam("petnextvaccin") Date petnextvaccin,
                                            @RequestParam("petregtime") Date petregtime,
                                            Vaccin vaccin) {
        Map<String, Object> map = new HashMap<>();
        List<Pet> listpet = petRepository.findAll();
        List<User> listuser = userRepository.findAll();
        List<Vaccin> listvaccin =vaccinRepository.findAll();
        int flag = 0;
        int flag1 = 0;
        int flag2 = 0;
        int flag3 = 0;
        int m = 0;
        for (int i = 0; i < listuser.size(); i++){
            if(listuser.get(i).getUserId()==petuserid){
                vaccin.setUserName(listuser.get(i).getUserName());
                vaccin.setUserSex(listuser.get(i).getUserSex());
                vaccin.setUserTel(listuser.get(i).getUserTel());
                vaccin.setUserEmail(listuser.get(i).getUserEmail());
                flag1=1;
            }
        }
        if(flag1==1){
            for (int i = 0; i < listvaccin.size(); i++){
                if(listvaccin.get(i).getPetId()==petid){
                    listvaccin.get(i).setUserId(petuserid);
                    listvaccin.get(i).setPetName(petname);
                    listvaccin.get(i).setPetSex(petsex);
                    listvaccin.get(i).setPetType(petype);
                    listvaccin.get(i).setPetpreVaccin(petprevaccin);
                    listvaccin.get(i).setPetnextVaccin(petnextvaccin);
                    listvaccin.get(i).setPetRegtime(petregtime);
                    vaccinRepository.saveAndFlush(listvaccin.get(i));
                    flag2=1;
                }
            }
        }
        if(flag1==1&&flag2==0){
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
            map.put("update",true);
            map.put("data","疫苗信息更新成功");
        }
        if(flag3==1) {
            map.put("success",true);
            map.put("data","疫苗信息成功生成");
        }
        return  map;
    }



    /*删除寄养申请信息*/
    @RequestMapping(value = "/deletepetById", method = RequestMethod.DELETE)
    public Map<String, Object> refusefosterapply(@RequestParam("id") int id) {
        Map<String, Object> map = new HashMap<>();
        List<Pet> listpet=petRepository.findAll();
        List<Fosterpet> listfoster=fosterRepository.findAll();
        List<Loseinfo> listlose=loseInfoRepository.findAll();
        List<Abandonpet> listabandon=abandonpetRepository.findAll();
        List<Vaccin> listvaccin=vaccinRepository.findAll();
        int flag=0;
        int flag1=0;
        int flag2=0;
        for(int i=0;i<listfoster.size();i++){
            if(listfoster.get(i).getFosterpetId()==id){
                flag1=1;
            }
        }
        for(int i=0;i<listlose.size();i++){
            if(listlose.get(i).getPetId()==id){
                flag1=1;
            }
        }
        for(int i=0;i<listabandon.size();i++){
            if(listabandon.get(i).getPetId()==id){
                flag1=1;
            }
        }
        if(flag1==0){
            try{
                for(int i=0;i<listpet.size();i++){
                    if(listpet.get(i).getPetId()==id){
                        petRepository.delete(id);
                        flag=1;
                    }
                }
                for(int i=0;i<listvaccin.size();i++){
                    if(listvaccin.get(i).getPetId()==id){
                        vaccinRepository.delete(id);
                        flag2=1;
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }
        if(flag1==1){
            map.put("success",false);
            map.put("msg","该宠物尚有业务在进行,不能删除该宠物信息");
        }
        if(flag==1&&flag2==1){
            map.put("success",true);
            map.put("data","删除成功.");
        }
        if(flag1==1){
            map.put("data","该宠物尚有业务在进行，不能删除");
        }
        return map;
    }

  /*  *//*删除寄养申请信息*//*
    @RequestMapping(value = "/deletepetById", method = RequestMethod.DELETE)
    public Map<String, Object> refusefosterapply(@RequestParam("id") int id) {
        Map<String, Object> map = new HashMap<>();
        List<Pet> listpet=petRepository.findAll();
        List<Fosterpet> listfoster=fosterRepository.findAll();
        List<Loseinfo> listlose=loseInfoRepository.findAll();
        List<Vaccin> listvaccin=vaccinRepository.findAll();
        int flag=0;
        int flag1=0;
        int flag2=0;
        for(int i=0;i<listpet.size();i++){
            if(listpet.get(i).getPetId()==id){
                petRepository.delete(id);
                flag=1;
            }
        }
        for(int i=0;i<listfoster.size();i++){
            if(listfoster.get(i).getFosterpetId()==id){
                fosterRepository.delete(id);
                flag=1;
            }
        }

        for(int i=0;i<listlose.size();i++){
            if(listlose.get(i).getPetId()==id){
                loseInfoRepository.delete(id);
                flag=1;
            }
        }
        for(int i=0;i<listvaccin.size();i++){
            if(listvaccin.get(i).getPetId()==id){
                vaccinRepository.delete(id);
                flag=1;
            }
        }
        if(flag==1){
            map.put("success",true);
            map.put("data","删除成功.");
        }
        return map;
    }*/

}
