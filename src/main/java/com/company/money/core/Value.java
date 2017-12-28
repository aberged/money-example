package com.company.money.core;

public class Value<T>{

    private T value;

    //prevents empty initialization
    private Value() {}

    public Value(T rawValue){
        this.value = rawValue;
    }

    public Value(Value<T> v){
        this.value = v.getValue();
    }

    public T getValue(){
        if (this.value instanceof Cloneable){
            try{
                T v = (T) this.value.getClass().getMethod("clone").invoke(this.value);
                return v;
            }catch (Exception e){}
        }
        return this.value;
    }

    public boolean equals(Value<T> v){
        return v.getValue().equals(this.value);
    }

    @Override
    public boolean equals(Object obj) {
        try{
            return ((T)obj).equals(this.value);
        }catch (Exception ex){
            try{
                return equals((Value<T>)obj);
            }catch (Exception e) {
                return this.value.equals(obj);
            }
        }
    }

    @Override
    public String toString() {
        return this.value.toString();
    }
}
