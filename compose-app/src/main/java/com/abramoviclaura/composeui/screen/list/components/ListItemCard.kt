package com.abramoviclaura.composeui.screen.list.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import coil.ImageLoader
import coil.compose.AsyncImage
import com.abramoviclaura.composeui.ui.theme.AndroidAnalysisUITheme
import com.abramoviclaura.composeui.ui.theme.FontSize
import com.abramoviclaura.composeui.ui.theme.PrimaryGreen
import com.abramoviclaura.composeui.ui.theme.PrimaryPink
import com.abramoviclaura.composeui.ui.theme.PrimaryRed
import com.abramoviclaura.composeui.ui.theme.PrimaryWhite
import com.abramoviclaura.shared.screen.list.ListItemCategory
import com.abramoviclaura.shared.screen.list.ListItemModel
import com.abramoviclaura.shared.screen.list.toStringRes
import com.abramoviclaura.shared.R as SharedR

@Composable
fun ListItemCard(
    item: ListItemModel,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Column(
        modifier = modifier
            .clip(RoundedCornerShape(dimensionResource(id = SharedR.dimen.common_spacing_m)))
            .border(
                width = dimensionResource(id = SharedR.dimen.common_border_width),
                color = Color.Black,
                shape = RoundedCornerShape(dimensionResource(id = SharedR.dimen.common_spacing_m))
            )
            .background(PrimaryWhite)
            .padding(vertical = dimensionResource(id = SharedR.dimen.common_spacing_m))
    ) {
        Row(modifier = Modifier.padding(horizontal = dimensionResource(id = SharedR.dimen.common_spacing_m))) {
            AsyncImage(
                model = item.imageUrl,
                contentDescription = null,
                imageLoader = ImageLoader(context),
                modifier = Modifier.size(dimensionResource(id = SharedR.dimen.list_item_card_image_size))
            )

            Spacer(modifier = Modifier.width(dimensionResource(id = SharedR.dimen.common_spacing_m)))

            Column {
                Row {
                    Text(
                        text = item.title,
                        fontSize = FontSize.l,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(1f)
                    )

                    Icon(
                        painter = painterResource(id = SharedR.drawable.ic_bookmark),
                        contentDescription = stringResource(id = SharedR.string.cd_bookmark)
                    )
                }

                Spacer(modifier = Modifier.height(dimensionResource(id = SharedR.dimen.common_spacing_xs)))

                Text(
                    text = item.subtitle,
                    fontSize = FontSize.m
                )

                Spacer(modifier = Modifier.height(dimensionResource(id = SharedR.dimen.common_spacing_s)))

                Row {
                    item.categories.forEach {
                        Text(
                            text = stringResource(id = it.toStringRes()).uppercase(),
                            fontSize = FontSize.s,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .background(PrimaryPink, RoundedCornerShape(dimensionResource(id = SharedR.dimen.common_spacing_m)))
                                .padding(horizontal = dimensionResource(id = SharedR.dimen.common_spacing_s))
                        )

                        Spacer(modifier = Modifier.width(dimensionResource(id = SharedR.dimen.common_spacing_xs)))
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(dimensionResource(id = SharedR.dimen.common_spacing_m)))

        Row(modifier = Modifier.padding(horizontal = dimensionResource(id = SharedR.dimen.common_spacing_m))) {
            CardButton(
                iconRes = SharedR.drawable.ic_check,
                textRes = SharedR.string.button_accept_text,
                buttonColors = ButtonDefaults.buttonColors(
                    containerColor = PrimaryGreen,
                    contentColor = PrimaryWhite
                ),
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(dimensionResource(id = SharedR.dimen.common_spacing_m)))

            CardButton(
                iconRes = SharedR.drawable.ic_close,
                textRes = SharedR.string.button_decline_text,
                buttonColors = ButtonDefaults.buttonColors(
                    containerColor = PrimaryRed,
                    contentColor = PrimaryWhite
                ),
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun CardButton(
    @DrawableRes iconRes: Int,
    @StringRes textRes: Int,
    modifier: Modifier = Modifier,
    buttonColors: ButtonColors = ButtonDefaults.buttonColors(),
) {
    Button(
        onClick = { /* action */ },
        colors = buttonColors,
        modifier = modifier
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(painter = painterResource(id = iconRes), contentDescription = null)
            Spacer(modifier = Modifier.width(dimensionResource(id = SharedR.dimen.common_spacing_s)))
            Text(text = stringResource(id = textRes))
        }
    }
}

@Preview
@Composable
private fun ListItemCardPreview() = AndroidAnalysisUITheme {
    val testItem = ListItemModel(
        title = "Title",
        subtitle = "Subtitle",
        categories = listOf(ListItemCategory.CATEGORY_1, ListItemCategory.CATEGORY_3),
        imageUrl = ""
    )

    ListItemCard(testItem)
}
