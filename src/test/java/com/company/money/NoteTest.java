package com.company.money;

import org.junit.Assert;
import org.junit.Test;

import java.util.Currency;
import java.util.Locale;

import static org.junit.Assert.*;

public class NoteTest {

    @Test
    public void notes_are_equal_when_sums_and_currencies_are_equal(){
        Note n1 = new Note(1, Currency.getInstance("CSD"));
        Note n2 = new Note(1, Currency.getInstance("CSD"));
        assertTrue(n1.equals(n2) && n2.equals(n1));
        n2 = new Note(1, Currency.getInstance("PGK"));
        assertFalse(n1.equals(n2));
    }

    @Test
    public void notes_with_only_sum_get_default_currency_for_locale(){
        Note n = new Note(1);
        assertTrue(n.getCurrency().equals(Currency.getInstance(Locale.getDefault())));
    }

}