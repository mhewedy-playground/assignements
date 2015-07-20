package patmat

import patmat.Huffman.{ CodeTree, Leaf, Fork }

object Test {
    //
    Huffman.weight(Leaf('a', 3))                  //> res0: Int = 3

    val fork = Huffman.makeCodeTree(Leaf('a', 3), Leaf('b', 5))
                                                  //> fork  : patmat.Huffman.Fork = Fork(Leaf(a,3),Leaf(b,5),List(a, b),8)
    Huffman.weight(fork)                          //> res1: Int = 8
    Huffman.chars(fork)                           //> res2: List[Char] = List(a, b)

    val fork2 = Huffman.makeCodeTree(fork, Leaf('z', 10))
                                                  //> fork2  : patmat.Huffman.Fork = Fork(Fork(Leaf(a,3),Leaf(b,5),List(a, b),8),L
                                                  //| eaf(z,10),List(a, b, z),18)
    Huffman.chars(fork2)                          //> res3: List[Char] = List(a, b, z)
    Huffman.weight(fork2)                         //> res4: Int = 18

    //"Abbas Ibn Fernas".toList

    //Huffman.times(List('a', 'b', 'a'))

    Huffman.timesMatches('b', List(('a', 3), ('b', 5)))
                                                  //> res5: List[(Char, Int)] = List((a,3), (b,6))

    List(('a', 3), ('b', 5))                      //> res6: List[(Char, Int)] = List((a,3), (b,5))

    ('a', 3) :: ('b', 5) :: Nil                   //> res7: List[(Char, Int)] = List((a,3), (b,5))

    Huffman.times("abcaabcde".toList)             //> res8: List[(Char, Int)] = List((e,1), (d,1), (c,2), (b,2), (a,3))

}