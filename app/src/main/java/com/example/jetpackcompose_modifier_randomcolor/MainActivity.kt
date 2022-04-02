package com.example.jetpackcompose_modifier_randomcolor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.jetpackcompose_modifier_randomcolor.ui.theme.JetpackCompose_Modifier_RandomColorTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackCompose_Modifier_RandomColorTheme {

                val photoList = arrayListOf<MyPhotoItem>().apply {
                    repeat(100) {

                        val photoLink = when {
                            it % 5 == 0 -> {
                                "https://developer.android.com/codelabs/jetpack-compose-animation/img/ea1442f28b3c3b39.png?hl=ko"
                            }
                            it % 5 == 4 -> {
                                "https://www.onlineimagetool.com/resources/static/1.0.0/images/bg/split-screen.jpg"
                            }
                            it % 5 == 3 -> {
                                "https://png.pngtree.com/png-clipart/20210831/ourmid/pngtree-year-of-the-tiger-korea-2022-tiger-cute-style-png-image_3840724.jpg"
                            }
                            it % 5 == 2 -> {
                                "https://img.etoday.co.kr/pto_db/2017/09/20170907015230_1122844_600_440.jpg"
                            }
                            else -> {
                                "https://img.marieclairekorea.com/2017/09/mck_59b0aa225f5e5.jpg"
                            }
                        }

                        add(MyPhotoItem(it, photoLink))
                    }
                }

                MyPhotoListView(photoList)
            }
        }
    }
}

@Composable
fun MyPhotoListView(photoList: ArrayList<MyPhotoItem>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
    ) {
        items(count = photoList.size, key = { it }) {
            val item = photoList[it]
            PhotoItemView(item)
        }
    }
}

@Composable
fun PhotoItemView(item: MyPhotoItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 14.dp),
        shape = RoundedCornerShape(8.dp),
        backgroundColor = MaterialTheme.colors.surface,
    ) {
        Row(
            modifier = Modifier
                .background(color = Color.random()),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "${item.idx}", style = MaterialTheme.typography.h4, color = Color.Black)

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(item.photoLink)
                    .size(200)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.load),
                contentDescription = item.photoLink,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(all = 10.dp)
                    .clip(CircleShape)
                    .size(100.dp)
            )
        }
    }
}

data class MyPhotoItem(
    val idx: Int,
    val photoLink: String
)

// 랜덤 칼라 가져오기
fun Color.Companion.random(): Color {
    val red = Random.nextInt(256)
    val green = Random.nextInt(256)
    val blue = Random.nextInt(256)
    return Color(red, green, blue)
}