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
        return false;
    }

    @Override
    public boolean updateTest(TestVO testVO) {
        TestEntity input=new TestEntity(testVO);
        input.setId(testVO.getId());
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
        return test.toTestVO();
    }
}
