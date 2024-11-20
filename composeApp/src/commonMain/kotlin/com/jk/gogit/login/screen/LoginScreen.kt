package com.jk.gogit.login.screen


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jk.gogit.components.Page
import com.jk.gogit.getIconResource
import com.mmk.kmpauth.firebase.github.GithubButtonUiContainer
import dev.gitlive.firebase.auth.FirebaseUser
import gogit_kmp.composeapp.generated.resources.Res
import gogit_kmp.composeapp.generated.resources.sign_in
import org.jetbrains.compose.resources.stringResource


@Composable
fun LoginScreen() {

    val oauth = listOf(
        "user",
        "user:follow",
        "admin:org",
        "admin:repo_hook",
        "admin:org_hook",
        "repo",
        "public_repo",
        "gist",
        "notifications"
    )
    Page {
        Column(
            verticalArrangement = Arrangement.spacedBy(
                space = 8.dp,
                alignment = Alignment.Top
            ),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(horizontal = 8.dp),
        ) {
            Image(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .size(148.dp),
                contentDescription = "App Icon",
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground),
                painter = getIconResource(),
                alignment = Alignment.Center,
            )
            GithubButtonUiContainer(
                linkAccount = false,
                onResult = {
                    if (it.isSuccess) {
                        val f: FirebaseUser? = it.getOrThrow()
                        val profileData = f?.photoURL


                    }
                },
                requestScopes = oauth,
                modifier = Modifier
                    .padding(vertical = 8.dp)
            ) {

                //Any View, you just need to delegate child view's click to this UI Container's click method
                androidx.compose.material.Button(onClick = { this.onClick() }) {
                    Text(text = stringResource(Res.string.sign_in))
                }
            }

            val annotatedString = buildAnnotatedString {
                pushStyle(style = ParagraphStyle(textAlign = TextAlign.Center))
                append("Your login indicates acceptance of our ")
                pushStringAnnotation(
                    tag = "PrivacyPolicy",
                    annotation = "https://jk2pr.github.io"
                ) // Replace the URL with your actual Privacy Policy URL
                withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline)) {
                    append("\n")
                    append("Privacy Policy")
                }
                pop()
            }


            ClickableText(
                modifier = Modifier
                    // .widthIn(max = 250.dp)
                    .align(Alignment.CenterHorizontally),
                text = annotatedString,
                onClick = { offset ->
                    annotatedString.getStringAnnotations(
                        tag = "PrivacyPolicy",
                        start = offset,
                        end = offset
                    )
                        .firstOrNull()?.let { annotation ->
                            //  val uri = Uri.parse(annotation.item)
                            //  val intent = Intent(Intent.ACTION_VIEW, uri)
                            // startActivity(activity, intent, null)
                        }
                },
                style = LocalTextStyle.current.copy(
                    color = MaterialTheme.colorScheme.outline,
                    fontSize = 14.sp
                ),
                maxLines = 2
            )


            // Other UI elements

        }

    }
}

