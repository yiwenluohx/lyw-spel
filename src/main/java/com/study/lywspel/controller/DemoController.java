package com.study.lywspel.controller;

import com.study.lywspel.annotation.Authorize;
import com.study.lywspel.annotation.SpelGetParm;
import com.study.lywspel.param.DemoParam;
import com.study.lywspel.param.Inventor;
import com.study.lywspel.param.User;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.GregorianCalendar;

/**
 * 实例
 *
 * @author luohx
 * @version 1.0.0
 * @date: 2023/8/15 上午9:57
 * @menu 实例
 */
@RequestMapping("/demo")
@RestController
public class DemoController {

    @GetMapping(value = "/get/test")
    public ResponseEntity<String> test(@Validated @RequestParam("requestId") String requestId) {
        //1、牛刀小试
        //1.1、定义解析器
//        SpelExpressionParser parser = new SpelExpressionParser();
        //1.2、使用解析器，解析表达式
//        String spelEx = "requstId:".concat(requestId);
//        Expression exp = parser.parseExpression("'" + spelEx + "'");
//        //1.3、获取解析结果
//        String reqId = (String) exp.getValue();

        //2、字符串方法的字面调用
//        Expression exp = parser.parseExpression("'Hello World'.concat('!')");
//        String value = (String) exp.getValue();
//        System.out.println(value);
//        exp = parser.parseExpression("'Hello World'.bytes");
//        byte[] bytes = (byte[]) exp.getValue();
//        exp = parser.parseExpression("'Hello World'.bytes.length");
//        int length = (Integer) exp.getValue();
//        System.out.println("length:" + length);
//
//        //调用
//        exp = parser.parseExpression("new String('Hello World').toUpperCase()");
//        System.out.println("大写："+ exp.getValue());

        //3、针对特定对象解析表达式
        // 创建  Inventor 对象
        GregorianCalendar c = new GregorianCalendar();
        c.set(1856, 7, 9);
        Inventor tesla = new Inventor("Nikola Tesla", c.getTime(), "Serbian");
        ExpressionParser parser = new SpelExpressionParser();
        Expression exp = parser.parseExpression("name");
        //在tesla对象上解析
        String name = (String) exp.getValue(tesla);
        System.out.println(name);
        exp = parser.parseExpression("name == 'Nikola Tesla'");
        // 在 tesla对象上解析并指定返回结果
        Boolean result = exp.getValue(tesla, Boolean.class);
        System.out.println(result);

        /**
         * 4、执行过程分析和关键接口
         *   4.1 执行过程分析
         *     a、用户表达式：我们定义的表达式，如1+1!=2
         *     b、解析器：ExpressionParser 接口，负责将用户表达式解析成SpEL认识的表达式对象
         *     c、表达式对象：Expression接口，SpEL的核心，表达式语言都是围绕表达式进行的
         *     d、评估上下文：EvaluationContext 接口，表示当前表达式对象操作的对象，表达式的评估计算是在上下文上进行的。
         */

        return new ResponseEntity("", HttpStatus.OK);
    }

    @PostMapping(value = "/get/demo")
    @Authorize(type = Authorize.Type.OWNER, eid = "#eid")
    public ResponseEntity getDemo(@RequestParam("eid") Long eid) {
        String eidStr = "企业id:".concat(eid.toString());
        return new ResponseEntity(eidStr, HttpStatus.OK);
    }

    @RequestMapping(value = "/post/demo", method = RequestMethod.POST)
    @Authorize(type = Authorize.Type.OWNER, eid = "#eid")
    public ResponseEntity<DemoParam> postDemo(@Validated @RequestBody DemoParam param) {
        String companyN = "企业id：+ " + param.getEid() + ",公司名称:".concat(param.getCompanyName());
        return new ResponseEntity(companyN, HttpStatus.OK);
    }

    @PostMapping("/param")
    @SpelGetParm(param = "#user.name")
    public ResponseEntity repeat(@RequestBody User user) {
        return new ResponseEntity(user, HttpStatus.OK);
    }
}
