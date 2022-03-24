package team.talibity.app.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import team.talibity.app.R
import team.talibity.app.SystemUiController
import team.talibity.app.onboard.Item
import team.talibity.app.ui.theme.Background
import team.talibity.app.ui.theme.Primary
import team.talibity.app.ui.theme.PrimaryDark

private val itemsList = listOf(
    Item(1, "운동", R.drawable.awsersize),
    Item(2, "게임", R.drawable.game),
    Item(3, "요리", R.drawable.cook),
    Item(4, "음악", R.drawable.music),
    Item(5, "학문", R.drawable.book),
    Item(6, "디자인", R.drawable.design),
    Item(7, "프로그래밍", R.drawable.dev),
    Item(8, "데이터 사이언스", R.drawable.data),
    Item(9, "커리어", R.drawable.car)
).chunked(3)

class FilterActivity : ComponentActivity() {
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
                val selectItems = remember { mutableStateListOf<Int>() }
                Box(modifier = Modifier.fillMaxSize().background(color = Background)) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .clip(RoundedCornerShape(bottomStart = 30.dp, bottomEnd = 30.dp))
                            .background(color = PrimaryDark)
                            .padding(horizontal = 30.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.clickable { finish() }
                        )
                        Text(
                            text = "카테고리",
                            style = LocalTextStyle.current.copy(color = Color.White)
                        )
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = null,
                            tint = PrimaryDark
                        )
                    }
                    LazyColumn(
                        modifier = Modifier.fillMaxWidth().wrapContentHeight()
                            .align(Alignment.Center)
                            .padding(50.dp),
                        verticalArrangement = Arrangement.spacedBy(30.dp),
                    ) {
                        items(itemsList) { items ->
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                items.forEach { item ->
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Box(
                                            modifier = Modifier.size(65.dp).clip(CircleShape)
                                                .background(
                                                    color = animateColorAsState(
                                                        when (selectItems.contains(item.id)) {
                                                            true -> Primary
                                                            else -> Color.White
                                                        }
                                                    ).value
                                                ).clickable {
                                                    if (selectItems.contains(item.id)) {
                                                        selectItems.remove(item.id)
                                                    } else {
                                                        selectItems.add(item.id)
                                                    }
                                                }
                                        ) {
                                            Icon(
                                                modifier = Modifier.fillMaxSize(),
                                                painter = painterResource(item.icon),
                                                contentDescription = null,
                                                tint = Color.Unspecified
                                            )
                                        }
                                        Text(
                                            text = item.text,
                                            modifier = Modifier.padding(top = 5.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}