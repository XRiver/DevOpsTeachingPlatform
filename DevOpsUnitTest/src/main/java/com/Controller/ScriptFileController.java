package com.Controller;

import com.DataVO.ReportVO;
import com.Service.ScriptFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScriptFileController {
    @Autowired
    private ScriptFileService scriptFileService;

    @RequestMapping(value = "/test/create-script", method = RequestMethod.POST)
    public boolean TestAll(@RequestParam("id") long id){
        return scriptFileService.uploadScript(id);

    }

}
