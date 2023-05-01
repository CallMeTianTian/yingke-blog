package com.tiantian.controller.question;


import com.tiantian.mapper.question.*;
import com.tiantian.pojo.Result;
import com.tiantian.pojo.question.AnswerUser;
import com.tiantian.pojo.question.QuestionManage;
import com.tiantian.utils.DateFormat;
import com.tiantian.utils.UserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/question")
@CrossOrigin
public class QuestionController {
    @Autowired
    private AnswerQuestionsMapper answerQuestionsMapper;

    @Autowired
    private ChooseMapper chooseMapper;

    @Autowired
    private ProgrammingMapper programmingMapper;

    @Autowired
    private QuestionManageMapper questionManageMapper;

    @Autowired
    private AnswerUserMapper answerUserMapper;


    /**
     * 获取今日题目信息
     */
    @GetMapping("/get-all")
    public Result getAll() {
        int paperId = Integer.parseInt(DateFormat.getNowCode()); // 20230426 当前日期编号 这里先写死 因为题目太少了
        QuestionManage questionManage = questionManageMapper.selectByPrimaryKey(20230426);
        Integer questionId = questionManage.getQuestionId();
        HashMap<String, Object> questions = new HashMap<>();
        questions.put("a", programmingMapper.getByQuestionId(questionId));
        questions.put("b", chooseMapper.getByQuestionId(questionId));
        questions.put("c", answerQuestionsMapper.getByQuestionId(questionId));
        return Result.ok(questions);
    }

    /**
     * 添加答题用户
     */
    @PostMapping("/answer-user")
    public Result addAnswerUser(@RequestBody AnswerUser answerUser) {
        int paperId = Integer.parseInt(DateFormat.getNowCode()); // 20230426 当前日期编号 这里先写死 因为题目有限
        answerUser.setPaperId(20230426);
        answerUser.setUid(UserHolder.getUid());
        int count = answerUserMapper.insertSelective(answerUser);
        return Result.update(count);
    }

    /**
     * 判断用户是否已经完成了今日答题
     */
    @GetMapping("/is-answer")
    public Result isAnswer() {
        int paperId = Integer.parseInt(DateFormat.getNowCode()); // 20230426 当前日期编号 这里先写死 因为题目有限
        // 查询数据库中是否有用户id和每日一题的关系
        int count = answerUserMapper.countAnswerTodayIsAnswer(20230426, UserHolder.getUid());
        return Result.update(count);
    }


}
