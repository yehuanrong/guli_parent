package com.yhr.eduservice.bean.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class SubjectData {

    @ExcelProperty(index = 0)
    private int oneSubjectName;

    @ExcelProperty(index = 1)
    private String twoSubjectName;
}
