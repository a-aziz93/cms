package ui.i18n

data class CountryCode(
    var countryCode: String,
    val countryPhoneCode: String = "",
    val countryName: String = "",
    val flagResID: Int = 0
)

fun getCountryFlag(countryCode: String): String {
    return when (countryCode) {
        "ad" -> "drawable/flags/flag_andorra.png"
        "ae" -> "drawable/flags/flag_uae.png"
        "af" -> "drawable/flags/flag_afghanistan.png"
        "ag" -> "drawable/flags/flag_antigua_and_barbuda.png"
        "ai" -> "drawable/flags/flag_anguilla.png"
        "al" -> "drawable/flags/flag_albania.png"
        "am" -> "drawable/flags/flag_armenia.png"
        "ao" -> "drawable/flags/flag_angola.png"
        "aq" -> "drawable/flags/flag_antarctica.png"
        "ar" -> "drawable/flags/flag_argentina.png"
        "as" -> "drawable/flags/flag_american_samoa.png"
        "at" -> "drawable/flags/flag_austria.png"
        "au" -> "drawable/flags/flag_australia.png"
        "aw" -> "drawable/flags/flag_aruba.png"
        "ax" -> "drawable/flags/flag_aland.png"
        "az" -> "drawable/flags/flag_azerbaijan.png"
        "ba" -> "drawable/flags/flag_bosnia.png"
        "bb" -> "drawable/flags/flag_barbados.png"
        "bd" -> "drawable/flags/flag_bangladesh.png"
        "be" -> "drawable/flags/flag_belgium.png"
        "bf" -> "drawable/flags/flag_burkina_faso.png"
        "bg" -> "drawable/flags/flag_bulgaria.png"
        "bh" -> "drawable/flags/flag_bahrain.png"
        "bi" -> "drawable/flags/flag_burundi.png"
        "bj" -> "drawable/flags/flag_benin.png"
        "bl" -> "drawable/flags/flag_saint_barthelemy.png" // custom
        "bm" -> "drawable/flags/flag_bermuda.png"
        "bn" -> "drawable/flags/flag_brunei.png"
        "bo" -> "drawable/flags/flag_bolivia.png"
        "br" -> "drawable/flags/flag_brazil.png"
        "bs" -> "drawable/flags/flag_bahamas.png"
        "bt" -> "drawable/flags/flag_bhutan.png"
        "bw" -> "drawable/flags/flag_botswana.png"
        "by" -> "drawable/flags/flag_belarus.png"
        "bz" -> "drawable/flags/flag_belize.png"
        "ca" -> "drawable/flags/flag_canada.png"
        "cc" -> "drawable/flags/flag_cocos.png" // custom
        "cd" -> "drawable/flags/flag_democratic_republic_of_the_congo.png"
        "cf" -> "drawable/flags/flag_central_african_republic.png"
        "cg" -> "drawable/flags/flag_republic_of_the_congo.png"
        "ch" -> "drawable/flags/flag_switzerland.png"
        "ci" -> "drawable/flags/flag_cote_divoire.png"
        "ck" -> "drawable/flags/flag_cook_islands.png"
        "cl" -> "drawable/flags/flag_chile.png"
        "cm" -> "drawable/flags/flag_cameroon.png"
        "cn" -> "drawable/flags/flag_china.png"
        "co" -> "drawable/flags/flag_colombia.png"
        "cr" -> "drawable/flags/flag_costa_rica.png"
        "cu" -> "drawable/flags/flag_cuba.png"
        "cv" -> "drawable/flags/flag_cape_verde.png"
        "cw" -> "drawable/flags/flag_curacao.png"
        "cx" -> "drawable/flags/flag_christmas_island.png"
        "cy" -> "drawable/flags/flag_cyprus.png"
        "cz" -> "drawable/flags/flag_czech_republic.png"
        "de" -> "drawable/flags/flag_germany.png"
        "dj" -> "drawable/flags/flag_djibouti.png"
        "dk" -> "drawable/flags/flag_denmark.png"
        "dm" -> "drawable/flags/flag_dominica.png"
        "do" -> "drawable/flags/flag_dominican_republic.png"
        "dz" -> "drawable/flags/flag_algeria.png"
        "ec" -> "drawable/flags/flag_ecuador.png"
        "ee" -> "drawable/flags/flag_estonia.png"
        "eg" -> "drawable/flags/flag_egypt.png"
        "er" -> "drawable/flags/flag_eritrea.png"
        "es" -> "drawable/flags/flag_spain.png"
        "et" -> "drawable/flags/flag_ethiopia.png"
        "fi" -> "drawable/flags/flag_finland.png"
        "fj" -> "drawable/flags/flag_fiji.png"
        "fk" -> "drawable/flags/flag_falkland_islands.png"
        "fm" -> "drawable/flags/flag_micronesia.png"
        "fo" -> "drawable/flags/flag_faroe_islands.png"
        "fr" -> "drawable/flags/flag_france.png"
        "ga" -> "drawable/flags/flag_gabon.png"
        "gb" -> "drawable/flags/flag_united_kingdom.png"
        "gd" -> "drawable/flags/flag_grenada.png"
        "ge" -> "drawable/flags/flag_georgia.png"
        "gf" -> "drawable/flags/flag_guyane.png"
        "gg" -> "drawable/flags/flag_guernsey.png"
        "gh" -> "drawable/flags/flag_ghana.png"
        "gi" -> "drawable/flags/flag_gibraltar.png"
        "gl" -> "drawable/flags/flag_greenland.png"
        "gm" -> "drawable/flags/flag_gambia.png"
        "gn" -> "drawable/flags/flag_guinea.png"
        "gp" -> "drawable/flags/flag_guadeloupe.png"
        "gq" -> "drawable/flags/flag_equatorial_guinea.png"
        "gr" -> "drawable/flags/flag_greece.png"
        "gt" -> "drawable/flags/flag_guatemala.png"
        "gu" -> "drawable/flags/flag_guam.png"
        "gw" -> "drawable/flags/flag_guinea_bissau.png"
        "gy" -> "drawable/flags/flag_guyana.png"
        "hk" -> "drawable/flags/flag_hong_kong.png"
        "hn" -> "drawable/flags/flag_honduras.png"
        "hr" -> "drawable/flags/flag_croatia.png"
        "ht" -> "drawable/flags/flag_haiti.png"
        "hu" -> "drawable/flags/flag_hungary.png"
        "id" -> "drawable/flags/flag_indonesia.png"
        "ie" -> "drawable/flags/flag_ireland.png"
        "im" -> "drawable/flags/flag_isleof_man.png" // custom
        "is" -> "drawable/flags/flag_iceland.png"
        "in" -> "drawable/flags/flag_india.png"
        "io" -> "drawable/flags/flag_british_indian_ocean_territory.png"
        "iq" -> "drawable/flags/flag_iraq_new.png"
        "ir" -> "drawable/flags/flag_iran.png"
        "it" -> "drawable/flags/flag_italy.png"
        "je" -> "drawable/flags/flag_jersey.png"
        "jm" -> "drawable/flags/flag_jamaica.png"
        "jo" -> "drawable/flags/flag_jordan.png"
        "jp" -> "drawable/flags/flag_japan.png"
        "ke" -> "drawable/flags/flag_kenya.png"
        "kg" -> "drawable/flags/flag_kyrgyzstan.png"
        "kh" -> "drawable/flags/flag_cambodia.png"
        "ki" -> "drawable/flags/flag_kiribati.png"
        "km" -> "drawable/flags/flag_comoros.png"
        "kn" -> "drawable/flags/flag_saint_kitts_and_nevis.png"
        "kp" -> "drawable/flags/flag_north_korea.png"
        "kr" -> "drawable/flags/flag_south_korea.png"
        "kw" -> "drawable/flags/flag_kuwait.png"
        "ky" -> "drawable/flags/flag_cayman_islands.png"
        "kz" -> "drawable/flags/flag_kazakhstan.png"
        "la" -> "drawable/flags/flag_laos.png"
        "lb" -> "drawable/flags/flag_lebanon.png"
        "lc" -> "drawable/flags/flag_saint_lucia.png"
        "li" -> "drawable/flags/flag_liechtenstein.png"
        "lk" -> "drawable/flags/flag_sri_lanka.png"
        "lr" -> "drawable/flags/flag_liberia.png"
        "ls" -> "drawable/flags/flag_lesotho.png"
        "lt" -> "drawable/flags/flag_lithuania.png"
        "lu" -> "drawable/flags/flag_luxembourg.png"
        "lv" -> "drawable/flags/flag_latvia.png"
        "ly" -> "drawable/flags/flag_libya.png"
        "ma" -> "drawable/flags/flag_morocco.png"
        "mc" -> "drawable/flags/flag_monaco.png"
        "md" -> "drawable/flags/flag_moldova.png"
        "me" -> "drawable/flags/flag_of_montenegro.png" // custom
        "mf" -> "drawable/flags/flag_saint_martin.png"
        "mg" -> "drawable/flags/flag_madagascar.png"
        "mh" -> "drawable/flags/flag_marshall_islands.png"
        "mk" -> "drawable/flags/flag_macedonia.png"
        "ml" -> "drawable/flags/flag_mali.png"
        "mm" -> "drawable/flags/flag_myanmar.png"
        "mn" -> "drawable/flags/flag_mongolia.png"
        "mo" -> "drawable/flags/flag_macao.png"
        "mp" -> "drawable/flags/flag_northern_mariana_islands.png"
        "mq" -> "drawable/flags/flag_martinique.png"
        "mr" -> "drawable/flags/flag_mauritania.png"
        "ms" -> "drawable/flags/flag_montserrat.png"
        "mt" -> "drawable/flags/flag_malta.png"
        "mu" -> "drawable/flags/flag_mauritius.png"
        "mv" -> "drawable/flags/flag_maldives.png"
        "mw" -> "drawable/flags/flag_malawi.png"
        "mx" -> "drawable/flags/flag_mexico.png"
        "my" -> "drawable/flags/flag_malaysia.png"
        "mz" -> "drawable/flags/flag_mozambique.png"
        "na" -> "drawable/flags/flag_namibia.png"
        "nc" -> "drawable/flags/flag_new_caledonia.png" // custom
        "ne" -> "drawable/flags/flag_niger.png"
        "nf" -> "drawable/flags/flag_norfolk_island.png"
        "ng" -> "drawable/flags/flag_nigeria.png"
        "ni" -> "drawable/flags/flag_nicaragua.png"
        "nl" -> "drawable/flags/flag_netherlands.png"
        "no" -> "drawable/flags/flag_norway.png"
        "np" -> "drawable/flags/flag_nepal.png"
        "nr" -> "drawable/flags/flag_nauru.png"
        "nu" -> "drawable/flags/flag_niue.png"
        "nz" -> "drawable/flags/flag_new_zealand.png"
        "om" -> "drawable/flags/flag_oman.png"
        "pa" -> "drawable/flags/flag_panama.png"
        "pe" -> "drawable/flags/flag_peru.png"
        "pf" -> "drawable/flags/flag_french_polynesia.png"
        "pg" -> "drawable/flags/flag_papua_new_guinea.png"
        "ph" -> "drawable/flags/flag_philippines.png"
        "pk" -> "drawable/flags/flag_pakistan.png"
        "pl" -> "drawable/flags/flag_poland.png"
        "pm" -> "drawable/flags/flag_saint_pierre.png"
        "pn" -> "drawable/flags/flag_pitcairn_islands.png"
        "pr" -> "drawable/flags/flag_puerto_rico.png"
        "ps" -> "drawable/flags/flag_palestine.png"
        "pt" -> "drawable/flags/flag_portugal.png"
        "pw" -> "drawable/flags/flag_palau.png"
        "py" -> "drawable/flags/flag_paraguay.png"
        "qa" -> "drawable/flags/flag_qatar.png"
        "re" -> "drawable/flags/flag_martinique.png" // no exact flag found
        "ro" -> "drawable/flags/flag_romania.png"
        "rs" -> "drawable/flags/flag_serbia.png" // custom
        "ru" -> "drawable/flags/flag_russian_federation.png"
        "rw" -> "drawable/flags/flag_rwanda.png"
        "sa" -> "drawable/flags/flag_saudi_arabia.png"
        "sb" -> "drawable/flags/flag_soloman_islands.png"
        "sc" -> "drawable/flags/flag_seychelles.png"
        "sd" -> "drawable/flags/flag_sudan.png"
        "se" -> "drawable/flags/flag_sweden.png"
        "sg" -> "drawable/flags/flag_singapore.png"
        "sh" -> "drawable/flags/flag_saint_helena.png" // custom
        "si" -> "drawable/flags/flag_slovenia.png"
        "sk" -> "drawable/flags/flag_slovakia.png"
        "sl" -> "drawable/flags/flag_sierra_leone.png"
        "sm" -> "drawable/flags/flag_san_marino.png"
        "sn" -> "drawable/flags/flag_senegal.png"
        "so" -> "drawable/flags/flag_somalia.png"
        "sr" -> "drawable/flags/flag_suriname.png"
        "ss" -> "drawable/flags/flag_south_sudan.png"
        "st" -> "drawable/flags/flag_sao_tome_and_principe.png"
        "sv" -> "drawable/flags/flag_el_salvador.png"
        "sx" -> "drawable/flags/flag_sint_maarten.png"
        "sy" -> "drawable/flags/flag_syria.png"
        "sz" -> "drawable/flags/flag_swaziland.png"
        "tc" -> "drawable/flags/flag_turks_and_caicos_islands.png"
        "td" -> "drawable/flags/flag_chad.png"
        "tg" -> "drawable/flags/flag_togo.png"
        "th" -> "drawable/flags/flag_thailand.png"
        "tj" -> "drawable/flags/flag_tajikistan.png"
        "tk" -> "drawable/flags/flag_tokelau.png" // custom
        "tl" -> "drawable/flags/flag_timor_leste.png"
        "tm" -> "drawable/flags/flag_turkmenistan.png"
        "tn" -> "drawable/flags/flag_tunisia.png"
        "to" -> "drawable/flags/flag_tonga.png"
        "tr" -> "drawable/flags/flag_turkey.png"
        "tt" -> "drawable/flags/flag_trinidad_and_tobago.png"
        "tv" -> "drawable/flags/flag_tuvalu.png"
        "tw" -> "drawable/flags/flag_taiwan.png"
        "tz" -> "drawable/flags/flag_tanzania.png"
        "ua" -> "drawable/flags/flag_ukraine.png"
        "ug" -> "drawable/flags/flag_uganda.png"
        "us" -> "drawable/flags/flag_united_states_of_america.png"
        "uy" -> "drawable/flags/flag_uruguay.png"
        "uz" -> "drawable/flags/flag_uzbekistan.png"
        "va" -> "drawable/flags/flag_vatican_city.png"
        "vc" -> "drawable/flags/flag_saint_vicent_and_the_grenadines.png"
        "ve" -> "drawable/flags/flag_venezuela.png"
        "vg" -> "drawable/flags/flag_british_virgin_islands.png"
        "vi" -> "drawable/flags/flag_us_virgin_islands.png"
        "vn" -> "drawable/flags/flag_vietnam.png"
        "vu" -> "drawable/flags/flag_vanuatu.png"
        "wf" -> "drawable/flags/flag_wallis_and_futuna.png"
        "ws" -> "drawable/flags/flag_samoa.png"
        "xk" -> "drawable/flags/flag_kosovo.png"
        "ye" -> "drawable/flags/flag_yemen.png"
        "yt" -> "drawable/flags/flag_martinique.png" // no exact flag found
        "za" -> "drawable/flags/flag_south_africa.png"
        "zm" -> "drawable/flags/flag_zambia.png"
        "zw" -> "drawable/flags/flag_zimbabwe.png"
        else -> "drawable/flags/flag_transparent.png"
    }
}

val listOfCountries = listOf(
    CountryCode("ad", "+376", "Andorra"),
    CountryCode("ae", "+971", "United Arab Emirates (UAE)"),
    CountryCode("af", "+93", "Afghanistan"),
    CountryCode("ag", "+1", "Antigua and Barbuda"),
    CountryCode("ai", "+1", "Anguilla"),
    CountryCode("al", "+355", "Albania"),
    CountryCode("am", "+374", "Armenia"),
    CountryCode("ao", "+244", "Angola"),
    CountryCode("aq", "+672", "Antarctica"),
    CountryCode("ar", "+54", "Argentina"),
    CountryCode("as", "+1", "American Samoa"),
    CountryCode("at", "+43", "Austria"),
    CountryCode("au", "+61", "Australia"),
    CountryCode("aw", "+297", "Aruba"),
    CountryCode("ax", "+358", "Åland Islands"),
    CountryCode("az", "+994", "Azerbaijan"),
    CountryCode("ba", "+387", "Bosnia And Herzegovina"),
    CountryCode("bb", "+1", "Barbados"),
    CountryCode("bd", "+880", "Bangladesh"),
    CountryCode("be", "+32", "Belgium"),
    CountryCode("bf", "+226", "Burkina Faso"),
    CountryCode("bg", "+359", "Bulgaria"),
    CountryCode("bh", "+973", "Bahrain"),
    CountryCode("bi", "+257", "Burundi"),
    CountryCode("bj", "+229", "Benin"),
    CountryCode("bl", "+590", "Saint Barthélemy"),
    CountryCode("bm", "+1", "Bermuda"),
    CountryCode("bn", "+673", "Brunei Darussalam"),
    CountryCode("bo", "+591", "Bolivia, Plurinational State Of"),
    CountryCode("br", "+55", "Brazil"),
    CountryCode("bs", "+1", "Bahamas"),
    CountryCode("bt", "+975", "Bhutan"),
    CountryCode("bw", "+267", "Botswana"),
    CountryCode("by", "+375", "Belarus"),
    CountryCode("bz", "+501", "Belize"),
    CountryCode("ca", "+1", "Canada"),
    CountryCode("cc", "+61", "Cocos (keeling) Islands"),
    CountryCode("cd", "+243", "Congo, The Democratic Republic Of The"),
    CountryCode("cf", "+236", "Central African Republic"),
    CountryCode("cg", "+242", "Congo"),
    CountryCode("ch", "+41", "Switzerland"),
    CountryCode("ci", "+225", "Côte D'ivoire"),
    CountryCode("ck", "+682", "Cook Islands"),
    CountryCode("cl", "+56", "Chile"),
    CountryCode("cm", "+237", "Cameroon"),
    CountryCode("cn", "+86", "China"),
    CountryCode("co", "+57", "Colombia"),
    CountryCode("cr", "+506", "Costa Rica"),
    CountryCode("cu", "+53", "Cuba"),
    CountryCode("cv", "+238", "Cape Verde"),
    CountryCode("cw", "+599", "Curaçao"),
    CountryCode("cx", "+61", "Christmas Island"),
    CountryCode("cy", "+357", "Cyprus"),
    CountryCode("cz", "+420", "Czech Republic"),
    CountryCode("de", "+49", "Germany"),
    CountryCode("dj", "+253", "Djibouti"),
    CountryCode("dk", "+45", "Denmark"),
    CountryCode("dm", "+1", "Dominica"),
    CountryCode("do", "+1", "Dominican Republic"),
    CountryCode("dz", "+213", "Algeria"),
    CountryCode("ec", "+593", "Ecuador"),
    CountryCode("ee", "+372", "Estonia"),
    CountryCode("eg", "+20", "Egypt"),
    CountryCode("er", "+291", "Eritrea"),
    CountryCode("es", "+34", "Spain"),
    CountryCode("et", "+251", "Ethiopia"),
    CountryCode("fi", "+358", "Finland"),
    CountryCode("fj", "+679", "Fiji"),
    CountryCode("fk", "+500", "Falkland Islands (malvinas)"),
    CountryCode("fm", "+691", "Micronesia, Federated States Of"),
    CountryCode("fo", "+298", "Faroe Islands"),
    CountryCode("fr", "+33", "France"),
    CountryCode("ga", "+241", "Gabon"),
    CountryCode("gb", "+44", "United Kingdom"),
    CountryCode("gd", "+1", "Grenada"),
    CountryCode("ge", "+995", "Georgia"),
    CountryCode("gf", "+594", "French Guyana"),
    CountryCode("gh", "+233", "Ghana"),
    CountryCode("gi", "+350", "Gibraltar"),
    CountryCode("gl", "+299", "Greenland"),
    CountryCode("gm", "+220", "Gambia"),
    CountryCode("gn", "+224", "Guinea"),
    CountryCode("gp", "+450", "Guadeloupe"),
    CountryCode("gq", "+240", "Equatorial Guinea"),
    CountryCode("gr", "+30", "Greece"),
    CountryCode("gt", "+502", "Guatemala"),
    CountryCode("gu", "+1", "Guam"),
    CountryCode("gw", "+245", "Guinea-bissau"),
    CountryCode("gy", "+592", "Guyana"),
    CountryCode("hk", "+852", "Hong Kong"),
    CountryCode("hn", "+504", "Honduras"),
    CountryCode("hr", "+385", "Croatia"),
    CountryCode("ht", "+509", "Haiti"),
    CountryCode("hu", "+36", "Hungary"),
    CountryCode("id", "+62", "Indonesia"),
    CountryCode("ie", "+353", "Ireland"),
    CountryCode("im", "+44", "Isle Of Man"),
    CountryCode("is", "+354", "Iceland"),
    CountryCode("in", "+91", "India"),
    CountryCode("io", "+246", "British Indian Ocean Territory"),
    CountryCode("iq", "+964", "Iraq"),
    CountryCode("ir", "+98", "Iran, Islamic Republic Of"),
    CountryCode("it", "+39", "Italy"),
    CountryCode("je", "+44", "Jersey "),
    CountryCode("jm", "+1", "Jamaica"),
    CountryCode("jo", "+962", "Jordan"),
    CountryCode("jp", "+81", "Japan"),
    CountryCode("ke", "+254", "Kenya"),
    CountryCode("kg", "+996", "Kyrgyzstan"),
    CountryCode("kh", "+855", "Cambodia"),
    CountryCode("ki", "+686", "Kiribati"),
    CountryCode("km", "+269", "Comoros"),
    CountryCode("kn", "+1", "Saint Kitts and Nevis"),
    CountryCode("kp", "+850", "North Korea"),
    CountryCode("kr", "+82", "South Korea"),
    CountryCode("kw", "+965", "Kuwait"),
    CountryCode("ky", "+1", "Cayman Islands"),
    CountryCode("kz", "+7", "Kazakhstan"),
    CountryCode("la", "+856", "Lao People's Democratic Republic"),
    CountryCode("lb", "+961", "Lebanon"),
    CountryCode("lc", "+1", "Saint Lucia"),
    CountryCode("li", "+423", "Liechtenstein"),
    CountryCode("lk", "+94", "Sri Lanka"),
    CountryCode("lr", "+231", "Liberia"),
    CountryCode("ls", "+266", "Lesotho"),
    CountryCode("lt", "+370", "Lithuania"),
    CountryCode("lu", "+352", "Luxembourg"),
    CountryCode("lv", "+371", "Latvia"),
    CountryCode("ly", "+218", "Libya"),
    CountryCode("ma", "+212", "Morocco"),
    CountryCode("mc", "+377", "Monaco"),
    CountryCode("md", "+373", "Moldova, Republic Of"),
    CountryCode("me", "+382", "Montenegro"),
    CountryCode("mf", "+590", "Saint Martin"),
    CountryCode("mg", "+261", "Madagascar"),
    CountryCode("mh", "+692", "Marshall Islands"),
    CountryCode("mk", "+389", "Macedonia (FYROM)"),
    CountryCode("ml", "+223", "Mali"),
    CountryCode("mm", "+95", "Myanmar"),
    CountryCode("mn", "+976", "Mongolia"),
    CountryCode("mo", "+853", "Macau"),
    CountryCode("mp", "+1", "Northern Mariana Islands"),
    CountryCode("mq", "+596", "Martinique"),
    CountryCode("mr", "+222", "Mauritania"),
    CountryCode("ms", "+1", "Montserrat"),
    CountryCode("mt", "+356", "Malta"),
    CountryCode("mu", "+230", "Mauritius"),
    CountryCode("mv", "+960", "Maldives"),
    CountryCode("mw", "+265", "Malawi"),
    CountryCode("mx", "+52", "Mexico"),
    CountryCode("my", "+60", "Malaysia"),
    CountryCode("mz", "+258", "Mozambique"),
    CountryCode("na", "+264", "Namibia"),
    CountryCode("nc", "+687", "New Caledonia"),
    CountryCode("ne", "+227", "Niger"),
    CountryCode("nf", "+672", "Norfolk Islands"),
    CountryCode("ng", "+234", "Nigeria"),
    CountryCode("ni", "+505", "Nicaragua"),
    CountryCode("nl", "+31", "Netherlands"),
    CountryCode("no", "+47", "Norway"),
    CountryCode("np", "+977", "Nepal"),
    CountryCode("nr", "+674", "Nauru"),
    CountryCode("nu", "+683", "Niue"),
    CountryCode("nz", "+64", "New Zealand"),
    CountryCode("om", "+968", "Oman"),
    CountryCode("pa", "+507", "Panama"),
    CountryCode("pe", "+51", "Peru"),
    CountryCode("pf", "+689", "French Polynesia"),
    CountryCode("pg", "+675", "Papua New Guinea"),
    CountryCode("ph", "+63", "Philippines"),
    CountryCode("pk", "+92", "Pakistan"),
    CountryCode("pl", "+48", "Poland"),
    CountryCode("pm", "+508", "Saint Pierre And Miquelon"),
    CountryCode("pn", "+870", "Pitcairn Islands"),
    CountryCode("pr", "+1", "Puerto Rico"),
    CountryCode("ps", "+970", "Palestine"),
    CountryCode("pt", "+351", "Portugal"),
    CountryCode("pw", "+680", "Palau"),
    CountryCode("py", "+595", "Paraguay"),
    CountryCode("qa", "+974", "Qatar"),
    CountryCode("re", "+262", "Réunion"),
    CountryCode("ro", "+40", "Romania"),
    CountryCode("rs", "+381", "Serbia"),
    CountryCode("ru", "+7", "Russian Federation"),
    CountryCode("rw", "+250", "Rwanda"),
    CountryCode("sa", "+966", "Saudi Arabia"),
    CountryCode("sb", "+677", "Solomon Islands"),
    CountryCode("sc", "+248", "Seychelles"),
    CountryCode("sd", "+249", "Sudan"),
    CountryCode("se", "+46", "Sweden"),
    CountryCode("sg", "+65", "Singapore"),
    CountryCode("sh", "+290", "Saint Helena, Ascension And Tristan Da Cunha"),
    CountryCode("si", "+386", "Slovenia"),
    CountryCode("sk", "+421", "Slovakia"),
    CountryCode("sl", "+232", "Sierra Leone"),
    CountryCode("sm", "+378", "San Marino"),
    CountryCode("sn", "+221", "Senegal"),
    CountryCode("so", "+252", "Somalia"),
    CountryCode("sr", "+597", "Suriname"),
    CountryCode("ss", "+211", "South Sudan"),
    CountryCode("st", "+239", "Sao Tome And Principe"),
    CountryCode("sv", "+503", "El Salvador"),
    CountryCode("sx", "+1", "Sint Maarten"),
    CountryCode("sy", "+963", "Syrian Arab Republic"),
    CountryCode("sz", "+268", "Swaziland"),
    CountryCode("tc", "+1", "Turks and Caicos Islands"),
    CountryCode("td", "+235", "Chad"),
    CountryCode("tg", "+228", "Togo"),
    CountryCode("th", "+66", "Thailand"),
    CountryCode("tj", "+992", "Tajikistan"),
    CountryCode("tk", "+690", "Tokelau"),
    CountryCode("tl", "+670", "Timor-leste"),
    CountryCode("tm", "+993", "Turkmenistan"),
    CountryCode("tn", "+216", "Tunisia"),
    CountryCode("to", "+676", "Tonga"),
    CountryCode("tr", "+90", "Turkey"),
    CountryCode("tt", "+1", "Trinidad &amp; Tobago"),
    CountryCode("tv", "+688", "Tuvalu"),
    CountryCode("tw", "+886", "Taiwan"),
    CountryCode("tz", "+255", "Tanzania, United Republic Of"),
    CountryCode("ua", "+380", "Ukraine"),
    CountryCode("ug", "+256", "Uganda"),
    CountryCode("us", "+1", "United States"),
    CountryCode("uy", "+598", "Uruguay"),
    CountryCode("uz", "+998", "Uzbekistan"),
    CountryCode("va", "+379", "Holy See (vatican City State)"),
    CountryCode("vc", "+1", "Saint Vincent &amp; The Grenadines"),
    CountryCode("ve", "+58", "Venezuela, Bolivarian Republic Of"),
    CountryCode("vg", "+1", "British Virgin Islands"),
    CountryCode("vi", "+1", "US Virgin Islands"),
    CountryCode("vn", "+84", "Vietnam"),
    CountryCode("vu", "+678", "Vanuatu"),
    CountryCode("wf", "+681", "Wallis And Futuna"),
    CountryCode("ws", "4685", "Samoa"),
    CountryCode("xk", "+383", "Kosovo"),
    CountryCode("ye", "+967", "Yemen"),
    CountryCode("yt", "+262", "Mayotte"),
    CountryCode("za", "+27", "South Africa"),
    CountryCode("zm", "+260", "Zambia"),
    CountryCode("zw", "+263", "Zimbabwe")
)

fun List<CountryCode>.searchCountryList(key: String): MutableList<CountryCode> {
    val tempList = mutableListOf<CountryCode>()
    this.forEach {
        if (it.countryName.lowercase().contains(key.lowercase())) {
            tempList.add(it)
        }
    }
    return tempList
}