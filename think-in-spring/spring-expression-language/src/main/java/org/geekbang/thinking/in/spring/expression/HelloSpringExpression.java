package org.geekbang.thinking.in.spring.expression;

import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

/**
 * @Description:
 * @Author : 郑玮泽
 * @Date : 13:42 2021/3/23
 */
public class HelloSpringExpression {

    public static void main(String[] args) {
        ExpressionParser parser = new SpelExpressionParser();
        //简单文字
        final Expression expression = parser.parseExpression("'Hello World'");
        final String value = (String) expression.getValue();
        System.out.println(value);

        //调用方法
        final Expression callMethodExpression = parser.parseExpression("'Hello World'.concat('!')");
        System.out.println(callMethodExpression.getValue());

        final Expression callMethodExpression1 = parser.parseExpression("'Hello World'.bytes");
        byte[] bytes = (byte[]) callMethodExpression1.getValue();
        System.out.println(bytes);

        //内嵌调用
        final Expression callNestedExpression = parser.parseExpression("'Hello World'.bytes.length");
        System.out.println(callNestedExpression.getValue());


        //调用构造方法
        final Expression callConstructor = parser.parseExpression("new String('hello world').toUpperCase()");
        System.out.println(callConstructor.getValue());

    }

}
