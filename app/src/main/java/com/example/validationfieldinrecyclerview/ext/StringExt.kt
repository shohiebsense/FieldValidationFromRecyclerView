package com.example.validationfieldinrecyclerview.ext

inline fun CharSequence.isNotEmpty(): Boolean{
    return toString().trim().length > 0
}