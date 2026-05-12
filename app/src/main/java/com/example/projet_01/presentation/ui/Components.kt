package com.example.projet_01.presentation.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.projet_01.presentation.ui.theme.Projet_01Theme

@Composable
fun MyError(modifier: Modifier = Modifier, errorMessage:String? = null){
    AnimatedVisibility(!errorMessage.isNullOrBlank()) {
        Text(
            text = errorMessage ?: "",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onError,
            modifier = modifier.fillMaxWidth().background(MaterialTheme.colorScheme.error)
        )
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SearchScreenPreview() {
    Projet_01Theme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            MyError(
                modifier = Modifier.padding(innerPadding),
                errorMessage = "Aucune réponse"
            )
        }
    }

}