/*
* FancyBottomBar created by Ji Sungbin
* FancyBottomBar license is under the MIT license.
* Please see: https://github.com/jisungbin/FancyBottomBar/blob/master/LICENSE
*/

package team.talibity.app.fancybottombar

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Immutable

@Immutable
data class FancyItem(val title: String = "", @DrawableRes val icon: Int? = null, val id: Int = 0)
