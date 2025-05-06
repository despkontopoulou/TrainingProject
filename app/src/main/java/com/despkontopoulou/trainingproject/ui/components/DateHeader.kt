package com.despkontopoulou.trainingproject.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.despkontopoulou.trainingproject.ui.theme.LightBlack
import com.despkontopoulou.trainingproject.ui.theme.White

@Composable
fun DateHeader(
    monthYear: String,
    modifier: Modifier = Modifier
){
    Box(
        modifier=modifier
            .fillMaxWidth()
            .background(LightBlack)
            .padding(8.dp)
    ){
        Text(
            text=monthYear,
            color=White,
            fontSize=18.sp
        )
    }
}

@Preview
@Composable
fun PreviewDateHeader(){
    DateHeader(monthYear = "Nov 2025")
}