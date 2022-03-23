package team.talibity.app.onboard

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
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
import team.talibity.app.SystemUiController
import team.talibity.app.ui.theme.Background
import team.talibity.app.ui.theme.PrimaryDark
import team.talibity.app.ui.theme.Secondary

class SetNearByTalent : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SystemUiController(window).run {
            setSystemBarsColor(Background)
        }
        setContent {
            Box(modifier = Modifier.fillMaxSize().background(color = Background)) {
                Column(modifier = Modifier.align(Alignment.TopStart).padding(30.dp)) {
                    Text(
                        text = "주변 Talent 위치 설정",
                        style = LocalTextStyle.current.copy(color = Color.Black, fontSize = 20.sp)
                    )
                    Text(
                        text = "현위치 기준으로 찾고자 하는 Talent의 위치를 정해주세요!",
                        modifier = Modifier.padding(top = 30.dp),
                        style = LocalTextStyle.current.copy(color = Color.Gray, fontSize = 13.sp)
                    )
                }
                Button(
                    modifier = Modifier.fillMaxWidth().height(120.dp).align(Alignment.BottomCenter)
                        .padding(30.dp),
                    shape = RoundedCornerShape(30.dp),
                    onClick = {
                        startActivity(
                            Intent(
                                this@SetNearByTalent,
                                SelectLikeCategory::class.java
                            )
                        )
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
