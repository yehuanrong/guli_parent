package com.yhr.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.yhr.eduservice.bean.excel.SubjectData;

public class SubjectListener extends AnalysisEventListener<SubjectData>{

    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
