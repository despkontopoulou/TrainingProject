package com.despkontopoulou.trainingproject.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import com.despkontopoulou.trainingproject.ui.components.InputField
import com.despkontopoulou.trainingproject.ui.components.SignInButton
import com.despkontopoulou.trainingproject.ui.components.TitleBar
import com.despkontopoulou.trainingproject.ui.theme.LightBlack


@Composable
fun LoginScreen(){
    var userId by remember {mutableStateOf("")}
    var password by remember{ mutableStateOf("")}

    val userIdInvalid = userId.isNotEmpty() && !userId.contains("TH")
    val passwordInvalid = password.isNotEmpty() && password.length < 5
    val isFormValid = userId.contains("TH") && password.length >= 5

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(LightBlack)
    ){
        TitleBar(
            title="Sign In",
            modifier= Modifier.statusBarsPadding()
        )

        Column(
            modifier = Modifier.padding(16.dp)
                .fillMaxSize()
                .padding(horizontal=16.dp)
        ) {

            Spacer(modifier = Modifier.height(200.dp))

            InputField(
                value = userId,
                onValueChange = { userId = it },
                label = "UserId",
                placeholder = "Enter your UserID",
                isPassword = false,
                isValid = !userIdInvalid
            )

            Spacer(modifier = Modifier.height(50.dp))

            InputField(
                value = password,
                onValueChange = { password = it },
                label = "Password",
                placeholder = "Enter your password",
                isPassword = true,
                isValid = !passwordInvalid
            )

            Spacer(modifier = Modifier.weight(1f))

            SignInButton(
                onClick = { /* Handle sign-in */ },
                enabled = isFormValid,
                modifier = Modifier
                    .padding(bottom = 29.dp)
                    .align(Alignment.CenterHorizontally)
            )
        }
    }
}
