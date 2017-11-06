package com.springapp.mvc.controllers;

import com.alibaba.fastjson.JSON;
import com.springapp.common.controller.BaseController;
import com.springapp.common.page.PageBean;
import com.springapp.common.page.PageParam;
import com.springapp.mvc.service.CodeService;
import com.springapp.mvc.vo.DataDto;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/api/system")
public class CodeController extends BaseController {

    @Autowired
    CodeService codeService;

    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public String list(HttpServletResponse response, HttpServletRequest request,
                       @RequestParam Map<String, Object> paramMap, Integer page, Integer pageSize) {

        String result = "";
        String message = "查询成功";
        DataDto dto = new DataDto();

        PageParam pageParam = new PageParam(page, pageSize);

        PageBean pageBean = codeService.getTables(pageParam, paramMap);
        dto.setTotalItem(pageBean.getTotalCount());
        dto.setData(pageBean.getRecordList());

        result = JSON.toJSONString(dto);

        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/codecreate", method = RequestMethod.GET)
    public void create(HttpServletResponse response, HttpServletRequest request,
                       String buttons, String tables) throws IOException {
        String[] buttonNames = new String[]{};
        buttonNames = JSON.parseArray(buttons).toArray(buttonNames);

        String[] tableNames = new String[] {};
        tableNames = JSON.parseArray(tables).toArray(tableNames);

        byte[] data = codeService.generatorCode(buttonNames,tableNames);
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"pwcode.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");

        IOUtils.write(data, response.getOutputStream());
    }
}
