package presentation.ui.home

import domain.model.Schedule
import domain.model.Segment
import java.time.LocalTime

val dualCore1 = Schedule(
    name = "Dual Core 1",
    tst = LocalTime.of(5, 20),
    segments = mutableListOf(
        Segment(
            LocalTime.of(21, 30),
            LocalTime.of(0, 50)
        ),
        Segment(
            LocalTime.of(5, 50),
            LocalTime.of(7, 30)
        ),

        Segment(
            LocalTime.of(14, 0),
            LocalTime.of(14, 20)
        )
    )
)


val everyman1 = Schedule(
    name = "Everyman 1",
    tst = LocalTime.of(6, 20),
    segments = mutableListOf(
        Segment(
            LocalTime.of(23, 0),
            LocalTime.of(5, 0)
        ),
        Segment(
            LocalTime.of(13, 0),
            LocalTime.of(13, 25)
        )
    )
)


val siesta = Schedule(
    name = "Siesta",
    tst = LocalTime.of(6, 30),
    segments = mutableListOf(
        Segment(
            LocalTime.of(23, 0),
            LocalTime.of(4, 0)
        ),
        Segment(
            LocalTime.of(13, 0),
            LocalTime.of(14, 30)
        )
    )
)


val segmented = Schedule(
    name = "Segmented",
    tst = LocalTime.of(7, 0),
    segments = mutableListOf(
        Segment(
            LocalTime.of(22, 0),
            LocalTime.of(1, 30)
        ),

        Segment(
            LocalTime.of(4, 30),
            LocalTime.of(8, 0)
        )
    )
)


val dualCore2 = Schedule(
    name = "Dual Core 2",
    tst = LocalTime.of(5, 10),
    segments = mutableListOf(
        Segment(
            LocalTime.of(22, 0),
            LocalTime.of(1, 0)
        ),
        Segment(
            LocalTime.of(5, 50),
            LocalTime.of(7, 20)
        ),
        Segment(
            LocalTime.of(12, 0),
            LocalTime.of(12, 25)
        ),
        Segment(
            LocalTime.of(16, 0),
            LocalTime.of(16, 25)
        )
    )
)


val everyman2 = Schedule(
    name = "Everyman 2",
    tst = LocalTime.of(5, 10),
    segments = mutableListOf(
        Segment(
            LocalTime.of(23, 0),
            LocalTime.of(3, 30)
        ),
        Segment(
            LocalTime.of(8, 0),
            LocalTime.of(8, 25)
        ),

        Segment(
            LocalTime.of(14, 30),
            LocalTime.of(14, 55)
        )
    )
)

