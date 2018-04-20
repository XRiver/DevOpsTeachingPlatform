package Devops.docker.DockerBranch.Controller;

import Devops.docker.DockerBranch.RemoteConnection.FileTransport;
import Devops.docker.DockerBranch.Service.FileService;
import Devops.docker.DockerBranch.Service.tools.FileTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@RestController
public class FileController {

    @Autowired
    FileService fileService;


    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public String upload(@RequestBody MultipartFile file, @RequestParam String path, @RequestParam String hostId){
        if(file.isEmpty()){
            return "文件为空";
        }
        return fileService.upload(file,path,hostId);
    }
}
