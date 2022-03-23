package team.talibity.app.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import team.talibity.app.SystemUiController
import team.talibity.app.ui.theme.Background
import team.talibity.app.ui.theme.PrimaryDark

class WriteActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SystemUiController(window).run {
            setStatusBarColor(PrimaryDark)
            setNavigationBarColor(Background)
        }
        setContent {
            var title by remember { mutableStateOf("") }
            var category by remember { mutableStateOf("") }
            var content by remember { mutableStateOf("") }
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
                        text = "재능 글쓰기",
                        style = LocalTextStyle.current.copy(color = Color.White)
                    )
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = null,
                        tint = PrimaryDark
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(30.dp)
                        .padding(top = 60.dp),
                    verticalArrangement = Arrangement.spacedBy(30.dp)
                ) {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        Button(
                            modifier = Modifier.align(Alignment.CenterEnd),
                            onClick = { /*TODO*/ },
                            colors = ButtonDefaults.buttonColors(backgroundColor = PrimaryDark)
                        ) {
                            Text(
                                text = "등록",
                                style = LocalTextStyle.current.copy(color = Color.White)
                            )
                        }
                    }
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = title,
                        onValueChange = { title = it },
                        placeholder = {
                            Text(text = "제목")
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = PrimaryDark
                        )
                    )
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = category,
                        onValueChange = { category = it },
                        placeholder = {
                            Text(text = "카테고리")
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = PrimaryDark
                        )
                    )
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth().height(200.dp),
                        value = content,
                        onValueChange = { content = it },
                        placeholder = {
                            Text(text = "내용")
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = PrimaryDark
                        )
                    )
                }
            }
        }
    }
}
