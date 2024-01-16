package core.crud.model.entity

class Page(
    offset: Long,
    limit: Long,
) : LimitOffset(offset * limit, limit)
