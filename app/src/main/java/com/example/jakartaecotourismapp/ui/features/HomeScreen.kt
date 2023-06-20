package com.example.jakartaecotourismapp.ui.features

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.accompanist.coil.rememberCoilPainter
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding

@Composable
fun HomeScreen(navController: NavController) {
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        item {
            HomeHeader()
        }
        item {
            Text(
                text = "DESTINASI EKOWISATA DI JAKARTA",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                modifier = Modifier.padding(
                    top = 20.dp,
                    start = 16.dp,
                    bottom = 16.dp
                ),
            )
        }

        itemsIndexed(tripListing) { position, data ->
            HomeTripItem(homeTripModel = data, navController = navController, tripId = position)
        }

        item {
            Spacer(modifier = Modifier.navigationBarsPadding())
        }

    }

}


@Composable
@Preview
fun HomeHeader() {

    val homeHeaderBg = "https://www.4freephotos.com/medium/2015/Blue-blurry-background-5731.jpg"

    Box {

        Image(
            painter = rememberCoilPainter(request = homeHeaderBg, fadeIn = true),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(150.dp)
                .alpha(0.2f)
                .fillMaxWidth()
        )


        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .statusBarsPadding()
                .padding(16.dp)
        ) {

            Text(
                text = "Hallo, Jakartans!",
                fontWeight = FontWeight.Bold,
                fontSize = 38.sp,
                letterSpacing = (-1).sp
            )

            Text(
                text = "Mau kemana anda hari ini ?",
                fontWeight = FontWeight.Light,
                fontSize = 18.sp,
                lineHeight = 24.sp,
                letterSpacing = ((-0.2).sp)
            )

            Spacer(modifier = Modifier.height(24.dp))

        }


    }

}

data class HomeTripModel(
    val id: Int,
    val image: String,
    val address: String,
    val title: String,
    val rating: Float
)

@Composable
fun HomeTripItem(homeTripModel: HomeTripModel,navController: NavController, tripId: Int) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {

        Image(
            painter = rememberCoilPainter(request = homeTripModel.image, fadeIn = true),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .clickable {
                    navController.navigate("detail${homeTripModel.id}")
                }
                .height(200.dp)
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row() {

            Text(
                text = homeTripModel.address,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp,
            )

            Spacer(modifier = Modifier.weight(1f))

            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = "",
                tint = Color(255, 215, 0),
                modifier = Modifier
                    .padding(4.dp)
                    .size(12.dp)
                    .align(Alignment.CenterVertically)
            )

            Text(
                text = homeTripModel.rating.toString(),
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp
            )
        }

        Text(
            text = homeTripModel.title,
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
            lineHeight = 24.sp
        )

    }


}

val tripListing = listOf<HomeTripModel>(
    HomeTripModel(
        1,
        "https://www.jakartamangrove.id/assets/images/img-0307-copy-2000x1333.jpg",
        "Jl. Garden House, Kamal Muara, Jakarta Utara",
        "Taman Wisata Alam Mangrove Angke Kapuk",
        4.3f
    ),

    HomeTripModel(
        2,
        "https://www.ancol.com/blog/wp-content/uploads/2022/04/wisata-alam-di-jakarta.jpg",
        "Jl. Lodan Timur No.7, Ancol, Jakarta Utara",
        "Allianz Ecopark",
        4.6f
    ),

    HomeTripModel(
        3,
        "https://getlost.id/wp-content/uploads/2021/06/@adriantjahjono-696x463.jpg",
        "Jl. H. Kelik, Srengseng, Jakarta Barat",
        "Hutan Kotan Srengseng",
        4.4f
    ),


    HomeTripModel(
        4,
        "https://www.ancol.com/blog/wp-content/uploads/2022/03/wisata-pantai-di-jakarta.jpg",
        "Jl. Kw. Wisata Ancol, Ancol, Jakarta Utara",
        "Pantai Ancol",
        4.4f
    ),

    HomeTripModel(
        5,
        "https://ragunanzoo.jakarta.go.id/wp-content/uploads/2014/11/slide_31.jpg",
        "Jl. Harsono RM No.1, Ragunan, Ps. Minggu, Jakarta Selatan",
        "Kebun Binatang Ragunan",
        4.5f
    ),
    HomeTripModel(
        6,
        "https://anekatempatwisata.com/wp-content/uploads/2022/05/Perkampungan-Budaya-Betawi-Setu-Babakan-strategi-720x600.jpg",
        "Srengseng Sawah, Jagakarsa, Jakarta Selatan",
        "Setu Babakan",
        4.4f
    ),

    HomeTripModel(
        7,
        "https://www.nativeindonesia.com/foto/taman-cattleya/Taman-Sejuk-Di-Tengah-Kota.jpg",
        "Jl. Letjen S. Parman, Kemanggisan, Jakarta Barat",
        "Taman Cattleya",
        4.5f
    ),
    HomeTripModel(
        8,
        "https://tnlkepulauanseribu.menlhk.go.id/wp-content/uploads/2022/10/Tampak-Atas-Sarpras-Wisata-Alam-SPTN-III.jpeg",
        "Pulau Pramuka, Kepulauan Seribu Utara, Kepulauan Seribu",
        "Taman Nasional Kepulauan Seribu",
        4.6f
    ),

    HomeTripModel(
        9,
        "https://ksmtour.com/media/images/articles11/taman-suropati-jakarta.jpg",
        "Jl. Taman Suropati No.5, Menteng, Jakarta Pusat",
        "Taman Suropati",
        4.6f
    ),

    HomeTripModel(
        10,
        "https://tebetecopark.id/img/banner/banner-home.jpg",
        "Jl. Tebet Barat Raya, Tebet, Jakarta Selatan",
        "Tebet Eco Park",
        4.6f
    ),
)