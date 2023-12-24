package ui.main.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import core.util.Country
import core.util.countryAlpha2CodeFlagPathMap
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import core.util.countries as countriesList

@OptIn(ExperimentalResourceApi::class)
@Composable
fun LocalePickerDialog(
    countries:List<Country> = countriesList,
    defaultSelectedCountry: Country = countriesList.first(),
    pickedCountry: (Country) -> Unit,
    dialogSearch: Boolean = true,
    dialogRounded: Int = 12,
    onDismissRequest: () -> Unit,
    ) {
        var isPickCountry by remember { mutableStateOf(defaultSelectedCountry) }
       
        var searchValue by remember { mutableStateOf("") }
        
                Dialog(
                    onDismissRequest = onDismissRequest,
                ) {
                    Card (
                      modifier=  Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.85f),
                      elevation=CardDefaults.elevatedCardElevation(4.dp),
                        shape = RoundedCornerShape(dialogRounded.dp)
                    ) {
                        Column {
                            if (dialogSearch) {
                                searchValue = dialogSearchView()
                            }
                            Divider(thickness=1.dp)
                          LazyColumn {
                                items(
                                    (if (searchValue.isEmpty()) {
                                        countries
                                    } else {
                                        countries.filter { it.name== searchValue}
                                    })
                                ) { c ->
                                    Row(
                                        Modifier
                                            .padding(
                                                horizontal = 18.dp,
                                                vertical = 18.dp
                                            )
                                            .clickable {
                                                pickedCountry(c)
                                                isPickCountry = c
                                                onDismissRequest()
                                            }) {
                                        Image(
                                            painter = painterResource(countryAlpha2CodeFlagPathMap[c.alpha2Code]!!), contentDescription = null
                                        )
                                        Text(
                                            c.name,
                                            Modifier.padding(horizontal = 18.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
    }

    @Composable
    private fun dialogSearchView(): String {
        var searchValue by remember { mutableStateOf("") }
        Row {
            SearchField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                value = searchValue,
                onValueChange = {
                    searchValue = it
                },
                fontSize = 14.sp,
                hint = "Search ...",
                textAlign = TextAlign.Start,
            )
        }
        return searchValue
    }

    @Composable
    private fun SearchField(
        modifier: Modifier = Modifier,
        value: String,
        onValueChange: (String) -> Unit,
        hint: String = "",
        fontSize: TextUnit = 16.sp,
        textAlign: TextAlign = TextAlign.Center
    ) {
        Box(
            modifier = modifier
                .background(
                    color = Color.White.copy(alpha = 0.1f)
                )
        ) {
            TextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = value,
                onValueChange = onValueChange,
                textStyle = LocalTextStyle.current.copy(
                    textAlign = textAlign,
                    fontSize = fontSize
                ),
                singleLine = true,
                leadingIcon = {
                    Icon(
                        Icons.Default.Search,
                        contentDescription = null,
                        tint = Color.Black.copy(0.2f)
                    )
                },
                colors = TextFieldDefaults.colors(
                    //TODO
//                    backgroundColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
            if (value.isEmpty()) {
                Text(
                    text = hint,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray,
                    modifier = Modifier.then(
                        Modifier
                            .align(Alignment.CenterStart)
                            .padding(start = 52.dp)
                    )
                )
            }
        }
    }