package com.sp.fc.web.test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SpELTest {

    ExpressionParser parser = new SpelExpressionParser();
    Person person = Person.builder().name("yuri").height(163).build();

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
                parser.parseExpression("over(160)").getValue(person, Boolean.class)
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