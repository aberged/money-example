package com.company.money;

import com.company.money.core.Market;
import com.company.money.core.MarketException;
import com.company.money.core.MarketExchange;
import com.company.money.core.Value;

import java.util.Currency;

public class MultiCurrencyMarket implements Market<Note> {

    private MarketExchange exchange = new NoteMarketExchange();

    public MultiCurrencyMarket(){}

    @Override
    public Value<Note> add(Value<Note> v1, Value<Note> v2) throws MarketException {
        Note n1 = v1.getValue();
        Note n2 = v2.getValue();
        Note note = new Note(n1.getSum()+n2.getSum()*getRatioFor(n1.getCurrency(), n2.getCurrency()), n1.getCurrency());
        return new Value<>(note);
    }

    @Override
    public Value<Note> times(Value<Note> v1, double n) {
        Note n1 = v1.getValue();
        Note note = new Note(n1.getSum()*n, n1.getCurrency());
        return new Value<>(note);
    }

    @Override
    public int compare(Value<Note> v1, Value<Note> v2) throws MarketException {
        Note n1 = v1.getValue();
        Note n2 = v2.getValue();
        double i1 = n1.getSum();
        double i2 = n2.getSum()*getRatioFor(n1.getCurrency(), n2.getCurrency());
        return i1 == i2? 0: i1 < i2 ? -1 : 1;
    }

    @Override
    public Value<Note> exchange(Value<Note> v1, Object currency) throws MarketException {
        try{
            Currency c = (Currency)currency;
            return exchange(v1, c);
        }catch (Exception e){
            throw new MarketException(e.getMessage());
        }
    }

    public Value<Note> exchange(Value<Note> v1, Currency currency) throws MarketException {
        Note n = v1.getValue();
        return new Value<>(new Note(n.getSum()*getRatioFor(currency, n.getCurrency()), currency));
    }

    private double getRatioFor(Currency currency1, Currency currency2) throws MarketException {
        return exchange.getRatio(currency1, currency2);
    }

}
