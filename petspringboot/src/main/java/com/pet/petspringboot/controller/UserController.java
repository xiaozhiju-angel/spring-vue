package com.pet.petspringboot.controller;

import com.pet.petspringboot.config.DateConverterConfig;
import com.pet.petspringboot.model.entity.*;
import com.pet.petspringboot.repository.*;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.util.DigestUtils;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController{
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
    private  VaccinRepository vaccinRepository;
    @Autowired
    private FosterpetinfoRepository fosterpetinfoRepository;
    @Autowired
    private AdoptInfoRepository adoptInfoRepository;

    @Autowired
    private  TipinfoRepository tipinfoRepository;

    @Autowired
    private TipRepository tipRepository;


    MD5 md=new MD5();

    /*注册业主信息*/
    @RequestMapping(value = "/saveuser", method = RequestMethod.POST)
    public Map<String, Object> saveuser(@RequestParam("username")String userName,
                                        @RequestParam("usersex")String userSex,
                                        @RequestParam("useridcard")String userIdcard,
                                        @RequestParam("usertel")String userTel,
                                        @RequestParam("useremail")String userEmail,
                                        @RequestParam("userdoor")String userDoor,
                                        @RequestParam("useristenant")String useristenant,
                                        @RequestParam("userloginame")String userLoginame,
                                        @RequestParam("userpass")String userPass,
                                        @RequestParam("userprop")String userProp,
                                        @RequestParam("userimg")String userImg,
                                        @RequestParam("useregtime") Date userRegtime,
                                        User user) {
        Map<String, Object> map = new HashMap<>();
        List<Admin> listadmin = adminRepository.findAll();
        List<Staff> liststaff = staffRepository.findAll();
        List<User> listuser = userRepository.findAll();
        int flag=0;
        for (int i = 0; i < listuser.size(); i++) {
            if (listuser.get(i).getUserLoginame().equals(userLoginame)) {
                map.put("loginamerepeat",true);
                map.put("repeat", "用户名重复");
                flag=1;
            }
            else if (listuser.get(i).getUserIdcard().equals(userIdcard)) {
                map.put("useridcard",true);
                map.put("idcard", "该身份证已注册");
                flag=1;
            }
        }
        for (int i = 0; i < liststaff.size(); i++) {
            if (liststaff.get(i).getStaffIdcard().equals(userIdcard)) {
                map.put("staffidcard",true);
                map.put("idcard", "该身份证已注册");
                flag=1;
            }
        }
        for (int i = 0; i < listadmin.size(); i++) {
            if (listadmin.get(i).getAdminIdcard().equals(userIdcard)) {
                map.put("adminidcard",true);
                map.put("idcard", "该身份证已注册");
                flag=1;
            }
        }
        if (flag==0) {
            try{
                user.setUserName(userName);
                user.setUserSex(userSex);
                user.setUserIdcard(userIdcard);
                user.setUserTel(userTel);
                user.setUserEmail(userEmail);
                user.setUserDoor(userDoor);
                user.setUserisTenant(useristenant);
                user.setUserLoginame(userLoginame);
                user.setUserPass(userPass);
                user.setUserProp(userProp);
                user.setUserImg(userImg);
                user.setUserRegtime(userRegtime);
                userRepository.save(user);
                map.put("save", "添加成功");
                map.put("success",true);

            }catch (Exception e){
                e.printStackTrace();

            }
        }
        return map;
    }


    @RequestMapping(value = "/deleteUser", method = RequestMethod.DELETE)
    public Map<String, Object> deleteUserByUserId(@RequestParam("id") String id) {
        Map<String, Object> map = new HashMap<>();
        List<User> listuser=userRepository.findAll();
        int flag=0;
        for(int i=0;i<listuser.size();i++){
            int m=listuser.get(i).getUserId();
            String str=String.valueOf(m);
            if(str.equals(id)){
                userRepository.delete(Integer.parseInt(id));
                flag=1;
            }
        }
        if(flag==1){
            map.put("delete",true);
            map.put("back","删除成功.");
            map.put("data",listuser);
        }
        return map;
    }

 /*用户查询自己宠物*/
    @RequestMapping(value = "/getselfpetInfoById", method = RequestMethod.GET)
    public Map<String, Object> getselfpetInfoById(@RequestParam("info")int info) {
        Map<String, Object> map = new HashMap<>();
        List<Pet> listpet = petRepository.findAll();
        List<Pet> pet = new ArrayList<>();
        int flag = 0;
        for (int i = 0; i < listpet.size(); i++) {
            if (listpet.get(i).getPetisuserApply().equals("否")&&listpet.get(i).getPetuserId()==info) {
                pet.add(listpet.get(i));
                flag = 1;
            }
        }
        if (flag == 1) {
            map.put("getinfo", true);
            map.put("info", pet);
        }
        return map;
    }


    @RequestMapping(value = "/getselffosterinfo", method = RequestMethod.GET)
    public Map<String, Object> getselffosterinfo(@RequestParam("info")int info) {
        Map<String, Object> map = new HashMap<>();
        List<Fosterpetinfo> listfoster=fosterpetinfoRepository.findAll();
        List<Fosterpetinfo> foster=new ArrayList<>();
        for(int i=0;i<listfoster.size();i++){
            if(listfoster.get(i).getFosteruserId()==info){
                foster.add(listfoster.get(i));
            }
        }
        map.put("getinfo",true);
        map.put("info", foster);
        return map;
    }

    /*初始化寄养批复*/
    @RequestMapping(value = "/getselfadoptinfo", method = RequestMethod.GET)
    public Map<String, Object> getselfadoptinfo(@RequestParam("info")int info) {
        Map<String, Object> map = new HashMap<>();
        List<Adoptinfo> listadopt=adoptInfoRepository.findAll();
        List<Adoptinfo> adopt=new ArrayList<>();
        int flag=0;
        for(int i=0;i<listadopt.size();i++){
            if(listadopt.get(i).getPreuserId()==info||listadopt.get(i).getNowuserId()==info){
                adopt.add(listadopt.get(i));
                flag=1;
            }
        }
        if(flag==1){
            map.put("getinfo",true);
            map.put("info", adopt);
        }
        return map;
    }


    @RequestMapping(value = "/vaccinInfo", method = RequestMethod.GET)
    public Map<String, Object> vaccinInfo(@RequestParam("info")int info) {
        FosterController fosterController=new FosterController();
        String s=fosterController.GetNowDate();
        Map<String, Object> map = new HashMap<>();
        List<Vaccin> listvaccin=vaccinRepository.findAll();
        List<Vaccin> vaccin=new ArrayList<>();
        int flag=0;
        for(int i=0;i<listvaccin.size();i++){
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            String str=sdf.format(listvaccin.get(i).getPetnextVaccin());
            int res=s.compareTo(str);
            if(res>0&&listvaccin.get(i).getUserId()==info){
                vaccin.add(listvaccin.get(i));
                flag=1;
            }
        }
        if(flag==1){
            map.put("getinfo",true);
            map.put("info", vaccin);
        }
        return map;
    }


    @RequestMapping(value = "/getselfpetInfo", method = RequestMethod.GET)
    public Map<String, Object> getselfpetInfo(@RequestParam("info")int info) {
        Map<String, Object> map = new HashMap<>();
        List<Fosterpet> listfoster=fosterRepository.findAll();
        List<Fosterpet> foster=new ArrayList<>();
        int flag=0;
        for(int i=0;i<listfoster.size();i++){
            if(listfoster.get(i).getFosterpetisuserApply().equals("否")&&listfoster.get(i).getFosteruserId()==info){
                foster.add(listfoster.get(i));
                flag=1;
            }
        }
        if(flag==1){
            map.put("getinfo",true);
            map.put("info", foster);
        }
        return map;
    }


    /*根据用户输入的关键字查找满足条件的值*/
    @RequestMapping(value = "/queryfosterInfoBykeywords", method = RequestMethod.GET)
    public Map<String, Object> queryfosterInfoBykeywords(@RequestParam("info")String info,
                                                         @RequestParam("userid")int userid) {
        Map<String, Object> map = new HashMap<>();
        List<Fosterpet> listfoster=fosterRepository.findAll();
        List<Fosterpet> information=new ArrayList<>();
        int flag=0;
        int flag1=0;
        for(int i=0;i<listfoster.size();i++){
            if(listfoster.get(i).getFosterpetisuserApply().equals("否")&&listfoster.get(i).getFosteruserId()==userid){
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
    @RequestMapping(value = "/getadoptBykeywords", method = RequestMethod.GET)
    public Map<String, Object> getadoptBykeywords(@RequestParam("info")String info,
                                                  @RequestParam("userid")int userid) {
        Map<String, Object> map = new HashMap<>();
        List<Adoptinfo> adopt=adoptInfoRepository.findAll();
        List<Adoptinfo> information=new ArrayList<>();
        int Flag=0;
        for (int i = 0; i < adopt.size(); i++) {
            if(adopt.get(i).getPreuserId()==userid||adopt.get(i).getNowuserId()==userid){
                int m=adopt.get(i).getId();
                String str=String.valueOf(m);

                int m1=adopt.get(i).getPetId();
                String str1=String.valueOf(m1);

                int m2=adopt.get(i).getPreuserId();
                String str2=String.valueOf(m2);

                int m3=adopt.get(i).getNowuserId();
                String str3=String.valueOf(m3);

                if(str.equals(info)){
                    information.add(adopt.get(i));
                    Flag=1;
                }
                else if (str1.equals(info)) {
                    information.add(adopt.get(i));
                    Flag=1;
                }
                else if (str2.equals(info)) {
                    information.add(adopt.get(i));
                    Flag=1;
                }
                else if (str3.equals(info)) {
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


    @RequestMapping(value = "/tipinfo", method = RequestMethod.POST)
    public Map<String, Object> losepublish(@RequestParam("userid")int userid,
                                           @RequestParam("tipdoor")String tipdoor,
                                           @RequestParam("time") Date time,
                                           @RequestParam("place") String place,
                                           @RequestParam("detail")String detail,
                                           @RequestParam("tiptime")Date tiptime,
                                           Tipinfo tip) {

        Map<String, Object> map = new HashMap<>();
        List<User> listuser=userRepository.findAll();
        List<Tip> info=tipRepository.findAll();
        List<Tipinfo> listinfo=tipinfoRepository.findAll();
        int flag=0;
        int flag1=0;
        int flag2=0;
        int flag3=0;
        for(int i=0;i<listuser.size();i++){
            if(listuser.get(i).getUserId()==userid){
                tip.setUserName(listuser.get(i).getUserName());
                tip.setUserEmail(listuser.get(i).getUserEmail());
                tip.setUserTel(listuser.get(i).getUserTel());
                flag1=1;
            }
        }
        if(flag1==1&&tipdoor==""){
            try{
                tip.setTipDoor("无");
                tip.setUserId(userid);
                tip.setTime(time);
                tip.setPlace(place);
                tip.setDetail(detail);
                tip.setTipTime(tiptime);
                tipinfoRepository.save(tip);
                flag=1;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        if(flag1==1&&tipdoor!=""){
            try{
                tip.setTipDoor(tipdoor);
                tip.setUserId(userid);
                tip.setTime(time);
                tip.setPlace(place);
                tip.setDetail(detail);
                tip.setTipTime(tiptime);
                tipinfoRepository.save(tip);
                flag=1;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        if(flag==1){
            map.put("success",true);
            map.put("data","投诉成功,十分感谢您的反馈！");
        }
        if(flag1==0){
            map.put("notreg",true);
            map.put("data", "用户未注册");
        }
        return map;
    }


    /*初始化寄养批复*/
    @RequestMapping(value = "/getipinfo", method = RequestMethod.GET)
    public Map<String, Object> getipinfo() {
        Map<String, Object> map = new HashMap<>();
        List<Tipinfo> listtip=tipinfoRepository.findAll();
        map.put("getinfo",true);
        map.put("info", listtip);
        return map;
    }



    /*根据用户输入的关键字查找满足条件的值*/
    @RequestMapping(value = "/querytip", method = RequestMethod.GET)
    public Map<String, Object> querytip(@RequestParam("info") String info) {
        Map<String, Object> map = new HashMap<>();
        List<Tipinfo> listip = tipinfoRepository.findAll();
        List<Tipinfo> information = new ArrayList<>();
        int flag = 0;
        for (int i = 0; i < listip.size(); i++) {
                int m1 = listip.get(i).getTipId();
                String str1 = String.valueOf(m1);
                int m2 = listip.get(i).getUserId();
                String str2 = String.valueOf(m2);
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                String str3=sdf.format(listip.get(i).getTipTime());
                if (str1.equals(info)) {
                    information.add(listip.get(i));
                    flag = 1;
                } else if (str2.equals(info)) {
                    information.add(listip.get(i));
                    flag = 1;
                } else if (str3.equals(info)) {
                    information.add(listip.get(i));
                    flag = 1;
                }
                else if (listip.get(i).getUserName().equals(info)) {
                    information.add(listip.get(i));
                    flag = 1;
                } else if (listip.get(i).getUserTel().equals(info)) {
                    information.add(listip.get(i));
                    flag = 1;
                } else if (listip.get(i).getUserEmail().equals(info)) {
                    information.add(listip.get(i));
                    flag = 1;
                }
                else if (listip.get(i).getPlace().equals(info)) {
                    information.add(listip.get(i));
                    flag = 1;
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



    /*根据员工id获取员工信息，注意类型是string还是int*/
    @RequestMapping(value = "/getipinfoById", method = RequestMethod.GET)
    public Map<String, Object> getipinfoById(@RequestParam("index")int info) {
        Map<String, Object> map = new HashMap<>();
        List<Tipinfo> listip=tipinfoRepository.findAll();
        List<Tipinfo> information=new ArrayList<>();
        int Flag=0;
        for (int i = 0; i < listip.size(); i++) {
            if(listip.get(i).getTipId()==info){
                information.add(listip.get(i));
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
    @RequestMapping(value = "/deleteTip", method = RequestMethod.DELETE)
    public Map<String, Object> deleteTip(@RequestParam("id") int id) {
        Map<String, Object> map = new HashMap<>();
        List<Tipinfo> listip=tipinfoRepository.findAll();
        int flag=0;
        for(int i=0;i<listip.size();i++){
            if(listip.get(i).getTipId()==id){
                tipinfoRepository.delete(id);
                flag=1;
            }
        }

        if(flag==1){
            map.put("delete",true);
            map.put("back","删除成功.");
        }
        return map;
    }



}
