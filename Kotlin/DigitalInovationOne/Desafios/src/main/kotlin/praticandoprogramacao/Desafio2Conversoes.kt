package praticandoprogramacao

import java.awt.List

fun main(){
        try {
            var n = readLine()!!.toString()
            var op = 0
            if (n.contains('x')){
                op = converteHexparaDec(n)
            }else{
                op = n.toInt()
            }
            while (op>=0){
                if(isHex(n)){
                    println(converteHexparaDec(n))
                }else{
                    println(converteDecparaHex(n))
                }
                n = readLine()!!.toString()
                if (n == "-1"){
                    break
                }
                if (n.contains('x')){
                    op = converteHexparaDec(n)
                }
            }

        }catch (e: Exception){
            println(e.printStackTrace())
        }

}
fun isHex(n: String): Boolean{
    try {
        if (n.length>2 && n.get(1)=='x'){
            return true
        }else{
            return false
        }
    }catch (e: Exception){
        return false
    }
}

fun converteHexparaDec(n: String): Int{
    val list = n.split('x')
    val dec = list[1]

    val hexaDecimalN = dec.toString()

    var i = hexaDecimalN.length - 1
    var decimalN: Long = 0
    var base = 1

    while(i >= 0) {
        val charAtPos = hexaDecimalN[i]

        val lastDigit = if((charAtPos >= '0') && (charAtPos <= '9')) {
            charAtPos - '0'
        } else if((charAtPos >= 'A') && (charAtPos <= 'F')) {
            charAtPos.toInt() - 55
        } else if((charAtPos >= 'a') && (charAtPos <= 'f')) {
            charAtPos.toInt() - 87
        } else {
            0
        }

        decimalN += lastDigit * base
        base *= 16

        i--
        }
    return decimalN.toInt()
}
fun converteDecparaHex(n: String): String{
    val dec = n.toInt()
    val hex = Integer.toHexString(dec).toUpperCase()
    return "0x"+hex
}
