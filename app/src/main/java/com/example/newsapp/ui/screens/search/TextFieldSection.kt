package com.example.newsapp.ui.screens.search

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.newsapp.R
import com.example.newsapp.ui.theme.Typography
import com.example.newsapp.ui.theme.darktext
import com.example.newsapp.ui.theme.roundedShape
import com.example.newsapp.ui.theme.spacing
import com.example.newsapp.viewmodel.SearchViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun TextFieldSection(
    searchViewModel: SearchViewModel,
    onTextReady:(String)->Unit
) {

    var newsText by rememberSaveable {
        mutableStateOf("")
    }

    var searchJob by remember { mutableStateOf<Job?>(null) }
    val courotinScope = rememberCoroutineScope()

    Column {

        BasicTextField(
            textStyle = TextStyle(
                color = MaterialTheme.colorScheme.darktext,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                lineHeight = 25.sp
            ),
            value = newsText,
            onValueChange = {
                newsText = it
                searchJob?.cancel()
                searchJob = courotinScope.launch {
                    delay(1000)
                    if (searchViewModel.validateQuery(newsText)) {
                        onTextReady(newsText)
                        searchViewModel.getNewsBySearch(newsText)
                    }

                }

            },
            cursorBrush = Brush.horizontalGradient(
                listOf(
                    MaterialTheme.colorScheme.darktext.copy(0.5f),
                    MaterialTheme.colorScheme.darktext.copy(0.5f)
                )
            ),
            modifier = Modifier
                .padding(
                    top = MaterialTheme.spacing.medium,
                    start = MaterialTheme.spacing.semiLarge,
                    end = MaterialTheme.spacing.semiLarge
                )
                .fillMaxWidth(),
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            brush = Brush.verticalGradient(
                                listOf(
                                    Color(0xFFe51324),
                                    Color(0xFF83000B)
                                )
                            ),
                            width = 1.dp,
                            shape = MaterialTheme.roundedShape.biggerSmall
                        )
                        .padding(MaterialTheme.spacing.medium)
                ) {
                    if (newsText.isNullOrBlank() || newsText.isNullOrEmpty()) {
                        Text(
                            text = stringResource(R.string.search_txt),
                            color = MaterialTheme.colorScheme.darktext.copy(0.5f),
                            style = Typography.displaySmall
                        )
                    }
                    innerTextField()
                }
            }
        )
    }

}