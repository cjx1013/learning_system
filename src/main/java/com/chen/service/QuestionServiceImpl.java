package com.chen.service;

import com.chen.dao.QuestionDao;
import com.chen.entity.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class QuestionServiceImpl implements QuestionService{
    @Autowired
    private QuestionDao questionDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Question> find(String key) {
        return questionDao.find(key);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Question> findAll() {
        return questionDao.findAll();
    }

    @Override
    public void delete(String id) {
        questionDao.delete(id);
    }

    @Override
    public Question search(String id) {
        return questionDao.search(id);
    }

    @Override
    public void update(Question question) {
        questionDao.update(question);
    }

    @Override
    public void insert(Question question) {
        question.setId(UUID.randomUUID().toString());
        questionDao.insert(question);
    }

}
