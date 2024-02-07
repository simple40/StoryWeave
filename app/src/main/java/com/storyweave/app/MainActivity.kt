package com.storyweave.app

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.google.android.gms.auth.api.identity.Identity
import com.storyweave.app.Auth.GoogleAuthUiClient
import com.storyweave.app.Auth.SignInScreen
import com.storyweave.app.Auth.SignInViewModel
import com.storyweave.app.presentation.storyRead.components.ReadScreen
import com.storyweave.app.ui.theme.StoryWeaveTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val googleAuthUiClient by lazy{
        GoogleAuthUiClient(
            context = applicationContext,
            oneTapClient = Identity.getSignInClient(applicationContext)
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StoryWeaveTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //Greeting("Android")
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "sign-in"){
                        composable("sign-in"){
                            val viewModel = viewModel<SignInViewModel>()
                            val state by viewModel.state.collectAsStateWithLifecycle()

                            LaunchedEffect(key1 = Unit){
                                if(googleAuthUiClient.getSignedInUser() != null){
                                    navController.navigate("read-screen")
                                }
                            }

                            val launcher = rememberLauncherForActivityResult(
                                contract = ActivityResultContracts.StartIntentSenderForResult(),
                                onResult = {result->
                                    if(result.resultCode == RESULT_OK){
                                        lifecycleScope.launch {
                                            val signInResult = googleAuthUiClient.signInWithIntent(
                                                intent = result.data?:return@launch
                                            )
                                            viewModel.onSignInResult(signInResult)
                                        }
                                    }
                                }
                            )

                            LaunchedEffect(key1 = state.isSignInSuccessful ){
                                if(state.isSignInSuccessful){
                                    val user = googleAuthUiClient.getSignedInUser()
                                    Toast.makeText(
                                        applicationContext,
                                        "Sign in Successful ${user?.username}",
                                        Toast.LENGTH_LONG
                                    ).show()

                                    navController.navigate("read-screen")
                                    viewModel.resetState()
                                }
                            }
                            SignInScreen(
                                state =state,
                                onSignInClick = {
                                    lifecycleScope.launch {
                                        val signInIntentSender = googleAuthUiClient.signIn()
                                        launcher.launch(
                                            IntentSenderRequest.Builder(
                                                signInIntentSender ?: return@launch
                                            ).build()
                                        )
                                    }
                                },
                                onSignOutClick = {
                                    lifecycleScope.launch {
                                        googleAuthUiClient.signOut()
                                        Toast.makeText(
                                            applicationContext,
                                            "Signed Out",
                                            Toast.LENGTH_LONG
                                        ).show()

                                    }
                                }
                                )
                        }

                        composable("read-screen"){
                            ReadScreen(
                                userData = googleAuthUiClient.getSignedInUser(),
                                onSignOutClick = {
                                    lifecycleScope.launch {
                                        googleAuthUiClient.signOut()
                                        Toast.makeText(
                                            applicationContext,
                                            "Signed Out",
                                            Toast.LENGTH_LONG
                                        ).show()
                                        navController.popBackStack()
                                    }
                                },
                                storyTitle = "Demo Story",
                                story = listOf(
                                    "Once upon a time...",
                                    "In a land far, far away...",
                                    "The protagonist embarked on a journey...",
                                    "They faced numerous challenges and overcame them...",
                                    "Throughout their adventure, they made new friends and encountered mysterious creatures...",
                                    "As the story unfolded, unexpected twists kept the readers on the edge of their seats...",
                                    "The journey took them to magical realms and enchanted forests...",
                                    "The protagonist discovered hidden powers within themselves...",
                                    "The climax of the story approached, leading to a final confrontation with the ultimate antagonist...",
                                    "In the end, the hero triumphed, and the land was saved from darkness...",
                                    "But the tale didn't end there; a new chapter began, promising more adventures and mysteries...",
                                    "And so, the story continued, weaving a tapestry of imagination and wonder...",
                                    "In another world, parallel to ours, a different saga unfolded...",
                                    "A lone wanderer set out on a quest to unlock the secrets of an ancient civilization...",
                                    "Mystical artifacts and forgotten prophecies guided their path...",
                                    "In the midst of chaos, alliances were forged and betrayals revealed...",
                                    "The echoes of ancient chants resonated through the air as the hero delved into the heart of darkness...",
                                    "The fate of worlds hung in the balance as the final battle approached...",
                                    "Across the cosmic expanse, a celestial dance of stars painted the canvas of the universe...",
                                    "Time itself seemed to bend as the hero confronted the enigmatic Timekeeper...",
                                    "Dimensions collided, creating a kaleidoscope of realities...",
                                    "The hero's journey intertwined with cosmic forces, shaping the destiny of existence...",
                                    "Legends whispered of a mythical city hidden beyond the edge of the world...",
                                    "Eldritch runes illuminated the path to the forgotten city of Eldoria...",
                                    "Ancient prophecies foretold of a chosen one who would unlock the city's secrets...",
                                    "The hero faced trials of mind and spirit, unlocking the ancient knowledge within...",
                                    "As dawn broke, the city's hidden wonders were revealed, rewriting the annals of history...",
                                    "But with every ending comes a new beginning, and the cycle of stories continued unabated...",
                                    "For in the realm of imagination, the possibilities were as boundless as the stars in the night sky..."
                                )
                            )
                        }

                    }


                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    StoryWeaveTheme {
        Greeting("Android")
    }
}