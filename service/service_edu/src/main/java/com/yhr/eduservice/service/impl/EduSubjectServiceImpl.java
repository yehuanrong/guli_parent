package com.yhr.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.yhr.eduservice.bean.EduSubject;
import com.yhr.eduservice.bean.excel.SubjectData;
import com.yhr.eduservice.listener.SubjectListener;
import com.yhr.eduservice.mapper.EduSubjectMapper;
import com.yhr.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-06-14
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    //添加课程分类
    @Override
    public void saveSubject(MultipartFile file) {

        try{

            InputStream in = file.getInputStream();

            EasyExcel.read(in, SubjectData.class,new SubjectListener()).sheet().doRead();


        }catch (Exception e){


        }

    }
}
