package Devops.docker.DockerBranch.Controller;

import Devops.docker.DockerBranch.Service.TaskSer;
import Devops.docker.DockerBranch.Service.impl.TaskSerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@ServerEndpoint(value = "/startTask/{taskid}/{username}")
@Component
public class SocketServer {

    private Session session;
    private static Map<String,Session> sessionPool = new HashMap<>();
    private static Map<String,String> sessionIds = new HashMap<>();

    private static final Logger logger = LoggerFactory.getLogger(SocketServer.class);

    @OnOpen
    public void open(Session session, @PathParam(value = "taskid") String userid,@PathParam(value = "username") String username){
        this.session = session;
        sessionPool.put(userid,session);
        sessionIds.put(session.getId(),userid);
        TaskSer taskSer = new TaskSerImpl();
        taskSer.cleanTask(userid);
        taskSer.startTask(userid,username);
        logger.info("连接成功");
    }

    @OnMessage
    public void onMessage(String Message){
        logger.info("当前发送人sessionid为"+session.getId());
    }

    @OnClose
    public void close(){
        sessionPool.remove(sessionIds.get(session.getId()));
        sessionIds.remove(session.getId());
    }

    @OnError
    public void onError(Session session,Throwable error){
        error.printStackTrace();
    }

    public static void sendMessage(String message,String userId){
        Session s = sessionPool.get(userId);
        if(s!=null){
            try{
                s.getBasicRemote().sendText(message);
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }

    public static int getOnlineNum(){
        return sessionPool.size();
    }

    public static String getOnlineUsers(){
        StringBuffer users = new StringBuffer();
        for(String key:sessionIds.keySet()){
            users.append(sessionIds.get(key)+",");
        }
        return users.toString();
    }
}
