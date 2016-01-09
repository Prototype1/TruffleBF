package com.bf.lang;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by moritz on 09.01.2016.
 */
public class BFObjectTest {

    @Test
    public void isBFObjectTest() {
        boolean b = BFObject.isBFObject(new int[32]);

        Assert.assertEquals(true, b);
    }

}