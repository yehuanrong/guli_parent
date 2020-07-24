package com.yhr.demo.excel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

public class TestEasyExcel {

    public static void main(String[] args) {

        //写操作
        /*String fileName="E:\\guli-log\\write.xlsx";

        EasyExcel.write(fileName,DemoData.class).sheet("学生列表").doWrite(getData());*/

       //读操作
        String fileName="E:\\guli-log\\write.xlsx";
        EasyExcel.read(fileName,DemoData.class,new ExcelListener()).sheet().doRead();
    }


    private static List<DemoData> getData(){

        List<DemoData> list=new ArrayList<>();

        for(int i=0;i<10;i++){

            DemoData data=new DemoData();

            data.setSno(i);

            data.setSname("yhr"+i);
            list.add(data);
        }

        return list;
    }
}
