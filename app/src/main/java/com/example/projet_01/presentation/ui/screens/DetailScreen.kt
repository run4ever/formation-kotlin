package com.example.projet_01.presentation.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.projet_01.R
import com.example.projet_01.data.remote.TempEntity
import com.example.projet_01.data.remote.Weather
import com.example.projet_01.data.remote.WeatherEntity
import com.example.projet_01.data.remote.WindEntity
import com.example.projet_01.presentation.ui.theme.Projet_01Theme

@Preview(showBackground = true, showSystemUi = true, locale = "fr")
@Preview(showBackground = true, showSystemUi = true, locale = "fr",
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES
            or android.content.res.Configuration.UI_MODE_TYPE_NORMAL)
@Composable
fun DetailScreenPreview() {
    Projet_01Theme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            DetailScreen(
                modifier = Modifier.padding(innerPadding),
                //jeu de donnée pour la Preview
                data = WeatherEntity(
                    id = 2,
                    name = "Toulouse",
                    main = TempEntity(temp = 22.3),
                    weather = listOf(
                        Weather(
                            description = "partiellement nuageux",
                            icon = "https://picsum.photos/201"
                        )
                    ),
                    wind = WindEntity(speed = 3.2)
                ),
                onBackButtonClick = {}
            )
        }
    }
}
@Composable //id du WeatherEntity à afficher
fun DetailScreen(
    modifier: Modifier = Modifier,
    data: WeatherEntity,
    onBackButtonClick: () -> Unit,
){

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
        ) {
        Text(
            text = data.name.uppercase(),
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        AsyncImage(
            model = data.weather.firstOrNull()?.icon,
            contentDescription = "",
            contentScale = ContentScale.FillWidth,
            error = painterResource(R.drawable.error),
            placeholder = painterResource(R.drawable.loading),
            onError = {
                println(it)
            },
            modifier = Modifier
                .heightIn(max = 100.dp)
                .widthIn(max = 100.dp)
        )
        Text(
            text = data.getResume(),
            color = MaterialTheme.colorScheme.secondary,
            fontSize = 14.sp,
        )
        Button(
            onClick = { onBackButtonClick() },
            contentPadding = ButtonDefaults.ButtonWithIconContentPadding
        ) {
            Icon(
                Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
                modifier = Modifier.size(ButtonDefaults.IconSize)
            )
            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
            Text(stringResource(R.string.btn_detail_back))
        }
    }

}