// ui/components/MagazineViewCollection.kt
package com.despkontopoulou.trainingproject.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.despkontopoulou.trainingproject.utils.Magazine
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

@Composable
fun MagazineViewCollection(
    magazines: List<Magazine>,
    downloadedIds: Set<Int>,
    onMagazineClick: (Magazine) -> Unit,
    modifier: Modifier = Modifier
) {
    val inputFmt  = DateTimeFormatter.ISO_OFFSET_DATE_TIME
    val outputFmt = DateTimeFormatter.ofPattern("MMM yyyy")

    val grouped: Map<String, List<Magazine>> = magazines
        .sortedByDescending { OffsetDateTime.parse(it.dateReleased, inputFmt) }
        .groupBy { dto ->
            OffsetDateTime.parse(dto.dateReleased, inputFmt)
                .format(outputFmt)
        }

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 4.dp)
    ) {
        grouped.forEach { (monthYear, itemsInSection) ->
            stickyHeader {
                DateHeader(monthYear = monthYear)
            }
            // Break each section into rows of two magazines
            items(itemsInSection.chunked(2)) { rowItems ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    rowItems.forEach { mag ->
                        MagazineView(
                            magazine= mag,
                            downloaded = downloadedIds.contains(mag.id),
                            onClick = onMagazineClick,
                            modifier= Modifier.weight(1f)
                        )
                    }

                    if (rowItems.size == 1) {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
}
