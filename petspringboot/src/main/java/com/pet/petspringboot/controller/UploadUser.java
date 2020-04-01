package com.pet.petspringboot.controller;
import com.pet.petspringboot.model.entity.Staff;
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
import java.util.*;

@RestController
@RequestMapping("/uploaduser")
@CrossOrigin
public class UploadUser {

    @RequestMapping("/upload")
    public String upLoadImg(@RequestParam(value = "file", required = false)MultipartFile file, HttpServletRequest request) throws IOException {
        String path = request.getSession().getServletContext().getRealPath("upload");
        System.out.println(path);
        String fileName = file.getOriginalFilename();// 获取到上传文件的名字
        // file.getSize();获取到上传文件的大小
        File dir = new File(path, fileName);
        System.out.println(dir);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        // MultipartFile自带的解析方法
        file.transferTo(dir);
        String directory = dir.getCanonicalPath();
        System.out.println("directory:" + directory);
        return "/upload" + "/" + fileName;
    }
}
