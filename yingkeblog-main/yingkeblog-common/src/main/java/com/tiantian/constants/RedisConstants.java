package com.tiantian.constants;

public class RedisConstants {
    private RedisConstants(){}
    public static final String LOGIN_CODE_KEY = "login:code:";
    public static final String EVERY_DAY_WORD = "every_day_word:";
    public static final String REGISTER_V_CODE = "register_v_code:";
    public static final Long LOGIN_CODE_TTL = 2L;
    public static final String LOGIN_USER_KEY = "login:token:";
    public static final String USER_PERMISSION = "permission:user:";
    public static final String USER_CHANGE_PWD = "user:changepwd:";
    public static final Long LOGIN_USER_TTL = 36000L;

    public static final Long CACHE_NULL_TTL = 2L;

    public static final Long CACHE_SHOP_TTL = 30L;
    public static final String CACHE_SHOP_KEY = "cache:shop:";

    public static final String LOCK_SHOP_KEY = "lock:shop:";
    public static final Long LOCK_SHOP_TTL = 10L;

    public static final String SECKILL_STOCK_KEY = "seckill:stock:";
    public static final String BLOG_LIKED_KEY = "blog:liked:";
    public static final String BLOG_COLLECT_KEY = "blog:collect:";
    public static final String USER_FOLLOW = "follows:";
    public static final String FEED_KEY = "feed:";
    public static final String SHOP_GEO_KEY = "shop:geo:";
    public static final String USER_SIGN_KEY = "sign:";
}
