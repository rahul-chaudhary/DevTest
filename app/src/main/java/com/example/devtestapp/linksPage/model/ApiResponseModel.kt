package com.example.devtestapp.linksPage.model

data class ApiResponse(
    val applied_campaign: Int,
    val `data`: Data,
    val extra_income: Double,
    val links_created_today: Int,
    val message: String,
    val startTime: String,
    val status: Boolean,
    val statusCode: Int,
    val support_whatsapp_number: String,
    val today_clicks: Int,
    val top_location: String,
    val top_source: String,
    val total_clicks: Int,
    val total_links: Int
)



//data class ApiResponse(
//    val status: Boolean,
//    val statusCode: Int,
//    val message: String,
//    val supportWhatsappNumber: String,
//    val extraIncome: Double,
//    val totalLinks: Int,
//    val totalClicks: Int,
//    val todayClicks: Int,
//    val topSource: String,
//    val topLocation: String,
//    val startTime: String,
//    val linksCreatedToday: Int,
//    val appliedCampaign: Int,
//    val data: Data
//)
//
//data class Data(
//    val recentLinks: List<Link>,
//    val topLinks: List<Link>,
//    val favouriteLinks: List<Link>,
//    val overallUrlChart: Map<String, Int>
//)
//
//data class Link(
//    val urlId: Int,
//    val webLink: String,
//    val smartLink: String,
//    val title: String,
//    val totalClicks: Int,
//    val originalImage: String?,
//    val thumbnail: String?,
//    val timesAgo: String,
//    val createdAt: String,
//    val domainId: String,
//    val urlPrefix: String?,
//    val urlSuffix: String,
//    val app: String,
//    val isFavourite: Boolean
//)