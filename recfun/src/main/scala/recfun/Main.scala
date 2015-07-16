package recfun
import common._

object Main {
    def main(args: Array[String]) {
        
        println("Pascal's Triangle")
        for (row <- 0 to 10) {
            for (col <- 0 to row)
                printf(pascal(col, row) + " ")
            println()
        }
        
    }

    /**
     * Exercise 1
     */
    def pascal(c: Int, r: Int): Int =
        if (c == 0 || c == r) 1 else
            pascal(c, r - 1) + pascal(c - 1, r - 1)

    /**
     * Exercise 2
     */
    def balance(chars: List[Char]): Boolean = {
        def loop(acc: Int, next: Char, chars: List[Char]): Boolean = {
            if (acc < 0) false
            else if (chars.isEmpty)
                acc + eval(next) == 0
            else
                loop(acc + eval(next), chars.head, chars.tail)
        }
        def eval(ch: Char) = if (ch == '(') 1 else if (ch == ')') -1 else 0
        loop(0, chars.head, chars.tail)
    }

    /**
     * Exercise 3
     */
    def countChange(money: Int, coins: List[Int]): Int = ???

}
