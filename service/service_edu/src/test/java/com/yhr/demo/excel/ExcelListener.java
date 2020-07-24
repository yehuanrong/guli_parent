package com.yhr.demo.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.CellData;

import java.util.Map;

public class ExcelListener extends AnalysisEventListener<DemoData>{

    //一行一行读取
    @Override
    public void invoke(DemoData demoData, AnalysisContext analysisContext) {

        System.out.println("*****"+demoData);
    }

    //读取表头内容
    @Override
    public void invokeHead(Map<Integer, CellData> headMap, AnalysisContext context) {

        System.out.println("表头:"+headMap);
    }

    //读取完成之后
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
