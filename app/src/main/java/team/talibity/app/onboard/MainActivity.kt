package team.talibity.app.onboard

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import team.talibity.app.R
import team.talibity.app.SystemUiController
import team.talibity.app.ui.theme.Primary
import team.talibity.app.ui.theme.PrimaryDark

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SystemUiController(window).run {
            setSystemBarsColor(Primary)
        }
        setContent {
            CompositionLocalProvider(
                LocalTextStyle provides TextStyle.Default.copy(
                    fontFamily = FontFamily(Font((R.font.notosans_r)))
                )
            ) {
                Box(modifier = Modifier.fillMaxSize().background(color = Primary)) {
                    Row(
                        verticalAlignment = Alignment.Bottom,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.align(Alignment.Center).fillMaxWidth().height(150.dp)
                    ) {
                        Icon(
                            modifier = Modifier.size(150.dp),
                            painter = painterResource(R.drawable.logo),
                            contentDescription = null,
                            tint = Color.Unspecified,
                        )
                        Text(
                            text = stringResource(R.string.app_name),
                            modifier = Modifier.padding(start = 8.dp, bottom = 20.dp),
                            style = LocalTextStyle.current.copy(
                                color = Color.White,
                                fontSize = 20.sp
                            )
                        )
                    }
                    Button(
                        modifier = Modifier.fillMaxWidth().height(120.dp)
                            .align(Alignment.BottomCenter)
                            .padding(30.dp),
                        shape = RoundedCornerShape(30.dp),
                        onClick = {
                            startActivity(
                                Intent(
                                    this@MainActivity,
                                    WelcomeActivity::class.java
                                )
                            )
                            finish()
                        },
                        colors = ButtonDefaults.buttonColors(backgroundColor = PrimaryDark)
                    ) {
                        Text(
                            color = Color.White,
                            text = "Google 로 로그인 하기"
                        )
                    }
                }
            }
        }
    }
}
