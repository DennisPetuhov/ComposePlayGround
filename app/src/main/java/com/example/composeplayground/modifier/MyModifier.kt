package com.example.composeplayground.modifier


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode.Companion.Color
import androidx.compose.ui.unit.dp

//https://getstream.io/blog/designing-effective-compose/



//1. Apply Modifiers to the top-most layout of the component
//2. Use single parameter for Modifier
//3. Avoid reusing Modifiers across components


//It's better to use a slot-based approach to give users the flexibility to customize internal content.
//For instance, rather than adding multiple Modifier parameters, you can define a slot that allows users
//to provide custom content while still maintaining a single Modifier for external customization.
@Composable
fun RoundedButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    content: @Composable RowScope.() -> Unit
) {
    Button(
        modifier = modifier.clip(RoundedCornerShape(32.dp)),
        onClick = onClick
    ) {
        content()
    }
}

//3. Avoid reusing Modifiers across components
// bad practice
@Composable
fun MyButtons(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Column(modifier = modifier) {
        Button(
            modifier = modifier,
            onClick = onClick
        ) {
            Text(
                modifier = modifier.padding(10.dp),
                text = "Rounded"
            )
        }

        Button(
            modifier = modifier,
            onClick = onClick
        ) {
            Text(
                modifier = modifier.padding(10.dp),
                text = "Not Rounded"
            )
        }
    }
}
// good practice


//4. Use Modifier Chains ThoughtfullyWhy:
//The order of Modifier functions is critical, as each function alters the result of the previous one in the chain.
//How:
//Define the sequence of Modifier calls carefully, starting with layout-modifying functions (e.g., fillMaxSize, padding) and ending with rendering-related functions (e.g., background, clip).
//For example:
//Modifier
//.fillMaxWidth()  // Define size first
//.padding(16.dp)  // Add spacing
//.background(Color.Blue)  // Then background
//.clip(RoundedCornerShape(8.dp))  // Finally clip

//5. Favor Clarity and Predictability
//Why:
//Users of a composable should understand how their Modifier customizations will affect the component without ambiguity.
//How:
//Provide clear documentation on where and how the Modifier will be applied.
//Avoid internal changes to the Modifier that could override the user's expectations.


//Examples of Good Modifier Practices
//Slot-Based Flexible Component
//Allow users to inject their own content while keeping the Modifier focused on the top-level behavior:
@Composable
fun CardWithHeader(
    modifier: Modifier = Modifier,
    headerContent: @Composable () -> Unit,
    bodyContent: @Composable () -> Unit
) {
    Column(
        modifier = modifier
            .padding(16.dp)
            .background(androidx.compose.ui.graphics.Color.Green)
            .clip(RoundedCornerShape(12.dp))
    ) {
        headerContent()
        Spacer(modifier = Modifier.height(8.dp))
        bodyContent()
    }
}


//Ensure the Modifier chain starts with layout-related functions (fillMaxWidth, widthIn) and ends
//with decorative ones (clip, background, padding).

//Key Takeaways
//Modifiers should only decorate the top-most layout node of a composable to ensure predictability.
//Use a single modifier parameter for composables to avoid unnecessary complexity.
//Do not reuse modifiers across multiple child elements; instead, create scoped modifiers for each.
//Be mindful of the modifier chain order to achieve the intended layout and visual results.
//Ensure clarity and transparency in how modifiers will impact your composable.
//By adhering to these best practices, you can design reusable and predictable APIs,
//benefiting both end users and your development team. Let me know if you'd like additional elaboration or examples!