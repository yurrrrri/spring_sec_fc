package com.sp.fc.web.test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.expression.AccessException;
import org.springframework.expression.BeanResolver;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SpELTest {

    ExpressionParser parser = new SpelExpressionParser();
    Person person = Person.builder().name("yuri").height(163).build();
    Cat cat = Cat.builder().name("nancy").height(60).build();

    @Test
    void basicTest() {
        assertEquals("yuri", parser.parseExpression("name").getValue(person));
    }

    @Test
    void modifyTest() {
        parser.parseExpression("name").setValue(person, "henny");
        assertEquals("henny", parser.parseExpression("name").getValue(person));
    }

    @Test
    void overMethodTest() {
        assertEquals(
                Boolean.TRUE,
                parser.parseExpression("over(100)").getValue(person, Boolean.class)
        );
    }

    @Test
    void overMethodTest2() {
        assertEquals(
                Boolean.FALSE,
                parser.parseExpression("over(100)").getValue(cat, Boolean.class)
        );
    }

    @Test
    void contextTest() {
        StandardEvaluationContext ctx = new StandardEvaluationContext();
        ctx.setBeanResolver((context, beanName) -> beanName.equals("person") ? person : cat);
        ctx.setRootObject(person);
        ctx.setVariable("may", cat);

        assertEquals(
                Boolean.TRUE,
                parser.parseExpression("@person.over(100)").getValue(ctx, Boolean.class)
        );
        assertEquals(
                Boolean.FALSE,
                parser.parseExpression("@cat.over(100)").getValue(ctx, Boolean.class)
        );
        assertEquals(
                Boolean.FALSE,
                parser.parseExpression("#may.over(100)").getValue(ctx, Boolean.class)
        );
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
class Person {
    private String name;
    private int height;

    public boolean over(int pivot) {
        return height >= pivot;
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
class Cat {
    private String name;
    private int height;

    public boolean over(int pivot) {
        return height >= pivot;
    }
}