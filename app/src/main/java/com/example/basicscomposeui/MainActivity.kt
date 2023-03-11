package com.example.basicscomposeui

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import android.view.RoundedCorner
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.basicscomposeui.ui.theme.BasicsComposeUITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasicsComposeUITheme {
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp() {
    /** Delegate Function. Remember State */
//    var shouldShowOnboarding by remember { mutableStateOf(true) }
    /** Delegate Function. Remember State - SavedInstance - After Re-create Activity..*/
    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }

    if (shouldShowOnboarding) {
        OnBoardingScreen { shouldShowOnboarding = false }
    } else {
        Greetings()
    }
}

@Composable
private fun Greetings(names: List<String> = List(1000) { "$it" }) {
    // A surface container using the 'background' color from the theme
    Surface(
        color = MaterialTheme.colors.background,
    ) {
        Column(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth(1f)
        ) {
            LazyColumn {
                item {
                    Text(
                        "Welcome To Boarding Screen.",
                        modifier = Modifier.padding(bottom = 12.dp),
                        style = MaterialTheme.typography.h4
                    )
                }
                items(items = names) { item ->
                    Greeting(name = item)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    val expanded = remember { mutableStateOf(false) }

    /** Add padding With Animation. */
    val extraPadding by animateDpAsState(
        targetValue = if (expanded.value) 22.dp else 0.dp,
        animationSpec = tween(durationMillis = 1000)
    )
    /** Add padding Without Animation.*/
    /* val extraPadding = if (expanded.value) 22.dp else 0.dp */

    Surface(
        color = MaterialTheme.colors.primary,
        modifier = Modifier
            .padding(6.dp)
            .fillMaxWidth()
    ) {
        Row {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "Contact Profile Picture.",
                modifier = Modifier
                    .clip(CircleShape)
                    .size(40.dp)
                    .padding(16.dp)
            )
            Column(
                modifier = Modifier
                    .padding(12.dp)
                    .padding(extraPadding)
                    .weight(1f)
            ) {
                Text(
                    text = "Hello ",
                )
                Text(
                    text = "$name!",
                )
            }
            OutlinedButton(
                modifier = Modifier.padding(end = 10.dp, top = 10.dp),
                onClick = { expanded.value = !expanded.value }
            ) {
                Text(if (expanded.value) "Show Less." else "Show More.")
            }
        }
    }
}

@Composable
fun OnBoardingScreen(
    modifier: Modifier = Modifier,
    onContinueClicked: () -> Unit
) {

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome to the Basics Codelab!")
        Button(
            modifier = Modifier.padding(vertical = 24.dp),
            onClick = onContinueClicked
        ) {
            Text("Continue")
        }
    }
}

//@Preview(showBackground = true, widthDp = 300, heightDp = 350, uiMode = UI_MODE_NIGHT_YES)
//@Preview(showBackground = true, widthDp = 300, heightDp = 350)
@Composable
fun OnBoardingPreview() {
    BasicsComposeUITheme {
        OnBoardingScreen(onContinueClicked = {})
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BasicsComposeUITheme {
        Greetings()
    }
}

