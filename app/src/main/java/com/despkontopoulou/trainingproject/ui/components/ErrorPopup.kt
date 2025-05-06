package com.despkontopoulou.trainingproject.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.despkontopoulou.trainingproject.ui.theme.Black
import com.despkontopoulou.trainingproject.ui.theme.Green
import com.despkontopoulou.trainingproject.ui.theme.LightBlack
import com.despkontopoulou.trainingproject.ui.theme.White

@Composable
fun ErrorPopup(
    onDismiss: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Black.copy(alpha = 0.6f)),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            color = LightBlack,
            shape = MaterialTheme.shapes.medium,
            tonalElevation = 8.dp,
            modifier = Modifier
                .padding(32.dp)
                .fillMaxWidth(0.8f)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Wrong Credentials",
                    color = White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    text = "You have entered incorrect credentials",
                    color = White,
                    fontSize = 14.sp
                )
                Spacer(Modifier.height(8.dp))
                Divider(color = Green, thickness = 2.dp)
                Spacer(Modifier.height(8.dp))
                Text(
                    text = "Back",
                    color = Green,
                    fontSize = 14.sp,
                    modifier = Modifier
                        .clickable { onDismiss() }
                        .padding(8.dp)
                )
            }
        }
    }
}