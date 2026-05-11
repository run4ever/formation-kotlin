package com.example.projet_01.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.example.projet_01.R
import com.example.projet_01.data.remote.WeatherEntity
import com.example.projet_01.presentation.ui.theme.Projet_01Theme
import com.example.projet_01.presentation.viewmodel.MainViewModel

@Composable
fun SearchScreen(modifier: Modifier = Modifier, model: MainViewModel = MainViewModel()) {
    Column(modifier = modifier) {
        modifier.background(Color.LightGray)
        Text(text = "Mon application Météo", fontSize = 20.sp)
        Spacer(Modifier.size(8.dp))
       // Text(text = "Text2", fontSize = 14.sp)
        model.dataList.collectAsStateWithLifecycle().value.forEach {
            PictureRowItem(data = it)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Preview(
    showBackground = true, showSystemUi = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES or android.content.res.Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun SearchScreenPreview() {
    Projet_01Theme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            SearchScreen(modifier = Modifier.padding(innerPadding))
        }
    }
}

@Composable
fun PictureRowItem(modifier: Modifier = Modifier, data: WeatherEntity) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = data.weather.firstOrNull()?.icon,
            contentDescription = "une photo de chat",
            contentScale = ContentScale.FillWidth,
            error = painterResource(R.drawable.error),
            placeholder = painterResource(R.drawable.loading),
            modifier = Modifier
                .heightIn(max = 100.dp)
                .widthIn(max = 100.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(
            modifier = Modifier
                .background(Color.White)
                .padding(8.dp)
                .fillMaxHeight()
                .fillMaxWidth()
        ) {
            Text(
                text = data.name.uppercase(),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            Text(
                text = data.getResume().take(15) + "...",
                color = Color.Blue,
                fontSize = 14.sp
            )
        }
    }
}