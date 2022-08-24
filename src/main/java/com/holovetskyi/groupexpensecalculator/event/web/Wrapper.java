package com.holovetskyi.groupexpensecalculator.event.web;

import java.util.List;
import java.util.Set;

public class Wrapper<T> {
    private T data;

    public Wrapper(){}

    public Wrapper(T data){
        this.data = data;
    }

    public T getData(){
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
