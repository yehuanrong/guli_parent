package com.yhr.exceptionconfig;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor  //生成有参构造
@NoArgsConstructor  //生成无参构造
public class GuliException extends RuntimeException{

    @ApiModelProperty(value = "状态码")
    private Integer code;

    private String msg;
}
