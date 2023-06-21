@file:OptIn(ExperimentalPagerApi::class)

package com.example.jakartaecotourismapp.ui.features

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultRegistry
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavController
import com.example.jakartaecotourismapp.R
import com.example.jakartaecotourismapp.ui.model.ImageData
import com.example.jakartaecotourismapp.ui.model.LocationChip
import com.example.jakartaecotourismapp.ui.model.TopButton
import com.example.jakartaecotourismapp.ui.model.TripDataItem
import com.example.jakartaecotourismapp.ui.model.TripDayContent
import com.example.jakartaecotourismapp.ui.model.TripDayData
import com.google.accompanist.insets.statusBarsPadding
import com.google.accompanist.pager.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield
import kotlin.math.absoluteValue

private val imageList = listOf(
    ImageData(
        R.drawable.c1
    ),
    ImageData(
        R.drawable.c2
    ),
    ImageData(
        R.drawable.c3
    ),
    ImageData(
        R.drawable.c4
    ),
    ImageData(
        R.drawable.c5
    ),
)

@OptIn(ExperimentalPagerApi::class)
@Composable
fun DetailScreen3(navController: NavController, activityResultRegistry: ActivityResultRegistry) {
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { _ ->

    }

    LazyColumn(
        modifier = Modifier
            .fillMaxHeight()
            .background(if (isSystemInDarkTheme()) Color.White else Color.White),
    ) {
        item {
            ViewPagerSlider(navController)
            TripInfoContent(navController)
        }

        itemsIndexed(tripDays) { _, data ->
            TripDayContent(data, launcher)
        }

    }
}

@ExperimentalPagerApi
@Composable
private fun ViewPagerSlider(navController: NavController) {
    val pagerState = rememberPagerState(
        pageCount = imageList.size,
        initialPage = 0
    )

    LaunchedEffect(Unit) {
        while (true) {
            yield()
            delay(4000)
            pagerState.animateScrollToPage(
                page = (pagerState.currentPage + 1) % (pagerState.pageCount),
                animationSpec = tween(600)
            )
        }
    }

    Box(
        modifier = Modifier
            .statusBarsPadding()
            .fillMaxWidth()
    ) {
        // Gambar slide
        HorizontalPager(state = pagerState, modifier = Modifier) { page ->
            Card(
                modifier = Modifier
                    .graphicsLayer {
                        val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue

                        lerp(
                            start = 0.85f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        ).also { scale ->
                            scaleX = scale
                            scaleY = scale
                        }
                        alpha = lerp(
                            start = 0.5f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )
                    }
                    .fillMaxWidth()
            ) {
                val newKids = imageList[page]
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                ) {
                    Image(
                        painter = painterResource(id = newKids.imgUri),
                        contentDescription = "Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Column(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(15.dp)
                    ) {
                        // Konten di bagian bawah gambar slide
                    }
                }
            }
        }

        // TopButton pertama
        TopButton(
            imageVector = Icons.Default.ArrowBack,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(16.dp)
        ) {
            navController.popBackStack()
        }

        // TopButton kedua
        TopButton(
            imageVector = Icons.Default.Map,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp)
        ) {
            val uri = Uri.parse("https://goo.gl/maps/4q3GqzPP4dpEyD2v5")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(navController.context, intent, null)
        }

        // Horizontal dot indicator
        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
        )
    }
}



@Composable
private fun TripInfoContent(navController: NavController) {
    Column(
        modifier = Modifier
            .padding(10.dp)
    ) {
        Row {
            LocationChip(text = "Jl. H. Kelik, Srengseng, Jakarta Barat")
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = Icons.Default.Star, contentDescription = "",
                modifier = Modifier
                    .padding(end = 8.dp)
                    .size(12.dp)
                    .align(CenterVertically),
                tint = Color(0xFFFBC110)
            )

            Text(
                text = "4,4",
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Hutan Kota Srengseng",
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
        )

        Divider(
            color = Color(0xFFECECEE),
            modifier = Modifier.padding(10.dp)
        )

        Row(Modifier.align(Alignment.Start)) {
            TripDataItem(
                imageVector = Icons.Default.CalendarToday,
                title = "Waktu Operasional",
                subtitle = "Senin - Minggu\n07:00 - 17:00 WIB",
                modifier = Modifier
            )

            TripDataItem(
                imageVector = Icons.Default.AttachMoney,
                title = "Biaya masuk",
                subtitle = "Rp. 2000",
                modifier = Modifier.padding(horizontal = 8.dp)
            )

        }

        Divider(
            color = Color(0xFFECECEE),
            modifier = Modifier.padding(8.dp)
        )
    }
}

private var tripDays = listOf(
    TripDayData(
        title = "Hutan Kota Srengseng",
        detail = "Jl. H. Kelik, RT.8/RW.6, Srengseng, Kec. Kembangan, Kota Jakarta Barat, Daerah Khusus Ibukota Jakarta  ."
    ),

    TripDayData(
        title = "Deskripsi",
        detail = "Ketika sedang berada di Jakarta, ngadem di mall adalah kegiatan lumrah. Namun jangan salah Jakarta juga punya hutan yang enak sebagai tempat ngadem seperti Hutan Kota Srengseng. Hutan ini merupakan ruang hijau yang berada di Srengseng, Jakarta Barat.\n" +
                "\n" +
                "Luasnya mencapai lebih dari 15 hektar dan berisi lebih dari 10.000 pohon. Suasana sejuknya sangat pas untuk menyegarkan tubuh maupun pikiran. Jika butuh pelarian singkat dari kesibukan kota, cobalah untuk mengunjungi teduhnya Hutan Kota Srengseng.\n"
    ),
)



//package com.example.jakartaecotourismapp.ui.features
//
//import android.content.Intent
//import android.net.Uri
//import androidx.compose.foundation.BorderStroke
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.itemsIndexed
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.Button
//import androidx.compose.material.ButtonDefaults
//import androidx.compose.material.Divider
//import androidx.compose.material.Icon
//import androidx.compose.material.Text
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.ArrowBack
//import androidx.compose.material.icons.filled.AttachMoney
//import androidx.compose.material.icons.filled.CalendarToday
//import androidx.compose.material.icons.filled.LocationOn
//import androidx.compose.material.icons.filled.Map
//import androidx.compose.material.icons.filled.Star
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Alignment.Companion.CenterVertically
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.vector.ImageVector
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.core.content.ContextCompat.startActivity
//import androidx.navigation.NavController
//import com.google.accompanist.coil.rememberCoilPainter
//import com.google.accompanist.insets.statusBarsPadding
//import androidx.activity.compose.rememberLauncherForActivityResult
//import androidx.activity.result.ActivityResultLauncher
//import androidx.activity.result.ActivityResultRegistry
//import androidx.activity.result.contract.ActivityResultContracts
//import com.example.jakartaecotourismapp.ui.model.TopButton
//
//@Composable
//fun DetailScreen3(navController: NavController, activityResultRegistry: ActivityResultRegistry) {
//    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { _ ->
//
//    }
//
//    LazyColumn() {
//        item {
//            DetailHeader3(navController)
//            TripInfoContent3(navController)
//        }
//
//        itemsIndexed(tripDays3) { _, data ->
//            TripDayContent3(data, launcher)
//        }
//
//    }
//}
//
//
//@Composable
//fun DetailHeader3(navController: NavController) {
//    val detailHeaderImageUrl = "https://getlost.id/wp-content/uploads/2021/06/@adriantjahjono-696x463.jpg"
//    Box() {
//        Image(
//            painter = rememberCoilPainter(request = detailHeaderImageUrl),
//            contentDescription = "",
//            contentScale = ContentScale.Crop,
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(300.dp)
//        )
//
//        Box(
//            modifier = Modifier
//                .statusBarsPadding()
//                .fillMaxWidth()
//        ) {
//            TopButton(
//                imageVector = Icons.Default.ArrowBack,
//                modifier = Modifier
//                    .align(Alignment.TopStart)
//                    .padding(16.dp)
//            ) {
//                navController.popBackStack()
//            }
//
//            TopButton(
//                imageVector = Icons.Default.Map,
//                modifier = Modifier
//                    .align(Alignment.TopEnd)
//                    .padding(16.dp)
//            ) {
//                val uri = Uri.parse("https://goo.gl/maps/4q3GqzPP4dpEyD2v5")
//                val intent = Intent(Intent.ACTION_VIEW, uri)
//                startActivity(navController.context, intent, null)
//            }
//        }
//    }
//}
//
//@Composable
//fun TripInfoContent3(navController: NavController) {
//    Column(
//        modifier = Modifier
//            .padding(10.dp)
//    ) {
//        Row {
//            LocationChip3(text = "Jl. H. Kelik, Srengseng, Jakarta Barat")
//            Spacer(modifier = Modifier.weight(1f))
//            Icon(
//                imageVector = Icons.Default.Star, contentDescription = "",
//                modifier = Modifier
//                    .padding(end = 8.dp)
//                    .size(12.dp)
//                    .align(CenterVertically),
//                tint = Color(0xFFFBC110)
//            )
//
//            Text(
//                text = "4,4",
//                fontWeight = FontWeight.SemiBold,
//                fontSize = 14.sp,
//            )
//        }
//
//        Spacer(modifier = Modifier.height(8.dp))
//
//        Text(
//            text = "Hutan Kota Srengseng",
//            fontWeight = FontWeight.SemiBold,
//            fontSize = 18.sp,
//        )
//
//        Divider(
//            color = Color(0xFFECECEE),
//            modifier = Modifier.padding(10.dp)
//        )
//
//        Row(Modifier.align(Alignment.Start)) {
//            TripDataItem3(
//                imageVector = Icons.Default.CalendarToday,
//                title = "Waktu Operasional",
//                subtitle = "Senin - Minggu\n07:00 - 17:00 WIB",
//                modifier = Modifier
//            )
//
//            TripDataItem3(
//                imageVector = Icons.Default.AttachMoney,
//                title = "Biaya masuk",
//                subtitle = "Rp. 2000",
//                modifier = Modifier.padding(horizontal = 8.dp)
//            )
//
//        }
//
//        Divider(
//            color = Color(0xFFECECEE),
//            modifier = Modifier.padding(8.dp)
//        )
//    }
//}
//
//
//
//data class TripDayData3(val title: String, val detail: String)
//
//var tripDays3 = listOf(
//    TripDayData3(
//        title = "Hutan Kota Srengseng",
//        detail = "Jl. H. Kelik, RT.8/RW.6, Srengseng, Kec. Kembangan, Kota Jakarta Barat, Daerah Khusus Ibukota Jakarta  ."
//    ),
//
//    TripDayData3(
//        title = "Deskripsi",
//        detail = "Ketika sedang berada di Jakarta, ngadem di mall adalah kegiatan lumrah. Namun jangan salah Jakarta juga punya hutan yang enak sebagai tempat ngadem seperti Hutan Kota Srengseng. Hutan ini merupakan ruang hijau yang berada di Srengseng, Jakarta Barat.\n" +
//                "\n" +
//                "Luasnya mencapai lebih dari 15 hektar dan berisi lebih dari 10.000 pohon. Suasana sejuknya sangat pas untuk menyegarkan tubuh maupun pikiran. Jika butuh pelarian singkat dari kesibukan kota, cobalah untuk mengunjungi teduhnya Hutan Kota Srengseng.\n"
//    ),
//)
//
//@Composable
//fun TripDayContent3(day: TripDayData3, launcher: ActivityResultLauncher<Intent>) {
//    val uri = Uri.parse(day.detail)
//    val intent = Intent(Intent.ACTION_VIEW, uri)
//
//    Column(
//        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
//    ) {
//        Text(
//            text = day.title,
//            fontSize = 14.sp,
//            fontWeight = FontWeight.ExtraBold,
//            letterSpacing = 0.75.sp
//        )
//
//        Spacer(modifier = Modifier.height(8.dp))
//
//        if (day.detail.startsWith("http://") || day.detail.startsWith("https://")) {
//            Text(
//                text = day.detail,
//                fontSize = 14.sp,
//                fontWeight = FontWeight.Light,
//                lineHeight = 18.sp,
//                modifier = Modifier.clickable { launcher.launch(intent) }
//            )
//        } else {
//            Text(
//                text = day.detail,
//                fontSize = 14.sp,
//                fontWeight = FontWeight.Light,
//                lineHeight = 18.sp,
//            )
//        }
//    }
//}
//
//
//
//@Composable
//fun TripDataItem3(imageVector: ImageVector, title: String, subtitle: String, modifier: Modifier) {
//    Row() {
//        Icon(
//            modifier = Modifier
//                .padding(8.dp)
//                .clip(CircleShape)
//                .background(Color(0xFFF5F6FF))
//                .size(32.dp)
//                .padding(8.dp),
//            imageVector = imageVector,
//            contentDescription = ""
//        )
//
//        Column {
//            Text(
//                text = title,
//                fontSize = 12.sp,
//                fontWeight = FontWeight.ExtraBold
//            )
//
//            Text(
//                text = subtitle,
//                fontSize = 11.sp,
//                fontWeight = FontWeight.Normal
//            )
//        }
//    }
//}
//
//
//@Composable
//fun LocationChip3(text: String) {
//    Row(
//        modifier = Modifier
//            .clip(RoundedCornerShape(6.dp))
//            .background(Color(0xFFFBF110))
//            .padding(horizontal = 4.dp, vertical = 1.dp)
//    ) {
//        Icon(
//            imageVector = Icons.Default.LocationOn,
//            contentDescription = "",
//            modifier = Modifier
//                .padding(end = 4.dp)
//                .size(12.dp)
//                .align(Alignment.CenterVertically)
//        )
//
//        Text(
//            text = text,
//            fontWeight = FontWeight.SemiBold,
//            fontSize = 13.sp,
//            color = Color.Black,
//        )
//    }
//}
//
//@Composable
//fun TopButton3(imageVector: ImageVector, modifier: Modifier, clickListener: () -> Unit) {
//    Button(
//        onClick = { clickListener() },
//        border = BorderStroke(2.dp, Color(0xFFEAFBFF)),
//        shape = CircleShape,
//        colors = ButtonDefaults.buttonColors(
//            backgroundColor = Color(0xDDF6F9FF),
//            contentColor = Color(0xFF3562D7)
//        ),
//        modifier = modifier.size(48.dp)
//    ) {
//        Icon(imageVector = imageVector, contentDescription = "")
//    }
//}