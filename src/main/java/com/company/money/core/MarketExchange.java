package com.company.money.core;

public interface MarketExchange<C> {
    double getRatio(C c1, C c2) throws MarketException;
}
