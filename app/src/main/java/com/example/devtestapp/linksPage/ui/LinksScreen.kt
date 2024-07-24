package com.example.devtestapp.linksPage.ui

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.ContactsContract.Profile
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.devtestapp.R
import com.example.devtestapp.components.Chart
import com.example.devtestapp.components.openWhatsAppChat
import com.example.devtestapp.linksPage.model.ApiResponse
import com.example.devtestapp.linksPage.model.RecentLink
import com.example.devtestapp.linksPage.model.TopLink
import com.example.devtestapp.linksPage.repository.apiResponse
import com.example.devtestapp.ui.theme.clrBlue
import com.example.devtestapp.ui.theme.clrBlueLight
import com.example.devtestapp.ui.theme.clrBlueLight2
import com.example.devtestapp.ui.theme.clrGreenLight
import com.example.devtestapp.ui.theme.clrGreenLight2
import com.example.devtestapp.ui.theme.clrGrey
import com.example.devtestapp.ui.theme.clrWhiteOff
import okhttp3.internal.trimSubstring

@Preview
@Composable
fun LinksScreenPreview() {
    LinksScreen(navController = NavController(LocalContext.current), context = LocalContext.current)
}

@Composable
fun LinksScreen(navController: NavController, context: Context) {
    val response: MutableState<ApiResponse?> = remember { mutableStateOf(null) }
    val todayClicks = remember { mutableIntStateOf(0) }
    val topLocation = remember { mutableStateOf("") }
    val topSource = remember { mutableStateOf("") }
    val topLinksData = remember { mutableStateOf(emptyList<TopLink>()) }
    val recentLinksData = remember { mutableStateOf(emptyList<RecentLink>()) }
//    val overallUrlChart: MutableState<List<Entry>> = remember { mutableStateOf(emptyList())}
    val overallURLChart = remember { mutableStateOf<Any?>(null) }
    val isTopLinkBtnEnabled = remember { mutableStateOf(true) }
    val isRecentLinkBtnEnabled = remember { mutableStateOf(false) }

    LaunchedEffect(key1 = Unit) {
        response.value = apiResponse(context)
    }

    if (response.value != null) {
        Log.d("Links Screen", response.value.toString())
        todayClicks.intValue = response.value!!.today_clicks
        topLocation.value = response.value!!.top_location
        topSource.value = response.value!!.top_source
        topLinksData.value = response.value!!.data.top_links
        recentLinksData.value = response.value!!.data.recent_links
//        overallUrlChart.value = response.value!!.data.overall_url_chart
        overallURLChart.value = response.value!!.data.overall_url_chart
        Log.d("Links Screen overallURLChart", "$overallURLChart.value")
    }

    Scaffold(containerColor = clrWhiteOff, bottomBar = {
        BottomAppBar(
            modifier = Modifier
                .height(70.dp)
                .fillMaxWidth(1f)
                .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)),
            containerColor = Color.White,
        ) {
            //Links
            IconButton(onClick = {},
                modifier = Modifier
                    .weight(1f, true)
                    .size(24.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.link1),
                    contentDescription = "Links",
                    tint = Color.Black,
                    modifier = Modifier.weight(0.8f)
                )
            }
            //Courses
            IconButton(onClick = {},
                modifier = Modifier
                    .weight(1f, true)
                    .size(24.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.book1),
                    contentDescription = "Courses",
                    tint = clrGrey
                )
            }
            //Add
            IconButton(onClick = {}, modifier = Modifier
                .weight(1.5f, true)
                .fillMaxSize(1f)
                .padding(vertical = 8.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.addcircle2),
                    contentDescription = "Add",
                    tint = Color.Unspecified,
                    modifier = Modifier.size(100.dp)
                )
            }
            //Campaign
            IconButton(onClick = {}, modifier = Modifier
                .weight(1f, true)
                .size(24.dp)) {
                Icon(
                    painter = painterResource(id = R.drawable.campaignmarketing1),
                    contentDescription = "Campaign",
                    tint = clrGrey
                )
            }
            //Profile
            IconButton(onClick = {}, modifier = Modifier
                .weight(1f, true)
                .size(24.dp)) {
                Icon(
                    painter = painterResource(id = R.drawable.profile3),
                    contentDescription = "Profile",
                    tint = clrGrey
                )
            }
        }
    }
    ) { paddingValues ->
        Surface(modifier = Modifier.padding(paddingValues), color = clrWhiteOff) {
            if (response.value != null) {

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth(1f)
                ) {
                    item {
                        LinksTopBar()
                    }
                    item { Box(modifier = Modifier.padding(vertical = 16.dp)) { GreetingsText() } }
                    item { GraphOverviewCard() }
                    item { LazyRowCards(response) }
                    item { OutlineTextButton("View Analytics", R.drawable.growth1) }
                    item { TabRow(isTopLinkBtnEnabled, isRecentLinkBtnEnabled) }
                    if (isTopLinkBtnEnabled.value) {
                        items(topLinksData.value) { item ->
                            LinksCard(
                                item.app,
                                item.total_clicks,
                                item.web_link,
                                item.original_image,
                                item.created_at,
                                LocalContext.current
                            )
                        }
                    } else {
                        items(recentLinksData.value) { item ->
                            LinksCard(
                                item.app,
                                item.total_clicks,
                                item.web_link,
                                item.original_image,
                                item.created_at,
                                LocalContext.current
                            )
                        }
                    }

                    item {
                        OutlineTextButton(
                            txt = "View All Links",
                            R.drawable.link1
                        )
                    }
                    item {
                        OutlineTextColoredButton(
                            "Talk with us",
                            R.drawable.whatsapp1,
                            clrGreenLight,
                            Color.Black,
                            Color.Unspecified,
                            BorderStroke(1.dp, clrGreenLight2),
                        ){
                            openWhatsAppChat(context, response.value!!.support_whatsapp_number)
                        }
                    }
                    item {
                        OutlineTextColoredButton(
                            "Frequently Asked Questions",
                            R.drawable.questionmark1,
                            clrBlueLight,
                            Color.Black,
                            clrBlue,
                            BorderStroke(1.dp, clrBlueLight2)
                        )
                    }


                }

            } else {
                Box(modifier = Modifier.fillMaxSize(1f), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }
        }
    }
}

@Composable
private fun LinksCard(
    app: String,
    totalClicks: Int,
    webLink: String,
    originalImage: String,
    createdAt: String,
    context: Context
) {
    Card(
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
            contentColor = clrWhiteOff
        ),
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth(1f)
            .height(120.dp),
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(1f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ImageBox(
                    originalImage, Modifier
                        .weight(1f, true)
                        .padding(12.dp)
                        .aspectRatio(1f, true)
                        .fillMaxWidth(0.1f)
                        .fillMaxHeight(0.1f)
                        .clip(RoundedCornerShape(16.dp)) // Adjust the corner radius as needed

                        .shadow(
                            elevation = 10.dp,
                            shape = RoundedCornerShape(0.dp)
                        )
                )

                //column1
                Column(Modifier.weight(1f, true)) {
                    Text(text = app, fontSize = 16.sp, color = Color.Black)
                    Text(text = createdAt.trimSubstring(0, 10), fontSize = 14.sp, color = clrGrey)
                }
                //column2
                Column(
                    Modifier
                        .weight(2f, true)
                        .padding(horizontal = 12.dp)
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(1f),
                        text = "$totalClicks",
                        fontSize = 16.sp,
                        color = Color.Black,
                        textAlign = TextAlign.End
                    )
                    Text(
                        modifier = Modifier.fillMaxWidth(1f),
                        text = "Clicks",
                        fontSize = 14.sp,
                        color = clrGrey,
                        textAlign = TextAlign.End
                    )
                }

            }
            Row(modifier = Modifier.background(clrBlueLight)) {
                Text(
                    text = webLink,
                    fontSize = 16.sp,
                    color = clrBlue,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .padding(top = 0.dp, start = 12.dp, end = 0.dp, bottom = 2.dp)
                        .fillMaxWidth(1f)
                        .weight(1f)
                        .clickable {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(webLink))
                            context.startActivity(intent)
                        }
                )
                IconButton(onClick = {
                    val clipboardManager: ClipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                    val clip = ClipData.newPlainText("Copied Text ", webLink)
                    clipboardManager.setPrimaryClip(clip)
                    Toast.makeText(context, "Link Copied!", Toast.LENGTH_SHORT).show()
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.copy1),
                        contentDescription = "Favourite",
                        tint = clrBlue
                    )
                }
            }

        }

    }
}

@Composable
private fun ImageBox(originalImage: String, modifier: Modifier) {
    AsyncImage(
        contentScale = ContentScale.Crop,
        model = ImageRequest.Builder(LocalContext.current)
            .data(originalImage)
            .crossfade(true)
            .build(),
        contentDescription = "Restaurant Image",
        modifier = modifier
    )
}

@Composable
private fun TabRow(
    isTopLinkBtnEnabled: MutableState<Boolean>,
    isRecentLinkBtnEnabled: MutableState<Boolean>
) {
    Row(
        modifier = Modifier
            .padding(horizontal = 12.dp)
            .fillMaxWidth(1f),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        TabButton("Top Links", isTopLinkBtnEnabled) {
            // Handle click
            isTopLinkBtnEnabled.value = !isTopLinkBtnEnabled.value
            isRecentLinkBtnEnabled.value = !isRecentLinkBtnEnabled.value
        }
        TabButton("Recent Links", isRecentLinkBtnEnabled) {
            // Handle click
            isTopLinkBtnEnabled.value = !isTopLinkBtnEnabled.value
            isRecentLinkBtnEnabled.value = !isRecentLinkBtnEnabled.value
        }
        IconButton(onClick = {}) {
            Icon(
                painter = painterResource(id = R.drawable.search1),
                contentDescription = "Search",
                modifier = Modifier.size(32.dp),
                tint = clrGrey
            )
        }


    }
}

@Composable
private fun TabButton(btnText: String, isEnabled: MutableState<Boolean>, onClick: () -> Unit) {
    val containerClr = if (isEnabled.value) clrBlue else clrWhiteOff
    val contentClr = if (isEnabled.value) Color.White else clrGrey
    Button(
        onClick = {
            onClick()
        },
        enabled = true,
        colors = ButtonColors(
            containerColor = containerClr,
            contentColor = contentClr,
            disabledContentColor = clrGrey,
            disabledContainerColor = clrWhiteOff
        )
    ) { Text(text = btnText) }
}

@Composable
private fun OutlineTextButton(txt: String, drawableID: Int) {
    OutlinedIconButton(
        onClick = {},
        modifier = Modifier
            .padding(12.dp)
            .height(50.dp)
            .fillMaxWidth(1f),
        shape = MaterialTheme.shapes.small,
        colors = IconButtonColors(
            containerColor = clrWhiteOff,
            contentColor = clrBlue,
            disabledContentColor = clrGrey,
            disabledContainerColor = clrGrey
        )
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                modifier = Modifier.size(32.dp),
                painter = painterResource(id =drawableID),
                contentDescription = "Icon",
                tint = Color.Unspecified
            )
            Text(
                text = txt,
                fontSize = 16.sp,
                color = Color.Black,
                modifier = Modifier.padding(horizontal = 12.dp)
            )
        }
    }
}

@Composable
private fun OutlineTextColoredButton(
    txt: String,
    drawableID: Int,
    containerClr: Color,
    contentClr: Color,
    iconTint: Color,
    borderStroke: BorderStroke,
    onClick: () -> Unit = {}
) {
    OutlinedIconButton(
        onClick = onClick,
        modifier = Modifier
            .padding(top = 12.dp, start = 12.dp, end = 12.dp, bottom = 0.dp)
            .height(70.dp)
            .fillMaxWidth(1f),
        shape = MaterialTheme.shapes.small,
        colors = IconButtonColors(
            containerColor = containerClr,
            contentColor = contentClr,
            disabledContentColor = clrGrey,
            disabledContainerColor = clrGrey
        ),
        border = borderStroke
    ) {
        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(horizontal = 12.dp)
        ) {
            Icon(
                painter = painterResource(id =drawableID),
                contentDescription = "Icon",
                tint = iconTint,
                modifier = Modifier.size(36.dp)
            )
            Text(
                text = txt,
                fontSize = 18.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 12.dp)
            )
        }
    }
}

@Composable
private fun GreetingsText() {
    Column {
        Text(
            text = "Good Morning!",
            fontSize = 16.sp,
            color = clrGrey,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
        )
        Text(
            text = "Rahul Chaudhary\uD83D\uDC4B",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 12.dp)
        )
    }
}

@Composable
private fun LazyRowCards(response: MutableState<ApiResponse?>) {
    LazyRow(modifier = Modifier.padding(end = 12.dp)) {
        item { QuickCard(response.value?.today_clicks.toString(), "Today's Click", R.drawable.click1) }
        item { QuickCard(response.value?.top_location.toString(), "Top Location", R.drawable.location1) }
        item { QuickCard(response.value?.top_source.toString(), "Top Source", R.drawable.internet1 ) }
        item { QuickCard('₹' + response.value?.extra_income.toString(), "Extra Income", R.drawable.money1) }


    }
}

@Composable
private fun QuickCard(title: String, description: String, drawableID: Int) {
    Card(
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
            contentColor = clrWhiteOff
        ),
        modifier = Modifier
            .padding(top = 12.dp, bottom = 12.dp, start = 12.dp, end = 0.dp)
            .width(140.dp)
            .height(140.dp),
    ) {
        Column() {
            Icon(
                painter = painterResource(id = drawableID),
                contentDescription = "Arrow Icon",
                tint = Color.Unspecified,
                modifier = Modifier
                    .padding(12.dp)
                    .size(40.dp)
            )
            Text(
                text = title,
                fontSize = 20.sp,
                color = Color.Black,
                modifier = Modifier.padding(horizontal = 12.dp)
            )
            Text(
                text = description,
                fontSize = 16.sp,
                color = clrGrey,
                modifier = Modifier.padding(vertical = 12.dp,horizontal = 12.dp)
            )
        }
    }
}


@Composable
private fun GraphOverviewCard() {
    Card(
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
            contentColor = clrWhiteOff
        ),
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth(1f)
            .height(150.dp),
    ) {
        Box(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxSize(1f)
        ) {
            Chart()
        }
    }
}

@Composable
private fun LinksTopBar() {
    Row(
        modifier = Modifier
            .height(100.dp)
            .fillMaxWidth(1f)
            .background(color = clrBlue),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            modifier = Modifier.padding(12.dp),
            text = "Dashboard",
            fontSize = 25.sp,
            color = Color.White
        )
        IconButton(onClick = {}) {
            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = "Menu",
                tint = Color.White
            )
        }
    }
}