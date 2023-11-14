package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    MakeLemonade()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MakeLemonade(modifier: Modifier = Modifier) {
    var stage by remember {
        mutableStateOf(1)
    }
    var squeezeCount by remember {
        mutableStateOf(2)
    }


    var imageSource: Int
    var stringResource: Int
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Lemonade",
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                ),
            )
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            color = MaterialTheme.colorScheme.background
        ) {
            when (stage) {
                1 -> {
                    imageSource = R.drawable.lemon_tree
                    stringResource = R.string.stage1
                }

                2 -> {
                    imageSource = R.drawable.lemon_squeeze
                    stringResource = R.string.stage2
                    squeezeCount = (2..4).random()
                }

                3 -> {
                    imageSource = R.drawable.lemon_drink
                    stringResource = R.string.stage3
                }

                else -> {
                    imageSource = R.drawable.lemon_restart
                    stringResource = R.string.stage4
                }

            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = modifier.fillMaxSize()
            ) {
                Button(
                    onClick = {
                        if (stage != 2) {
                            stage = (stage + 1) % 4
                        } else {
                            squeezeCount--
                            if (squeezeCount == 0) stage = (stage + 1) % 4
                        }
                    },
                    shape = RoundedCornerShape(40.dp),
                    colors = ButtonDefaults.buttonColors(Color(0xFFc2ecd3)),
                    contentPadding = PaddingValues(
                        start = 34.dp,
                        end = 34.dp,
                        top = 24.dp,
                        bottom = 24.dp
                    ),
                    border = BorderStroke(0.5.dp, Color.Black)
                ) {
                    Image(
                        painter = painterResource(id = imageSource),
                        contentDescription = "Lemon Tree",
                        modifier = Modifier
                            .width(128.dp)
                            .height(160.dp)
                    )
                }
                Text(
                    text = stringResource(stringResource),
                    fontSize = 18.sp,
                    modifier = Modifier.padding(top = 16.dp)
                )
            }
        }
    }
}


    @Preview(showBackground = true, showSystemUi = true)
    @Composable
    fun GreetingPreview() {
        LemonadeTheme {
            MakeLemonade()
        }
    }
