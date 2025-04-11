package com.example.api_testing_01

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
                            ) {
                                Text(it.title.toString(),
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Blue,
                                    modifier = Modifier
                                        .padding(start = 10.dp, end = 10.dp))
                                Text(it.id.toString(),
                                    modifier = Modifier
                                    .padding(start = 10.dp, end = 10.dp))
                                Text(it.category.toString(),
                                    fontWeight = FontWeight.SemiBold,
                                    color = Color.Magenta,
                                    modifier = Modifier
                                        .padding(start = 10.dp, end = 10.dp)
                                )
                                Text(it.description.toString(),
                                    modifier = Modifier
                                        .padding(start = 10.dp, end = 10.dp))
                                Text(it.rating.toString(),
                                    fontSize = 17.sp,
                                    color = Color.Green,
                                    modifier = Modifier
                                        .padding(start = 10.dp, end = 10.dp)
                                    )
                                Text("$"+it.price.toString(),
                                    fontSize = 25.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Red,
                                    modifier = Modifier
                                        .padding(start = 10.dp, end = 10.dp))
                            }
                        }
                    }
                }


            }
        }
    }
}

