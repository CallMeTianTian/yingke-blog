package com.tiantian.task;

import com.tiantian.mapper.WordMapper;
import com.tiantian.pojo.Word;
import com.tiantian.utils.CacheClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

import static com.tiantian.constants.RedisConstants.EVERY_DAY_WORD;

/**
 * 每日更新句子的定时任务
 */
@Component
@EnableScheduling
public class WordTask {

    @Autowired
    private WordMapper wordMapper;

    @Autowired
    private CacheClient cacheClient;

    @Scheduled(cron = "0 0 0 * * ?") // 每天0点 开始更新每日一句 并做缓存
    public void changeWord(){
        System.err.println("开始执行静态定时任务,时间为: " + LocalDateTime.now());
        // 先获取出当前句子id  再用当前句子的id将字段获取的is_show更新为1
        Word word = wordMapper.getWordAndId();
        wordMapper.updateIsShowByIdInt(word.getId());
        // 将缓存清空
        cacheClient.delete(EVERY_DAY_WORD);
    }
}
