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
fun DetailScreen4(navController: NavController) {
    LazyColumn() {
        item {
            DetailHeader4(navController)
            TripInfoContent4(navController)
        }

        itemsIndexed(tripDays4) { _, data ->
            TripDayContent4(data)
        }
    }
}



@Composable
fun DetailHeader4(navController: NavController) {
    val detailHeaderImageUrl = "https://www.ancol.com/blog/wp-content/uploads/2022/03/wisata-pantai-di-jakarta.jpg"
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
            TopButton4(
                imageVector = Icons.Default.ArrowBack,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(16.dp)
            ) {
                navController.popBackStack()
            }

            TopButton4(
                imageVector = Icons.Default.Map,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(16.dp)
            ) {
                val uri = Uri.parse("https://goo.gl/maps/aCp5Apx77NnxGh2i9")
                val intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(navController.context, intent, null)
            }
        }
    }
}

@Composable
fun TripInfoContent4(navController: NavController) {
    Column(
        modifier = Modifier
            .padding(10.dp)
    ) {
        Row {
            LocationChip4(text = "Jl. Kw. Wisata Ancol, Ancol,Jakarta Utara")
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
            text = "Pantai Ancol",
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
        )


        Divider(
            color = Color(0xFFECECEE),
            modifier = Modifier.padding(10.dp)
        )

        Row(Modifier.align(Alignment.Start)) {
            TripDataItem4(
                imageVector = Icons.Default.CalendarToday,
                title = "Waktu Operasional",
                subtitle = "Senin - Minggu\n06:00 - 24:00 WIB",
                modifier = Modifier,
                onClick = {}
            )

            TripDataItem4(
                imageVector = Icons.Default.AttachMoney,
                title = "Biaya masuk",
                subtitle = "Rp. 25.000\n(Biaya masuk\nkawasan ancol)",
                modifier = Modifier,
                onClick = {}
            )

            TripDataItem4(
                imageVector = Icons.Default.OpenInBrowser,
                title = "Website Resmi",
                subtitle = "Ancol.com",
                modifier = Modifier,
                onClick = {
                    val uri = Uri.parse("https://www.ancol.com/unit-rekreasi/ancol--1")
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

data class TripDayData4(val title: String, val detail: String)

var tripDays4 = listOf(
    TripDayData4(
        title = "Pantai Ancol",
        detail = "Jl. Kw. Wisata Ancol, Ancol, Kec. Pademangan, Jkt Utara, Daerah Khusus Ibukota Jakarta ."
    ),
    TripDayData4(
        title = "Deskripsi",
        detail = "Ancol Taman Impian mempunyai pantai yang indah yang merupakan destinasi wisata pantai dalam kota Jakarta. Pengunjung kawasan Ancol dapat menikmati suasana segar di area Pantai Lagoon, Festival, Indah, Beach Pool dan Carnaval, serta Danau Impian, sepanjang kurang lebih 6,5 km. Wisata pantai ini semakin sempurna dengan adanya promenade hampir di sepanjang pantai serta fasilitas kuliner yang lengkap, antara lain: Jimbaran Resto, Le Bridge, Starbucks, Segarra, Talaga Sampireun dan lain-lain. Hal tersebut menjadikan wisata pantai Ancol ini lebih menarik untuk dikunjungi.\n" +
                "\n" +
                "Rekreasi di Ancol Taman Impian semakin menyenangkan dengan kehadiran Kereta Wisata Sato-Sato. Transportasi massal ini selain bisa mengantarkan wisatawan ke berbagai destinasi seperti Dunia Fantasi, Ocean Dream Samudra, Allianz Ecopark, dan yang lainnya, juga bisa menjadi wahana rekreasi sambil menikmati pemandangan sepanjang Ancol.\n" +
                "\n" +
                "Jalur keretanya membentang sepanjang 3,6 kilometer, dari Gerbang Timur hingga Pantai Bende, dan dilanjut lagi hingga Ocean Dream Samudra, lalu ke Dunia Fantasi. Kereta Sato-sato akan beroperasi mulai pukul 08.00-18.00 WIB. Ada tiga rangkaian kereta yang bisa mengangkut penumpang hingga  400 orang.\n" +
                "\n" +
                "Wahana kereta tersebut juga ramah bagi difable atau orang berkebutuhan khusus. Di salah satu gerbongnya dapat dikhususkan bagi warga difable. Semua wisatawan tetap bisa menikmati wahana ini tanpa bayar.\n"
    ),
)

@Composable
fun TripDayContent4(day: TripDayData4) {
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
fun TripDataItem4(imageVector: ImageVector, title: String, subtitle: String, modifier: Modifier, onClick: () -> Unit) {
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
fun LocationChip4(text: String) {
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
fun TopButton4(imageVector: ImageVector, modifier: Modifier, clickListener: () -> Unit) {
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