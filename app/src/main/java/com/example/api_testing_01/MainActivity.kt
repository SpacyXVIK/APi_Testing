package com.example.api_testing_01

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.api_testing_01.model.postproductsItem
import com.example.api_testing_01.ui.theme.APi_Testing_01Theme

class MainActivity : ComponentActivity() {

    private val apiService: ApiService by lazy { RetrofitInstance.getApiService() }

    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            APi_Testing_01Theme {

                var Quotes by remember { mutableStateOf<List<postproductsItem>>(emptyList()) }

                LaunchedEffect(Unit) {
                    Quotes = apiService.getProducts()
                }



                Scaffold(modifier = Modifier.fillMaxSize()) {innerPadding->
                    LazyColumn(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                            .background(Color.Black)
                    ) {
                        items(Quotes) {
                            Card(
                                //Color = contentColorFor(backgroundColor = Color.LightGray),
                                modifier = Modifier
                                    .padding(horizontal = 12.dp, vertical = 8.dp)
                                    .fillMaxWidth(),
                                colors = CardDefaults.cardColors(Color.White)
                            ) {
                                AsyncImage(
                                    model = it.image,
                                    contentDescription = null,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .aspectRatio(1280f / 840f)
                                )

                                Spacer(modifier= Modifier.height(16.dp))

                                Text(it.title.toString(),
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Blue,
                                    modifier = Modifier
                                        .padding(start = 10.dp, end = 10.dp))
//                                Text("id="+it.id.toString(),
//                                    modifier = Modifier
//                                    .padding(start = 10.dp, end = 10.dp))
                                Text("Category: "+it.category.toString(),
                                    fontWeight = FontWeight.SemiBold,
                                    color = Color.Magenta,
                                    modifier = Modifier
                                        .padding(start = 10.dp, end = 10.dp)
                                )

                                Box(modifier = Modifier
                                    .padding(10.dp)
                                    .border(width = 1.dp,color = Color.Black, shape = RoundedCornerShape(10.dp))
                                    .fillMaxWidth()
                                ) {
                                Text(it.description.toString(),
                                    modifier = Modifier
                                        .padding(start = 10.dp, end = 10.dp))
                                }
                                Text(it.rating.toString(),
                                    fontSize = 17.sp,
                                    color = Color.Cyan,
                                    modifier = Modifier
                                        .padding(start = 10.dp, end = 10.dp)
                                    )
                                Text("$"+it.price.toString(),
                                    fontSize = 25.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Red,
                                    modifier = Modifier
                                        .padding(start = 10.dp, end = 10.dp))

                                Spacer(modifier = Modifier.height(15.dp))

                                Row(modifier = Modifier
                                        .fillMaxWidth()
                                        //.clickable(onClick = )
                                        //.background(Color.LightGray)
                                ) {

                                    Spacer(modifier = Modifier.padding(horizontal = 30.dp))

                                    Box(
                                        contentAlignment = Alignment.Center,
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(20.dp))
                                            .border(2.dp,Color.Black, shape = RoundedCornerShape(20.dp))
                                            .background(Color.Yellow)
                                            //.padding(start = 20.dp)
                                            .padding(10.dp)
                                            .size(width = 75.dp, height = 25.dp)
                                        //.fillMaxWidth()
                                            //.padding(bottom = 10.dp)
                                    ) {
                                        Text(
                                            "Buy Now",
                                            color = Color.Black,
                                            fontWeight = FontWeight.SemiBold,
                                        )

                                    }

                                    Spacer(modifier = Modifier.padding(horizontal = 20.dp))

                                    Box(
                                        contentAlignment = Alignment.Center,
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(20.dp))
                                            .border(2.dp,Color.Black, shape = RoundedCornerShape(20.dp))
                                            .background(Color.Yellow)
                                            //.padding(start = 20.dp)
                                            .padding(10.dp)
                                            .size(width = 100.dp, height = 25.dp)
                                        //.fillMaxWidth()
                                        //.padding(bottom = 10.dp)
                                    ) {
                                        Text(
                                            "Add to cart",
                                            color = Color.Black,
                                            fontWeight = FontWeight.SemiBold,
                                        )

                                    }
                                }

                                Spacer(modifier = Modifier.padding(bottom = 10.dp))

                            }

                        }

                    }
                }
            }
        }
    }
}



