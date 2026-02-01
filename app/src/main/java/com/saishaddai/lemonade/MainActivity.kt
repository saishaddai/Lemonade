package com.saishaddai.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.saishaddai.lemonade.ui.theme.LemonadeTheme

sealed class Stage {
    abstract val imageId: Int
    abstract val contentDescriptionId: Int
    abstract val instructions: Int

    class Tree(
        override val imageId: Int = R.drawable.lemon_tree,
        override val contentDescriptionId: Int = R.string.cd_lemon_tree,
        override val instructions: Int = R.string.instructions_1
    ) : Stage()

    class Lemon(
        override val imageId: Int = R.drawable.lemon_squeeze,
        override val contentDescriptionId: Int = R.string.cd_lemon,
        override val instructions: Int = R.string.instructions_2
    ) : Stage()

    class Lemonade(
        override val imageId: Int = R.drawable.lemon_drink,
        override val contentDescriptionId: Int = R.string.cd_glass_of_lemonade,
        override val instructions: Int = R.string.instructions_3
    ) : Stage()

    class Glass(
        override val imageId: Int = R.drawable.lemon_restart,
        override val contentDescriptionId: Int = R.string.cd_empty_glass,
        override val instructions: Int = R.string.instructions_4
    ) : Stage()
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                LemonadeScreen()
            }
        }
    }
}

@Composable
fun LemonadeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LemonadeHeader()
        LemonadeBody()
    }
}

@Composable
private fun LemonadeBody() {
    var stage: Stage by remember { mutableStateOf(Stage.Tree()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Card(
            shape = RoundedCornerShape(28.dp)
        ) {
            Image(
                painter = painterResource(id = stage.imageId),
                contentDescription = stringResource(stage.contentDescriptionId),
                modifier = Modifier
                    .border(2.dp, Color(105, 205, 216), RectangleShape)
                    .padding(16.dp)
                    .clickable {
                        stage = when (stage) {
                            is Stage.Tree -> Stage.Lemon()
                            is Stage.Lemon -> Stage.Lemonade()
                            is Stage.Lemonade -> Stage.Glass()
                            else -> Stage.Tree()
                        }
                    }
            )
        }

        Spacer(Modifier.height(16.dp))

        Text(text = stringResource(stage.instructions))
    }
}

@Composable
private fun LemonadeHeader() {
    Box(
        modifier = Modifier
            .height(56.dp)
            .fillMaxWidth()
            .background(Color.Yellow)
    ) {
        Text(
            text = stringResource(R.string.app_name),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(18.dp)
                .align(Alignment.Center)
                .background(Color.Yellow)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LemonadeScreenPreview() {
    LemonadeScreen()
}
