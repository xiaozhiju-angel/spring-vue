package com.pet.petspringboot.controller;

import com.pet.petspringboot.model.entity.User;
import com.pet.petspringboot.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/posts")
@CrossOrigin
public class MessageController {
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

    /*查询全部业主信息，用于查询初始化*/
    @RequestMapping(value = "/getUserInfo", method = RequestMethod.GET)
    public Map<String, Object> getAllUserInfo() {
        Map<String, Object> map = new HashMap<>();

        return map;
    }


    @RequestMapping("/getVerifYCode")
    public void getVerifYCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        HttpSession session = request.getSession();
        //String bit4Rand = CommonUtils.get4HibitRandom();
        String bit4Rand = "12345678";//先写死
        session.setAttribute(username, bit4Rand);
       /* logger.error("[存入的验证码和key] key："+username+" bit4Rand:"+bit4Rand);*/
        // TODO:调用下发验证码的邮件
        List<User> listuser = userRepository.findAll();
        for(int i=0;i<listuser.size();i++){

        }
       /* String email = um.getEmail();
        String host = "smtp.qq.com";//服务器
        String subject = "个人项目登陆时验证码";
        String content = "<\n>"+bit4Rand+"<\n>";
        String from = "@qq.com";
        String pwd = "";
        int result = SendEmailUtil.sendEmail(host, from, from, pwd, email, subject, content);

        response.getWriter().print(result);*/
    }

}
