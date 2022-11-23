package dev.seriy0904.wallpapers.ui.customList

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.TextFieldDefaults.indicatorLine
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment.Companion.CenterStart
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.ImeAction
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
    filter: FiltersModel = FiltersModel(),
    openDrawer: () -> Unit
) {
    val searchModel by viewModel.customList.observeAsState()
    val customFilter by remember { mutableStateOf(filter) }
    Scaffold(topBar = {
        CustomListTopAppBar(
            filter = customFilter,
            openDrawer = openDrawer,
            onSearch = {
                if (it.isNotBlank()) {
                    customFilter.tags = it
                    viewModel.customListLoad(customFilter)
                }
            }
        )
    }) { scaffoldPadding ->
        Column(Modifier.padding(scaffoldPadding)) {
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
                                viewModel.customListLoad(customFilter)
                            }
                            .size(34.dp)
                            .padding(end = 6.dp)
                    )
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
                fontSize = 22.sp,
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

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CategoriesCheckBoxes(selected: String, itemList: List<String>, onChecked: (String) -> Unit) {
    if (selected.length == itemList.size) {
        val selectedItems = remember { selected.map { it == '1' }.toMutableStateList() }
        Row(
            Modifier
                .background(MaterialTheme.colors.primaryVariant, MaterialTheme.shapes.medium),
            verticalAlignment = CenterVertically
        ) {
            itemList.forEachIndexed { index, _ ->
                CompositionLocalProvider(LocalMinimumTouchTargetEnforcement provides false) {
                    Checkbox(
                        modifier = Modifier.padding(horizontal = 4.dp),
                        checked = selectedItems[index],
                        colors = CheckboxDefaults.colors(checkedColor = MaterialTheme.colors.onPrimary),
                        onCheckedChange = {
                            selectedItems[index] = !selectedItems[index]
                            onChecked(String(selectedItems.map { if (it) '1' else '0' }
                                .toCharArray()))
                        }
                    )
                }
                Text(
                    text = itemList[index],
                    color = MaterialTheme.colors.onPrimary,
                    fontSize = 22.sp
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CustomListTopAppBar(
    filter: FiltersModel,
    openDrawer: () -> Unit,
    onSearch: (String) -> Unit
) {
    var findText by remember { mutableStateOf("") }
    TopAppBar(
        title = {
            Surface(
                modifier = Modifier
                    .padding(vertical = 8.dp)
            ) {
                BasicTextField(
                    value = findText,
                    onValueChange = { findText = it },
                    textStyle = TextStyle(
                        color = MaterialTheme.colors.onPrimary,
                        fontSize = 18.sp
                    ),
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colors.primaryVariant)
                        .background(
                            TextFieldDefaults
                                .textFieldColors()
                                .backgroundColor(enabled = true).value,
                            MaterialTheme.shapes.small
                        )
                        .indicatorLine(
                            enabled = true,
                            isError = false,
                            interactionSource = remember { MutableInteractionSource() },
                            colors = TextFieldDefaults.textFieldColors()
                        ),
                    decorationBox = { innerTextField ->
                        Box(contentAlignment = CenterStart) {
                            if (findText.isEmpty()) {
                                Text("....", color = MaterialTheme.colors.primary)
                            } else {
                                innerTextField()
                            }
                        }
                    },
                    singleLine = true,
                    cursorBrush = SolidColor(MaterialTheme.colors.onPrimary),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions { onSearch(findText) }
                )
            }
        },
        navigationIcon = {
            IconButton(onClick = { openDrawer() }) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Open drawer menu"
                )
            }
        }, actions = {
            IconButton(onClick = {
                onSearch(findText)
            }) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Open searcher"
                )
            }
        },
        backgroundColor = MaterialTheme.colors.primaryVariant,
        contentColor = MaterialTheme.colors.onPrimary
    )
}