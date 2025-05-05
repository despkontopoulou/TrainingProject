package com.despkontopoulou.trainingproject.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.despkontopoulou.trainingproject.ui.theme.Green
import com.despkontopoulou.trainingproject.ui.theme.Red
import com.despkontopoulou.trainingproject.ui.theme.White

@Composable
fun InputField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    isPassword: Boolean = false,
    isValid: Boolean
){
    var passwordVisible by remember {mutableStateOf(false)}
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            verticalAlignment =  Alignment.CenterVertically,
            horizontalArrangement= Arrangement.spacedBy(4.dp),
            modifier = Modifier.padding(bottom=4.dp)
        ){
            Text(
                text=label,
                color= White,
                fontSize=16.sp,
                fontWeight = FontWeight.Bold
            )
            Icon(
                imageVector = Icons.Default.Info,
                contentDescription = "Info",
                tint=Green,
                modifier=Modifier.size(18.dp)
            )
        }
        BasicTextField(
            value = value,
            onValueChange=onValueChange,
            textStyle= TextStyle(color=White, fontSize = 14.sp),
            visualTransformation = if (isPassword && !passwordVisible)
                PasswordVisualTransformation()
            else
                VisualTransformation.None,
            singleLine = true,
            decorationBox={ innerTextField ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                    ){
                    Box(
                        modifier=Modifier
                            .weight(1f)
                    ){
                        if (value.isEmpty()){
                            Text(
                                text = placeholder,
                                color = White.copy(alpha = 0.5f),
                                fontSize= 14.sp
                            )
                        }
                        innerTextField()
                    }

                    if (isPassword){
                        Text(
                            text= if (passwordVisible) "Hide" else "Show",
                            color = Green,
                            fontSize = 14.sp,
                            modifier = Modifier
                                .clickable {passwordVisible = !passwordVisible}
                        )

                    } else if (!isValid){
                        Icon(
                            imageVector = Icons.Default.Warning,
                            contentDescription = "Invalid input",
                            tint=Red,
                            modifier=Modifier.size(20.dp)
                        )
                    }

                }
            },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(2.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
                .background(if (isValid) Green else Red)
        )
    }

}
@Preview(showBackground=true)
@Composable
fun PreviewInputField(){
    var text by remember { mutableStateOf("")}
    val isValid= text.length >=5
    Box(modifier=Modifier.padding(16.dp)){
        InputField(
            value=text,
            onValueChange = {text=it},
            label="Password",
            placeholder="Enter password",
            isPassword = true,
            isValid= isValid
        )
    }
}