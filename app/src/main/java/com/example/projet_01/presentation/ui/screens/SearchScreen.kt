package com.example.projet_01.presentation.ui.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.example.projet_01.R
import com.example.projet_01.data.remote.WeatherEntity
import com.example.projet_01.presentation.ui.theme.Projet_01Theme
import com.example.projet_01.presentation.viewmodel.MainViewModel

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    model: MainViewModel = viewModel()
) {
    val myList = model.dataList.collectAsStateWithLifecycle().value
    var searchText = model.searchText.collectAsStateWithLifecycle().value

    //val myFilteredList = myList.filter { it.name.contains(searchText, ignoreCase = true) }
    Column(modifier = modifier) {
        modifier.background(Color.LightGray)
        Text(text = "Mon application Météo", fontSize = 20.sp)
        Spacer(Modifier.size(8.dp))
        // LazyColumn : chargement infini sur demande en remplacement du précédent for each qui affiche tous les éléments tout de suite
        SearchBar(
            searchText = searchText,
            onValueChange = { model.updateSearchText(it) },
            onSearchAction = { model.loadWeathers(searchText) }
        )
        LazyColumn(
            modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(myList.size) {
                PictureRowItem(data = myList[it])
            }
        }
        Row(
            modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally)
        ){

        Button(
            onClick = { searchText = "" },
            contentPadding = ButtonDefaults.ButtonWithIconContentPadding
        ) {
            Icon(
                Icons.Filled.Clear,
                contentDescription = "Clear",
                modifier = Modifier.size(ButtonDefaults.IconSize)
            )
            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
            Text(stringResource(R.string.btn_clear_filter))
        }
        Button(
            onClick = { model.loadWeathers(searchText) },
            contentPadding = ButtonDefaults.ButtonWithIconContentPadding
        ) {
            Icon(
                Icons.AutoMirrored.Filled.Send,
                contentDescription = "Load",
                modifier = Modifier.size(ButtonDefaults.IconSize)
            )
            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
            Text(stringResource(R.string.btn_load_data))
        }

        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Preview(
    showBackground = true, showSystemUi = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES or android.content.res.Configuration.UI_MODE_TYPE_NORMAL,
    locale = "fr",
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
    var fullDisplay by remember { mutableStateOf(false) }
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
            onError = {
                println(it)
            },
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
                .clickable(
                    interactionSource = null,
                    indication = null
                ) { fullDisplay = !fullDisplay }
        ) {
            Text(
                text = data.name.uppercase(),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            Text(
                text = if(fullDisplay) data.getResume() else data.getResume().take(15) + "...",
                modifier.animateContentSize(),
                color = MaterialTheme.colorScheme.secondary,
                fontSize = 14.sp,
            )
        }
    }
}

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    searchText: String,
    onValueChange: (String) -> Unit,
    onSearchAction: () -> Unit = {},
) {

    TextField(
        value = searchText, //Valeur affichée
        onValueChange = onValueChange, //Nouveau texte entré
        leadingIcon = { //Image d'icône
            Icon(
                imageVector = Icons.Default.Search,
                tint = MaterialTheme.colorScheme.primary,
                contentDescription = null
            )
        },
        singleLine = true,
        label = { //Texte d'aide qui se déplace
            Text(stringResource(R.string.search_placeholder))
        },
        trailingIcon = {
            if (searchText.isNotEmpty()) {
                IconButton(onClick = { onValueChange("") }) {
                    Icon(Icons.Default.Clear, contentDescription = "Effacer")
                }
            }
        },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = { onSearchAction() }),
        modifier = modifier
            .fillMaxWidth() // Prend toute la largeur
            .heightIn(min = 56.dp) //Hauteur minimum
    )
}