package Devops.docker.DockerBranch.Service.impl;

import Devops.docker.DockerBranch.Entity.Host;
import Devops.docker.DockerBranch.RemoteConnection.FileTransport;
import Devops.docker.DockerBranch.Service.FileService;
import Devops.docker.DockerBranch.Service.tools.FileTools;
import Devops.docker.DockerBranch.dao.HostDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileSerImpl implements FileService {

    @Autowired
    HostDao hostDao;

    private static final Logger logger = LoggerFactory.getLogger(FileSerImpl.class);
    @Override
    public String upload(MultipartFile file, String path, String hostId) {
        String fileName = file.getOriginalFilename();

        String[] array = fileName.split(".");
        int size = array.length;

        String fileType = "";
        if(size>=1){
            fileType = array[size-1];
        }
        logger.info("上传的文件名为："+fileName);

        String localPath = "F:\\save\\";
        Host host = hostDao.findById(Integer.parseInt(hostId)).get();

        try{
            FileTools.uploadFile(file.getBytes(),localPath,fileName);
        }catch(Exception e){

        }


        FileTransport fileTransport = new FileTransport(fileName,fileType,localPath+fileName,path,host);


        return file.getName();
    }
}
