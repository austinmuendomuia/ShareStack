package com.sharestack.models

// Represents a user logged into the app
data class User(
    val id: String,
    val name: String,
    val totalPortfolioValue: Double
)

// Represents an active investment pitch
data class Proposal(
    val id: String,
    val stockTarget: String,
    val targetAmount: Double,
    val activeMembers: List<String>
)

// Represents a group of users pooling money
data class InvestmentGroup(
    val id: String,
    val name: String,
    val memberCount: Int,
    val activeProposals: List<Proposal>
)