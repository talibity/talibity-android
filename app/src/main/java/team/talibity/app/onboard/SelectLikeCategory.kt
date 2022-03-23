package team.talibity.app.onboard

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import team.talibity.app.main.BoardActivity
import team.talibity.app.ui.theme.PrimaryDark

class SelectLikeCategory : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Box(modifier = Modifier.fillMaxSize()) {
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
                }
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                        .align(Alignment.BottomCenter)
                        .padding(30.dp),
                    shape = RoundedCornerShape(30.dp),
                    onClick = { startActivity(Intent(this@SelectLikeCategory, BoardActivity::class.java)) },
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
