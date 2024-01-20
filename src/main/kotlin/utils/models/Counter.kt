package utils.models

data class Counter(
    var count: Int = 0
) {

    // format number to pretty string
    val pretty: String
        get() = count.toString()
            .reversed()
            .chunked(3)
            .map { it.reversed() }
            .reduceRight { s, acc -> "${acc}_${s}" }
}