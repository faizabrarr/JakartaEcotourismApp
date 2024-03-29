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
        R.drawable.f1
    ),
    ImageData(
        R.drawable.f2
    ),
    ImageData(
        R.drawable.f3
    ),
    ImageData(
        R.drawable.f4
    ),
    ImageData(
        R.drawable.f5
    ),
    ImageData(
        R.drawable.f6
    ),
)

@OptIn(ExperimentalPagerApi::class)
@Composable
fun DetailScreen6(navController: NavController, activityResultRegistry: ActivityResultRegistry) {
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
            val uri = Uri.parse("https://goo.gl/maps/nznE1Tk9vsVfEJtv9")
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
            LocationChip(text = "Srengseng Sawah, Jagakarsa, Jakarta Selatan")
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
            text = "Setu Babakan",
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
                subtitle = "Senin - Minggu\n09:00 - 16:00 WIB",
                modifier = Modifier
            )

            TripDataItem(
                imageVector = Icons.Default.AttachMoney,
                title = "Biaya masuk",
                subtitle = "Gratis",
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
        title = "Setu Babakan",
        detail = "Srengseng Sawah, Kec. Jagakarsa, Kota Jakarta Selatan, Daerah Khusus Ibukota Jakarta."
    ),

    TripDayData(
        title = "Website Resmi",
        detail = "https://www.setubabakanbetawi.com/"
    ),

    TripDayData(
        title = "Deskripsi",
        detail = "Perkampungan Budaya Betawi (PBB) merupakan embrio pusat kebudayaan Betawi, suatu tempat di mana ditumbuhkembangkan keasrian alam, tradisi Betawi yang meliputi: Keagamaan, Kebudayaan dan Kesenian Betawi.\n" +
                "\n" +
                "Ide dan keinginan untuk membangun pusat kebudayaan Betawi sesungguhnya sudah tercetus sejak tahun 90 – an. Kemudian oleh BAMUS BETAWI (Badan Musyawarah Masyarakat Betawi) periode 1996 – 2001 keinginan ini dituangkan dalam sebuah rancangan program kerja yakni ”Membangun Pusat Perkampungan Budaya Betawi”.\n" +
                "\n" +
                "Desakan masyarakat Betawi yang amat kuat, dukungan tokoh-tokoh Betawi terdidik serta organisasi masyarakat ke-Betawian, bersama BAMUS BETAWI sebagai lembaga yang mengkoordinir dan mengayomi seluruh aktivitas organisasi-organisasi dan yayasan-yayasan masyarakat Betawi, akhirnya melahirkan kesepakatan. Tanpa melampaui tugas dan kewenangan Pemda DKI Jakarta pada tahun 1998, BAMUS BETAWI mengajukan proposal tentang “Pembangunan Perkampungan Budaya Betawi” dengan alternatif lokasi Setu Babakan Srengseng Sawah, Kecamatan Jagakarsa Jakarta Selatan. Usaha ini semata-mata karena semua pihak memiliki tanggung jawab moral untuk memotivasi, membina dan membangun serta sekaligus melestarikan budaya Betawi ini.\n" +
                "\n" +
                "Untuk lebih memantapkan usulan BAMUS BETAWI dan kebijakan Pemda DKI Jakarta, sebelumnya pada tanggal 13 September 1997 diselenggarakan “Festival Setu Babakan / sehari di Setu Babakan” oleh Sudin Pariwisata Jakarta Selatan dan mendapat sambutan hangat dari masyarakat. Karena dalam acara tersebut dapat dilihat jelas aktivitas masyarakat dengan kekentalan budayanya mulai dari pakaian, hasil industri rumahan, buah-buahan dan lainnya. Bersamaan dengan ini pula BAMUS menyerahkan kepada masyarakat dan salah satu organisasi pendukung (SATGAS PBB) untuk menjaga dan memantau embrio PBB (sampai sekarang).\n" +
                "\n" +
                "Pada tahun 2000 Gubernur Provinsi DKI Jakarta mengeluarkan Surat Keputusan Gubernur No. 92 tahun 2000 tentang Penataan Lingkungan Perkampungan Budaya Betawi di Kelurahan Srengseng Sawah Kecamatan Jagakarsa Jakarta Selatan. Berdasarkan SK tersebut akhirnya mulailah dibangun embrio PBB pada tanggal 15 September 2000.\n" +
                "\n" +
                "Kemudian pada tanggal 20 Januari 2001, BAMUS BETAWI mengadakan Halal Bihalal dengan organisasi pendukung dan masyarakat Betawi pada umumnya, dan pada saat itu pulalah Gubernur Provinsi DKI Jakarta saat itu, Bpk. Sutiyoso menandatangani prasasti Pencanangan awal Perkampungan Budaya Betawi. Sementara itu Ketua Umum Bamus Betawi (Bpk.dr.H.Abdul Syukur, S.K.M.) memberi mandat kepada SATGAS PBB untuk berperan aktif mengawasi Perkampungan Budaya Betawi (terutama Setu Babakan dan Setu Mangga Bolong).\n" +
                "\n" +
                "Yang harus dipahami bersama:\n" +
                "\n" +
                "Bahwa Perkampungan Budaya Betawi dibuat bukan untuk “mengaboriginkan” kaum Betawi dan juga bukan semata-mata untuk tujuan wisata, tetapi lebih kepada pelestarian, pengembangan dan penataan Budaya Betawi.\n" +
                "\n" +
                "Mengingat Perkampungan Budaya Betawi semakin banyak mendapat perhatian publik, sementara payung hukum yang ada (SK Gubernur no. 92 th. 2000) belum dapat menaungi secara utuh, maka melalui usulan, saran dari berbagai pihak agar dibuat satu Perda tentang Perkampungan Budaya Betawi, maka pada tanggal 10 Maret 2005 lahirlah “Perda no. 3 th. 2005” tentang Penetapan Perkampungan Budaya Betawi di Kelurahan Srengseng Sawah Kecamatan Jagakarsa Jakarta Selatan.\n" +
                "\n" +
                "Melalui Perda ini diharapkan pengembangan Perkampungan Budaya Betawi dapat lebih terkoordinasi dan tertata tentu juga menjadi tanggung jawab kita bersama untuk mengawal Perda tersebut."
    ),

    TripDayData(
        title = "Denah Lokasi",
        detail = "https://i.imgur.com/YrIdSAW.png"
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
                                Uri.parse("https://goo.gl/maps/nznE1Tk9vsVfEJtv9")
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
