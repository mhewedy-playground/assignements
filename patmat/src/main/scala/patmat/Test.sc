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

    //Huffman.timesMatches('b', List(('a', 3), ('b', 5)))

    List(('a', 3), ('b', 5))                      //> res5: List[(Char, Int)] = List((a,3), (b,5))

    ('a', 3) :: ('b', 5) :: Nil                   //> res6: List[(Char, Int)] = List((a,3), (b,5))

    val pairList = Huffman.times("xxxxxxddabccccefffezzzzz".toList)
                                                  //> pairList  : List[(Char, Int)] = List((z,5), (e,2), (f,3), (c,4), (b,1), (a,1
                                                  //| ), (d,2), (x,6))

    val trees = Huffman.makeOrderedLeafList(pairList)
                                                  //> trees  : List[patmat.Huffman.Leaf] = List(Leaf(b,1), Leaf(a,1), Leaf(e,2), L
                                                  //| eaf(d,2), Leaf(f,3), Leaf(c,4), Leaf(z,5), Leaf(x,6))

    val combined = Huffman.combine(trees)         //> combined  : List[patmat.Huffman.CodeTree] = List(Fork(Leaf(b,1),Leaf(a,1),Li
                                                  //| st(b, a),2), Leaf(e,2), Leaf(d,2), Leaf(f,3), Leaf(c,4), Leaf(z,5), Leaf(x,6
                                                  //| ))

    Huffman.createCodeTree("xxxxxxddabccccefffezzzzz".toList)
                                                  //> res7: patmat.Huffman.CodeTree = Fork(Fork(Fork(Leaf(d,2),Leaf(f,3),List(d, f
                                                  //| ),5),Leaf(z,5),List(d, f, z),10),Fork(Leaf(x,6),Fork(Fork(Fork(Leaf(b,1),Lea
                                                  //| f(a,1),List(b, a),2),Leaf(e,2),List(b, a, e),4),Leaf(c,4),List(b, a, e, c),8
                                                  //| ),List(x, b, a, e, c),14),List(d, f, z, x, b, a, e, c),24)

}