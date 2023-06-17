package com.example.jakartaecotourismapp.ui.features

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.OpenInBrowser
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavController
import com.google.accompanist.coil.rememberCoilPainter
import com.google.accompanist.insets.statusBarsPadding

@Composable
fun DetailScreen5(navController: NavController) {
    LazyColumn() {
        item {
            DetailHeader5(navController)
            TripInfoContent5(navController)
        }

        itemsIndexed(tripDays5) { _, data ->
            TripDayContent5(data)
        }
    }
}



@Composable
fun DetailHeader5(navController: NavController) {
    val detailHeaderImageUrl = "https://ragunanzoo.jakarta.go.id/wp-content/uploads/2014/11/slide_31.jpg"
    Box() {
        Image(
            painter = rememberCoilPainter(request = detailHeaderImageUrl),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        )

        Box(
            modifier = Modifier
                .statusBarsPadding()
                .fillMaxWidth()
        ) {
            TopButton5(
                imageVector = Icons.Default.ArrowBack,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(16.dp)
            ) {
                navController.popBackStack()
            }

            TopButton5(
                imageVector = Icons.Default.Map,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(16.dp)
            ) {
                val uri = Uri.parse("https://goo.gl/maps/GBBgABPcUfXWY9tE6")
                val intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(navController.context, intent, null)
            }
        }
    }
}

@Composable
fun TripInfoContent5(navController: NavController) {
    Column(
        modifier = Modifier
            .padding(10.dp)
    ) {
        Row {
            LocationChip5(text = "Jl. Harsono RM No.1, Ragunan, Jakarta Selatan")
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
                text = "4,5",
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
            )

        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Kebun Binatang Ragunan",
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
        )


        Divider(
            color = Color(0xFFECECEE),
            modifier = Modifier.padding(10.dp)
        )

        Row(Modifier.align(Alignment.Start)) {
            TripDataItem5(
                imageVector = Icons.Default.CalendarToday,
                title = "Waktu Operasional",
                subtitle = "Selasa - Minggu\n07:00 - 16:00 WIB",
                modifier = Modifier,
                onClick = {}
            )

            TripDataItem5(
                imageVector = Icons.Default.AttachMoney,
                title = "Biaya masuk",
                subtitle = "Rp. 3000 -\nRp. 15.000",
                modifier = Modifier,
                onClick = {}
            )

            TripDataItem5(
                imageVector = Icons.Default.OpenInBrowser,
                title = "Website Resmi",
                subtitle = "ragunanzoo.go.id",
                modifier = Modifier,
                onClick = {
                    val uri = Uri.parse("https://ragunanzoo.jakarta.go.id/language/en/")
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    startActivity(navController.context, intent, null)
                }
            )
        }

        Divider(
            color = Color(0xFFECECEE),
            modifier = Modifier.padding(8.dp)
        )
    }
}

data class TripDayData5(val title: String, val detail: String)

var tripDays5 = listOf(
    TripDayData5(
        title = "Kebun Binatang Ragunan",
        detail = "Jl. Harsono RM No.1, Ragunan, Ps. Minggu, Kota Jakarta Selatan, Daerah Khusus Ibukota Jakarta ."
    ),
    TripDayData5(
        title = "Deskripsi",
        detail = "Selamat datang di Kebun Binatang Ragunan, taman seluas 147 hektar dan rumah bagi 2.000 spesimen dan ditumbuhi lebih dari 50.000 pohon yang menjadikan lingkungannya sejuk dan nyaman.\n" +
                "\n" +
                "Kebun Binatang Ragunan yang terletak di Pasar Minggu, sekitar 20 Km dari pusat kota Jakarta, adalah yang termurah dan paling mudah diakses di kota ini. Satwa dan tamannya yang dikelilingi pepohonan besar menjadi daya tarik tersendiri bagi keluarga untuk piknik, terutama di akhir pekan.\n" +
                "\n" +
                "Kita akan menemukan tempat untuk berjalan-jalan dengan pemandangan alam yang sangat damai, dan dikelilingi oleh suara burung dan owa.\n" +
                "\n" +
                "Kebun Binatang Pertama didirikan pada tanggal 19 September 1864 berlokasi di Jakarta Pusat, kemudian pindah ke Jakarta Selatan pada tahun 1966.\n" +
                "\n" +
                "Total luas saat ini lebih dari 140 hektar, awalnya direncanakan sekitar 200 hektar, guna mendapatkan lahan untuk pembangunan. Pemerintah menyadari bahwa Jakarta membutuhkan daerah resapan air dan itu sama pentingnya dengan menyediakan tempat bagi penduduk untuk menikmati alam.\n"
    ),
)

@Composable
fun TripDayContent5(day: TripDayData5) {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            text = day.title,
            fontSize = 14.sp,
            fontWeight = FontWeight.ExtraBold,
            letterSpacing = 0.75.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = day.detail,
            fontSize = 14.sp,
            fontWeight = FontWeight.Light,
            lineHeight = 18.sp
        )
    }
}

@Composable
fun TripDataItem5(imageVector: ImageVector, title: String, subtitle: String, modifier: Modifier, onClick: () -> Unit) {
    Row(
        modifier = modifier.clickable { onClick() }
    ) {
        Icon(
            modifier = Modifier
                .padding(8.dp)
                .clip(CircleShape)
                .background(Color(0xFFF5F6FF))
                .size(32.dp)
                .padding(8.dp),
            imageVector = imageVector,
            contentDescription = ""
        )

        Column {
            Text(
                text = title,
                fontSize = 12.sp,
                fontWeight = FontWeight.ExtraBold
            )

            Text(
                text = subtitle,
                fontSize = 11.sp,
                fontWeight = FontWeight.Normal
            )
        }
    }
}

@Composable
fun LocationChip5(text: String) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(6.dp))
            .background(Color(0xFFFBF110))
            .padding(horizontal = 4.dp, vertical = 1.dp)
    ) {
        Icon(
            imageVector = Icons.Default.LocationOn,
            contentDescription = "",
            modifier = Modifier
                .padding(end = 4.dp)
                .size(12.dp)
                .align(Alignment.CenterVertically)
        )

        Text(
            text = text,
            fontWeight = FontWeight.SemiBold,
            fontSize = 13.sp,
            color = Color.Black,
        )
    }
}

@Composable
fun TopButton5(imageVector: ImageVector, modifier: Modifier, clickListener: () -> Unit) {
    Button(
        onClick = { clickListener() },
        border = BorderStroke(2.dp, Color(0xFFEAFBFF)),
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(0xDDF6F9FF),
            contentColor = Color(0xFF3562D7)
        ),
        modifier = modifier.size(48.dp)
    ) {
        Icon(imageVector = imageVector, contentDescription = "")
    }
}