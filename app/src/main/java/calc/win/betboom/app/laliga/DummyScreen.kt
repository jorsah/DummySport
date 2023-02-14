package calc.win.betboom.app.laliga

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import calc.win.betboom.app.laliga.model.dummyList
import calc.win.betboom.app.laliga.util.QuantityMenuSpinner
import calc.win.betboom.R

@Composable
fun DummyScreen() {
    val leftState = remember {
        mutableStateOf(dummyList[0])
    }
    val rightState = remember {
        mutableStateOf(dummyList[1])
    }

    val leftChances = remember {
        mutableStateOf(50)
    }
    val rightChances = remember {
        mutableStateOf(50)
    }
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.backround),
            contentScale = ContentScale.FillHeight,
            contentDescription = "background"
        )

        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row {
                Image(
                    modifier = Modifier
                        .height(120.dp)
                        .weight(0.5f),
                    painter = painterResource(id = leftState.value.iconId),
                    contentDescription = "Left Club"
                )
                Image(
                    modifier = Modifier
                        .height(120.dp)
                        .weight(0.5f),
                    painter = painterResource(id = rightState.value.iconId),
                    contentDescription = "Right Club"
                )
            }

            Row {
                QuantityMenuSpinner(
                    modifier = Modifier
                        .height(42.dp)
                        .weight(0.5f),
                    availableQuantities = dummyList,
                    selectedItem = leftState.value,
                    onItemSelected = {
                        leftState.value = it
                    }
                )
                QuantityMenuSpinner(
                    modifier = Modifier
                        .height(42.dp)
                        .weight(0.5f),
                    availableQuantities = dummyList,
                    selectedItem = rightState.value,
                    onItemSelected = {

                        rightState.value = it
                    }
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(horizontalArrangement = Arrangement.Center) {
                Text(
                    modifier = Modifier
                        .height(48.dp)
                        .weight(0.5f),
                    text = "${leftChances.value}%",
                    fontSize = 18.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
                Text(
                    modifier = Modifier
                        .height(48.dp)
                        .weight(0.5f),
                    text = "${rightChances.value}%",
                    fontSize = 18.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }

            Button(onClick = {
                val sumPower = leftState.value.power + rightState.value.power
                val percent = (leftState.value.power / sumPower.toDouble()) * 100
                leftChances.value = percent.toInt()
                rightChances.value = 100 - percent.toInt()
            }) {
                Text(text = "Evaluate Chances")
            }
        }
    }
}

@Preview
@Composable
fun DummyPreview() {
    DummyScreen()
}

