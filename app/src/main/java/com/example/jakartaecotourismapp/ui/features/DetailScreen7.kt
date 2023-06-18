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
fun DetailScreen7(navController: NavController, activityResultRegistry: ActivityResultRegistry) {
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { _ ->

    }

    LazyColumn() {
        item {
            DetailHeader7(navController)
            TripInfoContent7(navController)
        }

        itemsIndexed(tripDays7) { _, data ->
            TripDayContent7(data, launcher)
        }

    }
}



@Composable
fun DetailHeader7(navController: NavController) {
    val detailHeaderImageUrl = "https://www.nativeindonesia.com/foto/taman-cattleya/Taman-Sejuk-Di-Tengah-Kota.jpg"
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
            TopButton7(
                imageVector = Icons.Default.ArrowBack,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(16.dp)
            ) {
                navController.popBackStack()
            }

            TopButton7(
                imageVector = Icons.Default.Map,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(16.dp)
            ) {
                val uri = Uri.parse("https://goo.gl/maps/z8y6s9bqU4yo9jAXA")
                val intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(navController.context, intent, null)
            }
        }
    }
}

@Composable
fun TripInfoContent7(navController: NavController) {
    Column(
        modifier = Modifier
            .padding(10.dp)
    ) {
        Row {
            LocationChip7(text = "Jl. Letjen S. Parman, Kemanggisan, Jakarta Barat")
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
            text = "Taman Cattleya",
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
        )

        Divider(
            color = Color(0xFFECECEE),
            modifier = Modifier.padding(10.dp)
        )

        Row(Modifier.align(Alignment.Start)) {
            TripDataItem7(
                imageVector = Icons.Default.CalendarToday,
                title = "Waktu Operasional",
                subtitle = "Senin - Minggu\n07:00 - 22:00 WIB",
                modifier = Modifier
            )

            TripDataItem7(
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



data class TripDayData7(val title: String, val detail: String)

var tripDays7 = listOf(
    TripDayData7(
        title = "Taman Cattleya",
        detail = "Jl. Letjen S. Parman, RT.15/RW.1, Kemanggisan, Kec. Palmerah, Kota Jakarta Barat, Daerah Khusus Ibukota Jakarta."
    ),
    TripDayData7(
        title = "Deskripsi",
        detail = "Taman Cattleya adalah satu dari sekian banyak Ruang Terbuka Hijau (RTH) yang dimiliki oleh Pemrov DKI Jakarta, dan menjadi salah-satu Taman Hutan Kota yang dibuka kembali di masa pandemi.\n" +
                "\n" +
                "Kehadiran Taman Cattleya tentu saja sangat bermanfaat bagi masyarakat Ibu Kota, ditengah situasi penatnya kemacetan, tumpukkan pekerjaan, serta polusi yang menjadi ancaman bagi kota-kota besar.\n"
    ),
)


@Composable
fun TripDayContent7(day: TripDayData7, launcher: ActivityResultLauncher<Intent>) {
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
fun TripDataItem7(imageVector: ImageVector, title: String, subtitle: String, modifier: Modifier) {
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
fun LocationChip7(text: String) {
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
fun TopButton7(imageVector: ImageVector, modifier: Modifier, clickListener: () -> Unit) {
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