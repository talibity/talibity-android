package team.talibity.app.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random
import team.talibity.app.R
import team.talibity.app.SystemUiController
import team.talibity.app.fancybottombar.FancyBottomBar
import team.talibity.app.fancybottombar.FancyColors
import team.talibity.app.fancybottombar.FancyItem
import team.talibity.app.ui.theme.Background
import team.talibity.app.ui.theme.GrayScale
import team.talibity.app.ui.theme.PrimaryDark

class BoardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SystemUiController(window).run {
            setStatusBarColor(PrimaryDark)
            setNavigationBarColor(Background)
        }
        setContent {
            CompositionLocalProvider(
                LocalTextStyle provides TextStyle.Default.copy(
                    fontFamily = FontFamily(Font((R.font.notosans_r)))
                )
            ) {
                var state by remember { mutableStateOf(0) }
                var title by remember { mutableStateOf("홈") }
                var searchField by remember { mutableStateOf("") }
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = Background)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .clip(
                                RoundedCornerShape(bottomStart = 30.dp, bottomEnd = 30.dp)
                            )
                            .background(color = PrimaryDark)
                            .padding(horizontal = 30.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = null,
                            tint = PrimaryDark
                        )
                        Text(
                            text = title,
                            style = LocalTextStyle.current.copy(color = Color.White)
                        )
                        Icon(
                            modifier = Modifier.clickable {
                                startActivity(
                                    Intent(
                                        this@BoardActivity,
                                        FilterActivity::class.java
                                    )
                                )
                            },
                            imageVector = Icons.Default.Menu,
                            contentDescription = null,
                            tint = if (state == 0) {
                                Color.White
                            } else PrimaryDark
                        )
                    }
                    Crossfade(
                        modifier = Modifier.padding(
                            top = 90.dp,
                            start = 30.dp,
                            end = 30.dp,
                            bottom = 60.dp
                        ),
                        targetState = state
                    ) {
                        when (it) {
                            0 -> {
                                SideEffect {
                                    title = "홈"
                                }
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                ) {
                                    TextField(
                                        value = searchField,
                                        onValueChange = { it2 -> searchField = it2 },
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .clip(RoundedCornerShape(10.dp)),
                                        colors = TextFieldDefaults.textFieldColors(
                                            unfocusedIndicatorColor = GrayScale,
                                            focusedIndicatorColor = GrayScale,
                                            backgroundColor = GrayScale
                                        ),
                                        placeholder = {
                                            Text(text = "근처의 Talent 검색")
                                        },
                                        trailingIcon = {
                                            Icon(
                                                imageVector = Icons.Default.Search,
                                                contentDescription = null,
                                                tint = PrimaryDark
                                            )
                                        }
                                    )
                                    Spacer(modifier = Modifier.height(30.dp))
                                    LazyColumn(
                                        modifier = Modifier.fillMaxSize(),
                                        verticalArrangement = Arrangement.spacedBy(15.dp),
                                        contentPadding = PaddingValues(bottom = 30.dp)
                                    ) {
                                        items(10) {
                                            Row(
                                                modifier = Modifier
                                                    .clip(
                                                        RoundedCornerShape(10.dp)
                                                    )
                                                    .wrapContentHeight()
                                                    .fillMaxWidth()
                                                    .background(
                                                        color = Color.White
                                                    )
                                                    .clickable {
                                                        startActivity(
                                                            Intent(
                                                                this@BoardActivity,
                                                                DetailActivity::class.java
                                                            )
                                                        )
                                                    }.padding(4.dp),
                                                verticalAlignment = Alignment.CenterVertically
                                            ) {
                                                Image(
                                                    modifier = Modifier
                                                        .size(75.dp)
                                                        .padding(start = 8.dp),
                                                    painter = painterResource(R.drawable.cake),
                                                    contentDescription = null
                                                )
                                                Column(
                                                    modifier = Modifier.padding(start = 16.dp),
                                                    verticalArrangement = Arrangement.SpaceAround
                                                ) {
                                                    Text(
                                                        text = "딸기 케익 클래스!",
                                                        style = LocalTextStyle.current.copy(fontSize = 18.sp)
                                                    )
                                                    Text(
                                                        text = "딸기베리베이킹랩",
                                                        style = LocalTextStyle.current.copy(fontSize = 13.sp)
                                                    )
                                                    Row {
                                                        Icon(
                                                            imageVector = Icons.Default.FavoriteBorder,
                                                            contentDescription = null
                                                        )
                                                        Text(
                                                            text = Random.nextInt(1, 9).toString(),
                                                            modifier = Modifier.padding(start = 8.dp)
                                                        )
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            1 -> {
                                SideEffect {
                                    title = "채팅"
                                }
                                LazyColumn(
                                    modifier = Modifier.fillMaxSize(),
                                    verticalArrangement = Arrangement.spacedBy(15.dp),
                                    contentPadding = PaddingValues(bottom = 30.dp)
                                ) {
                                    items(10) {
                                        Row(
                                            modifier = Modifier
                                                .clip(RoundedCornerShape(10.dp))
                                                .wrapContentHeight()
                                                .fillMaxWidth()
                                                .background(
                                                    color = Color.White
                                                )
                                                .clickable {
                                                    /*startActivity(
                                                    Intent(
                                                        this@BoardActivity,
                                                        DetailActivity::class.java
                                                    )
                                                )*/
                                                }.padding(4.dp),
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Image(
                                                modifier = Modifier
                                                    .size(75.dp)
                                                    .padding(start = 8.dp),
                                                painter = painterResource(R.drawable.cake),
                                                contentDescription = null
                                            )
                                            Column(
                                                modifier = Modifier.padding(
                                                    start = 16.dp,
                                                    end = 8.dp
                                                ),
                                                verticalArrangement = Arrangement.spacedBy(10.dp)
                                            ) {
                                                Row(
                                                    modifier = Modifier.fillMaxWidth(),
                                                    horizontalArrangement = Arrangement.SpaceBetween
                                                ) {
                                                    Text(
                                                        text = "딸기가조아",
                                                        style = LocalTextStyle.current.copy(fontSize = 18.sp)
                                                    )
                                                    Text(
                                                        text = "방금 전",
                                                        style = LocalTextStyle.current.copy(fontSize = 13.sp)
                                                    )
                                                }
                                                Row(
                                                    modifier = Modifier.fillMaxWidth(),
                                                    horizontalArrangement = Arrangement.SpaceBetween
                                                ) {
                                                    Text(
                                                        text = "네! 그럼 그 날 뵈어요~",
                                                        style = LocalTextStyle.current.copy(fontSize = 13.sp)
                                                    )
                                                    Text(
                                                        text = Random.nextInt(2, 10).toString(),
                                                        modifier = Modifier
                                                            .clip(RoundedCornerShape(8.dp))
                                                            .background(color = PrimaryDark)
                                                            .padding(
                                                                vertical = 1.dp,
                                                                horizontal = 10.dp
                                                            ),
                                                        style = LocalTextStyle.current.copy(
                                                            color = Color.White,
                                                            fontSize = 10.sp
                                                        )
                                                    )
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            2 -> {
                                SideEffect {
                                    title = "스크랩"
                                }
                                LazyColumn(
                                    modifier = Modifier.fillMaxSize(),
                                    verticalArrangement = Arrangement.spacedBy(15.dp),
                                    contentPadding = PaddingValues(bottom = 30.dp)
                                ) {
                                    items(10) {
                                        Row(
                                            modifier = Modifier
                                                .clip(
                                                    RoundedCornerShape(10.dp)
                                                )
                                                .wrapContentHeight()
                                                .fillMaxWidth()
                                                .background(
                                                    color = Color.White
                                                )
                                                .clickable {
                                                    startActivity(
                                                        Intent(
                                                            this@BoardActivity,
                                                            DetailActivity::class.java
                                                        )
                                                    )
                                                }.padding(4.dp),
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Image(
                                                modifier = Modifier
                                                    .size(75.dp)
                                                    .padding(start = 8.dp),
                                                painter = painterResource(R.drawable.cake),
                                                contentDescription = null
                                            )
                                            Column(
                                                modifier = Modifier.padding(start = 16.dp),
                                                verticalArrangement = Arrangement.spacedBy(3.dp)
                                            ) {
                                                Text(
                                                    text = "딸기 케익 클래스!",
                                                    style = LocalTextStyle.current.copy(fontSize = 18.sp)
                                                )
                                                Text(
                                                    text = "딸기베리베이킹랩",
                                                    style = LocalTextStyle.current.copy(fontSize = 13.sp)
                                                )
                                                Text(
                                                    text = "2022.03.02",
                                                    style = LocalTextStyle.current.copy(fontSize = 13.sp)
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                            3 -> {
                                SideEffect {
                                    title = "내정보"
                                }
                                Column(
                                    modifier = Modifier.fillMaxSize(),
                                    verticalArrangement = Arrangement.spacedBy(15.dp)
                                ) {
                                    Text(
                                        text = "내 정보",
                                        style = LocalTextStyle.current.copy(
                                            color = Color.Gray,
                                            fontSize = 13.sp
                                        )
                                    )
                                    Row(modifier = Modifier.height(50.dp)) {
                                        Image(
                                            painter = painterResource(R.drawable.me),
                                            contentDescription = null,
                                            modifier = Modifier.size(50.dp)
                                        )
                                        Column(
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .padding(start = 16.dp),
                                            verticalArrangement = Arrangement.SpaceAround
                                        ) {
                                            Text(text = "베토짜르트")
                                            Text(
                                                text = "#1234",
                                                style = LocalTextStyle.current.copy(
                                                    color = Color.Gray,
                                                )
                                            )
                                        }
                                    }
                                    Divider(color = PrimaryDark)
                                    Text(
                                        text = "알림",
                                        style = LocalTextStyle.current.copy(
                                            color = Color.Gray,
                                            fontSize = 13.sp
                                        )
                                    )
                                    Text(text = "알림 설정")
                                    Text(text = "키워드 설정")
                                    Divider(color = PrimaryDark)
                                    Text(
                                        text = "사용자 설정",
                                        style = LocalTextStyle.current.copy(
                                            color = Color.Gray,
                                            fontSize = 13.sp
                                        )
                                    )
                                    Text(text = "언어 설정")
                                    Text(text = "카테고리 설정")
                                    Text(text = "차단 관리")
                                    Divider(color = PrimaryDark)
                                    Text(
                                        text = "기타",
                                        style = LocalTextStyle.current.copy(
                                            color = Color.Gray,
                                            fontSize = 13.sp
                                        )
                                    )
                                    Text(text = "로그아웃")
                                    Text(text = "탈퇴")
                                    Text(text = "앱정보")
                                }
                            }
                        }
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(70.dp)
                            .align(Alignment.BottomCenter)
                    ) {
                        FancyBottomBar(
                            modifier = Modifier.fillMaxSize(),
                            items = listOf(
                                FancyItem(id = 0, title = "홈", icon = R.drawable.ic_round_home_24),
                                FancyItem(id = 1, title = "채팅", icon = R.drawable.ic_round_chat_24),
                                FancyItem(
                                    id = 2,
                                    title = "스크랩",
                                    icon = R.drawable.ic_round_favorite_24
                                ),
                                FancyItem(
                                    id = 3,
                                    title = "내정보",
                                    icon = R.drawable.ic_round_sentiment_satisfied_alt_24
                                ),
                            ),
                            onItemChanged = { item ->
                                state = item.id
                            },
                            selectedState = state,
                            fancyColors = FancyColors(
                                background = Background,
                                primary = Color.White
                            )
                        )
                    }
                    if (state == 0) {
                        FloatingActionButton(
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .padding(bottom = 86.dp, end = 16.dp),
                            onClick = {
                                startActivity(
                                    Intent(
                                        this@BoardActivity,
                                        WriteActivity::class.java
                                    )
                                )
                            },
                            backgroundColor = PrimaryDark
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = null,
                                tint = Color.White
                            )
                        }
                    }
                }
            }
        }
    }
}
