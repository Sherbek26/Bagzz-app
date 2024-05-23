package com.example.bagzz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bagzz.ui.theme.BagzzTheme

val listOfAds = listOf(
    Item(image = R.drawable.ad_1, text = R.string.ad_1_text),
    Item(image = R.drawable.ad_2, text = R.string.ad_2_text),
    Item(image = R.drawable.ad_3, text = R.string.ad_3_text),
    Item(image = R.drawable.ad_4, text = R.string.ad_4_text),
)

val listOfBags = listOf(
    Item(image = R.drawable.bag_1, text = R.string.bag_1_text),
    Item(image = R.drawable.bag_2, text = R.string.bag_2_text),
    Item(image = R.drawable.bag_3, text = R.string.bag_3_text),
    Item(image = R.drawable.bag_4, text = R.string.bag_4_text),
    Item(image = R.drawable.bag_1, text = R.string.bag_1_text),
    Item(image = R.drawable.bag_2, text = R.string.bag_2_text),
    Item(image = R.drawable.bag_3, text = R.string.bag_3_text),
    Item(image = R.drawable.bag_4, text = R.string.bag_4_text),
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BagzzTheme {
                HomeScreen()
            }
        }
    }
}

@Composable
fun AdvertisingSection(
    modifier: Modifier = Modifier,
){
    var index by rememberSaveable {
        mutableIntStateOf(0)
    }
    Surface(
        modifier
            .height(200.dp)
            .fillMaxWidth()) {
        AdvertsItem(
            image = listOfAds[index].image,
            text = listOfAds[index].text
        )
        Box(
            contentAlignment = Alignment.BottomEnd,
            modifier = Modifier.padding(end = 12.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(
                    onClick = {
                        if (index <= 0){
                            index = listOfAds.size - 1
                        }else{
                            index--
                        }
                    },
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = Color.Black
                    )
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowLeft,
                        tint = Color.White,
                        contentDescription = null
                    )
                }
                IconButton(
                    onClick = {
                        if (index == listOfAds.lastIndex){
                            index = 0
                        }else{
                            index++
                        }
                    },
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = Color.Black
                    )
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowRight,
                        tint = Color.White,
                        contentDescription = null
                    )
                }
            }
        }
    }
}

@Composable
fun AdvertsItem(
    modifier: Modifier = Modifier,
    @DrawableRes image : Int,
    @StringRes text : Int
){
    Surface(
        modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(image),
            contentDescription = null,
            modifier = Modifier.padding(bottom = 18.dp),
            contentScale = ContentScale.Crop
        )
        Box(
            contentAlignment = Alignment.CenterEnd
        ) {
            Text(
                text = stringResource(id = text),
                style = MaterialTheme.typography.titleLarge.copy(
                    background = Color.White,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                ),
                textAlign = TextAlign.Start,
                overflow = TextOverflow.Clip,
                modifier = Modifier
                    .requiredWidth(110.dp)
            )
        }
    }
}

@Composable
fun BagsGridList(
    modifier: Modifier = Modifier
){
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier
            .height(400.dp),
        state = rememberLazyGridState(),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(listOfBags){bag->
            BagCart(image = bag.image, text = bag.text)
        }
    }
}

@Composable
fun BagCart(
    modifier: Modifier = Modifier,
    @DrawableRes image : Int,
    @StringRes text: Int
){
    Surface(
        modifier = modifier
            .height(250.dp)
            .width(200.dp),
        color = MaterialTheme.colorScheme.surfaceVariant,
        shape = RoundedCornerShape(8.dp)
    ) {

        Box(contentAlignment = Alignment.TopEnd) {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Default.FavoriteBorder,
                    contentDescription = null
                )
            }
        }

        Column(
            modifier = Modifier.padding(horizontal = 18.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = image),
                contentDescription = null,
                modifier = Modifier.size(120.dp)
            )
            Text(text = stringResource(id = text),
                style = TextStyle(
                    fontSize = 28.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                ),
                overflow = TextOverflow.Clip,
                textAlign = TextAlign.Center
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextButton(onClick = {}) {
                    Text(
                        text = "Shop now",
                        style = MaterialTheme.typography.titleMedium.copy(
                            color = Color.Black
                        )
                    )
                }
                Box(
                    modifier = Modifier
                        .background(color = Color.Black)
                        .size(width = 120.dp, height = 4.dp)
                )
            }
        }
    }
}

@Composable
fun ScreenSection(
    modifier: Modifier = Modifier,
    @StringRes title: Int,
    content : @Composable () -> Unit,
    @StringRes hyperLink : Int
){
    Column(
        modifier
            .padding(top = 18.dp)
    ) {
        Text(text = stringResource(id = title),
            style = MaterialTheme.typography.titleLarge.copy(
                color = Color.Black,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(bottom = 8.dp)
        )
        content()
        Spacer(modifier = Modifier.height(18.dp))
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            OutlinedButton(
                onClick = {},
                modifier = Modifier.height(50.dp),
                shape = MaterialTheme.shapes.medium,
                border = BorderStroke(2.dp, Color.Black)
            ) {
                Text(
                    text = stringResource(id = hyperLink),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color.Black
                    ),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val scrollState = rememberScrollState()
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(
                top = 8.dp,
                end = 12.dp,
                start = 12.dp,
                bottom = 16.dp
            ),
        verticalArrangement = Arrangement.spacedBy(8.dp) // Optional: Adds spacing between items
    ) {
        AdvertisingSection()
        ScreenSection(
            title = R.string.section_1,
            content = { BagsGridList() },
            hyperLink = R.string.hyperlink_1
        )
        ScreenSection(
            title = R.string.section_2,
            content = { BagsGridList() },
            hyperLink = R.string.hyperlink_2
        )
    }
}

@Composable
private fun SootheBottomNavigation(modifier: Modifier = Modifier) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        modifier = modifier
    ) {
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = null
                )
            },
            label = {
                Text("Home")
            },
            selected = true,
            onClick = {}
        )
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null
                )
            },
            label = {
                Text("Profile")
            },
            selected = false,
            onClick = {}
        )
    }
}



@Preview(showBackground = true)
@Composable
fun ContentsPreview(){
    BagzzTheme {
        Scaffold(
            bottomBar = { SootheBottomNavigation()}
        ) {paddingValues ->  
            HomeScreen(Modifier.padding(paddingValues))
        }
    }
}



