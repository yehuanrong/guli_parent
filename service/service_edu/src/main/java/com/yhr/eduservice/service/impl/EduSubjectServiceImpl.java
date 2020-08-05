package com.yhr.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yhr.eduservice.bean.EduSubject;
import com.yhr.eduservice.bean.excel.SubjectData;
import com.yhr.eduservice.bean.subject.OneSubject;
import com.yhr.eduservice.bean.subject.TwoSubject;
import com.yhr.eduservice.listener.SubjectListener;
import com.yhr.eduservice.mapper.EduSubjectMapper;
import com.yhr.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    private EduSubjectMapper eduSubjectMapper;
    //添加课程分类
    @Override
    public void saveSubject(MultipartFile file,EduSubjectService eduSubjectService) {

        try{

            InputStream in = file.getInputStream();

            EasyExcel.read(in, SubjectData.class,new SubjectListener(eduSubjectService)).sheet().doRead();


        }catch (Exception e){

            e.printStackTrace();
        }

    }

    //课程分类（树形）
    @Override
    public List<OneSubject> getAllOneTwoSubject() {

        //查询所有的一级分类
        QueryWrapper<EduSubject> wrapperOne=new QueryWrapper<>();

        wrapperOne.eq("parent_id","0");

        List<EduSubject> oneSubjectList = eduSubjectMapper.selectList(wrapperOne);

        //查询所有的二级分类
        QueryWrapper<EduSubject> wrapperTwo=new QueryWrapper<>();

        wrapperOne.ne("parent_id","0");

        List<EduSubject> twoSubjectList = eduSubjectMapper.selectList(wrapperTwo);

        //创建list集合，用于存储最终数据封装
        List<OneSubject> finalSubjectList=new ArrayList<>();

        //封装一级分类
        for (int i = 0; i < oneSubjectList.size(); i++) {

            EduSubject eduSubject = oneSubjectList.get(i);
            OneSubject oneSubject=new OneSubject();
            BeanUtils.copyProperties(eduSubject,oneSubject);

            finalSubjectList.add(oneSubject);

            //封装二级分类
            //在一级分类内循环遍历二级分类
            List<TwoSubject> twoFinalSubjectList=new ArrayList<>();
            for (int m = 0; m < twoSubjectList.size(); m++) {

                EduSubject tSubject = twoSubjectList.get(m);

                //判断一级分类id和二级分类parent_id是否相同
                if(tSubject.getParentId().equals(eduSubject.getId())){

                    TwoSubject twoSubject=new TwoSubject();
                    BeanUtils.copyProperties(tSubject,twoSubject);
                    twoFinalSubjectList.add(twoSubject);
                }
            }
            oneSubject.setChildren(twoFinalSubjectList);

        }


        return finalSubjectList;
    }
}
