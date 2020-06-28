package com.aevshvetsov.minimalvkclient.models.networkmodels


import com.google.gson.annotations.SerializedName

data class Response(
    val groups: List<Group>,
    val items: List<Item>,
    @SerializedName("next_from")
    val nextFrom: String,
    val profiles: List<Profile>
)

data class Group(
    val id: Int,
    @SerializedName("is_admin")
    val isAdmin: Int,
    @SerializedName("is_advertiser")
    val isAdvertiser: Int,
    @SerializedName("is_closed")
    val isClosed: Int,
    @SerializedName("is_member")
    val isMember: Int,
    val name: String,
    @SerializedName("photo_100")
    val photo100: String,
    @SerializedName("photo_200")
    val photo200: String,
    @SerializedName("photo_50")
    val photo50: String,
    @SerializedName("screen_name")
    val screenName: String,
    val type: String
)

data class Item(
    val attachments: List<Attachment>,
    @SerializedName("can_doubt_category")
    val canDoubtCategory: Boolean,
    @SerializedName("can_set_category")
    val canSetCategory: Boolean,
    val comments: Comments,
    val date: Int,
    @SerializedName("is_favorite")
    val isFavorite: Boolean,
    val likes: Likes,
    @SerializedName("marked_as_ads")
    val markedAsAds: Int,
    @SerializedName("post_id")
    val postId: Int,
    @SerializedName("post_source")
    val postSource: PostSource,
    @SerializedName("post_type")
    val postType: String,
    val reposts: Reposts,
    @SerializedName("source_id")
    val sourceId: Int,
    val views: Views,
    val text: String,
    val type: String
)

data class Profile(
    @SerializedName("can_access_closed")
    val canAccessClosed: Boolean,
    @SerializedName("first_name")
    val firstName: String,
    val id: Int,
    @SerializedName("is_closed")
    val isClosed: Boolean,
    @SerializedName("last_name")
    val lastName: String,
    val online: Int,
    @SerializedName("online_info")
    val onlineInfo: OnlineInfo,
    @SerializedName("photo_100")
    val photo100: String,
    @SerializedName("photo_50")
    val photo50: String,
    @SerializedName("screen_name")
    val screenName: String,
    val sex: Int
)