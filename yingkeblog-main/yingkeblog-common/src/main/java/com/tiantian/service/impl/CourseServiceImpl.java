package com.tiantian.service.impl;

import cn.hutool.core.text.StrBuilder;
import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.common.Term;
import com.tiantian.mapper.CourseMapper;
import com.tiantian.pojo.Course;
import com.tiantian.service.CourseService;
import com.tiantian.utils.vo.ClassNameAndInstitutionVO;
import com.tiantian.utils.vo.ClassNameAndValueKeyWordVO;
import com.tiantian.utils.vo.UserClassAndProgressVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Component
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseMapper courseMapper;

    @Override
    public List<Course> getClassList(Integer uid) {
        return courseMapper.getClassList(uid);
    }

    @Override
    public int updateById(Course course) {
        course.setModifyTime(new Date());
        return courseMapper.updateById(course);
    }

    @Override
    public List<Course> getById(Integer id) {
        return courseMapper.getById(id);
    }

    @Override
    public int deleteById(Integer id) {
        return courseMapper.delById(id);
    }

    @Override
    public int addCourse(Course course) {
        course.setModifyTime(new Date());
        return courseMapper.insertCourse(course);
    }

    @Override
    public List<UserClassAndProgressVO> selectUserClassAndProgress(Integer uid) {
        return courseMapper.selectUserClassAndProgress(uid);
    }

    /**
     * 词频统计
     * @param uid
     * @return
     */
    @Override
    public List<String> wordCount(Integer uid) {
        List<String> list = courseMapper.selectCourseClassNameByUserId(uid);
        StrBuilder sb = new StrBuilder();
        for (String context : list) {
            sb.append(context);
        }
        System.out.println(sb);
        // 将所有东西拼接成一个字符串已空格进行分隔开始统计
        List<Term> terms = HanLP.segment(sb.toString());
        // 创建词汇表
        Map<String, Integer> wordCount = new HashMap<>();
        // 统计词频
        for (Term term : terms) {
            String word = term.word;
            if (wordCount.containsKey(word)) {
                wordCount.put(word, wordCount.get(word) + 1);
            } else {
                wordCount.put(word, 1);
            }
        }
        // 将词汇表转换为列表，便于排序
        List<Map.Entry<String, Integer>> wordList = new ArrayList<>(wordCount.entrySet());
        // 按词频排序
        wordList.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));
        // 输出结果
        for (Map.Entry<String, Integer> entry : wordList) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        return null;
    }

    /**
     * <h1>Echarts所需数据</h1>
     * <h2>{'name': 'Java', 'value': 16801},</h2><br>
     * ClassNameAndInstitution(className=Springboot, institution=黑马程序员, progress=293/308)
     * ClassNameAndInstitution(className=JavaWeb, institution=尚硅谷, progress=300/308)
     *
     */

    @Override
    public List<ClassNameAndValueKeyWordVO> wordCountByProgress(Integer uid) {
        List<ClassNameAndInstitutionVO> data = courseMapper.selectClassNameAndInstitutionsByUserId(uid);
        List<ClassNameAndValueKeyWordVO> res = new ArrayList<>();
        for (ClassNameAndInstitutionVO d : data) {
            String molecule = d.getProgress().split("/")[0];
            String denominator = d.getProgress().split("/")[1];
            ClassNameAndValueKeyWordVO institutionAndValue = new ClassNameAndValueKeyWordVO(d.getInstitution(),Integer.parseInt(molecule));
            ClassNameAndValueKeyWordVO classNameAndValue = new ClassNameAndValueKeyWordVO(d.getClassName(),Integer.parseInt(denominator));
            res.add(institutionAndValue);
            res.add(classNameAndValue);
        }
        System.out.println(res);
        return res;
    }

    @Override
    public List<UserClassAndProgressVO> getFollowUserClassAndProgress(Integer uid) {
        return courseMapper.getFollowUserClassAndProgress(uid);
    }


}
