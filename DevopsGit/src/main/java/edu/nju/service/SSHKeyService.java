package edu.nju.service;

import com.alibaba.fastjson.JSON;
import edu.nju.api.Util;
import edu.nju.model.SSHKey;
import jdk.nashorn.internal.parser.JSONParser;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/3/31.
 */
@Service
public class SSHKeyService {

    public List<SSHKey> getAllSSHkey(){
        String keystr= Util.get("/user/keys",null);
        List<SSHKey> list= JSON.parseArray(keystr,SSHKey.class);
        return list;
    }

    public List<SSHKey> getUserSSHKey(String userID){
        String keystr=Util.get("/users/"+userID+"/keys",null);
        List<SSHKey> list= JSON.parseArray(keystr,SSHKey.class);
        return list;
    }

    /**
     *
     * @param userID
     * @param sshKey
     * @return success:sshkey json
     *         failure: {message:{fingerprint:[],key:[]}}
     */
    public String addSSHKey(String userID,SSHKey sshKey){
        Map<String,String> map=new HashMap<>();
        map.put("id",userID);
        map.put("title",sshKey.getTitle());
        map.put("key",sshKey.getKey());
        String result=Util.post("/users/"+userID+"/keys",map);
        return result;
    }

    public String deleteSSHKey(String id,String key_id){
        Map<String,String> map=new HashMap<>();
        map.put("id",id);
        map.put("key_id",key_id);
        String result=Util.post("/users/"+id+"/keys/"+key_id,map);
        return result;
    }

}
