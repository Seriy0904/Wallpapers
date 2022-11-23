package dev.seriy0904.wallpapers.ui.imageDetails

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.google.accompanist.flowlayout.FlowRow
import dev.seriy0904.wallpapers.model.FiltersModel
import dev.seriy0904.wallpapers.model.Tag
import dev.seriy0904.wallpapers.ui.graphs.WallpaperNavigationActions


private const val loadingText = "LOADING"

@Composable
fun ImageDetailsScreen(
    imageId: String,
    viewModel: ImageDetailsViewModel,
    navigationActions: WallpaperNavigationActions
) {
    val clipboardManager = LocalClipboardManager.current
    val mContext = LocalContext.current
    val imageDetails by viewModel.imageDetails.observeAsState()
    val scrollState = rememberScrollState()
    val imageUrl = imageDetails?.data?.path
    val uploaderImageUrl = imageDetails?.data?.uploader?.avatar?.`128px` ?: ""
    Scaffold {scaffoldPadding ->
        Column(
            Modifier
                .padding(scaffoldPadding)
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            SubcomposeAsyncImage(
                model = imageUrl,
                contentDescription = "Current Image",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(8.dp),
                loading = {
                    CircularProgressIndicator()
                })
            Divider(
                Modifier
                    .padding(vertical = 6.dp)
                    .height(1.dp),
                color = MaterialTheme.colors.primaryVariant
            )
            Column(Modifier.padding(horizontal = 6.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "ID",
                        color = MaterialTheme.colors.onPrimary,
                        fontSize = 20.sp
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = imageId,
                        color = MaterialTheme.colors.onPrimary,
                        modifier = Modifier
                            .background(MaterialTheme.colors.primaryVariant)
                            .padding(3.dp)
                            .clickable {
                                Toast
                                    .makeText(
                                        mContext,
                                        "$imageId saved to clipboard",
                                        Toast.LENGTH_SHORT
                                    )
                                    .show()
                                clipboardManager.setText(AnnotatedString(imageId))
                            }
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row {
                    Text(
                        text = "Uploader",
                        color = MaterialTheme.colors.onPrimary,
                        fontSize = 20.sp
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Row(
                        Modifier
                            .background(MaterialTheme.colors.primaryVariant)
                            .padding(3.dp)
                    ) {
                        SubcomposeAsyncImage(
                            model = uploaderImageUrl,
                            contentDescription = "Current Image uploader"
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text(
                                text = imageDetails?.data?.uploader?.username ?: loadingText,
                                color = MaterialTheme.colors.onPrimary,
                                fontSize = 18.sp
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = imageDetails?.data?.created_at?.substringBefore(' ')
                                    ?: loadingText,
                                color = MaterialTheme.colors.onPrimary
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))
                Row {
                    Text(
                        text = "Tags",
                        color = MaterialTheme.colors.onPrimary,
                        fontSize = 20.sp
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    TagsGrid(imageDetails?.data?.tags ?: listOf()) {
                        navigationActions.navigateToCustomList(FiltersModel(tags = it))
                    }
                }
            }
        }
    }
}

@Composable
fun TagsGrid(tags: List<Tag>, onClickAction: (String) -> Unit) {
    FlowRow {
        tags.forEach { tag ->
            Text(
                text = tag.name,
                Modifier
                    .padding(3.dp)
                    .background(MaterialTheme.colors.primaryVariant, MaterialTheme.shapes.small)
                    .padding(2.dp)
                    .clickable { onClickAction("id:${tag.id}") },
                color = MaterialTheme.colors.onPrimary
            )
        }
    }
}