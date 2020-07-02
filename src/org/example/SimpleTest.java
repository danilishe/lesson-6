package org.example;

import org.junit.Assert;
import org.junit.Test;

public class SimpleTest {
    @Test
    public void testSumm() {
        Assert.assertEquals("11 + 11 = 22", 22, 11 + 11);
    }
    // FIXME Test passes, but too fast! I don't understand what's happened!
    // TODO:
    //  1) Download Log4j2
    //  2) Add to libs
    //  3) Update Idea class path
}
