package com.sleepingcats.komposer.presentation.news

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.sleepingcats.komposer.domain.news.model.News
import com.sleepingcats.komposer.presentation.sharedComponent.composeGreetingView.Greeting
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import io.ktor.http.Url
import org.koin.compose.koinInject

@Composable
fun NewsView(viewModel: NewsViewModel = koinInject()) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.getNews()
    }

    Scaffold(modifier = Modifier.fillMaxSize()) {
        NewsContentView(modifier = Modifier.fillMaxSize().padding(it), state = state)
    }
}

@Composable
private fun NewsContentView(modifier: Modifier, state: NewsState) {
    Column(
        modifier = modifier
    ) {
        if (state.isLoading) {
            LoadingView(modifier = Modifier.fillMaxSize())
        }

        if (state.error != null) {
            ErrorView(modifier = Modifier.fillMaxSize(), message = state.error)
        }

        if (state.news.isNotEmpty()) {
            NewsListView(modifier = Modifier.fillMaxSize().padding(16.dp), news = state.news)
        }
    }
}

@Composable
private fun LoadingView(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun ErrorView(modifier: Modifier = Modifier, message: String) {
    Column(modifier = modifier) {
        Text(message)
    }
}

@Composable
private fun NewsListView(modifier: Modifier = Modifier, news: List<News>) {
    Column(modifier = modifier) {
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(Greeting().greet())
            Text("Here is today's news :")
        }

        Spacer(modifier = Modifier.padding(vertical = 8.dp))

        LazyColumn {
            items(news.size) { index ->
                NewsItem(news = news[index])
            }
        }
    }
}

@Composable
private fun NewsItem(news: News){
    Column(modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp)) {
        val imageResource = asyncPainterResource(
            data = Url(news.imageUrl)
        )

        KamelImage(
            resource = { imageResource },
            contentDescription = "Article Image of ${news.title}",
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.Crop,
            onLoading = {
                CircularProgressIndicator()
            },
            onFailure = {
                Text(it.message ?: "image request failed.")
            }
        )

        Spacer(Modifier.padding(bottom = 4.dp))

        Text(
            news.title,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}