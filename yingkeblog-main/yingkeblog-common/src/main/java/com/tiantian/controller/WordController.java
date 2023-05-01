package com.tiantian.controller;

import com.tiantian.mapper.WordMapper;
import com.tiantian.pojo.Result;
import com.tiantian.pojo.Word;
import com.tiantian.utils.CacheClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

import static com.tiantian.constants.RedisConstants.EVERY_DAY_WORD;

@RestController
@RequestMapping("/word")
@CrossOrigin
public class WordController {
    @Autowired
    private WordMapper wordMapper;

    @Autowired
    private CacheClient cacheClient;

    @GetMapping
    public Result getWords(){
        // 先查缓存
        Word wordCache = cacheClient.get(EVERY_DAY_WORD, Word.class);
        if (wordCache.getWord() != null) {
            return Result.ok(wordCache);
        }
        // 未命中缓存查询数据库
        Word word = wordMapper.getWordAndId();
        System.out.println(word);
        // 设置缓存24小时过期
        cacheClient.set(EVERY_DAY_WORD,word,24L, TimeUnit.HOURS);
        return Result.ok(word);
    }
}
