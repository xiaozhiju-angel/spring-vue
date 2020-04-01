package com.pet.petspringboot.controller;

import com.pet.petspringboot.model.entity.Fosterpet;
import com.pet.petspringboot.model.entity.Fosterpetinfo;
import com.pet.petspringboot.model.entity.Pet;
import com.pet.petspringboot.model.entity.User;
import com.pet.petspringboot.repository.*;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@RestController
@RequestMapping("/uploadtest")
@CrossOrigin
public class uploadFile {
    @Value("${prop.upload-video}")
    private String UPLOAD_VIDEO;
    private Logger logger = LoggerFactory.getLogger(uploadFile.class);
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
    @PostMapping("/test")
    public Object singleFileUpload(@RequestParam(value = "file", required = false)MultipartFile file,
                                   @RequestParam("fosterpetid") int fosterpetid,
                                   @RequestParam("fosteruserid") int fosteruserid,
                                   @RequestParam("fosterpetype") String fosterpetype,
                                   @RequestParam("fosterbegintime") Date fosterbegintime,
                                   @RequestParam("fosterendtime") Date fosterendtime,
                                   @RequestParam("fosterpricescale") String fosterpricescale,
                                   @RequestParam("fosterpayprice") String fosterpayprice,
                                   @RequestParam("fosterpayornot") String fosterpayornot,
                                   @RequestParam("fostertime") Date fostertime,
                                   Fosterpetinfo fosterpetinfo,
                                   HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        List<Fosterpet> listfoster=fosterRepository.findAll();
        List<Pet> listpet=petRepository.findAll();
        List<User> listuser=userRepository.findAll();
        List<Fosterpetinfo> listfosterinfo=fosterpetinfoRepository.findAll();
        List<Fosterpet> infofoster=new ArrayList<>();
        List<Fosterpetinfo> infomation=new ArrayList<>();
        int flag1=0;
        int flag2=0;
        if (Objects.isNull(file) || file.isEmpty()) {
            logger.error("文件为空");
            return "文件为空，请重新上传";
        }
        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOAD_VIDEO + file.getOriginalFilename());
            if (!Files.isWritable(path)) {
                Files.createDirectories(Paths.get(UPLOAD_VIDEO));
            }
            Files.write(path, bytes);
            for(int i=0;i<listuser.size();i++ ){
                if(listuser.get(i).getUserId()==fosteruserid){
                    fosterpetinfo.setFosterpetuserName(listuser.get(i).getUserName());
                    fosterpetinfo.setFosterpetuserSex(listuser.get(i).getUserSex());
                    fosterpetinfo.setFosterpetuserTel(listuser.get(i).getUserTel());
                    fosterpetinfo.setFosterpetuserEmail(listuser.get(i).getUserEmail());
                    flag1=1;
                }
            }
            for(int j=0;j<listpet.size();j++){
                if(listpet.get(j).getPetId()==fosterpetid){
                    fosterpetinfo.setFosterpetName(listpet.get(j).getPetName());
                    fosterpetinfo.setFosterpetSex(listpet.get(j).getPetSex());
                    fosterpetinfo.setFosterpetImg(listpet.get(j).getPetImg());
                    fosterpetinfo.setFosterpetType(listpet.get(j).getPetType());
                }
            }
            logger.debug("文件写入成功...");
            return "文件上传成功";

        } catch (IOException e) {
            e.printStackTrace();
            return "后端异常...";
        }
    }
}
