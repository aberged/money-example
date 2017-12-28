package com.company.money;

import com.company.money.core.MarketException;
import com.company.money.core.MarketExchange;

import java.util.Currency;
import java.util.HashMap;

public class NoteMarketExchange implements MarketExchange<Currency> {

    private HashMap<Currency, HashMap<Currency, Double>> exchangeMap;

    public NoteMarketExchange(){
        exchangeMap = new HashMap<>();
        HashMap<Currency, Double> pgkMap = new HashMap<>();
        pgkMap.put(Currency.getInstance("CSD"), (double) 2);
        exchangeMap.put(Currency.getInstance("PGK"), pgkMap);
    }

    @Override
    public double getRatio(Currency c1, Currency c2) throws MarketException {
        if (c1.equals(c2)) return 1;
        if (exchangeMap.containsKey(c1)){
            HashMap<Currency, Double> c1exchangeMap = exchangeMap.get(c1);
            if (c1exchangeMap.containsKey(c2))
                return c1exchangeMap.get(c2);
        }
        if (exchangeMap.containsKey(c2)){
            HashMap<Currency, Double> c2exchangeMap = exchangeMap.get(c2);
            if (c2exchangeMap.containsKey(c1))
                return 1/c2exchangeMap.get(c1);
        }
        throw new MarketException("No exchange relation for values on the market! Currencies: "+c1.toString()+" and "+c2.toString());
    }

}
