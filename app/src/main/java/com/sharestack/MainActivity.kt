package com.sharestack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.sharestack.ui.theme.ShareStackTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.sharestack.ui.screens.ActiveProposalCard
import com.sharestack.ui.screens.CreateProposalScreen
import com.sharestack.ui.screens.GroupDetailScreen
import com.sharestack.ui.screens.HomeDashboardScreen
import com.sharestack.ui.screens.LoginScreen
import com.sharestack.ui.screens.RegisterScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShareStackTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Our master navigation router
                    var currentScreen by remember { mutableStateOf("login") }

                    when (currentScreen) {
                        "login" -> {
                            LoginScreen(
                                onNavigateToHome = { currentScreen = "home" },
                                onNavigateToRegister = { currentScreen = "register" }
                            )
                        }
                        "register" -> {
                            RegisterScreen(
                                onNavigateToLogin = { currentScreen = "login" },
                                onNavigateToHome = { currentScreen = "home" }
                            )
                        }
                        "home" -> {
                            HomeDashboardScreen(
                                onNavigateToProposal = { currentScreen = "proposal" },
                                onNavigateToGroupHub = { currentScreen = "group_detail" }
                            )
                        }
                        "group_detail" -> {
                            GroupDetailScreen(
                                onNavigateBack = { currentScreen = "home" },
                                onNavigateToProposal = { currentScreen = "proposal" },
                                onNavigateToCreate = { currentScreen = "create_proposal" }
                            )
                        }
                        "proposal" -> {
                            // This is the very first screen we built!
                            ActiveProposalCard(
                            onNavigateBack = { currentScreen = "home" }
                            )
                        }
                        "create_proposal" -> {
                            CreateProposalScreen(
                                onNavigateBack = { currentScreen = "group_detail" },
                                onSubmit = {
                                    // TODO: Later, this will send data to the backend.
                                    // For now, just route them back to the hub.
                                    currentScreen = "group_detail"
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}