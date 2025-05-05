package com.despkontopoulou.trainingproject.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.*
import com.despkontopoulou.trainingproject.ui.theme.Green
import com.despkontopoulou.trainingproject.ui.theme.LightBlack
import com.despkontopoulou.trainingproject.ui.theme.TrainingProjectTheme

@Composable
fun SignInButton(
    onClick: () -> Unit,
    enabled: Boolean,
    modifier: Modifier = Modifier
){
    Button(
        onClick = onClick,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = LightBlack,
            contentColor = Green,
            disabledContainerColor = LightBlack.copy(alpha = 0.4f),
            disabledContentColor = Green.copy(alpha = 0.6f)
        ),
        shape = MaterialTheme.shapes.medium,
        modifier = modifier
            .height(41.dp)
            .border(1.dp, Green, MaterialTheme.shapes.medium)
            .width(200.dp)
    ){
        Text(
            text= "Sign In",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
