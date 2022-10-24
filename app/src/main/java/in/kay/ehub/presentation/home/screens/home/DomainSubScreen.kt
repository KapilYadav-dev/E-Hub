package `in`.kay.ehub.presentation.home.screens.home

import `in`.kay.ehub.R
import `in`.kay.ehub.domain.model.DomainSubOptions
import `in`.kay.ehub.presentation.home.viewModels.HomeViewModel
import `in`.kay.ehub.presentation.navigation.home.HomeNavRoutes
import `in`.kay.ehub.ui.theme.Typography
import `in`.kay.ehub.ui.theme.colorWhite
import android.widget.ImageView.ScaleType
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.navigation.NavHostController

@Composable
fun DomainSubScreen(
    viewModel: HomeViewModel,
    navController: NavHostController
) {

     val data = listOf<DomainSubOptions>(
         DomainSubOptions("Magazines",`in`.kay.ehub.R.drawable.magazins),
         DomainSubOptions("Resources",`in`.kay.ehub.R.drawable.resource),
         DomainSubOptions("Ask your query",`in`.kay.ehub.R.drawable.query),
         DomainSubOptions("Contact Mentor",`in`.kay.ehub.R.drawable.mentorcontact)
     )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFFFFF)) //0xEEEEEEEE
            .padding(horizontal = 20.dp)
    ){
        Row(
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
                .wrapContentHeight()
        ){
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "back-button",
                Modifier
                    .size(36.dp)
                    .align(Alignment.CenterVertically)
                    .clickable(true) {
                        navController.popBackStack()
                    })
            Text(text = domainsList()[viewModel.itemIndex.value].name,
                fontSize=27.sp,
                textAlign=TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
                    .align(Alignment.Top),
                style = Typography.h1,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis)

        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(16.dp)
        ) {
            itemsIndexed(data){index,item->
                DomainSubScreenCard(item = item,onItemClicked= {
                    if(index==0){

                    } else if(index==1){
                        navController.navigate(HomeNavRoutes.ResourceScreen.route)
                    } else if(index==2){

                    } else{

                    }
                }
                )
            }
        }

    }


}



@Composable
fun DomainSubScreenCard(item:DomainSubOptions,onItemClicked:()->Unit) {
    Card(
        modifier = Modifier
            .padding(vertical = 38.dp, horizontal = 8.dp)
            .size(160.dp)
            .clickable {
                       onItemClicked()
            },
        shape = RoundedCornerShape(16.dp),
        elevation = 8.dp,
        backgroundColor = colorWhite
    ){
        Column(modifier = Modifier.wrapContentWidth()){
            Image(painter = painterResource(id =item.img),
                contentDescription = "", contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth()
                .height(120.dp))
            Text(text = item.text,
                Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontSize = 16.sp)
        }

    }
}
