package team.talibity.app.onboard

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import team.talibity.app.R
import team.talibity.app.SystemUiController
import team.talibity.app.ui.theme.PrimaryDark
import team.talibity.app.ui.theme.Secondary

class WelcomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SystemUiController(window).run {
            setSystemBarsColor(Secondary)
        }
        setContent {
            CompositionLocalProvider(
                LocalTextStyle provides TextStyle.Default.copy(
                    fontFamily = FontFamily(Font((R.font.notosans_r)))
                )
            ) {
                Box(modifier = Modifier.fillMaxSize().background(color = Secondary)) {
                    Column(
                        modifier = Modifier.wrapContentSize().align(Alignment.TopCenter)
                            .padding(top = 80.dp).padding(horizontal = 30.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Welcome!",
                            style = LocalTextStyle.current.copy(
                                color = PrimaryDark,
                                fontSize = 30.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Spacer(modifier = Modifier.height(30.dp))
                        Box(
                            modifier = Modifier.fillMaxWidth().height(50.dp).clip(
                                RoundedCornerShape(8.dp)
                            ).background(color = Color.LightGray),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Google default nickname",
                            )
                        }
                        Spacer(modifier = Modifier.height(30.dp))
                        Text(text = "You can change nickname at profile setting", color = Color.Gray)
                    }
                    Button(
                        modifier = Modifier.fillMaxWidth().height(120.dp)
                            .align(Alignment.BottomCenter)
                            .padding(30.dp),
                        shape = RoundedCornerShape(30.dp),
                        onClick = {
                            startActivity(
                                Intent(
                                    this@WelcomeActivity,
                                    SetNearByTalent::class.java
                                )
                            )
                            finish()
                        },
                        colors = ButtonDefaults.buttonColors(backgroundColor = PrimaryDark)
                    ) {
                        Text(
                            style = LocalTextStyle.current.copy(color = Color.White),
                            text = "Continue"
                        )
                    }
                }
            }
        }
    }
}
