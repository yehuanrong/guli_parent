package com.yhr.eduservice.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yhr.commonutils.R;
import com.yhr.eduservice.bean.EduTeacher;
import com.yhr.eduservice.bean.vo.TeacherQuery;
import com.yhr.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-06-07
 */
@Api(description = "讲师管理")
@RestController
@RequestMapping("/eduservice/teacher")
@CrossOrigin
public class EduTeacherController {

    @Autowired
    private EduTeacherService teacherService;

    @GetMapping("findAll")
    @ApiOperation(value = "所有讲师列表")
    public R findAllTeacher(){

        List<EduTeacher> list = teacherService.list(null);
        return R.ok().data("items",list);

    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "根据ID删除讲师")
    public R removeTeacher(@ApiParam(name = "id", value = "讲师ID", required = true)
                                     @PathVariable String id){

        boolean flag = teacherService.removeById(id);

        if(flag){

            return R.ok();
        }else {

            return R.error();
        }

    }

    @GetMapping("pageTeacher/{current}/{limit}")
    @ApiOperation(value = "讲师分页查询")
    public R pageListTeacher(@ApiParam(name = "current", value = "当前页", required = true) @PathVariable long current,
                             @ApiParam(name = "limit", value = "每页的记录数", required = true) @PathVariable long limit){

        //创建page对象
        Page<EduTeacher> pageTeacher=new Page<>(current,limit);

        /*try {
            int i=10/0;
        }catch (Exception e){

            throw new GuliException(20001,"执行了自定义异常");
        }*/

        teacherService.page(pageTeacher,null);

        long total = pageTeacher.getTotal();
        List<EduTeacher> eduTeacherList = pageTeacher.getRecords();

        Map<String,Object> teacherMap=new HashMap<>();
        teacherMap.put("total",total);
        teacherMap.put("rows",eduTeacherList);

        return R.ok().data(teacherMap);
    }

    @ApiOperation(value = "组合条件分页查询")
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    public R pageTeacherCondition(@PathVariable long current, @PathVariable long limit,
                                  @RequestBody(required = false) TeacherQuery teacherQuery){

        //创建page对象
        Page<EduTeacher> pageTeacher=new Page<>(current,limit);
        //构建条件

        teacherService.pageQueryByCondition(pageTeacher, teacherQuery);

        long total = pageTeacher.getTotal();
        List<EduTeacher> eduTeacherList = pageTeacher.getRecords();

        Map<String,Object> teacherMap=new HashMap<>();
        teacherMap.put("total",total);
        teacherMap.put("rows",eduTeacherList);

        return R.ok().data(teacherMap);
    }

    @ApiOperation(value = "讲师新增功能")
    @PostMapping("addTeacher")
    public R addTeacher(@RequestBody EduTeacher eduTeacher){

        boolean flag = teacherService.save(eduTeacher);

        if(flag){

            return R.ok();
        }else{

            return R.error();
        }
    }

    @ApiOperation(value = "根据id查询讲师")
    @GetMapping("getTeacher/{id}")
    public R getTeacher(@PathVariable String id){

        EduTeacher eduTeacher = teacherService.getById(id);

        return R.ok().data("teacher",eduTeacher);
    }

    @ApiOperation(value = "讲师修改功能")
    @PostMapping("updateTeacher")
    public R updateTeacher(@RequestBody EduTeacher eduTeacher){

        boolean flag = teacherService.updateById(eduTeacher);

        if(flag){

            return R.ok();
        }else{

            return R.error();
        }
    }

}

