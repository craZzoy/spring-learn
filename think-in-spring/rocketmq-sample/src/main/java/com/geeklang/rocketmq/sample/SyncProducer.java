package com.geeklang.rocketmq.sample;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * @Description: 同步地发送消息
 * @Author : 郑玮泽
 * @Date : 16:06 2021/3/25
 */
public class SyncProducer {

    public static void main(String[] args) throws Exception {
        final DefaultMQProducer producer = new DefaultMQProducer("group1");
        //指定名字服务地址
        producer.setNamesrvAddr("localhost:9876");
        producer.start();
        for (int i = 0; i < 100; i++){
            Message msg = new Message("testTopic",
                    "taga",
                    ("Hello RocketMq " + i).getBytes(RemotingHelper.DEFAULT_CHARSET) );
            final SendResult send = producer.send(msg);
            System.out.printf("%s%n", send);
        }
        producer.shutdown();
    }

}
