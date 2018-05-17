package com.Service.Impl;

import com.DataVO.TestVO;
import com.Entity.TestEntity;
import com.Repository.TestRepository;
import com.Service.TestService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * Created by Administrator on 2018/3/20.
 */

@Service
public class TestServiceImpl implements TestService{
    @Autowired
    private TestRepository testRepository;

    @Override
    public boolean createTest(TestVO testVO) {
        TestEntity test=new TestEntity(testVO);
        testRepository.save(test);
        return true;
    }

    @Override
    public boolean deleteTest(Long id) {

        testRepository.delete(id);
        testRepository.flush();
        return true;
    }

    @Override
    public boolean updateTest(TestVO testVO) {
        if(testVO.getId()==0){
            System.out.println("id 不存在");
            return false;
        }

        TestEntity input=testRepository.findById(testVO.getId());
        input.setBranch(testVO.getBranch());
        input.setLanguage(testVO.getLanguage());
        input.setName(testVO.getName());
        input.setSrc(testVO.getSrc());
        input.setTestId(testVO.getTestId());
        testRepository.saveAndFlush(input);

        return true;
    }

    @Override
    public List<TestVO> getTestByProject(String projectId) {
        List<TestVO> list=new ArrayList<TestVO>();
        for(TestEntity temp:testRepository.findByProject_id(projectId)){
            list.add(temp.toTestVO());
        }
        return list;
    }

    @Override
    public TestVO getTestById(Long id) {
        TestEntity test=testRepository.findById(id);
        if(test==null){
            System.out.println("id 不存在");
            return null;
        }
        return test.toTestVO();
    }
}
