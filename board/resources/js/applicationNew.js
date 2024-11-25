//var snapshot = Defiant.getSnapshot(college_json);
var snapshot;

/* Application Constant Declaration */
var arrShift = ["-","Morning","Day","Evening"];
var arrVersion = ["-","Bangla","English"];
var arrGroup = ["Science","","Humanities","","Music","Home Economics","Islamic Studies","Agriculture","Business Studies","MADRASA - General","MADRASA - Muzzabid","","MADRASA - Science","","","","","","","","","HSCVOC - Agro Machinery","HSCVOC - Automobile","HSCVOC - Building Maintenance and Construction","HSCVOC - Clothing and Garments Finishing","HSCVOC - Computer Operation and Maintenance","HSCVOC - Drafting Civil","HSCVOC - Electrical Works and Maintenance","HSCVOC - Electronic Control and Communication","HSCVOC - Fish Culture and Breeding","HSCVOC - Machine Tools Operation and Maintenance","HSCVOC - Poultry Rearing and Farming","HSCVOC - Refrigeration and Air-conditioning","HSCVOC - Welding and Fabrication","HSCVOC - Industrial Wood Working","HSCVOC - Wet Processing","HSCVOC - Yarn and Fabric Manufacturing","HSCBM - Accounting","HSCBM - Banking","HSCBM - Computer Operation","HSCBM - Entrepreneurship Development","HSCBM - Secretarial Science","DCOM - Shorthand","DCOM - Accounting","HSCVOC - Warehouse and Storekeeping","HSCVOC - Home Economics","","","","",""];
var arrSscHscGroupMap=["77|33", "29|0", "29|2", "77|30", "29|4", "23|37", "77|21", "21|40", "21|41", "21|42", "21|43", "66|34", "66|33", "66|36", "27|37", "66|35", "27|38", "66|30", "27|39", "66|32", "66|31", "24|37", "24|38", "66|26", "66|27", "66|28", "66|29", "21|37", "21|39", "21|38", "95|8", "47|5", "47|6", "47|7", "47|8", "53|7", "47|2", "53|6", "47|4", "53|8", "95|0", "37|43", "37|42", "53|2", "95|2", "37|41", "53|5", "66|45", "37|40", "47|0", "53|4", "66|44", "95|4", "29|7", "66|43", "95|5", "29|8", "24|39", "66|42", "95|6", "93|0", "29|5", "66|41", "95|7", "29|6", "53|0", "66|40", "93|2", "93|5", "93|4", "93|7", "24|42", "66|39", "93|6", "24|43", "66|37", "93|8", "66|38", "49|4", "24|40", "49|6", "24|41", "49|5", "49|0", "49|2", "49|8", "49|7", "64|2", "64|5", "64|4", "64|0", "27|43", "27|42", "27|41", "27|40", "64|7", "64|6", "64|8", "62|8", "62|4", "62|5", "62|6", "62|7", "62|0", "62|2", "34|8", "32|2", "34|7", "32|4", "34|4", "34|6", "32|0", "34|5", "34|0", "34|2", "38|8", "32|6", "38|7", "32|5", "32|8", "32|7", "38|5", "66|5", "38|6", "66|4", "66|7", "38|4", "66|6", "38|2", "66|8", "38|0", "12|10", "66|0", "12|12", "66|2", "68|0", "68|2", "68|4", "68|5", "68|6", "68|7", "68|8", "78|40", "78|43", "78|42", "78|41", "80|31", "78|38", "78|39", "80|38", "80|37", "80|39", "78|37", "78|31", "59|40", "59|41", "59|42", "59|43", "59|39", "59|37", "59|38", "89|0", "89|2", "89|4", "89|6", "89|5", "31|39", "89|8", "31|38", "89|7", "31|37", "31|42", "31|43", "31|40", "31|41", "79|0", "40|8", "40|2", "79|8", "79|7", "40|0", "79|6", "79|5", "40|6", "79|4", "40|7", "40|4", "79|2", "40|5", "42|0", "42|5", "42|4", "42|2", "42|8", "42|7", "42|6", "37|39", "37|37", "37|38", "10|41", "10|42", "39|38", "39|37", "10|40", "39|39", "10|43", "66|21", "66|22", "66|23", "66|24", "66|25", "10|39", "10|38", "64|23", "10|37", "77|7", "10|10", "77|6", "77|5", "77|4", "77|8", "39|43", "39|42", "39|41", "77|2", "39|40", "77|0", "75|0", "36|0", "75|4", "75|5", "75|2", "75|8", "36|7", "36|8", "75|6", "36|5", "75|7", "36|6", "36|4", "36|2", "64|34", "64|38", "64|37", "71|2", "97|37", "71|5", "97|38", "71|4", "97|39", "71|7", "71|6", "64|39", "71|8", "77|41", "77|40", "77|43", "77|42", "77|37", "73|8", "77|38", "77|39", "73|0", "64|40", "64|41", "97|41", "73|2", "97|40", "97|43", "73|4", "97|42", "73|5", "73|6", "64|42", "71|0", "73|7", "64|43", "61|38", "61|39", "61|37", "29|40", "29|41", "29|42", "29|43", "98|5", "98|6", "98|7", "98|8", "91|40", "91|41", "91|42", "91|43", "98|0", "61|42", "98|2", "61|41", "61|40", "98|4", "61|43", "70|42", "70|41", "70|43", "91|39", "91|37", "91|38", "24|4", "24|5", "24|2", "24|8", "24|6", "24|7", "42|37", "42|38", "24|0", "42|39", "42|41", "42|40", "42|43", "91|23", "42|42", "63|38", "63|39", "29|38", "61|21", "29|39", "63|37", "29|37", "57|4", "57|5", "57|2", "57|8", "57|6", "57|7", "63|43", "63|42", "63|41", "57|0", "63|40", "35|39", "35|43", "35|42", "35|41", "35|40", "89|40", "52|39", "9|9", "52|38", "9|8", "9|7", "9|6", "9|5", "9|4", "52|37", "90|0", "89|45", "90|4", "89|44", "89|43", "90|2", "89|42", "89|41", "90|7", "90|8", "90|5", "90|6", "89|38", "89|39", "52|41", "9|2", "52|42", "52|43", "52|40", "70|38", "70|39", "89|31", "80|40", "89|30", "80|41", "70|37", "89|33", "80|42", "89|32", "80|43", "89|35", "89|34", "89|37", "70|30", "89|36", "89|27", "70|40", "89|28", "89|29", "89|22", "51|40", "89|21", "51|41", "9|10", "51|42", "51|43", "70|26", "89|26", "89|25", "89|24", "89|23", "35|38", "11|7", "51|38", "35|37", "11|6", "51|37", "11|5", "11|4", "51|39", "11|9", "11|8", "11|2", "73|27", "75|39", "95|43", "75|37", "95|42", "75|38", "95|41", "95|40", "96|2", "51|7", "51|6", "96|0", "51|5", "51|4", "96|6", "9|42", "96|5", "9|41", "51|2", "96|4", "2|42", "12|37", "2|43", "9|43", "51|0", "38|38", "2|40", "38|39", "2|41", "96|8", "75|43", "96|7", "75|42", "95|39", "75|41", "46|38", "75|40", "46|37", "95|37", "95|38", "38|37", "51|8", "46|39", "9|40", "12|39", "49|40", "12|38", "49|42", "49|41", "49|43", "38|43", "12|43", "9|37", "38|41", "12|41", "38|42", "12|42", "38|40", "46|41", "46|40", "46|43", "46|42", "72|43", "12|40", "9|39", "9|38", "49|39", "59|8", "49|38", "72|41", "49|37", "72|42", "22|0", "72|40", "22|2", "22|5", "22|4", "22|7", "22|6", "22|8", "72|39", "72|38", "72|37", "75|29", "26|2", "59|0", "72|24", "26|0", "59|2", "26|6", "59|5", "26|7", "59|4", "95|24", "26|4", "59|7", "26|5", "59|6", "46|0", "92|5", "46|6", "92|6", "46|7", "46|8", "92|4", "26|8", "46|2", "92|7", "46|4", "92|8", "46|5", "44|8", "92|2", "28|7", "44|5", "28|6", "44|4", "92|0", "44|7", "28|8", "44|6", "44|0", "44|2", "2|39", "23|38", "23|39", "73|39", "73|38", "73|37", "23|42", "23|41", "30|7", "30|8", "23|43", "73|42", "73|43", "73|40", "73|41", "30|2", "30|0", "30|5", "30|6", "2|38", "23|40", "30|4", "2|37", "40|37", "40|38", "40|39", "56|37", "44|40", "44|41", "76|29", "44|42", "44|43", "44|38", "44|37", "44|39", "28|2", "28|5", "28|4", "40|41", "40|40", "40|43", "40|42", "28|0", "56|43", "63|0", "56|41", "56|42", "63|2", "63|4", "63|5", "63|6", "63|7", "76|37", "63|8", "76|38", "76|39", "56|40", "76|40", "76|42", "76|41", "76|43", "56|39", "56|38", "94|39", "94|38", "94|37", "67|37", "94|41", "94|42", "94|40", "94|21", "67|23", "67|0", "67|2", "67|8", "67|7", "67|4", "67|6", "67|5", "71|38", "71|37", "71|39", "71|40", "71|41", "67|42", "67|41", "71|43", "67|43", "71|42", "67|40", "25|41", "25|42", "25|40", "67|38", "67|39", "25|43", "39|7", "33|5", "39|6", "33|4", "33|7", "39|8", "33|6", "33|8", "35|0", "35|2", "35|5", "35|4", "35|7", "35|6", "33|0", "25|37", "25|38", "35|8", "33|2", "25|39", "52|7", "52|8", "71|24", "94|6", "39|0", "94|5", "52|0", "94|8", "94|7", "52|2", "94|2", "39|4", "39|5", "52|4", "94|4", "39|2", "52|5", "52|6", "94|0", "57|39", "0|39", "0|37", "57|40", "0|38", "57|41", "57|42", "57|43", "28|41", "28|40", "28|43", "28|42", "61|4", "65|37", "61|2", "65|39", "65|38", "61|8", "98|24", "61|7", "61|6", "61|5", "28|38", "28|37", "65|40", "65|41", "65|42", "65|43", "61|0", "28|39", "69|6", "69|5", "69|8", "69|7", "33|42", "69|2", "33|43", "33|40", "69|4", "33|41", "69|0", "65|34", "28|25", "47|38", "47|39", "47|37", "33|39", "33|38", "33|37", "0|41", "0|40", "98|43", "0|43", "0|42", "98|41", "98|42", "98|40", "98|39", "98|38", "98|37", "78|6", "78|5", "78|4", "92|38", "78|8", "92|39", "78|7", "92|37", "92|32", "78|2", "78|0", "92|40", "41|8", "41|7", "36|37", "41|6", "41|5", "36|39", "41|4", "36|38", "41|2", "41|0", "30|39", "30|38", "79|38", "79|37", "30|37", "79|30", "36|42", "36|41", "47|43", "36|40", "30|41", "30|42", "30|43", "47|40", "94|43", "47|42", "36|43", "30|40", "47|41", "76|4", "76|2", "79|43", "76|0", "79|42", "96|43", "96|42", "79|41", "63|21", "96|41", "79|40", "63|22", "96|40", "76|8", "76|7", "76|6", "76|5", "37|2", "96|38", "37|0", "96|39", "37|6", "96|37", "37|7", "74|7", "37|4", "74|8", "37|5", "74|5", "74|6", "74|4", "79|39", "74|2", "74|0", "92|41", "70|8", "92|42", "37|8", "70|7", "96|33", "92|43", "72|0", "70|4", "70|6", "70|5", "72|5", "72|6", "70|0", "72|7", "72|8", "70|2", "72|2", "72|4", "41|39", "45|41", "41|38", "45|42", "45|43", "41|37", "45|40", "99|29", "90|27", "41|41", "45|39", "41|42", "45|38", "41|43", "41|40", "69|26", "90|39", "90|38", "90|37", "69|37", "69|38", "69|39", "22|43", "22|42", "22|41", "22|40", "69|41", "69|42", "69|40", "22|39", "22|37", "22|38", "26|39", "26|37", "26|38", "69|43", "45|37", "93|29", "93|28", "80|0", "80|2", "99|42", "93|25", "99|43", "93|24", "99|40", "93|27", "99|41", "93|26", "53|39", "93|21", "53|38", "53|37", "93|23", "93|22", "26|40", "99|39", "26|41", "99|38", "10|9", "26|42", "53|42", "99|37", "97|26", "93|30", "26|43", "53|43", "10|7", "53|40", "10|8", "53|41", "10|5", "10|6", "80|4", "80|5", "10|4", "80|6", "80|7", "10|2", "80|8", "97|2", "93|33", "93|34", "97|4", "93|31", "97|5", "93|32", "93|37", "93|38", "97|0", "93|35", "93|36", "62|37", "62|38", "93|39", "62|39", "97|6", "97|7", "97|8", "62|41", "99|8", "62|40", "62|43", "62|42", "99|5", "99|4", "99|7", "99|6", "99|0", "99|2", "93|41", "93|40", "93|42", "93|43", "93|44", "93|45", "8|7", "62|28", "8|6", "8|5", "8|4", "8|8", "8|2", "57|33", "57|38", "57|37", "0|8", "90|40", "90|41", "90|42", "90|43", "0|4", "0|5", "0|6", "0|7", "0|0", "0|2", "58|0", "58|7", "58|8", "58|5", "58|6", "58|4", "58|2", "56|8", "56|7", "56|6", "56|5", "56|4", "56|2", "56|0", "34|37", "34|40", "68|25", "11|42", "11|43", "11|40", "11|41", "34|43", "34|42", "34|41", "11|39", "11|38", "11|37", "34|38", "34|39", "8|40", "8|41", "8|42", "8|43", "68|37", "12|2", "68|38", "12|0", "32|41", "12|9", "32|42", "12|7", "32|40", "12|8", "12|5", "12|6", "32|43", "12|4", "32|39", "65|7", "32|38", "65|8", "32|37", "65|5", "65|6", "8|39", "8|37", "8|38", "65|0", "65|4", "65|2", "68|42", "68|43", "68|40", "68|41", "68|39", "45|4", "91|8", "11|10", "45|5", "45|6", "91|6", "45|7", "91|7", "45|8", "91|4", "91|5", "45|0", "45|2", "27|8", "58|38", "91|2", "27|7", "58|39", "91|0", "58|37", "58|40", "58|41", "58|42", "74|22", "74|21", "58|43", "21|2", "21|4", "21|0", "21|5", "21|6", "21|7", "21|8", "23|8", "23|7", "23|4", "23|6", "23|5", "23|0", "2|8", "2|7", "23|2", "2|6", "2|5", "2|4", "2|2", "27|5", "27|6", "27|4", "27|2", "27|0", "74|38", "74|39", "74|37", "74|42", "31|4", "25|0", "74|41", "31|5", "31|2", "74|43", "31|0", "74|40", "25|8", "25|7", "25|6", "25|5", "31|8", "25|4", "31|6", "25|2", "31|7", "740|2", "740|4", "740|6", "740|5", "740|8", "740|7", "780|38", "780|39", "740|0", "780|37", "780|31", "774|40", "741|39", "774|41", "774|42", "741|37", "774|43", "741|38", "740|9", "771|40", "757|40", "729|38", "729|37", "771|41", "771|42", "771|43", "729|39", "729|40", "757|38", "780|43", "729|41", "757|39", "780|40", "780|42", "729|42", "780|41", "729|43", "757|33", "774|39", "774|37", "774|38", "757|37", "738|4", "738|5", "738|6", "736|0", "738|7", "738|0", "764|12", "738|2", "764|10", "738|8", "738|9", "751|9", "751|8", "729|10", "751|7", "751|6", "729|12", "751|5", "751|4", "751|2", "741|12", "736|7", "751|0", "741|10", "736|6", "736|9", "794|21", "736|8", "757|43", "736|2", "736|5", "757|41", "736|4", "757|42", "764|23", "759|9", "759|8", "759|7", "759|6", "759|5", "759|4", "759|2", "759|0", "794|10", "794|12", "767|5", "764|34", "767|4", "764|37", "767|7", "767|6", "764|39", "767|9", "764|38", "767|8", "767|0", "767|2", "764|40", "763|2", "763|0", "763|7", "763|6", "764|43", "763|5", "764|42", "763|4", "764|41", "763|8", "763|9", "790|7", "778|2", "747|8", "790|6", "747|9", "790|9", "778|0", "747|6", "790|8", "747|7", "747|4", "747|5", "747|2", "790|0", "790|2", "790|5", "790|4", "747|0", "778|9", "778|8", "778|7", "778|6", "778|5", "778|4", "798|8", "798|9", "798|6", "798|7", "763|12", "763|10", "741|41", "741|40", "741|43", "741|42", "757|12", "798|0", "798|5", "798|4", "757|10", "798|2", "744|37", "744|38", "744|39", "768|43", "768|42", "739|10", "768|41", "742|2", "742|0", "742|5", "742|6", "742|4", "778|43", "778|42", "742|9", "778|41", "742|8", "778|40", "742|7", "768|37", "752|12", "744|41", "744|42", "744|40", "752|10", "721|6", "768|39", "721|7", "768|38", "721|8", "744|43", "721|9", "721|2", "721|5", "778|31", "721|4", "721|0", "778|38", "778|37", "739|12", "778|39", "768|40", "745|42", "747|10", "745|43", "745|40", "747|12", "745|41", "729|8", "729|9", "729|6", "729|7", "790|27", "729|0", "729|4", "752|42", "729|5", "752|43", "729|2", "752|40", "752|41", "727|2", "727|0", "727|7", "727|6", "727|5", "727|4", "727|9", "727|8", "739|43", "739|42", "739|41", "739|40", "752|37", "752|38", "752|39", "753|6", "753|5", "753|4", "753|2", "753|0", "753|9", "753|7", "753|8", "790|12", "790|10", "739|38", "739|39", "739|37", "794|43", "751|12", "794|42", "751|10", "796|12", "749|12", "796|10", "794|41", "794|40", "771|12", "790|40", "790|41", "790|42", "790|43", "747|42", "771|10", "747|43", "745|12", "745|10", "794|38", "794|37", "790|38", "790|37", "765|0", "765|2", "765|4", "790|39", "765|5", "765|7", "765|6", "765|9", "765|8", "749|10", "794|39", "796|2", "744|10", "796|33", "796|0", "744|12", "796|39", "796|7", "751|38", "796|6", "751|39", "796|37", "796|5", "796|38", "796|4", "751|37", "771|38", "749|38", "776|2", "771|39", "749|37", "776|5", "776|4", "749|39", "776|0", "745|37", "749|40", "771|0", "745|38", "749|41", "745|39", "749|42", "771|2", "749|43", "776|6", "776|7", "776|8", "771|37", "776|9", "771|7", "771|8", "796|8", "771|9", "796|9", "771|4", "771|5", "771|6", "796|40", "796|41", "796|42", "747|41", "796|43", "747|40", "771|24", "747|37", "749|8", "747|38", "749|9", "747|39", "749|6", "778|12", "749|7", "749|4", "749|5", "749|2", "749|0", "778|10", "767|38", "767|37", "767|39", "751|42", "751|41", "751|40", "758|42", "758|43", "751|43", "725|12", "725|10", "793|4", "795|9", "738|39", "735|40", "793|6", "738|38", "793|5", "738|37", "793|8", "793|7", "767|23", "793|9", "758|41", "758|40", "793|0", "793|2", "758|37", "758|38", "735|39", "795|0", "735|38", "738|42", "735|37", "795|2", "738|43", "738|40", "795|4", "738|41", "795|5", "758|39", "795|6", "795|7", "795|8", "725|41", "725|40", "732|8", "732|9", "732|6", "732|7", "734|2", "734|0", "734|7", "734|6", "732|0", "734|5", "725|39", "734|4", "732|5", "732|4", "734|9", "734|8", "732|2", "725|37", "725|38", "779|41", "767|43", "779|42", "767|42", "779|43", "767|41", "767|40", "735|10", "735|12", "732|12", "772|40", "732|10", "772|41", "772|42", "772|43", "766|25", "766|26", "738|10", "766|27", "766|28", "766|29", "766|21", "766|22", "766|23", "766|24", "772|37", "772|38", "774|8", "774|9", "774|7", "774|6", "774|5", "774|4", "772|39", "774|2", "774|0", "766|12", "766|10", "772|24", "767|12", "732|39", "732|38", "732|37", "767|10", "735|41", "735|42", "735|43", "766|43", "766|44", "766|45", "766|40", "766|41", "766|42", "732|43", "732|42", "732|41", "732|40", "738|12", "772|12", "772|10", "766|38", "766|39", "766|36", "766|37", "758|10", "766|34", "766|35", "758|12", "766|32", "766|33", "766|30", "766|31", "770|38", "770|37", "734|10", "770|39", "734|12", "792|12", "723|43", "753|12", "770|30", "723|42", "730|10", "723|41", "721|38", "721|37", "723|40", "721|39", "792|10", "763|38", "763|37", "763|39", "753|10", "730|12", "795|12", "723|6", "723|7", "795|10", "723|4", "723|5", "725|0", "723|8", "723|9", "770|42", "763|21", "770|43", "770|40", "770|41", "725|5", "725|4", "725|2", "725|9", "725|8", "723|2", "725|7", "725|6", "723|0", "763|22", "780|4", "757|4", "780|2", "757|5", "757|6", "780|0", "757|7", "757|8", "757|9", "795|24", "792|37", "792|38", "792|39", "780|9", "780|8", "780|7", "780|6", "780|5", "798|37", "798|38", "792|32", "798|39", "721|10", "721|12", "757|0", "757|2", "795|38", "795|39", "795|37", "789|0", "789|2", "792|43", "789|8", "792|42", "789|9", "792|41", "792|40", "789|4", "763|43", "789|5", "763|42", "789|6", "763|41", "789|7", "763|40", "798|40", "798|41", "798|42", "723|38", "798|43", "723|37", "723|39", "795|40", "795|42", "795|41", "795|43", "798|12", "791|43", "798|10", "791|40", "791|42", "791|41", "746|10", "746|12", "769|8", "769|9", "769|6", "769|7", "769|4", "769|5", "725|43", "769|2", "725|42", "753|40", "753|43", "753|42", "753|41", "769|0", "798|24", "774|12", "746|40", "774|10", "746|39", "730|39", "746|38", "730|38", "730|37", "761|0", "770|12", "753|39", "753|38", "761|2", "746|37", "761|5", "761|4", "761|7", "761|6", "770|10", "753|37", "761|9", "761|8", "774|22", "770|26", "745|5", "745|4", "745|7", "745|6", "774|21", "745|9", "745|8", "721|42", "721|43", "721|40", "745|0", "721|41", "745|2", "730|41", "730|40", "746|43", "730|43", "746|42", "730|42", "746|41", "773|43", "741|5", "766|9", "741|4", "768|0", "741|7", "741|6", "768|2", "766|5", "773|40", "741|0", "766|6", "773|41", "766|7", "773|42", "741|2", "766|8", "766|2", "768|8", "768|7", "766|4", "768|9", "741|8", "768|4", "741|9", "766|0", "768|6", "768|5", "797|26", "775|29", "791|37", "791|39", "791|38", "791|23", "762|7", "762|8", "762|5", "762|6", "764|9", "762|4", "764|7", "762|2", "764|8", "777|43", "762|0", "777|40", "777|41", "777|42", "775|39", "764|6", "775|38", "764|5", "764|4", "797|37", "797|38", "764|2", "797|39", "775|37", "764|0", "762|9", "777|38", "737|9", "777|39", "789|44", "773|27", "789|45", "777|37", "791|10", "789|42", "789|43", "791|12", "789|40", "789|41", "777|30", "737|2", "737|4", "737|5", "727|43", "737|6", "735|0", "727|42", "737|7", "727|41", "777|33", "737|8", "735|2", "727|40", "797|43", "735|4", "797|41", "735|6", "797|42", "735|5", "723|12", "735|8", "727|39", "797|40", "735|7", "723|10", "737|0", "727|37", "735|9", "727|38", "799|4", "773|38", "773|39", "799|2", "773|37", "799|0", "797|9", "797|8", "797|7", "777|21", "775|10", "797|5", "797|6", "775|12", "799|9", "797|4", "799|7", "799|8", "797|2", "799|5", "799|6", "797|0", "789|29", "789|28", "789|27", "789|26", "762|43", "789|21", "762|42", "762|41", "762|40", "722|0", "789|25", "789|24", "722|2", "789|23", "789|22", "722|4", "722|6", "722|5", "722|8", "722|7", "736|43", "736|42", "722|9", "734|43", "734|42", "734|41", "734|40", "727|12", "789|38", "762|37", "789|37", "762|39", "789|39", "727|10", "762|38", "789|30", "736|40", "773|12", "736|41", "789|32", "789|31", "773|10", "789|34", "759|42", "789|33", "759|41", "789|36", "759|40", "789|35", "736|38", "734|38", "736|37", "734|37", "734|39", "759|38", "759|39", "759|37", "736|39", "791|2", "726|5", "726|6", "791|4", "726|7", "726|8", "762|28", "726|2", "791|0", "726|4", "746|5", "791|9", "777|0", "746|6", "752|9", "746|4", "752|8", "791|6", "726|9", "746|9", "791|5", "777|4", "791|8", "746|7", "791|7", "777|2", "746|8", "777|8", "752|2", "777|7", "777|6", "752|0", "777|5", "746|2", "752|6", "752|7", "746|0", "752|4", "777|9", "752|5", "775|40", "726|0", "775|43", "775|41", "775|42", "762|10", "762|12", "756|0", "756|2", "789|12", "758|0", "789|10", "736|12", "758|5", "758|4", "736|10", "758|2", "758|9", "759|12", "758|8", "758|7", "759|10", "758|6", "756|4", "756|5", "756|6", "756|7", "756|8", "756|9", "779|10", "742|10", "779|12", "742|12", "724|10", "724|12", "772|2", "742|40", "772|4", "772|5", "772|6", "772|7", "742|43", "772|8", "742|42", "772|9", "742|41", "772|0", "759|43", "770|0", "770|2", "770|5", "770|4", "770|7", "770|6", "770|9", "770|8", "779|37", "779|30", "742|37", "779|39", "742|39", "779|38", "742|38", "779|40", "728|41", "728|42", "728|43", "728|40", "777|10", "777|12", "728|39", "728|38", "728|37", "775|0", "775|2", "775|4", "728|9", "775|6", "728|8", "775|5", "728|7", "728|6", "728|5", "728|4", "728|2", "728|0", "775|7", "775|8", "775|9", "724|43", "724|42", "724|41", "724|40", "779|6", "779|5", "779|4", "779|9", "779|8", "779|7", "724|39", "779|2", "724|38", "779|0", "724|37", "740|12", "740|10", "740|37", "740|38", "740|39", "728|10", "722|12", "792|0", "739|0", "722|10", "792|2", "739|2", "739|4", "740|43", "739|5", "739|6", "792|9", "740|41", "739|7", "792|8", "740|42", "739|8", "739|9", "740|40", "792|5", "792|4", "792|7", "792|6", "794|6", "794|7", "999|2", "794|8", "794|9", "794|2", "794|4", "794|5", "794|0", "733|4", "722|40", "722|41", "733|2", "722|42", "722|43", "731|9", "733|0", "728|25", "731|7", "731|8", "731|6", "731|5", "731|4", "733|9", "733|8", "731|2", "733|7", "733|6", "731|0", "733|5", "728|12", "722|38", "722|39", "722|37", "769|43", "769|42", "731|40", "731|43", "733|10", "731|41", "733|12", "731|42", "769|37", "769|38", "769|39", "737|43", "765|34", "768|12", "768|10", "765|39", "765|38", "769|41", "765|37", "769|40", "799|40", "799|41", "773|9", "799|42", "799|43", "769|26", "768|25", "773|0", "733|37", "733|38", "733|39", "773|4", "773|2", "773|8", "773|7", "773|6", "773|5", "769|12", "769|10", "765|12", "765|10", "733|41", "733|42", "733|40", "733|43", "737|37", "737|39", "737|38", "737|40", "737|41", "737|42", "793|10", "793|12", "799|10", "799|12", "780|10", "737|12", "780|12", "737|10", "761|42", "761|43", "761|40", "761|41", "799|37", "799|39", "799|38", "765|40", "765|41", "765|42", "765|43", "799|29", "776|41", "776|40", "731|39", "731|38", "726|38", "731|37", "726|39", "776|43", "776|42", "726|37", "761|21", "756|40", "756|41", "756|42", "756|43", "726|42", "726|41", "726|40", "724|2", "726|43", "730|6", "730|7", "724|0", "730|4", "730|5", "730|2", "730|0", "776|38", "776|37", "776|39", "761|38", "761|37", "761|39", "793|40", "793|42", "724|9", "756|39", "793|41", "793|44", "724|7", "756|37", "793|43", "724|8", "756|38", "724|5", "793|45", "724|6", "730|9", "724|4", "730|8", "797|12", "731|12", "726|12", "793|38", "726|10", "793|39", "776|29", "797|10", "731|10", "793|37", "793|36", "793|35", "793|34", "793|33", "793|32", "793|31", "793|30", "776|10", "744|9", "776|12", "793|27", "744|6", "793|28", "744|5", "793|29", "744|8", "744|7", "761|10", "761|12", "756|12", "756|10", "793|24", "793|23", "744|2", "793|26", "793|25", "744|4", "793|22", "793|21", "744|0"];
var arrYear = [2015,2014,2013];
var arrBTEByear = [2015,2014,2013,2012,2011,2010,2009,2008,2007,2006];
var college_map = {}; 
var college_eligibility;
var collegeSVG;

function reloadYear(board_id){
	$('#ssc_year').children('option').remove();
	$('#ssc_year').append($("<option></option>")
        		   .attr("value","")
        		   .text("Select Year")); 
	var year=[]

    if(board_id==19)
    	year=arrBTEByear;
    else
    	year=arrYear;
	 
	 
	 for (var i=0; i<year.length; i++) {
			$('#ssc_year')
	         	.append($("<option></option>")
	 			.attr("value",year[i])
	 			.text(year[i]));
		}

}
 function validateSscInfo(){
	 
	 enableField("submitButton");
	 $("#submitButton").css('background-color', 'buttonface');
		
	 clearHtml("personal_info_div","response_div");
	 clearColegeSelection();
	 $("#response_div").hide();
	 
	 
		
	 var valid=validateField("ssc_roll","ssc_board","ssc_year","ssc_reg");
	 if(valid==true){
	 $("#personal_info_div").html("<img src='/board/resources/images/239.gif' />");
		 $.ajax({
			 	url: 'applicationInfoCheck_TT.action',		
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
			 	url: 'applicationInfoCheckNew.action',		
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
			 	url: 'getApplicantForCancel.action',		
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
	var valid=validateField("mobile_number","confirm_mobile_number","quotaYN");
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
		if($("#quotaYN").val()=='Y'){
			if(document.getElementById("quota_ff").checked==false){
				$("#quota_type_selection").css("border", "2px solid red");
				valid=false;
			}
			else{
				$("#quota_type_selection").css("border", "none");
			}
		}
		else {
				
				$("#quota_type_selection").css("border", "none");	 			
		}
		
	}
	if(valid){
		$("#college_selection_div").show();
		scrollToBottom();
	}
	
}

function fetchCollegeList(search_type){
	clearSelectBox("college_id","shift_id","version_id","group_id");
	clearField("education_quota");
	
	if(search_type=="by_district"){
		 if($("#district_id").val()=="" || $("#helper_board_id").val()==""){
			return;
		 }
	 }
	 if(search_type=="by_eiin"){
		 if($("#eiin").val()=="")
			 return;
	 }
	 
	 $.ajax({
		 	url: 'getColleges.action',		
		 	type: 'POST',
		 	data: {eiin:$("#eiin").val(),district_id:$("#district_id").val(),helper_board_id:$("#helper_board_id").val(),college_search_type:search_type},
		 	success: function(data) {
		 		setCollegeSelectBoxData(data,search_type);
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
		$("#district_id").val(dist_id);
		$("#helper_board_id").val(helper_board_id);
		$("#college_id").val($("#eiin").val());
		getCollegeSVGInfo($("#eiin").val());
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


function getCollegeSVGInfo(eiin){
	$("#eiin").val(eiin);
	clearSelectBox("shift_id","version_id","group_id");
	clearField("available_seat","education_quota");
	
	if(eiin=="") return;	
	 $.ajax({
	  url: 'getCollegeSVG.action',
	  type: 'POST',
	  data: {eiin:eiin},
	  success: function(data) {		
		collegeSVG=data;  				
		var shift = data.shift;
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
	setAvailableSeat("");
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
	
	setAvailableSeat("");
}
function loadSQ(){
	
	
	emptySelectBox("special_quota");
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
	var availableSeatObj=collegeSVG.available_seat;
	var availableSeat=availableSeatObj[shift_id+","+version_id+','+group_id];	
	setAvailableSeat(availableSeat);
}
function setAvailableSeat(availableSeat){
	
	if(document.getElementById("available_seat"))
		$("#available_seat").val(availableSeat);
}

//Add more button
//'O' - > Open User; 'A' - > A for Applicant's Account ; 'R' -> Release Slip Section; 'S' -> Second Phase Application
function addChoice(from_where){
 	
 	var eiin=$("#college_id").val();
 	var own_eiin=$("#p_eiin").val();
 	var college_name=$("#college_id option:selected").text();
 	var shift_id=$("#shift_id").val();
 	var shift_name=$("#shift_id option:selected").text();
 	var version_id=$("#version_id").val();
 	var version_name=$("#version_id option:selected").text();
 	var group_id=$("#group_id").val();
 	var group_name=$("#group_id option:selected").text();
 	var special_quota=$("#special_quota").val();
 	var education_quota="";
 	if(from_where=="R" || from_where=="S"){
 		education_quota=$("#education_quota").val();
 		if(education_quota==""){
 			alert("Please select Education Quota");
 			return;
 		}
 		var available_seat=$("#available_seat").val();
 		if(parseInt(available_seat,10)<=0){
 			alert("Sorry, the selected college (Shift,Version,Group) has no more available seat.");
 			return;
 		}
 	}
 	var valid_code=validateSelection(eiin,college_name,shift_id,shift_name,version_id,version_name,group_id,group_name);
 	var valid_eligibility=validateEligibility(eiin,own_eiin,shift_id,version_id,group_id,special_quota,$("#p_gender").val(),$("#p_gpa").val());

 	var ssc_hsc_group=$("#p_group_id").val()+"|"+group_id;
 	
 	if(valid_code==1){
 		 alert("Please select College and Shift, Version, College Information.");
 		 return;
 	}
 	else if(valid_code==2){
		 alert("Duplicate Selection Found.");
		 return;
	}
 	else if(arrSscHscGroupMap.indexOf(ssc_hsc_group)<0){
 		alert("Sorry, you are not allowed to apply for the selected group.");
 		return;
 	}
 	else if(valid_eligibility==1){
		 alert("Sorry, the selected choice is not valid for you (either gender or other critaria does not match).");
		 return;
	}
 	else if(valid_eligibility==2 || valid_eligibility==3 || valid_eligibility==4 || valid_eligibility==5){
		 alert("You don't meet Minimum GPA Requirement for the selected choice.");
		 return;
	}
 
  
 	college_map[eiin] = eiin;
 	var distinctCollegeCount=getDistinctCollegeCount();
 	if(from_where=="R" || from_where=="S"){ 		
 		 if(distinctCollegeCount>10){
 			alert("You have already selected 10 different colleges.");
 			return;
 		}
 	}
 	else if(distinctCollegeCount<5)
	{
		alert("Please select at least 5(Five) different colleges for application submission.");
		delete college_map[eiin];
		return;
	}

 	var rowCount = $('#selection_row_table tr').length;
    var rowIndex=rowCount;
 
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
    
    row.push("<td align='center'><input type='text' name='choice["+rowIndex+"].priority' id='priority_"+rowIndex+"' value='"+rowIndex+"' style='width:30px;text-align:center;'></td>",
		  "<td align='center'>",
		  		"<input type='hidden' name='choice["+rowIndex+"].eiin' value='"+eiin+"' />",
		  		"<input type='hidden' name='choice["+rowIndex+"].shift_id' value='"+shift_id+"' />",
		  		"<input type='hidden' name='choice["+rowIndex+"].version_id' value='"+version_id+"' />",
		  		"<input type='hidden' name='choice["+rowIndex+"].group_id' value='"+group_id+"' />",
		  		"<input type='hidden' name='choice["+rowIndex+"].special_quota' value='"+special_quota+"' />");
    
    if(from_where=='R' || from_where=='S')
    	row.push("<input type='hidden' name='choice["+rowIndex+"].education_quota' value='"+education_quota+"' />");    
    
    if(from_where=='A' || from_where=='S')
    	row.push("<input type='hidden' name='choice["+rowIndex+"].via' value='W' />");

    row.push("<img id='img_delete_"+rowIndex+"' src='/board/resources/images/trash.gif' style='cursor:pointer' onclick=\"deleteChoice("+rowIndex+",'"+from_where+"')\"/>",
		  "</td>",
		  "</tr>");
    ///board/resources/images/cross_16_16.png

    var row_str=row.join("");
 	$('#selection_row_table tr').last().after(row_str);

}
 	
 
 
function deleteChoice(row_id,from_where){

	var priority=$("#priority_"+row_id).val();
	
	var $rows = $("#selection_row_table tr").each(function(index) {
		 
		 if(index>0 && isInteger($("#priority_"+index).val())){
			 
			 if($("#priority_"+index).val()>priority)
			 $("#priority_"+index).val($("#priority_"+index).val()-1);
		 }
	});
	
	$('table#selection_row_table tr#row_'+row_id).remove();
	
	orderSerialAndCorrectHtmlElementNames(from_where);
	
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
		arr_index=index-1;
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
 
function validateSelection(eiin,college_name,shift_id,shift_name,version_id,version_name,group_id,group_name){
	var add_data_key = [];
	var existing_data_key=[];
	var duplicate=false;
 	var valid_collegeSelection=validateField("college_id","shift_id","version_id","group_id");
 	var valid_eiinSelection=validateField("eiin","shift_id","version_id","group_id"); 	
    if(valid_collegeSelection==false && valid_eiinSelection==false)
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
 }
 
function submitApplication(){
	
	var rowCount = $('#selection_row_table tr').length;
	if(rowCount<2){
		alert("Please select atleast one college for application submission.");
		return;
	}
	if($("#security_code").val()==""){
		alert("Please provide security code.");
		return;
	}
	var fields=["p_student_name","p_ssc_roll","p_ssc_reg","p_passing_year","p_gpa","p_mobile_number"];	
	var priorityOk=isPriorityOk('O');
	if(priorityOk==false){
		alert("Please correct your priority and then submit again.");
		reutrn;
	}	
	enableField.apply(this,fields);
	orderSerialAndCorrectHtmlElementNames();
	var data = $('#applicationForm').serializeArray();
	$("#submitOverlay").html("<img style='position: absolute;left: 270px;right: 0;top: 100px;' src='/board/resources/images/239.gif'>");
	$("#submitOverlay").show();
	disableField("submitButton");
	$("#submitButton").css('background-color', 'gray');
	
	cleanPrintHtmlData();
	$.ajax({
		url: 'submitApplication.action',
		type: 'POST',
		data: data,
		success: function(data) {
			$("#submitOverlay").hide();
			enableField("submitButton");
			$("#submitButton").css('background-color', 'buttonface');
			if(data.status=='OK'){
				setDataForHtmlReport(data.application_id,data.password);
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
	
	disableField.apply(this,fields);
}
function submitNewApplication(){
	
	var distinctCollegeCount=getDistinctCollegeCount();
	if(distinctCollegeCount>10){
		alert("You can select maximum 10(ten) different colleges.");
		return;
	}
 	else if(distinctCollegeCount<5)
	{
		alert("Please select at least 5(Five) different colleges for application submission.");
		return;
	}
 	
	var fields=["p_student_name","p_ssc_roll","p_ssc_reg","p_passing_year","p_gpa","p_mobile_number"];	
	var priorityOk=isPriorityOk('S');
	if(priorityOk==false){
		alert("Please correct your priority and then submit again.");
		reutrn;
	}	
	enableField.apply(this,fields);
	orderSerialAndCorrectHtmlElementNames('S');
	var data = $('#applicationForm').serializeArray();
	$("#submitOverlay").html("<img style='position: absolute;left: 270px;right: 0;top: 100px;' src='/board/resources/images/239.gif'>");
	$("#submitOverlay").show();
	disableField("submitButton");
	$("#submitButton").css('background-color', 'gray');
	
	cleanPrintHtmlData();
	$.ajax({
		url: 'submitNewApplication.action',
		type: 'POST',
		data: data,
		success: function(data) {
			$("#submitOverlay").hide();
			enableField("submitButton");
			$("#submitButton").css('background-color', 'buttonface');
			if(data.status=='OK'){
				setDataForHtmlReportNew(data.application_id,data.password);
				clearHtml("personal_info_div");
				clearColegeSelection();
								
				$("#response_div").html(getNewSuccessResponseContent(data.sscInfo.student_name,data.application_id,data.password,data.payment_status));
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
	
	disableField.apply(this,fields);
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
		url: 'updateChoiceList.action',
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
		alert("You can select maximum 10(ten) different colleges for application submission.");
		return;
	}
 	else if(distinctCollegeCount<5)
	{
		alert("Please select at least 5(five) different colleges for application submission.");
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
		url: 'newUpdateChoiceList.action',
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
		alert("You can select maximum 10(ten) different colleges for application submission.");
		return;
	}
 	else if(distinctCollegeCount<5)
	{
		alert("Please select at least 5(five) different colleges for application submission.");
		return;
	}
	
	var priorityOk=isPriorityOk('R');
	if(priorityOk==false){
		alert("Please correct your priority and then submit again.");
		reutrn;
	}	
	
	var c = confirm("Are you sure to submit?\n(You may select upto 10 different colleges)");
	if (c == false) {
	    return;
	} 
	
	orderSerialAndCorrectHtmlElementNames('A');
	var data = $('#applicationForm').serializeArray();
	//console.log(data);
	$.ajax({
		url: 'updateReleaseList.action',
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
		url: 'updateEducationQuotaInfo.action',
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

function setDataForHtmlReport(application_id,password){
	
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

function getSuccessResponseContent(applicant_name,application_id,password){
	 var div_content = [];
	 div_content.push(
			  "Dear Applicant,<br/><br/>",
			  "We have successfully received your online Application.<br/>",
			  "<b>Applicant Name</b> : "+applicant_name+"</br>",
			  "<b>Application ID</b> : "+application_id+"</br>",
			  "<b>Password</b> : "+password+"</br>",
			  "<br/>",
			  "<font style='color:red;font-weight:bold'>To Confirm your Application, please pay 150 Taka by a Teletalk Mobile - go to message option and</font><br/>",
			  "<font style='color:red;font-weight:bold'>type : <font color='green'>CAD</font> {SPACE} <font color='green'>WEB</font> {SPACE}  <font color='green'>"+application_id+"</font> and send to 16222</font>",
			  "<br/>Please use the above Application Id and Password to log into your account.<br/><br/>",
			  "<a href='../board/login.action' style='font-weight:bold;'>Click here</a> to login.<br/><br/>",
			  "<input type='button' value='Print Version' class='htmlReportBtn' onclick=\"javascript:void window.open('applicantCopyHtmlReport','1433313485446','width=780,height=800,toolbar=0,menubar=0,location=0,status=1,scrollbars=1,resizable=1,left=0,top=0');\" />");

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
			  "<a href='../board/login.action' style='font-weight:bold;'>Click here</a> to login.<br/><br/>",
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
	//var all_text_fields=["ssc_roll","p_ssc_roll","p_board_name","p_board_id","p_passing_year","p_student_name","p_father_name","p_mother_name","p_birth_date","p_gender_name","p_gender","p_gpa","p_gpa_exc4th","mobile_number","confirm_mobile_number","eiin"];
	//clearField.apply(this,all_text_fields);
	//var select_fields=["ssc_year","ssc_board","quotaYN","quota_type"];
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
	clearSelectBox("college_id","shift_id","version_id","group_id");
	clearField("education_quota");
	$('#selection_row_table tr:gt(0)').remove();
	$("#college_selection_div").hide();
	college_map={};
	college_eligibility=[];
	collegeSVG={};
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
function getNewCapchaServlet()
{
	
	$.ajaxFileUpload
	(
		{
			url:'/board/CaptchaServlet.cap?'+new Date(),
			secureuri:false,
			dataType: 'html',
			contentType: "image/png",
			success: function (data, status)
			{
						document.getElementById("captchaImage").innerHTML = "";
						document.getElementById("captchaImage").innerHTML=  "<img id= 'ferretp1' src='"+data+"' width='120' height='25'>";
						 		
			},
			error: function (data, status, e)
			{
				alert(e);
			}
		}
	)
	
	return false;

} 

function getNewCapchaServlet1(){
	$.ajax({
		url:'/board/CaptchaServlet.cap?'+new Date(),
		secureuri:false,
		dataType: 'arraybuffer',
		contentType: "image/png",
		success: function(data) {
		alert(data);
		var blb = new Blob([data], {type: 'image/png'});
	    var url = (window.URL || window.webkitURL).createObjectURL(blb);
	    document.getElementById("cimage").src = url;
	    
	},
	error: function(e) {
	}
	});

}
function validateMobileNumber(mobileNumber) {
    var mob = /^(016|019|013|017|018|015)[0-9]{8}$/;

    if (mob.test(mobileNumber) == false) {
      return false;
    }
    return true;
  }