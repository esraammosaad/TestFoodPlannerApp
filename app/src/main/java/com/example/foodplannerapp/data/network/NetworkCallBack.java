package com.example.foodplannerapp.data.network;

import com.example.foodplannerapp.data.model.Meal;

import java.util.List;

public interface NetworkCallBack<T> {

    void onSuccess(Meal meal, List<T> list);
    void onFailure(String errorMessage);
}
