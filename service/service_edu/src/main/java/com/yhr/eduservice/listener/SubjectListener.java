package com.yhr.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yhr.eduservice.bean.EduSubject;
import com.yhr.eduservice.bean.excel.SubjectData;
import com.yhr.eduservice.service.EduSubjectService;
import com.yhr.exceptionconfig.GuliException;

public class SubjectListener extends AnalysisEventListener<SubjectData>{

    //SubjectListener没有交给spring管理（自己new出来的）,所以就不能使用Autowired注入属性
    //所以利用有参构造注入
    private EduSubjectService eduSubjectService;
    public SubjectListener(){}
    public SubjectListener(EduSubjectService eduSubjectService){

        this.eduSubjectService=eduSubjectService;
    }

    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {

        if(subjectData ==null){

            throw new GuliException(20001,"文件数据为空");
        }

        //添加一级分类
        EduSubject existOneSubject = this.existOneSubject(eduSubjectService, subjectData.getOneSubjectName());

        if(existOneSubject == null){//没有相同的一级分类，进行添加

            existOneSubject=new EduSubject();
            existOneSubject.setParentId("0");
            existOneSubject.setTitle(subjectData.getOneSubjectName());
            eduSubjectService.save(existOneSubject);

        }

        //添加二级分类
        //获取一级分类id
        String pid=existOneSubject.getId();
        EduSubject existTwoSubject = this.existTwoSubject(eduSubjectService, subjectData.getTwoSubjectName(), pid);

        if(existTwoSubject == null){

            existOneSubject=new EduSubject();
            existOneSubject.setParentId(pid);
            existOneSubject.setTitle(subjectData.getTwoSubjectName());
            eduSubjectService.save(existOneSubject);
        }

    }

    //判断一级分类是否重复
    private EduSubject existOneSubject(EduSubjectService eduSubjectService,String name) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id","0");
        EduSubject eduSubject = eduSubjectService.getOne(wrapper);
        return eduSubject;
    }

    //判断二级分类是否重复
    private EduSubject existTwoSubject(EduSubjectService eduSubjectService,String name,String pid) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id",pid);
        EduSubject eduSubject = eduSubjectService.getOne(wrapper);
        return eduSubject;
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
