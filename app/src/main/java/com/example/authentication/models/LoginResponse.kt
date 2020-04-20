package com.example.authentication.models

data class LoginResponse (val success: String, val userId: Int, val token: String)