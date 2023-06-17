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
fun DetailScreen8(navController: NavController) {
    LazyColumn() {
        item {
            DetailHeader8(navController)
            TripInfoContent8(navController)
        }

        itemsIndexed(tripDays8) { _, data ->
            TripDayContent8(data)
        }
    }
}



@Composable
fun DetailHeader8(navController: NavController) {
    val detailHeaderImageUrl = "https://tnlkepulauanseribu.menlhk.go.id/wp-content/uploads/2022/10/Tampak-Atas-Sarpras-Wisata-Alam-SPTN-III.jpeg"
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
            TopButton8(
                imageVector = Icons.Default.ArrowBack,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(16.dp)
            ) {
                navController.popBackStack()
            }

            TopButton8(
                imageVector = Icons.Default.Map,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(16.dp)
            ) {
                val uri = Uri.parse("https://goo.gl/maps/7XL865oB3cn3aLS49")
                val intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(navController.context, intent, null)
            }
        }
    }
}

@Composable
fun TripInfoContent8(navController: NavController) {
    Column(
        modifier = Modifier
            .padding(10.dp)
    ) {
        Row {
            LocationChip8(text = "Pulau Pramuka, Kep. Seribu Utara, Kep. Seribu")
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
            TripDataItem8(
                imageVector = Icons.Default.CalendarToday,
                title = "Waktu Operasional",
                subtitle = "Buka 24jam",
                modifier = Modifier,
                onClick = {}
            )

            TripDataItem8(
                imageVector = Icons.Default.AttachMoney,
                title = "Biaya masuk",
                subtitle = "Rp. 5000",
                modifier = Modifier,
                onClick = {}
            )

            TripDataItem8(
                imageVector = Icons.Default.OpenInBrowser,
                title = "Website Resmi",
                subtitle = "tnlkepulauanseribu.go.id",
                modifier = Modifier,
                onClick = {
                    val uri = Uri.parse("https://tnlkepulauanseribu.menlhk.go.id/")
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

data class TripDayData8(val title: String, val detail: String)

var tripDays8 = listOf(
    TripDayData8(
        title = "Taman Nasional Kepulauan Seribu",
        detail = "Pulau Pramuka, Pulau Panggang, Kepulauan Seribu Utara, Kab. Administrasi Kepulauan Seribu, Daerah Khusus Ibukota Jakarta."
    ),

    TripDayData8(
        title = "Deskripsi",
        detail = "Keberadaan Taman Nasional Kepulauan Seribu diawali dengan berbagai cerita yang menarik. Bermula dari tahun 1979, didukung oleh FAO (Food and Agriculture Organization) PBB, dilakukan kajian dan survey di Indonesia untuk menemukan satu lokasi perairan laut yang cocok dijadikan sebagai Taman Nasional laut (marine national park) pertama di Indonesia. Saat itu FAO sedang menjalankan proyek membantu under-developed country (negara belum berkembang) dalam upaya konservasi untuk pembangunan yang berkelanjutan."
    ),
)

@Composable
fun TripDayContent8(day: TripDayData8) {
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
fun TripDataItem8(imageVector: ImageVector, title: String, subtitle: String, modifier: Modifier, onClick: () -> Unit) {
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
fun LocationChip8(text: String) {
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
fun TopButton8(imageVector: ImageVector, modifier: Modifier, clickListener: () -> Unit) {
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