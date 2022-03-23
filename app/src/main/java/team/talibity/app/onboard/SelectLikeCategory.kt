package team.talibity.app.onboard

import android.content.Intent
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import team.talibity.app.R
import team.talibity.app.SystemUiController
import team.talibity.app.main.BoardActivity
import team.talibity.app.ui.theme.Background
import team.talibity.app.ui.theme.Primary
import team.talibity.app.ui.theme.PrimaryDark

@Immutable
data class Item(
    val id: Int,
    val text: String,
    val icon: Int,
)

class SelectLikeCategory : ComponentActivity() {

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SystemUiController(window).run {
            setSystemBarsColor(Background)
        }
        setContent {
            val selectItems = remember { mutableStateListOf<Int>() }
            Box(modifier = Modifier.fillMaxSize().background(color = Background)) {
                Column(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(30.dp)
                ) {
                    Text(
                        text = "관심있는 Talent 카테고리 선택",
                        style = LocalTextStyle.current.copy(color = Color.Black, fontSize = 20.sp)
                    )
                    Text(
                        text = "최소 3개의 카테고리를 선택해 주세요!",
                        modifier = Modifier.padding(top = 30.dp),
                        style = LocalTextStyle.current.copy(color = Color.Gray, fontSize = 13.sp)
                    )
                    LazyColumn(
                        modifier = Modifier.fillMaxSize().padding(top = 50.dp),
                        verticalArrangement = Arrangement.spacedBy(30.dp)
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
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                        .align(Alignment.BottomCenter)
                        .padding(30.dp),
                    shape = RoundedCornerShape(30.dp),
                    onClick = {
                        startActivity(Intent(this@SelectLikeCategory, BoardActivity::class.java))
                        finish()
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = PrimaryDark)
                ) {
                    Text(
                        color = Color.White,
                        text = "계속하기"
                    )
                }
            }
        }
    }
}
