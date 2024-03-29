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
        R.drawable.j2
    ),
    ImageData(
        R.drawable.j4
    ),
    ImageData(
        R.drawable.j5
    ),
    ImageData(
        R.drawable.j6
    ),
)

@OptIn(ExperimentalPagerApi::class)
@Composable
fun DetailScreen10(navController: NavController, activityResultRegistry: ActivityResultRegistry) {
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
            val uri = Uri.parse("https://goo.gl/maps/i74yxNMWHu9XW7wdA")
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
            LocationChip(text = "Jl. Tebet Barat Raya, Tebet, Jakarta Selatan")
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
            text = "Tebet Eco Park",
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
                subtitle = "Senin - Minggu\n08:00 - 17:00 WIB",
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
        title = "Tebet Eco Park",
        detail = "Jl. Tebet Barat Raya, RT.1/RW.10, Tebet Bar., Kec. Tebet, Kota Jakarta Selatan, Daerah Khusus Ibukota Jakarta."
    ),

    TripDayData(
        title = "Website Resmi",
        detail = "https://tebetecopark.id/"
    ),

    TripDayData(
        title = "Deskripsi",
        detail = "Tebet Eco Park (TEP) merupakan taman kota yang didedikasikan untuk masyarakat dan lingkungan. Terletak di Jakarta Selatan dengan area seluas 7,3 hektare, TEP kini hadir sebagai ruang terbuka hijau yang telah direvitalisasi. Dua kawasan taman yang awalnya terpisah dan berseberangan – Taman Tebet Utara dan Taman Tebet Selatan, kini telah menjadi satu taman terpadu yang mengusung konsep harmonisasi antara fungsi ekologi, sosial, edukasi dan rekreasi.\n" +
                "\n" +
                "Setiap zona TEP dirancang untuk mengambil peran penting dalam keberlangsungan lingkungan dan interaksi sosial, mulai dari menjaga kualitas alamiah lingkungan hingga meningkatkan kualitas hidup pengunjung dan masyarakat sekitarnya. Sungai yang di renaturalisasi, rawa (wetland) yang menjadi kolam retensi, konservasi tanaman dan penanaman kembali untuk mereduksi polusi, sampai berbagai ruang hijau terbuka yang berfungsi memfasilitasi masyarakat untuk berinteraksi.\n" +
                "\n" +
                "Lebih dari sebuah taman, Tebet Eco Park adalah ekosistem dimana alam dan manusia saling berinteraksi dan saling melindungi dalam sebuah harmoni.\n." +
                "\n" +
                "Dalam proses pembangunan Tebet Eco Park, terdapat beberapa cerita menarik. Antara lain : \n" +
                "\n" +
                "Kolaborasi Multi-Lembaga Pemerintah\n" +
                "\n" +
                "Proses pembangunan Tebet Eco Park dikawal dengan guideline dan rekomendasi teknis yang komprehensif untuk memastikan proses konstruksi tidak mengganggu proses ekologis yang terjadi di taman. Desain ekologis yang diterapkan dalam perencanaan, harus juga diaplikasikan dalam proses eksekusi fisiknya. Dalam tahap pekerjaan pendahuluan meliputi grading tanah, pembongkaran turap PHB, maupun pruning dan seleksi pohon yang rawan tumbang, terdapat tenaga ahli yang dapat memberikan penilaian dan rekomendasi khusus terhadap masalah yang ada di lapangan sehingga tetap sesuai dengan kaidah proses konstruksi untuk memitigasi kerusakan.\n" +
                "\n" +
                "Revitalisasi untuk pemulihan ekologi\n" +
                "\n" +
                "Langkah strategis Pemerintah Provinsi DKI Jakarta merevitalisasi Taman Tebet berpihak pada ekologi dan pengembangan berkelanjutan sebagai ruang publik yang mengundang semua orang berkunjung dengan ramah dan inklusif. Revitalisasi ini mewujudkan ruang publik yang mengemban fungsi rekreatif dan edukatif dengan semangat kolaborasi dengan berbagai pihak dalam hal pendanaan dan konstruksi. Sinergi positif dalam mewujudkan taman yang representatif bagi kota Megapolitan, Jakarta.\n" +
                "\n" +
                "Transformasi yang mendekatkan kita dengan alam\n" +
                "\n" +
                "Tebet Eco Park memiliki prinsip untuk meminimalisasi pembongkaran area taman yang sebelumnya sudah rindang dan hijau dengan berbagai pohon dan vegetasi. Dalam pertimbangan desain, ekosistem hutan kota yang sudah lama berlangsung dijaga agar tidak terganggu bahkan, dimaksimalkan potensinya sebagai centerpiece atau atraksi utama dari taman. Hal ini dilakukan dengan menggunakan garis organik yang melengkung dan meliuk, berada diantara pepohonan agar tidak banyak pohon yang harus ditebang ataupun direlokasi. Paviliun utara dan selatan juga mengakomodasi pohon pohon besar agar tetap hidup tanpa harus mengubah bentuk bangunan dengan cara menyediakan void (lubang) di atap yang memungkinkan batang pohon berada di dalam bangunan, akar pohon terlindung dan tetap dapat mengambil nutrisi dari tanah, dan kanopi pohon berada di atas atap agar tetap mendapatkan cahaya matari dengan maksimal.\n"

    ),

    TripDayData(
        title = "Denah Lokasi",
        detail = "https://i.imgur.com/CCjO0aJ.png"
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
                                Uri.parse("https://goo.gl/maps/i74yxNMWHu9XW7wdA")
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
