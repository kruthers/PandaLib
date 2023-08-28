package com.kruthers.pandalib

@Suppress("UNUSED")
class Version(
    private val major: Int, private val minor: Int, private val patch: Int, private val state: State,
    private val displayState: String?, private val meta: String
) {

    companion object {
        private val regex = Regex("^(\\d+)\\.(\\d+)\\.(\\d+)(?:-(\\w+)((?:\\.\\w+)+)?)?(\\+[\\S\\w]+)?\$")
        fun of(string: String): Version {
            val matches = regex.matchEntire(string) ?: throw InvalidFormatException(string)

            val major = matches.groupValues[1].toInt()
            val minor = matches.groupValues[2].toInt()
            val patch = matches.groupValues[3].toInt()

            val displayState = matches.groupValues.getOrNull(4)
            val state: State = displayState?.let { State.fromSuffix(it.lowercase()) }?: State.RELEASE

            val meta = "${matches.groupValues.getOrNull(5)?:""}${matches.groupValues.getOrNull(6)?:""}"

            return Version(major, minor, patch, state, displayState, meta)
        }
    }

    override fun toString(): String {
        val state: String = if (this.displayState != null && this.displayState != "") {
            "-$displayState"
        } else ""

        return "${this.major}.${this.minor}.${this.patch}${state}${this.meta}"
    }

    /**
     * Checks if the provided version is compatible with the current version
     * Compares following Semantic Versioning 2.0.0. will ignore meta and pre-release versioning
     * @param version The version to compare too
     * @return true if they are within versions and are not a newer release
     */
    @Suppress("MemberVisibilityCanBePrivate")
    fun isCompatible(version: Version): Boolean {
        if (version.major != this.major) return false //major versions may contain breaking changes
        if (version.minor > this.minor) return false //minor versions must be backwards compatible but can add new features
        if (version.state != this.state) return false //these are development updates there for unstable
        return true
    }

    /**
     * Checks if the provided version is compatible with the current version
     * Compares following Semantic Versioning 2.0.0. will ignore meta and pre-release versioning
     * @param version A string version following https://semver.org/
     * @return true if they are within versions and are not a newer release
     *
     * @throws InvalidFormatException if the version syntax does not match https://semver.org/
     */
    fun isCompatible(version: String): Boolean = this.isCompatible(of(version))

    /**
     * Compares to provided versions
     * Compares following Semantic Versioning 2.0.0. will ignore meta and pre-release versioning
     * @param version The version to compare too
     * @return A positive number means the provided is newer
     * @return A negative number mean the provided version is older
     */
    @Suppress("MemberVisibilityCanBePrivate")
    fun compare(version: Version): Int {
        var result = version.major - this.major
        if (result != 0) return result
        result = version.minor - this.minor
        if (result != 0) return result
        result = version.patch - this.patch
        if (result != 0) return result
        result = version.state.version - this.state.version
        return result
    }

    /**
     * Compares to provided versions
     * Compares following Semantic Versioning 2.0.0. will ignore meta and pre-release versioning
     * @param version A string version following https://semver.org/
     * @return A positive number means the provided is newer
     * @return A negative number mean the provided version is older
     *
     * @throws InvalidFormatException if the version syntax does not match https://semver.org/
     */
    fun compare(version: String): Int = this.compare(of(version))

    enum class State(val version: Int, vararg names: String) {
        RELEASE(4),
        PRE_RELEASE(3,"rc","snapshot","pre-release"),
        BETA(2,"beta","dev"),
        ALPHA(1,"alpha");

        val suffixs: Set<String>
        init {
            this.suffixs = names.toSet()
        }

        companion object {
            fun fromSuffix(string: String): State {
                var state = RELEASE
                entries.forEach {
                    if (it.suffixs.contains(string.lowercase())) {
                        state = it
                        return@forEach
                    }
                }
                return state
            }
        }
    }

    class InvalidFormatException(name: String): Exception("The provided version string '$name' does not match the Semantic Versioning " +
            "2.0.0 specification please read them here: https://semver.org/")

}