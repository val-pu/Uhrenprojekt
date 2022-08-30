package leko.valmx.uhrenprojekt.bundles

import java.util.*

class MultipleChoiceBundle(val title: String, val command: String, val choices: LinkedList<ChoiceItem>) : ContentBundle()

class ChoiceItem(val name: String, val description: String, val parameter: String)