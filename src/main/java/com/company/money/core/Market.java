package com.company.money.core;

public interface Market<T> {
    Value<T>   add      (Value<T> v1, Value<T> v2) throws MarketException;
    Value<T>   times    (Value<T> v, double n);
    int        compare  (Value<T> v1, Value<T> v2) throws MarketException;
    Value<T>   exchange (Value<T> v1, Object currency) throws MarketException;
}
