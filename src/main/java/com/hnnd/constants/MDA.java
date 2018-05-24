package com.hnnd.constants;

/**
 * 常量类
 * Created by 85073 on 2018/3/31.
 */
public class MDA {

    /**
     * redis中session的失效时间
     */
    public static final Integer SESSION_EXPIRE_TIME=1800;

    public static final Integer PAGE_CACHE_TIME = 60;

    public static final Integer OBJ_CACHE_TIME = 60;

    public static final String TOKEN_NAME="token";

    /**默认秒杀数量*/
    public static final int SSKILL_COUNT =1;
    /*秒杀测试地址发货地址*/
    public static final String DELIVRY_ADDERSS="秒杀测试地址发货地址";
    /**秒杀进行的标志*/
    public static final Integer SSKILL_IN_TIME_FLAG = 0;
    /**秒杀结束的标志*/
    public static final Integer SSKILL_OVER_TIME_FLAG = -1;

    public static final String ORDER_QUEUE_NAME = "ORDER_QUEUE";

    public static final String DIRECT_QUEUE = "DIRECT_QUEUE";

    public static final String DIRECT_QUEUE2 = "DIRECT_QUEUE2";

    public static final String FANOUT_QUEUE = "FANOUT_QUEUE";

    public static final String FANOUT_QUEUE2 = "FANOUT_QUEUE2";

    public static final String TOPIC_QUEUE = "TOPIC.QUEUE";

    public static final String TOPIC_QUEUE2 = "TOPIC.#";

    public static final String SSKILL_ORDER_QUEUE = "SSKILL_ORDER_QUEUE";

    /**
     * 随机字符串加密密钥
     */
    public static final String RANDOM_STR_PASS = "123456";

    public static final String HEADER_QUEUE= "HEADER_QUEUE";



    /**
     * 直接交换机
     */
    public static final String DIRECT_EXCHANGE = "DIRECT_EXCHANGE";

    /**
     * 扇形交换机(广播形势)
     */
    public static final String FANOUT_EXCHANGE = "FANOUT_EXCHANGE";

    /**
     * 主题交换机
     */
    public static final String TOPIC_EXCHANGE = "TOPIC_EXCHANGE";

    /**
     * 头部
     */
    public static final String HEADERS_EXCHANGE = "HEADERS_EXCHANGE";
}
