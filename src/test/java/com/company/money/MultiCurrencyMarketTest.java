package com.company.money;

import com.company.money.core.Market;
import com.company.money.core.MarketException;
import com.company.money.core.Value;
import org.junit.Assert;
import org.junit.Test;

import java.util.Currency;

import static org.junit.Assert.assertEquals;

public class MultiCurrencyMarketTest {

    Market<Note> mcm = new MultiCurrencyMarket();

    @Test
    public void test_addition() throws MarketException {

        Value<Note> novac1 = NoteValueFactory.get(1,"PGK");
        Value<Note> novac2 = NoteValueFactory.get(2,"CSD");
        Value<Note> value = mcm.add(novac1, novac2);
        Assert.assertTrue(NoteValueFactory.get(5, "PGK").equals(value));
        value = mcm.add(novac2, novac1);
        assertEquals(NoteValueFactory.get(2.5,"CSD"), value);
    }

    @Test
    public void test_multiplication(){
        Value v1 =  NoteValueFactory.get(1, "PGK");
        Value resValue = NoteValueFactory.get(2, "PGK");
        assertEquals(resValue, mcm.times(v1, 2));
    }

    @Test
    public void notes_in_market_are_equal_when_their_exchange_market_values_are_equal() throws MarketException {
        // PGK : CSD = 1 : 2
        Value<Note> novac1 = NoteValueFactory.get(2, "PGK");
        Value<Note> novac2 = NoteValueFactory.get(1, "CSD");
        assertEquals(0, mcm.compare(novac1, novac2));
        novac2 = NoteValueFactory.get(2, "CSD");
        assertEquals(1, mcm.compare(novac2, novac1));
    }

    @Test(expected = MarketException.class)
    public void market_addition_and_comparison_on_unknown_exchange_values_throws_market_exception() throws MarketException {
        Value v1 = new Value<>(new Note(1));
        Value v2 = NoteValueFactory.get(2, "CSD");
        mcm.compare(v1, v2);
    }

    @Test
    public void multiplication_can_work_with_exchange_values_unknown_to_the_market(){
        Value v1 =  new Value<>(new Note(1));
        Value resValue = new Value<>(new Note(2));
        assertEquals(resValue, mcm.times(v1, 2));
    }

    @Test
    public void market_exchange_should_return_equal_market_value() throws MarketException {
        Value v1 = NoteValueFactory.get(1, "CSD");
        assertEquals(0, mcm.compare(v1, mcm.exchange(v1, Currency.getInstance("PGK"))));
    }

}
