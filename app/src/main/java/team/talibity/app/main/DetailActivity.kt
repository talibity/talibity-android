package team.talibity.app.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.CompositionLocalProvider
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import team.talibity.app.R
import team.talibity.app.SystemUiController
import team.talibity.app.ui.theme.Background
import team.talibity.app.ui.theme.PrimaryDark

class DetailActivity : ComponentActivity() {
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
                var like by remember { mutableStateOf(false) }
                Box(modifier = Modifier.fillMaxSize().background(color = Background)) {
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
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.clickable { finish() }
                        )
                        Text(
                            text = "Show Talent",
                            style = LocalTextStyle.current.copy(color = Color.White)
                        )
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                    Column(
                        modifier = Modifier.fillMaxSize().padding(30.dp).padding(top = 60.dp),
                        verticalArrangement = Arrangement.spacedBy(30.dp)
                    ) {
                        Row(modifier = Modifier.fillMaxWidth().height(70.dp)) {
                            Image(
                                modifier = Modifier.size(70.dp).clip(RoundedCornerShape(3.dp)),
                                painter = painterResource(R.drawable.cake),
                                contentDescription = null
                            )
                            Row(
                                modifier = Modifier.fillMaxSize().padding(start = 16.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Column(
                                    modifier = Modifier.fillMaxHeight(),
                                    verticalArrangement = Arrangement.SpaceAround
                                ) {
                                    Text(
                                        text = "berrylov",
                                        style = LocalTextStyle.current.copy(color = Color.Black)
                                    )
                                    Text(
                                        text = "Star 4/5",
                                        style = LocalTextStyle.current.copy(color = Color.Gray)
                                    )
                                }
                                Icon(
                                    modifier = Modifier.clickable {
                                        like = !like
                                    },
                                    imageVector = when (like) {
                                        true -> Icons.Default.Favorite
                                        else -> Icons.Default.FavoriteBorder
                                    },
                                    contentDescription = null,
                                    tint = Color(219, 168, 193)
                                )
                            }
                        }
                        Text(
                            text = "Strawberry Cake Class!",
                            style = LocalTextStyle.current.copy(
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                        )
                        Text(
                            text = "Category: Baking - 2 hours ago",
                            style = LocalTextStyle.current.copy(
                                color = Color.Gray
                            )
                        )
                        Text(
                            text = """
                           I'm looking for someone who wants to make strawberry cake with me! The location is 수정.
                           We prepared a recipe that even beginners can follow easily.
                           Let's discuss the date and time!

                           + What I want to learn is music, but I'm very interested in playing the piano!

                            """.trimIndent()
                        )
                        LazyRow(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            items(3) { int ->
                                val res = when (int) {
                                    1 -> R.drawable.cake2
                                    2 -> R.drawable.cake3
                                    else -> R.drawable.cake4
                                }
                                Image(
                                    painter = painterResource(res),
                                    contentDescription = null,
                                    modifier = Modifier.size(100.dp).clip(RoundedCornerShape(5.dp))
                                )
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
                        onClick = {},
                        colors = ButtonDefaults.buttonColors(backgroundColor = PrimaryDark)
                    ) {
                        Text(
                            color = Color.White,
                            text = "Chatting"
                        )
                    }
                }
            }
        }
    }
}
