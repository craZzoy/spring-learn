package com.geeklang.rocketmq.sample;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * @Description: 单向发送消息（不需要broker返回确认消息）
 * @Author : 郑玮泽
 * @Date : 16:27 2021/3/25
 */
public class OneWayProducer {

    public static void main(String[] args) throws Exception{
        final DefaultMQProducer producer = new DefaultMQProducer("group1");
        //指定名字服务地址
        producer.setNamesrvAddr("localhost:9876");
        producer.start();
        for (int i = 0; i < 100; i++){
            Message msg = new Message("testTopic",
                    "taga",
                    ("Hello RocketMq " + i).getBytes(RemotingHelper.DEFAULT_CHARSET) );
            producer.sendOneway(msg);
        }
        Thread.sleep(5000);
        producer.shutdown();
    }

}


