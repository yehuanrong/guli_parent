package com.yhr.eduservice.service;

import com.yhr.eduservice.bean.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yhr.eduservice.bean.vo.CourseInfoVo;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-08-05
 */
public interface EduCourseService extends IService<EduCourse> {

    String saveCourseInfo(CourseInfoVo courseInfoVo);
}
