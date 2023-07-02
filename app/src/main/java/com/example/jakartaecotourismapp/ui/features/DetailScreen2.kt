@file:OptIn(ExperimentalPagerApi::class)

package com.example.jakartaecotourismapp.ui.features

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.ActivityResultRegistry
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.jakartaecotourismapp.R
import com.example.jakartaecotourismapp.ui.model.ImageData
import com.example.jakartaecotourismapp.ui.model.LocationChip
import com.example.jakartaecotourismapp.ui.model.TopButton
import com.example.jakartaecotourismapp.ui.model.TripDataItem
import com.example.jakartaecotourismapp.ui.model.TripDayData
import com.google.accompanist.insets.statusBarsPadding
import com.google.accompanist.pager.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield
import kotlin.math.absoluteValue

private val imageList = listOf(
    ImageData(
        R.drawable.b1
    ),
    ImageData(
        R.drawable.b3
    ),
    ImageData(
        R.drawable.b4
    ),
    ImageData(
        R.drawable.b5
    ),
)

@OptIn(ExperimentalPagerApi::class)
@Composable
fun DetailScreen2(navController: NavController, activityResultRegistry: ActivityResultRegistry) {
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
            val uri = Uri.parse("https://goo.gl/maps/G26BkjffvJiXMNjdA")
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
            LocationChip(text = "Jl. Lodan Timur No.7, Ancol, Jakarta Utara")
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
                text = "4,6",
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Allianz Ecopark",
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
                subtitle = "Senin - Minggu\n06:00 - 17:00 WIB",
                modifier = Modifier
            )

            TripDataItem(
                imageVector = Icons.Default.AttachMoney,
                title = "Biaya masuk",
                subtitle = "Rp. 25.000\n(Biaya masuk\nkawasan ancol)",
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
        title = "Allianz Ecopark",
        detail = "Jl. Lodan Timur No.7, RW.10, Ancol, Kec. Pademangan, Jkt Utara, Daerah Khusus Ibukota Jakarta ."
    ),

    TripDayData(
        title = "Website Resmi",
        detail = "https://www.ancol.com/unit-rekreasi/ecopark-ancol--7"
    ),

    TripDayData(
        title = "Deskripsi",
        detail = "Ecopark Ancol memiliki luas lahan hampir 34 hektar merupakan kawasan hasil dari mengalihfungsikan Padang Golf Ancol menjadi sebuah sarana rekreasi terbaru yang menawarkan nilai-nilai edukasi (edutainment) dan petualangan (adventure) dengan pendekatan green lifestyle, menjadi ruang terbuka bagi pengunjung Ancol Taman Impian untuk mengeksplorasi pengetahuan botani dan rekreasi luar ruang.\n" +
                "\n" +
                "Ecopark Ancol terbagi menjadi beberapa kawasan (zona) dengan fungsi dan fasilitas berbeda.\n" +
                "\n" +
                "Selain Eco Care, Eco Nature, dan Eco Art, Ecopark Ancol juga akan menghadirkan zona Eco Energy.\n" +
                "\n" +
                "Pada keseluruhan zona ini, beragam jenis tanaman pesisir sesuai dengan kegunaannya bagi lingkungan hidup telah ditanam, sekaligus untuk menjadikan Ecopark sebagai sebuah kawasan yang hijau dan teduh, serta tempat pembelajaran botani lengkap yang menyenangkan.\n" +
                "\n" +
                        "Allianz Ecopark atau Ecopark Ancol memiliki 4 Zona, yaitu :\n" +
                "1.\tEco-Care\n" +
                "Di Zona Eco-Care kalian akan diajak untuk melepas setres dengan berbagai permainan edukasi seru bersifat kekeluargaan. Selain itu bagi kalian yang pengen belajar cara menanam sayur atau tumbuhan lain yang baik dan benar.Di area ini kalian sekeluarga akan diajak berladang dan menyiangi tanaman di ladang. Bagi kalian yang hobi berkebun, aku jamin kalian nggak bakalan bosen di zona ini.\n" +
                "\n" +
                "2.\tEco-Art\n" +
                "Di zona Eco-Art, kalian akan dimanjakan dengan berbagai pertunjukan seni dan pameran yang patut dikunjungi. Menurut sebagian orang, zona ini paling seru di Ocean Ecopark Ancol. Namun, setiap orang punya hobi yang beda-beda, jadi menurutku sih relatif. Di zona ini, kalian dalam satu jam akan dihibur oleh pertunjukan teater panggung terbuka Fantastique Magic Fountain Show yang mementaskan cerita rakyat terkenal Timun Mas dan Buto Ijo\n" +
                "\n" +
                "3.\tEco-Nature\n" +
                "Sedangkan Eco-Nature akan memberi edukasi tentang berbagai jenis flora fauna unik dan hijau alam menenangkan. Kalian bisa berkenalan dengan aneka sawa yang biasanya hanya kalian lihat di TV-TV aja. Bisa dikatakan, zona ini adalah faunaland. Seperti burung kasuari, burung pelican, rusa dan kera. Wahana ini jadi satu sama Eco-Island yang berwujud sebuah danau buatan yang dihuni ribuan ikan koi, lele dan gurami.\n" +
                "\n" +
                "4.\tEco-Energy\n" +
                "Terakhir adalah Eco-Energy, Mungkin zona ini pada saat ini belum selesai dibangun. Kawasan eco energy nantinya akan difokuskan untuk memperkenalkan konservasi energi dan sumber energi terbaru. Zona ini dibangan sebab di era modern ini, manusia rata-rata termanjakan dengan energi yang tak bisa diperbaharui. Dengan adanya Eco-Energy ini, diharapkan pengunjung dapat lebih memahami apa itu energi alternatif dan bagaimana penggunaannya.\n"
    ),

    TripDayData(
        title = "Denah Lokasi",
        detail = "https://i.imgur.com/jEyLpde.png"
    ),
)

@Composable
private fun TripDayContent(data: TripDayData, launcher: ActivityResultLauncher<Intent>) {
    Column(Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
        Text(
            text = data.title,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            modifier = Modifier.padding(bottom = 4.dp)
        )

        when (data.title) {
            "Denah Lokasi" -> {
                val imageUrl = data.detail
                val painter = rememberImagePainter(imageUrl)
                val context = LocalContext.current
                Image(
                    painter = painter,
                    contentDescription = "Image",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(550.dp)
                        .clickable {
                            val intent = Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("https://goo.gl/maps/G26BkjffvJiXMNjdA")
                            )
                            startActivity(context, intent, null)
                        }
                )
            }
            "Website Resmi" -> {
                Text(
                    text = data.detail,
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    modifier = Modifier.clickable {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(data.detail))
                        launcher.launch(intent)
                    }
                )
            }
            else -> {
                Text(
                    text = data.detail,
                    fontSize = 14.sp,
                    lineHeight = 20.sp
                )
            }
        }
    }
}