package com.yhr.eduservice.service.impl;

import com.yhr.eduservice.bean.EduCourse;
import com.yhr.eduservice.bean.EduCourseDescription;
import com.yhr.eduservice.bean.vo.CourseInfoVo;
import com.yhr.eduservice.mapper.EduCourseDescriptionMapper;
import com.yhr.eduservice.mapper.EduCourseMapper;
import com.yhr.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yhr.exceptionconfig.GuliException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-08-05
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Autowired
    private EduCourseMapper eduCourseMapper;

    @Autowired
    private EduCourseDescriptionMapper eduCourseDescriptionMapper;

    @Override
    public String saveCourseInfo(CourseInfoVo courseInfoVo) {

        //向课程表添加课程信息
        EduCourse eduCourse=new EduCourse();
        BeanUtils.copyProperties(courseInfoVo,eduCourse);
        int resultCourseInfo = eduCourseMapper.insert(eduCourse);
        if(resultCourseInfo==0){

            throw new GuliException(20001,"课程信息保存失败");
        }

        //课程添加成功之后获取课程id
        String cid=eduCourse.getId();

        //向课程简介表添加课程简介信息
        EduCourseDescription eduCourseDescription=new EduCourseDescription();
        eduCourseDescription.setDescription(courseInfoVo.getDescription());

        //设置课程简介id使得和课程id一致
        eduCourseDescription.setId(cid);

        int resultDescription = eduCourseDescriptionMapper.insert(eduCourseDescription);
        if(resultDescription==0){

            throw new GuliException(20001,"课程简介保存失败");
        }

        return cid;
    }
}
