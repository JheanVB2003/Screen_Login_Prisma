
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.screenlogin.RetrofitConfig
import com.example.screenlogin.repository.LoginRequest
import com.example.screenlogin.repository.LoginResponse
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {
    // Estado que mantém o status da requisição (Loading, Success, Error)
    private val loginStatus = MutableLiveData<LoginStatus>(LoginStatus.Loading)
    val loginState: LiveData<LoginStatus> = loginStatus


    fun login(email: String, password: String) {
        // Inicializa o status para 'Loading' enquanto faz a requisição
        loginStatus.value = LoginStatus.Loading


        // Verifique se os campos não estão vazios
        if (email.isBlank() || password.isBlank()) {
            loginStatus.value = LoginStatus.Error("Email e Senha são obrigatórios.")
            return
        }

        val loginRequest = LoginRequest(email, password)

        // Faz a requisição para o servidor
        RetrofitConfig.apiService.login(loginRequest).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                Log.d("LoginViewModel", "HTTP Status Code: ${response.code()}")
                // Se a resposta for bem-sucedida (status 200)
                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    if (loginResponse != null && loginResponse.token != null) {
                        val token = loginResponse.token
                        loginStatus.value = LoginStatus.Success(token)
                    } else {
                        loginStatus.value = LoginStatus.Error("Resposta inválida da API: Token não encontrado.")
                    }
                } else {// Tenta capturar a mensagem de erro do corpo da resposta
                val errorBody = response.errorBody()?.string()
                val errorMessage = if (!errorBody.isNullOrEmpty()) {
                    try {
                        // Usa o Gson para extrair a mensagem de erro, caso o JSON tenha uma estrutura específica
                        val gson = Gson()
                        val errorJson = gson.fromJson(errorBody, ErrorResponse::class.java)
                        errorJson.message ?: "Credenciais inválidas." // Ajuste conforme o formato da API
                    } catch (e: Exception) {
                        "Credenciais inválidas."
                    }
                } else {
                    "Credenciais inválidas."
                }
                loginStatus.value = LoginStatus.Error(errorMessage)
            }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                // Caso ocorra um erro de rede ou de conexão
                loginStatus.value = LoginStatus.Error("Erro de conexão: ${t.message}")
            }
        })
    }
}
// Representa os diferentes estados do login: Carregando, Sucesso e Erro
sealed class LoginStatus {
    object Loading : LoginStatus()
    data class Success(val token: String?) : LoginStatus()
    data class Error(val message: String) : LoginStatus()
}
// Classe auxiliar para capturar a mensagem de erro da resposta
data class ErrorResponse(
    val message: String?
)

