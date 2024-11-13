package com.example.screenlogin.model

import ErrorResponse
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.screenlogin.RetrofitConfig
import com.example.screenlogin.repository.RegisterRequest
import com.example.screenlogin.repository.RegisterResponse
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterViewModel : ViewModel() {
    private val registerStatus = MutableLiveData<RegisterStatus>(RegisterStatus.Loading)
    val registerState: LiveData<RegisterStatus> = registerStatus

    fun register(nickName: String, email: String, password: String, ) {


        // Validações locais
        if (nickName.isBlank() || email.isBlank() || password.isBlank()) {
            registerStatus.value = RegisterStatus.Error("Todos os campos são obrigatórios.")
            return
        }

        if (nickName.contains(" ") || email.contains(" ") || password.contains(" ")) {
            registerStatus.value =
                RegisterStatus.Error("Nome de usuário, email e senha não podem conter espaços.")
            return
        }

        val registerRequest = RegisterRequest(nickName, email, password)

        // Faz a requisição para o servidor
        RetrofitConfig.apiService.register(registerRequest)
            .enqueue(object : Callback<RegisterResponse> { override fun onResponse(call: Call<RegisterResponse>,
                    response: Response<RegisterResponse>
                ) {
                    Log.d("RegisterViewModel", "HTTP Status Code: ${response.code()}")
                    if (response.isSuccessful) {
                        val registerResponse = response.body()
                        registerStatus.value = RegisterStatus.Success(
                            registerResponse?.message ?: "Registrado com sucesso."
                        )
                    } else {
                        val errorBody = response.errorBody()?.string()
                        val errorMessage = if (!errorBody.isNullOrEmpty()) {
                            errorBody
                        } else {
                            "Erro ao registrar."
                        }
                        registerStatus.value = RegisterStatus.Error(errorMessage)
                    }
                }

                override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                    registerStatus.value = RegisterStatus.Error("Erro de conexão: ${t.message}")
                }
            })

    }

    // Estados de Registro
    sealed class RegisterStatus {
        object Loading : RegisterStatus()
        data class Success(val message: String) : RegisterStatus()
        data class Error(val message: String) : RegisterStatus()
    }

}

