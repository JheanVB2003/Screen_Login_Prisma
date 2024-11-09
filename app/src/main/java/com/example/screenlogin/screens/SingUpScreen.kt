package com.example.screenlogin.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.screenlogin.R

@Composable
fun singUpScreen(

){
    var nickname by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(painter = painterResource(id = R.drawable.a),
            contentDescription = "ImageLogin",
            modifier = Modifier.size(170.dp),
        )

        Text(text = "Welcome Prisma VI", fontSize = 28.sp, fontWeight = FontWeight.Bold)


        Spacer(modifier = Modifier.height(4.dp))

        OutlinedTextField(value = nickname,
            onValueChange = {nickname = it},
            label = { Text(text = "Nickname") },
            singleLine = true,
            leadingIcon = {
                IconButton(onClick = {/*TODO*/}) {
                    Icon(imageVector = Icons.Filled.AccountCircle, contentDescription = "User Icon")
                }
            }
        )

        Spacer(modifier = Modifier.height(4.dp))

        OutlinedTextField(value = email,
            onValueChange = {email = it},
            label = { Text(text = "Email Address") },
            singleLine = true,
            leadingIcon = {
                IconButton(onClick = {/*TODO*/}) {
                    Icon(imageVector = Icons.Filled.Email, contentDescription = "User Icon")
                }
            }
        )

        Spacer(modifier = Modifier.height(4.dp))

        OutlinedTextField(value = password,
            onValueChange = {password = it},
            label = { Text(text = "Password") },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            leadingIcon = {
                IconButton(onClick = {/*TODO*/}) {
                    Icon(imageVector = Icons.Filled.Lock, contentDescription = "Password Icon")
                }
            }
        )

        Spacer(modifier = Modifier.height(4.dp))

        Button(onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF62D2A3)
            )
        ) {
            Text(text = "Registe")

        }


    }
}