package core.expression.temporal

enum class TemporalExpressionType {
    // NOW
    NOW,

    // Format HH:MI:SS.NNNNNNN
    TIME,

    // Format YYYY-MM-DD
    DATE,

    // Format: YYYY-MM-DD HH:MI:SS.NNNNNNN
    DATETIME,

    // Format: YYYY-MM-DD HH:MI:SS.NNNNNNN[+|-]HH:MM
    DATETIME_OFFSET,

    // Format YYYY or YY
    FORMAT,
}