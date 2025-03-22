package com.abramoviclaura.composeui.screen.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import coil.ImageLoader
import coil.compose.AsyncImage
import com.abramoviclaura.composeui.ui.theme.AndroidAnalysisUITheme
import com.abramoviclaura.composeui.ui.theme.PrimaryWhite
import com.abramoviclaura.composeui.ui.theme.Typography
import com.abramoviclaura.shared.R
import com.abramoviclaura.shared.screen.details.DetailsValues
import com.abramoviclaura.shared.screen.list.ListDataProvider

@Composable
fun DetailsScreen(
    itemId: Int,
    onBackClick: () -> Unit,
) {
    val item = ListDataProvider.getItem(itemId)
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .background(PrimaryWhite)
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            IconButton(
                onClick = onBackClick,
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_back),
                    contentDescription = stringResource(R.string.cd_back)
                )
            }

            Text(
                text = item.title,
                style = Typography.headlineSmall,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(vertical = dimensionResource(R.dimen.common_spacing_m))
            )
        }

        AsyncImage(
            model = item.imageUrl,
            contentDescription = null,
            imageLoader = ImageLoader(context),
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(id = R.dimen.details_image_height)),
            contentScale = ContentScale.Crop
        )

        Spacer(Modifier.height(dimensionResource(R.dimen.common_spacing_l)))

        Text(
            text = item.subtitle,
            style = Typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.common_spacing_l))
        )

        Spacer(Modifier.height(dimensionResource(R.dimen.common_spacing_m)))

        Text(
            text = DetailsValues.getLoremIpsumText(),
            modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.common_spacing_l))
        )
    }
}

@Preview
@Composable
private fun DetailsScreenPreview() = AndroidAnalysisUITheme {
    DetailsScreen(
        itemId = 2,
        onBackClick = {}
    )
}
