package com.despkontopoulou.trainingproject.ui.screens

import android.graphics.drawable.Icon
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.despkontopoulou.trainingproject.ui.theme.Green
import com.despkontopoulou.trainingproject.ui.theme.Grey
import com.despkontopoulou.trainingproject.ui.theme.TrainingProjectTheme
import com.despkontopoulou.trainingproject.ui.theme.White

@Composable
fun NavigationBar(){
    Row(
        modifier= Modifier
            .fillMaxWidth()
            .height(75.dp)
            .background(color= White),
        verticalAlignment = Alignment.CenterVertically
    ){
        val items = listOf(
            Triple("Magazine", Icons.Default.Menu,true),
            Triple("Scan",Icons.Default.Search,false),
            Triple("Profile",Icons.Default.Person,false),
            Triple("Settings",Icons.Default.Settings, false)
        )

        items.forEach{(label, icon, selected) ->
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                Icon(
                    imageVector = icon,
                    contentDescription=label,
                    tint= if(selected) Green else Grey
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = label,
                    color = if (selected) Green else Grey,
                    fontSize = 12.sp
                )
            }
        }
    }
}
