package patmat

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import patmat.Huffman._

@RunWith(classOf[JUnitRunner])
class HuffmanSuite extends FunSuite {
  trait TestTrees {
    val t1 = Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5)
    val t2 = Fork(Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5), Leaf('d',4), List('a','b','d'), 9)
  }

  test("weight of a larger tree") {
    new TestTrees {
      assert(weight(t1) === 5)
    }
  }

  test("chars of a larger tree") {
    new TestTrees {
      assert(chars(t2) === List('a','b','d'))
    }
  }

  test("string2chars(\"hello, world\")") {
    assert(string2Chars("hello, world") === List('h', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd'))
  }
  
  test("string \"abcaabcde\" have 'a' 3 times"){
      assert(times("abcaabcde".toList).contains(('a',3)))
  }

  test("makeOrderedLeafList for some frequency table") {
    assert(makeOrderedLeafList(List(('t', 2), ('e', 1), ('x', 3))) === List(Leaf('e',1), Leaf('t',2), Leaf('x',3)))
  }
  
  test("singletone with zeor element"){
      assert(!singleton(List()))
  }
  
  test("singletone with one element"){
      assert(singleton(List(makeCodeTree(Leaf('a', 1), Leaf('b', 2)))))
  }
  
  test("singletone with two element"){
      assert(!singleton(List(makeCodeTree(Leaf('a', 1), Leaf('b', 2)), Leaf('b', 2))))
  }

  test("combine of some leaf list") {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    assert(combine(leaflist) === List(Fork(Leaf('e',1),Leaf('t',2),List('e', 't'),3), Leaf('x',4)))
  }
  
  test("createCodeTree"){
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    assert(createCodeTree("ettxxxx".toList) === makeCodeTree(Fork(Leaf('e',1),Leaf('t',2),List('e', 't'),3), Leaf('x',4)))
  }
  
  test("createCodeTree of \"Hello\""){
    assert(createCodeTree("Hello".toList) === Fork(Leaf('l',2),Fork(Leaf('H',1),Fork(Leaf('o',1),Leaf('e',1),List('o', 'e'),2),List('H', 'o', 'e'),3),List('l', 'H', 'o','e'),5))
  }
  
  test("test `decodedSecret` to equals \"huffmanestcool\""){
      assert(decodedSecret.mkString == "huffmanestcool")
  }

  test("decode and encode a very short text should be identity") {
    new TestTrees {
      assert(decode(t1, encode(t1)("ab".toList)) === "ab".toList)
    }
  }
  
  test("decode and quickEncode a very short text should be identity") {
    new TestTrees {
      assert(decode(t1, quickEncode(t1)("ab".toList)) === "ab".toList)
    }
  }
  
  test("test encode using frenchCode on \"huffmanestcool\" to match `secret`") {
       assert(encode(frenchCode)("huffmanestcool".toList) === secret)
  }
  
  test("test quickEncode using frenchCode on \"huffmanestcool\" to match `secret`") {
       assert(quickEncode(frenchCode)("huffmanestcool".toList) === secret)
  }
}
