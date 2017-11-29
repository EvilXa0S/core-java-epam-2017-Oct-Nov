package com.epam.courses.jf.se3;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import static org.junit.Assert.*;

public class CharSequenceTestTest {

    @Test
    public void oneNonBmtCharacter() throws UnsupportedEncodingException {
        char ch = '现';

        char symbol = '\u73b0';
        System.out.println(symbol);
        assertEquals(0x73b0, +ch);
        assertEquals("73b0", Integer.toHexString(ch));

        String str = new String(new char[]{ch});
        assertEquals(1, str.length());

        byte[] bytes = str.getBytes(Charset.forName("UTF-8"));
        assertEquals(3, bytes.length);
        assertEquals((byte) 0xe7, bytes[0]);
        assertEquals((byte) 0x8e, bytes[1]);
        assertEquals((byte) 0xb0, bytes[2]);

        String a = null;
        System.out.println(a);
        String.valueOf(a);
    }
}