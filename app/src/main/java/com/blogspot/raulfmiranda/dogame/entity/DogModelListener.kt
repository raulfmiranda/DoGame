package com.blogspot.raulfmiranda.dogame.entity

interface DogModelListener {
    fun requestRandomDogByBreed(breed: String)
    fun requestRandomDogBySubBreed(breed: String, subBreed: String)
    fun randomDogSuccessfullyRetrieved(dog: Dog)
}