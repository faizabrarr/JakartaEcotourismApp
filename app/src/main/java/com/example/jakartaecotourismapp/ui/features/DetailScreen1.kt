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
        R.drawable.a1
    ),
    ImageData(
        R.drawable.a2
    ),
    ImageData(
        R.drawable.a3
    ),
    ImageData(
        R.drawable.a4
    ),
    ImageData(
        R.drawable.a5
    ),
    ImageData(
        R.drawable.a6
    ),
)

@Composable
fun DetailScreen1(navController: NavController, activityResultRegistry: ActivityResultRegistry) {
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
            val uri = Uri.parse("https://goo.gl/maps/dE1xfJmgkZyNB4qD6")
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
            LocationChip(text = "Jl. Garden House, Kamal Muara, Jakarta Utara")
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
                text = "4,3",
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Taman Wisata Alam Mangrove Angke Kapuk",
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
                subtitle = "Senin - Jum'at\n08:00 - 18:00 WIB",
                modifier = Modifier
            )

            TripDataItem(
                imageVector = Icons.Default.AttachMoney,
                title = "Biaya masuk",
                subtitle = "Rp. 15.000 s/d\nRp. 30.000",
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
        title = "TWA Mangrove Angke Kapuk",
        detail = "Jl. Garden House, Kamal Muara, Kec. Penjaringan, Jkt Utara, Daerah Khusus Ibukota Jakarta."
    ),

    TripDayData(
        title = "Website Resmi",
        detail = "https://www.jakartamangrove.id/"
    ),

    TripDayData(
        title = "Deskripsi",
        detail = "TWA Angke Kapuk merupakan bagian dari kawasan hutan Angke Kapuk yang ditetapkan berdasarkan Surat Keputusan Gubernur Jenderal Hindia Belanda Nomor 24 tanggal 1 Juni 1939 dengan luasan 99,82 Ha. Tipe ekosistem yang menjadi habitat berbagai jenis burung air ini adalah ekosistem mangrove. Ijin Pengusahaan Pariwisata Alam TWA Angke Kapuk diberikan kepada PT. MURINDRA KARYA LESTARI sejak 1997 dengan tujuan mengembangkan TWA Angke Kapuk sebagai sarana pariwisata alam sekaligus mempertahankan kelestarian fungsi mangrove sebagai sistem penyangga kehidupan.\nJenis-jenis fauna yang mendominasi kawasan TWA Angke Kapuk, umumnya adalah jenis-jenis burung merandai dan hampir seluruhnya merupakan satwa yang dilindungi. Beberapa jenis diantaranya adalah:\n" +
                "\n" +
                "· Belekok / Javan Pond Heron (Ardeola speciosa)\n" +
                "· Belibis / Wandering Whistling Duck (Dendrocygna arcuate)\n" +
                "· Cangak Abu / Grey Heron (Ardea cinerea)\n" +
                "· Cekakak Sungai / Collared Kingfisher (Todirhamphus chloris)\n" +
                "· Elang Laut Perut Putih / White-Bellied Sea Eagle (Haliaeetus leucogaster)\n" +
                "· Elang Tiram / Osprey (Pandion haliaetus)\n" +
                "· Gagang Bayam Timur / Pied Stilt (Himantopus leucocephalus)\n" +
                "· Itik Benjut / Sunda Teal (Anas Gibberifrons)\n" +
                "· Kokokan Laut / Little Heron (Butorides striatus)\n" +
                "· Kowak Malam Abu / Black-Crowned Night Heron (Nycticorax nycticorax)\n" +
                "· Kuntul Kerbau / Cattle Egret (Bulbucus ibis)\n" +
                "· Kuntul Putih / Little Egret (Egretta sp.)\n" +
                "· Pecuk Ular Asia / Oriental Darter (Anhinga melanogaster)\n" +
                "· Raja Udang Biru / Small Blue Kingisher (Alcedo coerulescens)\n" +
                "· Tangkar Centrong alias Murai Irian / Racket-tailed Treepie (Crypsirina temia)\n" +
                "\n" +
                "Selain itu terdapat pula bererapa jenis satwa lainnya diantaranya Biawak Air (Varanus Salvator), ikan Gelodok/Gelosoh (Glossogobius Giuris) dan Udang Bakau (Thalassina Anomala). \n" +
                "\n" +
                "Jenis-jenis mangrove yang terdapat dalam kawasan diantaranya :\n" +
                "\n" +
                "· Api-api (Avicennia marina)\n" +
                "· Bakau (Rhizophora mucronata dan Rhizophora stylosa)\n" +
                "· Bidara (Sonneratia caseolaris)\n" +
                "· Buta-buta (Exocecaris agallocha)\n" +
                "· Cantinggi (Ceriops sp.)\n" +
                "· Warakas (Acrosticum areum)\n" +
                "\n" +
    "\nDan jenis hutan pantai/rawa, antara lain:\n" +
            "\n" +
            "· Bluntas (Pluchea indica)\n" +
            "· Dadap (Erythrina variagate)\n" +
            "· Duri Busyetan (Mimosa sp.)\n" +
            "· Flamboyan (Delonix regia)\n" +
            "· Kedondong Laut (Polysia frutucosa)\n" +
            "· Ki Hujan (Samanea saman)\n" +
            "· Ki Tower (Deris heterophyla)\n" +
            "· Mendongan (Scripus litoralis)\n" +
            "· Waru Laut (Hibiscus tilliaceus)\n" +
            "\n"
    ),
)
