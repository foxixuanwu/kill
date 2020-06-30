package com.debug.kill.server.utils;

import org.joda.time.DateTime;

import java.text.SimpleDateFormat;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.SimpleFormatter;

/**
 * @author User
 * @Title: RandomUtil
 * @ProjectName kill
 * @Description: 随机数生成工具
 * @date 2020/6/30 17:05
 */
public class RandomUtil {

    public static final SimpleDateFormat dateFormatOne = new SimpleDateFormat("yyyyMMddHHmmssSS");

    public static final ThreadLocalRandom random = ThreadLocalRandom.current();
    /**
     * 生成订单编号-方式一
     * @return
     */
    public static String generateOrderCode(){
        //TODO:时间戳+N为随机数流水号
        return dateFormatOne.format(DateTime.now().toDate()) + generateNumber(4);
    }

    //生成N位随机数
    public static String generateNumber(final int num){
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 1; i <= num; i++) {
            stringBuffer.append(random.nextInt(9));
        }
        return stringBuffer.toString();
    }

//    public static void main(String[] args) {
//        for (int i = 0; i < 100000; i++) {
//            System.out.println(generateOrderCode());
//        }
//    }

}
