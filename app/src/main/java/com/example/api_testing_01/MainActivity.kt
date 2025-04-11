package com.example.api_testing_01

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
                    ) {
                        items(Quotes) {
                            Card(
                                modifier = Modifier
                                    .padding(horizontal = 12.dp, vertical = 8.dp)
                                    .fillMaxWidth()
                            ) {
                                Text(it.title.toString(),
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Blue)
                                Text(it.id.toString())
                                Text(it.category.toString(),
                                    fontWeight = FontWeight.SemiBold,
                                    color = Color.Magenta
                                )
                                Text(it.description.toString())
                                Text(it.rating.toString())
                                Text("$"+it.price.toString(),
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Red)
                            }
                        }
                    }
                }


            }
        }
    }
}

