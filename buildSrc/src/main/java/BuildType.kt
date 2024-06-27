interface BuildType {
    companion object {

        const val DEBUG = "debug"
        const val SIT = "sit"
        const val RELEASE = "release"
        const val UAT = "uat"
    }

    val isDebuggable: Boolean
    val isMinifyEnabled: Boolean
    val isTestCoverageEnabled: Boolean
}

object BuildTypeSit : BuildType {

    override val isDebuggable: Boolean
        get() = true
    override val isMinifyEnabled: Boolean
        get() = false
    override val isTestCoverageEnabled: Boolean
        get() = true

    const val applicationSuffix = ".sit"
    const val versionNameSuffix = "-sit"
}

object BuildTypeUat : BuildType {

    override val isDebuggable: Boolean
        get() = true
    override val isMinifyEnabled: Boolean
        get() = false
    override val isTestCoverageEnabled: Boolean
        get() = true

    const val applicationSuffix = ".uat"
    const val versionNameSuffix = "-uat"
}

object BuildTypeProd : BuildType {

    override val isDebuggable: Boolean
        get() = false
    override val isMinifyEnabled: Boolean
        get() = false
    override val isTestCoverageEnabled: Boolean
        get() = false

    const val applicationSuffix = ".perfectlyPressed"
}
