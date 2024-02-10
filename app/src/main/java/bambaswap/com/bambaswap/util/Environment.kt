package bambaswap.com.bambaswap.util


sealed class Environment(val url: String) {
    data object Dev : Environment("http://emmanuelkoech-002-site1.gtempurl.com/")
}