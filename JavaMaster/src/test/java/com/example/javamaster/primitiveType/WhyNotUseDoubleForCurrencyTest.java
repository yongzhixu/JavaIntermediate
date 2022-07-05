package com.example.javamaster.primitiveType;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class WhyNotUseDoubleForCurrencyTest {

//    https://dzone.com/articles/never-use-float-and-double-for-monetary-calculatio
    @Test
    void doubleForCurrency() {

//        sample of loss of precision (or loss of significance)
        /**
         * The output should have been 20.20 (20 dollars and 20 cents),
         *      but the floating point calculation made it 20.19999999999996.
         * This is the loss of precision (or loss of significance).
         */
        double total = 0.2;
        for (int i = 0; i < 100; i++) {
            total += 0.2;
        }
        System.out.println("total = " + total);

    }

    @Test
    void bigDecimalForCurrency() {
        /**
         * BigDecimal for the Rescue.
         * BigDecimal represents a signed decimal number of arbitrary precision with an associated scale.
         * Virtually, it's possible to calculate the value of pi to 2 billion decimal places using BigDecimal, with available physical memory being the only limit.
         */

        /**
         * Special Notes
         * Primitive type: int and long are also useful for monetary calculations if the decimal precision is not required.
         *
         * We should really avoid using the BigDecimal (double value) constructor, and instead,
         * we should really prefer using the BigDecimal(String), because BigDecimal (0.1) results
         *       in (0.1000000000000000055511151231257827021181583404541015625) being stored in the BigDecimal instance.
         * In contrast, BigDecimal ("0.1") stores exactly 0.1.
         */

        /**
         * Precision is the total number of digits (or significant digits) of a real number.
         * Scale specifies the number of digits after the decimal place.
         * For example, 12.345 has the precision of 5 (total digits) and the scale of 3 (number of digits right of the decimal).
         */
        int scale = 4;
        double value = 0.11111;
        BigDecimal tempBig = new BigDecimal(Double.toString(value));
        tempBig = tempBig.setScale(scale, BigDecimal.ROUND_HALF_EVEN);
        System.out.println("0.1d = " + value);
        System.out.println("Double.valueOf(0.1d) = " + Double.valueOf(0.1d));
        System.out.println("new BigDecimal(Double.valueOf(0.1d)) = " + new BigDecimal(Double.valueOf(0.1d)));
        System.out.println("tempBig = " + tempBig);
        String strValue = tempBig.stripTrailingZeros().toPlainString();
        System.out.println("tempBig = " + strValue);
    }
}