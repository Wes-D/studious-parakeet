package com.example.lifespark

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource

class CharacterViewModel : ViewModel() {
    var selectedRace = mutableStateOf("")
    var selectedArchetype = mutableStateOf("")
    var selectedGender = mutableStateOf("")
    var selectedAlignment = mutableStateOf("")
    var selectedTraits = mutableStateOf<List<String>>(emptyList())
    var backstoryPrompt = mutableStateOf("")
    var npcName = mutableStateOf("")
    var npcPortrait = mutableStateOf<Painter?>(null) // AI-generated portrait
    var npcBackstory = mutableStateOf("")
    var npcQuirk = mutableStateOf("")

    // Function to generate the NPC and populate relevant fields
    fun generateNPC() {
        npcName.value = generateRandomName(selectedRace.value)

        //npcPortrait.value = generateRandomPortrait(selectedRace.value)


        npcBackstory.value = generateAIBackstory(selectedRace.value, selectedArchetype.value, selectedTraits.value, backstoryPrompt.value)
        npcQuirk.value = generateRandomQuirk()
    }

    private fun generateRandomName(race: String): String {
        return when (race) {
            "Elf (Wood)" -> listOf("Aelrith", "Sylvanna", "Thalion", "Liora", "Eldrin", "Melian", "Calion",
                "Yavanna", "Faerion", "Lunara", "Elion", "Aerith", "Galadreth", "Valendra", "Eloria",
                "Thandor", "Caelrin", "Althara", "Ithilien", "Narilith").random()
            "Dwarf (Mountain)" -> listOf("Thrainor", "Durgrim", "Bramli", "Grimbald", "Malthar", "Bronvok", "Urik",
                "Kundrim", "Thrainok", "Borli", "Dwinthar", "Grondok", "Thrund", "Hildar", "Kladric",
                "Vorin", "Hargrim", "Galdur", "Vorrak", "Gorli").random()
            "Human" -> listOf("Roderic", "Anwen", "Cedric", "Isolde", "Bryndel", "Marwen", "Edric", "Aric",
                "Lyanna", "Tirion", "Valen", "Selwyn", "Eadric", "Brynden", "Cerdwyn", "Alaric", "Rowena",
                "Gareth", "Leofric", "Elara").random()
            "Orc" -> listOf("Grogash", "Thurg", "Bolgar", "Urthak", "Mugrok", "Krugath", "Rulgor", "Zogdar",
                "Thraz", "Garnok", "Urzag", "Krul", "Morgash", "Bruktar", "Thrugor", "Vorgak", "Zurgal",
                "Ghorz", "Roknar", "Throgg").random()
            "Halfling (Lightfoot)" -> listOf("Pipkin", "Brindle", "Winnifred", "Tansy", "Milo", "Roscoe", "Fennel",
                "Branrick", "Elderan", "Merri", "Fosco", "Tolly", "Andwise", "Perla", "Nilly", "Celandine",
                "Olo", "Sage", "Alder", "Poppy").random()
            "Dragonborn" -> listOf("Zarvok", "Tasseth", "Rhogar", "Azdreth", "Valasar", "Dornash", "Serthak",
                "Xalthor", "Torrath", "Mythror", "Drazan", "Kallak", "Tazul", "Krivar", "Vaxar", "Zernak",
                "Vormek", "Drazith", "Thaldrin", "Zarnash").random()
            "Gnome" -> listOf("Fizzwick", "Poggin", "Rundle", "Nyx", "Twidget", "Gimble", "Tink", "Wendel",
                "Nissa", "Vort", "Wrenna", "Dibble", "Tazra", "Whistle", "Merrix", "Zikra", "Twee",
                "Vimble", "Zox", "Blinka").random()
            "Tiefling" -> listOf("Xara", "Morthos", "Verin", "Zephra", "Ravnos", "Ezmara", "Jaxith", "Vendra",
                "Ryloth", "Zorva", "Sariel", "Thyra", "Nethros", "Kasra", "Zareth", "Kethra", "Xandros",
                "Lylith", "Vrax", "Zyara").random()
            "Firbolg" -> listOf("Brellin", "Haldir", "Tova", "Brynjar", "Oslaf", "Bergfinn", "Valka",
                "Thrain", "Freda", "Orn", "Torvald", "Hilda", "Sigrun", "Agnar", "Brynja", "Vigdis",
                "Ragnar", "Skjorn", "Yrsa", "Hrolf").random()
            "Loxodon" -> listOf("Durro", "Tharak", "Jorla", "Mazrak", "Khadra", "Urrok", "Zarno", "Vorda",
                "Thurnok", "Harra", "Barok", "Udra", "Korrak", "Zethra", "Narno", "Tharka", "Gurna",
                "Jorrok", "Mornak", "Vurrak").random()
            "Minotaur" -> listOf("Tauren", "Gorath", "Malkan", "Zyros", "Tharin", "Korvus", "Bulgor",
                "Kazra", "Rurik", "Thanos", "Hrak", "Zarnok", "Kranos", "Malkor", "Garok", "Korran",
                "Zaros", "Thrak", "Balnok", "Garnok").random()
            "Tabaxi" -> listOf("Sableclaw", "Windrunner", "Nightwhisker", "Shadefoot", "Proudswift",
                "Sunstrider", "Stormtail", "Moonshadow", "Whisperfang", "Duskmane", "Silentshadow",
                "Flickertail", "Quickpaw", "Firewhisker", "Starclaw", "Goldensight", "Shadeclaw",
                "Nightstep", "Swiftstride", "Brightfang").random()
            "Triton" -> listOf("Maris", "Delfar", "Korvath", "Zalara", "Pelagos", "Thalassa", "Valis",
                "Calder", "Oceana", "Nereus", "Xanthe", "Thalor", "Llyr", "Melora", "Ondar", "Zeressa",
                "Velara", "Pallas", "Narissa", "Zaltor").random()
            "Goliath" -> listOf("Thorin", "Bjorn", "Kaldar", "Vorn", "Thrag", "Jorak", "Baldor", "Kragar",
                "Vorna", "Harok", "Brakka", "Hralf", "Orka", "Thurin", "Bror", "Graltha", "Thrain",
                "Kragnar", "Thokar", "Vragar").random()
            "Kobold" -> listOf("Skritch", "Vark", "Zug", "Snizzle", "Trik", "Vik", "Krag", "Bral", "Skiv",
                "Tikkit", "Drez", "Snik", "Kriv", "Rix", "Flik", "Zeg", "Vash", "Kraz", "Brix", "Skrat").random()
            "Lizardfolk" -> listOf("Sissith", "Thakra", "Zekk", "Thess", "Krass", "Vizz", "Zethra", "Throk",
                "Jiss", "Vessik", "Razzik", "Thoss", "Zash", "Kress", "Vozz", "Thessik", "Zarrik", "Triss",
                "Kroth", "Veth").random()
            "Yuan-ti" -> listOf("Sissath", "Zarash", "Valthor", "Rizath", "Thessra", "Vessith", "Jezira",
                "Salthor", "Thalass", "Zizara", "Kezir", "Valiss", "Sithra", "Rassira", "Thazira",
                "Vissra", "Sithiss", "Jaleth", "Razira", "Thessith").random()
            "Satyr" -> listOf("Panora", "Thyxos", "Zephros", "Kassios", "Dionos", "Thyra", "Xanthos",
                "Phaedros", "Tyrannis", "Orpheon", "Kithros", "Doros", "Lyra", "Zephyra", "Thanos", "Xyros",
                "Tyros", "Zanthis", "Phoebos", "Kyra").random()
            "Warforged" -> listOf("Ironclad", "Steelbane", "Boltar", "Cogspark", "Gearthrottle", "Weldstrike",
                "Blastforge", "Grimjack", "Shockhammer", "Rustclaw", "Steamshear", "Ironstrike", "Boltgrip",
                "Rivetsnap", "Grimweld", "Metalsnap", "Chainlash", "Copperfist", "Hexgear", "Blitzsteel").random()
            "Leonin" -> listOf("Kharis", "Roath", "Thyros", "Xanath", "Myrro", "Thara", "Vornis", "Zoros",
                "Khaedra", "Vorok", "Mythra", "Zanir", "Thyra", "Zornis", "Varrok", "Xyra", "Kharos",
                "Marthos", "Xanthar", "Zaris").random()
            "Aarakocra" -> listOf("Skywhisper", "Stormclaw", "Windfeather", "Cloudrunner", "Breezewing",
                "Swiftclaw", "Stormtalon", "Skydancer", "Galeheart", "Featherstrike", "Windchaser", "Cloudstrike",
                "Thunderwing", "Breezeclaw", "Stormwing", "Skyhunter", "Falconstrike", "Windrider", "Stormeye",
                "Skyshadow").random()
            "Aasimar (Fallen)" -> listOf("Valthar", "Zethra", "Morvyn", "Razael", "Thessar", "Velkor", "Xerath",
                "Kaelix", "Zalath", "Drathos", "Malira", "Zereth", "Thariel", "Ravos", "Kalthos", "Daranor",
                "Saryth", "Valtren", "Korvash", "Xanara").random()
            "Aasimar (Protector)" -> listOf("Rathiel", "Auriel", "Seraphiel", "Galadren", "Elthir", "Calthar",
                "Thalaris", "Meliora", "Sariel", "Valethor", "Ariel", "Sereniel", "Isarith", "Galdriel",
                "Raviel", "Thandor", "Celithar", "Moriel", "Vareth", "Illyria").random()
            "Aasimar (Scourge)" -> listOf("Zalthar", "Valroth", "Thyren", "Ireth", "Maltheris", "Xeroth",
                "Caltris", "Vandor", "Therath", "Kariel", "Xalthra", "Drelas", "Rathor", "Zyraeth", "Kalthra",
                "Thyros", "Serathis", "Morvath", "Velkar", "Vandrath").random()
            "Bugbear" -> listOf("Grull", "Thrak", "Korgash", "Roknar", "Gorvak", "Brak", "Zog", "Kragthar",
                "Vorr", "Mogrok", "Thorg", "Rulg", "Bruthar", "Korrak", "Zarnak", "Thrag", "Gorash", "Kroth",
                "Zogthar", "Ruggok").random()
            "Centaur" -> listOf("Thyron", "Kalros", "Varath", "Xyra", "Cethros", "Mareth", "Phaedra", "Thalyra",
                "Valthos", "Khoren", "Thalira", "Xandar", "Vorna", "Caltros", "Lyra", "Phaeron", "Khoris",
                "Zanther", "Tarvos", "Xanthra").random()
            "Changling" -> listOf("Varis", "Caleth", "Zyra", "Thalan", "Ryn", "Xara", "Ithra", "Miran", "Velos",
                "Thyra", "Zaleth", "Varen", "Calira", "Thyren", "Korin", "Vira", "Thalith", "Xiren",
                "Velara", "Ithros").random()
            "Dwarf (Duergar)" -> listOf("Grumok", "Thargrim", "Durzok", "Kazrak", "Vordrak", "Hrothgar",
                "Bralnok", "Durnok", "Thorin", "Korgath", "Grundar", "Zurnok", "Vraknir", "Durgrim",
                "Brunok", "Throldar", "Mardrak", "Zorgrim", "Hulnok", "Kragthor").random()
            "Elf (Eladrin)" -> listOf("Faeril", "Lathal", "Celandor", "Aerith", "Thalindra", "Galathir",
                "Eryndor", "Calindor", "Sylvanna", "Valindor", "Laerith", "Melindra", "Caelir", "Thalandor",
                "Aelindra", "Ithariel", "Galendil", "Thalindra", "Lirion", "Aerandir").random()
            "Genasi (Air)" -> listOf("Zephyr", "Auran", "Gale", "Breeze", "Skyra", "Talon", "Whisp", "Thalor",
                "Aerol", "Valis", "Zorath", "Sylph", "Aelion", "Gareth", "Lyria", "Thora", "Vara", "Rithar",
                "Xylia", "Theron").random()
            "Genasi (Earth)" -> listOf("Granite", "Terrok", "Onyx", "Tharok", "Zarrok", "Malgran", "Vorn",
                "Kronis", "Durnak", "Ironclad", "Stoneheart", "Kaelok", "Thargrin", "Draxis", "Boulder",
                "Kalrok", "Gralthor", "Zornak", "Korran", "Magnar").random()
            "Genasi (Fire)" -> listOf("Ember", "Ignis", "Pyra", "Flare", "Thyra", "Blaze", "Zaleth", "Inferno",
                "Rynar", "Volcan", "Sear", "Cinder", "Flamara", "Torrick", "Ashryn", "Zaral", "Burnis",
                "Valkor", "Solas", "Theris").random()
            "Genasi (Water)" -> listOf("Aqua", "Maris", "Thalassa", "Rivara", "Zalara", "Nerin", "Oceara",
                "Siren", "Tethys", "Valara", "Llyra", "Riptide", "Coralyn", "Zephra", "Wavecrest", "Tidal",
                "Melara", "Zariel", "Valthys", "Thalor").random()
            "Githyanki" -> listOf("Zarok", "Thrynn", "Zalvor", "Corthis", "Varn", "Xalreth", "Tharnok", "Kaelith",
                "Morrok", "Zelith", "Thazrak", "Korath", "Velron", "Rathis", "Xathor", "Zalrak", "Tharvos",
                "Dornok", "Varak", "Rithar").random()
            "Githzerai" -> listOf("Rizoth", "Thaldir", "Vorthos", "Kaldrith", "Zethir", "Xarnath", "Vorak",
                "Zerith", "Thaldran", "Karith", "Raldor", "Zalnir", "Thralor", "Kaldron", "Varith", "Tharil",
                "Vornar", "Ralzir", "Tharnath", "Zaldrin").random()
            "Gnome (Svirfneblin)" -> listOf("Zibble", "Binkle", "Thok", "Trundle", "Snizz", "Grubble", "Wibble",
                "Drikk", "Zibble", "Fizzle", "Timmle", "Grizz", "Wendle", "Fennik", "Blek", "Rizzle",
                "Tobble", "Kibble", "Drizzle", "Fenn").random()
            "Half-Elf (Drow)" -> listOf("Zilra", "Valith", "Tharina", "Zaleth", "Kaelis", "Velora", "Drathis",
                "Zareth", "Thyra", "Xaleth", "Korith", "Valtha", "Zeryn", "Caltha", "Ralith", "Xarith",
                "Tharina", "Drethar", "Zilora", "Vornith").random()
            "Half-Elf (Eladrin)" -> listOf("Faelar", "Laelith", "Calthir", "Sylvara", "Lorin", "Galadren", "Melion",
                "Calindra", "Thalor", "Loritha", "Aerion", "Galandar", "Faelora", "Tharion", "Elandra", "Sylrin",
                "Caelindra", "Thalira", "Faerin", "Eldran").random()
            "Half-Elf (Moon)" -> listOf("Lunara", "Caelir", "Elion", "Thalia", "Lorin", "Sylvain", "Valara",
                "Elthir", "Celandor", "Galadren", "Ithil", "Aelira", "Thandor", "Loriel", "Sylvin", "Galindra",
                "Elthar", "Caelindra", "Tharion", "Elora").random()
            "Half-Elf (Sea)" -> listOf("Marith", "Thalindra", "Valara", "Oceara", "Rivara", "Zaril", "Tethis",
                "Llyra", "Meloria", "Zariel", "Aqualyn", "Valthys", "Nerin", "Thalora", "Sirena", "Coralyn",
                "Riptide", "Tidalyn", "Zephira", "Vallora").random()
            "Half-Elf (Shadar-Kai)" -> listOf("Zethra", "Varith", "Thalira", "Xariel", "Zorath", "Kaelra", "Velrin",
                "Thalora", "Zalrin", "Corith", "Valthir", "Xalora", "Zalith", "Tharros", "Kaleth", "Velara",
                "Draith", "Zaril", "Voranth", "Thaldrin").random()
            "Half-Elf (Sun)" -> listOf("Valion", "Aelar", "Galadren", "Thalion", "Celandor", "Aelith", "Sylvanna",
                "Thandor", "Galendil", "Caelir", "Elandra", "Valindra", "Thalorin", "Aerith", "Galrion",
                "Thalandor", "Calindor", "Melion", "Lorian", "Tharion").random()
            "Half-Elf (Wood)" -> listOf("Sylvar", "Thalrin", "Valorin", "Caelin", "Galrin", "Thalindra",
                "Elrin", "Caldor", "Lorin", "Melrin", "Valrin", "Thalor", "Galindra", "Elathor", "Calithor",
                "Thalria", "Galathrin", "Lorindra", "Valindra", "Thalindor").random()
            "Kalastar" -> listOf("Koryn", "Thyra", "Valen", "Zaleth", "Corynth", "Thalith", "Zora", "Xalira",
                "Korvin", "Valindra", "Raleth", "Therin", "Zorin", "Xaran", "Korlin", "Velath", "Dorith",
                "Zariel", "Valin", "Korith").random()
            "Kenku" -> listOf("Skreek", "Krazz", "Thrik", "Zikra", "Rikk", "Skraz", "Trikk", "Krath", "Vrik",
                "Skrith", "Zrax", "Klik", "Vriz", "Thrikka", "Krak", "Skreth", "Zith", "Krakk", "Vizz",
                "Skiv").random()
            "Tortle" -> listOf("Tork", "Shelldon", "Karrok", "Mardra", "Thurrok", "Vornok", "Kroth", "Terrak",
                "Zelrik", "Balor", "Shellar", "Thralok", "Korna", "Dorok", "Zarrok", "Mornak", "Krodd",
                "Tharok", "Vallor", "Karnak").random()
            "Dwarf (Hill)" -> listOf("Thrain", "Brunor", "Hilda", "Grunthor", "Dwalin", "Vorn", "Magda", "Faldor",
                "Borin", "Orla", "Gurn", "Thrud", "Haldir", "Kara", "Torin", "Grisla", "Vildar", "Durla",
                "Bror", "Helga").random()
            "Elf (Drow)" -> listOf("Zilvra", "Drathir", "Velkyn", "Lolthira", "Zaknor", "Xarath", "Malice",
                "Vlorna", "Zariel", "Ilyana", "Narath", "Vyrn", "Sarith", "Zarlith", "Erethra", "Xandril",
                "Vorath", "Thyra", "Ilvara", "Vethros").random()
            "Elf (Moon)" -> listOf("Lunara", "Elion", "Caelir", "Thalora", "Selana", "Calithar", "Sylvith",
                "Valaris", "Thandir", "Melara", "Galathor", "Ithariel", "Aelwyn", "Ravena", "Lorin",
                "Aeryn", "Elthir", "Valthor", "Caelindra", "Sylvaris").random()
            "Elf (Sea)" -> listOf("Maris", "Thalindra", "Oceara", "Nerin", "Valthys", "Tethys", "Zaril",
                "Meloria", "Aqualyn", "Rivara", "Coralyn", "Llyra", "Zalara", "Thalora", "Sirena", "Tidalyn",
                "Wavecrest", "Zephira", "Valora", "Aqualis").random()
            "Elf (Shadar-Kai)" -> listOf("Zethra", "Thyra", "Varith", "Xariel", "Vorath", "Velara", "Zalith",
                "Draith", "Valthar", "Xarith", "Korveth", "Thariel", "Zorath", "Kaelith", "Corith", "Velryn",
                "Zariel", "Thalora", "Valdryn", "Xandros").random()
            "Elf (Sun)" -> listOf("Calithor", "Thalion", "Valindor", "Aelion", "Galadren", "Sylvanna", "Faelora",
                "Thalor", "Elindor", "Caelindra", "Tharion", "Galathor", "Ithariel", "Lorian", "Aerith",
                "Valindra", "Melion", "Thalindra", "Celandor", "Sylvar").random()
            "Half-Orc" -> listOf("Gorruk", "Tharg", "Mogrin", "Brug", "Vorruk", "Zugrak", "Kroth", "Thargor",
                "Urak", "Mornak", "Zugath", "Gorr", "Thulok", "Vrak", "Korash", "Murog", "Grull", "Thogrin",
                "Brorak", "Vornak").random()
            "Halfling (Ghostwise)" -> listOf("Tali", "Wrenn", "Saris", "Pipkin", "Briala", "Finnis", "Korvin",
                "Mira", "Tolliver", "Nissa", "Serin", "Lyria", "Grenn", "Fennel", "Rosco", "Willow",
                "Taran", "Elder", "Hedra", "Merla").random()
            "Halfling (Lotusden)" -> listOf("Thistle", "Brindis", "Fern", "Moss", "Sorrel", "Willow",
                "Ashla", "Poppy", "Reed", "Clover", "Sage", "Renn", "Brindle", "Laurel", "Hazel", "Mira",
                "Thorn", "Briar", "Fenn", "Yarrow").random()
            "Halfling (Stout)" -> listOf("Branrick", "Milo", "Roscoe", "Tilly", "Fosco", "Beldar", "Perrin",
                "Tansy", "Andwise", "Nora", "Celandine", "Brim", "Eldo", "Tolly", "Farrah", "Dimple",
                "Merrick", "Gundig", "Rollo", "Poppy").random()

            else -> "Unknown"
        }
    }

    fun generateRandomPortrait(race: String): Int{
        // Placeholder for AI portrait generation based on race
        return R.drawable.alignment_true_neutral
    }

    private fun generateAIBackstory(race: String, archetype: String, traits: List<String>, backstoryPrompt: String): String {
        // Placeholder for AI backstory generation based on user input
        return "This is a detailed AI-generated backstory for a $race $archetype."
    }

    private fun generateRandomQuirk(): String {
        // Placeholder for generating a random quirk
        val quirks = listOf("Hums when nervous", "Collects shiny objects", "Speaks in riddles")
        return quirks.random()
    }
    
    // Function to reset NPC (Reroll)
    fun rerollNPC(lockName: Boolean, lockPortrait: Boolean) {
        if (!lockName) npcName.value = generateRandomName(selectedRace.value)
        //if (!lockPortrait) npcPortrait.value = generateRandomPortrait(selectedRace.value)
        npcBackstory.value = generateAIBackstory(selectedRace.value, selectedArchetype.value, selectedTraits.value, backstoryPrompt.value)
        npcQuirk.value = generateRandomQuirk()
    }

    // Add functions for saving, exporting, and editing as needed...
}

