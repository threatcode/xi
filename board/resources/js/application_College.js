if (!Array.prototype.indexOf) {
    Array.prototype.indexOf = function(val) {
        return jQuery.inArray(val, this);
    };
}


//var snapshot = Defiant.getSnapshot(college_json);
var snapshot;

/* Application Constant Declaration */
var arrShift = ["-","Morning","Day","Evening"];
var arrVersion = ["-","Bangla","English"]; 
var arrGroup = ["Science","","Humanities","","Agriculture","Home Economics","Islamic Studies","Music","Business Studies","MADRASA - General","MADRASA - Muzzabid","","MADRASA - Science","","","","","","","","","HSCVOC - Agro Machinery","HSCVOC - Automobile","HSCVOC - Building Maintenance and Construction","HSCVOC - Clothing and Garments Finishing","HSCVOC - Computer Operation and Maintenance","HSCVOC - Drafting Civil","HSCVOC - Electrical Works and Maintenance","HSCVOC - Electronic Control and Communication","HSCVOC - Fish Culture and Breeding","HSCVOC - Machine Tools Operation and Maintenance","HSCVOC - Poultry Rearing and Farming","HSCVOC - Refrigeration and Air-conditioning","HSCVOC - Welding and Fabrication","HSCVOC - Industrial Wood Working","HSCVOC - Wet Processing","HSCVOC - Yarn and Fabric Manufacturing","HSCBM - Accounting","HSCBM - Banking","HSCBM - Computer Operation","HSCBM - Entrepreneurship Development","HSCBM - Secretarial Science","DCOM - Shorthand","DCOM - Accounting","HSCVOC - Warehouse and Storekeeping","HSCVOC - Home Economics","","","","",""];
var arrSscHscGroupMap=["0|0", "0|2", "0|4", "0|5", "0|6", "0|7", "0|8", "0|37", "0|38", "0|39", "0|40", "0|41", "0|42", "0|43", "2|2", "2|4", "2|5", "2|6", "2|7", "2|8", "2|37", 
"2|38", "2|39", "2|40", "2|41", "2|42", "2|43", "8|2", "8|4", "8|5", "8|6", "8|7", "8|8", "8|37", "8|38", "8|39", "8|40", "8|41", "8|42", "8|43", "9|2", 
"9|4", "9|5", "9|6", "9|7", "9|8", "9|9", "9|10", "9|37", "9|38", "9|39", "9|40", "9|41", "9|42", "9|43", "10|2", "10|4", "10|5", "10|6", "10|7", "10|8", 
"10|9", "10|10", "10|37", "10|38", "10|39", "10|40", "10|41", "10|42", "10|43", "11|2", "11|4", "11|5", "11|6", "11|7", "11|8", "11|9", "11|10", "11|37", 
"11|38", "11|39", "11|40", "11|41", "11|42", "11|43", "12|0", "12|2", "12|4", "12|5", "12|6", "12|7", "12|8", "12|9", "12|10", "12|12", "12|37", "12|38", 
"12|39", "12|40", "12|41", "12|42", "12|43", "21|0", "21|2", "21|4", "21|5", "21|6", "21|7", "21|8", "21|37", "21|38", "21|39", "21|40", "21|41", "21|42", 
"21|43", "22|0", "22|2", "22|4", "22|5", "22|6", "22|7", "22|8", "22|37", "22|38", "22|39", "22|40", "22|41", "22|42", "22|43", "23|0", "23|2", "23|4", "23|5", 
"23|6", "23|7", "23|8", "23|21", "23|22", "23|37", "23|38", "23|39", "23|40", "23|41", "23|42", "23|43", "24|0", "24|2", "24|4", "24|5", "24|6", "24|7", "24|8", 
"24|23", "24|34", "24|37", "24|38", "24|39", "24|40", "24|41", "24|42", "24|43", "25|0", "25|2", "25|4", "25|5", "25|6", "25|7", "25|8", "25|34", "25|37", 
"25|38", "25|39", "25|40", "25|41", "25|42", "25|43", "26|0", "26|2", "26|4", "26|5", "26|6", "26|7", "26|8", "26|37", "26|38", "26|39", "26|40", "26|41", 
"26|42", "26|43", "27|0", "27|2", "27|4", "27|5", "27|6", "27|7", "27|8", "27|23", "27|37", "27|38", "27|39", "27|40", "27|41", "27|42", "27|43", "28|0", 
"28|2", "28|4", "28|5", "28|6", "28|7", "28|8", "28|25", "28|37", "28|38", "28|39", "28|40", "28|41", "28|42", "28|43", "29|0", "29|2", "29|4", "29|5", 
"29|6", "29|7", "29|8", "29|26", "29|37", "29|38", "29|39", "29|40", "29|41", "29|42", "29|43", "30|0", "30|2", "30|4", "30|5", "30|6", "30|7", "30|8", 
"30|37", "30|38", "30|39", "30|40", "30|41", "30|42", "30|43", "31|0", "31|2", "31|4", "31|5", "31|6", "31|7", "31|8", "31|24", "31|37", "31|38", "31|39", 
"31|40", "31|41", "31|42", "31|43", "32|0", "32|2", "32|4", "32|5", "32|6", "32|7", "32|8", "32|24", "32|37", "32|38", "32|39", "32|40", "32|41", "32|42", 
"32|43", "33|0", "33|2", "33|4", "33|5", "33|6", "33|7", "33|8", "33|37", "33|38", "33|39", "33|40", "33|41", "33|42", "33|43", "34|0", "34|2", "34|4", 
"34|5", "34|6", "34|7", "34|8", "34|21", "34|22", "34|37", "34|38", "34|39", "34|40", "34|41", "34|42", "34|43", "35|0", "35|2", "35|4", "35|5", "35|6", 
"35|7", "35|8", "35|37", "35|38", "35|39", "35|40", "35|41", "35|42", "35|43", "36|0", "36|2", "36|4", "36|5", "36|6", "36|7", "36|8", "36|37", "36|38", 
"36|39", "36|40", "36|41", "36|42", "36|43", "37|0", "37|2", "37|4", "37|5", "37|6", "37|7", "37|8", "37|21", "37|30", "37|33", "37|37", "37|38", "37|39", 
"37|40", "37|41", "37|42", "37|43", "38|0", "38|2", "38|4", "38|5", "38|6", "38|7", "38|8", "38|37", "38|38", "38|39", "38|40", "38|41", "38|42", "38|43", 
"39|0", "39|2", "39|4", "39|5", "39|6", "39|7", "39|8", "39|37", "39|38", "39|39", "39|40", "39|41", "39|42", "39|43", "40|0", "40|2", "40|4", "40|5", "40|6", 
"40|7", "40|8", "40|37", "40|38", "40|39", "40|40", "40|41", "40|42", "40|43", "41|0", "41|2", "41|4", "41|5", "41|6", "41|7", "41|8", "41|23", "41|37", 
"41|38", "41|39", "41|40", "41|41", "41|42", "41|43", "42|0", "42|2", "42|4", "42|5", "42|6", "42|7", "42|8", "42|37", "42|38", "42|39", "42|40", "42|41", 
"42|42", "42|43", "44|0", "44|2", "44|4", "44|5", "44|6", "44|7", "44|8", "44|37", "44|38", "44|39", "44|40", "44|41", "44|42", "44|43", "45|0", "45|2", 
"45|4", "45|5", "45|6", "45|7", "45|8", "45|24", "45|37", "45|38", "45|39", "45|40", "45|41", "45|42", "45|43", "46|0", "46|2", "46|4", "46|5", "46|6", 
"46|7", "46|8", "46|37", "46|38", "46|39", "46|40", "46|41", "46|42", "46|43", "47|0", "47|2", "47|4", "47|5", "47|6", "47|7", "47|8", "47|37", "47|38", 
"47|39", "47|40", "47|41", "47|42", "47|43", "49|0", "49|2", "49|4", "49|5", "49|6", "49|7", "49|8", "49|37", "49|38", "49|39", "49|40", "49|41", "49|42", 
"49|43", "51|0", "51|2", "51|4", "51|5", "51|6", "51|7", "51|8", "51|37", "51|38", "51|39", "51|40", "51|41", "51|42", "51|43", "52|0", "52|2", "52|4", 
"52|5", "52|6", "52|7", "52|8", "52|37", "52|38", "52|39", "52|40", "52|41", "52|42", "52|43", "53|0", "53|2", "53|4", "53|5", "53|6", "53|7", "53|8", 
"53|37", "53|38", "53|39", "53|40", "53|41", "53|42", "53|43", "56|0", "56|2", "56|4", "56|5", "56|6", "56|7", "56|8", "56|37", "56|38", "56|39", "56|40", 
"56|41", "56|42", "56|43", "57|0", "57|2", "57|4", "57|5", "57|6", "57|7", "57|8", "57|33", "57|37", "57|38", "57|39", "57|40", "57|41", "57|42", "57|43", 
"58|0", "58|2", "58|4", "58|5", "58|6", "58|7", "58|8", "58|37", "58|38", "58|39", "58|40", "58|41", "58|42", "58|43", "59|0", "59|2", "59|4", "59|5", 
"59|6", "59|7", "59|8", "59|37", "59|38", "59|39", "59|40", "59|41", "59|42", "59|43", "61|0", "61|2", "61|4", "61|5", "61|6", "61|7", "61|8", "61|21", 
"61|37", "61|38", "61|39", "61|40", "61|41", "61|42", "61|43", "62|0", "62|2", "62|4", "62|5", "62|6", "62|7", "62|8", "62|28", "62|37", "62|38", "62|39", 
"62|40", "62|41", "62|42", "62|43", "63|0", "63|2", "63|4", "63|5", "63|6", "63|7", "63|8", "63|21", "63|22", "63|37", "63|38", "63|39", "63|40", "63|41", 
"63|42", "63|43", "64|0", "64|2", "64|4", "64|5", "64|6", "64|7", "64|8", "64|23", "64|34", "64|37", "64|38", "64|39", "64|40", "64|41", "64|42", "64|43", 
"65|0", "65|2", "65|4", "65|5", "65|6", "65|7", "65|8", "65|34", "65|37", "65|38", "65|39", "65|40", "65|41", "65|42", "65|43", "66|0", "66|2", "66|4", 
"66|5", "66|6", "66|7", "66|8", "66|21", "66|22", "66|23", "66|24", "66|25", "66|26", "66|27", "66|28", "66|29", "66|30", "66|31", "66|32", "66|33", "66|34", 
"66|35", "66|36", "66|37", "66|38", "66|39", "66|40", "66|41", "66|42", "66|43", "66|44", "66|45", "67|0", "67|2", "67|4", "67|5", "67|6", "67|7", "67|8", 
"67|23", "67|37", "67|38", "67|39", "67|40", "67|41", "67|42", "67|43", "68|0", "68|2", "68|4", "68|5", "68|6", "68|7", "68|8", "68|25", "68|37", "68|38", 
"68|39", "68|40", "68|41", "68|42", "68|43", "69|0", "69|2", "69|4", "69|5", "69|6", "69|7", "69|8", "69|26", "69|37", "69|38", "69|39", "69|40", "69|41", 
"69|42", "69|43","70|0", "70|2", "70|4", "70|5", "70|6", "70|7", "70|8", "70|26", "70|30", "70|37", "70|38", "70|39", "70|40", "70|41", "70|42", "70|43", 
"71|0", "71|2", "71|4", "71|5", "71|6", "71|7", "71|8", "71|24", "71|37", "71|38", "71|39", "71|40", "71|41", "71|42", "71|43", "72|0", "72|2", "72|4", 
"72|5", "72|6", "72|7", "72|8", "72|24", "72|37", "72|38", "72|39", "72|40", "72|41", "72|42", "72|43", "73|0", "73|2", "73|4", "73|5", "73|6", "73|7", 
"73|8", "73|27", "73|37", "73|38", "73|39", "73|40", "73|41", "73|42", "73|43", "74|0", "74|2", "74|4", "74|5", "74|6", "74|7", "74|8", "74|21", "74|22", 
"74|37", "74|38", "74|39", "74|40", "74|41", "74|42", "74|43", "75|0", "75|2", "75|4", "75|5", "75|6", "75|7", "75|8", "75|29", "75|37", "75|38", "75|39", 
"75|40", "75|41", "75|42", "75|43", "76|0", "76|2", "76|4", "76|5", "76|6", "76|7", "76|8", "76|29", "76|37", "76|38", "76|39", "76|40", "76|41", "76|42", 
"76|43", "77|0", "77|2", "77|4", "77|5", "77|6", "77|7", "77|8", "77|21", "77|30", "77|33", "77|37", "77|38", "77|39", "77|40", "77|41", "77|42", "77|43", 
"78|0", "78|2", "78|4", "78|5", "78|6", "78|7", "78|8", "78|31", "78|37", "78|38", "78|39", "78|40", "78|41", "78|42", "78|43", "79|0", "79|2", "79|4", 
"79|5", "79|6", "79|7", "79|8", "79|30", "79|37", "79|38", "79|39", "79|40", "79|41", "79|42", "79|43", "80|0", "80|2", "80|4", "80|5", "80|6", "80|7", 
"80|8", "80|31", "80|37", "80|38", "80|39", "80|40", "80|41", "80|42", "80|43", "89|0", "89|2", "89|4", "89|5", "89|6", "89|7", "89|8", "89|21", "89|22", 
"89|23", "89|24", "89|25", "89|26", "89|27", "89|28", "89|29", "89|30", "89|31", "89|32", "89|33", "89|34", "89|35", "89|36", "89|37", "89|38", "89|39", 
"89|40", "89|41", "89|42", "89|43", "89|44", "89|45", "90|0", "90|2", "90|4", "90|5", "90|6", "90|7", "90|8", "90|27", "90|37", "90|38", "90|39", "90|40", 
"90|41", "90|42", "90|43", "91|0", "91|2", "91|4", "91|5", "91|6", "91|7", "91|8", "91|23", "91|37", "91|38", "91|39", "91|40", "91|41", "91|42", "91|43", 
"92|0", "92|2", "92|4", "92|5", "92|6", "92|7", "92|8", "92|32", "92|37", "92|38", "92|39", "92|40", "92|41", "92|42", "92|43", "93|0", "93|2", "93|4", 
"93|5", "93|6", "93|7", "93|8", "93|21", "93|22", "93|23", "93|24", "93|25", "93|26", "93|27", "93|28", "93|29", "93|30", "93|31", "93|32", "93|33", "93|34", 
"93|35", "93|36", "93|37", "93|38", "93|39", "93|40", "93|41", "93|42", "93|43", "93|44", "93|45", "94|0", "94|2", "94|4", "94|5", "94|6", "94|7", "94|8", 
"94|21", "94|37", "94|38", "94|39", "94|40", "94|41", "94|42", "94|43", "95|0", "95|2", "95|4", "95|5", "95|6", "95|7", "95|8", "95|24", "95|37", "95|38", 
"95|39", "95|40", "95|41", "95|42", "95|43", "96|0", "96|2", "96|4", "96|5", "96|6", "96|7", "96|8", "96|33", "96|37", "96|38", "96|39", "96|40", "96|41", 
"96|42", "96|43", "97|0", "97|2", "97|4", "97|5", "97|6", "97|7", "97|8", "97|26", "97|37", "97|38", "97|39", "97|40", "97|41", "97|42", "97|43", "98|0", 
"98|2", "98|4", "98|5", "98|6", "98|7", "98|8", "98|24", "98|37", "98|38", "98|39", "98|40", "98|41", "98|42", "98|43", "99|0", "99|2", "99|4", "99|5", 
"99|6", "99|7", "99|8", "99|29", "99|37", "99|38", "99|39", "99|40", "99|41", "99|42", "99|43", "721|0", "721|2", "721|4", "721|5", "721|6", "721|7", "721|8", 
"721|9", "721|10", "721|12", "721|37", "721|38", "721|39", "721|40", "721|41", "721|42", "721|43", "722|0", "722|2", "722|4", "722|5", "722|6", "722|7", 
"722|8", "722|9", "722|10", "722|12", "722|37", "722|38", "722|39", "722|40", "722|41", "722|42", "722|43", "723|0", "723|2", "723|4", "723|5", "723|6", 
"723|7", "723|8", "723|9", "723|10", "723|12", "723|37", "723|38", "723|39", "723|40", "723|41", "723|42", "723|43", "724|0", "724|2", "724|4", "724|5", 
"724|6", "724|7", "724|8", "724|9", "724|10", "724|12", "724|37", "724|38", "724|39", "724|40", "724|41", "724|42", "724|43", "725|0", "725|2", "725|4", 
"725|5", "725|6", "725|7", "725|8", "725|9", "725|10", "725|12", "725|37", "725|38", "725|39", "725|40", "725|41", "725|42", "725|43", "726|0", "726|2", 
"726|4", "726|5", "726|6", "726|7", "726|8", "726|9", "726|10", "726|12", "726|37", "726|38", "726|39", "726|40", "726|41", "726|42", "726|43", "727|0", 
"727|2", "727|4", "727|5", "727|6", "727|7", "727|8", "727|9", "727|10", "727|12", "727|37", "727|38", "727|39", "727|40", "727|41", "727|42", "727|43", 
"728|0", "728|2", "728|4", "728|5", "728|6", "728|7", "728|8", "728|9", "728|10", "728|12", "728|25", "728|37", "728|38", "728|39", "728|40", "728|41", 
"728|42", "728|43", "729|0", "729|2", "729|4", "729|5", "729|6", "729|7", "729|8", "729|9", "729|10", "729|12", "729|37", "729|38", "729|39", "729|40", 
"729|41", "729|42", "729|43", "730|0", "730|2", "730|4", "730|5", "730|6", "730|7", "730|8", "730|9", "730|10", "730|12", "730|37", "730|38", "730|39", 
"730|40", "730|41", "730|42", "730|43", "731|0", "731|2", "731|4", "731|5", "731|6", "731|7", "731|8", "731|9", "731|10", "731|12", "731|37", "731|38", 
"731|39", "731|40", "731|41", "731|42", "731|43", "732|0", "732|2", "732|4", "732|5", "732|6", "732|7", "732|8", "732|9", "732|10", "732|12", "732|37", 
"732|38", "732|39", "732|40", "732|41", "732|42", "732|43", "733|0", "733|2", "733|4", "733|5", "733|6", "733|7", "733|8", "733|9", "733|10", "733|12", 
"733|37", "733|38", "733|39", "733|40", "733|41", "733|42", "733|43", "734|0", "734|2", "734|4", "734|5", "734|6", "734|7", "734|8", "734|9", "734|10", 
"734|12", "734|37", "734|38", "734|39", "734|40", "734|41", "734|42", "734|43", "735|0", "735|2", "735|4", "735|5", "735|6", "735|7", "735|8", "735|9", 
"735|10", "735|12", "735|37", "735|38", "735|39", "735|40", "735|41", "735|42", "735|43", "736|0", "736|2", "736|4", "736|5", "736|6", "736|7", "736|8", 
"736|9", "736|10", "736|12", "736|37", "736|38", "736|39", "736|40", "736|41", "736|42", "736|43", "737|0", "737|2", "737|4", "737|5", "737|6", "737|7", 
"737|8", "737|9", "737|10", "737|12", "737|37", "737|38", "737|39", "737|40", "737|41", "737|42", "737|43", "738|0", "738|2", "738|4", "738|5", "738|6", 
"738|7", "738|8", "738|9", "738|10", "738|12", "738|37", "738|38", "738|39", "738|40", "738|41", "738|42", "738|43", "739|0", "739|2", "739|4", "739|5", 
"739|6", "739|7", "739|8", "739|9", "739|10", "739|12", "739|37", "739|38", "739|39", "739|40", "739|41", "739|42", "739|43", "740|0", "740|2", "740|4", 
"740|5", "740|6", "740|7", "740|8", "740|9", "740|10", "740|12", "740|37", "740|38", "740|39", "740|40", "740|41", "740|42", "740|43", "741|0", "741|2", 
"741|4", "741|5", "741|6", "741|7", "741|8", "741|9", "741|10", "741|12", "741|37", "741|38", "741|39", "741|40", "741|41", "741|42", "741|43", "742|0", 
"742|2", "742|4", "742|5", "742|6", "742|7", "742|8", "742|9", "742|10", "742|12", "742|37", "742|38", "742|39", "742|40", "742|41", "742|42", "742|43", 
"744|0", "744|2", "744|4", "744|5", "744|6", "744|7", "744|8", "744|9", "744|10", "744|12", "744|37", "744|38", "744|39", "744|40", "744|41", "744|42", 
"744|43", "745|0", "745|2", "745|4", "745|5", "745|6", "745|7", "745|8", "745|9", "745|10", "745|12", "745|37", "745|38", "745|39", "745|40", "745|41", 
"745|42", "745|43", "746|0", "746|2", "746|4", "746|5", "746|6", "746|7", "746|8", "746|9", "746|10", "746|12", "746|37", "746|38", "746|39", "746|40", 
"746|41", "746|42", "746|43", "747|0", "747|2", "747|4", "747|5", "747|6", "747|7", "747|8", "747|9", "747|10", "747|12", "747|37", "747|38", "747|39", 
"747|40", "747|41", "747|42", "747|43", "749|0", "749|2", "749|4", "749|5", "749|6", "749|7", "749|8", "749|9", "749|10", "749|12", "749|37", "749|38", 
"749|39", "749|40", "749|41", "749|42", "749|43", "751|0", "751|2", "751|4", "751|5", "751|6", "751|7", "751|8", "751|9", "751|10", "751|12", "751|37", 
"751|38", "751|39", "751|40", "751|41", "751|42", "751|43", "752|0", "752|2", "752|4", "752|5", "752|6", "752|7", "752|8", "752|9", "752|10", "752|12", 
"752|37", "752|38", "752|39", "752|40", "752|41", "752|42", "752|43", "753|0", "753|2", "753|4", "753|5", "753|6", "753|7", "753|8", "753|9", "753|10", 
"753|12", "753|37", "753|38", "753|39", "753|40", "753|41", "753|42", "753|43", "756|0", "756|2", "756|4", "756|5", "756|6", "756|7", "756|8", "756|9", 
"756|10", "756|12", "756|37", "756|38", "756|39", "756|40", "756|41", "756|42", "756|43", "757|0", "757|2", "757|4", "757|5", "757|6", "757|7", "757|8", 
"757|9", "757|10", "757|12", "757|33", "757|37", "757|38", "757|39", "757|40", "757|41", "757|42", "757|43", "758|0", "758|2", "758|4", "758|5", "758|6", 
"758|7", "758|8", "758|9", "758|10", "758|12", "758|37", "758|38", "758|39", "758|40", "758|41", "758|42", "758|43", "759|0", "759|2", "759|4", "759|5", 
"759|6", "759|7", "759|8", "759|9", "759|10", "759|12", "759|37", "759|38", "759|39", "759|40", "759|41", "759|42", "759|43", "761|0", "761|2", "761|4", 
"761|5", "761|6", "761|7", "761|8", "761|9", "761|10", "761|12", "761|21", "761|37", "761|38", "761|39", "761|40", "761|41", "761|42", "761|43", "762|0", 
"762|2", "762|4", "762|5", "762|6", "762|7", "762|8", "762|9", "762|10", "762|12", "762|28", "762|37", "762|38", "762|39", "762|40", "762|41", "762|42", 
"762|43", "763|0", "763|2", "763|4", "763|5", "763|6", "763|7", "763|8", "763|9", "763|10", "763|12", "763|21", "763|22", "763|37", "763|38", "763|39", 
"763|40", "763|41", "763|42", "763|43", "764|0", "764|2", "764|4", "764|5", "764|6", "764|7", "764|8", "764|9", "764|10", "764|12", "764|23", "764|34", 
"764|37", "764|38", "764|39", "764|40", "764|41", "764|42", "764|43", "765|0", "765|2", "765|4", "765|5", "765|6", "765|7", "765|8", "765|9", "765|10", 
"765|12", "765|34", "765|37", "765|38", "765|39", "765|40", "765|41", "765|42", "765|43", "766|0", "766|2", "766|4", "766|5", "766|6", "766|7", "766|8", 
"766|9", "766|10", "766|12", "766|21", "766|22", "766|23", "766|24", "766|25", "766|26", "766|27", "766|28", "766|29", "766|30", "766|31", "766|32", "766|33", 
"766|34", "766|35", "766|36", "766|37", "766|38", "766|39", "766|40", "766|41", "766|42", "766|43", "766|44", "766|45", "767|0", "767|2", "767|4", "767|5", 
"767|6", "767|7", "767|8", "767|9", "767|10", "767|12", "767|23", "767|37", "767|38", "767|39", "767|40", "767|41", "767|42", "767|43", "768|0", "768|2", 
"768|4", "768|5", "768|6", "768|7", "768|8", "768|9", "768|10", "768|12", "768|25", "768|37", "768|38", "768|39", "768|40", "768|41", "768|42", "768|43", 
"769|0", "769|2", "769|4", "769|5", "769|6", "769|7", "769|8", "769|9", "769|10", "769|12", "769|26", "769|37", "769|38", "769|39", "769|40", "769|41", 
"769|42", "769|43", "770|0", "770|2", "770|4", "770|5", "770|6", "770|7", "770|8", "770|9", "770|10", "770|12", "770|26", "770|30", "770|37", "770|38", 
"770|39", "770|40", "770|41", "770|42", "770|43", "771|0", "771|2", "771|4", "771|5", "771|6", "771|7", "771|8", "771|9", "771|10", "771|12", "771|24", 
"771|37", "771|38", "771|39", "771|40", "771|41", "771|42", "771|43", "772|0", "772|2", "772|4", "772|5", "772|6", "772|7", "772|8", "772|9", "772|10", 
"772|12", "772|24", "772|37", "772|38", "772|39", "772|40", "772|41", "772|42", "772|43", "773|0", "773|2", "773|4", "773|5", "773|6", "773|7", "773|8", 
"773|9", "773|10", "773|12", "773|27", "773|37", "773|38", "773|39", "773|40", "773|41", "773|42", "773|43", "774|0", "774|2", "774|4", "774|5", "774|6", 
"774|7", "774|8", "774|9", "774|10", "774|12", "774|21", "774|22", "774|37", "774|38", "774|39", "774|40", "774|41", "774|42", "774|43", "775|0", "775|2", 
"775|4", "775|5", "775|6", "775|7", "775|8", "775|9", "775|10", "775|12", "775|29", "775|37", "775|38", "775|39", "775|40", "775|41", "775|42", "775|43", 
"776|0", "776|2", "776|4", "776|5", "776|6", "776|7", "776|8", "776|9", "776|10", "776|12", "776|29", "776|37", "776|38", "776|39", "776|40", "776|41", 
"776|42", "776|43", "777|0", "777|2", "777|4", "777|5", "777|6", "777|7", "777|8", "777|9", "777|10", "777|12", "777|21", "777|30", "777|33", "777|37", 
"777|38", "777|39", "777|40", "777|41", "777|42", "777|43", "778|0", "778|2", "778|4", "778|5", "778|6", "778|7", "778|8", "778|9", "778|10", "778|12", 
"778|31", "778|37", "778|38", "778|39", "778|40", "778|41", "778|42", "778|43", "779|0", "779|2", "779|4", "779|5", "779|6", "779|7", "779|8", "779|9", 
"779|10", "779|12", "779|30", "779|37", "779|38", "779|39", "779|40", "779|41", "779|42", "779|43", "780|0", "780|2", "780|4", "780|5", "780|6", "780|7", 
"780|8", "780|9", "780|10", "780|12", "780|31", "780|37", "780|38", "780|39", "780|40", "780|41", "780|42", "780|43", "789|0", "789|2", "789|4", "789|5", 
"789|6", "789|7", "789|8", "789|9", "789|10", "789|12", "789|21", "789|22", "789|23", "789|24", "789|25", "789|26", "789|27", "789|28", "789|29", "789|30", 
"789|31", "789|32", "789|33", "789|34", "789|35", "789|36", "789|37", "789|38", "789|39", "789|40", "789|41", "789|42", "789|43", "789|44", "789|45", 
"790|0", "790|2", "790|4", "790|5", "790|6", "790|7", "790|8", "790|9", "790|10", "790|12", "790|27", "790|37", "790|38", "790|39", "790|40", "790|41", 
"790|42", "790|43", "791|0", "791|2", "791|4", "791|5", "791|6", "791|7", "791|8", "791|9", "791|10", "791|12", "791|23", "791|37", "791|38", "791|39", 
"791|40", "791|41", "791|42", "791|43", "792|0", "792|2", "792|4", "792|5", "792|6", "792|7", "792|8", "792|9", "792|10", "792|12", "792|32", "792|37", 
"792|38", "792|39", "792|40", "792|41", "792|42", "792|43", "793|0", "793|2", "793|4", "793|5", "793|6", "793|7", "793|8", "793|9", "793|10", "793|12", 
"793|21", "793|22", "793|23", "793|24", "793|25", "793|26", "793|27", "793|28", "793|29", "793|30", "793|31", "793|32", "793|33", "793|34", "793|35", 
"793|36", "793|37", "793|38", "793|39", "793|40", "793|41", "793|42", "793|43", "793|44", "793|45", "794|0", "794|2", "794|4", "794|5", "794|6", "794|7", 
"794|8", "794|9", "794|10", "794|12", "794|21", "794|37", "794|38", "794|39", "794|40", "794|41", "794|42", "794|43", "795|0", "795|2", "795|4", "795|5", 
"795|6", "795|7", "795|8", "795|9", "795|10", "795|12", "795|24", "795|37", "795|38", "795|39", "795|40", "795|41", "795|42", "795|43", "796|0", "796|2", 
"796|4", "796|5", "796|6", "796|7", "796|8", "796|9", "796|10", "796|12", "796|33", "796|37", "796|38", "796|39", "796|40", "796|41", "796|42", "796|43", 
"797|0", "797|2", "797|4", "797|5", "797|6", "797|7", "797|8", "797|9", "797|10", "797|12", "797|26", "797|37", "797|38", "797|39", "797|40", "797|41", 
"797|42", "797|43", "798|0", "798|2", "798|4", "798|5", "798|6", "798|7", "798|8", "798|9", "798|10", "798|12", "798|24", "798|37", "798|38", "798|39", 
"798|40", "798|41", "798|42", "798|43", "799|0", "799|2", "799|4", "799|5", "799|6", "799|7", "799|8", "799|9", "799|10", "799|12", "799|29", "799|37", 
"799|38", "799|39", "799|40", "799|41", "799|42", "799|43"];


var arrYear = [2018,2017,2016];
var arrYearBOU = [2018,2017,2016,2015,];
var arrBTEByear = [2018,2017,2016,2015,2014,2013,2012,2011,2010,2009,2008,2007];
var college_map = {}; 
var college_eligibility;
var collegeSVG;

var sshowsift = [];
var sshowversion = [];
var sshowgroup = [];
var sshowgender = [];
var sshowseatcount = [];
var currentCollegeName="";
var currentCollegeEIIN="";

function reloadYear(board_id){ 
//	if(board_id==20)
//		$('#ssc_roll').attr("maxlength","15")
//	else
//		$('#ssc_roll').attr("maxlength","6")
	$('#ssc_year').children('option').remove();
	$('#ssc_year').append($("<option></option>")
        		   .attr("value","")
        		   .text("Select Year")); 
	var year=[]

    if(board_id==19)
    	year=arrBTEByear;
    else if(board_id==20)
    	year=arrYearBOU;
    else
    	year=arrYear;
	 
	 
	 for (var i=0; i<year.length; i++) {
	 	if(board_id==20)
	 	{
		 	if(year[i]==2014)
				$('#ssc_year')
		         	.append($("<option selected></option>")
		 			.attr("value",year[i])
		 			.text(year[i]));
		 	else
				$('#ssc_year')
		         	.append($("<option></option>")
		 			.attr("value",year[i])
		 			.text(year[i]));
	 	}
	 	else
	 	{
		 	if(year[i]==2016)
				$('#ssc_year')
		         	.append($("<option selected></option>")
		 			.attr("value",year[i])
		 			.text(year[i]));
		 	else
				$('#ssc_year')
		         	.append($("<option></option>")
		 			.attr("value",year[i])
		 			.text(year[i]));
	 	}
	}

}
 function validateSscInfo(){
/*	 if(!(document.getElementById("user_captcha").value==(one1+two1)))
	 {
	 	alert('Please enter the correct summation value as verification');
	 	return;
	 }*/
	 enableField("submitButton");
	 $("#submitButton").css('background-color', 'buttonface');
		
	 clearHtml("personal_info_div","response_div");
	 clearColegeSelection();
	 resetAvailableSeat();
	 $("#response_div").hide();
	 
	 
		
	 var valid=validateField("ssc_roll","ssc_board","ssc_year","ssc_reg");
	 if(valid==true){
	 $("#personal_info_div").html("<img src='/board/resources/images/239.gif' />");
		 $.ajax({
			 	//url: 'applicationInfoCheck_College',		
			 	url: 'appCheck_College',		
			 	type: 'POST',
			 	data: {ssc_roll:$("#ssc_roll").val(),ssc_board:$("#ssc_board").val(),ssc_year:$("#ssc_year").val(),ssc_reg:$("#ssc_reg").val()
			 		},
			 	success: function(data) {
			 	if(data=="")
			 	{
			 	//	location.reload();
			 	}
			 	if(data.indexOf("validateTxInfo") >= 0)                //data.includes("validateTxInfo"))
			 	{
					$("#tx_info_div").html(data);

			 	    $("#personal_info_div").html("");
			 	}
			 	else
			 	{ 
			 		$("#personal_info_div").html(data);
			 		
			 		var a=$("#college_name").val();
			 		var b=$("#college_eiin").val();
			 		//$('#college_id').append('<option value="'+b+'" >' + a +'</option>');
			 		getCollegeSVGInfo(b);
			 		
			 		$('#mobile_number').unbind();
			 		$('#confirm_mobile_number').unbind();
			 		
			 		$('#mobile_number').bind("cut",function(e) {
			 	        e.preventDefault();
			 	    });
			 		$('#mobile_number').bind("copy",function(e) {
			 	        e.preventDefault();
			 	    });
			 		$('#mobile_number').bind("paste",function(e) {
			 	        e.preventDefault();
			 	    });
			 		
			 		$('#confirm_mobile_number').bind("cut",function(e) {
			 	        e.preventDefault();
			 	    });
			 		$('#confirm_mobile_number').bind("copy",function(e) {
			 	        e.preventDefault();
			 	    });
			 		$('#confirm_mobile_number').bind("paste",function(e) {
			 	        e.preventDefault();
			 	    });	
			 	    scrollToBottom();
			 	  } 	    
			 	},
			 	error: function(e) {
			 	}
		 		});
	 }
	 
	 scrollToBottom();
}

 
function validateTxInfo(){
	 if(!(document.getElementById("tx_captcha").value==(three1+four1)))
	 {
	 	alert('Please enter the correct summation value as verification');
	 	return;
	 }
	 enableField("submitButton");
	 $("#submitButton").css('background-color', 'buttonface');
		
	 clearHtml("tx_input_div","response_div");
	 clearColegeSelection();
	 $("#response_div").hide();
	 
	 
		
	 var valid=validateField("ssc_roll","ssc_board","ssc_year","ssc_reg","tx_captcha");
	 if(valid==true){
	 $("#personal_info_div").html("<img src='/board/resources/images/239.gif' />");
		 $.ajax({
			 	url: 'applicationInfoCheckTx_College',		
			 	type: 'POST',
			 	data: {txid:$("#txid").val(),ssc_roll:$("#ssc_roll").val(),ssc_board:$("#ssc_board").val(),ssc_year:$("#ssc_year").val(),ssc_reg:$("#ssc_reg").val(),
			 		tx_captcha:$("#tx_captcha").val(),java_captcha:$("#java_captcha").val()},			 	
			 	success: function(data) {
			 	if(data=="")
			 	{
			 		location.reload();
			 	}
			 		$("#personal_info_div").html(data);
			 		$('#mobile_number').unbind();
			 		$('#confirm_mobile_number').unbind();
			 		
			 		$('#mobile_number').bind("cut",function(e) {
			 	        e.preventDefault();
			 	    });
			 		$('#mobile_number').bind("copy",function(e) {
			 	        e.preventDefault();
			 	    });
			 		$('#mobile_number').bind("paste",function(e) {
			 	        e.preventDefault();
			 	    });
			 		
			 		$('#confirm_mobile_number').bind("cut",function(e) {
			 	        e.preventDefault();
			 	    });
			 		$('#confirm_mobile_number').bind("copy",function(e) {
			 	        e.preventDefault();
			 	    });
			 		$('#confirm_mobile_number').bind("paste",function(e) {
			 	        e.preventDefault();
			 	    });		 	    
			 	},
			 	error: function(e) {
			 	}
		 		});
	 }
	 
	 scrollToBottom();
}

 
 function showPersonalInfo(){
	 
	 enableField("submitButton");
	 $("#submitButton").css('background-color', 'buttonface');
		
	 clearHtml("personal_info_div","response_div");
	 clearColegeSelection();
	 resetAvailableSeat();
	 $("#response_div").hide();
	 
	 
		
	 var valid=validateField("ssc_roll","ssc_board","ssc_year","ssc_reg");
	 if(valid==true){
	 $("#personal_info_div").html("<img src='/board/resources/images/239.gif' />");
		 $.ajax({
			 	//url: 'showPersonalInfo_College',		
			 	url: 'showPersonalInfoDelwar_College',		
			 	type: 'POST',
			 	data: {ssc_roll:$("#ssc_roll").val(),ssc_board:$("#ssc_board").val(),ssc_year:$("#ssc_year").val(),ssc_reg:$("#ssc_reg").val()},
			 	success: function(data) {
			 		$("#personal_info_div").html(data);

			 	},
			 	error: function(e) {
			 	}
		 		});
	 }
}

function showPersonalInfoSMS(){
	 
	 enableField("submitButton");
	 $("#submitButton").css('background-color', 'buttonface');
		
	 clearHtml("personal_info_div","response_div");
	 clearColegeSelection();
	 $("#response_div").hide();
	 
	 
		
	 var valid=validateField("ssc_roll","ssc_board","ssc_year","ssc_reg");
	 if(valid==true){
	 $("#personal_info_div").html("<img src='/board/resources/images/239.gif' />");
		 $.ajax({
			 	url: 'showPersonalInfo_SMS',		
			 	type: 'POST',
			 	data: {ssc_roll:$("#ssc_roll").val(),ssc_board:$("#ssc_board").val(),ssc_year:$("#ssc_year").val(),ssc_reg:$("#ssc_reg").val()},
			 	success: function(data) {
			 		$("#personal_info_div").html(data);

			 	},
			 	error: function(e) {
			 	}
		 		});
	 }
}
 
 function showQuotaInfo(){
	 
	 enableField("submitButton");
	 $("#submitButton").css('background-color', 'buttonface');
		
	 clearHtml("personal_info_div","response_div");
	 clearColegeSelection();
	 $("#response_div").hide();
	 
	 
		
	 var valid=validateField("ssc_roll","ssc_board","ssc_year","ssc_reg");
	 if(valid==true){
	 $("#personal_info_div").html("<img src='/board/resources/images/239.gif' />");
		 $.ajax({
			 	url: 'showQuotaInfo_College',		
			 	type: 'POST',
			 	data: {ssc_roll:$("#ssc_roll").val(),ssc_board:$("#ssc_board").val(),ssc_year:$("#ssc_year").val(),ssc_reg:$("#ssc_reg").val()},
			 	success: function(data) {
			 		$("#personal_info_div").html(data);

			 	},
			 	error: function(e) {
			 	}
		 		});
	 }
}
 
 function editQuota(){
	 
	 enableField("submitButton");
	 $("#submitButton").css('background-color', 'buttonface');
		
	// clearHtml("personal_info_div","response_div");
//	 clearColegeSelection();
//	 $("#response_div").hide();
	 
	 
		
	 var valid=validateField("ssc_roll","ssc_board","ssc_year","ssc_reg");
	 if(valid==true){
	 $("#loader_div").html("<img src='/board/resources/images/239.gif' />");
		 $.ajax({
			 	url: 'editQuota_College',		
			 	type: 'POST',
			 	data: {quota_ff:$("#quotaFF").val(),quota_eq:$("#quotaEQ").val(),
			 		quota_bksp:$("#quotaBKSP").val(),quota_expatriate:$("#quotaEX").val(),
			 		application_id:$("#application_id").val()},
			 	success: function(data) {
			 		$("#personal_info_div").html(data);

			 	},
			 	error: function(e) {
			 	}
		 		});
	 }
}

function validateNewSscInfo(){
	 enableField("submitButton");
	 $("#submitButton").css('background-color', 'buttonface');
		
	 clearHtml("personal_info_div","response_div");
	 clearColegeSelection();
	 $("#response_div").hide();
	 
	 
		
	 var valid=validateField("ssc_roll","ssc_board","ssc_year","ssc_reg");
	 if(valid==true){
	 $("#personal_info_div").html("<img src='/board/resources/images/239.gif' />");
		 $.ajax({
			 	url: 'applicationInfoCheckNew',		
			 	type: 'POST',
			 	data: {ssc_roll:$("#ssc_roll").val(),ssc_board:$("#ssc_board").val(),ssc_year:$("#ssc_year").val(),ssc_reg:$("#ssc_reg").val()},
			 	success: function(data) {
			 		$("#personal_info_div").html(data);
			 		$('#mobile_number').unbind();
			 		$('#confirm_mobile_number').unbind();
			 		
			 		$('#mobile_number').bind("cut",function(e) {
			 	        e.preventDefault();
			 	    });
			 		$('#mobile_number').bind("copy",function(e) {
			 	        e.preventDefault();
			 	    });
			 		$('#mobile_number').bind("paste",function(e) {
			 	        e.preventDefault();
			 	    });
			 		
			 		$('#confirm_mobile_number').bind("cut",function(e) {
			 	        e.preventDefault();
			 	    });
			 		$('#confirm_mobile_number').bind("copy",function(e) {
			 	        e.preventDefault();
			 	    });
			 		$('#confirm_mobile_number').bind("paste",function(e) {
			 	        e.preventDefault();
			 	    });
			 	},
			 	error: function(e) {
			 	}
		 		});
	 }
}
 
function getSscInfo_Cancel(){
	 
	 enableField("submitButton");
	 $("#submitButton").css('background-color', 'buttonface');

	 $("#response_div").hide();
	 
	 
		
	 var valid=validateField("ssc_roll","ssc_board","ssc_year");
	 if(valid==true){
	 $("#personal_info_div").html("<img src='/board/resources/images/239.gif' />");
		 $.ajax({
			 	url: 'getApplicantForCancel',		
			 	type: 'POST',
			 	data: {ssc_roll:$("#ssc_roll").val(),ssc_board:$("#ssc_board").val(),ssc_year:$("#ssc_year").val()},
			 	success: function(data) {
			 	//alert(data);
			 		$("#personal_info_div").html(data);
			 		
			 	},
			 	error: function(e) {
			 	}
		 		});
	 }
}
 
 
 
function collegeSelection(){
	clearSVG();
	resetAvailableSeat();
	deleteChoice(1,'O');

	var valid=validateField("mobile_number","confirm_mobile_number"/*,"quotaYN"*/);
	if(valid==true){
		if($("#mobile_number").val()!=$("#confirm_mobile_number").val()){
			valid=false;
			$("#mobile_number").css("border", "2px solid red");
			$("#confirm_mobile_number").css("border", "2px solid red");
		}
		else{
			
			  if(validateMobileNumber($("#mobile_number").val())==false){
				  valid=false;
				  $("#mobile_number").css("border", "2px solid red");
			  } else
				  $("#mobile_number").css("border", "1px solid #add9e4");
			  
			  if(validateMobileNumber($("#confirm_mobile_number").val())==false){
				  valid=false;
				  $("#confirm_mobile_number").css("border", "2px solid red");
			  } else
				  $("#confirm_mobile_number").css("border", "1px solid #add9e4");
			  
		}
	}

	

	if(valid){
		$("#college_selection_div").show();
		scrollToBottom();

	}

	
}

function collegeSelection_notAdmitted()
{
	clearSVG();
	resetAvailableSeat();
	deleteChoice(1,'O');
	
		$("#college_selection_div").show();
		scrollToBottom();
}

function collegeSelectionEdit(){

	
	 enableField("submitButton");
	 $("#submitButton").css('background-color', 'buttonface');
		
	 clearHtml("college_selection_div");
	 clearColegeSelection();
	 $("#response_div").hide();
	document.getElementById("college_selection_div").style.minHeight = "400px"
	 
	 
		

	 $("#college_selection_div").html("<img src='/board/resources/images/239.gif' />");
		 $.ajax({
			 	url: 'editChoiceList_College',		
			 	type: 'POST',
			 	data: {ssc_roll:$("#ssc_roll").val(),ssc_board:$("#ssc_board").val(),ssc_year:$("#ssc_year").val(),ssc_reg:$("#ssc_reg").val(),application_id:$("#application_id").val()},
			 	success: function(data) {
			 		$("#college_selection_div").html(data);
			 
			 	},
			 	error: function(e) {
			 	}
		 		});
	
	

		       $("#college_selection_div").show();
		       scrollToBottom();
	
	
}

function fetchTahanList(){
	clearSelectBox("thana_id","college_id","shift_id","version_id","group_id");
	clearField("education_quota");
	disableCollegeInfoComplainButton();
	 $.ajax({
		 	url: 'getThanas',		
		 	type: 'POST',
		 	data: {district_id:$("#district_id").val()},
		 	success: function(data) {
		 		//console.log(data);
		 		disableCollegeInfoComplainButton();
		 		setThanaSelectBoxData(data);
		 	},
		 	error: function(e) {
		 	}
	 		});
	 
}

function fetchCollegeList(search_type){
//alert(search_type);
	clearSelectBox("college_id","shift_id","version_id","group_id");
	clearField("education_quota");
	disableCollegeInfoComplainButton();
	if(search_type=="by_thana"){
		 if($("#thana_id").val()=="" || $("#helper_board_id").val()==""){
			return;
		 }
	 }
	 if(search_type=="by_district"){
		 if($("#district_id").val()=="" || $("#helper_board_id").val()==""){
			return;
		 }
	 }
	 
	 $.ajax({
		 	url: 'getColleges',		
		 	type: 'POST',
		 	data: {eiin:$("#eiin").val(),district_id:$("#district_id").val(),thana_id:$("#thana_id").val(),helper_board_id:$("#helper_board_id").val(),college_search_type:search_type},
		 	success: function(data) {
		 		//console.log(data);
		 		disableCollegeInfoComplainButton();
		 		setCollegeSelectBoxData(data,search_type);
		 			 if(search_type=="by_district"){
						//var sel = document.getElementById('college_id');
						//var event = document.createEvent('college_id');
 						//event.initMouseEvent('mousedown', true, true, window);
 						//countries.dispatchEvent(event);
					 }
		 	},
		 	error: function(e) {
		 	}
	 		});
	 
}
function setCollegeSelectBoxData(data,search_type){
	//snapshot = Defiant.getSnapshot(data);
	
	var colleges = JSON.search(data, '//colleges');
	
	for (var i=0; i<colleges.length; i++) {
	     $('#college_id')
	         .append($("<option></option>")
	         .attr("value",colleges[i].eiin)
	         .text(colleges[i].college_name));
	}
	if(search_type=="by_eiin"){
		var dist_id = JSON.search(data, '//dist_id');
		var helper_board_id = JSON.search(data, '//helper_board_id');
		$("#thana_id").val(dist_id);
		$("#helper_board_id").val(helper_board_id);
		$("#college_id").val($("#eiin").val());
		getCollegeSVGInfo($("#eiin").val());
	 }
}

function setThanaSelectBoxData(data){
	//snapshot = Defiant.getSnapshot(data);
	
	var thanas = JSON.search(data, '//thana');
	
	for (var i=0; i<thanas.length; i++) {
	     $('#thana_id')
	         .append($("<option></option>")
	         .attr("value",thanas[i].thana_id)
	         .text(thanas[i].thana_name));
	}
}

/** Old Implementation with static javascript json content **/
/*
function fetchCollegeList(){

var district_id=$("#district_id").val();
clearCollegeList();
if(district_id=="")
	return;	


var colleges = JSON.search(snapshot, '//*[dist="'+district_id+'"]');
 
for (var i=0; i<colleges.length; i++) {
    //console.log(colleges[i].name+'['+colleges[i].eiin+']' );
     $('#college_id')
         .append($("<option></option>")
         .attr("value",colleges[i].eiin)
         .text(colleges[i].name));
}

}


function districtCollegeListFromEiin(eiin){
	
	var district_id = JSON.search(snapshot, '//*[eiin="'+eiin+'"]/dist');
	$("#district_id").val(district_id);
	fetchCollegeList();
	$("#college_id").val(eiin);
	getCollegeSVGInfo();
}
*/

function College_Name(abc) {
	//d = document.getElementById("college_id").value.txt;
	//alert(d);
	
	var selectedText = abc.options[abc.selectedIndex].innerHTML;
	currentCollegeName=selectedText.substring(0, selectedText.lastIndexOf("["));
    currentCollegeEIIN=selectedText.substring(selectedText.lastIndexOf("[")+1, selectedText.lastIndexOf("]"));
    //var selectedValue = abc.value;
    //alert("Selected Text: " + selectedText + " Value: " + selectedValue);
	document.getElementById("c_name").innerHTML=selectedText;
	}

function getCollegeSVGInfo(eiin1){
	//College_Name(eiin1);
	var eiin = eiin1;
	//alert(eiin);
	//var eiin = eiin1.value;
	
	$("#eiin").val(eiin);
	
	clearSelectBox("shift_id","version_id","group_id");
	resetAvailableSeat();
	//clearField("available_seat","education_quota");
	
	if(eiin=="") return;	
	 $.ajax({
	  url: 'getCollegeSVG',
	  type: 'POST',
	  data: {eiin:eiin},
	  success: function(data) {
		enableCollegeInfoComplainButton();
		collegeSVG=data;
		//alert(collegeSVG);
		//console.log(collegeSVG);
		var shift = data.shift;
		
		collegeView(data);
		
		createShiftSelectOption(shift);
	  },
	  error: function(e) {
	  }
});
}
function createShiftSelectOption(shifts){

 if(typeof shifts === "undefined")
	 return;
 
 for(var i=0;i<shifts.length;i++)
  {
    $('#shift_id')
         .append($("<option></option>")
         .attr("value",shifts[i])
         .text(arrShift[shifts[i]]));
  }
 $('#shift_id').val("");
}
function loadVersions(){
	resetAvailableSeat();
	clearSelectBox("version_id","group_id");
	clearField("education_quota");
	emptySelectBox("special_quota");
	var shift_id=$("#shift_id").val();
	var versionList=collegeSVG.version;
	var v=versionList[shift_id];
	if(typeof v === "undefined")
		 return;
	v=v.split(", ");
	
	for(var i=0;i<v.length;i++)
  	{
    	$('#version_id')
         .append($("<option></option>")
         .attr("value",v[i])
         .text(arrVersion[v[i]]));
 	}
	$('#version_id').val("");
	
 	
}

function loadGroups(){
	resetAvailableSeat();
	clearSelectBox("group_id");
	clearField("education_quota");
	emptySelectBox("special_quota");
	
	var shift_id=$("#shift_id").val();
	var version_id=$("#version_id").val();
	var groupList=collegeSVG.group;
	var g=groupList[shift_id+","+version_id];
	if(typeof g === "undefined")
		 return;
	
	g=g.split(", ");
	
	for(var i=0;i<g.length;i++)
  	{
    	$('#group_id')
         .append($("<option></option>")
         .attr("value",g[i])
         .text(arrGroup[g[i]]));
 	}	
	
	var groupList = $('#group_id option');

	groupList.sort(function(a,b){
	    a = a.value;
	    b = b.value;
	 
	    return a-b;
	});
	$("#group_id").html(groupList);
	
	$('#group_id').val("");
	
	//setAvailableSeat("");
}
function loadSQ(){
	
	resetAvailableSeat();
	emptySelectBox("special_quota");
	var eiin=$("#college_eiin").val();
	var shift_id=$("#shift_id").val();
	var version_id=$("#version_id").val();
	var group_id=$("#group_id").val();
	
	var sqList=collegeSVG.special_quota;
	var g=sqList[shift_id+","+version_id+','+group_id];
	if(typeof g === "undefined")
		 return;
	
	g=g.split(", ");
	
	for(var i=0;i<g.length;i++)
  	{		
		if(g[i]=="N"){
		 $('#special_quota')
         .append($("<option></option>")
         .attr("value","N")
         .text("No"));
		}
		
		else if(g[i]=="Y"){    		
    		$('#special_quota')
            .append($("<option></option>")
            .attr("value","N")
            .text("No"));
    		
    		$('#special_quota')
            .append($("<option></option>")
            .attr("value","Y")
            .text("Yes"));
    	}
 	}
/*	var availableSeatObj=collegeSVG.available_seat;
	var availableSeat=availableSeatObj[shift_id+","+version_id+','+group_id];	
	setAvailableSeat(availableSeat);*/
	
	
	 $.ajax({
		  url: 'getCollegeSeat',
		  type: 'POST',
		  data: {eiin:eiin,shift_id:shift_id,version_id:version_id,group_id:group_id},
		  success: function(data) {
			  $("#live_total_seat").val(data.total_seat);
			  $("#live_available_seat").val(data.available_seat);

		  },
		  error: function(e) {
		  }
	});
}


function collegeView(tmp)
{
	//alert('adnan');
		sshowsift.length = 0;
		sshowversion.length = 0;
		sshowgroup.length = 0;
		sshowgender.length = 0;
		sshowseatcount.length = 0;
		var tempGender="";
		var shift = tmp.shift;
		
		var strShift = "";
		var strVersion = "";
		var strGroup = "";
		var strGender = "";
		for(var i=0;i<shift.length;i++)
  		{
  			strShift += arrShift[shift[i]] + ", ";
  			//if(i < (shift.length-1))
  				//strShift += ", ";
  			var versionList=tmp.version;
  			var v=versionList[shift[i]];
			if(typeof v === "undefined")
		 		return;
			v=v.split(", ");
			for(var j=0;j<v.length;j++)
  			{
  				if(strVersion.indexOf(arrVersion[v[j]]) == -1)
  				{
	  				strVersion += arrVersion[v[j]] + ", ";
	  				//if(j < (v.length-1))
	  					//strVersion += ", ";
  				}
				var groupList=tmp.group;
				var g=groupList[shift[i]+","+v[j]];	
				if(typeof g === "undefined")
					 return;
				g=g.split(", ");					
				for(var k=0;k<g.length;k++)
		  		{		  			
		  			checkTotalSeat(shift[i],v[j],g[k]);
		  			sshowsift.push(arrShift[shift[i]]);
		  			sshowversion.push(arrVersion[v[j]]);
		  			sshowgroup.push(arrGroup[g[k]]);
		  			if(checkGender(shift[i],v[j],g[k],'M')!=1 && checkGender(shift[i],v[j],g[k],'F')!=1)
		  				sshowgender.push("Combined");
		  			else if(checkGender(shift[i],v[j],g[k],'M')!=1)
		  				sshowgender.push("Male");
					else if(checkGender(shift[i],v[j],g[k],'F')!=1)
		  				sshowgender.push("Female");
		  			
		  			if(strGender.indexOf("Male") == -1 && checkGender(shift[i],v[j],g[k],'M')!=1)
		  			{
		  				strGender += "Male, ";
		  			}
		  			if(strGender.indexOf("Female") == -1 && checkGender(shift[i],v[j],g[k],'F')!=1)
		  			{
		  				strGender += "Female, ";
		  			}
		  			if(strGroup.indexOf(arrGroup[g[k]].substring(0, 3)) == -1)
  					{
			  			strGroup += arrGroup[g[k]].substring(0, 3) + ", ";
			  			//if(k < (g.length-1))
			  				//strGroup += ",";
		  			}
		  		}
  					
  			}
  		}
  		//console.log(strGroup);
  		//console.log(strGroup.substring(0, strGroup.length-2));
		$("#sshowsift").html(strShift.substring(0, strShift.length-2));
		$("#sshowversion").html(strVersion.substring(0, strVersion.length-2));
		$("#sshowgroup").html(strGroup.substring(0, strGroup.length-2));
		$("#sshowgender").html(strGender.substring(0, strGender.length-2));
	//console.log(sshowsift.length);
	//console.log(sshowversion.length);
	//console.log(sshowgroup.length);
	//console.log(sshowgender.length);
}//sshowseatcount

function checkTotalSeat(shift_id,version_id,group_id)
{
	var keyArray1=[];
    keyArray1.push(shift_id,version_id,group_id);
    var eKey1=keyArray1.join(",");
    sshowseatcount.push(collegeSVG.available_seat[eKey1]);
//console.log( collegeSVG.available_seat[eKey1] );    
}


function checkGender(shift_id,version_id,group_id,applicant_gender)
{
	

	college_eligibility=[];
    var keyArray=[];
    keyArray.push(shift_id,version_id,group_id,applicant_gender);
    var eKey=keyArray.join("");
    college_eligibility =JSON.search(collegeSVG, '//eligibility[key="'+eKey+'"]');
    if(college_eligibility.length==0) {
    	college_eligibility=[];
    	keyArray=[];
    	keyArray.push(shift_id,version_id,group_id,"C");
    	eKey=keyArray.join("");
    	college_eligibility =JSON.search(collegeSVG, '//eligibility[key="'+eKey+'"]');
    	if(college_eligibility.length==0) 
    		return 1;
    }
}

function resetAvailableSeat(availableSeat){
	
	   $("#live_total_seat").val(""); 
		$("#live_available_seat").val("");
}
/*function setAvailableSeat(availableSeat){
	
	if(document.getElementById("live_available_seat"))
		$("#live_available_seat").val(availableSeat);
}*/



//Add more button
//'O' - > Open User; 'A' - > A for Applicant's Account ; 'R' -> Release Slip Section; 'S' -> Second Phase Application
function addChoice(from_where){
 	
 	//var eiin=$("#college_id").val();
 	var eiin=$("#college_eiin").val();
 	var college_name=$("#college_name").val();
 	//alert(eiin+college_name);
 	var own_eiin=$("#p_eiin").val();
 	//var college_name=$("#college_id option:selected").text();

 	var shift_id=$("#shift_id").val();
 	var shift_name=$("#shift_id option:selected").text();
 	var version_id=$("#version_id").val();
 	var version_name=$("#version_id option:selected").text();
 	var group_id=$("#group_id").val();
 	var group_name=$("#group_id option:selected").text();
 	var special_quota=$("#special_quota").val();
 	var education_quota="";
/* 	if(from_where=="R" || from_where=="S"){
 		education_quota=$("#education_quota").val();
 		if(education_quota==""){
 			alert("Please select Education Quota");
 			return;
 		}
 		var available_seat=$("#available_seat").val();
 		if(parseInt(available_seat,10)<=0){
 			alert("Sorry, the selected college choice (Shift,Version,Group) has no more available seat.");
 			return;
 		}
 	}*/
 	var available_seat=$("#live_available_seat").val();
		if(parseInt(available_seat,10)<=0){
			alert("Sorry, the selected college choice (Shift,Version,Group) has no more available seat.");
			return;
		}

	if(eiin==""){
		 alert("session time out.Please login and try again .");
		 return;
	}
 	var valid_code=validateSelection(eiin,college_name,shift_id,shift_name,version_id,version_name,group_id,group_name);
 	var valid_eligibility=validateEligibility(eiin,own_eiin,shift_id,version_id,group_id,special_quota,$("#p_gender").val(),$("#p_gpa").val());

 	var ssc_hsc_group=$("#p_group_id").val()+"|"+group_id;
 	
 	if(valid_code==1){
 		 alert("Please select shift/version/group information of a college.");
 		 return;
 	}
 	else if(valid_code==2){
		 alert("A duplicate selection has been found.");
		 return;
	}
 	else if(arrSscHscGroupMap.indexOf(ssc_hsc_group)<0){
 		alert("Sorry, This applicant is not eligible to apply to the selected group.");
 		return;
 	}
 	else if(valid_eligibility==1){
		 alert("Sorry, the selected choice (gender & other criteria) is not valid for this applicant.");
		 return;
	}
 	else if(valid_eligibility==2 || valid_eligibility==3 || valid_eligibility==4 || valid_eligibility==5){
		 alert("This applicant does not meet the Minimum GPA Requirement for the selected choice.");
		 return;
	}
 
  
 	college_map[eiin] = eiin;
 	//var distinctCollegeCount=getDistinctCollegeCount();
 	/* changed by adnan---- need to change validation for 10th unique college*/
/*	 if(distinctCollegeCount>10){
		alert("You have already selected 10 different colleges.");
	    return;
	 }*/
	/* changed by adnan*/
/* 	if(from_where=="R" || from_where=="S"){ 		
 		 if(distinctCollegeCount>10){
 			alert("You have already selected 10 different colleges.");
 			return;
 		}
 	}*/
 	
 	var rowCount = $('#selection_row_table tr').length;
    var rowIndex=rowCount;
   // alert(rowCount);
    if(rowCount>1){
		alert("You have already added a choice.");
	    return;
	 }
 
    var row = [];
    if(from_where=='A')
    	row.push("<tr class='unpaid' id='row_"+rowIndex+"'>");
    else if(from_where=='O')
    	row.push("<tr class='paid' id='row_"+rowIndex+"'>");
    else if(from_where=='R' || from_where=='S')
    	row.push("<tr class='release_slip' id='row_"+rowIndex+"'>");
    
    row.push("<td align='center'>"+rowIndex+"</td>",
		  "<td align='left' style='padding-left: 2px;'>"+college_name+"</td>",
		  "<td align='center'>"+eiin+"</td>",
		  "<td align='left'  style='padding-left: 2px;'>"+shift_name+"</td>",
		  "<td align='left'  style='padding-left: 2px;'>"+version_name+"</td>",
		  "<td align='left'  style='padding-left: 2px;'>"+group_name+"</td>",
		  "<td align='center'>"+special_quota+"</td>");
    
    if(from_where=='R' || from_where=='S')
    	row.push("<td align='center'>"+education_quota+"</td>");
    
    if(from_where=='A')
    	row.push("<td align='center'><img src='/board/resources/images/laptop.png' /></td>");
    
    /*row.push("<td align='center'><input type='text' name='choice["+rowIndex+"].priority' id='priority_"+rowIndex+"' value='"+rowIndex+"' style='width:30px;text-align:center;'></td>",*/
    		row.push("<td align='center'>",
		  		"<input type='hidden' name='choice["+rowIndex+"].eiin' value='"+eiin+"' />",
		  		"<input type='hidden' name='choice["+rowIndex+"].shift_id' value='"+shift_id+"' />",
		  		"<input type='hidden' name='choice["+rowIndex+"].version_id' value='"+version_id+"' />",
		  		"<input type='hidden' name='choice["+rowIndex+"].group_id' value='"+group_id+"' />",
		  		"<input type='hidden' name='choice["+rowIndex+"].special_quota' value='"+special_quota+"' />");
    
    if(from_where=='R' || from_where=='S')
    	row.push("<input type='hidden' name='choice["+rowIndex+"].education_quota' value='"+education_quota+"' />");    
    
    if(from_where=='A' || from_where=='S')
    	row.push("<input type='hidden' name='choice["+rowIndex+"].via' value='W' />");

    row.push("<img class='newlyAddedRow' id='img_delete_"+rowIndex+"' src='/board/resources/images/trash.gif' style='cursor:pointer' onclick=\"deleteChoice("+rowIndex+",'"+from_where+"')\"/>",
		  "</td>",
		  "</tr>");
    ///board/resources/images/cross_16_16.png

    var row_str=row.join("");
 	$('#selection_row_table tr').last().after(row_str);

}
 	
 
 
function deleteChoice(row_id,from_where){
	//alert(row_id+from_where);

	var priority=$("#priority_"+row_id).val();
	
	var $rows = $("#selection_row_table tr").each(function(index) {
		 
		 if(index>0 && isInteger($("#priority_"+index).val())){
			 
			 if($("#priority_"+index).val()>priority)
			 $("#priority_"+index).val($("#priority_"+index).val()-1);
		 }
	});
	
	$('table#selection_row_table tr#row_'+row_id).remove();
	
	orderSerialAndCorrectHtmlElementNamesEdit(from_where);
	
}
 

function orderSerialAndCorrectHtmlElementNames(from_where){
 
    college_map={};
    var arr_index=0;
    var priority_index;
    var post_fields_index;
    if(from_where=='A' || from_where=='R' ||  from_where=='S'){
    	priority_index=8;
    	post_fields_index=9;
    }
    else{
    	priority_index=7;
    	post_fields_index=8;
    }
	var $rows = $("#selection_row_table tr").each(function(index) {
	if(index>0) {
		arr_index=index;
		$(this).attr('id','row_'+index);
  		$cells = $(this).find("td");
		
  	    $cells.each(function(cellIndex){   
    		if(cellIndex==0){
      		        $(this).html(index);      		        
    		}
    		else if(cellIndex==2)
    			college_map[$(this).html()] = $(this).html();
    		else if(cellIndex==priority_index){
    			$(this).children('input').each(function () {
    				if($(this).attr('name').indexOf("priority")>0) {
				      $(this).attr('name','choice['+arr_index+'].priority');
    				  $(this).attr('id','priority_'+index);
    				  //$(this).attr('value',index);
    				}
    			
    			});
    		}
    		else if(cellIndex==post_fields_index){
    			$(this).children('input').each(function () {
				    if($(this).attr('name').indexOf("eiin")>0)
				      $(this).attr('name','choice['+arr_index+'].eiin');
				    else if($(this).attr('name').indexOf("shift_id")>0)
				      $(this).attr('name','choice['+arr_index+'].shift_id');
				    else if($(this).attr('name').indexOf("version_id")>0)
				      $(this).attr('name','choice['+arr_index+'].version_id');
				    else if($(this).attr('name').indexOf("group_id")>0)
				      $(this).attr('name','choice['+arr_index+'].group_id');
				    else if($(this).attr('name').indexOf("special_quota")>0)
					      $(this).attr('name','choice['+arr_index+'].special_quota');
				    else if($(this).attr('name').indexOf("education_quota")>0)
					      $(this).attr('name','choice['+arr_index+'].education_quota');
				    else if($(this).attr('name').indexOf("via")>0)
					      $(this).attr('name','choice['+arr_index+'].via');
				    
				
    			});
    			$(this).children('img').each(function () {
    				if(from_where=="A" || from_where=="R" || from_where=="S")
    					$(this).attr('onclick',"deleteChoice("+index+",'A')");
    				else
    					$(this).attr('onclick','deleteChoice('+index+')');
    				
    				$(this).attr('id','img_delete_'+index+'');
    				
//    				row.push("<img id='img_delete_"+rowIndex+"' src='/board/resources/images/trash.gif' style='cursor:pointer' onclick=\"deleteChoice("+rowIndex+",'"+from_where+"')\"/>",
    						
    						
    			});
    			
    		}
  	    }); 	//End of cells.each 
	} 			//End of If   
	}); 		//End of rows.each
}


function orderSerialAndCorrectHtmlElementNamesEdit(){
	 
    college_map={};
    var arr_index=0;
    var priority_index=6;
    var post_fields_index=7;
    
/*    if(from_where=='A' || from_where=='R' ||  from_where=='S'){
    	priority_index=8;
    	post_fields_index=9;
    }
    else{
    	priority_index=7;
    	post_fields_index=8;
    }*/
	var $rows = $("#selection_row_table tr").each(function(index) {
	if(index>0) {
		arr_index=index-1;
		$(this).attr('id','row_'+index);
  		$cells = $(this).find("td");
		
  	    $cells.each(function(cellIndex){ 
  	    //	alert(cellIndex);
    		if(cellIndex==0){
      		        $(this).html(index);      		        
    		}
    		else if(cellIndex==2)
    			college_map[$(this).html()] = $(this).html();
    		else if(cellIndex==priority_index){
    			$(this).children('input').each(function () {
    				if($(this).attr('name').indexOf("priority")>0) {
				      $(this).attr('name','choice['+arr_index+'].priority');
    				  $(this).attr('id','priority_'+index);
    				  //$(this).attr('value',index);
    				}
    			
    			});
    		}
    		else if(cellIndex==post_fields_index){
    			$(this).children('input').each(function () {
				    if($(this).attr('name').indexOf("eiin")>0)
				      $(this).attr('name','choice['+arr_index+'].eiin');
				    else if($(this).attr('name').indexOf("shift_id")>0)
				      $(this).attr('name','choice['+arr_index+'].shift_id');
				    else if($(this).attr('name').indexOf("version_id")>0)
				      $(this).attr('name','choice['+arr_index+'].version_id');
				    else if($(this).attr('name').indexOf("group_id")>0)
				      $(this).attr('name','choice['+arr_index+'].group_id');
				    else if($(this).attr('name').indexOf("special_quota")>0)
					      $(this).attr('name','choice['+arr_index+'].special_quota');
				    else if($(this).attr('name').indexOf("education_quota")>0)
					      $(this).attr('name','choice['+arr_index+'].education_quota');
				    else if($(this).attr('name').indexOf("via")>0)
					      $(this).attr('name','choice['+arr_index+'].via');
				    
				
    			});
    			$(this).children('img').each(function () {
/*    				if(from_where=="A" || from_where=="R" || from_where=="S")
    					$(this).attr('onclick',"deleteChoice("+index+",'A')");
    				else*/
    					$(this).attr('onclick','deleteChoice('+index+')');
    				
    				$(this).attr('id','img_delete_'+index+'');
    				
//    				row.push("<img id='img_delete_"+rowIndex+"' src='/board/resources/images/trash.gif' style='cursor:pointer' onclick=\"deleteChoice("+rowIndex+",'"+from_where+"')\"/>",
    						
    						
    			});
    			
    		}
  	    }); 	//End of cells.each 
	} 			//End of If   
	}); 		//End of rows.each
}



 
function validateSelection(eiin,college_name,shift_id,shift_name,version_id,version_name,group_id,group_name){
	var add_data_key = [];
	var existing_data_key=[];
	var duplicate=false;
 	var valid_collegeSelection=validateField("shift_id","version_id","group_id");
 	//var valid_eiinSelection=validateField("eiin","shift_id","version_id","group_id"); 	
    if(valid_collegeSelection==false)
     return 1;
 
    
 	add_data_key.push(eiin,shift_name,version_name,group_name);
    var aKey=add_data_key.join("");
    var eKey;
     
    $("#selection_row_table tr").each(function(index) {
		if(index>0) {
		    existing_data_key=[];
		    existing_data_key.push(
		    	$(this).find('td:eq(2)').html(),
		        $(this).find('td:eq(3)').html(),
		        $(this).find('td:eq(4)').html(),
		        $(this).find('td:eq(5)').html());
			eKey=existing_data_key.join("");
			if(aKey==eKey){
				duplicate=true;
			}
	    } 
    });
    if(duplicate==true)
    	return 2;
    else
    	return 0;
 }

function validateEligibility(eiin,own_eiin,shift_id,version_id,group_id,special_quota,applicant_gender,applicant_gpa)
{
	college_eligibility=[];
    var keyArray=[];
    keyArray.push(shift_id,version_id,group_id,applicant_gender);
    var eKey=keyArray.join("");
    college_eligibility =JSON.search(collegeSVG, '//eligibility[key="'+eKey+'"]');
    if(college_eligibility.length==0) {
    	college_eligibility=[];
    	keyArray=[];
    	keyArray.push(shift_id,version_id,group_id,"C");
    	eKey=keyArray.join("");
    	college_eligibility =JSON.search(collegeSVG, '//eligibility[key="'+eKey+'"]');
    	if(college_eligibility.length==0) 
    		return 1;
    }
    if(special_quota=='Y' && college_eligibility[0].spc_yn=='Y'){
    	if(applicant_gpa>=college_eligibility[0].spc_gpa)
    		return 0;
    	if(eiin==own_eiin){
    		if(applicant_gpa>=college_eligibility[0].own_gpa)
        		return 0;
    		else
    			return 2;
    	}
    	else
    		return 3;
    }
    else if(eiin==own_eiin){ 
    	if(applicant_gpa>=college_eligibility[0].own_gpa)
    		return 0;
    	else
    		return 4;
    }
    else if(applicant_gpa>=college_eligibility[0].gpa)
    	return 0;
    
    else return 5;
    
    
}

 function getDistinctCollegeCount(){
 	return Object.keys(college_map).length;
 	/*
 	if(Object.length>0)
 		return Object.keys(college_map).length;
 	else
 		return 0;
 	*/
 }
 

function submitApplication_College(){
	
	
	var rowCount = $('#selection_row_table tr').length;
	if(rowCount<2){
		alert("Please select at least one one choice for  submission.");
		return;
	}
	if(rowCount>2){
		alert("You can not add more than one choice .");
		return;
	}

	var fields=["p_student_name","p_ssc_roll","p_ssc_reg","p_passing_year","p_gpa","p_mobile_number","p_board_name","p_student_name","p_father_name","p_mother_name","p_gender_name"];	
	/*var priorityOk=isPriorityOk('O');
	if(priorityOk==false){
		alert("Please correct your priority and then submit again.");
		reutrn;
	}	*/
	enableField.apply(this,fields);
	orderSerialAndCorrectHtmlElementNames();
	var data = $('#applicationForm').serializeArray();
	$("#submitOverlay").html("<img style='position: absolute;left: 270px;right: 0;top: 100px;' src='/board/resources/images/239.gif'>");
	$("#submitOverlay").show();
	disableField("submitButton");
	$("#submitButton").css('background-color', 'gray');
	
	cleanPrintHtmlData();
	//alert("adnan");
	
	$.ajax({
		url: 'submitApplication_College',
		type: 'POST',
		data: data,
		success: function(data) {
			if (typeof data == 'undefined')
			{
				alert("Please try again");
				return false;
			}
			$("#submitOverlay").hide();
			enableField("submitButton");
			$("#submitButton").css('background-color', 'buttonface');
			if(data.status=='OK'){
				//console.log(data);
				alert(data.message);
				//setDataForHtmlReport(data,data.application_id,data.password);
				clearHtml("personal_info_div");
				clearHtml("txid_input_div");
				clearColegeSelection();
								
				$("#response_div").html(getSuccessResponseContent(data.sscInfo.student_name));
				$("#response_div").show();
				//getNewCapchaServlet();

			}
			else if(data.status=='INVALID'){
				//clearAll();
				var div_content = [];
			    div_content.push(data.message);
			    
			    var div_content_str=div_content.join("");
			    $("#response_div").html(div_content_str);
			    $("#response_div").show();
			    scrollToBottom();
			    //$("#college_selection_div").hide();
			}
/*			else if(data.status=='VALID'){
				alert("TEST OK");
				
			}*/
			else{
				alert(data.message);
				//getNewCapchaServlet();
			}
	},
	error: function(e) {
		//called when there is an error
		//console.log(e.message);
	}
	});
	
	disableField.apply(this,fields);
}

function submitApplicationEdit_College(){
	
	var rowCount = $('#selection_row_table tr').length;
	if(rowCount<2){
		alert("Please select at least one college for your application submission.");
		return;
	}
/*	if($("#security_code").val()==""){
		alert("Please provide security code.");
		return;
	}*/
	var fields=["p_student_name","p_ssc_roll","p_ssc_reg","p_passing_year","p_gpa","p_mobile_number","p_board_name","p_student_name","p_father_name","p_mother_name","p_gender_name"
	,"applicant.application_info.quota_ff","applicant.application_info.quota_eq","applicant.application_info.quota_bksp","applicant.application_info.quota_expatriate"];	
	/*var priorityOk=isPriorityOk('O');
	if(priorityOk==false){
		alert("Please correct your priority and then submit again.");
		reutrn;
	}	*/
	enableField.apply(this,fields);
	orderSerialAndCorrectHtmlElementNamesEdit();
	
	var data = $('#applicationForm').serializeArray();
	$("#submitOverlay").html("<img style='position: absolute;left: 270px;right: 0;top: 100px;' src='/board/resources/images/239.gif'>");
	$("#submitOverlay").show();
	disableField("submitButton");
	$("#submitButton").css('background-color', 'gray');
	
	cleanPrintHtmlData();
	//alert('Alter Msg');
	$.ajax({
		url: 'submitApplicationEdit_College',
		type: 'POST',
		data: data,
		success: function(data) {
			if (typeof data == 'undefined')
			{
				alert("Please try again");
				return false;
			}		
			$("#submitOverlay").hide();
			enableField("submitButton");
			$("#submitButton").css('background-color', 'buttonface');
			if(data.status=='OK'){
				//console.log(data);
				setDataForHtmlReport(data,data.application_id,data.password);
				clearHtml("personal_info_div");
				clearColegeSelection();
								
				$("#response_div").html(getSuccessResponseContent(data.sscInfo.student_name,data.application_id,data.password));
				$("#response_div").show();
				//getNewCapchaServlet();

			}
			else if(data.status=='INVALID'){
				//clearAll();
				var div_content = [];
			    div_content.push(data.message);
			    
			    var div_content_str=div_content.join("");
			    $("#response_div").html(div_content_str);
			    $("#response_div").show();
			    scrollToBottom();
			    //$("#college_selection_div").hide();
			}
			else{
				alert(data.message);
				//getNewCapchaServlet();
			}
	},
	error: function(e) {
		//called when there is an error
		//console.log(e.message);
	}
	});
	
	//disableField.apply(this,fields);
}

function updateChoiceList(){
	
	var priorityOk=isPriorityOk('A');
	if(priorityOk==false){
		alert("Please correct your priority and then submit again.");
		reutrn;
	}	
	orderSerialAndCorrectHtmlElementNames('A');
	var data = $('#applicationForm').serializeArray();
	//console.log(data);
	$.ajax({
		url: 'updateChoiceList',
		type: 'POST',
		data: data,
		success: function(data) {
			if(data.status=='OK'){
				alert(data.message);
			}
			else if(data.status=='INVALID'){
				alert(data.message);
			}
			else{
				alert(data.message);
			}
	},
	error: function(e) {}
	});
}

function newUpdateChoiceList(){
	var distinctCollegeCount=getDistinctCollegeCount();
	if(distinctCollegeCount>10){
		alert("You can select up to 10 different colleges for your application submission.");
		return;
	}
 	else if(distinctCollegeCount<5)
	{
		alert("Please select at least 5 different colleges for application submission.");
		return;
	}
	
	var priorityOk=isPriorityOk('S');
	if(priorityOk==false){
		alert("Please correct your priority and then submit again.");
		reutrn;
	}	
	orderSerialAndCorrectHtmlElementNames('A');
	var data = $('#applicationForm').serializeArray();
	//console.log(data);
	$.ajax({
		url: 'newUpdateChoiceList',
		type: 'POST',
		data: data,
		success: function(data) {
			if(data.status=='OK'){
				alert(data.message);
			}
			else if(data.status=='INVALID'){
				alert(data.message);
			}
			else{
				alert(data.message);
			}
	},
	error: function(e) {}
	});
}
function updateReleaseSlip(){
	
	var distinctCollegeCount=getDistinctCollegeCount();
	if(distinctCollegeCount>10){
		alert("You can select up to 10 different colleges for application submission.");
		return;
	}
 	else if(distinctCollegeCount<5)
	{
		alert("Please select at least 5 different colleges for application submission.");
		return;
	}
	
	var priorityOk=isPriorityOk('R');
	if(priorityOk==false){
		alert("Please correct your priority and then submit again.");
		reutrn;
	}	
	
	var c = confirm("Are you sure to submit?\n(You may select up to 10 different colleges)");
	if (c == false) {
	    return;
	} 
	
	orderSerialAndCorrectHtmlElementNames('A');
	var data = $('#applicationForm').serializeArray();
	//console.log(data);
	$.ajax({
		url: 'updateReleaseList',
		type: 'POST',
		data: data,
		success: function(data) {
			if(data.status=='OK'){
				alert(data.message);
			}
			else if(data.status=='INVALID'){
				alert(data.message);
			}
			else{
				alert(data.message);
			}
	},
	error: function(e) {}
	});
}

function updateEQInfo(){
	
	var data = $('#applicationForm').serializeArray();
	//console.log(data);
	$.ajax({
		url: 'updateEducationQuotaInfo',
		type: 'POST',
		data: data,
		success: function(data) {
			if(data.status=='OK'){
				alert(data.message);
			}
			else if(data.status=='INVALID'){
				alert(data.message);
			}
			else{
				alert(data.message);
			}
	},
	error: function(e) {}
	});
}

function setDataForHtmlReport(applicant,application_id,password){

	var tmp_quota = '';
	if(applicant.application_info.quota_ff=='Y' || applicant.application_info.quota_ff=='Yes')
		tmp_quota += "FQ,";
	if(applicant.application_info.quota_eq=='Y' || applicant.application_info.quota_eq=='Yes')
		tmp_quota += "EQ,";
	if(applicant.application_info.quota_bksp=='Y')
		tmp_quota += "BP,";
	if(applicant.application_info.quota_expatriate=='Y' || applicant.application_info.quota_expatriate=='Yes')
		tmp_quota += "PQ";
		
	if(	tmp_quota == '')
			tmp_quota ="Nil";
		
		
			//$("#print_app_id").val(application_id);
			//$("#print_password").val(password);
			$("#print_quota").val("FF("+applicant.application_info.quota_ff+")EDU("+applicant.application_info.quota_eq+")");
			$("#print_app_name").val($("#p_student_name").val());
			$("#print_ssc_roll").val($("#p_ssc_roll").val());
			$("#print_ssc_board").val($("#p_board_name").val());
			$("#print_ssc_year").val($("#p_passing_year").val());
			$("#print_ff").val(tmp_quota);
			$("#print_app_id").val($("#p_ssc_reg").val());
			
			
/*			
			if(document.getElementById("quota_ff").checked==true)
				$("#print_ff").val("FQ");
			else
				$("#print_ff").val("");
*/			
			
			
	var row = [];
	var row_str="";
	$("#selection_row_table tr").each(function(index) {
		if(index>0) {
			row.push($(this).find('td:eq(0)').html(),"#",
					 $(this).find('td:eq(1)').html(),"#",
					 $(this).find('td:eq(2)').html(),"#",
					 $(this).find('td:eq(3)').html(),"#",
					 $(this).find('td:eq(4)').html(),"#",
					 $(this).find('td:eq(5)').html(),"#",
					 $(this).find('td:eq(6)').html(),"#",
					 $(this).find('td:eq(7)').children('input').val(),"#","@@"
					);
			row_str=row.join("");
	    } 
    });
	$("#print_choice_list").val(row_str.substring(0, row_str.length-2));
				
}

function setDataForHtmlReportNew(application_id,password){
	
	$("#print_app_id").val(application_id);
	//$("#print_password").val(password);
	$("#print_quota").val($("#print_quota").val());
	$("#print_app_name").val($("#p_student_name").val());
	$("#print_ssc_roll").val($("#p_ssc_roll").val());
	$("#print_ssc_board").val($("#p_board_name").val());
	$("#print_ssc_year").val($("#p_passing_year").val());
	
	if(document.getElementById("quota_ff").checked==true)
		$("#print_ff").val("FQ");
	else
		$("#print_ff").val("");
	
	
	
var row = [];
var row_str="";
$("#selection_row_table tr").each(function(index) {
if(index>0) {
	row.push($(this).find('td:eq(0)').html(),"#",
			 $(this).find('td:eq(1)').html(),"#",
			 $(this).find('td:eq(2)').html(),"#",
			 $(this).find('td:eq(3)').html(),"#",
			 $(this).find('td:eq(4)').html(),"#",
			 $(this).find('td:eq(5)').html(),"#",
			 $(this).find('td:eq(6)').html(),"#",
			 $(this).find('td:eq(7)').html(),"#",
			 $(this).find('td:eq(8)').children('input').val(),"#","@@"
			);
	row_str=row.join("");
} 
});
$("#print_choice_list").val(row_str.substring(0, row_str.length-2));
		
}

function cleanPrintHtmlData(){

	var printHtmlElements=["print_app_id","print_quota","print_app_name","print_ssc_roll","print_ssc_board","print_ssc_year","print_fq","print_eq"];
	clearField.apply(this,printHtmlElements);

}
function isPriorityOk(from_where){
	
	var priority_index;
    if(from_where=='A' || from_where=='R' || from_where=='S'){
    	priority_index=8;
    }
    else{
    	priority_index=7;
    }

	var choice_map={};
	var total_priority= $('#selection_row_table tr').length;
	total_priority=total_priority-1;
	
	var $rows = $("#selection_row_table tr").each(function(index) {
		
		if(index>0){
			var priority=$(this).find('td').eq(priority_index).children('input').val();
			if(isInteger(priority)==false)
				return false;
			
			if(priority<=total_priority && priority>0)
				choice_map[priority] = priority;
		}
	});
	if(Object.keys(choice_map).length!=total_priority){
		return false;
	}
	else
		return true;
}

function getSuccessResponseContent(applicant_name){
	 var div_content = [];
	 div_content.push(
			  "Admission is succcessfully completed for:<br/>",
			  "<b>Applicant Name</b> : "+applicant_name+"</br>",
	          "<br/>");
//			  "<b>Application ID</b> : "+application_id+"</br>",
//			  "<b>Password</b> : "+password+"</br>",
//			  "<br/>",
//			  "<font style='color:red;font-weight:bold'>To Confirm your Application, please pay 150 Taka by a Teletalk Mobile - go to message option and</font><br/>",
//			  "<font style='color:red;font-weight:bold'>type : <font color='green'>CAD</font> {SPACE} <font color='green'>WEB</font> {SPACE}  <font color='green'>"+application_id+"</font> and send to 16222</font>",
//			  "<br/>Please use the above Application Id and Password to log into your account.<br/><br/>",
//			  "<a href='../board/login' style='font-weight:bold;'>Click here</a> to login.<br/><br/>",
//			  "<input type='button' value='Print Version' class='htmlReportBtn' onclick=\"javascript:void window.open('applicantCopyHtmlReport_College','1433313485446','width=780,height=800,toolbar=0,menubar=0,location=0,status=1,scrollbars=1,resizable=1,left=0,top=0');\" />");

	 var div_content_str=div_content.join("");
	 
	 return div_content_str;
}
function getNewSuccessResponseContent(applicant_name,application_id,password,payment_status){
	 var div_content = [];
	 div_content.push(
			  "Dear Applicant,<br/><br/>",
			  "We have successfully received your online Application.<br/>",
			  "<b>Applicant Name</b> : "+applicant_name+"</br>",
			  "<b>Application ID</b> : "+application_id+"</br>",
			  "<b>Password</b> : "+password+"</br>",
			  "<br/>");
	 if(payment_status=="N"){
		 div_content.push("<font style='color:red;font-weight:bold'>To Confirm your Application, please pay 150 Taka by a Teletalk Mobile - go to message option and</font><br/>",
			  "<font style='color:red;font-weight:bold'>type : <font color='green'>CAD</font> {SPACE} <font color='green'>WEB</font> {SPACE}  <font color='green'>"+application_id+"</font> and send to 16222</font>");
	 }
	 else{
		 div_content.push("<font style='color:red;font-weight:bold'>You do not need to pay application fee, as you have paid it earlier.</font><br/>");
	 }
	
	 	div_content.push("<br/>Please use the above Application Id and Password to log into your account.<br/><br/>",
			  "<a href='../board/login' style='font-weight:bold;'>Click here</a> to login.<br/><br/>",
			  "<input type='button' value='Print Version' class='htmlReportBtn' onclick=\"javascript:void window.open('applicantCopyHtmlReportNew','1433313485446','width=780,height=800,toolbar=0,menubar=0,location=0,status=1,scrollbars=1,resizable=1,left=0,top=0');\" />");

	 var div_content_str=div_content.join("");
	 
	 return div_content_str;
}
/*---------- Clear Fields & Html Elements   -------------*/
function clearAll(){
	clearSscInfo();
	clearPersonalInfo();
	clearColegeSelection();
	clearHtml("response_div");
	$("#response_div").hide();
}
function clearSscInfo(){

	clearField("ssc_roll");
	if(document.getElementById("ssc_reg")){
		clearField("ssc_reg");
	}
	var select_fields=["ssc_year","ssc_board"];	
	setSBoxFirstOption.apply(this, select_fields);
	
}
function clearPersonalInfo(){
	clearHtml("personal_info_div");
}
function clearColegeSelection(){	
	clearField("eiin");
	setSBoxFirstOption("district_id","generalized_board_id");
	clearSelectBox("shift_id","version_id","group_id","live_total_seat","live_available_seat");
	clearField("education_quota");
	$('#selection_row_table tr:gt(0)').remove();
	$("#college_selection_div").hide();
	college_map={};
	college_eligibility=[];
	collegeSVG={};
}
function clearSVG()
{
$("#shift_id").val("");
clearSelectBox("version_id","group_id","live_total_seat","live_available_seat");
}

function showHideQuotaType(quotaYN){
	if(quotaYN=='Y')
		$("#quota_type_div").show();
	else
		$("#quota_type_div").hide();
}
/*----------------Common Methods -------------*/
function validateField(){
	
	var isValid=true;
	var element;
	for (var i = 0; i < arguments.length; i++) {
		element=$("#"+arguments[i]);
	if(element && $.trim(element.val())==""){
		cbColor(element,"e");isValid=false;
	}
	else cbColor(element,"v");	  
	}
	return isValid;
}
function cbColor(element,type){
	
	if(type=="e")
		element.css("border", "2px solid red");
	else if(type=="v")
		element.css("border", "1px solid #add9e4");
}
function clearField()
{	 
	for (var i = 0; i < arguments.length; i++) {
	    $("#"+arguments[i]).val("");
	  }
}

function clearSelectBox()
{
	for (var i = 0; i < arguments.length; i++) {
	    $("#"+arguments[i]).find('option:gt(0)').remove();
	  }
}
function emptySelectBox()
{
	for (var i = 0; i < arguments.length; i++) {
	    $("#"+arguments[i]).find('option').remove();
	  }
}
function disableField()
{
	for (var i = 0; i < arguments.length; i++) {
	    $("#"+arguments[i]).attr("disabled", true);  
	  }
}
function enableField()
{
	for (var i = 0; i < arguments.length; i++) {
	    $("#"+arguments[i]).attr("disabled", false);  
	  }
}
function scrollToBottom(){
	$('html, body').animate({scrollTop:$(document).height()}, 'slow');
}
function setSBoxFirstOption(){
	for (var i = 0; i < arguments.length; i++) {
		$("#"+arguments[i]).val(""); 	  
	  }
}
function clearHtml()
{	 
	for (var i = 0; i < arguments.length; i++) {
	    $("#"+arguments[i]).html("");
	  }
}
function isInteger(x) {
	return Math.ceil(x) == Math.floor(x);
}



function validateMobileNumber(mobileNumber) {
    var mob = /^(016|019|013|017|018|015)[0-9]{8}$/;

    if (mob.test(mobileNumber) == false) {
      return false;
    }
    return true;
  }