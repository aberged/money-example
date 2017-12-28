package com.company.money;

import com.company.money.core.Value;

import java.util.Currency;
import java.util.Locale;

public class Note {
    private double sum;
    private Currency currency;

    public Note(){
        this(0);
    }

    public Note(double sum){
        this(sum, Currency.getInstance(Locale.getDefault()));
    }

    public Note(double sum, Currency currency){
        this.sum = sum;
        this.currency = currency;
    }

    public double getSum() {
        return sum;
    }

    public Currency getCurrency() {
        return currency;
    }

    @Override
    public boolean equals(Object obj) {
        try {
            Value<Note> v = (Value<Note>) obj;
            return equals(v);
        }catch (Exception e){
            try {
                Note v = (Note) obj;
                return equals(v);
            }catch (Exception ex){
                return false;
            }
        }
    }

    public boolean equals(Note obj) {
        Note note = obj;
        return note.getSum() == this.sum &&
                    note.getCurrency().getCurrencyCode().equals(this.currency.getCurrencyCode());
    }

    public boolean equals(Value v){
        return equals(v.getValue());
    }

    @Override
    public String toString() {
        return Double.toString(sum) + " -> " + currency.toString();
    }

    public Note clone(){
        return new Note(this.getSum(), this.getCurrency());
    }
}
