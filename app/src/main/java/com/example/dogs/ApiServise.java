package com.example.dogs;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;

public interface ApiServise {
    @GET("random")
    Single <DogsImage> loadDogImage();
}
