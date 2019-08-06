package com.ebaotech.study.test;

import org.junit.Test;

/**
 * @author: BlueMelancholy
 * 2019/8/5 10:37
 * @desc:
 */
public class EhcacheTest {
    @Test
    public void testA(){
        String path = System.getProperty("java.io.tmpdir");
        System.out.println(path);
    }
}
