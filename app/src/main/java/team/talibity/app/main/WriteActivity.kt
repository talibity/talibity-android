package team.talibity.app.main

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import team.talibity.app.R
import team.talibity.app.SystemUiController
import team.talibity.app.ui.theme.Background
import team.talibity.app.ui.theme.GrayScale
import team.talibity.app.ui.theme.PrimaryDark

class WriteActivity : ComponentActivity() {
    @OptIn(ExperimentalFoundationApi::class)
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
                var title by remember { mutableStateOf("") }
                var category by remember { mutableStateOf("") }
                var content by remember { mutableStateOf("") }
                val bitmapList = remember { mutableStateListOf<Bitmap>() }

                val takePhotoFromAlbumLauncher = // 갤러리에서 사진 가져오기
                    rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                        if (result.resultCode == RESULT_OK) {
                            result.data?.data?.let { uri ->
                                bitmapList.add(uri.parseBitmap(applicationContext))
                            }
                        }
                    }

                val takePhotoFromAlbumIntent = remember {
                    Intent(
                        Intent.ACTION_GET_CONTENT,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                    ).apply {
                        type = "image/*"
                        action = Intent.ACTION_GET_CONTENT
                        putExtra(
                            Intent.EXTRA_MIME_TYPES,
                            arrayOf("image/jpeg", "image/png", "image/bmp", "image/webp")
                        )
                        putExtra(Intent.EXTRA_ALLOW_MULTIPLE, false)
                    }
                }

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
                            text = "Writing My Talent",
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
                                onClick = { finish() },
                                colors = ButtonDefaults.buttonColors(backgroundColor = PrimaryDark)
                            ) {
                                Text(
                                    text = "Post",
                                    style = LocalTextStyle.current.copy(color = Color.White)
                                )
                            }
                        }
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = title,
                            onValueChange = { title = it },
                            placeholder = {
                                Text(text = "Title")
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
                                Text(text = "Category")
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
                                Text(text = "Content")
                            },
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = PrimaryDark
                            )
                        )
                        LazyRow(
                            modifier = Modifier.fillMaxWidth().wrapContentHeight(),
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            item {
                                Box(
                                    modifier = Modifier.size(75.dp).clip(RoundedCornerShape(10.dp))
                                        .border(
                                            width = 1.dp,
                                            color = PrimaryDark,
                                            shape = RoundedCornerShape(10.dp)
                                        ).clickable {
                                            takePhotoFromAlbumLauncher.launch(
                                                takePhotoFromAlbumIntent
                                            )
                                        },
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "Add Photo",
                                        style = LocalTextStyle.current.copy(
                                            color = PrimaryDark,
                                            fontSize = 13.sp
                                        )
                                    )
                                }
                            }
                            items(bitmapList) { bitmap ->
                                Box(
                                    modifier = Modifier.size(75.dp).clip(RoundedCornerShape(10.dp))
                                        .border(
                                            width = 1.dp,
                                            color = PrimaryDark,
                                            shape = RoundedCornerShape(10.dp)
                                        ).clickable {
                                            takePhotoFromAlbumLauncher.launch(
                                                takePhotoFromAlbumIntent
                                            )
                                        }.animateItemPlacement(),
                                    contentAlignment = Alignment.TopEnd
                                ) {
                                    Image(
                                        modifier = Modifier.fillMaxSize(),
                                        bitmap = bitmap.asImageBitmap(),
                                        contentDescription = null,
                                        contentScale = ContentScale.FillBounds
                                    )
                                    Icon(
                                        modifier = Modifier.clickable {
                                            bitmapList.remove(bitmap)
                                        },
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = null,
                                        tint = GrayScale,
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Suppress("DEPRECATION", "NewApi")
    private fun Uri.parseBitmap(context: Context): Bitmap {
        return when (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) { // 28
            true -> {
                val source = ImageDecoder.createSource(context.contentResolver, this)
                ImageDecoder.decodeBitmap(source)
            }
            else -> {
                MediaStore.Images.Media.getBitmap(context.contentResolver, this)
            }
        }
    }
}
