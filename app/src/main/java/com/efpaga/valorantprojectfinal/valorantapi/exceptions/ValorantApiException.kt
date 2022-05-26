package com.efpaga.valorantprojectfinal.valorantapi.exceptions

import java.lang.Exception

class ValorantApiException(
    status: Int?,
    error: String
): Exception("$error ${if(status != null) "(${status})" else ""}")