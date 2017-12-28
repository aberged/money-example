package com.company.money;

import com.company.money.core.MarketException;
import com.company.money.core.MarketExchange;
import org.junit.Test;

import java.util.Currency;

import static org.junit.Assert.assertTrue;

public class NoteMarketExchangeTest {

    @Test
    public void test_get_ratio() throws MarketException {
        MarketExchange mex = new NoteMarketExchange();
        Currency c1 = Currency.getInstance("CSD");
        Currency c2 = Currency.getInstance("PGK");
        assertTrue(mex.getRatio(c1, c2) == 1 / mex.getRatio(c2, c1));
    }

}
