package com.example.screenlogin

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun loginScreen(

){

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(painter = painterResource(id = R.drawable.a), contentDescription = "ImageLogin",
            modifier = Modifier.size(200.dp))
        
        Text(text = "Welcome Prisma VI", fontSize = 28.sp, fontWeight = FontWeight.Bold)
        
        Text(text = "Login Account")
        
        Spacer(modifier = Modifier.height(4.dp))
        
        OutlinedTextField(value = "", onValueChange = {}, label = {
            Text(text = "Email Address")
        })
        
        Spacer(modifier = Modifier.height(4.dp))

        OutlinedTextField(value = "", onValueChange = {}, label = {
            Text(text = "Password")
        })
        
        Spacer(modifier = Modifier.height(4.dp))
    
        Button(onClick = { /*TODO*/ }, colors = ButtonDefaults.buttonColors(Color.Magenta)) {
            Text(text = "Login")
            
        }

    }
}