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
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import team.talibity.app.R
import team.talibity.app.fancybottombar.FancyBottomBar
import team.talibity.app.fancybottombar.FancyColors
import team.talibity.app.fancybottombar.FancyItem
import team.talibity.app.ui.theme.GrayScale
import team.talibity.app.ui.theme.PrimaryDark

class BoardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var state by remember { mutableStateOf(0) }
            var title by remember { mutableStateOf("홈") }
            var searchField by remember { mutableStateOf("") }
            Box(modifier = Modifier.fillMaxSize()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .clip(
                            RoundedCornerShape(bottomStart = 30.dp, bottomEnd = 30.dp)
                        )
                        .background(color = PrimaryDark),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = title,
                        style = LocalTextStyle.current.copy(color = Color.White)
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
                                        .clip(RoundedCornerShape(5.dp))
                                        .background(color = GrayScale),
                                    placeholder = {
                                        Text(text = "근처의 Talent 검색")
                                    },
                                    trailingIcon = {
                                        Icon(
                                            imageVector = Icons.Default.Search,
                                            contentDescription = null
                                        )
                                    }
                                )
                                Spacer(modifier = Modifier.height(30.dp))
                                LazyColumn(
                                    modifier = Modifier.fillMaxSize(),
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    items(10) {
                                        Row(
                                            modifier = Modifier
                                                .padding(8.dp)
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
                                                },
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Image(
                                                modifier = Modifier.size(75.dp),
                                                painter = painterResource(R.drawable.cake),
                                                contentDescription = null
                                            )
                                            Column(
                                                modifier = Modifier.padding(start = 16.dp),
                                                verticalArrangement = Arrangement.spacedBy(10.dp)
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
                                                        text = "3",
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
                            LazyColumn(modifier = Modifier.fillMaxSize()) {
                                items(10) {
                                    Row(
                                        modifier = Modifier
                                            .padding(8.dp)
                                            .clip(
                                                RoundedCornerShape(10.dp)
                                            )
                                            .wrapContentHeight()
                                            .fillMaxWidth()
                                            .background(
                                                color = Color.White
                                            ).clickable {
                                                startActivity(
                                                    Intent(
                                                        this@BoardActivity,
                                                        DetailActivity::class.java
                                                    )
                                                )
                                            },
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Image(
                                            modifier = Modifier.size(75.dp),
                                            painter = painterResource(R.drawable.cake),
                                            contentDescription = null
                                        )
                                        Column(
                                            modifier = Modifier.padding(start = 16.dp),
                                            verticalArrangement = Arrangement.spacedBy(10.dp)
                                        ) {
                                            Text(
                                                text = "딸기가조아",
                                                style = LocalTextStyle.current.copy(fontSize = 18.sp)
                                            )
                                            Text(
                                                text = "네! 그럼 그 날 뵈어요~",
                                                style = LocalTextStyle.current.copy(fontSize = 13.sp)
                                            )
                                        }
                                    }
                                }
                            }
                        }
                        2 -> {
                            SideEffect {
                                title = "스크랩"
                            }
                            LazyColumn(modifier = Modifier.fillMaxSize()) {
                                items(10) {
                                    Row(
                                        modifier = Modifier
                                            .padding(8.dp)
                                            .clip(
                                                RoundedCornerShape(10.dp)
                                            )
                                            .wrapContentHeight()
                                            .fillMaxWidth()
                                            .background(
                                                color = Color.White
                                            ).clickable {
                                                startActivity(
                                                    Intent(
                                                        this@BoardActivity,
                                                        DetailActivity::class.java
                                                    )
                                                )
                                            },
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Image(
                                            modifier = Modifier.size(75.dp),
                                            painter = painterResource(R.drawable.cake),
                                            contentDescription = null
                                        )
                                        Column(
                                            modifier = Modifier.padding(start = 16.dp),
                                            verticalArrangement = Arrangement.spacedBy(10.dp)
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
                        modifier = Modifier.fillMaxSize().padding(bottom = 5.dp),
                        items = listOf(
                            FancyItem(id = 0, title = "홈"),
                            FancyItem(id = 1, title = "채팅"),
                            FancyItem(id = 2, title = "스크랩"),
                            FancyItem(id = 3, title = "내정보"),
                        ),
                        onItemChanged = { item ->
                            state = item.id
                        },
                        selectedState = state,
                        fancyColors = FancyColors(background = Color.White, primary = Color.White)
                    )
                }
                if (state == 0) {
                    FloatingActionButton(
                        modifier = Modifier.align(Alignment.BottomEnd)
                            .padding(bottom = 86.dp, end = 16.dp),
                        onClick = { /*TODO*/ },
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
