package com.despkontopoulou.trainingproject.ui.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.despkontopoulou.trainingproject.api.ApiClient
import com.despkontopoulou.trainingproject.api.LoginRequest
import com.despkontopoulou.trainingproject.ui.components.ErrorPopup
import com.despkontopoulou.trainingproject.ui.components.InfoPopup
import com.despkontopoulou.trainingproject.ui.components.InputField
import com.despkontopoulou.trainingproject.ui.components.SignInButton
import com.despkontopoulou.trainingproject.ui.components.TitleBar
import com.despkontopoulou.trainingproject.ui.theme.LightBlack
import com.despkontopoulou.trainingproject.utils.ValidationUtils
import kotlinx.coroutines.launch

private const val TAG = "LoginScreen"

@Composable
fun LoginScreen(onSignIn: (userId: String, password: String, token: String) -> Unit) {
    var userId by remember {mutableStateOf("")}
    var password by remember{ mutableStateOf("")}

    var showError by remember{ mutableStateOf(false)}
    var showUserIdInfo by remember { mutableStateOf(false) }
    var showPasswordInfo by remember { mutableStateOf(false)}

    val userIdInvalid = userId.isNotEmpty() && !ValidationUtils.userIdRegex.matches(userId)
    val passwordInvalid = password.isNotEmpty() && !ValidationUtils.passwordRegex.matches(password)
    val isFormValid = ValidationUtils.userIdRegex.matches(userId) && ValidationUtils.passwordRegex.matches(password)

    val scope = rememberCoroutineScope()
    val context= LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(LightBlack)
    ){
        if (showUserIdInfo) {
        InfoPopup(
            message = "It must start with 2 capital letters and then 4 numbers",
            onDismiss = { showUserIdInfo = false }
        )
    }
        if (showPasswordInfo) {
            InfoPopup(
                message = "At least 8 characters (2 uppercase, 3 lowercase, 1 special, 2 numbers)",
                onDismiss = { showPasswordInfo = false }
            )
        }
        if(showError){
            ErrorPopup { showError=false }
        }
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
                isValid = !userIdInvalid,
                onInfoClick={showUserIdInfo=true}
            )

            Spacer(modifier = Modifier.height(50.dp))

            InputField(
                value = password,
                onValueChange = { password = it },
                label = "Password",
                placeholder = "Enter your password",
                isPassword = true,
                isValid = !passwordInvalid,
                onInfoClick={showPasswordInfo=true}

            )

            Spacer(modifier = Modifier.weight(1f))

            SignInButton(
                onClick = {
                    scope.launch {
                        try {
                            val response = ApiClient.authApi.login(
                                LoginRequest(UserName = userId, Password = password)
                            )
                            Log.d(TAG, "Login response:\n$response")
                            Toast.makeText(
                                context,
                                "Token: ${response.access_token}",
                                Toast.LENGTH_LONG
                            ).show()

                            onSignIn(userId, password, response.access_token)
                        } catch (e: Exception) {
                            showError = true
                        }
                    }
                },
                enabled = isFormValid,
                modifier = Modifier
                    .padding(bottom = 29.dp)
                    .align(Alignment.CenterHorizontally)
            )
        }
    }
}
