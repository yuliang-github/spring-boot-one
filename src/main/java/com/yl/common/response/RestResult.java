package com.yl.common.response;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Alex
 * @since 2019/2/19 15:25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestResult {

    private int code;

    private int subCode;

    private String message;

    public String toJson(){

        return JSON.toJSONString(this);

    }
}
