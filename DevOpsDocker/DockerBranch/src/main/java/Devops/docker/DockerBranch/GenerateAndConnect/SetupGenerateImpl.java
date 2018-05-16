package Devops.docker.DockerBranch.GenerateAndConnect;

import org.springframework.stereotype.Service;

@Service
public class SetupGenerateImpl implements SetupGenerate{
    @Override
    public StringBuilder getMysqlSetup() {
        StringBuilder result = new StringBuilder();
        result.append("set -e\n");
        result.append("#use to check the status of mysql can be deleted\n");
        result.append("echo 'service mysql status'\n");
        result.append("echo '1.starting mysql....'\n");
        result.append("service mysql start\n");
        result.append("sleep 3\n");
        result.append("echo '2.importing the data....'\n");
        result.append("mysql < /mysql/database.sql\n");
        result.append("sleep 3\n");
        result.append("echo '3.changing the password....'\n");
        result.append("sleep 3\n");
        result.append("tail -f /dev/null\n");
        return result;
    }
}
