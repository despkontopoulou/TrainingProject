package com.despkontopoulou.trainingproject.ui.screens

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Environment
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import android.net.Uri
import android.widget.Toast
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.core.content.FileProvider
import com.despkontopoulou.trainingproject.api.ApiClient
import com.despkontopoulou.trainingproject.ui.components.*
import com.despkontopoulou.trainingproject.ui.theme.LightBlack
import com.despkontopoulou.trainingproject.utils.Magazine
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.File

@Composable
fun MagazineScreen(bearerToken: String) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val httpClient = remember { OkHttpClient() }

    // Inspect existing files to know which magazines are already downloaded
    val downloadsDir = context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)
    val initiallyDownloaded = remember {
        downloadsDir
            ?.listFiles()
            ?.mapNotNull { it.nameWithoutExtension.toIntOrNull() }
            ?.toSet()
            ?: emptySet()
    }
    // Track downloaded IDs in state
    var downloadedIds by rememberSaveable { mutableStateOf(initiallyDownloaded) }

    // Load magazines from API
    val magazines by produceState(initialValue = emptyList<Magazine>(), key1 = bearerToken) {
        value = try {
            ApiClient.booksApi.getBooks("Bearer $bearerToken")
        } catch (_: Exception) {
            emptyList()
        }
    }

    // When user taps a cover
    val onMagazineClick: (Magazine) -> Unit = { mag ->
        scope.launch(Dispatchers.IO) {
            val outFile = File(downloadsDir, "${mag.id}.pdf")

            // if downloaded, open
            if (mag.id in downloadedIds && outFile.exists()) {
                val uri = FileProvider.getUriForFile(
                    context,
                    "${context.packageName}.fileprovider",
                    outFile
                )
                context.startActivity(Intent(Intent.ACTION_VIEW).apply {
                    setDataAndType(uri, "application/pdf")
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or
                            Intent.FLAG_GRANT_READ_URI_PERMISSION
                })
                return@launch
            }

            // otherwise, download now
            try {
                val resp = httpClient.newCall(Request.Builder().url(mag.pdfUrl).build())
                    .execute()
                if (!resp.isSuccessful || resp.body == null) throw Exception("Download failed")
                resp.body!!.byteStream().use { input ->
                    outFile.outputStream().use { output ->
                        input.copyTo(output)
                    }
                }

                //and then open
                withContext(Dispatchers.Main) {
                    downloadedIds = downloadedIds + mag.id
                    val uri = FileProvider.getUriForFile(
                        context,
                        "${context.packageName}.fileprovider",
                        outFile
                    )
                    context.startActivity(Intent(Intent.ACTION_VIEW).apply {
                        setDataAndType(uri, "application/pdf")
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK or
                                Intent.FLAG_GRANT_READ_URI_PERMISSION
                    })
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        context,
                        "Download or open failed",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    Box(
        Modifier
            .fillMaxSize()
            .background(LightBlack)
    ) {
        Column(Modifier.fillMaxSize()) {
            TitleBar(
                title= "Magazine",
                modifier= Modifier.statusBarsPadding()
            )
            Box(Modifier.weight(1f)) {
                MagazineViewCollection(
                    magazines= magazines,
                    downloadedIds= downloadedIds,
                    onMagazineClick= onMagazineClick,
                    modifier= Modifier.fillMaxSize()
                )
            }
            NavigationBar()
        }
    }
}
