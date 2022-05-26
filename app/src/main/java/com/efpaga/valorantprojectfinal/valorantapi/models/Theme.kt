package com.efpaga.valorantprojectfinal.valorantapi.models

import java.io.Serializable

class Theme(
    val uuid: String,
    val displayName: String,
    val displayIcon: String,
    val storeFeaturedImage: String,
    val assetPath: String
): Serializable