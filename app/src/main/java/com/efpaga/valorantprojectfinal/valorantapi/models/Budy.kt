package com.efpaga.valorantprojectfinal.valorantapi.models

import java.io.Serializable

class Budy(
     val uuid: String,
     val displayName: String,
     val themeUuid: String?,
     val displayIcon: String,
     val assetPath: String,
     val isHiddenIfNotOwned: Boolean
): Serializable