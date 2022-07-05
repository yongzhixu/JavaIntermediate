package com.example.javamaster.primitiveType;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CastingTest {

    @Test
    void cast_between_primitives() {

        int integerValue = 123;
        byte byteValue = 56;
        short shortValue = 123;

        shortValue = (short) (byteValue * 2);
        integerValue = byteValue * 2;
        shortValue = byteValue;
        integerValue = shortValue;


        long longValue = 50000+10*(byteValue+shortValue+integerValue);

    }
}