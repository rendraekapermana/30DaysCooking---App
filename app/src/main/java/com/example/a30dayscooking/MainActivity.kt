package com.example.a30dayscooking

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandIn
import androidx.compose.animation.shrinkOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.a30dayscooking.data.DataSource
import com.example.a30dayscooking.model.Food
import com.example.a30dayscooking.model.Profile
import com.example.a30dayscooking.ui.theme.Purple
import com.example.a30dayscooking.ui.theme.SoftPurple
import com.example.a30dayscooking.ui.theme._30DaysCookingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            _30DaysCookingTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    CookingApp()
                }
            }
        }
    }
}

@Composable
fun CookingApp() {
    Scaffold(
        topBar = {
            TopAppBar()
        }
    ) { paddingValues ->
        LazyColumn(contentPadding = paddingValues) {
            itemsIndexed(DataSource.cuisine) { index, food ->
                CookingCard(
                    food = food,
                    profile = DataSource.profile.getOrNull(index) ?: Profile(
                        nameRes = R.string.profile1,
                        uploadRes = R.string.upload1,
                        profileRes = R.drawable.pevita_pearce
                    ),
                    onClick = {},
                    expanded = false,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar() {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.displayLarge,
                color = Color.White
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Purple
        )
    )
}

@Composable
fun CookingCard(
    food: Food,
    profile: Profile,
    onClick: () -> Unit,
    expanded: Boolean,
    modifier: Modifier = Modifier
) {
    var isExpanded by remember { mutableStateOf(false) }
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(SoftPurple)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row {
                Image(
                    painter = painterResource(profile.profileRes),
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(64.dp),
                    contentScale = ContentScale.Crop,
                    contentDescription = null
                )

                Column {
                    Text(
                        text = stringResource(profile.nameRes),
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(start = 8.dp, top = 8.dp, bottom = 0.dp)
                    )
                    Text(
                        text = stringResource(profile.uploadRes),
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(start = 8.dp, top = 0.dp)
                    )
                }
            }
            Row {
                Text(
                    text = stringResource(food.foodRes),
                    style = MaterialTheme.typography.displaySmall,
                    modifier = Modifier.padding(top = 8.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
                IconButton(
                    onClick = {
                        isExpanded = !isExpanded
                        onClick()
                    },
                    modifier = modifier
                ) {
                    Icon(
                        painter = painterResource(if (isExpanded) R.drawable.expand_less else R.drawable.expand_more),
                        contentDescription = stringResource(food.receiptRes)
                    )
                }
            }
            Image(
                painter = painterResource(food.imageRes),
                modifier = Modifier
                    .fillMaxWidth()
                    .size(150.dp)
                    .clip(MaterialTheme.shapes.small),
                contentScale = ContentScale.Crop,
                contentDescription = null
            )
        }
        AnimatedVisibility(
            visible = isExpanded,
            enter = expandIn(),
            exit = shrinkOut(),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, bottom = 16.dp)
            ) {
                Text(
                    text = stringResource(R.string.Receipt),
                    style = MaterialTheme.typography.displaySmall
                )
                Text(
                    text = stringResource(food.receiptRes),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}

@Preview
@Composable
fun Preview() {
    _30DaysCookingTheme {
        CookingCard(
            food = Food(
                R.string.food1, R.string.receipt1, R.drawable.spaghetti_carbonara
            ),
            profile = Profile(R.string.profile1, R.string.upload1, R.drawable.pevita_pearce),
            onClick = {},
            expanded = false
        )
    }
}

@Preview
@Composable
fun AppPreview() {
    _30DaysCookingTheme {
        CookingApp()
    }
}

@Preview
@Composable
fun TopBarPreview() {
    _30DaysCookingTheme {
        TopAppBar()
    }
}

