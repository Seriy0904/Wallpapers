package dev.seriy0904.wallpapers.ui.customList

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.seriy0904.wallpapers.model.FiltersModel
import dev.seriy0904.wallpapers.model.categoriesList
import dev.seriy0904.wallpapers.model.sortingDestinations
import dev.seriy0904.wallpapers.model.sortingList
import dev.seriy0904.wallpapers.ui.graphs.WallpaperNavigationActions
import dev.seriy0904.wallpapers.ui.utils.ImageList

@Composable
fun CustomListScreen(
    viewModel: CustomListViewModel,
    navigationActions: WallpaperNavigationActions,
    filter: FiltersModel = FiltersModel()
) {
    Column {
        val searchModel by viewModel.customList.observeAsState()
        val customFilter by remember { mutableStateOf(filter) }
        LazyRow(
            verticalAlignment = CenterVertically,
            modifier = Modifier.padding(4.dp)
        ) {
            item {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    tint = MaterialTheme.colors.onPrimary,
                    contentDescription = "Refresh list with filters",
                    modifier = Modifier
                        .clickable {
                            viewModel.customListLoad(filter)
                        }
                        .size(34.dp)
                        .padding(end = 6.dp)
                )
                SearchTag(){
                    customFilter.tags = it
                }
                Spacer(modifier = Modifier.width(6.dp))
                SortingDropDownMenu(customFilter.sorting, sortingList, sortingDestinations) {
                    customFilter.sorting = it
                }
                Spacer(modifier = Modifier.width(6.dp))
                CategoriesCheckBoxes(customFilter.categories, categoriesList) {
                    customFilter.categories = it
                }
            }
        }
        ImageList(searchModel) {
            navigationActions.navigateToSelectedImage(it)
        }
    }
}

@Composable
fun SortingDropDownMenu(
    selectedItem: String,
    itemList: List<String>,
    itemsDestinations: HashMap<String, String>,
    onSelect: (String) -> Unit
) {
    var selected by remember { mutableStateOf(selectedItem) }
    val expandedMenu = remember { mutableStateOf(false) }
    Box(modifier = Modifier
        .clickable { expandedMenu.value = true }
        .background(
            MaterialTheme.colors.primaryVariant,
            MaterialTheme.shapes.small
        )) {
        Row {
            Text(
                text = itemsDestinations[selected] ?: "null",
                color = MaterialTheme.colors.onPrimary,
                fontSize = 24.sp,
                modifier = Modifier.padding(start = 4.dp)
            )
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = "drawable icons",
                modifier = Modifier.align(CenterVertically),
                tint = MaterialTheme.colors.onPrimary,
            )
        }
        DropdownMenu(
            expanded = expandedMenu.value,
            onDismissRequest = { expandedMenu.value = false }) {
            itemList.forEach {
                DropdownMenuItem(onClick = {
                    onSelect(it)//
                    selected = it
                    expandedMenu.value = false
                }) {
                    val textType = MaterialTheme.typography.body1
                    Text(
                        text = itemsDestinations[it] ?: "null",
                        fontStyle = if (it == selected) FontStyle.Italic else textType.fontStyle,
                        color = if (it == selected) Color.Green else textType.color
                    )
                }
            }
        }
    }
}

@Composable
fun CategoriesCheckBoxes(selected: String, itemList: List<String>, onChecked: (String) -> Unit) {
    if (selected.length == itemList.size) {
        val selectedItems = remember { selected.map { it == '1' }.toMutableStateList() }
        Row(
            Modifier
                .background(MaterialTheme.colors.primary, MaterialTheme.shapes.medium)
                .height(34.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            itemList.forEachIndexed { index, s ->
                Checkbox(
                    checked = selectedItems[index],
                    colors = CheckboxDefaults.colors(checkedColor = Color.Green),
                    onCheckedChange = {
                        selectedItems[index] = !selectedItems[index]
                        onChecked(String(selectedItems.map { if (it) '1' else '0' }.toCharArray()))
                    }
                )
                Text(text = itemList[index])
            }
            Spacer(modifier = Modifier.width(12.dp))
        }
    }
}

@Composable
fun SearchTag(onChanged: (String) -> Unit) {
    val findText = remember { mutableStateOf("") }
    val textEnable = remember { mutableStateOf(false) }
    IconButton(onClick = { textEnable.value = !textEnable.value }) {
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = "Search",
            tint = MaterialTheme.colors.onPrimary
        )
    }
    if (textEnable.value) {
        BasicTextField(
            value = findText.value,
            onValueChange = {
                findText.value = it
                onChanged(it)
            },
            textStyle = MaterialTheme.typography.body1.copy(color = MaterialTheme.colors.onPrimary),
            modifier = Modifier
                .widthIn(1.dp, Dp.Infinity)
                .background(MaterialTheme.colors.primaryVariant)
                .padding(4.dp)
//                .height(34.dp))
        )
    }
}