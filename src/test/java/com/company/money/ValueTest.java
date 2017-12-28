package com.company.money;

import com.company.money.core.Value;
import org.junit.Test;

import static org.junit.Assert.*;

public class ValueTest {

    @Test
    public void values_are_equal_if_their_raw_values_are_equal(){
        Object o1 = 1;
        Object o2 = 1;
        Value v1 = new Value(o1);
        Value v2 = new Value(new Value(new Value(new Value(new Value(o2)))));
        Value v3 = new Value(3);
        assertTrue(v2.equals(v1));
        assertNotEquals(v3,v1);
        assertNotEquals(v3,v2);
    }


}
