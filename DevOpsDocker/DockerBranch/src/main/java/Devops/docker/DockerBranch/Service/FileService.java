package Devops.docker.DockerBranch.Service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    public String upload(MultipartFile file, String path, String hostId);
}
