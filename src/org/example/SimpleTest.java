package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

public class SimpleTest {
    private static final Logger log = LogManager.getLogger();
    @Test
    public void testSumm() {
        log.info("Сейчас мы будем проверять сумму");
        Assert.assertEquals("11 + 11 = 22", 22, 11 + 11);
        log.info("Тест прошёл успешно!");
    }
    // FIXME Dependencies are too big! Use Maven!
}
