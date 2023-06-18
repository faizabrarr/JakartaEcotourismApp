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
fun DetailScreen6(navController: NavController, activityResultRegistry: ActivityResultRegistry) {
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { _ ->

    }

    LazyColumn() {
        item {
            DetailHeader6(navController)
            TripInfoContent6(navController)
        }

        itemsIndexed(tripDays6) { _, data ->
            TripDayContent6(data, launcher)
        }

    }
}



@Composable
fun DetailHeader6(navController: NavController) {
    val detailHeaderImageUrl = "https://anekatempatwisata.com/wp-content/uploads/2022/05/Perkampungan-Budaya-Betawi-Setu-Babakan-strategi-720x600.jpg"
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
            TopButton6(
                imageVector = Icons.Default.ArrowBack,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(16.dp)
            ) {
                navController.popBackStack()
            }

            TopButton6(
                imageVector = Icons.Default.Map,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(16.dp)
            ) {
                val uri = Uri.parse("https://goo.gl/maps/HqUa3bqztGjCvYz29")
                val intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(navController.context, intent, null)
            }
        }
    }
}

@Composable
fun TripInfoContent6(navController: NavController) {
    Column(
        modifier = Modifier
            .padding(10.dp)
    ) {
        Row {
            LocationChip4(text = "Srengseng Sawah, Jagakarsa, Jakarta Selatan")
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
            TripDataItem6(
                imageVector = Icons.Default.CalendarToday,
                title = "Waktu Operasional",
                subtitle = "Senin - Minggu\n09:00 - 16:00 WIB",
                modifier = Modifier
            )

            TripDataItem6(
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



data class TripDayData6(val title: String, val detail: String)

var tripDays6 = listOf(
    TripDayData6(
        title = "Setu Babakan",
        detail = "Srengseng Sawah, Kec. Jagakarsa, Kota Jakarta Selatan, Daerah Khusus Ibukota Jakarta."
    ),

    TripDayData6(
        title = "Website Resmi",
        detail = "https://www.setubabakanbetawi.com/"
    ),

    TripDayData6(
        title = "Deskripsi",
        detail = "Ide dan keinginan untuk membangun pusat kebudayaan Betawi sesungguhnya sudah tercetus sejak tahun 90 – an. Kemudian oleh BAMUS BETAWI (Badan Musyawarah Masyarakat Betawi) periode 1996 – 2001 keinginan ini dituangkan dalam sebuah rancangan program kerja yakni ”Membangun Pusat Perkampungan Budaya Betawi”.\n" +
                "\n" +
                "Desakan masyarakat Betawi yang amat kuat, dukungan tokoh-tokoh Betawi terdidik serta organisasi masyarakat ke-Betawian, bersama BAMUS BETAWI sebagai lembaga yang mengkoordinir dan mengayomi seluruh aktivitas organisasi-organisasi dan yayasan-yayasan masyarakat Betawi, akhirnya melahirkan kesepakatan. Tanpa melampaui tugas dan kewenangan Pemda DKI Jakarta pada tahun 1998, BAMUS BETAWI mengajukan proposal tentang “Pembangunan Perkampungan Budaya Betawi” dengan alternatif lokasi Setu Babakan Srengseng Sawah, Kecamatan Jagakarsa Jakarta Selatan. Usaha ini semata-mata karena semua pihak memiliki tanggung jawab moral untuk memotivasi, membina dan membangun serta sekaligus melestarikan budaya Betawi ini.\n"
    ),
)


@Composable
fun TripDayContent6(day: TripDayData6, launcher: ActivityResultLauncher<Intent>) {
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
fun TripDataItem6(imageVector: ImageVector, title: String, subtitle: String, modifier: Modifier) {
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
fun LocationChip6(text: String) {
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
fun TopButton6(imageVector: ImageVector, modifier: Modifier, clickListener: () -> Unit) {
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