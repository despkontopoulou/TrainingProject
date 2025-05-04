package com.despkontopoulou.trainingproject.ui.components
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.despkontopoulou.trainingproject.ui.theme.Black
import com.despkontopoulou.trainingproject.ui.theme.White

@Composable
fun TitleBar(title: String){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Black)
            .padding(vertical = 29.dp),
        contentAlignment = Alignment.Center
    ){
        Text(
            text = title,
            color = White,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TitleBarPreview(){
    TitleBar(title= "Sign In")
}