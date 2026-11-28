package com.yarez.banking;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Single place for building and formatting money values.
 * All amounts are stored with scale 2 and HALF_EVEN rounding. Values are
 * created from String or integral values only; the double constructor of
 * BigDecimal is never used.
 */
public final class Money {

    public static final int SCALE = 2;
    public static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_EVEN;

    private Money() {
    }

    public static BigDecimal of(String value) {
        return normalize(new BigDecimal(value));
    }

    public static BigDecimal of(long value) {
        return normalize(BigDecimal.valueOf(value));
    }

    public static BigDecimal of(BigDecimal value) {
        return normalize(value);
    }

    public static BigDecimal normalize(BigDecimal value) {
        return value.setScale(SCALE, ROUNDING_MODE);
    }

    /**
     * Prints whole amounts without a decimal part and keeps cents when present,
     * preserving the original console layout.
     */
    public static String format(BigDecimal value) {
        BigDecimal stripped = value.stripTrailingZeros();
        if (stripped.scale() <= 0) {
            return stripped.setScale(0).toPlainString();
        }
        return value.setScale(SCALE).toPlainString();
    }
}
