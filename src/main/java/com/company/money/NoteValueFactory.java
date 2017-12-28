package com.company.money;

import com.company.money.core.Value;

import java.util.Currency;
import java.util.Locale;

public final class NoteValueFactory {
    public static Value<Note> get(double n){
        return get(n, Currency.getInstance(Locale.getDefault()));
    }
    public static Value<Note> get(double n, String c){
        return get(n, Currency.getInstance(c));
    }
    public static Value<Note> get(double n, Currency c){
        return new Value<>(new Note(n, c));
    }
}
