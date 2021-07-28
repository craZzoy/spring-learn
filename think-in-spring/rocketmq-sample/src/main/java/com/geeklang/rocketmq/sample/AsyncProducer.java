package com.geeklang.rocketmq.sample;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @Description: 异步地发送消息
 * @Author : 郑玮泽
 * @Date : 16:18 2021/3/25
 */
public class AsyncProducer {

    public static void main(String[] args) throws Exception{
        final DefaultMQProducer producer = new DefaultMQProducer("group2");
        //指定名字服务地址
        producer.setNamesrvAddr("localhost:9876");
        producer.start();
        producer.setRetryTimesWhenSendAsyncFailed(0);

        int messageCount = 100;
        final CountDownLatch countDownLatch = new CountDownLatch(100);
        for (int i = 0; i < 100; i++){
            Integer index = i;
            Message msg = new Message("testTopic",
                    "taga",
                    ("Hello RocketMq " + i).getBytes(RemotingHelper.DEFAULT_CHARSET) );
            producer.send(msg, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    countDownLatch.countDown();
                    System.out.printf("%-10d OK %s %n", index, sendResult.getMsgId());
                }

                @Override
                public void onException(Throwable throwable) {
                    countDownLatch.countDown();
                    System.out.printf("%-10d Exception %s %n", index, throwable);
                    throwable.printStackTrace();
                }
            });
            //System.out.printf("%s%n", send);
        }
        countDownLatch.await(5, TimeUnit.SECONDS);
        producer.shutdown();
    }

}
