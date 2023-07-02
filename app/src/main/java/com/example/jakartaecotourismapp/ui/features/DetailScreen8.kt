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
        R.drawable.h1
    ),
    ImageData(
        R.drawable.h2
    ),
    ImageData(
        R.drawable.h3
    ),
    ImageData(
        R.drawable.h4
    ),
    ImageData(
        R.drawable.h5
    ),
)

@OptIn(ExperimentalPagerApi::class)
@Composable
fun DetailScreen8(navController: NavController, activityResultRegistry: ActivityResultRegistry) {
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
            val uri = Uri.parse("https://goo.gl/maps/7XL865oB3cn3aLS49")
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
            LocationChip(text = "Pulau Pramuka, Kep. Seribu Uatara, Kep. Seribu")
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
            text = "Taman Nasional Kepulauan Seribu",
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
                subtitle = "Buka 24jam",
                modifier = Modifier
            )

            TripDataItem(
                imageVector = Icons.Default.AttachMoney,
                title = "Biaya masuk",
                subtitle = "Rp. 5000",
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
        title = "Taman Nasional Kepulauan Seribu",
        detail = "Pulau Pramuka, Pulau Panggang, Kepulauan Seribu Utara, Kab. Administrasi Kepulauan Seribu, Daerah Khusus Ibukota Jakarta."
    ),

    TripDayData(
        title = "Website Resmi",
        detail = "https://tnlkepulauanseribu.menlhk.go.id/"
    ),

    TripDayData(
        title = "Deskripsi",
        detail = "Perjalanan Taman Nasional Kepulauan\n" +
                "\n" +
                "Keberadaan Taman Nasional Kepulauan Seribu diawali dengan berbagai cerita yang menarik. Bermula dari tahun 1979, didukung oleh FAO (Food and Agriculture Organization) PBB, dilakukan kajian dan survey di Indonesia untuk menemukan satu lokasi perairan laut yang cocok dijadikan sebagai Taman Nasional laut (marine national park) pertama di Indonesia. Saat itu FAO sedang menjalankan proyek membantu under-developed country (negara belum berkembang) dalam upaya konservasi untuk pembangunan yang berkelanjutan.\n" +
                "\n" +
                "Kajian untuk menentukan Taman Nasional Kepulauan Seribu berlangsung dari tahun 1979 sampai tahun 1981. Kajian terhadap kondisi Kepulauan Seribu berbasis pada pencarian ekosistem penting. Berdasarkan hasil kajian dan disertai pertimbangan akses yang mudah dan strategis karena berada di ibu kota Indonesia, namun mengalami tingginya ancaman terhadap sumber daya alam, maka dipilihlah Kepulauan Seribu.\n" +
                "\n" +
                "Setelah penentuan Kepulauan Seribu sebagai kawasan perairan laut yang dijadikan Taman Nasional laut pertama di Indonesia, pada tahun 1982 ditetapkan Cagar Alam Pulau Seribu. Penetapan Cagar Alam Pulau Seribu itu bertujuan untuk melindungi beberapa pulau dan perairan penting ekologis di Kepulauan Seribu. Saat itu, Kepulauan Seribu menghadapi ancaman yang dapat membahayakan kelangsungan ekosistem laut dan biota laut. Penangkapan ikan dengan bom dan racun sianida ditambah maraknya aktivitas pengambilan cangkang kima raksasa (Tridacna gigas) dengan mencongkel karang benar-benar menjadi tiga ancaman serius di Kepulauan Seribu.\n" +
                "\n" +
                "Maka ketika pada tahun yang sama, yaitu bulan Oktober 1982, diselenggarakan Kongres Taman Nasional Sedunia di Bali, menjadi kesempatan besar menyatakan Cagar Alam Kepulauan Seribu sebagai Calon Taman Nasional Kepulauan Seribu.\n" +
                "\n" +
                "Dalam proses penetapan dan pembentukan Taman Nasional Kepulauan Seribu, diwarnai dengan upaya meyakinkan pemerintah DKI Jakarta mengenai pentingnya keberadaan Taman Nasional Kepulauan Seribu. Tim pelaksana penetapan dan pembentukan Taman Nasional Kepulauan Seribu yang saat itu dipimpin oleh Bapak Mattheus Halim berhasil meyakinkan Pemerintah DKI Jakarta bahwa keberadaan Taman Nasional Kepulauan Seribu justru untuk mendukung pengembangan wisata bahari di Jakarta secara berkelanjutan.\n" +
                "Pada saat itu, Kepulauan Seribu sedang menjadi target pembangunan wisata bahari yang strategis di Jakarta oleh Pemerintah DKI Jakarta. Kepulauan Seribu memang menyajikan beragam sumber daya alam laut yang potensial menjadi destinasi wisata bahari.\n" +
                "\n" +
                "Dalam penetapan Taman Nasional Kepulauan Seribu, tidak semua wilayah Kepulauan Seribu dijadikan Taman Nasional. Satu pertimbangan yang mendasari adalah hasil penelitian oleh tim penetapan dan pembentukan TN Kepulauan Seribu yang juga didukung hasil penelitian LIPI, bahwa kondisi keragaman jenis karang mulai dari perairan terdekat dengan daratan Jakarta menunjukkan kondisi nol keragaman, sementara semakin ke wilayah Utara Kepulauan Seribu, keragaman jenis karang semakin tinggi. Keragaman jenis karang paling tinggi ditemukan di perairan sekitar Pulau Belanda dan Pulau Kayu Angin Bira.\n" +
                "\n" +
                "Memperhatikan adanya indikasi potensi kawasan dan pemanfaatan SDA laut di wilayah Kepulauan Seribu yang tinggi, Pemerintah Pusat melakukan beberapa pengaturan antara lain sebagai berikut :\n" +
                "\n" +
                "· Keputusan Menteri Pertanian Nomor 527/Kpts/Um/7/1982 tanggal 21 Juli 1982, yang menunjukkan wilayah seluas 108.000 hektar Kepulauan Seribu sebagai Cagar Alam dengan nama Cagar Alam Laut Pulau Seribu.\n" +
                "\n" +
                "· Keputusan Menteri Kehutanan Nomor Ab 161/Kpts-II/95, tentang Perubahan Fungsi Cagar Alam Laut Kepulauan Seribu Seluas 108 ha menjadi Taman Nasional Laut Kepulauan Seribu.\n" +
                "\n" +
                "· Keputusan Direktur Taman Nasional dan Hutan Wisata Direktorat Jenderal Perlindungan Hutan dan Pelestarian Alam Departemen Kehutanan Nomor 02/VI/TN-2/SK/1986 tanggal 19 April 1986 tentang Pembagian Zona di Kawasan TNKpS.\n" +
                "\n" +
                "· Keputusan Menteri Kehutanan Nomor 162/Kpts-II/1995 tanggal 21 Maret 1995 tentang Perubahan Fungsi Cagar Alam Laut Kepulauan Seribu yang Terletak di Kotamadya Daerah Tingkat II Jakarta Utara Daerah Khusus Ibukota Jakarta Seluas ± 108.000 (Seratus Delapan Ribu) Hektar Menjadi Taman Nasional Laut Kepulauan Seribu.\n" +
                "\n" +
                "· Keputusan Menteri Kehutanan Dan Pekebunan Nomor 220/Kpts-II/2000 tanggal 2 Agustus 2000 tentang Penunjukan Kawasan Hutan Dan Perairan Di Wilayah Propinsi Daerah Khusus Ibukota Jakarta Seluas 108.475,45 (Seratus Delapan Ribu Empat Ratus Tujuh Puluh Lima, Empat Puluh Lima Perseratus) Hektar.\n" +
                "\n" +
                "· Keputusan Menteri Kehutanan Nomor 6310/Kpts-II/2002 tanggal 13 Juni 2002 tentang Penetapan Kawasan Pelestarian Alam Perairan Taman Nasional Laut Kepulauan Seribu Seluas 107.489 (Seratus Tujuh Ribu Empat Ratus Delapan Puluh Sembilan) Hektar, Yang Terletak Di Kabupaten Administrasi Kepulauan Seribu, Propinsi Daerah Khusus Ibukota Jakarta."
    ),

    TripDayData(
        title = "Denah Lokasi",
        detail = "https://i.imgur.com/85pHYRh.png"
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
                                Uri.parse("https://goo.gl/maps/7XL865oB3cn3aLS49")
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