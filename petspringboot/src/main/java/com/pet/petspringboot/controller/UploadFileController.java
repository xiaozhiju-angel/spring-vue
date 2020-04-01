package com.pet.petspringboot.controller;

import com.pet.petspringboot.model.entity.User;
import com.pet.petspringboot.repository.StaffRepository;
import com.pet.petspringboot.repository.UserRepository;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/upload")
@CrossOrigin
public class UploadFileController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StaffRepository staffRepository;
    @Value("${prop.upload-folder}")
    private String UPLOAD_FOLDER;

    @Value("${prop.upload-video}")
    private String UPLOAD_VIDEO;

    private Logger logger = LoggerFactory.getLogger(UploadFileController.class);


    @PostMapping("/singlefile")
    public Map<String,Object> singleFileUpload(@RequestParam(value = "file", required = false)MultipartFile file,
                                               @RequestParam("username")String userName,
                                               @RequestParam("usersex")String userSex,
                                               @RequestParam("useridcard")String userIdcard,
                                               @RequestParam("usertel")String userTel,
                                               @RequestParam("useremail")String userEmail,
                                               @RequestParam("userdoor")String userDoor,
                                               @RequestParam("useristenant")String useristenant,
                                               @RequestParam("userloginame")String userLoginame,
                                               @RequestParam("userpass")String userPass,
                                               @RequestParam("userprop")String userProp,
                                               @RequestParam("useregtime") Date userRegtime,

                                               HttpServletRequest request) {
        /*logger.debug("file", JSON.toJSONString(file, true));*/
        Map<String, Object> map = new HashMap<>();
        if (Objects.isNull(file) || file.isEmpty()) {
            logger.error("文件为空");
        }
        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOAD_VIDEO + file.getOriginalFilename());
            if (!Files.isWritable(path)) {
                Files.createDirectories(Paths.get(UPLOAD_VIDEO));
            }
            Files.write(path, bytes);
            logger.debug("文件写入成功...");
            //获取文件类型，以最后一个`.`为标识
            String originalFileName = file.getOriginalFilename();
            String type = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
            System.out.println("文件类型：" + type);
            //获取文件名称（不包含格式）
            String name = originalFileName.substring(0, originalFileName.lastIndexOf("."));
            String fileName =name + "." + type;
            System.out.println("新文件名称：" + fileName);
          /* String pic_path = request.getSession().getServletContext().getRealPath("file");
            System.out.println(pic_path);
             //获取的图片上传路径
            String requestURL = request.getRequestURL().toString();
            System.out.println("-----------"+requestURL);*/
            map.put("filename",fileName);
            map.put("filepath",path);
            return map;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("上传失败");
            e.printStackTrace();
            return  map;
        }
    }

   /* @RequestMapping("/uploadimg")
    public Map<String,Object> upload(@RequestParam("file") MultipartFile file,
                                     HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        //获取文件在服务器的储存位置
        String path = request.getSession().getServletContext().getRealPath("/uploadimg");
        System.out.println(path);
        File filePath = new File(path);
        System.out.println("文件的保存路径：" + path);
        if (!filePath.exists() && !filePath.isDirectory()) {
            System.out.println("目录不存在，创建目录:" + filePath);
            filePath.mkdir();
        }

        //获取原始文件名称(包含格式)
        String originalFileName = file.getOriginalFilename();
        System.out.println("原始文件名称：" + originalFileName);

        //获取文件类型，以最后一个`.`为标识
        String type = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
        System.out.println("文件类型：" + type);
        //获取文件名称（不包含格式）
        String name = originalFileName.substring(0, originalFileName.lastIndexOf("."));

        //设置文件新名称: 当前时间+文件名称（不包含格式）
        String fileName =name + "." + type;
        System.out.println("新文件名称：" + fileName);

        //在指定路径下创建一个文件
        File targetFile = new File(path, fileName);
        System.out.println(targetFile);

        //将文件保存到服务器指定位置
        try {
            file.transferTo(targetFile);
            System.out.println(targetFile);
            System.out.println("上传成功");
            //将文件在服务器的存储路径返回
            map.put("filename","/upload/"+fileName);
            map.put("filepath",path);
            return map;
        } catch (IOException e) {
            System.out.println("上传失败");
            e.printStackTrace();
            return  map;
        }
    }*/

    @RequestMapping("/uploadimg")
    public Map<String,Object> upload(@RequestParam("file") MultipartFile file,
                       HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        //获取文件在服务器的储存位置
       /* String path = request.getSession().getServletContext().getRealPath("/testupload");*/
        String path=UPLOAD_FOLDER;
        System.out.println(path);
        File filePath = new File(path);
        System.out.println("文件的保存路径：" + path);
        if (!filePath.exists() && !filePath.isDirectory()) {
            System.out.println("目录不存在，创建目录:" + filePath);
            filePath.mkdir();
        }
        //获取原始文件名称(包含格式)
        String originalFileName = file.getOriginalFilename();
        System.out.println("原始文件名称：" + originalFileName);

        //获取文件类型，以最后一个`.`为标识
        String type = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
        System.out.println("文件类型：" + type);
        //获取文件名称（不包含格式）
        String name = originalFileName.substring(0, originalFileName.lastIndexOf("."));

        String fileName =name + "." + type;
        System.out.println("新文件名称：" + fileName);

        //在指定路径下创建一个文件
        File targetFile = new File(path, fileName);
        System.out.println(targetFile);

        //将文件保存到服务器指定位置
        try {
            file.transferTo(targetFile);
            System.out.println(targetFile);
            System.out.println("上传成功");
            //将文件在服务器的存储路径返回
            map.put("filename",fileName);
            map.put("filepath",path);
            return map;
        } catch (IOException e) {
            System.out.println("上传失败");
            e.printStackTrace();
            return  map;
        }
    }
}
