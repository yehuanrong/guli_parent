package com.yhr.eduservice.controller;


import com.yhr.commonutils.R;
import com.yhr.eduservice.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-06-14
 */
@RestController
@RequestMapping("/eduservice/subject")
@CrossOrigin
public class EduSubjectController {

    @Autowired
    private EduSubjectService SubjectService;

    @PostMapping("addSubject")
    public R addSubject(MultipartFile file){

        //添加课程，将上传的文件内容读取出来
        SubjectService.saveSubject(file);

        return R.ok();
    }

}

