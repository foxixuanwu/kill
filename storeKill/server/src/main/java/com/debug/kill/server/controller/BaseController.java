package com.debug.kill.server.controller;

import com.debug.kill.api.enums.StatusCode;
import com.debug.kill.api.response.BaseResponse;
import jodd.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author User
 * @Title: BaseController
 * @ProjectName kill
 * @Description: 测试
 * @date 2020/6/29 22:36
 */
@Controller
@RequestMapping("base")
public class BaseController {

    private static final Logger log = LoggerFactory.getLogger(BaseController.class);

//    @RequestMapping(value = "/welcome",method = RequestMethod.GET)
    @GetMapping(value = "/welcome")
    public String welcome(String name, ModelMap modelMap){
        if (StringUtil.isBlank(name)) {
            name = "这是welcome";
        }
        modelMap.put("name",name);
        return "welcome";
    }

    @GetMapping("/data")
    @ResponseBody
    public String data(String name){
        if (StringUtil.isBlank(name)) {
            name = "这是welcome";
        }
        return name;
    }

    @GetMapping("/response")
    @ResponseBody
    public BaseResponse response(String name){
        BaseResponse baseResponse = new BaseResponse(StatusCode.Success);
        if (StringUtil.isBlank(name)) {
            name = "这是welcome";
        }
        baseResponse.setData(name);
        return baseResponse;
    }


}
