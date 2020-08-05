package com.yhr.eduservice.controller;


import com.yhr.commonutils.R;
import com.yhr.eduservice.bean.subject.OneSubject;
import com.yhr.eduservice.service.EduSubjectService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-06-14
 */
@Api(description = "课程管理")
@RestController
@RequestMapping("/eduservice/subject")
@CrossOrigin
public class EduSubjectController {

    @Autowired
    private EduSubjectService eduSubjectService;

    @PostMapping("addSubject")
    public R addSubject(MultipartFile file){

        //添加课程，将上传的文件内容读取出来
        eduSubjectService.saveSubject(file,eduSubjectService);

        return R.ok();
    }

    //课程分类列表
    @GetMapping("getAllSubject")
    public R getAllSubject(){

        List<OneSubject> list=eduSubjectService.getAllOneTwoSubject();

        return R.ok().data("list",list);
    }

}

