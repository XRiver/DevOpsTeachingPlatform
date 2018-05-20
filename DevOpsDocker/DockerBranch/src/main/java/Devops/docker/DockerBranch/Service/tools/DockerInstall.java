package Devops.docker.DockerBranch.Service.tools;

import Devops.docker.DockerBranch.Entity.Host;
import Devops.docker.DockerBranch.Exception.RemoteOperateException;
import Devops.docker.DockerBranch.RemoteConnection.FileTransport;
import Devops.docker.DockerBranch.RemoteConnection.RemoteExecuteCommand;
import Devops.docker.DockerBranch.RemoteConnection.RemoteSignIn;
import Devops.docker.DockerBranch.Service.impl.HostSerImpl;
import ch.ethz.ssh2.Connection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class DockerInstall {

    private static final Logger logger = LoggerFactory.getLogger(DockerInstall.class);
    public int dockerInstall(Host host) {
        Connection connection = this.getConnection(host);
        RemoteExecuteCommand remoteExecuteCommand = new RemoteExecuteCommand();
        StringBuilder version = null;

        try {
            version = remoteExecuteCommand.ExecCommand(new StringBuilder("sudo mkdir /home/admin/docker"), connection);
        } catch (IOException e) {
            connection.close();
            return 3;
        }

        String str = System.getProperty("user.dir");

        String localPath = str + "\\DevOpsDocker\\DockerBranch\\src\\main\\resources\\shell\\";
        FileTransport fileTransport = null;
        if (host.getOpsSystem().equals("centos")) {
            fileTransport = new FileTransport("docker_centos", "sh", localPath, "/home/admin/docker", connection);
            try {
                fileTransport.putFile();

            } catch (IOException e) {
                connection.close();
                return 3;
            }

            try {
                version = remoteExecuteCommand.ExecCommand(new StringBuilder("sudo bash /home/admin/docker/docker_centos.sh"), connection);
            } catch (IOException e) {
                logger.info(version.toString() + "012");
                connection.close();
                return 3;
            }

        } else {
            fileTransport = new FileTransport("docker_ubuntu", "sh", localPath, "/home/admin/docker", connection);

            try {
                fileTransport.putFile();

            } catch (IOException e) {
                connection.close();
                return 3;
            }

            try {
                version = remoteExecuteCommand.ExecCommand(new StringBuilder("sudo bash /home/admin/docker/docker_ubuntu.sh"), connection);
                logger.info(version.toString() + "123");
            } catch (IOException e) {
                connection.close();
                return 3;
            }
        }
        connection.close();
        return 4;
    }

    private Connection getConnection(Host host) {
        RemoteSignIn sign = new RemoteSignIn(host.getIp(), Integer.parseInt(host.getSshPort()), host.getRoot(), host.getPassword());
        Connection connection = null;
        try {
            connection = sign.ConnectAndAuth(sign.getUSER(), sign.getPASSWORD());
        } catch (RemoteOperateException e1) {
            // TODO Auto-generated catch block
//			e1.printStackTrace();
            return null;
        } catch (IOException e1) {
            // TODO Auto-generated catch block
//			e1.printStackTrace();
            return null;
        }
        return connection;
    }
}
