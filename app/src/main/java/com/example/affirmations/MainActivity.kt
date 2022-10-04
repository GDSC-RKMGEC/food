/*
 * Copyright (C) 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.affirmations
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.affirmations.data.Datasource
import com.example.affirmations.model.Affirmation
import com.example.affirmations.ui.theme.AffirmationsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AffirmationApp()
        }
    }
}
@Composable
fun SearchBar(modifier: Modifier = Modifier.padding(1.dp)) {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    return OutlinedTextField(
        modifier = Modifier.fillMaxWidth().padding(12.dp),
        value = text,
        shape = RoundedCornerShape(20.dp),
        leadingIcon = { Icon(imageVector = Icons.Default.Search, tint = Color.Black, contentDescription = "SearchIcon") },
        //trailingIcon = { Icon(imageVector = Icons.Default.Add, contentDescription = null) },
        onValueChange = {
            text = it
        },
        placeholder = { Text(text = "Resturant name or a dish",color = Color.Black) },

        )
}
@Preview
@Composable
fun AffirmationApp() {
    SearchBar(modifier = Modifier.fillMaxWidth())
    Spacer(modifier = Modifier.height(10.dp))
    AffirmationsTheme {
        Scaffold(content = {
            AffirmationList(affirmationList = Datasource().loadAffirmations())
        })
    }
}



@Composable
fun AffirmationCard(affirmation: Affirmation, modifier: Modifier = Modifier){

    Card(modifier = modifier.padding(15.dp), elevation = 4.dp,shape = RoundedCornerShape(20.dp)) {

        Column {
            Image(painter = painterResource(affirmation.imageResourceId),
            contentDescription = stringResource(affirmation.stringResourceId),
            modifier = Modifier
                .fillMaxWidth()
                .height(154.dp), contentScale = ContentScale.Crop)
            Row{
                Column{
                    Text(
                        text = stringResource(affirmation.stringResourceId),
                        fontWeight = FontWeight.W700,
                        fontSize= 26.sp,
                        modifier = Modifier.padding(top = 8.dp, start = 4.dp,),
                        style = MaterialTheme.typography.h6
                    )
                    Text(
                        text = "Tea, Milk Shake, fast food,..",
                        modifier = Modifier.padding(bottom = 4.dp, start = 4.dp, end = 4.dp),
                        fontWeight = FontWeight.Normal,
                        style = MaterialTheme.typography.h6
                    )
                }
                Column{
                    Button(

                        onClick = {},
                        colors = ButtonDefaults.textButtonColors(
                            backgroundColor = colorResource(R.color.teal_700),
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier

                            .padding(start = 15.dp, top = 5.dp)


                    ) {

                            Row{
                                Text("4.1", fontSize = 20.sp)
                                Spacer(Modifier.width(5.dp))
                                Image(
                                    painter = painterResource(id = R.drawable.star_icon),

                                    contentDescription = "Image",

                                    modifier = Modifier
                                        .height(25.dp)
                                        .width(25.dp).padding(start=0.dp)

                                )
                            }



                    }
                    Text(
                        text = "₹200 for one",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.padding(start=1.dp),
                        style = MaterialTheme.typography.h6
                    )
                }

            }
        }
    }
}


@Composable
private fun AffirmationCardPreview(){
    AffirmationCard(Affirmation(R.string.affirmation1, R.drawable.image1))
}

@Composable
private fun AffirmationList(affirmationList: List<Affirmation>, modifier: Modifier = Modifier){

        Column(Modifier.padding(top = 26.dp)) {
            SearchBar()
            LazyColumn(Modifier.padding(top = 10.dp)) {

                items(affirmationList) { affirmation ->
                    AffirmationCard(affirmation)
                }
        }

        }


}