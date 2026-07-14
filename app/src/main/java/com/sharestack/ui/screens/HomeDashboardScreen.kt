package com.sharestack.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sharestack.models.InvestmentGroup
import com.sharestack.models.Proposal
import com.sharestack.ui.theme.ShareStackTheme
import com.sharestack.viewModel.ShareStackViewModel
import java.util.Calendar

// Helper function to determine time of day
fun getGreetingMessage(): String {
    val currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
    return when (currentHour) {
        in 0..11 -> "Good morning"
        in 12..16 -> "Good afternoon"
        else -> "Good evening"
    }
}
@Composable
fun HomeDashboardScreen(
    viewModel: ShareStackViewModel = viewModel(),
    onNavigateToProposal: () -> Unit ={},
    onNavigateToGroupHub: (String) -> Unit = {}, // <-- Now requires a String ID!
    onLogout:() -> Unit ={}
) {
    val currentUser by viewModel.currentUser.collectAsState()
    val balance by viewModel.balance.collectAsState()
    val investmentGroups by viewModel.investmentGroups.collectAsState()

    val displayGroups = if (investmentGroups.isEmpty()) {
        emptyList() // We don't need the dummy fallback anymore since the ViewModel has real mock data!
    } else {
        investmentGroups
    }

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .systemBarsPadding()
                    .padding(all = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment =androidx.compose.ui.Alignment.CenterVertically
            ) {
                Text(
                    text = "${getGreetingMessage()}, ${currentUser.name}!",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )

                //LOGOUT
                TextButton(onClick = onLogout) {
                    Text("Logout", color = MaterialTheme.colorScheme.error, fontWeight = FontWeight.Bold)
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {
            // PORTFOLIO VALUE CARD
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 12.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
            ) {
                Column(modifier = Modifier.padding(all = 28.dp)) {
                    Text("Total Stack Value", color = MaterialTheme.colorScheme.onPrimaryContainer)
                    Text(
                        text = "Ksh ${String.format("%.2f", balance)}",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Text("Your Active Stacks", fontSize = 18.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 12.dp))

            LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                items(displayGroups) { group ->
                    // GROUP CARDS
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = { onNavigateToGroupHub(group.id) }, // <-- Passes the EXACT ID of the card clicked!
                        shape = RoundedCornerShape(16.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                    ) {
                        Column(modifier = Modifier.padding(all = 20.dp)) {
                            Text(text = group.name, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                            Text(
                                text = "Members: ${group.memberCount} | Active Proposals: ${group.activeProposals.size}",
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.padding(top = 4.dp)
                            )
                            // Notice: The extra button is completely gone!
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeDashboard() {
    ShareStackTheme { HomeDashboardScreen() }
}