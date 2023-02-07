package com.example.dummysport.app.laliga.util

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.dummysport.app.laliga.model.ClubModel
import com.example.dummysport.ui.theme.Purple500

@Composable
fun <T> Spinner(
    modifier: Modifier = Modifier,
    dropDownModifier: Modifier = Modifier,
    items: List<T>,
    selectedItem: T,
    onItemSelected: (T) -> Unit,
    selectedItemFactory: @Composable (Modifier, T) -> Unit,
    dropdownItemFactory: @Composable (T, Int) -> Unit,
) {
    val expanded = remember { mutableStateOf(false) }

    Box(modifier = modifier.wrapContentSize(Alignment.BottomEnd)) {
        selectedItemFactory(
            Modifier
                .clickable { expanded.value = true },
            selectedItem
        )

        DropdownMenu(
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false },
            modifier = dropDownModifier
        ) {
            items.forEachIndexed { index, element ->
                DropdownMenuItem(onClick = {
                    onItemSelected(items[index])
                    expanded.value = false
                }) {
                    dropdownItemFactory(element, index)
                }
            }
        }
    }
}

@Composable
fun QuantityMenuSpinner(
    modifier: Modifier = Modifier,
    availableQuantities: List<ClubModel>,
    selectedItem: ClubModel,
    onItemSelected: (ClubModel) -> Unit
) {
    Spinner(
        modifier = modifier.wrapContentSize().background(Purple500),
        dropDownModifier = Modifier.wrapContentSize(),
        items = availableQuantities,
        selectedItem = selectedItem,
        onItemSelected = onItemSelected,
        selectedItemFactory = { modifier1, item ->
            Row(
                modifier = modifier1
                    .padding(8.dp)
                    .height(42.dp)
            ) {
                Text(text = item.name, color = Color.White)
            }
        },
        dropdownItemFactory = { item, _ ->
            Text(text = item.name)
        }
    )
}