package com.tiantian;

import cn.dev33.satoken.secure.SaSecureUtil;
import com.tiantian.dto.MyFollowDTO;
import com.tiantian.dto.UserBlogDTO;
import com.tiantian.mapper.*;
import com.tiantian.mapper.question.AnswerQuestionsMapper;
import com.tiantian.mapper.question.AnswerUserMapper;
import com.tiantian.mapper.question.ChooseMapper;
import com.tiantian.mapper.question.ProgrammingMapper;
import com.tiantian.pojo.*;
import com.tiantian.pojo.question.AnswerQuestions;
import com.tiantian.pojo.question.Choose;
import com.tiantian.pojo.question.Programming;
import com.tiantian.service.CourseService;
import com.tiantian.service.SendMailService;
import com.tiantian.utils.CacheClient;
import com.tiantian.utils.DateFormat;
import com.tiantian.utils.vo.UserCommentVO;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.tiantian.constants.RedisConstants.EVERY_DAY_WORD;


@SpringBootTest(classes = {TiantianstudyApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.NONE)
@SuppressWarnings("all")
@MapperScan("com/tiantian/mapper")
class TiantianstudyApplicationTests {

    @Test
    public void FuckYou() {
        List<Course> classList = courseMapper.getClassList(1);
        System.out.println("classList = " + classList);
    }


    @Autowired
    private UserPermissionMapper userPermissionMapper;

    @Test
    public void testUserPermissionByUserId() {
        UserPermission userPermission = userPermissionMapper.selectPermission(1);
    }

    @Autowired
    private UserMapper userMapper;

    @Test
    public void selectUserInfoByOne() {
        String email = "1979214069@qq.com";
        String password = "123";
        System.out.println(userMapper.selectEmailAndPassword(email, password));
        System.out.println(userMapper.selectUserInfoOne(email));
    }

    @Autowired
    private SendMailService sendMailService;

    @Test
    public void testSengdEmail() {
        MailRequest mailRequest = new MailRequest();
        mailRequest.setSubject("TianTianStduy");
        mailRequest.setSendTo("1979214069@qq.com");
        mailRequest.setText("【TianTianStudy】，尊敬的TianTianStudy用户，您的你的验证码为: " + 123456 + "，该验证码5分钟内有效，请勿泄露他人。");
        mailRequest.setFrom("1979214069@qq.com" + "(TianTianStudy)");
        sendMailService.sendSimpleMail(mailRequest);
    }

    @Autowired
    private BlogMapper blogMapper;

    @Test
    public void testBlog() {
        List<Blog> blogs = blogMapper.selectAll();
        for (Blog blog : blogs) {
            System.out.println(blog);
        }
    }

    @Test
    public void getBlogByUserIdTest() {
        List<UserBlogDTO> blogByUserId = blogMapper.getBlogByUserId(1);
        for (UserBlogDTO blog : blogByUserId) {
            System.out.println("blog = " + blog);
        }
    }

    @Test
    public void SearchByKeyWord() {
        List<UserBlogDTO> searchRes = blogMapper.selectByKeyWord("查找");
        for (UserBlogDTO blog : searchRes) {
            System.out.println(blog);
        }
    }


    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseMapper courseMapper;

    @Test
    public void testByWordCount() {
        courseService.wordCountByProgress(1);
    }


    @Test
    void testPasswrodEncode() {
        // 定义秘钥和明文
        String key = "HovJ2n2sqkNXEio6Ji0QQJAFLW1Kt80qypMqot+mHhS+0KfdOpaKeMWMSR4Ij5VfE63WzETEeWAMQESxzhavN1WOTb3/p6icgcVbgPQBaWhGg==";
        String text = "123456";

        // 加密
        String ciphertext = SaSecureUtil.aesEncrypt(key, text);
        System.out.println("AES加密后：" + ciphertext);

        // 解密
        String text2 = SaSecureUtil.aesDecrypt(key, ciphertext);
        System.out.println("AES解密后：" + text2);

    }

    @Autowired
    private BaseUserInfoMapper baseUserInfoMapper; // 测试全部通过

    @Test
    void getBaseUserInfo() {
        BaseUserInfo baseUserInfo = baseUserInfoMapper.selectByUid(1);
        System.out.println(baseUserInfo);
    }

    @Test
    void getAddBaseUserInfo() {
        BaseUserInfo baseUserInfo = new BaseUserInfo();
        baseUserInfo.setUid(5);
/*        baseUserInfo.setIdentity("学生");
        baseUserInfo.setBirthday("2023-04-18");
        List<String> list = new ArrayList<>(Arrays.asList("Java","Python","帅哥"));
        baseUserInfo.setLabel(list);
        baseUserInfo.setUsername("TianTianTest");
        baseUserInfo.setDescription("测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据");*/
        baseUserInfo.setSchoolOrCorporation("哈哈佛大学");
        int isInsert = baseUserInfoMapper.updateByUid(baseUserInfo);
        if (isInsert > 0) {
            System.out.println("成功");
        } else {
            System.out.println("失败");
        }
    }

    @Autowired
    private CacheClient cacheClient; // 测试全部通过

    @Autowired
    private WordMapper wordMapper; // 测试全部通过

    @Test
    void testRedis() {
        Word word = wordMapper.getWordAndId();
        // 设置缓存24小时过期
        cacheClient.set(EVERY_DAY_WORD, word, 24L, TimeUnit.HOURS);
    }

    @Test
    void getTestRedis() {
        Word word = cacheClient.get(EVERY_DAY_WORD, Word.class);
        System.out.println(word);
    }

    @Test
    void deleteRedisKey() {
        Boolean delete = cacheClient.delete(EVERY_DAY_WORD);
        System.out.println(delete.booleanValue());
    }

    @Test
    void printHelloWord() {
        System.out.println("hello world!");
    }

    @Autowired
    private FollowMapper followMapper; // 测试全部通过

    @Test
    void testFollowFunction() {
        Follow follow = new Follow();
        follow.setUserId(10); // 当前用户id
        follow.setFollowUserId(1);  // 被关注者的id
        int count = followMapper.addFollowByUid(follow);
        if (count > 0) {
            System.out.println("新增成功 添加到Redis中");
        }
    }

    @Test
    void testFollowDelFunction() {
        int count = followMapper.deleteByUserIdAndFollowUserId(10, 1);
        if (count > 0) {
            System.out.println("新增成功");
        }
    }

    @Test
    void testFollowSelFunction() {
        int count = followMapper.countByUserIdAndFollowUserid(6, 1);
        if (count > 0) {
            System.out.println("查询成功");
        }
    }

    @Test
    void getMyLove(){
        List<MyFollowDTO> myFollowDTOS = followMapper.selectMyLikeByUserId(1);
        for (MyFollowDTO myFollowDTO : myFollowDTOS) {
            System.out.println(myFollowDTO);
        }
    }

    @Autowired
    private UserCollectMapper userCollectMapper;

    @Test
    void getCollectByUid(){
        List<Blog> blogs = userCollectMapper.selectCollectsBlogsByUid(1);
        for (Blog blog : blogs) {
            System.out.println(blog);
        }
    }
    @Test
    void addCollectByUid(){
        UserCollect userCollect = new UserCollect();
        userCollect.setUserId(1);
        userCollect.setBlogId(5);
        int count = userCollectMapper.insertSelective(userCollect);
        if (count > 0) {
            System.out.println("修改成功");
        }
    }

    @Autowired
    private CountTypeMapper countTypeMapper;

    @Test
    void countTypeMapperTest(){
        List<CountType> countTypes = countTypeMapper.countType();
        System.out.println(countTypes);
    }

    @Autowired
    private UserCommentMapper userCommentMapper;

    @Test
    void getUserCommentByBlogId(){
        List<UserCommentVO> userCommonsByBlogId = userCommentMapper.getUserCommonsByBlogId(16);
        for (UserCommentVO s : userCommonsByBlogId) {
            System.out.println(s);
        }
    }

    @Test
    void addUserCommentByBlogId(){
        UserCommentVO commentVO = new UserCommentVO();
        commentVO.setBlogId(1);
        commentVO.setUid(1);
        commentVO.setContent("测试评论测试评论测试评论测试评论测试评论测试评论测试评论测试评论测试评论测试评论测试评论测试评论测试评论测试评论测试评论测试评论测试评论测试评论");
        commentVO.setReleaseTime(DateFormat.getNowDateFormatComment());
        int i = userCommentMapper.addUserComment(commentVO);
        if (i>0) {
            System.out.println("新增成功");
        }
    }

    @Test
    void delByidAndUid(){
        int i = userCommentMapper.deleteUserCommentByCommentIdAndUid(2, 1);
        if (i>0) {
            System.out.println("删除成功");
        }
    }

    @Test
    void testDate(){
        System.out.println(System.currentTimeMillis());
    }

    @Autowired
    private AnswerQuestionsMapper answerQuestionsMapper;

    @Autowired
    private ChooseMapper chooseMapper;

    @Autowired
    private ProgrammingMapper programmingMapper;

    @Test
    void testProgrammingMapper(){
        HashMap<String, Object> questions = new HashMap<>();
        AnswerQuestions answerQuestions = answerQuestionsMapper.selectOne();
        Choose choose = chooseMapper.selectOne();
        Programming programming = programmingMapper.selectOne();
        questions.put("a",answerQuestions);
        questions.put("b",choose);
        questions.put("c",programming);
        System.out.println("================================");
        System.out.println(questions);
    }

    @Autowired
    private AnswerUserMapper answerUserMapper;

    @Test
    void isAnswer(){
        int count = answerUserMapper.countAnswerTodayIsAnswer(20230426, 1);
        System.out.println(count);
    }

}
