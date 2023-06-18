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
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.ActivityResultRegistry
import androidx.activity.result.contract.ActivityResultContracts

@Composable
fun DetailScreen10(navController: NavController, activityResultRegistry: ActivityResultRegistry) {
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { _ ->

    }

    LazyColumn() {
        item {
            DetailHeader10(navController)
            TripInfoContent10(navController)
        }

        itemsIndexed(tripDays10) { _, data ->
            TripDayContent10(data, launcher)
        }

    }
}



@Composable
fun DetailHeader10(navController: NavController) {
    val detailHeaderImageUrl = "https://tebetecopark.id/img/banner/banner-home.jpg"
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
            TopButton10(
                imageVector = Icons.Default.ArrowBack,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(16.dp)
            ) {
                navController.popBackStack()
            }

            TopButton10(
                imageVector = Icons.Default.Map,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(16.dp)
            ) {
                val uri = Uri.parse("https://goo.gl/maps/i74yxNMWHu9XW7wdA")
                val intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(navController.context, intent, null)
            }
        }
    }
}

@Composable
fun TripInfoContent10(navController: NavController) {
    Column(
        modifier = Modifier
            .padding(10.dp)
    ) {
        Row {
            LocationChip10(text = "Jl. Tebet Barat Raya, Tebet, Jakarta Selatan")
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
            TripDataItem10(
                imageVector = Icons.Default.CalendarToday,
                title = "Waktu Operasional",
                subtitle = "Senin - Minggu\n08:00 - 17:00 WIB",
                modifier = Modifier
            )

            TripDataItem10(
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



data class TripDayData10(val title: String, val detail: String)

var tripDays10 = listOf(
    TripDayData10(
        title = "Tebet Eco Park",
        detail = "Jl. Tebet Barat Raya, RT.1/RW.10, Tebet Bar., Kec. Tebet, Kota Jakarta Selatan, Daerah Khusus Ibukota Jakarta."
    ),

    TripDayData10(
        title = "Website Resmi",
        detail = "https://tebetecopark.id/"
    ),

    TripDayData10(
        title = "Deskripsi",
        detail = "Tebet Eco Park (TEP) merupakan taman kota yang didedikasikan untuk masyarakat dan lingkungan. Terletak di Jakarta Selatan dengan area seluas 7,3 hektare, TEP kini hadir sebagai ruang terbuka hijau yang telah direvitalisasi. Dua kawasan taman yang awalnya terpisah dan berseberangan – Taman Tebet Utara dan Taman Tebet Selatan, kini telah menjadi satu taman terpadu yang mengusung konsep harmonisasi antara fungsi ekologi, sosial, edukasi dan rekreasi.\n" +
                "\n" +
                "Setiap zona TEP dirancang untuk mengambil peran penting dalam keberlangsungan lingkungan dan interaksi sosial, mulai dari menjaga kualitas alamiah lingkungan hingga meningkatkan kualitas hidup pengunjung dan masyarakat sekitarnya. Sungai yang di renaturalisasi, rawa (wetland) yang menjadi kolam retensi, konservasi tanaman dan penanaman kembali untuk mereduksi polusi, sampai berbagai ruang hijau terbuka yang berfungsi memfasilitasi masyarakat untuk berinteraksi.\n" +
                "\n" +
                "Lebih dari sebuah taman, Tebet Eco Park adalah ekosistem dimana alam dan manusia saling berinteraksi dan saling melindungi dalam sebuah harmoni.\n."
    ),
)

@Composable
fun TripDayContent10(day: TripDayData10, launcher: ActivityResultLauncher<Intent>) {
    val uri = Uri.parse(day.detail)
    val intent = Intent(Intent.ACTION_VIEW, uri)

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

        if (day.detail.startsWith("http://") || day.detail.startsWith("https://")) {
            Text(
                text = day.detail,
                fontSize = 14.sp,
                fontWeight = FontWeight.Light,
                lineHeight = 18.sp,
                modifier = Modifier.clickable { launcher.launch(intent) }
            )
        } else {
            Text(
                text = day.detail,
                fontSize = 14.sp,
                fontWeight = FontWeight.Light,
                lineHeight = 18.sp,
            )
        }
    }
}



@Composable
fun TripDataItem10(imageVector: ImageVector, title: String, subtitle: String, modifier: Modifier) {
    Row() {
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
fun LocationChip10(text: String) {
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
fun TopButton10(imageVector: ImageVector, modifier: Modifier, clickListener: () -> Unit) {
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