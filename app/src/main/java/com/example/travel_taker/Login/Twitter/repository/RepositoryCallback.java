package com.example.travel_taker.Login.Twitter.repository;

public interface RepositoryCallback<T> {
    void onSuccess(T object);
    void onFailure(Throwable error);
}
