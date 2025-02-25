package com.example.foodplannerapp.search_details.presenter;

import android.annotation.SuppressLint;

import com.example.foodplannerapp.data.models.GetMealsByFilterResponse;
import com.example.foodplannerapp.data.models.MealByFilter;
import com.example.foodplannerapp.data.repo.MealsRepositoryImpl;
import com.example.foodplannerapp.search_details.view.ViewInterface;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.SingleTransformer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class PresenterImpl {

    MealsRepositoryImpl mealsRepository;
    ViewInterface viewInterface;

    public PresenterImpl(MealsRepositoryImpl mealsRepository, ViewInterface viewInterface) {
        this.mealsRepository = mealsRepository;
        this.viewInterface = viewInterface;
    }

    @SuppressLint("CheckResult")
    public void getMealsByCategory(String categoryName) {

        mealsRepository.getAllMealsByCategory(categoryName).
                compose(apply()).
                subscribe(mealByFilters -> viewInterface.onSuccess(mealByFilters),
                        throwable -> viewInterface.onFailure(throwable.getMessage()));

    }

    @SuppressLint("CheckResult")
    public void getMealsByArea(String areaName) {

        mealsRepository.getAllMealsByArea(areaName).
                compose(apply()).
                subscribe(mealByFilters -> viewInterface.onSuccess(mealByFilters),
                        throwable -> viewInterface.onFailure(throwable.getMessage()));

    }

    @SuppressLint("CheckResult")
    public void getMealsByIngredient(String ingredientName) {

        mealsRepository.getAllMealsByIngredient(ingredientName).
                compose(apply()).
                subscribe(mealByFilters -> viewInterface.onSuccess(mealByFilters),
                        throwable -> viewInterface.onFailure(throwable.getMessage()));

    }


    public SingleTransformer<GetMealsByFilterResponse, List<MealByFilter>> apply() {

        return upstream ->
                upstream.
                        subscribeOn(Schedulers.io()).
                        observeOn(AndroidSchedulers.mainThread()).
                        map(getMealsByFilterResponse -> getMealsByFilterResponse.getMeals());


    }


}
