package com.example.projet_01.presentation.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projet_01.presentation.ui.theme.Projet_01Theme

@Composable
fun SearchScreen(modifier:Modifier = Modifier) {
    Column(modifier= modifier) {
        println("SearchScreen()")
        Text(text = "Text1",fontSize = 20.sp)
        Spacer(Modifier.size(8.dp))
        Text(text = "Text2",fontSize = 14.sp)
        repeat(2){
            PictureRowItem(content = "Hello $it", color = Color.Blue)
        }
        PictureRowItem(content = "Hello from PictureRowItem", color = Color.Blue)
        PictureRowItem(content = "Hello from 2", color = Color.Red)
        PictureRowItem(content = "Hello from 33", color = Color.Green)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Preview(showBackground = true, showSystemUi = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES or android.content.res.Configuration.UI_MODE_TYPE_NORMAL)
@Composable
fun SearchScreenPreview() {
    Projet_01Theme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            SearchScreen(modifier = Modifier.padding(innerPadding))
        }
    }
}

@Composable
fun PictureRowItem(modifier:Modifier = Modifier, content:String, color: Color) {
    Text(text = content, fontSize = 20.sp, color = color)
}