package com.Service.Impl;

import com.DataVO.TestCaseVO;
import com.Entity.TestCase;
import com.Entity.TestEntity;
import com.Repository.TestCaseRepository;
import com.Repository.TestRepository;
import com.Service.TestCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2018/3/20.
 */
@Service
public class TestCaseServiceImpl implements TestCaseService{

    @Autowired
    private TestRepository testRepository;
    @Autowired
    private TestCaseRepository testCaseRepo;

    @Override
    public boolean createTestCase(TestCaseVO testCaseVO, Long testId) {
        TestCase testCase=new TestCase(testCaseVO);
        TestEntity test=testRepository.findById(testId);
        test.addTest_case(testCase);
        testRepository.saveAndFlush(test);
        return true;
    }

    @Override
    public boolean deleteTestCase(Long id) {
        TestCase testCase=testCaseRepo.findById(id);
        testCaseRepo.delete(testCase);
        testCaseRepo.flush();
        return true;
    }

    @Override
    public boolean updateTestCase(TestCaseVO testCaseVO) {

        if(testCaseVO.getId()==0){
            System.out.println("id 不存在");
            return false;
        }
        TestCase testCase=testCaseRepo.findById(testCaseVO.getId());
        testCase.setFile(testCaseVO.getFile());
        testCase.setName(testCaseVO.getName());
        testCase.setInfo(testCaseVO.getInfo());
        testCase.setCaseId(testCaseVO.getCaseId());

        testCaseRepo.saveAndFlush(testCase);
        return true;
    }

    @Override
    public TestCaseVO getTestCaseById(Long id) {
        TestCase testCase=testCaseRepo.findById(id);
        if(testCase==null){
            return null;
        }
        return testCase.toTestCaseVO();
    }
}
