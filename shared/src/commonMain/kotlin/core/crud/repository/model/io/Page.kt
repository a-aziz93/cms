package core.crud.repository.model.io

class Page(
    offset: Long,
    limit: Long,
) : LimitOffset(offset * limit, limit)
