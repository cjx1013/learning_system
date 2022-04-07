package com.chen.service;

import com.chen.entity.Question;

import java.util.List;

public interface QuestionService {
   List<Question> find(String key);

   List<Question> findAll();

    void delete(String id);

    Question search(String id);

    void update(Question question);

    void insert(Question question);
}
