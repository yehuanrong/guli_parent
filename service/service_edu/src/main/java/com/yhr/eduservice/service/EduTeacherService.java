package com.yhr.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yhr.eduservice.bean.EduTeacher;
import com.yhr.eduservice.bean.vo.TeacherQuery;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-06-07
 */
public interface EduTeacherService extends IService<EduTeacher> {

    void pageQueryByCondition(Page<EduTeacher> pageTeacher, TeacherQuery teacherQuery);
}
