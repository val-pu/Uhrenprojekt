package leko.valmx.uhrenprojekt.widgets

class ToggleWidget : Widget() {
    override fun init() {
        title("Diverse Optionen")
        description("Diese Optionen kommen idealerweise irgendwann woanders unter")

        command("Geburtstagsnotifikationen", "")
        command("Temperatur", "temp", "Zeigt die aktuelle, maximale und minimale Temperatur.")
        command("Neustart", "reset", "Startet die Uhr neu.")
        command("Melodien", "ps", "Spielt alle möglichen Melodien ab.")
        command("Es ist", "ei", "Schalte \"Es ist\" in der Anzeige an/aus.")
    }
}