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
fun DetailScreen2(navController: NavController, activityResultRegistry: ActivityResultRegistry) {
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { _ ->

    }

    LazyColumn() {
        item {
            DetailHeader2(navController)
            TripInfoContent2(navController)
        }

        itemsIndexed(tripDays2) { _, data ->
            TripDayContent2(data, launcher)
        }

    }
}


@Composable
fun DetailHeader2(navController: NavController) {
    val detailHeaderImageUrl = "https://www.ancol.com/blog/wp-content/uploads/2022/04/wisata-alam-di-jakarta.jpg"
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
            TopButton2(
                imageVector = Icons.Default.ArrowBack,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(16.dp)
            ) {
                navController.popBackStack()
            }

            TopButton2(
                imageVector = Icons.Default.Map,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(16.dp)
            ) {
                val uri = Uri.parse("https://goo.gl/maps/G26BkjffvJiXMNjdA")
                val intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(navController.context, intent, null)
            }
        }
    }
}

@Composable
fun TripInfoContent2(navController: NavController) {
    Column(
        modifier = Modifier
            .padding(10.dp)
    ) {
        Row {
            LocationChip2(text = "Jl. Lodan Timur No.7, Ancol, Jakarta Utara")
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
            TripDataItem2(
                imageVector = Icons.Default.CalendarToday,
                title = "Waktu Operasional",
                subtitle = "Senin - Minggu\n06:00 - 18:00 WIB",
                modifier = Modifier
            )

            TripDataItem2(
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



data class TripDayData2(val title: String, val detail: String)

var tripDays2 = listOf(
    TripDayData2(
        title = "Allianz Ecopark",
        detail = "Jl. Lodan Timur No.7, RW.10, Ancol, Kec. Pademangan, Jkt Utara, Daerah Khusus Ibukota Jakarta ."
    ),

    TripDayData2(
        title = "Website Resmi",
        detail = "https://www.ancol.com/unit-rekreasi/ecopark-ancol--7"
    ),

    TripDayData2(
        title = "Deskripsi",
        detail = "Ecopark Ancol memiliki luas lahan hampir 34 hektar merupakan kawasan hasil dari mengalihfungsikan Padang Golf Ancol menjadi sebuah sarana rekreasi terbaru yang menawarkan nilai-nilai edukasi (edutainment) dan petualangan (adventure) dengan pendekatan green lifestyle, menjadi ruang terbuka bagi pengunjung Ancol Taman Impian untuk mengeksplorasi pengetahuan botani dan rekreasi luar ruang.\n" +
                "\n" +
                "Ecopark Ancol terbagi menjadi beberapa kawasan (zona) dengan fungsi dan fasilitas berbeda.\n" +
                "\n" +
                "Selain Eco Care, Eco Nature, dan Eco Art, Ecopark Ancol juga akan menghadirkan zona Eco Energy.\n" +
                "\n" +
                "Pada keseluruhan zona ini, beragam jenis tanaman pesisir sesuai dengan kegunaannya bagi lingkungan hidup telah ditanam, sekaligus untuk menjadikan Ecopark sebagai sebuah kawasan yang hijau dan teduh, serta tempat pembelajaran botani lengkap yang menyenangkan.\n"
    ),
)

@Composable
fun TripDayContent2(day: TripDayData2, launcher: ActivityResultLauncher<Intent>) {
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
fun TripDataItem2(imageVector: ImageVector, title: String, subtitle: String, modifier: Modifier) {
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
fun LocationChip2(text: String) {
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
fun TopButton2(imageVector: ImageVector, modifier: Modifier, clickListener: () -> Unit) {
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